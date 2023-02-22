using BFFinderCoreApp.Models;
using BFFinderCoreApp.Services;
using StartUp.Services;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Sockets;
using System.Text;
using System.Threading.Tasks;

namespace StartUp.Utils
{
    public class TextFieldChecker
    {
        public static bool ComparePassword(string oldPass, string newPass)
        {
            return oldPass.Equals(newPass);
        }

        public static bool CheckRegisterField(User curUser, string password)
        {
            if (curUser == null)
            {
                return false;
            }
            if(curUser.ProfileImage == null)
            {
                return false;
            }

            if (curUser.Username.Length < 5 ||
                curUser.FirstName.Length < 3 ||
                curUser.SecondName.Length < 3 ||
                curUser.Varsta < 7 ||
                curUser.Email.Length < 3 ||
                !curUser.Email.Contains("@") ||
                curUser.ProfileImage.Length < 3
              )
            {
                return false;
            }
            return true;
        }

        public static bool CheckEditField(User curUser, string password, string newPassword)
        {
            if (curUser == null)
            {
                return false;
            }

            if (curUser.Username.Length < 5 ||
                curUser.FirstName.Length < 3 ||
                curUser.SecondName.Length < 3 ||
                curUser.Varsta < 7 ||
                curUser.Email.Length < 3 ||
                !curUser.Email.Contains("@") ||
                curUser.ProfileImage.Length < 3
              )
            {
                return false;
            }

            return true;
        }

        public static bool CheckUserAlreadyExist(User currUser, ClientToSv _client)
        {
            UserService us = new UserService();
            
            _client.clearAll();

            us.SearchUserRequestFunct(currUser.Username, currUser.Email, _client.getClient());

            while (_client.returnMessage == null)
            {
                //wait a bit
            }
            if (_client.returnMessage.Count == 0)
            {
                while (_client.returnMessage.Count == 0)
                {
                }
            }
            string noMsg = _client.returnMessage[0];
            if (_client.returnMessage.Count != Convert.ToInt32(noMsg) + 1)
            {
                while (_client.returnMessage.Count != Convert.ToInt32(noMsg) + 1)
                {
                }
            }

            List<string> msg = _client.returnMessage;
            string response = msg[1];
            

            if(response == "SearchSucces!")
            {
                if(Convert.ToInt32(msg[2]) == 1)
                {
                    return true;
                }
            }

            return false;
        }
    }
}
