// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.tagmanager;

import android.content.Context;
import java.util.HashMap;
import java.util.Map;

// Referenced classes of package com.google.tagmanager:
//            ResourceStorageImpl, CtfeHost, Log, ResourceUtil, 
//            Runtime, TagManager, PreviewManager, NoopEventInfoDistributor, 
//            Types, ObjectAndStatic, ResourceLoaderSchedulerImpl, Clock, 
//            LoadCallback, EventInfoDistributor

public class Container
{
    public static interface Callback
    {

        public abstract void containerRefreshBegin(Container container, RefreshType refreshtype);

        public abstract void containerRefreshFailure(Container container, RefreshType refreshtype, RefreshFailure refreshfailure);

        public abstract void containerRefreshSuccess(Container container, RefreshType refreshtype);
    }

    public static interface FunctionCallMacroHandler
    {

        public abstract Object getValue(String s, Map map);
    }

    private class FunctionCallMacroHandlerAdapter
        implements CustomFunctionCall.CustomEvaluator
    {

        public Object evaluate(String s, Map map)
        {
            FunctionCallMacroHandler functioncallmacrohandler = getFunctionCallMacroHandler(s);
            if(functioncallmacrohandler == null)
                return null;
            else
                return functioncallmacrohandler.getValue(s, map);
        }

        final Container this$0;

        private FunctionCallMacroHandlerAdapter()
        {
            this$0 = Container.this;
            super();
        }

    }

    public static interface FunctionCallTagHandler
    {

        public abstract void execute(String s, Map map);
    }

    private class FunctionCallTagHandlerAdapter
        implements CustomFunctionCall.CustomEvaluator
    {

        public Object evaluate(String s, Map map)
        {
            getFunctionCallTagHandler(s).execute(s, map);
            return Types.getDefaultString();
        }

        final Container this$0;

        private FunctionCallTagHandlerAdapter()
        {
            this$0 = Container.this;
            super();
        }

    }

    public static final class RefreshFailure extends Enum
    {

        public static RefreshFailure valueOf(String s)
        {
            return (RefreshFailure)Enum.valueOf(com/google/tagmanager/Container$RefreshFailure, s);
        }

        public static RefreshFailure[] values()
        {
            return (RefreshFailure[])$VALUES.clone();
        }

        private static final RefreshFailure $VALUES[];
        public static final RefreshFailure IO_ERROR;
        public static final RefreshFailure NETWORK_ERROR;
        public static final RefreshFailure NO_NETWORK;
        public static final RefreshFailure NO_SAVED_CONTAINER;
        public static final RefreshFailure SERVER_ERROR;
        public static final RefreshFailure UNKNOWN_ERROR;

        static 
        {
            NO_SAVED_CONTAINER = new RefreshFailure("NO_SAVED_CONTAINER", 0);
            IO_ERROR = new RefreshFailure("IO_ERROR", 1);
            NO_NETWORK = new RefreshFailure("NO_NETWORK", 2);
            NETWORK_ERROR = new RefreshFailure("NETWORK_ERROR", 3);
            SERVER_ERROR = new RefreshFailure("SERVER_ERROR", 4);
            UNKNOWN_ERROR = new RefreshFailure("UNKNOWN_ERROR", 5);
            RefreshFailure arefreshfailure[] = new RefreshFailure[6];
            arefreshfailure[0] = NO_SAVED_CONTAINER;
            arefreshfailure[1] = IO_ERROR;
            arefreshfailure[2] = NO_NETWORK;
            arefreshfailure[3] = NETWORK_ERROR;
            arefreshfailure[4] = SERVER_ERROR;
            arefreshfailure[5] = UNKNOWN_ERROR;
            $VALUES = arefreshfailure;
        }

        private RefreshFailure(String s, int i)
        {
            super(s, i);
        }
    }

    public static final class RefreshType extends Enum
    {

