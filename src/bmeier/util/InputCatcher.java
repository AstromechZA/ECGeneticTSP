package bmeier.util;

public class InputCatcher
{
    public static int toInt(String value, int def)
    {
        try
        {
            return Integer.parseInt(value);
        }
        catch (NumberFormatException e)
        {
            return def;
        }
    }
}
