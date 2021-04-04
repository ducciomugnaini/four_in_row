using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace FourInRow.Entities
{
    public class Player
    {
        public int Id { get; set; }
        public string Nickname { get; set; }
        public int Wins { get; set; }
        public int Loses { get; set; }
        public DateTime JoinOn { get; set; }
        public string ContextConnection { get; set; }

    }
}
