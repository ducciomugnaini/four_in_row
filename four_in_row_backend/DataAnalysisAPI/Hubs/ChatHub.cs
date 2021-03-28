using Microsoft.AspNetCore.SignalR;
using System;
using System.Threading.Tasks;

namespace FourInRow.Hubs
{
    public class ChatHub : Hub
    {
        public void Send(string name, string message)
        {
            // Call the broadcastMessage method to update clients.
            Clients.All.SendAsync("broadcastMessage", name, message);   // => to index.html
            Clients.All.SendAsync("Send", message);                     // => to Android Client
        }

        public async Task SendToGroup(string name, string message, string groupName)
        {
            try
            {
                await Clients.Group(groupName).SendAsync("SendToGroup", name, message, groupName);
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex);
            }
        }

        public async Task AddToGroup(string name, string groupName)
        {
            try
            {
                await Groups.AddToGroupAsync(Context.ConnectionId, groupName);
                await Clients.Group(groupName).SendAsync("SendToGroup", name, $"{Context.ConnectionId} has joined the group {groupName}.", groupName);
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex);
            }
        }

        public async Task RemoveFromGroup(string name, string groupName)
        {
            await Groups.RemoveFromGroupAsync(Context.ConnectionId, groupName);
            await Clients.Group(groupName).SendAsync("SendToGroup", name, $"{Context.ConnectionId} has left the group {groupName}.");
        }

        // ----------------------------------------------------------------------------------------------------------------------------------------------

        public async Task SendSubscriptionToGroup(string clientName, string lobbyname)
        {
            try
            {
                await Groups.AddToGroupAsync(Context.ConnectionId, lobbyname);
                await Clients.Group(lobbyname).SendAsync("ReceiveSubscriptionConfirm", clientName, $"{Context.ConnectionId} has joined the group {lobbyname}.");

                var canGameStart = true;
                if (canGameStart)
                {
                    await Clients.Group(lobbyname).SendAsync("ReceiveStartGame", clientName, $"{Context.ConnectionId} belongs {lobbyname} can start the game.");
                }                
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex);
            }
        }

    }
}
