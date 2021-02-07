using FourInRowAI.AI;
using FourInRowStructures.Models;
using NUnit.Framework;

namespace FourInRowAINUTest
{
    public class Tests
    {
        public static Grid MainGrid;
        public static Player Human;
        public static Player AI;


        [SetUp]
        public void Setup()
        {
            MainGrid = new Grid(7, 6);
            Human = new Player(Player.HumanPlayerCode, Player.HumanPlayerColor, 0);
            AI = new Player(Player.AIPlayerCode, Player.AIPlayerColor, 0);
        }

        [Test]
        public void FirstMove()
        {
            // player move
            MainGrid.PlaceChip(3, Human);

            int MaxComputeDepth = 3;
            FirAI ai = new FirAI(MaxComputeDepth);

            var nextMove = ai.GetNextMove(MainGrid);

            var expectedMove = new Move
            {
                Column = 4,
                Score = 0
            };

            Assert.AreEqual(nextMove, expectedMove);

            Assert.Pass();
        }
    }
}