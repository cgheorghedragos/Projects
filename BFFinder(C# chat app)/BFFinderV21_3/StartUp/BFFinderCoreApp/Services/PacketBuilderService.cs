using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using BFFinderCoreApp.Enums;

namespace BFFinderCoreApp.Services
{
    public class PacketBuilderService
    {
        private MemoryStream _ms;
        public PacketBuilderService()
        {
            _ms = new MemoryStream();
        }

        public void WritePacketHeaderCode(PacketHeaders header)
        {
            byte opCode = Convert.ToByte(header);
            _ms.WriteByte(opCode);
        }

        public void WriteMessage(List<string> packetMessage)
        {
            var msg = GetPacketMessage(packetMessage);
            var msgLenght = msg.Length;
            _ms.Write(BitConverter.GetBytes(msgLenght));
            _ms.Write(Encoding.ASCII.GetBytes(msg));

        }

        public string GetPacketMessage(List<string> packetMessage)
        {
            string message = "";
            char delimitator = '\0';   
            message = packetMessage.Count.ToString() + '\0';
            string message2 = string.Join(delimitator, packetMessage.ToArray());
            return message + message2;
        }

        public byte[] GetPacketBytes()
        {
            return _ms.ToArray();
        }
    }
}
