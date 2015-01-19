// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package eu.janmuller.android.simplecropimage;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.*;
import android.media.FaceDetector;
import android.net.Uri;
import android.os.*;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Toast;
import com.newrelic.agent.android.instrumentation.BitmapFactoryInstrumentation;
import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

// Referenced classes of package eu.janmuller.android.simplecropimage:
//            MonitoredActivity, HighlightView, Util, CropImageView, 
//            BitmapManager, RotateBitmap

public class CropImage extends MonitoredActivity
{

    public CropImage()
    {
        mOutputFormat = android.graphics.Bitmap.CompressFormat.JPEG;
        mSaveUri = null;
        mDoFaceDetection = true;
        mCircleCrop = false;
        mScaleUp = true;
        centerer = new Runnable() {

            public void run()
            {
                mImageView.center(true, true);
            }

            final CropImage this$0;

            
            {
                this$0 = CropImage.this;
                super();
            }
        }
;
        mRunFaceDetection = new Runnable() {

            private void handleFace(android.media.FaceDetector.Face face)
            {
                PointF pointf = new PointF();
                int i = 2 * (int)(face.eyesDistance() * mScale);
                face.getMidPoint(pointf);
                pointf.x = pointf.x * mScale;
                pointf.y = pointf.y * mScale;
                int j = (int)pointf.x;
                int k = (int)pointf.y;
                HighlightView highlightview = new HighlightView(mImageView);
                Rect rect = new Rect(0, 0, mBitmap.getWidth(), mBitmap.getHeight());
                RectF rectf = new RectF(j, k, j, k);
                rectf.inset(-i, -i);
                if(rectf.left < 0.0F)
                    rectf.inset(-rectf.left, -rectf.left);
                if(rectf.top < 0.0F)
                    rectf.inset(-rectf.top, -rectf.top);
                if(rectf.right > (float)rect.right)
                    rectf.inset(rectf.right - (float)rect.right, rectf.right - (float)rect.right);
                if(rectf.bottom > (float)rect.bottom)
                    rectf.inset(rectf.bottom - (float)rect.bottom, rectf.bottom - (float)rect.bottom);
                Matrix matrix = mImageMatrix;
                boolean flag = mCircleCrop;
                boolean flag1;
                if(mAspectX != 0 && mAspectY != 0)
                    flag1 = true;
                else
                    flag1 = false;
                highlightview.setup(matrix, rect, rectf, flag, flag1);
                mImageView.add(highlightview);
            }

            private void makeDefault()
            {
                HighlightView highlightview = new HighlightView(mImageView);
                int i = mBitmap.getWidth();
                int j = mBitmap.getHeight();
                Rect rect = new Rect(0, 0, i, j);
                int k = (4 * Math.min(i, j)) / 5;
                int l = k;
                int i1;
                int j1;
                RectF rectf;
                Matrix matrix;
                boolean flag;
                int k1;
                boolean flag1;
                if(mAspectX != 0 && mAspectY != 0)
                    if(mAspectX > mAspectY)
                        l = (k * mAspectY) / mAspectX;
                    else
                        k = (l * mAspectX) / mAspectY;
                i1 = (i - k) / 2;
                j1 = (j - l) / 2;
                rectf = new RectF(i1, j1, i1 + k, j1 + l);
                matrix = mImageMatrix;
                flag = mCircleCrop;
                k1 = mAspectX;
                flag1 = false;
                if(k1 != 0)
                {
                    int l1 = mAspectY;
                    flag1 = false;
                    if(l1 != 0)
                        flag1 = true;
                }
                highlightview.setup(matrix, rect, rectf, flag, flag1);
                mImageView.mHighlightViews.clear();
                mImageView.add(highlightview);
            }

            private Bitmap prepareBitmap()
            {
                if(mBitmap == null)
                    return null;
                if(mBitmap.getWidth() > 256)
                    mScale = 256F / (float)mBitmap.getWidth();
                Matrix matrix = new Matrix();
                matrix.setScale(mScale, mScale);
                return Bitmap.createBitmap(mBitmap, 0, 0, mBitmap.getWidth(), mBitmap.getHeight(), matrix, true);
            }

            public void run()
            {
                mImageMatrix = mImageView.getImageMatrix();
                Bitmap bitmap = prepareBitmap();
                mScale = 1.0F / mScale;
                if(bitmap != null && mDoFaceDetection)
                    mNumFaces = (new FaceDetector(bitmap.getWidth(), bitmap.getHeight(), mFaces.length)).findFaces(bitmap, mFaces);
                if(bitmap != null && bitmap != mBitmap)
                    bitmap.recycle();
                mHandler.post(new Runnable() {

                    public void run()
                    {
                        CropImage cropimage = _fld0;
                        boolean flag;
                        if(mNumFaces > 1)
                            flag = true;
                        else
                            flag = false;
                        cropimage.mWaitingToPick = flag;
                        if(mNumFaces > 0)
                        {
                            for(int i = 0; i < mNumFaces; i++)
                                handleFace(mFaces[i]);

                        } else
                        {
                            makeDefault();
                        }
                        mImageView.invalidate();
                        if(mImageView.mHighlightViews.size() == 1)
                        {
                            mCrop = (HighlightView)mImageView.mHighlightViews.get(0);
                            mCrop.setFocus(true);
                        }
                        if(mNumFaces > 1)
                            Toast.makeText(_fld0, "Multi face crop help", 0).show();
                    }

                    final _cls8 this$1;

            
            {
                this$1 = _cls8.this;
                super();
            }
                }
);
            }

            android.media.FaceDetector.Face mFaces[];
            Matrix mImageMatrix;
            int mNumFaces;
            float mScale;
            final CropImage this$0;



            
            {
                this$0 = CropImage.this;
                super();
                mScale = 1.0F;
                mFaces = new android.media.FaceDetector.Face[3];
            }
        }
;
    }

