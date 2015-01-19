// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.common.data;

import android.os.Bundle;
import java.util.Iterator;

// Referenced classes of package com.google.android.gms.common.data:
//            DataHolder, a

public abstract class DataBuffer
    implements Iterable
{

    protected DataBuffer(DataHolder dataholder)
    {
        zU = dataholder;
        if(zU != null)
            zU.c(this);
    }

    public void close()
    {
        if(zU != null)
            zU.close();
    }

    public int describeContents()
    {
        return 0;
    }

    public abstract Object get(int i);

    public int getCount()
    {
        if(zU == null)
            return 0;
        else
            return zU.getCount();
    }

    public Bundle getMetadata()
    {
        return zU.getMetadata();
    }

    public boolean isClosed()
    {
        if(zU == null)
            return true;
        else
            return zU.isClosed();
    }

    public Iterator iterator()
    {
        return new a(this);
    }

    protected final DataHolder zU;
}
