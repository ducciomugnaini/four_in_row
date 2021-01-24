# Four in row
# C# Side

[Net Core 3](https://docs.microsoft.com/it-it/dotnet/core/whats-new/dotnet-core-3-0)  
[Net Core 3 StringClass realtime compiler](https://laurentkempe.com/2019/02/18/dynamically-compile-and-run-code-using-dotNET-Core-3.0/)

[Scriban](https://github.com/lunet-io/scriban)  
[Scriban render json](https://stackoverflow.com/questions/60389949/render-scriban-template-from-json-data)

[NLog](https://nlog-project.org/)  
[NLog Setup](https://github.com/NLog/NLog/wiki/Getting-started-with-ASP.NET-Core-3)  
[NLog Config](https://nlog-project.org/config/)
[NLog with class library](https://stackoverflow.com/questions/52953158/how-to-log-nlog-calls-from-a-class-library-in-my-asp-net-core-mvc-app)

In un futuro lontano magari per fare i fighi metteremo sentry ad ora non è banale  
[NLog with Sentry](https://github.com/getsentry/sentry-dotnet)  
[NLog with Sentry](https://github.com/getsentry/sentry-dotnet/tree/main/samples/Sentry.Samples.NLog)  
[NLog with Sentry](https://docs.sentry.io/platforms/dotnet/guides/aspnetcore/)

[DI NetCore](https://www.tutorialsteacher.com/core/dependency-injection-in-aspnet-core)  
[DI Built-in container](https://www.tutorialsteacher.com/core/internals-of-builtin-ioc-container-in-aspnet-core)  
Il container di netCore fa cagare ma dobbiamo tenerlo finchè non si capisce come sostituirlo con AutoFAC

[DI NetCore for logging in external library (console)](https://xfischer.github.io/logging-dotnet-core/)  
[DI NetCore for logging in external library (console)](https://github.com/xfischer/CoreLoggingTests)  
[DI NetCore for logging in external library (ASP)](https://www.iaspnetcore.com/blog/5a5f97570b5daa1f58fa62bc/how-to-logging-in-class-libraries-with-aspnet-core)  
L'ultimo link è quello fondamentale per impostare il log per le dll esterne.  
Baraccone tirato su per non passare esplicitamente il logger alla libreria esterna ma attraverso la DI.

[JWT Auth](https://jasonwatmore.com/post/2019/10/11/aspnet-core-3-jwt-authentication-tutorial-with-example-api#app-settings-cs)

[SingnalR overview](https://docs.microsoft.com/en-us/aspnet/core/tutorials/signalr?view=aspnetcore-5.0&tabs=visual-studio)
[SingnalR samples](https://github.com/aspnet/SignalR-samples)
[SingnalR java client](https://github.com/aspnet/SignalR-samples)

# Utilities

[Code Maid](https://marketplace.visualstudio.com/items?itemName=SteveCadwallader.CodeMaid)

# Hints

Al momento sembra che signalR sia funzionante con project mentre con iss behind sorgono problemi di connessione da parte dell app android. 
Problema da idagare prima di andare in produzione.
[Debug settings #iis #project](https://stackoverflow.com/questions/51801184/asp-net-core-launch-settings-iis-express-iis-project-executable)