// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ly.count.android.api;

import android.content.Context;
import android.util.Log;
import com.newrelic.agent.android.instrumentation.HttpInstrumentation;
import java.io.InputStream;
import java.net.URI;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.OpenUDID.OpenUDID_manager;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.client.DefaultHttpClient;

// Referenced classes of package ly.count.android.api:
//            DeviceInfo

class ConnectionQueue
{

    ConnectionQueue()
    {
        queue_ = new ConcurrentLinkedQueue();
        thread_ = null;
    }

    private void tick()
    {
        while(thread_ != null && thread_.isAlive() || queue_.isEmpty()) 
            return;
        thread_ = new Thread() {

            public void run()
            {
_L5:
                String s = (String)queue_.peek();
                if(s != null) goto _L2; else goto _L1
_L1:
                return;
_L2:
                if(s.indexOf("REPLACE_UDID") == -1)
                    break MISSING_BLOCK_LABEL_45;
                if(!OpenUDID_manager.isInitialized()) goto _L1; else goto _L3
_L3:
                s = s.replaceFirst("REPLACE_UDID", OpenUDID_manager.getOpenUDID());
                DefaultHttpClient defaulthttpclient;
                HttpGet httpget;
                HttpResponse httpresponse1;
                defaulthttpclient = new DefaultHttpClient();
                httpget = new HttpGet(new URI((new StringBuilder(String.valueOf(serverURL_))).append("/i?").append(s).toString()));
                if(defaulthttpclient instanceof HttpClient)
                    break; /* Loop/switch isn't completed */
                httpresponse1 = defaulthttpclient.execute(httpget);
_L6:
                for(InputStream inputstream = httpresponse1.getEntity().getContent(); inputstream.read() != -1;);
                defaulthttpclient.getConnectionManager().shutdown();
                Log.d("Countly", (new StringBuilder("ok ->")).append(s).toString());
                queue_.poll();
                Exception exception;
                if(true) goto _L5; else goto _L4
_L4:
                HttpResponse httpresponse;
                try
                {
                    httpresponse = HttpInstrumentation.execute((HttpClient)defaulthttpclient, httpget);
                }
                // Misplaced declaration of an exception variable
                catch(Exception exception)
                {
                    Log.d("Countly", exception.toString());
                    Log.d("Countly", (new StringBuilder("error ->")).append(s).toString());
                    return;
                }
                httpresponse1 = httpresponse;
                  goto _L6
                if(true) goto _L5; else goto _L7
_L7:
            }

            final ConnectionQueue this$0;

            
            {
                this$0 = ConnectionQueue.this;
                super();
            }
        }
;
        thread_.start();
    }

    public void beginSession()
    {
        String s = (new StringBuilder(String.valueOf((new StringBuilder(String.valueOf((new StringBuilder(String.valueOf((new StringBuilder(String.valueOf((new StringBuilder(String.valueOf((new StringBuilder("app_key=")).append(appKey_).toString()))).append("&device_id=").append(DeviceInfo.getUDID()).toString()))).append("&timestamp=").append((long)((double)System.currentTimeMillis() / 1000D)).toString()))).append("&sdk_version=1.0").toString()))).append("&begin_session=1").toString()))).append("&metrics=").append(DeviceInfo.getMetrics(context_)).toString();
        queue_.offer(s);
        tick();
    }

    public void endSession(int i)
    {
        String s = (new StringBuilder(String.valueOf((new StringBuilder(String.valueOf((new StringBuilder(String.valueOf((new StringBuilder(String.valueOf((new StringBuilder("app_key=")).append(appKey_).toString()))).append("&device_id=").append(DeviceInfo.getUDID()).toString()))).append("&timestamp=").append((long)((double)System.currentTimeMillis() / 1000D)).toString()))).append("&end_session=1").toString()))).append("&session_duration=").append(i).toString();
        queue_.offer(s);
        tick();
    }

    public void recordEvents(String s)
    {
        String s1 = (new StringBuilder(String.valueOf((new StringBuilder(String.valueOf((new StringBuilder(String.valueOf((new StringBuilder("app_key=")).append(appKey_).toString()))).append("&device_id=").append(DeviceInfo.getUDID()).toString()))).append("&timestamp=").append((long)((double)System.currentTimeMillis() / 1000D)).toString()))).append("&events=").append(s).toString();
        queue_.offer(s1);
        tick();
    }

    public void setAppKey(String s)
    {
        appKey_ = s;
    }

    public void setContext(Context context)
    {
        context_ = context;
    }

    public void setServerURL(String s)
    {
        serverURL_ = s;
    }

    public void updateSession(int i)
    {
        String s = (new StringBuilder(String.valueOf((new StringBuilder(String.valueOf((new StringBuilder(String.valueOf((new StringBuilder("app_key=")).append(appKey_).toString()))).append("&device_id=").append(DeviceInfo.getUDID()).toString()))).append("&timestamp=").append((long)((double)System.currentTimeMillis() / 1000D)).toString()))).append("&session_duration=").append(i).toString();
        queue_.offer(s);
        tick();
    }

    private String appKey_;
    private Context context_;
    private ConcurrentLinkedQueue queue_;
    private String serverURL_;
    private Thread thread_;


}
