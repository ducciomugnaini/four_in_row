namespace FourInRowStructures.Models
{
    public class Player
    {
        public static readonly string HumanPlayerCode = "Human Player";
        public static readonly string AIPlayerCode = "AI Player";

        public static readonly string HumanPlayerColor = "Red";
        public static readonly string AIPlayerColor = "Blue";

        public string Name { get; set; }
        public string Color { get; set; }
        public int Score { get; set; }

        public Player(string name, string color, int score)
        {
            Name = name;
            Color = color;
            Score = score;
        }
    }
}