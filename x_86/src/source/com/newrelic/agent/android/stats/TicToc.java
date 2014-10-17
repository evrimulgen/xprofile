// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.agent.android.stats;


public class TicToc
{
    private static final class State extends Enum
    {

        public static State valueOf(String s)
        {
            return (State)Enum.valueOf(com/newrelic/agent/android/stats/TicToc$State, s);
        }

        public static State[] values()
        {
            return (State[])$VALUES.clone();
        }

        private static final State $VALUES[];
        public static final State STARTED;
        public static final State STOPPED;

        static 
        {
            STOPPED = new State("STOPPED", 0);
            STARTED = new State("STARTED", 1);
            State astate[] = new State[2];
            astate[0] = STOPPED;
            astate[1] = STARTED;
            $VALUES = astate;
        }

        private State(String s, int i)
        {
            super(s, i);
        }
    }


    public TicToc()
    {
    }

    public void tic()
    {
        state = State.STARTED;
        startTime = System.currentTimeMillis();
    }

    public long toc()
    {
        endTime = System.currentTimeMillis();
        if(state == State.STARTED)
        {
            state = State.STOPPED;
            return endTime - startTime;
        } else
        {
            return -1L;
        }
    }

    private long endTime;
    private long startTime;
    private State state;
}
