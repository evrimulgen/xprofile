// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.roadtrippers.api.models.*;
import com.roadtrippers.db.models.Search;
import com.roadtrippers.db.models.TripData;
import java.util.*;
import nl.qbusict.cupboard.*;

public class DatabaseHelper extends SQLiteOpenHelper
{

    public DatabaseHelper(Context context)
    {
        super(context, "roadtrippers.db", null, 5);
    }

    public void deleteTrip(String s)
    {
        SQLiteDatabase sqlitedatabase;
        sqlitedatabase = getWritableDatabase();
        sqlitedatabase.beginTransaction();
        CupboardFactory.cupboard().withDatabase(sqlitedatabase).delete(com/roadtrippers/db/models/TripData, "id = ?", new String[] {
            s
        });
        sqlitedatabase.setTransactionSuccessful();
        sqlitedatabase.endTransaction();
        return;
        Exception exception;
        exception;
        sqlitedatabase.endTransaction();
        throw exception;
    }

    public GooglePlace[] getPlaces(String s)
    {
        SQLiteDatabase sqlitedatabase = getReadableDatabase();
        Search search = (Search)CupboardFactory.cupboard().withDatabase(sqlitedatabase).query(com/roadtrippers/db/models/Search).withSelection("query = ?", new String[] {
            s
        }).get();
        if(search != null)
        {
            ArrayList arraylist = new ArrayList();
            nl.qbusict.cupboard.DatabaseCompartment.QueryBuilder querybuilder = CupboardFactory.cupboard().withDatabase(sqlitedatabase).query(com/roadtrippers/api/models/GooglePlace);
            String as[] = new String[1];
            as[0] = String.valueOf(search._id);
            QueryResultIterable queryresultiterable = querybuilder.withSelection("search = ?", as).query();
            for(Iterator iterator = queryresultiterable.iterator(); iterator.hasNext(); arraylist.add((GooglePlace)iterator.next()));
            queryresultiterable.close();
            return (GooglePlace[])arraylist.toArray(new GooglePlace[arraylist.size()]);
        } else
        {
            return null;
        }
    }

    public RTAutocompleteSuggestion[] getRTAutocompleteSuggestions(String s)
    {
        SQLiteDatabase sqlitedatabase = getReadableDatabase();
        Search search = (Search)CupboardFactory.cupboard().withDatabase(sqlitedatabase).query(com/roadtrippers/db/models/Search).withSelection("query = ?", new String[] {
            s
        }).get();
        if(search != null)
        {
            ArrayList arraylist = new ArrayList();
            nl.qbusict.cupboard.DatabaseCompartment.QueryBuilder querybuilder = CupboardFactory.cupboard().withDatabase(sqlitedatabase).query(com/roadtrippers/api/models/RTAutocompleteSuggestion);
            String as[] = new String[1];
            as[0] = String.valueOf(search._id);
            QueryResultIterable queryresultiterable = querybuilder.withSelection("search = ?", as).query();
            for(Iterator iterator = queryresultiterable.iterator(); iterator.hasNext(); arraylist.add((RTAutocompleteSuggestion)iterator.next()));
            queryresultiterable.close();
            return (RTAutocompleteSuggestion[])arraylist.toArray(new RTAutocompleteSuggestion[arraylist.size()]);
        } else
        {
            return null;
        }
    }

    public Trip getTrip(String s)
    {
        SQLiteDatabase sqlitedatabase = getReadableDatabase();
        TripData tripdata = (TripData)CupboardFactory.cupboard().withDatabase(sqlitedatabase).query(com/roadtrippers/db/models/TripData).withSelection("id = ?", new String[] {
            s
        }).get();
        if(tripdata != null)
            return tripdata.toTrip();
        else
            return null;
    }

    public void onCreate(SQLiteDatabase sqlitedatabase)
    {
        CupboardFactory.cupboard().withDatabase(sqlitedatabase).createTables();
    }

    public void onUpgrade(SQLiteDatabase sqlitedatabase, int i, int j)
    {
        CupboardFactory.cupboard().withDatabase(sqlitedatabase).upgradeTables();
    }

