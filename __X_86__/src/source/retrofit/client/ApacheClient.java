// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package retrofit.client;

import com.newrelic.agent.android.instrumentation.HttpInstrumentation;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.AbstractHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;
import retrofit.mime.TypedByteArray;
import retrofit.mime.TypedOutput;

// Referenced classes of package retrofit.client:
//            Client, Header, Response, Request

public class ApacheClient
    implements Client
{
    private static class GenericHttpRequest extends HttpEntityEnclosingRequestBase
    {

        public String getMethod()
        {
            return method;
        }

        private final String method;

        GenericHttpRequest(Request request)
        {
            method = request.getMethod();
            setURI(URI.create(request.getUrl()));
            retrofit.client.Header header;
            for(Iterator iterator = request.getHeaders().iterator(); iterator.hasNext(); addHeader(new BasicHeader(header.getName(), header.getValue())))
                header = (retrofit.client.Header)iterator.next();

            TypedOutput typedoutput = request.getBody();
            if(typedoutput != null)
                setEntity(new TypedOutputEntity(typedoutput));
        }
    }

    static class TypedOutputEntity extends AbstractHttpEntity
    {

        public InputStream getContent()
            throws IOException
        {
            ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
            typedOutput.writeTo(bytearrayoutputstream);
            return new ByteArrayInputStream(bytearrayoutputstream.toByteArray());
        }

        public long getContentLength()
        {
            return typedOutput.length();
        }

        public boolean isRepeatable()
        {
            return true;
        }

        public boolean isStreaming()
        {
            return false;
        }

        public void writeTo(OutputStream outputstream)
            throws IOException
        {
            typedOutput.writeTo(outputstream);
        }

        final TypedOutput typedOutput;

        TypedOutputEntity(TypedOutput typedoutput)
        {
            typedOutput = typedoutput;
        }
    }


    public ApacheClient()
    {
        this(createDefaultClient());
    }

    public ApacheClient(HttpClient httpclient)
    {
        client = httpclient;
    }

    private static HttpClient createDefaultClient()
    {
        BasicHttpParams basichttpparams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(basichttpparams, 15000);
        HttpConnectionParams.setSoTimeout(basichttpparams, 20000);
        return new DefaultHttpClient(basichttpparams);
    }

    static HttpUriRequest createRequest(Request request)
    {
        return new GenericHttpRequest(request);
    }

    static Response parseResponse(String s, HttpResponse httpresponse)
        throws IOException
    {
        StatusLine statusline = httpresponse.getStatusLine();
        int i = statusline.getStatusCode();
        String s1 = statusline.getReasonPhrase();
        ArrayList arraylist = new ArrayList();
        String s2 = "application/octet-stream";
        Header aheader[] = httpresponse.getAllHeaders();
        int j = aheader.length;
        for(int k = 0; k < j; k++)
        {
            Header header = aheader[k];
            String s3 = header.getName();
            String s4 = header.getValue();
            if("Content-Type".equalsIgnoreCase(s3))
                s2 = s4;
            arraylist.add(new retrofit.client.Header(s3, s4));
        }

        org.apache.http.HttpEntity httpentity = httpresponse.getEntity();
        TypedByteArray typedbytearray = null;
        if(httpentity != null)
            typedbytearray = new TypedByteArray(s2, EntityUtils.toByteArray(httpentity));
        return new Response(s, i, s1, arraylist, typedbytearray);
    }

    protected HttpResponse execute(HttpClient httpclient, HttpUriRequest httpurirequest)
        throws IOException
    {
        if(!(httpclient instanceof HttpClient))
            return httpclient.execute(httpurirequest);
        else
            return HttpInstrumentation.execute((HttpClient)httpclient, httpurirequest);
    }

    public Response execute(Request request)
        throws IOException
    {
        HttpUriRequest httpurirequest = createRequest(request);
        HttpResponse httpresponse = execute(client, httpurirequest);
        return parseResponse(request.getUrl(), httpresponse);
    }

    private final HttpClient client;
}