        public static RefreshType valueOf(String s)
        {
            return (RefreshType)Enum.valueOf(com/google/tagmanager/Container$RefreshType, s);
        }

        public static RefreshType[] values()
        {
            return (RefreshType[])$VALUES.clone();
        }

        private static final RefreshType $VALUES[];
        public static final RefreshType NETWORK;
        public static final RefreshType SAVED;

        static 
        {
            SAVED = new RefreshType("SAVED", 0);
            NETWORK = new RefreshType("NETWORK", 1);
            RefreshType arefreshtype[] = new RefreshType[2];
            arefreshtype[0] = SAVED;
            arefreshtype[1] = NETWORK;
            $VALUES = arefreshtype;
        }

        private RefreshType(String s, int i)
        {
            super(s, i);
        }
    }

    static interface ResourceLoaderScheduler
    {

        public abstract void close();

        public abstract void loadAfterDelay(long l, String s);

        public abstract void setCtfeURLPathAndQuery(String s);

        public abstract void setLoadCallback(LoadCallback loadcallback);
    }

    static interface ResourceStorage
    {

        public abstract void close();

        public abstract ResourceUtil.ExpandedResource loadExpandedResourceFromJsonAsset(String s);

        public abstract com.google.analytics.containertag.proto.Serving.Resource loadResourceFromContainerAsset(String s);

        public abstract void loadResourceFromDiskInBackground();

        public abstract void saveResourceToDiskInBackground(com.google.tagmanager.proto.Resource.ResourceWithMetadata resourcewithmetadata);

        public abstract void setLoadCallback(LoadCallback loadcallback);
    }


    Container(Context context, String s, TagManager tagmanager)
    {
        this(context, s, tagmanager, ((ResourceStorage) (new ResourceStorageImpl(context, s))));
    }

    Container(Context context, String s, TagManager tagmanager, ResourceStorage resourcestorage)
    {
        mResourceVersion = "";
        mResourceFormatVersion = 0;
        mCtfeHost = new CtfeHost();
        mContext = context;
        mContainerId = s;
        mTagManager = tagmanager;
        mResourceStorage = resourcestorage;
        mNumTokens = 30;
        mFunctionCallMacroHandlers = new HashMap();
        mFunctionCallTagHandlers = new HashMap();
        createInitialContainer();
    }

