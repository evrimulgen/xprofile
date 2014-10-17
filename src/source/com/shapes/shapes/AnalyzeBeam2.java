// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.shapes.shapes;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.*;
import android.net.Uri;
import android.os.Bundle;
import android.text.*;
import android.text.method.LinkMovementMethod;
import android.view.*;
import android.widget.*;
import java.text.DecimalFormat;

// Referenced classes of package com.shapes.shapes:
//            ShapeTable, Shape, About, BeamAnalysis, 
//            MainActivity, FeetInchesType, Analyze, Options

public class AnalyzeBeam2 extends Activity
{

    public AnalyzeBeam2()
    {
        settings = new float[7];
    }

    private void analyze(double d, double d1, double d2, double d3, double d4, double d5, double d6, 
            double d7, double d8, float f)
    {
        Shape shape = table.getSelected(this);
        double ad[] = {
            0.0D, 1.0D, 2D
        };
        double ad1[] = {
            0.0D, 1.0D, 2D, 3D
        };
        double d9 = shape.get_ix();
        double d10 = shape.get_j();
        double d11 = shape.get_zx();
        double d12 = shape.get_sx();
        double d13 = shape.get_ry();
        double d14 = shape.get_rts();
        double d15 = shape.get_ho();
        double d16 = shape.get_bf_2tf();
        double d17 = shape.get_h_tw();
        double d18 = shape.get_iyc_over_iy();
        double d19 = shape.get_aw();
        double d20 = shape.get_rt();
        double d21 = shape.get_aweb();
        double d22 = 0.0D;
        double d23 = 0.0D;
        boolean flag = true;
        String as[] = new String[10];
        String as1[] = new String[10];
        new String();
        new String();
        double d24 = d9 * (double)29000;
        double d25 = Math.abs(d7 + d8);
        double d26 = (5D * d25 * Math.pow(d, 4D)) / 12D / (384D * d24);
        double d27 = (5D * d8 * Math.pow(d, 4D)) / 12D / (384D * d24);
        if(f > 0.0F)
            d25 = Math.abs(1.2D * d7 + 1.6000000000000001D * d8);
        double d28 = (d25 * d) / 12D / 2D;
        double d29 = (d25 * Math.pow(d / 12D, 2D)) / 8D;
        double d30 = (double)Math.round(100D * ((12.5D * Math.pow(d, 2D)) / (12.5D * Math.pow(d, 2D) - 1.5D * Math.pow(d1, 2D)))) / 100D;
        as[1] = (new StringBuilder("C<sub>b</sub> = ")).append(Double.toString(d30)).toString();
        double d31 = 0.38D * Math.sqrt((double)29000 / d2);
        double d32 = 1.0D * Math.sqrt((double)29000 / d2);
        double d33 = 3.7599999999999998D * Math.sqrt((double)29000 / d2);
        double d34 = 5.7000000000000002D * Math.sqrt((double)29000 / d2);
        double d35;
        String s;
        double d36;
        String s1;
        double d37;
        double d38;
        double d44;
        double d45;
        double d46;
        double d47;
        double d48;
        DecimalFormat decimalformat;
        String s2;
        String s4;
        String s5;
        String s6;
        String s7;
        String s9;
        String s10;
        String s11;
        String s12;
        if(d16 <= d31)
        {
            d35 = ad[0];
            s = "Compact";
        } else
        if(d16 <= d32)
        {
            d35 = ad[1];
            s = "Noncompact";
        } else
        {
            d35 = ad[2];
            s = "Slender";
        }
        as[2] = (new StringBuilder("Flange is ")).append(s).toString();
        if(d17 <= d33)
        {
            d36 = ad[0];
            s1 = "Compact";
        } else
        if(d17 <= d34)
        {
            d36 = ad[1];
            s1 = "Noncompact";
        } else
        {
            d36 = ad[2];
            s1 = "Slender";
        }
        as[3] = (new StringBuilder("Web is ")).append(s1).toString();
        if(d35 == ad[0] && d36 == ad[0])
            d37 = ad1[0];
        else
        if(d36 == ad[0])
            d37 = ad1[1];
        else
        if(d36 == ad[1])
            d37 = ad1[2];
        else
            d37 = ad1[3];
        d38 = d2 * d11;
        as[6] = "(eqn F2-1)";
        if(d37 == ad1[0])
        {
            double d63 = 1.76D * d13 * Math.sqrt((double)29000 / d2);
            double d64 = ((1.95D * d14 * (double)29000) / (0.69999999999999996D * d2)) * Math.sqrt(d10 / (d12 * d15) + Math.sqrt(Math.pow(d10 / (d12 * d15), 2D) + 6.7599999999999998D * Math.pow((0.69999999999999996D * d2) / (double)29000, 2D)));
            as[4] = (new StringBuilder("L<sub>p</sub> = ")).append((double)Math.round(100D * (d63 / 12D)) / 100D).append(" ft. (eqn F2-5)").toString();
            as[5] = (new StringBuilder("L<sub>r</sub> = ")).append((double)Math.round(100D * (d64 / 12D)) / 100D).append(" ft. (eqn F2-6)").toString();
            DecimalFormat decimalformat1;
            double d49;
            double d50;
            double d51;
            double d65;
            if(d1 <= d63)
            {
                d65 = d38;
                as[6] = "(eqn F2-1)";
            } else
            if(d1 <= d64)
            {
                d65 = d30 * (d38 - ((d38 - d12 * (0.69999999999999996D * d2)) * (d1 - d63)) / (d64 - d63));
                if(d65 > d38)
                    d65 = d38;
                as[6] = "(eqn F2-2)";
            } else
            {
                d65 = d12 * (((d30 * Math.pow(3.1415926535897931D, 2D) * (double)29000) / Math.pow(d1 / d14, 2D)) * Math.sqrt(1.0D + ((0.078D * d10) / (d12 * d15)) * Math.pow(d1 / d14, 2D)));
                as[6] = "(eqn F2-3)";
            }
            d23 = minimum(d38, d65) / 12D;
        } else
        if(d37 == ad1[1])
        {
            double d60 = 1.76D * d13 * Math.sqrt((double)29000 / d2);
            double d61 = ((1.95D * d14 * (double)29000) / (0.69999999999999996D * d2)) * Math.sqrt(d10 / (d12 * d15) + Math.sqrt(Math.pow(d10 / (d12 * d15), 2D) + 6.7599999999999998D * Math.pow((0.69999999999999996D * d2) / (double)29000, 2D)));
            as[4] = (new StringBuilder("L<sub>p</sub> = ")).append((double)Math.round(100D * (d60 / 12D)) / 100D).append("ft. (eqn F2-5)").toString();
            as[5] = (new StringBuilder("L<sub>r</sub> = ")).append((double)Math.round(100D * (d61 / 12D)) / 100D).append("ft. (eqn F2-6)").toString();
            double d62;
            if(d1 <= d60)
            {
                d62 = d38;
                as[6] = "(eqn F2-1)";
            } else
            if(d1 <= d61)
            {
                d62 = d30 * (d38 - ((d38 - d12 * (0.69999999999999996D * d2)) * (d1 - d60)) / (d61 - d60));
                if(d62 > d38)
                    d62 = d38;
                as[6] = "(eqn F2-2)";
            } else
            {
                d62 = d12 * (((d30 * Math.pow(3.1415926535897931D, 2D) * (double)29000) / Math.pow(d1 / d14, 2D)) * Math.sqrt(1.0D + ((0.078D * d10) / (d12 * d15)) * Math.pow(d1 / d14, 2D)));
                as[6] = "(eqn F2-3)";
            }
            if(d35 == ad[1])
            {
                d22 = d38 - ((d38 - d12 * (0.69999999999999996D * d2)) * (d16 - d31)) / (d32 - d31);
                as1[0] = "(eqn F3-1)";
            } else
            if(d35 == ad[2])
            {
                d22 = (d12 * ((4D / Math.sqrt(d17)) * (0.90000000000000002D * (double)29000))) / Math.pow(d16, 2D);
                as1[0] = "(eqn F3-2)";
            }
            d23 = minimum(d38, d62, d22) / 12D;
            if(d23 == d22)
                as[6] = new String(as1[0]);
        } else
        if(d37 == ad1[2])
        {
            double d52 = minimum(d2 * d11, d12 * (1.6000000000000001D * d2));
            double d53 = d2 * d12;
            double d54;
            double d55;
            double d56;
            double d57;
            double d59;
            if(d18 > 0.23000000000000001D)
            {
                if(d17 <= d33)
                    d54 = d52 / d53;
                else
                    d54 = minimum(d52 / d53 - ((d52 / d53 - 1.0D) * (d17 - d33)) / (d34 - d33), d52 / d53);
            } else
            {
                d54 = 1.0D;
            }
            d55 = d54 * d53;
            as[6] = "(eqn F4-1)";
            d56 = 1.1000000000000001D * d20 * Math.sqrt((double)29000 / d2);
            d57 = ((1.95D * d20 * (double)29000) / (0.69999999999999996D * d2)) * Math.sqrt(d10 / (d12 * d15) + Math.sqrt(Math.pow(d10 / (d12 * d15), 2D) + 6.7599999999999998D * Math.pow((0.69999999999999996D * d2) / (double)29000, 2D)));
            as[4] = (new StringBuilder("L<sub>p</sub> = ")).append((double)Math.round(100D * (d56 / 12D)) / 100D).append("ft. (eqn F2-5)").toString();
            as[5] = (new StringBuilder("L<sub>r</sub> = ")).append((double)Math.round(100D * (d57 / 12D)) / 100D).append("ft. (eqn F2-6)").toString();
            if(d1 <= d56)
                d59 = d55;
            else
            if(d1 <= d57)
            {
                d59 = d30 * (d54 * d53 - ((d54 * d53 - d12 * (0.69999999999999996D * d2)) * (d1 - d56)) / (d57 - d56));
                as[6] = "(eqn F4-2)";
            } else
            {
                double d58;
                if(d18 <= 0.23000000000000001D)
                    d58 = (d30 * Math.pow(3.1415926535897931D, 2D) * (double)29000) / Math.pow(d1 / d20, 2D);
                else
                    d58 = ((d30 * Math.pow(3.1415926535897931D, 2D) * (double)29000) / Math.pow(d1 / d20, 2D)) * Math.sqrt(1.0D + ((0.078D * d10) / (d12 * d15)) * Math.pow(d1 / d20, 2D));
                d59 = d58 * d12;
                as[6] = "(eqnS. F4-4, F4-5)";
            }
            if(d35 == ad[0])
                d22 = d55;
            else
            if(d35 == ad[1])
            {
                d22 = d55 - ((d55 - d12 * (0.69999999999999996D * d2)) * (d16 - d31)) / (d32 - d31);
                as1[0] = "(eqn F4-13)";
            } else
            if(d35 == ad[2])
            {
                d22 = (d12 * ((4D / Math.sqrt(d17)) * (0.90000000000000002D * (double)29000))) / Math.pow(d16, 2D);
                as1[0] = "(eqn F4-14)";
            }
            d23 = minimum(d55, d59, d22) / 12D;
            if(d23 == d22)
                as[6] = new String(as1[0]);
        } else
        if(d37 == ad1[3])
        {
            if(d19 > 10D)
                d19 = 10D;
            double d39 = 1.0D - (d19 / (12D + 300D * d19)) * (d17 - 5.7000000000000002D * Math.sqrt((double)29000 / d2));
            double d40 = d12 * (d39 * d2);
            as[6] = "(eqn F5-1)";
            double d41 = 1.1000000000000001D * d20 * Math.sqrt((double)29000 / d2);
            double d42 = 3.1415926535897931D * d20 * Math.sqrt((double)29000 / (0.69999999999999996D * d2));
            as[4] = (new StringBuilder("L<sub>p</sub> = ")).append(d41 / 12D).append("ft. (eqn F4-7)").toString();
            as[5] = (new StringBuilder("L<sub>r</sub> = ")).append(d41 / 12D).append("ft. (eqn F5-5)").toString();
            double d43;
            if(d1 <= d41)
                d43 = d40;
            else
            if(d1 <= d42)
            {
                d43 = d12 * (d39 * minimum(d30 * (d2 - (0.29999999999999999D * d2 * (d1 - d41)) / (d42 - d41)), d2));
                as[6] = "(eqnS. F5-2, F5-3)";
            } else
            {
                d43 = d12 * (d39 * minimum((d30 * Math.pow(3.1415926535897931D, 2D) * (double)29000) / Math.pow(d1 / d20, 2D), d2));
                as[6] = "(eqnS. F5-2, F5-4)";
            }
            if(d35 == ad[0])
                d22 = d40;
            else
            if(d35 == ad[1])
            {
                d22 = d39 * d12 * (d2 - (0.29999999999999999D * d2 * (d16 - d31)) / (d32 - d31));
                as1[0] = "(eqn F5-8)";
            } else
            if(d35 == ad[2])
            {
                d22 = ((4D / Math.sqrt(d17)) * (0.90000000000000002D * (d39 * d12) * (double)29000)) / Math.pow(d16, 2D);
                as1[0] = "(eqn F5-9)";
            }
            d23 = minimum(d40, d43, d22) / 12D;
            if(d23 == d22)
                as[6] = as1[0];
        }
        if(d17 <= 2.2400000000000002D * Math.sqrt((double)29000 / d2))
        {
            d44 = 1.0D;
            d45 = 1.0D;
            d46 = 1.5D;
            as1[1] = "C<sub>v</sub> = 1.0 (eqn G2-2)";
            if(f > 0.0F)
                as1[2] = "&#934; = 1.0";
            else
                as1[2] = "&#937; = 1.5";
        } else
        if(d17 <= 1.1000000000000001D * Math.sqrt((double)0x23668 / d2))
        {
            d44 = 1.0D;
            d45 = 0.90000000000000002D;
            d46 = 1.6699999999999999D;
            as1[1] = "C<sub>v</sub> = 1.0 (eqn G2-3)";
            if(f > 0.0F)
                as1[2] = "&#934; = 0.9";
            else
                as1[2] = "&#937; = 1.67";
        } else
        if(d17 <= 1.3700000000000001D * Math.sqrt((double)0x23668 / d2))
        {
            d44 = (1.1000000000000001D * Math.sqrt((double)0x23668 / d2)) / d17;
            d45 = 0.90000000000000002D;
            d46 = 1.6699999999999999D;
            as1[1] = (new StringBuilder("C<sub>v</sub> = ")).append(d44).append(" (eqn G2-4)").toString();
            if(f > 0.0F)
                as1[2] = "&#934; = 0.9";
            else
                as1[2] = "&#937; = 1.67";
        } else
        {
            d44 = (7.5499999999999998D * (double)29000) / (d2 * Math.pow(d17, 2D));
            d45 = 0.90000000000000002D;
            d46 = 1.6699999999999999D;
            as1[1] = (new StringBuilder("C<sub>v</sub> = ")).append(d44).append(" (eqn G2-5)").toString();
            if(f > 0.0F)
                as1[2] = "&#934; = 0.9";
            else
                as1[2] = "&#937; = 1.67";
        }
        d47 = d44 * (d21 * (d2 * (0.59999999999999998D * d45)));
        d48 = (d44 * (d21 * (0.59999999999999998D * d2))) / d46;
        decimalformat = new DecimalFormat("#,##0.00");
        decimalformat1 = new DecimalFormat("#,##0.000");
        d49 = (double)Math.round(100D * d17) / 100D;
        s2 = (new StringBuilder(String.valueOf((new StringBuilder(String.valueOf((new StringBuilder(String.valueOf((new StringBuilder(String.valueOf((new StringBuilder(String.valueOf((new StringBuilder(String.valueOf((new StringBuilder(String.valueOf((new StringBuilder(String.valueOf((new StringBuilder(String.valueOf((new StringBuilder(String.valueOf(""))).append("<b>").append(shape.get_designation()).append("</b><br/>").toString()))).append("Span = ").append(d / 12D).append(" ft.<br/>").toString()))).append("Yield Strength = F<sub>y</sub> ").append(d2).append(" ksi<br/>").toString()))).append("Dead Load = ").append(d7).append(" klf<br/>").toString()))).append("Live Load = ").append(d8).append(" klf<br/>").toString()))).append("<br/>").toString()))).append("<u><b>Shear Check</b></u><br/>").toString()))).append("h/t<sub>w</sub> = ").append(d49).append("<br/>").toString()))).append(as1[1]).append("<br/>").toString()))).append(as1[2]).append("<br/>").toString();
        if(f > 0.0F)
        {
            String s16 = (new StringBuilder(String.valueOf((new StringBuilder(String.valueOf(s2))).append("Ultimate Shear Load, V<sub>u</sub> = ").append(decimalformat.format(d28)).append(" k").toString()))).append("<br/>Factored Capcity, &#934;V<sub>n</sub> = ").append(decimalformat.format(d47)).append(" k (eqn G2-1)").toString();
            if(d28 <= d47)
            {
                s4 = (new StringBuilder(String.valueOf(s16))).append("<br/>Shear is OK <br/>").toString();
            } else
            {
                s4 = (new StringBuilder(String.valueOf(s16))).append("<br/>Shear is NO GOOD <br/>").toString();
                flag = false;
            }
        } else
        {
            String s3 = (new StringBuilder(String.valueOf((new StringBuilder(String.valueOf(s2))).append("<br/>Actual Shear Load, V = ").append(decimalformat.format(d28)).append(" k").toString()))).append("<br/>Allowable Capcity, V<sub>n</sub>/&#937; = ").append(decimalformat.format(d48)).append(" k (eqn G2-1)").toString();
            if(d28 <= d47)
            {
                s4 = (new StringBuilder(String.valueOf(s3))).append("<br/>Shear is OK<br/>").toString();
            } else
            {
                s4 = (new StringBuilder(String.valueOf(s3))).append("<br/>Shear is NO GOOD<br/>").toString();
                flag = false;
            }
        }
        s5 = (new StringBuilder(String.valueOf(s4))).append("<br/><u><b>Moment Check</b></u><br/>").toString();
        if(f > 0.0F)
            s6 = (new StringBuilder(String.valueOf(s5))).append("&#934; = 0.9<br/>").toString();
        else
            s6 = (new StringBuilder(String.valueOf(s5))).append("&#937; = 1.67<br/>").toString();
        s7 = (new StringBuilder(String.valueOf((new StringBuilder(String.valueOf((new StringBuilder(String.valueOf((new StringBuilder(String.valueOf((new StringBuilder(String.valueOf((new StringBuilder(String.valueOf(s6))).append(as[1]).append("<br/>").toString()))).append(as[2]).append("<br/>").toString()))).append(as[3]).append("<br/>").toString()))).append("Unbraced Length, L<sub>b</sub> = ").append((double)Math.round(100D * (d1 / 12D)) / 100D).append(" ft.<br/>").toString()))).append(as[4]).append("<br/>").toString()))).append(as[5]).append("<br/>").toString();
        if(f > 0.0F)
        {
            String s15 = (new StringBuilder(String.valueOf((new StringBuilder(String.valueOf(s7))).append("<br/>Ultimate Moment, M<sub>u</sub> = ").append(decimalformat.format(d29)).append(" k-ft").toString()))).append("<br/>Factored Capcity, &#934;M<sub>n</sub> = ").append(decimalformat.format(0.90000000000000002D * d23)).append(" k-ft ").append(as[6]).toString();
            if(d29 <= 0.90000000000000002D * d23)
            {
                s9 = (new StringBuilder(String.valueOf(s15))).append("<br/>Moment is OK<br/>").toString();
            } else
            {
                s9 = (new StringBuilder(String.valueOf(s15))).append("<br/>Moment is NO GOOD<br/>").toString();
                flag = false;
            }
        } else
        {
            String s8 = (new StringBuilder(String.valueOf((new StringBuilder(String.valueOf(s7))).append("<br/>Actual Moment, M = ").append(decimalformat.format(d29)).append(" k-ft").toString()))).append("<br/>Allowable Capcity, M<sub>n</sub>/&#937; = ").append(decimalformat.format(d23 / 1.6699999999999999D)).append(" k-ft ").append(as[6]).toString();
            if(d29 <= d23 / 1.6699999999999999D)
            {
                s9 = (new StringBuilder(String.valueOf(s8))).append("<br/>Positive Moment is OK<br/>").toString();
            } else
            {
                s9 = (new StringBuilder(String.valueOf(s8))).append("<br/>Positive Moment is NO GOOD<br/>").toString();
                flag = false;
            }
        }
        d50 = 0.0D;
        d51 = 0.0D;
        if(d5 > 0.0D)
            d50 = d / d5;
        if(d6 > 0.0D)
            d50 = minimum(d50, d6);
        if(d3 > 0.0D)
            d51 = d / d3;
        if(d4 > 0.0D)
            d51 = minimum(d51, d4);
        s10 = (new StringBuilder(String.valueOf((new StringBuilder(String.valueOf(s9))).append("<br/><u><b>Deflection Check</b></u><br/>").toString()))).append("Actual Live Load Deflection = ").append(decimalformat.format(d27)).append('"').append("<br/>").toString();
        if(d51 != 0.0D)
        {
            String s14 = (new StringBuilder(String.valueOf(s10))).append("Allowable Deflection = ").append(decimalformat1.format(d51)).append('"').append("<br/>").toString();
            if(d27 <= d51)
            {
                s10 = (new StringBuilder(String.valueOf(s14))).append("Live Load Deflection is OK<br/>").toString();
            } else
            {
                s10 = (new StringBuilder(String.valueOf(s14))).append("Live Load Deflection is NO GOOD<br/>").toString();
                flag = false;
            }
        }
        s11 = (new StringBuilder(String.valueOf(s10))).append("<br/>Actual Total Load Deflection = ").append(decimalformat.format(d26)).append('"').append("<br/>").toString();
        if(d50 != 0.0D)
        {
            String s13 = (new StringBuilder(String.valueOf(s11))).append("Allowable Deflection = ").append(decimalformat1.format(d50)).append('"').append("<br/>").toString();
            if(d26 <= d50)
            {
                s11 = (new StringBuilder(String.valueOf(s13))).append("Total Load Deflection is OK<br/>").toString();
            } else
            {
                s11 = (new StringBuilder(String.valueOf(s13))).append("Total Load Deflection is NO GOOD<br/>").toString();
                flag = false;
            }
        }
        if(flag)
            s12 = (new StringBuilder(String.valueOf(s11))).append("<br/><b>Beam is OK</b><br/>").toString();
        else
            s12 = (new StringBuilder(String.valueOf(s11))).append("<br/><b>Beam is <u>NOT</u> OK</b><br/>").toString();
        showResults(this, (new StringBuilder(String.valueOf(s12))).append("</br>").toString());
    }

