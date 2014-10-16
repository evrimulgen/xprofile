// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package retrofit;


public interface RequestInterceptor
{
    public static interface RequestFacade
    {

        public abstract void addEncodedPathParam(String s, String s1);

        public abstract void addEncodedQueryParam(String s, String s1);

        public abstract void addHeader(String s, String s1);

        public abstract void addPathParam(String s, String s1);

        public abstract void addQueryParam(String s, String s1);
    }


    public abstract void intercept(RequestFacade requestfacade);

    public static final RequestInterceptor NONE = new RequestInterceptor() {

        public void intercept(RequestFacade requestfacade)
        {
        }

    }
;

}
