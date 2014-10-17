// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.quantcast.measurement.service;

import com.newrelic.agent.android.instrumentation.HttpInstrumentation;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.util.Collection;
import java.util.Iterator;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.json.*;

// Referenced classes of package com.quantcast.measurement.service:
//            QCUtility, QCEvent, QCLog, QCMeasurement

class QCDataUploader
{

    QCDataUploader()
    {
    }

    private boolean isSuccessful(int i)
    {
        return i >= 200 && i <= 299;
    }

    String synchronousUploadEvents(Collection collection)
    {
        if(collection != null && !collection.isEmpty()) goto _L2; else goto _L1
_L1:
        String s = null;
_L8:
        return s;
_L2:
        JSONObject jsonobject;
        JSONArray jsonarray;
        s = QCUtility.generateUniqueId();
        jsonobject = new JSONObject();
        try
        {
            jsonobject.put("uplid", s);
            jsonobject.put("qcv", "1_1_0");
            jsonarray = new JSONArray();
            for(Iterator iterator = collection.iterator(); iterator.hasNext(); jsonarray.put(new JSONObject(((QCEvent)iterator.next()).getParameters())));
        }
        catch(JSONException jsonexception)
        {
            QCLog.e(TAG, "Error while encoding json.");
            return null;
        }
        jsonobject.put("events", jsonarray);
        int i;
        String s1;
        DefaultHttpClient defaulthttpclient;
        BasicHttpContext basichttpcontext;
        i = 400;
        s1 = QCUtility.addScheme("m.quantcount.com/mobile");
        defaulthttpclient = new DefaultHttpClient();
        basichttpcontext = new BasicHttpContext();
        HttpPost httppost;
        httppost = new HttpPost(s1);
        httppost.setHeader("Content-Type", "application/json");
        if(jsonobject instanceof JSONObject) goto _L4; else goto _L3
_L3:
        String s2 = jsonobject.toString();
_L9:
        httppost.setEntity(new StringEntity(s2, "ASCII"));
        BasicHttpParams basichttpparams = new BasicHttpParams();
        basichttpparams.setBooleanParameter("http.protocol.expect-continue", false);
        httppost.setParams(basichttpparams);
        if(defaulthttpclient instanceof HttpClient) goto _L6; else goto _L5
_L5:
        HttpResponse httpresponse1 = defaulthttpclient.execute(httppost, basichttpcontext);
_L10:
        int j = httpresponse1.getStatusLine().getStatusCode();
        i = j;
_L11:
        if(isSuccessful(i)) goto _L8; else goto _L7
_L7:
        QCLog.e(TAG, (new StringBuilder()).append("Events not sent to server. Response code: ").append(i).toString());
        QCMeasurement.INSTANCE.logSDKError("json-upload-failure", (new StringBuilder()).append("Bad response from server. Response code: ").append(i).toString(), null);
        return null;
_L4:
        s2 = JSONObjectInstrumentation.toString((JSONObject)jsonobject);
          goto _L9
_L6:
        HttpResponse httpresponse = HttpInstrumentation.execute((HttpClient)defaulthttpclient, httppost, basichttpcontext);
        httpresponse1 = httpresponse;
          goto _L10
        Exception exception;
        exception;
        QCLog.e(TAG, "Could not upload events", exception);
        QCMeasurement.INSTANCE.logSDKError("json-upload-failure", exception.getMessage(), null);
          goto _L11
    }

    static final String QC_EVENTS_KEY = "events";
    static final String QC_QCV_KEY = "qcv";
    static final String QC_UPLOAD_ID_KEY = "uplid";
    private static final QCLog.Tag TAG = new QCLog.Tag(com/quantcast/measurement/service/QCDataUploader);
    private static final String UPLOAD_URL_WITHOUT_SCHEME = "m.quantcount.com/mobile";

}
