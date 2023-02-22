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
    public partial class ChatView : Form
    {
        private static ChatView instance = null;
        private ChatView()
        {
            InitializeComponent();
        }

        public static ChatView GetInstance()
        {
            if (instance == null)
            {
                instance = new ChatView();
            }
            return instance;
        }

        private void textBox1_TextChanged(object sender, EventArgs e)
        {

        }
    }
}
