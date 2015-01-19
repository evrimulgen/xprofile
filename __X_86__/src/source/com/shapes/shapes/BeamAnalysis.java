// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.shapes.shapes;


public class BeamAnalysis
{

    public BeamAnalysis()
    {
        left_cantilever = 0.0D;
        main_span = 0.0D;
        right_cantilever = 0.0D;
        top_flange = 0.0D;
        bottom_flange = 0.0D;
        yield_strength = 0;
        live_limit = 0;
        live_absolute = 0.0D;
        total_limit = 0;
        total_absolute = 0.0D;
        dead_load = 0.0D;
        live_load = 0.0D;
        d = 0.0D;
        bf = 0.0D;
        tf = 0.0D;
        tw = 0.0D;
        result = 0.0D;
    }

    public BeamAnalysis(double d1, double d2, double d3, double d4, double d5, int i, int j, double d6, 
            int k, double d7, double d8, double d9, 
            double d10, double d11, double d12, double d13)
    {
        left_cantilever = d1;
        main_span = d2;
        right_cantilever = d3;
        top_flange = d4;
        bottom_flange = d5;
        yield_strength = i;
        live_limit = j;
        live_absolute = d6;
        total_limit = k;
        total_absolute = d7;
        dead_load = d8;
        live_load = d9;
        d = d10;
        bf = d11;
        tf = d12;
        tw = d13;
    }

    public double analyze()
    {
        return result;
    }

    public double getBottomFlange()
    {
        return bottom_flange;
    }

    public double getDeadLoad()
    {
        return dead_load;
    }

    public double getLeftCantilever()
    {
        return left_cantilever;
    }

    public double getLiveAbsolute()
    {
        return live_absolute;
    }

    public int getLiveLimit()
    {
        return live_limit;
    }

    public double getLiveLoad()
    {
        return live_load;
    }

    public double getMainSpan()
    {
        return main_span;
    }

    public double getRightCantilever()
    {
        return right_cantilever;
    }

    public double getTopFlange()
    {
        return top_flange;
    }

    public double getTotalAbsolute()
    {
        return total_absolute;
    }

    public int getTotalLimit()
    {
        return total_limit;
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

    public void setBottomFlange(double d1)
    {
        bottom_flange = d1;
    }

    public void setDeadLoad(double d1)
    {
        dead_load = d1;
    }

    public void setLeftCantilever(double d1)
    {
        left_cantilever = d1;
    }

    public void setLiveAbsolute(double d1)
    {
        live_absolute = d1;
    }

    public void setLiveLimit(int i)
    {
        live_limit = i;
    }

    public void setLiveLoad(double d1)
    {
        live_load = d1;
    }

    public void setMainSpan(double d1)
    {
        main_span = d1;
    }

    public void setRightCantilever(double d1)
    {
        right_cantilever = d1;
    }

    public void setTopFlange(double d1)
    {
        top_flange = d1;
    }

    public void setTotalAbsolute(double d1)
    {
        total_absolute = d1;
    }

    public void setTotalLimit(int i)
    {
        total_limit = i;
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
    private double bottom_flange;
    private double d;
    private double dead_load;
    private double left_cantilever;
    private double live_absolute;
    private int live_limit;
    private double live_load;
    private double main_span;
    private double result;
    private double right_cantilever;
    private double tf;
    private double top_flange;
    private double total_absolute;
    private int total_limit;
    private double tw;
    private int yield_strength;
}
