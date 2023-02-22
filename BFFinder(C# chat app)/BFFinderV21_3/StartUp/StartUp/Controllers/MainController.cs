using StartUp.Views;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace StartUp.Controllers
{
    public class MainController
    {
        Main main = null;
        ChatView cv = ChatView.GetInstance();
        NoUniversityView nv = new NoUniversityView();
        UniversityView uv = new UniversityView();
        ChatController chatController;
        FacultyController fc;


        public MainController(Main main)
        {
            this.main = main;
            ButtonsControlLoad();
            PanelSelectedChanged(0);

            HomeView hv = new HomeView();
            hv.Dock = DockStyle.Fill;
            hv.TopLevel = false;
            hv.Visible = true;

            if(main.SwitchPanel.Controls.Count > 0)
            {
                main.SwitchPanel.Controls.Clear();
            }
            main.SwitchPanel.Controls.Add(hv);
            main.SwitchPanel.Visible = true;

            chatController = new ChatController(main.GetUser(), cv);
            fc = new FacultyController(main, nv, uv, main.GetUser());
            PanelSelectedChanged(0);
        }

        private void ButtonsControlLoad()
        {
            SettingsButton_Load();
            ChatButton_Load();
            HomeButton_Load();
            AddNewGroupButton_Load();
            University_Load();
            Disconnect_Load();
        }

        private void SettingsButton_Load()
        {
            main.SettingsButton.textLabel.Click += new System.EventHandler(SettingsButton_MouseClick);
            main.SettingsButton.PicImage.Click += new System.EventHandler(SettingsButton_MouseClick);
            main.SettingsButton.panelSelected.Click += new System.EventHandler(SettingsButton_MouseClick);
            main.SettingsButton.Click += new System.EventHandler(SettingsButton_MouseClick);
        }

        private void ChatButton_Load()
        {
            main.ChatButton.textLabel.Click += new System.EventHandler(ChatButton_Click);
            main.ChatButton.PicImage.Click += new System.EventHandler(ChatButton_Click);
            main.ChatButton.panelSelected.Click += new System.EventHandler(ChatButton_Click);
            main.ChatButton.Click += new System.EventHandler(ChatButton_Click);
        }

        private void HomeButton_Load()
        {
            main.HomeButton.textLabel.Click += new System.EventHandler(HomeButton_Click);
            main.HomeButton.PicImage.Click += new System.EventHandler(HomeButton_Click);
            main.HomeButton.panelSelected.Click += new System.EventHandler(HomeButton_Click);
            main.HomeButton.Click += new System.EventHandler(HomeButton_Click);
            PanelSelectedChanged(0);
        }

        private void AddNewGroupButton_Load()
        {
            main.AddGroupButton.textLabel.Click += new System.EventHandler(AddNewGroup_Click);
            main.AddGroupButton.PicImage.Click += new System.EventHandler(AddNewGroup_Click);
            main.AddGroupButton.panelSelected.Click += new System.EventHandler(AddNewGroup_Click);
            main.AddGroupButton.Click += new System.EventHandler(AddNewGroup_Click);
        }

        private void University_Load()
        {
            main.UniversityBUtton.textLabel.Click += new System.EventHandler(UniversityButton_MouseClick);
            main.UniversityBUtton.PicImage.Click += new System.EventHandler(UniversityButton_MouseClick);
            main.UniversityBUtton.panelSelected.Click += new System.EventHandler(UniversityButton_MouseClick);
            main.UniversityBUtton.Click += new System.EventHandler(UniversityButton_MouseClick);
        }

        private void Disconnect_Load()
        {
            main.DisconnectButton.textLabel.Click += new System.EventHandler(Disconnect_Click);
            main.DisconnectButton.PicImage.Click += new System.EventHandler(Disconnect_Click);
            main.DisconnectButton.panelSelected.Click += new System.EventHandler(Disconnect_Click);
            main.DisconnectButton.Click += new System.EventHandler(Disconnect_Click);
        }

        private void SettingsButton_MouseClick(object sender, EventArgs e)
        {
            if (main.SwitchPanel.Controls.Count > 0)
            {
                main.SwitchPanel.Controls.Clear();
            }

            SettingsView sv = SettingsView.GetInstance();
            SettingsController settingsController = new SettingsController(main.GetUser(), sv, main);
            sv.Dock = DockStyle.Fill;
            sv.TopLevel = false;
            sv.Visible = true;

            main.SwitchPanel.Controls.Add(sv);
            main.SwitchPanel.Visible = true;
            PanelSelectedChanged(4);

        }

        private void UniversityButton_MouseClick(object sender, EventArgs e)
        {
            if (main.SwitchPanel.Controls.Count > 0)
            {
                main.SwitchPanel.Controls.Clear();
            }

            
            
            nv.Dock = DockStyle.Fill;
            nv.TopLevel = false;
            nv.Visible = true;
            uv.Dock = DockStyle.Fill;
            uv.TopLevel = false;
            uv.Visible = true;

            fc.SwitchPanel();
            PanelSelectedChanged(3);

        }

        private void Disconnect_Click(object sender, EventArgs e)
        {
            main.Visible = false ;
            Login lg = new Login();
            lg.Visible = true;
        }

        private void ChatButton_Click(object sender, EventArgs e)
        {
            if (main.SwitchPanel.Controls.Count > 0)
            {
                main.SwitchPanel.Controls.Clear();
            }

            
            cv.Dock = DockStyle.Fill;
            cv.TopLevel = false;
            cv.Visible = true;

            main.SwitchPanel.Controls.Add(cv);
            main.SwitchPanel.Visible = true;
            chatController.LoadGroups();
            PanelSelectedChanged(1);

        }
        private void AddNewGroup_Click(object sender, EventArgs e)
        {
            if (main.SwitchPanel.Controls.Count > 0)
            {
                main.SwitchPanel.Controls.Clear();
            }

            AddNewGroupView ang = new AddNewGroupView();
            AddNewGroupController angC = new AddNewGroupController(ang, main.GetUser());
            ang.Dock = DockStyle.Fill;
            ang.TopLevel = false;
            ang.Visible = true;

            main.SwitchPanel.Controls.Add(ang);
            main.SwitchPanel.Visible = true;
            PanelSelectedChanged(2);

        }
        private void HomeButton_Click(object sender, EventArgs e)
        {
            if (main.SwitchPanel.Controls.Count > 0)
            {
                main.SwitchPanel.Controls.Clear();
            }

            HomeView hv = new HomeView();
            hv.Dock = DockStyle.Fill;
            hv.TopLevel = false;
            hv.Visible = true;

            main.SwitchPanel.Controls.Add(hv);
            main.SwitchPanel.Visible = true;
            PanelSelectedChanged(0);

        }


        private void PanelSelectedChanged(int index)
        {
            switch (index)
            {
                case 0:
                    main.HomeButton.panelSelected.Visible = true;
                    main.ChatButton.panelSelected.Visible = false;
                    main.AddGroupButton.panelSelected.Visible = false;
                    main.UniversityBUtton.panelSelected.Visible = false;
                    main.SettingsButton.panelSelected.Visible = false;
                    break;
                case 1:
                    main.HomeButton.panelSelected.Visible = false;
                    main.ChatButton.panelSelected.Visible = true;
                    main.AddGroupButton.panelSelected.Visible = false;
                    main.UniversityBUtton.panelSelected.Visible = false;
                    main.SettingsButton.panelSelected.Visible = false;
                    break;
                case 2:
                    main.HomeButton.panelSelected.Visible = false;
                    main.ChatButton.panelSelected.Visible = false;
                    main.AddGroupButton.panelSelected.Visible = true;
                    main.UniversityBUtton.panelSelected.Visible = false;
                    main.SettingsButton.panelSelected.Visible = false;
                    break;
                case 3:
                    main.HomeButton.panelSelected.Visible = false;
                    main.ChatButton.panelSelected.Visible = false;
                    main.AddGroupButton.panelSelected.Visible = false;
                    main.UniversityBUtton.panelSelected.Visible = true;
                    main.SettingsButton.panelSelected.Visible = false;
                    break;
                case 4:
                    main.HomeButton.panelSelected.Visible = false;
                    main.ChatButton.panelSelected.Visible = false;
                    main.AddGroupButton.panelSelected.Visible = false;
                    main.UniversityBUtton.panelSelected.Visible = false;
                    main.SettingsButton.panelSelected.Visible = true;
                    break;
                default:
                    break;
            }
        }

    }
}
