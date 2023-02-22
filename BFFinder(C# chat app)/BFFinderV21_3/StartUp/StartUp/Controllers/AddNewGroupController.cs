using BFFinderCoreApp.Models;
using BFFinderCoreApp.Services;
using StartUp.Services;
using StartUp.Views;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace StartUp.Controllers
{
    public class AddNewGroupController
    {
        ClientToSv _client;
        AddNewGroupView adv = null;
        User currentUser = null;
        List<string> groups;

        public AddNewGroupController(AddNewGroupView adv, User currentUser) {
            this.adv = adv;
            this.currentUser = currentUser;
            _client = ClientToSv.getInstance;
            InitData();
            ButtonEvents();
        }

        private void ButtonEvents()
        {
            adv.crtButton.Click += new EventHandler(CreateButtonPanel_Click);
            adv.jgButton.Click += new EventHandler(JoinButtonPanel_Click);
            adv.joinnGrup.Click += new EventHandler(JoinButton_Click);
            adv.CreateGrup.Click += new EventHandler(CreateGroup_Click);
        }
        private void InitData()
        {
            adv.jgButton.Enabled = true;
            adv.crtButton.Enabled = true;
            adv.joinPanel.Visible = false;
            adv.createPanel.Visible = false;
        }

        private void JoinButtonPanel_Click(object o, EventArgs e)
        {
            adv.jgButton.Enabled = false;
            adv.crtButton.Enabled = true;
            adv.joinPanel.Visible = true;
            adv.createPanel.Visible = false;

            GroupService gs = new GroupService();


            _client.clearAll();
            gs.AllGroupRequestFunct(currentUser, _client.getClient());

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
                }
            }

            List<string> msg = _client.returnMessage;
            string response = msg[1];


            if (response == "AllGroupsSucces!")
            {
               
                groups = new List<string>();

                for(int i = 2; i<msg.Count;  i++)
                {
                    groups.Add(msg[i]);
                }

                adv.GroupList.Items.Clear();

                for (int i = 0; i<groups.Count; i=i+3)
                {
                    string idGrup = groups[i];
                    string numeGrup = groups[i + 1];
                    string numarPers = groups[i + 2];

                    string addingListboxString = numeGrup + "   Capacity: " + numarPers + "/10";

                    adv.GroupList.Items.Add(addingListboxString);

                }
            }
            else
            {
                MessageBox.Show("Something went wrong while Loading all Groups.." +response);
                return;
            }

        }

        private void CreateButtonPanel_Click(object o, EventArgs e)
        {
            adv.jgButton.Enabled = true;
            adv.crtButton.Enabled = false;
            adv.joinPanel.Visible = false;
            adv.createPanel.Visible = true;

        }

        private void JoinButton_Click(object o, EventArgs e)
        {
            int selectedGroup = adv.GroupList.SelectedIndex;

            if(selectedGroup != -1)
            {
                string idGrup = groups[selectedGroup * 3];
                GroupService gs = new GroupService();
                _client.clearAll();
                gs.JoinGroupRequestFunct(currentUser, idGrup, _client.getClient());

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
                    }
                }

                List<string> msg = _client.returnMessage;
                string response = msg[1];

                if (response == "JoinGroupSucces!")
                {
                    MessageBox.Show("Joined to Group Succesfully!");
                    adv.joinPanel.Visible = false;
                    adv.jgButton.Enabled = true;
                }
                else
                {
                    MessageBox.Show("Something went wrong while join Groups..");
                    return;
                }

            }
        }

        private void CreateGroup_Click(object o, EventArgs e)
        {
            string numeGrup = adv.textGrup.Text;
            GroupService gs = new GroupService();
            _client.clearAll();
            gs.CreateGroupRequestFunct(numeGrup, _client.getClient());

            while(_client.returnMessage == null)
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
                }
            }

            List<string> msg = _client.returnMessage;
            string response = msg[1];

            if (response == "CreateGroupSucces!")
            {
                MessageBox.Show("Created Group Succesfully!");
            }
            else
            {
                MessageBox.Show("Something went wrong while Creating Groups..");
                return;
            }

        }


    }
}
