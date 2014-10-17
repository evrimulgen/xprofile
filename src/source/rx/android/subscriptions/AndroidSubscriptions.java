// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.android.subscriptions;

import android.os.Looper;
import rx.Scheduler;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.subscriptions.Subscriptions;

public final class AndroidSubscriptions
{

    private AndroidSubscriptions()
    {
    }

    public static Subscription unsubscribeInUiThread(Action0 action0)
    {
        return Subscriptions.create(new Action0(action0) {

            public void call()
            {
                if(Looper.getMainLooper() == Looper.myLooper())
                {
                    unsubscribe.call();
                    return;
                } else
                {
                    AndroidSchedulers.mainThread().schedule(new Action1() {

                        public volatile void call(Object obj)
                        {
                            call((rx.Scheduler.Inner)obj);
                        }

                        public void call(rx.Scheduler.Inner inner)
                        {
                            unsubscribe.call();
                        }

                        final _cls1 this$0;

            
            {
                this$0 = _cls1.this;
                super();
            }
                    }
);
                    return;
                }
            }

            final Action0 val$unsubscribe;

            
            {
                unsubscribe = action0;
                super();
            }
        }
);
    }
}
