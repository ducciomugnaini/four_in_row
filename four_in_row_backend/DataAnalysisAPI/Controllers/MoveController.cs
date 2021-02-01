using FourInRow.Authentication.Utility;
using FourInRow.Entities;
using Microsoft.AspNetCore.Mvc;

namespace FourInRow.Controllers
{
    [Authorize]
    [ApiController]
    [Route("api/[controller]")]
    public class MoveController : ControllerBase
    {
        [HttpGet]
        public Move GetMove()
        {
            return new Move
            {
                Column = 3
            };
        }
    }
}
