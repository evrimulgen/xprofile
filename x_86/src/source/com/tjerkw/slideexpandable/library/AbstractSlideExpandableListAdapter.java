// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.tjerkw.slideexpandable.library;

import android.app.Activity;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ListAdapter;
import com.roadtrippers.RoadTrippersApp;
import com.roadtrippers.events.GroupExpand;
import com.squareup.otto.Bus;
import dagger.Lazy;
import java.util.BitSet;

// Referenced classes of package com.tjerkw.slideexpandable.library:
//            WrapperListAdapterImpl, ExpandCollapseAnimation

public abstract class AbstractSlideExpandableListAdapter extends WrapperListAdapterImpl
{
    static class SavedState extends android.view.View.BaseSavedState
    {

        public void writeToParcel(Parcel parcel, int i)
        {
            super.writeToParcel(parcel, i);
            lastOpenPosition = parcel.readInt();
            openItems = AbstractSlideExpandableListAdapter.readBitSet(parcel);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public SavedState createFromParcel(Parcel parcel)
            {
                return new SavedState(parcel);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public SavedState[] newArray(int i)
            {
                return new SavedState[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        public int lastOpenPosition;
        public BitSet openItems;


        private SavedState(Parcel parcel)
        {
            super(parcel);
            openItems = null;
            lastOpenPosition = -1;
            parcel.writeInt(lastOpenPosition);
            AbstractSlideExpandableListAdapter.writeBitSet(parcel, openItems);
        }


        SavedState(Parcelable parcelable)
        {
            super(parcelable);
            openItems = null;
            lastOpenPosition = -1;
        }
    }


    public AbstractSlideExpandableListAdapter(Activity activity, ListAdapter listadapter)
    {
        super(listadapter);
        lastOpen = null;
        lastOpenPosition = -1;
        animationDuration = 330;
        openItems = new BitSet();
        RoadTrippersApp.from(activity).inject(this);
    }

    private void animateView(View view, int i)
    {
        ExpandCollapseAnimation expandcollapseanimation = new ExpandCollapseAnimation(view, i);
        expandcollapseanimation.setDuration(getAnimationDuration());
        view.startAnimation(expandcollapseanimation);
    }

    private void enableFor(View view, final View target, final int position)
    {
        if(target == lastOpen && position != lastOpenPosition)
            lastOpen = null;
        if(position == lastOpenPosition)
            lastOpen = target;
        if(viewHeights.get(position, -1) == -1)
        {
            viewHeights.put(position, target.getMeasuredHeight());
            updateExpandable(target, position);
        } else
        {
            updateExpandable(target, position);
        }
        view.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view1)
            {
                Animation animation = target.getAnimation();
                if(animation != null && animation.hasStarted() && !animation.hasEnded())
                {
                    animation.setAnimationListener(view1. new android.view.animation.Animation.AnimationListener() {

                        public void onAnimationEnd(Animation animation)
                        {
                            view.performClick();
                        }

                        public void onAnimationRepeat(Animation animation)
                        {
                        }

                        public void onAnimationStart(Animation animation)
                        {
                        }

                        final _cls1 this$1;
                        final View val$view;

            
            {
                this$1 = final__pcls1;
                view = View.this;
                super();
            }
                    }
);
                    return;
                }
                target.setAnimation(null);
                int i;
                if(target.getVisibility() == 0)
                    i = 1;
                else
                    i = 0;
                ((Bus)bus.get()).post(new GroupExpand(view1));
                if(i == 0)
                    openItems.set(position, true);
                else
                    openItems.set(position, false);
                if(i != 0) goto _L2; else goto _L1
_L1:
                if(lastOpenPosition != -1 && lastOpenPosition != position)
                {
                    if(lastOpen != null)
                        animateView(lastOpen, 1);
                    openItems.set(lastOpenPosition, false);
                }
                lastOpen = target;
                lastOpenPosition = position;
_L4:
                animateView(target, i);
                return;
_L2:
                if(lastOpenPosition == position)
                    lastOpenPosition = -1;
                if(true) goto _L4; else goto _L3
_L3:
            }

            final AbstractSlideExpandableListAdapter this$0;
            final int val$position;
            final View val$target;

            
            {
                this$0 = AbstractSlideExpandableListAdapter.this;
                target = view;
                position = i;
                super();
            }
        }
);
    }

    private static BitSet readBitSet(Parcel parcel)
    {
        int i = parcel.readInt();
        BitSet bitset = new BitSet();
        for(int j = 0; j < i; j++)
            bitset.set(parcel.readInt());

        return bitset;
    }

    private void updateExpandable(View view, int i)
    {
        android.widget.LinearLayout.LayoutParams layoutparams = (android.widget.LinearLayout.LayoutParams)view.getLayoutParams();
        if(openItems.get(i))
        {
            view.setVisibility(0);
            layoutparams.bottomMargin = 0;
            return;
        } else
        {
            view.setVisibility(8);
            layoutparams.bottomMargin = 0 - viewHeights.get(i);
            return;
        }
    }

    private static void writeBitSet(Parcel parcel, BitSet bitset)
    {
        int i = -1;
        parcel.writeInt(bitset.cardinality());
        do
        {
            i = bitset.nextSetBit(i + 1);
            if(i != -1)
                parcel.writeInt(i);
            else
                return;
        } while(true);
    }

    public boolean collapseLastOpen()
    {
        if(isAnyItemExpanded())
        {
            if(lastOpen != null)
                animateView(lastOpen, 1);
            openItems.set(lastOpenPosition, false);
            lastOpenPosition = -1;
            return true;
        } else
        {
            return false;
        }
    }

    public void enableFor(View view, int i)
    {
        View view1 = getExpandToggleButton(view);
        View view2 = getExpandableView(view);
        view2.measure(view.getWidth(), view.getHeight());
        enableFor(view1, view2, i);
    }

    public int getAnimationDuration()
    {
        return animationDuration;
    }

    public abstract View getExpandToggleButton(View view);

    public abstract View getExpandableView(View view);

    public View getView(int i, View view, ViewGroup viewgroup)
    {
        View view1 = wrapped.getView(i, view, viewgroup);
        enableFor(view1, i);
        return view1;
    }

    public boolean isAnyItemExpanded()
    {
        return lastOpenPosition != -1;
    }

    public void onRestoreInstanceState(SavedState savedstate)
    {
        lastOpenPosition = savedstate.lastOpenPosition;
        openItems = savedstate.openItems;
    }

    public Parcelable onSaveInstanceState(Parcelable parcelable)
    {
        SavedState savedstate = new SavedState(parcelable);
        savedstate.lastOpenPosition = lastOpenPosition;
        savedstate.openItems = openItems;
        return savedstate;
    }

    public void setAnimationDuration(int i)
    {
        if(i < 0)
        {
            throw new IllegalArgumentException("Duration is less than zero");
        } else
        {
            animationDuration = i;
            return;
        }
    }

    private int animationDuration;
    protected Lazy bus;
    private View lastOpen;
    private int lastOpenPosition;
    private BitSet openItems;
    private final SparseIntArray viewHeights = new SparseIntArray(10);




/*
    static int access$102(AbstractSlideExpandableListAdapter abstractslideexpandablelistadapter, int i)
    {
        abstractslideexpandablelistadapter.lastOpenPosition = i;
        return i;
    }

*/



/*
    static View access$202(AbstractSlideExpandableListAdapter abstractslideexpandablelistadapter, View view)
    {
        abstractslideexpandablelistadapter.lastOpen = view;
        return view;
    }

*/



}
