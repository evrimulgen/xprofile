// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.maps.android.clustering.view;

import android.animation.*;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.*;
import android.graphics.drawable.shapes.OvalShape;
import android.os.*;
import android.util.DisplayMetrics;
import android.util.SparseArray;
import android.view.animation.DecelerateInterpolator;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.model.*;
import com.google.maps.android.MarkerManager;
import com.google.maps.android.clustering.*;
import com.google.maps.android.geometry.Point;
import com.google.maps.android.projection.SphericalMercatorProjection;
import com.google.maps.android.ui.IconGenerator;
import com.google.maps.android.ui.SquareTextView;
import java.util.*;
import java.util.concurrent.locks.*;

// Referenced classes of package com.google.maps.android.clustering.view:
//            ClusterRenderer

public class DefaultClusterRenderer
    implements ClusterRenderer
{
    private class AnimationTask extends AnimatorListenerAdapter
        implements android.animation.ValueAnimator.AnimatorUpdateListener
    {

        public void onAnimationEnd(Animator animator)
        {
            if(mRemoveOnComplete)
            {
                mMarkerCache.remove(marker);
                mMarkerToCluster.remove(marker);
                mMarkerManager.remove(marker);
            }
            markerWithPosition.position = to;
        }

        public void onAnimationUpdate(ValueAnimator valueanimator)
        {
            float f = valueanimator.getAnimatedFraction();
            double d = (to.latitude - from.latitude) * (double)f + from.latitude;
            double d1 = to.longitude - from.longitude;
            if(Math.abs(d1) > 180D)
                d1 -= 360D * Math.signum(d1);
            LatLng latlng = new LatLng(d, d1 * (double)f + from.longitude);
            marker.setPosition(latlng);
        }

        public void perform()
        {
            ValueAnimator valueanimator = ValueAnimator.ofFloat(new float[] {
                0.0F, 1.0F
            });
            valueanimator.setInterpolator(DefaultClusterRenderer.ANIMATION_INTERP);
            valueanimator.addUpdateListener(this);
            valueanimator.addListener(this);
            valueanimator.start();
        }

        public void removeOnAnimationComplete(MarkerManager markermanager)
        {
            mMarkerManager = markermanager;
            mRemoveOnComplete = true;
        }

        private final LatLng from;
        private MarkerManager mMarkerManager;
        private boolean mRemoveOnComplete;
        private final Marker marker;
        private final MarkerWithPosition markerWithPosition;
        final DefaultClusterRenderer this$0;
        private final LatLng to;

        private AnimationTask(MarkerWithPosition markerwithposition, LatLng latlng, LatLng latlng1)
        {
            this$0 = DefaultClusterRenderer.this;
            super();
            markerWithPosition = markerwithposition;
            marker = markerwithposition.marker;
            from = latlng;
            to = latlng1;
        }

    }

    private class CreateMarkerTask
    {

        private void perform(MarkerModifier markermodifier)
        {
            if(!shouldRenderAsCluster(cluster))
            {
                Iterator iterator = cluster.getItems().iterator();
                while(iterator.hasNext()) 
                {
                    ClusterItem clusteritem = (ClusterItem)iterator.next();
                    Marker marker1 = mMarkerCache.get(clusteritem);
                    MarkerWithPosition markerwithposition1;
                    if(marker1 == null)
                    {
                        MarkerOptions markeroptions2 = new MarkerOptions();
                        if(animateFrom != null)
                            markeroptions2.position(animateFrom);
                        else
                            markeroptions2.position(clusteritem.getPosition());
                        onBeforeClusterItemRendered(clusteritem, markeroptions2);
                        marker1 = mClusterManager.getMarkerCollection().addMarker(markeroptions2);
                        markerwithposition1 = new MarkerWithPosition(marker1);
                        mMarkerCache.put(clusteritem, marker1);
                        if(animateFrom != null)
                            markermodifier.animate(markerwithposition1, animateFrom, clusteritem.getPosition());
                    } else
                    {
                        markerwithposition1 = new MarkerWithPosition(marker1);
                    }
                    onClusterItemRendered(clusteritem, marker1);
                    newMarkers.add(markerwithposition1);
                }
            } else
            {
                MarkerOptions markeroptions = new MarkerOptions();
                LatLng latlng;
                MarkerOptions markeroptions1;
                Marker marker;
                MarkerWithPosition markerwithposition;
                if(animateFrom == null)
                    latlng = cluster.getPosition();
                else
                    latlng = animateFrom;
                markeroptions1 = markeroptions.position(latlng);
                onBeforeClusterRendered(cluster, markeroptions1);
                marker = mClusterManager.getClusterMarkerCollection().addMarker(markeroptions1);
                mMarkerToCluster.put(marker, cluster);
                markerwithposition = new MarkerWithPosition(marker);
                if(animateFrom != null)
                    markermodifier.animate(markerwithposition, animateFrom, cluster.getPosition());
                onClusterRendered(cluster, marker);
                newMarkers.add(markerwithposition);
            }
        }

        private final LatLng animateFrom;
        private final Cluster cluster;
        private final Set newMarkers;
        final DefaultClusterRenderer this$0;


        public CreateMarkerTask(Cluster cluster1, Set set, LatLng latlng)
        {
            this$0 = DefaultClusterRenderer.this;
            super();
            cluster = cluster1;
            newMarkers = set;
            animateFrom = latlng;
        }
    }

    private static class MarkerCache
    {

        public Marker get(Object obj)
        {
            return (Marker)mCache.get(obj);
        }

        public Object get(Marker marker)
        {
            return mCacheReverse.get(marker);
        }

        public void put(Object obj, Marker marker)
        {
            mCache.put(obj, marker);
            mCacheReverse.put(marker, obj);
        }

        public void remove(Marker marker)
        {
            Object obj = mCacheReverse.get(marker);
            mCacheReverse.remove(marker);
            mCache.remove(obj);
        }

        private Map mCache;
        private Map mCacheReverse;

        private MarkerCache()
        {
            mCache = new HashMap();
            mCacheReverse = new HashMap();
        }

    }

    private class MarkerModifier extends Handler
        implements android.os.MessageQueue.IdleHandler
    {

        private void performNextTask()
        {
            if(!mOnScreenRemoveMarkerTasks.isEmpty())
            {
                removeMarker((Marker)mOnScreenRemoveMarkerTasks.poll());
            } else
            {
                if(!mAnimationTasks.isEmpty())
                {
                    ((AnimationTask)mAnimationTasks.poll()).perform();
                    return;
                }
                if(!mOnScreenCreateMarkerTasks.isEmpty())
                {
                    ((CreateMarkerTask)mOnScreenCreateMarkerTasks.poll()).perform(this);
                    return;
                }
                if(!mCreateMarkerTasks.isEmpty())
                {
                    ((CreateMarkerTask)mCreateMarkerTasks.poll()).perform(this);
                    return;
                }
                if(!mRemoveMarkerTasks.isEmpty())
                {
                    removeMarker((Marker)mRemoveMarkerTasks.poll());
                    return;
                }
            }
        }

        private void removeMarker(Marker marker)
        {
            mMarkerCache.remove(marker);
            mMarkerToCluster.remove(marker);
            mClusterManager.getMarkerManager().remove(marker);
        }

        public void add(boolean flag, CreateMarkerTask createmarkertask)
        {
            lock.lock();
            sendEmptyMessage(0);
            if(flag)
                mOnScreenCreateMarkerTasks.add(createmarkertask);
            else
                mCreateMarkerTasks.add(createmarkertask);
            lock.unlock();
        }

        public void animate(MarkerWithPosition markerwithposition, LatLng latlng, LatLng latlng1)
        {
            lock.lock();
            mAnimationTasks.add(new AnimationTask(markerwithposition, latlng, latlng1));
            lock.unlock();
        }

        public void animateThenRemove(MarkerWithPosition markerwithposition, LatLng latlng, LatLng latlng1)
        {
            lock.lock();
            AnimationTask animationtask = new AnimationTask(markerwithposition, latlng, latlng1);
            animationtask.removeOnAnimationComplete(mClusterManager.getMarkerManager());
            mAnimationTasks.add(animationtask);
            lock.unlock();
        }

        public void handleMessage(Message message)
        {
            int i;
            if(!mListenerAdded)
            {
                Looper.myQueue().addIdleHandler(this);
                mListenerAdded = true;
            }
            removeMessages(0);
            lock.lock();
            i = 0;
_L2:
            if(i >= 10)
                break; /* Loop/switch isn't completed */
            performNextTask();
            i++;
            if(true) goto _L2; else goto _L1
_L1:
            if(isBusy()) goto _L4; else goto _L3
_L3:
            mListenerAdded = false;
            Looper.myQueue().removeIdleHandler(this);
            busyCondition.signalAll();
_L6:
            lock.unlock();
            return;
_L4:
            sendEmptyMessageDelayed(0, 10L);
            if(true) goto _L6; else goto _L5
_L5:
            Exception exception;
            exception;
            lock.unlock();
            throw exception;
        }

        public boolean isBusy()
        {
            return !mCreateMarkerTasks.isEmpty() || !mOnScreenCreateMarkerTasks.isEmpty() || !mOnScreenRemoveMarkerTasks.isEmpty() || !mRemoveMarkerTasks.isEmpty() || !mAnimationTasks.isEmpty();
        }

        public boolean queueIdle()
        {
            sendEmptyMessage(0);
            return true;
        }

        public void remove(boolean flag, Marker marker)
        {
            lock.lock();
            sendEmptyMessage(0);
            if(flag)
                mOnScreenRemoveMarkerTasks.add(marker);
            else
                mRemoveMarkerTasks.add(marker);
            lock.unlock();
        }

        public void waitUntilFree()
        {
_L2:
            if(!isBusy())
                break; /* Loop/switch isn't completed */
            sendEmptyMessage(0);
            lock.lock();
            if(isBusy())
                busyCondition.await();
            lock.unlock();
            if(true) goto _L2; else goto _L1
            InterruptedException interruptedexception;
            interruptedexception;
            throw new RuntimeException(interruptedexception);
            Exception exception;
            exception;
            lock.unlock();
            throw exception;
_L1:
        }

        private static final int BLANK;
        private final Condition busyCondition;
        private final Lock lock;
        private Queue mAnimationTasks;
        private Queue mCreateMarkerTasks;
        private boolean mListenerAdded;
        private Queue mOnScreenCreateMarkerTasks;
        private Queue mOnScreenRemoveMarkerTasks;
        private Queue mRemoveMarkerTasks;
        final DefaultClusterRenderer this$0;

        private MarkerModifier()
        {
            this$0 = DefaultClusterRenderer.this;
            super(Looper.getMainLooper());
            lock = new ReentrantLock();
            busyCondition = lock.newCondition();
            mCreateMarkerTasks = new LinkedList();
            mOnScreenCreateMarkerTasks = new LinkedList();
            mRemoveMarkerTasks = new LinkedList();
            mOnScreenRemoveMarkerTasks = new LinkedList();
            mAnimationTasks = new LinkedList();
        }

    }

    private static class MarkerWithPosition
    {

        public boolean equals(Object obj)
        {
            if(obj instanceof MarkerWithPosition)
                return marker.equals(((MarkerWithPosition)obj).marker);
            else
                return false;
        }

        public int hashCode()
        {
            return marker.hashCode();
        }

        private final Marker marker;
        private LatLng position;



/*
        static LatLng access$1602(MarkerWithPosition markerwithposition, LatLng latlng)
        {
            markerwithposition.position = latlng;
            return latlng;
        }

*/


        private MarkerWithPosition(Marker marker1)
        {
            marker = marker1;
            position = marker1.getPosition();
        }

    }

    private class RenderTask
        implements Runnable
    {

        public void run()
        {
            if(clusters.equals(mClusters))
            {
                mCallback.run();
                return;
            }
            MarkerModifier markermodifier = new MarkerModifier();
            float f = mMapZoom;
            boolean flag;
            float f1;
            Set set;
            LatLngBounds latlngbounds;
            Set set1;
            ArrayList arraylist;
            if(f > mZoom)
                flag = true;
            else
                flag = false;
            f1 = f - mZoom;
            set = mMarkers;
            latlngbounds = mProjection.getVisibleRegion().latLngBounds;
            set1 = mClusters;
            arraylist = null;
            if(set1 != null)
            {
                boolean flag4 = DefaultClusterRenderer.SHOULD_ANIMATE;
                arraylist = null;
                if(flag4)
                {
                    arraylist = new ArrayList();
                    Iterator iterator3 = mClusters.iterator();
                    do
                    {
                        if(!iterator3.hasNext())
                            break;
                        Cluster cluster2 = (Cluster)iterator3.next();
                        if(shouldRenderAsCluster(cluster2) && latlngbounds.contains(cluster2.getPosition()))
                            arraylist.add(mSphericalMercatorProjection.toPoint(cluster2.getPosition()));
                    } while(true);
                }
            }
            HashSet hashset = new HashSet();
            for(Iterator iterator = clusters.iterator(); iterator.hasNext();)
            {
                Cluster cluster1 = (Cluster)iterator.next();
                boolean flag3 = latlngbounds.contains(cluster1.getPosition());
                if(flag && flag3 && DefaultClusterRenderer.SHOULD_ANIMATE)
                {
                    Point point1 = DefaultClusterRenderer.findClosestCluster(arraylist, mSphericalMercatorProjection.toPoint(cluster1.getPosition()));
                    if(point1 != null)
                    {
                        LatLng latlng1 = mSphericalMercatorProjection.toLatLng(point1);
                        CreateMarkerTask createmarkertask2 = new CreateMarkerTask(cluster1, hashset, latlng1);
                        markermodifier.add(true, createmarkertask2);
                    } else
                    {
                        CreateMarkerTask createmarkertask1 = new CreateMarkerTask(cluster1, hashset, null);
                        markermodifier.add(true, createmarkertask1);
                    }
                } else
                {
                    CreateMarkerTask createmarkertask = new CreateMarkerTask(cluster1, hashset, null);
                    markermodifier.add(flag3, createmarkertask);
                }
            }

            markermodifier.waitUntilFree();
            set.removeAll(hashset);
            boolean flag1 = DefaultClusterRenderer.SHOULD_ANIMATE;
            ArrayList arraylist1 = null;
            if(flag1)
            {
                arraylist1 = new ArrayList();
                Iterator iterator1 = clusters.iterator();
                do
                {
                    if(!iterator1.hasNext())
                        break;
                    Cluster cluster = (Cluster)iterator1.next();
                    if(shouldRenderAsCluster(cluster) && latlngbounds.contains(cluster.getPosition()))
                        arraylist1.add(mSphericalMercatorProjection.toPoint(cluster.getPosition()));
                } while(true);
            }
            for(Iterator iterator2 = set.iterator(); iterator2.hasNext();)
            {
                MarkerWithPosition markerwithposition = (MarkerWithPosition)iterator2.next();
                boolean flag2 = latlngbounds.contains(markerwithposition.position);
                if(!flag && f1 > -3F && flag2 && DefaultClusterRenderer.SHOULD_ANIMATE)
                {
                    Point point = DefaultClusterRenderer.findClosestCluster(arraylist1, mSphericalMercatorProjection.toPoint(markerwithposition.position));
                    if(point != null)
                    {
                        LatLng latlng = mSphericalMercatorProjection.toLatLng(point);
                        markermodifier.animateThenRemove(markerwithposition, markerwithposition.position, latlng);
                    } else
                    {
                        markermodifier.remove(true, markerwithposition.marker);
                    }
                } else
                {
                    markermodifier.remove(flag2, markerwithposition.marker);
                }
            }

            markermodifier.waitUntilFree();
            mMarkers = hashset;
            mClusters = clusters;
            mZoom = f;
            mCallback.run();
        }

        public void setCallback(Runnable runnable)
        {
            mCallback = runnable;
        }

        public void setMapZoom(float f)
        {
            mMapZoom = f;
            mSphericalMercatorProjection = new SphericalMercatorProjection(256D * Math.pow(2D, Math.min(f, mZoom)));
        }

        public void setProjection(Projection projection)
        {
            mProjection = projection;
        }

        final Set clusters;
        private Runnable mCallback;
        private float mMapZoom;
        private Projection mProjection;
        private SphericalMercatorProjection mSphericalMercatorProjection;
        final DefaultClusterRenderer this$0;

        private RenderTask(Set set)
        {
            this$0 = DefaultClusterRenderer.this;
            super();
            clusters = set;
        }

    }

    private class ViewModifier extends Handler
    {

        public void handleMessage(Message message)
        {
            if(message.what != 1) goto _L2; else goto _L1
_L1:
            mViewModificationInProgress = false;
            if(mNextClusters != null)
                sendEmptyMessage(0);
_L4:
            return;
_L2:
            removeMessages(0);
            if(mViewModificationInProgress || mNextClusters == null) goto _L4; else goto _L3
_L3:
            this;
            JVM INSTR monitorenter ;
            RenderTask rendertask;
            rendertask = mNextClusters;
            mNextClusters = null;
            mViewModificationInProgress = true;
            this;
            JVM INSTR monitorexit ;
            rendertask.setCallback(new Runnable() {

                public void run()
                {
                    sendEmptyMessage(1);
                }

                final ViewModifier this$1;

            
            {
                this$1 = ViewModifier.this;
                super();
            }
            }
);
            rendertask.setProjection(mMap.getProjection());
            rendertask.setMapZoom(mMap.getCameraPosition().zoom);
            (new Thread(rendertask)).start();
            return;
            Exception exception;
            exception;
            this;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public void queue(Set set)
        {
            this;
            JVM INSTR monitorenter ;
            mNextClusters = new RenderTask(set);
            this;
            JVM INSTR monitorexit ;
            sendEmptyMessage(0);
            return;
            Exception exception;
            exception;
            this;
            JVM INSTR monitorexit ;
            throw exception;
        }

        private static final int RUN_TASK = 0;
        private static final int TASK_FINISHED = 1;
        private RenderTask mNextClusters;
        private boolean mViewModificationInProgress;
        final DefaultClusterRenderer this$0;

        private ViewModifier()
        {
            this$0 = DefaultClusterRenderer.this;
            super();
            mViewModificationInProgress = false;
            mNextClusters = null;
        }

    }


    public DefaultClusterRenderer(Context context, GoogleMap googlemap, ClusterManager clustermanager)
    {
        mMarkers = new HashSet();
        mIcons = new SparseArray();
        mMarkerCache = new MarkerCache();
        mMarkerToCluster = new HashMap();
        mMap = googlemap;
        mDensity = context.getResources().getDisplayMetrics().density;
        mIconGenerator = new IconGenerator(context);
        mIconGenerator.setContentView(makeSquareTextView(context));
        mIconGenerator.setTextAppearance(com.google.maps.android.R.style.ClusterIcon_TextAppearance);
        mIconGenerator.setBackground(makeClusterBackground());
        mClusterManager = clustermanager;
    }

    private static double distanceSquared(Point point, Point point1)
    {
        return (point.x - point1.x) * (point.x - point1.x) + (point.y - point1.y) * (point.y - point1.y);
    }

    private static Point findClosestCluster(List list, Point point)
    {
        Point point1;
        if(list == null || list.isEmpty())
        {
            point1 = null;
        } else
        {
            double d = 10000D;
            point1 = null;
            Iterator iterator = list.iterator();
            while(iterator.hasNext()) 
            {
                Point point2 = (Point)iterator.next();
                double d1 = distanceSquared(point2, point);
                if(d1 < d)
                {
                    point1 = point2;
                    d = d1;
                }
            }
        }
        return point1;
    }

    private int getColor(int i)
    {
        float f = Math.min(i, 300F);
        return Color.HSVToColor(new float[] {
            220F * (((300F - f) * (300F - f)) / 90000F), 1.0F, 0.6F
        });
    }

    private LayerDrawable makeClusterBackground()
    {
        mColoredCircleBackground = new ShapeDrawable(new OvalShape());
        ShapeDrawable shapedrawable = new ShapeDrawable(new OvalShape());
        shapedrawable.getPaint().setColor(0x80ffffff);
        Drawable adrawable[] = new Drawable[2];
        adrawable[0] = shapedrawable;
        adrawable[1] = mColoredCircleBackground;
        LayerDrawable layerdrawable = new LayerDrawable(adrawable);
        int i = (int)(3F * mDensity);
        layerdrawable.setLayerInset(1, i, i, i, i);
        return layerdrawable;
    }

    private SquareTextView makeSquareTextView(Context context)
    {
        SquareTextView squaretextview = new SquareTextView(context);
        squaretextview.setLayoutParams(new android.view.ViewGroup.LayoutParams(-2, -2));
        squaretextview.setId(com.google.maps.android.R.id.text);
        int i = (int)(12F * mDensity);
        squaretextview.setPadding(i, i, i, i);
        return squaretextview;
    }

    protected int getBucket(Cluster cluster)
    {
        int i = cluster.getSize();
        if(i <= BUCKETS[0])
            return i;
        for(int j = 0; j < -1 + BUCKETS.length; j++)
            if(i < BUCKETS[j + 1])
                return BUCKETS[j];

        return BUCKETS[-1 + BUCKETS.length];
    }

    protected String getClusterText(int i)
    {
        if(i < BUCKETS[0])
            return String.valueOf(i);
        else
            return (new StringBuilder()).append(String.valueOf(i)).append("+").toString();
    }

    public void onAdd()
    {
        mClusterManager.getMarkerCollection().setOnMarkerClickListener(new com.google.android.gms.maps.GoogleMap.OnMarkerClickListener() {

            public boolean onMarkerClick(Marker marker)
            {
                return mItemClickListener != null && mItemClickListener.onClusterItemClick((ClusterItem)mMarkerCache.get(marker));
            }

            final DefaultClusterRenderer this$0;

            
            {
                this$0 = DefaultClusterRenderer.this;
                super();
            }
        }
);
        mClusterManager.getMarkerCollection().setOnInfoWindowClickListener(new com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener() {

            public void onInfoWindowClick(Marker marker)
            {
                if(mItemInfoWindowClickListener != null)
                    mItemInfoWindowClickListener.onClusterItemInfoWindowClick((ClusterItem)mMarkerCache.get(marker));
            }

            final DefaultClusterRenderer this$0;

            
            {
                this$0 = DefaultClusterRenderer.this;
                super();
            }
        }
);
        mClusterManager.getClusterMarkerCollection().setOnMarkerClickListener(new com.google.android.gms.maps.GoogleMap.OnMarkerClickListener() {

            public boolean onMarkerClick(Marker marker)
            {
                return mClickListener != null && mClickListener.onClusterClick((Cluster)mMarkerToCluster.get(marker));
            }

            final DefaultClusterRenderer this$0;

            
            {
                this$0 = DefaultClusterRenderer.this;
                super();
            }
        }
);
        mClusterManager.getClusterMarkerCollection().setOnInfoWindowClickListener(new com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener() {

            public void onInfoWindowClick(Marker marker)
            {
                if(mInfoWindowClickListener != null)
                    mInfoWindowClickListener.onClusterInfoWindowClick((Cluster)mMarkerToCluster.get(marker));
            }

            final DefaultClusterRenderer this$0;

            
            {
                this$0 = DefaultClusterRenderer.this;
                super();
            }
        }
);
    }

    protected void onBeforeClusterItemRendered(ClusterItem clusteritem, MarkerOptions markeroptions)
    {
    }

    protected void onBeforeClusterRendered(Cluster cluster, MarkerOptions markeroptions)
    {
        int i = getBucket(cluster);
        BitmapDescriptor bitmapdescriptor = (BitmapDescriptor)mIcons.get(i);
        if(bitmapdescriptor == null)
        {
            mColoredCircleBackground.getPaint().setColor(getColor(i));
            bitmapdescriptor = BitmapDescriptorFactory.fromBitmap(mIconGenerator.makeIcon(getClusterText(i)));
            mIcons.put(i, bitmapdescriptor);
        }
        markeroptions.icon(bitmapdescriptor);
    }

    protected void onClusterItemRendered(ClusterItem clusteritem, Marker marker)
    {
    }

    protected void onClusterRendered(Cluster cluster, Marker marker)
    {
    }

    public void onClustersChanged(Set set)
    {
        mViewModifier.queue(set);
    }

    public void onRemove()
    {
        mClusterManager.getMarkerCollection().setOnMarkerClickListener(null);
        mClusterManager.getClusterMarkerCollection().setOnMarkerClickListener(null);
    }

    public void setOnClusterClickListener(com.google.maps.android.clustering.ClusterManager.OnClusterClickListener onclusterclicklistener)
    {
        mClickListener = onclusterclicklistener;
    }

    public void setOnClusterInfoWindowClickListener(com.google.maps.android.clustering.ClusterManager.OnClusterInfoWindowClickListener onclusterinfowindowclicklistener)
    {
        mInfoWindowClickListener = onclusterinfowindowclicklistener;
    }

    public void setOnClusterItemClickListener(com.google.maps.android.clustering.ClusterManager.OnClusterItemClickListener onclusteritemclicklistener)
    {
        mItemClickListener = onclusteritemclicklistener;
    }

    public void setOnClusterItemInfoWindowClickListener(com.google.maps.android.clustering.ClusterManager.OnClusterItemInfoWindowClickListener onclusteriteminfowindowclicklistener)
    {
        mItemInfoWindowClickListener = onclusteriteminfowindowclicklistener;
    }

    protected boolean shouldRenderAsCluster(Cluster cluster)
    {
        return cluster.getSize() > 4;
    }

    private static final TimeInterpolator ANIMATION_INTERP = new DecelerateInterpolator();
    private static final int BUCKETS[] = {
        10, 20, 50, 100, 200, 500, 1000
    };
    private static final int MIN_CLUSTER_SIZE = 4;
    private static final boolean SHOULD_ANIMATE;
    private com.google.maps.android.clustering.ClusterManager.OnClusterClickListener mClickListener;
    private final ClusterManager mClusterManager;
    private Set mClusters;
    private ShapeDrawable mColoredCircleBackground;
    private final float mDensity;
    private final IconGenerator mIconGenerator;
    private SparseArray mIcons;
    private com.google.maps.android.clustering.ClusterManager.OnClusterInfoWindowClickListener mInfoWindowClickListener;
    private com.google.maps.android.clustering.ClusterManager.OnClusterItemClickListener mItemClickListener;
    private com.google.maps.android.clustering.ClusterManager.OnClusterItemInfoWindowClickListener mItemInfoWindowClickListener;
    private final GoogleMap mMap;
    private MarkerCache mMarkerCache;
    private Map mMarkerToCluster;
    private Set mMarkers;
    private final ViewModifier mViewModifier = new ViewModifier();
    private float mZoom;

    static 
    {
        boolean flag;
        if(android.os.Build.VERSION.SDK_INT >= 11)
            flag = true;
        else
            flag = false;
        SHOULD_ANIMATE = flag;
    }



/*
    static float access$1002(DefaultClusterRenderer defaultclusterrenderer, float f)
    {
        defaultclusterrenderer.mZoom = f;
        return f;
    }

*/



/*
    static Set access$1102(DefaultClusterRenderer defaultclusterrenderer, Set set)
    {
        defaultclusterrenderer.mClusters = set;
        return set;
    }

*/



/*
    static Set access$1302(DefaultClusterRenderer defaultclusterrenderer, Set set)
    {
        defaultclusterrenderer.mMarkers = set;
        return set;
    }

*/











}
