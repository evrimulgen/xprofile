// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.facebook.model;

import com.facebook.FacebookGraphObjectException;
import com.facebook.internal.Utility;
import com.facebook.internal.Validate;
import java.lang.reflect.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import org.json.*;

// Referenced classes of package com.facebook.model:
//            GraphObjectList, PropertyName, CreateGraphObject, JsonUtil

public interface GraphObject
{
    public static final class Factory
    {

        static Object coerceValueToExpectedType(Object obj, Class class1, ParameterizedType parameterizedtype)
        {
            Class class2;
            SimpleDateFormat asimpledateformat[];
            int i;
            int j;
            if(obj == null)
            {
                if(Boolean.TYPE.equals(class1))
                    return Boolean.valueOf(false);
                if(Character.TYPE.equals(class1))
                    return Character.valueOf('\0');
                if(class1.isPrimitive())
                    return Integer.valueOf(0);
                else
                    return null;
            }
            class2 = obj.getClass();
            if(class1.isAssignableFrom(class2))
                return obj;
            if(class1.isPrimitive())
                return obj;
            if(com/facebook/model/GraphObject.isAssignableFrom(class1))
            {
                if(org/json/JSONObject.isAssignableFrom(class2))
                    return createGraphObjectProxy(class1, (JSONObject)obj);
                if(com/facebook/model/GraphObject.isAssignableFrom(class2))
                    return ((GraphObject)obj).cast(class1);
                else
                    throw new FacebookGraphObjectException((new StringBuilder()).append("Can't create GraphObject from ").append(class2.getName()).toString());
            }
            if(java/lang/Iterable.equals(class1) || java/util/Collection.equals(class1) || java/util/List.equals(class1) || com/facebook/model/GraphObjectList.equals(class1))
            {
                if(parameterizedtype == null)
                    throw new FacebookGraphObjectException((new StringBuilder()).append("can't infer generic type of: ").append(class1.toString()).toString());
                Type atype[] = parameterizedtype.getActualTypeArguments();
                if(atype == null || atype.length != 1 || !(atype[0] instanceof Class))
                    throw new FacebookGraphObjectException("Expect collection properties to be of a type with exactly one generic parameter.");
                Class class3 = (Class)atype[0];
                if(org/json/JSONArray.isAssignableFrom(class2))
                    return createList((JSONArray)obj, class3);
                else
                    throw new FacebookGraphObjectException((new StringBuilder()).append("Can't create Collection from ").append(class2.getName()).toString());
            }
            if(java/lang/String.equals(class1))
            {
                if(java/lang/Double.isAssignableFrom(class2) || java/lang/Float.isAssignableFrom(class2))
                    return String.format("%f", new Object[] {
                        obj
                    });
                if(java/lang/Number.isAssignableFrom(class2))
                    return String.format("%d", new Object[] {
                        obj
                    });
                break MISSING_BLOCK_LABEL_453;
            }
            if(!java/util/Date.equals(class1) || !java/lang/String.isAssignableFrom(class2))
                break MISSING_BLOCK_LABEL_453;
            asimpledateformat = dateFormats;
            i = asimpledateformat.length;
            j = 0;
_L3:
            if(j >= i) goto _L2; else goto _L1
_L1:
            SimpleDateFormat simpledateformat = asimpledateformat[j];
            Date date = simpledateformat.parse((String)obj);
            if(date != null)
                return date;
            continue; /* Loop/switch isn't completed */
            ParseException parseexception;
            parseexception;
            j++;
              goto _L3
_L2:
            throw new FacebookGraphObjectException((new StringBuilder()).append("Can't convert type").append(class2.getName()).append(" to ").append(class1.getName()).toString());
        }

        static String convertCamelCaseToLowercaseWithUnderscores(String s)
        {
            return s.replaceAll("([a-z])([A-Z])", "$1_$2").toLowerCase(Locale.US);
        }

        public static GraphObject create()
        {
            return create(com/facebook/model/GraphObject);
        }

