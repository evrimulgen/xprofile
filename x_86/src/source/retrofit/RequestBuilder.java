// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package retrofit;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.net.URLEncoder;
import java.util.*;
import retrofit.client.Header;
import retrofit.client.Request;
import retrofit.converter.Converter;
import retrofit.mime.FormUrlEncodedTypedOutput;
import retrofit.mime.MultipartTypedOutput;
import retrofit.mime.TypedOutput;
import retrofit.mime.TypedString;

// Referenced classes of package retrofit:
//            RestMethodInfo

final class RequestBuilder
    implements RequestInterceptor.RequestFacade
{

    RequestBuilder(String s, RestMethodInfo restmethodinfo, Converter converter1)
    {
        apiUrl = s;
        converter = converter1;
        paramNames = restmethodinfo.requestParamNames;
        paramUsages = restmethodinfo.requestParamUsage;
        requestMethod = restmethodinfo.requestMethod;
        isSynchronous = restmethodinfo.isSynchronous;
        isObservable = restmethodinfo.isObservable;
        if(restmethodinfo.headers != null)
            headers = new ArrayList(restmethodinfo.headers);
        hasContentTypeHeader = restmethodinfo.hasContentTypeHeader;
        relativeUrl = restmethodinfo.requestUrl;
        String s1 = restmethodinfo.requestQuery;
        if(s1 != null)
            queryParams = (new StringBuilder()).append('?').append(s1);
        static class _cls1
        {

            static final int $SwitchMap$retrofit$RestMethodInfo$ParamUsage[];
            static final int $SwitchMap$retrofit$RestMethodInfo$RequestType[];

            static 
            {
                $SwitchMap$retrofit$RestMethodInfo$ParamUsage = new int[RestMethodInfo.ParamUsage.values().length];
                try
                {
                    $SwitchMap$retrofit$RestMethodInfo$ParamUsage[RestMethodInfo.ParamUsage.PATH.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$retrofit$RestMethodInfo$ParamUsage[RestMethodInfo.ParamUsage.ENCODED_PATH.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$retrofit$RestMethodInfo$ParamUsage[RestMethodInfo.ParamUsage.QUERY.ordinal()] = 3;
                }
                catch(NoSuchFieldError nosuchfielderror2) { }
                try
                {
                    $SwitchMap$retrofit$RestMethodInfo$ParamUsage[RestMethodInfo.ParamUsage.ENCODED_QUERY.ordinal()] = 4;
                }
                catch(NoSuchFieldError nosuchfielderror3) { }
                try
                {
                    $SwitchMap$retrofit$RestMethodInfo$ParamUsage[RestMethodInfo.ParamUsage.QUERY_MAP.ordinal()] = 5;
                }
                catch(NoSuchFieldError nosuchfielderror4) { }
                try
                {
                    $SwitchMap$retrofit$RestMethodInfo$ParamUsage[RestMethodInfo.ParamUsage.ENCODED_QUERY_MAP.ordinal()] = 6;
                }
                catch(NoSuchFieldError nosuchfielderror5) { }
                try
                {
                    $SwitchMap$retrofit$RestMethodInfo$ParamUsage[RestMethodInfo.ParamUsage.HEADER.ordinal()] = 7;
                }
                catch(NoSuchFieldError nosuchfielderror6) { }
                try
                {
                    $SwitchMap$retrofit$RestMethodInfo$ParamUsage[RestMethodInfo.ParamUsage.FIELD.ordinal()] = 8;
                }
                catch(NoSuchFieldError nosuchfielderror7) { }
                try
                {
                    $SwitchMap$retrofit$RestMethodInfo$ParamUsage[RestMethodInfo.ParamUsage.FIELD_MAP.ordinal()] = 9;
                }
                catch(NoSuchFieldError nosuchfielderror8) { }
                try
                {
                    $SwitchMap$retrofit$RestMethodInfo$ParamUsage[RestMethodInfo.ParamUsage.PART.ordinal()] = 10;
                }
                catch(NoSuchFieldError nosuchfielderror9) { }
                try
                {
                    $SwitchMap$retrofit$RestMethodInfo$ParamUsage[RestMethodInfo.ParamUsage.PART_MAP.ordinal()] = 11;
                }
                catch(NoSuchFieldError nosuchfielderror10) { }
                try
                {
                    $SwitchMap$retrofit$RestMethodInfo$ParamUsage[RestMethodInfo.ParamUsage.BODY.ordinal()] = 12;
                }
                catch(NoSuchFieldError nosuchfielderror11) { }
                $SwitchMap$retrofit$RestMethodInfo$RequestType = new int[RestMethodInfo.RequestType.values().length];
                try
                {
                    $SwitchMap$retrofit$RestMethodInfo$RequestType[RestMethodInfo.RequestType.FORM_URL_ENCODED.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror12) { }
                try
                {
                    $SwitchMap$retrofit$RestMethodInfo$RequestType[RestMethodInfo.RequestType.MULTIPART.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror13) { }
                try
                {
                    $SwitchMap$retrofit$RestMethodInfo$RequestType[RestMethodInfo.RequestType.SIMPLE.ordinal()] = 3;
                }
                catch(NoSuchFieldError nosuchfielderror14)
                {
                    return;
                }
            }
        }

        switch(_cls1..SwitchMap.retrofit.RestMethodInfo.RequestType[restmethodinfo.requestType.ordinal()])
        {
        default:
            throw new IllegalArgumentException((new StringBuilder()).append("Unknown request type: ").append(restmethodinfo.requestType).toString());

        case 1: // '\001'
            formBody = new FormUrlEncodedTypedOutput();
            multipartBody = null;
            body = formBody;
            return;

        case 2: // '\002'
            formBody = null;
            multipartBody = new MultipartTypedOutput();
            body = multipartBody;
            return;

        case 3: // '\003'
            formBody = null;
            break;
        }
    }

    private void addPathParam(String s, String s1, boolean flag)
    {
        if(s == null)
            throw new IllegalArgumentException("Path replacement name must not be null.");
        if(s1 == null)
            throw new IllegalArgumentException((new StringBuilder()).append("Path replacement \"").append(s).append("\" value must not be null.").toString());
        if(flag)
            try
            {
                String s2 = URLEncoder.encode(String.valueOf(s1), "UTF-8").replace("+", "%20");
                relativeUrl = relativeUrl.replace((new StringBuilder()).append("{").append(s).append("}").toString(), s2);
                return;
            }
            catch(UnsupportedEncodingException unsupportedencodingexception)
            {
                throw new RuntimeException((new StringBuilder()).append("Unable to convert path parameter \"").append(s).append("\" value to UTF-8:").append(s1).toString(), unsupportedencodingexception);
            }
        relativeUrl = relativeUrl.replace((new StringBuilder()).append("{").append(s).append("}").toString(), String.valueOf(s1));
        return;
    }

    private void addQueryParam(String s, String s1, boolean flag)
    {
        if(s == null)
            throw new IllegalArgumentException("Query param name must not be null.");
        if(s1 == null)
            throw new IllegalArgumentException((new StringBuilder()).append("Query param \"").append(s).append("\" value must not be null.").toString());
        if(!flag)
            break MISSING_BLOCK_LABEL_64;
        s1 = URLEncoder.encode(String.valueOf(s1), "UTF-8");
        StringBuilder stringbuilder = queryParams;
        if(stringbuilder != null)
            break MISSING_BLOCK_LABEL_90;
        stringbuilder = new StringBuilder();
        queryParams = stringbuilder;
        char c;
        if(stringbuilder.length() > 0)
            c = '&';
        else
            c = '?';
        try
        {
            stringbuilder.append(c);
            stringbuilder.append(s).append('=').append(s1);
            return;
        }
        catch(UnsupportedEncodingException unsupportedencodingexception)
        {
            throw new RuntimeException((new StringBuilder()).append("Unable to convert query parameter \"").append(s).append("\" value to UTF-8: ").append(s1).toString(), unsupportedencodingexception);
        }
    }

    public void addEncodedPathParam(String s, String s1)
    {
        addPathParam(s, s1, false);
    }

    public void addEncodedQueryParam(String s, String s1)
    {
        addQueryParam(s, s1, false);
    }

    public void addHeader(String s, String s1)
    {
        if(s == null)
            throw new IllegalArgumentException("Header name must not be null.");
        Object obj = headers;
        if(obj == null)
        {
            obj = new ArrayList(2);
            headers = ((List) (obj));
        }
        ((List) (obj)).add(new Header(s, s1));
        if("Content-Type".equalsIgnoreCase(s))
            hasContentTypeHeader = true;
    }

    public void addPathParam(String s, String s1)
    {
        addPathParam(s, s1, true);
    }

    public void addQueryParam(String s, String s1)
    {
        addQueryParam(s, s1, true);
    }

    Request build()
        throws UnsupportedEncodingException
    {
        if(multipartBody != null && multipartBody.getPartCount() == 0)
            throw new IllegalStateException("Multipart requests must contain at least one part.");
        String s = apiUrl;
        StringBuilder stringbuilder = new StringBuilder(s);
        if(s.endsWith("/"))
            stringbuilder.deleteCharAt(-1 + stringbuilder.length());
        stringbuilder.append(relativeUrl);
        StringBuilder stringbuilder1 = queryParams;
        if(stringbuilder1 != null)
            stringbuilder.append(stringbuilder1);
        TypedOutput typedoutput = body;
        if(typedoutput != null)
        {
            if(!hasContentTypeHeader)
                addHeader("Content-Type", typedoutput.mimeType());
            long l = typedoutput.length();
            if(l != -1L)
                addHeader("Content-Length", String.valueOf(l));
        }
        return new Request(requestMethod, stringbuilder.toString(), headers, typedoutput);
    }

    void setArguments(Object aobj[])
    {
        if(aobj != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        int i;
        int j;
        i = aobj.length;
        if(!isSynchronous && !isObservable)
            i--;
        j = 0;
_L15:
        if(j >= i) goto _L1; else goto _L3
_L3:
        String s;
        Object obj;
        RestMethodInfo.ParamUsage paramusage;
        s = paramNames[j];
        obj = aobj[j];
        paramusage = paramUsages[j];
        _cls1..SwitchMap.retrofit.RestMethodInfo.ParamUsage[paramusage.ordinal()];
        JVM INSTR tableswitch 1 12: default 124
    //                   1 153
    //                   2 209
    //                   3 262
    //                   4 262
    //                   5 421
    //                   6 421
    //                   7 520
    //                   8 539
    //                   9 684
    //                   10 767
    //                   11 860
    //                   12 1018;
           goto _L4 _L5 _L6 _L7 _L7 _L8 _L8 _L9 _L10 _L11 _L12 _L13 _L14
_L14:
        break MISSING_BLOCK_LABEL_1018;
_L4:
        throw new IllegalArgumentException((new StringBuilder()).append("Unknown parameter usage: ").append(paramusage).toString());
_L5:
        if(obj == null)
            throw new IllegalArgumentException((new StringBuilder()).append("Path parameter \"").append(s).append("\" value must not be null.").toString());
        addPathParam(s, obj.toString());
_L16:
        j++;
          goto _L15
_L6:
        if(obj == null)
            throw new IllegalArgumentException((new StringBuilder()).append("Path parameter \"").append(s).append("\" value must not be null.").toString());
        addEncodedPathParam(s, obj.toString());
          goto _L16
_L7:
        if(obj != null)
        {
            boolean flag1;
            if(paramusage == RestMethodInfo.ParamUsage.QUERY)
                flag1 = true;
            else
                flag1 = false;
            if(obj instanceof Iterable)
            {
                Iterator iterator4 = ((Iterable)obj).iterator();
                while(iterator4.hasNext()) 
                {
                    Object obj7 = iterator4.next();
                    if(obj7 != null)
                        addQueryParam(s, obj7.toString(), flag1);
                }
            } else
            if(obj.getClass().isArray())
            {
                int i1 = 0;
                int j1 = Array.getLength(obj);
                while(i1 < j1) 
                {
                    Object obj6 = Array.get(obj, i1);
                    if(obj6 != null)
                        addQueryParam(s, obj6.toString(), flag1);
                    i1++;
                }
            } else
            {
                addQueryParam(s, obj.toString(), flag1);
            }
        }
          goto _L16
_L8:
        if(obj != null)
        {
            boolean flag;
            Iterator iterator3;
            if(paramusage == RestMethodInfo.ParamUsage.QUERY_MAP)
                flag = true;
            else
                flag = false;
            iterator3 = ((Map)obj).entrySet().iterator();
            while(iterator3.hasNext()) 
            {
                java.util.Map.Entry entry2 = (java.util.Map.Entry)iterator3.next();
                Object obj5 = entry2.getValue();
                if(obj5 != null)
                    addQueryParam(entry2.getKey().toString(), obj5.toString(), flag);
            }
        }
          goto _L16
_L9:
        if(obj != null)
            addHeader(s, obj.toString());
          goto _L16
_L10:
        if(obj != null)
            if(obj instanceof Iterable)
            {
                Iterator iterator2 = ((Iterable)obj).iterator();
                while(iterator2.hasNext()) 
                {
                    Object obj4 = iterator2.next();
                    if(obj4 != null)
                        formBody.addField(s, obj4.toString());
                }
            } else
            if(obj.getClass().isArray())
            {
                int k = 0;
                int l = Array.getLength(obj);
                while(k < l) 
                {
                    Object obj3 = Array.get(obj, k);
                    if(obj3 != null)
                        formBody.addField(s, obj3.toString());
                    k++;
                }
            } else
            {
                formBody.addField(s, obj.toString());
            }
          goto _L16
_L11:
        if(obj != null)
        {
            Iterator iterator1 = ((Map)obj).entrySet().iterator();
            while(iterator1.hasNext()) 
            {
                java.util.Map.Entry entry1 = (java.util.Map.Entry)iterator1.next();
                Object obj2 = entry1.getValue();
                if(obj2 != null)
                    formBody.addField(entry1.getKey().toString(), obj2.toString());
            }
        }
          goto _L16
_L12:
        if(obj != null)
            if(obj instanceof TypedOutput)
                multipartBody.addPart(s, (TypedOutput)obj);
            else
            if(obj instanceof String)
            {
                MultipartTypedOutput multiparttypedoutput1 = multipartBody;
                TypedString typedstring1 = new TypedString((String)obj);
                multiparttypedoutput1.addPart(s, typedstring1);
            } else
            {
                multipartBody.addPart(s, converter.toBody(obj));
            }
          goto _L16
_L13:
        if(obj != null)
        {
            Iterator iterator = ((Map)obj).entrySet().iterator();
            while(iterator.hasNext()) 
            {
                java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
                String s1 = entry.getKey().toString();
                Object obj1 = entry.getValue();
                if(obj1 != null)
                    if(obj1 instanceof TypedOutput)
                        multipartBody.addPart(s1, (TypedOutput)obj1);
                    else
                    if(obj1 instanceof String)
                    {
                        MultipartTypedOutput multiparttypedoutput = multipartBody;
                        TypedString typedstring = new TypedString((String)obj1);
                        multiparttypedoutput.addPart(s1, typedstring);
                    } else
                    {
                        multipartBody.addPart(s1, converter.toBody(obj1));
                    }
            }
        }
          goto _L16
        if(obj == null)
            throw new IllegalArgumentException("Body parameter value must not be null.");
        if(obj instanceof TypedOutput)
            body = (TypedOutput)obj;
        else
            body = converter.toBody(obj);
          goto _L16
    }

    private final String apiUrl;
    private TypedOutput body;
    private final Converter converter;
    private final FormUrlEncodedTypedOutput formBody;
    private boolean hasContentTypeHeader;
    private List headers;
    private final boolean isObservable;
    private final boolean isSynchronous;
    private final MultipartTypedOutput multipartBody = null;
    private final String paramNames[];
    private final RestMethodInfo.ParamUsage paramUsages[];
    private StringBuilder queryParams;
    private String relativeUrl;
    private final String requestMethod;
}
