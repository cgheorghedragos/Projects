using BFFinderCoreApp.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BFFinderCoreApp.Repository
{
    internal interface IMessageRepository
    {
        void SendMessage(int idSender, int idGroup, string message, string ora);
      
    }
}
