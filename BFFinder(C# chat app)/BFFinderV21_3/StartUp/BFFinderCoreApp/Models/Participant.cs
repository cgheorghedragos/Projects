﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Drawing;
using System.Threading.Tasks;

namespace BFFinderCoreApp.Models
{
    public class Participant
    {
        private int _id;
        private string _name;
        private Image _image;

        public int ID
        {
            get { return _id; }
            set { _id = value; }
        }

        public string Name
        {
            get { return _name; }
            set { _name = value; }
        }

        public Image Image
        {
            get { return _image; }
            set { _image = value; }
        }
    }
}
