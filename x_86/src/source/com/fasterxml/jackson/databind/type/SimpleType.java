// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.type;

import com.fasterxml.jackson.core.type.ResolvedType;
import com.fasterxml.jackson.databind.JavaType;
import java.util.Collection;
import java.util.Map;

// Referenced classes of package com.fasterxml.jackson.databind.type:
//            TypeBase

public final class SimpleType extends TypeBase
{

    protected SimpleType(Class class1)
    {
        this(class1, null, null, null, null, false);
    }

    protected SimpleType(Class class1, String as[], JavaType ajavatype[], Object obj, Object obj1, boolean flag)
    {
        super(class1, 0, obj, obj1, flag);
        if(as == null || as.length == 0)
        {
            _typeNames = null;
            _typeParameters = null;
            return;
        } else
        {
            _typeNames = as;
            _typeParameters = ajavatype;
            return;
        }
    }

    public static SimpleType construct(Class class1)
    {
        if(java/util/Map.isAssignableFrom(class1))
            throw new IllegalArgumentException((new StringBuilder()).append("Can not construct SimpleType for a Map (class: ").append(class1.getName()).append(")").toString());
        if(java/util/Collection.isAssignableFrom(class1))
            throw new IllegalArgumentException((new StringBuilder()).append("Can not construct SimpleType for a Collection (class: ").append(class1.getName()).append(")").toString());
        if(class1.isArray())
            throw new IllegalArgumentException((new StringBuilder()).append("Can not construct SimpleType for an array (class: ").append(class1.getName()).append(")").toString());
        else
            return new SimpleType(class1);
    }

    public static SimpleType constructUnsafe(Class class1)
    {
        return new SimpleType(class1, null, null, null, null, false);
    }

    protected JavaType _narrow(Class class1)
    {
        return new SimpleType(class1, _typeNames, _typeParameters, _valueHandler, _typeHandler, _asStatic);
    }

    protected String buildCanonicalName()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(_class.getName());
        if(_typeParameters != null && _typeParameters.length > 0)
        {
            stringbuilder.append('<');
            boolean flag = true;
            JavaType ajavatype[] = _typeParameters;
            int i = ajavatype.length;
            int j = 0;
            while(j < i) 
            {
                JavaType javatype = ajavatype[j];
                if(flag)
                    flag = false;
                else
                    stringbuilder.append(',');
                stringbuilder.append(javatype.toCanonical());
                j++;
            }
            stringbuilder.append('>');
        }
        return stringbuilder.toString();
    }

    public volatile ResolvedType containedType(int i)
    {
        return containedType(i);
    }

    public JavaType containedType(int i)
    {
        if(i < 0 || _typeParameters == null || i >= _typeParameters.length)
            return null;
        else
            return _typeParameters[i];
    }

    public int containedTypeCount()
    {
        if(_typeParameters == null)
            return 0;
        else
            return _typeParameters.length;
    }

    public String containedTypeName(int i)
    {
        if(i < 0 || _typeNames == null || i >= _typeNames.length)
            return null;
        else
            return _typeNames[i];
    }

    public boolean equals(Object obj)
    {
        if(obj != this) goto _L2; else goto _L1
_L1:
        boolean flag = true;
_L4:
        return flag;
_L2:
        JavaType ajavatype[];
        JavaType ajavatype1[];
        int i1;
        flag = false;
        if(obj == null)
            continue; /* Loop/switch isn't completed */
        Class class1 = obj.getClass();
        Class class2 = getClass();
        flag = false;
        if(class1 != class2)
            continue; /* Loop/switch isn't completed */
        SimpleType simpletype = (SimpleType)obj;
        Class class3 = simpletype._class;
        Class class4 = _class;
        flag = false;
        if(class3 != class4)
            continue; /* Loop/switch isn't completed */
        ajavatype = _typeParameters;
        ajavatype1 = simpletype._typeParameters;
        if(ajavatype != null)
            break MISSING_BLOCK_LABEL_99;
        if(ajavatype1 == null)
            break; /* Loop/switch isn't completed */
        i1 = ajavatype1.length;
        flag = false;
        if(i1 != 0) goto _L4; else goto _L3
_L3:
        return true;
        flag = false;
        if(ajavatype1 != null)
        {
            int i = ajavatype.length;
            int j = ajavatype1.length;
            flag = false;
            if(i == j)
            {
                int k = ajavatype.length;
                int l = 0;
label0:
                do
                {
label1:
                    {
                        if(l >= k)
                            break label1;
                        boolean flag1 = ajavatype[l].equals(ajavatype1[l]);
                        flag = false;
                        if(!flag1)
                            break label0;
                        l++;
                    }
                } while(true);
            }
        }
        if(true) goto _L4; else goto _L5
_L5:
        return true;
    }

    public StringBuilder getErasedSignature(StringBuilder stringbuilder)
    {
        return _classSignature(_class, stringbuilder, true);
    }

    public StringBuilder getGenericSignature(StringBuilder stringbuilder)
    {
        int i = 0;
        _classSignature(_class, stringbuilder, false);
        if(_typeParameters != null)
        {
            stringbuilder.append('<');
            JavaType ajavatype[] = _typeParameters;
            for(int j = ajavatype.length; i < j; i++)
                stringbuilder = ajavatype[i].getGenericSignature(stringbuilder);

            stringbuilder.append('>');
        }
        stringbuilder.append(';');
        return stringbuilder;
    }

    public boolean isContainerType()
    {
        return false;
    }

    public JavaType narrowContentsBy(Class class1)
    {
        throw new IllegalArgumentException("Internal error: SimpleType.narrowContentsBy() should never be called");
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder(40);
        stringbuilder.append("[simple type, class ").append(buildCanonicalName()).append(']');
        return stringbuilder.toString();
    }

    public JavaType widenContentsBy(Class class1)
    {
        throw new IllegalArgumentException("Internal error: SimpleType.widenContentsBy() should never be called");
    }

    public JavaType withContentTypeHandler(Object obj)
    {
        throw new IllegalArgumentException("Simple types have no content types; can not call withContenTypeHandler()");
    }

    public volatile JavaType withContentValueHandler(Object obj)
    {
        return withContentValueHandler(obj);
    }

    public SimpleType withContentValueHandler(Object obj)
    {
        throw new IllegalArgumentException("Simple types have no content types; can not call withContenValueHandler()");
    }

    public volatile JavaType withStaticTyping()
    {
        return withStaticTyping();
    }

    public SimpleType withStaticTyping()
    {
        if(_asStatic)
            return this;
        else
            return new SimpleType(_class, _typeNames, _typeParameters, _valueHandler, _typeHandler, _asStatic);
    }

    public volatile JavaType withTypeHandler(Object obj)
    {
        return withTypeHandler(obj);
    }

    public SimpleType withTypeHandler(Object obj)
    {
        return new SimpleType(_class, _typeNames, _typeParameters, _valueHandler, obj, _asStatic);
    }

    public volatile JavaType withValueHandler(Object obj)
    {
        return withValueHandler(obj);
    }

    public SimpleType withValueHandler(Object obj)
    {
        if(obj == _valueHandler)
            return this;
        else
            return new SimpleType(_class, _typeNames, _typeParameters, obj, _typeHandler, _asStatic);
    }

    private static final long serialVersionUID = 0xf4e47f55695bcb98L;
    protected final String _typeNames[];
    protected final JavaType _typeParameters[];
}
