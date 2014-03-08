package bmeier.mutator;

import bmeier.Tour;
import bmeier.World;

public class ThreeOptMutator implements IMutatorOp
{
    @Override
    public Tour mutate(Tour t)
    {
        World w = t.getWorld();
        int len = t.order.length;  
        int[] order = t.order;
       
        float initialcost = t.getCost();
        
        float minnewcost = Float.MAX_VALUE;
        int cuti = -1;
        int cutj = -1;
        int cutk = -1;
        int ma = -1;
        int mb = -1;
        int mc = -1;
        int md = -1;
        
        // first cut position
        for(int i=0;i<len;i++)
        {            
            // second cut position
            for(int j=i+2;j<len;j++)
            {
                // third cut position
                for(int k=j+2;k<len;k++)
                {
                    boolean notoverend = k<(len-1);
                    // reduce cost
                    float subcost = initialcost - w.getCost(order[i], order[i+1]) - w.getCost(order[j], order[j+1]);
                    if(notoverend) subcost -= w.getCost(order[k], order[(k+1) % len]);
                    
                    {
                        int a = i+1, b = j, c = j+1, d = k;
                        float newcost = subcost + w.getCost(order[i], order[a]) + w.getCost(order[b], order[c]);
                        if(notoverend) newcost += w.getCost(order[d], order[(k+1) % len]);
                                        
                        if(newcost < initialcost && newcost < minnewcost)
                        {
                            minnewcost = newcost;
                            cuti = i; cutj = j; cutk = k;
                            ma = a; mb = b; mc = c; md = d;
                        }   
                    }
                    {
                        int a = i+1, b = j, c = k, d = j+1;
                        float newcost = subcost + w.getCost(order[i], order[a]) + w.getCost(order[b], order[c]);
                        if(notoverend) newcost += w.getCost(order[d], order[(k+1) % len]);
                                        
                        if(newcost < initialcost && newcost < minnewcost)
                        {
                            minnewcost = newcost;
                            cuti = i; cutj = j; cutk = k;
                            ma = a; mb = b; mc = c; md = d;
                        }   
                    }
                    {
                        int a = j, b = i+1, c = j+1, d = k;
                        float newcost = subcost + w.getCost(order[i], order[a]) + w.getCost(order[b], order[c]);
                        if(notoverend) newcost += w.getCost(order[d], order[(k+1) % len]);
                                        
                        if(newcost < initialcost && newcost < minnewcost)
                        {
                            minnewcost = newcost;
                            cuti = i; cutj = j; cutk = k;
                            ma = a; mb = b; mc = c; md = d;
                        }   
                    }
                    {
                        int a = j, b = i+1, c = k, d = j+1;
                        float newcost = subcost + w.getCost(order[i], order[a]) + w.getCost(order[b], order[c]);
                        if(notoverend) newcost += w.getCost(order[d], order[(k+1) % len]);
                                        
                        if(newcost < initialcost && newcost < minnewcost)
                        {
                            minnewcost = newcost;
                            cuti = i; cutj = j; cutk = k;
                            ma = a; mb = b; mc = c; md = d;
                        }   
                    }
                    
                    {
                        int a = j+1, b = k, c = i+1, d = j;
                        float newcost = subcost + w.getCost(order[i], order[a]) + w.getCost(order[b], order[c]);
                        if(notoverend) newcost += w.getCost(order[d], order[(k+1) % len]);
                                        
                        if(newcost < initialcost && newcost < minnewcost)
                        {
                            minnewcost = newcost;
                            cuti = i; cutj = j; cutk = k;
                            ma = a; mb = b; mc = c; md = d;
                        }   
                    }
                    {
                        int a = j+1, b = k, c = j, d = i+1;
                        float newcost = subcost + w.getCost(order[i], order[a]) + w.getCost(order[b], order[c]);
                        if(notoverend) newcost += w.getCost(order[d], order[(k+1) % len]);
                                        
                        if(newcost < initialcost && newcost < minnewcost)
                        {
                            minnewcost = newcost;
                            cuti = i; cutj = j; cutk = k;
                            ma = a; mb = b; mc = c; md = d;
                        }   
                    }
                    {
                        int a = k, b = j+1, c = i+1, d = j;
                        float newcost = subcost + w.getCost(order[i], order[a]) + w.getCost(order[b], order[c]);
                        if(notoverend) newcost += w.getCost(order[d], order[(k+1) % len]);
                                        
                        if(newcost < initialcost && newcost < minnewcost)
                        {
                            minnewcost = newcost;
                            cuti = i; cutj = j; cutk = k;
                            ma = a; mb = b; mc = c; md = d;
                        }   
                    }
                    {
                        int a = k, b = j+1, c = j, d = i+1;
                        float newcost = subcost + w.getCost(order[i], order[a]) + w.getCost(order[b], order[c]);
                        if(notoverend) newcost += w.getCost(order[d], order[(k+1) % len]);
                                        
                        if(newcost < initialcost && newcost < minnewcost)
                        {
                            minnewcost = newcost;
                            cuti = i; cutj = j; cutk = k;
                            ma = a; mb = b; mc = c; md = d;
                        }   
                    }         
                }
            }
        }
        
        // System.out.printf("[-%d][%d-%d][%d-%d][%d-]\n", cuti, ma, mb, mc, md, cutk+1);
        
        int[] after = t.order.clone();
        if(cuti != -1)
        {
            int cursor = cuti+1;
            
            // a - b
            if(ma < mb)
            {
                for(int i=ma;i<=mb;i++)
                {
                    after[cursor] = order[i];
                    cursor++;
                }
            }
            // b - a
            else
            {
                for(int i=ma;i>=mb;i--)
                {
                    after[cursor] = order[i];
                    cursor++;
                }
            }
            
            // c - d
            if(mc < md)
            {
                for(int i=mc;i<=md;i++)
                {
                    after[cursor] = order[i];
                    cursor++;
                }
            }
            // d - c
            else
            {
                for(int i=mc;i>=md;i--)
                {
                    after[cursor] = order[i];
                    cursor++;
                }
            }
            
        }

        return new Tour(t.getWorld(), after);
    }
}
