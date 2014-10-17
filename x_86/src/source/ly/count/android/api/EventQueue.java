// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ly.count.android.api;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

// Referenced classes of package ly.count.android.api:
//            Event

class EventQueue
{

    public EventQueue()
    {
        events_ = new ArrayList();
    }

    public String events()
    {
        String s = "[";
        this;
        JVM INSTR monitorenter ;
        int i = 0;
_L6:
        if(i < events_.size())
            break MISSING_BLOCK_LABEL_60;
        events_.clear();
        this;
        JVM INSTR monitorexit ;
        int j;
        String s7 = (new StringBuilder(String.valueOf(s))).append("]").toString();
        Exception exception;
        Event event;
        String s1;
        String s2;
        String as[];
        String s3;
        String s4;
        String s5;
        String s6;
        String s8;
        try
        {
            s8 = URLEncoder.encode(s7, "UTF-8");
        }
        catch(UnsupportedEncodingException unsupportedencodingexception)
        {
            return s7;
        }
        return s8;
        event = (Event)events_.get(i);
        s1 = (new StringBuilder(String.valueOf((new StringBuilder(String.valueOf(s))).append("{").toString()))).append("\"key\":\"").append(event.key).append("\"").toString();
        if(event.segmentation == null) goto _L2; else goto _L1
_L1:
        s2 = "{";
        as = (String[])event.segmentation.keySet().toArray(new String[0]);
        j = 0;
_L7:
        if(j < as.length) goto _L4; else goto _L3
_L3:
        s5 = (new StringBuilder(String.valueOf(s2))).append("}").toString();
        s1 = (new StringBuilder(String.valueOf(s1))).append(",\"segmentation\":").append(s5).toString();
_L2:
        s6 = (new StringBuilder(String.valueOf(s1))).append(",\"count\":").append(event.count).toString();
        if(event.sum > 0.0D)
            s6 = (new StringBuilder(String.valueOf(s6))).append(",\"sum\":").append(event.sum).toString();
        s = (new StringBuilder(String.valueOf((new StringBuilder(String.valueOf(s6))).append(",\"timestamp\":").append((long)event.timestamp).toString()))).append("}").toString();
        if(i + 1 < events_.size())
            s = (new StringBuilder(String.valueOf(s))).append(",").toString();
          goto _L5
_L4:
        s3 = as[j];
        s4 = (String)event.segmentation.get(s3);
        s2 = (new StringBuilder(String.valueOf(s2))).append("\"").append(s3).append("\"").append(":").append("\"").append(s4).append("\"").toString();
        if(j + 1 < as.length)
            s2 = (new StringBuilder(String.valueOf(s2))).append(",").toString();
        break MISSING_BLOCK_LABEL_502;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
_L5:
        i++;
          goto _L6
        j++;
          goto _L7
    }

    public void recordEvent(String s, int i)
    {
        this;
        JVM INSTR monitorenter ;
        int j = 0;
_L2:
        if(j < events_.size())
            break MISSING_BLOCK_LABEL_62;
        Event event = new Event();
        event.key = s;
        event.count = i;
        event.timestamp = (double)System.currentTimeMillis() / 1000D;
        events_.add(event);
        this;
        JVM INSTR monitorexit ;
        return;
        Event event1 = (Event)events_.get(j);
        if(!event1.key.equals(s))
            break MISSING_BLOCK_LABEL_132;
        event1.count = i + event1.count;
        event1.timestamp = (event1.timestamp + (double)System.currentTimeMillis() / 1000D) / 2D;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        j++;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void recordEvent(String s, int i, double d)
    {
        this;
        JVM INSTR monitorenter ;
        int j = 0;
_L2:
        if(j < events_.size())
            break MISSING_BLOCK_LABEL_70;
        Event event = new Event();
        event.key = s;
        event.count = i;
        event.sum = d;
        event.timestamp = (double)System.currentTimeMillis() / 1000D;
        events_.add(event);
        this;
        JVM INSTR monitorexit ;
        return;
        Event event1 = (Event)events_.get(j);
        if(!event1.key.equals(s))
            break MISSING_BLOCK_LABEL_153;
        event1.count = i + event1.count;
        event1.sum = d + event1.sum;
        event1.timestamp = (event1.timestamp + (double)System.currentTimeMillis() / 1000D) / 2D;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        j++;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void recordEvent(String s, Map map, int i)
    {
        this;
        JVM INSTR monitorenter ;
        int j = 0;
_L2:
        if(j < events_.size())
            break MISSING_BLOCK_LABEL_70;
        Event event = new Event();
        event.key = s;
        event.segmentation = map;
        event.count = i;
        event.timestamp = (double)System.currentTimeMillis() / 1000D;
        events_.add(event);
        this;
        JVM INSTR monitorexit ;
        return;
        Event event1 = (Event)events_.get(j);
        if(!event1.key.equals(s) || event1.segmentation == null || !event1.segmentation.equals(map))
            break MISSING_BLOCK_LABEL_163;
        event1.count = i + event1.count;
        event1.timestamp = (event1.timestamp + (double)System.currentTimeMillis() / 1000D) / 2D;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        j++;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void recordEvent(String s, Map map, int i, double d)
    {
        this;
        JVM INSTR monitorenter ;
        int j = 0;
_L2:
        if(j < events_.size())
            break MISSING_BLOCK_LABEL_77;
        Event event = new Event();
        event.key = s;
        event.segmentation = map;
        event.count = i;
        event.sum = d;
        event.timestamp = (double)System.currentTimeMillis() / 1000D;
        events_.add(event);
        this;
        JVM INSTR monitorexit ;
        return;
        Event event1 = (Event)events_.get(j);
        if(!event1.key.equals(s) || event1.segmentation == null || !event1.segmentation.equals(map))
            break MISSING_BLOCK_LABEL_183;
        event1.count = i + event1.count;
        event1.sum = d + event1.sum;
        event1.timestamp = (event1.timestamp + (double)System.currentTimeMillis() / 1000D) / 2D;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        j++;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public int size()
    {
        this;
        JVM INSTR monitorenter ;
        int i = events_.size();
        this;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private ArrayList events_;
}
