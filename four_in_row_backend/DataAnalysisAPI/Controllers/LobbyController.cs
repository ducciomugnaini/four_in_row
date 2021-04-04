using FourInRow.Authentication.Utility;
using FourInRow.Entities;
using FourInRow.Facilities;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;

namespace FourInRow.Controllers
{
    public class ClientLobby
    {
        public string Name { get; set; }

        public ClientLobby(Lobby masterLobby)
        {
            Name = masterLobby.Name;
        }
    }


    [Authorize]
    [ApiController]
    [Route("api/[controller]")]
    public class LobbyController : ControllerBase
    {
        private readonly ILogger<LobbyController> _logger;

        public LobbyController(ILogger<LobbyController> logger)
        {
            _logger = logger;
            _logger.LogDebug(1, "NLog injected into LobbyController");
        }

        [HttpPost]
        [Route("SearchLobby")]
        public ClientLobby SearchLobby([FromBody]ClientPlayer clientPlayer)
        {
            _logger.LogInformation($"Player search a lobby: {clientPlayer}");

            var masterLobby = LobbyManager.Instance.SearchOrCreate(new Player(clientPlayer));

            _logger.LogInformation($"Found lobby: \n{masterLobby.ToPrettyJson()}");

            return new ClientLobby(masterLobby);
        }
    }
}
