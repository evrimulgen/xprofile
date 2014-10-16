// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.nineoldandroids.view;

import android.view.View;
import android.view.animation.Interpolator;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.ValueAnimator;
import java.lang.ref.WeakReference;
import java.util.*;

// Referenced classes of package com.nineoldandroids.view:
//            ViewPropertyAnimator

class ViewPropertyAnimatorHC extends ViewPropertyAnimator
{
    private class AnimatorEventListener
        implements com.nineoldandroids.animation.Animator.AnimatorListener, com.nineoldandroids.animation.ValueAnimator.AnimatorUpdateListener
    {

        public void onAnimationCancel(Animator animator)
        {
            if(mListener != null)
                mListener.onAnimationCancel(animator);
        }

        public void onAnimationEnd(Animator animator)
        {
            if(mListener != null)
                mListener.onAnimationEnd(animator);
            mAnimatorMap.remove(animator);
            if(mAnimatorMap.isEmpty())
                mListener = null;
        }

        public void onAnimationRepeat(Animator animator)
        {
            if(mListener != null)
                mListener.onAnimationRepeat(animator);
        }

        public void onAnimationStart(Animator animator)
        {
            if(mListener != null)
                mListener.onAnimationStart(animator);
        }

        public void onAnimationUpdate(ValueAnimator valueanimator)
        {
            float f = valueanimator.getAnimatedFraction();
            PropertyBundle propertybundle = (PropertyBundle)mAnimatorMap.get(valueanimator);
            if((0x1ff & propertybundle.mPropertyMask) != 0)
            {
                View view1 = (View)mView.get();
                if(view1 != null)
                    view1.invalidate();
            }
            ArrayList arraylist = propertybundle.mNameValuesHolder;
            if(arraylist != null)
            {
                int i = arraylist.size();
                for(int j = 0; j < i; j++)
                {
                    NameValuesHolder namevaluesholder = (NameValuesHolder)arraylist.get(j);
                    float f1 = namevaluesholder.mFromValue + f * namevaluesholder.mDeltaValue;
                    setValue(namevaluesholder.mNameConstant, f1);
                }

            }
            View view = (View)mView.get();
            if(view != null)
                view.invalidate();
        }

        final ViewPropertyAnimatorHC this$0;

        private AnimatorEventListener()
        {
            this$0 = ViewPropertyAnimatorHC.this;
            super();
        }

    }

    private static class NameValuesHolder
    {

        float mDeltaValue;
        float mFromValue;
        int mNameConstant;

        NameValuesHolder(int i, float f, float f1)
        {
            mNameConstant = i;
            mFromValue = f;
            mDeltaValue = f1;
        }
    }

    private static class PropertyBundle
    {

        boolean cancel(int i)
        {
            if((i & mPropertyMask) != 0 && mNameValuesHolder != null)
            {
                int j = mNameValuesHolder.size();
                for(int k = 0; k < j; k++)
                    if(((NameValuesHolder)mNameValuesHolder.get(k)).mNameConstant == i)
                    {
                        mNameValuesHolder.remove(k);
                        mPropertyMask = mPropertyMask & ~i;
                        return true;
                    }

            }
            return false;
        }

        ArrayList mNameValuesHolder;
        int mPropertyMask;

        PropertyBundle(int i, ArrayList arraylist)
        {
            mPropertyMask = i;
            mNameValuesHolder = arraylist;
        }
    }


    ViewPropertyAnimatorHC(View view)
    {
        mDurationSet = false;
        mStartDelay = 0L;
        mStartDelaySet = false;
        mInterpolatorSet = false;
        mListener = null;
        mAnimatorEventListener = new AnimatorEventListener();
        mPendingAnimations = new ArrayList();
        mAnimationStarter = new Runnable() {

            public void run()
            {
                startAnimation();
            }

            final ViewPropertyAnimatorHC this$0;

            
            {
                this$0 = ViewPropertyAnimatorHC.this;
                super();
            }
        }
;
        mAnimatorMap = new HashMap();
        mView = new WeakReference(view);
    }

    private void animateProperty(int i, float f)
    {
        float f1 = getValue(i);
        animatePropertyBy(i, f1, f - f1);
    }

    private void animatePropertyBy(int i, float f)
    {
        animatePropertyBy(i, getValue(i), f);
    }

