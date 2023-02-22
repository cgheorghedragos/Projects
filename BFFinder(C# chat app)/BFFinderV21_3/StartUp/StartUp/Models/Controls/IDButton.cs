using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace StartUp.Models.Controls
{
    internal class IDButton : Button
    {
        [Category("Identification")]
        [Description("The identification id")]
        public int ID { set; get; } 
    }
}
