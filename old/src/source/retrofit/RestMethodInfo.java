// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package retrofit;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import retrofit.http.Body;
import retrofit.http.EncodedPath;
import retrofit.http.EncodedQuery;
import retrofit.http.EncodedQueryMap;
import retrofit.http.Field;
import retrofit.http.FieldMap;
import retrofit.http.FormUrlEncoded;
import retrofit.http.Header;
import retrofit.http.Headers;
import retrofit.http.Multipart;
import retrofit.http.Part;
import retrofit.http.PartMap;
import retrofit.http.Path;
import retrofit.http.Query;
import retrofit.http.QueryMap;
import retrofit.http.RestMethod;
import rx.Observable;

// Referenced classes of package retrofit:
//            Callback, Platform, Types

final class RestMethodInfo
{
    static final class ParamUsage extends Enum
    {

        public static ParamUsage valueOf(String s)
        {
            return (ParamUsage)Enum.valueOf(retrofit/RestMethodInfo$ParamUsage, s);
        }

        public static ParamUsage[] values()
        {
            return (ParamUsage[])$VALUES.clone();
        }

        private static final ParamUsage $VALUES[];
        public static final ParamUsage BODY;
        public static final ParamUsage ENCODED_PATH;
        public static final ParamUsage ENCODED_QUERY;
        public static final ParamUsage ENCODED_QUERY_MAP;
        public static final ParamUsage FIELD;
        public static final ParamUsage FIELD_MAP;
        public static final ParamUsage HEADER;
        public static final ParamUsage PART;
        public static final ParamUsage PART_MAP;
        public static final ParamUsage PATH;
        public static final ParamUsage QUERY;
        public static final ParamUsage QUERY_MAP;

        static 
        {
            PATH = new ParamUsage("PATH", 0);
            ENCODED_PATH = new ParamUsage("ENCODED_PATH", 1);
            QUERY = new ParamUsage("QUERY", 2);
            ENCODED_QUERY = new ParamUsage("ENCODED_QUERY", 3);
            QUERY_MAP = new ParamUsage("QUERY_MAP", 4);
            ENCODED_QUERY_MAP = new ParamUsage("ENCODED_QUERY_MAP", 5);
            FIELD = new ParamUsage("FIELD", 6);
            FIELD_MAP = new ParamUsage("FIELD_MAP", 7);
            PART = new ParamUsage("PART", 8);
            PART_MAP = new ParamUsage("PART_MAP", 9);
            BODY = new ParamUsage("BODY", 10);
            HEADER = new ParamUsage("HEADER", 11);
            ParamUsage aparamusage[] = new ParamUsage[12];
            aparamusage[0] = PATH;
            aparamusage[1] = ENCODED_PATH;
            aparamusage[2] = QUERY;
            aparamusage[3] = ENCODED_QUERY;
            aparamusage[4] = QUERY_MAP;
            aparamusage[5] = ENCODED_QUERY_MAP;
            aparamusage[6] = FIELD;
            aparamusage[7] = FIELD_MAP;
            aparamusage[8] = PART;
            aparamusage[9] = PART_MAP;
            aparamusage[10] = BODY;
            aparamusage[11] = HEADER;
            $VALUES = aparamusage;
        }

        private ParamUsage(String s, int i)
        {
            super(s, i);
        }
    }

    static final class RequestType extends Enum
    {

        public static RequestType valueOf(String s)
        {
            return (RequestType)Enum.valueOf(retrofit/RestMethodInfo$RequestType, s);
        }

        public static RequestType[] values()
        {
            return (RequestType[])$VALUES.clone();
        }

        private static final RequestType $VALUES[];
        public static final RequestType FORM_URL_ENCODED;
        public static final RequestType MULTIPART;
        public static final RequestType SIMPLE;

        static 
        {
            SIMPLE = new RequestType("SIMPLE", 0);
            MULTIPART = new RequestType("MULTIPART", 1);
            FORM_URL_ENCODED = new RequestType("FORM_URL_ENCODED", 2);
            RequestType arequesttype[] = new RequestType[3];
            arequesttype[0] = SIMPLE;
            arequesttype[1] = MULTIPART;
            arequesttype[2] = FORM_URL_ENCODED;
            $VALUES = arequesttype;
        }

