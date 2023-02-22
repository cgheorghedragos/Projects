using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace StartUp.Services
{
    public class EncryptService
    {
        public static String ENCRYPT(String encryptData)
        {
            String encryptionText = "";

            foreach(char eachChar in encryptData)
            {
                
                int asciiCode = (int)eachChar;

                char first_encrypt = (char)(asciiCode * 2);
                char second_encrypt = (char)(asciiCode /2  + 11);

                encryptionText += first_encrypt + second_encrypt;
            }

            return encryptionText;

        }
    }
}
