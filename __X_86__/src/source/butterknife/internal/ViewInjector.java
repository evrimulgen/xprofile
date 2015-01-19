// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package butterknife.internal;

import java.util.*;

// Referenced classes of package butterknife.internal:
//            ViewInjection, FieldBinding, Binding, Listener, 
//            MethodBinding, Parameter

final class ViewInjector
{

    ViewInjector(String s, String s1, String s2)
    {
        classPackage = s;
        className = s1;
        targetClass = s2;
    }

    static void emitCastIfNeeded(StringBuilder stringbuilder, String s)
    {
        emitCastIfNeeded(stringbuilder, "android.view.View", s);
    }

    static void emitCastIfNeeded(StringBuilder stringbuilder, String s, String s1)
    {
        if(!s.equals(s1))
            stringbuilder.append('(').append(s1).append(") ");
    }

    private void emitFieldBindings(StringBuilder stringbuilder, ViewInjection viewinjection)
    {
        Collection collection = viewinjection.getFieldBindings();
        if(!collection.isEmpty())
        {
            Iterator iterator = collection.iterator();
            while(iterator.hasNext()) 
            {
                FieldBinding fieldbinding = (FieldBinding)iterator.next();
                stringbuilder.append("    target.").append(fieldbinding.getName()).append(" = ");
                emitCastIfNeeded(stringbuilder, fieldbinding.getType());
                stringbuilder.append("view;\n");
            }
        }
    }

    static void emitHumanDescription(StringBuilder stringbuilder, List list)
    {
label0:
        {
            switch(list.size())
            {
            default:
                int i = 0;
                for(int j = list.size(); i < j; i++)
                {
                    Binding binding = (Binding)list.get(i);
                    if(i != 0)
                        stringbuilder.append(", ");
                    if(i == j - 1)
                        stringbuilder.append("and ");
                    stringbuilder.append(binding.getDescription());
                }

                break;

            case 2: // '\002'
                break label0;

            case 1: // '\001'
                stringbuilder.append(((Binding)list.get(0)).getDescription());
                break;
            }
            return;
        }
        stringbuilder.append(((Binding)list.get(0)).getDescription()).append(" and ").append(((Binding)list.get(1)).getDescription());
    }

    private void emitInject(StringBuilder stringbuilder)
    {
        stringbuilder.append("  public static void inject(Finder finder, final ").append(targetClass).append(" target, Object source) {\n");
        if(parentInjector != null)
            stringbuilder.append("    ").append(parentInjector).append(".inject(finder, target, source);\n\n");
        stringbuilder.append("    View view;\n");
        for(Iterator iterator = viewIdMap.values().iterator(); iterator.hasNext(); emitViewInjection(stringbuilder, (ViewInjection)iterator.next()));
        stringbuilder.append("  }\n");
    }

    private void emitMethodBindings(StringBuilder stringbuilder, ViewInjection viewinjection)
    {
        Map map = viewinjection.getMethodBindings();
        if(!map.isEmpty())
        {
            String s = "";
            boolean flag = viewinjection.getRequiredBindings().isEmpty();
            if(flag)
            {
                stringbuilder.append("    if (view != null) {\n");
                s = "  ";
            }
            for(Iterator iterator = map.entrySet().iterator(); iterator.hasNext(); stringbuilder.append(s).append("      });\n"))
            {
                java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
                Listener listener = (Listener)entry.getKey();
                MethodBinding methodbinding = (MethodBinding)entry.getValue();
                boolean flag1;
                List list;
                int i;
                if(!"android.view.View".equals(listener.getOwnerType()))
                    flag1 = true;
                else
                    flag1 = false;
                stringbuilder.append(s).append("    ");
                if(flag1)
                    stringbuilder.append("((").append(listener.getOwnerType()).append(") ");
                stringbuilder.append("view");
                if(flag1)
                    stringbuilder.append(')');
                stringbuilder.append('.').append(listener.getSetterName()).append("(\n");
                stringbuilder.append(s).append("      new ").append(listener.getType()).append("() {\n");
                stringbuilder.append(s).append("        @Override public ").append(listener.getReturnType()).append(' ').append(listener.getMethodName()).append("(\n");
                list = listener.getParameterTypes();
                i = 0;
                for(int j = list.size(); i < j; i++)
                {
                    stringbuilder.append(s).append("          ").append((String)list.get(i)).append(" p").append(i);
                    if(i < j - 1)
                        stringbuilder.append(',');
                    stringbuilder.append('\n');
                }

                stringbuilder.append(s).append("        ) {\n");
                stringbuilder.append(s).append("          ");
                if(!"void".equals(listener.getReturnType()))
                    stringbuilder.append("return ");
                stringbuilder.append("target.").append(methodbinding.getName()).append('(');
                List list1 = methodbinding.getParameters();
                List list2 = listener.getParameterTypes();
                int k = 0;
                for(int l = list1.size(); k < l; k++)
                {
                    Parameter parameter = (Parameter)list1.get(k);
                    int i1 = parameter.getListenerPosition();
                    emitCastIfNeeded(stringbuilder, (String)list2.get(i1), parameter.getType());
                    stringbuilder.append('p').append(i1);
                    if(k < l - 1)
                        stringbuilder.append(", ");
                }

                stringbuilder.append(");\n");
                stringbuilder.append(s).append("        }\n");
            }

            if(flag)
            {
                stringbuilder.append("    }\n");
                return;
            }
        }
    }