        private RequestType(String s, int i)
        {
            super(s, i);
        }
    }

    private static final class ResponseType extends Enum
    {

        public static ResponseType valueOf(String s)
        {
            return (ResponseType)Enum.valueOf(retrofit/RestMethodInfo$ResponseType, s);
        }

        public static ResponseType[] values()
        {
            return (ResponseType[])$VALUES.clone();
        }

        private static final ResponseType $VALUES[];
        public static final ResponseType OBJECT;
        public static final ResponseType OBSERVABLE;
        public static final ResponseType VOID;

        static 
        {
            VOID = new ResponseType("VOID", 0);
            OBSERVABLE = new ResponseType("OBSERVABLE", 1);
            OBJECT = new ResponseType("OBJECT", 2);
            ResponseType aresponsetype[] = new ResponseType[3];
            aresponsetype[0] = VOID;
            aresponsetype[1] = OBSERVABLE;
            aresponsetype[2] = OBJECT;
            $VALUES = aresponsetype;
        }

        private ResponseType(String s, int i)
        {
            super(s, i);
        }
    }

    private static final class RxSupport
    {

        public static Type getObservableType(Type type, Class class1)
        {
            return Types.getSupertype(type, class1, rx/Observable);
        }

        public static boolean isObservable(Class class1)
        {
            return class1 == rx/Observable;
        }

        private RxSupport()
        {
        }
    }


    RestMethodInfo(Method method1)
    {
        boolean flag = true;
        super();
        loaded = false;
        requestType = RequestType.SIMPLE;
        method = method1;
        boolean flag1;
        if(responseType == ResponseType.OBJECT)
            flag1 = flag;
        else
            flag1 = false;
        isSynchronous = flag1;
        if(responseType != ResponseType.OBSERVABLE)
            flag = false;
        isObservable = flag;
    }

    private static Type getParameterUpperBound(ParameterizedType parameterizedtype)
    {
        Type atype[] = parameterizedtype.getActualTypeArguments();
        for(int i = 0; i < atype.length; i++)
        {
            Type type = atype[i];
            if(type instanceof WildcardType)
                atype[i] = ((WildcardType)type).getUpperBounds()[0];
        }

        return atype[0];
    }

    private transient RuntimeException methodError(String s, Object aobj[])
    {
        if(aobj.length > 0)
            s = String.format(s, aobj);
        return new IllegalArgumentException((new StringBuilder()).append(method.getDeclaringClass().getSimpleName()).append(".").append(method.getName()).append(": ").append(s).toString());
    }

    private transient RuntimeException parameterError(int i, String s, Object aobj[])
    {
        return methodError((new StringBuilder()).append(s).append(" (parameter #").append(i + 1).append(")").toString(), aobj);
    }

