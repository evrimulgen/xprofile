// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;

// Referenced classes of package com.fasterxml.jackson.databind.introspect:
//            AnnotatedMember, AnnotationMap, Annotated

public final class AnnotatedField extends AnnotatedMember
    implements Serializable
{
    private static final class Serialization
        implements Serializable
    {

        private static final long serialVersionUID = 1L;
        protected Class clazz;
        protected String name;

        public Serialization(Field field)
        {
            clazz = field.getDeclaringClass();
            name = field.getName();
        }
    }


    protected AnnotatedField(Serialization serialization)
    {
        super(null);
        _field = null;
        _serialization = serialization;
    }

    public AnnotatedField(Field field, AnnotationMap annotationmap)
    {
        super(annotationmap);
        _field = field;
    }

    public volatile AnnotatedElement getAnnotated()
    {
        return getAnnotated();
    }

    public Field getAnnotated()
    {
        return _field;
    }

    public Annotation getAnnotation(Class class1)
    {
        if(_annotations == null)
            return null;
        else
            return _annotations.get(class1);
    }

    public int getAnnotationCount()
    {
        return _annotations.size();
    }

    public Class getDeclaringClass()
    {
        return _field.getDeclaringClass();
    }

    public String getFullName()
    {
        return (new StringBuilder()).append(getDeclaringClass().getName()).append("#").append(getName()).toString();
    }

    public Type getGenericType()
    {
        return _field.getGenericType();
    }

    public Member getMember()
    {
        return _field;
    }

    public int getModifiers()
    {
        return _field.getModifiers();
    }

    public String getName()
    {
        return _field.getName();
    }

    public Class getRawType()
    {
        return _field.getType();
    }

    public Object getValue(Object obj)
        throws IllegalArgumentException
    {
        Object obj1;
        try
        {
            obj1 = _field.get(obj);
        }
        catch(IllegalAccessException illegalaccessexception)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Failed to getValue() for field ").append(getFullName()).append(": ").append(illegalaccessexception.getMessage()).toString(), illegalaccessexception);
        }
        return obj1;
    }

    Object readResolve()
    {
        Class class1 = _serialization.clazz;
        AnnotatedField annotatedfield;
        try
        {
            Field field = class1.getDeclaredField(_serialization.name);
            if(!field.isAccessible())
                ClassUtil.checkAndFixAccess(field);
            annotatedfield = new AnnotatedField(field, null);
        }
        catch(Exception exception)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Could not find method '").append(_serialization.name).append("' from Class '").append(class1.getName()).toString());
        }
        return annotatedfield;
    }

    public void setValue(Object obj, Object obj1)
        throws IllegalArgumentException
    {
        try
        {
            _field.set(obj, obj1);
            return;
        }
        catch(IllegalAccessException illegalaccessexception)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Failed to setValue() for field ").append(getFullName()).append(": ").append(illegalaccessexception.getMessage()).toString(), illegalaccessexception);
        }
    }

    public String toString()
    {
        return (new StringBuilder()).append("[field ").append(getFullName()).append("]").toString();
    }

    public volatile Annotated withAnnotations(AnnotationMap annotationmap)
    {
        return withAnnotations(annotationmap);
    }

    public AnnotatedField withAnnotations(AnnotationMap annotationmap)
    {
        return new AnnotatedField(_field, annotationmap);
    }

    Object writeReplace()
    {
        return new AnnotatedField(new Serialization(_field));
    }

    private static final long serialVersionUID = 0x6633b4850c53b6dfL;
    protected final transient Field _field;
    protected Serialization _serialization;
}
