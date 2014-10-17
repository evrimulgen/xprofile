// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package butterknife.internal;

import butterknife.*;
import java.io.*;
import java.lang.reflect.Method;
import java.util.*;
import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.lang.model.type.*;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.JavaFileObject;

// Referenced classes of package butterknife.internal:
//            ViewInjector, ListenerClass, Listener, Parameter

public final class InjectViewProcessor extends AbstractProcessor
{

    public InjectViewProcessor()
    {
    }

    private boolean containsTypeMirror(Collection collection, TypeMirror typemirror)
    {
        TypeMirror typemirror1 = typeUtils.erasure(typemirror);
        for(Iterator iterator = collection.iterator(); iterator.hasNext();)
        {
            TypeMirror typemirror2 = (TypeMirror)iterator.next();
            if(typeUtils.isSameType(typemirror2, typemirror1))
                return true;
        }

        return false;
    }

    private transient void error(Element element, String s, Object aobj[])
    {
        processingEnv.getMessager().printMessage(javax.tools.Diagnostic.Kind.ERROR, String.format(s, aobj), element);
    }

    private void findAndParseListener(RoundEnvironment roundenvironment, Class class1, Map map, Set set)
    {
        for(Iterator iterator = roundenvironment.getElementsAnnotatedWith(class1).iterator(); iterator.hasNext();)
        {
            Element element = (Element)iterator.next();
            try
            {
                parseListenerAnnotation(class1, element, map, set);
            }
            catch(Exception exception)
            {
                StringWriter stringwriter = new StringWriter();
                exception.printStackTrace(new PrintWriter(stringwriter));
                Object aobj[] = new Object[2];
                aobj[0] = class1.getSimpleName();
                aobj[1] = stringwriter.toString();
                error(element, "Unable to generate view injector for @%s.\n\n%s", aobj);
            }
        }

    }

    private Map findAndParseTargets(RoundEnvironment roundenvironment)
    {
        LinkedHashMap linkedhashmap = new LinkedHashMap();
        LinkedHashSet linkedhashset = new LinkedHashSet();
        for(Iterator iterator = roundenvironment.getElementsAnnotatedWith(butterknife/InjectView).iterator(); iterator.hasNext();)
        {
            Element element = (Element)iterator.next();
            try
            {
                parseInjectView(element, linkedhashmap, linkedhashset);
            }
            catch(Exception exception)
            {
                StringWriter stringwriter = new StringWriter();
                exception.printStackTrace(new PrintWriter(stringwriter));
                Object aobj[] = new Object[1];
                aobj[0] = stringwriter.toString();
                error(element, "Unable to generate view injector for @InjectView.\n\n%s", aobj);
            }
        }

        for(Iterator iterator1 = LISTENERS.iterator(); iterator1.hasNext(); findAndParseListener(roundenvironment, (Class)iterator1.next(), linkedhashmap, linkedhashset));
        Iterator iterator2 = linkedhashmap.entrySet().iterator();
        do
        {
            if(!iterator2.hasNext())
                break;
            java.util.Map.Entry entry = (java.util.Map.Entry)iterator2.next();
            String s = findParentFqcn((TypeElement)entry.getKey(), linkedhashset);
            if(s != null)
                ((ViewInjector)entry.getValue()).setParentInjector((new StringBuilder()).append(s).append("$$ViewInjector").toString());
        } while(true);
        return linkedhashmap;
    }

    private String findParentFqcn(TypeElement typeelement, Set set)
    {
        TypeMirror typemirror;
        do
        {
            typemirror = typeelement.getSuperclass();
            if(typemirror.getKind() == TypeKind.NONE)
                return null;
            typeelement = (TypeElement)((DeclaredType)typemirror).asElement();
        } while(!containsTypeMirror(set, typemirror));
        String s = getPackageName(typeelement);
        return (new StringBuilder()).append(s).append(".").append(getClassName(typeelement, s)).toString();
    }

    private static String getClassName(TypeElement typeelement, String s)
    {
        int i = 1 + s.length();
        return typeelement.getQualifiedName().toString().substring(i).replace('.', '$');
    }

    private ViewInjector getOrCreateTargetClass(Map map, TypeElement typeelement)
    {
        ViewInjector viewinjector = (ViewInjector)map.get(typeelement);
        if(viewinjector == null)
        {
            String s = typeelement.getQualifiedName().toString();
            String s1 = getPackageName(typeelement);
            viewinjector = new ViewInjector(s1, (new StringBuilder()).append(getClassName(typeelement, s1)).append("$$ViewInjector").toString(), s);
            map.put(typeelement, viewinjector);
        }
        return viewinjector;
    }