    public static int calculatePicturesRemaining(Activity activity)
    {
        if(!"mounted".equals(Environment.getExternalStorageState())) goto _L2; else goto _L1
_L1:
        String s1 = Environment.getExternalStorageDirectory().toString();
_L3:
        StatFs statfs = new StatFs(s1);
        return (int)(((float)statfs.getAvailableBlocks() * (float)statfs.getBlockSize()) / 400000F);
_L2:
        String s = activity.getFilesDir().toString();
        s1 = s;
          goto _L3
        Exception exception;
        exception;
        return -2;
    }

    private Bitmap getBitmap(String s)
    {
        Uri uri = getImageUri(s);
        android.graphics.BitmapFactory.Options options;
        InputStream inputstream = mContentResolver.openInputStream(uri);
        options = new android.graphics.BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactoryInstrumentation.decodeStream(inputstream, null, options);
        inputstream.close();
        int i = 1;
        Bitmap bitmap;
        if(options.outHeight > 1024 || options.outWidth > 1024)
            i = (int)Math.pow(2D, (int)Math.round(Math.log(1024D / (double)Math.max(options.outHeight, options.outWidth)) / Math.log(0.5D)));
        android.graphics.BitmapFactory.Options options1 = new android.graphics.BitmapFactory.Options();
        options1.inSampleSize = i;
        InputStream inputstream1 = mContentResolver.openInputStream(uri);
        bitmap = BitmapFactoryInstrumentation.decodeStream(inputstream1, null, options1);
        inputstream1.close();
        return bitmap;
        FileNotFoundException filenotfoundexception;
        filenotfoundexception;
        Log.e("CropImage", (new StringBuilder()).append("file ").append(s).append(" not found").toString());
_L2:
        return null;
        IOException ioexception;
        ioexception;
        Log.e("CropImage", (new StringBuilder()).append("file ").append(s).append(" not found").toString());
        if(true) goto _L2; else goto _L1
_L1:
    }

    private Uri getImageUri(String s)
    {
        return Uri.fromFile(new File(s));
    }

