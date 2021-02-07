namespace FourInRowStructures.Models
{
    public class Player
    {
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