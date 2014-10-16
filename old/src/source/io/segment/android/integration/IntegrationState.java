// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package io.segment.android.integration;


public final class IntegrationState extends Enum
{

    private IntegrationState(String s, int i, int j)
    {
        super(s, i);
        value = j;
    }

    public static IntegrationState valueOf(String s)
    {
        return (IntegrationState)Enum.valueOf(io/segment/android/integration/IntegrationState, s);
    }

    public static IntegrationState[] values()
    {
        IntegrationState aintegrationstate[] = ENUM$VALUES;
        int i = aintegrationstate.length;
        IntegrationState aintegrationstate1[] = new IntegrationState[i];
        System.arraycopy(aintegrationstate, 0, aintegrationstate1, 0, i);
        return aintegrationstate1;
    }

    public boolean ge(IntegrationState integrationstate)
    {
        return value >= integrationstate.value;
    }

    public int getValue()
    {
        return value;
    }

    public static final IntegrationState DISABLED;
    public static final IntegrationState ENABLED;
    private static final IntegrationState ENUM$VALUES[];
    public static final IntegrationState INITIALIZED;
    public static final IntegrationState INVALID;
    public static final IntegrationState NOT_INITIALIZED;
    public static final IntegrationState READY;
    private final int value;

    static 
    {
        NOT_INITIALIZED = new IntegrationState("NOT_INITIALIZED", 0, 0);
        INVALID = new IntegrationState("INVALID", 1, 1);
        INITIALIZED = new IntegrationState("INITIALIZED", 2, 2);
        DISABLED = new IntegrationState("DISABLED", 3, 3);
        ENABLED = new IntegrationState("ENABLED", 4, 4);
        READY = new IntegrationState("READY", 5, 5);
        IntegrationState aintegrationstate[] = new IntegrationState[6];
        aintegrationstate[0] = NOT_INITIALIZED;
        aintegrationstate[1] = INVALID;
        aintegrationstate[2] = INITIALIZED;
        aintegrationstate[3] = DISABLED;
        aintegrationstate[4] = ENABLED;
        aintegrationstate[5] = READY;
        ENUM$VALUES = aintegrationstate;
    }
}
