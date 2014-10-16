// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package nl.qbusict.cupboard;

import android.content.*;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import java.util.List;
import java.util.Map;
import nl.qbusict.cupboard.convert.Converter;

// Referenced classes of package nl.qbusict.cupboard:
//            BaseCompartment, QueryResultIterable

public class ProviderCompartment extends BaseCompartment
{
    public static class QueryBuilder
    {

        public Object get()
        {
            return query().get();
        }

        public Cursor getCursor()
        {
            return query().getCursor();
        }

        public QueryBuilder orderBy(String s)
        {
            mOrder = s;
            return this;
        }

        public QueryResultIterable query()
        {
            return mCompartment.query(mUri, mEntityClass, mProjection, mSelection, mSelectionArgs, mOrder);
        }

        public transient QueryBuilder withProjection(String as[])
        {
            mProjection = as;
            return this;
        }

        public transient QueryBuilder withSelection(String s, String as[])
        {
            mSelection = s;
            mSelectionArgs = as;
            return this;
        }

        private final ProviderCompartment mCompartment;
        private final Class mEntityClass;
        private String mOrder;
        private String mProjection[];
        private String mSelection;
        private String mSelectionArgs[];
        private final Uri mUri;

        public QueryBuilder(Uri uri, Class class1, ProviderCompartment providercompartment)
        {
            mEntityClass = class1;
            mCompartment = providercompartment;
            mUri = uri;
        }
    }


    protected ProviderCompartment(Map map, Context context)
    {
        super(map);
        mResolver = context.getContentResolver();
    }

    private QueryResultIterable query(Uri uri, Class class1, String as[], String s, String as1[], String s1)
    {
        Converter converter = getConverter(class1);
        Object obj = mResolver.query(uri, as, s, as1, s1);
        if(obj == null)
            obj = new MatrixCursor(new String[] {
                "_id"
            });
        return new QueryResultIterable(((Cursor) (obj)), converter);
    }

    public int delete(Uri uri, Object obj)
    {
        Long long1 = getConverter(obj.getClass()).getId(obj);
        if(long1 == null)
            return 0;
        else
            return mResolver.delete(ContentUris.withAppendedId(uri, long1.longValue()), null, null);
    }

    public Object get(Uri uri, Class class1)
    {
        return query(uri, class1).query().get();
    }

    public Object get(Uri uri, Object obj)
    {
        Long long1 = getConverter(obj.getClass()).getId(obj);
        if(long1 == null)
            throw new IllegalArgumentException("entity does not have it's id set");
        else
            return get(ContentUris.withAppendedId(uri, long1.longValue()), obj.getClass());
    }

    public transient int put(Uri uri, Class class1, Object aobj[])
    {
        Converter converter = getConverter(class1);
        ContentValues acontentvalues[] = new ContentValues[aobj.length];
        int i = converter.getColumns().size();
        for(int j = 0; j < aobj.length; j++)
        {
            acontentvalues[j] = new ContentValues(i);
            converter.toValues(aobj[j], acontentvalues[j]);
        }

        return mResolver.bulkInsert(uri, acontentvalues);
    }

    public Uri put(Uri uri, Object obj)
    {
        Converter converter = getConverter(obj.getClass());
        ContentValues contentvalues = new ContentValues(converter.getColumns().size());
        converter.toValues(obj, contentvalues);
        Long long1 = converter.getId(obj);
        if(long1 == null)
            return mResolver.insert(uri, contentvalues);
        else
            return mResolver.insert(ContentUris.withAppendedId(uri, long1.longValue()), contentvalues);
    }

    public QueryBuilder query(Uri uri, Class class1)
    {
        return new QueryBuilder(uri, class1, this);
    }

    private final ContentResolver mResolver;

}
