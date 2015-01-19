// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.agent.android.instrumentation;

import android.content.res.Resources;
import android.graphics.*;
import android.util.TypedValue;
import com.newrelic.agent.android.tracing.TraceMachine;
import java.io.FileDescriptor;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

// Referenced classes of package com.newrelic.agent.android.instrumentation:
//            MetricCategory

public class BitmapFactoryInstrumentation
{

    private BitmapFactoryInstrumentation()
    {
    }

    public static Bitmap decodeByteArray(byte abyte0[], int i, int j)
    {
        TraceMachine.enterMethod("BitmapFactory#decodeByteArray", categoryParams);
        Bitmap bitmap = BitmapFactory.decodeByteArray(abyte0, i, j);
        TraceMachine.exitMethod();
        return bitmap;
    }

    public static Bitmap decodeByteArray(byte abyte0[], int i, int j, android.graphics.BitmapFactory.Options options)
    {
        TraceMachine.enterMethod("BitmapFactory#decodeByteArray", categoryParams);
        Bitmap bitmap = BitmapFactory.decodeByteArray(abyte0, i, j, options);
        TraceMachine.exitMethod();
        return bitmap;
    }

    public static Bitmap decodeFile(String s)
    {
        TraceMachine.enterMethod("BitmapFactory#decodeFile", categoryParams);
        Bitmap bitmap = BitmapFactory.decodeFile(s);
        TraceMachine.exitMethod();
        return bitmap;
    }

    public static Bitmap decodeFile(String s, android.graphics.BitmapFactory.Options options)
    {
        TraceMachine.enterMethod("BitmapFactory#decodeFile", categoryParams);
        Bitmap bitmap = BitmapFactory.decodeFile(s, options);
        TraceMachine.exitMethod();
        return bitmap;
    }

    public static Bitmap decodeFileDescriptor(FileDescriptor filedescriptor)
    {
        TraceMachine.enterMethod("BitmapFactory#decodeFileDescriptor", categoryParams);
        Bitmap bitmap = BitmapFactory.decodeFileDescriptor(filedescriptor);
        TraceMachine.exitMethod();
        return bitmap;
    }

    public static Bitmap decodeFileDescriptor(FileDescriptor filedescriptor, Rect rect, android.graphics.BitmapFactory.Options options)
    {
        TraceMachine.enterMethod("BitmapFactory#decodeFileDescriptor", categoryParams);
        Bitmap bitmap = BitmapFactory.decodeFileDescriptor(filedescriptor, rect, options);
        TraceMachine.exitMethod();
        return bitmap;
    }

    public static Bitmap decodeResource(Resources resources, int i)
    {
        TraceMachine.enterMethod("BitmapFactory#decodeResource", categoryParams);
        Bitmap bitmap = BitmapFactory.decodeResource(resources, i);
        TraceMachine.exitMethod();
        return bitmap;
    }

    public static Bitmap decodeResource(Resources resources, int i, android.graphics.BitmapFactory.Options options)
    {
        TraceMachine.enterMethod("BitmapFactory#decodeResource", categoryParams);
        Bitmap bitmap = BitmapFactory.decodeResource(resources, i, options);
        TraceMachine.exitMethod();
        return bitmap;
    }

    public static Bitmap decodeResourceStream(Resources resources, TypedValue typedvalue, InputStream inputstream, Rect rect, android.graphics.BitmapFactory.Options options)
    {
        TraceMachine.enterMethod("BitmapFactory#decodeResourceStream", categoryParams);
        Bitmap bitmap = BitmapFactory.decodeResourceStream(resources, typedvalue, inputstream, rect, options);
        TraceMachine.exitMethod();
        return bitmap;
    }

    public static Bitmap decodeStream(InputStream inputstream)
    {
        TraceMachine.enterMethod("BitmapFactory#decodeStream", categoryParams);
        Bitmap bitmap = BitmapFactory.decodeStream(inputstream);
        TraceMachine.exitMethod();
        return bitmap;
    }

    public static Bitmap decodeStream(InputStream inputstream, Rect rect, android.graphics.BitmapFactory.Options options)
    {
        TraceMachine.enterMethod("BitmapFactory#decodeStream", categoryParams);
        Bitmap bitmap = BitmapFactory.decodeStream(inputstream, rect, options);
        TraceMachine.exitMethod();
        return bitmap;
    }

    private static final ArrayList categoryParams;

    static 
    {
        String as[] = new String[3];
        as[0] = "category";
        as[1] = com/newrelic/agent/android/instrumentation/MetricCategory.getName();
        as[2] = "IMAGE";
        categoryParams = new ArrayList(Arrays.asList(as));
    }
}
