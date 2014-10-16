// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.maps.android.heatmaps;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v4.util.LongSparseArray;
import com.google.android.gms.maps.model.*;
import com.google.maps.android.geometry.Bounds;
import com.google.maps.android.geometry.Point;
import com.google.maps.android.quadtree.PointQuadTree;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.Array;
import java.util.*;

// Referenced classes of package com.google.maps.android.heatmaps:
//            Gradient, WeightedLatLng

public class HeatmapTileProvider
    implements TileProvider
{
    public static class Builder
    {

        public HeatmapTileProvider build()
        {
            if(data == null)
                throw new IllegalStateException("No input data: you must use either .data or .weightedData before building");
            else
                return new HeatmapTileProvider(this);
        }

        public Builder data(Collection collection)
        {
            return weightedData(HeatmapTileProvider.wrapData(collection));
        }

        public Builder gradient(Gradient gradient1)
        {
            gradient = gradient1;
            return this;
        }

        public Builder opacity(double d)
        {
            opacity = d;
            if(opacity < 0.0D || opacity > 1.0D)
                throw new IllegalArgumentException("Opacity must be in range [0, 1]");
            else
                return this;
        }

        public Builder radius(int i)
        {
            radius = i;
            if(radius < 10 || radius > 50)
                throw new IllegalArgumentException("Radius not within bounds.");
            else
                return this;
        }

        public Builder weightedData(Collection collection)
        {
            data = collection;
            if(data.isEmpty())
                throw new IllegalArgumentException("No input points.");
            else
                return this;
        }

        private Collection data;
        private Gradient gradient;
        private double opacity;
        private int radius;





        public Builder()
        {
            radius = 20;
            gradient = HeatmapTileProvider.DEFAULT_GRADIENT;
            opacity = 0.69999999999999996D;
        }
    }


    private HeatmapTileProvider(Builder builder)
    {
        mData = builder.data;
        mRadius = builder.radius;
        mGradient = builder.gradient;
        mOpacity = builder.opacity;
        mKernel = generateKernel(mRadius, (double)mRadius / 3D);
        setGradient(mGradient);
        setWeightedData(mData);
    }


    static Bitmap colorize(double ad[][], int ai[], double d)
    {
        int i = ai[-1 + ai.length];
        double d1 = (double)(-1 + ai.length) / d;
        int j = ad.length;
        int ai1[] = new int[j * j];
        for(int k = 0; k < j; k++)
        {
            int l = 0;
            while(l < j) 
            {
                double d2 = ad[l][k];
                int i1 = l + k * j;
                int j1 = (int)(d2 * d1);
                if(d2 != 0.0D)
                {
                    if(j1 < ai.length)
                        ai1[i1] = ai[j1];
                    else
                        ai1[i1] = i;
                } else
                {
                    ai1[i1] = 0;
                }
                l++;
            }
        }

        Bitmap bitmap = Bitmap.createBitmap(j, j, android.graphics.Bitmap.Config.ARGB_8888);
        bitmap.setPixels(ai1, 0, j, 0, 0, j, j);
        return bitmap;
    }

    private static Tile convertBitmap(Bitmap bitmap)
    {
        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
        bitmap.compress(android.graphics.Bitmap.CompressFormat.PNG, 100, bytearrayoutputstream);
        return new Tile(512, 512, bytearrayoutputstream.toByteArray());
    }

    static double[][] convolve(double ad[][], double ad1[])
    {
        int i = (int)Math.floor((double)ad1.length / 2D);
        int j = ad.length;
        int k = j - i * 2;
        int l = -1 + (i + k);
        int ai[] = {
            j, j
        };
        double ad2[][] = (double[][])Array.newInstance(Double.TYPE, ai);
        for(int i1 = 0; i1 < j; i1++)
        {
            int j3;
            int l3;
            for(int i3 = 0; i3 < j; i3++)
            {
                double d1 = ad[i1][i3];
                if(d1 == 0.0D)
                    continue;
                int k3;
                int i4;
                if(l < i1 + i)
                    j3 = l;
                else
                    j3 = i1 + i;
                k3 = j3 + 1;
                if(i > i1 - i)
                    l3 = i;
                else
                    l3 = i1 - i;
                for(i4 = l3; i4 < k3; i4++)
                {
                    double ad5[] = ad2[i4];
                    ad5[i3] = ad5[i3] + d1 * ad1[i4 - (i1 - i)];
                }

            }

        }

        int ai1[] = {
            k, k
        };
        double ad3[][] = (double[][])Array.newInstance(Double.TYPE, ai1);
        for(int j1 = i; j1 < l + 1; j1++)
        {
            int l1;
            int j2;
            for(int k1 = 0; k1 < j; k1++)
            {
                double d = ad2[j1][k1];
                if(d == 0.0D)
                    continue;
                int i2;
                int k2;
                if(l < k1 + i)
                    l1 = l;
                else
                    l1 = k1 + i;
                i2 = l1 + 1;
                if(i > k1 - i)
                    j2 = i;
                else
                    j2 = k1 - i;
                for(k2 = j2; k2 < i2; k2++)
                {
                    double ad4[] = ad3[j1 - i];
                    int l2 = k2 - i;
                    ad4[l2] = ad4[l2] + d * ad1[k2 - (k1 - i)];
                }

            }

        }

        return ad3;
    }

    static double[] generateKernel(int i, double d)
    {
        double ad[] = new double[1 + i * 2];
        for(int j = -i; j <= i; j++)
            ad[j + i] = Math.exp((double)(j * -j) / (d * (2D * d)));

        return ad;
    }

    static Bounds getBounds(Collection collection)
    {
        Iterator iterator = collection.iterator();
        WeightedLatLng weightedlatlng = (WeightedLatLng)iterator.next();
        double d = weightedlatlng.getPoint().x;
        double d1 = weightedlatlng.getPoint().x;
        double d2 = weightedlatlng.getPoint().y;
        double d3 = weightedlatlng.getPoint().y;
        do
        {
            if(!iterator.hasNext())
                break;
            WeightedLatLng weightedlatlng1 = (WeightedLatLng)iterator.next();
            double d4 = weightedlatlng1.getPoint().x;
            double d5 = weightedlatlng1.getPoint().y;
            if(d4 < d)
                d = d4;
            if(d4 > d1)
                d1 = d4;
            if(d5 < d2)
                d2 = d5;
            if(d5 > d3)
                d3 = d5;
        } while(true);
        return new Bounds(d, d1, d2, d3);
    }

    private double[] getMaxIntensities(int i)
    {
        double ad[] = new double[22];
        for(int j = 5; j < 11; j++)
        {
            ad[j] = getMaxValue(mData, mBounds, i, (int)(1280D * Math.pow(2D, j - 3)));
            if(j != 5)
                continue;
            for(int l = 0; l < j; l++)
                ad[l] = ad[j];

        }

        for(int k = 11; k < 22; k++)
            ad[k] = ad[10];

        return ad;
    }

    static double getMaxValue(Collection collection, Bounds bounds, int i, int j)
    {
        double d = bounds.minX;
        double d1 = bounds.maxX;
        double d2 = bounds.minY;
        double d3 = bounds.maxY;
        double d4;
        double d5;
        LongSparseArray longsparsearray;
        double d6;
        Iterator iterator;
        if(d1 - d > d3 - d2)
            d4 = d1 - d;
        else
            d4 = d3 - d2;
        d5 = (double)(int)(0.5D + (double)(j / (i * 2))) / d4;
        longsparsearray = new LongSparseArray();
        d6 = 0.0D;
        iterator = collection.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            WeightedLatLng weightedlatlng = (WeightedLatLng)iterator.next();
            double d7 = weightedlatlng.getPoint().x;
            double d8 = weightedlatlng.getPoint().y;
            int k = (int)(d5 * (d7 - d));
            int l = (int)(d5 * (d8 - d2));
            LongSparseArray longsparsearray1 = (LongSparseArray)longsparsearray.get(k);
            if(longsparsearray1 == null)
            {
                longsparsearray1 = new LongSparseArray();
                longsparsearray.put(k, longsparsearray1);
            }
            Double double1 = (Double)longsparsearray1.get(l);
            if(double1 == null)
                double1 = Double.valueOf(0.0D);
            Double double2 = Double.valueOf(double1.doubleValue() + weightedlatlng.getIntensity());
            longsparsearray1.put(l, double2);
            if(double2.doubleValue() > d6)
                d6 = double2.doubleValue();
        } while(true);
        return d6;
    }

    private static Collection wrapData(Collection collection)
    {
        ArrayList arraylist = new ArrayList();
        for(Iterator iterator = collection.iterator(); iterator.hasNext(); arraylist.add(new WeightedLatLng((LatLng)iterator.next())));
        return arraylist;
    }

    public Tile getTile(int i, int j, int k)
    {
        double d1;
        double d2;
        double d3;
        double d4;
        double d5;
        double d6;
        double d7;
        Object obj;
        double d = 1.0D / Math.pow(2D, k);
        d1 = (d * (double)mRadius) / 512D;
        d2 = (d + 2D * d1) / (double)(512 + 2 * mRadius);
        d3 = d * (double)i - d1;
        d4 = d1 + d * (double)(i + 1);
        d5 = d * (double)j - d1;
        d6 = d1 + d * (double)(j + 1);
        d7 = 0.0D;
        obj = new ArrayList();
        if(d3 >= 0.0D) goto _L2; else goto _L1
_L1:
        Bounds bounds = new Bounds(1.0D + d3, 1.0D, d5, d6);
        d7 = -1D;
        obj = mTree.search(bounds);
_L4:
        Bounds bounds1;
        bounds1 = new Bounds(d3, d4, d5, d6);
        if(!bounds1.intersects(new Bounds(mBounds.minX - d1, d1 + mBounds.maxX, mBounds.minY - d1, d1 + mBounds.maxY)))
            return TileProvider.NO_TILE;
        break; /* Loop/switch isn't completed */
_L2:
        if(d4 > 1.0D)
        {
            Bounds bounds2 = new Bounds(0.0D, d4 - 1.0D, d5, d6);
            d7 = 1.0D;
            obj = mTree.search(bounds2);
        }
        if(true) goto _L4; else goto _L3
_L3:
        Collection collection = mTree.search(bounds1);
        if(collection.isEmpty())
            return TileProvider.NO_TILE;
        int ai[] = {
            512 + 2 * mRadius, 512 + 2 * mRadius
        };
        double ad[][] = (double[][])Array.newInstance(Double.TYPE, ai);
        for(Iterator iterator = collection.iterator(); iterator.hasNext();)
        {
            WeightedLatLng weightedlatlng1 = (WeightedLatLng)iterator.next();
            Point point1 = weightedlatlng1.getPoint();
            int j1 = (int)((point1.x - d3) / d2);
            int k1 = (int)((point1.y - d5) / d2);
            double ad2[] = ad[j1];
            ad2[k1] = ad2[k1] + weightedlatlng1.getIntensity();
        }

        for(Iterator iterator1 = ((Collection) (obj)).iterator(); iterator1.hasNext();)
        {
            WeightedLatLng weightedlatlng = (WeightedLatLng)iterator1.next();
            Point point = weightedlatlng.getPoint();
            int l = (int)(((d7 + point.x) - d3) / d2);
            int i1 = (int)((point.y - d5) / d2);
            double ad1[] = ad[l];
            ad1[i1] = ad1[i1] + weightedlatlng.getIntensity();
        }

        return convertBitmap(colorize(convolve(ad, mKernel), mColorMap, mMaxIntensity[k]));
    }

    public void setData(Collection collection)
    {
        setWeightedData(wrapData(collection));
    }

    public void setGradient(Gradient gradient)
    {
        mGradient = gradient;
        mColorMap = gradient.generateColorMap(mOpacity);
    }

    public void setOpacity(double d)
    {
        mOpacity = d;
        setGradient(mGradient);
    }

    public void setRadius(int i)
    {
        mRadius = i;
        mKernel = generateKernel(mRadius, (double)mRadius / 3D);
        mMaxIntensity = getMaxIntensities(mRadius);
    }

    public void setWeightedData(Collection collection)
    {
        mData = collection;
        if(mData.isEmpty())
            throw new IllegalArgumentException("No input points.");
        mBounds = getBounds(mData);
        mTree = new PointQuadTree(mBounds);
        WeightedLatLng weightedlatlng;
        for(Iterator iterator = mData.iterator(); iterator.hasNext(); mTree.add(weightedlatlng))
            weightedlatlng = (WeightedLatLng)iterator.next();

        mMaxIntensity = getMaxIntensities(mRadius);
    }

    public static final Gradient DEFAULT_GRADIENT;
    private static final int DEFAULT_GRADIENT_COLORS[];
    private static final float DEFAULT_GRADIENT_START_POINTS[] = {
        0.2F, 1.0F
    };
    private static final int DEFAULT_MAX_ZOOM = 11;
    private static final int DEFAULT_MIN_ZOOM = 5;
    public static final double DEFAULT_OPACITY = 0.69999999999999996D;
    public static final int DEFAULT_RADIUS = 20;
    private static final int MAX_RADIUS = 50;
    private static final int MAX_ZOOM_LEVEL = 22;
    private static final int MIN_RADIUS = 10;
    private static final int SCREEN_SIZE = 1280;
    private static final int TILE_DIM = 512;
    static final double WORLD_WIDTH = 1D;
    private Bounds mBounds;
    private int mColorMap[];
    private Collection mData;
    private Gradient mGradient;
    private double mKernel[];
    private double mMaxIntensity[];
    private double mOpacity;
    private int mRadius;
    private PointQuadTree mTree;

    static 
    {
        int ai[] = new int[2];
        ai[0] = Color.rgb(102, 225, 0);
        ai[1] = Color.rgb(255, 0, 0);
        DEFAULT_GRADIENT_COLORS = ai;
        DEFAULT_GRADIENT = new Gradient(DEFAULT_GRADIENT_COLORS, DEFAULT_GRADIENT_START_POINTS);
    }

}
