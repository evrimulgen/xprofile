// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.adobe.adms.mediameasurement;

import com.adobe.adms.measurement.ADMS_Measurement;
import java.util.*;

// Referenced classes of package com.adobe.adms.mediameasurement:
//            ADMS_MediaItem, ADMS_MediaState, ADMS_MediaMeasurementDelegate

public class ADMS_MediaMeasurement
{
    private static class ADMS_MediaMeasurementHolder
    {

        public static ADMS_MediaMeasurement baseInstance = new ADMS_MediaMeasurement();


        private ADMS_MediaMeasurementHolder()
        {
        }
    }


    private ADMS_MediaMeasurement()
    {
        list = null;
        trackVars = "";
        trackEvents = "";
        channel = "";
        trackSeconds = 0;
        completeCloseOffsetThreshold = 1;
        trackMilestones = "";
        segmentByMilestones = false;
        trackOffsetMilestones = "";
        segmentByOffsetMilestones = false;
        contextDataMapping = null;
        _flddelegate = null;
    }


    private boolean isInteger(Object obj)
    {
        Integer integer;
        boolean flag;
        try
        {
            integer = (Integer)obj;
        }
        catch(Exception exception)
        {
            return false;
        }
        flag = false;
        if(integer != null)
            flag = true;
        return flag;
    }

    private boolean isString(Object obj)
    {
        String s;
        boolean flag;
        try
        {
            s = (String)obj;
        }
        catch(Exception exception)
        {
            return false;
        }
        flag = false;
        if(s != null)
            flag = true;
        return flag;
    }

    public static ADMS_MediaMeasurement sharedInstance()
    {
        return ADMS_MediaMeasurementHolder.baseInstance;
    }

    private String toString(Object obj)
    {
        return (String)obj;
    }

