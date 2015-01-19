// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.android.observables;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.operators.OperatorConditionalBinding;
import rx.operators.OperatorObserveFromAndroidComponent;

// Referenced classes of package rx.android.observables:
//            Assertions

public final class AndroidObservable
{

    private AndroidObservable()
    {
    }

    public static Observable bindActivity(Activity activity, Observable observable)
    {
        Assertions.assertUiThread();
        return observable.observeOn(AndroidSchedulers.mainThread()).lift(new OperatorConditionalBinding(activity, ACTIVITY_VALIDATOR));
    }

    public static Observable bindFragment(Object obj, Observable observable)
    {
        Assertions.assertUiThread();
        Observable observable1 = observable.observeOn(AndroidSchedulers.mainThread());
        if(USES_SUPPORT_FRAGMENTS && (obj instanceof Fragment))
            return observable1.lift(new OperatorConditionalBinding((Fragment)obj, FRAGMENTV4_VALIDATOR));
        if(android.os.Build.VERSION.SDK_INT >= 11 && (obj instanceof android.app.Fragment))
            return observable1.lift(new OperatorConditionalBinding((android.app.Fragment)obj, FRAGMENT_VALIDATOR));
        else
            throw new IllegalArgumentException("Target fragment is neither a native nor support library Fragment");
    }

    public static Observable fromActivity(Activity activity, Observable observable)
    {
        Assertions.assertUiThread();
        return OperatorObserveFromAndroidComponent.observeFromAndroidComponent(observable, activity);
    }

    public static Observable fromFragment(Object obj, Observable observable)
    {
        Assertions.assertUiThread();
        if(USES_SUPPORT_FRAGMENTS && (obj instanceof Fragment))
            return OperatorObserveFromAndroidComponent.observeFromAndroidComponent(observable, (Fragment)obj);
        if(android.os.Build.VERSION.SDK_INT >= 11 && (obj instanceof android.app.Fragment))
            return OperatorObserveFromAndroidComponent.observeFromAndroidComponent(observable, (android.app.Fragment)obj);
        else
            throw new IllegalArgumentException("Target fragment is neither a native nor support library Fragment");
    }

    private static final Func1 ACTIVITY_VALIDATOR;
    private static final Func1 FRAGMENTV4_VALIDATOR;
    private static final Func1 FRAGMENT_VALIDATOR;
    private static final boolean USES_SUPPORT_FRAGMENTS;

    static 
    {
        Class.forName("android.support.v4.app.Fragment");
        boolean flag = true;
_L2:
        USES_SUPPORT_FRAGMENTS = flag;
        ACTIVITY_VALIDATOR = new Func1() {

            public Boolean call(Activity activity)
            {
                boolean flag1;
                if(!activity.isFinishing())
                    flag1 = true;
                else
                    flag1 = false;
                return Boolean.valueOf(flag1);
            }

            public volatile Object call(Object obj)
            {
                return call((Activity)obj);
            }

        }
;
        FRAGMENT_VALIDATOR = new Func1() {

            public Boolean call(android.app.Fragment fragment)
            {
                boolean flag1;
                if(fragment.isAdded() && !fragment.getActivity().isFinishing())
                    flag1 = true;
                else
                    flag1 = false;
                return Boolean.valueOf(flag1);
            }

            public volatile Object call(Object obj)
            {
                return call((android.app.Fragment)obj);
            }

        }
;
        FRAGMENTV4_VALIDATOR = new Func1() {

            public Boolean call(Fragment fragment)
            {
                boolean flag1;
                if(fragment.isAdded() && !fragment.getActivity().isFinishing())
                    flag1 = true;
                else
                    flag1 = false;
                return Boolean.valueOf(flag1);
            }

            public volatile Object call(Object obj)
            {
                return call((Fragment)obj);
            }

        }
;
        return;
        ClassNotFoundException classnotfoundexception;
        classnotfoundexception;
        flag = false;
        if(true) goto _L2; else goto _L1
_L1:
    }
}
