using BFFinderCoreApp.Models;
using BFFinderCoreApp.Services;
using StartUp.Models.Controls;
using StartUp.Services;
using StartUp.Utils;
using StartUp.Views;
using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Message = BFFinderCoreApp.Models.Message;

namespace StartUp.Controllers
{
    public class ChatController
    {
        User currentUser = null;
        ChatView chatView = null;
        ClientToSv _client;
        IDButton button;
        Thread readMessageThread = null;


        public ChatController(User user, ChatView chatView)
        {
            this.currentUser = user;
            this.chatView = chatView;
            this._client = ClientToSv.getInstance;
            this.chatView.SendButtonMessage.Click += new EventHandler(SendMessage_Click);
            this.chatView.SendButtonMessage.SendButtonImage.Click += new EventHandler(SendMessage_Click);
        }

        public void LoadGroups()
        {
            GroupService gs = new GroupService();
            ChatView chatView = ChatView.GetInstance();
            chatView.chatingPanel.Visible = false;

            // stergem orice urma de mesaje ramase cumva in interfata de client
            _client.clearAll();

            gs.GroupLoadRequestFunct(currentUser, _client.getClient());
            while (_client.returnMessage == null)
            {
                //wait a bit
            }
            if (_client.returnMessage.Count == 0)
            {
                long counter = 0;
                while (_client.returnMessage.Count == 0)
                {
                    counter++;
                    if(counter >= 100000000)
                    {
                        _client.clearAll();
                        gs.GroupLoadRequestFunct(currentUser, _client.getClient());
                        counter = 0;
                        Debug.WriteLine("Eroare La nivel de incarcare! " + _client.returnMessage.Count);
                    }
                    ;
                }
            }
            string noMsg = _client.returnMessage[0];
            if (_client.returnMessage.Count != Convert.ToInt32(noMsg) + 1)
            {
                while (_client.returnMessage.Count != Convert.ToInt32(noMsg) + 1)
                {
                    if (_client.returnMessage.Count > Convert.ToInt32(noMsg) + 1)
                    {
                        _client.clearAll();
                        gs.GroupLoadRequestFunct(currentUser, _client.getClient());
                        Debug.WriteLine(_client.returnMessage.Count);
                    }
                    Debug.WriteLine("Eroare La nivel de incarcare toate mesajele "+_client.returnMessage.Count);
                }
            }

            List<string> msg = _client.returnMessage;
            string response = msg[1];

            if (response == "LoadGroupsSucces!")
            {
                if (chatView.FlowGroups.Controls.Count > 0)
                {
                    chatView.FlowGroups.Controls.Clear();
                }


                for(int i = 2; i < msg.Count; i = i + 2)
                {
                    IDButton groupButton = new IDButton();
                    groupButton.Text = msg[i+1];
                    groupButton.ID = Convert.ToInt32(msg[i]);
                    groupButton.AutoSize = true;
                    groupButton.Width = chatView.FlowGroups.Width-5;
                    groupButton.Click += new EventHandler(ButtonGroup_Click);
                    
                    chatView.FlowGroups.Controls.Add(groupButton);
                }
            }
            else
            {
                MessageBox.Show("Something went wrong while Updating..");
                return;
            }

        }
        
