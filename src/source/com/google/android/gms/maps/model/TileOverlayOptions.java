// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.maps.model;

import android.os.*;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.maps.internal.r;
import com.google.android.gms.maps.model.internal.g;

// Referenced classes of package com.google.android.gms.maps.model:
//            TileOverlayOptionsCreator, j, TileProvider, Tile

public final class TileOverlayOptions
    implements SafeParcelable
{

    public TileOverlayOptions()
    {
        PQ = true;
        Qv = true;
        wj = 1;
    }

    TileOverlayOptions(int i, IBinder ibinder, boolean flag, float f, boolean flag1)
    {
        PQ = true;
        Qv = true;
        wj = i;
        Qt = com.google.android.gms.maps.model.internal.g.a.au(ibinder);
        TileProvider tileprovider;
        if(Qt == null)
            tileprovider = null;
        else
            tileprovider = new TileProvider() {

                public Tile getTile(int k, int l, int i1)
                {
                    Tile tile;
                    try
                    {
                        tile = Qw.getTile(k, l, i1);
                    }
                    catch(RemoteException remoteexception)
                    {
                        return null;
                    }
                    return tile;
                }

                private final g Qw;
                final TileOverlayOptions Qx;

            
            {
                Qx = TileOverlayOptions.this;
                super();
                Qw = TileOverlayOptions.a(Qx);
            }
            }
;
        Qu = tileprovider;
        PQ = flag;
        PP = f;
        Qv = flag1;
    }

    static g a(TileOverlayOptions tileoverlayoptions)
    {
        return tileoverlayoptions.Qt;
    }

    public int describeContents()
    {
        return 0;
    }

    public TileOverlayOptions fadeIn(boolean flag)
    {
        Qv = flag;
        return this;
    }

    public boolean getFadeIn()
    {
        return Qv;
    }

    public TileProvider getTileProvider()
    {
        return Qu;
    }

    int getVersionCode()
    {
        return wj;
    }

    public float getZIndex()
    {
        return PP;
    }

    IBinder hh()
    {
        return Qt.asBinder();
    }

    public boolean isVisible()
    {
        return PQ;
    }

    public TileOverlayOptions tileProvider(TileProvider tileprovider)
    {
        Qu = tileprovider;
        Object obj;
        if(Qu == null)
            obj = null;
        else
            obj = new com.google.android.gms.maps.model.internal.g.a(tileprovider) {

                public Tile getTile(int i, int k, int l)
                {
                    return Qy.getTile(i, k, l);
                }

                final TileOverlayOptions Qx;
                final TileProvider Qy;

            
            {
                Qx = TileOverlayOptions.this;
                Qy = tileprovider;
                super();
            }
            }
;
        Qt = ((g) (obj));
        return this;
    }

    public TileOverlayOptions visible(boolean flag)
    {
        PQ = flag;
        return this;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        if(r.hc())
        {
            j.a(this, parcel, i);
            return;
        } else
        {
            TileOverlayOptionsCreator.a(this, parcel, i);
            return;
        }
    }

    public TileOverlayOptions zIndex(float f)
    {
        PP = f;
        return this;
    }

    public static final TileOverlayOptionsCreator CREATOR = new TileOverlayOptionsCreator();
    private float PP;
    private boolean PQ;
    private g Qt;
    private TileProvider Qu;
    private boolean Qv;
    private final int wj;

}
