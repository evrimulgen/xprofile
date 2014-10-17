// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.squareup.okhttp.internal.http;


// Referenced classes of package com.squareup.okhttp.internal.http:
//            Request

public final class Failure
{
    public static class Builder
    {

        public Failure build()
        {
            return new Failure(this);
        }

        public Builder exception(Throwable throwable)
        {
            exception = throwable;
            return this;
        }

        public Builder request(Request request1)
        {
            request = request1;
            return this;
        }

        private Throwable exception;
        private Request request;



        public Builder()
        {
        }
    }


    private Failure(Builder builder)
    {
        request = builder.request;
        exception = builder.exception;
    }


    public Throwable exception()
    {
        return exception;
    }

    public Request request()
    {
        return request;
    }

    private final Throwable exception;
    private final Request request;
}
