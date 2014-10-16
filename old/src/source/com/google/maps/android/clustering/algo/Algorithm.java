// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.maps.android.clustering.algo;

import com.google.maps.android.clustering.ClusterItem;
import java.util.Collection;
import java.util.Set;

public interface Algorithm
{

    public abstract void addItem(ClusterItem clusteritem);

    public abstract void addItems(Collection collection);

    public abstract void clearItems();

    public abstract Set getClusters(double d);

    public abstract Collection getItems();

    public abstract void removeItem(ClusterItem clusteritem);
}
