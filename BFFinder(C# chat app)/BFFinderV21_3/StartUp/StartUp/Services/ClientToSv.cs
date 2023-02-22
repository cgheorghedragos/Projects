using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Sockets;
using System.Text;
using System.Threading.Tasks;
using BFFinderCoreApp.Services;
using BFFinderCoreApp.Enums;

namespace StartUp.Services
{
    public class ClientToSv
    {
        private string ip = "127.0.0.1";
        int port = 5000;
        public List<string> returnMessage { get; set; }
        public List<string> returnMessage2 { get; set; }
        public List<string> returnMessageGroup { get; set; }

        public List<string> returnMessageGroup2 { get; set; }
        private TcpClient _client;
        public PacketReaderService PacketReader;
        private static ClientToSv instance = null;
        public static ClientToSv getInstance
        {
            get
            {
                if (instance == null)
                {
                    instance = new ClientToSv();
                }
                return instance;
            }
        }
        private ClientToSv()
        {
            if (_client == null)
            {
                _client = new TcpClient();
            }

            ConnectToServer();

        }
        public bool isConnected()
        {
            if (_client != null)              //later x1 probl
                                              //{
                                              //    if (_client.Connected)
                                              //        return true;
                                              //}

                if (_client.Connected)
                    return true;
            return false;
        }

        public void clearAll()
        {
            if (this.returnMessage != null)
            {
                this.returnMessage.Clear();
            }
            return;
        }

        public void clearAll2()
        {
            if (this.returnMessage2 != null)
            {
                this.returnMessage2.Clear();
            }
            return;
        }

        public void clearRM2()
        {
            if(this.returnMessageGroup!= null)
            {
                this.returnMessageGroup.Clear();
            }
            return;
        }

        public void clearRM3()
        {
            if (this.returnMessageGroup2 != null)
            {
                this.returnMessageGroup2.Clear();
            }
            return;
        }


        public void ConnectToServer()
        {
            try
            {
                if (!_client.Connected)
                {
                    string Hostname = null;
                    if (ip == "127.0.0.1")
                    {
                        string MyIP = "";
                        IPHostEntry Host = default(IPHostEntry);
                        Hostname = System.Environment.MachineName;
                        Host = Dns.GetHostEntry(Hostname);
                        foreach (IPAddress IP in Host.AddressList)
                        {
                            if (IP.AddressFamily == System.Net.Sockets.AddressFamily.InterNetwork)
                            {
                                MyIP = Convert.ToString(IP);
                            }
                        }
                        ip = MyIP;
                    }
                    try
                    {
                        _client.Connect(ip, port);
                        PacketReader = new PacketReaderService(_client.GetStream());
                    }
                    catch (Exception e)
                    {
                        MessageBox.Show("Unable to connect to Server! Server down \n" + e.Message);
                        instance = null;
                        _client = null;
                        return;
                    }
                    if (!string.IsNullOrEmpty(Hostname))
                    {
                        List<string> message = new List<string>();
                        var connectPacket = new PacketBuilderService();
                        connectPacket.WritePacketHeaderCode(PacketHeaders.ConectToServerRequest);
                        message.Add(Hostname);
                        connectPacket.WriteMessage(message);
                        _client.Client.Send(connectPacket.GetPacketBytes());
                    }

                    ReadPackets();

                }
            }
            catch (Exception e)
            {
                MessageBox.Show("Unable to connect to Server! Server down \n" + e.Message);
                instance = null;
                _client = null;
                return;
            }
        }

        private void ReadPackets()
        {
            Task.Run(() =>
            {
                while (true)
                {
                    try
                    {
                        var opcode = PacketReader.ReadByte();
                        switch (opcode)
                        {
                            case 1:
                                //
                                List<string> messaje = PacketReader.ReadMessage();
                                returnMessage = messaje;
                                break;
                            case (byte)PacketHeaders.ConnectToServerResponse:
                                ConnectToServerResponse();
                                break;
                            case (byte)PacketHeaders.LoginResponse:
                                LoginService loginService = new LoginService();
                                returnMessage = loginService.LoginClientResponse(PacketReader);
                                break;
                            case (byte)PacketHeaders.UpdateUserResponse:
                                UserService us = new UserService();
                                returnMessage = us.UpdateUserClientResponse(PacketReader);
                                break;
                            case (byte)PacketHeaders.LoadGroupResponse:
                                GroupService groupService = new GroupService(); 
                                returnMessage = groupService.LoadGroupClientResponse(PacketReader);
                                break;
                            case (byte)PacketHeaders.LoadMessagesResponse:
                                GroupService messageService = new GroupService();
                                returnMessage = messageService.LoadMessagesClientResponse(PacketReader);
                                break;
                            case (byte)PacketHeaders.RegisterResponse:
                                RegisterService reg = new RegisterService();
                                returnMessage = reg.RegisterClientResponse(PacketReader);
                                break;
                            case (byte)PacketHeaders.JoinGroupResponse:
                                GroupService gs = new GroupService();
                                returnMessage = gs.JoinGroupClientResponse(PacketReader);
                                break;
                            case (byte)PacketHeaders.AllGroupResponse:
                                GroupService gss = new GroupService();
                                returnMessage = gss.AllGroupClientResponse(PacketReader);
                                break;
                            case (byte)PacketHeaders.CreateGroupResponse:
                                GroupService gsss = new GroupService();
                                returnMessage = gsss.CreateGroupClientResponse(PacketReader);
                                break;
                            case (byte)PacketHeaders.BroadcastMessageResponse:
                                 MessageService msgService = new MessageService();
                                returnMessageGroup = msgService.SendMessageClientResponse(PacketReader);
                                break;
                            case (byte)PacketHeaders.CheckUserExistResponse:
                                UserService userService = new UserService();
                                 returnMessage = userService.SearchUserClientResponse(PacketReader);
                                break;
                            case (byte)PacketHeaders.BroadcastFacultyMessageResponse:
                                MessageService msgServicce = new MessageService();
                                returnMessageGroup2 = msgServicce.SendFacultyMSGClientResponse(PacketReader);
                                break;
                            case (byte)PacketHeaders.LoadFacultyResponse:
                                MessageService mm = new MessageService();
                                returnMessage2 = mm.FacultyClientResponse(PacketReader);
                                break;
                            default:
                                Console.WriteLine("ah, yes .. ");
                                break;
                        }
                    }
                    catch(Exception ex)
                    {
                        MessageBox.Show(ex.ToString());
                        instance = null;
                        _client = null;
                        break;
                    }
                }
            });
        }

        public void ConnectToServerResponse()
        {
            List<string> msg = PacketReader.ReadMessage();
            while (msg == null)
            {
                //just wait
            }
            while (msg.Count == 0)
            {
                //just wait
            }
            string response = msg[1];
            if (response == "Connection failed!")
            {
                MessageBox.Show("Unable to connect to server!");
            }
        }

        public TcpClient getClient()
        {
            return _client;
        }


    }
}
