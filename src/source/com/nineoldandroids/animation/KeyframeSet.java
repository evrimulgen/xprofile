// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.nineoldandroids.animation;

import android.view.animation.Interpolator;
import java.util.ArrayList;
import java.util.Arrays;

// Referenced classes of package com.nineoldandroids.animation:
//            Keyframe, FloatKeyframeSet, IntKeyframeSet, TypeEvaluator

class KeyframeSet
{

    public transient KeyframeSet(Keyframe akeyframe[])
    {
        mNumKeyframes = akeyframe.length;
        mKeyframes = new ArrayList();
        mKeyframes.addAll(Arrays.asList(akeyframe));
        mFirstKeyframe = (Keyframe)mKeyframes.get(0);
        mLastKeyframe = (Keyframe)mKeyframes.get(-1 + mNumKeyframes);
        mInterpolator = mLastKeyframe.getInterpolator();
    }

    public static transient KeyframeSet ofFloat(float af[])
    {
        int i = af.length;
        Keyframe.FloatKeyframe afloatkeyframe[] = new Keyframe.FloatKeyframe[Math.max(i, 2)];
        if(i == 1)
        {
            afloatkeyframe[0] = (Keyframe.FloatKeyframe)Keyframe.ofFloat(0.0F);
            afloatkeyframe[1] = (Keyframe.FloatKeyframe)Keyframe.ofFloat(1.0F, af[0]);
        } else
        {
            afloatkeyframe[0] = (Keyframe.FloatKeyframe)Keyframe.ofFloat(0.0F, af[0]);
            int j = 1;
            while(j < i) 
            {
                afloatkeyframe[j] = (Keyframe.FloatKeyframe)Keyframe.ofFloat((float)j / (float)(i - 1), af[j]);
                j++;
            }
        }
        return new FloatKeyframeSet(afloatkeyframe);
    }

    public static transient KeyframeSet ofInt(int ai[])
    {
        int i = ai.length;
        Keyframe.IntKeyframe aintkeyframe[] = new Keyframe.IntKeyframe[Math.max(i, 2)];
        if(i == 1)
        {
            aintkeyframe[0] = (Keyframe.IntKeyframe)Keyframe.ofInt(0.0F);
            aintkeyframe[1] = (Keyframe.IntKeyframe)Keyframe.ofInt(1.0F, ai[0]);
        } else
        {
            aintkeyframe[0] = (Keyframe.IntKeyframe)Keyframe.ofInt(0.0F, ai[0]);
            int j = 1;
            while(j < i) 
            {
                aintkeyframe[j] = (Keyframe.IntKeyframe)Keyframe.ofInt((float)j / (float)(i - 1), ai[j]);
                j++;
            }
        }
        return new IntKeyframeSet(aintkeyframe);
    }

    public static transient KeyframeSet ofKeyframe(Keyframe akeyframe[])
    {
        int i = akeyframe.length;
        boolean flag = false;
        boolean flag1 = false;
        boolean flag2 = false;
        int j = 0;
        while(j < i) 
        {
            if(akeyframe[j] instanceof Keyframe.FloatKeyframe)
                flag = true;
            else
            if(akeyframe[j] instanceof Keyframe.IntKeyframe)
                flag1 = true;
            else
                flag2 = true;
            j++;
        }
        if(flag && !flag1 && !flag2)
        {
            Keyframe.FloatKeyframe afloatkeyframe[] = new Keyframe.FloatKeyframe[i];
            for(int l = 0; l < i; l++)
                afloatkeyframe[l] = (Keyframe.FloatKeyframe)akeyframe[l];

            return new FloatKeyframeSet(afloatkeyframe);
        }
        if(flag1 && !flag && !flag2)
        {
            Keyframe.IntKeyframe aintkeyframe[] = new Keyframe.IntKeyframe[i];
            for(int k = 0; k < i; k++)
                aintkeyframe[k] = (Keyframe.IntKeyframe)akeyframe[k];

            return new IntKeyframeSet(aintkeyframe);
        } else
        {
            return new KeyframeSet(akeyframe);
        }
    }

