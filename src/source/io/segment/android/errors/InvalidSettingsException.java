// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package io.segment.android.errors;


public class InvalidSettingsException extends Exception
{

    public InvalidSettingsException(String s)
    {
        super(String.format("Provider requires the setting '%s'.", new Object[] {
            s
        }));
        setting = s;
    }

    public InvalidSettingsException(String s, String s1)
    {
        super(String.format("Provider requires the setting '%s': %s", new Object[] {
            s, s1
        }));
        setting = s;
    }

    public String getSetting()
    {
        return setting;
    }

    private static final long serialVersionUID = 0x7a347bdb5e9d2084L;
    private String setting;
}