    private boolean isValid(double d, double d1)
    {
        boolean flag = true;
        if(d + d1 <= 0.0D)
        {
            showDialog(this, "Invalid load.");
            flag = false;
        }
        return flag;
    }

    private double minimum(double d, double d1)
    {
        if(d < d1)
            return d;
        else
            return d1;
    }

    private double minimum(double d, double d1, double d2)
    {
        if(d < d1 && d < d2)
            return d;
        if(d1 < d && d1 < d2)
            return d1;
        else
            return d2;
    }

    public boolean about_click()
    {
        Intent intent = new Intent(this, com/shapes/shapes/About);
        intent.setFlags(0x4000000);
        startActivity(intent);
        overridePendingTransition(0x7f040001, 0x7f040000);
        return true;
    }

    public void analyze_click(View view)
    {
        ((LinearLayout)findViewById(0x7f0a0005)).requestFocus();
        EditText edittext4;
        EditText edittext5;
        int i;
        double d;
        int j;
        double d1;
        EditText edittext = (EditText)findViewById(0x7f0a0023);
        EditText edittext1 = (EditText)findViewById(0x7f0a0022);
        EditText edittext2 = (EditText)findViewById(0x7f0a001e);
        EditText edittext3 = (EditText)findViewById(0x7f0a001c);
        edittext4 = (EditText)findViewById(0x7f0a001d);
        edittext5 = (EditText)findViewById(0x7f0a0027);
        i = Integer.parseInt(edittext.getText().toString().trim());
        d = Double.parseDouble(edittext1.getText().toString().replace("\"", ""));
        j = Integer.parseInt(edittext2.getText().toString().trim());
        d1 = Double.parseDouble(edittext3.getText().toString().replace("\"", ""));
        double d2;
        double d3;
        d2 = 0.0D;
        d3 = 0.0D;
        String s;
        String s1;
        s = edittext4.getText().toString().trim();
        s1 = edittext5.getText().toString().trim();
        if(s.length() <= 0) goto _L2; else goto _L1
_L1:
        d2 = Double.parseDouble(s);
_L10:
        if(s1.length() <= 0) goto _L4; else goto _L3
_L3:
        d3 = Double.parseDouble(s1);
_L8:
        if(!isValid(d2, d3)) goto _L6; else goto _L5
_L5:
        double d4;
        double d5;
        double d6;
        float f;
        d4 = beam1.getMainSpan();
        d5 = beam1.getTopFlange();
        d6 = beam1.getYieldStrength();
        f = settings[6];
        beam1.setLiveLimit(i);
        beam1.setLiveAbsolute(d);
        beam1.setTotalLimit(j);
        beam1.setTotalAbsolute(d1);
        beam1.setDeadLoad(d2);
        beam1.setLiveLoad(d3);
          goto _L7
_L2:
        showDialog(this, "Invalid load.");
        continue; /* Loop/switch isn't completed */
_L7:
        if(d5 == 0.0D)
            d5 = d4;
        try
        {
            table.updateBeamAnalysis(beam1, this);
            analyze(d4, d5, d6, i, d, j, d1, d2, d3, f);
            return;
        }
        catch(Exception exception)
        {
            Toast.makeText(this, exception.toString(), 1).show();
        }
        return;
_L4:
        showDialog(this, "Invalid load.");
          goto _L8
_L6:
        return;
        if(true) goto _L10; else goto _L9
_L9:
    }

