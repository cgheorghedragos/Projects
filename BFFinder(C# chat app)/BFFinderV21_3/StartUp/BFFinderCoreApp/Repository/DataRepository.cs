using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using BFFinderCoreApp.Models;

namespace BFFinderCoreApp.Repository
{
    public class DataRepository<T> : IDataRepository<T>
    {
     
        public User FindUserByUsername(String username,String password)
        {
            DatabaseConnectionRepository databaseCon = new();
            String userSearched = "'"+username+"'";
            String pass = "'"+password+"'";
            User user = null;

            databaseCon.OpenConnection();

            
            string queryString = "SELECT * FROM dbo.users WHERE username = "+userSearched+" AND password = "+pass;

            SqlCommand com = new SqlCommand(queryString,databaseCon.GetConnection());
            SqlDataReader searchUser = com.ExecuteReader();

            if (searchUser.Read())
            { 
                if (Convert.ToString(searchUser["password"]).Equals(password))
                {
                    user = new User();
                    user.Id = Convert.ToInt32(searchUser["id_user"]);
                    user.Varsta = Convert.ToInt32(searchUser["varsta"]);              
                    user.FirstName = Convert.ToString(searchUser["nume"]);
                    user.SecondName = Convert.ToString(searchUser["prenume"]);                    
                    user.Email = Convert.ToString(searchUser["email"]);
                    user.Scor = Convert.ToInt32(searchUser["scor"]);
                    user.Sex = Convert.ToInt32(searchUser["sex"]);
                    user.ProfileImage = Convert.ToString(searchUser["image"]);
                    user.Username = Convert.ToString(searchUser["username"]);
                    user.Facultate = Convert.ToString(searchUser["facultate"]);
                    databaseCon.CloseConnection();
                    return user;
                } 
            }

            databaseCon.CloseConnection();
            return null;

        }



        public int SearchIfAlreadyExist(String Username, String Email)
        {
            String userSearched = "'" + Username + "'";
            String emailSearched = "'" + Email + "'";
            User user = null;

            DatabaseConnectionRepository databaseCon = new();
            databaseCon.OpenConnection();


            string queryString = "SELECT * FROM dbo.users WHERE username = " + userSearched + " OR email = "+emailSearched;

            SqlCommand com = new SqlCommand(queryString, databaseCon.GetConnection());
            SqlDataReader searchUser = com.ExecuteReader();

            if (searchUser.Read())
            {
                databaseCon.CloseConnection();

                return 1;
            }
            databaseCon.CloseConnection();

            return 0;
        }
        public User FindUserByEmail(String email,String password)
        {
            String userSearched = "'" + email + "'";
            String pass = "'" + password + "'";
            User user = null;

            DatabaseConnectionRepository databaseCon = new();

            databaseCon.OpenConnection();
           
            string queryString = "SELECT * FROM users WHERE email = " + userSearched+ " AND password = " + pass;


            SqlCommand com = new SqlCommand(queryString, databaseCon.GetConnection());
            SqlDataReader searchUser = com.ExecuteReader();

            if (searchUser.Read())
            {
                if (Convert.ToString(searchUser["password"]).Equals(password))
                {
                    user = new User();
                    user.Id = Convert.ToInt32(searchUser["id_user"]);
                    user.Varsta = Convert.ToInt32(searchUser["varsta"]);
                    user.FirstName = Convert.ToString(searchUser["nume"]);
                    user.SecondName = Convert.ToString(searchUser["prenume"]);
                    user.Email = Convert.ToString(searchUser["email"]);
                    user.Scor = Convert.ToInt32(searchUser["scor"]);
                    user.Sex = Convert.ToInt32(searchUser["sex"]);
                    user.ProfileImage = Convert.ToString(searchUser["image"]);
                    user.Username = Convert.ToString(searchUser["username"]);
                    user.Facultate = Convert.ToString(searchUser["facultate"]);
                    databaseCon.CloseConnection();

                    return user;
                }
            }


            databaseCon.CloseConnection();
            return null;
        } 
        public void Insert(User user, string password)
        {
            DatabaseConnectionRepository databaseCon = new();

            databaseCon.OpenConnection();

            string usernameAdded = " '" + user.Username.ToString() + "' , ";
            string firstNameAdded = "'" + user.FirstName.ToString() + "' , ";
            string secondNameAdded = "'" + user.SecondName.ToString() + "' , ";
            string sexAdded = "'" + user.Sex.ToString() + "' , ";
            string varstaAdded = "'" + user.Varsta.ToString() + "' , ";
            string scorAdded = "'" + user.Scor.ToString() + "' , ";
            string emailAdded = "'" + user.Email.ToString() + "' , ";
            string profImgAdded = "'" + user.ProfileImage.ToString() + "' , ";
            string passwordAdded = "'" + password.ToString() + "' , ";
            string facultateAdded = "'" + user.Facultate.ToString() + "' ";


            string queryString = "INSERT INTO dbo.users(username,nume,prenume,sex,varsta,scor,email,image,password,facultate) " +
                "VALUES( " + usernameAdded +
                firstNameAdded +
                secondNameAdded +
                sexAdded +
                varstaAdded +
                scorAdded +
                emailAdded +
                profImgAdded +
                passwordAdded + 
                facultateAdded + " )";


            SqlCommand com = new SqlCommand(queryString, databaseCon.GetConnection());

            com.ExecuteNonQuery();

            databaseCon.CloseConnection();

        }
        public void Update(User user, string password)
        {
            DatabaseConnectionRepository databaseCon = new();

            databaseCon.OpenConnection();

            string usernameAdded = " '" + user.Username.ToString() + "' , ";
            string idUser = " '" + user.Id.ToString() + "'";
            string firstNameAdded = "'" + user.FirstName.ToString() + "' , ";
            string secondNameAdded = "'" + user.SecondName.ToString() + "' , ";
            string sexAdded = "'" + user.Sex.ToString() + "' , ";
            string varstaAdded = "'" + user.Varsta.ToString() + "' , ";
            string scorAdded = "'" + user.Scor.ToString() + "' , ";
            string emailAdded = "'" + user.Email.ToString() + "' , ";
            string profImgAdded = "'" + user.ProfileImage.ToString() + "' , ";
            string passwordAdded = "'" + password.ToString() + "' ";


            string queryString = "UPDATE dbo.users " +
                "SET username = " + usernameAdded +
                "nume = " + firstNameAdded +
                "prenume = " + secondNameAdded +
                "sex = " + sexAdded +
                "varsta= " + varstaAdded +
                "scor = " + scorAdded +
                "email = " + emailAdded +
                "image = " + profImgAdded +
                "password = " + passwordAdded + " " +
                "WHERE id_user = " + idUser;


            SqlCommand com = new SqlCommand(queryString, databaseCon.GetConnection());

            com.ExecuteNonQuery();

            databaseCon.CloseConnection();
        }

    }
}
