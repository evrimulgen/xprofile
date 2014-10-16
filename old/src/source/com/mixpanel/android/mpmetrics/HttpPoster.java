// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.mixpanel.android.mpmetrics;

import android.util.Log;
import com.mixpanel.android.util.Base64Coder;
import com.mixpanel.android.util.StringUtils;
import com.newrelic.agent.android.instrumentation.HttpInstrumentation;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

class HttpPoster
{
    public static final class PostResult extends Enum
    {

        public static PostResult valueOf(String s)
        {
            return (PostResult)Enum.valueOf(com/mixpanel/android/mpmetrics/HttpPoster$PostResult, s);
        }

        public static PostResult[] values()
        {
            return (PostResult[])$VALUES.clone();
        }

        private static final PostResult $VALUES[];
        public static final PostResult FAILED_RECOVERABLE;
        public static final PostResult FAILED_UNRECOVERABLE;
        public static final PostResult SUCCEEDED;

        static 
        {
            SUCCEEDED = new PostResult("SUCCEEDED", 0);
            FAILED_RECOVERABLE = new PostResult("FAILED_RECOVERABLE", 1);
            FAILED_UNRECOVERABLE = new PostResult("FAILED_UNRECOVERABLE", 2);
            PostResult apostresult[] = new PostResult[3];
            apostresult[0] = SUCCEEDED;
            apostresult[1] = FAILED_RECOVERABLE;
            apostresult[2] = FAILED_UNRECOVERABLE;
            $VALUES = apostresult;
        }

        private PostResult(String s, int i)
        {
            super(s, i);
        }
    }


    public HttpPoster(String s, String s1)
    {
        mDefaultHost = s;
        mFallbackHost = s1;
    }

    private PostResult postHttpRequest(String s, List list)
    {
        PostResult postresult;
        DefaultHttpClient defaulthttpclient;
        HttpPost httppost;
        postresult = PostResult.FAILED_UNRECOVERABLE;
        defaulthttpclient = new DefaultHttpClient();
        httppost = new HttpPost(s);
        httppost.setEntity(new UrlEncodedFormEntity(list));
        if(defaulthttpclient instanceof HttpClient) goto _L2; else goto _L1
_L1:
        HttpResponse httpresponse1 = defaulthttpclient.execute(httppost);
_L3:
        HttpEntity httpentity = httpresponse1.getEntity();
        if(httpentity == null)
            break MISSING_BLOCK_LABEL_142;
        if(StringUtils.inputStreamToString(httpentity.getContent()).equals("1\n"))
            return PostResult.SUCCEEDED;
        break MISSING_BLOCK_LABEL_142;
_L2:
        HttpResponse httpresponse = HttpInstrumentation.execute((HttpClient)defaulthttpclient, httppost);
        httpresponse1 = httpresponse;
          goto _L3
        IOException ioexception;
        ioexception;
        Log.i("MixpanelAPI", "Cannot post message to Mixpanel Servers (May Retry)", ioexception);
        return PostResult.FAILED_RECOVERABLE;
        OutOfMemoryError outofmemoryerror;
        outofmemoryerror;
        Log.e("MixpanelAPI", "Cannot post message to Mixpanel Servers, will not retry.", outofmemoryerror);
        postresult = PostResult.FAILED_UNRECOVERABLE;
        return postresult;
    }

    public PostResult postData(String s, String s1)
    {
        String s2 = Base64Coder.encodeString(s);
        ArrayList arraylist = new ArrayList(1);
        arraylist.add(new BasicNameValuePair("data", s2));
        PostResult postresult = postHttpRequest((new StringBuilder()).append(mDefaultHost).append(s1).toString(), arraylist);
        if(postresult == PostResult.FAILED_RECOVERABLE && mFallbackHost != null)
            postresult = postHttpRequest((new StringBuilder()).append(mFallbackHost).append(s1).toString(), arraylist);
        return postresult;
    }

    private static final String LOGTAG = "MixpanelAPI";
    private final String mDefaultHost;
    private final String mFallbackHost;
}
