// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.quantcast.measurement.service;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.instrumentation.AsyncTaskInstrumentation;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import java.io.*;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.quantcast.measurement.service:
//            QCNotificationCenter

class QCOptOutUtility
{

    QCOptOutUtility()
    {
    }

    static void askEveryone(Context context, boolean flag, boolean flag1)
    {
        TraceFieldInterface tracefieldinterface = new TraceFieldInterface(context) {

            public void _nr_setTrace(Trace trace)
            {
                try
                {
                    _nr_trace = trace;
                    return;
                }
                catch(Exception exception)
                {
                    return;
                }
            }

            protected transient Boolean doInBackground(Boolean aboolean1[])
            {
                boolean flag2;
                boolean flag3;
                Boolean boolean1;
                PackageManager packagemanager;
                flag2 = aboolean1[0].booleanValue();
                flag3 = aboolean1[1].booleanValue();
                boolean1 = Boolean.valueOf(false);
                packagemanager = context.getPackageManager();
                if(packagemanager == null) goto _L2; else goto _L1
_L1:
                Iterator iterator = packagemanager.getInstalledPackages(0).iterator();
_L5:
                PackageInfo packageinfo;
                do
                {
                    if(!iterator.hasNext())
                        break; /* Loop/switch isn't completed */
                    packageinfo = (PackageInfo)iterator.next();
                } while(packageinfo.packageName.equals(context.getPackageName()));
                Context context1 = context.createPackageContext(packageinfo.packageName, 0);
                if(!flag3) goto _L4; else goto _L3
_L3:
                boolean flag4;
                try
                {
                    QCOptOutUtility.createOptOut(context1, flag2);
                }
                catch(Exception exception) { }
                if(true) goto _L5; else goto _L4
_L4:
                boolean1 = Boolean.valueOf(QCOptOutUtility.isOptedOut(context1, false));
                flag4 = boolean1.booleanValue();
                if(!flag4) goto _L6; else goto _L2
_L6:
                break; /* Loop/switch isn't completed */
_L2:
                if(!flag3)
                    QCOptOutUtility.createOptOut(context, boolean1.booleanValue());
                return boolean1;
            }

            protected volatile Object doInBackground(Object aobj[])
            {
                TraceMachine.enterMethod(_nr_trace, "QCOptOutUtility$1#doInBackground", null);
_L1:
                Boolean boolean1 = doInBackground((Boolean[])aobj);
                TraceMachine.exitMethod();
                TraceMachine.unloadTraceContext(this);
                return boolean1;
                NoSuchFieldError nosuchfielderror;
                nosuchfielderror;
                TraceMachine.enterMethod(null, "QCOptOutUtility$1#doInBackground", null);
                  goto _L1
            }

            protected void onPostExecute(Boolean boolean1)
            {
                if(boolean1.booleanValue())
                    QCNotificationCenter.INSTANCE.postNotification("QC_OUC", boolean1);
            }

            protected volatile void onPostExecute(Object obj)
            {
                TraceMachine.enterMethod(_nr_trace, "QCOptOutUtility$1#onPostExecute", null);
_L1:
                onPostExecute((Boolean)obj);
                TraceMachine.exitMethod();
                return;
                NoSuchFieldError nosuchfielderror;
                nosuchfielderror;
                TraceMachine.enterMethod(null, "QCOptOutUtility$1#onPostExecute", null);
                  goto _L1
            }

            public Trace _nr_trace;
            final Context val$context;

            
            {
                context = context1;
                super();
            }
        }
;
        Boolean aboolean[] = new Boolean[2];
        aboolean[0] = Boolean.valueOf(flag);
        aboolean[1] = Boolean.valueOf(flag1);
        if(!(tracefieldinterface instanceof AsyncTask))
        {
            tracefieldinterface.execute(aboolean);
            return;
        } else
        {
            AsyncTaskInstrumentation.execute((AsyncTask)tracefieldinterface, aboolean);
            return;
        }
    }

    static void createOptOut(Context context, boolean flag)
    {
        FileOutputStream fileoutputstream = null;
        fileoutputstream = context.openFileOutput("QC-OPT-OUT", 3);
        int i;
        if(flag)
            i = 1;
        else
            i = 0;
        fileoutputstream.write(i);
        if(fileoutputstream == null)
            break MISSING_BLOCK_LABEL_31;
        fileoutputstream.close();
_L2:
        return;
        Exception exception1;
        exception1;
        if(fileoutputstream == null) goto _L2; else goto _L1
_L1:
        try
        {
            fileoutputstream.close();
            return;
        }
        catch(IOException ioexception1)
        {
            return;
        }
        Exception exception;
        exception;
        IOException ioexception2;
        if(fileoutputstream != null)
            try
            {
                fileoutputstream.close();
            }
            catch(IOException ioexception) { }
        throw exception;
        ioexception2;
    }

    static boolean isOptedOut(Context context)
    {
        return isOptedOut(context, true);
    }

    private static boolean isOptedOut(Context context, boolean flag)
    {
        FileInputStream fileinputstream = null;
        int i;
        fileinputstream = context.openFileInput("QC-OPT-OUT");
        i = fileinputstream.read();
        boolean flag1;
        if(i != 0)
            flag1 = true;
        else
            flag1 = false;
        Exception exception;
        IOException ioexception1;
        IOException ioexception2;
        FileNotFoundException filenotfoundexception;
        IOException ioexception3;
        if(fileinputstream != null)
            try
            {
                fileinputstream.close();
            }
            catch(IOException ioexception4)
            {
                return flag1;
            }
        return flag1;
        filenotfoundexception;
        if(!flag)
            break MISSING_BLOCK_LABEL_52;
        askEveryone(context, false, false);
        flag1 = false;
        if(fileinputstream != null)
        {
            try
            {
                fileinputstream.close();
            }
            // Misplaced declaration of an exception variable
            catch(IOException ioexception3)
            {
                return false;
            }
            return false;
        } else
        {
            break MISSING_BLOCK_LABEL_31;
        }
        ioexception1;
        flag1 = false;
        if(fileinputstream != null)
        {
            try
            {
                fileinputstream.close();
            }
            // Misplaced declaration of an exception variable
            catch(IOException ioexception2)
            {
                return false;
            }
            return false;
        } else
        {
            break MISSING_BLOCK_LABEL_31;
        }
        exception;
        if(fileinputstream != null)
            try
            {
                fileinputstream.close();
            }
            catch(IOException ioexception) { }
        throw exception;
    }

    static void saveOptOutStatus(Context context, boolean flag)
    {
        createOptOut(context, flag);
        askEveryone(context, flag, true);
        QCNotificationCenter.INSTANCE.postNotification("QC_OUC", Boolean.valueOf(flag));
    }

    private static final String QCMEASUREMENT_OPTOUT_STRING = "QC-OPT-OUT";
    public static final String QC_NOTIF_OPT_OUT_CHANGED = "QC_OUC";

}