    private void parseMethodAnnotations()
    {
        Annotation aannotation[];
        int i;
        int j;
        aannotation = method.getAnnotations();
        i = aannotation.length;
        j = 0;
_L6:
        Annotation annotation;
        Class class1;
        Annotation aannotation1[];
        int k;
        int l;
        if(j < i)
        {
            annotation = aannotation[j];
            class1 = annotation.annotationType();
            aannotation1 = class1.getAnnotations();
            k = aannotation1.length;
            l = 0;
            break MISSING_BLOCK_LABEL_47;
        }
          goto _L1
        l++;
        if(true) goto _L3; else goto _L2
_L3:
        RestMethod restmethod = null;
        if(l < k)
        {
            Annotation annotation1 = aannotation1[l];
            if(retrofit/http/RestMethod != annotation1.annotationType())
                break MISSING_BLOCK_LABEL_129;
            restmethod = (RestMethod)annotation1;
        }
        if(restmethod == null)
            break; /* Loop/switch isn't completed */
        if(requestMethod != null)
        {
            Object aobj1[] = new Object[2];
            aobj1[0] = requestMethod;
            aobj1[1] = restmethod.value();
            throw methodError("Only one HTTP method is allowed. Found: %s and %s.", aobj1);
        }
        String s;
        try
        {
            s = (String)class1.getMethod("value", new Class[0]).invoke(annotation, new Object[0]);
        }
        catch(Exception exception)
        {
            Object aobj[] = new Object[1];
            aobj[0] = class1.getSimpleName();
            throw methodError("Failed to extract String 'value' from @%s annotation.", aobj);
        }
        parsePath(s);
        requestMethod = restmethod.value();
        requestHasBody = restmethod.hasBody();
_L4:
        j++;
        continue; /* Loop/switch isn't completed */
_L2:
        if(class1 == retrofit/http/Headers)
        {
            String as[] = ((Headers)annotation).value();
            if(as.length == 0)
                throw methodError("@Headers annotation is empty.", new Object[0]);
            headers = parseHeaders(as);
        } else
        if(class1 == retrofit/http/Multipart)
        {
            if(requestType != RequestType.SIMPLE)
                throw methodError("Only one encoding annotation is allowed.", new Object[0]);
            requestType = RequestType.MULTIPART;
        } else
        if(class1 == retrofit/http/FormUrlEncoded)
        {
            if(requestType != RequestType.SIMPLE)
                throw methodError("Only one encoding annotation is allowed.", new Object[0]);
            requestType = RequestType.FORM_URL_ENCODED;
        }
        if(true) goto _L4; else goto _L1
_L1:
        if(requestMethod == null)
            throw methodError("HTTP method annotation is required (e.g., @GET, @POST, etc.).", new Object[0]);
        if(!requestHasBody)
        {
            if(requestType == RequestType.MULTIPART)
                throw methodError("Multipart can only be specified on HTTP methods with request body (e.g., @POST).", new Object[0]);
            if(requestType == RequestType.FORM_URL_ENCODED)
                throw methodError("FormUrlEncoded can only be specified on HTTP methods with request body (e.g., @POST).", new Object[0]);
        }
        return;
        if(true) goto _L6; else goto _L5
_L5:
    }

