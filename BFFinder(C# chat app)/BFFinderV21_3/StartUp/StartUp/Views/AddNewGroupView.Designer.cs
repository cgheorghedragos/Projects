namespace StartUp.Views
{
    partial class AddNewGroupView
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
            this.GroupList = new System.Windows.Forms.ListBox();
            this.joinnGrup = new System.Windows.Forms.Button();
            this.crtButton = new System.Windows.Forms.Button();
            this.jgButton = new System.Windows.Forms.Button();
            this.label1 = new System.Windows.Forms.Label();
            this.textGrup = new System.Windows.Forms.TextBox();
            this.label2 = new System.Windows.Forms.Label();
            this.joinPanel = new System.Windows.Forms.Panel();
            this.createPanel = new StartUp.Models.Controls.IDPanel();
            this.CreateGrup = new System.Windows.Forms.Button();
            this.joinPanel.SuspendLayout();
            this.createPanel.SuspendLayout();
            this.SuspendLayout();
            // 
            // GroupList
            // 
            this.GroupList.FormattingEnabled = true;
            this.GroupList.ItemHeight = 20;
            this.GroupList.Location = new System.Drawing.Point(20, 16);
            this.GroupList.Name = "GroupList";
            this.GroupList.Size = new System.Drawing.Size(250, 304);
            this.GroupList.TabIndex = 1;
            // 
            // joinnGrup
            // 
            this.joinnGrup.BackColor = System.Drawing.Color.BlueViolet;
            this.joinnGrup.Font = new System.Drawing.Font("Segoe UI", 9F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point);
            this.joinnGrup.ForeColor = System.Drawing.Color.Lavender;
            this.joinnGrup.Location = new System.Drawing.Point(59, 358);
            this.joinnGrup.Name = "joinnGrup";
            this.joinnGrup.Size = new System.Drawing.Size(171, 34);
            this.joinnGrup.TabIndex = 2;
            this.joinnGrup.Text = "Join Selected Group";
            this.joinnGrup.UseVisualStyleBackColor = false;
            // 
            // crtButton
            // 
            this.crtButton.BackColor = System.Drawing.Color.BlueViolet;
            this.crtButton.Font = new System.Drawing.Font("Segoe UI", 9F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point);
            this.crtButton.ForeColor = System.Drawing.Color.Lavender;
            this.crtButton.Location = new System.Drawing.Point(506, 520);
            this.crtButton.Name = "crtButton";
            this.crtButton.Size = new System.Drawing.Size(183, 45);
            this.crtButton.TabIndex = 3;
            this.crtButton.Text = "Create a new Group";
            this.crtButton.UseVisualStyleBackColor = false;
            // 
            // jgButton
            // 
            this.jgButton.BackColor = System.Drawing.Color.BlueViolet;
            this.jgButton.Font = new System.Drawing.Font("Segoe UI", 9F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point);
            this.jgButton.ForeColor = System.Drawing.Color.Lavender;
            this.jgButton.Location = new System.Drawing.Point(234, 520);
            this.jgButton.Name = "jgButton";
            this.jgButton.Size = new System.Drawing.Size(183, 45);
            this.jgButton.TabIndex = 3;
            this.jgButton.Text = "Join in a Group";
            this.jgButton.UseVisualStyleBackColor = false;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(446, 532);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(29, 20);
            this.label1.TabIndex = 4;
            this.label1.Text = "OR";
            // 
            // textGrup
            // 
            this.textGrup.Location = new System.Drawing.Point(23, 82);
            this.textGrup.Name = "textGrup";
            this.textGrup.Size = new System.Drawing.Size(233, 27);
            this.textGrup.TabIndex = 5;
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(81, 42);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(88, 20);
            this.label2.TabIndex = 6;
            this.label2.Text = "Nume Grup:";
            // 
            // joinPanel
            // 
            this.joinPanel.Controls.Add(this.GroupList);
            this.joinPanel.Controls.Add(this.joinnGrup);
            this.joinPanel.Location = new System.Drawing.Point(180, 52);
            this.joinPanel.Name = "joinPanel";
            this.joinPanel.Size = new System.Drawing.Size(280, 419);
            this.joinPanel.TabIndex = 7;
            // 
            // createPanel
            // 
            this.createPanel.Controls.Add(this.CreateGrup);
            this.createPanel.Controls.Add(this.textGrup);
            this.createPanel.Controls.Add(this.label2);
            this.createPanel.ID = 0;
            this.createPanel.Location = new System.Drawing.Point(466, 136);
            this.createPanel.Name = "createPanel";
            this.createPanel.Size = new System.Drawing.Size(278, 236);
            this.createPanel.TabIndex = 8;
            // 
            // CreateGrup
            // 
            this.CreateGrup.BackColor = System.Drawing.Color.DarkViolet;
            this.CreateGrup.Font = new System.Drawing.Font("Segoe UI", 9F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point);
            this.CreateGrup.ForeColor = System.Drawing.Color.Lavender;
            this.CreateGrup.Location = new System.Drawing.Point(48, 174);
            this.CreateGrup.Name = "CreateGrup";
            this.CreateGrup.Size = new System.Drawing.Size(172, 29);
            this.CreateGrup.TabIndex = 7;
            this.CreateGrup.Text = "Create Group";
            this.CreateGrup.UseVisualStyleBackColor = false;
            // 
            // AddNewGroupView
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 20F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.Lavender;
            this.ClientSize = new System.Drawing.Size(906, 622);
            this.Controls.Add(this.createPanel);
            this.Controls.Add(this.joinPanel);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.jgButton);
            this.Controls.Add(this.crtButton);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.None;
            this.Name = "AddNewGroupView";
            this.Text = " ";
            this.joinPanel.ResumeLayout(false);
            this.createPanel.ResumeLayout(false);
            this.createPanel.PerformLayout();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion
        private Label label1;
        private Label label2;
        public Panel joinPanel;
        public ListBox GroupList;
        public Button crtButton;
        public Button jgButton;
        public Models.Controls.IDPanel createPanel;
        public TextBox textGrup;
        public Button CreateGrup;
        public Button joinnGrup;
    }
}