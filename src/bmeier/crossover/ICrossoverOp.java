package bmeier.crossover;

import bmeier.Tour;
import bmeier.util.Pair;

public interface ICrossoverOp
{
    public Pair<Tour, Tour> recombine(Tour t1, Tour t2);
}
