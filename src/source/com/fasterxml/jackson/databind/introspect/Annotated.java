// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeBindings;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;

// Referenced classes of package com.fasterxml.jackson.databind.introspect:
//            AnnotationMap

public abstract class Annotated
{

    protected Annotated()
    {
    }

    public abstract Iterable annotations();

    protected abstract AnnotationMap getAllAnnotations();

    public abstract AnnotatedElement getAnnotated();

    public abstract Annotation getAnnotation(Class class1);

    public abstract Type getGenericType();

    protected abstract int getModifiers();

    public abstract String getName();

    public abstract Class getRawType();

    public JavaType getType(TypeBindings typebindings)
    {
        return typebindings.resolveType(getGenericType());
    }

    public final boolean hasAnnotation(Class class1)
    {
        return getAnnotation(class1) != null;
    }

    public final boolean isPublic()
    {
        return Modifier.isPublic(getModifiers());
    }

    public abstract Annotated withAnnotations(AnnotationMap annotationmap);

    public final Annotated withFallBackAnnotationsFrom(Annotated annotated)
    {
        return withAnnotations(AnnotationMap.merge(getAllAnnotations(), annotated.getAllAnnotations()));
    }
}
