// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package nl.qbusict.cupboard;

import android.content.*;
import android.net.Uri;
import java.util.*;
import nl.qbusict.cupboard.convert.Converter;

// Referenced classes of package nl.qbusict.cupboard:
//            BaseCompartment

public class ProviderOperationsCompartment extends BaseCompartment
{

    protected ProviderOperationsCompartment(Map map, ArrayList arraylist)
    {
        super(map);
        mOperations = arraylist;
    }

    public ProviderOperationsCompartment delete(Uri uri, Object obj)
    {
        Long long1 = getConverter(obj.getClass()).getId(obj);
        if(long1 == null)
        {
            return this;
        } else
        {
            mOperations.add(ContentProviderOperation.newDelete(ContentUris.withAppendedId(uri, long1.longValue())).build());
            return this;
        }
    }

    public ArrayList getOperations()
    {
        return mOperations;
    }

    public transient ProviderOperationsCompartment put(Uri uri, Class class1, Object aobj[])
    {
        Converter converter = getConverter(class1);
        ContentValues acontentvalues[] = new ContentValues[aobj.length];
        int i = converter.getColumns().size();
        for(int j = 0; j < aobj.length; j++)
        {
            acontentvalues[j] = new ContentValues(i);
            converter.toValues(aobj[j], acontentvalues[j]);
        }

        for(int k = 0; k < aobj.length; k++)
            put(uri, aobj[k]);

        return this;
    }

    public ProviderOperationsCompartment put(Uri uri, Object obj)
    {
        Converter converter = getConverter(obj.getClass());
        ContentValues contentvalues = new ContentValues(converter.getColumns().size());
        converter.toValues(obj, contentvalues);
        Long long1 = converter.getId(obj);
        if(long1 == null)
        {
            mOperations.add(ContentProviderOperation.newInsert(uri).withValues(contentvalues).build());
            return this;
        } else
        {
            mOperations.add(ContentProviderOperation.newInsert(ContentUris.withAppendedId(uri, long1.longValue())).withValues(contentvalues).build());
            return this;
        }
    }

    private final ArrayList mOperations;
}
