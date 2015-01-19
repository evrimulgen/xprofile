// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.games;

import com.google.android.gms.common.api.GoogleApiClient;

public interface Notifications
{

    public abstract void clear(GoogleApiClient googleapiclient, int i);

    public abstract void clearAll(GoogleApiClient googleapiclient);

    public static final int NOTIFICATION_TYPES_ALL = 7;
    public static final int NOTIFICATION_TYPES_MULTIPLAYER = 3;
    public static final int NOTIFICATION_TYPE_INVITATION = 1;
    public static final int NOTIFICATION_TYPE_MATCH_UPDATE = 2;
    public static final int NOTIFICATION_TYPE_REQUEST = 4;
}
