// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeBindings;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.Serializable;
import java.lang.reflect.*;

// Referenced classes of package com.fasterxml.jackson.databind.introspect:
//            AnnotatedWithParams, AnnotationMap, Annotated

public final class AnnotatedConstructor extends AnnotatedWithParams
{
    private static final class Serialization
        implements Serializable
    {

        private static final long serialVersionUID = 1L;
        protected Class args[];
        protected Class clazz;

        public Serialization(Constructor constructor)
        {
            clazz = constructor.getDeclaringClass();
            args = constructor.getParameterTypes();
        }
    }


    protected AnnotatedConstructor(Serialization serialization)
    {
        super(null, null);
        _constructor = null;
        _serialization = serialization;
    }

    public AnnotatedConstructor(Constructor constructor, AnnotationMap annotationmap, AnnotationMap aannotationmap[])
    {
        super(annotationmap, aannotationmap);
        if(constructor == null)
        {
            throw new IllegalArgumentException("Null constructor not allowed");
        } else
        {
            _constructor = constructor;
            return;
        }
    }

    public final Object call()
        throws Exception
    {
        return _constructor.newInstance(new Object[0]);
    }

    public final Object call(Object aobj[])
        throws Exception
    {
        return _constructor.newInstance(aobj);
    }

    public final Object call1(Object obj)
        throws Exception
    {
        return _constructor.newInstance(new Object[] {
            obj
        });
    }

    public volatile AnnotatedElement getAnnotated()
    {
        return getAnnotated();
    }

    public Constructor getAnnotated()
    {
        return _constructor;
    }

    public Class getDeclaringClass()
    {
        return _constructor.getDeclaringClass();
    }

    public Type getGenericParameterType(int i)
    {
        Type atype[] = _constructor.getGenericParameterTypes();
        if(i >= atype.length)
            return null;
        else
            return atype[i];
    }

    public Type getGenericType()
    {
        return getRawType();
    }

    public Member getMember()
    {
        return _constructor;
    }

    public int getModifiers()
    {
        return _constructor.getModifiers();
    }

    public String getName()
    {
        return _constructor.getName();
    }

    public int getParameterCount()
    {
        return _constructor.getParameterTypes().length;
    }

    public Class getRawParameterType(int i)
    {
        Class aclass[] = _constructor.getParameterTypes();
        if(i >= aclass.length)
            return null;
        else
            return aclass[i];
    }

    public Class getRawType()
    {
        return _constructor.getDeclaringClass();
    }

    public JavaType getType(TypeBindings typebindings)
    {
        return getType(typebindings, _constructor.getTypeParameters());
    }

    public Object getValue(Object obj)
        throws UnsupportedOperationException
    {
        throw new UnsupportedOperationException((new StringBuilder()).append("Cannot call getValue() on constructor of ").append(getDeclaringClass().getName()).toString());
    }

    Object readResolve()
    {
        Class class1 = _serialization.clazz;
        AnnotatedConstructor annotatedconstructor;
        try
        {
            Constructor constructor = class1.getDeclaredConstructor(_serialization.args);
            if(!constructor.isAccessible())
                ClassUtil.checkAndFixAccess(constructor);
            annotatedconstructor = new AnnotatedConstructor(constructor, null, null);
        }
        catch(Exception exception)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Could not find constructor with ").append(_serialization.args.length).append(" args from Class '").append(class1.getName()).toString());
        }
        return annotatedconstructor;
    }

    public void setValue(Object obj, Object obj1)
        throws UnsupportedOperationException
    {
        throw new UnsupportedOperationException((new StringBuilder()).append("Cannot call setValue() on constructor of ").append(getDeclaringClass().getName()).toString());
    }

    public String toString()
    {
        return (new StringBuilder()).append("[constructor for ").append(getName()).append(", annotations: ").append(_annotations).append("]").toString();
    }

    public volatile Annotated withAnnotations(AnnotationMap annotationmap)
    {
        return withAnnotations(annotationmap);
    }

    public AnnotatedConstructor withAnnotations(AnnotationMap annotationmap)
    {
        return new AnnotatedConstructor(_constructor, annotationmap, _paramAnnotations);
    }

    Object writeReplace()
    {
        return new AnnotatedConstructor(new Serialization(_constructor));
    }

    private static final long serialVersionUID = 1L;
    protected final Constructor _constructor;
    protected Serialization _serialization;
}
