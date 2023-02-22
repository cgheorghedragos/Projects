using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Sockets;
using System.Text;
using System.Threading.Tasks;
using BFFinderCoreApp.Enums;
using BFFinderCoreApp.Models;
using BFFinderCoreApp.Repository;

namespace BFFinderCoreApp.Services
{
    public class LoginService
    {
        #region Login
        public void LoginRequestFunct(string username, string password, TcpClient _client)
        {
            var connectPacket = new PacketBuilderService();
            connectPacket.WritePacketHeaderCode(PacketHeaders.LoginRequest);
            List<string> message = new List<string>();

            //messages to transmit
            message.Add(username);
            message.Add(password);

            connectPacket.WriteMessage(message);
            _client.Client.Send(connectPacket.GetPacketBytes());
        }

        public List<string> LoginClientResponse(PacketReaderService PacketReader)
        {
            List<string> msg = PacketReader.ReadMessage();
            return msg;
        }

        public void LoginServerResponse(string response, TcpClient ClientSocket , User user)
        {
            var msgPacket = new PacketBuilderService();
            msgPacket.WritePacketHeaderCode(PacketHeaders.LoginResponse);
            List<string> msg = new List<string>();
            msg.Add(response);  

            
            if (user != null)
            { 
                msg.Add(user.Id.ToString());
                msg.Add(user.FirstName.ToString());
                msg.Add(user.SecondName.ToString());
                msg.Add(user.Varsta.ToString());
                msg.Add(user.Sex.ToString());
                msg.Add(user.Email.ToString());
                msg.Add(user.Scor.ToString());
                msg.Add(user.ProfileImage.ToString());
                msg.Add(user.Username.ToString());
                msg.Add(user.Facultate.ToString());
            }
            
            msgPacket.WriteMessage(msg);

            ClientSocket.Client.Send(msgPacket.GetPacketBytes());
        }

        public void HandleLoginRequest(PacketReaderService packetReader,TcpClient ClientSocket)
        {
            List<string> message = packetReader.ReadMessage();

            string username = message[1];
            string password = message[2];

            DataRepository<User> dataRepo = new DataRepository<User>();


            User searchUser =  dataRepo.FindUserByUsername(username, password);

            if (searchUser == null)
            {
                searchUser = dataRepo.FindUserByEmail(username, password);
            }
            
            if ( searchUser != null)
            {
                Console.WriteLine($"[{ DateTime.Now}] - { username  }: Connected!");
                LoginServerResponse("LogInSucces!", ClientSocket, searchUser);
            }
            else
            {
                Console.WriteLine($"[{ DateTime.Now}] - { username}: Failed to Connect!" );
                LoginServerResponse("LogInFailed", ClientSocket , searchUser);
            }
        }
        #endregion


       
    }
}
