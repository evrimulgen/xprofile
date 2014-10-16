// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.plugins;

import java.util.concurrent.atomic.AtomicReference;

// Referenced classes of package rx.plugins:
//            RxJavaDefaultSchedulers, RxJavaDefaultSchedulersDefault, RxJavaErrorHandler, RxJavaErrorHandlerDefault, 
//            RxJavaObservableExecutionHook, RxJavaObservableExecutionHookDefault

public class RxJavaPlugins
{

    RxJavaPlugins()
    {
    }

    public static RxJavaPlugins getInstance()
    {
        return INSTANCE;
    }

    private static Object getPluginImplementationViaProperty(Class class1)
    {
        String s = class1.getSimpleName();
        String s1 = System.getProperty((new StringBuilder()).append("rxjava.plugin.").append(s).append(".implementation").toString());
        if(s1 != null)
        {
            Object obj;
            try
            {
                obj = Class.forName(s1).asSubclass(class1).newInstance();
            }
            catch(ClassCastException classcastexception)
            {
                throw new RuntimeException((new StringBuilder()).append(s).append(" implementation is not an instance of ").append(s).append(": ").append(s1).toString());
            }
            catch(ClassNotFoundException classnotfoundexception)
            {
                throw new RuntimeException((new StringBuilder()).append(s).append(" implementation class not found: ").append(s1).toString(), classnotfoundexception);
            }
            catch(InstantiationException instantiationexception)
            {
                throw new RuntimeException((new StringBuilder()).append(s).append(" implementation not able to be instantiated: ").append(s1).toString(), instantiationexception);
            }
            catch(IllegalAccessException illegalaccessexception)
            {
                throw new RuntimeException((new StringBuilder()).append(s).append(" implementation not able to be accessed: ").append(s1).toString(), illegalaccessexception);
            }
            return obj;
        } else
        {
            return null;
        }
    }

    public RxJavaDefaultSchedulers getDefaultSchedulers()
    {
        if(schedulerOverrides.get() == null)
        {
            Object obj = getPluginImplementationViaProperty(rx/plugins/RxJavaDefaultSchedulers);
            if(obj == null)
                schedulerOverrides.compareAndSet(null, RxJavaDefaultSchedulersDefault.getInstance());
            else
                schedulerOverrides.compareAndSet(null, (RxJavaDefaultSchedulers)obj);
        }
        return (RxJavaDefaultSchedulers)schedulerOverrides.get();
    }

    public RxJavaErrorHandler getErrorHandler()
    {
        if(errorHandler.get() == null)
        {
            Object obj = getPluginImplementationViaProperty(rx/plugins/RxJavaErrorHandler);
            if(obj == null)
                errorHandler.compareAndSet(null, RxJavaErrorHandlerDefault.getInstance());
            else
                errorHandler.compareAndSet(null, (RxJavaErrorHandler)obj);
        }
        return (RxJavaErrorHandler)errorHandler.get();
    }

    public RxJavaObservableExecutionHook getObservableExecutionHook()
    {
        if(observableExecutionHook.get() == null)
        {
            Object obj = getPluginImplementationViaProperty(rx/plugins/RxJavaObservableExecutionHook);
            if(obj == null)
                observableExecutionHook.compareAndSet(null, RxJavaObservableExecutionHookDefault.getInstance());
            else
                observableExecutionHook.compareAndSet(null, (RxJavaObservableExecutionHook)obj);
        }
        return (RxJavaObservableExecutionHook)observableExecutionHook.get();
    }

    public void registerDefaultSchedulers(RxJavaDefaultSchedulers rxjavadefaultschedulers)
    {
        if(!schedulerOverrides.compareAndSet(null, rxjavadefaultschedulers))
            throw new IllegalStateException((new StringBuilder()).append("Another strategy was already registered: ").append(schedulerOverrides.get()).toString());
        else
            return;
    }

    public void registerErrorHandler(RxJavaErrorHandler rxjavaerrorhandler)
    {
        if(!errorHandler.compareAndSet(null, rxjavaerrorhandler))
            throw new IllegalStateException((new StringBuilder()).append("Another strategy was already registered: ").append(errorHandler.get()).toString());
        else
            return;
    }

    public void registerObservableExecutionHook(RxJavaObservableExecutionHook rxjavaobservableexecutionhook)
    {
        if(!observableExecutionHook.compareAndSet(null, rxjavaobservableexecutionhook))
            throw new IllegalStateException((new StringBuilder()).append("Another strategy was already registered: ").append(observableExecutionHook.get()).toString());
        else
            return;
    }

    void reset()
    {
        INSTANCE.errorHandler.set(null);
        INSTANCE.observableExecutionHook.set(null);
    }

    private static final RxJavaPlugins INSTANCE = new RxJavaPlugins();
    private final AtomicReference errorHandler = new AtomicReference();
    private final AtomicReference observableExecutionHook = new AtomicReference();
    private final AtomicReference schedulerOverrides = new AtomicReference();

}
