namespace StartUp.Models.Controls
{
    partial class IDMessageBox
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

        #region Component Designer generated code

        /// <summary> 
        /// Required method for Designer support - do not modify 
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.TimeLabel = new StartUp.Models.Controls.IDLabel();
            this.ProfilPic = new StartUp.Models.Controls.RoundedPictureBox();
            this.textMessage = new StartUp.Models.Controls.IDLabel();
            this.Nume = new System.Windows.Forms.Label();
            ((System.ComponentModel.ISupportInitialize)(this.ProfilPic)).BeginInit();
            this.SuspendLayout();
            // 
            // TimeLabel
            // 
            this.TimeLabel.Dock = System.Windows.Forms.DockStyle.Top;
            this.TimeLabel.ID = 0;
            this.TimeLabel.Location = new System.Drawing.Point(0, 0);
            this.TimeLabel.Name = "TimeLabel";
            this.TimeLabel.Size = new System.Drawing.Size(255, 21);
            this.TimeLabel.TabIndex = 0;
            this.TimeLabel.Text = "Time";
            this.TimeLabel.TextAlign = System.Drawing.ContentAlignment.TopCenter;
            // 
            // ProfilPic
            // 
            this.ProfilPic.BackColor = System.Drawing.Color.DarkGray;
            this.ProfilPic.Location = new System.Drawing.Point(3, 22);
            this.ProfilPic.Name = "ProfilPic";
            this.ProfilPic.Size = new System.Drawing.Size(34, 33);
            this.ProfilPic.SizeMode = System.Windows.Forms.PictureBoxSizeMode.Zoom;
            this.ProfilPic.TabIndex = 1;
            this.ProfilPic.TabStop = false;
            // 
            // textMessage
            // 
            this.textMessage.AutoSize = true;
            this.textMessage.ID = 0;
            this.textMessage.Location = new System.Drawing.Point(52, 45);
            this.textMessage.Name = "textMessage";
            this.textMessage.Size = new System.Drawing.Size(36, 20);
            this.textMessage.TabIndex = 2;
            this.textMessage.Text = "Text";
            // 
            // Nume
            // 
            this.Nume.AutoSize = true;
            this.Nume.Font = new System.Drawing.Font("Segoe UI", 9F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point);
            this.Nume.Location = new System.Drawing.Point(52, 18);
            this.Nume.Name = "Nume";
            this.Nume.Size = new System.Drawing.Size(51, 20);
            this.Nume.TabIndex = 3;
            this.Nume.Text = "label1";
            // 
            // IDMessageBox
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 20F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.Controls.Add(this.Nume);
            this.Controls.Add(this.textMessage);
            this.Controls.Add(this.ProfilPic);
            this.Controls.Add(this.TimeLabel);
            this.Name = "IDMessageBox";
            this.Size = new System.Drawing.Size(255, 77);
            ((System.ComponentModel.ISupportInitialize)(this.ProfilPic)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion
        public RoundedPictureBox ProfilPic;
        public IDLabel textMessage;
        public Label Nume;
        public IDLabel TimeLabel;
    }
}