    public void savePlaces(String s, GooglePlace agoogleplace[])
    {
        SQLiteDatabase sqlitedatabase;
        DatabaseCompartment databasecompartment;
        sqlitedatabase = getWritableDatabase();
        databasecompartment = CupboardFactory.cupboard().withDatabase(sqlitedatabase);
        sqlitedatabase.beginTransaction();
        Search search;
        int i;
        search = (new Search()).withQuery(s);
        search._id = Long.valueOf(databasecompartment.put(search));
        i = agoogleplace.length;
        int j = 0;
_L2:
        if(j >= i)
            break; /* Loop/switch isn't completed */
        GooglePlace googleplace = agoogleplace[j];
        googleplace.search = search;
        databasecompartment.put(googleplace);
        j++;
        if(true) goto _L2; else goto _L1
_L1:
        sqlitedatabase.setTransactionSuccessful();
        sqlitedatabase.endTransaction();
        return;
        Exception exception;
        exception;
        sqlitedatabase.endTransaction();
        throw exception;
    }

    public void saveRTAutocompleteSuggestions(String s, RTAutocompleteSuggestion artautocompletesuggestion[])
    {
        SQLiteDatabase sqlitedatabase;
        DatabaseCompartment databasecompartment;
        RTAutocompleteSuggestion artautocompletesuggestion1[];
        sqlitedatabase = getWritableDatabase();
        databasecompartment = CupboardFactory.cupboard().withDatabase(sqlitedatabase);
        sqlitedatabase.beginTransaction();
        artautocompletesuggestion1 = getRTAutocompleteSuggestions(s);
        Search search;
        search = (new Search()).withQuery(s);
        search._id = Long.valueOf(databasecompartment.put(search));
        if(artautocompletesuggestion1 == null) goto _L2; else goto _L1
_L1:
        int i = artautocompletesuggestion1.length;
        int j = 0;
_L3:
        if(j >= i)
            break; /* Loop/switch isn't completed */
        RTAutocompleteSuggestion rtautocompletesuggestion = artautocompletesuggestion1[j];
        rtautocompletesuggestion.search = search;
        databasecompartment.delete(rtautocompletesuggestion);
        j++;
        if(true) goto _L3; else goto _L2
_L2:
        int k = artautocompletesuggestion.length;
        int l = 0;
_L5:
        if(l >= k)
            break; /* Loop/switch isn't completed */
        RTAutocompleteSuggestion rtautocompletesuggestion1 = artautocompletesuggestion[l];
        rtautocompletesuggestion1.search = search;
        databasecompartment.put(rtautocompletesuggestion1);
        l++;
        if(true) goto _L5; else goto _L4
_L4:
        sqlitedatabase.setTransactionSuccessful();
        sqlitedatabase.endTransaction();
        return;
        Exception exception;
        exception;
        sqlitedatabase.endTransaction();
        throw exception;
    }

    public void saveTrip(Trip trip)
    {
        SQLiteDatabase sqlitedatabase;
        DatabaseCompartment databasecompartment;
        sqlitedatabase = getWritableDatabase();
        databasecompartment = CupboardFactory.cupboard().withDatabase(sqlitedatabase);
        sqlitedatabase.beginTransaction();
        String as[] = new String[1];
        as[0] = trip.id;
        databasecompartment.delete(com/roadtrippers/db/models/TripData, "id = ?", as);
        databasecompartment.put(TripData.fromTrip(trip));
        sqlitedatabase.setTransactionSuccessful();
        sqlitedatabase.endTransaction();
        return;
        Exception exception;
        exception;
        sqlitedatabase.endTransaction();
        throw exception;
    }

    public void saveTrips(Trip atrip[])
    {
        SQLiteDatabase sqlitedatabase;
        DatabaseCompartment databasecompartment;
        sqlitedatabase = getWritableDatabase();
        databasecompartment = CupboardFactory.cupboard().withDatabase(sqlitedatabase);
        sqlitedatabase.beginTransaction();
        int i = atrip.length;
        int j = 0;
_L2:
        if(j >= i)
            break; /* Loop/switch isn't completed */
        databasecompartment.put(TripData.fromTrip(atrip[j]));
        j++;
        if(true) goto _L2; else goto _L1
_L1:
        sqlitedatabase.setTransactionSuccessful();
        sqlitedatabase.endTransaction();
        return;
        Exception exception;
        exception;
        sqlitedatabase.endTransaction();
        throw exception;
    }

    static final String DATABASE_NAME = "roadtrippers.db";
    static final int DATABASE_VERSION = 5;

    static 
    {
        CupboardFactory.cupboard().register(com/roadtrippers/db/models/Search);
        CupboardFactory.cupboard().register(com/roadtrippers/api/models/GooglePlace);
        CupboardFactory.cupboard().register(com/roadtrippers/api/models/RTAutocompleteSuggestion);
        CupboardFactory.cupboard().register(com/roadtrippers/db/models/TripData);
    }
}
