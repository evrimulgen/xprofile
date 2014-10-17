// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.shapes.shapes;

import java.text.DecimalFormat;

public class FeetInchesType
{
    public static final class FeetInches extends Enum
    {

        public static FeetInches valueOf(String s)
        {
            return (FeetInches)Enum.valueOf(com/shapes/shapes/FeetInchesType$FeetInches, s);
        }

        public static FeetInches[] values()
        {
            FeetInches afeetinches[] = ENUM$VALUES;
            int i = afeetinches.length;
            FeetInches afeetinches1[] = new FeetInches[i];
            System.arraycopy(afeetinches, 0, afeetinches1, 0, i);
            return afeetinches1;
        }

        private static final FeetInches ENUM$VALUES[];
        public static final FeetInches Feet1;
        public static final FeetInches Inches1;

        static 
        {
            Feet1 = new FeetInches("Feet1", 0);
            Inches1 = new FeetInches("Inches1", 1);
            FeetInches afeetinches[] = new FeetInches[2];
            afeetinches[0] = Feet1;
            afeetinches[1] = Inches1;
            ENUM$VALUES = afeetinches;
        }

        private FeetInches(String s, int i)
        {
            super(s, i);
        }
    }

    public static final class LengthUnitsType extends Enum
    {

        public static LengthUnitsType valueOf(String s)
        {
            return (LengthUnitsType)Enum.valueOf(com/shapes/shapes/FeetInchesType$LengthUnitsType, s);
        }

        public static LengthUnitsType[] values()
        {
            LengthUnitsType alengthunitstype[] = ENUM$VALUES;
            int i = alengthunitstype.length;
            LengthUnitsType alengthunitstype1[] = new LengthUnitsType[i];
            System.arraycopy(alengthunitstype, 0, alengthunitstype1, 0, i);
            return alengthunitstype1;
        }

        private static final LengthUnitsType ENUM$VALUES[];
        public static final LengthUnitsType Feet;
        public static final LengthUnitsType FeetSymbol;
        public static final LengthUnitsType Ft;
        public static final LengthUnitsType FtIn;
        public static final LengthUnitsType InchAbbrev;
        public static final LengthUnitsType InchSymbol;
        public static final LengthUnitsType Inches;

        static 
        {
            Feet = new LengthUnitsType("Feet", 0);
            Ft = new LengthUnitsType("Ft", 1);
            FeetSymbol = new LengthUnitsType("FeetSymbol", 2);
            Inches = new LengthUnitsType("Inches", 3);
            InchAbbrev = new LengthUnitsType("InchAbbrev", 4);
            InchSymbol = new LengthUnitsType("InchSymbol", 5);
            FtIn = new LengthUnitsType("FtIn", 6);
            LengthUnitsType alengthunitstype[] = new LengthUnitsType[7];
            alengthunitstype[0] = Feet;
            alengthunitstype[1] = Ft;
            alengthunitstype[2] = FeetSymbol;
            alengthunitstype[3] = Inches;
            alengthunitstype[4] = InchAbbrev;
            alengthunitstype[5] = InchSymbol;
            alengthunitstype[6] = FtIn;
            ENUM$VALUES = alengthunitstype;
        }

        private LengthUnitsType(String s, int i)
        {
            super(s, i);
        }
    }


    static int[] $SWITCH_TABLE$com$shapes$shapes$FeetInchesType$FeetInches()
    {
        int ai[] = $SWITCH_TABLE$com$shapes$shapes$FeetInchesType$FeetInches;
        if(ai != null)
            return ai;
        int ai1[] = new int[FeetInches.values().length];
        try
        {
            ai1[FeetInches.Feet1.ordinal()] = 1;
        }
        catch(NoSuchFieldError nosuchfielderror) { }
        try
        {
            ai1[FeetInches.Inches1.ordinal()] = 2;
        }
        catch(NoSuchFieldError nosuchfielderror1) { }
        $SWITCH_TABLE$com$shapes$shapes$FeetInchesType$FeetInches = ai1;
        return ai1;
    }

