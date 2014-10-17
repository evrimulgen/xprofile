// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.shapes.shapes;


public class ColumnAnalysis
{

    public ColumnAnalysis()
    {
        height = 0.0D;
        weak_axis = 0.0D;
        strong_axis = 0.0D;
        yield_strength = 0;
        d = 0.0D;
        bf = 0.0D;
        tf = 0.0D;
        tw = 0.0D;
        result = 0.0D;
    }

    public ColumnAnalysis(double d1, double d2, double d3, int i, 
            double d4, double d5, double d6, double d7)
    {
        height = d1;
        weak_axis = d2;
        strong_axis = d3;
        yield_strength = i;
        d = d4;
        bf = d5;
        tf = d6;
        tw = d7;
    }

    public double analyze()
    {
        return result;
    }

    public double getHeight()
    {
        return height;
    }

    public double getStrongAxis()
    {
        return strong_axis;
    }

    public double getWeakAxis()
    {
        return weak_axis;
    }

    public int getYieldStrength()
    {
        return yield_strength;
    }

    public double get_bf()
    {
        return bf;
    }

    public double get_d()
    {
        return d;
    }

    public double get_tf()
    {
        return tf;
    }

    public double get_tw()
    {
        return tw;
    }

    public void setHeight(double d1)
    {
        height = d1;
    }

    public void setStrongAxis(double d1)
    {
        strong_axis = d1;
    }

    public void setWeakAxis(double d1)
    {
        weak_axis = d1;
    }

    public void setYieldStrength(int i)
    {
        yield_strength = i;
    }

    public void set_bf(double d1)
    {
        bf = d1;
    }

    public void set_d(double d1)
    {
        d = d1;
    }

    public void set_tf(double d1)
    {
        tf = d1;
    }

    public void set_tw(double d1)
    {
        tw = d1;
    }

    private double bf;
    private double d;
    private double height;
    private double result;
    private double strong_axis;
    private double tf;
    private double tw;
    private double weak_axis;
    private int yield_strength;
}
