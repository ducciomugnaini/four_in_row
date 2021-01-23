using Microsoft.AspNetCore.Mvc;

namespace FourInRow.Controllers
{
    public class UnderAuthTestController : Controller
    {
        public IActionResult Index()
        {
            return View();
        }
    }
}
