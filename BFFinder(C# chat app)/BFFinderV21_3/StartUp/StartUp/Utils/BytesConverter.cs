using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace StartUp.Utils
{
    public class BytesConverter
    {
       public static string ConvertImageToBytes(Image img)
        {
            using (MemoryStream ms = new MemoryStream())
            {
                img.Save(ms, System.Drawing.Imaging.ImageFormat.Png);
                return Convert.ToBase64String(ms.ToArray());
            }
        }

        public static Bitmap ConvertStringToBitmapImage(string data)
        {
            byte[] bytesData = Convert.FromBase64String(data);

            MemoryStream ms = new MemoryStream(bytesData);

            var image = new Bitmap(ms);
            return image;

        }
    }
}
