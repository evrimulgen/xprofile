// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind;

import java.io.Serializable;

public class PropertyMetadata
    implements Serializable
{

    protected PropertyMetadata(Boolean boolean1, String s)
    {
        _required = boolean1;
        _description = s;
    }

    public static PropertyMetadata construct(boolean flag, String s)
    {
        PropertyMetadata propertymetadata;
        if(flag)
            propertymetadata = STD_REQUIRED;
        else
            propertymetadata = STD_OPTIONAL;
        if(s != null)
            propertymetadata = propertymetadata.withDescription(s);
        return propertymetadata;
    }

    public String getDescription()
    {
        return _description;
    }

    public Boolean getRequired()
    {
        return _required;
    }

    public boolean isRequired()
    {
        return _required != null && _required.booleanValue();
    }

    protected Object readResolve()
    {
        if(_description == null)
        {
            if(_required == null)
                return STD_REQUIRED_OR_OPTIONAL;
            if(_required.booleanValue())
                return STD_REQUIRED;
            else
                return STD_OPTIONAL;
        } else
        {
            return this;
        }
    }

    public PropertyMetadata withDescription(String s)
    {
        return new PropertyMetadata(_required, s);
    }

    public PropertyMetadata withRequired(Boolean boolean1)
    {
        if(boolean1 != null ? _required != null && _required.booleanValue() == boolean1.booleanValue() : _required == null)
            return this;
        else
            return new PropertyMetadata(boolean1, _description);
    }

    public static final PropertyMetadata STD_OPTIONAL;
    public static final PropertyMetadata STD_REQUIRED;
    public static final PropertyMetadata STD_REQUIRED_OR_OPTIONAL = new PropertyMetadata(null, null);
    private static final long serialVersionUID = -1L;
    protected final String _description;
    protected final Boolean _required;

    static 
    {
        STD_REQUIRED = new PropertyMetadata(Boolean.TRUE, null);
        STD_OPTIONAL = new PropertyMetadata(Boolean.FALSE, null);
    }
}
