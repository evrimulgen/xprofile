// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package dagger.internal;

import java.util.*;

// Referenced classes of package dagger.internal:
//            ArrayQueue, Keys, BuiltInBinding, LazyBinding, 
//            Loader, Binding, BindingsGroup

public final class Linker
{
    private static class DeferredBinding extends Binding
    {

        public void getDependencies(Set set, Set set1)
        {
            throw new UnsupportedOperationException("Deferred bindings must resolve first.");
        }

        public void injectMembers(Object obj)
        {
            throw new UnsupportedOperationException("Deferred bindings must resolve first.");
        }

        public String toString()
        {
            return (new StringBuilder()).append("DeferredBinding[deferredKey=").append(deferredKey).append("]").toString();
        }

        final ClassLoader classLoader;
        final String deferredKey;
        final boolean mustHaveInjections;

        private DeferredBinding(String s, ClassLoader classloader, Object obj, boolean flag)
        {
            super(null, null, false, obj);
            deferredKey = s;
            classLoader = classloader;
            mustHaveInjections = flag;
        }

    }

    public static interface ErrorHandler
    {

        public abstract void handleErrors(List list);

        public static final ErrorHandler NULL = new ErrorHandler() {

            public void handleErrors(List list)
            {
            }

        }
;

    }

    private static class SingletonBinding extends Binding
    {

        public void attach(Linker linker)
        {
            binding.attach(linker);
        }

        public boolean dependedOn()
        {
            return binding.dependedOn();
        }

        public Object get()
        {
            if(onlyInstance != Linker.UNINITIALIZED) goto _L2; else goto _L1
_L1:
            this;
            JVM INSTR monitorenter ;
            if(onlyInstance == Linker.UNINITIALIZED)
                onlyInstance = binding.get();
            this;
            JVM INSTR monitorexit ;
_L2:
            return onlyInstance;
            Exception exception;
            exception;
            this;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public void getDependencies(Set set, Set set1)
        {
            binding.getDependencies(set, set1);
        }

        public void injectMembers(Object obj)
        {
            binding.injectMembers(obj);
        }

        public boolean isCycleFree()
        {
            return binding.isCycleFree();
        }

        public boolean isLinked()
        {
            return binding.isLinked();
        }

        protected boolean isSingleton()
        {
            return true;
        }

        public boolean isVisiting()
        {
            return binding.isVisiting();
        }

        public boolean library()
        {
            return binding.library();
        }

        public void setCycleFree(boolean flag)
        {
            binding.setCycleFree(flag);
        }

        public void setDependedOn(boolean flag)
        {
            binding.setDependedOn(flag);
        }

        public void setLibrary(boolean flag)
        {
            binding.setLibrary(true);
        }

        protected void setLinked()
        {
            binding.setLinked();
        }

        public void setVisiting(boolean flag)
        {
            binding.setVisiting(flag);
        }

        public String toString()
        {
            return (new StringBuilder()).append("@Singleton/").append(binding.toString()).toString();
        }

        private final Binding binding;
        private volatile Object onlyInstance;

        private SingletonBinding(Binding binding1)
        {
            super(binding1.provideKey, binding1.membersKey, true, binding1.requiredBy);
            onlyInstance = Linker.UNINITIALIZED;
            binding = binding1;
        }

    }


    public Linker(Linker linker, Loader loader, ErrorHandler errorhandler)
    {
        attachSuccess = true;
        linkedBindings = null;
        if(loader == null)
            throw new NullPointerException("plugin");
        if(errorhandler == null)
        {
            throw new NullPointerException("errorHandler");
        } else
        {
            base = linker;
            plugin = loader;
            errorHandler = errorhandler;
            return;
        }
    }

    private void addError(String s)
    {
        errors.add(s);
    }

    private void assertLockHeld()
    {
        if(!Thread.holdsLock(this))
            throw new AssertionError();
        else
            return;
    }

    private Binding createBinding(String s, Object obj, ClassLoader classloader, boolean flag)
    {
        String s1 = Keys.getBuiltInBindingsKey(s);
        Object obj1;
        if(s1 != null)
        {
            obj1 = new BuiltInBinding(s, obj, classloader, s1);
        } else
        {
            String s2 = Keys.getLazyKey(s);
            if(s2 != null)
                return new LazyBinding(s, obj, classloader, s2);
            String s3 = Keys.getClassName(s);
            if(s3 == null || Keys.isAnnotated(s))
                throw new IllegalArgumentException(s);
            obj1 = plugin.getAtInjectBinding(s, s3, classloader, flag);
            if(obj1 == null)
                throw new Binding.InvalidBindingException(s3, (new StringBuilder()).append("could not be bound with key ").append(s).toString());
        }
        return ((Binding) (obj1));
    }

    private void putBinding(Binding binding)
    {
        if(binding.provideKey != null)
            putIfAbsent(bindings, binding.provideKey, binding);
        if(binding.membersKey != null)
            putIfAbsent(bindings, binding.membersKey, binding);
    }

    private void putIfAbsent(Map map, Object obj, Object obj1)
    {
        Object obj2 = map.put(obj, obj1);
        if(obj2 != null)
            map.put(obj, obj2);
    }

    static Binding scope(Binding binding)
    {
        if(!binding.isSingleton() || (binding instanceof SingletonBinding))
            return binding;
        else
            return new SingletonBinding(binding);
    }

