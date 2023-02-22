using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net.Sockets;
using System.Text;
using System.Threading.Tasks;

namespace BFFinderCoreApp.Services
{
    public class PacketReaderService : BinaryReader
    {
        private NetworkStream _ns;
        public PacketReaderService(NetworkStream ns) : base(ns)
        {
            _ns = ns;
        }

        public List<string> ReadMessage()
        {
            if (_ns.CanRead)
            {
                byte[] msgBuffer;
                var length = ReadInt32();
                msgBuffer = new byte[length];
                _ns.Read(msgBuffer, 0, length);

                var msg = Encoding.ASCII.GetString(msgBuffer);
                return DecodeMessage(msg);
            }
            else
            {
                throw new Exception("Nu se poate executa citirea! ");
            }
        }

            public List<string> DecodeMessage(string message)
        {
            List<string> msg = new List<string>();
            string[] helper = message.Split('\0');
            foreach (var x in helper)
            {
                msg.Add(x);
            }
            return msg;
        }
    }
}
