// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.agent.android;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.location.*;
import android.os.*;
import com.newrelic.agent.android.api.common.TransactionData;
import com.newrelic.agent.android.api.v1.ConnectionEvent;
import com.newrelic.agent.android.api.v1.ConnectionListener;
import com.newrelic.agent.android.api.v1.DeviceForm;
import com.newrelic.agent.android.api.v2.TraceMachineInterface;
import com.newrelic.agent.android.background.ApplicationStateEvent;
import com.newrelic.agent.android.background.ApplicationStateListener;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.harvest.ApplicationInformation;
import com.newrelic.agent.android.harvest.DeviceInformation;
import com.newrelic.agent.android.harvest.Harvest;
import com.newrelic.agent.android.harvest.HarvestConfiguration;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.newrelic.agent.android.sample.MachineMeasurementConsumer;
import com.newrelic.agent.android.sample.Sampler;
import com.newrelic.agent.android.stats.StatsEngine;
import com.newrelic.agent.android.tracing.TraceMachine;
import com.newrelic.agent.android.util.AndroidEncoder;
import com.newrelic.agent.android.util.Carrier;
import com.newrelic.agent.android.util.Encoder;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// Referenced classes of package com.newrelic.agent.android:
//            AgentImpl, AgentInitializationException, AgentConfiguration, SavedState, 
//            Agent, Measurements, NullAgentImpl

