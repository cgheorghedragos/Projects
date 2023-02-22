using BFFinderCoreApp.Models;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BFFinderCoreApp.Repository
{
    public class GroupRepository
    {

        public List<string> GetGroupsForUser(User user)
        {
            List<string> groupList = new List<string>();

            DatabaseConnectionRepository databaseCon = new();
            
            databaseCon.OpenConnection();
            string queryString = "SELECT GU.id_grup,G.NumeGrup FROM dbo.grup_utilizatori GU, grupuri G " +
                "WHERE G.id = GU.id_grup and GU.id_user  = '" + user.Id + "'    " +
                "AND GU.id_grup NOT IN ('1') ";

            SqlCommand com = new SqlCommand(queryString, databaseCon.GetConnection());
            SqlDataReader searchGroup = com.ExecuteReader();

            while (searchGroup.Read())
            {
                string id_group= Convert.ToString(searchGroup["id_grup"]);
                string NumeGrup= Convert.ToString(searchGroup["NumeGrup"]);
                groupList.Add(id_group);
                groupList.Add(NumeGrup);
            }

            databaseCon.CloseConnection();

            return groupList;
        }

        public List<string> GetAllGroupsForUser(User user)
        {
            List<string> groupsList = new List<string>();

            DatabaseConnectionRepository databaseCon = new();

            databaseCon.OpenConnection();

            string queryString =
               " SELECT Count(G.id) as 'numar_persoane', G.id, G.NumeGrup " +
               " FROM grupuri G LEFT JOIN grup_utilizatori GU ON G.id = GU.id_grup " +
               " WHERE G.id NOT IN(SELECT DISTINCT id_grup FROM grup_utilizatori WHERE id_user = '" + user.Id+"') " +
               " AND G.id NOT IN ('1') "+
               " Group By G.id, G.NumeGrup " +
               " HAVING Count(G.id) < 10 ";

            SqlCommand com = new SqlCommand(queryString, databaseCon.GetConnection());
            SqlDataReader searchGroup = com.ExecuteReader();

            while (searchGroup.Read())
            {
                string id_group = Convert.ToString(searchGroup["id"]);
                string NumeGrup = Convert.ToString(searchGroup["NumeGrup"]);
                string numarPers = Convert.ToString(searchGroup["numar_persoane"]);

                groupsList.Add(id_group);
                groupsList.Add(NumeGrup);
                groupsList.Add(numarPers);
            }

            databaseCon.CloseConnection();
            return groupsList;
        }

        public void AddNewGroup(string numeGrup)
        {
            DatabaseConnectionRepository databaseCon = new();

            databaseCon.OpenConnection();

            string queryString = "INSERT INTO grupuri(NumeGrup) VALUES('" + numeGrup + "')";
            SqlCommand com = new SqlCommand(queryString, databaseCon.GetConnection());


            com.ExecuteNonQuery();

            databaseCon.CloseConnection();
        }

        public void JoinGroup(User user, String idGrup)
        {
            DatabaseConnectionRepository databaseCon = new();

            databaseCon.OpenConnection();

            string queryString = "INSERT INTO grup_utilizatori(id_grup,id_user) VALUES('" + idGrup + "' , '"+user.Id+"' )";
            SqlCommand com = new SqlCommand(queryString, databaseCon.GetConnection());


            com.ExecuteNonQuery();

            databaseCon.CloseConnection();
        }
    }
}
