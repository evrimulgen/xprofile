// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.maps.android.clustering;

import android.content.Context;
import android.os.AsyncTask;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Marker;
import com.google.maps.android.MarkerManager;
import com.google.maps.android.clustering.algo.Algorithm;
import com.google.maps.android.clustering.algo.NonHierarchicalDistanceBasedAlgorithm;
import com.google.maps.android.clustering.algo.PreCachingAlgorithmDecorator;
import com.google.maps.android.clustering.view.ClusterRenderer;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.instrumentation.AsyncTaskInstrumentation;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import java.util.Collection;
import java.util.Set;
import java.util.concurrent.locks.*;

// Referenced classes of package com.google.maps.android.clustering:
//            ClusterItem, Cluster

public class ClusterManager
    implements com.google.android.gms.maps.GoogleMap.OnCameraChangeListener, com.google.android.gms.maps.GoogleMap.OnMarkerClickListener, com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener
{
    private class ClusterTask extends AsyncTask
        implements TraceFieldInterface
    {

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

        protected volatile Object doInBackground(Object aobj[])
        {
            TraceMachine.enterMethod(_nr_trace, "ClusterManager$ClusterTask#doInBackground", null);
_L1:
            Set set = doInBackground((Float[])aobj);
            TraceMachine.exitMethod();
            TraceMachine.unloadTraceContext(this);
            return set;
            NoSuchFieldError nosuchfielderror;
            nosuchfielderror;
            TraceMachine.enterMethod(null, "ClusterManager$ClusterTask#doInBackground", null);
              goto _L1
        }

        protected transient Set doInBackground(Float afloat[])
        {
            mAlgorithmLock.readLock().lock();
            Set set = mAlgorithm.getClusters(afloat[0].floatValue());
            mAlgorithmLock.readLock().unlock();
            return set;
            Exception exception;
            exception;
            mAlgorithmLock.readLock().unlock();
            throw exception;
        }

        protected volatile void onPostExecute(Object obj)
        {
            TraceMachine.enterMethod(_nr_trace, "ClusterManager$ClusterTask#onPostExecute", null);
_L1:
            onPostExecute((Set)obj);
            TraceMachine.exitMethod();
            return;
            NoSuchFieldError nosuchfielderror;
            nosuchfielderror;
            TraceMachine.enterMethod(null, "ClusterManager$ClusterTask#onPostExecute", null);
              goto _L1
        }

        protected void onPostExecute(Set set)
        {
            mRenderer.onClustersChanged(set);
        }

        public Trace _nr_trace;
        final ClusterManager this$0;

        private ClusterTask()
        {
            this$0 = ClusterManager.this;
            super();
        }

    }

    public static interface OnClusterClickListener
    {

        public abstract boolean onClusterClick(Cluster cluster1);
    }

    public static interface OnClusterInfoWindowClickListener
    {

        public abstract void onClusterInfoWindowClick(Cluster cluster1);
    }

    public static interface OnClusterItemClickListener
    {

        public abstract boolean onClusterItemClick(ClusterItem clusteritem);
    }

    public static interface OnClusterItemInfoWindowClickListener
    {

        public abstract void onClusterItemInfoWindowClick(ClusterItem clusteritem);
    }


    public ClusterManager(Context context, GoogleMap googlemap)
    {
        this(context, googlemap, new MarkerManager(googlemap));
    }

    public ClusterManager(Context context, GoogleMap googlemap, MarkerManager markermanager)
    {
        mAlgorithmLock = new ReentrantReadWriteLock();
        mClusterTaskLock = new ReentrantReadWriteLock();
        mMap = googlemap;
        mMarkerManager = markermanager;
        mClusterMarkers = markermanager.newCollection();
        mMarkers = markermanager.newCollection();
        mRenderer = new DefaultClusterRenderer(context, googlemap, this);
        mAlgorithm = new PreCachingAlgorithmDecorator(new NonHierarchicalDistanceBasedAlgorithm());
        mClusterTask = new ClusterTask();
        mRenderer.onAdd();
    }

    public void addItem(ClusterItem clusteritem)
    {
        mAlgorithmLock.writeLock().lock();
        mAlgorithm.addItem(clusteritem);
        mAlgorithmLock.writeLock().unlock();
        return;
        Exception exception;
        exception;
        mAlgorithmLock.writeLock().unlock();
        throw exception;
    }

    public void addItems(Collection collection)
    {
        mAlgorithmLock.writeLock().lock();
        mAlgorithm.addItems(collection);
        mAlgorithmLock.writeLock().unlock();
        return;
        Exception exception;
        exception;
        mAlgorithmLock.writeLock().unlock();
        throw exception;
    }

    public void clearItems()
    {
        mAlgorithmLock.writeLock().lock();
        mAlgorithm.clearItems();
        mAlgorithmLock.writeLock().unlock();
        return;
        Exception exception;
        exception;
        mAlgorithmLock.writeLock().unlock();
        throw exception;
    }

    public void cluster()
    {
        mClusterTaskLock.writeLock().lock();
        ClusterTask clustertask;
        Float afloat[];
        mClusterTask.cancel(true);
        mClusterTask = new ClusterTask();
        clustertask = mClusterTask;
        afloat = new Float[1];
        afloat[0] = Float.valueOf(mMap.getCameraPosition().zoom);
        if(clustertask instanceof AsyncTask) goto _L2; else goto _L1
_L1:
        clustertask.execute(afloat);
_L4:
        mClusterTaskLock.writeLock().unlock();
        return;
_L2:
        AsyncTaskInstrumentation.execute((AsyncTask)clustertask, afloat);
        if(true) goto _L4; else goto _L3
_L3:
        Exception exception;
        exception;
        mClusterTaskLock.writeLock().unlock();
        throw exception;
    }

    public com.google.maps.android.MarkerManager.Collection getClusterMarkerCollection()
    {
        return mClusterMarkers;
    }

    public com.google.maps.android.MarkerManager.Collection getMarkerCollection()
    {
        return mMarkers;
    }

    public MarkerManager getMarkerManager()
    {
        return mMarkerManager;
    }

    public void onCameraChange(CameraPosition cameraposition)
    {
        if(mRenderer instanceof com.google.android.gms.maps.GoogleMap.OnCameraChangeListener)
            ((com.google.android.gms.maps.GoogleMap.OnCameraChangeListener)mRenderer).onCameraChange(cameraposition);
        CameraPosition cameraposition1 = mMap.getCameraPosition();
        if(mPreviousCameraPosition != null && mPreviousCameraPosition.zoom == cameraposition1.zoom)
        {
            return;
        } else
        {
            mPreviousCameraPosition = mMap.getCameraPosition();
            cluster();
            return;
        }
    }

    public void onInfoWindowClick(Marker marker)
    {
        getMarkerManager().onInfoWindowClick(marker);
    }

    public boolean onMarkerClick(Marker marker)
    {
        return getMarkerManager().onMarkerClick(marker);
    }

    public void removeItem(ClusterItem clusteritem)
    {
        mAlgorithmLock.writeLock().lock();
        mAlgorithm.removeItem(clusteritem);
        mAlgorithmLock.writeLock().unlock();
        return;
        Exception exception;
        exception;
        mAlgorithmLock.writeLock().unlock();
        throw exception;
    }

    public void setAlgorithm(Algorithm algorithm)
    {
        mAlgorithmLock.writeLock().lock();
        if(mAlgorithm != null)
            algorithm.addItems(mAlgorithm.getItems());
        mAlgorithm = new PreCachingAlgorithmDecorator(algorithm);
        mAlgorithmLock.writeLock().unlock();
        cluster();
        return;
        Exception exception;
        exception;
        mAlgorithmLock.writeLock().unlock();
        throw exception;
    }

    public void setOnClusterClickListener(OnClusterClickListener onclusterclicklistener)
    {
        mOnClusterClickListener = onclusterclicklistener;
        mRenderer.setOnClusterClickListener(onclusterclicklistener);
    }

    public void setOnClusterInfoWindowClickListener(OnClusterInfoWindowClickListener onclusterinfowindowclicklistener)
    {
        mOnClusterInfoWindowClickListener = onclusterinfowindowclicklistener;
        mRenderer.setOnClusterInfoWindowClickListener(onclusterinfowindowclicklistener);
    }

    public void setOnClusterItemClickListener(OnClusterItemClickListener onclusteritemclicklistener)
    {
        mOnClusterItemClickListener = onclusteritemclicklistener;
        mRenderer.setOnClusterItemClickListener(onclusteritemclicklistener);
    }

    public void setOnClusterItemInfoWindowClickListener(OnClusterItemInfoWindowClickListener onclusteriteminfowindowclicklistener)
    {
        mOnClusterItemInfoWindowClickListener = onclusteriteminfowindowclicklistener;
        mRenderer.setOnClusterItemInfoWindowClickListener(onclusteriteminfowindowclicklistener);
    }

    public void setRenderer(ClusterRenderer clusterrenderer)
    {
        mRenderer.setOnClusterClickListener(null);
        mRenderer.setOnClusterItemClickListener(null);
        mClusterMarkers.clear();
        mMarkers.clear();
        mRenderer.onRemove();
        mRenderer = clusterrenderer;
        mRenderer.onAdd();
        mRenderer.setOnClusterClickListener(mOnClusterClickListener);
        mRenderer.setOnClusterInfoWindowClickListener(mOnClusterInfoWindowClickListener);
        mRenderer.setOnClusterItemClickListener(mOnClusterItemClickListener);
        mRenderer.setOnClusterItemInfoWindowClickListener(mOnClusterItemInfoWindowClickListener);
        cluster();
    }

    private Algorithm mAlgorithm;
    private final ReadWriteLock mAlgorithmLock;
    private final com.google.maps.android.MarkerManager.Collection mClusterMarkers;
    private ClusterTask mClusterTask;
    private final ReadWriteLock mClusterTaskLock;
    private GoogleMap mMap;
    private final MarkerManager mMarkerManager;
    private final com.google.maps.android.MarkerManager.Collection mMarkers;
    private OnClusterClickListener mOnClusterClickListener;
    private OnClusterInfoWindowClickListener mOnClusterInfoWindowClickListener;
    private OnClusterItemClickListener mOnClusterItemClickListener;
    private OnClusterItemInfoWindowClickListener mOnClusterItemInfoWindowClickListener;
    private CameraPosition mPreviousCameraPosition;
    private ClusterRenderer mRenderer;



}