    public Map fullyLinkedBindings()
    {
        return linkedBindings;
    }

    public void installBindings(BindingsGroup bindingsgroup)
    {
        if(linkedBindings != null)
            throw new IllegalStateException("Cannot install further bindings after calling linkAll().");
        java.util.Map.Entry entry;
        for(Iterator iterator = bindingsgroup.entrySet().iterator(); iterator.hasNext(); bindings.put(entry.getKey(), scope((Binding)entry.getValue())))
            entry = (java.util.Map.Entry)iterator.next();

    }

    public Map linkAll()
    {
        assertLockHeld();
        if(linkedBindings != null)
            return linkedBindings;
        Iterator iterator = bindings.values().iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            Binding binding = (Binding)iterator.next();
            if(!binding.isLinked())
                toLink.add(binding);
        } while(true);
        linkRequested();
        linkedBindings = Collections.unmodifiableMap(bindings);
        return linkedBindings;
    }

    public void linkRequested()
    {
        assertLockHeld();
_L2:
        Binding binding;
        DeferredBinding deferredbinding;
        String s;
        boolean flag;
        binding = (Binding)toLink.poll();
        if(binding == null)
            break; /* Loop/switch isn't completed */
        if(!(binding instanceof DeferredBinding))
            break MISSING_BLOCK_LABEL_384;
        deferredbinding = (DeferredBinding)binding;
        s = deferredbinding.deferredKey;
        flag = deferredbinding.mustHaveInjections;
        if(bindings.containsKey(s))
            continue; /* Loop/switch isn't completed */
        Binding.InvalidBindingException invalidbindingexception;
        Binding binding1;
        binding1 = createBinding(s, binding.requiredBy, deferredbinding.classLoader, flag);
        binding1.setLibrary(binding.library());
        binding1.setDependedOn(binding.dependedOn());
        if(!s.equals(binding1.provideKey) && !s.equals(binding1.membersKey))
            throw new IllegalStateException((new StringBuilder()).append("Unable to create binding for ").append(s).toString());
        try
        {
            Binding binding2 = scope(binding1);
            toLink.add(binding2);
            putBinding(binding2);
        }
        // Misplaced declaration of an exception variable
        catch(Binding.InvalidBindingException invalidbindingexception)
        {
            addError((new StringBuilder()).append(invalidbindingexception.type).append(" ").append(invalidbindingexception.getMessage()).append(" required by ").append(binding.requiredBy).toString());
            bindings.put(s, Binding.UNRESOLVED);
        }
        catch(UnsupportedOperationException unsupportedoperationexception)
        {
            addError((new StringBuilder()).append("Unsupported: ").append(unsupportedoperationexception.getMessage()).append(" required by ").append(binding.requiredBy).toString());
            bindings.put(s, Binding.UNRESOLVED);
        }
        catch(IllegalArgumentException illegalargumentexception)
        {
            addError((new StringBuilder()).append(illegalargumentexception.getMessage()).append(" required by ").append(binding.requiredBy).toString());
            bindings.put(s, Binding.UNRESOLVED);
        }
        catch(RuntimeException runtimeexception)
        {
            throw runtimeexception;
        }
        catch(Exception exception1)
        {
            throw new RuntimeException(exception1);
        }
        continue; /* Loop/switch isn't completed */
        attachSuccess = true;
        binding.attach(this);
        if(attachSuccess)
            binding.setLinked();
        else
            toLink.add(binding);
        if(true) goto _L2; else goto _L1
_L1:
        errorHandler.handleErrors(errors);
        errors.clear();
        return;
        Exception exception;
        exception;
        errors.clear();
        throw exception;
    }

    public Binding requestBinding(String s, Object obj)
    {
        return requestBinding(s, obj, getClass().getClassLoader(), true, true);
    }

    public Binding requestBinding(String s, Object obj, ClassLoader classloader)
    {
        return requestBinding(s, obj, classloader, true, true);
    }

    public Binding requestBinding(String s, Object obj, ClassLoader classloader, boolean flag, boolean flag1)
    {
        assertLockHeld();
        Binding binding = null;
        Linker linker = this;
        do
        {
            if(linker == null)
                break;
            binding = (Binding)linker.bindings.get(s);
            if(binding != null)
            {
                if(linker != this && !binding.isLinked())
                    throw new AssertionError();
                break;
            }
            linker = linker.base;
        } while(true);
        if(binding == null)
        {
            DeferredBinding deferredbinding = new DeferredBinding(s, classloader, obj, flag);
            deferredbinding.setLibrary(flag1);
            deferredbinding.setDependedOn(true);
            toLink.add(deferredbinding);
            attachSuccess = false;
            return null;
        }
        if(!binding.isLinked())
            toLink.add(binding);
        binding.setLibrary(flag1);
        binding.setDependedOn(true);
        return binding;
    }

    public Binding requestBinding(String s, Object obj, boolean flag, boolean flag1)
    {
        return requestBinding(s, obj, getClass().getClassLoader(), flag, flag1);
    }

    private static final Object UNINITIALIZED = new Object();
    private boolean attachSuccess;
    private final Linker base;
    private final Map bindings = new HashMap();
    private final ErrorHandler errorHandler;
    private final List errors = new ArrayList();
    private volatile Map linkedBindings;
    private final Loader plugin;
    private final Queue toLink = new ArrayQueue();


}
