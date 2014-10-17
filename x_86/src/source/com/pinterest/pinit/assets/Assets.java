// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.pinterest.pinit.assets;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.*;
import android.graphics.drawable.NinePatchDrawable;
import android.graphics.drawable.StateListDrawable;
import android.util.Base64;
import android.util.DisplayMetrics;
import com.newrelic.agent.android.instrumentation.BitmapFactoryInstrumentation;
import com.pinterest.pinit.assets.buttons.WhiteHdpi;
import com.pinterest.pinit.assets.buttons.WhiteMdpi;
import com.pinterest.pinit.assets.buttons.WhiteXhdpi;

public class Assets
{

    public Assets()
    {
    }

    private static byte[] decode(String s)
    {
        return Base64.decode(s, 0);
    }

    public static StateListDrawable getPinItDrawable(Context context)
    {
        Resources resources;
        int i;
        StateListDrawable statelistdrawable;
        resources = context.getResources();
        i = resources.getDisplayMetrics().densityDpi;
        statelistdrawable = new StateListDrawable();
        i;
        JVM INSTR lookupswitch 3: default 56
    //                   240: 224
    //                   320: 213
    //                   480: 213;
           goto _L1 _L2 _L3 _L3
_L1:
        byte abyte0[] = decode(WhiteMdpi.getDown());
_L7:
        Bitmap bitmap = BitmapFactoryInstrumentation.decodeByteArray(abyte0, 0, abyte0.length);
        byte abyte1[] = bitmap.getNinePatchChunk();
        NinePatch.isNinePatchChunk(abyte1);
        statelistdrawable.addState(statesDown, new NinePatchDrawable(resources, bitmap, abyte1, new Rect(), null));
        i;
        JVM INSTR lookupswitch 3: default 152
    //                   240: 246
    //                   320: 235
    //                   480: 235;
           goto _L4 _L5 _L6 _L6
_L5:
        break MISSING_BLOCK_LABEL_246;
_L4:
        byte abyte2[] = decode(WhiteMdpi.getUp());
_L8:
        Bitmap bitmap1 = BitmapFactoryInstrumentation.decodeByteArray(abyte2, 0, abyte2.length);
        byte abyte3[] = bitmap1.getNinePatchChunk();
        NinePatch.isNinePatchChunk(abyte3);
        statelistdrawable.addState(statesUp, new NinePatchDrawable(resources, bitmap1, abyte3, new Rect(), null));
        return statelistdrawable;
_L3:
        abyte0 = decode(WhiteXhdpi.getDown());
          goto _L7
_L2:
        abyte0 = decode(WhiteHdpi.getDown());
          goto _L7
_L6:
        abyte2 = decode(WhiteXhdpi.getUp());
          goto _L8
        abyte2 = decode(WhiteHdpi.getUp());
          goto _L8
    }

    public static final int DENSITY_DEFAULT = 160;
    public static final int DENSITY_HIGH = 240;
    public static final int DENSITY_MEDIUM = 160;
    public static final int DENSITY_TV = 213;
    public static final int DENSITY_XHIGH = 320;
    public static final int DENSITY_XXHIGH = 480;
    private static int statesDown[] = {
        0x10100a7
    };
    private static int statesUp[] = {
        0x101009e
    };

}
