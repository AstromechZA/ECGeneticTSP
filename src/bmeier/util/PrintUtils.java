package bmeier.util;

public class PrintUtils
{
    public static String a_to_s(int[] a)
    {
        StringBuffer sb = new StringBuffer();
        sb.append('[');
        for (int aa : a)
        {
            sb.append(aa);
            sb.append(", ");
        }
        sb.append(']');
        return sb.toString();
    }
    
    public static String a_to_s(boolean[] a)
    {
        StringBuffer sb = new StringBuffer();
        sb.append('[');
        for (boolean aa : a)
        {
            sb.append(aa);
            sb.append(", ");
        }
        sb.append(']');
        return sb.toString();
    }
}