    private void parseParameters()
    {
        Class aclass[] = method.getParameterTypes();
        Annotation aannotation[][] = method.getParameterAnnotations();
        int i = aannotation.length;
        if(!isSynchronous && !isObservable)
            i--;
        String as[] = new String[i];
        requestParamNames = as;
        ParamUsage aparamusage[] = new ParamUsage[i];
        requestParamUsage = aparamusage;
        boolean flag = false;
        boolean flag1 = false;
        boolean flag2 = false;
        for(int j = 0; j < i; j++)
        {
            Class class1 = aclass[j];
            Annotation aannotation1[] = aannotation[j];
            if(aannotation1 != null)
            {
                int k = aannotation1.length;
                int l = 0;
                while(l < k) 
                {
                    Annotation annotation = aannotation1[l];
                    Class class2 = annotation.annotationType();
                    if(class2 == retrofit/http/Path)
                    {
                        String s4 = ((Path)annotation).value();
                        validatePathName(j, s4);
                        as[j] = s4;
                        aparamusage[j] = ParamUsage.PATH;
                    } else
                    if(class2 == retrofit/http/EncodedPath)
                    {
                        String s3 = ((EncodedPath)annotation).value();
                        validatePathName(j, s3);
                        as[j] = s3;
                        aparamusage[j] = ParamUsage.ENCODED_PATH;
                    } else
                    if(class2 == retrofit/http/Query)
                    {
                        as[j] = ((Query)annotation).value();
                        aparamusage[j] = ParamUsage.QUERY;
                    } else
                    if(class2 == retrofit/http/EncodedQuery)
                    {
                        as[j] = ((EncodedQuery)annotation).value();
                        aparamusage[j] = ParamUsage.ENCODED_QUERY;
                    } else
                    if(class2 == retrofit/http/QueryMap)
                    {
                        if(!java/util/Map.isAssignableFrom(class1))
                            throw parameterError(j, "@QueryMap parameter type must be Map.", new Object[0]);
                        aparamusage[j] = ParamUsage.QUERY_MAP;
                    } else
                    if(class2 == retrofit/http/EncodedQueryMap)
                    {
                        if(!java/util/Map.isAssignableFrom(class1))
                            throw parameterError(j, "@EncodedQueryMap parameter type must be Map.", new Object[0]);
                        aparamusage[j] = ParamUsage.ENCODED_QUERY_MAP;
                    } else
                    if(class2 == retrofit/http/Header)
                    {
                        String s2 = ((Header)annotation).value();
                        if(class1 != java/lang/String)
                        {
                            Object aobj[] = new Object[1];
                            aobj[0] = class1.getSimpleName();
                            throw parameterError(j, "@Header parameter type must be String. Found: %s.", aobj);
                        }
                        as[j] = s2;
                        aparamusage[j] = ParamUsage.HEADER;
                        if("Content-Type".equalsIgnoreCase(s2))
                            hasContentTypeHeader = true;
                    } else
                    if(class2 == retrofit/http/Field)
                    {
                        if(requestType != RequestType.FORM_URL_ENCODED)
                            throw parameterError(j, "@Field parameters can only be used with form encoding.", new Object[0]);
                        String s1 = ((Field)annotation).value();
                        flag = true;
                        as[j] = s1;
                        aparamusage[j] = ParamUsage.FIELD;
                    } else
                    if(class2 == retrofit/http/FieldMap)
                    {
                        if(requestType != RequestType.FORM_URL_ENCODED)
                            throw parameterError(j, "@FieldMap parameters can only be used with form encoding.", new Object[0]);
                        if(!java/util/Map.isAssignableFrom(class1))
                            throw parameterError(j, "@FieldMap parameter type must be Map.", new Object[0]);
                        flag = true;
                        aparamusage[j] = ParamUsage.FIELD_MAP;
                    } else
                    if(class2 == retrofit/http/Part)
                    {
                        if(requestType != RequestType.MULTIPART)
                            throw parameterError(j, "@Part parameters can only be used with multipart encoding.", new Object[0]);
                        String s = ((Part)annotation).value();
                        flag1 = true;
                        as[j] = s;
                        aparamusage[j] = ParamUsage.PART;
                    } else
                    if(class2 == retrofit/http/PartMap)
                    {
                        if(requestType != RequestType.MULTIPART)
                            throw parameterError(j, "@PartMap parameters can only be used with multipart encoding.", new Object[0]);
                        if(!java/util/Map.isAssignableFrom(class1))
                            throw parameterError(j, "@PartMap parameter type must be Map.", new Object[0]);
                        flag1 = true;
                        aparamusage[j] = ParamUsage.PART_MAP;
                    } else
                    if(class2 == retrofit/http/Body)
                    {
                        if(requestType != RequestType.SIMPLE)
                            throw parameterError(j, "@Body parameters cannot be used with form or multi-part encoding.", new Object[0]);
                        if(flag2)
                            throw methodError("Multiple @Body method annotations found.", new Object[0]);
                        flag2 = true;
                        aparamusage[j] = ParamUsage.BODY;
                    }
                    l++;
                }
            }
            if(aparamusage[j] == null)
                throw parameterError(j, "No Retrofit annotation found.", new Object[0]);
        }

        if(requestType == RequestType.SIMPLE && !requestHasBody && flag2)
            throw methodError("Non-body HTTP method cannot contain @Body or @TypedOutput.", new Object[0]);
        if(requestType == RequestType.FORM_URL_ENCODED && !flag)
            throw methodError("Form-encoded method must contain at least one @Field.", new Object[0]);
        if(requestType == RequestType.MULTIPART && !flag1)
            throw methodError("Multipart method must contain at least one @Part.", new Object[0]);
        else
            return;
    }

    private void parsePath(String s)
    {
        if(s == null || s.length() == 0 || s.charAt(0) != '/')
            throw methodError("URL path \"%s\" must start with '/'.", new Object[] {
                s
            });
        String s1 = s;
        int i = s.indexOf('?');
        String s2 = null;
        if(i != -1)
        {
            int j = -1 + s.length();
            s2 = null;
            if(i < j)
            {
                s1 = s.substring(0, i);
                s2 = s.substring(i + 1);
                if(PARAM_URL_REGEX.matcher(s2).find())
                    throw methodError("URL query string \"%s\" must not have replace block.", new Object[] {
                        s2
                    });
            }
        }
        Set set = parsePathParameters(s);
        requestUrl = s1;
        requestUrlParamNames = set;
        requestQuery = s2;
    }

    static Set parsePathParameters(String s)
    {
        Matcher matcher = PARAM_URL_REGEX.matcher(s);
        LinkedHashSet linkedhashset = new LinkedHashSet();
        for(; matcher.find(); linkedhashset.add(matcher.group(1)));
        return linkedhashset;
    }

