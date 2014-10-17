// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.drive.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;

// Referenced classes of package com.google.android.gms.drive.internal:
//            c

public class ak extends c
{

    public ak(com.google.android.gms.common.api.a.c c1)
    {
        vj = c1;
    }

    public void l(Status status)
        throws RemoteException
    {
        vj.b(status);
    }

    public void onSuccess()
        throws RemoteException
    {
        vj.b(Status.zQ);
    }

    private final com.google.android.gms.common.api.a.c vj;
}