    private String getPackageName(TypeElement typeelement)
    {
        return elementUtils.getPackageOf(typeelement).getQualifiedName().toString();
    }

    private boolean isSubtypeOfType(TypeMirror typemirror, String s)
    {
        if(!s.equals(typemirror.toString())) goto _L2; else goto _L1
_L1:
        return true;
_L2:
        DeclaredType declaredtype;
        StringBuilder stringbuilder;
        if(!(typemirror instanceof DeclaredType))
            return false;
        declaredtype = (DeclaredType)typemirror;
        List list = declaredtype.getTypeArguments();
        if(list.size() <= 0)
            break; /* Loop/switch isn't completed */
        stringbuilder = new StringBuilder(declaredtype.asElement().toString());
        stringbuilder.append('<');
        for(int i = 0; i < list.size(); i++)
        {
            if(i > 0)
                stringbuilder.append(',');
            stringbuilder.append('?');
        }

        stringbuilder.append('>');
        if(stringbuilder.toString().equals(s)) goto _L1; else goto _L3
_L3:
        Element element = declaredtype.asElement();
        if(!(element instanceof TypeElement))
            return false;
        TypeElement typeelement = (TypeElement)element;
        if(!isSubtypeOfType(typeelement.getSuperclass(), s))
        {
            for(Iterator iterator = typeelement.getInterfaces().iterator(); iterator.hasNext();)
                if(isSubtypeOfType((TypeMirror)iterator.next(), s))
                    return true;

            return false;
        }
        if(true) goto _L1; else goto _L4
_L4:
    }

    private boolean isValidForGeneratedCode(Class class1, String s, Element element)
    {
        TypeElement typeelement;
        boolean flag;
label0:
        {
            typeelement = (TypeElement)element.getEnclosingElement();
            Set set = element.getModifiers();
            if(!set.contains(Modifier.PRIVATE))
            {
                boolean flag1 = set.contains(Modifier.STATIC);
                flag = false;
                if(!flag1)
                    break label0;
            }
            Object aobj[] = new Object[4];
            aobj[0] = class1.getSimpleName();
            aobj[1] = s;
            aobj[2] = typeelement.getQualifiedName();
            aobj[3] = element.getSimpleName();
            error(element, "@%s %s must not be private or static. (%s.%s)", aobj);
            flag = true;
        }
        if(typeelement.getKind() != ElementKind.CLASS)
        {
            Object aobj2[] = new Object[4];
            aobj2[0] = class1.getSimpleName();
            aobj2[1] = s;
            aobj2[2] = typeelement.getQualifiedName();
            aobj2[3] = element.getSimpleName();
            error(typeelement, "@%s %s may only be contained in classes. (%s.%s)", aobj2);
            flag = true;
        }
        if(typeelement.getModifiers().contains(Modifier.PRIVATE))
        {
            Object aobj1[] = new Object[4];
            aobj1[0] = class1.getSimpleName();
            aobj1[1] = s;
            aobj1[2] = typeelement.getQualifiedName();
            aobj1[3] = element.getSimpleName();
            error(typeelement, "@%s %s may not be contained in private classes. (%s.%s)", aobj1);
            flag = true;
        }
        return flag;
    }

    private void parseInjectView(Element element, Map map, Set set)
    {
        boolean flag = true;
        TypeElement typeelement = (TypeElement)element.getEnclosingElement();
        boolean flag1 = isSubtypeOfType(element.asType(), "android.view.View");
        boolean flag2 = false;
        if(!flag1)
        {
            Object aobj[] = new Object[2];
            aobj[0] = typeelement.getQualifiedName();
            aobj[flag] = element.getSimpleName();
            error(element, "@InjectView fields must extend from View (%s.%s).", aobj);
            flag2 = true;
        }
        if(flag2 | isValidForGeneratedCode(butterknife/InjectView, "fields", element))
            return;
        String s = element.getSimpleName().toString();
        int i = ((InjectView)element.getAnnotation(butterknife/InjectView)).value();
        String s1 = element.asType().toString();
        if(element.getAnnotation(butterknife/Optional) != null)
            flag = false;
        getOrCreateTargetClass(map, typeelement).addField(i, s, s1, flag);
        set.add(typeUtils.erasure(typeelement.asType()));
    }

