// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.annotation;

import java.util.UUID;

// Referenced classes of package com.fasterxml.jackson.annotation:
//            ObjectIdGenerator

public class ObjectIdGenerators
{
    private static abstract class Base extends ObjectIdGenerator
    {

        public boolean canUseFor(ObjectIdGenerator objectidgenerator)
        {
            return objectidgenerator.getClass() == getClass() && objectidgenerator.getScope() == _scope;
        }

        public abstract Object generateId(Object obj);

        public final Class getScope()
        {
            return _scope;
        }

        protected final Class _scope;

        protected Base(Class class1)
        {
            _scope = class1;
        }
    }

    public static final class IntSequenceGenerator extends Base
    {

        public volatile boolean canUseFor(ObjectIdGenerator objectidgenerator)
        {
            return super.canUseFor(objectidgenerator);
        }

        public ObjectIdGenerator forScope(Class class1)
        {
            if(_scope == class1)
                return this;
            else
                return new IntSequenceGenerator(class1, _nextValue);
        }

        public Integer generateId(Object obj)
        {
            int i = _nextValue;
            _nextValue = 1 + _nextValue;
            return Integer.valueOf(i);
        }

        public volatile Object generateId(Object obj)
        {
            return generateId(obj);
        }

        protected int initialValue()
        {
            return 1;
        }

        public ObjectIdGenerator.IdKey key(Object obj)
        {
            return new ObjectIdGenerator.IdKey(getClass(), _scope, obj);
        }

        public ObjectIdGenerator newForSerialization(Object obj)
        {
            return new IntSequenceGenerator(_scope, initialValue());
        }

        private static final long serialVersionUID = 1L;
        protected transient int _nextValue;

        public IntSequenceGenerator()
        {
            this(java/lang/Object, -1);
        }

        public IntSequenceGenerator(Class class1, int i)
        {
            super(class1);
            _nextValue = i;
        }
    }

    public static abstract class None extends ObjectIdGenerator
    {

        public None()
        {
        }
    }

    public static abstract class PropertyGenerator extends Base
    {

        public volatile boolean canUseFor(ObjectIdGenerator objectidgenerator)
        {
            return super.canUseFor(objectidgenerator);
        }

        private static final long serialVersionUID = 1L;

        protected PropertyGenerator(Class class1)
        {
            super(class1);
        }
    }

    public static final class UUIDGenerator extends Base
    {

        public boolean canUseFor(ObjectIdGenerator objectidgenerator)
        {
            return objectidgenerator.getClass() == getClass();
        }

        public ObjectIdGenerator forScope(Class class1)
        {
            return this;
        }

        public volatile Object generateId(Object obj)
        {
            return generateId(obj);
        }

        public UUID generateId(Object obj)
        {
            return UUID.randomUUID();
        }

        public ObjectIdGenerator.IdKey key(Object obj)
        {
            return new ObjectIdGenerator.IdKey(getClass(), null, obj);
        }

        public ObjectIdGenerator newForSerialization(Object obj)
        {
            return this;
        }

        private static final long serialVersionUID = 1L;

        public UUIDGenerator()
        {
            this(java/lang/Object);
        }

        private UUIDGenerator(Class class1)
        {
            super(java/lang/Object);
        }
    }


    public ObjectIdGenerators()
    {
    }
}
