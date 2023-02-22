using BFFinderCoreApp.Models;
using StartUp.Views;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using BFFinderCoreApp.Repository;
using StartUp.Services;
using BFFinderCoreApp.Services;
using StartUp.Utils;

namespace StartUp.Controllers
{
    public class SettingsController
    {
        User currUser = null;
        User newUser = new User(); 
        SettingsView sv = null;
        string profilImage = null;
        ClientToSv _client;
        Main main;

        public SettingsController(User user, SettingsView sv, Main mainView)
        {
            currUser = user;
            this.sv = sv;
            _client = ClientToSv.getInstance;
            main = mainView;
            sv.saveButton.Click += new EventHandler(EditData);
            sv.UploadPhotoButton.Click += new EventHandler(NewPhoto_Click);
            InitializeData();
        }

        private void InitializeData()
        {
            sv.FNameTXT.Text = currUser.FirstName;
            sv.SNameTXT.Text = currUser.SecondName;
            sv.VarstaTXT.Text = currUser.Varsta.ToString();
            sv.EmailTXT.Text = currUser.Email;

            if(currUser.Sex == 1)
            {
                sv.mRadioButton.Checked = true;
                sv.fRadioButton.Checked = false;
            }
            else
            {
                sv.fRadioButton.Checked = true;
                sv.mRadioButton.Checked = false;
            }
        }

        private void EditData(object o, EventArgs e)
        {
            newUser.FirstName = sv.FNameTXT.Text;
            newUser.SecondName = sv.SNameTXT.Text;
            newUser.Email = sv.EmailTXT.Text;
            newUser.Id = currUser.Id;
            newUser.Scor = currUser.Scor;
            newUser.Varsta = Convert.ToInt32(sv.VarstaTXT.Text);
            string oldPass = EncryptService.ENCRYPT(sv.oldPassTXT.Text);
            string newPass = EncryptService.ENCRYPT( sv.newPassTXT.Text );

            newUser.ProfileImage = currUser.ProfileImage;
            newUser.Username = currUser.Username;

            if(profilImage != null)
            {
                newUser.ProfileImage = profilImage;
            }

            if (sv.mRadioButton.Checked == true)
            {
                newUser.Sex = 1;
            }
            else
            {
                newUser.Sex = 0;
            }


            if (_client.isConnected())
            {
                User currentUser;
                // stergem orice urma de mesaje ramase cumva in interfata de client
                _client.clearAll();
                UserService us = new UserService();

                us.UpdateUserRequestFunct(newUser,oldPass, newPass, _client.getClient());

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

                if (response == "UpdateSucces!")
                {
                    MessageBox.Show("User Updated Succesfully");
                    main.nameLabel.Text = newUser.FirstName + " " + newUser.SecondName;
                    main.SetUser(newUser);
                    main.roundedPictureBox1.Image = BytesConverter.ConvertStringToBitmapImage(newUser.ProfileImage);
                }
                else
                {
                    MessageBox.Show("Something went wrong while Updating..");
                    return;
                }

            }
        }

        private void NewPhoto_Click(object sender, EventArgs e)
        {
            using (OpenFileDialog ofd = new OpenFileDialog())
            {
                Image myImg;
                ofd.Filter = "txt files (*.jpg)|*.jpg|All files (*.*)|*.*";

                if (ofd.ShowDialog() == DialogResult.OK)
                {

                    myImg = Image.FromFile(ofd.FileName);
                    profilImage = BytesConverter.ConvertImageToBytes(myImg);
                }
            }
        }

    }
}
