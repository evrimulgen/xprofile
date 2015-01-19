// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.fragments.base;

import android.content.*;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.support.v4.app.FragmentActivity;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.dmitriy.tarasov.android.intents.IntentUtils;
import com.newrelic.agent.android.instrumentation.BitmapFactoryInstrumentation;
import com.roadtrippers.util.DiskUtils;
import com.roadtrippers.util.Log;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import eu.janmuller.android.simplecropimage.CropImage;
import java.io.*;
import rx.*;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

// Referenced classes of package com.roadtrippers.fragments.base:
//            RetainedProgressFragment

public abstract class ChooseCropPhotoFragment extends RetainedProgressFragment
{

    public ChooseCropPhotoFragment()
    {
    }

    static void copy(File file, File file1)
        throws IOException
    {
        FileInputStream fileinputstream = new FileInputStream(file);
        FileOutputStream fileoutputstream = new FileOutputStream(file1);
        byte abyte0[] = new byte[1024];
        do
        {
            int i = fileinputstream.read(abyte0);
            if(i > 0)
            {
                fileoutputstream.write(abyte0, 0, i);
            } else
            {
                fileinputstream.close();
                fileoutputstream.close();
                return;
            }
        } while(true);
    }

    void chooseFromCamera()
    {
        startActivityForResult(IntentUtils.photoCapture(getPhotoFile().getAbsolutePath()), 6846);
    }

    void chooseFromGallery()
    {
        startActivityForResult(IntentUtils.pickImage(), 7897);
    }

