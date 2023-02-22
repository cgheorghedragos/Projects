﻿using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace StartUp.Models.Controls
{
    public class IDPanel :Panel
    {
        [Category("Identification")]
        [Description("The identification id")]
        public int ID { set; get; }
    }
}
