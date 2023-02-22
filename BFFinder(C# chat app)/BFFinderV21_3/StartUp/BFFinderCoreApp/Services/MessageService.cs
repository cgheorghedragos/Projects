using BFFinderCoreApp.Enums;
using BFFinderCoreApp.Models;
using BFFinderCoreApp.Repository;
using System;
using System.Collections.Generic;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Net.Sockets;
using System.Text;
using System.Threading.Tasks;


namespace BFFinderCoreApp.Services
{
    public class MessageService
    {
        #region SendMessage

        public void SendMessageRequestFunct(string Message, int userID,int groupID,string userPhoto, string fullName, TcpClient _client)
        {
            var connectPacket = new PacketBuilderService();
            connectPacket.WritePacketHeaderCode(PacketHeaders.SendMessageRequest);
            List<string> message = new List<string>();

            //messages to transmit
            message.Add(Message);
            message.Add(userID.ToString());
            message.Add(groupID.ToString());
            message.Add(userPhoto.ToString());
            message.Add(fullName.ToString());

            connectPacket.WriteMessage(message);
            _client.Client.Send(connectPacket.GetPacketBytes());
        }

        public void SendMessageServerResponse(string response,List<string> Messages, TcpClient ClientSocket)
        {
            var msgPacket = new PacketBuilderService();
            msgPacket.WritePacketHeaderCode(PacketHeaders.BroadcastMessageResponse);
            List<string> msg = new List<string>();
            msg.Add(response);

            foreach(string st in Messages)
            {
                msg.Add(st);
            }

            msgPacket.WriteMessage(msg);

            ClientSocket.Client.Send(msgPacket.GetPacketBytes());
        }

        public List<string> SendMessageClientResponse(PacketReaderService PacketReader)
        {
            List<string> msg = PacketReader.ReadMessage();
            return msg;
        }

        public void HandleSendMessageRequest(PacketReaderService packetReader, TcpClient ClientSocket, List<TcpClient> ClientsSocket)
        {
            List<string> message = packetReader.ReadMessage();
            List<string> transmitMsg = new List<string>();
            int userId;
            int groupID;

            string sendMsg = Convert.ToString(message[1]);
            userId = Convert.ToInt32(message[2]);
            groupID = Convert.ToInt32(message[3]);
            string userPhoto = Convert.ToString(message[4]);
            string fullName = Convert.ToString(message[5]);

            transmitMsg.Add(sendMsg);
            transmitMsg.Add(fullName);
            transmitMsg.Add(userPhoto);
            transmitMsg.Add(groupID.ToString());
            transmitMsg.Add(DateTime.Now.ToString());  

            MessageRepository mr = new MessageRepository();


            try
            {
                mr.SendMessage(userId, groupID, sendMsg , DateTime.Now.ToString());
                Console.WriteLine($"[{ DateTime.Now}] - { userId } -id : Succes to SendMessage  for groupID {groupID.ToString()}");

               foreach(TcpClient CS in ClientsSocket)
                {
                    if(CS != null)
                    {
                        SendMessageServerResponse("ReceivedBroadcastMessage!", transmitMsg, CS);
                    }
                    
                }
                
            }
            catch (Exception ex)
            {
                Console.WriteLine($"[{ DateTime.Now}] - { userId } -id: Failed to Send messages for groupID {groupID.ToString()}");
                SendMessageServerResponse("SendMsgFailed", transmitMsg, ClientSocket);
            }

        }
        #endregion

        #region sendFacultyMessage

        public void SendFacultyMSGRequestFunct(string Message, int userID, string faculty,string userPhoto, string fullName, TcpClient _client)
        {
            var connectPacket = new PacketBuilderService();
            connectPacket.WritePacketHeaderCode(PacketHeaders.SendFacultyMesageRequest);
            List<string> message = new List<string>();

            //messages to transmit
            message.Add(Message);
            message.Add(userID.ToString());
            message.Add(faculty.ToString());
            message.Add(userPhoto.ToString());
            message.Add(fullName.ToString());

            connectPacket.WriteMessage(message);
            _client.Client.Send(connectPacket.GetPacketBytes());
        }

        public void SendFacultyMSGServerResponse(string response, List<string> Messages, TcpClient ClientSocket)
        {
            var msgPacket = new PacketBuilderService();
            msgPacket.WritePacketHeaderCode(PacketHeaders.BroadcastFacultyMessageResponse);
            List<string> msg = new List<string>();
            msg.Add(response);

            foreach (string st in Messages)
            {
                msg.Add(st);
            }

            msgPacket.WriteMessage(msg);

            ClientSocket.Client.Send(msgPacket.GetPacketBytes());
        }

        public List<string> SendFacultyMSGClientResponse(PacketReaderService PacketReader)
        {
            List<string> msg = PacketReader.ReadMessage();
            return msg;
        }

        public void HandleSendFacultyMSGRequest(PacketReaderService packetReader, TcpClient ClientSocket, List<TcpClient> ClientsSocket)
        {
            List<string> message = packetReader.ReadMessage();
            List<string> transmitMsg = new List<string>();
            int userId;
            string  faculty;

            string sendMsg = Convert.ToString(message[1]);
            userId = Convert.ToInt32(message[2]);
            faculty = Convert.ToString(message[3]);
            string userPhoto = Convert.ToString(message[4]);
            string fullName = Convert.ToString(message[5]);

            transmitMsg.Add(sendMsg);
            transmitMsg.Add(fullName);
            transmitMsg.Add(userPhoto);
            transmitMsg.Add(faculty.ToString());
            transmitMsg.Add(DateTime.Now.ToString());

            MessageRepository mr = new MessageRepository();


            try
            {
                mr.SendMessage(userId, 1, sendMsg, DateTime.Now.ToString());
                Console.WriteLine($"[{ DateTime.Now}] - { userId } -id : Succes to SendMessage  for groupID {"1".ToString()}");

                foreach (TcpClient CS in ClientsSocket)
                {
                    if (CS != null)
                    {
                        SendFacultyMSGServerResponse("ReceivedBroadcastFacultyMessage!", transmitMsg, CS);
                    }

                }

            }
            catch (Exception ex)
            {
                Console.WriteLine($"[{ DateTime.Now}] - { userId } -id: Failed to Send messages for groupID {"1".ToString()}");
                SendFacultyMSGServerResponse("SendFacultyMsgFailed", transmitMsg, ClientSocket);
            }

        }

        #endregion

        #region LoadFacultyMessage
        public void FacultyRequestFunct(User user, TcpClient _client)
        {
            var editPacket = new PacketBuilderService();
            editPacket.WritePacketHeaderCode(PacketHeaders.LoadFacultyRequest);
            List<string> message = new List<string>();

            //messages to transmit
            message.Add(user.Id.ToString());

            editPacket.WriteMessage(message);
            _client.Client.Send(editPacket.GetPacketBytes());
        }

        public List<string> FacultyClientResponse(PacketReaderService PacketReader)
        {
            List<string> msg = PacketReader.ReadMessage();
            return msg;
        }

        public void FacultyServerResponse(string response, TcpClient ClientSocket, List<Message> messages)
        {
            var msgPacket = new PacketBuilderService();
            msgPacket.WritePacketHeaderCode(PacketHeaders.LoadFacultyResponse);
            List<string> msg = new List<string>();
            msg.Add(response);

            foreach (Message message in messages)
            {
                msg.Add(message.userNameSender);
                msg.Add(message.photoSender);
                msg.Add(message.message);
                msg.Add(message.hourSent);
                msg.Add(message.faculty);
            }


            msgPacket.WriteMessage(msg);

            ClientSocket.Client.Send(msgPacket.GetPacketBytes());
        }

        public void HandleFacultyRequest(PacketReaderService packetReader, TcpClient ClientSocket)
        {
            List<string> message = packetReader.ReadMessage();
            List<Message> messages = null;
            User user = new User();

            user.Id = Convert.ToInt32(message[1]);

            MessageRepository mr = new MessageRepository();


            try
            {
                messages = mr.ImportLastMessages(4, 1);
                Console.WriteLine($"[{ DateTime.Now}] - { user.Username }: Succes to Load messages faculty messages");
                FacultyServerResponse("LoadFacultyMsgSucces!", ClientSocket, messages);
            }
            catch (Exception ex)
            {
                Console.WriteLine($"[{ DateTime.Now}] - { user.Username}: Failed to Load faculty messages");
                FacultyServerResponse("LoadFacultyMsgFailed", ClientSocket, messages);
            }

        }

        #endregion
    }
}