    protected void buildContextData(Hashtable hashtable, Hashtable hashtable1, ADMS_MediaItem adms_mediaitem)
    {
        StringBuffer stringbuffer;
        StringBuffer stringbuffer1;
        StringBuffer stringbuffer2;
        stringbuffer = new StringBuffer(String.valueOf(hashtable1.get("linkTrackVars")));
        stringbuffer1 = new StringBuffer(String.valueOf(hashtable1.get("linkTrackEvents")));
        String s = "m_i";
        hashtable.put("a.contentType", "video");
        if(isSet(channel))
            hashtable.put((new StringBuilder()).append("a.media.").append("channel").toString(), channel);
        hashtable.put((new StringBuilder()).append("a.media.").append("name").toString(), adms_mediaitem.name);
        hashtable.put((new StringBuilder()).append("a.media.").append("playerName").toString(), adms_mediaitem.playerName);
        if(adms_mediaitem.length > 0.0D)
            hashtable.put((new StringBuilder()).append("a.media.").append("length").toString(), (new StringBuilder()).append("").append((int)adms_mediaitem.length).toString());
        hashtable.put((new StringBuilder()).append("a.media.").append("timePlayed").toString(), (new StringBuilder()).append("").append((int)Math.floor(adms_mediaitem.timePlayedSinceTrack)).toString());
        if(!adms_mediaitem.viewTracked)
        {
            hashtable.put((new StringBuilder()).append("a.media.").append("view").toString(), String.valueOf(true));
            s = "m_s";
            adms_mediaitem.viewTracked = true;
        }
        if(isSet(adms_mediaitem.segment))
        {
            hashtable.put((new StringBuilder()).append("a.media.").append("segmentNum").toString(), (new StringBuilder()).append("").append(adms_mediaitem.segmentNum).toString());
            hashtable.put((new StringBuilder()).append("a.media.").append("segment").toString(), adms_mediaitem.segment);
            if(adms_mediaitem.segmentLength > 0.0D)
                hashtable.put((new StringBuilder()).append("a.media.").append("segmentLength").toString(), (new StringBuilder()).append("").append(adms_mediaitem.segmentLength).toString());
            if(adms_mediaitem.segmentChanged && adms_mediaitem.timePlayedSinceTrack > 0.0D)
                hashtable.put((new StringBuilder()).append("a.media.").append("segmentView").toString(), String.valueOf(true));
        }
        if(!adms_mediaitem.completeTracked && adms_mediaitem.complete)
        {
            hashtable.put((new StringBuilder()).append("a.media.").append("complete").toString(), String.valueOf(true));
            adms_mediaitem.completeTracked = true;
        }
        if(adms_mediaitem.lastMilestone > 0)
            hashtable.put((new StringBuilder()).append("a.media.").append("milestone").toString(), (new StringBuilder()).append("").append(adms_mediaitem.lastMilestone).toString());
        if(adms_mediaitem.lastOffsetMilestone > 0)
            hashtable.put((new StringBuilder()).append("a.media.").append("offsetMilestone").toString(), (new StringBuilder()).append("").append(adms_mediaitem.lastOffsetMilestone).toString());
        hashtable1.put("pe", s);
        hashtable1.put("pev3", "video");
        stringbuffer2 = new StringBuffer();
        if(!isSet(contextDataMapping)) goto _L2; else goto _L1
_L1:
        Enumeration enumeration1 = contextDataMapping.keys();
_L8:
        String s1;
        String s2;
        String s5;
        if(!enumeration1.hasMoreElements())
            break; /* Loop/switch isn't completed */
        s1 = (String)enumeration1.nextElement();
        Object obj;
        String as[];
        int i;
        if(s1.length() > "a.media.".length() && s1.substring(0, "a.media.".length()).equals("a.media."))
            s2 = s1.substring("a.media.".length());
        else
            s2 = "";
        obj = contextDataMapping.get(s1);
        if(!isString(obj) || !hashtable.containsKey(s1))
            break MISSING_BLOCK_LABEL_1388;
        as = ((String)obj).split(",");
        i = 0;
_L4:
        if(i >= as.length)
            continue; /* Loop/switch isn't completed */
        s5 = as[i];
        if(!s1.equals("a.contentType"))
            break; /* Loop/switch isn't completed */
        if(stringbuffer.length() > 0)
        {
            stringbuffer.append(",");
            stringbuffer.append(String.valueOf(s5));
        }
        hashtable1.put((String)s5, (String)(String)hashtable.get(s1));
_L6:
        i++;
        if(true) goto _L4; else goto _L3
_L3:
        if(s2.equals("")) goto _L6; else goto _L5
_L5:
        if(s2.equals("view") || s2.equals("segmentView") || s2.equals("complete") || s2.equals("timePlayed"))
        {
            if(s2.equals("timePlayed"))
            {
                if(isSet(hashtable.get(s1)) && Integer.valueOf(String.valueOf(hashtable.get(s1))).intValue() > 0)
                {
                    String s7;
                    if(stringbuffer2.length() > 0)
                        s7 = ",";
                    else
                        s7 = "";
                    stringbuffer2.append(s7);
                    stringbuffer2.append(String.valueOf(s5));
                    stringbuffer2.append("=");
                    stringbuffer2.append(hashtable.get(s1));
                    if(stringbuffer1.length() > 0)
                    {
                        stringbuffer1.append(",");
                        stringbuffer1.append(String.valueOf(s5));
                        stringbuffer1.append("=");
                        stringbuffer1.append(hashtable.get(s1));
                    }
                }
            } else
            if(isSet(hashtable.get(s1)))
            {
                String s6;
                if(stringbuffer2.length() > 0)
                    s6 = ",";
                else
                    s6 = "";
                stringbuffer2.append(s6);
                stringbuffer2.append(String.valueOf(s5));
                if(stringbuffer1.length() > 0)
                {
                    stringbuffer1.append(",");
                    stringbuffer1.append(String.valueOf(s5));
                }
            }
        } else
        if(s2.equals("segment") && hashtable.containsKey((new StringBuilder()).append(s1).append("Num").toString()) && isSet(hashtable.get((new StringBuilder()).append(s1).append("Num").toString())))
        {
            if(stringbuffer.length() > 0)
            {
                stringbuffer.append(",");
                stringbuffer.append(String.valueOf(s5));
            }
            hashtable1.put(String.valueOf(s5), (new StringBuilder()).append("").append(hashtable.get((new StringBuilder()).append(s1).append("Num").toString())).append(":").append(String.valueOf(hashtable.get(s1))).toString());
        } else
        {
            if(stringbuffer.length() > 0)
            {
                stringbuffer.append(",");
                stringbuffer.append(String.valueOf(s5));
            }
            hashtable1.put(String.valueOf(s5), String.valueOf(hashtable.get(s1)));
        }
          goto _L6
        if(s2.equals("milestones") || s2.equals("offsetMilestones"))
        {
            String s3 = s1.substring(0, -1 + s1.length());
            Hashtable hashtable2 = (Hashtable)contextDataMapping.get((new StringBuilder()).append(s3).append("s").toString());
            if(hashtable.containsKey(s3) && isSet(hashtable.get(s3)) && hashtable2.containsKey(hashtable.get(s3)))
            {
                if(stringbuffer1.length() > 0)
                {
                    stringbuffer1.append(",");
                    stringbuffer1.append(hashtable2.get(hashtable.get(s3)));
                }
                String s4;
                if(stringbuffer2.length() > 0)
                    s4 = ",";
                else
                    s4 = "";
                stringbuffer2.append(s4);
                stringbuffer2.append(hashtable2.get(hashtable.get(s3)));
            }
        }
        if(true) goto _L8; else goto _L7
_L7:
        hashtable1.put("events", stringbuffer2.toString());
_L10:
        hashtable1.put("linkTrackVars", stringbuffer.toString());
        hashtable1.put("linkTrackEvents", stringbuffer1.toString());
        return;
_L2:
        if(stringbuffer.length() == 0)
            stringbuffer.append("None");
        Enumeration enumeration = hashtable.keys();
        while(enumeration.hasMoreElements()) 
        {
            stringbuffer.append(",contextdata.");
            stringbuffer.append((String)enumeration.nextElement());
        }
        if(true) goto _L10; else goto _L9
_L9:
    }

