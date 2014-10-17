// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

// Referenced classes of package com.fasterxml.jackson.databind:
//            DeserializationContext, BeanProperty

public abstract class InjectableValues
{
    public static class Std extends InjectableValues
        implements Serializable
    {

        public Std addValue(Class class1, Object obj)
        {
            _values.put(class1.getName(), obj);
            return this;
        }

        public Std addValue(String s, Object obj)
        {
            _values.put(s, obj);
            return this;
        }

        public Object findInjectableValue(Object obj, DeserializationContext deserializationcontext, BeanProperty beanproperty, Object obj1)
        {
            if(!(obj instanceof String))
            {
                String s1;
                if(obj == null)
                    s1 = "[null]";
                else
                    s1 = obj.getClass().getName();
                throw new IllegalArgumentException((new StringBuilder()).append("Unrecognized inject value id type (").append(s1).append("), expecting String").toString());
            }
            String s = (String)obj;
            Object obj2 = _values.get(s);
            if(obj2 == null && !_values.containsKey(s))
                throw new IllegalArgumentException((new StringBuilder()).append("No injectable id with value '").append(s).append("' found (for property '").append(beanproperty.getName()).append("')").toString());
            else
                return obj2;
        }

        private static final long serialVersionUID = 1L;
        protected final Map _values;

        public Std()
        {
            this(((Map) (new HashMap())));
        }

        public Std(Map map)
        {
            _values = map;
        }
    }


    public InjectableValues()
    {
    }

    public abstract Object findInjectableValue(Object obj, DeserializationContext deserializationcontext, BeanProperty beanproperty, Object obj1);
}
