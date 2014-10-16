// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.cfg;

import java.io.Serializable;
import java.util.*;

public abstract class ContextAttributes
{
    public static class Impl extends ContextAttributes
        implements Serializable
    {

        private Map _copy(Map map)
        {
            return new HashMap(map);
        }

        public static ContextAttributes getEmpty()
        {
            return EMPTY;
        }

        public Object getAttribute(Object obj)
        {
            if(_nonShared != null)
            {
                Object obj1 = _nonShared.get(obj);
                if(obj1 != null)
                {
                    if(obj1 == NULL_SURROGATE)
                        obj1 = null;
                    return obj1;
                }
            }
            return _shared.get(obj);
        }

        protected ContextAttributes nonSharedInstance(Object obj, Object obj1)
        {
            HashMap hashmap = new HashMap();
            if(obj1 == null)
                obj1 = NULL_SURROGATE;
            hashmap.put(obj, obj1);
            return new Impl(_shared, hashmap);
        }

        public ContextAttributes withPerCallAttribute(Object obj, Object obj1)
        {
label0:
            {
label1:
                {
                    if(obj1 == null)
                    {
                        if(!_shared.containsKey(obj))
                            break label1;
                        obj1 = NULL_SURROGATE;
                    }
                    if(_nonShared != null)
                        break label0;
                    this = nonSharedInstance(obj, obj1);
                }
                return this;
            }
            _nonShared.put(obj, obj1);
            return this;
        }

        public ContextAttributes withSharedAttribute(Object obj, Object obj1)
        {
            Object obj2;
            if(this == EMPTY)
                obj2 = new HashMap(8);
            else
                obj2 = _copy(_shared);
            ((Map) (obj2)).put(obj, obj1);
            return new Impl(((Map) (obj2)));
        }

        public ContextAttributes withSharedAttributes(Map map)
        {
            return new Impl(map);
        }

        public ContextAttributes withoutSharedAttribute(Object obj)
        {
            while(_shared.isEmpty() || !_shared.containsKey(obj)) 
                return this;
            if(_shared.size() == 1)
            {
                return EMPTY;
            } else
            {
                Map map = _copy(_shared);
                map.remove(obj);
                return new Impl(map);
            }
        }

        protected static final Impl EMPTY = new Impl(Collections.emptyMap());
        protected static final Object NULL_SURROGATE = new Object();
        private static final long serialVersionUID = 1L;
        protected transient Map _nonShared;
        protected final Map _shared;


        protected Impl(Map map)
        {
            _shared = map;
            _nonShared = null;
        }

        protected Impl(Map map, Map map1)
        {
            _shared = map;
            _nonShared = map1;
        }
    }


    public ContextAttributes()
    {
    }

    public static ContextAttributes getEmpty()
    {
        return Impl.getEmpty();
    }

    public abstract Object getAttribute(Object obj);

    public abstract ContextAttributes withPerCallAttribute(Object obj, Object obj1);

    public abstract ContextAttributes withSharedAttribute(Object obj, Object obj1);

    public abstract ContextAttributes withSharedAttributes(Map map);

    public abstract ContextAttributes withoutSharedAttribute(Object obj);
}