    private void onSaveClicked()
        throws Exception
    {
_L2:
        return;
        if(mSaving || mCrop == null) goto _L2; else goto _L1
_L1:
        Rect rect;
        int i;
        int j;
        mSaving = true;
        rect = mCrop.getCropRect();
        i = rect.width();
        j = rect.height();
        android.graphics.Bitmap.Config config;
        if(!mCircleCrop)
            break MISSING_BLOCK_LABEL_314;
        config = android.graphics.Bitmap.Config.ARGB_8888;
_L3:
        Bitmap bitmap = Bitmap.createBitmap(i, j, config);
        Bitmap bitmap1 = bitmap;
        if(bitmap1 != null)
        {
            Canvas canvas = new Canvas(bitmap1);
            Rect rect1 = new Rect(0, 0, i, j);
            canvas.drawBitmap(mBitmap, rect, rect1, null);
            if(mCircleCrop)
            {
                Canvas canvas1 = new Canvas(bitmap1);
                Path path = new Path();
                path.addCircle((float)i / 2.0F, (float)j / 2.0F, (float)i / 2.0F, android.graphics.Path.Direction.CW);
                canvas1.clipPath(path, android.graphics.Region.Op.DIFFERENCE);
                canvas1.drawColor(0, android.graphics.PorterDuff.Mode.CLEAR);
            }
            Exception exception;
            Bundle bundle;
            if(mOutputX != 0 && mOutputY != 0)
                if(mScale)
                {
                    Bitmap bitmap3 = bitmap1;
                    bitmap1 = Util.transform(new Matrix(), bitmap1, mOutputX, mOutputY, mScaleUp);
                    if(bitmap3 != bitmap1)
                        bitmap3.recycle();
                } else
                {
                    Bitmap bitmap2 = Bitmap.createBitmap(mOutputX, mOutputY, android.graphics.Bitmap.Config.RGB_565);
                    Canvas canvas2 = new Canvas(bitmap2);
                    Rect rect2 = mCrop.getCropRect();
                    Rect rect3 = new Rect(0, 0, mOutputX, mOutputY);
                    int k = (rect2.width() - rect3.width()) / 2;
                    int l = (rect2.height() - rect3.height()) / 2;
                    rect2.inset(Math.max(0, k), Math.max(0, l));
                    rect3.inset(Math.max(0, -k), Math.max(0, -l));
                    canvas2.drawBitmap(mBitmap, rect2, rect3, null);
                    bitmap1.recycle();
                    bitmap1 = bitmap2;
                }
            bundle = getIntent().getExtras();
            if(bundle != null && (bundle.getParcelable("data") != null || bundle.getBoolean("return-data")))
            {
                Bundle bundle1 = new Bundle();
                bundle1.putParcelable("data", bitmap1);
                setResult(-1, (new Intent()).setAction("inline-data").putExtras(bundle1));
                finish();
                return;
            } else
            {
                final Bitmap b = bitmap1;
                String s = getString(R.string.saving_image);
                Runnable runnable = new Runnable() {

                    public void run()
                    {
                        saveOutput(b);
                    }

                    final CropImage this$0;
                    final Bitmap val$b;

            
            {
                this$0 = CropImage.this;
                b = bitmap;
                super();
            }
                }
;
                Util.startBackgroundJob(this, null, s, runnable, mHandler);
                return;
            }
        }
          goto _L2
        try
        {
            config = android.graphics.Bitmap.Config.RGB_565;
        }
        // Misplaced declaration of an exception variable
        catch(Exception exception)
        {
            throw exception;
        }
          goto _L3
    }

