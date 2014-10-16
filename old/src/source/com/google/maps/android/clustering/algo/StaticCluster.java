// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.maps.android.clustering.algo;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterItem;
import java.util.*;

public class StaticCluster
    implements Cluster
{

    public StaticCluster(LatLng latlng)
    {
        mCenter = latlng;
    }

    public boolean add(ClusterItem clusteritem)
    {
        return mItems.add(clusteritem);
    }

    public Collection getItems()
    {
        return mItems;
    }

    public LatLng getPosition()
    {
        return mCenter;
    }

    public int getSize()
    {
        return mItems.size();
    }

    public boolean remove(ClusterItem clusteritem)
    {
        return mItems.remove(clusteritem);
    }

    public String toString()
    {
        return (new StringBuilder()).append("StaticCluster{mCenter=").append(mCenter).append(", mItems.size=").append(mItems.size()).append('}').toString();
    }

    private final LatLng mCenter;
    private final List mItems = new ArrayList();
}
