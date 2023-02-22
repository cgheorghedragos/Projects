namespace StartUp.Models.Controls
{
    partial class SendButton
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
            this.SendButtonImage = new System.Windows.Forms.PictureBox();
            ((System.ComponentModel.ISupportInitialize)(this.SendButtonImage)).BeginInit();
            this.SuspendLayout();
            // 
            // SendButtonImage
            // 
            this.SendButtonImage.Dock = System.Windows.Forms.DockStyle.Fill;
            this.SendButtonImage.Image = global::StartUp.Properties.Resources.SendMessageIcon;
            this.SendButtonImage.InitialImage = null;
            this.SendButtonImage.Location = new System.Drawing.Point(0, 0);
            this.SendButtonImage.Name = "SendButtonImage";
            this.SendButtonImage.Size = new System.Drawing.Size(66, 66);
            this.SendButtonImage.SizeMode = System.Windows.Forms.PictureBoxSizeMode.Zoom;
            this.SendButtonImage.TabIndex = 0;
            this.SendButtonImage.TabStop = false;
            // 
            // SendButton
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 20F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.Controls.Add(this.SendButtonImage);
            this.Name = "SendButton";
            this.Size = new System.Drawing.Size(66, 66);
            ((System.ComponentModel.ISupportInitialize)(this.SendButtonImage)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        public PictureBox SendButtonImage;
    }
}
