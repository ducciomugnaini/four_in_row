using Microsoft.AspNetCore.SignalR;
using Microsoft.Extensions.Logging;
using System;
using System.Threading.Tasks;

namespace FourInRow.Hubs
{
    public class ChatHub : Hub
    {
        private readonly ILogger<ChatHub> _logger;

        public ChatHub(ILogger<ChatHub> logger)
        {
            _logger = logger;
            _logger.LogInformation(1, "NLog injected into ChatHub");            
        }

        // ----------------------------------------------------------------------------------------------------------------------------------------------

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

        public async Task SendSubscriptionToGroup(string clientName, string lobbyName)
        {
            try
            {
                _logger.LogInformation($"SendSubscriptionToGroup received from {clientName} => {lobbyName}");
                await Groups.AddToGroupAsync(Context.ConnectionId, lobbyName);

                var subscriptionConfirmMsg = $"{Context.ConnectionId} has joined the group {lobbyName}.";
                _logger.LogInformation($"ReceiveSubscriptionConfirm sended from {clientName} => {subscriptionConfirmMsg}");
                await Clients.Group(lobbyName).SendAsync("ReceiveSubscriptionConfirm", clientName, subscriptionConfirmMsg);

                // todo search in queue a potential gamer
                // se lo trovo inizio la partita
                // altrimenti creo una nuova lobby

                var canGameStart = true;
                if (canGameStart)
                {
                    await Clients.Group(lobbyName).SendAsync("ReceiveStartGame", clientName, $"{Context.ConnectionId} belongs {lobbyName} can start the game.");
                }                
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex);
            }
        }

        public async Task SendRemoveFromLobby(string clientName, string lobbyName)
        {
            _logger.LogInformation($"SendRemoveFromLobby received from {clientName} => {lobbyName}");
            await Groups.RemoveFromGroupAsync(Context.ConnectionId, lobbyName);

            _logger.LogInformation($"ReceiveLobbyAdandoned sended from {clientName} => {lobbyName}");
            await Clients.Group(lobbyName).SendAsync("ReceiveLobbyAdandoned", clientName, $"{Context.ConnectionId} has left the group {lobbyName}.");
        }

    }
}
