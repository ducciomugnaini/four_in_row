using FourInRow.Authentication.Utility;
using FourInRow.Entities;
using Microsoft.AspNetCore.Mvc;

namespace FourInRow.Controllers
{
    [Authorize]
    [ApiController]
    [Route("api/[controller]")]
    public class LobbyController : ControllerBase
    {
        [HttpGet]
        public Lobby GetLobby()
        {
            return new Lobby
            {
                Name = "LOBBY_DEMO"
            };
        }
    }
}