    static int[] $SWITCH_TABLE$com$shapes$shapes$FeetInchesType$LengthUnitsType()
    {
        int ai[] = $SWITCH_TABLE$com$shapes$shapes$FeetInchesType$LengthUnitsType;
        if(ai != null)
            return ai;
        int ai1[] = new int[LengthUnitsType.values().length];
        try
        {
            ai1[LengthUnitsType.Feet.ordinal()] = 1;
        }
        catch(NoSuchFieldError nosuchfielderror) { }
        try
        {
            ai1[LengthUnitsType.FeetSymbol.ordinal()] = 3;
        }
        catch(NoSuchFieldError nosuchfielderror1) { }
        try
        {
            ai1[LengthUnitsType.Ft.ordinal()] = 2;
        }
        catch(NoSuchFieldError nosuchfielderror2) { }
        try
        {
            ai1[LengthUnitsType.FtIn.ordinal()] = 7;
        }
        catch(NoSuchFieldError nosuchfielderror3) { }
        try
        {
            ai1[LengthUnitsType.InchAbbrev.ordinal()] = 5;
        }
        catch(NoSuchFieldError nosuchfielderror4) { }
        try
        {
            ai1[LengthUnitsType.InchSymbol.ordinal()] = 6;
        }
        catch(NoSuchFieldError nosuchfielderror5) { }
        try
        {
            ai1[LengthUnitsType.Inches.ordinal()] = 4;
        }
        catch(NoSuchFieldError nosuchfielderror6) { }
        $SWITCH_TABLE$com$shapes$shapes$FeetInchesType$LengthUnitsType = ai1;
        return ai1;
    }

    public FeetInchesType()
    {
        feet = 0.0D;
        inches = 0.0D;
        input = "";
    }

