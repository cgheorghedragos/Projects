using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BFFinderCoreApp.Models
{
    public class User
    {
        private List<Groups> _groups;
        public int Id { get; set; }
        public String FirstName { get; set; }
        public String SecondName { get; set; }
        public String Email { get; set; }
        public int Varsta { get; set; }
        public int Sex { get; set; }
        public int Scor { get; set; }
        public string ProfileImage { get; set; }
        public string Username { get; set; }
        
        public string Facultate { get; set; }

        public void SetGroup(List<Groups> groups)
        {
            _groups = groups;
        }

        public List<Groups> GetGroups()
        {
            return _groups;
        }



    }
}
