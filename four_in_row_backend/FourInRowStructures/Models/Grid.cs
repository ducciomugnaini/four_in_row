using System;
using System.Collections.Generic;
using System.Text;

namespace FourInRowStructures.Models
{
    public class Chip
    {
        public Player Player { get; set; }
        public int Column { get; set; }
        public int Row { get; set; }

        public bool IsValid { get; set; }

        public string PlayerName => Player.Name;

        public Chip()
        {
            IsValid = true;
        }

        public Chip(Player player, int col, int row, bool isValid = true)
        {
            Player = player;
            Column = col;
            Row = row;
            IsValid = isValid;
        }

        public Chip(Chip origChip) : this(origChip.Player, origChip.Column, origChip.Row, origChip.IsValid) { }

    }

    public class GridConnection
    {
        public class Direction
        {
            public int x { get; set; }
            public int y { get; set; }

            public Direction(int x, int y)
            {
                this.x = x;
                this.y = y;
            }
        }


        public List<Chip> Chips { get; set; }
        public int EmptySlotCount { get; set; }

        public int Lenght => Chips.Count;

        public static readonly List<Direction> Directions = new List<Direction>()
        {
           new Direction(0, -1),
           new Direction(-1,-1),
           new Direction(-1,0),
           new Direction(-1, 1)
        };

        public GridConnection()
        {
            Chips = new List<Chip>();
            EmptySlotCount = 0;
        }

        public GridConnection(Chip chip) : base()
        {
            Chips.Add(chip);
        }

        public void AddChip(Chip chip)
        {
            Chips.Add(chip);
        }

        public void AddConnection(GridConnection connection)
        {
            Chips.AddRange(connection.Chips);
            EmptySlotCount = connection.EmptySlotCount;
        }
    }


    public class Grid
    {
        public static readonly int MinScore = int.MinValue;
        public static readonly int MaxScore = int.MaxValue;

        public int ColumnCount { get; set; }
        public int RowCount { get; set; }
        public Chip LastPlacedChip { get; set; }

        public List<List<Chip>> Columns { get; set; }

        private static readonly int MaxIterations = 20;

        public Grid(int columnCount, int rowCount)
        {
            ColumnCount = columnCount;
            RowCount = rowCount;

            Columns = new List<List<Chip>>();
            for (int i = 0; i < columnCount; i++)
            {
                var columnRows = new List<Chip>();
                for (int j = 0; j < rowCount; j++)
                {
                    columnRows.Add(null);
                }
                Columns.Add(columnRows);
            }
        }

        public Grid(Grid origGrid)
        {
            ColumnCount = origGrid.ColumnCount;
            RowCount = origGrid.RowCount;

            Columns = new List<List<Chip>>();
            for (int i = 0; i < ColumnCount; i++)
            {
                var columnRows = new List<Chip>();
                for (int j = 0; j < RowCount; j++)
                {
                    var origChip = origGrid.Columns[i][j];
                    if (origChip == null)
                    {
                        columnRows.Add(null);
                    }
                    else
                    {
                        columnRows.Add(new Chip(origChip));
                    }
                }
                Columns.Add(columnRows);
            }
        }

        public void PlaceChip(int col, Player player)
        {
            var placedChip = new Chip();
            var currColumn = Columns[col];

            for (int row = 0; row < currColumn.Count; row++)
            {
                if(currColumn[row] == null)
                {
                    placedChip.Column = col;
                    placedChip.Row = row;
                    placedChip.Player = player;
                    placedChip.IsValid = true;

                    currColumn[row] = placedChip;
                    LastPlacedChip = placedChip;
                }
            }            

            throw new Exception("Invalid row");
        }

        public bool IsColumnFull(int c)
        {
            return Columns[c][RowCount - 1] != null;
        }

