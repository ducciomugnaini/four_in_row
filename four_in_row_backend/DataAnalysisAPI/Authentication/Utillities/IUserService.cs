using FourInRow.Authentication.Entity;
using FourInRow.Authentication.Model;
using System.Collections.Generic;

namespace FourInRow.Authentication.Utillities
{
    public interface IUserService
    {
        AuthenticateResponse Authenticate(AuthenticateRequest model);
        IEnumerable<User> GetAll();
        User GetById(int id);
    }
}
