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

public final class AnnotatedMethod extends AnnotatedWithParams
    implements Serializable
{
    private static final class Serialization
        implements Serializable
    {

        private static final long serialVersionUID = 1L;
        protected Class args[];
        protected Class clazz;
        protected String name;

        public Serialization(Method method)
        {
            clazz = method.getDeclaringClass();
            name = method.getName();
            args = method.getParameterTypes();
        }
    }


    protected AnnotatedMethod(Serialization serialization)
    {
        super(null, null);
        _method = null;
        _serialization = serialization;
    }

    public AnnotatedMethod(Method method, AnnotationMap annotationmap, AnnotationMap aannotationmap[])
    {
        super(annotationmap, aannotationmap);
        if(method == null)
        {
            throw new IllegalArgumentException("Can not construct AnnotatedMethod with null Method");
        } else
        {
            _method = method;
            return;
        }
    }

    public final Object call()
        throws Exception
    {
        return _method.invoke(null, new Object[0]);
    }

    public final Object call(Object aobj[])
        throws Exception
    {
        return _method.invoke(null, aobj);
    }

    public final Object call1(Object obj)
        throws Exception
    {
        return _method.invoke(null, new Object[] {
            obj
        });
    }

    public volatile AnnotatedElement getAnnotated()
    {
        return getAnnotated();
    }

    public Method getAnnotated()
    {
        return _method;
    }

    public Class getDeclaringClass()
    {
        return _method.getDeclaringClass();
    }

    public String getFullName()
    {
        return (new StringBuilder()).append(getDeclaringClass().getName()).append("#").append(getName()).append("(").append(getParameterCount()).append(" params)").toString();
    }

    public Type getGenericParameterType(int i)
    {
        Type atype[] = _method.getGenericParameterTypes();
        if(i >= atype.length)
            return null;
        else
            return atype[i];
    }

    public Type[] getGenericParameterTypes()
    {
        return _method.getGenericParameterTypes();
    }

    public Type getGenericReturnType()
    {
        return _method.getGenericReturnType();
    }

    public Type getGenericType()
    {
        return _method.getGenericReturnType();
    }

    public volatile Member getMember()
    {
        return getMember();
    }

    public Method getMember()
    {
        return _method;
    }

    public int getModifiers()
    {
        return _method.getModifiers();
    }

    public String getName()
    {
        return _method.getName();
    }

    public int getParameterCount()
    {
        return getRawParameterTypes().length;
    }

    public Class getRawParameterType(int i)
    {
        Class aclass[] = getRawParameterTypes();
        if(i >= aclass.length)
            return null;
        else
            return aclass[i];
    }

    public Class[] getRawParameterTypes()
    {
        if(_paramClasses == null)
            _paramClasses = _method.getParameterTypes();
        return _paramClasses;
    }

    public Class getRawReturnType()
    {
        return _method.getReturnType();
    }

    public Class getRawType()
    {
        return _method.getReturnType();
    }

    public JavaType getType(TypeBindings typebindings)
    {
        return getType(typebindings, _method.getTypeParameters());
    }

    public Object getValue(Object obj)
        throws IllegalArgumentException
    {
        Object obj1;
        try
        {
            obj1 = _method.invoke(obj, new Object[0]);
        }
        catch(IllegalAccessException illegalaccessexception)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Failed to getValue() with method ").append(getFullName()).append(": ").append(illegalaccessexception.getMessage()).toString(), illegalaccessexception);
        }
        catch(InvocationTargetException invocationtargetexception)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Failed to getValue() with method ").append(getFullName()).append(": ").append(invocationtargetexception.getMessage()).toString(), invocationtargetexception);
        }
        return obj1;
    }

    Object readResolve()
    {
        Class class1 = _serialization.clazz;
        AnnotatedMethod annotatedmethod;
        try
        {
            Method method = class1.getDeclaredMethod(_serialization.name, _serialization.args);
            if(!method.isAccessible())
                ClassUtil.checkAndFixAccess(method);
            annotatedmethod = new AnnotatedMethod(method, null, null);
        }
        catch(Exception exception)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Could not find method '").append(_serialization.name).append("' from Class '").append(class1.getName()).toString());
        }
        return annotatedmethod;
    }

    public void setValue(Object obj, Object obj1)
        throws IllegalArgumentException
    {
        try
        {
            _method.invoke(obj, new Object[] {
                obj1
            });
            return;
        }
        catch(IllegalAccessException illegalaccessexception)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Failed to setValue() with method ").append(getFullName()).append(": ").append(illegalaccessexception.getMessage()).toString(), illegalaccessexception);
        }
        catch(InvocationTargetException invocationtargetexception)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Failed to setValue() with method ").append(getFullName()).append(": ").append(invocationtargetexception.getMessage()).toString(), invocationtargetexception);
        }
    }

    public String toString()
    {
        return (new StringBuilder()).append("[method ").append(getFullName()).append("]").toString();
    }

    public volatile Annotated withAnnotations(AnnotationMap annotationmap)
    {
        return withAnnotations(annotationmap);
    }

    public AnnotatedMethod withAnnotations(AnnotationMap annotationmap)
    {
        return new AnnotatedMethod(_method, annotationmap, _paramAnnotations);
    }

    public AnnotatedMethod withMethod(Method method)
    {
        return new AnnotatedMethod(method, _annotations, _paramAnnotations);
    }

    Object writeReplace()
    {
        return new AnnotatedMethod(new Serialization(_method));
    }

    private static final long serialVersionUID = 1L;
    protected final transient Method _method;
    protected Class _paramClasses[];
    protected Serialization _serialization;
}
