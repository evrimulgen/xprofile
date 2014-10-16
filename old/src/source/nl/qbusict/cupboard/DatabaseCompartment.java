// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package nl.qbusict.cupboard;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.newrelic.agent.android.instrumentation.SQLiteInstrumentation;
import java.util.*;
import nl.qbusict.cupboard.convert.Converter;
import nl.qbusict.cupboard.convert.ConverterHolder;

// Referenced classes of package nl.qbusict.cupboard:
//            BaseCompartment, QueryResultIterable

public class DatabaseCompartment extends BaseCompartment
{
    public static class QueryBuilder
    {

        public QueryBuilder byId(long l)
        {
            mSelection = "_id = ?";
            String as[] = new String[1];
            as[0] = String.valueOf(l);
            mSelectionArgs = as;
            limit(1);
            return this;
        }

        public QueryBuilder distinct()
        {
            mDistinct = true;
            return this;
        }

        public Object get()
        {
            return query().get();
        }

        public Cursor getCursor()
        {
            return query().getCursor();
        }

        public QueryBuilder groupBy(String s)
        {
            mGroup = s;
            return this;
        }

        public QueryBuilder having(String s)
        {
            mHaving = s;
            return this;
        }

        public QueryBuilder limit(int i)
        {
            if(i < 1)
            {
                throw new IllegalArgumentException("Limit must be greater or equal to 1");
            } else
            {
                mLimit = String.valueOf(i);
                return this;
            }
        }

        public QueryBuilder orderBy(String s)
        {
            mOrder = s;
            return this;
        }

        public QueryResultIterable query()
        {
            return mCompartment.query(mEntityClass, mProjection, mSelection, mSelectionArgs, mGroup, mHaving, mOrder, mLimit, mDistinct);
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

        private final DatabaseCompartment mCompartment;
        private boolean mDistinct;
        private final Class mEntityClass;
        private String mGroup;
        private String mHaving;
        private String mLimit;
        private String mOrder;
        private String mProjection[];
        private String mSelection;
        private String mSelectionArgs[];

        QueryBuilder(Class class1, DatabaseCompartment databasecompartment)
        {
            mLimit = null;
            mDistinct = false;
            mEntityClass = class1;
            mCompartment = databasecompartment;
        }
    }


    protected DatabaseCompartment(Map map, SQLiteDatabase sqlitedatabase)
    {
        super(map);
        mDatabase = sqlitedatabase;
    }

    private QueryResultIterable query(Class class1, String as[], String s, String as1[], String s1, String s2, String s3, 
            String s4, boolean flag)
    {
        Converter converter = getConverter(class1);
        SQLiteDatabase sqlitedatabase = mDatabase;
        String s5 = quoteTable(converter.getTable());
        Cursor cursor;
        if(!(sqlitedatabase instanceof SQLiteDatabase))
            cursor = sqlitedatabase.query(flag, s5, as, s, as1, s1, s2, s3, s4);
        else
            cursor = SQLiteInstrumentation.query((SQLiteDatabase)sqlitedatabase, flag, s5, as, s, as1, s1, s2, s3, s4);
        return new QueryResultIterable(cursor, converter);
    }

    private String quoteTable(String s)
    {
        return (new StringBuilder()).append("'").append(s).append("'").toString();
    }

    boolean createNewTable(SQLiteDatabase sqlitedatabase, String s, List list)
    {
        StringBuilder stringbuilder = new StringBuilder((new StringBuilder()).append("create table '").append(s).append("' (_id integer primary key autoincrement").toString());
        Iterator iterator = list.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            nl.qbusict.cupboard.convert.Converter.Column column = (nl.qbusict.cupboard.convert.Converter.Column)iterator.next();
            String s2 = column.name;
            if(!s2.equals("_id"))
            {
                stringbuilder.append(", '").append(s2).append("'");
                stringbuilder.append(" ").append(column.type.toString());
            }
        } while(true);
        stringbuilder.append(");");
        String s1 = stringbuilder.toString();
        if(!(sqlitedatabase instanceof SQLiteDatabase))
            sqlitedatabase.execSQL(s1);
        else
            SQLiteInstrumentation.execSQL((SQLiteDatabase)sqlitedatabase, s1);
        return true;
    }

    public void createTables()
    {
        createAllConverters();
        Converter converter;
        for(Iterator iterator = mConverters.entrySet().iterator(); iterator.hasNext(); createNewTable(mDatabase, converter.getTable(), converter.getColumns()))
            converter = ((ConverterHolder)((java.util.Map.Entry)iterator.next()).getValue()).get();

    }

