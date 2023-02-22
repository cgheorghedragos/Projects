using BFFinderCoreApp.Models;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace StartUp.Views
{
    public partial class SettingsView : Form
    {
        private static SettingsView instance = null;
        private SettingsView()
        {
            InitializeComponent();
        }

        public static SettingsView GetInstance()
        {
            if (instance == null)
            {
                instance = new SettingsView();
            }
            return instance;
        }

        private void saveButton_Click(object sender, EventArgs e)
        {

        }
    }
}
