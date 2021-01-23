using FourInRow.Authentication.Entity;
using FourInRow.Authentication.Model;
using Microsoft.Extensions.Options;
using Microsoft.IdentityModel.Tokens;
using System;
using System.Collections.Generic;
using System.IdentityModel.Tokens.Jwt;
using System.Linq;
using System.Security.Claims;
using System.Text;

namespace FourInRow.Authentication.Utillities
{
    public class UserService : IUserService
    {
        private readonly AppSettings _appSettings;
                
        private List<User> _users = new List<User>
        {
            // todo to replace
            // users hardcoded for simplicity, store in a db with hashed passwords in production applications            
            new User { Id = 1, FirstName = "Test", LastName = "User", Username = "test", Password = "test" }
        };        

        public UserService(IOptions<AppSettings> appSettings)
        {
            _appSettings = appSettings.Value;
        }

        public IEnumerable<User> GetAll()
        {
            return _users;
        }

        public User GetById(int id)
        {
            return _users.FirstOrDefault(x => x.Id == id);
        }

        public AuthenticateResponse Authenticate(AuthenticateRequest model)
        {
            var user = _users.SingleOrDefault(x => x.Username == model.Username && x.Password == model.Password);

            // return null if user not found
            if (user == null) 
                return null;

            // authentication successful so generate jwt token
            var token = GenerateJwtToken(user);

            return new AuthenticateResponse(user, token);
        }        

        private string GenerateJwtToken(User user)
        {
            var key = Encoding.ASCII.GetBytes(_appSettings.Secret); // da configurazione

            // ---------------------------------------------- JWT Generation

            // generate token that is valid for 7 days
            var tokenHandler = new JwtSecurityTokenHandler(); 
            var token = tokenHandler.CreateToken(new SecurityTokenDescriptor
            {
                Subject = new ClaimsIdentity(new[] { new Claim("id", user.Id.ToString()) }),
                Expires = DateTime.UtcNow.AddDays(7),
                SigningCredentials = new SigningCredentials(new SymmetricSecurityKey(key), SecurityAlgorithms.HmacSha256Signature)
            });

            // ----------------------------------------------

            return tokenHandler.WriteToken(token);
        }
    }
}