    public transient int delete(Class class1, String s, String as[])
    {
        Converter converter = getConverter(class1);
        SQLiteDatabase sqlitedatabase = mDatabase;
        String s1 = quoteTable(converter.getTable());
        if(!(sqlitedatabase instanceof SQLiteDatabase))
            return sqlitedatabase.delete(s1, s, as);
        else
            return SQLiteInstrumentation.delete((SQLiteDatabase)sqlitedatabase, s1, s, as);
    }

    public boolean delete(Class class1, long l)
    {
        Converter converter = getConverter(class1);
        SQLiteDatabase sqlitedatabase = mDatabase;
        String s = quoteTable(converter.getTable());
        String as[] = new String[1];
        as[0] = String.valueOf(l);
        int i;
        if(!(sqlitedatabase instanceof SQLiteDatabase))
            i = sqlitedatabase.delete(s, "_id = ?", as);
        else
            i = SQLiteInstrumentation.delete((SQLiteDatabase)sqlitedatabase, s, "_id = ?", as);
        return i > 0;
    }

    public boolean delete(Object obj)
    {
        Class class1 = obj.getClass();
        Long long1 = getConverter(class1).getId(obj);
        if(long1 != null)
        {
            String as[] = new String[1];
            as[0] = String.valueOf(long1);
            return delete(class1, "_id = ?", as) > 0;
        } else
        {
            return false;
        }
    }

    public Object get(Class class1, long l)
    {
        return query(class1).byId(l).get();
    }

    public Object get(Object obj)
        throws IllegalArgumentException
    {
        Converter converter = getConverter(obj.getClass());
        if(converter.getId(obj) != null)
            return get(obj.getClass(), converter.getId(obj).longValue());
        else
            throw new IllegalArgumentException((new StringBuilder()).append("id of entity ").append(obj.getClass()).append(" is not set").toString());
    }

    public long put(Class class1, ContentValues contentvalues)
    {
        Converter converter = getConverter(class1);
        Long long1 = contentvalues.getAsLong("_id");
        if(long1 != null)
        {
            SQLiteDatabase sqlitedatabase1 = mDatabase;
            String s1 = quoteTable(converter.getTable());
            if(!(sqlitedatabase1 instanceof SQLiteDatabase))
                sqlitedatabase1.replaceOrThrow(s1, "_id", contentvalues);
            else
                SQLiteInstrumentation.replaceOrThrow((SQLiteDatabase)sqlitedatabase1, s1, "_id", contentvalues);
            return long1.longValue();
        }
        SQLiteDatabase sqlitedatabase = mDatabase;
        String s = quoteTable(converter.getTable());
        long l;
        if(!(sqlitedatabase instanceof SQLiteDatabase))
            l = sqlitedatabase.insertOrThrow(s, "_id", contentvalues);
        else
            l = SQLiteInstrumentation.insertOrThrow((SQLiteDatabase)sqlitedatabase, s, "_id", contentvalues);
        return Long.valueOf(l).longValue();
    }

    public long put(Object obj)
    {
        Converter converter = getConverter(obj.getClass());
        ContentValues contentvalues = new ContentValues();
        converter.toValues(obj, contentvalues);
        Long long1 = contentvalues.getAsLong("_id");
        long l = put(obj.getClass(), contentvalues);
        if(long1 == null)
            converter.setId(Long.valueOf(l), obj);
        if(long1 == null)
            return l;
        else
            return long1.longValue();
    }

    public void put(Collection collection)
    {
        boolean flag;
        flag = mDatabase.inTransaction();
        mDatabase.beginTransaction();
        Iterator iterator = collection.iterator();
_L2:
        do
        {
            if(!iterator.hasNext())
                break MISSING_BLOCK_LABEL_70;
            put(iterator.next());
        } while(flag);
        mDatabase.yieldIfContendedSafely();
        if(true) goto _L2; else goto _L1
_L1:
        Exception exception;
        exception;
        mDatabase.endTransaction();
        throw exception;
        mDatabase.setTransactionSuccessful();
        mDatabase.endTransaction();
        return;
    }

