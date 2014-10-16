// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;

// Referenced classes of package com.fasterxml.jackson.databind.deser:
//            ValueInstantiator

public interface ValueInstantiators
{
    public static class Base
        implements ValueInstantiators
    {

        public ValueInstantiator findValueInstantiator(DeserializationConfig deserializationconfig, BeanDescription beandescription, ValueInstantiator valueinstantiator)
        {
            return valueinstantiator;
        }

        public Base()
        {
        }
    }


    public abstract ValueInstantiator findValueInstantiator(DeserializationConfig deserializationconfig, BeanDescription beandescription, ValueInstantiator valueinstantiator);
}
