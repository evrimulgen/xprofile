// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package retrofit.client;

import com.newrelic.agent.android.instrumentation.HttpInstrumentation;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import retrofit.mime.TypedInput;
import retrofit.mime.TypedOutput;

// Referenced classes of package retrofit.client:
//            Client, Request, Header, Response

public class UrlConnectionClient
    implements Client
{
    private static class TypedInputStream
        implements TypedInput
    {

        public InputStream in()
            throws IOException
        {
            return stream;
        }

        public long length()
        {
            return length;
        }

        public String mimeType()
        {
            return mimeType;
        }

        private final long length;
        private final String mimeType;
        private final InputStream stream;

        private TypedInputStream(String s, long l, InputStream inputstream)
        {
            mimeType = s;
            length = l;
            stream = inputstream;
        }

        TypedInputStream(String s, long l, InputStream inputstream, _cls1 _pcls1)
        {
            this(s, l, inputstream);
        }
    }


    public UrlConnectionClient()
    {
    }

    public Response execute(Request request)
        throws IOException
    {
        HttpURLConnection httpurlconnection = openConnection(request);
        prepareRequest(httpurlconnection, request);
        return readResponse(httpurlconnection);
    }

    protected HttpURLConnection openConnection(Request request)
        throws IOException
    {
        HttpURLConnection httpurlconnection = (HttpURLConnection)HttpInstrumentation.openConnection((new URL(request.getUrl())).openConnection());
        httpurlconnection.setConnectTimeout(15000);
        httpurlconnection.setReadTimeout(20000);
        return httpurlconnection;
    }

    void prepareRequest(HttpURLConnection httpurlconnection, Request request)
        throws IOException
    {
        httpurlconnection.setRequestMethod(request.getMethod());
        httpurlconnection.setDoInput(true);
        Header header;
        for(Iterator iterator = request.getHeaders().iterator(); iterator.hasNext(); httpurlconnection.addRequestProperty(header.getName(), header.getValue()))
            header = (Header)iterator.next();

        TypedOutput typedoutput = request.getBody();
        if(typedoutput != null)
        {
            httpurlconnection.setDoOutput(true);
            long l = typedoutput.length();
            if(l != -1L)
                httpurlconnection.setFixedLengthStreamingMode((int)l);
            else
                httpurlconnection.setChunkedStreamingMode(4096);
            typedoutput.writeTo(httpurlconnection.getOutputStream());
        }
    }

    Response readResponse(HttpURLConnection httpurlconnection)
        throws IOException
    {
        int i = httpurlconnection.getResponseCode();
        String s = httpurlconnection.getResponseMessage();
        if(s == null)
            s = "";
        ArrayList arraylist = new ArrayList();
        for(Iterator iterator = httpurlconnection.getHeaderFields().entrySet().iterator(); iterator.hasNext();)
        {
            java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
            String s2 = (String)entry.getKey();
            Iterator iterator1 = ((List)entry.getValue()).iterator();
            while(iterator1.hasNext()) 
                arraylist.add(new Header(s2, (String)iterator1.next()));
        }

        String s1 = httpurlconnection.getContentType();
        int j = httpurlconnection.getContentLength();
        InputStream inputstream;
        TypedInputStream typedinputstream;
        if(i >= 400)
            inputstream = httpurlconnection.getErrorStream();
        else
            inputstream = httpurlconnection.getInputStream();
        typedinputstream = new TypedInputStream(s1, j, inputstream);
        return new Response(httpurlconnection.getURL().toString(), i, s, arraylist, typedinputstream);
    }

    private static final int CHUNK_SIZE = 4096;
}
