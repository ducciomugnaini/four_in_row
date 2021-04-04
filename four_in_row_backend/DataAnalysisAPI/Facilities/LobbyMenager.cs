using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace FourInRow.Facilities
{
    public sealed class LobbyManager
    {
        private static readonly Lazy<LobbyManager> lazy = new Lazy<LobbyManager>(() => new LobbyManager());

        public static LobbyManager Instance
        {
            get { return lazy.Value; }
        }

        private LobbyManager()
        {
        }
    }
}
