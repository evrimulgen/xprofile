// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.drive;

import android.content.IntentSender;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.drive.internal.CreateFileIntentSenderRequest;
import com.google.android.gms.drive.internal.n;
import com.google.android.gms.drive.internal.u;
import com.google.android.gms.internal.er;
import java.io.IOException;

// Referenced classes of package com.google.android.gms.drive:
//            Contents, Drive, MetadataChangeSet, DriveId

public class CreateFileActivityBuilder
{

    public CreateFileActivityBuilder()
    {
    }

    public IntentSender build(GoogleApiClient googleapiclient)
    {
        er.b(CW, "Must provide initial contents to CreateFileActivityBuilder.");
        u u1;
        IntentSender intentsender;
        try
        {
            CW.getParcelFileDescriptor().close();
        }
        catch(IOException ioexception) { }
        CW.close();
        er.a(googleapiclient.isConnected(), "Client must be connected");
        u1 = ((n)googleapiclient.a(Drive.va)).eT();
        try
        {
            intentsender = u1.a(new CreateFileIntentSenderRequest(CV.eS(), CW.eP(), CX, CY));
        }
        catch(RemoteException remoteexception)
        {
            throw new RuntimeException("Unable to connect Drive Play Service", remoteexception);
        }
        return intentsender;
    }

    public CreateFileActivityBuilder setActivityStartFolder(DriveId driveid)
    {
        CY = (DriveId)er.f(driveid);
        return this;
    }

    public CreateFileActivityBuilder setActivityTitle(String s)
    {
        CX = (String)er.f(s);
        return this;
    }

    public CreateFileActivityBuilder setInitialContents(Contents contents)
    {
        CW = (Contents)er.f(contents);
        return this;
    }

    public CreateFileActivityBuilder setInitialMetadata(MetadataChangeSet metadatachangeset)
    {
        CV = (MetadataChangeSet)er.f(metadatachangeset);
        return this;
    }

    public static final String EXTRA_RESPONSE_DRIVE_ID = "response_drive_id";
    private MetadataChangeSet CV;
    private Contents CW;
    private String CX;
    private DriveId CY;
}