    public void calculate_click(View view)
    {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        final ScrollView layout = (ScrollView)LayoutInflater.from(this).inflate(0x7f030007, null);
        builder.setTitle(Html.fromHtml("<font color='#000000'>Calculate Loads</font>"));
        builder.setView(layout);
        builder.setPositiveButton("OK", new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i)
            {
                new String();
                double d = 0.0D;
                EditText edittext = (EditText)layout.findViewById(0x7f0a0054);
                EditText edittext1 = (EditText)layout.findViewById(0x7f0a0053);
                EditText edittext2 = (EditText)layout.findViewById(0x7f0a0056);
                type.setDisplayValue("", 4, FeetInchesType.FeetInches.Inches1, FeetInchesType.LengthUnitsType.InchSymbol);
                String s = edittext.getText().toString();
                int j = s.length();
                int k = 0;
                if(j > 0)
                    k = Integer.parseInt(s);
                String s1 = edittext1.getText().toString();
                int l = s1.length();
                int i1 = 0;
                if(l > 0)
                    i1 = Integer.parseInt(s1);
                String s2 = edittext2.getText().toString();
                type.setDisplayValue(s2, 4, FeetInchesType.FeetInches.Feet1, FeetInchesType.LengthUnitsType.InchSymbol);
                String s3 = type.getDisplayValue().replace("\"", "");
                if(s3.length() > 0)
                    d = Double.parseDouble(s3);
                EditText edittext3 = (EditText)findViewById(0x7f0a001d);
                EditText edittext4 = (EditText)findViewById(0x7f0a0027);
                double d1 = (d * (double)k) / 12D / 1000D;
                double d2 = (d * (double)i1) / 12D / 1000D;
                edittext3.setText(Double.toString(d1));
                edittext4.setText(Double.toString(d2));
            }

            final AnalyzeBeam2 this$0;
            private final ScrollView val$layout;

            
            {
                this$0 = AnalyzeBeam2.this;
                layout = scrollview;
                super();
            }
        }
);
        builder.setNegativeButton("Cancel", new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i)
            {
                dialoginterface.dismiss();
            }

            final AnalyzeBeam2 this$0;

            
            {
                this$0 = AnalyzeBeam2.this;
                super();
            }
        }
);
        final EditText edt_calc_width_load = (EditText)layout.findViewById(0x7f0a0056);
        edt_calc_width_load.setOnFocusChangeListener(new android.view.View.OnFocusChangeListener() {

            public void onFocusChange(View view1, boolean flag)
            {
                try
                {
                    type.setDisplayValue("0", 4, FeetInchesType.FeetInches.Feet1, FeetInchesType.LengthUnitsType.FtIn);
                    type.setDisplayValue(edt_calc_width_load.getText().toString(), 4, FeetInchesType.FeetInches.Feet1, FeetInchesType.LengthUnitsType.FtIn);
                    String s = type.getDisplayValue();
                    edt_calc_width_load.setText(s);
                    return;
                }
                catch(Exception exception)
                {
                    edt_calc_width_load.setText("");
                }
            }

            final AnalyzeBeam2 this$0;
            private final EditText val$edt_calc_width_load;

            
            {
                this$0 = AnalyzeBeam2.this;
                edt_calc_width_load = edittext;
                super();
            }
        }
);
        builder.show();
    }

    public void clear_click(View view)
    {
        EditText edittext = (EditText)findViewById(0x7f0a0023);
        EditText edittext1 = (EditText)findViewById(0x7f0a0022);
        EditText edittext2 = (EditText)findViewById(0x7f0a001e);
        EditText edittext3 = (EditText)findViewById(0x7f0a001c);
        EditText edittext4 = (EditText)findViewById(0x7f0a001d);
        EditText edittext5 = (EditText)findViewById(0x7f0a0027);
        edittext.setText("0");
        edittext1.setText("0.0000\"");
        edittext2.setText("0");
        edittext3.setText("0.0000\"");
        edittext4.setText("0.0");
        edittext5.setText("0.0");
    }

    public boolean help_click()
    {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(MainActivity.URL));
        startActivity(intent);
        return true;
    }

    public boolean home_click()
    {
        Intent intent = new Intent(this, com/shapes/shapes/MainActivity);
        intent.setFlags(0x4000000);
        startActivity(intent);
        overridePendingTransition(0x7f040001, 0x7f040000);
        return true;
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f030002);
        type = new FeetInchesType();
        table = new ShapeTable();
        beam1 = Analyze.beam;
        settings = table.getSettings(this);
        Options.previous = com/shapes/shapes/AnalyzeBeam2;
        final EditText edt_live_limit = (EditText)findViewById(0x7f0a0023);
        final EditText edt_live_abs = (EditText)findViewById(0x7f0a0022);
        final EditText edt_total_limit = (EditText)findViewById(0x7f0a001e);
        final EditText edt_total_abs = (EditText)findViewById(0x7f0a001c);
        final EditText edt_dead_load = (EditText)findViewById(0x7f0a001d);
        final EditText edt_live_load = (EditText)findViewById(0x7f0a0027);
        edt_live_limit.setOnFocusChangeListener(new android.view.View.OnFocusChangeListener() {

            public void onFocusChange(View view, boolean flag)
            {
                if(flag)
                {
                    int i;
                    int j;
                    EditText edittext;
                    Object aobj[];
                    try
                    {
                        edt_live_limit.setBackgroundResource(0x7f020007);
                        return;
                    }
                    catch(Exception exception)
                    {
                        edt_live_limit.setText("");
                    }
                    break MISSING_BLOCK_LABEL_109;
                }
                i = edt_live_limit.getText().toString().length();
                j = 0;
                if(i <= 0)
                    break MISSING_BLOCK_LABEL_56;
                j = Integer.parseInt(edt_live_limit.getText().toString());
                edittext = edt_live_limit;
                aobj = new Object[1];
                aobj[0] = Integer.valueOf(j);
                edittext.setText(String.format("%4d", aobj));
                edt_live_limit.setBackgroundResource(0x7f020005);
                return;
                edt_live_limit.setBackgroundResource(0x7f020005);
                return;
            }

            final AnalyzeBeam2 this$0;
            private final EditText val$edt_live_limit;

            
            {
                this$0 = AnalyzeBeam2.this;
                edt_live_limit = edittext;
                super();
            }
        }
);
        edt_live_abs.setOnFocusChangeListener(new android.view.View.OnFocusChangeListener() {

            public void onFocusChange(View view, boolean flag)
            {
                if(flag)
                {
                    String s;
                    try
                    {
                        edt_live_abs.setBackgroundResource(0x7f020007);
                        return;
                    }
                    catch(Exception exception)
                    {
                        edt_live_abs.setText("");
                    }
                    break MISSING_BLOCK_LABEL_121;
                }
                type.setDisplayValue("0", 4, FeetInchesType.FeetInches.Inches1, FeetInchesType.LengthUnitsType.InchSymbol);
                if(edt_live_abs.getText().toString().length() > 0)
                    type.setDisplayValue(edt_live_abs.getText().toString(), 4, FeetInchesType.FeetInches.Inches1, FeetInchesType.LengthUnitsType.InchSymbol);
                s = type.getDisplayValue();
                edt_live_abs.setText(s);
                edt_live_abs.setBackgroundResource(0x7f020005);
                return;
                edt_live_abs.setBackgroundResource(0x7f020005);
                return;
            }

            final AnalyzeBeam2 this$0;
            private final EditText val$edt_live_abs;

            
            {
                this$0 = AnalyzeBeam2.this;
                edt_live_abs = edittext;
                super();
            }
        }
);
        edt_total_limit.setOnFocusChangeListener(new android.view.View.OnFocusChangeListener() {

            public void onFocusChange(View view, boolean flag)
            {
                if(flag)
                {
                    int i;
                    int j;
                    EditText edittext;
                    Object aobj[];
                    try
                    {
                        edt_total_limit.setBackgroundResource(0x7f020007);
                        return;
                    }
                    catch(Exception exception)
                    {
                        edt_total_limit.setText("");
                    }
                    break MISSING_BLOCK_LABEL_109;
                }
                i = edt_total_limit.getText().toString().length();
                j = 0;
                if(i <= 0)
                    break MISSING_BLOCK_LABEL_56;
                j = Integer.parseInt(edt_total_limit.getText().toString());
                edittext = edt_total_limit;
                aobj = new Object[1];
                aobj[0] = Integer.valueOf(j);
                edittext.setText(String.format("%4d", aobj));
                edt_total_limit.setBackgroundResource(0x7f020005);
                return;
                edt_total_limit.setBackgroundResource(0x7f020005);
                return;
            }

            final AnalyzeBeam2 this$0;
            private final EditText val$edt_total_limit;

            
            {
                this$0 = AnalyzeBeam2.this;
                edt_total_limit = edittext;
                super();
            }
        }
);
        edt_total_abs.setOnFocusChangeListener(new android.view.View.OnFocusChangeListener() {

            public void onFocusChange(View view, boolean flag)
            {
                if(flag)
                {
                    String s;
                    try
                    {
                        edt_total_abs.setBackgroundResource(0x7f020007);
                        return;
                    }
                    catch(Exception exception)
                    {
                        edt_total_abs.setText("");
                    }
                    break MISSING_BLOCK_LABEL_121;
                }
                type.setDisplayValue("0", 4, FeetInchesType.FeetInches.Inches1, FeetInchesType.LengthUnitsType.InchSymbol);
                if(edt_total_abs.getText().toString().length() > 0)
                    type.setDisplayValue(edt_total_abs.getText().toString(), 4, FeetInchesType.FeetInches.Inches1, FeetInchesType.LengthUnitsType.InchSymbol);
                s = type.getDisplayValue();
                edt_total_abs.setText(s);
                edt_total_abs.setBackgroundResource(0x7f020005);
                return;
                edt_total_abs.setBackgroundResource(0x7f020005);
                return;
            }

            final AnalyzeBeam2 this$0;
            private final EditText val$edt_total_abs;

            
            {
                this$0 = AnalyzeBeam2.this;
                edt_total_abs = edittext;
                super();
            }
        }
);
        edt_dead_load.setOnFocusChangeListener(new android.view.View.OnFocusChangeListener() {

            public void onFocusChange(View view, boolean flag)
            {
                if(flag)
                {
                    int i;
                    float f;
                    EditText edittext;
                    Object aobj[];
                    try
                    {
                        edt_dead_load.setBackgroundResource(0x7f020007);
                        return;
                    }
                    catch(Exception exception)
                    {
                        edt_dead_load.setText("");
                    }
                    break MISSING_BLOCK_LABEL_109;
                }
                i = edt_dead_load.getText().toString().length();
                f = 0.0F;
                if(i <= 0)
                    break MISSING_BLOCK_LABEL_56;
                f = Float.parseFloat(edt_dead_load.getText().toString());
                edittext = edt_dead_load;
                aobj = new Object[1];
                aobj[0] = Float.valueOf(f);
                edittext.setText(String.format("%2.2f", aobj));
                edt_dead_load.setBackgroundResource(0x7f020005);
                return;
                edt_dead_load.setBackgroundResource(0x7f020005);
                return;
            }

            final AnalyzeBeam2 this$0;
            private final EditText val$edt_dead_load;

            
            {
                this$0 = AnalyzeBeam2.this;
                edt_dead_load = edittext;
                super();
            }
        }
);
        edt_live_load.setOnFocusChangeListener(new android.view.View.OnFocusChangeListener() {

            public void onFocusChange(View view, boolean flag)
            {
                if(flag)
                {
                    int i;
                    float f;
                    EditText edittext;
                    Object aobj[];
                    try
                    {
                        edt_live_load.setBackgroundResource(0x7f020007);
                        return;
                    }
                    catch(Exception exception)
                    {
                        edt_live_load.setText("");
                    }
                    break MISSING_BLOCK_LABEL_109;
                }
                i = edt_live_load.getText().toString().length();
                f = 0.0F;
                if(i <= 0)
                    break MISSING_BLOCK_LABEL_56;
                f = Float.parseFloat(edt_live_load.getText().toString());
                edittext = edt_live_load;
                aobj = new Object[1];
                aobj[0] = Float.valueOf(f);
                edittext.setText(String.format("%2.2f", aobj));
                edt_live_load.setBackgroundResource(0x7f020005);
                return;
                edt_live_load.setBackgroundResource(0x7f020005);
                return;
            }

            final AnalyzeBeam2 this$0;
            private final EditText val$edt_live_load;

            
            {
                this$0 = AnalyzeBeam2.this;
                edt_live_load = edittext;
                super();
            }
        }
);
        edt_live_limit.setText(Integer.toString(beam1.getLiveLimit()));
        type.setDisplayValue("360", 4, FeetInchesType.FeetInches.Inches1, FeetInchesType.LengthUnitsType.InchSymbol);
        type.setDisplayValue(Double.toString(beam1.getLiveAbsolute()), 4, FeetInchesType.FeetInches.Inches1, FeetInchesType.LengthUnitsType.InchSymbol);
        edt_live_abs.setText(type.getDisplayValue());
        edt_total_limit.setText(Integer.toString(beam1.getTotalLimit()));
        type.setDisplayValue("240", 4, FeetInchesType.FeetInches.Inches1, FeetInchesType.LengthUnitsType.InchSymbol);
        type.setDisplayValue(Double.toString(beam1.getTotalAbsolute()), 4, FeetInchesType.FeetInches.Inches1, FeetInchesType.LengthUnitsType.InchSymbol);
        edt_total_abs.setText(type.getDisplayValue());
        edt_dead_load.setText(Double.toString(beam1.getDeadLoad()));
        edt_live_load.setText(Double.toString(beam1.getLiveLoad()));
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(0x7f090004, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuitem)
    {
        switch(menuitem.getItemId())
        {
        default:
            return super.onOptionsItemSelected(menuitem);

        case 2131361880: 
            return home_click();

        case 2131361882: 
            help_click();
            return true;

        case 2131361883: 
            about_click();
            return true;

        case 2131361881: 
            options_click();
            return true;
        }
    }

    public boolean options_click()
    {
        Options.previous = com/shapes/shapes/AnalyzeBeam2;
        Intent intent = new Intent(this, com/shapes/shapes/Options);
        intent.setFlags(0x4000000);
        startActivity(intent);
        overridePendingTransition(0x7f040001, 0x7f040000);
        return true;
    }

    public void showDialog(Context context, String s)
    {
        if(myAlertDialog != null && myAlertDialog.isShowing())
        {
            return;
        } else
        {
            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
            View view = ((LayoutInflater)getSystemService("layout_inflater")).inflate(0x7f030008, null);
            TextView textview = (TextView)view.findViewById(0x7f0a0057);
            textview.setMovementMethod(LinkMovementMethod.getInstance());
            textview.setTextSize(12F);
            textview.setText(Html.fromHtml(s));
            builder.setView(view);
            builder.setPositiveButton("OK", new android.content.DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialoginterface, int i)
                {
                    dialoginterface.dismiss();
                }

                final AnalyzeBeam2 this$0;

            
            {
                this$0 = AnalyzeBeam2.this;
                super();
            }
            }
);
            builder.setCancelable(false);
            myAlertDialog = builder.create();
            myAlertDialog.show();
            return;
        }
    }

    public void showResults(Context context, final String message)
    {
        if(myAlertDialog != null && myAlertDialog.isShowing())
        {
            return;
        } else
        {
            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
            View view = ((LayoutInflater)getSystemService("layout_inflater")).inflate(0x7f030008, null);
            TextView textview = (TextView)view.findViewById(0x7f0a0057);
            textview.setMovementMethod(LinkMovementMethod.getInstance());
            textview.setTextSize(12F);
            textview.setText(Html.fromHtml(message));
            builder.setView(view);
            builder.setPositiveButton("OK", new android.content.DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialoginterface, int i)
                {
                    dialoginterface.dismiss();
                }

                final AnalyzeBeam2 this$0;

            
            {
                this$0 = AnalyzeBeam2.this;
                super();
            }
            }
);
            builder.setNegativeButton("Copy to Clipboard", new android.content.DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialoginterface, int i)
                {
                    ((ClipboardManager)getSystemService("clipboard")).setText(Html.fromHtml(message).toString());
                    dialoginterface.dismiss();
                }

                final AnalyzeBeam2 this$0;
                private final String val$message;

            
            {
                this$0 = AnalyzeBeam2.this;
                message = s;
                super();
            }
            }
);
            myAlertDialog = builder.create();
            myAlertDialog.show();
            return;
        }
    }

    private BeamAnalysis beam1;
    private AlertDialog myAlertDialog;
    private float settings[];
    private ShapeTable table;
    private FeetInchesType type;

}
