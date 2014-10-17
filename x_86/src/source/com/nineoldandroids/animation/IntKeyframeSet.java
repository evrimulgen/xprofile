// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.nineoldandroids.animation;

import android.view.animation.Interpolator;
import java.util.ArrayList;

// Referenced classes of package com.nineoldandroids.animation:
//            KeyframeSet, Keyframe, TypeEvaluator

class IntKeyframeSet extends KeyframeSet
{

    public transient IntKeyframeSet(Keyframe.IntKeyframe aintkeyframe[])
    {
        super(aintkeyframe);
        firstTime = true;
    }

    public IntKeyframeSet clone()
    {
        ArrayList arraylist = mKeyframes;
        int i = mKeyframes.size();
        Keyframe.IntKeyframe aintkeyframe[] = new Keyframe.IntKeyframe[i];
        for(int j = 0; j < i; j++)
            aintkeyframe[j] = (Keyframe.IntKeyframe)((Keyframe)arraylist.get(j)).clone();

        return new IntKeyframeSet(aintkeyframe);
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

    public int getIntValue(float f)
    {
        if(mNumKeyframes == 2)
        {
            if(firstTime)
            {
                firstTime = false;
                firstValue = ((Keyframe.IntKeyframe)mKeyframes.get(0)).getIntValue();
                lastValue = ((Keyframe.IntKeyframe)mKeyframes.get(1)).getIntValue();
                deltaValue = lastValue - firstValue;
            }
            if(mInterpolator != null)
                f = mInterpolator.getInterpolation(f);
            if(mEvaluator == null)
                return firstValue + (int)(f * (float)deltaValue);
            else
                return ((Number)mEvaluator.evaluate(f, Integer.valueOf(firstValue), Integer.valueOf(lastValue))).intValue();
        }
        if(f <= 0.0F)
        {
            Keyframe.IntKeyframe intkeyframe4 = (Keyframe.IntKeyframe)mKeyframes.get(0);
            Keyframe.IntKeyframe intkeyframe5 = (Keyframe.IntKeyframe)mKeyframes.get(1);
            int j1 = intkeyframe4.getIntValue();
            int k1 = intkeyframe5.getIntValue();
            float f5 = intkeyframe4.getFraction();
            float f6 = intkeyframe5.getFraction();
            Interpolator interpolator2 = intkeyframe5.getInterpolator();
            if(interpolator2 != null)
                f = interpolator2.getInterpolation(f);
            float f7 = (f - f5) / (f6 - f5);
            if(mEvaluator == null)
                return j1 + (int)(f7 * (float)(k1 - j1));
            else
                return ((Number)mEvaluator.evaluate(f7, Integer.valueOf(j1), Integer.valueOf(k1))).intValue();
        }
        if(f >= 1.0F)
        {
            Keyframe.IntKeyframe intkeyframe2 = (Keyframe.IntKeyframe)mKeyframes.get(-2 + mNumKeyframes);
            Keyframe.IntKeyframe intkeyframe3 = (Keyframe.IntKeyframe)mKeyframes.get(-1 + mNumKeyframes);
            int l = intkeyframe2.getIntValue();
            int i1 = intkeyframe3.getIntValue();
            float f2 = intkeyframe2.getFraction();
            float f3 = intkeyframe3.getFraction();
            Interpolator interpolator1 = intkeyframe3.getInterpolator();
            if(interpolator1 != null)
                f = interpolator1.getInterpolation(f);
            float f4 = (f - f2) / (f3 - f2);
            if(mEvaluator == null)
                return l + (int)(f4 * (float)(i1 - l));
            else
                return ((Number)mEvaluator.evaluate(f4, Integer.valueOf(l), Integer.valueOf(i1))).intValue();
        }
        Keyframe.IntKeyframe intkeyframe = (Keyframe.IntKeyframe)mKeyframes.get(0);
        for(int i = 1; i < mNumKeyframes; i++)
        {
            Keyframe.IntKeyframe intkeyframe1 = (Keyframe.IntKeyframe)mKeyframes.get(i);
            if(f < intkeyframe1.getFraction())
            {
                Interpolator interpolator = intkeyframe1.getInterpolator();
                if(interpolator != null)
                    f = interpolator.getInterpolation(f);
                float f1 = (f - intkeyframe.getFraction()) / (intkeyframe1.getFraction() - intkeyframe.getFraction());
                int j = intkeyframe.getIntValue();
                int k = intkeyframe1.getIntValue();
                if(mEvaluator == null)
                    return j + (int)(f1 * (float)(k - j));
                else
                    return ((Number)mEvaluator.evaluate(f1, Integer.valueOf(j), Integer.valueOf(k))).intValue();
            }
            intkeyframe = intkeyframe1;
        }

        return ((Number)((Keyframe)mKeyframes.get(-1 + mNumKeyframes)).getValue()).intValue();
    }

    public Object getValue(float f)
    {
        return Integer.valueOf(getIntValue(f));
    }

    private int deltaValue;
    private boolean firstTime;
    private int firstValue;
    private int lastValue;
}
