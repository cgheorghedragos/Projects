namespace StartUp.Views
{
    partial class UniversityView
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
            this.FlowMessages = new System.Windows.Forms.FlowLayoutPanel();
            this.sendButton2 = new StartUp.Models.Controls.SendButton();
            this.InputMessage = new System.Windows.Forms.TextBox();
            this.label1 = new System.Windows.Forms.Label();
            this.SuspendLayout();
            // 
            // FlowMessages
            // 
            this.FlowMessages.AutoScroll = true;
            this.FlowMessages.FlowDirection = System.Windows.Forms.FlowDirection.TopDown;
            this.FlowMessages.Location = new System.Drawing.Point(22, 112);
            this.FlowMessages.Name = "FlowMessages";
            this.FlowMessages.Size = new System.Drawing.Size(843, 413);
            this.FlowMessages.TabIndex = 1;
            // 
            // sendButton2
            // 
            this.sendButton2.Cursor = System.Windows.Forms.Cursors.Hand;
            this.sendButton2.ForeColor = System.Drawing.SystemColors.ControlText;
            this.sendButton2.Location = new System.Drawing.Point(710, 553);
            this.sendButton2.Name = "sendButton2";
            this.sendButton2.Size = new System.Drawing.Size(30, 27);
            this.sendButton2.TabIndex = 4;
            // 
            // InputMessage
            // 
            this.InputMessage.Location = new System.Drawing.Point(65, 553);
            this.InputMessage.Name = "InputMessage";
            this.InputMessage.Size = new System.Drawing.Size(576, 27);
            this.InputMessage.TabIndex = 3;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Font = new System.Drawing.Font("Californian FB", 19.8F, ((System.Drawing.FontStyle)((System.Drawing.FontStyle.Bold | System.Drawing.FontStyle.Italic))), System.Drawing.GraphicsUnit.Point);
            this.label1.Location = new System.Drawing.Point(146, 47);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(250, 39);
            this.label1.TabIndex = 5;
            this.label1.Text = "Grup Facultate:";
            // 
            // UniversityView
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 20F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.Lavender;
            this.ClientSize = new System.Drawing.Size(906, 622);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.sendButton2);
            this.Controls.Add(this.InputMessage);
            this.Controls.Add(this.FlowMessages);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.None;
            this.Name = "UniversityView";
            this.Text = "UniversityView";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion
        public FlowLayoutPanel FlowMessages;
        public Models.Controls.SendButton sendButton2;
        public TextBox InputMessage;
        private Label label1;
    }
}