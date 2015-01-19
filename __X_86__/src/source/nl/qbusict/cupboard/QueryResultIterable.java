// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package nl.qbusict.cupboard;

import android.database.Cursor;
import java.util.Iterator;
import java.util.NoSuchElementException;
import nl.qbusict.cupboard.convert.Converter;

// Referenced classes of package nl.qbusict.cupboard:
//            PreferredColumnOrderCursorWrapper

public class QueryResultIterable
    implements Iterable
{
    static class QueryResultIterator
        implements Iterator
    {

        public boolean hasNext()
        {
            return mHasNext;
        }

        public Object next()
        {
            if(!mHasNext)
            {
                throw new NoSuchElementException();
            } else
            {
                Object obj = mTranslator.fromCursor(mCursor);
                mHasNext = mCursor.moveToNext();
                return obj;
            }
        }

        public void remove()
        {
            throw new UnsupportedOperationException();
        }

        private final Cursor mCursor;
        private boolean mHasNext;
        private final Converter mTranslator;

        public QueryResultIterator(Cursor cursor, Converter converter)
        {
            mCursor = new PreferredColumnOrderCursorWrapper(cursor, converter.getColumns());
            mTranslator = converter;
            mHasNext = cursor.moveToNext();
        }
    }


    QueryResultIterable(Cursor cursor, Converter converter)
    {
        if(cursor.getPosition() > -1)
            mPosition = -1 + cursor.getPosition();
        else
            mPosition = -1;
        mCursor = cursor;
        mTranslator = converter;
    }

    public void close()
    {
        if(!mCursor.isClosed())
            mCursor.close();
    }

    public Object get()
    {
        return get(true);
    }

    public Object get(boolean flag)
    {
        Iterator iterator1 = iterator();
        if(!iterator1.hasNext()) goto _L2; else goto _L1
_L1:
        Object obj1 = iterator1.next();
        Object obj;
        obj = obj1;
        if(flag)
            close();
_L4:
        return obj;
_L2:
        obj = null;
        if(!flag) goto _L4; else goto _L3
_L3:
        close();
        return null;
        Exception exception;
        exception;
        if(flag)
            close();
        throw exception;
    }

    public Cursor getCursor()
    {
        return mCursor;
    }

    public Iterator iterator()
    {
        mCursor.moveToPosition(mPosition);
        return new QueryResultIterator(mCursor, mTranslator);
    }

    private final Cursor mCursor;
    private final int mPosition;
    private final Converter mTranslator;
}
