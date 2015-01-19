// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package dagger.internal;


// Referenced classes of package dagger.internal:
//            Memoizer, Binding, ModuleAdapter, StaticInjection

public abstract class Loader
{

    public Loader()
    {
    }

    public abstract Binding getAtInjectBinding(String s, String s1, ClassLoader classloader, boolean flag);

    public abstract ModuleAdapter getModuleAdapter(Class class1);

    public abstract StaticInjection getStaticInjection(Class class1);

    protected Object instantiate(String s, ClassLoader classloader)
    {
        Class class1;
        Object obj;
        try
        {
            class1 = loadClass(classloader, s);
        }
        catch(InstantiationException instantiationexception)
        {
            throw new RuntimeException((new StringBuilder()).append("Failed to initialize ").append(s).toString(), instantiationexception);
        }
        catch(IllegalAccessException illegalaccessexception)
        {
            throw new RuntimeException((new StringBuilder()).append("Failed to initialize ").append(s).toString(), illegalaccessexception);
        }
        if(class1 == java/lang/Void)
            return null;
        obj = class1.newInstance();
        return obj;
    }

    protected Class loadClass(ClassLoader classloader, String s)
    {
        if(classloader == null)
            classloader = ClassLoader.getSystemClassLoader();
        return (Class)((Memoizer)caches.get(classloader)).get(s);
    }

    private final Memoizer caches = new Memoizer() {

        protected Memoizer create(ClassLoader classloader)
        {
            return classloader. new Memoizer() {

                protected Class create(String s)
                {
                    Class class1;
                    try
                    {
                        class1 = classLoader.loadClass(s);
                    }
                    catch(ClassNotFoundException classnotfoundexception)
                    {
                        return java/lang/Void;
                    }
                    return class1;
                }

                protected volatile Object create(Object obj)
                {
                    return create((String)obj);
                }

                final _cls1 this$1;
                final ClassLoader val$classLoader;

            
            {
                this$1 = final__pcls1;
                classLoader = ClassLoader.this;
                super();
            }
            }
;
        }

        protected volatile Object create(Object obj)
        {
            return create((ClassLoader)obj);
        }

        final Loader this$0;

            
            {
                this$0 = Loader.this;
                super();
            }
    }
;
}
