// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.OpenUDID;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.*;

public class OpenUDID_service extends Service
{

    public OpenUDID_service()
    {
    }

    public IBinder onBind(Intent intent)
    {
        return new Binder() {

            public boolean onTransact(int i, Parcel parcel, Parcel parcel1, int j)
            {
                SharedPreferences sharedpreferences = getSharedPreferences("openudid_prefs", 0);
                parcel1.writeInt(parcel.readInt());
                parcel1.writeString(sharedpreferences.getString("openudid", null));
                return true;
            }

            final OpenUDID_service this$0;

            
            {
                this$0 = OpenUDID_service.this;
                super();
            }
        }
;
    }
}
