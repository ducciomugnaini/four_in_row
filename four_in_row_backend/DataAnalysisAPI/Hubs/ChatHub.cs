using Microsoft.AspNetCore.SignalR;

namespace FourInRow.Hubs
{
    public class ChatHub : Hub
    {
        public void Send(string name, string message)
        {
            // Call the broadcastMessage method to update clients.
            Clients.All.SendAsync("broadcastMessage", name, message);

            Clients.All.SendAsync("Send", message);
        }
    }
}