        private void ButtonGroup_Click(object o, EventArgs e)
        {
            button = (IDButton)o;
            GroupService gs = new GroupService();
            // stergem orice urma de mesaje ramase cumva in interfata de client
            _client.clearAll();

            gs.LoadMessagesRequestFunct(currentUser,button.ID, _client.getClient());

            chatView.group_name.ID = button.ID;


            while (_client.returnMessage == null)
            {
                //wait a bit
            }
            if (_client.returnMessage.Count == 0)
            {
                while (_client.returnMessage.Count == 0)
                {
                }
            }

            string noMsg = _client.returnMessage[0];
 
           
            if (_client.returnMessage.Count != Convert.ToInt32(noMsg) + 1)
            {
                
                while (_client.returnMessage.Count != Convert.ToInt32(noMsg) + 1)
                {
                    if (_client.returnMessage.Count > Convert.ToInt32(noMsg) + 1)
                    {
                        _client.clearAll();
                        gs.LoadMessagesRequestFunct(currentUser, button.ID, _client.getClient());
                        Debug.WriteLine(_client.returnMessage.Count);
                    }
                }
            }

            List<string> msg = _client.returnMessage;
            string response = msg[1];

            if (response == "LoadMsgSucces!")
            {
                if (chatView.FlowMessages.Controls.Count > 0)
                {
                    chatView.FlowMessages.Controls.Clear();
                }
                chatView.group_name.Text = button.Text;
                chatView.chatingPanel.Visible = true;


                for(int i=msg.Count-1; i> 2; i = i - 4)
                {
                    Message message = new Message();
                    message.userNameSender = Convert.ToString(msg[i - 3]);
                    message.photoSender = Convert.ToString(msg[i - 2]);
                    message.message = Convert.ToString(msg[i - 1]);
                    message.hourSent = Convert.ToString(msg[i]);

                    IDMessageBox iDMessageBox = new IDMessageBox();
                    
                    iDMessageBox.AutoSize = false;
                    iDMessageBox.Width = 600;
                    iDMessageBox.Height = 200;
                    iDMessageBox.textMessage.Text = message.message;
                    iDMessageBox.textMessage.AutoSize = false;
                    iDMessageBox.textMessage.MaximumSize = new System.Drawing.Size(550, 500);
                    iDMessageBox.textMessage.AutoSize = true;
                    iDMessageBox.MinimumSize = new System.Drawing.Size(590, 0);
                    iDMessageBox.MaximumSize = new System.Drawing.Size(591, 5000);
                    iDMessageBox.AutoSize = true;

                    iDMessageBox.Nume.Text = message.userNameSender;
                    iDMessageBox.ProfilPic.Image = BytesConverter.ConvertStringToBitmapImage(message.photoSender);
                    iDMessageBox.TimeLabel.Text = message.hourSent;

                    chatView.FlowMessages.HorizontalScroll.Maximum = 0;
                    chatView.FlowMessages.WrapContents = false;
                    chatView.FlowMessages.AutoScroll = true;
                    chatView.FlowMessages.Controls.Add(iDMessageBox);

                }

                if(readMessageThread == null || !readMessageThread.IsAlive)
                {
                    readMessageThread = new Thread(new ThreadStart(ReadingMessagesThread));
                    readMessageThread.Start();
                }
            }
            else
            {
                MessageBox.Show("Something went wrong while Loading messages.."+response);
                return;
            }

        }

        private void SendMessage_Click(object o, EventArgs e)
        {
            MessageService ms = new MessageService();
            string myPhoto = currentUser.ProfileImage;
            string myFullName = currentUser.FirstName +" "+ currentUser.SecondName;
            string message = chatView.InputMessage.Text;
            chatView.InputMessage.Text = "";

            ms.SendMessageRequestFunct(message, currentUser.Id, chatView.group_name.ID, myPhoto, myFullName, _client.getClient());

        }

        private void ReadingMessagesThread()
        {
            while (true)
            {
                _client.clearRM2();

                while (_client.returnMessageGroup == null)
                {
                    //wait a bit
                }
                if (_client.returnMessageGroup.Count == 0)
                {
                    while (_client.returnMessageGroup.Count == 0)
                    {

                    }
                }


                string noMsg = _client.returnMessageGroup[0];

                if (_client.returnMessageGroup.Count != Convert.ToInt32(noMsg) + 1)
                {
                    while (_client.returnMessageGroup.Count != Convert.ToInt32(noMsg) + 1)
                    {
                        if (_client.returnMessageGroup.Count > Convert.ToInt32(noMsg) + 1)
                        {
                            MessageBox.Show("I failed to receive The message!");
                        }
                    }
                }

                List<string> msg = _client.returnMessageGroup;
                string response = msg[1];

                if (response == "ReceivedBroadcastMessage!")
                {

                    for (int i = 2; i < msg.Count; i = i + 5)
                    {
                        string message = msg[i];
                        string fullName = msg[i + 1];
                        string photo = msg[i + 2];
                        int group = Convert.ToInt32(msg[i + 3]);
                        string ora = Convert.ToString(msg[i + 4]);

                        if (group == chatView.group_name.ID)
                        {
                            IDMessageBox iDMessageBox = new IDMessageBox();

                            iDMessageBox.AutoSize = false;
                            iDMessageBox.Width = 600;
                            iDMessageBox.Height = 200;
                            iDMessageBox.textMessage.Text = message;
                            iDMessageBox.textMessage.AutoSize = false;
                            iDMessageBox.textMessage.MaximumSize = new System.Drawing.Size(550, 500);
                            iDMessageBox.textMessage.AutoSize = true;
                            iDMessageBox.MinimumSize = new System.Drawing.Size(590, 0);
                            iDMessageBox.MaximumSize = new System.Drawing.Size(591, 5000);
                            iDMessageBox.AutoSize = true;

                            iDMessageBox.Nume.Text = fullName;
                            iDMessageBox.TimeLabel.Text = ora;
                            iDMessageBox.ProfilPic.Image = BytesConverter.ConvertStringToBitmapImage(photo);

                            chatView.Invoke((MethodInvoker)(() => chatView.FlowMessages.Controls.Add(iDMessageBox)));
                        }


                    }
                }
                else if (response == "SendMsgFailed")
                {
                    MessageBox.Show("Message didn't delivered");
                }
            }
        }
    }
}
