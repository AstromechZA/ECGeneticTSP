package bmeier.crossover;

import bmeier.Pair;
import bmeier.Tour;

public interface ICrossoverOp
{
    public Pair<Tour, Tour> recombine(Tour t1, Tour t2);
}
