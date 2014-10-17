// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.view.*;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;
import butterknife.ButterKnife;
import com.ctrlplusz.anytextview.AnyEditTextView;
import com.nineoldandroids.animation.*;
import com.nineoldandroids.view.ViewHelper;
import com.roadtrippers.RoadTrippersApp;
import com.roadtrippers.adapters.base.InflaterAdapter;
import com.roadtrippers.api.models.*;
import com.roadtrippers.events.*;
import com.roadtrippers.fragments.CategoriesFragment;
import com.roadtrippers.fragments.base.BaseProgressFragment;
import com.roadtrippers.util.Log;
import com.roadtrippers.util.TripManager;
import com.squareup.otto.Bus;
import dagger.Lazy;
import java.util.*;

// Referenced classes of package com.roadtrippers.adapters:
//            GooglePlacesAutocompleteAdapter

public class CurrentTripAdapter extends InflaterAdapter
{
    class ViewHolder
    {

        void addWaypoint()
        {
            if(getCount() < 40)
            {
                positionsToAnimate.add(Integer.valueOf(1 + position));
                Waypoint awaypoint[] = ((TripManager)tripManagerLazy.get()).getWaypoints();
                Waypoint awaypoint1[] = new Waypoint[1 + awaypoint.length];
                int i = 0;
                while(i < awaypoint1.length) 
                {
                    if(i < 1 + position)
                        awaypoint1[i] = awaypoint[i];
                    else
                    if(i == 1 + position)
                    {
                        Waypoint waypoint1 = new Waypoint();
                        waypoint1.type = com.roadtrippers.api.requests.WaypointRequest.Type.geo;
                        awaypoint1[i] = waypoint1;
                    } else
                    {
                        awaypoint1[i] = awaypoint[i - 1];
                    }
                    i++;
                }
                ((TripManager)tripManagerLazy.get()).getCurrentTrip().waypoints = awaypoint1;
                notifyDataSetChanged();
                return;
            } else
            {
                ((Bus)busLazy.get()).post(CurrentTripFull.EVENT);
                return;
            }
        }

        void deleteWaypoint()
        {
            if(getCount() > 1)
            {
                Waypoint waypoint1 = waypoint;
                ((Bus)busLazy.get()).post(new WaypointDeleted(waypoint1));
                AnimatorSet animatorset = new AnimatorSet();
                Animator aanimator[] = new Animator[2];
                View view = container;
                float af[] = new float[2];
                af[0] = 0.0F;
                af[1] = -container.getWidth();
                aanimator[0] = ObjectAnimator.ofFloat(view, "translationX", af);
                aanimator[1] = ObjectAnimator.ofFloat(container, "alpha", new float[] {
                    1.0F, 0.0F
                });
                animatorset.playTogether(aanimator);
                animatorset.addListener(waypoint1. new AnimatorListenerAdapter() {

                    public void onAnimationEnd(Animator animator)
                    {
                        Waypoint awaypoint[] = ((TripManager)tripManagerLazy.get()).getCurrentTrip().waypoints;
                        Log.d("OnDelete ---------");
                        int i = awaypoint.length;
                        for(int j = 0; j < i; j++)
                        {
                            Waypoint waypoint = awaypoint[j];
                            Object aobj1[] = new Object[1];
                            aobj1[0] = waypoint.name;
                            Log.d("OnDelete %s", aobj1);
                        }

                        int k = CategoriesFragment.indexOf(fixedWaypoint, awaypoint);
                        Object aobj[] = new Object[2];
                        aobj[0] = fixedWaypoint.name;
                        aobj[1] = Integer.valueOf(k);
                        Log.d("OnDelete index of %s = %d", aobj);
                        Log.d("OnDelete ---------");
                        if(k != -1)
                        {
                            Waypoint awaypoint1[] = CurrentTripAdapter.remove(awaypoint, k);
                            ((TripManager)tripManagerLazy.get()).getCurrentTrip().waypoints = awaypoint1;
                            notifyDataSetChanged();
                        }
                        ViewHelper.setTranslationX(container, 0.0F);
                        ViewHelper.setAlpha(container, 1.0F);
                    }

                    final ViewHolder this$1;
                    final Waypoint val$fixedWaypoint;

            
            {
                this$1 = final_viewholder;
                fixedWaypoint = Waypoint.this;
                super();
            }
                }
);
                animatorset.start();
            }
        }

