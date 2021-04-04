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

[SignalR overview](https://docs.microsoft.com/en-us/aspnet/core/tutorials/signalr?view=aspnetcore-5.0&tabs=visual-studio)  
[SignalR samples](https://github.com/aspnet/SignalR-samples)  
[SignalR java client](https://github.com/aspnet/SignalR-samples)  
[SignalR group subscription](https://docs.microsoft.com/en-us/aspnet/core/signalr/groups?view=aspnetcore-5.0)  
[SignalR after hub start](https://forums.asp.net/t/2155319.aspx?SignalR+Java+client+How+to+invoke+a+method+immediately+after+hub+start+)  
[SignalR group auth JWT - net core - Built-in JWT authentication](https://docs.microsoft.com/en-us/aspnet/core/signalr/authn-and-authz?view=aspnetcore-3.1&source=docs)  
[SignalR group auth JWT - Android  - Configure bearer token authentication](https://docs.microsoft.com/en-us/aspnet/core/signalr/java-client?view=aspnetcore-3.1)  
Sottoscrizione alla lobby di gioco

[Singleton](https://csharpindepth.com/articles/singleton)  
[Lazy< T >](https://docs.microsoft.com/en-us/dotnet/api/system.lazy-1?view=net-5.0#constructors)  
[Lazy< T >](https://www.dotnetperls.com/lazy)  
Constructors. In an overloaded constructor, you can specify thread safety, and even specify a Func type that serves as a factory design pattern method.

[Format json](https://weblog.west-wind.com/posts/2015/mar/31/prettifying-a-json-string-in-net)  
As is usually the case, JSON.NET makes JSON manipulations super easy – in fact it’s a single line of code.

# Utilities

[Code Maid](https://marketplace.visualstudio.com/items?itemName=SteveCadwallader.CodeMaid)

[Code Snippets VS](https://docs.microsoft.com/en-us/visualstudio/ide/walkthrough-creating-a-code-snippet?view=vs-2019)  
[Live Template Rider](https://www.jetbrains.com/help/rider/Templates__Applying_Templates__Creating_Source_Code_Using_Live_Templates.html#selecting)  
[Live Template Rider List](https://www.jetbrains.com/help/rider/Reference__Templates_Explorer__Live_Templates_CSHARP.html)

[Vertical Rules](https://nickjanetakis.com/blog/80-characters-per-line-is-a-standard-worth-sticking-to-even-today)  
[Vertical Rules VS](https://marketplace.visualstudio.com/items?itemName=PaulHarrington.EditorGuidelines)
[Vertical Rules VS - .editorconfig](https://docs.microsoft.com/en-us/visualstudio/ide/create-portable-custom-editor-options?view=vs-2019)  
[Disabling highlight current row VS](https://stackoverflow.com/questions/7883966/disabling-highlighting-of-current-line-in-the-visual-studio-editor)  
[Vertical Rules Intellij](https://stackoverflow.com/questions/7883966/disabling-highlighting-of-current-line-in-the-visual-studio-editor)

# Hints

Al momento sembra che signalR sia funzionante con project mentre con iss behind sorgono problemi di connessione da parte dell app android. 
Problema da idagare prima di andare in produzione.  
[Debug settings #iis #project](https://stackoverflow.com/questions/51801184/asp-net-core-launch-settings-iis-express-iis-project-executable)

Settaggio delle cartelle della web app  
[app.UseStaticFiles() vs app.UseFileServer()](https://docs.microsoft.com/en-us/aspnet/core/fundamentals/static-files?view=aspnetcore-5.0)