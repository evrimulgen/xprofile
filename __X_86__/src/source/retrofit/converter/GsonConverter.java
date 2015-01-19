// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package retrofit.converter;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.newrelic.agent.android.instrumentation.GsonInstrumentation;
import java.io.*;
import java.lang.reflect.Type;
import retrofit.mime.*;

// Referenced classes of package retrofit.converter:
//            Converter, ConversionException

public class GsonConverter
    implements Converter
{
    private static class JsonTypedOutput
        implements TypedOutput
    {

        public String fileName()
        {
            return null;
        }

        public long length()
        {
            return (long)jsonBytes.length;
        }

        public String mimeType()
        {
            return mimeType;
        }

        public void writeTo(OutputStream outputstream)
            throws IOException
        {
            outputstream.write(jsonBytes);
        }

        private final byte jsonBytes[];
        private final String mimeType;

        JsonTypedOutput(byte abyte0[], String s)
        {
            jsonBytes = abyte0;
            mimeType = (new StringBuilder()).append("application/json; charset=").append(s).toString();
        }
    }


    public GsonConverter(Gson gson1)
    {
        this(gson1, "UTF-8");
    }

    public GsonConverter(Gson gson1, String s)
    {
        gson = gson1;
        encoding = s;
    }

    public Object fromBody(TypedInput typedinput, Type type)
        throws ConversionException
    {
        String s;
        InputStreamReader inputstreamreader;
        s = "UTF-8";
        if(typedinput.mimeType() != null)
            s = MimeUtil.parseCharset(typedinput.mimeType());
        inputstreamreader = null;
        InputStreamReader inputstreamreader1 = new InputStreamReader(typedinput.in(), s);
        Gson gson1 = gson;
        if(gson1 instanceof Gson) goto _L2; else goto _L1
_L1:
        Object obj2 = gson1.fromJson(inputstreamreader1, type);
        Object obj1 = obj2;
_L3:
        IOException ioexception;
        Exception exception;
        JsonParseException jsonparseexception;
        Object obj;
        if(inputstreamreader1 != null)
            try
            {
                inputstreamreader1.close();
            }
            catch(IOException ioexception2)
            {
                return obj1;
            }
        return obj1;
_L2:
        obj = GsonInstrumentation.fromJson((Gson)gson1, inputstreamreader1, type);
        obj1 = obj;
          goto _L3
        ioexception;
_L7:
        throw new ConversionException(ioexception);
        exception;
_L4:
        if(inputstreamreader != null)
            try
            {
                inputstreamreader.close();
            }
            catch(IOException ioexception1) { }
        throw exception;
        jsonparseexception;
_L5:
        throw new ConversionException(jsonparseexception);
        exception;
        inputstreamreader = inputstreamreader1;
          goto _L4
        jsonparseexception;
        inputstreamreader = inputstreamreader1;
          goto _L5
        ioexception;
        inputstreamreader = inputstreamreader1;
        if(true) goto _L7; else goto _L6
_L6:
    }

    public TypedOutput toBody(Object obj)
    {
        Gson gson1 = gson;
        if(gson1 instanceof Gson) goto _L2; else goto _L1
_L1:
        String s1 = gson1.toJson(obj);
_L3:
        return new JsonTypedOutput(s1.getBytes(encoding), encoding);
_L2:
        String s = GsonInstrumentation.toJson((Gson)gson1, obj);
        s1 = s;
          goto _L3
        UnsupportedEncodingException unsupportedencodingexception;
        unsupportedencodingexception;
        throw new AssertionError(unsupportedencodingexception);
    }

    private String encoding;
    private final Gson gson;
}
