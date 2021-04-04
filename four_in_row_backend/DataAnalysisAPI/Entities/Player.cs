using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace FourInRow.Entities
{
    public class ClientPlayer
    {
        public string Nickname { get; set; }
        public int Wins { get; set; }
        public int Loses { get; set; }

        public ClientPlayer() { }
    }

    public class Player : ClientPlayer
    {
        public int Id { get; set; }
        public DateTime JoinOn { get; set; }
        public string ContextConnection { get; set; }

        public Player() { }

        public Player(ClientPlayer clientPlayer)
        {
            Nickname = clientPlayer.Nickname;
            Wins = clientPlayer.Wins;
            Loses = clientPlayer.Loses;
            JoinOn = DateTime.Now;
            ContextConnection = "";
        }

    }
}