public class AndroidAgentImpl
    implements AgentImpl, ConnectionListener, ApplicationStateListener, TraceMachineInterface
{

    public AndroidAgentImpl(Context context1, String s, String s1, boolean flag, boolean flag1, String s2)
        throws AgentInitializationException
    {
        connected = false;
        context = appContext(context1);
        savedState = new SavedState(context);
        if(!s.equals(savedState.getAppToken()))
        {
            log.debug("License key has changed. Clearing saved state.");
            savedState.clear();
        }
        if(!Agent.getVersion().equals(savedState.getAgentVersion()))
        {
            log.debug("Agent version has changed. Clearing saved state.");
            savedState.clear();
        }
        if(isDisabled())
            throw new AgentInitializationException("This version of the agent has been disabled");
        initApplicationInformation();
        if(flag1 && context.getPackageManager().checkPermission("android.permission.ACCESS_FINE_LOCATION", getApplicationInformation().getPackageId()) == 0)
        {
            log.debug("Location stats enabled");
            addLocationListener();
        }
        TraceMachine.setTraceMachineInterface(this);
        savedState.saveAppToken(s);
        savedState.saveAgentVersion(Agent.getVersion());
        agentConfiguration.setApplicationToken(s);
        agentConfiguration.setCollectorHost(s1);
        agentConfiguration.setUseSsl(flag);
        ApplicationStateMonitor.getInstance().addApplicationStateListener(this);
        Sampler.init(context1);
    }

    private void addLocationListener()
    {
        LocationManager locationmanager = (LocationManager)context.getSystemService("location");
        if(locationmanager == null)
        {
            log.error("Unable to retrieve reference to LocationManager. Disabling location listener.");
            return;
        } else
        {
            locationListener = new LocationListener() {

                public void onLocationChanged(Location location)
                {
                    if(isAccurate(location))
                        setLocation(location);
                }

                public void onProviderDisabled(String s)
                {
                    if("passive".equals(s))
                        removeLocationListener();
                }

                public void onProviderEnabled(String s)
                {
                }

                public void onStatusChanged(String s, int i, Bundle bundle)
                {
                }

                final AndroidAgentImpl this$0;

            
            {
                this$0 = AndroidAgentImpl.this;
                super();
            }
            }
;
            locationmanager.requestLocationUpdates("passive", 1000L, 0.0F, locationListener);
            return;
        }
    }

    private static Context appContext(Context context1)
    {
        if(!(context1 instanceof Application))
            context1 = context1.getApplicationContext();
        return context1;
    }

    private static DeviceForm deviceForm(Context context1)
    {
        int i = 0xf & context1.getResources().getConfiguration().screenLayout;
        switch(i)
        {
        default:
            if(i > 3)
                return DeviceForm.XLARGE;
            else
                return DeviceForm.UNKNOWN;

        case 1: // '\001'
            return DeviceForm.SMALL;

        case 2: // '\002'
            return DeviceForm.NORMAL;

        case 3: // '\003'
            return DeviceForm.LARGE;
        }
    }

    private String getUUID()
    {
        String s = savedState.getAndroidIdBugWorkAround();
        if(s == null)
        {
            s = UUID.randomUUID().toString();
            savedState.saveAndroidIdBugWorkAround(s);
        }
        return s;
    }

    private String getUnhandledExceptionHandlerName()
    {
        String s;
        try
        {
            s = Thread.getDefaultUncaughtExceptionHandler().getClass().getName();
        }
        catch(Exception exception)
        {
            return "unknown";
        }
        return s;
    }

    public static void init(Context context1, String s, String s1, boolean flag, boolean flag1, String s2)
    {
        try
        {
            Agent.setImpl(new AndroidAgentImpl(context1, s, s1, flag, flag1, s2));
            Agent.start();
            return;
        }
        catch(AgentInitializationException agentinitializationexception)
        {
            log.error((new StringBuilder()).append("Failed to initialize the agent: ").append(agentinitializationexception.toString()).toString());
        }
    }

    private boolean isAccurate(Location location)
    {
        while(location == null || 500F < location.getAccuracy()) 
            return false;
        return true;
    }

    private void removeLocationListener()
    {
        LocationManager locationmanager;
        if(locationListener == null)
            return;
        locationmanager = (LocationManager)context.getSystemService("location");
        if(locationmanager == null)
        {
            log.error("Unable to retrieve reference to LocationManager. Can't unregister location listener.");
            return;
        }
        locationmanager;
        JVM INSTR monitorenter ;
        locationmanager.removeUpdates(locationListener);
        locationListener = null;
        locationmanager;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        locationmanager;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private void stop(boolean flag)
    {
        Sampler.stop();
        TraceMachine.haltTracing();
        if(flag)
            Harvest.harvestNow();
        Harvest.shutdown();
        Measurements.shutdown();
    }

    public void addTransactionData(TransactionData transactiondata)
    {
    }

    public void applicationBackgrounded(ApplicationStateEvent applicationstateevent)
    {
        log.error("AndroidAgentImpl: application backgrounded ");
        stop();
    }

    public void applicationForegrounded(ApplicationStateEvent applicationstateevent)
    {
        log.error("AndroidAgentImpl: application foregrounded ");
        start();
    }

    public void connected(ConnectionEvent connectionevent)
    {
        log.error("AndroidAgentImpl: connected ");
    }

    public void disable()
    {
        log.warning((new StringBuilder()).append("PERMANENTLY DISABLING AGENT v").append(Agent.getVersion()).toString());
        savedState.saveDisabledVersion(Agent.getVersion());
        stop(false);
        Agent.setImpl(NullAgentImpl.instance);
        return;
        Exception exception2;
        exception2;
        Agent.setImpl(NullAgentImpl.instance);
        throw exception2;
        Exception exception;
        exception;
        stop(false);
        Agent.setImpl(NullAgentImpl.instance);
        throw exception;
        Exception exception1;
        exception1;
        Agent.setImpl(NullAgentImpl.instance);
        throw exception1;
    }

    public void disconnected(ConnectionEvent connectionevent)
    {
        savedState.clear();
    }

    public List getAndClearTransactionData()
    {
        return null;
    }

    public ApplicationInformation getApplicationInformation()
    {
        return applicationInformation;
    }

    public String getCrossProcessId()
    {
        lock.lock();
        String s = savedState.getCrossProcessId();
        String s1;
        boolean flag;
        if("".equals(s))
            s1 = null;
        else
            s1 = s;
        flag = connected;
        if(!flag)
            s1 = null;
        lock.unlock();
        return s1;
        Exception exception;
        exception;
        lock.unlock();
        throw exception;
    }

    public long getCurrentThreadId()
    {
        return Thread.currentThread().getId();
    }

    public String getCurrentThreadName()
    {
        return Thread.currentThread().getName();
    }

    public DeviceInformation getDeviceInformation()
    {
        if(deviceInformation != null)
        {
            return deviceInformation;
        } else
        {
            DeviceInformation deviceinformation = new DeviceInformation();
            deviceinformation.setOsName("Android");
            deviceinformation.setOsVersion(android.os.Build.VERSION.RELEASE);
            deviceinformation.setModel(Build.MODEL);
            deviceinformation.setAgentName("AndroidAgent");
            deviceinformation.setAgentVersion(Agent.getVersion());
            deviceinformation.setManufacturer(Build.MANUFACTURER);
            deviceinformation.setDeviceId(getUUID());
            deviceinformation.addMisc("size", deviceForm(context).name().toLowerCase());
            deviceInformation = deviceinformation;
            return deviceInformation;
        }
    }

    public Encoder getEncoder()
    {
        return encoder;
    }

    public String getNetworkCarrier()
    {
        return Carrier.nameFromContext(context);
    }

    public int getResponseBodyLimit()
    {
        lock.lock();
        int i = savedState.getHarvestConfiguration().getResponse_body_limit();
        lock.unlock();
        return i;
        Exception exception;
        exception;
        lock.unlock();
        throw exception;
    }

    public int getStackTraceLimit()
    {
        lock.lock();
        int i = savedState.getStackTraceLimit();
        lock.unlock();
        return i;
        Exception exception;
        exception;
        lock.unlock();
        throw exception;
    }

    public void initApplicationInformation()
        throws AgentInitializationException
    {
        String s;
        String s2;
        s = context.getPackageName();
        PackageManager packagemanager = context.getPackageManager();
        PackageInfo packageinfo;
        String s1;
        android.content.pm.ApplicationInfo applicationinfo;
        String s3;
        try
        {
            packageinfo = packagemanager.getPackageInfo(s, 0);
        }
        catch(android.content.pm.PackageManager.NameNotFoundException namenotfoundexception)
        {
            throw new AgentInitializationException((new StringBuilder()).append("Could not determine package version: ").append(namenotfoundexception.getMessage()).toString());
        }
        if(packageinfo == null) goto _L2; else goto _L1
_L1:
        if(packageinfo.versionName == null || packageinfo.versionName.length() <= 0) goto _L2; else goto _L3
_L3:
        s1 = packageinfo.versionName;
        applicationinfo = packagemanager.getApplicationInfo(s, 0);
        if(applicationinfo == null) goto _L5; else goto _L4
_L4:
        s3 = packagemanager.getApplicationLabel(applicationinfo).toString();
        s2 = s3;
_L7:
        applicationInformation = new ApplicationInformation(s2, s1, s);
        return;
_L2:
        throw new AgentInitializationException("Your app doesn't appear to have a version defined. Ensure you have defined 'versionName' in your manifest.");
_L5:
        s2 = s;
        continue; /* Loop/switch isn't completed */
        android.content.pm.PackageManager.NameNotFoundException namenotfoundexception1;
        namenotfoundexception1;
        log.warning(namenotfoundexception1.toString());
        s2 = s;
        continue; /* Loop/switch isn't completed */
        SecurityException securityexception;
        securityexception;
        log.warning(securityexception.toString());
        s2 = s;
        if(true) goto _L7; else goto _L6
_L6:
    }

    protected void initialize()
    {
        Harvest.addHarvestListener(savedState);
        Harvest.initialize(agentConfiguration);
        Harvest.setHarvestConfiguration(savedState.getHarvestConfiguration());
        Measurements.initialize();
        AgentLog agentlog = log;
        Object aobj[] = new Object[1];
        aobj[0] = Agent.getVersion();
        agentlog.info(MessageFormat.format("New Relic Agent v{0}", aobj));
        AgentLog agentlog1 = log;
        Object aobj1[] = new Object[1];
        aobj1[0] = agentConfiguration.getApplicationToken();
        agentlog1.verbose(MessageFormat.format("Application token: {0}", aobj1));
        machineMeasurementConsumer = new MachineMeasurementConsumer();
        Measurements.addMeasurementConsumer(machineMeasurementConsumer);
        StatsEngine.get().inc((new StringBuilder()).append("Supportability/AgentHealth/UncaughtExceptionHandler/").append(getUnhandledExceptionHandlerName()).toString());
    }

    public boolean isDisabled()
    {
        return Agent.getVersion().equals(savedState.getDisabledVersion());
    }

    public boolean isUIThread()
    {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    public void mergeTransactionData(List list)
    {
    }

    public void setLocation(Location location)
    {
        List list;
        if(location == null)
            throw new IllegalArgumentException("Location must not be null.");
        Geocoder geocoder = new Geocoder(context);
        List list1;
        try
        {
            list1 = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
        }
        catch(IOException ioexception)
        {
            log.error((new StringBuilder()).append("Unable to geocode location: ").append(ioexception.toString()).toString());
            list = null;
            continue; /* Loop/switch isn't completed */
        }
        list = list1;
_L6:
        if(list != null && list.size() != 0) goto _L2; else goto _L1
_L1:
        Address address;
        return;
_L2:
        if((address = (Address)list.get(0)) == null) goto _L1; else goto _L3
_L3:
        String s;
        String s1;
        s = address.getCountryCode();
        s1 = address.getAdminArea();
        if(s == null || s1 == null) goto _L1; else goto _L4
_L4:
        setLocation(s, s1);
        removeLocationListener();
        return;
        if(true) goto _L6; else goto _L5
_L5:
    }

    public void setLocation(String s, String s1)
    {
        if(s == null || s1 == null)
            throw new IllegalArgumentException("Country code and administrative region are required.");
        else
            return;
    }

    public void start()
    {
        if(!isDisabled())
        {
            initialize();
            Harvest.start();
            return;
        } else
        {
            stop(false);
            return;
        }
    }

    public void stop()
    {
        stop(true);
    }

    private static final float LOCATION_ACCURACY_THRESHOLD = 500F;
    private static final Comparator cmp = new Comparator() {

        public int compare(TransactionData transactiondata, TransactionData transactiondata1)
        {
            if(transactiondata.getTimestamp() > transactiondata1.getTimestamp())
                return -1;
            return transactiondata.getTimestamp() >= transactiondata1.getTimestamp() ? 0 : 1;
        }

        public volatile int compare(Object obj, Object obj1)
        {
            return compare((TransactionData)obj, (TransactionData)obj1);
        }

    }
;
    private static final AgentLog log = AgentLogManager.getAgentLog();
    private final AgentConfiguration agentConfiguration = new AgentConfiguration();
    private ApplicationInformation applicationInformation;
    private boolean connected;
    private final Context context;
    private DeviceInformation deviceInformation;
    private final Encoder encoder = new AndroidEncoder();
    private LocationListener locationListener;
    private final Lock lock = new ReentrantLock();
    private MachineMeasurementConsumer machineMeasurementConsumer;
    private SavedState savedState;



}
