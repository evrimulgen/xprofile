// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.ext;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.FromStringDeserializer;
import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

public abstract class DOMDeserializer extends FromStringDeserializer
{
    public static class DocumentDeserializer extends DOMDeserializer
    {

        public volatile Object _deserialize(String s, DeserializationContext deserializationcontext)
        {
            return _deserialize(s, deserializationcontext);
        }

        public Document _deserialize(String s, DeserializationContext deserializationcontext)
            throws IllegalArgumentException
        {
            return parse(s);
        }

        private static final long serialVersionUID = 1L;

        public DocumentDeserializer()
        {
            super(org/w3c/dom/Document);
        }
    }

    public static class NodeDeserializer extends DOMDeserializer
    {

        public volatile Object _deserialize(String s, DeserializationContext deserializationcontext)
        {
            return _deserialize(s, deserializationcontext);
        }

        public Node _deserialize(String s, DeserializationContext deserializationcontext)
            throws IllegalArgumentException
        {
            return parse(s);
        }

        private static final long serialVersionUID = 1L;

        public NodeDeserializer()
        {
            super(org/w3c/dom/Node);
        }
    }


    protected DOMDeserializer(Class class1)
    {
        super(class1);
    }

    public abstract Object _deserialize(String s, DeserializationContext deserializationcontext);

    protected final Document parse(String s)
        throws IllegalArgumentException
    {
        Document document;
        try
        {
            document = _parserFactory.newDocumentBuilder().parse(new InputSource(new StringReader(s)));
        }
        catch(Exception exception)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Failed to parse JSON String as XML: ").append(exception.getMessage()).toString(), exception);
        }
        return document;
    }

    private static final DocumentBuilderFactory _parserFactory;
    private static final long serialVersionUID = 1L;

    static 
    {
        _parserFactory = DocumentBuilderFactory.newInstance();
        _parserFactory.setNamespaceAware(true);
    }
}
