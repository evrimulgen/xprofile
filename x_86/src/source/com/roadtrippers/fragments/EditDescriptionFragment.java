// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.*;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import com.ctrlplusz.anytextview.Util;
import com.roadtrippers.api.Roadtrippers;
import com.roadtrippers.api.models.Trip;
import com.roadtrippers.events.CloseEditDescription;
import com.roadtrippers.fragments.base.BaseFragment;
import com.roadtrippers.util.RTAnalytics;
import com.roadtrippers.util.TripManager;
import com.squareup.otto.Bus;
import dagger.Lazy;
import java.util.Map;
import org.jraf.android.backport.switchwidget.Switch;
import rx.Observable;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

// Referenced classes of package com.roadtrippers.fragments:
//            SettingsFragment

public class EditDescriptionFragment extends BaseFragment
{

    public EditDescriptionFragment()
    {
    }

    void cancel()
    {
        ((Bus)busLazy.get()).post(CloseEditDescription.EVENT);
    }

    void gotIt()
    {
        Trip trip = ((TripManager)tripManagerLazy.get()).getCurrentTrip();
        if(trip != null)
        {
            trip.description = SettingsFragment.trim(description);
            int i;
            if(yesNo.isChecked())
                i = 0;
            else
                i = 1;
            trip.privacy_level = i;
            if(trip.id != null)
            {
                RTAnalytics.logEvent(getActivity(), 0x7f0c0071, 0x7f0c0047, trip.getAnalyticsProps());
                ((Roadtrippers)roadtrippersLazy.get()).updateTrip(trip.id, trip.getTripDescriptionRequest()).subscribeOn(Schedulers.io()).subscribe(new Action1() {

                    public void call(Trip trip1)
                    {
                    }

                    public volatile void call(Object obj)
                    {
                        call((Trip)obj);
                    }

                    final EditDescriptionFragment this$0;

            
            {
                this$0 = EditDescriptionFragment.this;
                super();
            }
                }
);
            }
        }
        inputMethodManager.hideSoftInputFromWindow(description.getWindowToken(), 0);
        ((Bus)busLazy.get()).post(CloseEditDescription.EVENT);
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        return layoutinflater.inflate(0x7f03003f, viewgroup, false);
    }

    public void onViewCreated(View view, Bundle bundle)
    {
        super.onViewCreated(view, bundle);
        yesNo.setSwitchTypeface((Typeface)Util.typefaceCache.get("hvd_fonts__brandontextlight.ttf"));
        Trip trip = ((TripManager)tripManagerLazy.get()).getCurrentTrip();
        if(trip != null)
        {
            description.setText(trip.description);
            Switch switch1 = yesNo;
            boolean flag;
            if(trip.privacy_level == 0)
                flag = true;
            else
                flag = false;
            switch1.setChecked(flag);
        } else
        {
            ((TripManager)tripManagerLazy.get()).createNewEmptyTrip();
        }
        view.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view1)
            {
                inputMethodManager.hideSoftInputFromWindow(description.getWindowToken(), 0);
            }

            final EditDescriptionFragment this$0;

            
            {
                this$0 = EditDescriptionFragment.this;
                super();
            }
        }
);
    }

    Lazy busLazy;
    EditText description;
    InputMethodManager inputMethodManager;
    Lazy roadtrippersLazy;
    Lazy tripManagerLazy;
    Switch yesNo;
}
