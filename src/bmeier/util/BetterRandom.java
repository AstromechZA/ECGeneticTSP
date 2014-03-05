package bmeier.util;

import java.util.Collection;
import java.util.Random;

public class BetterRandom extends Random
{

    private static final long serialVersionUID = 7276343025118653062L;

    public <T> T choose(final Collection<T> collection)
    {
        if(collection.size() <= 0) return null;
        int ri = this.nextInt(collection.size());
        int c = 0;
        for (T t : collection)
        {
            if (c==ri) return t;
            c+=1;
        }
        return null;
    }
    
}
