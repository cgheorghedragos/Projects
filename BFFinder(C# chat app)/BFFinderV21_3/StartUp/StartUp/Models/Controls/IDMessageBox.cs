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
    public partial class IDMessageBox : UserControl
    {
        
        public IDMessageBox()
        {
            InitializeComponent();
        }

        [Category("Identification")]
        [Description("The identification id")]
        public int ID { set; get; }
    }
}
