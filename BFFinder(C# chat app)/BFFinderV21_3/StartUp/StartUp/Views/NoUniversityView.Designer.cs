namespace StartUp.Views
{
    partial class NoUniversityView
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(NoUniversityView));
            this.idPictureBox1 = new StartUp.Models.Controls.IDPictureBox();
            this.label1 = new System.Windows.Forms.Label();
            ((System.ComponentModel.ISupportInitialize)(this.idPictureBox1)).BeginInit();
            this.SuspendLayout();
            // 
            // idPictureBox1
            // 
            this.idPictureBox1.ID = 0;
            this.idPictureBox1.Image = ((System.Drawing.Image)(resources.GetObject("idPictureBox1.Image")));
            this.idPictureBox1.Location = new System.Drawing.Point(77, 60);
            this.idPictureBox1.Name = "idPictureBox1";
            this.idPictureBox1.Size = new System.Drawing.Size(750, 256);
            this.idPictureBox1.SizeMode = System.Windows.Forms.PictureBoxSizeMode.Zoom;
            this.idPictureBox1.TabIndex = 0;
            this.idPictureBox1.TabStop = false;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Font = new System.Drawing.Font("Times New Roman", 24F, ((System.Drawing.FontStyle)((System.Drawing.FontStyle.Bold | System.Drawing.FontStyle.Italic))), System.Drawing.GraphicsUnit.Point);
            this.label1.Location = new System.Drawing.Point(148, 436);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(561, 46);
            this.label1.TabIndex = 1;
            this.label1.Text = "You aren\'t currently at a faculty!";
            // 
            // NoUniversityView
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 20F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.SystemColors.ActiveCaption;
            this.ClientSize = new System.Drawing.Size(912, 622);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.idPictureBox1);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.None;
            this.Name = "NoUniversityView";
            this.Text = "NoUniversityView";
            ((System.ComponentModel.ISupportInitialize)(this.idPictureBox1)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private Models.Controls.IDPictureBox idPictureBox1;
        private Label label1;
    }
}