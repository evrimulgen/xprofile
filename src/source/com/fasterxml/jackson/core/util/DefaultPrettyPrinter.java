// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.core.util;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.io.SerializedString;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;

// Referenced classes of package com.fasterxml.jackson.core.util:
//            Instantiatable

public class DefaultPrettyPrinter
    implements PrettyPrinter, Instantiatable, Serializable
{
    public static class FixedSpaceIndenter extends NopIndenter
    {

        public boolean isInline()
        {
            return true;
        }

        public void writeIndentation(JsonGenerator jsongenerator, int i)
            throws IOException, JsonGenerationException
        {
            jsongenerator.writeRaw(' ');
        }

        public static final FixedSpaceIndenter instance = new FixedSpaceIndenter();


        public FixedSpaceIndenter()
        {
        }
    }

    public static interface Indenter
    {

        public abstract boolean isInline();

        public abstract void writeIndentation(JsonGenerator jsongenerator, int i)
            throws IOException, JsonGenerationException;
    }

    public static class Lf2SpacesIndenter extends NopIndenter
    {

        public boolean isInline()
        {
            return false;
        }

        public Lf2SpacesIndenter withLinefeed(String s)
        {
            if(s.equals(_lf))
                return this;
            else
                return new Lf2SpacesIndenter(s);
        }

        public void writeIndentation(JsonGenerator jsongenerator, int i)
            throws IOException, JsonGenerationException
        {
            jsongenerator.writeRaw(_lf);
            if(i > 0)
            {
                int j;
                for(j = i + i; j > 64; j -= SPACES.length)
                    jsongenerator.writeRaw(SPACES, 0, 64);

                jsongenerator.writeRaw(SPACES, 0, j);
            }
        }

        static final char SPACES[];
        static final int SPACE_COUNT = 64;
        private static final String SYS_LF;
        public static final Lf2SpacesIndenter instance;
        protected final String _lf;

        static 
        {
            String s1 = System.getProperty("line.separator");
            String s = s1;
_L2:
            if(s == null)
                s = "\n";
            SYS_LF = s;
            SPACES = new char[64];
            Arrays.fill(SPACES, ' ');
            instance = new Lf2SpacesIndenter();
            return;
            Throwable throwable;
            throwable;
            s = null;
            if(true) goto _L2; else goto _L1
_L1:
        }

        public Lf2SpacesIndenter()
        {
            this(SYS_LF);
        }

        public Lf2SpacesIndenter(String s)
        {
            _lf = s;
        }
    }

    public static class NopIndenter
        implements Indenter, Serializable
    {

        public boolean isInline()
        {
            return true;
        }

        public void writeIndentation(JsonGenerator jsongenerator, int i)
            throws IOException, JsonGenerationException
        {
        }

        public static final NopIndenter instance = new NopIndenter();


        public NopIndenter()
        {
        }
    }


    public DefaultPrettyPrinter()
    {
        this(((SerializableString) (DEFAULT_ROOT_VALUE_SEPARATOR)));
    }

    public DefaultPrettyPrinter(SerializableString serializablestring)
    {
        _arrayIndenter = FixedSpaceIndenter.instance;
        _objectIndenter = Lf2SpacesIndenter.instance;
        _spacesInObjectEntries = true;
        _nesting = 0;
        _rootSeparator = serializablestring;
    }

    public DefaultPrettyPrinter(DefaultPrettyPrinter defaultprettyprinter)
    {
        this(defaultprettyprinter, defaultprettyprinter._rootSeparator);
    }

    public DefaultPrettyPrinter(DefaultPrettyPrinter defaultprettyprinter, SerializableString serializablestring)
    {
        _arrayIndenter = FixedSpaceIndenter.instance;
        _objectIndenter = Lf2SpacesIndenter.instance;
        _spacesInObjectEntries = true;
        _nesting = 0;
        _arrayIndenter = defaultprettyprinter._arrayIndenter;
        _objectIndenter = defaultprettyprinter._objectIndenter;
        _spacesInObjectEntries = defaultprettyprinter._spacesInObjectEntries;
        _nesting = defaultprettyprinter._nesting;
        _rootSeparator = serializablestring;
    }

    public DefaultPrettyPrinter(String s)
    {
        Object obj;
        if(s == null)
            obj = null;
        else
            obj = new SerializedString(s);
        this(((SerializableString) (obj)));
    }

    protected DefaultPrettyPrinter _withSpaces(boolean flag)
    {
        if(_spacesInObjectEntries == flag)
        {
            return this;
        } else
        {
            DefaultPrettyPrinter defaultprettyprinter = new DefaultPrettyPrinter(this);
            defaultprettyprinter._spacesInObjectEntries = flag;
            return defaultprettyprinter;
        }
    }

    public void beforeArrayValues(JsonGenerator jsongenerator)
        throws IOException, JsonGenerationException
    {
        _arrayIndenter.writeIndentation(jsongenerator, _nesting);
    }

    public void beforeObjectEntries(JsonGenerator jsongenerator)
        throws IOException, JsonGenerationException
    {
        _objectIndenter.writeIndentation(jsongenerator, _nesting);
    }

    public DefaultPrettyPrinter createInstance()
    {
        return new DefaultPrettyPrinter(this);
    }

    public volatile Object createInstance()
    {
        return createInstance();
    }

    public void indentArraysWith(Indenter indenter)
    {
        if(indenter == null)
            indenter = NopIndenter.instance;
        _arrayIndenter = indenter;
    }

    public void indentObjectsWith(Indenter indenter)
    {
        if(indenter == null)
            indenter = NopIndenter.instance;
        _objectIndenter = indenter;
    }

    public void spacesInObjectEntries(boolean flag)
    {
        _spacesInObjectEntries = flag;
    }

    public DefaultPrettyPrinter withArrayIndenter(Indenter indenter)
    {
        if(indenter == null)
            indenter = NopIndenter.instance;
        if(_arrayIndenter == indenter)
        {
            return this;
        } else
        {
            DefaultPrettyPrinter defaultprettyprinter = new DefaultPrettyPrinter(this);
            defaultprettyprinter._arrayIndenter = indenter;
            return defaultprettyprinter;
        }
    }

    public DefaultPrettyPrinter withObjectIndenter(Indenter indenter)
    {
        if(indenter == null)
            indenter = NopIndenter.instance;
        if(_objectIndenter == indenter)
        {
            return this;
        } else
        {
            DefaultPrettyPrinter defaultprettyprinter = new DefaultPrettyPrinter(this);
            defaultprettyprinter._objectIndenter = indenter;
            return defaultprettyprinter;
        }
    }

    public DefaultPrettyPrinter withRootSeparator(SerializableString serializablestring)
    {
        if(_rootSeparator == serializablestring || serializablestring != null && serializablestring.equals(_rootSeparator))
            return this;
        else
            return new DefaultPrettyPrinter(this, serializablestring);
    }

    public DefaultPrettyPrinter withSpacesInObjectEntries()
    {
        return _withSpaces(true);
    }

    public DefaultPrettyPrinter withoutSpacesInObjectEntries()
    {
        return _withSpaces(false);
    }

    public void writeArrayValueSeparator(JsonGenerator jsongenerator)
        throws IOException, JsonGenerationException
    {
        jsongenerator.writeRaw(',');
        _arrayIndenter.writeIndentation(jsongenerator, _nesting);
    }

    public void writeEndArray(JsonGenerator jsongenerator, int i)
        throws IOException, JsonGenerationException
    {
        if(!_arrayIndenter.isInline())
            _nesting = -1 + _nesting;
        if(i > 0)
            _arrayIndenter.writeIndentation(jsongenerator, _nesting);
        else
            jsongenerator.writeRaw(' ');
        jsongenerator.writeRaw(']');
    }

    public void writeEndObject(JsonGenerator jsongenerator, int i)
        throws IOException, JsonGenerationException
    {
        if(!_objectIndenter.isInline())
            _nesting = -1 + _nesting;
        if(i > 0)
            _objectIndenter.writeIndentation(jsongenerator, _nesting);
        else
            jsongenerator.writeRaw(' ');
        jsongenerator.writeRaw('}');
    }

    public void writeObjectEntrySeparator(JsonGenerator jsongenerator)
        throws IOException, JsonGenerationException
    {
        jsongenerator.writeRaw(',');
        _objectIndenter.writeIndentation(jsongenerator, _nesting);
    }

    public void writeObjectFieldValueSeparator(JsonGenerator jsongenerator)
        throws IOException, JsonGenerationException
    {
        if(_spacesInObjectEntries)
        {
            jsongenerator.writeRaw(" : ");
            return;
        } else
        {
            jsongenerator.writeRaw(':');
            return;
        }
    }

    public void writeRootValueSeparator(JsonGenerator jsongenerator)
        throws IOException, JsonGenerationException
    {
        if(_rootSeparator != null)
            jsongenerator.writeRaw(_rootSeparator);
    }

    public void writeStartArray(JsonGenerator jsongenerator)
        throws IOException, JsonGenerationException
    {
        if(!_arrayIndenter.isInline())
            _nesting = 1 + _nesting;
        jsongenerator.writeRaw('[');
    }

    public void writeStartObject(JsonGenerator jsongenerator)
        throws IOException, JsonGenerationException
    {
        jsongenerator.writeRaw('{');
        if(!_objectIndenter.isInline())
            _nesting = 1 + _nesting;
    }

    public static final SerializedString DEFAULT_ROOT_VALUE_SEPARATOR = new SerializedString(" ");
    private static final long serialVersionUID = 0xb37f5ba7a3142563L;
    protected Indenter _arrayIndenter;
    protected transient int _nesting;
    protected Indenter _objectIndenter;
    protected final SerializableString _rootSeparator;
    protected boolean _spacesInObjectEntries;

}