    private void animatePropertyBy(int i, float f, float f1)
    {
        if(mAnimatorMap.size() > 0)
        {
            Iterator iterator = mAnimatorMap.keySet().iterator();
            Animator animator;
            do
            {
                boolean flag = iterator.hasNext();
                animator = null;
                if(!flag)
                    break;
                Animator animator1 = (Animator)iterator.next();
                PropertyBundle propertybundle = (PropertyBundle)mAnimatorMap.get(animator1);
                if(!propertybundle.cancel(i) || propertybundle.mPropertyMask != 0)
                    continue;
                animator = animator1;
                break;
            } while(true);
            if(animator != null)
                animator.cancel();
        }
        NameValuesHolder namevaluesholder = new NameValuesHolder(i, f, f1);
        mPendingAnimations.add(namevaluesholder);
        View view = (View)mView.get();
        if(view != null)
        {
            view.removeCallbacks(mAnimationStarter);
            view.post(mAnimationStarter);
        }
    }

    private float getValue(int i)
    {
        View view = (View)mView.get();
        if(view == null) goto _L2; else goto _L1
_L1:
        i;
        JVM INSTR lookupswitch 10: default 108
    //                   1: 110
    //                   2: 115
    //                   4: 135
    //                   8: 140
    //                   16: 120
    //                   32: 125
    //                   64: 130
    //                   128: 145
    //                   256: 150
    //                   512: 155;
           goto _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12
_L2:
        return 0.0F;
_L3:
        return view.getTranslationX();
_L4:
        return view.getTranslationY();
_L7:
        return view.getRotation();
_L8:
        return view.getRotationX();
_L9:
        return view.getRotationY();
_L5:
        return view.getScaleX();
_L6:
        return view.getScaleY();
_L10:
        return view.getX();
_L11:
        return view.getY();
_L12:
        return view.getAlpha();
    }

    private void setValue(int i, float f)
    {
        View view = (View)mView.get();
        if(view == null) goto _L2; else goto _L1
_L1:
        i;
        JVM INSTR lookupswitch 10: default 108
    //                   1: 109
    //                   2: 115
    //                   4: 139
    //                   8: 145
    //                   16: 121
    //                   32: 127
    //                   64: 133
    //                   128: 151
    //                   256: 157
    //                   512: 163;
           goto _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12
_L2:
        return;
_L3:
        view.setTranslationX(f);
        return;
_L4:
        view.setTranslationY(f);
        return;
_L7:
        view.setRotation(f);
        return;
_L8:
        view.setRotationX(f);
        return;
_L9:
        view.setRotationY(f);
        return;
_L5:
        view.setScaleX(f);
        return;
_L6:
        view.setScaleY(f);
        return;
_L10:
        view.setX(f);
        return;
_L11:
        view.setY(f);
        return;
_L12:
        view.setAlpha(f);
        return;
    }

    private void startAnimation()
    {
        ValueAnimator valueanimator = ValueAnimator.ofFloat(new float[] {
            1.0F
        });
        ArrayList arraylist = (ArrayList)mPendingAnimations.clone();
        mPendingAnimations.clear();
        int i = 0;
        int j = arraylist.size();
        for(int k = 0; k < j; k++)
            i |= ((NameValuesHolder)arraylist.get(k)).mNameConstant;

        mAnimatorMap.put(valueanimator, new PropertyBundle(i, arraylist));
        valueanimator.addUpdateListener(mAnimatorEventListener);
        valueanimator.addListener(mAnimatorEventListener);
        if(mStartDelaySet)
            valueanimator.setStartDelay(mStartDelay);
        if(mDurationSet)
            valueanimator.setDuration(mDuration);
        if(mInterpolatorSet)
            valueanimator.setInterpolator(mInterpolator);
        valueanimator.start();
    }

    public ViewPropertyAnimator alpha(float f)
    {
        animateProperty(512, f);
        return this;
    }

    public ViewPropertyAnimator alphaBy(float f)
    {
        animatePropertyBy(512, f);
        return this;
    }

    public void cancel()
    {
        if(mAnimatorMap.size() > 0)
        {
            for(Iterator iterator = ((HashMap)mAnimatorMap.clone()).keySet().iterator(); iterator.hasNext(); ((Animator)iterator.next()).cancel());
        }
        mPendingAnimations.clear();
        View view = (View)mView.get();
        if(view != null)
            view.removeCallbacks(mAnimationStarter);
    }

    public long getDuration()
    {
        if(mDurationSet)
            return mDuration;
        else
            return (new ValueAnimator()).getDuration();
    }

