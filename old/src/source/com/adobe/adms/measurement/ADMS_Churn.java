// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.adobe.adms.measurement;

import android.content.*;
import android.content.pm.*;
import android.database.Cursor;
import android.net.Uri;

// Referenced classes of package com.adobe.adms.measurement:
//            ADMS_ChurnBase, ADMS_Measurement

final class ADMS_Churn extends ADMS_ChurnBase
{

    protected ADMS_Churn(ADMS_Measurement adms_measurement)
    {
        super(adms_measurement);
        measurement = null;
        context = null;
        measurement = adms_measurement;
    }

    protected String getApplicationName()
    {
        String s;
        try
        {
            PackageManager packagemanager = measurement.context.getPackageManager();
            s = (String)packagemanager.getApplicationLabel(packagemanager.getApplicationInfo(measurement.context.getPackageName(), 0));
        }
        catch(android.content.pm.PackageManager.NameNotFoundException namenotfoundexception)
        {
            namenotfoundexception.printStackTrace();
            return null;
        }
        return s;
    }

    protected String getApplicationVersion()
    {
        String s;
        try
        {
            s = measurement.context.getPackageManager().getPackageInfo(measurement.context.getPackageName(), 0).versionName;
        }
        catch(android.content.pm.PackageManager.NameNotFoundException namenotfoundexception)
        {
            namenotfoundexception.printStackTrace();
            return "";
        }
        return s;
    }

    protected void handleFBReferrer()
    {
        if(context != null)
        {
            Uri uri = Uri.parse("content://com.facebook.katana.provider.AttributionIdProvider");
            Cursor cursor = context.getContentResolver().query(uri, new String[] {
                "aid"
            }, null, null, null);
            if(cursor != null && cursor.moveToFirst())
            {
                setContextDataValue(cursor.getString(cursor.getColumnIndex("aid")), "a.fb.attrib");
                return;
            }
        }
    }

    protected void handleGPReferrer()
    {
        if(!prefsGetBool("ADMS_ReferrerProcessed", false))
        {
            String s = prefsGetString("utm_source", null);
            String s1 = prefsGetString("utm_medium", null);
            String s2 = prefsGetString("utm_term", null);
            String s3 = prefsGetString("utm_content", null);
            String s4 = prefsGetString("utm_campaign", null);
            if(s != null && s1 != null && s4 != null)
            {
                setContextDataValue(s, "a.referrer.campaign.source");
                setContextDataValue(s1, "a.referrer.campaign.medium");
                setContextDataValue(s2, "a.referrer.campaign.term");
                setContextDataValue(s3, "a.referrer.campaign.content");
                setContextDataValue(s4, "a.referrer.campaign.name");
                prefsPutBool("ADMS_ReferrerProcessed", true);
            }
        }
    }

    protected void handleReferrers()
    {
        handleGPReferrer();
    }

    protected boolean prefsContains(String s)
    {
        return ADMS_Measurement.prefs.contains(s);
    }

    protected boolean prefsGetBool(String s, boolean flag)
    {
        return ADMS_Measurement.prefs.getBoolean(s, flag);
    }

    protected int prefsGetInt(String s, int i)
    {
        return ADMS_Measurement.prefs.getInt(s, i);
    }

    protected long prefsGetLong(String s, long l)
    {
        return ADMS_Measurement.prefs.getLong(s, l);
    }

    protected String prefsGetString(String s, String s1)
    {
        return ADMS_Measurement.prefs.getString(s, s1);
    }

    protected void prefsPutBool(String s, boolean flag)
    {
        ADMS_Measurement.editor.putBoolean(s, flag);
        ADMS_Measurement.editor.commit();
    }

    protected void prefsPutInt(String s, int i)
    {
        ADMS_Measurement.editor.putInt(s, i);
        ADMS_Measurement.editor.commit();
    }

    protected void prefsPutLong(String s, long l)
    {
        ADMS_Measurement.editor.putLong(s, l);
        ADMS_Measurement.editor.commit();
    }

    protected void prefsPutString(String s, String s1)
    {
        ADMS_Measurement.editor.putString(s, s1);
        ADMS_Measurement.editor.commit();
    }

    protected void removeObjectFromPrefsWithKey(String s)
    {
        ADMS_Measurement.editor.remove(s);
        ADMS_Measurement.editor.commit();
    }

    protected void setGenericVariables()
    {
        super.setGenericVariables();
        setContextDataValue((new StringBuilder()).append("Android ").append(ADMS_Measurement.getAndroidVersion()).toString(), appEnvironmentEvar);
    }

    protected void startActivity(Context context1)
    {
        context = context1;
        String s = (new StringBuilder()).append(context1.getApplicationInfo().packageName).append("open").toString();
        if(prefsGetBool(s, false))
            prefsPutBool("ADMS_SuccessfulClose", false);
        prefsPutBool(s, true);
        startSession();
    }

    protected void stopActivity(Context context1)
    {
        prefsPutBool((new StringBuilder()).append(context1.getApplicationInfo().packageName).append("open").toString(), false);
        stopSession();
    }

    private Context context;
    private ADMS_Measurement measurement;
}
