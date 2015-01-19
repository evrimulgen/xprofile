// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.handmark.pulltorefresh.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.*;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.handmark.pulltorefresh.library.internal.FlipLoadingLayout;
import com.handmark.pulltorefresh.library.internal.LoadingLayout;
import com.handmark.pulltorefresh.library.internal.RotateLoadingLayout;
import com.handmark.pulltorefresh.library.internal.Utils;
import com.handmark.pulltorefresh.library.internal.ViewCompat;

// Referenced classes of package com.handmark.pulltorefresh.library:
//            IPullToRefresh, LoadingLayoutProxy, OverscrollHelper, ILoadingLayout

public abstract class PullToRefreshBase extends LinearLayout
    implements IPullToRefresh
{
    public static final class AnimationStyle extends Enum
    {

        static AnimationStyle getDefault()
        {
            return ROTATE;
        }

        static AnimationStyle mapIntToValue(int i)
        {
            switch(i)
            {
            default:
                return ROTATE;

            case 1: // '\001'
                return FLIP;
            }
        }

        public static AnimationStyle valueOf(String s)
        {
            return (AnimationStyle)Enum.valueOf(com/handmark/pulltorefresh/library/PullToRefreshBase$AnimationStyle, s);
        }

        public static AnimationStyle[] values()
        {
            return (AnimationStyle[])$VALUES.clone();
        }

        LoadingLayout createLoadingLayout(Context context, Mode mode, Orientation orientation, TypedArray typedarray)
        {
            static class _cls4
            {

                static final int $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$AnimationStyle[];
                static final int $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Mode[];
                static final int $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Orientation[];
                static final int $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$State[];

                static 
                {
                    $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$AnimationStyle = new int[AnimationStyle.values().length];
                    try
                    {
                        $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$AnimationStyle[AnimationStyle.ROTATE.ordinal()] = 1;
                    }
                    catch(NoSuchFieldError nosuchfielderror) { }
                    try
                    {
                        $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$AnimationStyle[AnimationStyle.FLIP.ordinal()] = 2;
                    }
                    catch(NoSuchFieldError nosuchfielderror1) { }
                    $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Mode = new int[Mode.values().length];
                    try
                    {
                        $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Mode[Mode.PULL_FROM_END.ordinal()] = 1;
                    }
                    catch(NoSuchFieldError nosuchfielderror2) { }
                    try
                    {
                        $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Mode[Mode.PULL_FROM_START.ordinal()] = 2;
                    }
                    catch(NoSuchFieldError nosuchfielderror3) { }
                    try
                    {
                        $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Mode[Mode.MANUAL_REFRESH_ONLY.ordinal()] = 3;
                    }
                    catch(NoSuchFieldError nosuchfielderror4) { }
                    try
                    {
                        $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Mode[Mode.BOTH.ordinal()] = 4;
                    }
                    catch(NoSuchFieldError nosuchfielderror5) { }
                    $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$State = new int[State.values().length];
                    try
                    {
                        $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$State[State.RESET.ordinal()] = 1;
                    }
                    catch(NoSuchFieldError nosuchfielderror6) { }
                    try
                    {
                        $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$State[State.PULL_TO_REFRESH.ordinal()] = 2;
                    }
                    catch(NoSuchFieldError nosuchfielderror7) { }
                    try
                    {
                        $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$State[State.RELEASE_TO_REFRESH.ordinal()] = 3;
                    }
                    catch(NoSuchFieldError nosuchfielderror8) { }
                    try
                    {
                        $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$State[State.REFRESHING.ordinal()] = 4;
                    }
                    catch(NoSuchFieldError nosuchfielderror9) { }
                    try
                    {
                        $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$State[State.MANUAL_REFRESHING.ordinal()] = 5;
                    }
                    catch(NoSuchFieldError nosuchfielderror10) { }
                    try
                    {
                        $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$State[State.OVERSCROLLING.ordinal()] = 6;
                    }
                    catch(NoSuchFieldError nosuchfielderror11) { }
                    $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Orientation = new int[Orientation.values().length];
                    try
                    {
                        $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Orientation[Orientation.HORIZONTAL.ordinal()] = 1;
                    }
                    catch(NoSuchFieldError nosuchfielderror12) { }
                    try
                    {
                        $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Orientation[Orientation.VERTICAL.ordinal()] = 2;
                    }
                    catch(NoSuchFieldError nosuchfielderror13)
                    {
                        return;
                    }
                }
            }

            switch(_cls4..SwitchMap.com.handmark.pulltorefresh.library.PullToRefreshBase.AnimationStyle[ordinal()])
            {
            default:
                return new RotateLoadingLayout(context, mode, orientation, typedarray);

            case 2: // '\002'
                return new FlipLoadingLayout(context, mode, orientation, typedarray);
            }
        }

        private static final AnimationStyle $VALUES[];
        public static final AnimationStyle FLIP;
        public static final AnimationStyle ROTATE;

        static 
        {
            ROTATE = new AnimationStyle("ROTATE", 0);
            FLIP = new AnimationStyle("FLIP", 1);
            AnimationStyle aanimationstyle[] = new AnimationStyle[2];
            aanimationstyle[0] = ROTATE;
            aanimationstyle[1] = FLIP;
            $VALUES = aanimationstyle;
        }

        private AnimationStyle(String s, int i)
        {
            super(s, i);
        }
    }

    public static final class Mode extends Enum
    {

        static Mode getDefault()
        {
            return PULL_FROM_START;
        }

        static Mode mapIntToValue(int i)
        {
            Mode amode[] = values();
            int j = amode.length;
            for(int k = 0; k < j; k++)
            {
                Mode mode = amode[k];
                if(i == mode.getIntValue())
                    return mode;
            }

            return getDefault();
        }

        public static Mode valueOf(String s)
        {
            return (Mode)Enum.valueOf(com/handmark/pulltorefresh/library/PullToRefreshBase$Mode, s);
        }

        public static Mode[] values()
        {
            return (Mode[])$VALUES.clone();
        }

        int getIntValue()
        {
            return mIntValue;
        }

        boolean permitsPullToRefresh()
        {
            return this != DISABLED && this != MANUAL_REFRESH_ONLY;
        }

        public boolean showFooterLoadingLayout()
        {
            return this == PULL_FROM_END || this == BOTH || this == MANUAL_REFRESH_ONLY;
        }

        public boolean showHeaderLoadingLayout()
        {
            return this == PULL_FROM_START || this == BOTH;
        }

        private static final Mode $VALUES[];
        public static final Mode BOTH;
        public static final Mode DISABLED;
        public static final Mode MANUAL_REFRESH_ONLY;
        public static Mode PULL_DOWN_TO_REFRESH;
        public static final Mode PULL_FROM_END;
        public static final Mode PULL_FROM_START;
        public static Mode PULL_UP_TO_REFRESH;
        private int mIntValue;

        static 
        {
            DISABLED = new Mode("DISABLED", 0, 0);
            PULL_FROM_START = new Mode("PULL_FROM_START", 1, 1);
            PULL_FROM_END = new Mode("PULL_FROM_END", 2, 2);
            BOTH = new Mode("BOTH", 3, 3);
            MANUAL_REFRESH_ONLY = new Mode("MANUAL_REFRESH_ONLY", 4, 4);
            Mode amode[] = new Mode[5];
            amode[0] = DISABLED;
            amode[1] = PULL_FROM_START;
            amode[2] = PULL_FROM_END;
            amode[3] = BOTH;
            amode[4] = MANUAL_REFRESH_ONLY;
            $VALUES = amode;
            PULL_DOWN_TO_REFRESH = PULL_FROM_START;
            PULL_UP_TO_REFRESH = PULL_FROM_END;
        }

        private Mode(String s, int i, int j)
        {
            super(s, i);
            mIntValue = j;
        }
    }

    public static interface OnLastItemVisibleListener
    {

        public abstract void onLastItemVisible();
    }

    public static interface OnPullEventListener
    {

        public abstract void onPullEvent(PullToRefreshBase pulltorefreshbase, State state, Mode mode);
    }

    public static interface OnRefreshListener
    {

        public abstract void onRefresh(PullToRefreshBase pulltorefreshbase);
    }

    public static interface OnRefreshListener2
    {

        public abstract void onPullDownToRefresh(PullToRefreshBase pulltorefreshbase);

        public abstract void onPullUpToRefresh(PullToRefreshBase pulltorefreshbase);
    }

    static interface OnSmoothScrollFinishedListener
    {

        public abstract void onSmoothScrollFinished();
    }

    public static final class Orientation extends Enum
    {

        public static Orientation valueOf(String s)
        {
            return (Orientation)Enum.valueOf(com/handmark/pulltorefresh/library/PullToRefreshBase$Orientation, s);
        }

        public static Orientation[] values()
        {
            return (Orientation[])$VALUES.clone();
        }

        private static final Orientation $VALUES[];
        public static final Orientation HORIZONTAL;
        public static final Orientation VERTICAL;

        static 
        {
            VERTICAL = new Orientation("VERTICAL", 0);
            HORIZONTAL = new Orientation("HORIZONTAL", 1);
            Orientation aorientation[] = new Orientation[2];
            aorientation[0] = VERTICAL;
            aorientation[1] = HORIZONTAL;
            $VALUES = aorientation;
        }

        private Orientation(String s, int i)
        {
            super(s, i);
        }
    }

    final class SmoothScrollRunnable
        implements Runnable
    {

        public void run()
        {
            if(mStartTime == -1L)
            {
                mStartTime = System.currentTimeMillis();
            } else
            {
                long l = Math.max(Math.min((1000L * (System.currentTimeMillis() - mStartTime)) / mDuration, 1000L), 0L);
                int i = Math.round((float)(mScrollFromY - mScrollToY) * mInterpolator.getInterpolation((float)l / 1000F));
                mCurrentY = mScrollFromY - i;
                setHeaderScroll(mCurrentY);
            }
            if(mContinueRunning && mScrollToY != mCurrentY)
                ViewCompat.postOnAnimation(PullToRefreshBase.this, this);
            else
            if(mListener != null)
            {
                mListener.onSmoothScrollFinished();
                return;
            }
        }

        public void stop()
        {
            mContinueRunning = false;
            removeCallbacks(this);
        }

        private boolean mContinueRunning;
        private int mCurrentY;
        private final long mDuration;
        private final Interpolator mInterpolator;
        private OnSmoothScrollFinishedListener mListener;
        private final int mScrollFromY;
        private final int mScrollToY;
        private long mStartTime;
        final PullToRefreshBase this$0;

        public SmoothScrollRunnable(int i, int j, long l, OnSmoothScrollFinishedListener onsmoothscrollfinishedlistener)
        {
            this$0 = PullToRefreshBase.this;
            super();
            mContinueRunning = true;
            mStartTime = -1L;
            mCurrentY = -1;
            mScrollFromY = i;
            mScrollToY = j;
            mInterpolator = mScrollAnimationInterpolator;
            mDuration = l;
            mListener = onsmoothscrollfinishedlistener;
        }
    }

    public static final class State extends Enum
    {

        static State mapIntToValue(int i)
        {
            State astate[] = values();
            int j = astate.length;
            for(int k = 0; k < j; k++)
            {
                State state = astate[k];
                if(i == state.getIntValue())
                    return state;
            }

            return RESET;
        }

        public static State valueOf(String s)
        {
            return (State)Enum.valueOf(com/handmark/pulltorefresh/library/PullToRefreshBase$State, s);
        }

        public static State[] values()
        {
            return (State[])$VALUES.clone();
        }

        int getIntValue()
        {
            return mIntValue;
        }

        private static final State $VALUES[];
        public static final State MANUAL_REFRESHING;
        public static final State OVERSCROLLING;
        public static final State PULL_TO_REFRESH;
        public static final State REFRESHING;
        public static final State RELEASE_TO_REFRESH;
        public static final State RESET;
        private int mIntValue;

        static 
        {
            RESET = new State("RESET", 0, 0);
            PULL_TO_REFRESH = new State("PULL_TO_REFRESH", 1, 1);
            RELEASE_TO_REFRESH = new State("RELEASE_TO_REFRESH", 2, 2);
            REFRESHING = new State("REFRESHING", 3, 8);
            MANUAL_REFRESHING = new State("MANUAL_REFRESHING", 4, 9);
            OVERSCROLLING = new State("OVERSCROLLING", 5, 16);
            State astate[] = new State[6];
            astate[0] = RESET;
            astate[1] = PULL_TO_REFRESH;
            astate[2] = RELEASE_TO_REFRESH;
            astate[3] = REFRESHING;
            astate[4] = MANUAL_REFRESHING;
            astate[5] = OVERSCROLLING;
            $VALUES = astate;
        }

        private State(String s, int i, int j)
        {
            super(s, i);
            mIntValue = j;
        }
    }


    public PullToRefreshBase(Context context)
    {
        super(context);
        mIsBeingDragged = false;
        mState = State.RESET;
        mMode = Mode.getDefault();
        mShowViewWhileRefreshing = true;
        mScrollingWhileRefreshingEnabled = false;
        mFilterTouchEvents = true;
        mOverScrollEnabled = true;
        mLayoutVisibilityChangesEnabled = true;
        mLoadingAnimationStyle = AnimationStyle.getDefault();
        init(context, null);
    }

    public PullToRefreshBase(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mIsBeingDragged = false;
        mState = State.RESET;
        mMode = Mode.getDefault();
        mShowViewWhileRefreshing = true;
        mScrollingWhileRefreshingEnabled = false;
        mFilterTouchEvents = true;
        mOverScrollEnabled = true;
        mLayoutVisibilityChangesEnabled = true;
        mLoadingAnimationStyle = AnimationStyle.getDefault();
        init(context, attributeset);
    }

    public PullToRefreshBase(Context context, Mode mode)
    {
        super(context);
        mIsBeingDragged = false;
        mState = State.RESET;
        mMode = Mode.getDefault();
        mShowViewWhileRefreshing = true;
        mScrollingWhileRefreshingEnabled = false;
        mFilterTouchEvents = true;
        mOverScrollEnabled = true;
        mLayoutVisibilityChangesEnabled = true;
        mLoadingAnimationStyle = AnimationStyle.getDefault();
        mMode = mode;
        init(context, null);
    }

    public PullToRefreshBase(Context context, Mode mode, AnimationStyle animationstyle)
    {
        super(context);
        mIsBeingDragged = false;
        mState = State.RESET;
        mMode = Mode.getDefault();
        mShowViewWhileRefreshing = true;
        mScrollingWhileRefreshingEnabled = false;
        mFilterTouchEvents = true;
        mOverScrollEnabled = true;
        mLayoutVisibilityChangesEnabled = true;
        mLoadingAnimationStyle = AnimationStyle.getDefault();
        mMode = mode;
        mLoadingAnimationStyle = animationstyle;
        init(context, null);
    }

    private void addRefreshableView(Context context, View view)
    {
        mRefreshableViewWrapper = new FrameLayout(context);
        mRefreshableViewWrapper.addView(view, -1, -1);
        addViewInternal(mRefreshableViewWrapper, new android.widget.LinearLayout.LayoutParams(-1, -1));
    }

    private void callRefreshListener()
    {
        if(mOnRefreshListener != null)
            mOnRefreshListener.onRefresh(this);
        else
        if(mOnRefreshListener2 != null)
        {
            if(mCurrentMode == Mode.PULL_FROM_START)
            {
                mOnRefreshListener2.onPullDownToRefresh(this);
                return;
            }
            if(mCurrentMode == Mode.PULL_FROM_END)
            {
                mOnRefreshListener2.onPullUpToRefresh(this);
                return;
            }
        }
    }

    private android.widget.LinearLayout.LayoutParams getLoadingLayoutLayoutParams()
    {
        switch(_cls4..SwitchMap.com.handmark.pulltorefresh.library.PullToRefreshBase.Orientation[getPullToRefreshScrollDirection().ordinal()])
        {
        default:
            return new android.widget.LinearLayout.LayoutParams(-1, -2);

        case 1: // '\001'
            return new android.widget.LinearLayout.LayoutParams(-2, -1);
        }
    }

    private int getMaximumPullScroll()
    {
        switch(_cls4..SwitchMap.com.handmark.pulltorefresh.library.PullToRefreshBase.Orientation[getPullToRefreshScrollDirection().ordinal()])
        {
        default:
            return Math.round((float)getHeight() / 2.0F);

        case 1: // '\001'
            return Math.round((float)getWidth() / 2.0F);
        }
    }

    private void init(Context context, AttributeSet attributeset)
    {
        switch(_cls4..SwitchMap.com.handmark.pulltorefresh.library.PullToRefreshBase.Orientation[getPullToRefreshScrollDirection().ordinal()])
        {
        default:
            setOrientation(1);
            break;

        case 1: // '\001'
            break MISSING_BLOCK_LABEL_231;
        }
_L1:
        setGravity(17);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        TypedArray typedarray = context.obtainStyledAttributes(attributeset, R.styleable.PullToRefresh);
        if(typedarray.hasValue(4))
            mMode = Mode.mapIntToValue(typedarray.getInteger(4, 0));
        if(typedarray.hasValue(12))
            mLoadingAnimationStyle = AnimationStyle.mapIntToValue(typedarray.getInteger(12, 0));
        mRefreshableView = createRefreshableView(context, attributeset);
        addRefreshableView(context, mRefreshableView);
        mHeaderLayout = createLoadingLayout(context, Mode.PULL_FROM_START, typedarray);
        mFooterLayout = createLoadingLayout(context, Mode.PULL_FROM_END, typedarray);
        if(typedarray.hasValue(0))
        {
            Drawable drawable1 = typedarray.getDrawable(0);
            if(drawable1 != null)
                mRefreshableView.setBackgroundDrawable(drawable1);
        } else
        if(typedarray.hasValue(16))
        {
            Utils.warnDeprecation("ptrAdapterViewBackground", "ptrRefreshableViewBackground");
            Drawable drawable = typedarray.getDrawable(16);
            if(drawable != null)
                mRefreshableView.setBackgroundDrawable(drawable);
        }
        if(typedarray.hasValue(9))
            mOverScrollEnabled = typedarray.getBoolean(9, true);
        if(typedarray.hasValue(13))
            mScrollingWhileRefreshingEnabled = typedarray.getBoolean(13, false);
        handleStyledAttributes(typedarray);
        typedarray.recycle();
        updateUIForMode();
        return;
        setOrientation(0);
          goto _L1
    }

    private boolean isReadyForPull()
    {
        _cls4..SwitchMap.com.handmark.pulltorefresh.library.PullToRefreshBase.Mode[mMode.ordinal()];
        JVM INSTR tableswitch 1 4: default 40
    //                   1 47
    //                   2 42
    //                   3 40
    //                   4 52;
           goto _L1 _L2 _L3 _L1 _L4
_L1:
        return false;
_L3:
        return isReadyForPullStart();
_L2:
        return isReadyForPullEnd();
_L4:
        if(isReadyForPullEnd() || isReadyForPullStart())
            return true;
        if(true) goto _L1; else goto _L5
_L5:
    }

    private void pullEvent()
    {
        _cls4..SwitchMap.com.handmark.pulltorefresh.library.PullToRefreshBase.Orientation[getPullToRefreshScrollDirection().ordinal()];
        JVM INSTR tableswitch 1 1: default 28
    //                   1 183;
           goto _L1 _L2
_L1:
        float f;
        float f1;
        f = mInitialMotionY;
        f1 = mLastMotionY;
_L15:
        _cls4..SwitchMap.com.handmark.pulltorefresh.library.PullToRefreshBase.Mode[mCurrentMode.ordinal()];
        JVM INSTR tableswitch 1 1: default 68
    //                   1 196;
           goto _L3 _L4
_L3:
        int i;
        int j;
        i = Math.round(Math.min(f - f1, 0.0F) / 2.0F);
        j = getHeaderSize();
_L11:
        setHeaderScroll(i);
        if(i == 0 || isRefreshing()) goto _L6; else goto _L5
_L5:
        float f2 = (float)Math.abs(i) / (float)j;
        _cls4..SwitchMap.com.handmark.pulltorefresh.library.PullToRefreshBase.Mode[mCurrentMode.ordinal()];
        JVM INSTR tableswitch 1 1: default 144
    //                   1 218;
           goto _L7 _L8
_L7:
        mHeaderLayout.onPull(f2);
_L12:
        if(mState == State.PULL_TO_REFRESH || j < Math.abs(i)) goto _L10; else goto _L9
_L9:
        setState(State.PULL_TO_REFRESH, new boolean[0]);
_L6:
        return;
_L2:
        f = mInitialMotionX;
        f1 = mLastMotionX;
        continue; /* Loop/switch isn't completed */
_L4:
        i = Math.round(Math.max(f - f1, 0.0F) / 2.0F);
        j = getFooterSize();
          goto _L11
_L8:
        mFooterLayout.onPull(f2);
          goto _L12
_L10:
        if(mState != State.PULL_TO_REFRESH || j >= Math.abs(i)) goto _L6; else goto _L13
_L13:
        setState(State.RELEASE_TO_REFRESH, new boolean[0]);
        return;
        if(true) goto _L15; else goto _L14
_L14:
    }

    private final void smoothScrollTo(int i, long l)
    {
        smoothScrollTo(i, l, 0L, null);
    }

    private final void smoothScrollTo(int i, long l, long l1, OnSmoothScrollFinishedListener onsmoothscrollfinishedlistener)
    {
        if(mCurrentSmoothScrollRunnable != null)
            mCurrentSmoothScrollRunnable.stop();
        _cls4..SwitchMap.com.handmark.pulltorefresh.library.PullToRefreshBase.Orientation[getPullToRefreshScrollDirection().ordinal()];
        JVM INSTR tableswitch 1 1: default 44
    //                   1 111;
           goto _L1 _L2
_L1:
        int j = getScrollY();
_L4:
        if(j != i)
        {
            if(mScrollAnimationInterpolator == null)
                mScrollAnimationInterpolator = new DecelerateInterpolator();
            mCurrentSmoothScrollRunnable = new SmoothScrollRunnable(j, i, l, onsmoothscrollfinishedlistener);
            if(l1 <= 0L)
                break; /* Loop/switch isn't completed */
            postDelayed(mCurrentSmoothScrollRunnable, l1);
        }
        return;
_L2:
        j = getScrollX();
        if(true) goto _L4; else goto _L3
_L3:
        post(mCurrentSmoothScrollRunnable);
        return;
    }

    private final void smoothScrollToAndBack(int i)
    {
        smoothScrollTo(i, 200L, 0L, new OnSmoothScrollFinishedListener() {

            public void onSmoothScrollFinished()
            {
                smoothScrollTo(0, 200L, 225L, null);
            }

            final PullToRefreshBase this$0;

            
            {
                this$0 = PullToRefreshBase.this;
                super();
            }
        }
);
    }

    public void addView(View view, int i, android.view.ViewGroup.LayoutParams layoutparams)
    {
        Log.d("PullToRefresh", (new StringBuilder()).append("addView: ").append(view.getClass().getSimpleName()).toString());
        View view1 = getRefreshableView();
        if(view1 instanceof ViewGroup)
        {
            ((ViewGroup)view1).addView(view, i, layoutparams);
            return;
        } else
        {
            throw new UnsupportedOperationException("Refreshable View is not a ViewGroup so can't addView");
        }
    }

    protected final void addViewInternal(View view, int i, android.view.ViewGroup.LayoutParams layoutparams)
    {
        super.addView(view, i, layoutparams);
    }

    protected final void addViewInternal(View view, android.view.ViewGroup.LayoutParams layoutparams)
    {
        super.addView(view, -1, layoutparams);
    }

    protected LoadingLayout createLoadingLayout(Context context, Mode mode, TypedArray typedarray)
    {
        LoadingLayout loadinglayout = mLoadingAnimationStyle.createLoadingLayout(context, mode, getPullToRefreshScrollDirection(), typedarray);
        loadinglayout.setVisibility(4);
        return loadinglayout;
    }

    protected LoadingLayoutProxy createLoadingLayoutProxy(boolean flag, boolean flag1)
    {
        LoadingLayoutProxy loadinglayoutproxy = new LoadingLayoutProxy();
        if(flag && mMode.showHeaderLoadingLayout())
            loadinglayoutproxy.addLayout(mHeaderLayout);
        if(flag1 && mMode.showFooterLoadingLayout())
            loadinglayoutproxy.addLayout(mFooterLayout);
        return loadinglayoutproxy;
    }

    protected abstract View createRefreshableView(Context context, AttributeSet attributeset);

    public final boolean demo()
    {
        if(mMode.showHeaderLoadingLayout() && isReadyForPullStart())
        {
            smoothScrollToAndBack(2 * -getHeaderSize());
            return true;
        }
        if(mMode.showFooterLoadingLayout() && isReadyForPullEnd())
        {
            smoothScrollToAndBack(2 * getFooterSize());
            return true;
        } else
        {
            return false;
        }
    }

    protected final void disableLoadingLayoutVisibilityChanges()
    {
        mLayoutVisibilityChangesEnabled = false;
    }

    public final Mode getCurrentMode()
    {
        return mCurrentMode;
    }

    public final boolean getFilterTouchEvents()
    {
        return mFilterTouchEvents;
    }

    protected final LoadingLayout getFooterLayout()
    {
        return mFooterLayout;
    }

    protected final int getFooterSize()
    {
        return mFooterLayout.getContentSize();
    }

    protected final LoadingLayout getHeaderLayout()
    {
        return mHeaderLayout;
    }

    protected final int getHeaderSize()
    {
        return mHeaderLayout.getContentSize();
    }

    public final ILoadingLayout getLoadingLayoutProxy()
    {
        return getLoadingLayoutProxy(true, true);
    }

    public final ILoadingLayout getLoadingLayoutProxy(boolean flag, boolean flag1)
    {
        return createLoadingLayoutProxy(flag, flag1);
    }

    public final Mode getMode()
    {
        return mMode;
    }

    public abstract Orientation getPullToRefreshScrollDirection();

    protected int getPullToRefreshScrollDuration()
    {
        return 200;
    }

    protected int getPullToRefreshScrollDurationLonger()
    {
        return 325;
    }

    public final View getRefreshableView()
    {
        return mRefreshableView;
    }

    protected FrameLayout getRefreshableViewWrapper()
    {
        return mRefreshableViewWrapper;
    }

    public final boolean getShowViewWhileRefreshing()
    {
        return mShowViewWhileRefreshing;
    }

    public final State getState()
    {
        return mState;
    }

    protected void handleStyledAttributes(TypedArray typedarray)
    {
    }

    public final boolean isDisableScrollingWhileRefreshing()
    {
        return !isScrollingWhileRefreshingEnabled();
    }

    public final boolean isPullToRefreshEnabled()
    {
        return mMode.permitsPullToRefresh();
    }

    public final boolean isPullToRefreshOverScrollEnabled()
    {
        return android.os.Build.VERSION.SDK_INT >= 9 && mOverScrollEnabled && OverscrollHelper.isAndroidOverScrollEnabled(mRefreshableView);
    }

    protected abstract boolean isReadyForPullEnd();

    protected abstract boolean isReadyForPullStart();

    public final boolean isRefreshing()
    {
        return mState == State.REFRESHING || mState == State.MANUAL_REFRESHING;
    }

    public final boolean isScrollingWhileRefreshingEnabled()
    {
        return mScrollingWhileRefreshingEnabled;
    }

    public final boolean onInterceptTouchEvent(MotionEvent motionevent)
    {
        int i;
        if(!isPullToRefreshEnabled())
            return false;
        i = motionevent.getAction();
        if(i == 3 || i == 1)
        {
            mIsBeingDragged = false;
            return false;
        }
        if(i != 0 && mIsBeingDragged)
            return true;
        i;
        JVM INSTR tableswitch 0 2: default 72
    //                   0 339
    //                   1 72
    //                   2 77;
           goto _L1 _L2 _L1 _L3
_L1:
        return mIsBeingDragged;
_L3:
        float f2;
        float f3;
        if(!mScrollingWhileRefreshingEnabled && isRefreshing())
            return true;
        if(!isReadyForPull())
            continue; /* Loop/switch isn't completed */
        f2 = motionevent.getY();
        f3 = motionevent.getX();
        _cls4..SwitchMap.com.handmark.pulltorefresh.library.PullToRefreshBase.Orientation[getPullToRefreshScrollDirection().ordinal()];
        JVM INSTR tableswitch 1 1: default 140
    //                   1 255;
           goto _L4 _L5
_L4:
        float f4;
        float f5;
        f4 = f2 - mLastMotionY;
        f5 = f3 - mLastMotionX;
_L6:
        float f6 = Math.abs(f4);
        if(f6 > (float)mTouchSlop && (!mFilterTouchEvents || f6 > Math.abs(f5)))
            if(mMode.showHeaderLoadingLayout() && f4 >= 1.0F && isReadyForPullStart())
            {
                mLastMotionY = f2;
                mLastMotionX = f3;
                mIsBeingDragged = true;
                if(mMode == Mode.BOTH)
                    mCurrentMode = Mode.PULL_FROM_START;
            } else
            if(mMode.showFooterLoadingLayout() && f4 <= -1F && isReadyForPullEnd())
            {
                mLastMotionY = f2;
                mLastMotionX = f3;
                mIsBeingDragged = true;
                if(mMode == Mode.BOTH)
                    mCurrentMode = Mode.PULL_FROM_END;
            }
        continue; /* Loop/switch isn't completed */
_L5:
        f4 = f3 - mLastMotionX;
        f5 = f2 - mLastMotionY;
          goto _L6
_L2:
        if(isReadyForPull())
        {
            float f = motionevent.getY();
            mInitialMotionY = f;
            mLastMotionY = f;
            float f1 = motionevent.getX();
            mInitialMotionX = f1;
            mLastMotionX = f1;
            mIsBeingDragged = false;
        }
        if(true) goto _L1; else goto _L7
_L7:
    }

    protected void onPtrRestoreInstanceState(Bundle bundle)
    {
    }

    protected void onPtrSaveInstanceState(Bundle bundle)
    {
    }

    protected void onPullToRefresh()
    {
        switch(_cls4..SwitchMap.com.handmark.pulltorefresh.library.PullToRefreshBase.Mode[mCurrentMode.ordinal()])
        {
        default:
            return;

        case 1: // '\001'
            mFooterLayout.pullToRefresh();
            return;

        case 2: // '\002'
            mHeaderLayout.pullToRefresh();
            break;
        }
    }

    public final void onRefreshComplete()
    {
        if(isRefreshing())
            setState(State.RESET, new boolean[0]);
    }

    protected void onRefreshing(boolean flag)
    {
        if(mMode.showHeaderLoadingLayout())
            mHeaderLayout.refreshing();
        if(mMode.showFooterLoadingLayout())
            mFooterLayout.refreshing();
        if(flag)
        {
            if(mShowViewWhileRefreshing)
            {
                OnSmoothScrollFinishedListener onsmoothscrollfinishedlistener = new OnSmoothScrollFinishedListener() {

                    public void onSmoothScrollFinished()
                    {
                        callRefreshListener();
                    }

                    final PullToRefreshBase this$0;

            
            {
                this$0 = PullToRefreshBase.this;
                super();
            }
                }
;
                switch(_cls4..SwitchMap.com.handmark.pulltorefresh.library.PullToRefreshBase.Mode[mCurrentMode.ordinal()])
                {
                case 2: // '\002'
                default:
                    smoothScrollTo(-getHeaderSize(), onsmoothscrollfinishedlistener);
                    return;

                case 1: // '\001'
                case 3: // '\003'
                    smoothScrollTo(getFooterSize(), onsmoothscrollfinishedlistener);
                    break;
                }
                return;
            } else
            {
                smoothScrollTo(0);
                return;
            }
        } else
        {
            callRefreshListener();
            return;
        }
    }

    protected void onReleaseToRefresh()
    {
        switch(_cls4..SwitchMap.com.handmark.pulltorefresh.library.PullToRefreshBase.Mode[mCurrentMode.ordinal()])
        {
        default:
            return;

        case 1: // '\001'
            mFooterLayout.releaseToRefresh();
            return;

        case 2: // '\002'
            mHeaderLayout.releaseToRefresh();
            break;
        }
    }

    protected void onReset()
    {
        mIsBeingDragged = false;
        mLayoutVisibilityChangesEnabled = true;
        mHeaderLayout.reset();
        mFooterLayout.reset();
        smoothScrollTo(0);
    }

    protected final void onRestoreInstanceState(Parcelable parcelable)
    {
        if(parcelable instanceof Bundle)
        {
            Bundle bundle = (Bundle)parcelable;
            setMode(Mode.mapIntToValue(bundle.getInt("ptr_mode", 0)));
            mCurrentMode = Mode.mapIntToValue(bundle.getInt("ptr_current_mode", 0));
            mScrollingWhileRefreshingEnabled = bundle.getBoolean("ptr_disable_scrolling", false);
            mShowViewWhileRefreshing = bundle.getBoolean("ptr_show_refreshing_view", true);
            super.onRestoreInstanceState(bundle.getParcelable("ptr_super"));
            State state = State.mapIntToValue(bundle.getInt("ptr_state", 0));
            if(state == State.REFRESHING || state == State.MANUAL_REFRESHING)
                setState(state, new boolean[] {
                    true
                });
            onPtrRestoreInstanceState(bundle);
            return;
        } else
        {
            super.onRestoreInstanceState(parcelable);
            return;
        }
    }

    protected final Parcelable onSaveInstanceState()
    {
        Bundle bundle = new Bundle();
        onPtrSaveInstanceState(bundle);
        bundle.putInt("ptr_state", mState.getIntValue());
        bundle.putInt("ptr_mode", mMode.getIntValue());
        bundle.putInt("ptr_current_mode", mCurrentMode.getIntValue());
        bundle.putBoolean("ptr_disable_scrolling", mScrollingWhileRefreshingEnabled);
        bundle.putBoolean("ptr_show_refreshing_view", mShowViewWhileRefreshing);
        bundle.putParcelable("ptr_super", super.onSaveInstanceState());
        return bundle;
    }

    protected final void onSizeChanged(int i, int j, int k, int l)
    {
        Object aobj[] = new Object[2];
        aobj[0] = Integer.valueOf(i);
        aobj[1] = Integer.valueOf(j);
        Log.d("PullToRefresh", String.format("onSizeChanged. W: %d, H: %d", aobj));
        super.onSizeChanged(i, j, k, l);
        refreshLoadingViewsSize();
        refreshRefreshableViewSize(i, j);
        post(new Runnable() {

            public void run()
            {
                requestLayout();
            }

            final PullToRefreshBase this$0;

            
            {
                this$0 = PullToRefreshBase.this;
                super();
            }
        }
);
    }

    public final boolean onTouchEvent(MotionEvent motionevent)
    {
        if(isPullToRefreshEnabled()) goto _L2; else goto _L1
_L1:
        return false;
_L2:
        if(!mScrollingWhileRefreshingEnabled && isRefreshing())
            return true;
        if(motionevent.getAction() == 0 && motionevent.getEdgeFlags() != 0) goto _L1; else goto _L3
_L3:
        motionevent.getAction();
        JVM INSTR tableswitch 0 3: default 72
    //                   0 74
    //                   1 142
    //                   2 113
    //                   3 142;
           goto _L4 _L5 _L6 _L7 _L6
_L6:
        continue; /* Loop/switch isn't completed */
_L4:
        return false;
_L5:
        if(!isReadyForPull()) goto _L1; else goto _L8
_L8:
        float f = motionevent.getY();
        mInitialMotionY = f;
        mLastMotionY = f;
        float f1 = motionevent.getX();
        mInitialMotionX = f1;
        mLastMotionX = f1;
        return true;
_L7:
        if(!mIsBeingDragged) goto _L1; else goto _L9
_L9:
        mLastMotionY = motionevent.getY();
        mLastMotionX = motionevent.getX();
        pullEvent();
        return true;
        if(!mIsBeingDragged) goto _L1; else goto _L10
_L10:
        mIsBeingDragged = false;
        if(mState == State.RELEASE_TO_REFRESH && (mOnRefreshListener != null || mOnRefreshListener2 != null))
        {
            setState(State.REFRESHING, new boolean[] {
                true
            });
            return true;
        }
        if(isRefreshing())
        {
            smoothScrollTo(0);
            return true;
        } else
        {
            setState(State.RESET, new boolean[0]);
            return true;
        }
    }

    protected final void refreshLoadingViewsSize()
    {
        int i;
        int j;
        int k;
        int l;
        int i1;
        i = (int)(1.2F * (float)getMaximumPullScroll());
        j = getPaddingLeft();
        k = getPaddingTop();
        l = getPaddingRight();
        i1 = getPaddingBottom();
        _cls4..SwitchMap.com.handmark.pulltorefresh.library.PullToRefreshBase.Orientation[getPullToRefreshScrollDirection().ordinal()];
        JVM INSTR tableswitch 1 2: default 68
    //                   1 133
    //                   2 190;
           goto _L1 _L2 _L3
_L1:
        Object aobj[] = new Object[4];
        aobj[0] = Integer.valueOf(j);
        aobj[1] = Integer.valueOf(k);
        aobj[2] = Integer.valueOf(l);
        aobj[3] = Integer.valueOf(i1);
        Log.d("PullToRefresh", String.format("Setting Padding. L: %d, T: %d, R: %d, B: %d", aobj));
        setPadding(j, k, l, i1);
        return;
_L2:
        if(mMode.showHeaderLoadingLayout())
        {
            mHeaderLayout.setWidth(i);
            j = -i;
        } else
        {
            j = 0;
        }
        if(mMode.showFooterLoadingLayout())
        {
            mFooterLayout.setWidth(i);
            l = -i;
        } else
        {
            l = 0;
        }
        continue; /* Loop/switch isn't completed */
_L3:
        if(mMode.showHeaderLoadingLayout())
        {
            mHeaderLayout.setHeight(i);
            k = -i;
        } else
        {
            k = 0;
        }
        if(mMode.showFooterLoadingLayout())
        {
            mFooterLayout.setHeight(i);
            i1 = -i;
        } else
        {
            i1 = 0;
        }
        if(true) goto _L1; else goto _L4
_L4:
    }

    protected final void refreshRefreshableViewSize(int i, int j)
    {
        android.widget.LinearLayout.LayoutParams layoutparams = (android.widget.LinearLayout.LayoutParams)mRefreshableViewWrapper.getLayoutParams();
        _cls4..SwitchMap.com.handmark.pulltorefresh.library.PullToRefreshBase.Orientation[getPullToRefreshScrollDirection().ordinal()];
        JVM INSTR tableswitch 1 2: default 44
    //                   1 45
    //                   2 66;
           goto _L1 _L2 _L3
_L1:
        return;
_L2:
        if(layoutparams.width != i)
        {
            layoutparams.width = i;
            mRefreshableViewWrapper.requestLayout();
            return;
        }
        continue; /* Loop/switch isn't completed */
_L3:
        if(layoutparams.height != j)
        {
            layoutparams.height = j;
            mRefreshableViewWrapper.requestLayout();
            return;
        }
        if(true) goto _L1; else goto _L4
_L4:
    }

    public void setDisableScrollingWhileRefreshing(boolean flag)
    {
        boolean flag1;
        if(!flag)
            flag1 = true;
        else
            flag1 = false;
        setScrollingWhileRefreshingEnabled(flag1);
    }

    public final void setFilterTouchEvents(boolean flag)
    {
        mFilterTouchEvents = flag;
    }

    protected final void setHeaderScroll(int i)
    {
        Log.d("PullToRefresh", (new StringBuilder()).append("setHeaderScroll: ").append(i).toString());
        int j = getMaximumPullScroll();
        int k = Math.min(j, Math.max(-j, i));
        if(mLayoutVisibilityChangesEnabled)
            if(k < 0)
                mHeaderLayout.setVisibility(0);
            else
            if(k > 0)
            {
                mFooterLayout.setVisibility(0);
            } else
            {
                mHeaderLayout.setVisibility(4);
                mFooterLayout.setVisibility(4);
            }
        switch(_cls4..SwitchMap.com.handmark.pulltorefresh.library.PullToRefreshBase.Orientation[getPullToRefreshScrollDirection().ordinal()])
        {
        default:
            return;

        case 2: // '\002'
            scrollTo(0, k);
            return;

        case 1: // '\001'
            scrollTo(k, 0);
            return;
        }
    }

    public void setLastUpdatedLabel(CharSequence charsequence)
    {
        getLoadingLayoutProxy().setLastUpdatedLabel(charsequence);
    }

    public void setLoadingDrawable(Drawable drawable)
    {
        getLoadingLayoutProxy().setLoadingDrawable(drawable);
    }

    public void setLoadingDrawable(Drawable drawable, Mode mode)
    {
        getLoadingLayoutProxy(mode.showHeaderLoadingLayout(), mode.showFooterLoadingLayout()).setLoadingDrawable(drawable);
    }

    public void setLongClickable(boolean flag)
    {
        getRefreshableView().setLongClickable(flag);
    }

    public final void setMode(Mode mode)
    {
        if(mode != mMode)
        {
            Log.d("PullToRefresh", (new StringBuilder()).append("Setting mode to: ").append(mode).toString());
            mMode = mode;
            updateUIForMode();
        }
    }

    public void setOnPullEventListener(OnPullEventListener onpulleventlistener)
    {
        mOnPullEventListener = onpulleventlistener;
    }

    public final void setOnRefreshListener(OnRefreshListener2 onrefreshlistener2)
    {
        mOnRefreshListener2 = onrefreshlistener2;
        mOnRefreshListener = null;
    }

    public final void setOnRefreshListener(OnRefreshListener onrefreshlistener)
    {
        mOnRefreshListener = onrefreshlistener;
        mOnRefreshListener2 = null;
    }

    public void setPullLabel(CharSequence charsequence)
    {
        getLoadingLayoutProxy().setPullLabel(charsequence);
    }

    public void setPullLabel(CharSequence charsequence, Mode mode)
    {
        getLoadingLayoutProxy(mode.showHeaderLoadingLayout(), mode.showFooterLoadingLayout()).setPullLabel(charsequence);
    }

    public final void setPullToRefreshEnabled(boolean flag)
    {
        Mode mode;
        if(flag)
            mode = Mode.getDefault();
        else
            mode = Mode.DISABLED;
        setMode(mode);
    }

    public final void setPullToRefreshOverScrollEnabled(boolean flag)
    {
        mOverScrollEnabled = flag;
    }

    public final void setRefreshing()
    {
        setRefreshing(true);
    }

    public final void setRefreshing(boolean flag)
    {
        if(!isRefreshing())
            setState(State.MANUAL_REFRESHING, new boolean[] {
                flag
            });
    }

    public void setRefreshingLabel(CharSequence charsequence)
    {
        getLoadingLayoutProxy().setRefreshingLabel(charsequence);
    }

    public void setRefreshingLabel(CharSequence charsequence, Mode mode)
    {
        getLoadingLayoutProxy(mode.showHeaderLoadingLayout(), mode.showFooterLoadingLayout()).setRefreshingLabel(charsequence);
    }

    public void setReleaseLabel(CharSequence charsequence)
    {
        setReleaseLabel(charsequence, Mode.BOTH);
    }

    public void setReleaseLabel(CharSequence charsequence, Mode mode)
    {
        getLoadingLayoutProxy(mode.showHeaderLoadingLayout(), mode.showFooterLoadingLayout()).setReleaseLabel(charsequence);
    }

    public void setScrollAnimationInterpolator(Interpolator interpolator)
    {
        mScrollAnimationInterpolator = interpolator;
    }

    public final void setScrollingWhileRefreshingEnabled(boolean flag)
    {
        mScrollingWhileRefreshingEnabled = flag;
    }

    public final void setShowViewWhileRefreshing(boolean flag)
    {
        mShowViewWhileRefreshing = flag;
    }

    final transient void setState(State state, boolean aflag[])
    {
        mState = state;
        Log.d("PullToRefresh", (new StringBuilder()).append("State: ").append(mState.name()).toString());
        _cls4..SwitchMap.com.handmark.pulltorefresh.library.PullToRefreshBase.State[mState.ordinal()];
        JVM INSTR tableswitch 1 5: default 84
    //                   1 110
    //                   2 117
    //                   3 124
    //                   4 131
    //                   5 131;
           goto _L1 _L2 _L3 _L4 _L5 _L5
_L1:
        if(mOnPullEventListener != null)
            mOnPullEventListener.onPullEvent(this, mState, mCurrentMode);
        return;
_L2:
        onReset();
        continue; /* Loop/switch isn't completed */
_L3:
        onPullToRefresh();
        continue; /* Loop/switch isn't completed */
_L4:
        onReleaseToRefresh();
        continue; /* Loop/switch isn't completed */
_L5:
        onRefreshing(aflag[0]);
        if(true) goto _L1; else goto _L6
_L6:
    }

    protected final void smoothScrollTo(int i)
    {
        smoothScrollTo(i, getPullToRefreshScrollDuration());
    }

    protected final void smoothScrollTo(int i, OnSmoothScrollFinishedListener onsmoothscrollfinishedlistener)
    {
        smoothScrollTo(i, getPullToRefreshScrollDuration(), 0L, onsmoothscrollfinishedlistener);
    }

    protected final void smoothScrollToLonger(int i)
    {
        smoothScrollTo(i, getPullToRefreshScrollDurationLonger());
    }

    protected void updateUIForMode()
    {
        android.widget.LinearLayout.LayoutParams layoutparams = getLoadingLayoutLayoutParams();
        if(this == mHeaderLayout.getParent())
            removeView(mHeaderLayout);
        if(mMode.showHeaderLoadingLayout())
            addViewInternal(mHeaderLayout, 0, layoutparams);
        if(this == mFooterLayout.getParent())
            removeView(mFooterLayout);
        if(mMode.showFooterLoadingLayout())
            addViewInternal(mFooterLayout, layoutparams);
        refreshLoadingViewsSize();
        Mode mode;
        if(mMode != Mode.BOTH)
            mode = mMode;
        else
            mode = Mode.PULL_FROM_START;
        mCurrentMode = mode;
    }

    static final boolean DEBUG = true;
    static final int DEMO_SCROLL_INTERVAL = 225;
    static final float FRICTION = 2F;
    static final String LOG_TAG = "PullToRefresh";
    public static final int SMOOTH_SCROLL_DURATION_MS = 200;
    public static final int SMOOTH_SCROLL_LONG_DURATION_MS = 325;
    static final String STATE_CURRENT_MODE = "ptr_current_mode";
    static final String STATE_MODE = "ptr_mode";
    static final String STATE_SCROLLING_REFRESHING_ENABLED = "ptr_disable_scrolling";
    static final String STATE_SHOW_REFRESHING_VIEW = "ptr_show_refreshing_view";
    static final String STATE_STATE = "ptr_state";
    static final String STATE_SUPER = "ptr_super";
    static final boolean USE_HW_LAYERS;
    private Mode mCurrentMode;
    private SmoothScrollRunnable mCurrentSmoothScrollRunnable;
    private boolean mFilterTouchEvents;
    private LoadingLayout mFooterLayout;
    private LoadingLayout mHeaderLayout;
    private float mInitialMotionX;
    private float mInitialMotionY;
    private boolean mIsBeingDragged;
    private float mLastMotionX;
    private float mLastMotionY;
    private boolean mLayoutVisibilityChangesEnabled;
    private AnimationStyle mLoadingAnimationStyle;
    private Mode mMode;
    private OnPullEventListener mOnPullEventListener;
    private OnRefreshListener mOnRefreshListener;
    private OnRefreshListener2 mOnRefreshListener2;
    private boolean mOverScrollEnabled;
    View mRefreshableView;
    private FrameLayout mRefreshableViewWrapper;
    private Interpolator mScrollAnimationInterpolator;
    private boolean mScrollingWhileRefreshingEnabled;
    private boolean mShowViewWhileRefreshing;
    private State mState;
    private int mTouchSlop;



}