    void choosePhotoClick()
    {
        if(IntentUtils.isIntentAvailable(getActivity(), new Intent("android.media.action.IMAGE_CAPTURE")))
        {
            (new Builder(getActivity())).setItems(getResources().getStringArray(0x7f060002), new android.content.DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialoginterface, int i)
                {
                    switch(i)
                    {
                    default:
                        return;

                    case 0: // '\0'
                        chooseFromCamera();
                        return;

                    case 1: // '\001'
                        chooseFromGallery();
                        break;
                    }
                }

                final ChooseCropPhotoFragment this$0;

            
            {
                this$0 = ChooseCropPhotoFragment.this;
                Object();
            }
            }
).show();
            return;
        } else
        {
            chooseFromGallery();
            return;
        }
    }

    void cropAvatar(Bitmap bitmap)
    {
        try
        {
            File file = getCroppedFile();
            FileOutputStream fileoutputstream = new FileOutputStream(file);
            bitmap.compress(android.graphics.Bitmap.CompressFormat.PNG, 100, fileoutputstream);
            fileoutputstream.flush();
            fileoutputstream.close();
            cropAvatar(file.getAbsolutePath(), false);
            return;
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }

    void cropAvatar(final String path, final boolean copyFile)
    {
        Observable.create(new rx.Observable.OnSubscribeFunc() {

            public Subscription onSubscribe(Observer observer)
            {
                File file = new File(path);
                if(!copyFile) goto _L2; else goto _L1
_L1:
                File file1;
                file1 = getCroppedFile();
                ChooseCropPhotoFragment.copy(file, file1);
_L3:
                observer.onNext(file1);
_L4:
                return Subscriptions.empty();
_L2:
                file1 = file;
                  goto _L3
                IOException ioexception;
                ioexception;
                ioexception.printStackTrace();
                observer.onError(ioexception);
                  goto _L4
            }

            final ChooseCropPhotoFragment this$0;
            final boolean val$copyFile;
            final String val$path;

            
            {
                this$0 = ChooseCropPhotoFragment.this;
                path = s;
                copyFile = flag;
                Object();
            }
        }
).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer() {

            public void onCompleted()
            {
            }

            public void onError(Throwable throwable)
            {
                Crouton.showText(getActivity(), "Failed to process the image, please try again", Style.ALERT, getCroutonContainer());
            }

            public void onNext(File file)
            {
                Intent intent = new Intent(getActivity(), eu/janmuller/android/simplecropimage/CropImage);
                intent.putExtra("image-path", file.getAbsolutePath());
                intent.putExtra("scale", true);
                intent.putExtra("aspectX", getCropAspectX());
                intent.putExtra("aspectY", getCropAspectY());
                startActivityForResult(intent, 4569);
            }

            public volatile void onNext(Object obj)
            {
                onNext((File)obj);
            }

            final ChooseCropPhotoFragment this$0;

            
            {
                this$0 = ChooseCropPhotoFragment.this;
                Object();
            }
        }
);
    }

    protected abstract int getCropAspectX();

    protected abstract int getCropAspectY();

    protected File getCroppedFile()
    {
        return new File(DiskUtils.getFilesDir(getActivity()), "avatar");
    }

    protected abstract ViewGroup getCroutonContainer();

    File getPhotoFile()
    {
        return new File(DiskUtils.getFilesDir(getActivity()), "photo");
    }

    protected abstract ImageView getTargetImageView();

    public void onActivityResult(int i, int j, Intent intent)
    {
        onActivityResult(i, j, intent);
        if(j == -1) goto _L2; else goto _L1
_L1:
        return;
_L2:
        android.net.Uri uri;
        switch(i)
        {
        default:
            return;

        case 4569: 
            String s = intent.getStringExtra("image-path");
            if(s != null)
            {
                Bitmap bitmap = BitmapFactoryInstrumentation.decodeFile(s);
                getTargetImageView().setImageBitmap(bitmap);
                onTargetImageChanged();
                Observable.from(bitmap).observeOn(Schedulers.io()).subscribe(new Action1() {

                    public void call(Bitmap bitmap1)
                    {
                        Bitmap bitmap2 = bitmap1;
                        try
                        {
                            if(bitmap2.getHeight() * bitmap2.getWidth() > 0x3d090)
                            {
                                double d = ratioXY();
                                double d1 = Math.sqrt(250000D / d);
                                bitmap2 = Bitmap.createScaledBitmap(bitmap2, (int)(d * d1), (int)d1, false);
                            }
                            FileOutputStream fileoutputstream = new FileOutputStream(getCroppedFile());
                            bitmap2.compress(android.graphics.Bitmap.CompressFormat.PNG, 100, fileoutputstream);
                            fileoutputstream.close();
                            return;
                        }
                        catch(Exception exception)
                        {
                            Log.e(exception);
                        }
                    }

                    public volatile void call(Object obj)
                    {
                        call((Bitmap)obj);
                    }

                    final ChooseCropPhotoFragment this$0;

            
            {
                this$0 = ChooseCropPhotoFragment.this;
                Object();
            }
                }
);
                return;
            }
            break;

        case 6846: 
            cropAvatar(getPhotoFile().getAbsolutePath(), false);
            return;

        case 7897: 
            uri = intent.getData();
            continue; /* Loop/switch isn't completed */
        }
        if(true) goto _L1; else goto _L3
_L3:
        if(uri == null) goto _L1; else goto _L4
_L4:
        try
        {
            cropAvatar(BitmapFactoryInstrumentation.decodeStream(getActivity().getContentResolver().openInputStream(uri)));
            return;
        }
        catch(FileNotFoundException filenotfoundexception)
        {
            filenotfoundexception.printStackTrace();
        }
        return;
    }

    public void onDestroy()
    {
        Observable.from(getCroppedFile()).observeOn(Schedulers.io()).subscribe(new Action1() {

            public void call(File file)
            {
                file.delete();
            }

            public volatile void call(Object obj)
            {
                call((File)obj);
            }

            final ChooseCropPhotoFragment this$0;

            
            {
                this$0 = ChooseCropPhotoFragment.this;
                Object();
            }
        }
);
        onDestroy();
    }

    protected void onRetryButtonClick()
    {
    }

    protected abstract void onTargetImageChanged();

    protected double ratioXY()
    {
        return (double)getCropAspectX() / (double)getCropAspectY();
    }

    static final String AVATAR_JPG = "avatar";
    static final int CAMERA_REQUEST = 6846;
    static final int CROP_REQUEST = 4569;
    static final int GALLERY_REQUEST = 7897;
    public static final int MAX_SENDED_IMG_SIZE = 0x3d090;
    static final String PHOTO_JPG = "photo";
}
