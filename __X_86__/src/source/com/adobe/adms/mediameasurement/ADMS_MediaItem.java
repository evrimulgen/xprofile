// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.adobe.adms.mediameasurement;

import android.util.Log;
import java.util.Hashtable;

// Referenced classes of package com.adobe.adms.mediameasurement:
//            ADMS_MediaMeasurement

public class ADMS_MediaItem
{
    private static class MonitorThread extends Thread
    {

        public void run()
        {
            try
            {
                while(!canceled) 
                {
                    if(monitorMediaItem.lastEventType == 1)
                        monitorMediaItem.m.playerEvent(monitorMediaItem.name, 3, -1D, 0, null, -1D, null);
                    Thread.sleep(delay);
                }
            }
            catch(InterruptedException interruptedexception)
            {
                Log.d("ADMS_MediaMeasurement", (new StringBuilder()).append("AppMeasurement Error : Background Thread Interrupted : ").append(interruptedexception.getMessage()).toString());
            }
        }

        public boolean canceled;
        long delay;
        public ADMS_MediaItem monitorMediaItem;

        private MonitorThread()
        {
            delay = 1000L;
            canceled = false;
        }

    }


    public ADMS_MediaItem()
    {
    }

    protected void startMonitor()
    {
        while(monitor != null && !monitor.canceled || monitor != null && !monitor.canceled) 
            return;
        stopMonitor();
        monitor = new MonitorThread();
        monitor.monitorMediaItem = this;
        monitor.start();
    }

    protected void stopMonitor()
    {
        if(monitor != null)
        {
            synchronized(monitor)
            {
                monitor.canceled = true;
                monitor = null;
            }
            return;
        } else
        {
            return;
        }
        exception;
        monitorthread;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public boolean complete;
    public boolean completeTracked;
    public Hashtable firstEventList;
    public double lastEventOffset;
    public double lastEventTimestamp;
    public int lastEventType;
    public int lastMilestone;
    public int lastOffsetMilestone;
    public double lastTrackOffset;
    public double length;
    protected ADMS_MediaMeasurement m;
    private MonitorThread monitor;
    public String name;
    public double offset;
    public double percent;
    public String playerID;
    public String playerName;
    public String segment;
    public boolean segmentChanged;
    public boolean segmentGenerated;
    double segmentLength;
    public int segmentNum;
    public String session;
    public double timePlayed;
    public double timePlayedSinceTrack;
    public double timestamp;
    public int trackCount;
    public boolean updateSegment;
    public boolean viewTracked;
}
