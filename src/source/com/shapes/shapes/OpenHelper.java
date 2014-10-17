// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.shapes.shapes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

// Referenced classes of package com.shapes.shapes:
//            ShapeTable

public class OpenHelper extends SQLiteOpenHelper
{

    OpenHelper(Context context1)
    {
        super(context1, "shape_app", null, ShapeTable.DATABASE_VERSION);
        context = context1;
    }

    public void onCreate(SQLiteDatabase sqlitedatabase)
    {
        ShapeTable.onCreate(sqlitedatabase);
    }

    public void onOpen(SQLiteDatabase sqlitedatabase)
    {
        super.onOpen(sqlitedatabase);
    }

    public void onUpgrade(SQLiteDatabase sqlitedatabase, int i, int j)
    {
        ShapeTable.onUpgrade(sqlitedatabase, i, j);
    }

    private Context context;
}
