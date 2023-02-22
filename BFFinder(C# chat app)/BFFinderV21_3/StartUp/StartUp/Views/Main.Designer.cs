namespace StartUp.Views
{
    partial class Main
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(Main));
            this.LeftMainPanel = new System.Windows.Forms.Panel();
            this.MenuPanel = new System.Windows.Forms.Panel();
            this.SettingsButton = new StartUp.Models.Controls.NavButton();
            this.UniversityBUtton = new StartUp.Models.Controls.NavButton();
            this.AddGroupButton = new StartUp.Models.Controls.NavButton();
            this.ChatButton = new StartUp.Models.Controls.NavButton();
            this.HomeButton = new StartUp.Models.Controls.NavButton();
            this.DisconnectButton = new StartUp.Models.Controls.NavButton();
            this.AvatarPanel = new System.Windows.Forms.Panel();
            this.roundedPictureBox1 = new StartUp.Models.Controls.RoundedPictureBox();
            this.nameLabel = new StartUp.Models.Controls.IDLabel();
            this.movablePanel = new StartUp.Models.Controls.IDPanel();
            this.ExitButton = new StartUp.Models.Controls.IDButton();
            this.Minimalize = new StartUp.Models.Controls.IDButton();
            this.SwitchPanel = new System.Windows.Forms.Panel();
            this.LeftMainPanel.SuspendLayout();
            this.MenuPanel.SuspendLayout();
            this.AvatarPanel.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.roundedPictureBox1)).BeginInit();
            this.movablePanel.SuspendLayout();
            this.SuspendLayout();
            // 
            // LeftMainPanel
            // 
            this.LeftMainPanel.BackColor = System.Drawing.Color.BlueViolet;
            this.LeftMainPanel.Controls.Add(this.MenuPanel);
            this.LeftMainPanel.Controls.Add(this.DisconnectButton);
            this.LeftMainPanel.Controls.Add(this.AvatarPanel);
            this.LeftMainPanel.Dock = System.Windows.Forms.DockStyle.Left;
            this.LeftMainPanel.Location = new System.Drawing.Point(0, 0);
            this.LeftMainPanel.MinimumSize = new System.Drawing.Size(186, 500);
            this.LeftMainPanel.Name = "LeftMainPanel";
            this.LeftMainPanel.Size = new System.Drawing.Size(186, 642);
            this.LeftMainPanel.TabIndex = 0;
            this.LeftMainPanel.Paint += new System.Windows.Forms.PaintEventHandler(this.MenuPanel_Paint);
            // 
            // MenuPanel
            // 
            this.MenuPanel.Anchor = System.Windows.Forms.AnchorStyles.Left;
            this.MenuPanel.Controls.Add(this.SettingsButton);
            this.MenuPanel.Controls.Add(this.UniversityBUtton);
            this.MenuPanel.Controls.Add(this.AddGroupButton);
            this.MenuPanel.Controls.Add(this.ChatButton);
            this.MenuPanel.Controls.Add(this.HomeButton);
            this.MenuPanel.Location = new System.Drawing.Point(0, 220);
            this.MenuPanel.Name = "MenuPanel";
            this.MenuPanel.Size = new System.Drawing.Size(186, 231);
            this.MenuPanel.TabIndex = 1;
            // 
            // SettingsButton
            // 
            this.SettingsButton.AccessibleRole = System.Windows.Forms.AccessibleRole.None;
            this.SettingsButton.BackColor = System.Drawing.Color.Lavender;
            this.SettingsButton.Cursor = System.Windows.Forms.Cursors.Hand;
            this.SettingsButton.Dock = System.Windows.Forms.DockStyle.Top;
            this.SettingsButton.Font = new System.Drawing.Font("Segoe UI", 9F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point);
            this.SettingsButton.ImagePic = ((System.Drawing.Image)(resources.GetObject("SettingsButton.ImagePic")));
            this.SettingsButton.LabelText = "Settings";
            this.SettingsButton.Location = new System.Drawing.Point(0, 168);
            this.SettingsButton.Name = "SettingsButton";
            this.SettingsButton.PanelVisible = false;
            this.SettingsButton.Size = new System.Drawing.Size(186, 42);
            this.SettingsButton.TabIndex = 16;
            // 
            // UniversityBUtton
            // 
            this.UniversityBUtton.AccessibleRole = System.Windows.Forms.AccessibleRole.None;
            this.UniversityBUtton.BackColor = System.Drawing.Color.Lavender;
            this.UniversityBUtton.Cursor = System.Windows.Forms.Cursors.Hand;
            this.UniversityBUtton.Dock = System.Windows.Forms.DockStyle.Top;
            this.UniversityBUtton.Font = new System.Drawing.Font("Segoe UI", 9F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point);
            this.UniversityBUtton.ImagePic = ((System.Drawing.Image)(resources.GetObject("UniversityBUtton.ImagePic")));
            this.UniversityBUtton.LabelText = "University";
            this.UniversityBUtton.Location = new System.Drawing.Point(0, 126);
            this.UniversityBUtton.Name = "UniversityBUtton";
            this.UniversityBUtton.PanelVisible = false;
            this.UniversityBUtton.Size = new System.Drawing.Size(186, 42);
            this.UniversityBUtton.TabIndex = 15;
            this.UniversityBUtton.Load += new System.EventHandler(this.UniversityBUtton_Load);
            // 
            // AddGroupButton
            // 
            this.AddGroupButton.AccessibleRole = System.Windows.Forms.AccessibleRole.None;
            this.AddGroupButton.BackColor = System.Drawing.Color.Lavender;
            this.AddGroupButton.Cursor = System.Windows.Forms.Cursors.Hand;
            this.AddGroupButton.Dock = System.Windows.Forms.DockStyle.Top;
            this.AddGroupButton.Font = new System.Drawing.Font("Segoe UI", 9F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point);
            this.AddGroupButton.ImagePic = ((System.Drawing.Image)(resources.GetObject("AddGroupButton.ImagePic")));
            this.AddGroupButton.LabelText = "Add New Group";
            this.AddGroupButton.Location = new System.Drawing.Point(0, 84);
            this.AddGroupButton.Name = "AddGroupButton";
            this.AddGroupButton.PanelVisible = false;
            this.AddGroupButton.Size = new System.Drawing.Size(186, 42);
            this.AddGroupButton.TabIndex = 13;
            this.AddGroupButton.Load += new System.EventHandler(this.AddGroupButton_Load);
            // 
            // ChatButton
            // 
            this.ChatButton.AccessibleRole = System.Windows.Forms.AccessibleRole.None;
            this.ChatButton.BackColor = System.Drawing.Color.Lavender;
            this.ChatButton.Cursor = System.Windows.Forms.Cursors.Hand;
            this.ChatButton.Dock = System.Windows.Forms.DockStyle.Top;
            this.ChatButton.Font = new System.Drawing.Font("Segoe UI", 9F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point);
            this.ChatButton.ImagePic = ((System.Drawing.Image)(resources.GetObject("ChatButton.ImagePic")));
            this.ChatButton.LabelText = "Chat";
            this.ChatButton.Location = new System.Drawing.Point(0, 42);
            this.ChatButton.Name = "ChatButton";
            this.ChatButton.PanelVisible = false;
            this.ChatButton.Size = new System.Drawing.Size(186, 42);
            this.ChatButton.TabIndex = 12;
            this.ChatButton.Load += new System.EventHandler(this.ChatButton_Load);
            // 
            // HomeButton
            // 
            this.HomeButton.AccessibleRole = System.Windows.Forms.AccessibleRole.None;
            this.HomeButton.BackColor = System.Drawing.Color.Lavender;
            this.HomeButton.Cursor = System.Windows.Forms.Cursors.Hand;
            this.HomeButton.Dock = System.Windows.Forms.DockStyle.Top;
            this.HomeButton.Font = new System.Drawing.Font("Segoe UI", 9F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point);
            this.HomeButton.ForeColor = System.Drawing.Color.DarkSlateBlue;
            this.HomeButton.ImagePic = ((System.Drawing.Image)(resources.GetObject("HomeButton.ImagePic")));
            this.HomeButton.LabelText = "Home";
            this.HomeButton.Location = new System.Drawing.Point(0, 0);
            this.HomeButton.Name = "HomeButton";
            this.HomeButton.PanelVisible = false;
            this.HomeButton.Size = new System.Drawing.Size(186, 42);
            this.HomeButton.TabIndex = 11;
            // 
            // DisconnectButton
            // 
            this.DisconnectButton.AccessibleRole = System.Windows.Forms.AccessibleRole.None;
            this.DisconnectButton.BackColor = System.Drawing.Color.Lavender;
            this.DisconnectButton.Cursor = System.Windows.Forms.Cursors.Hand;
            this.DisconnectButton.Dock = System.Windows.Forms.DockStyle.Bottom;
            this.DisconnectButton.Font = new System.Drawing.Font("Segoe UI", 9F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point);
            this.DisconnectButton.ImagePic = ((System.Drawing.Image)(resources.GetObject("DisconnectButton.ImagePic")));
            this.DisconnectButton.LabelText = "Disconnect";
            this.DisconnectButton.Location = new System.Drawing.Point(0, 600);
            this.DisconnectButton.Name = "DisconnectButton";
            this.DisconnectButton.PanelVisible = false;
            this.DisconnectButton.Size = new System.Drawing.Size(186, 42);
            this.DisconnectButton.TabIndex = 7;
            // 
            // AvatarPanel
            // 
            this.AvatarPanel.BackColor = System.Drawing.Color.BlueViolet;
            this.AvatarPanel.Controls.Add(this.roundedPictureBox1);
            this.AvatarPanel.Controls.Add(this.nameLabel);
            this.AvatarPanel.Dock = System.Windows.Forms.DockStyle.Top;
            this.AvatarPanel.Location = new System.Drawing.Point(0, 0);
            this.AvatarPanel.Name = "AvatarPanel";
            this.AvatarPanel.Size = new System.Drawing.Size(186, 214);
            this.AvatarPanel.TabIndex = 0;
            this.AvatarPanel.Paint += new System.Windows.Forms.PaintEventHandler(this.AvatarPanel_Paint);
            // 
            // roundedPictureBox1
            // 
            this.roundedPictureBox1.BackColor = System.Drawing.Color.DarkGray;
            this.roundedPictureBox1.ErrorImage = null;
            this.roundedPictureBox1.Image = ((System.Drawing.Image)(resources.GetObject("roundedPictureBox1.Image")));
            this.roundedPictureBox1.InitialImage = ((System.Drawing.Image)(resources.GetObject("roundedPictureBox1.InitialImage")));
            this.roundedPictureBox1.Location = new System.Drawing.Point(29, 32);
            this.roundedPictureBox1.Name = "roundedPictureBox1";
            this.roundedPictureBox1.Size = new System.Drawing.Size(118, 101);
            this.roundedPictureBox1.SizeMode = System.Windows.Forms.PictureBoxSizeMode.Zoom;
            this.roundedPictureBox1.TabIndex = 2;
            this.roundedPictureBox1.TabStop = false;
            this.roundedPictureBox1.Click += new System.EventHandler(this.roundedPictureBox1_Click);
            // 
            // nameLabel
            // 
            this.nameLabel.Font = new System.Drawing.Font("Segoe UI", 10.2F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point);
            this.nameLabel.ForeColor = System.Drawing.Color.Lavender;
            this.nameLabel.ID = 0;
            this.nameLabel.Location = new System.Drawing.Point(0, 152);
            this.nameLabel.Name = "nameLabel";
            this.nameLabel.Size = new System.Drawing.Size(186, 23);
            this.nameLabel.TabIndex = 1;
            this.nameLabel.Text = "Ciurezu Dragos";
            this.nameLabel.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // movablePanel
            // 
            this.movablePanel.BackColor = System.Drawing.Color.BlueViolet;
            this.movablePanel.Controls.Add(this.ExitButton);
            this.movablePanel.Controls.Add(this.Minimalize);
            this.movablePanel.Cursor = System.Windows.Forms.Cursors.SizeAll;
            this.movablePanel.Dock = System.Windows.Forms.DockStyle.Top;
            this.movablePanel.ID = 0;
            this.movablePanel.Location = new System.Drawing.Point(186, 0);
            this.movablePanel.Name = "movablePanel";
            this.movablePanel.Size = new System.Drawing.Size(932, 26);
            this.movablePanel.TabIndex = 16;
            this.movablePanel.MouseMove += new System.Windows.Forms.MouseEventHandler(this.movablePanel_MouseMove);
            // 
            // ExitButton
            // 
            this.ExitButton.BackColor = System.Drawing.Color.Lavender;
            this.ExitButton.Dock = System.Windows.Forms.DockStyle.Right;
            this.ExitButton.Font = new System.Drawing.Font("Segoe UI", 9F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point);
            this.ExitButton.ForeColor = System.Drawing.Color.Red;
            this.ExitButton.ID = 0;
            this.ExitButton.Location = new System.Drawing.Point(882, 0);
            this.ExitButton.Margin = new System.Windows.Forms.Padding(0);
            this.ExitButton.Name = "ExitButton";
            this.ExitButton.Size = new System.Drawing.Size(50, 26);
            this.ExitButton.TabIndex = 0;
            this.ExitButton.Text = "X";
            this.ExitButton.UseVisualStyleBackColor = false;
            this.ExitButton.Click += new System.EventHandler(this.ExitButton_Click);
            // 
            // Minimalize
            // 
            this.Minimalize.BackColor = System.Drawing.Color.Lavender;
            this.Minimalize.Font = new System.Drawing.Font("Segoe UI", 9F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point);
            this.Minimalize.ID = 0;
            this.Minimalize.Location = new System.Drawing.Point(839, 0);
            this.Minimalize.Margin = new System.Windows.Forms.Padding(0);
            this.Minimalize.Name = "Minimalize";
            this.Minimalize.Size = new System.Drawing.Size(44, 26);
            this.Minimalize.TabIndex = 1;
            this.Minimalize.Text = "___";
            this.Minimalize.UseVisualStyleBackColor = false;
            this.Minimalize.Click += new System.EventHandler(this.Minimalize_Click);
            // 
            // SwitchPanel
            // 
            this.SwitchPanel.BackColor = System.Drawing.Color.Lavender;
            this.SwitchPanel.Dock = System.Windows.Forms.DockStyle.Fill;
            this.SwitchPanel.Location = new System.Drawing.Point(186, 26);
            this.SwitchPanel.Name = "SwitchPanel";
            this.SwitchPanel.Size = new System.Drawing.Size(932, 616);
            this.SwitchPanel.TabIndex = 17;
            // 
            // Main
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 20F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(46)))), ((int)(((byte)(51)))), ((int)(((byte)(73)))));
            this.ClientSize = new System.Drawing.Size(1118, 642);
            this.Controls.Add(this.SwitchPanel);
            this.Controls.Add(this.movablePanel);
            this.Controls.Add(this.LeftMainPanel);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.None;
            this.Name = "Main";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "Main";
            this.Load += new System.EventHandler(this.Main_Load);
            this.LeftMainPanel.ResumeLayout(false);
            this.MenuPanel.ResumeLayout(false);
            this.AvatarPanel.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.roundedPictureBox1)).EndInit();
            this.movablePanel.ResumeLayout(false);
            this.ResumeLayout(false);

        }

        #endregion

        private Panel LeftMainPanel;
        private Panel AvatarPanel;
        private Panel MenuPanel;
        private Models.Controls.IDPanel movablePanel;
        public Panel SwitchPanel;
        private Models.Controls.IDButton ExitButton;
        private Models.Controls.IDButton Minimalize;
        public Models.Controls.IDLabel nameLabel;
        public Models.Controls.RoundedPictureBox roundedPictureBox1;
        public Models.Controls.NavButton AddGroupButton;
        public Models.Controls.NavButton ChatButton;
        public Models.Controls.NavButton HomeButton;
        public Models.Controls.NavButton SettingsButton;
        public Models.Controls.NavButton UniversityBUtton;
        public Models.Controls.NavButton DisconnectButton;
    }
}