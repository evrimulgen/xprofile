// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package retrofit;

import java.io.IOException;
import java.lang.reflect.Type;
import retrofit.client.Response;
import retrofit.converter.ConversionException;
import retrofit.converter.Converter;

public class RetrofitError extends RuntimeException
{

    RetrofitError(String s, Response response1, Converter converter1, Type type, boolean flag, Throwable throwable)
    {
        super(throwable);
        url = s;
        response = response1;
        converter = converter1;
        successType = type;
        networkError = flag;
    }

    public static RetrofitError conversionError(String s, Response response1, Converter converter1, Type type, ConversionException conversionexception)
    {
        return new RetrofitError(s, response1, converter1, type, false, conversionexception);
    }

    public static RetrofitError httpError(String s, Response response1, Converter converter1, Type type)
    {
        return new RetrofitError(s, response1, converter1, type, false, null);
    }

    public static RetrofitError networkError(String s, IOException ioexception)
    {
        return new RetrofitError(s, null, null, null, true, ioexception);
    }

    public static RetrofitError unexpectedError(String s, Throwable throwable)
    {
        return new RetrofitError(s, null, null, null, false, throwable);
    }

    public Object getBody()
    {
        retrofit.mime.TypedInput typedinput = response.getBody();
        if(typedinput == null)
            return null;
        Object obj;
        try
        {
            obj = converter.fromBody(typedinput, successType);
        }
        catch(ConversionException conversionexception)
        {
            throw new RuntimeException(conversionexception);
        }
        return obj;
    }

    public Object getBodyAs(Type type)
    {
        retrofit.mime.TypedInput typedinput = response.getBody();
        if(typedinput == null)
            return null;
        Object obj;
        try
        {
            obj = converter.fromBody(typedinput, type);
        }
        catch(ConversionException conversionexception)
        {
            throw new RuntimeException(conversionexception);
        }
        return obj;
    }

    public Response getResponse()
    {
        return response;
    }

    public String getUrl()
    {
        return url;
    }

    public boolean isNetworkError()
    {
        return networkError;
    }

    private final Converter converter;
    private final boolean networkError;
    private final Response response;
    private final Type successType;
    private final String url;
}
