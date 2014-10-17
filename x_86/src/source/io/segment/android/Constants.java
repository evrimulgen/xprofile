// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package io.segment.android;


public class Constants
{
    public static class Database
    {

        public static final String NAME;
        public static final int VERSION = 1;

        static 
        {
            NAME = Constants.PACKAGE_NAME;
        }

        public Database()
        {
        }
    }

    public static class Database.PayloadTable
    {

        public static final String FIELD_NAMES[] = {
            "id", "payload"
        };
        public static final String NAME = "payload_table";


        public Database.PayloadTable()
        {
        }
    }

    public static class Database.PayloadTable.Fields
    {

        public Database.PayloadTable.Fields()
        {
        }
    }

    public static class Database.PayloadTable.Fields.Id
    {

        public static final String NAME = "id";
        public static final String TYPE = "INTEGER PRIMARY KEY AUTOINCREMENT";

        public Database.PayloadTable.Fields.Id()
        {
        }
    }

    public static class Database.PayloadTable.Fields.Payload
    {

        public static final String NAME = "payload";
        public static final String TYPE = " TEXT";

        public Database.PayloadTable.Fields.Payload()
        {
        }
    }

    public class Permission
    {

        public static final String ACCESS_NETWORK_STATE = "android.permission.ACCESS_NETWORK_STATE";
        public static final String COARSE_LOCATION = "android.permission.ACCESS_COARSE_LOCATION";
        public static final String FINE_LOCATION = "android.permission.ACCESS_FINE_LOCATION";
        public static final String GET_ACCOUNTS = "android.permission.GET_ACCOUNTS";
        public static final String INTERNET = "android.permission.INTERNET";
        public static final String READ_PHONE_STATE = "android.permission.READ_PHONE_STATE";
        final Constants this$0;

        public Permission()
        {
            this$0 = Constants.this;
            super();
        }
    }

    public class SharedPreferences
    {

        public static final String GROUP_ID_KEY = "group.id";
        public static final String SESSION_ID_KEY = "session.id";
        public static final String USER_ID_KEY = "user.id";
        final Constants this$0;

        public SharedPreferences()
        {
            this$0 = Constants.this;
            super();
        }
    }


    public Constants()
    {
    }

    public static final int MAX_FLUSH = 20;
    public static final String PACKAGE_NAME = io/segment/android/Constants.getPackage().getName();
    public static final String TAG = "analytics";

}
