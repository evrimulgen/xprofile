// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.quantcast.measurement.service;

import java.lang.ref.WeakReference;
import java.util.*;

// Referenced classes of package com.quantcast.measurement.service:
//            QCNotificationListener

final class QCNotificationCenter extends Enum
{

    private QCNotificationCenter(String s, int i)
    {
        super(s, i);
    }

    public static QCNotificationCenter valueOf(String s)
    {
        return (QCNotificationCenter)Enum.valueOf(com/quantcast/measurement/service/QCNotificationCenter, s);
    }

    public static QCNotificationCenter[] values()
    {
        return (QCNotificationCenter[])$VALUES.clone();
    }

    protected void addListener(String s, QCNotificationListener qcnotificationlistener)
    {
        Object obj = (List)m_bus.get(s);
        if(obj == null)
            obj = new ArrayList();
        ((List) (obj)).add(new WeakReference(qcnotificationlistener));
        m_bus.put(s, obj);
    }

    protected void postNotification(String s, Object obj)
    {
        List list = (List)m_bus.get(s);
        if(list != null)
        {
            for(Iterator iterator = list.iterator(); iterator.hasNext();)
            {
                QCNotificationListener qcnotificationlistener = (QCNotificationListener)((WeakReference)iterator.next()).get();
                if(qcnotificationlistener != null)
                    qcnotificationlistener.notificationCallback(s, obj);
                else
                    iterator.remove();
            }

        }
    }

    protected void removeAllListeners(String s)
    {
        m_bus.remove(s);
    }

    protected void removeListener(String s, QCNotificationListener qcnotificationlistener)
    {
        List list = (List)m_bus.get(s);
        if(list != null)
        {
            Iterator iterator = list.iterator();
            do
            {
                if(!iterator.hasNext())
                    break;
                WeakReference weakreference = (WeakReference)iterator.next();
                if(weakreference.get() == null || weakreference.get() == qcnotificationlistener)
                    iterator.remove();
            } while(true);
        }
    }

    private static final QCNotificationCenter $VALUES[];
    public static final QCNotificationCenter INSTANCE;
    private final Map m_bus = new HashMap();

    static 
    {
        INSTANCE = new QCNotificationCenter("INSTANCE", 0);
        QCNotificationCenter aqcnotificationcenter[] = new QCNotificationCenter[1];
        aqcnotificationcenter[0] = INSTANCE;
        $VALUES = aqcnotificationcenter;
    }
}