        public static GraphObject create(Class class1)
        {
            return createGraphObjectProxy(class1, new JSONObject());
        }

        public static GraphObject create(JSONObject jsonobject)
        {
            return create(jsonobject, com/facebook/model/GraphObject);
        }

        public static GraphObject create(JSONObject jsonobject, Class class1)
        {
            return createGraphObjectProxy(class1, jsonobject);
        }

        private static GraphObject createGraphObjectProxy(Class class1, JSONObject jsonobject)
        {
            verifyCanProxyClass(class1);
            Class aclass[] = {
                class1
            };
            GraphObjectProxy graphobjectproxy = new GraphObjectProxy(jsonobject, class1);
            return (GraphObject)Proxy.newProxyInstance(com/facebook/model/GraphObject.getClassLoader(), aclass, graphobjectproxy);
        }

        private static Map createGraphObjectProxyForMap(JSONObject jsonobject)
        {
            Class aclass[] = {
                java/util/Map
            };
            GraphObjectProxy graphobjectproxy = new GraphObjectProxy(jsonobject, java/util/Map);
            return (Map)Proxy.newProxyInstance(com/facebook/model/GraphObject.getClassLoader(), aclass, graphobjectproxy);
        }

        public static GraphObjectList createList(Class class1)
        {
            return createList(new JSONArray(), class1);
        }

        public static GraphObjectList createList(JSONArray jsonarray, Class class1)
        {
            return new GraphObjectListImpl(jsonarray, class1);
        }

        private static Object getUnderlyingJSONObject(Object obj)
        {
            JSONArray jsonarray;
            if(obj == null)
            {
                jsonarray = null;
            } else
            {
                Class class1 = obj.getClass();
                if(com/facebook/model/GraphObject.isAssignableFrom(class1))
                    return ((GraphObject)obj).getInnerJSONObject();
                if(com/facebook/model/GraphObjectList.isAssignableFrom(class1))
                    return ((GraphObjectList)obj).getInnerJSONArray();
                if(java/lang/Iterable.isAssignableFrom(class1))
                {
                    jsonarray = new JSONArray();
                    Iterator iterator = ((Iterable)obj).iterator();
                    while(iterator.hasNext()) 
                    {
                        Object obj1 = iterator.next();
                        if(com/facebook/model/GraphObject.isAssignableFrom(obj1.getClass()))
                            jsonarray.put(((GraphObject)obj1).getInnerJSONObject());
                        else
                            jsonarray.put(obj1);
                    }
                } else
                {
                    return obj;
                }
            }
            return jsonarray;
        }

        private static boolean hasClassBeenVerified(Class class1)
        {
            com/facebook/model/GraphObject$Factory;
            JVM INSTR monitorenter ;
            boolean flag = verifiedGraphObjectClasses.contains(class1);
            com/facebook/model/GraphObject$Factory;
            JVM INSTR monitorexit ;
            return flag;
            Exception exception;
            exception;
            throw exception;
        }

        public static boolean hasSameId(GraphObject graphobject, GraphObject graphobject1)
        {
            if(graphobject != null && graphobject1 != null && graphobject.asMap().containsKey("id") && graphobject1.asMap().containsKey("id"))
            {
                if(graphobject.equals(graphobject1))
                    return true;
                Object obj = graphobject.getProperty("id");
                Object obj1 = graphobject1.getProperty("id");
                if(obj != null && obj1 != null && (obj instanceof String) && (obj1 instanceof String))
                    return obj.equals(obj1);
            }
            return false;
        }

