// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.fragments;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.*;
import android.widget.*;
import com.facebook.Session;
import com.roadtrippers.activities.*;
import com.roadtrippers.adapters.LeftDrawableAdapter;
import com.roadtrippers.api.models.User;
import com.roadtrippers.fragments.base.BaseFragment;
import com.roadtrippers.util.Persistence;
import com.roadtrippers.util.RTAnalytics;
import dagger.Lazy;
import rx.Observable;
import rx.Observer;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class UserPanelFragment extends BaseFragment
{

    public UserPanelFragment()
    {
    }

    void close()
    {
        MainActivity.removeUserPanel(getActivity(), this);
    }

    public void onActivityResult(int i, int j, Intent intent)
    {
        super.onActivityResult(i, j, intent);
        if(i == 9875 && j == -1)
            ((Persistence)persistence.get()).clear().subscribeOn(Schedulers.io()).subscribe(new Action1() {

                public void call(Boolean boolean1)
                {
                    Session session = Session.getActiveSession();
                    if(session == null)
                    {
                        session = new Session(getActivity());
                        Session.setActiveSession(session);
                    }
                    session.closeAndClearTokenInformation();
                    startActivity(new Intent(getActivity(), com/roadtrippers/activities/LoginActivity));
                    getActivity().finish();
                }

                public volatile void call(Object obj)
                {
                    call((Boolean)obj);
                }

                final UserPanelFragment this$0;

            
            {
                this$0 = UserPanelFragment.this;
                super();
            }
            }
);
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        return layoutinflater.inflate(0x7f030066, viewgroup, false);
    }

    public void onUserLoaded(User user)
    {
        username.setText(user.username);
    }

    public void onViewCreated(View view, Bundle bundle)
    {
        super.onViewCreated(view, bundle);
        ((Persistence)persistence.get()).getUser().subscribe(new Observer() {

            public void onCompleted()
            {
            }

            public void onError(Throwable throwable)
            {
                if(getView() != null)
                    username.setText(0x7f0c007a);
            }

            public void onNext(User user)
            {
                onUserLoaded(user);
            }

            public volatile void onNext(Object obj)
            {
                onNext((User)obj);
            }

            final UserPanelFragment this$0;

            
            {
                this$0 = UserPanelFragment.this;
                super();
            }
        }
);
        list.setAdapter(new LeftDrawableAdapter(getActivity(), getResources().getStringArray(0x7f060001), getResources().obtainTypedArray(0x7f060000)));
        list.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView adapterview, View view1, int i, long l)
            {
                if(getActivity() == null) goto _L2; else goto _L1
_L1:
                Object obj = null;
                i;
                JVM INSTR tableswitch 0 2: default 40
            //                           0 69
            //                           1 117
            //                           2 140;
                   goto _L3 _L4 _L5 _L6
_L3:
                if(obj != null)
                    startActivity(new Intent(getActivity(), ((Class) (obj))));
_L2:
                return;
_L4:
                RTAnalytics.logEvent(getActivity(), 0x7f0c006b, 0x7f0c0021, "settings");
                startActivityForResult(new Intent(getActivity(), com/roadtrippers/activities/SettingsActivity), 9875);
                obj = null;
                continue; /* Loop/switch isn't completed */
_L5:
                RTAnalytics.logEvent(getActivity(), 0x7f0c006b, 0x7f0c0021, "support");
                obj = com/roadtrippers/activities/SupportActivity;
                continue; /* Loop/switch isn't completed */
_L6:
                RTAnalytics.logEvent(getActivity(), 0x7f0c006b, 0x7f0c0021, "about");
                obj = com/roadtrippers/activities/AboutActivity;
                if(true) goto _L3; else goto _L7
_L7:
            }

            final UserPanelFragment this$0;

            
            {
                this$0 = UserPanelFragment.this;
                super();
            }
        }
);
    }

    static final int LOGOUT_REQUEST = 9875;
    ListView list;
    Lazy persistence;
    TextView username;
}
