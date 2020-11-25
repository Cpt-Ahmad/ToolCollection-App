package de.cptahmad.toolcollection.utils;

public class Counter
{
    private int count;

    public void reset()
    {
        count = 0;
    }

    public void count()
    {
        count++;
    }

    public int getCounter()
    {
        return count;
    }
}