        private static void recordClassHasBeenVerified(Class class1)
        {
            com/facebook/model/GraphObject$Factory;
            JVM INSTR monitorenter ;
            verifiedGraphObjectClasses.add(class1);
            com/facebook/model/GraphObject$Factory;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        private static void verifyCanProxyClass(Class class1)
        {
            Method amethod[];
            int i;
            int j;
            if(hasClassBeenVerified(class1))
                return;
            if(!class1.isInterface())
                throw new FacebookGraphObjectException((new StringBuilder()).append("Factory can only wrap interfaces, not class: ").append(class1.getName()).toString());
            amethod = class1.getMethods();
            i = amethod.length;
            j = 0;
_L3:
            Method method;
            String s;
            int k;
            Class class2;
            boolean flag;
            if(j >= i)
                break; /* Loop/switch isn't completed */
            method = amethod[j];
            s = method.getName();
            k = method.getParameterTypes().length;
            class2 = method.getReturnType();
            flag = method.isAnnotationPresent(com/facebook/model/PropertyName);
              goto _L1
_L4:
            j++;
            if(true) goto _L3; else goto _L2
_L1:
            if(!method.getDeclaringClass().isAssignableFrom(com/facebook/model/GraphObject) && (k != 1 || class2 != Void.TYPE ? k != 0 || class2 == Void.TYPE || (flag ? Utility.isNullOrEmpty(((PropertyName)method.getAnnotation(com/facebook/model/PropertyName)).value()) : !s.startsWith("get") || s.length() <= 3) : flag ? Utility.isNullOrEmpty(((PropertyName)method.getAnnotation(com/facebook/model/PropertyName)).value()) : !s.startsWith("set") || s.length() <= 3)) goto _L5; else goto _L4
_L5:
            throw new FacebookGraphObjectException((new StringBuilder()).append("Factory can't proxy method: ").append(method.toString()).toString());
_L2:
            recordClassHasBeenVerified(class1);
            return;
        }

        private static final SimpleDateFormat dateFormats[];
        private static final HashSet verifiedGraphObjectClasses = new HashSet();

        static 
        {
            SimpleDateFormat asimpledateformat[] = new SimpleDateFormat[3];
            asimpledateformat[0] = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.US);
            asimpledateformat[1] = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US);
            asimpledateformat[2] = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
            dateFormats = asimpledateformat;
        }