    public transient void put(Object aobj[])
    {
        boolean flag;
        flag = mDatabase.inTransaction();
        mDatabase.beginTransaction();
        int i = aobj.length;
        int j = 0;
_L2:
        if(j >= i)
            break MISSING_BLOCK_LABEL_53;
        put(aobj[j]);
        if(flag)
            break MISSING_BLOCK_LABEL_78;
        mDatabase.yieldIfContendedSafely();
        break MISSING_BLOCK_LABEL_78;
        mDatabase.setTransactionSuccessful();
        mDatabase.endTransaction();
        return;
        Exception exception;
        exception;
        mDatabase.endTransaction();
        throw exception;
        j++;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public QueryBuilder query(Class class1)
    {
        return new QueryBuilder(class1, this);
    }

    public int update(Class class1, ContentValues contentvalues)
    {
        Converter converter = getConverter(class1);
        if(contentvalues.containsKey("_id"))
        {
            SQLiteDatabase sqlitedatabase1 = mDatabase;
            String s1 = quoteTable(converter.getTable());
            String as[] = new String[1];
            as[0] = contentvalues.getAsString("_id");
            if(!(sqlitedatabase1 instanceof SQLiteDatabase))
                return sqlitedatabase1.update(s1, contentvalues, "_id = ?", as);
            else
                return SQLiteInstrumentation.update((SQLiteDatabase)sqlitedatabase1, s1, contentvalues, "_id = ?", as);
        }
        SQLiteDatabase sqlitedatabase = mDatabase;
        String s = quoteTable(converter.getTable());
        if(!(sqlitedatabase instanceof SQLiteDatabase))
            return sqlitedatabase.update(s, contentvalues, null, null);
        else
            return SQLiteInstrumentation.update((SQLiteDatabase)sqlitedatabase, s, contentvalues, null, null);
    }

    public transient int update(Class class1, ContentValues contentvalues, String s, String as[])
    {
        Converter converter = getConverter(class1);
        SQLiteDatabase sqlitedatabase = mDatabase;
        String s1 = quoteTable(converter.getTable());
        if(!(sqlitedatabase instanceof SQLiteDatabase))
            return sqlitedatabase.update(s1, contentvalues, s, as);
        else
            return SQLiteInstrumentation.update((SQLiteDatabase)sqlitedatabase, s1, contentvalues, s, as);
    }

    boolean updateTable(SQLiteDatabase sqlitedatabase, String s, Cursor cursor, List list)
    {
        Locale locale = Locale.US;
        HashMap hashmap = new HashMap(list.size());
        nl.qbusict.cupboard.convert.Converter.Column column1;
        for(Iterator iterator = list.iterator(); iterator.hasNext(); hashmap.put(column1.name.toLowerCase(locale), column1))
            column1 = (nl.qbusict.cupboard.convert.Converter.Column)iterator.next();

        int i = cursor.getColumnIndex("name");
        for(; cursor.moveToNext(); hashmap.remove(cursor.getString(i).toLowerCase(locale)));
        if(hashmap.isEmpty())
            return false;
        for(Iterator iterator1 = hashmap.values().iterator(); iterator1.hasNext();)
        {
            nl.qbusict.cupboard.convert.Converter.Column column = (nl.qbusict.cupboard.convert.Converter.Column)iterator1.next();
            String s1 = (new StringBuilder()).append("alter table '").append(s).append("' add column '").append(column.name).append("' ").append(column.type.toString()).toString();
            if(!(sqlitedatabase instanceof SQLiteDatabase))
                sqlitedatabase.execSQL(s1);
            else
                SQLiteInstrumentation.execSQL((SQLiteDatabase)sqlitedatabase, s1);
        }

        return true;
    }

    boolean updateTable(SQLiteDatabase sqlitedatabase, String s, List list)
    {
        Cursor cursor;
        String s1 = (new StringBuilder()).append("pragma table_info('").append(s).append("')").toString();
        boolean flag1;
        if(!(sqlitedatabase instanceof SQLiteDatabase))
            cursor = sqlitedatabase.rawQuery(s1, null);
        else
            cursor = SQLiteInstrumentation.rawQuery((SQLiteDatabase)sqlitedatabase, s1, null);
        if(cursor.getCount() != 0)
            break MISSING_BLOCK_LABEL_88;
        flag1 = createNewTable(sqlitedatabase, s, list);
        cursor.close();
        return flag1;
        boolean flag = updateTable(sqlitedatabase, s, cursor, list);
        cursor.close();
        return flag;
        Exception exception;
        exception;
        cursor.close();
        throw exception;
    }

    public void upgradeTables()
    {
        createAllConverters();
        Converter converter;
        for(Iterator iterator = mConverters.entrySet().iterator(); iterator.hasNext(); updateTable(mDatabase, converter.getTable(), converter.getColumns()))
            converter = ((ConverterHolder)((java.util.Map.Entry)iterator.next()).getValue()).get();

    }

    private static final String QUERY_BY_ID = "_id = ?";
    private final SQLiteDatabase mDatabase;

}
