using BFFinderCoreApp.Models;
using BFFinderCoreApp.Services;
using StartUp.Services;
using StartUp.Utils;
using StartUp.Views;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace StartUp.Controllers
{
    internal class LoginController
    {
        ClientToSv _client;
        string username, password;
        Login log;
        public LoginController( string username, string password, Login log)
        {
             _client = ClientToSv.getInstance;
            this.username = username;
            this.password = password;
            this.log = log;
        }


        public void Login()
        {
            if (_client.isConnected())
            {
                User currentUser;
                // stergem orice urma de mesaje ramase cumva in interfata de client
                _client.clearAll();

                LoginService loginService = new LoginService();

                loginService.LoginRequestFunct(username,EncryptService.ENCRYPT( password ), _client.getClient());

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

                if (response == "LogInSucces!")
                {
                    currentUser = new User();
                    currentUser.Id = Convert.ToInt32(msg[2]);
                    currentUser.FirstName = Convert.ToString(msg[3]);
                    currentUser.SecondName = Convert.ToString(msg[4]);
                    currentUser.Varsta = Convert.ToInt32(msg[5]);
                    currentUser.Sex = Convert.ToInt32(msg[6]);
                    currentUser.Email = Convert.ToString(msg[7]);
                    currentUser.Scor = Convert.ToInt32(msg[8]);
                    currentUser.ProfileImage = Convert.ToString(msg[9]);
                    currentUser.Username = Convert.ToString(msg[10]);
                    currentUser.Facultate = Convert.ToString(msg[11]);

                    Main main = new Main(currentUser);
                    MainController mc = new MainController(main);
                    main.nameLabel.Text = currentUser.FirstName + " " + currentUser.SecondName;
                    main.roundedPictureBox1.Image = BytesConverter.ConvertStringToBitmapImage(currentUser.ProfileImage);
                    main.log = log;
                    main.Show();
                    log.Visible = false;
                    

                    MessageBox.Show("Connected");
                }
                else
                {
                    MessageBox.Show("Wrong username or password!");
                    return;
                }
            }
            else
            {
                MessageBox.Show("Server down! Please try again later!");

                return;
            }
        }
    }
}