        private Factory()
        {
        }
    }

    private static final class Factory.GraphObjectListImpl extends AbstractList
        implements GraphObjectList
    {

        private void checkIndex(int i)
        {
            if(i < 0 || i >= state.length())
                throw new IndexOutOfBoundsException();
            else
                return;
        }

        private void put(int i, Object obj)
        {
            Object obj1 = Factory.getUnderlyingJSONObject(obj);
            try
            {
                state.put(i, obj1);
                return;
            }
            catch(JSONException jsonexception)
            {
                throw new IllegalArgumentException(jsonexception);
            }
        }

        public void add(int i, Object obj)
        {
            if(i < 0)
                throw new IndexOutOfBoundsException();
            if(i < size())
            {
                throw new UnsupportedOperationException("Only adding items at the end of the list is supported.");
            } else
            {
                put(i, obj);
                return;
            }
        }

        public final GraphObjectList castToListOf(Class class1)
        {
            if(com/facebook/model/GraphObject.isAssignableFrom(itemType))
            {
                if(class1.isAssignableFrom(itemType))
                    return this;
                else
                    return Factory.createList(state, class1);
            } else
            {
                throw new FacebookGraphObjectException((new StringBuilder()).append("Can't cast GraphObjectCollection of non-GraphObject type ").append(itemType).toString());
            }
        }

        public void clear()
        {
            throw new UnsupportedOperationException();
        }

        public boolean equals(Object obj)
        {
            if(obj != null)
            {
                if(this == obj)
                    return true;
                if(getClass() == obj.getClass())
                {
                    Factory.GraphObjectListImpl graphobjectlistimpl = (Factory.GraphObjectListImpl)obj;
                    return state.equals(graphobjectlistimpl.state);
                }
            }
            return false;
        }

        public Object get(int i)
        {
            checkIndex(i);
            return Factory.coerceValueToExpectedType(state.opt(i), itemType, null);
        }

        public final JSONArray getInnerJSONArray()
        {
            return state;
        }

        public int hashCode()
        {
            return state.hashCode();
        }

        public boolean remove(Object obj)
        {
            throw new UnsupportedOperationException();
        }

        public boolean removeAll(Collection collection)
        {
            throw new UnsupportedOperationException();
        }

        public boolean retainAll(Collection collection)
        {
            throw new UnsupportedOperationException();
        }

        public Object set(int i, Object obj)
        {
            checkIndex(i);
            Object obj1 = get(i);
            put(i, obj);
            return obj1;
        }

        public int size()
        {
            return state.length();
        }

        public String toString()
        {
            Object aobj[] = new Object[2];
            aobj[0] = itemType.getSimpleName();
            aobj[1] = state;
            return String.format("GraphObjectList{itemType=%s, state=%s}", aobj);
        }

        private final Class itemType;
        private final JSONArray state;

        public Factory.GraphObjectListImpl(JSONArray jsonarray, Class class1)
        {
            Validate.notNull(jsonarray, "state");
            Validate.notNull(class1, "itemType");
            state = jsonarray;
            itemType = class1;
        }
    }

    private static final class Factory.GraphObjectProxy extends Factory.ProxyBase
    {

        private Object createGraphObjectsFromParameters(CreateGraphObject creategraphobject, Object obj)
        {
            String s;
label0:
            {
                if(creategraphobject != null && !Utility.isNullOrEmpty(creategraphobject.value()))
                {
                    s = creategraphobject.value();
                    if(!java/util/List.isAssignableFrom(obj.getClass()))
                        break label0;
                    GraphObjectList graphobjectlist = Factory.createList(com/facebook/model/GraphObject);
                    GraphObject graphobject1;
                    for(Iterator iterator = ((List)obj).iterator(); iterator.hasNext(); graphobjectlist.add(graphobject1))
                    {
                        Object obj1 = iterator.next();
                        graphobject1 = Factory.create();
                        graphobject1.setProperty(s, obj1);
                    }

                    obj = graphobjectlist;
                }
                return obj;
            }
            GraphObject graphobject = Factory.create();
            graphobject.setProperty(s, obj);
            return graphobject;
        }

        private final Object proxyGraphObjectGettersAndSetters(Method method, Object aobj[])
            throws JSONException
        {
            String s = method.getName();
            int i = method.getParameterTypes().length;
            PropertyName propertyname = (PropertyName)method.getAnnotation(com/facebook/model/PropertyName);
            String s1;
            if(propertyname != null)
                s1 = propertyname.value();
            else
                s1 = Factory.convertCamelCaseToLowercaseWithUnderscores(s.substring(3));
            if(i == 0)
            {
                Object obj1 = ((JSONObject)state).opt(s1);
                Class class1 = method.getReturnType();
                Type type = method.getGenericReturnType();
                boolean flag = type instanceof ParameterizedType;
                ParameterizedType parameterizedtype = null;
                if(flag)
                    parameterizedtype = (ParameterizedType)type;
                return Factory.coerceValueToExpectedType(obj1, class1, parameterizedtype);
            }
            if(i == 1)
            {
                Object obj = Factory.getUnderlyingJSONObject(createGraphObjectsFromParameters((CreateGraphObject)method.getAnnotation(com/facebook/model/CreateGraphObject), aobj[0]));
                ((JSONObject)state).putOpt(s1, obj);
                return null;
            } else
            {
                return throwUnexpectedMethodSignature(method);
            }
        }

        private final Object proxyGraphObjectMethods(Object obj, Method method, Object aobj[])
        {
            String s = method.getName();
            if(s.equals("cast"))
            {
                Class class1 = (Class)aobj[0];
                if(class1 != null && class1.isAssignableFrom(graphObjectClass))
                    return obj;
                else
                    return Factory.createGraphObjectProxy(class1, (JSONObject)state);
            }
            if(s.equals("getInnerJSONObject"))
                return ((Factory.GraphObjectProxy)Proxy.getInvocationHandler(obj)).state;
            if(s.equals("asMap"))
                return Factory.createGraphObjectProxyForMap((JSONObject)state);
            if(s.equals("getProperty"))
                return ((JSONObject)state).opt((String)aobj[0]);
            if(s.equals("getPropertyAs"))
                return Factory.coerceValueToExpectedType(((JSONObject)state).opt((String)aobj[0]), (Class)aobj[1], null);
            if(s.equals("getPropertyAsList"))
                return Factory.coerceValueToExpectedType(((JSONObject)state).opt((String)aobj[0]), com/facebook/model/GraphObjectList, ((Class)aobj[1]). new ParameterizedType() {

                    public Type[] getActualTypeArguments()
                    {
                        Type atype[] = new Type[1];
                        atype[0] = expectedType;
                        return atype;
                    }

                    public Type getOwnerType()
                    {
                        return null;
                    }

                    public Type getRawType()
                    {
                        return com/facebook/model/GraphObjectList;
                    }

                    final Factory.GraphObjectProxy this$0;
                    final Class val$expectedType;

            
            {
                this$0 = final_graphobjectproxy;
                expectedType = Class.this;
                super();
            }
                }
);
            if(s.equals("setProperty"))
                return setJSONProperty(aobj);
            if(s.equals("removeProperty"))
            {
                ((JSONObject)state).remove((String)aobj[0]);
                return null;
            } else
            {
                return throwUnexpectedMethodSignature(method);
            }
        }

        private final Object proxyMapMethods(Method method, Object aobj[])
        {
            String s = method.getName();
            if(s.equals("clear"))
            {
                JsonUtil.jsonObjectClear((JSONObject)state);
                return null;
            }
            if(s.equals("containsKey"))
                return Boolean.valueOf(((JSONObject)state).has((String)aobj[0]));
            if(s.equals("containsValue"))
                return Boolean.valueOf(JsonUtil.jsonObjectContainsValue((JSONObject)state, aobj[0]));
            if(s.equals("entrySet"))
                return JsonUtil.jsonObjectEntrySet((JSONObject)state);
            if(s.equals("get"))
                return ((JSONObject)state).opt((String)aobj[0]);
            if(s.equals("isEmpty"))
            {
                boolean flag;
                if(((JSONObject)state).length() == 0)
                    flag = true;
                else
                    flag = false;
                return Boolean.valueOf(flag);
            }
            if(s.equals("keySet"))
                return JsonUtil.jsonObjectKeySet((JSONObject)state);
            if(s.equals("put"))
                return setJSONProperty(aobj);
            if(s.equals("putAll"))
            {
                Map map;
                if(aobj[0] instanceof Map)
                    map = (Map)aobj[0];
                else
                if(aobj[0] instanceof GraphObject)
                    map = ((GraphObject)aobj[0]).asMap();
                else
                    return null;
                JsonUtil.jsonObjectPutAll((JSONObject)state, map);
                return null;
            }
            if(s.equals("remove"))
            {
                ((JSONObject)state).remove((String)aobj[0]);
                return null;
            }
            if(s.equals("size"))
                return Integer.valueOf(((JSONObject)state).length());
            if(s.equals("values"))
                return JsonUtil.jsonObjectValues((JSONObject)state);
            else
                return throwUnexpectedMethodSignature(method);
        }

        private Object setJSONProperty(Object aobj[])
        {
            String s = (String)aobj[0];
            Object obj = Factory.getUnderlyingJSONObject(aobj[1]);
            try
            {
                ((JSONObject)state).putOpt(s, obj);
            }
            catch(JSONException jsonexception)
            {
                throw new IllegalArgumentException(jsonexception);
            }
            return null;
        }

        public final Object invoke(Object obj, Method method, Object aobj[])
            throws Throwable
        {
            Class class1 = method.getDeclaringClass();
            if(class1 == java/lang/Object)
                return proxyObjectMethods(obj, method, aobj);
            if(class1 == java/util/Map)
                return proxyMapMethods(method, aobj);
            if(class1 == com/facebook/model/GraphObject)
                return proxyGraphObjectMethods(obj, method, aobj);
            if(com/facebook/model/GraphObject.isAssignableFrom(class1))
                return proxyGraphObjectGettersAndSetters(method, aobj);
            else
                return throwUnexpectedMethodSignature(method);
        }

        public String toString()
        {
            Object aobj[] = new Object[2];
            aobj[0] = graphObjectClass.getSimpleName();
            aobj[1] = state;
            return String.format("GraphObject{graphObjectClass=%s, state=%s}", aobj);
        }

        private static final String CASTTOMAP_METHOD = "asMap";
        private static final String CAST_METHOD = "cast";
        private static final String CLEAR_METHOD = "clear";
        private static final String CONTAINSKEY_METHOD = "containsKey";
        private static final String CONTAINSVALUE_METHOD = "containsValue";
        private static final String ENTRYSET_METHOD = "entrySet";
        private static final String GETINNERJSONOBJECT_METHOD = "getInnerJSONObject";
        private static final String GETPROPERTYASLIST_METHOD = "getPropertyAsList";
        private static final String GETPROPERTYAS_METHOD = "getPropertyAs";
        private static final String GETPROPERTY_METHOD = "getProperty";
        private static final String GET_METHOD = "get";
        private static final String ISEMPTY_METHOD = "isEmpty";
        private static final String KEYSET_METHOD = "keySet";
        private static final String PUTALL_METHOD = "putAll";
        private static final String PUT_METHOD = "put";
        private static final String REMOVEPROPERTY_METHOD = "removeProperty";
        private static final String REMOVE_METHOD = "remove";
        private static final String SETPROPERTY_METHOD = "setProperty";
        private static final String SIZE_METHOD = "size";
        private static final String VALUES_METHOD = "values";
        private final Class graphObjectClass;

        public Factory.GraphObjectProxy(JSONObject jsonobject, Class class1)
        {
            super(jsonobject);
            graphObjectClass = class1;
        }
    }

    private static abstract class Factory.ProxyBase
        implements InvocationHandler
    {

        protected final Object proxyObjectMethods(Object obj, Method method, Object aobj[])
            throws Throwable
        {
            String s = method.getName();
            if(s.equals("equals"))
            {
                Object obj1 = aobj[0];
                if(obj1 == null)
                    return Boolean.valueOf(false);
                InvocationHandler invocationhandler = Proxy.getInvocationHandler(obj1);
                if(!(invocationhandler instanceof Factory.GraphObjectProxy))
                {
                    return Boolean.valueOf(false);
                } else
                {
                    Factory.GraphObjectProxy graphobjectproxy = (Factory.GraphObjectProxy)invocationhandler;
                    return Boolean.valueOf(state.equals(graphobjectproxy.state));
                }
            }
            if(s.equals("toString"))
                return toString();
            else
                return method.invoke(state, aobj);
        }

        protected final Object throwUnexpectedMethodSignature(Method method)
        {
            throw new FacebookGraphObjectException((new StringBuilder()).append(getClass().getName()).append(" got an unexpected method signature: ").append(method.toString()).toString());
        }

        private static final String EQUALS_METHOD = "equals";
        private static final String TOSTRING_METHOD = "toString";
        protected final Object state;

        protected Factory.ProxyBase(Object obj)
        {
            state = obj;
        }
    }


    public abstract Map asMap();

    public abstract GraphObject cast(Class class1);

    public abstract JSONObject getInnerJSONObject();

    public abstract Object getProperty(String s);

    public abstract GraphObject getPropertyAs(String s, Class class1);

    public abstract GraphObjectList getPropertyAsList(String s, Class class1);

    public abstract void removeProperty(String s);

    public abstract void setProperty(String s, Object obj);
}