    protected String cleanName(String s)
    {
        return s.replace("\n", "").replace("\r", "").replace("--**--", "");
    }

    public Object clone()
        throws CloneNotSupportedException
    {
        throw new CloneNotSupportedException();
    }

    public void close(String s)
    {
        playerEvent(s, 0, -1D, 0, null, -1D, null);
    }

    public void complete(String s, double d)
    {
        playerEvent(s, 5, d, 0, null, -1D, null);
    }

    protected boolean isBoolean(Object obj)
    {
        Boolean boolean1;
        boolean flag;
        try
        {
            boolean1 = (Boolean)obj;
        }
        catch(Exception exception)
        {
            return false;
        }
        flag = false;
        if(boolean1 != null)
            flag = true;
        return flag;
    }

    protected boolean isSet(double d)
    {
        return d != 0.0D;
    }

    protected boolean isSet(float f)
    {
        return (double)f != 0.0D;
    }

    protected boolean isSet(int i)
    {
        return i != 0;
    }

    protected boolean isSet(Object obj)
    {
        if(obj == null)
            return false;
        if(isString(obj))
            return isSet(toString(obj));
        if(isInteger(obj))
            return isSet(toInteger(obj));
        if(isBoolean(obj))
            return isSet(toBoolean(obj));
        else
            return true;
    }

    protected boolean isSet(String s)
    {
        while(s == null || s.length() == 0) 
            return false;
        return true;
    }

    protected boolean isSet(boolean flag)
    {
        return flag;
    }

    public void open(String s, double d, String s1)
    {
        open(s, d, s1, null);
    }

