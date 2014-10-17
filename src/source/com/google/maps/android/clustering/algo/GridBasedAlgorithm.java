// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.maps.android.clustering.algo;

import android.support.v4.util.LongSparseArray;
import com.google.maps.android.clustering.ClusterItem;
import com.google.maps.android.geometry.Point;
import com.google.maps.android.projection.SphericalMercatorProjection;
import java.util.*;

// Referenced classes of package com.google.maps.android.clustering.algo:
//            Algorithm, StaticCluster

public class GridBasedAlgorithm
    implements Algorithm
{

    public GridBasedAlgorithm()
    {
    }

    private static long getCoord(long l, double d, double d1)
    {
        return (long)((double)l * Math.floor(d) + Math.floor(d1));
    }

    public void addItem(ClusterItem clusteritem)
    {
        mItems.add(clusteritem);
    }

    public void addItems(Collection collection)
    {
        mItems.addAll(collection);
    }

    public void clearItems()
    {
        mItems.clear();
    }

    public Set getClusters(double d)
    {
        long l;
        SphericalMercatorProjection sphericalmercatorprojection;
        HashSet hashset;
        LongSparseArray longsparsearray;
        l = (long)Math.ceil((256D * Math.pow(2D, d)) / 100D);
        sphericalmercatorprojection = new SphericalMercatorProjection(l);
        hashset = new HashSet();
        longsparsearray = new LongSparseArray();
        Set set = mItems;
        set;
        JVM INSTR monitorenter ;
        Iterator iterator = mItems.iterator();
_L1:
        ClusterItem clusteritem;
        com.google.maps.android.projection.Point point;
        long l1;
        StaticCluster staticcluster;
        if(!iterator.hasNext())
            break MISSING_BLOCK_LABEL_219;
        clusteritem = (ClusterItem)iterator.next();
        point = sphericalmercatorprojection.toPoint(clusteritem.getPosition());
        l1 = getCoord(l, ((Point) (point)).x, ((Point) (point)).y);
        staticcluster = (StaticCluster)longsparsearray.get(l1);
        if(staticcluster != null)
            break MISSING_BLOCK_LABEL_200;
        staticcluster = new StaticCluster(sphericalmercatorprojection.toLatLng(new Point(0.5D + Math.floor(((Point) (point)).x), 0.5D + Math.floor(((Point) (point)).y))));
        longsparsearray.put(l1, staticcluster);
        hashset.add(staticcluster);
        staticcluster.add(clusteritem);
          goto _L1
        Exception exception;
        exception;
        set;
        JVM INSTR monitorexit ;
        throw exception;
        set;
        JVM INSTR monitorexit ;
        return hashset;
    }

    public Collection getItems()
    {
        return mItems;
    }

    public void removeItem(ClusterItem clusteritem)
    {
        mItems.remove(clusteritem);
    }

    private static final int GRID_SIZE = 100;
    private final Set mItems = Collections.synchronizedSet(new HashSet());
}
