// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package nl.qbusict.cupboard;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.*;
import nl.qbusict.cupboard.convert.ConverterFactory;
import nl.qbusict.cupboard.convert.ConverterHolder;
import nl.qbusict.cupboard.convert.DefaultConverterFactory;

// Referenced classes of package nl.qbusict.cupboard:
//            EntityCompartment, ProviderCompartment, CursorCompartment, DatabaseCompartment, 
//            ProviderOperationsCompartment

public class Cupboard
{

    public Cupboard()
    {
        this(((ConverterFactory) (new DefaultConverterFactory())));
    }

    public Cupboard(ConverterFactory converterfactory)
    {
        mEntities = new HashMap();
        mTranslatorFactory = converterfactory;
    }

    public Collection getRegisteredEntities()
    {
        return Collections.unmodifiableSet(mEntities.keySet());
    }

    public String getTable(Class class1)
    {
        return withEntity(class1).getTable();
    }

    public void register(Class class1)
    {
        mEntities.put(class1, new ConverterHolder(class1, mTranslatorFactory, Collections.unmodifiableMap(mEntities)));
    }

    public ProviderCompartment withContext(Context context)
    {
        return new ProviderCompartment(mEntities, context);
    }

    public CursorCompartment withCursor(Cursor cursor)
    {
        return new CursorCompartment(mEntities, cursor);
    }

    public DatabaseCompartment withDatabase(SQLiteDatabase sqlitedatabase)
    {
        return new DatabaseCompartment(mEntities, sqlitedatabase);
    }

    public EntityCompartment withEntity(Class class1)
    {
        ConverterHolder converterholder = (ConverterHolder)mEntities.get(class1);
        if(converterholder == null)
            throw new IllegalArgumentException((new StringBuilder()).append("Class ").append(class1.toString()).append(" isn't registered.").toString());
        else
            return new EntityCompartment(converterholder);
    }

    public ProviderOperationsCompartment withOperations(ArrayList arraylist)
    {
        return new ProviderOperationsCompartment(mEntities, arraylist);
    }

    private final HashMap mEntities;
    private final ConverterFactory mTranslatorFactory;
}
