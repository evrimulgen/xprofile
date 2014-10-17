// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package retrofit;

import java.io.*;
import java.util.concurrent.Executor;
import retrofit.client.Request;
import retrofit.client.Response;
import retrofit.mime.TypedByteArray;
import retrofit.mime.TypedInput;
import retrofit.mime.TypedOutput;

final class Utils
{
    static class SynchronousExecutor
        implements Executor
    {

        public void execute(Runnable runnable)
        {
            runnable.run();
        }

        SynchronousExecutor()
        {
        }
    }


    private Utils()
    {
    }

    static Request readBodyToBytesIfNecessary(Request request)
        throws IOException
    {
        TypedOutput typedoutput = request.getBody();
        if(typedoutput == null || (typedoutput instanceof TypedByteArray))
        {
            return request;
        } else
        {
            String s = typedoutput.mimeType();
            ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
            typedoutput.writeTo(bytearrayoutputstream);
            TypedByteArray typedbytearray = new TypedByteArray(s, bytearrayoutputstream.toByteArray());
            return new Request(request.getMethod(), request.getUrl(), request.getHeaders(), typedbytearray);
        }
    }

    static Response readBodyToBytesIfNecessary(Response response)
        throws IOException
    {
        String s;
        InputStream inputstream;
        TypedInput typedinput = response.getBody();
        if(typedinput == null || (typedinput instanceof TypedByteArray))
            return response;
        s = typedinput.mimeType();
        inputstream = typedinput.in();
        TypedByteArray typedbytearray = new TypedByteArray(s, streamToBytes(inputstream));
        Response response1 = replaceResponseBody(response, typedbytearray);
        Exception exception;
        if(inputstream != null)
            try
            {
                inputstream.close();
            }
            catch(IOException ioexception1) { }
        return response1;
        exception;
_L2:
        if(inputstream != null)
            try
            {
                inputstream.close();
            }
            catch(IOException ioexception) { }
        throw exception;
        exception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    static Response replaceResponseBody(Response response, TypedInput typedinput)
    {
        return new Response(response.getUrl(), response.getStatus(), response.getReason(), response.getHeaders(), typedinput);
    }

    static byte[] streamToBytes(InputStream inputstream)
        throws IOException
    {
        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
        if(inputstream != null)
        {
            byte abyte0[] = new byte[4096];
            do
            {
                int i = inputstream.read(abyte0);
                if(i == -1)
                    break;
                bytearrayoutputstream.write(abyte0, 0, i);
            } while(true);
        }
        return bytearrayoutputstream.toByteArray();
    }

    static void validateServiceClass(Class class1)
    {
        if(!class1.isInterface())
            throw new IllegalArgumentException("Only interface endpoint definitions are supported.");
        if(class1.getInterfaces().length > 0)
            throw new IllegalArgumentException("Interface definitions must not extend other interfaces.");
        else
            return;
    }

    private static final int BUFFER_SIZE = 4096;
}
