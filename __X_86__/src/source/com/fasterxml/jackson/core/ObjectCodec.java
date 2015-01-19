// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.core;

import com.fasterxml.jackson.core.type.ResolvedType;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.IOException;
import java.util.Iterator;

// Referenced classes of package com.fasterxml.jackson.core:
//            TreeCodec, Versioned, JsonProcessingException, Version, 
//            TreeNode, JsonFactory, JsonParser, JsonGenerator

public abstract class ObjectCodec extends TreeCodec
    implements Versioned
{

    protected ObjectCodec()
    {
    }

    public abstract TreeNode createArrayNode();

    public abstract TreeNode createObjectNode();

    public JsonFactory getFactory()
    {
        return getJsonFactory();
    }

    public abstract JsonFactory getJsonFactory();

    public abstract TreeNode readTree(JsonParser jsonparser)
        throws IOException, JsonProcessingException;

    public abstract Object readValue(JsonParser jsonparser, ResolvedType resolvedtype)
        throws IOException, JsonProcessingException;

    public abstract Object readValue(JsonParser jsonparser, TypeReference typereference)
        throws IOException, JsonProcessingException;

    public abstract Object readValue(JsonParser jsonparser, Class class1)
        throws IOException, JsonProcessingException;

    public abstract Iterator readValues(JsonParser jsonparser, ResolvedType resolvedtype)
        throws IOException, JsonProcessingException;

    public abstract Iterator readValues(JsonParser jsonparser, TypeReference typereference)
        throws IOException, JsonProcessingException;

    public abstract Iterator readValues(JsonParser jsonparser, Class class1)
        throws IOException, JsonProcessingException;

    public abstract JsonParser treeAsTokens(TreeNode treenode);

    public abstract Object treeToValue(TreeNode treenode, Class class1)
        throws JsonProcessingException;

    public Version version()
    {
        return Version.unknownVersion();
    }

    public abstract void writeTree(JsonGenerator jsongenerator, TreeNode treenode)
        throws IOException, JsonProcessingException;

    public abstract void writeValue(JsonGenerator jsongenerator, Object obj)
        throws IOException, JsonProcessingException;
}
