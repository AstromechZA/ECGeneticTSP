package bmeier;

public class TestRoulette
{

    public static void main(String[] args)
    {
        // TODO Auto-generated method stub
        
        RouletteWheelSelector<String> r = new RouletteWheelSelector<String>();

        r.add("common", 75);
        r.add("less", 25);
        
        for(int i=0;i<100;i++)
        {
            System.out.println(r.spin());
        }
        
    }

}
