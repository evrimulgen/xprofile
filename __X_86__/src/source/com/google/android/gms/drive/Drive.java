// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.drive;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.drive.internal.l;
import com.google.android.gms.drive.internal.n;
import com.google.android.gms.drive.internal.p;
import com.google.android.gms.internal.ee;
import java.util.List;

// Referenced classes of package com.google.android.gms.drive:
//            c, DriveApi

public final class Drive
{

    private Drive()
    {
    }

    public static final Api API;
    public static final Scope Da = new Scope("https://www.googleapis.com/auth/drive");
    public static final c Db = new p();
    public static final DriveApi DriveApi = new l();
    public static final Scope SCOPE_APPFOLDER = new Scope("https://www.googleapis.com/auth/drive.appdata");
    public static final Scope SCOPE_FILE = new Scope("https://www.googleapis.com/auth/drive.file");
    public static final com.google.android.gms.common.api.Api.b va;

    static 
    {
        va = new com.google.android.gms.common.api.Api.b() {

            public com.google.android.gms.common.api.Api.a b(Context context, Looper looper, ee ee1, com.google.android.gms.common.api.GoogleApiClient.ApiOptions apioptions, com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks connectioncallbacks, com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener onconnectionfailedlistener)
            {
                return d(context, looper, ee1, apioptions, connectioncallbacks, onconnectionfailedlistener);
            }

            public n d(Context context, Looper looper, ee ee1, com.google.android.gms.common.api.GoogleApiClient.ApiOptions apioptions, com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks connectioncallbacks, com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener onconnectionfailedlistener)
            {
                List list = ee1.dT();
                return new n(context, looper, ee1, connectioncallbacks, onconnectionfailedlistener, (String[])list.toArray(new String[list.size()]));
            }

            public int getPriority()
            {
                return 0x7fffffff;
            }

        }
;
        API = new Api(va, new Scope[0]);
    }
}
