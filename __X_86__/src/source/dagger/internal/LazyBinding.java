// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package dagger.internal;

import dagger.Lazy;

// Referenced classes of package dagger.internal:
//            Binding, Linker

final class LazyBinding extends Binding
{

    public LazyBinding(String s, Object obj, ClassLoader classloader, String s1)
    {
        super(s, null, false, obj);
        loader = classloader;
        lazyKey = s1;
    }

    public void attach(Linker linker)
    {
        _flddelegate = linker.requestBinding(lazyKey, requiredBy, loader);
    }

    public Lazy get()
    {
        return new Lazy() {

            public Object get()
            {
                if(cacheValue != LazyBinding.NOT_PRESENT) goto _L2; else goto _L1
_L1:
                this;
                JVM INSTR monitorenter ;
                if(cacheValue == LazyBinding.NOT_PRESENT)
                    cacheValue = _flddelegate.get();
                this;
                JVM INSTR monitorexit ;
_L2:
                return cacheValue;
                Exception exception;
                exception;
                this;
                JVM INSTR monitorexit ;
                throw exception;
            }

            private volatile Object cacheValue;
            final LazyBinding this$0;

            
            {
                this$0 = LazyBinding.this;
                super();
                cacheValue = LazyBinding.NOT_PRESENT;
            }
        }
;
    }

    public volatile Object get()
    {
        return get();
    }

    public void injectMembers(Lazy lazy)
    {
        throw new UnsupportedOperationException();
    }

    public volatile void injectMembers(Object obj)
    {
        injectMembers((Lazy)obj);
    }

    private static final Object NOT_PRESENT = new Object();
    private Binding _flddelegate;
    private final String lazyKey;
    private final ClassLoader loader;



}