    public String formattedLength(String s, int i, FeetInches feetinches, LengthUnitsType lengthunitstype)
    {
        String s1;
        String s2;
        String s3;
        String as[];
        boolean flag;
        s1 = new String();
        s2 = new String();
        s3 = new String();
        as = new String[2];
        as[0] = new String();
        as[1] = new String();
        new String();
        new String();
        flag = true;
        if(s.length() <= 0) goto _L2; else goto _L1
_L1:
        int j;
        double d;
        double d1;
        String s6;
        double d2;
        int k;
        DecimalFormat decimalformat;
        String s16;
        boolean flag3;
        double d4;
        double d5;
        String s15;
        if(s.substring(0, 1).equals("-"))
        {
            int k4 = s.length();
            s = s.substring(1, k4);
            j = -1;
        } else
        {
            j = 1;
        }
        s.replace(",", "");
_L33:
        if(s.length() > 0 && "0123456789.".indexOf(s.charAt(0)) > -1) goto _L4; else goto _L3
_L3:
        if(flag && s.length() > 0) goto _L6; else goto _L5
_L5:
        if(s.length() != 0 || !s3.matches("I")) goto _L8; else goto _L7
_L7:
        feet = 0.0D;
        if(as[0].length() > 0)
            inches = Double.parseDouble(as[0]);
        if(s2.matches("")) goto _L10; else goto _L9
_L9:
        s15 = s2.substring(0, s2.trim().indexOf("/"));
        s16 = s2.substring(1 + s2.trim().indexOf("/"), s2.length());
        flag3 = true;
_L25:
        if(flag3) goto _L12; else goto _L11
_L11:
        inches = inches + Double.parseDouble(s15) / Double.parseDouble(s16);
_L10:
        d = feet;
        d1 = inches;
        s6 = new String();
        d2 = 1.0D;
        k = 0;
_L29:
        if(k < i) goto _L14; else goto _L13
_L13:
        if(s6.length() > 0)
            s6 = (new StringBuilder(".")).append(s6).toString();
        decimalformat = new DecimalFormat((new StringBuilder("#0")).append(s6).toString());
        $SWITCH_TABLE$com$shapes$shapes$FeetInchesType$LengthUnitsType()[lengthunitstype.ordinal()];
        JVM INSTR tableswitch 1 7: default 416
    //                   1 1826
    //                   2 1881
    //                   3 1936
    //                   4 1991
    //                   5 2046
    //                   6 2101
    //                   7 2156;
           goto _L15 _L16 _L17 _L18 _L19 _L20 _L21 _L22
_L15:
        s1 = "0";
_L30:
        d4 = feet;
        d5 = inches;
        feet = (double)j * (d4 + d5 / 12D);
        inches = (double)j * (d5 + 12D * d4);
_L2:
        return s1;
_L4:
        as[0] = (new StringBuilder(String.valueOf(as[0].toString()))).append(s.substring(0, 1)).toString();
        int j4 = s.length();
        s = s.substring(1, j4);
        continue; /* Loop/switch isn't completed */
_L6:
        if("-".indexOf(s.charAt(0)) > -1)
        {
            int i4 = s.length();
            s = s.substring(1, i4);
            s3 = "F";
        } else
        if("'FOET".indexOf(Character.toUpperCase(s.charAt(0))) > -1)
        {
            int l3 = s.length();
            s = s.substring(1, l3);
            s3 = "F";
        } else
        if("\"INCHS".indexOf(Character.toUpperCase(s.charAt(0))) > -1)
        {
            int k3 = s.length();
            s = s.substring(1, k3);
            s3 = "I";
        } else
        if("0123456789.".indexOf(s.charAt(0)) > -1)
        {
            flag = false;
        } else
        {
label0:
            {
                if(" ".indexOf(s.charAt(0)) <= -1)
                    break MISSING_BLOCK_LABEL_811;
                if(!s3.equals("F"))
                    break label0;
                int j3 = s.length();
                s = s.substring(1, j3);
                flag = false;
            }
        }
          goto _L3
        if(s.indexOf("/") <= -1)
            break MISSING_BLOCK_LABEL_794;
        int k2 = s.length();
        s2 = s.substring(1, k2);
        int l2 = s.length();
        s = s.substring(1, l2);
_L23:
label1:
        {
            if(flag)
                break label1;
            flag = true;
        }
          goto _L3
        if(s.length() > 0 && "0123456789./".indexOf(s.charAt(0)) > -1)
        {
            int i3 = s.length();
            s = s.substring(1, i3);
        } else
        {
            flag = false;
        }
          goto _L23
        int j2 = s.length();
        s = s.substring(1, j2);
          goto _L3
        String s17;
        if("/".indexOf(s.charAt(0)) <= -1)
            break MISSING_BLOCK_LABEL_931;
        s17 = (new StringBuilder(String.valueOf(as[0]))).append(s).toString();
        as[0] = "0";
        s = s17;
_L24:
label2:
        {
            if(flag)
                break label2;
            s2 = s17.substring(0, s17.length() - s.length());
            flag = true;
        }
          goto _L3
        if(s.length() > 0 && "0123456789./".indexOf(s.substring(0, 1)) > -1)
        {
            int i2 = s.length();
            s = s.substring(1, i2);
        } else
        {
            flag = false;
        }
          goto _L24
        int l1 = s.length();
        s = s.substring(1, l1);
          goto _L3
_L12:
        if(s16.length() > 1 && "0123456789.".indexOf(s16.substring(0, 1)) > -1)
            s16 = s16.substring(0, -1 + s16.length());
        else
            flag3 = false;
          goto _L25
_L8:
label3:
        {
            if(s.length() != 0 || !s3.matches("F"))
                break label3;
            inches = 0.0D;
            if(as[0].toUpperCase().matches("[A-Z]*"))
                feet = 0.0D;
            else
                feet = Double.parseDouble(as[0]);
            if(!s2.matches(""))
            {
                String s13 = s2.substring(0, s2.trim().indexOf("/"));
                String s14 = s2.substring(1 + s2.trim().indexOf("/"), s2.length());
                feet = feet + Double.parseDouble(s13) / Double.parseDouble(s14);
            }
        }
          goto _L10
        if(s.length() == 0 && s3.matches(""))
        {
            switch($SWITCH_TABLE$com$shapes$shapes$FeetInchesType$FeetInches()[feetinches.ordinal()])
            {
            case 1: // '\001'
                feet = Double.parseDouble(as[0]);
                inches = 0.0D;
                if(!s2.matches(""))
                {
                    String s11 = s2.substring(0, s2.trim().indexOf("/"));
                    String s12 = s2.substring(1 + s2.trim().indexOf("/"), s2.length());
                    if(s12.length() > 0)
                        feet = feet + Double.parseDouble(s11) / Double.parseDouble(s12);
                }
                break;

            case 2: // '\002'
                feet = 0.0D;
                inches = Double.parseDouble(as[0]);
                if(!s2.matches(""))
                {
                    String s9 = s2.substring(0, s2.trim().indexOf("/"));
                    String s10 = s2.substring(1 + s2.trim().indexOf("/"), s2.length());
                    if(s10.length() > 0)
                        inches = inches + Double.parseDouble(s9) / Double.parseDouble(s10);
                }
                break;
            }
            continue; /* Loop/switch isn't completed */
        }
_L26:
        String s5;
        boolean flag1;
        String s4;
        while(s.length() > 0) 
            if("0123456789.".indexOf(s.substring(0, 1)) > -1)
            {
                String s8 = as[1];
                StringBuilder stringbuilder = new StringBuilder(String.valueOf(s8));
                as[1] = stringbuilder.append(s.substring(0, 1)).toString();
                int k1 = s.length();
                s = s.substring(1, k1);
            } else
            {
label4:
                {
                    if(" ".indexOf(s.substring(0, 1)) <= -1)
                        break label4;
                    int j1 = s.length();
                    s2 = s.substring(1, j1);
                    s = "";
                }
            }
        feet = Double.parseDouble(as[0]);
        inches = Double.parseDouble(as[1]);
        if(s2.matches("") || s2.indexOf("/") <= -1)
            continue; /* Loop/switch isn't completed */
        s4 = s2.substring(0, s2.trim().indexOf("/"));
        s5 = s2.substring(1 + s2.trim().indexOf("/"), s2.length());
        flag1 = true;
_L28:
        if(!flag1)
        {
            inches = inches + Double.parseDouble(s4) / Double.parseDouble(s5);
            continue; /* Loop/switch isn't completed */
        }
        break MISSING_BLOCK_LABEL_1741;
        boolean flag2;
        String s7;
        if("/".indexOf(s.substring(0, 1)) <= -1)
            break MISSING_BLOCK_LABEL_1724;
        flag2 = true;
        s7 = (new StringBuilder(String.valueOf(as[1]))).append(s).toString();
        as[1] = "0";
        s = s7;
_L27:
label5:
        {
            if(flag2)
                break label5;
            s2 = s7.substring(0, s7.length() - s.length());
        }
          goto _L26
        if(s.length() > 0 && "0123456789./".indexOf(s.substring(0, 1)) > -1)
        {
            int i1 = s.length();
            s = s.substring(1, i1);
        } else
        {
            flag2 = false;
        }
          goto _L27
        int l = s.length();
        s = s.substring(1, l);
          goto _L26
        if(s5.length() > 1 && "0123456789.".indexOf(s5.substring(0, 1)) > -1)
            s5 = s5.substring(0, -1 + s5.length());
        else
            flag1 = false;
          goto _L28
_L14:
        d2 *= 10D;
        s6 = (new StringBuilder(String.valueOf(s6))).append("0").toString();
        k++;
          goto _L29
_L16:
        s1 = (new StringBuilder(String.valueOf(decimalformat.format((double)(float)Math.round(d2 * ((double)j * (d + d1 / 12D))) / d2).toString()))).append(" feet").toString();
          goto _L30
_L17:
        s1 = (new StringBuilder(String.valueOf(decimalformat.format((double)(float)Math.round(d2 * ((double)j * (d + d1 / 12D))) / d2).toString()))).append(" ft").toString();
          goto _L30
_L18:
        s1 = (new StringBuilder(String.valueOf(decimalformat.format((double)(float)Math.round(d2 * ((double)j * (d + d1 / 12D))) / d2).toString()))).append("'").toString();
          goto _L30
_L19:
        s1 = (new StringBuilder(String.valueOf(decimalformat.format((double)(float)Math.round(d2 * (d1 + 12D * (d * (double)j))) / d2).toString()))).append(" inches").toString();
          goto _L30
_L20:
        s1 = (new StringBuilder(String.valueOf(decimalformat.format((double)(float)Math.round(d2 * (d1 + 12D * (d * (double)j))) / d2).toString()))).append(" in").toString();
          goto _L30
_L21:
        s1 = (new StringBuilder(String.valueOf(decimalformat.format((double)(float)Math.round(d2 * (d1 + 12D * (d * (double)j))) / d2).toString()))).append('"').toString();
          goto _L30
_L22:
        DecimalFormat decimalformat1 = new DecimalFormat("###,##0");
        double d3 = d1 + 12D * (d - (double)(int)d);
        if(d3 >= 12D)
        {
            d += (int)(d3 / 12D);
            d3 -= 12 * (int)(d3 / 12D);
        }
        s1 = (new StringBuilder(String.valueOf(decimalformat1.format((double)Math.round(d2 * (double)(j * (int)d)) / d2).toString()))).append("'-").append(decimalformat.format((double)(int)(d3 * d2) / d2).toString()).append('"').toString();
        if(d == 0.0D && j == -1)
            s1 = (new StringBuilder("-")).append(s1).toString();
          goto _L30
        if(true) goto _L10; else goto _L31
_L31:
        if(true) goto _L33; else goto _L32
_L32:
    }

    public String getDisplayValue()
    {
        return input;
    }

    public double getFeet()
    {
        return feet;
    }

    public double getInches()
    {
        return feet;
    }

    public void setDisplayValue(String s, int i, FeetInches feetinches, LengthUnitsType lengthunitstype)
    {
        input = formattedLength(s, i, feetinches, lengthunitstype);
    }

    private static int $SWITCH_TABLE$com$shapes$shapes$FeetInchesType$FeetInches[];
    private static int $SWITCH_TABLE$com$shapes$shapes$FeetInchesType$LengthUnitsType[];
    private double feet;
    private double inches;
    private String input;
}
