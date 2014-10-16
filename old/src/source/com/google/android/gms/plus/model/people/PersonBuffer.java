// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.plus.model.people;

import android.os.Bundle;
import com.google.android.gms.common.data.*;
import com.google.android.gms.internal.ir;
import com.google.android.gms.internal.jc;

// Referenced classes of package com.google.android.gms.plus.model.people:
//            Person

public final class PersonBuffer extends DataBuffer
{

    public PersonBuffer(DataHolder dataholder)
    {
        super(dataholder);
        if(dataholder.getMetadata() != null && dataholder.getMetadata().getBoolean("com.google.android.gms.plus.IsSafeParcelable", false))
        {
            Tu = new c(dataholder, ir.CREATOR);
            return;
        } else
        {
            Tu = null;
            return;
        }
    }

    public Person get(int i)
    {
        if(Tu != null)
            return (Person)Tu.H(i);
        else
            return new jc(zU, i);
    }

    public volatile Object get(int i)
    {
        return get(i);
    }

    private final c Tu;
}
