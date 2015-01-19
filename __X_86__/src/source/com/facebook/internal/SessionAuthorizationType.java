// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.facebook.internal;


public final class SessionAuthorizationType extends Enum
{

    private SessionAuthorizationType(String s, int i)
    {
        super(s, i);
    }

    public static SessionAuthorizationType valueOf(String s)
    {
        return (SessionAuthorizationType)Enum.valueOf(com/facebook/internal/SessionAuthorizationType, s);
    }

    public static SessionAuthorizationType[] values()
    {
        return (SessionAuthorizationType[])$VALUES.clone();
    }

    private static final SessionAuthorizationType $VALUES[];
    public static final SessionAuthorizationType PUBLISH;
    public static final SessionAuthorizationType READ;

    static 
    {
        READ = new SessionAuthorizationType("READ", 0);
        PUBLISH = new SessionAuthorizationType("PUBLISH", 1);
        SessionAuthorizationType asessionauthorizationtype[] = new SessionAuthorizationType[2];
        asessionauthorizationtype[0] = READ;
        asessionauthorizationtype[1] = PUBLISH;
        $VALUES = asessionauthorizationtype;
    }
}
