using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace FourInRow.Facilities
{
    /*
    https://www.dotnetperls.com/lazy
    https://docs.microsoft.com/en-us/dotnet/api/system.lazy-1?view=net-5.0#constructors
    Constructors. In an overloaded constructor, you can specify thread safety, 
    and even specify a Func type that serves as a factory design pattern method.
    */

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
