// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package retrofit.converter;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import retrofit.mime.*;

// Referenced classes of package retrofit.converter:
//            Converter, ConversionException

public class JacksonConverter
    implements Converter
{

    public JacksonConverter()
    {
        this(new ObjectMapper());
    }

    public JacksonConverter(ObjectMapper objectmapper)
    {
        objectMapper = objectmapper;
    }

    public Object fromBody(TypedInput typedinput, Type type)
        throws ConversionException
    {
        Object obj;
        try
        {
            com.fasterxml.jackson.databind.JavaType javatype = objectMapper.getTypeFactory().constructType(type);
            obj = objectMapper.readValue(typedinput.in(), javatype);
        }
        catch(JsonParseException jsonparseexception)
        {
            throw new ConversionException(jsonparseexception);
        }
        catch(JsonMappingException jsonmappingexception)
        {
            throw new ConversionException(jsonmappingexception);
        }
        catch(IOException ioexception)
        {
            throw new ConversionException(ioexception);
        }
        return obj;
    }

    public TypedOutput toBody(Object obj)
    {
        TypedByteArray typedbytearray;
        try
        {
            typedbytearray = new TypedByteArray("application/json; charset=UTF-8", objectMapper.writeValueAsString(obj).getBytes("UTF-8"));
        }
        catch(JsonProcessingException jsonprocessingexception)
        {
            throw new AssertionError(jsonprocessingexception);
        }
        catch(UnsupportedEncodingException unsupportedencodingexception)
        {
            throw new AssertionError(unsupportedencodingexception);
        }
        return typedbytearray;
    }

    private static final String MIME_TYPE = "application/json; charset=UTF-8";
    private final ObjectMapper objectMapper;
}