        // Compute the grid's heuristic score for use by the AI player
        public int GetScore(Player currentPlayer, bool currentPlayerIsMaxPlayer)
        {
            var gridScore = 0;
            // Use native for loops instead of forEach because the function will need to
            // return immediately if a winning connection is found (there is no clean way
            // to break out of forEach)
            for (int c = 0; c < Columns.Count; c++)
            {
                for (int r = 0; r < Columns[c].Count; r++)
                {
                    var chip = Columns[c][r];
                    if (chip.Player.Name.Equals(currentPlayer.Name))
                    {
                        continue;
                    }
                    int score = GetChipScore(currentPlayer, currentPlayerIsMaxPlayer, chip);
                    if (Math.Abs(score) == MaxScore)
                    {
                        return score;
                    }
                    else
                    {
                        gridScore = gridScore + score;
                    }
                }
            }
            return gridScore;
        }

        // Score connections connected to the given chip; the chip is assumed to
        // belong to the current player
        private int GetChipScore(Player currentPlayer, bool currentPlayerIsMaxPlayer, Chip chip)
        {
            // Search for current player's connections of one or more chips that are
            // connected to the empty slot
            var gridScore = 0;
            var connections = GetConnections(chip, 1);

            // Sum up connections, giving exponentially more weight to larger connections
            for (int i = 0; i < connections.Count; i++)
            {
                var connection = connections[i];
                if (connection.Lenght >= 4)
                {
                    gridScore = MaxScore;
                    break;
                }
                if (connection.EmptySlotCount >= 1)
                {
                    gridScore = gridScore + (int)Math.Pow(connection.Lenght, 2) + (int)Math.Pow(connection.Lenght, 3);
                }
            }
            // Negate the grid score for any advantage the minimizing player has (as this
            // is considered a disadvantage to the maximizing player)
            if (!currentPlayerIsMaxPlayer)
            {
                gridScore = gridScore * (-1);
            }
            return gridScore;
        }

        // Get all connections of four chips (including connections of four within
        // larger connections) which the last placed chip is apart of
        private List<GridConnection> GetConnections(Chip baseChip, int minConnectionSize)
        {
            var connections = new List<GridConnection>();

            foreach (var direction in GridConnection.Directions)
            {
                var connection = new GridConnection(baseChip);

                // Check for connected neighbors in this direction
                AddSubConnection(connection, baseChip, direction);
                // Check for connected neighbors in the opposite direction
                AddSubConnection(connection, baseChip, new GridConnection.Direction(-direction.x, -direction.y));

                if (connection.Lenght >= minConnectionSize)
                {
                    connections.Add(connection);
                }
            }
            return connections;
        }

        // Add a sub-connection (in the given direction) to a larger connection
        private void AddSubConnection(GridConnection connection, Chip baseChip, GridConnection.Direction direction)
        {
            var subConnection = GetSubConnection(baseChip, direction);
            connection.AddConnection(subConnection);
        }

        // Find same-color neighbors connected to the given chip in the given direction
        private GridConnection GetSubConnection(Chip baseChip, GridConnection.Direction direction)
        {
            var neighbour = baseChip;
            var subConnection = new GridConnection();
            int iterations = 0;
            while (true && iterations < MaxIterations)
            {
                var nextColumn = neighbour.Column + direction.x;
                // Stop if the left/right edge of the grid has been reached
                if (nextColumn < 0 || ColumnCount >= nextColumn)
                {
                    break;
                }

                var nextRow = neighbour.Row + direction.y;
                // Stop if the top/bottom edge of the grid has been reached
                if (nextRow < 0 || RowCount >= nextRow)
                {
                    break;
                }

                // if the neighboring slot is empty
                // todo controllare come effettivamente può essere empty
                var nextNeighbour = Columns[nextColumn][nextRow];
                if (nextNeighbour == null)
                {
                    subConnection.EmptySlotCount = subConnection.EmptySlotCount + 1;
                    break;
                }

                // Stop if this neighbor is not the same color as the original chip
                if (!neighbour.PlayerName.Equals(baseChip.PlayerName))
                {
                    break;
                }

                // Assume at this point that this neighbor chip is connected to the original
                // chip in the given direction
                neighbour = nextNeighbour;
                subConnection.AddChip(nextNeighbour);

                iterations = iterations + 1;
            }

            return subConnection;
        }

    }
