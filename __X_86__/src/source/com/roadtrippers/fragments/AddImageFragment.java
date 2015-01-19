// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.fragments;

import android.app.ActivityManager;
import android.content.ContentResolver;
import android.graphics.*;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Base64;
import android.view.*;
import android.widget.EditText;
import android.widget.ImageView;
import com.newrelic.agent.android.instrumentation.BitmapFactoryInstrumentation;
import com.roadtrippers.api.Roadtrippers;
import com.roadtrippers.api.models.Poi;
import com.roadtrippers.api.requests.AddGalleryImageRequest;
import com.roadtrippers.events.PhotoAddedEvent;
import com.roadtrippers.fragments.base.BaseFragment;
import com.roadtrippers.util.Log;
import com.roadtrippers.util.Serializer;
import com.squareup.otto.Bus;
import dagger.Lazy;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import retrofit.client.Response;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

public class AddImageFragment extends BaseFragment
{

    public AddImageFragment()
    {
        submitEnabled = true;
    }

    public static int calculateInSampleSize(android.graphics.BitmapFactory.Options options, int i, int j)
    {
        int k = options.outHeight;
        int l = options.outWidth;
        int i1;
        for(i1 = 1; k / i1 > j || l / i1 > i; i1 *= 2);
        return i1;
    }

    private void disableSaveButton()
    {
        submitEnabled = false;
        saveButton.setColorFilter(Color.parseColor("#88888888"));
        saveButton.setOnClickListener(null);
    }

    private void enableSaveButton()
    {
        submitEnabled = true;
        getActivity().runOnUiThread(new Runnable() {

            public void run()
            {
                saveButton.setColorFilter(null);
                saveButton.setOnClickListener(new android.view.View.OnClickListener() {

                    public void onClick(View view)
                    {
                        onSaveClicked();
                    }

                    final _cls3 this$1;

            
            {
                this$1 = _cls3.this;
                super();
            }
                }
);
            }

            final AddImageFragment this$0;

            
            {
                this$0 = AddImageFragment.this;
                super();
            }
        }
);
    }

    public static AddImageFragment newInstance(String s, String s1)
    {
        AddImageFragment addimagefragment = new AddImageFragment();
        Bundle bundle = new Bundle();
        bundle.putString(IMAGE_PATH, s);
        bundle.putString(POI, s1);
        addimagefragment.setArguments(bundle);
        return addimagefragment;
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        return layoutinflater.inflate(0x7f030019, viewgroup, false);
    }

    public void onDestroyView()
    {
        super.onDestroyView();
        if(bmp != null)
        {
            bmp.recycle();
            bmp = null;
        }
    }

