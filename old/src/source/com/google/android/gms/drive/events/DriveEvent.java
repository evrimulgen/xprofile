// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.drive.events;

import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public interface DriveEvent
    extends SafeParcelable
{
    public static interface Listener
    {

        public abstract void onEvent(DriveEvent driveevent);
    }


    public abstract int getType();

    public static final int TYPE_CHANGE = 1;
}
