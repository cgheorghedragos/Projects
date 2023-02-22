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
    public class GroupService
    {
        #region CreateGroup
        public void CreateGroupRequestFunct(string numeGrup, TcpClient _client)
        {
            var loadPacket = new PacketBuilderService();
            loadPacket.WritePacketHeaderCode(PacketHeaders.CreateGroupRequest);
            List<string> message = new List<string>();

            //messages to transmit
 
            message.Add(numeGrup.ToString());

            loadPacket.WriteMessage(message);
            _client.Client.Send(loadPacket.GetPacketBytes());
        }

        public List<string> CreateGroupClientResponse(PacketReaderService PacketReader)
        {
            List<string> msg = PacketReader.ReadMessage();
            return msg;
        }

        public void CreateGroupServerResponse(string response, TcpClient ClientSocket)
        {
            var msgPacket = new PacketBuilderService();
            msgPacket.WritePacketHeaderCode(PacketHeaders.CreateGroupResponse);
            List<string> msg = new List<string>();
            msg.Add(response);

            msgPacket.WriteMessage(msg);

            ClientSocket.Client.Send(msgPacket.GetPacketBytes());
        }

        public void HandleCreateGroupRequest(PacketReaderService packetReader, TcpClient ClientSocket)
        {
            List<string> message = packetReader.ReadMessage();

            string numeGrup = Convert.ToString(message[1]);

            GroupRepository gr = new GroupRepository();


            try
            {
                gr.AddNewGroup(numeGrup);
                Console.WriteLine($"[{ DateTime.Now}] - : Succes to join group!");
                CreateGroupServerResponse("CreateGroupSucces!", ClientSocket);
            }
            catch (Exception ex)
            {
                Console.WriteLine($"[{ DateTime.Now}] - : Failed to join group!");
                CreateGroupServerResponse("CreateGroupFailed", ClientSocket);
            }

        }
        #endregion
        ///Join Group
        ///
        #region Join Group
        public void JoinGroupRequestFunct(User user, string idGroup, TcpClient _client)
        {
            var loadPacket = new PacketBuilderService();
            loadPacket.WritePacketHeaderCode(PacketHeaders.JoinGroupRequest);
            List<string> message = new List<string>();

            //messages to transmit
            message.Add(user.Id.ToString());
            message.Add(idGroup.ToString());

            loadPacket.WriteMessage(message);
            _client.Client.Send(loadPacket.GetPacketBytes());
        }

        public List<string> JoinGroupClientResponse(PacketReaderService PacketReader)
        {
            List<string> msg = PacketReader.ReadMessage();
            return msg;
        }

        public void JoinGroupServerResponse(string response, TcpClient ClientSocket)
        {
            var msgPacket = new PacketBuilderService();
            msgPacket.WritePacketHeaderCode(PacketHeaders.JoinGroupResponse);
            List<string> msg = new List<string>();
            msg.Add(response); 

            msgPacket.WriteMessage(msg);

            ClientSocket.Client.Send(msgPacket.GetPacketBytes());
        }

        public void HandleJoinGroupRequest(PacketReaderService packetReader, TcpClient ClientSocket)
        {
            List<string> message = packetReader.ReadMessage();
            List<string> groups = new List<string>();
            User user = new User();

            user.Id = Convert.ToInt32(message[1]);
            string groupId = Convert.ToString(message[2]);

            GroupRepository gr = new GroupRepository();


            try
            {
                 gr.JoinGroup(user, groupId);
                Console.WriteLine($"[{ DateTime.Now}] - { user.Username }: Succes to join group!");
                JoinGroupServerResponse("JoinGroupSucces!", ClientSocket);
            }
            catch (Exception ex)
            {
                Console.WriteLine($"[{ DateTime.Now}] - { user.Username}: Failed to join group!");
                JoinGroupServerResponse("JoinGroupFailed", ClientSocket);
            }

        }
        #endregion

        #region AllGroups
        /// All Groups
        public void AllGroupRequestFunct(User user, TcpClient _client)
        {
            var loadPacket = new PacketBuilderService();
            loadPacket.WritePacketHeaderCode(PacketHeaders.AllGroupRequest);
            List<string> message = new List<string>();

            //messages to transmit
            message.Add(user.Id.ToString());

            loadPacket.WriteMessage(message);
            _client.Client.Send(loadPacket.GetPacketBytes());
        }

        public List<string> AllGroupClientResponse(PacketReaderService PacketReader)
        {
            List<string> msg = PacketReader.ReadMessage();
            return msg;
        }

        public void AllGroupServerResponse(string response, TcpClient ClientSocket, List<string> groups)
        {
            var msgPacket = new PacketBuilderService();
            msgPacket.WritePacketHeaderCode(PacketHeaders.AllGroupResponse);
            List<string> msg = new List<string>();
            msg.Add(response);

            foreach (string group in groups)
            {
                msg.Add(group);
            }

            msgPacket.WriteMessage(msg);

            ClientSocket.Client.Send(msgPacket.GetPacketBytes());
        }

        public void HandleAllGroupRequest(PacketReaderService packetReader, TcpClient ClientSocket)
        {
            List<string> message = packetReader.ReadMessage();
            List<string> groups = new List<string>();
            User user = new User();

            user.Id = Convert.ToInt32(message[1]);

            GroupRepository gr = new GroupRepository();


            try
            {
                groups = gr.GetAllGroupsForUser(user);
                Console.WriteLine($"[{ DateTime.Now}] -: Succes to load All groups!");
                AllGroupServerResponse("AllGroupsSucces!", ClientSocket, groups);
            }
            catch (Exception ex)
            {
                Console.WriteLine($"[{ DateTime.Now}] - : Failed to load All groups!");
                AllGroupServerResponse("AllGroupsFailed", ClientSocket, groups);
            }

        }
        #endregion

        #region GroupLoadForOneUser
        /// For one User
        /// 
        public void GroupLoadRequestFunct(User user, TcpClient _client)
        {
            var loadPacket = new PacketBuilderService();
            loadPacket.WritePacketHeaderCode(PacketHeaders.LoadGroupRequest);
            List<string> message = new List<string>();

            //messages to transmit
            message.Add(user.Id.ToString());
       
            loadPacket.WriteMessage(message);
            _client.Client.Send(loadPacket.GetPacketBytes());
        }

        public List<string> LoadGroupClientResponse(PacketReaderService PacketReader)
        {
            List<string> msg = PacketReader.ReadMessage();
            return msg;
        }

        public void LoadGroupServerResponse(string response, TcpClient ClientSocket, List<string> groups)
        {
            var msgPacket = new PacketBuilderService();
            msgPacket.WritePacketHeaderCode(PacketHeaders.LoadGroupResponse);
            List<string> msg = new List<string>();
            msg.Add(response);

            foreach(string group in groups)
            {
                msg.Add(group);
            }

            msgPacket.WriteMessage(msg);

            ClientSocket.Client.Send(msgPacket.GetPacketBytes());
        }

        public void HandleLoadGroupRequest(PacketReaderService packetReader, TcpClient ClientSocket)
        {
            List<string> message = packetReader.ReadMessage();
            List<string> groups = new List<string>();
            User user = new User();

            user.Id = Convert.ToInt32(message[1]);

            GroupRepository gr = new GroupRepository();


            try
            {
                groups = gr.GetGroupsForUser(user);
                Console.WriteLine($"[{ DateTime.Now}] - { user.Username }: Succes to load groups!");
                LoadGroupServerResponse("LoadGroupsSucces!", ClientSocket,groups);
            }
            catch (Exception ex)
            {
                Console.WriteLine($"[{ DateTime.Now}] - { user.Username}: Failed to load groups!");
                LoadGroupServerResponse("LoadGroupsFailed", ClientSocket, groups);
            }

        }
        #endregion

        #region LoadMessage
        /// Messages

        public void LoadMessagesRequestFunct(User user, int idGroup, TcpClient _client)
        {
            var editPacket = new PacketBuilderService();
            editPacket.WritePacketHeaderCode(PacketHeaders.LoadMessagesRequest);
            List<string> message = new List<string>();

            //messages to transmit
            message.Add(user.Id.ToString());
            message.Add(idGroup.ToString());

            editPacket.WriteMessage(message);
            _client.Client.Send(editPacket.GetPacketBytes());
        }

        public List<string> LoadMessagesClientResponse(PacketReaderService PacketReader)
        {
            List<string> msg = PacketReader.ReadMessage();
            return msg;
        }

        public void LoadMessagesServerResponse(string response, TcpClient ClientSocket, List<Message> messages)
        {
            var msgPacket = new PacketBuilderService();
            msgPacket.WritePacketHeaderCode(PacketHeaders.LoadMessagesResponse);
            List<string> msg = new List<string>();
            msg.Add(response);

            foreach (Message message in messages)
            {
                msg.Add(message.userNameSender);
                msg.Add(message.photoSender);
                msg.Add(message.message);
                msg.Add(message.hourSent);
            }


            msgPacket.WriteMessage(msg);

            ClientSocket.Client.Send(msgPacket.GetPacketBytes());
        }

        public void HandleLoadMessagesRequest(PacketReaderService packetReader, TcpClient ClientSocket)
        {
            List<string> message = packetReader.ReadMessage();
            List<Message> messages = null;
            User user = new User();
            int groupID;

            user.Id = Convert.ToInt32(message[1]);
            groupID = Convert.ToInt32(message[2]);
            
            MessageRepository mr = new MessageRepository();


            try
            {
                messages = mr.ImportLastMessages(100, groupID);
                Console.WriteLine($"[{ DateTime.Now}] - { user.Username }: Succes to Load messages for groupID {groupID.ToString()}");
                LoadMessagesServerResponse("LoadMsgSucces!", ClientSocket, messages);
            }
            catch (Exception ex)
            {
                Console.WriteLine($"[{ DateTime.Now}] - { user.Username}: Failed to Load messages for groupID {groupID.ToString()}");
                LoadMessagesServerResponse("LoadMsgFailed", ClientSocket, messages);
            }

        }

        #endregion

       
    }
}
