// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.maps.android.clustering.algo;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterItem;
import com.google.maps.android.geometry.Bounds;
import com.google.maps.android.geometry.Point;
import com.google.maps.android.projection.SphericalMercatorProjection;
import com.google.maps.android.quadtree.PointQuadTree;
import java.util.*;

// Referenced classes of package com.google.maps.android.clustering.algo:
//            Algorithm, StaticCluster

public class NonHierarchicalDistanceBasedAlgorithm
    implements Algorithm
{
    private static class QuadItem
        implements com.google.maps.android.quadtree.PointQuadTree.Item, Cluster
    {

        public volatile Collection getItems()
        {
            return getItems();
        }

        public Set getItems()
        {
            return singletonSet;
        }

        public Point getPoint()
        {
            return mPoint;
        }

        public LatLng getPosition()
        {
            return mPosition;
        }

        public int getSize()
        {
            return 1;
        }

        private final ClusterItem mClusterItem;
        private final Point mPoint;
        private final LatLng mPosition;
        private Set singletonSet;


        private QuadItem(ClusterItem clusteritem)
        {
            mClusterItem = clusteritem;
            mPosition = clusteritem.getPosition();
            mPoint = NonHierarchicalDistanceBasedAlgorithm.PROJECTION.toPoint(mPosition);
            singletonSet = Collections.singleton(mClusterItem);
        }

    }


    public NonHierarchicalDistanceBasedAlgorithm()
    {
    }

    private Bounds createBoundsFromSpan(Point point, double d)
    {
        double d1 = d / 2D;
        return new Bounds(point.x - d1, d1 + point.x, point.y - d1, d1 + point.y);
    }

    private double distanceSquared(Point point, Point point1)
    {
        return (point.x - point1.x) * (point.x - point1.x) + (point.y - point1.y) * (point.y - point1.y);
    }

    public void addItem(ClusterItem clusteritem)
    {
        QuadItem quaditem = new QuadItem(clusteritem);
        synchronized(mQuadTree)
        {
            mItems.add(quaditem);
            mQuadTree.add(quaditem);
        }
        return;
        exception;
        pointquadtree;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void addItems(Collection collection)
    {
        for(Iterator iterator = collection.iterator(); iterator.hasNext(); addItem((ClusterItem)iterator.next()));
    }

    public void clearItems()
    {
        synchronized(mQuadTree)
        {
            mItems.clear();
            mQuadTree.clear();
        }
        return;
        exception;
        pointquadtree;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public Set getClusters(double d)
    {
        double d1;
        HashSet hashset;
        HashSet hashset1;
        HashMap hashmap;
        HashMap hashmap1;
        d1 = 100D / Math.pow(2D, (int)d) / 256D;
        hashset = new HashSet();
        hashset1 = new HashSet();
        hashmap = new HashMap();
        hashmap1 = new HashMap();
        PointQuadTree pointquadtree = mQuadTree;
        pointquadtree;
        JVM INSTR monitorenter ;
        Iterator iterator = mItems.iterator();
_L3:
        QuadItem quaditem;
        Collection collection;
        do
        {
            if(!iterator.hasNext())
                break MISSING_BLOCK_LABEL_368;
            quaditem = (QuadItem)iterator.next();
        } while(hashset.contains(quaditem));
        Bounds bounds = createBoundsFromSpan(quaditem.getPoint(), d1);
        collection = mQuadTree.search(bounds);
        if(collection.size() != 1) goto _L2; else goto _L1
_L1:
        hashset1.add(quaditem);
        hashset.add(quaditem);
        hashmap.put(quaditem, Double.valueOf(0.0D));
          goto _L3
        Exception exception;
        exception;
        pointquadtree;
        JVM INSTR monitorexit ;
        throw exception;
_L2:
        StaticCluster staticcluster;
        Iterator iterator1;
        staticcluster = new StaticCluster(quaditem.mClusterItem.getPosition());
        hashset1.add(staticcluster);
        iterator1 = collection.iterator();
_L5:
        QuadItem quaditem1;
        Double double1;
        double d2;
        if(!iterator1.hasNext())
            break MISSING_BLOCK_LABEL_355;
        quaditem1 = (QuadItem)iterator1.next();
        double1 = (Double)hashmap.get(quaditem1);
        d2 = distanceSquared(quaditem1.getPoint(), quaditem.getPoint());
        if(double1 == null)
            break MISSING_BLOCK_LABEL_314;
        if(double1.doubleValue() < d2) goto _L5; else goto _L4
_L4:
        ((StaticCluster)hashmap1.get(quaditem1)).remove(quaditem1.mClusterItem);
        hashmap.put(quaditem1, Double.valueOf(d2));
        staticcluster.add(quaditem1.mClusterItem);
        hashmap1.put(quaditem1, staticcluster);
          goto _L5
        hashset.addAll(collection);
          goto _L3
        pointquadtree;
        JVM INSTR monitorexit ;
        return hashset1;
    }

    public Collection getItems()
    {
        ArrayList arraylist = new ArrayList();
        PointQuadTree pointquadtree = mQuadTree;
        pointquadtree;
        JVM INSTR monitorenter ;
        for(Iterator iterator = mItems.iterator(); iterator.hasNext(); arraylist.add(((QuadItem)iterator.next()).mClusterItem));
        break MISSING_BLOCK_LABEL_64;
        Exception exception;
        exception;
        pointquadtree;
        JVM INSTR monitorexit ;
        throw exception;
        pointquadtree;
        JVM INSTR monitorexit ;
        return arraylist;
    }

    public void removeItem(ClusterItem clusteritem)
    {
        throw new UnsupportedOperationException("NonHierarchicalDistanceBasedAlgorithm.remove not implemented");
    }

    public static final int MAX_DISTANCE_AT_ZOOM = 100;
    private static final SphericalMercatorProjection PROJECTION = new SphericalMercatorProjection(1.0D);
    private final Collection mItems = new ArrayList();
    private final PointQuadTree mQuadTree = new PointQuadTree(0.0D, 1.0D, 0.0D, 1.0D);


}
