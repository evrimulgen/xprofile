// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.*;
import android.widget.FrameLayout;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.animation.ValueAnimator;
import com.roadtrippers.activities.base.BaseActivity;
import com.roadtrippers.anim.WeightEvaluator;
import com.roadtrippers.api.models.UserResponse;
import com.roadtrippers.events.*;
import com.roadtrippers.fragments.*;
import com.roadtrippers.util.Persistence;
import dagger.Lazy;
import rx.Observable;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

// Referenced classes of package com.roadtrippers.activities:
//            MainActivity

public class LoginActivity extends BaseActivity
{

    public LoginActivity()
    {
    }

    boolean isShown()
    {
        Fragment fragment = getSupportFragmentManager().findFragmentById(0x7f0900fb);
        return (fragment instanceof CreateAccountFragment) || (fragment instanceof LoginFieldsFragment);
    }

    public void onBackPressed()
    {
        if(isShown())
        {
            getSupportFragmentManager().popBackStack();
            WeightEvaluator weightevaluator = new WeightEvaluator(stubFrame);
            Object aobj[] = new Object[2];
            aobj[0] = Float.valueOf(WeightEvaluator.getWeight(stubFrame));
            aobj[1] = Float.valueOf(0.6F);
            ObjectAnimator.ofObject(weightevaluator, aobj).start();
            return;
        } else
        {
            super.onBackPressed();
            return;
        }
    }

    public void onCancelButtonClicked(CancelButtonClick cancelbuttonclick)
    {
        onBackPressed();
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f03004b);
        if(getSupportFragmentManager().findFragmentById(0x7f0900fb) == null)
            getSupportFragmentManager().beginTransaction().replace(0x7f0900fb, new LoginFragment()).commit();
        if(bundle != null && bundle.getBoolean("shown", false))
            WeightEvaluator.setWeight(stubFrame, 0.0F);
    }

    public void onCreateAccountClicked(CreateAccountButtonClicked createaccountbuttonclicked)
    {
        show(new CreateAccountFragment());
    }

    public void onLoginButtonClicked(LoginButtonClicked loginbuttonclicked)
    {
        show(new LoginFieldsFragment());
    }

    public void onLoginSuccess(UserResponse userresponse)
    {
        ((Persistence)persistence.get()).saveUser(userresponse).subscribeOn(Schedulers.io()).subscribe(new Action1() {

            public void call(Boolean boolean1)
            {
                startActivity(new Intent(LoginActivity.this, com/roadtrippers/activities/MainActivity));
                finish();
            }

            public volatile void call(Object obj)
            {
                call((Boolean)obj);
            }

            final LoginActivity this$0;

            
            {
                this$0 = LoginActivity.this;
                super();
            }
        }
);
    }

    protected void onSaveInstanceState(Bundle bundle)
    {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("shown", isShown());
    }

    void show(Fragment fragment)
    {
        getSupportFragmentManager().beginTransaction().setCustomAnimations(0x7f040006, 0x7f040007, 0x7f040006, 0x7f040007).replace(0x7f0900fb, fragment).addToBackStack(null).commit();
        WeightEvaluator weightevaluator = new WeightEvaluator(stubFrame);
        Object aobj[] = new Object[2];
        aobj[0] = Float.valueOf(WeightEvaluator.getWeight(stubFrame));
        aobj[1] = Float.valueOf(0.0F);
        ObjectAnimator.ofObject(weightevaluator, aobj).setDuration(200L).start();
    }

    static final String SHOWN = "shown";
    Lazy persistence;
    FrameLayout stubFrame;
}
