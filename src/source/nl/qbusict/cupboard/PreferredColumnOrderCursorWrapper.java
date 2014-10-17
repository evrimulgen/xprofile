// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package nl.qbusict.cupboard;

import android.database.Cursor;
import android.database.CursorWrapper;
import java.util.Arrays;
import java.util.List;

class PreferredColumnOrderCursorWrapper extends CursorWrapper
{

    public PreferredColumnOrderCursorWrapper(Cursor cursor, List list)
    {
        this(cursor, toColumNames(list));
    }

    public PreferredColumnOrderCursorWrapper(Cursor cursor, String as[])
    {
        super(cursor);
        mColumns = as;
        mColumnMap = new int[as.length];
        Arrays.fill(mColumnMap, -1);
        mColumns = remapColumns(cursor.getColumnNames(), as);
    }

    private String[] remapColumns(String as[], String as1[])
    {
        int i = 0;
        for(int j = 0; j < as1.length; j++)
        {
            int k = getColumnIndex(as1[j]);
            mColumnMap[j] = k;
            if(k != -1)
                i = j;
        }

        if(i + 1 < as1.length)
        {
            String as2[] = new String[i + 1];
            System.arraycopy(as1, 0, as2, 0, i + 1);
            as1 = as2;
        }
        return as1;
    }

    private static String[] toColumNames(List list)
    {
        String as[] = new String[list.size()];
        for(int i = -1 + as.length; i >= 0; i--)
            as[i] = ((nl.qbusict.cupboard.convert.Converter.Column)list.get(i)).name;

        return as;
    }

    public byte[] getBlob(int i)
    {
        int j = mColumnMap[i];
        if(j == -1)
            return null;
        else
            return super.getBlob(j);
    }

    public int getColumnCount()
    {
        return mColumns.length;
    }

    public String[] getColumnNames()
    {
        return mColumns;
    }

    public double getDouble(int i)
    {
        int j = mColumnMap[i];
        if(j == -1)
            return 0.0D;
        else
            return super.getDouble(j);
    }

    public float getFloat(int i)
    {
        int j = mColumnMap[i];
        if(j == -1)
            return 0.0F;
        else
            return super.getFloat(j);
    }

    public int getInt(int i)
    {
        int j = mColumnMap[i];
        if(j == -1)
            return 0;
        else
            return super.getInt(j);
    }

    public long getLong(int i)
    {
        int j = mColumnMap[i];
        if(j == -1)
            return 0L;
        else
            return super.getLong(j);
    }

    public short getShort(int i)
    {
        int j = mColumnMap[i];
        if(j == -1)
            return 0;
        else
            return super.getShort(j);
    }

    public String getString(int i)
    {
        int j = mColumnMap[i];
        if(j == -1)
            return null;
        else
            return super.getString(j);
    }

    public boolean isNull(int i)
    {
        int j = mColumnMap[i];
        if(j == -1)
            return true;
        else
            return super.isNull(j);
    }

    private final int mColumnMap[];
    private String mColumns[];
}
