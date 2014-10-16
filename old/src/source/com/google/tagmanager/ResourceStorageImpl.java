// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.tagmanager;

import android.content.Context;
import android.content.res.AssetManager;
import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONException;

// Referenced classes of package com.google.tagmanager:
//            Log, JsonUtils, ProtoExtensionRegistry, LoadCallback, 
//            PreviewManager

class ResourceStorageImpl
    implements Container.ResourceStorage
{

    ResourceStorageImpl(Context context, String s)
    {
        mContext = context;
        mContainerId = s;
    }

    private String stringFromInputStream(InputStream inputstream)
        throws IOException
    {
        StringWriter stringwriter = new StringWriter();
        char ac[] = new char[1024];
        BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(inputstream, "UTF-8"));
        do
        {
            int i = bufferedreader.read(ac);
            if(i != -1)
                stringwriter.write(ac, 0, i);
            else
                return stringwriter.toString();
        } while(true);
    }

    public void close()
    {
        this;
        JVM INSTR monitorenter ;
        mExecutor.shutdown();
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    File getResourceFile()
    {
        String s = (new StringBuilder()).append("resource_").append(mContainerId).toString();
        return new File(mContext.getDir("google_tagmanager", 0), s);
    }

    public ResourceUtil.ExpandedResource loadExpandedResourceFromJsonAsset(String s)
    {
        ResourceUtil.ExpandedResource expandedresource;
        AssetManager assetmanager;
        expandedresource = null;
        Log.v((new StringBuilder()).append("loading default container from ").append(s).toString());
        assetmanager = mContext.getAssets();
        if(assetmanager != null) goto _L2; else goto _L1
_L1:
        Log.w("Looking for default JSON container in package, but no assets were found.");
_L3:
        return expandedresource;
_L2:
        InputStream inputstream = null;
        ResourceUtil.ExpandedResource expandedresource1;
        inputstream = assetmanager.open(s);
        expandedresource1 = JsonUtils.expandedResourceFromJsonString(stringFromInputStream(inputstream));
        expandedresource = expandedresource1;
        if(inputstream != null)
        {
            try
            {
                inputstream.close();
            }
            catch(IOException ioexception4)
            {
                return expandedresource;
            }
            return expandedresource;
        }
          goto _L3
        IOException ioexception2;
        ioexception2;
        Log.w((new StringBuilder()).append("No asset file: ").append(s).append(" found (or errors reading it).").toString());
        expandedresource = null;
        if(inputstream != null)
        {
            try
            {
                inputstream.close();
            }
            catch(IOException ioexception3)
            {
                return null;
            }
            return null;
        }
          goto _L3
        JSONException jsonexception;
        jsonexception;
        Log.w((new StringBuilder()).append("Error parsing JSON file").append(s).append(" : ").append(jsonexception).toString());
        expandedresource = null;
        if(inputstream == null) goto _L3; else goto _L4
_L4:
        try
        {
            inputstream.close();
        }
        catch(IOException ioexception1)
        {
            return null;
        }
        return null;
        Exception exception;
        exception;
        if(inputstream != null)
            try
            {
                inputstream.close();
            }
            catch(IOException ioexception) { }
        throw exception;
    }

    public com.google.analytics.containertag.proto.Serving.Resource loadResourceFromContainerAsset(String s)
    {
        InputStream inputstream;
        Log.v((new StringBuilder()).append("Loading default container from ").append(s).toString());
        AssetManager assetmanager = mContext.getAssets();
        if(assetmanager == null)
        {
            Log.e("No assets found in package");
            return null;
        }
        com.google.analytics.containertag.proto.Serving.Resource resource;
        IOException ioexception4;
        try
        {
            inputstream = assetmanager.open(s);
        }
        catch(IOException ioexception)
        {
            Log.w((new StringBuilder()).append("No asset file: ").append(s).append(" found.").toString());
            return null;
        }
        resource = com.google.analytics.containertag.proto.Serving.Resource.parseFrom(inputstream, ProtoExtensionRegistry.getRegistry());
        Log.v((new StringBuilder()).append("Parsed default container: ").append(resource).toString());
        try
        {
            inputstream.close();
        }
        // Misplaced declaration of an exception variable
        catch(IOException ioexception4)
        {
            return resource;
        }
        return resource;
        IOException ioexception2;
        ioexception2;
        Log.w((new StringBuilder()).append("Error when parsing: ").append(s).toString());
        Exception exception;
        try
        {
            inputstream.close();
        }
        catch(IOException ioexception3) { }
        return null;
        exception;
        try
        {
            inputstream.close();
        }
        catch(IOException ioexception1) { }
        throw exception;
    }

    void loadResourceFromDisk()
    {
        FileInputStream fileinputstream;
        if(mCallback == null)
            throw new IllegalStateException("callback must be set before execute");
        mCallback.startLoad();
        Log.v("Start loading resource from disk ...");
        if((PreviewManager.getInstance().getPreviewMode() == PreviewManager.PreviewMode.CONTAINER || PreviewManager.getInstance().getPreviewMode() == PreviewManager.PreviewMode.CONTAINER_DEBUG) && mContainerId.equals(PreviewManager.getInstance().getContainerId()))
        {
            mCallback.onFailure(LoadCallback.Failure.NOT_AVAILABLE);
            return;
        }
        try
        {
            fileinputstream = new FileInputStream(getResourceFile());
        }
        catch(FileNotFoundException filenotfoundexception)
        {
            Log.d("resource not on disk");
            mCallback.onFailure(LoadCallback.Failure.NOT_AVAILABLE);
            return;
        }
        mCallback.onSuccess(com.google.tagmanager.proto.Resource.ResourceWithMetadata.parseFrom(fileinputstream, ProtoExtensionRegistry.getRegistry()));
        try
        {
            fileinputstream.close();
        }
        catch(IOException ioexception3)
        {
            Log.w("error closing stream for reading resource from disk");
        }
_L2:
        Log.v("Load resource from disk finished.");
        return;
        IOException ioexception1;
        ioexception1;
        Log.w("error reading resource from disk");
        mCallback.onFailure(LoadCallback.Failure.IO_ERROR);
        try
        {
            fileinputstream.close();
        }
        catch(IOException ioexception2)
        {
            Log.w("error closing stream for reading resource from disk");
        }
        if(true) goto _L2; else goto _L1
_L1:
        Exception exception;
        exception;
        try
        {
            fileinputstream.close();
        }
        catch(IOException ioexception)
        {
            Log.w("error closing stream for reading resource from disk");
        }
        throw exception;
    }

    public void loadResourceFromDiskInBackground()
    {
        mExecutor.execute(new Runnable() {

            public void run()
            {
                loadResourceFromDisk();
            }

            final ResourceStorageImpl this$0;

            
            {
                this$0 = ResourceStorageImpl.this;
                super();
            }
        }
);
    }

    boolean saveResourceToDisk(com.google.tagmanager.proto.Resource.ResourceWithMetadata resourcewithmetadata)
    {
        File file;
        FileOutputStream fileoutputstream;
        file = getResourceFile();
        try
        {
            fileoutputstream = new FileOutputStream(file);
        }
        catch(FileNotFoundException filenotfoundexception)
        {
            Log.e("Error opening resource file for writing");
            return false;
        }
        resourcewithmetadata.writeTo(fileoutputstream);
        try
        {
            fileoutputstream.close();
        }
        catch(IOException ioexception3)
        {
            Log.w("error closing stream for writing resource to disk");
        }
        return true;
        IOException ioexception1;
        ioexception1;
        Log.w("Error writing resource to disk. Removing resource from disk.");
        file.delete();
        try
        {
            fileoutputstream.close();
        }
        catch(IOException ioexception2)
        {
            Log.w("error closing stream for writing resource to disk");
        }
        return false;
        Exception exception;
        exception;
        try
        {
            fileoutputstream.close();
        }
        catch(IOException ioexception)
        {
            Log.w("error closing stream for writing resource to disk");
        }
        throw exception;
    }

    public void saveResourceToDiskInBackground(final com.google.tagmanager.proto.Resource.ResourceWithMetadata resource)
    {
        mExecutor.execute(new Runnable() {

            public void run()
            {
                saveResourceToDisk(resource);
            }

            final ResourceStorageImpl this$0;
            final com.google.tagmanager.proto.Resource.ResourceWithMetadata val$resource;

            
            {
                this$0 = ResourceStorageImpl.this;
                resource = resourcewithmetadata;
                super();
            }
        }
);
    }

    public void setLoadCallback(LoadCallback loadcallback)
    {
        mCallback = loadcallback;
    }

    private static final String SAVED_RESOURCE_FILENAME_PREFIX = "resource_";
    private static final String SAVED_RESOURCE_SUB_DIR = "google_tagmanager";
    private LoadCallback mCallback;
    private final String mContainerId;
    private final Context mContext;
    private final ExecutorService mExecutor = Executors.newSingleThreadExecutor();
}
