// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package retrofit.converter;

import java.lang.reflect.Type;
import retrofit.mime.TypedInput;
import retrofit.mime.TypedOutput;

// Referenced classes of package retrofit.converter:
//            ConversionException

public interface Converter
{

    public abstract Object fromBody(TypedInput typedinput, Type type)
        throws ConversionException;

    public abstract TypedOutput toBody(Object obj);
}
