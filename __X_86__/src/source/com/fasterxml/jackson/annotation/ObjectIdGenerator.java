// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.annotation;

import java.io.Serializable;

public abstract class ObjectIdGenerator
    implements Serializable
{
    public static final class IdKey
        implements Serializable
    {

        public boolean equals(Object obj)
        {
            if(obj != this)
            {
                if(obj == null)
                    return false;
                if(obj.getClass() != getClass())
                    return false;
                IdKey idkey = (IdKey)obj;
                if(!idkey.key.equals(key) || idkey.type != type || idkey.scope != scope)
                    return false;
            }
            return true;
        }

        public int hashCode()
        {
            return hashCode;
        }

        private static final long serialVersionUID = 1L;
        private final int hashCode;
        private final Object key;
        private final Class scope;
        private final Class type;

        public IdKey(Class class1, Class class2, Object obj)
        {
            type = class1;
            scope = class2;
            key = obj;
            int i = obj.hashCode() + class1.getName().hashCode();
            if(class2 != null)
                i ^= class2.getName().hashCode();
            hashCode = i;
        }
    }


    public ObjectIdGenerator()
    {
    }

    public abstract boolean canUseFor(ObjectIdGenerator objectidgenerator);

    public abstract ObjectIdGenerator forScope(Class class1);

    public abstract Object generateId(Object obj);

    public abstract Class getScope();

    public abstract IdKey key(Object obj);

    public abstract ObjectIdGenerator newForSerialization(Object obj);
}
