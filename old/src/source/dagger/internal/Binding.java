// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package dagger.internal;

import dagger.MembersInjector;
import java.util.Set;
import javax.inject.Provider;

// Referenced classes of package dagger.internal:
//            Keys, Linker

public abstract class Binding
    implements Provider, MembersInjector
{
    public static class InvalidBindingException extends RuntimeException
    {

        public final String type;

        public InvalidBindingException(String s, String s1)
        {
            super(s1);
            type = s;
        }

        public InvalidBindingException(String s, String s1, Throwable throwable)
        {
            super((new StringBuilder()).append("Binding for ").append(s).append(" was invalid: ").append(s1).toString(), throwable);
            type = s;
        }
    }


    protected Binding(String s, String s1, boolean flag, Object obj)
    {
        if(flag && s == null)
            throw new InvalidBindingException(Keys.getClassName(s1), "is exclusively members injected and therefore cannot be scoped");
        provideKey = s;
        membersKey = s1;
        requiredBy = obj;
        int i;
        if(flag)
            i = 1;
        else
            i = 0;
        bits = i;
    }

    public void attach(Linker linker)
    {
    }

    public boolean dependedOn()
    {
        return (0x10 & bits) != 0;
    }

    public Object get()
    {
        throw new UnsupportedOperationException((new StringBuilder()).append("No injectable constructor on ").append(getClass().getName()).toString());
    }

    public void getDependencies(Set set, Set set1)
    {
    }

    public void injectMembers(Object obj)
    {
    }

    public boolean isCycleFree()
    {
        return (8 & bits) != 0;
    }

    public boolean isLinked()
    {
        return (2 & bits) != 0;
    }

    boolean isSingleton()
    {
        return (1 & bits) != 0;
    }

    public boolean isVisiting()
    {
        return (4 & bits) != 0;
    }

    public boolean library()
    {
        return (0x20 & bits) != 0;
    }

    public void setCycleFree(boolean flag)
    {
        int i;
        if(flag)
            i = 8 | bits;
        else
            i = -9 & bits;
        bits = i;
    }

    public void setDependedOn(boolean flag)
    {
        int i;
        if(flag)
            i = 0x10 | bits;
        else
            i = 0xffffffef & bits;
        bits = i;
    }

    public void setLibrary(boolean flag)
    {
        int i;
        if(flag)
            i = 0x20 | bits;
        else
            i = 0xffffffdf & bits;
        bits = i;
    }

    void setLinked()
    {
        bits = 2 | bits;
    }

    public void setVisiting(boolean flag)
    {
        int i;
        if(flag)
            i = 4 | bits;
        else
            i = -5 & bits;
        bits = i;
    }

    public String toString()
    {
        return (new StringBuilder()).append(getClass().getSimpleName()).append("[provideKey=\"").append(provideKey).append("\", memberskey=\"").append(membersKey).append("\"]").toString();
    }

    private static final int CYCLE_FREE = 8;
    private static final int DEPENDED_ON = 16;
    protected static final boolean IS_SINGLETON = true;
    private static final int LIBRARY = 32;
    private static final int LINKED = 2;
    protected static final boolean NOT_SINGLETON = false;
    private static final int SINGLETON = 1;
    public static final Binding UNRESOLVED = new Binding(null, null, false, null) {

        public Object get()
        {
            throw new AssertionError("Unresolved binding should never be called to inject.");
        }

        public void injectMembers(Object obj)
        {
            throw new AssertionError("Unresolved binding should never be called to inject.");
        }

    }
;
    private static final int VISITING = 4;
    private int bits;
    public final String membersKey;
    public final String provideKey;
    public final Object requiredBy;

}
