// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.fragments.base;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.*;
import android.graphics.drawable.*;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife;
import com.devspark.progressfragment.ProgressFragment;
import com.roadtrippers.RoadTrippersApp;
import com.squareup.otto.Bus;
import dagger.Lazy;
import java.io.File;
import java.io.FileInputStream;
import retrofit.client.Response;
import retrofit.mime.*;
import rx.*;
import rx.subscriptions.Subscriptions;

public abstract class BaseProgressFragment extends ProgressFragment
{

    public BaseProgressFragment()
    {
    }

    public static Observable base64Observable(File file)
    {
        return Observable.create(new rx.Observable.OnSubscribeFunc(file) {

            public Subscription onSubscribe(Observer observer)
            {
                try
                {
                    byte abyte0[] = new byte[(int)avatar.length()];
                    FileInputStream fileinputstream = new FileInputStream(avatar);
                    fileinputstream.read(abyte0);
                    observer.onNext(Base64.encodeToString(abyte0, 4));
                    observer.onCompleted();
                    fileinputstream.close();
                }
                catch(Exception exception)
                {
                    observer.onError(exception);
                }
                return Subscriptions.empty();
            }

            final File val$avatar;

            
            {
                avatar = file;
                super();
            }
        }
);
    }

    private static StateListDrawable drawableWithColorFilter(Context context, BitmapDrawable bitmapdrawable, int i)
    {
        Bitmap bitmap = bitmapdrawable.getBitmap();
        Bitmap bitmap1 = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), android.graphics.Bitmap.Config.ARGB_8888);
        Paint paint = new Paint();
        paint.setColorFilter(new LightingColorFilter(i, 1));
        (new Canvas(bitmap1)).drawBitmap(bitmap, 0.0F, 0.0F, paint);
        StateListDrawable statelistdrawable = new StateListDrawable();
        statelistdrawable.addState(new int[] {
            0x10100a7
        }, new BitmapDrawable(context.getResources(), bitmap1));
        statelistdrawable.addState(new int[0], bitmapdrawable);
        return statelistdrawable;
    }

    public static String getBodyString(Response response)
    {
        TypedInput typedinput;
        typedinput = response.getBody();
        if(typedinput == null)
            break MISSING_BLOCK_LABEL_40;
        String s = new String(((TypedByteArray)typedinput).getBytes(), MimeUtil.parseCharset(typedinput.mimeType()));
        return s;
        Exception exception;
        exception;
        exception.printStackTrace();
        return "read response failed";
    }

    private void setAnimationShown(boolean flag)
    {
        if(flag)
            progressDrawable.stop();
        else
        if(!progressDrawable.isRunning())
        {
            lastShown = System.currentTimeMillis();
            progressDrawable.start();
            return;
        }
    }

    public static void setColorFilter(View view, int i)
    {
        view.setBackgroundDrawable(drawableWithColorFilter(view.getContext(), (BitmapDrawable)view.getBackground(), i));
    }

    public static void setColorFilter(ImageView imageview, int i)
    {
        imageview.setImageDrawable(drawableWithColorFilter(imageview.getContext(), (BitmapDrawable)imageview.getDrawable(), i));
    }

    public volatile View getContentView()
    {
        return getContentView();
    }

    public ViewGroup getContentView()
    {
        return (ViewGroup)super.getContentView();
    }

    public boolean isContentShown()
    {
        return contentIsShown;
    }

    protected boolean isInPortrait()
    {
        return getResources().getConfiguration().orientation == 1;
    }

    public void onActivityCreated(Bundle bundle)
    {
        super.onActivityCreated(bundle);
        onCreateContent(bundle);
        ButterKnife.inject(this, getView());
        if(bundle != null)
        {
            setContentEmpty(bundle.getBoolean("empty", false));
            setContentShownNoAnimation(bundle.getBoolean("shown", true));
        }
        onContentCreated(bundle);
    }

    protected abstract void onContentCreated(Bundle bundle);

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        RoadTrippersApp.from(getActivity()).inject(this);
        if(bundle != null)
            contentIsShown = bundle.getBoolean("shown", false);
    }

    protected abstract void onCreateContent(Bundle bundle);

    public void onDestroyView()
    {
        ButterKnife.reset(this);
        super.onDestroyView();
    }

    public void onPause()
    {
        super.onPause();
        try
        {
            ((Bus)busLazy.get()).unregister(this);
            return;
        }
        catch(IllegalArgumentException illegalargumentexception)
        {
            illegalargumentexception.printStackTrace();
        }
    }

    public void onResume()
    {
        super.onResume();
        ((Bus)busLazy.get()).register(this);
    }

    protected abstract void onRetryButtonClick();

    public void onRetryClick()
    {
        onRetryButtonClick();
    }

    public void onSaveInstanceState(Bundle bundle)
    {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("shown", contentIsShown);
    }

    public void onViewCreated(View view, Bundle bundle)
    {
        super.onViewCreated(view, bundle);
        progressDrawable = (AnimationDrawable)((ImageView)ButterKnife.findById(view, 0x7f0900e6)).getDrawable();
        lastShown = System.currentTimeMillis();
        if(progressDrawable != null)
            progressDrawable.start();
    }

    public void setContentShown(boolean flag)
    {
        if(flag && System.currentTimeMillis() - lastShown <= 500L)
        {
            setContentShownNoAnimation(true);
            return;
        } else
        {
            contentIsShown = flag;
            super.setContentShown(flag);
            setAnimationShown(flag);
            return;
        }
    }

    public void setContentShownNoAnimation(boolean flag)
    {
        contentIsShown = flag;
        super.setContentShownNoAnimation(flag);
        setAnimationShown(flag);
    }

    protected void setErrorMessage(int i)
    {
        errorMessage.setText(i);
    }

    protected void setErrorMessage(CharSequence charsequence)
    {
        errorMessage.setText(charsequence);
    }

    Lazy busLazy;
    boolean contentIsShown;
    TextView errorMessage;
    long lastShown;
    AnimationDrawable progressDrawable;
}
