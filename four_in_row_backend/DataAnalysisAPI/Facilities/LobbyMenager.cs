using FourInRow.Entities;
using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;

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
        private static readonly object _lobbiesLock = new object();        
        private static readonly Lazy<LobbyManager> _lobbyManager;
        private static readonly string _lobbyFilePath;

        public static LobbyManager Instance
        {
            get { return _lobbyManager.Value; }
        }

        static LobbyManager()
        {
            _lobbyManager = new Lazy<LobbyManager>(() => new LobbyManager());
            _lobbyFilePath = Path.GetFullPath(Path.Combine(AppContext.BaseDirectory, "..\\..\\..\\Lobbies\\lobbies.json"));
        }

        private LobbyManager() { }

        public Lobby SearchOrCreate(Player searchingPlayer)
        {
            lock (_lobbiesLock)
            {
                if (!File.Exists(_lobbyFilePath))
                {
                    File.Create(_lobbyFilePath);
                    TextWriter tw = new StreamWriter(_lobbyFilePath);
                    tw.WriteLine("[]");
                    tw.Close();
                }

                List<Lobby> _lobbies = ReadLobbies();

                var candidateLobby = _lobbies.FirstOrDefault(lb => lb.Players.Count == 1);
                if (candidateLobby.IsNotNull())
                {
                    candidateLobby.Players.Add(searchingPlayer);
                    
                    UpdateLobbies(_lobbies);
                    return candidateLobby;
                }                    

                var newLobby = new Lobby
                {
                    Name = "Lobby_" + Guid.NewGuid().ToString().Substring(0, 5),
                    CreatedOn = DateTime.Now,
                    Players = new List<Player>()
                };
                newLobby.Players.Add(searchingPlayer);

                _lobbies.Add(newLobby);
                UpdateLobbies(_lobbies);

                return newLobby;
            }
        }

        private List<Lobby> ReadLobbies()
        {
            // deserialize _lobbies.json -> load
            return JsonConvert.DeserializeObject<List<Lobby>>(File.ReadAllText($@"{_lobbyFilePath}"));
        }

        private void UpdateLobbies(List<Lobby> updatedLobbies)
        {
            // serialize _lobbies.json -> save
            File.WriteAllText(_lobbyFilePath, updatedLobbies.ToPrettyJson());
        }
    }
}