    private ResponseType parseResponseType()
    {
        boolean flag = true;
        Type type = method.getGenericReturnType();
        Type atype[] = method.getGenericParameterTypes();
        int i = atype.length;
        Class class1 = null;
        Type type1 = null;
        if(i > 0)
        {
            Type type3 = atype[-1 + atype.length];
            type1 = type3;
            if(type3 instanceof ParameterizedType)
                type3 = ((ParameterizedType)type3).getRawType();
            boolean flag2 = type3 instanceof Class;
            class1 = null;
            if(flag2)
                class1 = (Class)type3;
        }
        boolean flag1;
        if(type != Void.TYPE)
            flag1 = flag;
        else
            flag1 = false;
        if(class1 == null || !retrofit/Callback.isAssignableFrom(class1))
            flag = false;
        if(flag1 && flag)
            throw methodError("Must have return type or Callback as last argument, not both.", new Object[0]);
        if(!flag1 && !flag)
            throw methodError("Must have either a return type or Callback as last argument.", new Object[0]);
        if(flag1)
        {
            if(Platform.HAS_RX_JAVA)
            {
                Class class2 = Types.getRawType(type);
                if(RxSupport.isObservable(class2))
                {
                    responseObjectType = getParameterUpperBound((ParameterizedType)RxSupport.getObservableType(type, class2));
                    return ResponseType.OBSERVABLE;
                }
            }
            responseObjectType = type;
            return ResponseType.OBJECT;
        }
        Type type2 = Types.getSupertype(type1, Types.getRawType(type1), retrofit/Callback);
        if(type2 instanceof ParameterizedType)
        {
            responseObjectType = getParameterUpperBound((ParameterizedType)type2);
            return ResponseType.VOID;
        } else
        {
            throw methodError("Last parameter must be of type Callback<X> or Callback<? super X>.", new Object[0]);
        }
    }

    private void validatePathName(int i, String s)
    {
        if(!PARAM_NAME_REGEX.matcher(s).matches())
        {
            Object aobj1[] = new Object[2];
            aobj1[0] = PARAM_URL_REGEX.pattern();
            aobj1[1] = s;
            throw parameterError(i, "@Path parameter name must match %s. Found: %s", aobj1);
        }
        if(!requestUrlParamNames.contains(s))
        {
            Object aobj[] = new Object[2];
            aobj[0] = requestUrl;
            aobj[1] = s;
            throw parameterError(i, "URL \"%s\" does not contain \"{%s}\".", aobj);
        } else
        {
            return;
        }
    }

    void init()
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag = loaded;
        if(!flag) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        parseMethodAnnotations();
        parseParameters();
        loaded = true;
        if(true) goto _L1; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    List parseHeaders(String as[])
    {
        ArrayList arraylist = new ArrayList();
        int i = as.length;
        for(int j = 0; j < i; j++)
        {
            String s = as[j];
            int k = s.indexOf(':');
            if(k == -1 || k == 0 || k == -1 + s.length())
                throw methodError("@Headers value must be in the form \"Name: Value\". Found: \"%s\"", new Object[] {
                    s
                });
            String s1 = s.substring(0, k);
            String s2 = s.substring(k + 1).trim();
            if("Content-Type".equalsIgnoreCase(s1))
                hasContentTypeHeader = true;
            arraylist.add(new retrofit.client.Header(s1, s2));
        }

        return arraylist;
    }

    private static final String PARAM = "[a-zA-Z][a-zA-Z0-9_-]*";
    private static final Pattern PARAM_NAME_REGEX = Pattern.compile("[a-zA-Z][a-zA-Z0-9_-]*");
    private static final Pattern PARAM_URL_REGEX = Pattern.compile("\\{([a-zA-Z][a-zA-Z0-9_-]*)\\}");
    boolean hasContentTypeHeader;
    List headers;
    final boolean isObservable;
    final boolean isSynchronous;
    boolean loaded;
    final Method method;
    boolean requestHasBody;
    String requestMethod;
    String requestParamNames[];
    ParamUsage requestParamUsage[];
    String requestQuery;
    RequestType requestType;
    String requestUrl;
    Set requestUrlParamNames;
    Type responseObjectType;
    final ResponseType responseType = parseResponseType();

}
