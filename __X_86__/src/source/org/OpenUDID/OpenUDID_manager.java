// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.OpenUDID;

import android.content.*;
import android.content.pm.*;
import android.os.*;
import android.util.Log;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.*;

public class OpenUDID_manager
    implements ServiceConnection
{
    private class ValueComparator
        implements Comparator
    {

        public int compare(Object obj, Object obj1)
        {
            if(((Integer)mReceivedOpenUDIDs.get(obj)).intValue() < ((Integer)mReceivedOpenUDIDs.get(obj1)).intValue())
                return 1;
            return mReceivedOpenUDIDs.get(obj) != mReceivedOpenUDIDs.get(obj1) ? -1 : 0;
        }

        final OpenUDID_manager this$0;

        private ValueComparator()
        {
            this$0 = OpenUDID_manager.this;
            super();
        }

        ValueComparator(ValueComparator valuecomparator)
        {
            this();
        }
    }


    private OpenUDID_manager(Context context)
    {
        mPreferences = context.getSharedPreferences("openudid_prefs", 0);
        mContext = context;
        mReceivedOpenUDIDs = new HashMap();
    }

    private void generateOpenUDID()
    {
        Log.d("OpenUDID", "Generating openUDID");
        OpenUDID = android.provider.Settings.Secure.getString(mContext.getContentResolver(), "android_id");
        if(OpenUDID == null || OpenUDID.equals("9774d56d682e549c") || OpenUDID.length() < 15)
            OpenUDID = (new BigInteger(64, new SecureRandom())).toString(16);
    }

    private void getMostFrequentOpenUDID()
    {
        if(!mReceivedOpenUDIDs.isEmpty())
        {
            TreeMap treemap = new TreeMap(new ValueComparator(null));
            treemap.putAll(mReceivedOpenUDIDs);
            OpenUDID = (String)treemap.firstKey();
        }
    }

    public static String getOpenUDID()
    {
        if(!mInitialized)
            Log.e("OpenUDID", "Initialisation isn't done");
        return OpenUDID;
    }

    public static boolean isInitialized()
    {
        return mInitialized;
    }

    private void startService()
    {
        if(mMatchingIntents.size() > 0)
        {
            Log.d("OpenUDID", (new StringBuilder("Trying service ")).append(((ResolveInfo)mMatchingIntents.get(0)).loadLabel(mContext.getPackageManager())).toString());
            ServiceInfo serviceinfo = ((ResolveInfo)mMatchingIntents.get(0)).serviceInfo;
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(serviceinfo.applicationInfo.packageName, serviceinfo.name));
            mContext.bindService(intent, this, 1);
            mMatchingIntents.remove(0);
            return;
        }
        getMostFrequentOpenUDID();
        if(OpenUDID == null)
            generateOpenUDID();
        Log.d("OpenUDID", (new StringBuilder("OpenUDID: ")).append(OpenUDID).toString());
        storeOpenUDID();
        mInitialized = true;
    }

    private void storeOpenUDID()
    {
        android.content.SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString("openudid", OpenUDID);
        editor.commit();
    }

    public static void sync(Context context)
    {
        OpenUDID_manager openudid_manager = new OpenUDID_manager(context);
        OpenUDID = openudid_manager.mPreferences.getString("openudid", null);
        if(OpenUDID == null)
        {
            openudid_manager.mMatchingIntents = context.getPackageManager().queryIntentServices(new Intent("org.OpenUDID.GETUDID"), 0);
            Log.d("OpenUDID", (new StringBuilder(String.valueOf(openudid_manager.mMatchingIntents.size()))).append(" services matches OpenUDID").toString());
            if(openudid_manager.mMatchingIntents != null)
                openudid_manager.startService();
            return;
        } else
        {
            Log.d("OpenUDID", (new StringBuilder("OpenUDID: ")).append(OpenUDID).toString());
            mInitialized = true;
            return;
        }
    }

    public void onServiceConnected(ComponentName componentname, IBinder ibinder)
    {
        Parcel parcel;
        Parcel parcel1;
        parcel = Parcel.obtain();
        parcel.writeInt(mRandom.nextInt());
        parcel1 = Parcel.obtain();
        ibinder.transact(1, Parcel.obtain(), parcel1, 0);
        if(parcel.readInt() != parcel1.readInt()) goto _L2; else goto _L1
_L1:
        String s = parcel1.readString();
        if(s == null) goto _L2; else goto _L3
_L3:
        Log.d("OpenUDID", (new StringBuilder("Received ")).append(s).toString());
        if(!mReceivedOpenUDIDs.containsKey(s)) goto _L5; else goto _L4
_L4:
        mReceivedOpenUDIDs.put(s, Integer.valueOf(1 + ((Integer)mReceivedOpenUDIDs.get(s)).intValue()));
_L2:
        mContext.unbindService(this);
        startService();
        return;
_L5:
        try
        {
            mReceivedOpenUDIDs.put(s, Integer.valueOf(1));
        }
        catch(RemoteException remoteexception)
        {
            Log.e("OpenUDID", (new StringBuilder("RemoteException: ")).append(remoteexception.getMessage()).toString());
        }
        if(true) goto _L2; else goto _L6
_L6:
    }

    public void onServiceDisconnected(ComponentName componentname)
    {
    }

    private static final boolean LOG = true;
    private static String OpenUDID = null;
    public static final String PREFS_NAME = "openudid_prefs";
    public static final String PREF_KEY = "openudid";
    public static final String TAG = "OpenUDID";
    private static boolean mInitialized = false;
    private final Context mContext;
    private List mMatchingIntents;
    private final SharedPreferences mPreferences;
    private final Random mRandom = new Random();
    private Map mReceivedOpenUDIDs;


}
