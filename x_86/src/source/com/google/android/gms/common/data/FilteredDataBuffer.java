// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.common.data;

import android.os.Bundle;
import com.google.android.gms.internal.ed;

// Referenced classes of package com.google.android.gms.common.data:
//            DataBuffer

public abstract class FilteredDataBuffer extends DataBuffer
{

    public FilteredDataBuffer(DataBuffer databuffer)
    {
        super(null);
        ed.d(databuffer);
        boolean flag;
        if(!(databuffer instanceof FilteredDataBuffer))
            flag = true;
        else
            flag = false;
        ed.a(flag, "Not possible to have nested FilteredDataBuffers.");
        mDataBuffer = databuffer;
    }

    public void close()
    {
        mDataBuffer.close();
    }

    protected abstract int computeRealPosition(int i);

    public Object get(int i)
    {
        return mDataBuffer.get(computeRealPosition(i));
    }

    public Bundle getMetadata()
    {
        return mDataBuffer.getMetadata();
    }

    public boolean isClosed()
    {
        return mDataBuffer.isClosed();
    }

    protected final DataBuffer mDataBuffer;
}
