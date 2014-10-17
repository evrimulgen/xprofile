// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.bugsnag.android;

import android.app.Activity;
import android.content.Context;
import com.bugsnag.Configuration;
import com.bugsnag.Error;
import com.bugsnag.MetaData;
import com.bugsnag.Metrics;
import com.bugsnag.Notification;
import com.bugsnag.android.utils.Async;
import com.bugsnag.http.BadResponseException;
import com.bugsnag.http.NetworkException;
import java.io.File;
import java.io.IOException;

// Referenced classes of package com.bugsnag.android:
//            Logger, Diagnostics, ActivityStack

public class Client extends com.bugsnag.Client
{

    public Client(Context context, String s, boolean flag)
    {
        super(s);
        logger = new Logger();
        setLogger(logger);
        applicationContext = context.getApplicationContext();
        diagnostics = new Diagnostics(config, applicationContext);
        cachePath = prepareCachePath();
        setNotifierName("Android Bugsnag Notifier");
        setNotifierVersion("2.1.0");
        if(flag)
            Async.safeAsync(new Runnable() {

                public final void run()
                {
                    try
                    {
                        createMetrics().deliver();
                        return;
                    }
                    catch(NetworkException networkexception)
                    {
                        logger.info("Could not send metrics to Bugsnag");
                        return;
                    }
                    catch(BadResponseException badresponseexception)
                    {
                        logger.warn(badresponseexception.getMessage());
                    }
                }

                private Client this$0;

            
            {
                this$0 = Client.this;
                super();
            }
            }
);
        if(cachePath != null)
            Async.safeAsync(new Runnable() {

                public final void run()
                {
                    File file = new File(cachePath);
                    if(!file.exists() || !file.isDirectory()) goto _L2; else goto _L1
_L1:
                    Notification notification;
                    File afile[];
                    int i;
                    int j;
                    notification = null;
                    afile = file.listFiles();
                    i = afile.length;
                    j = 0;
_L4:
                    if(j >= i) goto _L2; else goto _L3
_L3:
                    File file1;
                    file1 = afile[j];
                    if(notification != null)
                        break MISSING_BLOCK_LABEL_202;
                    Notification notification2 = createNotification();
                    Notification notification1 = notification2;
_L8:
                    notification1.setError(file1);
                    notification1.deliver();
                    logger.debug((new StringBuilder()).append("Deleting sent error file ").append(file1.getName()).toString());
                    file1.delete();
_L5:
                    j++;
                    notification = notification1;
                      goto _L4
                    NetworkException networkexception;
                    networkexception;
_L7:
                    logger.warn("Could not send error(s) to Bugsnag, will try again later", networkexception);
                      goto _L5
                    Exception exception;
                    exception;
_L6:
                    logger.warn("Problem sending unsent error from disk", exception);
                    file1.delete();
                      goto _L5
_L2:
                    return;
                    Exception exception1;
                    exception1;
                    notification1 = notification;
                    exception = exception1;
                      goto _L6
                    NetworkException networkexception1;
                    networkexception1;
                    notification1 = notification;
                    networkexception = networkexception1;
                      goto _L7
                    notification1 = notification;
                      goto _L8
                }

                private Client this$0;

            
            {
                this$0 = Client.this;
                super();
            }
            }
);
        logger.info("Bugsnag is loaded and ready to handle exceptions");
    }

    private String prepareCachePath()
    {
        String s;
label0:
        {
            try
            {
                s = (new StringBuilder()).append(applicationContext.getCacheDir().getAbsolutePath()).append("/bugsnag-errors/").toString();
                File file = new File(s);
                file.mkdirs();
                if(file.exists())
                    break label0;
                logger.warn("Could not prepare cache directory");
            }
            catch(Exception exception)
            {
                logger.warn("Could not prepare cache directory", exception);
                return null;
            }
            return null;
        }
        return s;
    }

    public void notify(Throwable throwable, String s, MetaData metadata)
    {
        if(!config.shouldNotify())
            return;
        try
        {
            if(!config.shouldIgnore(throwable.getClass().getName()))
            {
                Async.safeAsync(new Runnable() {

                    public final void run()
                    {
                        try
                        {
                            createNotification(error).deliver();
                            return;
                        }
                        catch(NetworkException networkexception)
                        {
                            logger.info("Could not send error(s) to Bugsnag, saving to disk to send later");
                            logger.info(networkexception.toString());
                            Client.access$100(Client.this, error);
                            return;
                        }
                    }

                    private Client this$0;
                    private Error val$error;

            
            {
                this$0 = Client.this;
                error = error1;
                super();
            }
                }
);
                return;
            }
        }
        catch(Exception exception)
        {
            logger.warn("Error notifying Bugsnag", exception);
        }
        return;
    }

    public void setContext(Activity activity)
    {
        setContext(ActivityStack.getContextName(activity));
    }

    public void setLogger(Logger logger1)
    {
        super.setLogger(logger1);
        Async.logger = logger1;
    }

    private Context applicationContext;
    private String cachePath;
    private Logger logger;



/*
    static void access$100(Client client, Error error)
    {
        if(client.cachePath == null || error == null)
            return;
        try
        {
            Object aobj[] = new Object[2];
            aobj[0] = client.cachePath;
            aobj[1] = Long.valueOf(System.currentTimeMillis());
            error.writeToFile(String.format("%s%d.json", aobj));
            return;
        }
        catch(IOException ioexception)
        {
            client.logger.warn("Unable to save bugsnag error", ioexception);
        }
        return;
    }

*/

}
