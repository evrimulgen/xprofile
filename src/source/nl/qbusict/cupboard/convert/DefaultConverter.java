// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package nl.qbusict.cupboard.convert;

import android.content.ContentValues;
import android.database.Cursor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;
import nl.qbusict.cupboard.annotation.Column;
import nl.qbusict.cupboard.annotation.Ignore;

// Referenced classes of package nl.qbusict.cupboard.convert:
//            Converter, ConverterHolder

public class DefaultConverter
    implements Converter
{
    private static class BooleanConverter
        implements TypeConverter
    {

        public Boolean fromCursorValue(Cursor cursor, int i)
        {
            boolean flag = true;
            Boolean boolean1;
            if(cursor.getInt(i) != flag)
                flag = false;
            boolean1 = Boolean.valueOf(flag);
            return boolean1;
            NumberFormatException numberformatexception;
            numberformatexception;
            return Boolean.valueOf("true".equals(cursor.getString(i)));
        }

        public volatile Object fromCursorValue(Cursor cursor, int i)
        {
            return fromCursorValue(cursor, i);
        }

        public Converter.ColumnType getColumnType()
        {
            return Converter.ColumnType.INTEGER;
        }

        public void toContentValue(Boolean boolean1, String s, ContentValues contentvalues)
        {
            contentvalues.put(s, boolean1);
        }

        public volatile void toContentValue(Object obj, String s, ContentValues contentvalues)
        {
            toContentValue((Boolean)obj, s, contentvalues);
        }

        private BooleanConverter()
        {
        }

    }

    private static class ByteArrayConverter
        implements TypeConverter
    {

        public volatile Object fromCursorValue(Cursor cursor, int i)
        {
            return fromCursorValue(cursor, i);
        }

        public byte[] fromCursorValue(Cursor cursor, int i)
        {
            return cursor.getBlob(i);
        }

        public Converter.ColumnType getColumnType()
        {
            return Converter.ColumnType.BLOB;
        }

        public volatile void toContentValue(Object obj, String s, ContentValues contentvalues)
        {
            toContentValue((byte[])obj, s, contentvalues);
        }

        public void toContentValue(byte abyte0[], String s, ContentValues contentvalues)
        {
            contentvalues.put(s, abyte0);
        }

        private ByteArrayConverter()
        {
        }

    }

    private static class ByteConverter
        implements TypeConverter
    {

        public Byte fromCursorValue(Cursor cursor, int i)
        {
            return Byte.valueOf((byte)cursor.getInt(i));
        }

        public volatile Object fromCursorValue(Cursor cursor, int i)
        {
            return fromCursorValue(cursor, i);
        }

        public Converter.ColumnType getColumnType()
        {
            return Converter.ColumnType.INTEGER;
        }

        public void toContentValue(Byte byte1, String s, ContentValues contentvalues)
        {
            contentvalues.put(s, byte1);
        }

        public volatile void toContentValue(Object obj, String s, ContentValues contentvalues)
        {
            toContentValue((Byte)obj, s, contentvalues);
        }

        private ByteConverter()
        {
        }

    }

    private static class DateConverter
        implements TypeConverter
    {

        public volatile Object fromCursorValue(Cursor cursor, int i)
        {
            return fromCursorValue(cursor, i);
        }

        public Date fromCursorValue(Cursor cursor, int i)
        {
            return new Date(cursor.getLong(i));
        }

        public Converter.ColumnType getColumnType()
        {
            return Converter.ColumnType.INTEGER;
        }

        public volatile void toContentValue(Object obj, String s, ContentValues contentvalues)
        {
            toContentValue((Date)obj, s, contentvalues);
        }

        public void toContentValue(Date date, String s, ContentValues contentvalues)
        {
            contentvalues.put(s, Long.valueOf(date.getTime()));
        }

        private DateConverter()
        {
        }

    }

    private static class DoubleConverter
        implements TypeConverter
    {

        public Double fromCursorValue(Cursor cursor, int i)
        {
            return Double.valueOf(cursor.getDouble(i));
        }

        public volatile Object fromCursorValue(Cursor cursor, int i)
        {
            return fromCursorValue(cursor, i);
        }

        public Converter.ColumnType getColumnType()
        {
            return Converter.ColumnType.REAL;
        }

        public void toContentValue(Double double1, String s, ContentValues contentvalues)
        {
            contentvalues.put(s, double1);
        }

        public volatile void toContentValue(Object obj, String s, ContentValues contentvalues)
        {
            toContentValue((Double)obj, s, contentvalues);
        }

        private DoubleConverter()
        {
        }

    }

    private static class EntityConverter
        implements TypeConverter
    {

        private Converter getTranslator()
        {
            if(mTranslator != null)
                return mTranslator;
            else
                return mTranslatorHolder.get();
        }

        public Object fromCursorValue(Cursor cursor, int i)
        {
            long l = cursor.getLong(i);
            Object obj;
            try
            {
                obj = entityClass.newInstance();
            }
            catch(IllegalAccessException illegalaccessexception)
            {
                throw new RuntimeException(illegalaccessexception);
            }
            catch(InstantiationException instantiationexception)
            {
                throw new RuntimeException(instantiationexception);
            }
            getTranslator().setId(Long.valueOf(l), obj);
            return obj;
        }

        public Converter.ColumnType getColumnType()
        {
            return Converter.ColumnType.INTEGER;
        }

        public void toContentValue(Object obj, String s, ContentValues contentvalues)
        {
            contentvalues.put(s, getTranslator().getId(obj));
        }

        private final Class entityClass;
        private final Converter mTranslator;
        private final ConverterHolder mTranslatorHolder;

        public EntityConverter(Class class1, Converter converter)
        {
            mTranslatorHolder = null;
            entityClass = class1;
            mTranslator = converter;
        }

        public EntityConverter(Class class1, ConverterHolder converterholder)
        {
            mTranslatorHolder = converterholder;
            entityClass = class1;
            mTranslator = null;
        }
    }

    private static class EnumConverter
        implements TypeConverter
    {

        public Enum fromCursorValue(Cursor cursor, int i)
        {
            return Enum.valueOf(mEnumClass, cursor.getString(i));
        }

        public volatile Object fromCursorValue(Cursor cursor, int i)
        {
            return fromCursorValue(cursor, i);
        }

        public Converter.ColumnType getColumnType()
        {
            return Converter.ColumnType.TEXT;
        }

        public void toContentValue(Enum enum, String s, ContentValues contentvalues)
        {
            contentvalues.put(s, enum.toString());
        }

        public volatile void toContentValue(Object obj, String s, ContentValues contentvalues)
        {
            toContentValue((Enum)obj, s, contentvalues);
        }

        private final Class mEnumClass;

        public EnumConverter(Class class1)
        {
            mEnumClass = class1;
        }
    }

    private static class FloatConverter
        implements TypeConverter
    {

        public Float fromCursorValue(Cursor cursor, int i)
        {
            return Float.valueOf(cursor.getFloat(i));
        }

        public volatile Object fromCursorValue(Cursor cursor, int i)
        {
            return fromCursorValue(cursor, i);
        }

        public Converter.ColumnType getColumnType()
        {
            return Converter.ColumnType.REAL;
        }

        public void toContentValue(Float float1, String s, ContentValues contentvalues)
        {
            contentvalues.put(s, float1);
        }

        public volatile void toContentValue(Object obj, String s, ContentValues contentvalues)
        {
            toContentValue((Float)obj, s, contentvalues);
        }

        private FloatConverter()
        {
        }

    }

    private static class IntegerConverter
        implements TypeConverter
    {

        public Integer fromCursorValue(Cursor cursor, int i)
        {
            return Integer.valueOf(cursor.getInt(i));
        }

        public volatile Object fromCursorValue(Cursor cursor, int i)
        {
            return fromCursorValue(cursor, i);
        }

        public Converter.ColumnType getColumnType()
        {
            return Converter.ColumnType.INTEGER;
        }

        public void toContentValue(Integer integer, String s, ContentValues contentvalues)
        {
            contentvalues.put(s, integer);
        }

        public volatile void toContentValue(Object obj, String s, ContentValues contentvalues)
        {
            toContentValue((Integer)obj, s, contentvalues);
        }

        private IntegerConverter()
        {
        }

    }

    private static class LongConverter
        implements TypeConverter
    {

        public Long fromCursorValue(Cursor cursor, int i)
        {
            return Long.valueOf(cursor.getLong(i));
        }

        public volatile Object fromCursorValue(Cursor cursor, int i)
        {
            return fromCursorValue(cursor, i);
        }

        public Converter.ColumnType getColumnType()
        {
            return Converter.ColumnType.INTEGER;
        }

        public void toContentValue(Long long1, String s, ContentValues contentvalues)
        {
            contentvalues.put(s, long1);
        }

        public volatile void toContentValue(Object obj, String s, ContentValues contentvalues)
        {
            toContentValue((Long)obj, s, contentvalues);
        }

        private LongConverter()
        {
        }

    }

    private static class Property
    {

        Converter.ColumnType columnType;
        Field field;
        String name;
        Class type;
        TypeConverter typeConverter;

        private Property()
        {
        }

    }

    private static class ShortConverter
        implements TypeConverter
    {

        public volatile Object fromCursorValue(Cursor cursor, int i)
        {
            return fromCursorValue(cursor, i);
        }

        public Short fromCursorValue(Cursor cursor, int i)
        {
            return Short.valueOf(cursor.getShort(i));
        }

        public Converter.ColumnType getColumnType()
        {
            return Converter.ColumnType.REAL;
        }

        public volatile void toContentValue(Object obj, String s, ContentValues contentvalues)
        {
            toContentValue((Short)obj, s, contentvalues);
        }

        public void toContentValue(Short short1, String s, ContentValues contentvalues)
        {
            contentvalues.put(s, short1);
        }

        private ShortConverter()
        {
        }

    }

    private static class StringConverter
        implements TypeConverter
    {

        public volatile Object fromCursorValue(Cursor cursor, int i)
        {
            return fromCursorValue(cursor, i);
        }

        public String fromCursorValue(Cursor cursor, int i)
        {
            return cursor.getString(i);
        }

        public Converter.ColumnType getColumnType()
        {
            return Converter.ColumnType.TEXT;
        }

        public volatile void toContentValue(Object obj, String s, ContentValues contentvalues)
        {
            toContentValue((String)obj, s, contentvalues);
        }

        public void toContentValue(String s, String s1, ContentValues contentvalues)
        {
            contentvalues.put(s1, s);
        }

        private StringConverter()
        {
        }

    }

    private static interface TypeConverter
    {

        public abstract Object fromCursorValue(Cursor cursor, int i);

        public abstract Converter.ColumnType getColumnType();

        public abstract void toContentValue(Object obj, String s, ContentValues contentvalues);
    }


    public DefaultConverter(Class class1, Map map, boolean flag)
    {
        mUsingAnnotations = false;
        mIdProperty = null;
        mUsingAnnotations = flag;
        Field afield[] = getAllFields(class1);
        mColumns = new ArrayList(afield.length);
        mClass = class1;
        ArrayList arraylist = new ArrayList();
        int i = afield.length;
        int j = 0;
        do
        {
            while(j < i) 
            {
                Field field = afield[j];
                if(!Modifier.isTransient(field.getModifiers()) && !Modifier.isFinal(field.getModifiers()) && !Modifier.isStatic(field.getModifiers()) && (!mUsingAnnotations || field.getAnnotation(nl/qbusict/cupboard/annotation/Ignore) == null))
                {
                    Class class2 = field.getType();
                    Object obj;
                    Property property;
                    if(class2.isEnum())
                        obj = new EnumConverter(class2);
                    else
                        obj = (TypeConverter)sTypeConverters.get(class2);
                    if(obj == null)
                        if(class2 == class1)
                        {
                            obj = new EntityConverter(class2, this);
                        } else
                        {
                            ConverterHolder converterholder = (ConverterHolder)map.get(class2);
                            if(converterholder == null)
                                throw new IllegalArgumentException((new StringBuilder()).append("Field ").append(field).append(" cannot be persisted and should be marked as transient").toString());
                            obj = new EntityConverter(class2, converterholder);
                        }
                    property = new Property();
                    property.field = field;
                    if(!field.isAccessible())
                        field.setAccessible(true);
                    property.name = getColumn(field);
                    property.type = field.getType();
                    property.typeConverter = ((TypeConverter) (obj));
                    property.columnType = ((TypeConverter) (obj)).getColumnType();
                    arraylist.add(property);
                    if("_id".equals(property.name))
                        mIdProperty = property;
                    mColumns.add(new Converter.Column(property.name, property.columnType));
                }
                j++;
            }
            mProperties = (Property[])arraylist.toArray(new Property[arraylist.size()]);
            return;
        } while(true);
    }

    private Field[] getAllFields(Class class1)
    {
        if(class1.getSuperclass() == null)
            return class1.getDeclaredFields();
        ArrayList arraylist = new ArrayList(256);
        Class class2 = class1;
        do
        {
            arraylist.addAll(Arrays.asList(class2.getDeclaredFields()));
            class2 = class2.getSuperclass();
        } while(class2 != null);
        return (Field[])arraylist.toArray(new Field[arraylist.size()]);
    }

    private static String getTable(Class class1)
    {
        return class1.getSimpleName();
    }

    public Object fromCursor(Cursor cursor)
    {
        Object obj;
        int i;
        String as[];
        Property property;
        Class class1;
        try
        {
            obj = mClass.newInstance();
            as = cursor.getColumnNames();
        }
        catch(InstantiationException instantiationexception)
        {
            throw new RuntimeException(instantiationexception);
        }
        catch(IllegalAccessException illegalaccessexception)
        {
            throw new RuntimeException(illegalaccessexception);
        }
        i = 0;
_L2:
        if(i >= as.length)
            break MISSING_BLOCK_LABEL_123;
        property = mProperties[i];
        class1 = property.type;
        if(cursor.isNull(i))
        {
            if(!class1.isPrimitive())
                property.field.set(obj, null);
            break MISSING_BLOCK_LABEL_126;
        }
        property.field.set(obj, property.typeConverter.fromCursorValue(cursor, i));
        break MISSING_BLOCK_LABEL_126;
        return obj;
        i++;
        if(true) goto _L2; else goto _L1
_L1:
    }

    protected String getColumn(Field field)
    {
        if(mUsingAnnotations)
        {
            Column column = (Column)field.getAnnotation(nl/qbusict/cupboard/annotation/Column);
            if(column != null)
                return column.value();
        }
        return field.getName();
    }

    public List getColumns()
    {
        return mColumns;
    }

    public Long getId(Object obj)
    {
        if(mIdProperty != null)
        {
            Long long1;
            try
            {
                long1 = (Long)mIdProperty.field.get(obj);
            }
            catch(IllegalArgumentException illegalargumentexception)
            {
                throw new RuntimeException(illegalargumentexception);
            }
            catch(IllegalAccessException illegalaccessexception)
            {
                throw new RuntimeException(illegalaccessexception);
            }
            return long1;
        } else
        {
            return null;
        }
    }

    public String getTable()
    {
        return getTable(mClass);
    }

    public void setId(Long long1, Object obj)
    {
        if(mIdProperty == null)
            break MISSING_BLOCK_LABEL_19;
        mIdProperty.field.set(obj, long1);
        return;
        IllegalArgumentException illegalargumentexception;
        illegalargumentexception;
        throw new RuntimeException(illegalargumentexception);
        IllegalAccessException illegalaccessexception;
        illegalaccessexception;
        throw new RuntimeException(illegalaccessexception);
    }

    public void toValues(Object obj, ContentValues contentvalues)
    {
        Property aproperty[];
        int i;
        int j;
        aproperty = mProperties;
        i = aproperty.length;
        j = 0;
_L2:
        if(j >= i)
            break MISSING_BLOCK_LABEL_99;
        Property property = aproperty[j];
        Object obj1;
        try
        {
            obj1 = property.field.get(obj);
        }
        catch(IllegalAccessException illegalaccessexception)
        {
            throw new RuntimeException(illegalaccessexception);
        }
        if(obj1 != null)
            break MISSING_BLOCK_LABEL_66;
        if(!property.name.equals("_id"))
            contentvalues.putNull(property.name);
        break MISSING_BLOCK_LABEL_100;
        property.typeConverter.toContentValue(obj1, property.name, contentvalues);
        break MISSING_BLOCK_LABEL_100;
        return;
        j++;
        if(true) goto _L2; else goto _L1
_L1:
    }

    private static HashMap sTypeConverters;
    private final Class mClass;
    private final List mColumns;
    private Property mIdProperty;
    private final Property mProperties[];
    private boolean mUsingAnnotations;

    static 
    {
        sTypeConverters = new HashMap(25);
        sTypeConverters.put(java/lang/String, new StringConverter());
        sTypeConverters.put(Integer.TYPE, new IntegerConverter());
        sTypeConverters.put(java/lang/Integer, new IntegerConverter());
        sTypeConverters.put(Float.TYPE, new FloatConverter());
        sTypeConverters.put(java/lang/Float, new FloatConverter());
        sTypeConverters.put(Short.TYPE, new ShortConverter());
        sTypeConverters.put(java/lang/Short, new ShortConverter());
        sTypeConverters.put(Double.TYPE, new DoubleConverter());
        sTypeConverters.put(java/lang/Double, new DoubleConverter());
        sTypeConverters.put(Long.TYPE, new LongConverter());
        sTypeConverters.put(java/lang/Long, new LongConverter());
        sTypeConverters.put(Byte.TYPE, new ByteConverter());
        sTypeConverters.put(java/lang/Byte, new ByteConverter());
        sTypeConverters.put([B, new ByteArrayConverter());
        sTypeConverters.put(Boolean.TYPE, new BooleanConverter());
        sTypeConverters.put(java/lang/Boolean, new BooleanConverter());
        sTypeConverters.put(java/util/Date, new DateConverter());
    }
}
