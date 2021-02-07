using System;
using System.Collections.Generic;
using System.Text;

namespace FourInRowStructures.Models
{
    public static class ListExtension
    {
        public static bool IsindexOutOfRange<T>(this List<T> list, int index)
        {
            return 0 <= index && index < list.Count;
        }
    }
}
