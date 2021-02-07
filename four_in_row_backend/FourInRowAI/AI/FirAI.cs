using FourInRowStructures.Models;
using System;

namespace FourInRowAI.AI
{
    public class Move
    {
        public int? Column { get; set; }
        public int Score { get; set; }
    }

    public class FirAI : IArtificialIntelligence
    {
        public Player Human { get; set; } // => Minimize player
        public Player AI { get; set; } // => Maximaze player

        // The maximum number of grid moves to look ahead; for reasons unknown,
        // increasing this to a value greater than 3 will actually cripple the AI's
        // ability to handle connect-three trap scenarios
        public int MaxComputeDepth { get; set; }

        public FirAI(int maxComputeDepth = 3)
        {
            MaxComputeDepth = maxComputeDepth;
        }

        // Compute the column where the AI player should place its next chip
        public Move GetNextMove(Grid grid)
        {
            Human = new Player(Player.HumanPlayerCode, Player.HumanPlayerColor, 0);
            AI = new Player(Player.AIPlayerCode, Player.AIPlayerColor, 0);
            return MaximizeMove(grid, Human, 0, Grid.MinScore, Grid.MaxScore);
        }

        private Move MaximizeMove(Grid grid, Player minPlayer, int depth, int alpha, int beta)
        {
            var gridScore = grid.GetScore(AI, true);

            // If max search depth was reached or if winning grid was found
            if (depth == MaxComputeDepth || Math.Abs(gridScore) == Grid.MaxScore)
            {
                return new Move()
                {
                    Column = null,
                    Score = gridScore
                };
            }

            var maxMove = new Move()
            {
                Column = null,
                Score = gridScore
            };

            for (int c = 0; c < grid.ColumnCount; c++)
            {
                // Continue to next possible move if this column is full
                if (grid.IsColumnFull(c))
                {
                    continue;
                }

                // Clone the current grid and place a chip to generate a new permutation
                var nextGrid = new Grid(grid);
                nextGrid.PlaceChip(c, AI);

                // Minimize the opponent human player's chances of winning
                var minMove = MinimizeMove(nextGrid, minPlayer, depth + 1, alpha, beta);

                // If a move yields a lower opponent score, make it the tentative max move
                if (minMove.Score > maxMove.Score)
                {
                    maxMove.Column = c;
                    maxMove.Score = minMove.Score;
                    alpha = minMove.Score;
                }
                else if (maxMove.Score == Grid.MinScore)
                {
                    // Ensure that the AI always blocks an opponent win even if the opponent
                    // is guaranteed to win on its next turn
                    maxMove.Column = minMove.Column;
                    maxMove.Score = minMove.Score;
                    alpha = minMove.Score;
                }

                // Stop if there are no moves better than the current max move
                if (alpha >= beta)
                {
                    break;
                }
            }

            return maxMove;
        }

        // Choose a column that will minimize the human player's chances of winning
        private Move MinimizeMove(Grid grid, Player minPlayer, int depth, int alpha, int beta)
        {
            var gridScore = grid.GetScore(minPlayer, false);

            // If max search depth was reached or if winning grid was found
            if (depth == MaxComputeDepth || Math.Abs(gridScore) == Grid.MaxScore)
            {
                return new Move()
                {
                    Column = null,
                    Score = gridScore
                };
            }

            var minMove = new Move()
            {
                Column = null,
                Score = Grid.MaxScore
            };

            for (int c = 0; c < grid.ColumnCount; c++)
            {
                // Continue to next possible move if this column is full
                if (grid.IsColumnFull(c))
                {
                    continue;
                }

                var nextGrid = new Grid(grid);
                // The human playing against the AI is always the first player
                nextGrid.PlaceChip(c, minPlayer);

                // Maximize the AI player's chances of winning
                var maxMove = MaximizeMove(nextGrid, minPlayer, depth + 1, alpha, beta);

                // If a move yields a higher AI score, make it the tentative max move
                if(maxMove.Score < minMove.Score)
                {
                    minMove.Column = c;
                    minMove.Score = maxMove.Score;
                    beta = maxMove.Score;
                }

                // Stop if there are no moves better than the current min move
                if (alpha >= beta)
                {
                    break;
                }
            }

            return minMove;
        }
    }
}
