using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Data.SqlClient;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace StartUp.Views
{
    public partial class Test : Form
    {
        private readonly SqlConnection _conString = new SqlConnection("Data Source=GHEORGHEDRAGOS\\SQLEXPRESS;Initial Catalog=BFFinderDatabase;Integrated Security=True");

        public Test()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {

            using (OpenFileDialog ofd = new OpenFileDialog())
            {
                Image myImg;
                ofd.Filter = "txt files (*.jpg)|*.jpg|All files (*.*)|*.*";

                if (ofd.ShowDialog() == DialogResult.OK)
                {

                    myImg = Image.FromFile(ofd.FileName);
                    string cimg = ConvertImageToBytes(myImg);

                    _conString.Open();
                    

                    string ccimg = "'"+cimg+"'";
                    MessageBox.Show(ccimg.Length.ToString());
                    string queryString = "UPDATE users SET image = " + ccimg + " WHERE username = 'dragosbossu'";
    
                    SqlCommand com = new SqlCommand(queryString, _conString);
                    com.ExecuteNonQuery();
                    _conString.Close();

                }


                
            }
        }

        private string ConvertImageToBytes(Image img)
        {
            using (MemoryStream ms = new MemoryStream())
            {
                img.Save(ms, System.Drawing.Imaging.ImageFormat.Png);
                return Convert.ToBase64String(ms.ToArray());
            }
        }

        private Bitmap ConvertStringToBitmapImage(string data)
        {
            byte[] bytesData = Convert.FromBase64String(data);

            MemoryStream ms = new MemoryStream(bytesData);

            var image = new Bitmap(ms);
            return image;

        }

        private void button2_Click(object sender, EventArgs e)
        {
            string queryString = "SELECT * FROM dbo.users WHERE username = 'dragosbossu' ";

            _conString.Open();

            SqlCommand com = new SqlCommand(queryString, _conString);
            SqlDataReader searchUser = com.ExecuteReader();

            if (searchUser.Read()) { 
            string cimg = Convert.ToString(searchUser["image"]);

                pictureBox1.Image = ConvertStringToBitmapImage(cimg);
                    }
            _conString.Close();
        }

        private void button3_Click(object sender, EventArgs e)
        {

            DateTime date = DateTime.Now;
            _conString.Open();
            string queryString = "UPDATE mesaje SET ora = @value WHERE id_mesaj = '1'";
            SqlCommand cmd = new SqlCommand(queryString, _conString);
            cmd.Parameters.AddWithValue("@value", date);
            
            cmd.ExecuteNonQuery();

            _conString.Close();

        }

        private void button4_Click(object sender, EventArgs e)
        {
            DateTime date;
            _conString.Open();
            SqlCommand cmd = new SqlCommand("SELECT * FROM mesaje where id_mesaj = '1'", _conString);


            SqlDataReader importMessages = cmd.ExecuteReader(); 

            if (importMessages.Read())
            {
                date = Convert.ToDateTime(importMessages["ora"]);
                label1.Text = date.ToString();
            }

                      

            _conString.Close();
        }
    }
}