    private void parseListenerAnnotation(Class class1, Element element, Map map, Set set)
        throws Exception
    {
        if((element instanceof ExecutableElement) && element.getKind() == ElementKind.METHOD) goto _L2; else goto _L1
_L1:
        Object aobj[] = new Object[1];
        aobj[0] = class1.getSimpleName();
        error(element, "@%s annotation must be on a method.", aobj);
_L4:
        return;
_L2:
        ExecutableElement executableelement;
        TypeElement typeelement;
        int ai[];
        String s;
        boolean flag;
        Listener listener;
        List list;
        Parameter aparameter[];
        BitSet bitset;
        List list1;
        int j1;
        TypeMirror typemirror;
        int l1;
        executableelement = (ExecutableElement)element;
        typeelement = (TypeElement)element.getEnclosingElement();
        java.lang.annotation.Annotation annotation = element.getAnnotation(class1);
        Method method = class1.getDeclaredMethod("value", new Class[0]);
        if(method == null || method.getReturnType() != [I)
        {
            Object aobj1[] = new Object[3];
            aobj1[0] = class1;
            aobj1[1] = typeelement.getQualifiedName();
            aobj1[2] = element.getSimpleName();
            error(element, "@%s annotation lacks int[] value property. (%s.%s)", aobj1);
            return;
        }
        ai = (int[])(int[])method.invoke(annotation, new Object[0]);
        s = executableelement.getSimpleName().toString();
        boolean flag1;
        LinkedHashSet linkedhashset;
        int i;
        if(element.getAnnotation(butterknife/Optional) == null)
            flag = true;
        else
            flag = false;
        flag1 = isValidForGeneratedCode(class1, "methods", element);
        linkedhashset = new LinkedHashSet(ai.length);
        i = ai.length;
        for(int j = 0; j < i; j++)
        {
            int l2 = ai[j];
            if(!linkedhashset.add(Integer.valueOf(l2)))
            {
                Object aobj7[] = new Object[4];
                aobj7[0] = class1.getSimpleName();
                aobj7[1] = Integer.valueOf(l2);
                aobj7[2] = typeelement.getQualifiedName();
                aobj7[3] = element.getSimpleName();
                error(element, "@%s annotation for method contains duplicate ID %d. (%s.%s)", aobj7);
                flag1 = true;
            }
        }

        ListenerClass listenerclass = (ListenerClass)class1.getAnnotation(butterknife/internal/ListenerClass);
        if(listenerclass == null)
        {
            Object aobj6[] = new Object[2];
            aobj6[0] = butterknife/internal/ListenerClass.getSimpleName();
            aobj6[1] = class1.getSimpleName();
            error(element, "No @%s defined on @%s.", aobj6);
            return;
        }
        String s1 = listenerclass.value();
        listener = (Listener)LISTENER_MAP.get(s1);
        int k1;
        int i2;
        if(listener == null)
            try
            {
                listener = Listener.from(elementUtils.getTypeElement(s1), typeUtils);
                LISTENER_MAP.put(s1, listener);
            }
            catch(IllegalArgumentException illegalargumentexception)
            {
                TypeElement typeelement1 = elementUtils.getTypeElement(class1.getName());
                Object aobj5[] = new Object[3];
                aobj5[0] = illegalargumentexception.getMessage();
                aobj5[1] = s1;
                aobj5[2] = class1.getName();
                error(typeelement1, "%s (%s on @%s)", aobj5);
                return;
            }
        list = executableelement.getParameters();
        if(list.size() > listener.getParameterTypes().size())
        {
            Object aobj4[] = new Object[4];
            aobj4[0] = class1.getSimpleName();
            aobj4[1] = Integer.valueOf(listener.getParameterTypes().size());
            aobj4[2] = typeelement.getQualifiedName();
            aobj4[3] = element.getSimpleName();
            error(element, "@%s methods can have at most %s parameter(s). (%s.%s)", aobj4);
            flag1 = true;
        }
        if(!executableelement.getReturnType().toString().equals(listener.getReturnType()))
        {
            Object aobj3[] = new Object[4];
            aobj3[0] = class1.getSimpleName();
            aobj3[1] = listener.getReturnType();
            aobj3[2] = typeelement.getQualifiedName();
            aobj3[3] = element.getSimpleName();
            error(element, "@%s methods must have a '%s' return type. (%s.%s)", aobj3);
            flag1 = true;
        }
        if(flag1) goto _L4; else goto _L3
_L3:
        aparameter = Parameter.NONE;
        if(list.isEmpty()) goto _L6; else goto _L5
_L5:
        aparameter = new Parameter[list.size()];
        bitset = new BitSet(list.size());
        list1 = listener.getParameterTypes();
        j1 = 0;
_L12:
        k1 = list.size();
        if(j1 >= k1)
            break; /* Loop/switch isn't completed */
        typemirror = ((VariableElement)list.get(j1)).asType();
        l1 = 0;
_L9:
        i2 = list1.size();
        if(l1 >= i2)
            break; /* Loop/switch isn't completed */
          goto _L7
_L11:
        l1++;
        if(true) goto _L9; else goto _L8
_L7:
        if(bitset.get(l1) || !isSubtypeOfType(typemirror, (String)list1.get(l1))) goto _L11; else goto _L10
_L10:
        String s3 = typemirror.toString();
        aparameter[j1] = new Parameter(l1, s3);
        bitset.set(l1);
_L8:
        if(aparameter[j1] == null)
        {
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append("Unable to match @").append(class1.getSimpleName()).append(" method arguments. (").append(typeelement.getQualifiedName()).append('.').append(element.getSimpleName()).append(')');
            int j2 = 0;
            do
            {
                int k2 = aparameter.length;
                if(j2 >= k2)
                    break;
                Parameter parameter = aparameter[j2];
                stringbuilder.append("\n\n  Parameter #").append(j2 + 1).append(": ").append(((VariableElement)list.get(j2)).asType().toString()).append("\n    ");
                if(parameter == null)
                    stringbuilder.append("did not match any listener parameters");
                else
                    stringbuilder.append("matched listener parameter #").append(1 + parameter.getListenerPosition()).append(": ").append(parameter.getType());
                j2++;
            } while(true);
            stringbuilder.append("\n\nMethods may have up to ").append(listener.getParameterTypes().size()).append(" parameter(s):\n");
            String s2;
            for(Iterator iterator = listener.getParameterTypes().iterator(); iterator.hasNext(); stringbuilder.append("\n  ").append(s2))
                s2 = (String)iterator.next();

            stringbuilder.append("\n\nThese may be listed in any order but will be searched for from top to bottom.");
            error(executableelement, stringbuilder.toString(), new Object[0]);
            return;
        }
        j1++;
        if(true) goto _L12; else goto _L6
_L6:
        ViewInjector viewinjector = getOrCreateTargetClass(map, typeelement);
        int k = ai.length;
        for(int l = 0; l < k; l++)
        {
            int i1 = ai[l];
            if(!viewinjector.addMethod(i1, listener, s, Arrays.asList(aparameter), flag))
            {
                Object aobj2[] = new Object[3];
                aobj2[0] = class1.getSimpleName();
                aobj2[1] = Integer.valueOf(i1);
                aobj2[2] = typeelement.getQualifiedName();
                error(element, "Multiple @%s methods declared for ID %s in %s.", aobj2);
                return;
            }
        }

        set.add(typeUtils.erasure(typeelement.asType()));
        return;
    }

    public Set getSupportedAnnotationTypes()
    {
        LinkedHashSet linkedhashset = new LinkedHashSet();
        linkedhashset.add(butterknife/InjectView.getCanonicalName());
        for(Iterator iterator = LISTENERS.iterator(); iterator.hasNext(); linkedhashset.add(((Class)iterator.next()).getCanonicalName()));
        return linkedhashset;
    }

    public SourceVersion getSupportedSourceVersion()
    {
        return SourceVersion.latestSupported();
    }

    public void init(ProcessingEnvironment processingenvironment)
    {
        this;
        JVM INSTR monitorenter ;
        super.init(processingenvironment);
        elementUtils = processingenvironment.getElementUtils();
        typeUtils = processingenvironment.getTypeUtils();
        filer = processingenvironment.getFiler();
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public boolean process(Set set, RoundEnvironment roundenvironment)
    {
        for(Iterator iterator = findAndParseTargets(roundenvironment).entrySet().iterator(); iterator.hasNext();)
        {
            java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
            TypeElement typeelement = (TypeElement)entry.getKey();
            ViewInjector viewinjector = (ViewInjector)entry.getValue();
            try
            {
                Writer writer = filer.createSourceFile(viewinjector.getFqcn(), new Element[] {
                    typeelement
                }).openWriter();
                writer.write(viewinjector.brewJava());
                writer.flush();
                writer.close();
            }
            catch(IOException ioexception)
            {
                Object aobj[] = new Object[2];
                aobj[0] = typeelement;
                aobj[1] = ioexception.getMessage();
                error(typeelement, "Unable to write injector for type %s: %s", aobj);
            }
        }

        return true;
    }

    private static final List LISTENERS = Arrays.asList(new Class[] {
        butterknife/OnCheckedChanged, butterknife/OnClick, butterknife/OnEditorAction, butterknife/OnFocusChanged, butterknife/OnItemClick, butterknife/OnItemLongClick, butterknife/OnLongClick
    });
    private static final Map LISTENER_MAP = new LinkedHashMap();
    public static final String SUFFIX = "$$ViewInjector";
    static final String VIEW_TYPE = "android.view.View";
    private Elements elementUtils;
    private Filer filer;
    private Types typeUtils;

}
