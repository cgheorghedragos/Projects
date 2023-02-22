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
    public class RegisterController
    {
        User currUser = new User();
        Register reg = null;
        string profilImage = null;
        ClientToSv _client;
        Login log = null;

        public RegisterController(Register reg,Login log)
        {
            this.reg = reg;
            this.log = log;
            log.Visible = false;
            _client = ClientToSv.getInstance;
            reg.backButton.Click += new EventHandler(Back_Click);
            reg.registerButton.Click += new EventHandler(Register_Click);
            reg.upPic.Click += new EventHandler(AddPhoto_Click);
            InitData();

        }


        private void InitData()
        {
            reg.rb1.Checked = true;
            reg.rb21.Checked = true;
            reg.rb31.Checked = true;
            reg.rb41.Checked = true;
            reg.rb51.Checked = true;

        }


        private int calculateScor()
        {
            int sum = checkRadioButton(reg.rb1, reg.rb2, reg.rb3, reg.rb4, reg.rb5);
            sum += checkRadioButton(reg.rb51, reg.rb52, reg.rb53, reg.rb54, reg.rb55);
            sum += checkRadioButton(reg.rb21, reg.rb22, reg.rb23, reg.rb24, reg.rb25);
            sum += checkRadioButton(reg.rb31, reg.rb32, reg.rb33, reg.rb34, reg.rb35);
            sum += checkRadioButton(reg.rb41, reg.rb42, reg.rb43, reg.rb44, reg.rb45);
            
            return sum/5;
        }
        private int checkRadioButton(RadioButton r1, RadioButton r2, RadioButton r3, RadioButton r4, RadioButton r5)
        {
            if (r1.Checked == true) return 1;
            if (r2.Checked == true) return 2;
            if (r3.Checked == true) return 3;
            if (r4.Checked == true) return 4;
            if (r5.Checked == true) return 5;
            return 0;
        }
        
        private void Register_Click(object o, EventArgs e)
        {
            currUser.FirstName = reg.num.Text;
            currUser.SecondName = reg.pren.Text;
            currUser.Varsta = Convert.ToInt32( reg.varsta.Text );
            currUser.Email = reg.email.Text; 
            currUser.Username = reg.username.Text;
            currUser.Scor = calculateScor();

            if (reg.mSex.Checked)
            {
                currUser.Sex = 1;
            }
            else
            {
                currUser.Sex = 0;
            }
            currUser.ProfileImage = profilImage;
            currUser.Facultate = reg.facultate.Text;

            string password = EncryptService.ENCRYPT( reg.pass.Text );

            if (TextFieldChecker.CheckRegisterField(currUser, password) )
            {
                if(!TextFieldChecker.CheckUserAlreadyExist(currUser, _client))
                {
                    RegisterService regService = new RegisterService();

                    if (_client.isConnected())
                    {

                        // stergem orice urma de mesaje ramase cumva in interfata de client
                        _client.clearAll();


                        regService.RegisterRequestFunct(password, currUser, _client.getClient());

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

                        if (response == "RegisterSucces!")
                        {
                            MessageBox.Show("User Registered Succesfully");

                        }
                        else
                        {

                            MessageBox.Show("Something went wrong while Registering.. ERROR:  " + response);
                            return;
                        }
                    }

                    }
                else
                {
                    MessageBox.Show("User Already Exist");
                }
                
            }
            else
            {
                MessageBox.Show("Invalid inputs!");
            }
        }

        private void Back_Click(object o, EventArgs e)
        {
            log.Visible = true;
            reg.Close();
        }

        private void AddPhoto_Click(object sender, EventArgs e)
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
