// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package nl.qbusict.cupboard;

import android.content.ContentValues;
import java.util.List;
import nl.qbusict.cupboard.convert.Converter;
import nl.qbusict.cupboard.convert.ConverterHolder;

public class EntityCompartment
{

    protected EntityCompartment(ConverterHolder converterholder)
    {
        mConverter = converterholder;
    }

    public String getTable()
    {
        return mConverter.get().getTable();
    }

    public ContentValues toContentValues(Object obj)
    {
        return toContentValues(obj, null);
    }

    public ContentValues toContentValues(Object obj, ContentValues contentvalues)
    {
        if(contentvalues == null)
            contentvalues = new ContentValues(mConverter.get().getColumns().size());
        mConverter.get().toValues(obj, contentvalues);
        return contentvalues;
    }

    private final ConverterHolder mConverter;
}