    public void onSaveClicked()
    {
        if(ActivityManager.isUserAMonkey())
        {
            return;
        } else
        {
            disableSaveButton();
            Crouton.cancelAllCroutons();
            Crouton crouton = Crouton.makeText(getActivity(), "Uploading image...", Style.INFO, (ViewGroup)getView());
            crouton.setConfiguration((new de.keyboardsurfer.android.widget.crouton.Configuration.Builder()).setDuration(-1).build());
            crouton.show();
            bitmapSubscriber = new Subscriber() {

                public void onCompleted()
                {
                    getActivity().runOnUiThread(new Runnable() {

                        public void run()
                        {
                            ((Bus)bus.get()).post(new PhotoAddedEvent());
                            getFragmentManager().popBackStack();
                        }

                        final _cls1 this$1;

            
            {
                this$1 = _cls1.this;
                super();
            }
                    }
);
                }

                public void onError(Throwable throwable)
                {
                    if(throwable != null)
                        throwable.printStackTrace();
                    getActivity().runOnUiThread(new Runnable() {

                        public void run()
                        {
                            Crouton.cancelAllCroutons();
                            Crouton crouton = Crouton.makeText(getActivity(), "There was a problem adding your image, please try again", Style.ALERT, (ViewGroup)getView());
                            crouton.setConfiguration((new de.keyboardsurfer.android.widget.crouton.Configuration.Builder()).setDuration(-1).build());
                            crouton.show();
                            enableSaveButton();
                        }

                        final _cls1 this$1;

            
            {
                this$1 = _cls1.this;
                super();
            }
                    }
);
                }

                public void onNext(Bitmap bitmap)
                {
                    ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
                    bitmap.compress(android.graphics.Bitmap.CompressFormat.PNG, 100, bytearrayoutputstream);
                    String s = Base64.encodeToString(bytearrayoutputstream.toByteArray(), 0);
                    Response response = ((Roadtrippers)roadtrippers.get()).addImageToPlace(poi.id, new AddGalleryImageRequest(caption.getText().toString().trim(), s));
                    if(response.getStatus() >= 200 && response.getStatus() < 300)
                        onCompleted();
                    else
                        onError(null);
                    Log.d("OnNext");
                }

                public volatile void onNext(Object obj)
                {
                    onNext((Bitmap)obj);
                }

                final AddImageFragment this$0;

            
            {
                this$0 = AddImageFragment.this;
                super();
            }
            }
;
            bitmapObservable = new rx.Observable.OnSubscribe() {

                public volatile void call(Object obj)
                {
                    call((Subscriber)obj);
                }

                public void call(Subscriber subscriber)
                {
                    subscriber.onNext(bmp);
                }

                final AddImageFragment this$0;

            
            {
                this$0 = AddImageFragment.this;
                super();
            }
            }
;
            Observable.create(bitmapObservable).subscribeOn(Schedulers.io()).subscribe(bitmapSubscriber);
            return;
        }
    }

    public void onSaveInstanceState(Bundle bundle)
    {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("enabled", submitEnabled);
    }

    public void onViewCreated(View view, Bundle bundle)
    {
        super.onViewCreated(view, bundle);
        if(bundle != null)
        {
            submitEnabled = bundle.getBoolean("enabled");
            String s;
            android.graphics.BitmapFactory.Options options;
            Object aobj[];
            Object aobj1[];
            Object aobj2[];
            if(submitEnabled)
                enableSaveButton();
            else
                disableSaveButton();
        }
        s = getArguments().getString(IMAGE_PATH);
        if(s != null)
        {
            options = new android.graphics.BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            try
            {
                BitmapFactoryInstrumentation.decodeStream(getActivity().getContentResolver().openInputStream(Uri.parse(s)), new Rect(), options);
                aobj = new Object[2];
                aobj[0] = Integer.valueOf(options.outWidth);
                aobj[1] = Integer.valueOf(options.outHeight);
                Log.d("Size %d x %d", aobj);
                aobj1 = new Object[2];
                aobj1[0] = Integer.valueOf(1024);
                aobj1[1] = Integer.valueOf(1024);
                Log.d("Max %d x %d", aobj1);
                options.inSampleSize = calculateInSampleSize(options, 1024, 1024);
                aobj2 = new Object[1];
                aobj2[0] = Integer.valueOf(options.inSampleSize);
                Log.d("InSampleSize %d", aobj2);
                options.inJustDecodeBounds = false;
                bmp = BitmapFactoryInstrumentation.decodeStream(getActivity().getContentResolver().openInputStream(Uri.parse(s)), new Rect(), options);
            }
            catch(FileNotFoundException filenotfoundexception)
            {
                filenotfoundexception.printStackTrace();
            }
            photo.setImageBitmap(bmp);
        }
        poi = (Poi)((Serializer)jackson.get()).deserialize(getArguments().getString(POI), com/roadtrippers/api/models/Poi);
    }

    public static String IMAGE_PATH = "IMAGEFILE";
    public static final int MAX_IMAGE_DIMENSION = 1024;
    public static String POI = "POI";
    public static final String SUBMIT_ENABLED = "enabled";
    private rx.Observable.OnSubscribe bitmapObservable;
    private Subscriber bitmapSubscriber;
    private Bitmap bmp;
    Lazy bus;
    EditText caption;
    Lazy jackson;
    ImageView photo;
    Poi poi;
    Lazy roadtrippers;
    ImageView saveButton;
    boolean submitEnabled;



}