        void setWaypoint(int i, Waypoint waypoint1)
        {
            position = i;
            waypoint = waypoint1;
            if(position == 0)
            {
                waypointNum.setBackgroundResource(0x7f0201b7);
                waypointNum.setText("");
            } else
            if(position == -1 + getCount())
            {
                waypointNum.setBackgroundResource(0x7f0201b5);
                waypointNum.setText("");
            } else
            {
                waypointNum.setBackgroundResource(0x7f0201b8);
                waypointNum.setText(String.valueOf(position));
            }
            if(waypoint1 != null)
            {
                editTrip.setText(waypoint.name);
                Resources resources = editTrip.getResources();
                Integer integer;
                AnimatorSet animatorset;
                Animator aanimator[];
                View view;
                float af[];
                if(waypoint.type == com.roadtrippers.api.requests.WaypointRequest.Type.via)
                {
                    editTrip.setFocusable(false);
                    editTrip.setTextColor(resources.getColor(0x7f08002d));
                } else
                {
                    editTrip.setFocusableInTouchMode(true);
                    editTrip.setTextColor(resources.getColor(0x7f080004));
                }
            }
            integer = Integer.valueOf(position);
            if(positionsToAnimate.contains(integer))
            {
                animatorset = new AnimatorSet();
                aanimator = new Animator[2];
                view = container;
                af = new float[2];
                af[0] = container.getWidth();
                af[1] = 0.0F;
                aanimator[0] = ObjectAnimator.ofFloat(view, "translationX", af);
                aanimator[1] = ObjectAnimator.ofFloat(container, "alpha", new float[] {
                    0.0F, 1.0F
                });
                animatorset.playTogether(aanimator);
                animatorset.start();
                positionsToAnimate.remove(integer);
            }
        }

        ImageView addWaypoint;
        View container;
        ImageView deleteWaypoint;
        AnyEditTextView editTrip;
        int position;
        final CurrentTripAdapter this$0;
        Waypoint waypoint;
        TextView waypointNum;

        ViewHolder(View view)
        {
            this.this$0 = CurrentTripAdapter.this;
            super();
            ButterKnife.inject(this, view);
            addWaypoint.setVisibility(0);
            GooglePlacesAutocompleteAdapter googleplacesautocompleteadapter = new GooglePlacesAutocompleteAdapter(view.getContext(), 0x7f030043);
            editTrip.setAdapter(googleplacesautocompleteadapter);
            editTrip.setOnFocusChangeListener(googleplacesautocompleteadapter. new _cls1());
            editTrip.setOnItemClickListener(new _cls2());
            BaseProgressFragment.setColorFilter(addWaypoint, 0xffcccccc);
            BaseProgressFragment.setColorFilter(deleteWaypoint, 0xffcccccc);
        }
    }


    public CurrentTripAdapter(Context context)
    {
        super(context);
        positionsToAnimate = new ArrayList();
        holders = new ArrayList();
        RoadTrippersApp.from(context).inject(this);
        inputMethod = (InputMethodManager)context.getSystemService("input_method");
    }

