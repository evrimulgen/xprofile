// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.nineoldandroids.view;

import android.view.View;
import android.view.animation.Interpolator;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.ValueAnimator;
import com.nineoldandroids.view.animation.AnimatorProxy;
import java.lang.ref.WeakReference;
import java.util.*;

// Referenced classes of package com.nineoldandroids.view:
//            ViewPropertyAnimator

class ViewPropertyAnimatorPreHC extends ViewPropertyAnimator
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

        final ViewPropertyAnimatorPreHC this$0;

        private AnimatorEventListener()
        {
            this$0 = ViewPropertyAnimatorPreHC.this;
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


    ViewPropertyAnimatorPreHC(View view)
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

            final ViewPropertyAnimatorPreHC this$0;

            
            {
                this$0 = ViewPropertyAnimatorPreHC.this;
                super();
            }
        }
;
        mAnimatorMap = new HashMap();
        mView = new WeakReference(view);
        mProxy = AnimatorProxy.wrap(view);
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
        switch(i)
        {
        default:
            return 0.0F;

        case 1: // '\001'
            return mProxy.getTranslationX();

        case 2: // '\002'
            return mProxy.getTranslationY();

        case 16: // '\020'
            return mProxy.getRotation();

        case 32: // ' '
            return mProxy.getRotationX();

        case 64: // '@'
            return mProxy.getRotationY();

        case 4: // '\004'
            return mProxy.getScaleX();

        case 8: // '\b'
            return mProxy.getScaleY();

        case 128: 
            return mProxy.getX();

        case 256: 
            return mProxy.getY();

        case 512: 
            return mProxy.getAlpha();
        }
    }

    private void setValue(int i, float f)
    {
        switch(i)
        {
        default:
            return;

        case 1: // '\001'
            mProxy.setTranslationX(f);
            return;

        case 2: // '\002'
            mProxy.setTranslationY(f);
            return;

        case 16: // '\020'
            mProxy.setRotation(f);
            return;

        case 32: // ' '
            mProxy.setRotationX(f);
            return;

        case 64: // '@'
            mProxy.setRotationY(f);
            return;

        case 4: // '\004'
            mProxy.setScaleX(f);
            return;

        case 8: // '\b'
            mProxy.setScaleY(f);
            return;

        case 128: 
            mProxy.setX(f);
            return;

        case 256: 
            mProxy.setY(f);
            return;

        case 512: 
            mProxy.setAlpha(f);
            break;
        }
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
    private final AnimatorProxy mProxy;
    private long mStartDelay;
    private boolean mStartDelaySet;
    private final WeakReference mView;




/*
    static com.nineoldandroids.animation.Animator.AnimatorListener access$202(ViewPropertyAnimatorPreHC viewpropertyanimatorprehc, com.nineoldandroids.animation.Animator.AnimatorListener animatorlistener)
    {
        viewpropertyanimatorprehc.mListener = animatorlistener;
        return animatorlistener;
    }

*/



}
