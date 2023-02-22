namespace StartUp.Models.Controls
{
    partial class NavButton
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(NavButton));
            this.PicImage = new StartUp.Models.Controls.IDPictureBox();
            this.panelSelected = new StartUp.Models.Controls.IDPanel();
            this.textLabel = new StartUp.Models.Controls.IDLabel();
            ((System.ComponentModel.ISupportInitialize)(this.PicImage)).BeginInit();
            this.SuspendLayout();
            // 
            // PicImage
            // 
            this.PicImage.Anchor = System.Windows.Forms.AnchorStyles.Right;
            this.PicImage.ID = 0;
            this.PicImage.Image = ((System.Drawing.Image)(resources.GetObject("PicImage.Image")));
            this.PicImage.Location = new System.Drawing.Point(135, 10);
            this.PicImage.Margin = new System.Windows.Forms.Padding(20);
            this.PicImage.Name = "PicImage";
            this.PicImage.Size = new System.Drawing.Size(38, 26);
            this.PicImage.SizeMode = System.Windows.Forms.PictureBoxSizeMode.Zoom;
            this.PicImage.TabIndex = 1;
            this.PicImage.TabStop = false;
            // 
            // panelSelected
            // 
            this.panelSelected.BackColor = System.Drawing.SystemColors.Highlight;
            this.panelSelected.Dock = System.Windows.Forms.DockStyle.Left;
            this.panelSelected.ID = 0;
            this.panelSelected.Location = new System.Drawing.Point(0, 0);
            this.panelSelected.Name = "panelSelected";
            this.panelSelected.Size = new System.Drawing.Size(5, 42);
            this.panelSelected.TabIndex = 2;
            // 
            // textLabel
            // 
            this.textLabel.Anchor = System.Windows.Forms.AnchorStyles.Left;
            this.textLabel.AutoSize = true;
            this.textLabel.ForeColor = System.Drawing.Color.BlueViolet;
            this.textLabel.ID = 0;
            this.textLabel.Location = new System.Drawing.Point(18, 10);
            this.textLabel.Name = "textLabel";
            this.textLabel.Size = new System.Drawing.Size(66, 20);
            this.textLabel.TabIndex = 0;
            this.textLabel.Text = "idLabel1";
            // 
            // NavButton
            // 
            this.AccessibleRole = System.Windows.Forms.AccessibleRole.None;
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 20F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(24)))), ((int)(((byte)(30)))), ((int)(((byte)(54)))));
            this.Controls.Add(this.textLabel);
            this.Controls.Add(this.panelSelected);
            this.Controls.Add(this.PicImage);
            this.Name = "NavButton";
            this.Size = new System.Drawing.Size(186, 42);
            ((System.ComponentModel.ISupportInitialize)(this.PicImage)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        public IDPictureBox PicImage;
        public IDPanel panelSelected;
        public IDLabel textLabel;
    }
}
