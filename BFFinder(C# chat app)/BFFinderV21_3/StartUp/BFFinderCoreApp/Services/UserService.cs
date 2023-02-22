using BFFinderCoreApp.Enums;
using BFFinderCoreApp.Models;
using BFFinderCoreApp.Repository;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Sockets;
using System.Text;
using System.Threading.Tasks;

namespace BFFinderCoreApp.Services
{
    public class UserService
    {
        #region UpdateUser
        public void UpdateUserRequestFunct(User user,string oldPass, string password, TcpClient _client)
        {
            var editPacket = new PacketBuilderService();
            editPacket.WritePacketHeaderCode(PacketHeaders.UpdateUserRequest);
            List<string> message = new List<string>();

            //messages to transmit
            message.Add(user.Id.ToString());
            message.Add(user.Username.ToString());
            message.Add(user.Varsta.ToString());
            message.Add(user.Sex.ToString());
            message.Add(user.FirstName.ToString());
            message.Add(user.SecondName.ToString());
            message.Add(user.Scor.ToString());
            message.Add(user.ProfileImage.ToString());
            message.Add(user.Email.ToString());
            message.Add(password);
            message.Add(oldPass);

            editPacket.WriteMessage(message);
            _client.Client.Send(editPacket.GetPacketBytes());
        }

        public List<string> UpdateUserClientResponse(PacketReaderService PacketReader)
        {
            List<string> msg = PacketReader.ReadMessage();
            return msg;
        }

        public void UpdateUserServerResponse(string response, TcpClient ClientSocket)
        {
            var msgPacket = new PacketBuilderService();
            msgPacket.WritePacketHeaderCode(PacketHeaders.UpdateUserResponse);
            List<string> msg = new List<string>();
            msg.Add(response);


            msgPacket.WriteMessage(msg);

            ClientSocket.Client.Send(msgPacket.GetPacketBytes());
        }

        public void HandleUpdateUserRequest(PacketReaderService packetReader, TcpClient ClientSocket)
        {
            List<string> message = packetReader.ReadMessage();
            User user = new User();

            user.Id = Convert.ToInt32( message[1]);
            user.Username = message[2];
            user.Varsta = Convert.ToInt32(message[3]);
            user.Sex = Convert.ToInt32(message[4]);
            user.FirstName = message[5];
            user.SecondName = message[6];
            user.Scor = Convert.ToInt32(message[7]);
            user.ProfileImage = message[8];
            user.Email = message[9];
            string password = message[10];
            string oldPass = message[10];

            DataRepository<User> dataRepo = new DataRepository<User>();


            try
            {
                User searchUser =dataRepo.FindUserByUsername(user.Username, oldPass);
                if(searchUser == null)
                {
                    throw new Exception();
                }
                dataRepo.Update(user, password);
                Console.WriteLine($"[{ DateTime.Now}] - { user.Username }: Succes to edit profile!");
                UpdateUserServerResponse("UpdateSucces!", ClientSocket);
            }
            catch (Exception ex)
            {
                Console.WriteLine($"[{ DateTime.Now}] - { user.Username}: Failed to Edit profile!");
                UpdateUserServerResponse("UpdateFailed", ClientSocket);
            }

        }
        #endregion

        #region CheckUser
        public void SearchUserRequestFunct(String user, string Email, TcpClient _client)
        {
            var editPacket = new PacketBuilderService();
            editPacket.WritePacketHeaderCode(PacketHeaders.CheckUserExistRequest);
            List<string> message = new List<string>();

            //messages to transmit
            message.Add(user);
            message.Add(Email);

            editPacket.WriteMessage(message);
            _client.Client.Send(editPacket.GetPacketBytes());
        }

        public List<string> SearchUserClientResponse(PacketReaderService PacketReader)
        {
            List<string> msg = PacketReader.ReadMessage();
            return msg;
        }

        public void SearchUserServerResponse(string response, TcpClient ClientSocket, int val)
        {
            var msgPacket = new PacketBuilderService();
            msgPacket.WritePacketHeaderCode(PacketHeaders.CheckUserExistResponse);
            List<string> msg = new List<string>();
            msg.Add(response);
            msg.Add(val.ToString());

            msgPacket.WriteMessage(msg);

            ClientSocket.Client.Send(msgPacket.GetPacketBytes());
        }

        public void HandleSearchUserRequest(PacketReaderService packetReader, TcpClient ClientSocket)
        {
            List<string> message = packetReader.ReadMessage();
            string username = message[1];
            string email = message[2];


            DataRepository<User> dU = new DataRepository<User>();


            try
            {
              int val = dU.SearchIfAlreadyExist(username, email);
               
                SearchUserServerResponse("SearchSucces!", ClientSocket, val);
            }
            catch (Exception ex)
            {
                SearchUserServerResponse("SearchFailed", ClientSocket, 0);
            }

        }
        #endregion 
    }

}
