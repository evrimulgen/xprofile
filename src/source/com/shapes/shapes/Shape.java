// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.shapes.shapes;


public class Shape
{

    public Shape()
    {
        id = 0;
        start_year = 0;
        end_year = 0;
        edition = "";
        designation = "";
        ag = 0.0D;
        d = 0.0D;
        tw = 0.0D;
        bf = 0.0D;
        tf = 0.0D;
        h = 0.0D;
        ho = 0.0D;
        aweb = 0.0D;
        aw = 0.0D;
        bf_2tf = 0.0D;
        h_tw = 0.0D;
        ix = 0.0D;
        zx = 0.0D;
        sx = 0.0D;
        rx = 0.0D;
        iy = 0.0D;
        iyc_over_iy = 0.0D;
        ry = 0.0D;
        j = 0.0D;
        rt = 0.0D;
        rts = 0.0D;
    }

    public Shape(int i, int k, int l, String s, String s1, double d1, 
            double d2, double d3, double d4, double d5, double d6, double d7, double d8, 
            double d9, double d10, double d11, double d12, double d13, double d14, double d15, 
            double d16, double d17, double d18, double d19, double d20, double d21)
    {
        id = i;
        start_year = k;
        end_year = l;
        edition = s;
        designation = s1;
        ag = d1;
        d = d2;
        tw = d3;
        bf = d4;
        tf = d5;
        h = d6;
        ho = d7;
        aweb = d8;
        aw = d9;
        bf_2tf = d10;
        h_tw = d11;
        ix = d12;
        zx = d13;
        sx = d14;
        rx = d15;
        iy = d16;
        iyc_over_iy = d17;
        ry = d18;
        j = d19;
        rt = d20;
        rts = d21;
    }

    public double get_ag()
    {
        return ag;
    }

    public double get_aw()
    {
        return aw;
    }

    public double get_aweb()
    {
        return aweb;
    }

    public double get_bf()
    {
        return bf;
    }

    public double get_bf_2tf()
    {
        return bf_2tf;
    }

    public double get_d()
    {
        return d;
    }

    public String get_designation()
    {
        return designation;
    }

    public String get_edition()
    {
        return edition;
    }

    public int get_end_year()
    {
        return end_year;
    }

    public double get_h()
    {
        return h;
    }

    public double get_h_tw()
    {
        return h_tw;
    }

    public double get_ho()
    {
        return ho;
    }

    public int get_id()
    {
        return id;
    }

    public double get_ix()
    {
        return ix;
    }

    public double get_iy()
    {
        return iy;
    }

    public double get_iyc_over_iy()
    {
        return iyc_over_iy;
    }

    public double get_j()
    {
        return j;
    }

    public double get_rt()
    {
        return rt;
    }

    public double get_rts()
    {
        return rts;
    }

    public double get_rx()
    {
        return rx;
    }

    public double get_ry()
    {
        return ry;
    }

    public int get_start_year()
    {
        return start_year;
    }

    public double get_sx()
    {
        return sx;
    }

    public double get_tf()
    {
        return tf;
    }

    public double get_tw()
    {
        return tw;
    }

    public double get_zx()
    {
        return zx;
    }

    public void set_ag(double d1)
    {
        ag = d1;
    }

    public void set_aw(double d1)
    {
        aw = d1;
    }

    public void set_aweb(double d1)
    {
        aweb = d1;
    }

    public void set_bf(double d1)
    {
        bf = d1;
    }

    public void set_bf_2tf(double d1)
    {
        bf_2tf = d1;
    }

    public void set_d(double d1)
    {
        d = d1;
    }

    public void set_designation(String s)
    {
        designation = s;
    }

    public void set_edition(String s)
    {
        edition = s;
    }

    public void set_end_year(int i)
    {
        end_year = i;
    }

    public void set_h(double d1)
    {
        h = d1;
    }

    public void set_h_tw(double d1)
    {
        h_tw = d1;
    }

    public void set_ho(double d1)
    {
        ho = d1;
    }

    public void set_id(int i)
    {
        id = i;
    }

    public void set_ix(double d1)
    {
        ix = d1;
    }

    public void set_iy(double d1)
    {
        iy = d1;
    }

    public void set_iyc_over_iy(double d1)
    {
        iyc_over_iy = d1;
    }

    public void set_j(double d1)
    {
        j = d1;
    }

    public void set_rt(double d1)
    {
        rt = d1;
    }

    public void set_rts(double d1)
    {
        rts = d1;
    }

    public void set_rx(double d1)
    {
        rx = d1;
    }

    public void set_ry(double d1)
    {
        ry = d1;
    }

    public void set_start_year(int i)
    {
        start_year = i;
    }

    public void set_sx(double d1)
    {
        sx = d1;
    }

    public void set_tf(double d1)
    {
        tf = d1;
    }

    public void set_tw(double d1)
    {
        tw = d1;
    }

    public void set_zx(double d1)
    {
        zx = d1;
    }

    private double ag;
    private double aw;
    private double aweb;
    private double bf;
    private double bf_2tf;
    private double d;
    private String designation;
    private String edition;
    private int end_year;
    private double h;
    private double h_tw;
    private double ho;
    private int id;
    private double ix;
    private double iy;
    private double iyc_over_iy;
    private double j;
    private double rt;
    private double rts;
    private double rx;
    private double ry;
    private int start_year;
    private double sx;
    private double tf;
    private double tw;
    private double zx;
}
