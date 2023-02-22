using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BFFinderCoreApp.Repository
{
    internal class DatabaseConnectionRepository
    {
        private readonly SqlConnection _conString = new SqlConnection("Data Source=GHEORGHEDRAGOS\\SQLEXPRESS;Initial Catalog=BFFinderDatabase;Integrated Security=True");

       
        public void OpenConnection()
        {
            _conString.Open();
        }

        public void CloseConnection()
        {
            _conString.Close();
        }

        public SqlConnection GetConnection()
        {
            return _conString;
        }
    }
}
