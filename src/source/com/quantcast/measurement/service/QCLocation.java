// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.quantcast.measurement.service;

import android.content.Context;
import android.location.*;
import android.os.*;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.instrumentation.*;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import org.json.*;

// Referenced classes of package com.quantcast.measurement.service:
//            QCNotificationListener, QCNotificationCenter, QCLog, QCMeasurement

public final class QCLocation extends Enum
    implements QCNotificationListener
{
    private class MeasurementLocation
    {

        public String getCountry()
        {
            return country;
        }

        public String getLocality()
        {
            return locality;
        }

        public String getState()
        {
            return state;
        }

        private final String country;
        private final String locality;
        private final String state;
        final QCLocation this$0;

        public MeasurementLocation(String s, String s1, String s2)
        {
            this$0 = QCLocation.this;
            super();
            country = s;
            state = s1;
            locality = s2;
        }
    }


    private QCLocation(String s, int i)
    {
        super(s, i);
        _locationEnabled = false;
        QCNotificationCenter.INSTANCE.addListener("QC_START", this);
        QCNotificationCenter.INSTANCE.addListener("QC_STOP", this);
        QCNotificationCenter.INSTANCE.addListener("QC_OUC", this);
        QCNotificationCenter.INSTANCE.addListener("QC_PU", this);
    }

    private boolean containsString(JSONArray jsonarray, String s)
    {
        for(int i = 0; i < jsonarray.length(); i++)
        {
            String s1 = jsonarray.optString(i);
            if(s1 != null && s1.equals(s))
                return true;
        }

        return false;
    }

    private MeasurementLocation lookup(double d, double d1)
    {
        String s;
        BufferedReader bufferedreader;
        s = (new StringBuilder()).append("https://maps.googleapis.com/maps/api/geocode/json?sensor=true&latlng=").append(d).append(",").append(d1).toString();
        bufferedreader = null;
        BufferedReader bufferedreader1 = new BufferedReader(new InputStreamReader(((HttpURLConnection)HttpInstrumentation.openConnection((new URL(s)).openConnection())).getInputStream()));
        StringBuilder stringbuilder = new StringBuilder();
_L3:
        String s1 = bufferedreader1.readLine();
        if(s1 == null) goto _L2; else goto _L1
_L1:
        stringbuilder.append(s1);
        stringbuilder.append("\n");
          goto _L3
        Exception exception1;
        exception1;
        bufferedreader = bufferedreader1;
_L7:
        QCLog.e(TAG, "Exception thrown by Google Maps", exception1);
        Exception exception;
        MeasurementLocation measurementlocation;
        if(bufferedreader != null)
            try
            {
                bufferedreader.close();
            }
            catch(IOException ioexception1) { }
        return null;
_L2:
        measurementlocation = parseJson(stringbuilder.toString());
        JSONException jsonexception;
        IOException ioexception2;
        if(bufferedreader1 != null)
            try
            {
                bufferedreader1.close();
            }
            catch(IOException ioexception3) { }
        return measurementlocation;
        jsonexception;
        QCLog.e(TAG, "Unable to get address from JSON", jsonexception);
        if(bufferedreader1 != null)
            try
            {
                bufferedreader1.close();
            }
            // Misplaced declaration of an exception variable
            catch(IOException ioexception2) { }
        if(true)
            break MISSING_BLOCK_LABEL_136;
        exception;
_L5:
        if(bufferedreader != null)
            try
            {
                bufferedreader.close();
            }
            catch(IOException ioexception) { }
        throw exception;
        exception;
        bufferedreader = bufferedreader1;
        if(true) goto _L5; else goto _L4
_L4:
        exception1;
        bufferedreader = null;
        if(true) goto _L7; else goto _L6
_L6:
    }

    private MeasurementLocation parseJson(String s)
        throws JSONException
    {
        JSONObject _tmp = JVM INSTR new #234 <Class JSONObject>;
        JSONObject jsonobject = JSONObjectInstrumentation.init(s);
        if("OK".equals(jsonobject.optString("status")))
        {
            JSONArray jsonarray = jsonobject.optJSONArray("results");
            if(jsonarray != null)
            {
                for(int i = 0; i < jsonarray.length(); i++)
                {
                    JSONArray jsonarray1 = jsonarray.getJSONObject(i).optJSONArray("address_components");
                    if(jsonarray1 != null)
                    {
                        String s1 = "";
                        String s2 = "";
                        String s3 = "";
                        for(int j = 0; j < jsonarray1.length(); j++)
                        {
                            JSONObject jsonobject1 = jsonarray1.getJSONObject(j);
                            JSONArray jsonarray2 = jsonobject1.optJSONArray("types");
                            if(jsonarray2 == null)
                                continue;
                            if(containsString(jsonarray2, "locality"))
                                s2 = jsonobject1.getString("short_name");
                            if(containsString(jsonarray2, "administrative_area_level_1"))
                                s3 = jsonobject1.getString("short_name");
                            if(containsString(jsonarray2, "country"))
                                s1 = jsonobject1.getString("short_name");
                        }

                        return new MeasurementLocation(s1, s3, s2);
                    }
                }

            }
        }
        return null;
    }

    private void sendLocation(Location location)
    {
        Double double1 = Double.valueOf(location.getLatitude());
        Double double2 = Double.valueOf(location.getLongitude());
        TraceFieldInterface tracefieldinterface = new TraceFieldInterface() {

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

            protected transient MeasurementLocation doInBackground(Double adouble1[])
            {
                double d = adouble1[0].doubleValue();
                double d1 = adouble1[1].doubleValue();
                QCLog.i(QCLocation.TAG, "Looking for address.");
                List list;
                MeasurementLocation measurementlocation;
                Address address;
                try
                {
                    QCLog.i(QCLocation.TAG, "Geocoder.");
                    list = _geocoder.getFromLocation(d, d1, 1);
                }
                catch(Exception exception)
                {
                    QCLog.i(QCLocation.TAG, "Geocoder API not available.");
                    return fallbackGeoLocate(d, d1);
                }
                if(list == null)
                    break MISSING_BLOCK_LABEL_102;
                if(list.size() > 0)
                {
                    address = (Address)list.get(0);
                    return new MeasurementLocation(address.getCountryCode(), address.getAdminArea(), address.getLocality());
                }
                QCLog.i(QCLocation.TAG, "Geocoder reverse lookup failed.");
                measurementlocation = fallbackGeoLocate(d, d1);
                return measurementlocation;
            }

            protected volatile Object doInBackground(Object aobj[])
            {
                TraceMachine.enterMethod(_nr_trace, "QCLocation$1#doInBackground", null);
_L1:
                MeasurementLocation measurementlocation = doInBackground((Double[])aobj);
                TraceMachine.exitMethod();
                TraceMachine.unloadTraceContext(this);
                return measurementlocation;
                NoSuchFieldError nosuchfielderror;
                nosuchfielderror;
                TraceMachine.enterMethod(null, "QCLocation$1#doInBackground", null);
                  goto _L1
            }

            protected MeasurementLocation fallbackGeoLocate(double d, double d1)
            {
                MeasurementLocation measurementlocation = lookup(d, d1);
                if(measurementlocation != null && !isCancelled())
                {
                    return measurementlocation;
                } else
                {
                    QCLog.i(QCLocation.TAG, "Google Maps API reverse lookup failed.");
                    return null;
                }
            }

            protected void onPostExecute(MeasurementLocation measurementlocation)
            {
                if(measurementlocation != null && measurementlocation.getCountry() != null)
                {
                    QCLog.i(QCLocation.TAG, (new StringBuilder()).append("Got address and sending...").append(measurementlocation.getCountry()).append(" ").append(measurementlocation.getState()).append(" ").append(measurementlocation.getLocality()).toString());
                    HashMap hashmap = new HashMap();
                    hashmap.put("event", "location");
                    if(measurementlocation.getCountry() != null)
                        hashmap.put("c", measurementlocation.getCountry());
                    if(measurementlocation.getState() != null)
                        hashmap.put("st", measurementlocation.getState());
                    if(measurementlocation.getLocality() != null)
                        hashmap.put("l", measurementlocation.getLocality());
                    QCMeasurement.INSTANCE.logOptionalEvent(hashmap, null, null);
                }
            }

            protected volatile void onPostExecute(Object obj)
            {
                TraceMachine.enterMethod(_nr_trace, "QCLocation$1#onPostExecute", null);
_L1:
                onPostExecute((MeasurementLocation)obj);
                TraceMachine.exitMethod();
                return;
                NoSuchFieldError nosuchfielderror;
                nosuchfielderror;
                TraceMachine.enterMethod(null, "QCLocation$1#onPostExecute", null);
                  goto _L1
            }

            public Trace _nr_trace;
            final QCLocation this$0;

            
            {
                this$0 = QCLocation.this;
                super();
            }
        }
;
        Double adouble[] = {
            double1, double2
        };
        AsyncTask asynctask;
        if(!(tracefieldinterface instanceof AsyncTask))
            asynctask = tracefieldinterface.execute(adouble);
        else
            asynctask = AsyncTaskInstrumentation.execute((AsyncTask)tracefieldinterface, adouble);
        _geoTask = asynctask;
    }

    public static void setEnableLocationGathering(boolean flag)
    {
        INSTANCE.setLocationEnabled(flag);
        if(QCMeasurement.INSTANCE.isMeasurementActive())
            INSTANCE.startStopLocation(QCMeasurement.INSTANCE.getAppContext());
    }

    public static QCLocation valueOf(String s)
    {
        return (QCLocation)Enum.valueOf(com/quantcast/measurement/service/QCLocation, s);
    }

    public static QCLocation[] values()
    {
        return (QCLocation[])$VALUES.clone();
    }

    Location findBestLocation()
    {
        Location location = null;
        long l = System.currentTimeMillis() - 0x927c0L;
        float f = 3.402823E+038F;
        Iterator iterator = _locManager.getAllProviders().iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            String s = (String)iterator.next();
            Location location1 = _locManager.getLastKnownLocation(s);
            if(location1 != null)
            {
                float f1 = location1.getAccuracy();
                long l1 = location1.getTime();
                if((double)f1 > 0.0D && l1 >= l && f1 <= f)
                {
                    location = location1;
                    f = f1;
                    l = l1;
                }
            }
        } while(true);
        return location;
    }

    Geocoder getGeocoder()
    {
        return _geocoder;
    }

    public void notificationCallback(String s, Object obj)
    {
        if(s.equals("QC_START") || s.equals("QC_LOC_START"))
        {
            startStopLocation((Context)obj);
        } else
        {
            if(s.equals("QC_STOP"))
            {
                stop();
                return;
            }
            if(s.equals("QC_OUC"))
                if(((Boolean)obj).booleanValue())
                {
                    stop();
                    _locManager = null;
                    _myProvider = null;
                    _geocoder = null;
                    return;
                } else
                {
                    startStopLocation(QCMeasurement.INSTANCE.getAppContext());
                    return;
                }
        }
    }

    public void setLocationEnabled(boolean flag)
    {
        _locationEnabled = flag;
    }

    void setupLocManager(Context context)
    {
        if(context == null)
            return;
        _locManager = (LocationManager)context.getSystemService("location");
        if(_locManager != null)
        {
            Criteria criteria = new Criteria();
            criteria.setAccuracy(2);
            criteria.setAltitudeRequired(false);
            criteria.setBearingRequired(false);
            criteria.setCostAllowed(false);
            criteria.setPowerRequirement(0);
            criteria.setSpeedRequired(false);
            _myProvider = _locManager.getBestProvider(criteria, true);
            _geocoder = new Geocoder(context);
        }
        QCLog.i(TAG, (new StringBuilder()).append("Setting location provider ").append(_myProvider).toString());
    }

    void start()
    {
        Location location;
        QCLog.i(TAG, "Start retrieving location ");
        location = findBestLocation();
        if(location == null) goto _L2; else goto _L1
_L1:
        sendLocation(location);
_L4:
        return;
_L2:
        if(_myProvider == null)
            break MISSING_BLOCK_LABEL_78;
        if(!_locManager.isProviderEnabled(_myProvider)) goto _L4; else goto _L3
_L3:
        _locManager.requestLocationUpdates(_myProvider, 0L, 0.0F, singleUpdateListener, Looper.getMainLooper());
        return;
        Exception exception;
        exception;
        QCLog.e(TAG, "Available location provider not found.  Skipping Location Event", exception);
        return;
        QCLog.i(TAG, "Available location provider not found.  Skipping Location Event");
        return;
    }

    void startStopLocation(Context context)
    {
        if(_locationEnabled)
        {
            setupLocManager(context);
            start();
            return;
        } else
        {
            stop();
            _locManager = null;
            _myProvider = null;
            _geocoder = null;
            return;
        }
    }

    void stop()
    {
        if(_locManager != null)
        {
            _locManager.removeUpdates(singleUpdateListener);
            if(_geoTask != null && _geoTask.getStatus() != android.os.AsyncTask.Status.FINISHED)
                _geoTask.cancel(true);
            _geoTask = null;
        }
    }

    private static final QCLocation $VALUES[];
    private static final String ADDRESS = "address_components";
    private static final String COUNTRY = "country";
    public static final QCLocation INSTANCE;
    private static final String LOCALITY = "locality";
    private static final String OK = "OK";
    static final String QC_CITY_KEY = "l";
    static final String QC_COUNTRY_KEY = "c";
    static final String QC_EVENT_LOCATION = "location";
    public static final String QC_NOTIF_LOCATION_START = "QC_LOC_START";
    static final String QC_STATE_KEY = "st";
    private static final String RESULTS = "results";
    private static final String SHORT_NAME = "short_name";
    private static final int STALE_LIMIT = 0x927c0;
    private static final String STATE = "administrative_area_level_1";
    private static final String STATUS = "status";
    private static final QCLog.Tag TAG = new QCLog.Tag(com/quantcast/measurement/service/QCLocation);
    private static final String TYPES = "types";
    private static final String URL = "https://maps.googleapis.com/maps/api/geocode/json?sensor=true&latlng=";
    private AsyncTask _geoTask;
    private Geocoder _geocoder;
    private LocationManager _locManager;
    private boolean _locationEnabled;
    private String _myProvider;
    protected final LocationListener singleUpdateListener = new LocationListener() {

        public void onLocationChanged(Location location)
        {
            _locManager.removeUpdates(singleUpdateListener);
            if(location != null)
                sendLocation(location);
        }

        public void onProviderDisabled(String s1)
        {
        }

        public void onProviderEnabled(String s1)
        {
        }

        public void onStatusChanged(String s1, int j, Bundle bundle)
        {
        }

        final QCLocation this$0;

            
            {
                this$0 = QCLocation.this;
                super();
            }
    }
;

    static 
    {
        INSTANCE = new QCLocation("INSTANCE", 0);
        QCLocation aqclocation[] = new QCLocation[1];
        aqclocation[0] = INSTANCE;
        $VALUES = aqclocation;
    }





}
