package de.cptahmad.toolcollection.utils;

public class SimpleTimer
{
    private long before;

    public SimpleTimer(){}

    public void reset()
    {
        before = System.currentTimeMillis();
    }

    public long time()
    {
        return System.currentTimeMillis() - before;
    }
}
