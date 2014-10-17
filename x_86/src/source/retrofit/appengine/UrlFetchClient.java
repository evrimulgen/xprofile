// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package retrofit.appengine;

import com.google.appengine.api.urlfetch.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import retrofit.client.*;
import retrofit.mime.TypedByteArray;
import retrofit.mime.TypedOutput;

public class UrlFetchClient
    implements Client
{

    public UrlFetchClient()
    {
        this(URLFetchServiceFactory.getURLFetchService());
    }

    public UrlFetchClient(URLFetchService urlfetchservice)
    {
        urlFetchService = urlfetchservice;
    }

    static HTTPRequest createRequest(Request request)
        throws IOException
    {
        HTTPMethod httpmethod = getHttpMethod(request.getMethod());
        HTTPRequest httprequest = new HTTPRequest(new URL(request.getUrl()), httpmethod);
        Header header;
        for(Iterator iterator = request.getHeaders().iterator(); iterator.hasNext(); httprequest.addHeader(new HTTPHeader(header.getName(), header.getValue())))
            header = (Header)iterator.next();

        TypedOutput typedoutput = request.getBody();
        if(typedoutput != null)
        {
            ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
            typedoutput.writeTo(bytearrayoutputstream);
            httprequest.setPayload(bytearrayoutputstream.toByteArray());
        }
        return httprequest;
    }

    private static HTTPMethod getHttpMethod(String s)
    {
        if("GET".equals(s))
            return HTTPMethod.GET;
        if("POST".equals(s))
            return HTTPMethod.POST;
        if("PATCH".equals(s))
            return HTTPMethod.PATCH;
        if("PUT".equals(s))
            return HTTPMethod.PUT;
        if("DELETE".equals(s))
            return HTTPMethod.DELETE;
        if("HEAD".equals(s))
            return HTTPMethod.HEAD;
        else
            throw new IllegalStateException((new StringBuilder()).append("Illegal HTTP method: ").append(s).toString());
    }

    static Response parseResponse(HTTPResponse httpresponse)
    {
        String s = httpresponse.getFinalUrl().toString();
        int i = httpresponse.getResponseCode();
        List list = httpresponse.getHeaders();
        ArrayList arraylist = new ArrayList(list.size());
        String s1 = "application/octet-stream";
        String s2;
        String s3;
        for(Iterator iterator = list.iterator(); iterator.hasNext(); arraylist.add(new Header(s2, s3)))
        {
            HTTPHeader httpheader = (HTTPHeader)iterator.next();
            s2 = httpheader.getName();
            s3 = httpheader.getValue();
            if("Content-Type".equalsIgnoreCase(s2))
                s1 = s3;
        }

        byte abyte0[] = httpresponse.getContent();
        TypedByteArray typedbytearray = null;
        if(abyte0 != null)
            typedbytearray = new TypedByteArray(s1, abyte0);
        return new Response(s, i, "", arraylist, typedbytearray);
    }

    protected HTTPResponse execute(URLFetchService urlfetchservice, HTTPRequest httprequest)
        throws IOException
    {
        return urlfetchservice.fetch(httprequest);
    }

    public Response execute(Request request)
        throws IOException
    {
        HTTPRequest httprequest = createRequest(request);
        return parseResponse(execute(urlFetchService, httprequest));
    }

    private final URLFetchService urlFetchService;
}
