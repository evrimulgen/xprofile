// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package retrofit;


public interface Profiler
{
    public static final class RequestInformation
    {

        public String getBaseUrl()
        {
            return baseUrl;
        }

        public long getContentLength()
        {
            return contentLength;
        }

        public String getContentType()
        {
            return contentType;
        }

        public String getMethod()
        {
            return method;
        }

        public String getRelativePath()
        {
            return relativePath;
        }

        private final String baseUrl;
        private final long contentLength;
        private final String contentType;
        private final String method;
        private final String relativePath;

        public RequestInformation(String s, String s1, String s2, long l, String s3)
        {
            method = s;
            baseUrl = s1;
            relativePath = s2;
            contentLength = l;
            contentType = s3;
        }
    }


    public abstract void afterCall(RequestInformation requestinformation, long l, int i, Object obj);

    public abstract Object beforeCall();
}
