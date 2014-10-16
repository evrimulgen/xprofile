// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.facebook;


public final class SessionState extends Enum
{
    private static final class Category extends Enum
    {

        public static Category valueOf(String s)
        {
            return (Category)Enum.valueOf(com/facebook/SessionState$Category, s);
        }

        public static Category[] values()
        {
            return (Category[])$VALUES.clone();
        }

        private static final Category $VALUES[];
        public static final Category CLOSED_CATEGORY;
        public static final Category CREATED_CATEGORY;
        public static final Category OPENED_CATEGORY;

        static 
        {
            CREATED_CATEGORY = new Category("CREATED_CATEGORY", 0);
            OPENED_CATEGORY = new Category("OPENED_CATEGORY", 1);
            CLOSED_CATEGORY = new Category("CLOSED_CATEGORY", 2);
            Category acategory[] = new Category[3];
            acategory[0] = CREATED_CATEGORY;
            acategory[1] = OPENED_CATEGORY;
            acategory[2] = CLOSED_CATEGORY;
            $VALUES = acategory;
        }

        private Category(String s, int i)
        {
            super(s, i);
        }
    }


    private SessionState(String s, int i, Category category1)
    {
        super(s, i);
        category = category1;
    }

    public static SessionState valueOf(String s)
    {
        return (SessionState)Enum.valueOf(com/facebook/SessionState, s);
    }

    public static SessionState[] values()
    {
        return (SessionState[])$VALUES.clone();
    }

    public boolean isClosed()
    {
        return category == Category.CLOSED_CATEGORY;
    }

    public boolean isOpened()
    {
        return category == Category.OPENED_CATEGORY;
    }

    private static final SessionState $VALUES[];
    public static final SessionState CLOSED;
    public static final SessionState CLOSED_LOGIN_FAILED;
    public static final SessionState CREATED;
    public static final SessionState CREATED_TOKEN_LOADED;
    public static final SessionState OPENED;
    public static final SessionState OPENED_TOKEN_UPDATED;
    public static final SessionState OPENING;
    private final Category category;

    static 
    {
        CREATED = new SessionState("CREATED", 0, Category.CREATED_CATEGORY);
        CREATED_TOKEN_LOADED = new SessionState("CREATED_TOKEN_LOADED", 1, Category.CREATED_CATEGORY);
        OPENING = new SessionState("OPENING", 2, Category.CREATED_CATEGORY);
        OPENED = new SessionState("OPENED", 3, Category.OPENED_CATEGORY);
        OPENED_TOKEN_UPDATED = new SessionState("OPENED_TOKEN_UPDATED", 4, Category.OPENED_CATEGORY);
        CLOSED_LOGIN_FAILED = new SessionState("CLOSED_LOGIN_FAILED", 5, Category.CLOSED_CATEGORY);
        CLOSED = new SessionState("CLOSED", 6, Category.CLOSED_CATEGORY);
        SessionState asessionstate[] = new SessionState[7];
        asessionstate[0] = CREATED;
        asessionstate[1] = CREATED_TOKEN_LOADED;
        asessionstate[2] = OPENING;
        asessionstate[3] = OPENED;
        asessionstate[4] = OPENED_TOKEN_UPDATED;
        asessionstate[5] = CLOSED_LOGIN_FAILED;
        asessionstate[6] = CLOSED;
        $VALUES = asessionstate;
    }
}