    private void emitReset(StringBuilder stringbuilder)
    {
        stringbuilder.append("  public static void reset(").append(targetClass).append(" target) {\n");
        if(parentInjector != null)
            stringbuilder.append("    ").append(parentInjector).append(".reset(target);\n\n");
        for(Iterator iterator = viewIdMap.values().iterator(); iterator.hasNext();)
        {
            Iterator iterator1 = ((ViewInjection)iterator.next()).getFieldBindings().iterator();
            while(iterator1.hasNext()) 
            {
                FieldBinding fieldbinding = (FieldBinding)iterator1.next();
                stringbuilder.append("    target.").append(fieldbinding.getName()).append(" = null;\n");
            }
        }

        stringbuilder.append("  }\n");
    }

    private void emitViewInjection(StringBuilder stringbuilder, ViewInjection viewinjection)
    {
        stringbuilder.append("    view = finder.findById(source, ").append(viewinjection.getId()).append(");\n");
        List list = viewinjection.getRequiredBindings();
        if(!list.isEmpty())
        {
            stringbuilder.append("    if (view == null) {\n").append("      throw new IllegalStateException(\"Required view with id '").append(viewinjection.getId()).append("' for ");
            emitHumanDescription(stringbuilder, list);
            stringbuilder.append(" was not found. If this view is optional add '@Optional' annotation.\");\n").append("    }\n");
        }
        emitFieldBindings(stringbuilder, viewinjection);
        emitMethodBindings(stringbuilder, viewinjection);
    }

    private ViewInjection getOrCreateViewBinding(int i)
    {
        ViewInjection viewinjection = (ViewInjection)viewIdMap.get(Integer.valueOf(i));
        if(viewinjection == null)
        {
            viewinjection = new ViewInjection(i);
            viewIdMap.put(Integer.valueOf(i), viewinjection);
        }
        return viewinjection;
    }

    void addField(int i, String s, String s1, boolean flag)
    {
        getOrCreateViewBinding(i).addFieldBinding(new FieldBinding(s, s1, flag));
    }

    boolean addMethod(int i, Listener listener, String s, List list, boolean flag)
    {
        try
        {
            getOrCreateViewBinding(i).addMethodBinding(listener, new MethodBinding(s, list, flag));
        }
        catch(IllegalStateException illegalstateexception)
        {
            return false;
        }
        return true;
    }

    String brewJava()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("// Generated code from Butter Knife. Do not modify!\n");
        stringbuilder.append("package ").append(classPackage).append(";\n\n");
        stringbuilder.append("import android.view.View;\n");
        stringbuilder.append("import butterknife.ButterKnife.Finder;\n\n");
        stringbuilder.append("public class ").append(className).append(" {\n");
        emitInject(stringbuilder);
        stringbuilder.append('\n');
        emitReset(stringbuilder);
        stringbuilder.append("}\n");
        return stringbuilder.toString();
    }

    String getFqcn()
    {
        return (new StringBuilder()).append(classPackage).append(".").append(className).toString();
    }

    void setParentInjector(String s)
    {
        parentInjector = s;
    }

    private final String className;
    private final String classPackage;
    private String parentInjector;
    private final String targetClass;
    private final Map viewIdMap = new LinkedHashMap();
}