    public static transient KeyframeSet ofObject(Object aobj[])
    {
        int i = aobj.length;
        Keyframe.ObjectKeyframe aobjectkeyframe[] = new Keyframe.ObjectKeyframe[Math.max(i, 2)];
        if(i == 1)
        {
            aobjectkeyframe[0] = (Keyframe.ObjectKeyframe)Keyframe.ofObject(0.0F);
            aobjectkeyframe[1] = (Keyframe.ObjectKeyframe)Keyframe.ofObject(1.0F, aobj[0]);
        } else
        {
            aobjectkeyframe[0] = (Keyframe.ObjectKeyframe)Keyframe.ofObject(0.0F, aobj[0]);
            int j = 1;
            while(j < i) 
            {
                aobjectkeyframe[j] = (Keyframe.ObjectKeyframe)Keyframe.ofObject((float)j / (float)(i - 1), aobj[j]);
                j++;
            }
        }
        return new KeyframeSet(aobjectkeyframe);
    }

    public KeyframeSet clone()
    {
        ArrayList arraylist = mKeyframes;
        int i = mKeyframes.size();
        Keyframe akeyframe[] = new Keyframe[i];
        for(int j = 0; j < i; j++)
            akeyframe[j] = ((Keyframe)arraylist.get(j)).clone();

        return new KeyframeSet(akeyframe);
    }

    public volatile Object clone()
        throws CloneNotSupportedException
    {
        return clone();
    }

    public Object getValue(float f)
    {
        if(mNumKeyframes == 2)
        {
            if(mInterpolator != null)
                f = mInterpolator.getInterpolation(f);
            return mEvaluator.evaluate(f, mFirstKeyframe.getValue(), mLastKeyframe.getValue());
        }
        if(f <= 0.0F)
        {
            Keyframe keyframe3 = (Keyframe)mKeyframes.get(1);
            Interpolator interpolator2 = keyframe3.getInterpolator();
            if(interpolator2 != null)
                f = interpolator2.getInterpolation(f);
            float f5 = mFirstKeyframe.getFraction();
            float f6 = (f - f5) / (keyframe3.getFraction() - f5);
            return mEvaluator.evaluate(f6, mFirstKeyframe.getValue(), keyframe3.getValue());
        }
        if(f >= 1.0F)
        {
            Keyframe keyframe2 = (Keyframe)mKeyframes.get(-2 + mNumKeyframes);
            Interpolator interpolator1 = mLastKeyframe.getInterpolator();
            if(interpolator1 != null)
                f = interpolator1.getInterpolation(f);
            float f3 = keyframe2.getFraction();
            float f4 = (f - f3) / (mLastKeyframe.getFraction() - f3);
            return mEvaluator.evaluate(f4, keyframe2.getValue(), mLastKeyframe.getValue());
        }
        Keyframe keyframe = mFirstKeyframe;
        for(int i = 1; i < mNumKeyframes; i++)
        {
            Keyframe keyframe1 = (Keyframe)mKeyframes.get(i);
            if(f < keyframe1.getFraction())
            {
                Interpolator interpolator = keyframe1.getInterpolator();
                if(interpolator != null)
                    f = interpolator.getInterpolation(f);
                float f1 = keyframe.getFraction();
                float f2 = (f - f1) / (keyframe1.getFraction() - f1);
                return mEvaluator.evaluate(f2, keyframe.getValue(), keyframe1.getValue());
            }
            keyframe = keyframe1;
        }

        return mLastKeyframe.getValue();
    }

    public void setEvaluator(TypeEvaluator typeevaluator)
    {
        mEvaluator = typeevaluator;
    }

    public String toString()
    {
        String s = " ";
        for(int i = 0; i < mNumKeyframes; i++)
            s = (new StringBuilder()).append(s).append(((Keyframe)mKeyframes.get(i)).getValue()).append("  ").toString();

        return s;
    }

    TypeEvaluator mEvaluator;
    Keyframe mFirstKeyframe;
    Interpolator mInterpolator;
    ArrayList mKeyframes;
    Keyframe mLastKeyframe;
    int mNumKeyframes;
}
