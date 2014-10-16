// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.fragments;

import android.app.ActivityManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.*;
import android.widget.*;
import com.mobsandgeeks.saripaar.Rule;
import com.mobsandgeeks.saripaar.Validator;
import com.roadtrippers.api.Roadtrippers;
import com.roadtrippers.api.models.Errors;
import com.roadtrippers.api.models.User;
import com.roadtrippers.api.requests.UpdateUserRequest;
import com.roadtrippers.events.UserUpdatedEvent;
import com.roadtrippers.fragments.base.ChooseCropPhotoFragment;
import com.roadtrippers.util.*;
import com.squareup.otto.Bus;
import com.squareup.picasso.*;
import dagger.Lazy;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import java.io.File;
import java.io.FilenameFilter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import rx.*;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

// Referenced classes of package com.roadtrippers.fragments:
//            CreateAccountFragment

public class SettingsFragment extends ChooseCropPhotoFragment
{

    public SettingsFragment()
    {
    }

    public static String md5(String s)
    {
        byte abyte0[];
        StringBuffer stringbuffer;
        int i;
        String s1;
        String s2;
        try
        {
            MessageDigest messagedigest = MessageDigest.getInstance("MD5");
            messagedigest.update(s.getBytes());
            abyte0 = messagedigest.digest();
            stringbuffer = new StringBuffer();
        }
        catch(NoSuchAlgorithmException nosuchalgorithmexception)
        {
            return "";
        }
        i = 0;
        if(i >= abyte0.length)
            break; /* Loop/switch isn't completed */
        for(s2 = Integer.toHexString(0xff & abyte0[i]); s2.length() < 2; s2 = (new StringBuilder()).append("0").append(s2).toString());
        stringbuffer.append(s2);
        i++;
        if(true) goto _L2; else goto _L1
_L2:
        break MISSING_BLOCK_LABEL_31;
_L1:
        s1 = stringbuffer.toString();
        return s1;
    }

    public static void removeUserImageFromCache(User user1, Context context, LruCache lrucache)
    {
        String s = md5(user1.image_iphone_url);
        File afile[] = DiskUtils.cacheDirNamed(context, "http").listFiles(new FilenameFilter(s) {

            public boolean accept(File file, String s1)
            {
                return s1.startsWith(imageHash);
            }

            final String val$imageHash;

            
            {
                imageHash = s;
                super();
            }
        }
);
        int i = afile.length;
        for(int j = 0; j < i; j++)
            afile[j].delete();

        lrucache.clear();
    }

    public static String trim(TextView textview)
    {
        return textview.getText().toString().trim();
    }

    private Observable updateObservable(final Context context, final String image)
    {
        return Observable.create(new rx.Observable.OnSubscribeFunc() {

            public Subscription onSubscribe(Observer observer)
            {
                Roadtrippers roadtrippers;
                String s;
                String s1;
                roadtrippers = (Roadtrippers)roadtrippersLazy.get();
                s = SettingsFragment.trim(username);
                s1 = SettingsFragment.trim(email);
                if(password.length() <= 0) goto _L2; else goto _L1
_L1:
                String s2 = SettingsFragment.trim(password);
_L3:
                int i = confirmPassword.length();
                String s3;
                s3 = null;
                if(i <= 0)
                    break MISSING_BLOCK_LABEL_97;
                s3 = SettingsFragment.trim(confirmPassword);
                User user1 = roadtrippers.updateUser(new UpdateUserRequest(s, s1, s2, s3, image));
                User user2 = user.merge(user1);
                SettingsFragment.removeUserImageFromCache(user2, context, (LruCache)lruCacheLazy.get());
                ((Persistence)persistenceLazy.get()).saveUser(user2);
                observer.onNext(user1);
                observer.onCompleted();
_L4:
                return Subscriptions.empty();
_L2:
                s2 = null;
                  goto _L3
                Throwable throwable;
                throwable;
                observer.onError(throwable);
                  goto _L4
            }

            final SettingsFragment this$0;
            final Context val$context;
            final String val$image;

            
            {
                this$0 = SettingsFragment.this;
                image = s;
                context = context1;
                super();
            }
        }
);
    }

    void back()
    {
        getActivity().finish();
    }

    protected int getCropAspectX()
    {
        return 1;
    }

    protected int getCropAspectY()
    {
        return 1;
    }

    protected ViewGroup getCroutonContainer()
    {
        return container;
    }

    protected ImageView getTargetImageView()
    {
        return avatarView;
    }

    void loadUser()
    {
        ((Persistence)persistenceLazy.get()).getUser().subscribe(new Observer() {

            public void onCompleted()
            {
            }

            public void onError(Throwable throwable)
            {
            }

            public void onNext(User user1)
            {
                onUserLoaded(user1);
            }

            public volatile void onNext(Object obj)
            {
                onNext((User)obj);
            }

            final SettingsFragment this$0;

            
            {
                this$0 = SettingsFragment.this;
                super();
            }
        }
);
    }

