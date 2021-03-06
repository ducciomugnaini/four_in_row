using FourInRow.Authentication.Utillities;
using FourInRow.Hubs;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;
using RealtimeCompiler.Interfaces;

namespace FourInRow
{
    public class Startup
    {
        public IConfiguration Configuration { get; }

        public Startup(IConfiguration configuration)
        {
            Configuration = configuration;
        }        

        // This method gets called by the runtime. Use this method to add services to the container.
        public void ConfigureServices(IServiceCollection services)
        {

            #region General config

            services.AddControllers();

            services.AddCors(c =>
            {
                c.AddPolicy("AllowOrigin", options => options.AllowAnyOrigin());
            });

            // JWT Auth - configure strongly typed settings object
            services.Configure<AppSettings>(Configuration.GetSection("AppSettings"));

            #endregion

            #region DI

            // Configurazione della DI per runtime compiler
            services.AddTransient<IRunnable, RealtimeCompiler.RealTimeCompilerDI>();

            // JWT Auth - configure DI for application services (lettura condigurazioni)
            services.AddScoped<IUserService, UserService>();

            #endregion

            #region SignaR

            services.AddSignalR();

            #endregion

        }

        // This method gets called by the runtime. Use this method to configure the HTTP request pipeline.
        public void Configure(IApplicationBuilder app, IWebHostEnvironment env)
        {
            if (env.IsDevelopment())
            {
                app.UseDeveloperExceptionPage();
            }

            #region Middlewares setup

            // custom jwt auth middleware
            app.UseMiddleware<JwtMiddleware>();

            #endregion

            app.UseHttpsRedirection();
            app.UseFileServer();
            app.UseRouting();
            app.UseAuthorization();
            app.UseCors(options => options.AllowAnyOrigin());

            app.UseEndpoints(endpoints =>
            {
                endpoints.MapControllers();

                endpoints.MapControllerRoute(
                    name: "default",
                    pattern: "{controller=Home}/{action=Index}/{id?}");

                endpoints.MapHub<ChatHub>("/chat");
            });
        }
    }
}
