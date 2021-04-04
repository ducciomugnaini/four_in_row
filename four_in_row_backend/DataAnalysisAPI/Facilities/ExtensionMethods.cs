using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace FourInRow.Facilities
{
    public static class ExtensionMethods
    {
        public static bool IsNull(this object value)
        {
            return value == null;
        }

        public static bool IsNotNull(this object value)
        {
            return value != null;
        }

        public static string ToPrettyJson(this object value)
        {
            return JToken.Parse(JsonConvert.SerializeObject(value)).ToString(Formatting.Indented);
        }
    }
}
