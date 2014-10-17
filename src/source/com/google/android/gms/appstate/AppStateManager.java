// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.appstate;

import android.content.Context;
import android.os.Looper;
import android.os.RemoteException;
import com.google.android.gms.common.api.*;
import com.google.android.gms.internal.*;
import java.util.List;

// Referenced classes of package com.google.android.gms.appstate:
//            AppStateBuffer

public final class AppStateManager
{
    public static interface StateConflictResult
        extends Releasable, Result
    {

        public abstract byte[] getLocalData();

        public abstract String getResolvedVersion();

        public abstract byte[] getServerData();

        public abstract int getStateKey();
    }

    public static interface StateDeletedResult
        extends Result
    {

        public abstract int getStateKey();
    }

    public static interface StateListResult
        extends Result
    {

        public abstract AppStateBuffer getStateBuffer();
    }

    public static interface StateLoadedResult
        extends Releasable, Result
    {

        public abstract byte[] getLocalData();

        public abstract int getStateKey();
    }

    public static interface StateResult
        extends Releasable, Result
    {

        public abstract StateConflictResult getConflictResult();

        public abstract StateLoadedResult getLoadedResult();
    }

    public static abstract class a extends com.google.android.gms.common.api.a.a
        implements PendingResult
    {

        public a()
        {
            super(AppStateManager.va);
        }
    }

    private static abstract class b extends a
    {

        private b()
        {
        }

    }

    private static abstract class c extends a
    {

        public Result d(Status status)
        {
            return e(status);
        }

        public StateListResult e(Status status)
        {
            return new StateListResult(this, status) {

                public AppStateBuffer getStateBuffer()
                {
                    return new AppStateBuffer(null);
                }

                public Status getStatus()
                {
                    return vb;
                }

                final Status vb;
                final c vh;

            
            {
                vh = c1;
                vb = status;
                super();
            }
            }
;
        }

        private c()
        {
        }

    }

    private static abstract class d extends a
    {

        public Result d(Status status)
        {
            return f(status);
        }

        public Status f(Status status)
        {
            return status;
        }

        private d()
        {
        }

    }

    private static abstract class e extends a
    {

        public Result d(Status status)
        {
            return g(status);
        }

        public StateResult g(Status status)
        {
            return AppStateManager.b(status);
        }

        private e()
        {
        }

    }


    private AppStateManager()
    {
    }

    private static StateResult a(Status status)
    {
        return new StateResult(status) {

            public StateConflictResult getConflictResult()
            {
                return null;
            }

            public StateLoadedResult getLoadedResult()
            {
                return null;
            }

            public Status getStatus()
            {
                return vb;
            }

            public void release()
            {
            }

            final Status vb;

            
            {
                vb = status;
                super();
            }
        }
;
    }

    public static dl a(GoogleApiClient googleapiclient)
    {
        boolean flag = true;
        boolean flag1;
        dl dl1;
        if(googleapiclient != null)
            flag1 = flag;
        else
            flag1 = false;
        er.b(flag1, "GoogleApiClient parameter is required.");
        er.a(googleapiclient.isConnected(), "GoogleApiClient must be connected.");
        dl1 = (dl)googleapiclient.a(va);
        if(dl1 == null)
            flag = false;
        er.a(flag, "GoogleApiClient is not configured to use the AppState API. Pass AppStateManager.API into GoogleApiClient.Builder#addApi() to use this feature.");
        return dl1;
    }

    static StateResult b(Status status)
    {
        return a(status);
    }

    public static PendingResult delete(GoogleApiClient googleapiclient, int i)
    {
        return googleapiclient.b(new b(i) {

            protected volatile void a(com.google.android.gms.common.api.Api.a a1)
                throws RemoteException
            {
                a((dl)a1);
            }

            protected void a(dl dl1)
            {
                dl1.a(this, vc);
            }

            public StateDeletedResult c(Status status)
            {
                return new StateDeletedResult(this, status) {

                    public int getStateKey()
                    {
                        return ve.vc;
                    }

                    public Status getStatus()
                    {
                        return vb;
                    }

                    final Status vb;
                    final _cls5 ve;

            
            {
                ve = _pcls5;
                vb = status;
                super();
            }
                }
;
            }

            public Result d(Status status)
            {
                return c(status);
            }

            final int vc;

            
            {
                vc = i;
                super();
            }
        }
);
    }

