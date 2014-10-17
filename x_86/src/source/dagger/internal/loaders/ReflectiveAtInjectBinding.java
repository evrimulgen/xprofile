// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package dagger.internal.loaders;

import dagger.internal.*;
import java.lang.reflect.*;
import java.util.*;
import javax.inject.Inject;
import javax.inject.Singleton;

public final class ReflectiveAtInjectBinding extends Binding
{

    private ReflectiveAtInjectBinding(String s, String s1, boolean flag, Class class1, Field afield[], Constructor constructor1, int i, 
            Class class2, String as[])
    {
        super(s, s1, flag, class1);
        constructor = constructor1;
        fields = afield;
        supertype = class2;
        keys = as;
        parameterBindings = new Binding[i];
        fieldBindings = new Binding[afield.length];
        loader = class1.getClassLoader();
    }

    public static Binding create(Class class1, boolean flag)
    {
        boolean flag1;
        ArrayList arraylist;
        ArrayList arraylist1;
        Constructor constructor1;
        flag1 = class1.isAnnotationPresent(javax/inject/Singleton);
        arraylist = new ArrayList();
        arraylist1 = new ArrayList();
        for(Class class2 = class1; class2 != java/lang/Object; class2 = class2.getSuperclass())
        {
            Field afield[] = class2.getDeclaredFields();
            int j1 = afield.length;
            int k1 = 0;
            while(k1 < j1) 
            {
                Field field = afield[k1];
                if(field.isAnnotationPresent(javax/inject/Inject) && !Modifier.isStatic(field.getModifiers()))
                {
                    if((2 & field.getModifiers()) != 0)
                        throw new IllegalStateException((new StringBuilder()).append("Can't inject private field: ").append(field).toString());
                    field.setAccessible(true);
                    arraylist1.add(field);
                    arraylist.add(Keys.get(field.getGenericType(), field.getAnnotations(), field));
                }
                k1++;
            }
        }

        constructor1 = null;
        Constructor aconstructor[] = getConstructorsForType(class1);
        int i = aconstructor.length;
        int j = 0;
        while(j < i) 
        {
            Constructor constructor3 = aconstructor[j];
            if(constructor3.isAnnotationPresent(javax/inject/Inject))
            {
                if(constructor1 != null)
                    throw new dagger.internal.Binding.InvalidBindingException(class1.getName(), "has too many injectable constructors");
                constructor1 = constructor3;
            }
            j++;
        }
        if(constructor1 != null) goto _L2; else goto _L1
_L1:
        if(arraylist1.isEmpty()) goto _L4; else goto _L3
_L3:
        Constructor constructor2 = class1.getDeclaredConstructor(new Class[0]);
        constructor1 = constructor2;
          goto _L2
_L4:
        if(flag)
            throw new dagger.internal.Binding.InvalidBindingException(class1.getName(), "has no injectable members. Do you want to add an injectable constructor?");
_L2:
        String s;
        int k;
        if(constructor1 != null)
        {
            if((2 & constructor1.getModifiers()) != 0)
                throw new IllegalStateException((new StringBuilder()).append("Can't inject private constructor: ").append(constructor1).toString());
            s = Keys.get(class1);
            constructor1.setAccessible(true);
            java.lang.reflect.Type atype[] = constructor1.getGenericParameterTypes();
            k = atype.length;
            if(k != 0)
            {
                java.lang.annotation.Annotation aannotation[][] = constructor1.getParameterAnnotations();
                int l = 0;
                do
                {
                    int i1 = atype.length;
                    if(l >= i1)
                        break;
                    arraylist.add(Keys.get(atype[l], aannotation[l], constructor1));
                    l++;
                } while(true);
            }
        } else
        {
            s = null;
            k = 0;
            if(flag1)
                throw new IllegalArgumentException((new StringBuilder()).append("No injectable constructor on @Singleton ").append(class1.getName()).toString());
        }
        Class class3 = class1.getSuperclass();
        if(class3 != null)
            if(Keys.isPlatformType(class3.getName()))
                class3 = null;
            else
                arraylist.add(Keys.getMembersKey(class3));
        return new ReflectiveAtInjectBinding(s, Keys.getMembersKey(class1), flag1, class1, (Field[])arraylist1.toArray(new Field[arraylist1.size()]), constructor1, k, class3, (String[])arraylist.toArray(new String[arraylist.size()]));
        NoSuchMethodException nosuchmethodexception;
        nosuchmethodexception;
        if(true) goto _L2; else goto _L5
_L5:
    }

    private static Constructor[] getConstructorsForType(Class class1)
    {
        return (Constructor[])class1.getDeclaredConstructors();
    }

    public void attach(Linker linker)
    {
        int i = 0;
        for(int j = 0; j < fields.length; j++)
        {
            if(fieldBindings[j] == null)
                fieldBindings[j] = linker.requestBinding(keys[i], fields[j], loader);
            i++;
        }

        if(constructor != null)
        {
            for(int k = 0; k < parameterBindings.length; k++)
            {
                if(parameterBindings[k] == null)
                    parameterBindings[k] = linker.requestBinding(keys[i], constructor, loader);
                i++;
            }

        }
        if(supertype != null && supertypeBinding == null)
            supertypeBinding = linker.requestBinding(keys[i], membersKey, loader, false, true);
    }

    public Object get()
    {
        if(constructor == null)
            throw new UnsupportedOperationException();
        Object aobj[] = new Object[parameterBindings.length];
        for(int i = 0; i < parameterBindings.length; i++)
            aobj[i] = parameterBindings[i].get();

        Object obj;
        try
        {
            obj = constructor.newInstance(aobj);
        }
        catch(InvocationTargetException invocationtargetexception)
        {
            Throwable throwable = invocationtargetexception.getCause();
            RuntimeException runtimeexception;
            if(throwable instanceof RuntimeException)
                runtimeexception = (RuntimeException)throwable;
            else
                runtimeexception = new RuntimeException(throwable);
            throw runtimeexception;
        }
        catch(IllegalAccessException illegalaccessexception)
        {
            throw new AssertionError(illegalaccessexception);
        }
        catch(InstantiationException instantiationexception)
        {
            throw new RuntimeException(instantiationexception);
        }
        injectMembers(obj);
        return obj;
    }

    public void getDependencies(Set set, Set set1)
    {
        if(parameterBindings != null)
            Collections.addAll(set, parameterBindings);
        Collections.addAll(set1, fieldBindings);
        if(supertypeBinding != null)
            set1.add(supertypeBinding);
    }

    public void injectMembers(Object obj)
    {
        int i = 0;
        do
        {
            try
            {
                if(i >= fields.length)
                    break;
                fields[i].set(obj, fieldBindings[i].get());
            }
            catch(IllegalAccessException illegalaccessexception)
            {
                throw new AssertionError(illegalaccessexception);
            }
            i++;
        } while(true);
        if(supertypeBinding != null)
            supertypeBinding.injectMembers(obj);
        return;
    }

    public String toString()
    {
        if(provideKey != null)
            return provideKey;
        else
            return membersKey;
    }

    private final Constructor constructor;
    private final Binding fieldBindings[];
    private final Field fields[];
    private final String keys[];
    private final ClassLoader loader;
    private final Binding parameterBindings[];
    private final Class supertype;
    private Binding supertypeBinding;
}