    public void open(String s, double d, String s1, String s2)
    {
        ADMS_MediaItem adms_mediaitem = new ADMS_MediaItem();
        String s3;
        if(!isSet(s1))
            s1 = "Not_Specified";
        s3 = cleanName(s1);
        if(isSet(s))
            s = cleanName(s);
        if(!isSet(d))
            d = -1D;
        if(isSet(s) && isSet(s3))
        {
            if(!isSet(list))
                list = new Hashtable();
            if(list.containsKey(s))
                close(s);
            if(isSet(s2))
            {
                Enumeration enumeration = list.keys();
                do
                {
                    if(!enumeration.hasMoreElements())
                        break;
                    String s4 = (String)enumeration.nextElement();
                    if(list.containsKey(s4) && ((ADMS_MediaItem)(ADMS_MediaItem)list.get(s4)).playerID.equals(s2))
                        close(s4);
                } while(true);
            }
            adms_mediaitem.name = s;
            adms_mediaitem.length = d;
            adms_mediaitem.offset = 0.0D;
            adms_mediaitem.percent = 0.0D;
            adms_mediaitem.playerName = cleanName(s3);
            adms_mediaitem.playerID = s2;
            adms_mediaitem.timePlayed = 0.0D;
            adms_mediaitem.timePlayedSinceTrack = 0.0D;
            adms_mediaitem.timestamp = Math.floor((double)System.currentTimeMillis() / 1000D);
            adms_mediaitem.lastEventType = 0;
            adms_mediaitem.lastEventTimestamp = adms_mediaitem.timestamp;
            adms_mediaitem.lastEventOffset = 0.0D;
            adms_mediaitem.session = "";
            adms_mediaitem.lastTrackOffset = -1D;
            adms_mediaitem.trackCount = 0;
            adms_mediaitem.firstEventList = new Hashtable();
            adms_mediaitem.viewTracked = false;
            adms_mediaitem.segmentNum = 0;
            adms_mediaitem.segment = "";
            adms_mediaitem.segmentLength = 0.0D;
            adms_mediaitem.segmentGenerated = false;
            adms_mediaitem.segmentChanged = false;
            adms_mediaitem.lastMilestone = 0;
            adms_mediaitem.lastOffsetMilestone = 0;
            adms_mediaitem.m = this;
            list.put(s, adms_mediaitem);
        }
    }

    public void play(String s, double d)
    {
        if(isSet(s))
            s = cleanName(s);
        playerEvent(s, 1, d, 0, null, -1D, null);
        if(isSet(s) && isSet(list) && list.containsKey(s))
            ((ADMS_MediaItem)(ADMS_MediaItem)list.get(s)).startMonitor();
    }

