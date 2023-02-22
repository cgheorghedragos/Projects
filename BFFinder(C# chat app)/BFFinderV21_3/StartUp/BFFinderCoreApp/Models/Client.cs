using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Sockets;
using System.Text;
using System.Threading.Tasks;

namespace BFFinderCoreApp.Models
{
    public class Client
    {
        
        public string Hostname { get; set; }
        public Guid UID { get; set; }
        public TcpClient ClientSocket { get; set; }
    }
}
