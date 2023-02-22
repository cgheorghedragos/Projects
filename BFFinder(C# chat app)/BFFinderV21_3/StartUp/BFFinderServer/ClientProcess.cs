using BFFinderCoreApp.Enums;
using BFFinderCoreApp.Models;
using BFFinderCoreApp.Services;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Sockets;
using System.Text;
using System.Threading.Tasks;

namespace BFFinderServer
{
    public class ClientProcess : Client
    {
        PacketReaderService _packetReader;
        public ClientProcess()
        {

        }

        public ClientProcess(TcpClient client)
        {
            ClientSocket = client;
            UID = Guid.NewGuid();
            _packetReader = new PacketReaderService(ClientSocket.GetStream());

            var opcode = _packetReader.ReadByte();
            List<string> message = _packetReader.ReadMessage();
            Hostname = message[1];

            Console.WriteLine($"[{DateTime.Now}]: Client has connected with the username: {Hostname}");

            Task.Run(() => Process());
        }
        void Process()
        {
            while (true)
            {
                try
                {
                    var opCode = _packetReader.ReadByte();

                    switch (opCode)
                    {
                        case (byte)PacketHeaders.Dofirst:
                            List<string> messaj = _packetReader.ReadMessage();
                            string ceva = messaj[1];
                            Console.WriteLine($"[{DateTime.Now}] - {Hostname} : {ceva}");

                            //Program.SendBackToUserMessage();

                            break;
                        case (byte)PacketHeaders.DoSecond:
                            List<string> messaje = _packetReader.ReadMessage();
                            string altceva = messaje[1];
                            Console.WriteLine($"[{DateTime.Now}] - {Hostname} : {altceva}");
                            break;

                        case (byte)PacketHeaders.LoginRequest:
                            {
                                LoginService loginService = new LoginService();
                                loginService.HandleLoginRequest(_packetReader, ClientSocket);
                            }
                            break;
                        case (byte)PacketHeaders.UpdateUserRequest:
                            {
                                UserService us = new UserService();
                                us.HandleUpdateUserRequest(_packetReader, ClientSocket);
                            }
                            break;
                        case (byte)PacketHeaders.LoadMessagesRequest:
                            {
                                GroupService groupService = new GroupService();
                                groupService.HandleLoadMessagesRequest(_packetReader, ClientSocket);
                            }
                            break;
                        case (byte)PacketHeaders.LoadGroupRequest:
                            {
                                GroupService groupService = new GroupService();
                                groupService.HandleLoadGroupRequest(_packetReader, ClientSocket);
                            }
                            break;
                        case (byte)PacketHeaders.RegisterRequest:
                            {
                                RegisterService registerService = new RegisterService();
                                registerService.HandleRegisterRequest(_packetReader, ClientSocket);
                            }
                            break;
                        case (byte)PacketHeaders.JoinGroupRequest:
                            {
                                GroupService groupService = new GroupService();
                                groupService.HandleJoinGroupRequest(_packetReader, ClientSocket);
                            }
                            break;
                        case (byte)PacketHeaders.CreateGroupRequest:
                            {
                                GroupService groupService = new GroupService();
                                groupService.HandleCreateGroupRequest(_packetReader, ClientSocket);
                            }
                            break;
                        case (byte)PacketHeaders.AllGroupRequest:
                            {
                                GroupService groupService = new GroupService();
                                groupService.HandleAllGroupRequest(_packetReader, ClientSocket);
                            }
                            break;
                        case (byte)PacketHeaders.SendMessageRequest:
                            {
                                List<TcpClient> clientList = new List<TcpClient>();
                                List<ClientProcess> cp = Program.users;
                                
                                foreach(ClientProcess process in cp)
                                {
                                    if (process.ClientSocket.Client != null)
                                    {
                                        clientList.Add(process.ClientSocket);
                                    }
                                }
                                MessageService myMsgService = new MessageService();

                                myMsgService.HandleSendMessageRequest(_packetReader, ClientSocket, clientList);


                            }
                            break;
                        case (byte)PacketHeaders.CheckUserExistRequest:
                            {
                                UserService uss = new UserService();
                                uss.HandleSearchUserRequest(_packetReader, ClientSocket);
                            }
                            break;
                        case (byte)PacketHeaders.SendFacultyMesageRequest:
                            {
                                List<TcpClient> clientList = new List<TcpClient>();
                                List<ClientProcess> cp = Program.users;

                                foreach (ClientProcess process in cp)
                                {
                                    if (process.ClientSocket.Client != null)
                                    {
                                        clientList.Add(process.ClientSocket);
                                    }
                                }
                                MessageService myMsgService = new MessageService();

                                myMsgService.HandleSendFacultyMSGRequest(_packetReader, ClientSocket, clientList);
                            }
                            break;

                        case (byte)PacketHeaders.LoadFacultyRequest:
                            {
                                MessageService ms = new MessageService();
                                ms.HandleFacultyRequest(_packetReader, ClientSocket);
                            }
                            break;
                        default:
                            break;
                    }
                }
                catch (Exception)
                {
                    Console.WriteLine($"[{Hostname}]:Disconnected!");
                    ClientSocket.Close();
                    break;
                }
            }
        }
    }
}
