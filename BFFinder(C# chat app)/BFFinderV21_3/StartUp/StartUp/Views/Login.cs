using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using BFFinderCoreApp.Repository;
using BFFinderCoreApp.Models;
using BFFinderCoreApp.Services;
using StartUp.Controllers;
using StartUp.Services;

namespace StartUp.Views
{
    public partial class Login : Form
    {
        public Login()
        {
            InitializeComponent();
           
        }

        public Button GetLoginButton()
        {
            return LoginButton;
        }

        public TextBox GetUserNameTextBox()
        {
            return UserInput;
        }

        public TextBox GetPasswordTextBox()
        {
            return PasswordInput;
        }

        private void Login_Load(object sender, EventArgs e)
        {
           
        }

        private void LoginButton_Click(object sender, EventArgs e)
        {
            string username = UserInput.Text;
            string password = PasswordInput.Text;
            
            LoginController loginController = new LoginController(username, password,this);

            loginController.Login();
            
        }

        private void register_Click_1(object sender, EventArgs e)
        {
            Register reg = new Register();
            RegisterController register = new RegisterController(reg, this);
            reg.Show();
        }
    }



}
