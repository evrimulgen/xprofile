// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.com.google.gson;

import java.lang.reflect.Field;

public interface FieldNamingStrategy
{

    public abstract String translateName(Field field);
}
