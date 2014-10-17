// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.nineoldandroids.animation;

import android.view.View;
import com.nineoldandroids.util.Property;
import com.nineoldandroids.view.animation.AnimatorProxy;
import java.util.HashMap;
import java.util.Map;

// Referenced classes of package com.nineoldandroids.animation:
//            ValueAnimator, PreHoneycombCompat, PropertyValuesHolder, TypeEvaluator, 
//            Animator

public final class ObjectAnimator extends ValueAnimator
{

    public ObjectAnimator()
    {
    }

    private ObjectAnimator(Object obj, Property property)
    {
        mTarget = obj;
        setProperty(property);
    }

    private ObjectAnimator(Object obj, String s)
    {
        mTarget = obj;
        setPropertyName(s);
    }

    public static transient ObjectAnimator ofFloat(Object obj, Property property, float af[])
    {
        ObjectAnimator objectanimator = new ObjectAnimator(obj, property);
        objectanimator.setFloatValues(af);
        return objectanimator;
    }

    public static transient ObjectAnimator ofFloat(Object obj, String s, float af[])
    {
        ObjectAnimator objectanimator = new ObjectAnimator(obj, s);
        objectanimator.setFloatValues(af);
        return objectanimator;
    }

    public static transient ObjectAnimator ofInt(Object obj, Property property, int ai[])
    {
        ObjectAnimator objectanimator = new ObjectAnimator(obj, property);
        objectanimator.setIntValues(ai);
        return objectanimator;
    }

    public static transient ObjectAnimator ofInt(Object obj, String s, int ai[])
    {
        ObjectAnimator objectanimator = new ObjectAnimator(obj, s);
        objectanimator.setIntValues(ai);
        return objectanimator;
    }

    public static transient ObjectAnimator ofObject(Object obj, Property property, TypeEvaluator typeevaluator, Object aobj[])
    {
        ObjectAnimator objectanimator = new ObjectAnimator(obj, property);
        objectanimator.setObjectValues(aobj);
        objectanimator.setEvaluator(typeevaluator);
        return objectanimator;
    }

    public static transient ObjectAnimator ofObject(Object obj, String s, TypeEvaluator typeevaluator, Object aobj[])
    {
        ObjectAnimator objectanimator = new ObjectAnimator(obj, s);
        objectanimator.setObjectValues(aobj);
        objectanimator.setEvaluator(typeevaluator);
        return objectanimator;
    }

    public static transient ObjectAnimator ofPropertyValuesHolder(Object obj, PropertyValuesHolder apropertyvaluesholder[])
    {
        ObjectAnimator objectanimator = new ObjectAnimator();
        objectanimator.mTarget = obj;
        objectanimator.setValues(apropertyvaluesholder);
        return objectanimator;
    }

    void animateValue(float f)
    {
        super.animateValue(f);
        int i = mValues.length;
        for(int j = 0; j < i; j++)
            mValues[j].setAnimatedValue(mTarget);

    }

    public volatile Animator clone()
    {
        return clone();
    }

    public ObjectAnimator clone()
    {
        return (ObjectAnimator)super.clone();
    }

    public volatile ValueAnimator clone()
    {
        return clone();
    }

    public volatile Object clone()
        throws CloneNotSupportedException
    {
        return clone();
    }

    public String getPropertyName()
    {
        return mPropertyName;
    }

    public Object getTarget()
    {
        return mTarget;
    }

    void initAnimation()
    {
        if(!mInitialized)
        {
            if(mProperty == null && AnimatorProxy.NEEDS_PROXY && (mTarget instanceof View) && PROXY_PROPERTIES.containsKey(mPropertyName))
                setProperty((Property)PROXY_PROPERTIES.get(mPropertyName));
            int i = mValues.length;
            for(int j = 0; j < i; j++)
                mValues[j].setupSetterAndGetter(mTarget);

            super.initAnimation();
        }
    }

    public volatile Animator setDuration(long l)
    {
        return setDuration(l);
    }

    public ObjectAnimator setDuration(long l)
    {
        super.setDuration(l);
        return this;
    }

    public volatile ValueAnimator setDuration(long l)
    {
        return setDuration(l);
    }

    public transient void setFloatValues(float af[])
    {
        if(mValues == null || mValues.length == 0)
        {
            if(mProperty != null)
            {
                PropertyValuesHolder apropertyvaluesholder1[] = new PropertyValuesHolder[1];
                apropertyvaluesholder1[0] = PropertyValuesHolder.ofFloat(mProperty, af);
                setValues(apropertyvaluesholder1);
                return;
            } else
            {
                PropertyValuesHolder apropertyvaluesholder[] = new PropertyValuesHolder[1];
                apropertyvaluesholder[0] = PropertyValuesHolder.ofFloat(mPropertyName, af);
                setValues(apropertyvaluesholder);
                return;
            }
        } else
        {
            super.setFloatValues(af);
            return;
        }
    }

