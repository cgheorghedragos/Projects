using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BFFinderCoreApp.Models
{
    public class Groups
    {
        private List<Participant> participants;
        private int groupID;

        public void SetGroup(int group)
        {
            groupID = group;
        }

        public int GetGroup()
        {
            return groupID;
        }

        public List<Participant> GetParticipants()
        {
            return participants;
        }

        public void SetParticipants(List<Participant> participants)
        {
            this.participants = participants;
        }
    }
}