    protected ADMS_MediaItem playerEvent(String s, int i, double d, int j, String s1, double d1, Object obj)
    {
        double d2;
        String s2;
        String s3;
        double d3;
        String s4;
        String s5;
        boolean flag;
        boolean flag1;
        boolean flag2;
        ADMS_MediaItem adms_mediaitem;
        int k;
        ADMS_MediaState adms_mediastate;
        double d8;
        double d10;
        int l4;
        d2 = Math.floor((double)System.currentTimeMillis() / 1000D);
        s2 = trackVars;
        s3 = trackEvents;
        d3 = trackSeconds;
        s4 = trackMilestones;
        s5 = trackOffsetMilestones;
        flag = segmentByMilestones;
        flag1 = segmentByOffsetMilestones;
        flag2 = true;
        if(isSet(s))
            s = cleanName(s);
        if(isSet(s) && isSet(list) && list.containsKey(s))
            adms_mediaitem = (ADMS_MediaItem)(ADMS_MediaItem)list.get(s);
        else
            adms_mediaitem = null;
        if(adms_mediaitem == null)
            break MISSING_BLOCK_LABEL_2470;
        if(d < 0.0D)
            if(adms_mediaitem.lastEventType == 1 && adms_mediaitem.lastEventTimestamp > 0.0D)
                d = (d2 - adms_mediaitem.lastEventTimestamp) + adms_mediaitem.lastEventOffset;
            else
                d = adms_mediaitem.lastEventOffset;
        if(adms_mediaitem.length > 0.0D && d >= adms_mediaitem.length)
            d = adms_mediaitem.length;
        if(d < 0.0D)
            d = 0.0D;
        adms_mediaitem.offset = d;
        if(adms_mediaitem.length > 0.0D)
        {
            adms_mediaitem.percent = 100D * (adms_mediaitem.offset / adms_mediaitem.length);
            int k2;
            int l2;
            boolean flag3;
            int i4;
            int j4;
            int k4;
            int i5;
            int j5;
            double d11;
            if(adms_mediaitem.percent > 100D)
                d11 = 100D;
            else
                d11 = adms_mediaitem.percent;
            adms_mediaitem.percent = d11;
        }
        if(adms_mediaitem.lastEventOffset < 0.0D)
            adms_mediaitem.lastEventOffset = d;
        k = adms_mediaitem.trackCount;
        adms_mediastate = new ADMS_MediaState();
        adms_mediastate.name = s;
        adms_mediastate.length = adms_mediaitem.length;
        adms_mediastate.openTime = new Date();
        adms_mediastate.openTime.setTime((long)adms_mediaitem.timestamp);
        adms_mediastate.offset = adms_mediaitem.offset;
        adms_mediastate.percent = adms_mediaitem.percent;
        adms_mediastate.playerName = adms_mediaitem.playerName;
        if(adms_mediaitem.lastTrackOffset >= 0.0D) goto _L2; else goto _L1
_L1:
        adms_mediastate.mediaEvent = "OPEN";
_L20:
        if(i <= 2)
        {
            j5 = adms_mediaitem.lastEventType;
            if(i == j5 || i == 2 && adms_mediaitem.lastEventType != 1)
                break MISSING_BLOCK_LABEL_2470;
        }
        if(!isSet(s1))
        {
            j = adms_mediaitem.segmentNum;
            s1 = adms_mediaitem.segment;
        }
        if(i <= 0) goto _L4; else goto _L3
_L3:
        if(i == 1)
            adms_mediaitem.lastEventOffset = d;
        if(i > 3 && i != 5 || adms_mediaitem.lastTrackOffset < 0.0D) goto _L6; else goto _L5
_L5:
        s2 = "None";
        s3 = "None";
        k2 = Math.abs(adms_mediaitem.lastTrackOffset - d) != 0.0001D;
        flag2 = false;
        if(k2 <= 0) goto _L6; else goto _L7
_L7:
        d8 = adms_mediaitem.lastTrackOffset;
        if(d8 > d)
        {
            d8 = adms_mediaitem.lastEventOffset;
            if(d8 > d)
                d8 = d;
        }
        String as2[];
        if(isSet(s4))
            as2 = s4.split(",");
        else
            as2 = null;
        l2 = adms_mediaitem.length != 0.0D;
        flag2 = false;
        if(l2 <= 0) goto _L9; else goto _L8
_L8:
        flag3 = isSet(as2);
        flag2 = false;
        if(!flag3) goto _L9; else goto _L10
_L10:
        i4 = d != d8;
        flag2 = false;
        if(i4 < 0) goto _L9; else goto _L11
_L11:
        j4 = 0;
_L15:
        if(j4 >= as2.length) goto _L9; else goto _L12
_L12:
        if(!isSet(as2[j4])) goto _L14; else goto _L13
_L13:
        i5 = Integer.parseInt((new StringBuilder()).append("").append(as2[j4]).toString());
        l4 = i5;
_L16:
        d10 = l4;
_L17:
        if(isSet(d10) && 100D * (d8 / adms_mediaitem.length) < d10 && adms_mediaitem.percent >= d10)
        {
            flag2 = true;
            adms_mediastate.mediaEvent = "MILESTONE";
            k4 = (int)d10;
            adms_mediastate.milestone = k4;
            adms_mediaitem.lastMilestone = k4;
            j4 = as2.length;
        }
        j4++;
          goto _L15
_L2:
        switch(i)
        {
        default:
            adms_mediastate.mediaEvent = "CLOSE";
            break;

        case 1: // '\001'
            adms_mediastate.mediaEvent = "PLAY";
            break;

        case 2: // '\002'
            adms_mediastate.mediaEvent = "STOP";
            break;

        case 3: // '\003'
            adms_mediastate.mediaEvent = "MONITOR";
            break;

        case 4: // '\004'
            adms_mediastate.mediaEvent = "TRACK";
            break;

        case 5: // '\005'
            adms_mediastate.mediaEvent = "COMPLETE";
            break;
        }
        continue; /* Loop/switch isn't completed */
_L14:
        l4 = 0;
          goto _L16
        NumberFormatException numberformatexception3;
        numberformatexception3;
        d10 = 0.0D;
          goto _L17
_L9:
        String as3[];
        int i3;
        double d9;
        int k3;
        int l3;
        if(isSet(s5))
            as3 = s5.split(",");
        else
            as3 = null;
        if(!isSet(as3) || d < d8) goto _L6; else goto _L18
_L18:
        i3 = 0;
_L23:
        if(i3 < as3.length)
            break MISSING_BLOCK_LABEL_935;
          goto _L6
        if(true) goto _L20; else goto _L19
_L19:
        if(!isSet(as3[i3])) goto _L22; else goto _L21
_L21:
        l3 = Integer.parseInt((new StringBuilder()).append("").append(as3[i3]).toString());
        k3 = l3;
_L24:
        d9 = k3;
_L25:
        if(isSet(d9) && d8 < d9 && d >= d9)
        {
            flag2 = true;
            adms_mediastate.mediaEvent = "OFFSET_MILESTONE";
            int j3 = (int)d9;
            adms_mediastate.offsetMilestone = j3;
            adms_mediaitem.lastOffsetMilestone = j3;
            i3 = as3.length;
        }
        i3++;
          goto _L23
_L22:
        k3 = 0;
          goto _L24
        NumberFormatException numberformatexception2;
        numberformatexception2;
        d9 = 0.0D;
          goto _L25
_L6:
        String as1[];
        double d6;
        int l1;
        if(!adms_mediaitem.segmentGenerated && isSet(s1))
            break MISSING_BLOCK_LABEL_1676;
        if(!flag || !isSet(s4) || adms_mediaitem.length <= 0.0D)
            break MISSING_BLOCK_LABEL_1349;
        as1 = (new StringBuilder()).append(s4).append(",100").toString().split(",");
        if(!isSet(as1))
            break MISSING_BLOCK_LABEL_1661;
        d6 = 0.0D;
        l1 = 0;
_L28:
        if(l1 >= as1.length)
            break MISSING_BLOCK_LABEL_1661;
        if(!isSet(as1[l1])) goto _L27; else goto _L26
_L26:
        int j2 = Integer.parseInt((new StringBuilder()).append("").append(as1[l1]).toString());
        int i2 = j2;
_L29:
        double d7 = i2;
_L30:
        if(isSet(d7))
        {
            if(adms_mediaitem.percent < d7)
            {
                j = l1 + 1;
                StringBuilder stringbuilder2 = (new StringBuilder()).append("M:");
                Object aobj1[] = new Object[1];
                aobj1[0] = Double.valueOf(d6);
                StringBuilder stringbuilder3 = stringbuilder2.append(String.format("%1.0f", aobj1)).append("-");
                Object aobj2[] = new Object[1];
                aobj2[0] = Double.valueOf(d7);
                s1 = stringbuilder3.append(String.format("%1.0f", aobj2)).toString();
                l1 = as1.length;
            }
            d6 = d7;
        }
        l1++;
          goto _L28
_L27:
        i2 = 0;
          goto _L29
        NumberFormatException numberformatexception1;
        numberformatexception1;
        d7 = 0.0D;
          goto _L30
        double d5;
        int j1;
        if(!flag1 || !isSet(s5))
            break MISSING_BLOCK_LABEL_1661;
        String as[];
        double d4;
        int i1;
        StringBuilder stringbuilder1;
        Object aobj[];
        int k1;
        if(adms_mediaitem.length > 0.0D)
            as = (new StringBuilder()).append(s5).append(",").append((long)Math.floor(adms_mediaitem.length)).toString().split(",");
        else
            as = (new StringBuilder()).append(s5).append(",").append("E").toString().split(",");
        if(!isSet(as))
            break MISSING_BLOCK_LABEL_1661;
        d4 = 0.0D;
        i1 = 0;
        if(i1 >= as.length)
            break MISSING_BLOCK_LABEL_1661;
        if(!isSet(as[i1])) goto _L32; else goto _L31
_L31:
        k1 = Integer.parseInt((new StringBuilder()).append("").append(as[i1]).toString());
        j1 = k1;
_L33:
        d5 = j1;
_L34:
        if(isSet(d5) || as[i1].equals("E"))
        {
            if(d < d5 || as[i1].equals("E"))
            {
                j = i1 + 1;
                stringbuilder1 = (new StringBuilder()).append("O:");
                aobj = new Object[1];
                aobj[0] = Double.valueOf(d4);
                s1 = stringbuilder1.append(String.format("%1.0f", aobj)).append("-").append(as[i1]).toString();
                i1 = as.length;
            }
            d4 = d5;
        }
        i1++;
        break MISSING_BLOCK_LABEL_1427;
_L32:
        j1 = 0;
          goto _L33
        NumberFormatException numberformatexception;
        numberformatexception;
        d5 = 0.0D;
          goto _L34
        if(isSet(s1))
            adms_mediaitem.segmentGenerated = true;
        String s6;
        if(isSet(s1) || isSet(adms_mediaitem.segment))
        {
            String s7 = adms_mediaitem.segment;
            if(!s1.equals(s7))
            {
                adms_mediaitem.updateSegment = true;
                if(!isSet(adms_mediaitem.segment))
                {
                    adms_mediaitem.segmentNum = j;
                    adms_mediaitem.segment = s1;
                }
                if(adms_mediaitem.lastTrackOffset >= 0.0D)
                    flag2 = true;
            }
        }
        if(i >= 2 && adms_mediaitem.lastEventOffset < d)
        {
            adms_mediaitem.timePlayed = adms_mediaitem.timePlayed + (d - adms_mediaitem.lastEventOffset);
            adms_mediaitem.timePlayedSinceTrack = adms_mediaitem.timePlayedSinceTrack + (d - adms_mediaitem.lastEventOffset);
        }
        if(i <= 2 || i == 3 && adms_mediaitem.lastEventType == 0)
        {
            StringBuilder stringbuilder = (new StringBuilder()).append(adms_mediaitem.session);
            Hashtable hashtable;
            Hashtable hashtable1;
            String s8;
            int l;
            if(i == 1 || i == 3)
                s8 = "S";
            else
                s8 = "E";
            adms_mediaitem.session = stringbuilder.append(s8).append(Math.floor(d)).toString();
            if(i == 3)
                l = 1;
            else
                l = i;
            adms_mediaitem.lastEventType = l;
        }
        if(!flag2 && adms_mediaitem.lastTrackOffset >= 0.0D && i <= 3)
        {
            if(d3 <= 0.0D)
                d3 = 0.0D;
            if(d3 > 0.0D && adms_mediaitem.timePlayedSinceTrack >= d3)
            {
                flag2 = true;
                adms_mediastate.mediaEvent = "SECONDS";
            }
        }
        adms_mediaitem.lastEventTimestamp = d2;
        adms_mediaitem.lastEventOffset = d;
_L4:
        if(i == 0 || i <= 3 && adms_mediaitem.percent >= 100D)
        {
            if(adms_mediaitem.lastEventType != 2)
                adms_mediaitem.session = (new StringBuilder()).append(adms_mediaitem.session).append("E").append(Math.floor(d)).toString();
            i = 0;
            s2 = "None";
            s3 = "None";
            adms_mediastate.mediaEvent = "CLOSE";
        }
        if(i == 5 || (i == 0 || adms_mediaitem.percent >= 100D) && adms_mediaitem.length > 0.0D && d >= adms_mediaitem.length - (double)completeCloseOffsetThreshold)
        {
            adms_mediastate.complete = true;
            adms_mediaitem.complete = true;
            flag2 = true;
        }
        s6 = adms_mediastate.mediaEvent;
        if(s6.equals("MILESTONE"))
            s6 = (new StringBuilder()).append(s6).append("_").append(adms_mediastate.milestone).toString();
        else
        if(s6.equals("OFFSET_MILESTONE"))
            s6 = (new StringBuilder()).append(s6).append("_").append(adms_mediastate.offsetMilestone).toString();
        if(!adms_mediaitem.firstEventList.containsKey(s6))
        {
            adms_mediastate.eventFirstTime = true;
            adms_mediaitem.firstEventList.put(s6, Integer.valueOf(1));
        } else
        {
            adms_mediastate.eventFirstTime = false;
        }
        adms_mediastate.timePlayed = adms_mediaitem.timePlayed;
        adms_mediastate.segmentNum = adms_mediaitem.segmentNum;
        adms_mediastate.segment = adms_mediaitem.segment;
        adms_mediastate.segmentLength = adms_mediaitem.segmentLength;
        if(_flddelegate != null && i != 4)
            _flddelegate.ADMS_MediaMeasurementMonitor(ADMS_Measurement.sharedInstance(), adms_mediastate);
        if(i == 0)
        {
            list.remove(s);
            adms_mediaitem.stopMonitor();
        }
        if(flag2 && adms_mediaitem.trackCount == k)
        {
            hashtable = new Hashtable();
            hashtable1 = new Hashtable();
            if(!isSet(s2))
                s2 = "";
            if(!isSet(s3))
                s3 = "";
            hashtable.put("linkTrackVars", s2);
            hashtable.put("linkTrackEvents", s3);
            buildContextData(hashtable1, hashtable, adms_mediaitem);
            ADMS_Measurement.sharedInstance().track(hashtable1, hashtable);
            if(adms_mediaitem.updateSegment)
            {
                adms_mediaitem.segmentNum = j;
                adms_mediaitem.segment = s1;
                adms_mediaitem.segmentChanged = true;
                adms_mediaitem.updateSegment = false;
            } else
            if(adms_mediaitem.timePlayedSinceTrack > 0.0D)
                adms_mediaitem.segmentChanged = false;
            adms_mediaitem.session = "";
            adms_mediaitem.lastMilestone = 0;
            adms_mediaitem.lastOffsetMilestone = 0;
            adms_mediaitem.timePlayedSinceTrack = adms_mediaitem.timePlayedSinceTrack - Math.floor(adms_mediaitem.timePlayedSinceTrack);
            adms_mediaitem.lastTrackOffset = d;
            adms_mediaitem.trackCount = 1 + adms_mediaitem.trackCount;
        }
        return adms_mediaitem;
          goto _L23
    }

    public void stop(String s, double d)
    {
        playerEvent(s, 2, d, 0, null, -1D, null);
    }

    protected boolean toBoolean(Object obj)
    {
        return ((Boolean)obj).booleanValue();
    }

    protected int toInteger(Object obj)
    {
        return ((Integer)obj).intValue();
    }

    public void track(String s)
    {
        playerEvent(s, 4, -1D, 0, null, -1D, null);
    }

    public String channel;
    public int completeCloseOffsetThreshold;
    public Hashtable contextDataMapping;
    public ADMS_MediaMeasurementDelegate _flddelegate;
    protected Hashtable list;
    public boolean segmentByMilestones;
    public boolean segmentByOffsetMilestones;
    public String trackEvents;
    public String trackMilestones;
    public String trackOffsetMilestones;
    public int trackSeconds;
    public String trackVars;
}