    private void callRefreshBegin(RefreshType refreshtype)
    {
        this;
        JVM INSTR monitorenter ;
        if(mUserCallback != null)
            mUserCallback.containerRefreshBegin(this, refreshtype);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private void callRefreshFailure(RefreshType refreshtype, RefreshFailure refreshfailure)
    {
        this;
        JVM INSTR monitorenter ;
        if(mUserCallback != null)
            mUserCallback.containerRefreshFailure(this, refreshtype, refreshfailure);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private void callRefreshSuccess(RefreshType refreshtype)
    {
        this;
        JVM INSTR monitorenter ;
        Log.v((new StringBuilder()).append("calling containerRefreshSuccess(").append(refreshtype).append("): mUserCallback = ").append(mUserCallback).toString());
        if(mUserCallback != null)
            mUserCallback.containerRefreshSuccess(this, refreshtype);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private void createInitialContainer()
    {
        String s = (new StringBuilder()).append("tagmanager/").append(mContainerId).toString();
        com.google.analytics.containertag.proto.Serving.Resource resource = mResourceStorage.loadResourceFromContainerAsset(s);
        if(resource != null)
        {
            initEvaluators(resource, true);
            return;
        }
        ResourceUtil.ExpandedResource expandedresource = mResourceStorage.loadExpandedResourceFromJsonAsset((new StringBuilder()).append(s).append(".json").toString());
        if(expandedresource == null)
        {
            Log.w("No default container found; creating an empty container.");
            expandedresource = ResourceUtil.ExpandedResource.newBuilder().build();
        }
        initEvaluatorsWithExpandedResource(expandedresource);
    }

    private Runtime getRuntime()
    {
        this;
        JVM INSTR monitorenter ;
        Runtime runtime = mRuntime;
        this;
        JVM INSTR monitorexit ;
        return runtime;
        Exception exception;
        exception;
        throw exception;
    }

    private void initEvaluators(com.google.analytics.containertag.proto.Serving.Resource resource, boolean flag)
    {
        ResourceUtil.ExpandedResource expandedresource;
        try
        {
            expandedresource = ResourceUtil.getExpandedResource(resource);
        }
        catch(ResourceUtil.InvalidResourceException invalidresourceexception)
        {
            Log.e((new StringBuilder()).append("Not loading resource: ").append(resource).append(" because it is invalid: ").append(invalidresourceexception.toString()).toString());
            return;
        }
        if(!flag)
            mLastLoadedResource = resource;
        initEvaluatorsWithExpandedResource(expandedresource);
    }

    private void initEvaluatorsWithExpandedResource(ResourceUtil.ExpandedResource expandedresource)
    {
        mResourceVersion = expandedresource.getVersion();
        mResourceFormatVersion = expandedresource.getResourceFormatVersion();
        EventInfoDistributor eventinfodistributor = createEventInfoDistributor(mResourceVersion);
        setRuntime(new Runtime(mContext, expandedresource, mTagManager.getDataLayer(), new FunctionCallMacroHandlerAdapter(), new FunctionCallTagHandlerAdapter(), eventinfodistributor));
    }

    private boolean isContainerPreview()
    {
        PreviewManager previewmanager = PreviewManager.getInstance();
        return (previewmanager.getPreviewMode() == PreviewManager.PreviewMode.CONTAINER || previewmanager.getPreviewMode() == PreviewManager.PreviewMode.CONTAINER_DEBUG) && mContainerId.equals(previewmanager.getContainerId());
    }

    private boolean isDefaultContainerRefreshMode()
    {
        return mTagManager.getRefreshMode() == TagManager.RefreshMode.DEFAULT_CONTAINER;
    }

    private void saveResourceToDisk(com.google.analytics.containertag.proto.Serving.Resource resource)
    {
        this;
        JVM INSTR monitorenter ;
        if(mResourceStorage != null)
            mResourceStorage.saveResourceToDiskInBackground(com.google.tagmanager.proto.Resource.ResourceWithMetadata.newBuilder().setTimeStamp(getLastRefreshTime()).setResource(resource).build());
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private void setRuntime(Runtime runtime)
    {
        this;
        JVM INSTR monitorenter ;
        mRuntime = runtime;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private boolean useAvailableToken(long l)
    {
        if(mLastRefreshMethodCalledTime == 0L)
        {
            mNumTokens = -1 + mNumTokens;
            return true;
        }
        long l1 = l - mLastRefreshMethodCalledTime;
        if(l1 < 5000L)
            return false;
        if(mNumTokens < 30)
            mNumTokens = Math.min(30, (int)Math.floor(l1 / 0xdbba0L) + mNumTokens);
        if(mNumTokens > 0)
        {
            mNumTokens = -1 + mNumTokens;
            return true;
        } else
        {
            return false;
        }
    }

    public void close()
    {
        this;
        JVM INSTR monitorenter ;
        if(mNetworkLoadScheduler != null)
            mNetworkLoadScheduler.close();
        mNetworkLoadScheduler = null;
        if(mResourceStorage != null)
            mResourceStorage.close();
        mResourceStorage = null;
        mUserCallback = null;
        mTagManager.removeContainer(mContainerId);
_L1:
        mRuntime = null;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception1;
        exception1;
        Log.e((new StringBuilder()).append("Calling close() threw an exception: ").append(exception1.getMessage()).toString());
          goto _L1
        Exception exception;
        exception;
        throw exception;
    }

    EventInfoDistributor createEventInfoDistributor(String s)
    {
        if(!PreviewManager.getInstance().getPreviewMode().equals(PreviewManager.PreviewMode.CONTAINER_DEBUG));
        return new NoopEventInfoDistributor();
    }

    void evaluateTags(String s)
    {
        getRuntime().evaluateTags(s);
    }

    public boolean getBoolean(String s)
    {
        Runtime runtime = getRuntime();
        if(runtime == null)
        {
            Log.e("getBoolean called for closed container.");
            return Types.getDefaultBoolean().booleanValue();
        }
        boolean flag;
        try
        {
            flag = Types.valueToBoolean((com.google.analytics.midtier.proto.containertag.TypeSystem.Value)runtime.evaluateMacroReference(s).getObject()).booleanValue();
        }
        catch(Exception exception)
        {
            Log.e((new StringBuilder()).append("Calling getBoolean() threw an exception: ").append(exception.getMessage()).append(" Returning default value.").toString());
            return Types.getDefaultBoolean().booleanValue();
        }
        return flag;
    }

    public String getContainerId()
    {
        return mContainerId;
    }

    String getCtfeUrlPathAndQuery()
    {
        return mCtfeUrlPathAndQuery;
    }

    public double getDouble(String s)
    {
        Runtime runtime = getRuntime();
        if(runtime == null)
        {
            Log.e("getDouble called for closed container.");
            return Types.getDefaultDouble().doubleValue();
        }
        double d;
        try
        {
            d = Types.valueToDouble((com.google.analytics.midtier.proto.containertag.TypeSystem.Value)runtime.evaluateMacroReference(s).getObject()).doubleValue();
        }
        catch(Exception exception)
        {
            Log.e((new StringBuilder()).append("Calling getDouble() threw an exception: ").append(exception.getMessage()).append(" Returning default value.").toString());
            return Types.getDefaultDouble().doubleValue();
        }
        return d;
    }

    public FunctionCallMacroHandler getFunctionCallMacroHandler(String s)
    {
        this;
        JVM INSTR monitorenter ;
        FunctionCallMacroHandler functioncallmacrohandler = (FunctionCallMacroHandler)mFunctionCallMacroHandlers.get(s);
        this;
        JVM INSTR monitorexit ;
        return functioncallmacrohandler;
        Exception exception;
        exception;
        throw exception;
    }

    public FunctionCallTagHandler getFunctionCallTagHandler(String s)
    {
        this;
        JVM INSTR monitorenter ;
        FunctionCallTagHandler functioncalltaghandler = (FunctionCallTagHandler)mFunctionCallTagHandlers.get(s);
        this;
        JVM INSTR monitorexit ;
        return functioncalltaghandler;
        Exception exception;
        exception;
        throw exception;
    }

    public long getLastRefreshTime()
    {
        return mLastRefreshTime;
    }

    public long getLong(String s)
    {
        Runtime runtime = getRuntime();
        if(runtime == null)
        {
            Log.e("getLong called for closed container.");
            return Types.getDefaultInt64().longValue();
        }
        long l;
        try
        {
            l = Types.valueToInt64((com.google.analytics.midtier.proto.containertag.TypeSystem.Value)runtime.evaluateMacroReference(s).getObject()).longValue();
        }
        catch(Exception exception)
        {
            Log.e((new StringBuilder()).append("Calling getLong() threw an exception: ").append(exception.getMessage()).append(" Returning default value.").toString());
            return Types.getDefaultInt64().longValue();
        }
        return l;
    }

    String getResourceVersion()
    {
        return mResourceVersion;
    }

    public String getString(String s)
    {
        Runtime runtime = getRuntime();
        if(runtime == null)
        {
            Log.e("getString called for closed container.");
            return Types.getDefaultString();
        }
        String s1;
        try
        {
            s1 = Types.valueToString((com.google.analytics.midtier.proto.containertag.TypeSystem.Value)runtime.evaluateMacroReference(s).getObject());
        }
        catch(Exception exception)
        {
            Log.e((new StringBuilder()).append("Calling getString() threw an exception: ").append(exception.getMessage()).append(" Returning default value.").toString());
            return Types.getDefaultString();
        }
        return s1;
    }

    public boolean isDefault()
    {
        return getLastRefreshTime() == 0L;
    }

    void load(Callback callback)
    {
        Clock clock = new Clock() {

            public long currentTimeMillis()
            {
                return System.currentTimeMillis();
            }

            final Container this$0;

            
            {
                this$0 = Container.this;
                super();
            }
        }
;
        load(callback, ((ResourceLoaderScheduler) (new ResourceLoaderSchedulerImpl(mContext, mContainerId, mCtfeHost))), clock);
    }

    void load(Callback callback, ResourceLoaderScheduler resourceloaderscheduler, final Clock clock)
    {
        this;
        JVM INSTR monitorenter ;
        if(mDiskLoadCallback != null)
            throw new RuntimeException((new StringBuilder()).append("Container already loaded: container ID: ").append(mContainerId).toString());
        break MISSING_BLOCK_LABEL_47;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        mClock = clock;
        mUserCallback = callback;
        mDiskLoadCallback = new LoadCallback() {

            private RefreshFailure failureToRefreshFailure(LoadCallback.Failure failure)
            {
                static class _cls4
                {

                    static final int $SwitchMap$com$google$tagmanager$LoadCallback$Failure[];

                    static 
                    {
                        $SwitchMap$com$google$tagmanager$LoadCallback$Failure = new int[LoadCallback.Failure.values().length];
                        try
                        {
                            $SwitchMap$com$google$tagmanager$LoadCallback$Failure[LoadCallback.Failure.NOT_AVAILABLE.ordinal()] = 1;
                        }
                        catch(NoSuchFieldError nosuchfielderror) { }
                        try
                        {
                            $SwitchMap$com$google$tagmanager$LoadCallback$Failure[LoadCallback.Failure.IO_ERROR.ordinal()] = 2;
                        }
                        catch(NoSuchFieldError nosuchfielderror1) { }
                        try
                        {
                            $SwitchMap$com$google$tagmanager$LoadCallback$Failure[LoadCallback.Failure.SERVER_ERROR.ordinal()] = 3;
                        }
                        catch(NoSuchFieldError nosuchfielderror2)
                        {
                            return;
                        }
                    }
                }

                switch(_cls4..SwitchMap.com.google.tagmanager.LoadCallback.Failure[failure.ordinal()])
                {
                default:
                    return RefreshFailure.UNKNOWN_ERROR;

                case 1: // '\001'
                    return RefreshFailure.NO_SAVED_CONTAINER;

                case 2: // '\002'
                    return RefreshFailure.IO_ERROR;

                case 3: // '\003'
                    return RefreshFailure.SERVER_ERROR;
                }
            }

            public void onFailure(LoadCallback.Failure failure)
            {
                callRefreshFailure(RefreshType.SAVED, failureToRefreshFailure(failure));
                if(isDefault())
                    loadAfterDelay(0L);
            }

            public void onSuccess(com.google.tagmanager.proto.Resource.ResourceWithMetadata resourcewithmetadata)
            {
                if(isDefault())
                {
                    initEvaluators(resourcewithmetadata.getResource(), false);
                    Log.v((new StringBuilder()).append("setting refresh time to saved time: ").append(resourcewithmetadata.getTimeStamp()).toString());
                    mLastRefreshTime = resourcewithmetadata.getTimeStamp();
                    loadAfterDelay(Math.max(0L, Math.min(0x2932e00L, (0x2932e00L + mLastRefreshTime) - clock.currentTimeMillis())));
                }
                callRefreshSuccess(RefreshType.SAVED);
            }

            public volatile void onSuccess(Object obj)
            {
                onSuccess((com.google.tagmanager.proto.Resource.ResourceWithMetadata)obj);
            }

            public void startLoad()
            {
                callRefreshBegin(RefreshType.SAVED);
            }

            final Container this$0;
            final Clock val$clock;

            
            {
                this$0 = Container.this;
                clock = clock1;
                super();
            }
        }
;
        if(!isDefaultContainerRefreshMode())
            break MISSING_BLOCK_LABEL_86;
        Log.i("Container is in DEFAULT_CONTAINER mode. Use default container.");
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
        mResourceStorage.setLoadCallback(mDiskLoadCallback);
        mNetworkLoadCallback = new LoadCallback() {

            private RefreshFailure failureToRefreshFailure(LoadCallback.Failure failure)
            {
                switch(_cls4..SwitchMap.com.google.tagmanager.LoadCallback.Failure[failure.ordinal()])
                {
                default:
                    return RefreshFailure.UNKNOWN_ERROR;

                case 1: // '\001'
                    return RefreshFailure.NO_NETWORK;

                case 2: // '\002'
                    return RefreshFailure.NETWORK_ERROR;

                case 3: // '\003'
                    return RefreshFailure.SERVER_ERROR;
                }
            }

            public void onFailure(LoadCallback.Failure failure)
            {
                loadAfterDelay(0x36ee80L);
                callRefreshFailure(RefreshType.NETWORK, failureToRefreshFailure(failure));
            }

            public void onSuccess(com.google.analytics.containertag.proto.Serving.Resource resource)
            {
                Container container = Container.this;
                container;
                JVM INSTR monitorenter ;
                if(resource == null) goto _L2; else goto _L1
_L1:
                initEvaluators(resource, false);
_L3:
                mLastRefreshTime = clock.currentTimeMillis();
                Log.v((new StringBuilder()).append("setting refresh time to current time: ").append(mLastRefreshTime).toString());
                if(!isContainerPreview())
                    saveResourceToDisk(resource);
                loadAfterDelay(0x2932e00L);
                container;
                JVM INSTR monitorexit ;
                callRefreshSuccess(RefreshType.NETWORK);
                return;
_L2:
                if(mLastLoadedResource != null)
                    break MISSING_BLOCK_LABEL_131;
                onFailure(LoadCallback.Failure.SERVER_ERROR);
                container;
                JVM INSTR monitorexit ;
                return;
                Exception exception1;
                exception1;
                container;
                JVM INSTR monitorexit ;
                throw exception1;
                com.google.analytics.containertag.proto.Serving.Resource resource1 = mLastLoadedResource;
                resource = resource1;
                  goto _L3
            }

            public volatile void onSuccess(Object obj)
            {
                onSuccess((com.google.analytics.containertag.proto.Serving.Resource)obj);
            }

            public void startLoad()
            {
                callRefreshBegin(RefreshType.NETWORK);
            }

            final Container this$0;
            final Clock val$clock;

            
            {
                this$0 = Container.this;
                clock = clock1;
                super();
            }
        }
;
        resourceloaderscheduler.setLoadCallback(mNetworkLoadCallback);
        if(isContainerPreview())
        {
            mCtfeUrlPathAndQuery = PreviewManager.getInstance().getCTFEUrlPath();
            resourceloaderscheduler.setCtfeURLPathAndQuery(mCtfeUrlPathAndQuery);
        }
        if(mCtfeServerAddress != null)
            mCtfeHost.setCtfeServerAddress(mCtfeServerAddress);
        mNetworkLoadScheduler = resourceloaderscheduler;
        mResourceStorage.loadResourceFromDiskInBackground();
          goto _L1
    }

    void loadAfterDelay(long l)
    {
        this;
        JVM INSTR monitorenter ;
        if(mNetworkLoadScheduler == null || isDefaultContainerRefreshMode()) goto _L2; else goto _L1
_L1:
        if(mLastLoadedResource != null) goto _L4; else goto _L3
_L3:
        String s = null;
_L6:
        mNetworkLoadScheduler.loadAfterDelay(l, s);
_L2:
        this;
        JVM INSTR monitorexit ;
        return;
_L4:
        String s1 = mLastLoadedResource.getVersion();
        s = s1;
        if(true) goto _L6; else goto _L5
_L5:
        Exception exception;
        exception;
        throw exception;
    }

    public void refresh()
    {
        this;
        JVM INSTR monitorenter ;
        if(getRuntime() != null) goto _L2; else goto _L1
_L1:
        Log.w("refresh called for closed container");
_L3:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        if(!isDefaultContainerRefreshMode())
            break MISSING_BLOCK_LABEL_69;
        Log.w("Container is in DEFAULT_CONTAINER mode. Refresh request is ignored.");
          goto _L3
        Exception exception1;
        exception1;
        Log.e((new StringBuilder()).append("Calling refresh() throws an exception: ").append(exception1.getMessage()).toString());
          goto _L3
        Exception exception;
        exception;
        throw exception;
label0:
        {
            long l = mClock.currentTimeMillis();
            if(!useAvailableToken(l))
                break label0;
            Log.v("Container refresh requested");
            loadAfterDelay(0L);
            mLastRefreshMethodCalledTime = l;
        }
          goto _L3
        Log.v("Container refresh was called too often. Ignored.");
          goto _L3
    }

    public void registerFunctionCallMacroHandler(String s, FunctionCallMacroHandler functioncallmacrohandler)
    {
        this;
        JVM INSTR monitorenter ;
        mFunctionCallMacroHandlers.put(s, functioncallmacrohandler);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void registerFunctionCallTagHandler(String s, FunctionCallTagHandler functioncalltaghandler)
    {
        this;
        JVM INSTR monitorenter ;
        mFunctionCallTagHandlers.put(s, functioncalltaghandler);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    void setCtfeServerAddress(String s)
    {
        this;
        JVM INSTR monitorenter ;
        mCtfeServerAddress = s;
        if(s == null)
            break MISSING_BLOCK_LABEL_19;
        mCtfeHost.setCtfeServerAddress(s);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    void setCtfeUrlPathAndQuery(String s)
    {
        this;
        JVM INSTR monitorenter ;
        mCtfeUrlPathAndQuery = s;
        if(mNetworkLoadScheduler != null)
            mNetworkLoadScheduler.setCtfeURLPathAndQuery(s);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    static final boolean ENABLE_CONTAINER_DEBUG_MODE = false;
    static final int MAX_NUMBER_OF_TOKENS = 30;
    static final int MINIMUM_REFRESH_PERIOD_BURST_MODE_MS = 5000;
    static final long MINIMUM_REFRESH_PERIOD_MS = 0xdbba0L;
    static final long REFRESH_PERIOD_ON_FAILURE_MS = 0x36ee80L;
    static final long REFRESH_PERIOD_ON_SUCCESS_MS = 0x2932e00L;
    private Clock mClock;
    private final String mContainerId;
    private final Context mContext;
    private CtfeHost mCtfeHost;
    private volatile String mCtfeServerAddress;
    private volatile String mCtfeUrlPathAndQuery;
    LoadCallback mDiskLoadCallback;
    private Map mFunctionCallMacroHandlers;
    private Map mFunctionCallTagHandlers;
    private volatile com.google.analytics.containertag.proto.Serving.Resource mLastLoadedResource;
    private volatile long mLastRefreshMethodCalledTime;
    private volatile long mLastRefreshTime;
    LoadCallback mNetworkLoadCallback;
    private ResourceLoaderScheduler mNetworkLoadScheduler;
    private volatile int mNumTokens;
    private volatile int mResourceFormatVersion;
    private ResourceStorage mResourceStorage;
    private volatile String mResourceVersion;
    private Runtime mRuntime;
    private final TagManager mTagManager;
    private Callback mUserCallback;





/*
    static long access$202(Container container, long l)
    {
        container.mLastRefreshTime = l;
        return l;
    }

*/





}
