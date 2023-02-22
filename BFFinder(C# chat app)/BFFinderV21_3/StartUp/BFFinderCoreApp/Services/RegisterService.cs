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
    public class RegisterService
    {
        public void RegisterRequestFunct(string password, User user, TcpClient _client)
        {
            var connectPacket = new PacketBuilderService();
            connectPacket.WritePacketHeaderCode(PacketHeaders.RegisterRequest);
            List<string> message = new List<string>();

            //messages to transmit
            message.Add(user.FirstName);
            message.Add(user.SecondName);
            message.Add(user.Varsta.ToString());
            message.Add(user.Sex.ToString());
            message.Add(user.Email);
            message.Add(user.Username);
            message.Add(user.Facultate);
            message.Add(user.Scor.ToString());
            message.Add(user.ProfileImage);
            message.Add(password);

            connectPacket.WriteMessage(message);
            _client.Client.Send(connectPacket.GetPacketBytes());
        }

        public List<string> RegisterClientResponse(PacketReaderService PacketReader)
        {
            List<string> msg = PacketReader.ReadMessage();
            return msg;
        }

        public void RegisterServerResponse(string response, TcpClient ClientSocket)
        {
            var msgPacket = new PacketBuilderService();
            msgPacket.WritePacketHeaderCode(PacketHeaders.RegisterResponse);
            List<string> msg = new List<string>();
            msg.Add(response);

            msgPacket.WriteMessage(msg);

            ClientSocket.Client.Send(msgPacket.GetPacketBytes());
        }

        public void HandleRegisterRequest(PacketReaderService packetReader, TcpClient ClientSocket)
        {
            List<string> message = packetReader.ReadMessage();
            User user = new User();

            user.FirstName= message[1];
            user.SecondName = message[2];
            user.Varsta = Convert.ToInt32(message[3]);
            user.Sex =Convert.ToInt32( message[4] );
            user.Email = message[5];
            user.Username = message[6];
            user.Facultate = message[7];
            user.Scor = Convert.ToInt32(message[8]);
            user.ProfileImage = message[9];
            string password = message[10];

            try
            {
                DataRepository<User> dR = new DataRepository<User>();
                GroupRepository gr = new GroupRepository();
                

                dR.Insert(user, password);

                User uss = dR.FindUserByUsername(user.Username, password);
                if (!user.Facultate.Equals("")){
                    gr.JoinGroup(uss, "1");
                }
                Console.WriteLine($"[{ DateTime.Now}] - { user.Username  }: Registered succesfully!");
                RegisterServerResponse("RegisterSucces!", ClientSocket);
            }
            catch (Exception ex)
            {
                Console.WriteLine($"[{ DateTime.Now}] - { user.Username}: Failed to register!");
                RegisterServerResponse(ex.ToString(), ClientSocket);
            }
        }
    }
}