    void logout()
    {
        (new android.app.AlertDialog.Builder(getActivity())).setTitle("Are you sure?").setPositiveButton("Logout", new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i)
            {
                RTAnalytics.logEvent(getActivity(), 0x7f0c0069, 0x7f0c001b);
                getActivity().setResult(-1);
                getActivity().finish();
            }

            final SettingsFragment this$0;

            
            {
                this$0 = SettingsFragment.this;
                super();
            }
        }
).setNegativeButton("Cancel", null).show();
    }

    protected void onContentCreated(Bundle bundle)
    {
        if(user == null)
            loadUser();
        else
            onUserLoaded(user);
        if(validator == null)
        {
            validator = new Validator(this);
            validator.setValidationListener(new com.mobsandgeeks.saripaar.Validator.ValidationListener() {

                public void onValidationFailed(View view, Rule rule)
                {
                    if(view instanceof EditText)
                        ((EditText)view).setError(rule.getFailureMessage());
                }

                public void onValidationSucceeded()
                {
                    validationSucceeded();
                }

                final SettingsFragment this$0;

            
            {
                this$0 = SettingsFragment.this;
                super();
            }
            }
);
        }
        setColorFilter(backButton, 0xffcccccc);
        setContentShownNoAnimation(true);
    }

    protected void onCreateContent(Bundle bundle)
    {
        setContentView(0x7f03005d);
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        return layoutinflater.inflate(0x7f03005e, viewgroup, false);
    }

    protected void onTargetImageChanged()
    {
    }

    public void onUserLoaded(User user1)
    {
        user = user1;
        ((Picasso)picassoLazy.get()).load(user1.image_iphone_url).placeholder(0x7f020166).error(0x7f0200e2).into(avatarView);
        username.setText(user1.username);
        email.setText(user1.email);
    }

    public void onUserUpdated(UserUpdatedEvent userupdatedevent)
    {
        Crouton.cancelAllCroutons();
        setContentShown(true);
        if(userupdatedevent.isOk())
        {
            RTAnalytics.logEvent(getActivity(), 0x7f0c006f, 0x7f0c0039);
            Crouton.showText(getActivity(), 0x7f0c00de, Style.CONFIRM, container);
            return;
        } else
        {
            RTAnalytics.logEvent(getActivity(), 0x7f0c006f, 0x7f0c0038);
            Crouton.showText(getActivity(), 0x7f0c00dd, Style.ALERT, container);
            return;
        }
    }

    void saveChanges()
    {
        validator.validate();
    }

    void validationSucceeded()
    {
        if(ActivityManager.isUserAMonkey())
            return;
        Crouton.cancelAllCroutons();
        Crouton.showText(getActivity(), 0x7f0c00e1, Style.INFO, container);
        File file = getCroppedFile();
        final Context context = getActivity().getApplicationContext();
        Observable observable;
        if(file.exists() && file.length() > 0L)
            observable = base64Observable(file).flatMap(new Func1() {

                public volatile Object call(Object obj)
                {
                    return call((String)obj);
                }

                public Observable call(String s)
                {
                    return updateObservable(context, s);
                }

                final SettingsFragment this$0;
                final Context val$context;

            
            {
                this$0 = SettingsFragment.this;
                context = context1;
                super();
            }
            }
);
        else
            observable = updateObservable(context, null);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer() {

            public void onCompleted()
            {
            }

            public void onError(Throwable throwable)
            {
                Object aobj[] = new Object[1];
                aobj[0] = throwable.getMessage();
                Log.d("UserUpdateError %s", aobj);
                Log.e(throwable);
                ((Bus)busLazy.get()).post(new UserUpdatedEvent(throwable));
            }

            public void onNext(User user1)
            {
                Object aobj[] = new Object[1];
                aobj[0] = user1.username;
                Log.d("User update %s", aobj);
                Object aobj1[] = new Object[1];
                String s;
                Errors errors;
                if(user1.errors == null)
                    s = "none";
                else
                    s = user1.errors.toString();
                aobj1[0] = s;
                Log.d("User errors %s", aobj1);
                errors = user1.errors;
                if(errors != null)
                {
                    RTAnalytics.logEvent(getActivity(), 0x7f0c006f, 0x7f0c0038);
                    CreateAccountFragment.setError(errors.username, username);
                    CreateAccountFragment.setError(errors.email, email);
                    CreateAccountFragment.setError(errors.password, password);
                    CreateAccountFragment.setError(errors.password_confirmation, confirmPassword);
                    Crouton.cancelAllCroutons();
                    return;
                } else
                {
                    ((Bus)busLazy.get()).post(new UserUpdatedEvent(user1));
                    return;
                }
            }

            public volatile void onNext(Object obj)
            {
                onNext((User)obj);
            }

            final SettingsFragment this$0;

            
            {
                this$0 = SettingsFragment.this;
                super();
            }
        }
);
    }

    private static final int TRACKING_CATEGORY = 0x7f0c0069;
    ImageView avatarView;
    ImageView backButton;
    Lazy busLazy;
    EditText confirmPassword;
    FrameLayout container;
    EditText email;
    Lazy lruCacheLazy;
    EditText password;
    Lazy persistenceLazy;
    Lazy picassoLazy;
    Lazy roadtrippersLazy;
    User user;
    EditText username;
    Validator validator;

}
