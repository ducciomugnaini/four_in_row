using FourInRowStructures.Models;
using System;
using System.Collections.Generic;
using System.Text;

namespace FourInRowAI.AI
{
    interface IArtificialIntelligence
    {
        Move GetNextMove(Grid grid);
    }
}