    public long getStartDelay()
    {
        if(mStartDelaySet)
            return mStartDelay;
        else
            return 0L;
    }

    public ViewPropertyAnimator rotation(float f)
    {
        animateProperty(16, f);
        return this;
    }

    public ViewPropertyAnimator rotationBy(float f)
    {
        animatePropertyBy(16, f);
        return this;
    }

    public ViewPropertyAnimator rotationX(float f)
    {
        animateProperty(32, f);
        return this;
    }

    public ViewPropertyAnimator rotationXBy(float f)
    {
        animatePropertyBy(32, f);
        return this;
    }

    public ViewPropertyAnimator rotationY(float f)
    {
        animateProperty(64, f);
        return this;
    }

    public ViewPropertyAnimator rotationYBy(float f)
    {
        animatePropertyBy(64, f);
        return this;
    }

    public ViewPropertyAnimator scaleX(float f)
    {
        animateProperty(4, f);
        return this;
    }

    public ViewPropertyAnimator scaleXBy(float f)
    {
        animatePropertyBy(4, f);
        return this;
    }

    public ViewPropertyAnimator scaleY(float f)
    {
        animateProperty(8, f);
        return this;
    }

    public ViewPropertyAnimator scaleYBy(float f)
    {
        animatePropertyBy(8, f);
        return this;
    }

    public ViewPropertyAnimator setDuration(long l)
    {
        if(l < 0L)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Animators cannot have negative duration: ").append(l).toString());
        } else
        {
            mDurationSet = true;
            mDuration = l;
            return this;
        }
    }

    public ViewPropertyAnimator setInterpolator(Interpolator interpolator)
    {
        mInterpolatorSet = true;
        mInterpolator = interpolator;
        return this;
    }

    public ViewPropertyAnimator setListener(com.nineoldandroids.animation.Animator.AnimatorListener animatorlistener)
    {
        mListener = animatorlistener;
        return this;
    }

    public ViewPropertyAnimator setStartDelay(long l)
    {
        if(l < 0L)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Animators cannot have negative duration: ").append(l).toString());
        } else
        {
            mStartDelaySet = true;
            mStartDelay = l;
            return this;
        }
    }

    public void start()
    {
        startAnimation();
    }

    public ViewPropertyAnimator translationX(float f)
    {
        animateProperty(1, f);
        return this;
    }

    public ViewPropertyAnimator translationXBy(float f)
    {
        animatePropertyBy(1, f);
        return this;
    }

    public ViewPropertyAnimator translationY(float f)
    {
        animateProperty(2, f);
        return this;
    }

    public ViewPropertyAnimator translationYBy(float f)
    {
        animatePropertyBy(2, f);
        return this;
    }

    public ViewPropertyAnimator x(float f)
    {
        animateProperty(128, f);
        return this;
    }

    public ViewPropertyAnimator xBy(float f)
    {
        animatePropertyBy(128, f);
        return this;
    }

    public ViewPropertyAnimator y(float f)
    {
        animateProperty(256, f);
        return this;
    }

    public ViewPropertyAnimator yBy(float f)
    {
        animatePropertyBy(256, f);
        return this;
    }

    private static final int ALPHA = 512;
    private static final int NONE = 0;
    private static final int ROTATION = 16;
    private static final int ROTATION_X = 32;
    private static final int ROTATION_Y = 64;
    private static final int SCALE_X = 4;
    private static final int SCALE_Y = 8;
    private static final int TRANSFORM_MASK = 511;
    private static final int TRANSLATION_X = 1;
    private static final int TRANSLATION_Y = 2;
    private static final int X = 128;
    private static final int Y = 256;
    private Runnable mAnimationStarter;
    private AnimatorEventListener mAnimatorEventListener;
    private HashMap mAnimatorMap;
    private long mDuration;
    private boolean mDurationSet;
    private Interpolator mInterpolator;
    private boolean mInterpolatorSet;
    private com.nineoldandroids.animation.Animator.AnimatorListener mListener;
    ArrayList mPendingAnimations;
    private long mStartDelay;
    private boolean mStartDelaySet;
    private final WeakReference mView;




/*
    static com.nineoldandroids.animation.Animator.AnimatorListener access$202(ViewPropertyAnimatorHC viewpropertyanimatorhc, com.nineoldandroids.animation.Animator.AnimatorListener animatorlistener)
    {
        viewpropertyanimatorhc.mListener = animatorlistener;
        return animatorlistener;
    }

*/



}
