// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package nl.qbusict.cupboard;

import android.database.Cursor;
import java.util.Map;

// Referenced classes of package nl.qbusict.cupboard:
//            BaseCompartment, QueryResultIterable

public class CursorCompartment extends BaseCompartment
{

    protected CursorCompartment(Map map, Cursor cursor)
    {
        super(map);
        mCursor = cursor;
    }

    public Object get(Class class1)
    {
        return iterate(class1).get(false);
    }

    public QueryResultIterable iterate(Class class1)
    {
        nl.qbusict.cupboard.convert.Converter converter = getConverter(class1);
        return new QueryResultIterable(mCursor, converter);
    }

    private final Cursor mCursor;
}
