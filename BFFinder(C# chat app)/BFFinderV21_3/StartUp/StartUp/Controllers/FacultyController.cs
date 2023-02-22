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

namespace StartUp.Controllers
{
    public class FacultyController
    {
        NoUniversityView nv;
        UniversityView uv;
        User currUser;
        ClientToSv _client;
        Main main;
        Thread readMessageThread = null;

        public FacultyController(Main main, NoUniversityView nv, UniversityView uv, User user)
        {
            this.nv = nv;
            this.uv = uv;
            this.currUser = user;
            _client = ClientToSv.getInstance;
            this.main = main;
            this.uv.sendButton2.Click += new EventHandler(SendMessage_Click);
            this.uv.sendButton2.SendButtonImage.Click += new EventHandler(SendMessage_Click);
            this.uv.FlowMessages.Visible = true;
  
        }

        public void LoadMsg()
        {
            _client.clearAll2();

            MessageService ms = new MessageService();
            // stergem orice urma de mesaje ramase cumva in interfata de client
            
            ms.FacultyRequestFunct(currUser, _client.getClient());

            while (_client.returnMessage2 == null)
            {
                //wait a bit
            }
            if (_client.returnMessage2.Count == 0)
            {
                while (_client.returnMessage2.Count == 0)
                {

                }
            }
            string noMsg = _client.returnMessage2[0];

            if (_client.returnMessage2.Count != Convert.ToInt32(noMsg) + 1)
            {
                while (_client.returnMessage2.Count != Convert.ToInt32(noMsg) + 1)
                {
                    if(_client.returnMessage2.Count == 0)
                    {
                        ms.FacultyRequestFunct(currUser, _client.getClient());
                    }
                    if (_client.returnMessage2.Count > Convert.ToInt32(noMsg) + 1)
                    {
                        Debug.WriteLine("ERROR FacultateLOAD " + _client.returnMessage2.Count + " " + noMsg);
                        _client.clearAll2();
                        ms.FacultyRequestFunct(currUser, _client.getClient());
                    }
                    Debug.WriteLine("ERROR FacultateLOAD " + _client.returnMessage2.Count + " " + noMsg);
                }
            }

            List<string> msg = _client.returnMessage2;
            string response = msg[1];

            if (response == "LoadFacultyMsgSucces!")
            {
                if (uv.FlowMessages.Controls.Count > 0)
                {
                    uv.FlowMessages.Controls.Clear();
                }

                for (int i = msg.Count - 1; i > 2; i = i - 5)
                {
                    BFFinderCoreApp.Models.Message message = new BFFinderCoreApp.Models.Message();
                    message.userNameSender = Convert.ToString(msg[i - 4]);
                    message.photoSender = Convert.ToString(msg[i - 3]);
                    message.message = Convert.ToString(msg[i - 2]);
                    message.hourSent = Convert.ToString(msg[i - 1]);
                    message.faculty = Convert.ToString(msg[i]);

                    FacultyMessageBox iDMessageBox = new FacultyMessageBox();

                    iDMessageBox.AutoSize = false;
                    iDMessageBox.Width = 800;
                    iDMessageBox.Height = 220;
                    iDMessageBox.textMessage.Text = message.message;
                    iDMessageBox.textMessage.AutoSize = false;
                    iDMessageBox.textMessage.MaximumSize = new System.Drawing.Size(800, 500);
                    iDMessageBox.textMessage.AutoSize = true;
                    iDMessageBox.MinimumSize = new System.Drawing.Size(800, 0);
                    iDMessageBox.MaximumSize = new System.Drawing.Size(800, 5000);
                    iDMessageBox.AutoSize = true;

                    iDMessageBox.Nume.Text = message.userNameSender;
                    iDMessageBox.ProfilPic.Image = BytesConverter.ConvertStringToBitmapImage(message.photoSender);
                    iDMessageBox.TimeLabel.Text = message.hourSent;
                    iDMessageBox.Faculty.Text = message.faculty;

                    uv.FlowMessages.HorizontalScroll.Maximum = 0;
                    uv.FlowMessages.WrapContents = false;
                    uv.FlowMessages.AutoScroll = true;
                    uv.FlowMessages.Controls.Add(iDMessageBox);

                }


                if (readMessageThread == null)
                {
                    readMessageThread = new Thread(new ThreadStart(ReadingMessagesThread));
                    readMessageThread.Start();
                }
            }
            else
            {
                MessageBox.Show("Something went wrong while Loading messages.." + response);
                return;
            }

        }

        public void SwitchPanel()
        {
            if (currUser.Facultate.Equals(""))
            {

                if (main.SwitchPanel.Controls.Count > 0)
                {
                    main.SwitchPanel.Controls.Clear();
                }
                main.SwitchPanel.Controls.Add(nv);
                main.SwitchPanel.Visible = true;
            }
            else
            {

                if (main.SwitchPanel.Controls.Count > 0)
                {
                    main.SwitchPanel.Controls.Clear();
                }
                main.SwitchPanel.Controls.Add(uv);
                main.SwitchPanel.Visible = true;
                LoadMsg();

            }
        }

        private void SendMessage_Click(object o, EventArgs e)
        {
            MessageService ms = new MessageService();
            string myPhoto = currUser.ProfileImage;
            string myFullName = currUser.FirstName + " " + currUser.SecondName;
            string message = uv.InputMessage.Text;
            uv.InputMessage.Text = "";

            ms.SendFacultyMSGRequestFunct(message, currUser.Id, currUser.Facultate, myPhoto, myFullName, _client.getClient());

        }

        private void ReadingMessagesThread()
        {
            while (true)
            {
                _client.clearRM3();

                while (_client.returnMessageGroup2 == null)
                {
                    //wait a bit
                }
                if (_client.returnMessageGroup2.Count == 0)
                {
                    while (_client.returnMessageGroup2.Count == 0)
                    {
                    }
                }


                string noMsg = _client.returnMessageGroup2[0];

                if (_client.returnMessageGroup2.Count != Convert.ToInt32(noMsg) + 1)
                {
                    while (_client.returnMessageGroup2.Count != Convert.ToInt32(noMsg) + 1)
                    {
                        if (_client.returnMessageGroup2.Count > Convert.ToInt32(noMsg) + 1)
                        {
                            _client.clearRM3();
                            Debug.WriteLine(_client.returnMessageGroup2.Count);
                            MessageBox.Show("Error in receiving message!");
                        }
                    }
                }

                List<string> msg = _client.returnMessageGroup2;
                string response = msg[1];

                if (response == "ReceivedBroadcastFacultyMessage!")
                {

                    for (int i = 2; i < msg.Count; i = i + 5)
                    {
                        string message = msg[i];
                        string fullName = msg[i + 1];
                        string photo = msg[i + 2];
                        string faculty = Convert.ToString(msg[i + 3]);
                        string ora = Convert.ToString(msg[i + 4]);

                        FacultyMessageBox iDMessageBox = new FacultyMessageBox();
                        iDMessageBox.AutoSize = false;
                        iDMessageBox.Width = 800;
                        iDMessageBox.Height = 200;
                        iDMessageBox.textMessage.Text = message;
                        iDMessageBox.textMessage.AutoSize = false;
                        iDMessageBox.textMessage.MaximumSize = new System.Drawing.Size(800, 500);
                        iDMessageBox.textMessage.AutoSize = true;
                        iDMessageBox.MinimumSize = new System.Drawing.Size(800, 0);
                        iDMessageBox.MaximumSize = new System.Drawing.Size(800, 5000);
                        iDMessageBox.AutoSize = true;
                        iDMessageBox.Faculty.Text = faculty;

                        iDMessageBox.Nume.Text = fullName;
                        iDMessageBox.TimeLabel.Text = ora;
                        iDMessageBox.ProfilPic.Image = BytesConverter.ConvertStringToBitmapImage(photo);

                        uv.Invoke((MethodInvoker)(() => uv.FlowMessages.Controls.Add(iDMessageBox)));

                    }
                }
                else if (response == "SendFacultyMsgFailed")
                {
                    MessageBox.Show("Message didn't delivered");
                }
            }
        }
    }
}