    private void saveOutput(Bitmap bitmap)
    {
        if(mSaveUri == null) goto _L2; else goto _L1
_L1:
        java.io.OutputStream outputstream = null;
        outputstream = mContentResolver.openOutputStream(mSaveUri);
        if(outputstream == null)
            break MISSING_BLOCK_LABEL_37;
        bitmap.compress(mOutputFormat, 90, outputstream);
        Util.closeSilently(outputstream);
        Bundle bundle = new Bundle();
        Intent intent = new Intent(mSaveUri.toString());
        intent.putExtras(bundle);
        intent.putExtra("image-path", mImagePath);
        intent.putExtra("orientation_in_degrees", Util.getOrientationInDegree(this));
        setResult(-1, intent);
_L4:
        bitmap.recycle();
        finish();
        return;
        IOException ioexception;
        ioexception;
        Log.e("CropImage", (new StringBuilder()).append("Cannot open file: ").append(mSaveUri).toString(), ioexception);
        setResult(0);
        finish();
        Util.closeSilently(outputstream);
        return;
        Exception exception;
        exception;
        Util.closeSilently(outputstream);
        throw exception;
_L2:
        Log.e("CropImage", "not defined image url");
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static void showStorageToast(Activity activity)
    {
        showStorageToast(activity, calculatePicturesRemaining(activity));
    }

    public static void showStorageToast(Activity activity, int i)
    {
        if(i != -1) goto _L2; else goto _L1
_L1:
        String s;
        if(Environment.getExternalStorageState().equals("checking"))
            s = activity.getString(R.string.preparing_card);
        else
            s = activity.getString(R.string.no_storage_card);
_L4:
        if(s != null)
            Toast.makeText(activity, s, 5000).show();
        return;
_L2:
        s = null;
        if(i < 1)
            s = activity.getString(R.string.not_enough_space);
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void startFaceDetection()
    {
        if(isFinishing())
        {
            return;
        } else
        {
            mImageView.setImageBitmapResetBase(mBitmap, true);
            Util.startBackgroundJob(this, null, "Please wait\u2026", new Runnable() {

                public void run()
                {
                    CountDownLatch countdownlatch = new CountDownLatch(1);
                    final Bitmap b = mBitmap;
                    mHandler.post(countdownlatch. new Runnable() {

                        public void run()
                        {
                            if(b != mBitmap && b != null)
                            {
                                mImageView.setImageBitmapResetBase(b, true);
                                mBitmap.recycle();
                                mBitmap = b;
                            }
                            if(mImageView.getScale() == 1.0F)
                                mImageView.center(true, true);
                            latch.countDown();
                        }

                        final _cls6 this$1;
                        final Bitmap val$b;
                        final CountDownLatch val$latch;

            
            {
                this$1 = final__pcls6;
                b = bitmap;
                latch = CountDownLatch.this;
                super();
            }
                    }
);
                    try
                    {
                        countdownlatch.await();
                    }
                    catch(InterruptedException interruptedexception)
                    {
                        throw new RuntimeException(interruptedexception);
                    }
                    mRunFaceDetection.run();
                }

                final CropImage this$0;

            
            {
                this$0 = CropImage.this;
                super();
            }
            }
, mHandler);
            return;
        }
    }

    public void onCreate(Bundle bundle)
    {
label0:
        {
label1:
            {
                super.onCreate(bundle);
                mContentResolver = getContentResolver();
                requestWindowFeature(1);
                setContentView(R.layout.cropimage);
                mImageView = (CropImageView)findViewById(R.id.image);
                showStorageToast(this);
                Bundle bundle1 = getIntent().getExtras();
                if(bundle1 != null)
                {
                    if(bundle1.getString("circleCrop") != null)
                    {
                        if(android.os.Build.VERSION.SDK_INT > 11)
                            mImageView.setLayerType(1, null);
                        mCircleCrop = true;
                        mAspectX = 1;
                        mAspectY = 1;
                    }
                    mImagePath = bundle1.getString("image-path");
                    mSaveUri = getImageUri(mImagePath);
                    mBitmap = getBitmap(mImagePath);
                    if(!bundle1.containsKey("aspectX") || !(bundle1.get("aspectX") instanceof Integer))
                        break label1;
                    mAspectX = bundle1.getInt("aspectX");
                    if(!bundle1.containsKey("aspectY") || !(bundle1.get("aspectY") instanceof Integer))
                        break label0;
                    mAspectY = bundle1.getInt("aspectY");
                    mOutputX = bundle1.getInt("outputX");
                    mOutputY = bundle1.getInt("outputY");
                    mScale = bundle1.getBoolean("scale", true);
                    mScaleUp = bundle1.getBoolean("scaleUpIfNeeded", true);
                }
                if(mBitmap == null)
                {
                    Log.d("CropImage", "finish!!!");
                    finish();
                    return;
                } else
                {
                    getWindow().addFlags(1024);
                    findViewById(R.id.discard).setOnClickListener(new android.view.View.OnClickListener() {

                        public void onClick(View view)
                        {
                            setResult(0);
                            finish();
                        }

                        final CropImage this$0;

            
            {
                this$0 = CropImage.this;
                super();
            }
                    }
);
                    findViewById(R.id.save).setOnClickListener(new android.view.View.OnClickListener() {

                        public void onClick(View view)
                        {
                            try
                            {
                                onSaveClicked();
                                return;
                            }
                            catch(Exception exception)
                            {
                                finish();
                            }
                        }

                        final CropImage this$0;

            
            {
                this$0 = CropImage.this;
                super();
            }
                    }
);
                    findViewById(R.id.rotateLeft).setOnClickListener(new android.view.View.OnClickListener() {

                        public void onClick(View view)
                        {
                            mBitmap = Util.rotateImage(mBitmap, -90F);
                            RotateBitmap rotatebitmap = new RotateBitmap(mBitmap);
                            mImageView.setImageRotateBitmapResetBase(rotatebitmap, true);
                            mRunFaceDetection.run();
                            mHandler.post(centerer);
                        }

                        final CropImage this$0;

            
            {
                this$0 = CropImage.this;
                super();
            }
                    }
);
                    findViewById(R.id.rotateRight).setOnClickListener(new android.view.View.OnClickListener() {

                        public void onClick(View view)
                        {
                            mBitmap = Util.rotateImage(mBitmap, 90F);
                            RotateBitmap rotatebitmap = new RotateBitmap(mBitmap);
                            mImageView.setImageRotateBitmapResetBase(rotatebitmap, true);
                            mRunFaceDetection.run();
                            mHandler.post(centerer);
                        }

                        final CropImage this$0;

            
            {
                this$0 = CropImage.this;
                super();
            }
                    }
);
                    startFaceDetection();
                    return;
                }
            }
            throw new IllegalArgumentException("aspect_x must be integer");
        }
        throw new IllegalArgumentException("aspect_y must be integer");
    }

    protected void onDestroy()
    {
        super.onDestroy();
        if(mBitmap != null)
            mBitmap.recycle();
    }

    protected void onPause()
    {
        super.onPause();
        BitmapManager.instance().cancelThreadDecoding(mDecodingThreads);
    }

    public static final String ACTION_INLINE_DATA = "inline-data";
    public static final String ASPECT_X = "aspectX";
    public static final String ASPECT_Y = "aspectY";
    public static final int CANNOT_STAT_ERROR = -2;
    public static final String CIRCLE_CROP = "circleCrop";
    public static final String IMAGE_PATH = "image-path";
    public static final int NO_STORAGE_ERROR = -1;
    public static final String ORIENTATION_IN_DEGREES = "orientation_in_degrees";
    public static final String OUTPUT_X = "outputX";
    public static final String OUTPUT_Y = "outputY";
    public static final String RETURN_DATA = "return-data";
    public static final String RETURN_DATA_AS_BITMAP = "data";
    public static final String SCALE = "scale";
    public static final String SCALE_UP_IF_NEEDED = "scaleUpIfNeeded";
    private static final String TAG = "CropImage";
    final int IMAGE_MAX_SIZE = 1024;
    private Runnable centerer;
    private int mAspectX;
    private int mAspectY;
    private Bitmap mBitmap;
    private boolean mCircleCrop;
    private ContentResolver mContentResolver;
    HighlightView mCrop;
    private final BitmapManager.ThreadSet mDecodingThreads = new BitmapManager.ThreadSet();
    private boolean mDoFaceDetection;
    private final Handler mHandler = new Handler();
    private String mImagePath;
    private CropImageView mImageView;
    private android.graphics.Bitmap.CompressFormat mOutputFormat;
    private int mOutputX;
    private int mOutputY;
    Runnable mRunFaceDetection;
    private Uri mSaveUri;
    boolean mSaving;
    private boolean mScale;
    private boolean mScaleUp;
    boolean mWaitingToPick;




/*
    static Bitmap access$102(CropImage cropimage, Bitmap bitmap)
    {
        cropimage.mBitmap = bitmap;
        return bitmap;
    }

*/








}
