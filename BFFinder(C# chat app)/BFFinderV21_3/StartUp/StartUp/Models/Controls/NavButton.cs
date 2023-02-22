using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace StartUp.Models.Controls
{
    public partial class NavButton : UserControl
    {
        public NavButton()
        {
            InitializeComponent();
           // System.EventHandler()
        }

        [Category("My Models")]
        [Description("The Text Label")]
        public string LabelText
        {
            set
            {
                textLabel.Text = value;
            }
            get
            {
                return textLabel.Text;
            }
        }

        [Category("My Models")]
        [Description("The Image")]
        public Image ImagePic
        {
            set
            {
                PicImage.Image = value;
            }
            get
            {
                return PicImage.Image;
            }
        }

        [Category("My Models")]
        [Description("The visible Position")]
        public bool PanelVisible
        {
            set
            {
                panelSelected.Visible = value;
            }
            get
            {
                return panelSelected.Visible;
            }
        }
       
        
    }
}