    public static int getMaxNumKeys(GoogleApiClient googleapiclient)
    {
        return a(googleapiclient).cO();
    }

    public static int getMaxStateSize(GoogleApiClient googleapiclient)
    {
        return a(googleapiclient).cN();
    }

    public static PendingResult list(GoogleApiClient googleapiclient)
    {
        return googleapiclient.a(new c() {

            protected volatile void a(com.google.android.gms.common.api.Api.a a1)
                throws RemoteException
            {
                a((dl)a1);
            }

            protected void a(dl dl1)
            {
                dl1.a(this);
            }

        }
);
    }

    public static PendingResult load(GoogleApiClient googleapiclient, int i)
    {
        return googleapiclient.a(new e(i) {

            protected volatile void a(com.google.android.gms.common.api.Api.a a1)
                throws RemoteException
            {
                a((dl)a1);
            }

            protected void a(dl dl1)
            {
                dl1.b(this, vc);
            }

            final int vc;

            
            {
                vc = i;
                super();
            }
        }
);
    }

    public static PendingResult resolve(GoogleApiClient googleapiclient, int i, String s, byte abyte0[])
    {
        return googleapiclient.b(new e(i, s, abyte0) {

            protected volatile void a(com.google.android.gms.common.api.Api.a a1)
                throws RemoteException
            {
                a((dl)a1);
            }

            protected void a(dl dl1)
            {
                dl1.a(this, vc, vf, vg);
            }

            final int vc;
            final String vf;
            final byte vg[];

            
            {
                vc = i;
                vf = s;
                vg = abyte0;
                super();
            }
        }
);
    }

    public static PendingResult signOut(GoogleApiClient googleapiclient)
    {
        return googleapiclient.b(new d() {

            protected volatile void a(com.google.android.gms.common.api.Api.a a1)
                throws RemoteException
            {
                a((dl)a1);
            }

            protected void a(dl dl1)
            {
                dl1.b(this);
            }

        }
);
    }

    public static void update(GoogleApiClient googleapiclient, int i, byte abyte0[])
    {
        googleapiclient.b(new e(i, abyte0) {

            protected volatile void a(com.google.android.gms.common.api.Api.a a1)
                throws RemoteException
            {
                a((dl)a1);
            }

            protected void a(dl dl1)
            {
                dl1.a(null, vc, vd);
            }

            final int vc;
            final byte vd[];

            
            {
                vc = i;
                vd = abyte0;
                super();
            }
        }
);
    }

    public static PendingResult updateImmediate(GoogleApiClient googleapiclient, int i, byte abyte0[])
    {
        return googleapiclient.b(new e(i, abyte0) {

            protected volatile void a(com.google.android.gms.common.api.Api.a a1)
                throws RemoteException
            {
                a((dl)a1);
            }

            protected void a(dl dl1)
            {
                dl1.a(this, vc, vd);
            }

            final int vc;
            final byte vd[];

            
            {
                vc = i;
                vd = abyte0;
                super();
            }
        }
);
    }

    public static final Api API;
    public static final Scope SCOPE_APP_STATE;
    static final com.google.android.gms.common.api.Api.b va;

    static 
    {
        va = new com.google.android.gms.common.api.Api.b() {

            public dl a(Context context, Looper looper, ee ee1, com.google.android.gms.common.api.GoogleApiClient.ApiOptions apioptions, com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks connectioncallbacks, com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener onconnectionfailedlistener)
            {
                return new dl(context, looper, connectioncallbacks, onconnectionfailedlistener, ee1.dR(), (String[])ee1.dT().toArray(new String[0]));
            }

            public com.google.android.gms.common.api.Api.a b(Context context, Looper looper, ee ee1, com.google.android.gms.common.api.GoogleApiClient.ApiOptions apioptions, com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks connectioncallbacks, com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener onconnectionfailedlistener)
            {
                return a(context, looper, ee1, apioptions, connectioncallbacks, onconnectionfailedlistener);
            }

            public int getPriority()
            {
                return 0x7fffffff;
            }

        }
;
        SCOPE_APP_STATE = new Scope("https://www.googleapis.com/auth/appstate");
        com.google.android.gms.common.api.Api.b b1 = va;
        Scope ascope[] = new Scope[1];
        ascope[0] = SCOPE_APP_STATE;
        API = new Api(b1, ascope);
    }
}
