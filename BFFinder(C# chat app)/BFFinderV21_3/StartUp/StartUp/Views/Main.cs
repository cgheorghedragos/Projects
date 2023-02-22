using BFFinderCoreApp.Models;
using BFFinderCoreApp.Services;
using StartUp.Controllers;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Runtime.InteropServices;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace StartUp.Views
{
    public partial class Main : Form
    {
        
        
        User currentUser;

        public Login log;
       
        public Main(User user)
        {
            currentUser = user;
            InitializeComponent();
        }


        public Main()
        {
            InitializeComponent();
        }

        private void Main_Load(object sender, EventArgs e)
        {

            if(SwitchPanel.Controls.Count > 0)
            {
                SwitchPanel.Controls.Clear();
            }

            /*
            ChatView chatView = ChatView.GetInstance();
            chatView.TopLevel = false;
            chatView.Dock = DockStyle.Fill;
            Button button = new Button();
            button.Text = "myButton";
            button.AutoSize = true;

            Button button2 = new Button();
            button2.Text = "myButton2";
            button2.AutoSize = true;

            chatView.FlowGroups.Controls.Add(button);
            chatView.FlowGroups.Controls.Add(button2);

            HomeView homeView = new HomeView();
            homeView.Dock = DockStyle.Fill;
            homeView.TopLevel = false;
            homeView.Visible = true;

            SwitchPanel.Controls.Add(homeView);
            SwitchPanel.Visible = true;
            chatView.Visible = true;
            University_Load();
            */
        }

        private void MenuPanel_Paint(object sender, PaintEventArgs e)
        {

        }

   

        #region Mouse Move
        public const int WM_NCLBUTTONDOWN = 0xA1;
        public const int HT_CAPTION = 0x2;

        [DllImportAttribute("user32.dll")]
        public static extern int SendMessage(IntPtr hWnd, int Msg, int wParam, int lParam);
        [DllImportAttribute("user32.dll")]
        public static extern bool ReleaseCapture();
        private void movablePanel_MouseMove(object sender, MouseEventArgs e)
        {
            if (e.Button == MouseButtons.Left)
            {
                ReleaseCapture();
                SendMessage(Handle, WM_NCLBUTTONDOWN, HT_CAPTION, 0);
            }
        }





        #endregion


        private void roundedPictureBox1_Click(object sender, EventArgs e)
        {

        }

        private void ExitButton_Click(object sender, EventArgs e)
        {
            Application.Exit();
        }

        private void Minimalize_Click(object sender, EventArgs e)
        {
            this.WindowState = FormWindowState.Minimized;
        }

        private void ChatButton_Load(object sender, EventArgs e)
        {

        }

        private void AddGroupButton_Load(object sender, EventArgs e)
        {

        }




        private void UniversityBUtton_Load(object sender, EventArgs e)
        {

        }

        public User GetUser()
        {
            return currentUser;
        }

        public void SetUser(User user)
        {
            this.currentUser = user;
        }

        private void AvatarPanel_Paint(object sender, PaintEventArgs e)
        {

        }
    }
}
