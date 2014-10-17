// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.tagmanager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import java.io.FileNotFoundException;
import java.io.IOException;

// Referenced classes of package com.google.tagmanager:
//            NetworkClientFactory, LoadCallback, Log, NetworkClient, 
//            ProtoExtensionRegistry, CtfeHost, PreviewManager

class ResourceLoader
    implements Runnable
{

    public ResourceLoader(Context context, String s, CtfeHost ctfehost)
    {
        this(context, s, new NetworkClientFactory(), ctfehost);
    }

    ResourceLoader(Context context, String s, NetworkClientFactory networkclientfactory, CtfeHost ctfehost)
    {
        mContext = context;
        mClientFactory = networkclientfactory;
        mContainerId = s;
        mCtfeHost = ctfehost;
        mDefaultCtfeUrlPathAndQuery = (new StringBuilder()).append("/r?id=").append(s).toString();
        mCtfeUrlPathAndQuery = mDefaultCtfeUrlPathAndQuery;
        mPreviousVersion = null;
    }

    private void loadResource()
    {
        String s;
        NetworkClient networkclient;
        if(!okToLoad())
        {
            mCallback.onFailure(LoadCallback.Failure.NOT_AVAILABLE);
            return;
        }
        Log.v("Start loading resource from network ...");
        s = getCtfeUrl();
        networkclient = mClientFactory.createNetworkClient();
        java.io.InputStream inputstream = networkclient.getInputStream(s);
        LoadCallback loadcallback;
        com.google.analytics.containertag.proto.Serving.Resource resource;
        com.google.analytics.containertag.proto.Serving.OptionalResource optionalresource = com.google.analytics.containertag.proto.Serving.OptionalResource.parseFrom(inputstream, ProtoExtensionRegistry.getRegistry());
        Log.v((new StringBuilder()).append("Successfully loaded resource: ").append(optionalresource).toString());
        if(!optionalresource.hasResource())
            Log.v((new StringBuilder()).append("No change for container: ").append(mContainerId).toString());
        loadcallback = mCallback;
        if(!optionalresource.hasResource())
            break MISSING_BLOCK_LABEL_273;
        resource = optionalresource.getResource();
_L1:
        loadcallback.onSuccess(resource);
        networkclient.close();
        Log.v("Load resource from network finished.");
        return;
        FileNotFoundException filenotfoundexception;
        filenotfoundexception;
        Log.w((new StringBuilder()).append("No data is retrieved from the given url: ").append(s).append(". Make sure container_id: ").append(mContainerId).append(" is correct.").toString());
        mCallback.onFailure(LoadCallback.Failure.SERVER_ERROR);
        networkclient.close();
        return;
        IOException ioexception;
        ioexception;
        Log.w((new StringBuilder()).append("Error when loading resources from url: ").append(s).append(" ").append(ioexception.getMessage()).toString(), ioexception);
        mCallback.onFailure(LoadCallback.Failure.IO_ERROR);
        networkclient.close();
        return;
        resource = null;
          goto _L1
        IOException ioexception1;
        ioexception1;
        Log.w((new StringBuilder()).append("Error when parsing downloaded resources from url: ").append(s).append(" ").append(ioexception1.getMessage()).toString(), ioexception1);
        mCallback.onFailure(LoadCallback.Failure.SERVER_ERROR);
        networkclient.close();
        return;
        Exception exception;
        exception;
        networkclient.close();
        throw exception;
    }

    private boolean okToLoad()
    {
        NetworkInfo networkinfo = ((ConnectivityManager)mContext.getSystemService("connectivity")).getActiveNetworkInfo();
        if(networkinfo == null || !networkinfo.isConnected())
        {
            Log.v("...no network connectivity");
            return false;
        } else
        {
            return true;
        }
    }

    String getCtfeUrl()
    {
        String s = (new StringBuilder()).append(mCtfeHost.getCtfeServerAddress()).append(mCtfeUrlPathAndQuery).append("&v=a50788154").toString();
        if(mPreviousVersion != null && !mPreviousVersion.trim().equals(""))
            s = (new StringBuilder()).append(s).append("&pv=").append(mPreviousVersion).toString();
        if(PreviewManager.getInstance().getPreviewMode().equals(PreviewManager.PreviewMode.CONTAINER_DEBUG))
            s = (new StringBuilder()).append(s).append("&gtm_debug=x").toString();
        return s;
    }

    public void run()
    {
        if(mCallback == null)
        {
            throw new IllegalStateException("callback must be set before execute");
        } else
        {
            mCallback.startLoad();
            loadResource();
            return;
        }
    }

    void setCtfeURLPathAndQuery(String s)
    {
        if(s == null)
        {
            mCtfeUrlPathAndQuery = mDefaultCtfeUrlPathAndQuery;
            return;
        } else
        {
            Log.d((new StringBuilder()).append("Setting CTFE URL path: ").append(s).toString());
            mCtfeUrlPathAndQuery = s;
            return;
        }
    }

    void setLoadCallback(LoadCallback loadcallback)
    {
        mCallback = loadcallback;
    }

    void setPreviousVersion(String s)
    {
        Log.d((new StringBuilder()).append("Setting previous container version: ").append(s).toString());
        mPreviousVersion = s;
    }

    private static final String CTFE_URL_PREFIX = "/r?id=";
    private static final String CTFE_URL_SUFFIX = "&v=a50788154";
    private static final String PREVIOUS_CONTAINER_VERSION_QUERY_NAME = "pv";
    static final String SDK_VERSION = "a50788154";
    private LoadCallback mCallback;
    private final NetworkClientFactory mClientFactory;
    private final String mContainerId;
    private final Context mContext;
    private volatile CtfeHost mCtfeHost;
    private volatile String mCtfeUrlPathAndQuery;
    private final String mDefaultCtfeUrlPathAndQuery;
    private volatile String mPreviousVersion;
}