    public static Waypoint[] remove(Waypoint awaypoint[], int i)
    {
        Object aobj[] = new Object[1];
        aobj[0] = Integer.valueOf(i);
        Log.d("ToRemove %d", aobj);
        int j = awaypoint.length;
        for(int k = 0; k < j; k++)
        {
            Waypoint waypoint1 = awaypoint[k];
            Object aobj2[] = new Object[1];
            aobj2[0] = waypoint1.name;
            Log.d("ToRemove %s", aobj2);
        }

        Log.d("ToRemove ---------");
        Waypoint awaypoint1[];
        if(i != -1)
        {
            awaypoint1 = new Waypoint[-1 + awaypoint.length];
            int l = 0;
            while(l < awaypoint1.length) 
            {
                int k1;
                if(l < i)
                    k1 = l;
                else
                    k1 = l + 1;
                awaypoint1[l] = awaypoint[k1];
                l++;
            }
            Waypoint awaypoint2[] = awaypoint1;
            int i1 = awaypoint2.length;
            for(int j1 = 0; j1 < i1; j1++)
            {
                Waypoint waypoint = awaypoint2[j1];
                Object aobj1[] = new Object[1];
                aobj1[0] = waypoint.name;
                Log.d("ToRemove %s", aobj1);
            }

        } else
        {
            awaypoint1 = awaypoint;
        }
        return awaypoint1;
    }

    public boolean areAllItemsEnabled()
    {
        return false;
    }

    public int getCount()
    {
        if(((TripManager)tripManagerLazy.get()).getCurrentTrip() != null)
            return ((TripManager)tripManagerLazy.get()).getWaypoints().length;
        else
            return 0;
    }

    public Waypoint getItem(int i)
    {
        Waypoint awaypoint[] = ((TripManager)tripManagerLazy.get()).getWaypoints();
        if(awaypoint != null)
            return awaypoint[i];
        else
            return null;
    }

    public volatile Object getItem(int i)
    {
        return getItem(i);
    }

    public View getView(int i, View view, ViewGroup viewgroup)
    {
        if(view == null)
        {
            view = inflater.inflate(0x7f030035, viewgroup, false);
            ViewHolder viewholder = new ViewHolder(view);
            view.setTag(viewholder);
            holders.add(viewholder);
        }
        ((ViewHolder)view.getTag()).setWaypoint(i, getItem(i));
        return view;
    }

    public boolean isEnabled(int i)
    {
        return false;
    }

    public void onScroll()
    {
        Iterator iterator = holders.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            ViewHolder viewholder = (ViewHolder)iterator.next();
            if(viewholder.editTrip.isFocused())
            {
                viewholder.editTrip.clearFocus();
                inputMethod.hideSoftInputFromWindow(viewholder.editTrip.getWindowToken(), 0);
            }
        } while(true);
    }

    Lazy busLazy;
    List holders;
    InputMethodManager inputMethod;
    int lastCount;
    List positionsToAnimate;
    Lazy tripManagerLazy;

    // Unreferenced inner class com/roadtrippers/adapters/CurrentTripAdapter$ViewHolder$1

/* anonymous class */
    class ViewHolder._cls1
        implements android.view.View.OnFocusChangeListener
    {

        public void onFocusChange(View view, boolean flag)
        {
            adapter.setFocused(flag);
        }

        final ViewHolder this$1;
        final GooglePlacesAutocompleteAdapter val$adapter;
        final CurrentTripAdapter val$this$0;

            
            {
                this$1 = final_viewholder;
                this$0 = currenttripadapter;
                adapter = GooglePlacesAutocompleteAdapter.this;
                super();
            }
    }


    // Unreferenced inner class com/roadtrippers/adapters/CurrentTripAdapter$ViewHolder$2

/* anonymous class */
    class ViewHolder._cls2
        implements android.widget.AdapterView.OnItemClickListener
    {

        public void onItemClick(AdapterView adapterview, View view, int i, long l)
        {
            GooglePlace googleplace = (GooglePlace)adapterview.getItemAtPosition(i);
            ((Bus)busLazy.get()).post(new GooglePlaceSelected(googleplace, waypoint));
            inputMethod.hideSoftInputFromWindow(editTrip.getWindowToken(), 0);
        }

        final ViewHolder this$1;
        final CurrentTripAdapter val$this$0;

            
            {
                this$1 = final_viewholder;
                this$0 = CurrentTripAdapter.this;
                super();
            }
    }

}