    public transient void setIntValues(int ai[])
    {
        if(mValues == null || mValues.length == 0)
        {
            if(mProperty != null)
            {
                PropertyValuesHolder apropertyvaluesholder1[] = new PropertyValuesHolder[1];
                apropertyvaluesholder1[0] = PropertyValuesHolder.ofInt(mProperty, ai);
                setValues(apropertyvaluesholder1);
                return;
            } else
            {
                PropertyValuesHolder apropertyvaluesholder[] = new PropertyValuesHolder[1];
                apropertyvaluesholder[0] = PropertyValuesHolder.ofInt(mPropertyName, ai);
                setValues(apropertyvaluesholder);
                return;
            }
        } else
        {
            super.setIntValues(ai);
            return;
        }
    }

    public transient void setObjectValues(Object aobj[])
    {
        if(mValues == null || mValues.length == 0)
        {
            if(mProperty != null)
            {
                PropertyValuesHolder apropertyvaluesholder1[] = new PropertyValuesHolder[1];
                apropertyvaluesholder1[0] = PropertyValuesHolder.ofObject(mProperty, (TypeEvaluator)null, aobj);
                setValues(apropertyvaluesholder1);
                return;
            } else
            {
                PropertyValuesHolder apropertyvaluesholder[] = new PropertyValuesHolder[1];
                apropertyvaluesholder[0] = PropertyValuesHolder.ofObject(mPropertyName, (TypeEvaluator)null, aobj);
                setValues(apropertyvaluesholder);
                return;
            }
        } else
        {
            super.setObjectValues(aobj);
            return;
        }
    }

    public void setProperty(Property property)
    {
        if(mValues != null)
        {
            PropertyValuesHolder propertyvaluesholder = mValues[0];
            String s = propertyvaluesholder.getPropertyName();
            propertyvaluesholder.setProperty(property);
            mValuesMap.remove(s);
            mValuesMap.put(mPropertyName, propertyvaluesholder);
        }
        if(mProperty != null)
            mPropertyName = property.getName();
        mProperty = property;
        mInitialized = false;
    }

    public void setPropertyName(String s)
    {
        if(mValues != null)
        {
            PropertyValuesHolder propertyvaluesholder = mValues[0];
            String s1 = propertyvaluesholder.getPropertyName();
            propertyvaluesholder.setPropertyName(s);
            mValuesMap.remove(s1);
            mValuesMap.put(s, propertyvaluesholder);
        }
        mPropertyName = s;
        mInitialized = false;
    }

    public void setTarget(Object obj)
    {
label0:
        {
            if(mTarget != obj)
            {
                Object obj1 = mTarget;
                mTarget = obj;
                if(obj1 == null || obj == null || obj1.getClass() != obj.getClass())
                    break label0;
            }
            return;
        }
        mInitialized = false;
    }

    public void setupEndValues()
    {
        initAnimation();
        int i = mValues.length;
        for(int j = 0; j < i; j++)
            mValues[j].setupEndValue(mTarget);

    }

    public void setupStartValues()
    {
        initAnimation();
        int i = mValues.length;
        for(int j = 0; j < i; j++)
            mValues[j].setupStartValue(mTarget);

    }

    public void start()
    {
        super.start();
    }

    public String toString()
    {
        String s = (new StringBuilder()).append("ObjectAnimator@").append(Integer.toHexString(hashCode())).append(", target ").append(mTarget).toString();
        if(mValues != null)
        {
            for(int i = 0; i < mValues.length; i++)
                s = (new StringBuilder()).append(s).append("\n    ").append(mValues[i].toString()).toString();

        }
        return s;
    }

    private static final boolean DBG;
    private static final Map PROXY_PROPERTIES;
    private Property mProperty;
    private String mPropertyName;
    private Object mTarget;

    static 
    {
        PROXY_PROPERTIES = new HashMap();
        PROXY_PROPERTIES.put("alpha", PreHoneycombCompat.ALPHA);
        PROXY_PROPERTIES.put("pivotX", PreHoneycombCompat.PIVOT_X);
        PROXY_PROPERTIES.put("pivotY", PreHoneycombCompat.PIVOT_Y);
        PROXY_PROPERTIES.put("translationX", PreHoneycombCompat.TRANSLATION_X);
        PROXY_PROPERTIES.put("translationY", PreHoneycombCompat.TRANSLATION_Y);
        PROXY_PROPERTIES.put("rotation", PreHoneycombCompat.ROTATION);
        PROXY_PROPERTIES.put("rotationX", PreHoneycombCompat.ROTATION_X);
        PROXY_PROPERTIES.put("rotationY", PreHoneycombCompat.ROTATION_Y);
        PROXY_PROPERTIES.put("scaleX", PreHoneycombCompat.SCALE_X);
        PROXY_PROPERTIES.put("scaleY", PreHoneycombCompat.SCALE_Y);
        PROXY_PROPERTIES.put("scrollX", PreHoneycombCompat.SCROLL_X);
        PROXY_PROPERTIES.put("scrollY", PreHoneycombCompat.SCROLL_Y);
        PROXY_PROPERTIES.put("x", PreHoneycombCompat.X);
        PROXY_PROPERTIES.put("y", PreHoneycombCompat.Y);
    }
}
