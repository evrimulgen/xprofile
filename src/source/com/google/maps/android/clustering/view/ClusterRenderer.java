// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.maps.android.clustering.view;

import java.util.Set;

public interface ClusterRenderer
{

    public abstract void onAdd();

    public abstract void onClustersChanged(Set set);

    public abstract void onRemove();

    public abstract void setOnClusterClickListener(com.google.maps.android.clustering.ClusterManager.OnClusterClickListener onclusterclicklistener);

    public abstract void setOnClusterInfoWindowClickListener(com.google.maps.android.clustering.ClusterManager.OnClusterInfoWindowClickListener onclusterinfowindowclicklistener);

    public abstract void setOnClusterItemClickListener(com.google.maps.android.clustering.ClusterManager.OnClusterItemClickListener onclusteritemclicklistener);

    public abstract void setOnClusterItemInfoWindowClickListener(com.google.maps.android.clustering.ClusterManager.OnClusterItemInfoWindowClickListener onclusteriteminfowindowclicklistener);
}
