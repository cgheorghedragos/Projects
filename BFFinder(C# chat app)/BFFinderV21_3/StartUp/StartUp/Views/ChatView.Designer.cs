namespace StartUp.Views
{
    partial class ChatView
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
            this.panel1 = new System.Windows.Forms.Panel();
            this.FlowGroups = new System.Windows.Forms.FlowLayoutPanel();
            this.panel3 = new System.Windows.Forms.Panel();
            this.label1 = new System.Windows.Forms.Label();
            this.chatingPanel = new System.Windows.Forms.Panel();
            this.panel5 = new System.Windows.Forms.Panel();
            this.SendButtonMessage = new StartUp.Models.Controls.SendButton();
            this.InputMessage = new System.Windows.Forms.TextBox();
            this.FlowMessages = new System.Windows.Forms.FlowLayoutPanel();
            this.panel4 = new System.Windows.Forms.Panel();
            this.group_name = new StartUp.Models.Controls.IDLabel();
            this.panel1.SuspendLayout();
            this.panel3.SuspendLayout();
            this.chatingPanel.SuspendLayout();
            this.panel5.SuspendLayout();
            this.panel4.SuspendLayout();
            this.SuspendLayout();
            // 
            // panel1
            // 
            this.panel1.Controls.Add(this.FlowGroups);
            this.panel1.Controls.Add(this.panel3);
            this.panel1.Dock = System.Windows.Forms.DockStyle.Left;
            this.panel1.Location = new System.Drawing.Point(0, 0);
            this.panel1.Name = "panel1";
            this.panel1.Size = new System.Drawing.Size(224, 622);
            this.panel1.TabIndex = 0;
            // 
            // FlowGroups
            // 
            this.FlowGroups.BackColor = System.Drawing.Color.Lavender;
            this.FlowGroups.Dock = System.Windows.Forms.DockStyle.Fill;
            this.FlowGroups.FlowDirection = System.Windows.Forms.FlowDirection.TopDown;
            this.FlowGroups.Location = new System.Drawing.Point(0, 58);
            this.FlowGroups.Name = "FlowGroups";
            this.FlowGroups.Size = new System.Drawing.Size(224, 564);
            this.FlowGroups.TabIndex = 1;
            // 
            // panel3
            // 
            this.panel3.BackColor = System.Drawing.Color.BlueViolet;
            this.panel3.Controls.Add(this.label1);
            this.panel3.Dock = System.Windows.Forms.DockStyle.Top;
            this.panel3.Font = new System.Drawing.Font("Segoe UI", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point);
            this.panel3.Location = new System.Drawing.Point(0, 0);
            this.panel3.Name = "panel3";
            this.panel3.Size = new System.Drawing.Size(224, 58);
            this.panel3.TabIndex = 0;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.ForeColor = System.Drawing.Color.Lavender;
            this.label1.Location = new System.Drawing.Point(81, 18);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(84, 28);
            this.label1.TabIndex = 0;
            this.label1.Text = "Groups:";
            // 
            // chatingPanel
            // 
            this.chatingPanel.Controls.Add(this.panel5);
            this.chatingPanel.Controls.Add(this.FlowMessages);
            this.chatingPanel.Controls.Add(this.panel4);
            this.chatingPanel.Dock = System.Windows.Forms.DockStyle.Fill;
            this.chatingPanel.Location = new System.Drawing.Point(224, 0);
            this.chatingPanel.Name = "chatingPanel";
            this.chatingPanel.Size = new System.Drawing.Size(682, 622);
            this.chatingPanel.TabIndex = 1;
            // 
            // panel5
            // 
            this.panel5.BackColor = System.Drawing.Color.LightSteelBlue;
            this.panel5.Controls.Add(this.SendButtonMessage);
            this.panel5.Controls.Add(this.InputMessage);
            this.panel5.Dock = System.Windows.Forms.DockStyle.Fill;
            this.panel5.Location = new System.Drawing.Point(0, 551);
            this.panel5.Name = "panel5";
            this.panel5.Size = new System.Drawing.Size(682, 71);
            this.panel5.TabIndex = 2;
            // 
            // SendButtonMessage
            // 
            this.SendButtonMessage.Cursor = System.Windows.Forms.Cursors.Hand;
            this.SendButtonMessage.ForeColor = System.Drawing.SystemColors.ControlText;
            this.SendButtonMessage.Location = new System.Drawing.Point(614, 17);
            this.SendButtonMessage.Name = "SendButtonMessage";
            this.SendButtonMessage.Size = new System.Drawing.Size(30, 27);
            this.SendButtonMessage.TabIndex = 2;
            // 
            // InputMessage
            // 
            this.InputMessage.Location = new System.Drawing.Point(59, 17);
            this.InputMessage.Name = "InputMessage";
            this.InputMessage.Size = new System.Drawing.Size(522, 27);
            this.InputMessage.TabIndex = 1;
            this.InputMessage.TextChanged += new System.EventHandler(this.textBox1_TextChanged);
            // 
            // FlowMessages
            // 
            this.FlowMessages.BackColor = System.Drawing.Color.Lavender;
            this.FlowMessages.Dock = System.Windows.Forms.DockStyle.Top;
            this.FlowMessages.FlowDirection = System.Windows.Forms.FlowDirection.TopDown;
            this.FlowMessages.Location = new System.Drawing.Point(0, 58);
            this.FlowMessages.Name = "FlowMessages";
            this.FlowMessages.Size = new System.Drawing.Size(682, 493);
            this.FlowMessages.TabIndex = 1;
            // 
            // panel4
            // 
            this.panel4.BackColor = System.Drawing.Color.BlueViolet;
            this.panel4.Controls.Add(this.group_name);
            this.panel4.Dock = System.Windows.Forms.DockStyle.Top;
            this.panel4.Font = new System.Drawing.Font("Segoe UI", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point);
            this.panel4.Location = new System.Drawing.Point(0, 0);
            this.panel4.Name = "panel4";
            this.panel4.Size = new System.Drawing.Size(682, 58);
            this.panel4.TabIndex = 0;
            // 
            // group_name
            // 
            this.group_name.AutoSize = true;
            this.group_name.ForeColor = System.Drawing.Color.Lavender;
            this.group_name.ID = 0;
            this.group_name.Location = new System.Drawing.Point(274, 18);
            this.group_name.Name = "group_name";
            this.group_name.Size = new System.Drawing.Size(134, 28);
            this.group_name.TabIndex = 1;
            this.group_name.Text = "Group_Name";
            // 
            // ChatView
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 20F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.AutoSize = true;
            this.ClientSize = new System.Drawing.Size(906, 622);
            this.Controls.Add(this.chatingPanel);
            this.Controls.Add(this.panel1);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.None;
            this.Name = "ChatView";
            this.Text = "ChatView";
            this.panel1.ResumeLayout(false);
            this.panel3.ResumeLayout(false);
            this.panel3.PerformLayout();
            this.chatingPanel.ResumeLayout(false);
            this.panel5.ResumeLayout(false);
            this.panel5.PerformLayout();
            this.panel4.ResumeLayout(false);
            this.panel4.PerformLayout();
            this.ResumeLayout(false);

        }

        #endregion

        private Panel panel1;
        private Panel panel3;
        private Label label1;
        public FlowLayoutPanel FlowMessages;
        private Panel panel4;
        public FlowLayoutPanel FlowGroups;
        private Panel panel5;
        public Panel chatingPanel;
        public TextBox InputMessage;
        public Models.Controls.IDLabel group_name;
        public Models.Controls.SendButton SendButtonMessage;
    }
}