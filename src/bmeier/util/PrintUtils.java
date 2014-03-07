package bmeier.util;

public class PrintUtils
{
    public static String a_to_s(int[] a)
    {
        StringBuffer sb = new StringBuffer();
        sb.append('[');
        boolean first = true;
        for (int aa : a)
        {
        	if(!first) sb.append(", ");
        	first = false;
            sb.append(aa);
        }
        sb.append(']');
        return sb.toString();
    }
    
    public static String a_to_s(boolean[] a)
    {
        StringBuffer sb = new StringBuffer();
        sb.append('[');
        boolean first = true;
        for (boolean aa : a)
        {
        	if(!first) sb.append(", ");
        	first = false;
            sb.append(aa);
        }
        sb.append(']');
        return sb.toString();
    }
}
