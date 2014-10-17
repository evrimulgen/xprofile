// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package eu.janmuller.android.simplecropimage;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.*;
import android.os.Handler;
import android.view.Display;
import android.view.WindowManager;
import java.io.Closeable;

// Referenced classes of package eu.janmuller.android.simplecropimage:
//            MonitoredActivity

public class Util
{
    private static class BackgroundJob extends MonitoredActivity.LifeCycleAdapter
        implements Runnable
    {

        public void onActivityDestroyed(MonitoredActivity monitoredactivity)
        {
            mCleanupRunner.run();
            mHandler.removeCallbacks(mCleanupRunner);
        }

        public void onActivityStarted(MonitoredActivity monitoredactivity)
        {
            mDialog.show();
        }

        public void onActivityStopped(MonitoredActivity monitoredactivity)
        {
            mDialog.hide();
        }

        public void run()
        {
            mJob.run();
            mHandler.post(mCleanupRunner);
            return;
            Exception exception;
            exception;
            mHandler.post(mCleanupRunner);
            throw exception;
        }

        private final MonitoredActivity mActivity;
        private final Runnable mCleanupRunner = new _cls1();
        private final ProgressDialog mDialog;
        private final Handler mHandler;
        private final Runnable mJob;



        public BackgroundJob(MonitoredActivity monitoredactivity, Runnable runnable, ProgressDialog progressdialog, Handler handler)
        {
            mActivity = monitoredactivity;
            mDialog = progressdialog;
            mJob = runnable;
            mActivity.addLifeCycleListener(this);
            mHandler = handler;
        }
    }


    private Util()
    {
    }

    public static void closeSilently(Closeable closeable)
    {
        if(closeable == null)
            return;
        try
        {
            closeable.close();
            return;
        }
        catch(Throwable throwable)
        {
            return;
        }
    }

    public static android.graphics.BitmapFactory.Options createNativeAllocOptions()
    {
        return new android.graphics.BitmapFactory.Options();
    }

    public static int getOrientationInDegree(Activity activity)
    {
        switch(activity.getWindowManager().getDefaultDisplay().getRotation())
        {
        default:
            return 0;

        case 0: // '\0'
            return 0;

        case 1: // '\001'
            return 90;

        case 2: // '\002'
            return 180;

        case 3: // '\003'
            return 270;
        }
    }

    public static Bitmap rotateImage(Bitmap bitmap, float f)
    {
        Matrix matrix = new Matrix();
        matrix.postRotate(f);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    public static void startBackgroundJob(MonitoredActivity monitoredactivity, String s, String s1, Runnable runnable, Handler handler)
    {
        (new Thread(new BackgroundJob(monitoredactivity, runnable, ProgressDialog.show(monitoredactivity, s, s1, true, false), handler))).start();
    }

    public static Bitmap transform(Matrix matrix, Bitmap bitmap, int i, int j, boolean flag)
    {
        int k = bitmap.getWidth() - i;
        int l = bitmap.getHeight() - j;
        Bitmap bitmap2;
        if(!flag && (k < 0 || l < 0))
        {
            bitmap2 = Bitmap.createBitmap(i, j, android.graphics.Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap2);
            int k1 = Math.max(0, k / 2);
            int l1 = Math.max(0, l / 2);
            Rect rect = new Rect(k1, l1, k1 + Math.min(i, bitmap.getWidth()), l1 + Math.min(j, bitmap.getHeight()));
            int i2 = (i - rect.width()) / 2;
            int j2 = (j - rect.height()) / 2;
            Rect rect1 = new Rect(i2, j2, i - i2, j - j2);
            canvas.drawBitmap(bitmap, rect, rect1, null);
        } else
        {
            float f = bitmap.getWidth();
            float f1 = bitmap.getHeight();
            Bitmap bitmap1;
            if(f / f1 > (float)i / (float)j)
            {
                float f3 = (float)j / f1;
                int i1;
                int j1;
                if(f3 < 0.9F || f3 > 1.0F)
                    matrix.setScale(f3, f3);
                else
                    matrix = null;
            } else
            {
                float f2 = (float)i / f;
                if(f2 < 0.9F || f2 > 1.0F)
                    matrix.setScale(f2, f2);
                else
                    matrix = null;
            }
            if(matrix != null)
                bitmap1 = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            else
                bitmap1 = bitmap;
            i1 = Math.max(0, bitmap1.getWidth() - i);
            j1 = Math.max(0, bitmap1.getHeight() - j);
            bitmap2 = Bitmap.createBitmap(bitmap1, i1 / 2, j1 / 2, i, j);
            if(bitmap1 != bitmap)
            {
                bitmap1.recycle();
                return bitmap2;
            }
        }
        return bitmap2;
    }

    private static final String TAG = "db.Util";

    // Unreferenced inner class eu/janmuller/android/simplecropimage/Util$BackgroundJob$1

/* anonymous class */
    class BackgroundJob._cls1
        implements Runnable
    {

        public void run()
        {
            mActivity.removeLifeCycleListener(BackgroundJob.this);
            if(mDialog.getWindow() != null)
                mDialog.dismiss();
        }

        final BackgroundJob this$0;

            
            {
                this$0 = BackgroundJob.this;
                super();
            }
    }

}
