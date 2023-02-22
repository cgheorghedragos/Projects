using BFFinderCoreApp.Models;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BFFinderCoreApp.Repository
{
    public class MessageRepository:IMessageRepository
    {
        public void SendMessage(int idUser,int idGroup, string message,string ora) {
            DatabaseConnectionRepository databaseCon = new DatabaseConnectionRepository();
            databaseCon.OpenConnection();

            string queryString = "INSERT INTO mesaje(id_user,id_grup,mesaj,ora) " + "VALUES('" +
                idUser + "','" + idGroup + "','" + message + "','"+ora+"')";

            SqlCommand com = new SqlCommand(queryString, databaseCon.GetConnection());
            com.ExecuteNonQuery();

            databaseCon.CloseConnection();
        }



        public List<Message> ImportLastMessages(int numberOfMessages,int idGroup)
        {
            DatabaseConnectionRepository databaseCon = new DatabaseConnectionRepository();
            databaseCon.OpenConnection();
            List<Message> messages = new List<Message>();

            string 
                queryString = 
            "SELECT TOP " + numberOfMessages + "  M.mesaj as 'mesaj', U.prenume as 'prenume', U.nume as 'nume', U.image as 'photo' , M.ora as 'ora' , U.facultate as 'facultate'" +
            " FROM mesaje M, users U " +
            " WHERE M.id_user = U.id_user " +
            " AND id_grup = '" + idGroup + "' " +
            " ORDER BY M.id_mesaj DESC ";


            SqlCommand com = new SqlCommand(queryString, databaseCon.GetConnection());
            SqlDataReader importMessages = com.ExecuteReader();

            while (importMessages.Read())
            {
                Message message = new Message();
                message.photoSender = Convert.ToString(importMessages["photo"]);
                message.message = Convert.ToString(importMessages["mesaj"]);
                message.userNameSender = Convert.ToString(importMessages["prenume"]) + " " + Convert.ToString(importMessages["nume"]);
                message.hourSent = Convert.ToString(importMessages["ora"]);
                message.faculty = Convert.ToString(importMessages["facultate"]);
 

                messages.Add(message);
            }

            databaseCon.CloseConnection();

            return messages;
        }

    }
}
