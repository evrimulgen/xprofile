// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.nineoldandroids.animation;

import android.view.animation.Interpolator;
import java.util.ArrayList;

// Referenced classes of package com.nineoldandroids.animation:
//            KeyframeSet, Keyframe, TypeEvaluator

class FloatKeyframeSet extends KeyframeSet
{

    public transient FloatKeyframeSet(Keyframe.FloatKeyframe afloatkeyframe[])
    {
        super(afloatkeyframe);
        firstTime = true;
    }

    public FloatKeyframeSet clone()
    {
        ArrayList arraylist = mKeyframes;
        int i = mKeyframes.size();
        Keyframe.FloatKeyframe afloatkeyframe[] = new Keyframe.FloatKeyframe[i];
        for(int j = 0; j < i; j++)
            afloatkeyframe[j] = (Keyframe.FloatKeyframe)((Keyframe)arraylist.get(j)).clone();

        return new FloatKeyframeSet(afloatkeyframe);
    }

    public volatile KeyframeSet clone()
    {
        return clone();
    }

    public volatile Object clone()
        throws CloneNotSupportedException
    {
        return clone();
    }

    public float getFloatValue(float f)
    {
        if(mNumKeyframes == 2)
        {
            if(firstTime)
            {
                firstTime = false;
                firstValue = ((Keyframe.FloatKeyframe)mKeyframes.get(0)).getFloatValue();
                lastValue = ((Keyframe.FloatKeyframe)mKeyframes.get(1)).getFloatValue();
                deltaValue = lastValue - firstValue;
            }
            if(mInterpolator != null)
                f = mInterpolator.getInterpolation(f);
            if(mEvaluator == null)
                return firstValue + f * deltaValue;
            else
                return ((Number)mEvaluator.evaluate(f, Float.valueOf(firstValue), Float.valueOf(lastValue))).floatValue();
        }
        if(f <= 0.0F)
        {
            Keyframe.FloatKeyframe floatkeyframe4 = (Keyframe.FloatKeyframe)mKeyframes.get(0);
            Keyframe.FloatKeyframe floatkeyframe5 = (Keyframe.FloatKeyframe)mKeyframes.get(1);
            float f9 = floatkeyframe4.getFloatValue();
            float f10 = floatkeyframe5.getFloatValue();
            float f11 = floatkeyframe4.getFraction();
            float f12 = floatkeyframe5.getFraction();
            Interpolator interpolator2 = floatkeyframe5.getInterpolator();
            if(interpolator2 != null)
                f = interpolator2.getInterpolation(f);
            float f13 = (f - f11) / (f12 - f11);
            if(mEvaluator == null)
                return f9 + f13 * (f10 - f9);
            else
                return ((Number)mEvaluator.evaluate(f13, Float.valueOf(f9), Float.valueOf(f10))).floatValue();
        }
        if(f >= 1.0F)
        {
            Keyframe.FloatKeyframe floatkeyframe2 = (Keyframe.FloatKeyframe)mKeyframes.get(-2 + mNumKeyframes);
            Keyframe.FloatKeyframe floatkeyframe3 = (Keyframe.FloatKeyframe)mKeyframes.get(-1 + mNumKeyframes);
            float f4 = floatkeyframe2.getFloatValue();
            float f5 = floatkeyframe3.getFloatValue();
            float f6 = floatkeyframe2.getFraction();
            float f7 = floatkeyframe3.getFraction();
            Interpolator interpolator1 = floatkeyframe3.getInterpolator();
            if(interpolator1 != null)
                f = interpolator1.getInterpolation(f);
            float f8 = (f - f6) / (f7 - f6);
            if(mEvaluator == null)
                return f4 + f8 * (f5 - f4);
            else
                return ((Number)mEvaluator.evaluate(f8, Float.valueOf(f4), Float.valueOf(f5))).floatValue();
        }
        Keyframe.FloatKeyframe floatkeyframe = (Keyframe.FloatKeyframe)mKeyframes.get(0);
        for(int i = 1; i < mNumKeyframes; i++)
        {
            Keyframe.FloatKeyframe floatkeyframe1 = (Keyframe.FloatKeyframe)mKeyframes.get(i);
            if(f < floatkeyframe1.getFraction())
            {
                Interpolator interpolator = floatkeyframe1.getInterpolator();
                if(interpolator != null)
                    f = interpolator.getInterpolation(f);
                float f1 = (f - floatkeyframe.getFraction()) / (floatkeyframe1.getFraction() - floatkeyframe.getFraction());
                float f2 = floatkeyframe.getFloatValue();
                float f3 = floatkeyframe1.getFloatValue();
                if(mEvaluator == null)
                    return f2 + f1 * (f3 - f2);
                else
                    return ((Number)mEvaluator.evaluate(f1, Float.valueOf(f2), Float.valueOf(f3))).floatValue();
            }
            floatkeyframe = floatkeyframe1;
        }

        return ((Number)((Keyframe)mKeyframes.get(-1 + mNumKeyframes)).getValue()).floatValue();
    }

    public Object getValue(float f)
    {
        return Float.valueOf(getFloatValue(f));
    }

    private float deltaValue;
    private boolean firstTime;
    private float firstValue;
    private float lastValue;
}
