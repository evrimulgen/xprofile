// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ly.count.android.api;

import android.content.Context;
import java.util.*;
import org.OpenUDID.OpenUDID_manager;

// Referenced classes of package ly.count.android.api:
//            ConnectionQueue, EventQueue

public class Countly
{

    private Countly()
    {
        queue_ = new ConnectionQueue();
        eventQueue_ = new EventQueue();
        timer_ = new Timer();
        timer_.schedule(new TimerTask() {

            public void run()
            {
                onTimer();
            }

            final Countly this$0;

            
            {
                this$0 = Countly.this;
                super();
            }
        }
, 60000L, 60000L);
        isVisible_ = false;
        unsentSessionLength_ = 0.0D;
        activityCount_ = 0;
    }

    private void onTimer()
    {
        if(isVisible_)
        {
            double d = (double)System.currentTimeMillis() / 1000D;
            unsentSessionLength_ = unsentSessionLength_ + (d - lastTime_);
            lastTime_ = d;
            int i = (int)unsentSessionLength_;
            queue_.updateSession(i);
            unsentSessionLength_ = unsentSessionLength_ - (double)i;
            if(eventQueue_.size() > 0)
            {
                queue_.recordEvents(eventQueue_.events());
                return;
            }
        }
    }

    public static Countly sharedInstance()
    {
        if(sharedInstance_ == null)
            sharedInstance_ = new Countly();
        return sharedInstance_;
    }

    public void init(Context context, String s, String s1)
    {
        OpenUDID_manager.sync(context);
        queue_.setContext(context);
        queue_.setServerURL(s);
        queue_.setAppKey(s1);
    }

    public void onStart()
    {
        activityCount_ = 1 + activityCount_;
        if(activityCount_ == 1)
            onStartHelper();
    }

    public void onStartHelper()
    {
        lastTime_ = (double)System.currentTimeMillis() / 1000D;
        queue_.beginSession();
        isVisible_ = true;
    }

    public void onStop()
    {
        activityCount_ = -1 + activityCount_;
        if(activityCount_ == 0)
            onStopHelper();
    }

    public void onStopHelper()
    {
        if(eventQueue_.size() > 0)
            queue_.recordEvents(eventQueue_.events());
        double d = (double)System.currentTimeMillis() / 1000D;
        unsentSessionLength_ = unsentSessionLength_ + (d - lastTime_);
        int i = (int)unsentSessionLength_;
        queue_.endSession(i);
        unsentSessionLength_ = unsentSessionLength_ - (double)i;
        isVisible_ = false;
    }

    public void recordEvent(String s, int i)
    {
        eventQueue_.recordEvent(s, i);
        if(eventQueue_.size() >= 10)
            queue_.recordEvents(eventQueue_.events());
    }

    public void recordEvent(String s, int i, double d)
    {
        eventQueue_.recordEvent(s, i, d);
        if(eventQueue_.size() >= 10)
            queue_.recordEvents(eventQueue_.events());
    }

    public void recordEvent(String s, Map map, int i)
    {
        eventQueue_.recordEvent(s, map, i);
        if(eventQueue_.size() >= 10)
            queue_.recordEvents(eventQueue_.events());
    }

    public void recordEvent(String s, Map map, int i, double d)
    {
        eventQueue_.recordEvent(s, map, i, d);
        if(eventQueue_.size() >= 10)
            queue_.recordEvents(eventQueue_.events());
    }

    private static Countly sharedInstance_;
    private int activityCount_;
    private EventQueue eventQueue_;
    private boolean isVisible_;
    private double lastTime_;
    private ConnectionQueue queue_;
    private Timer timer_;
    private double unsentSessionLength_;

}
