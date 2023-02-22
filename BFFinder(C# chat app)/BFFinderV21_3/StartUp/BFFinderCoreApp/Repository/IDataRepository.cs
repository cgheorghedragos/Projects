using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using BFFinderCoreApp.Models;

namespace BFFinderCoreApp.Repository
{
    internal interface IDataRepository<T>
    {
        User FindUserByUsername(String username, String password);
        User FindUserByEmail(String email, String password);
        void Insert(User user,string password);
        void Update(User user, string password);

    }
}
