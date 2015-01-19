// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.com.google.gson.stream;

import java.io.*;

public class JsonWriter
    implements Closeable, Flushable
{

    public JsonWriter(Writer writer)
    {
        stack = new int[32];
        stackSize = 0;
        push(6);
        separator = ":";
        serializeNulls = true;
        if(writer == null)
        {
            throw new NullPointerException("out == null");
        } else
        {
            out = writer;
            return;
        }
    }

    private void beforeName()
        throws IOException
    {
        int i = peek();
        if(i == 5)
            out.write(44);
        else
        if(i != 3)
            throw new IllegalStateException("Nesting problem.");
        newline();
        replaceTop(4);
    }

    private void beforeValue(boolean flag)
        throws IOException
    {
        switch(peek())
        {
        case 3: // '\003'
        case 5: // '\005'
        default:
            throw new IllegalStateException("Nesting problem.");

        case 7: // '\007'
            if(!lenient)
                throw new IllegalStateException("JSON must have only one top-level value.");
            // fall through

        case 6: // '\006'
            if(!lenient && !flag)
            {
                throw new IllegalStateException("JSON must start with an array or an object.");
            } else
            {
                replaceTop(7);
                return;
            }

        case 1: // '\001'
            replaceTop(2);
            newline();
            return;

        case 2: // '\002'
            out.append(',');
            newline();
            return;

        case 4: // '\004'
            out.append(separator);
            replaceTop(5);
            return;
        }
    }

    private JsonWriter close(int i, int j, String s)
        throws IOException
    {
        int k = peek();
        if(k != j && k != i)
            throw new IllegalStateException("Nesting problem.");
        if(deferredName != null)
            throw new IllegalStateException((new StringBuilder()).append("Dangling name: ").append(deferredName).toString());
        stackSize = -1 + stackSize;
        if(k == j)
            newline();
        out.write(s);
        return this;
    }

    private void newline()
        throws IOException
    {
        if(indent != null)
        {
            out.write("\n");
            int i = 1;
            int j = stackSize;
            while(i < j) 
            {
                out.write(indent);
                i++;
            }
        }
    }

    private JsonWriter open(int i, String s)
        throws IOException
    {
        beforeValue(true);
        push(i);
        out.write(s);
        return this;
    }

    private int peek()
    {
        if(stackSize == 0)
            throw new IllegalStateException("JsonWriter is closed.");
        else
            return stack[-1 + stackSize];
    }

    private void push(int i)
    {
        if(stackSize == stack.length)
        {
            int ai1[] = new int[2 * stackSize];
            System.arraycopy(stack, 0, ai1, 0, stackSize);
            stack = ai1;
        }
        int ai[] = stack;
        int j = stackSize;
        stackSize = j + 1;
        ai[j] = i;
    }

    private void replaceTop(int i)
    {
        stack[-1 + stackSize] = i;
    }

    private void string(String s)
        throws IOException
    {
        int i;
        int j;
        int k;
        char c;
        String s1;
        String as[];
        if(htmlSafe)
            as = HTML_SAFE_REPLACEMENT_CHARS;
        else
            as = REPLACEMENT_CHARS;
        out.write("\"");
        i = 0;
        j = s.length();
        k = 0;
        if(k >= j)
            break MISSING_BLOCK_LABEL_141;
        c = s.charAt(k);
        if(c >= '\200') goto _L2; else goto _L1
_L1:
        s1 = as[c];
        if(s1 != null) goto _L4; else goto _L3
_L3:
        k++;
        break MISSING_BLOCK_LABEL_31;
_L2:
        if(c != '\u2028')
            continue; /* Loop/switch isn't completed */
        s1 = "\\u2028";
_L4:
        if(i < k)
            out.write(s, i, k - i);
        out.write(s1);
        i = k + 1;
        if(true) goto _L3; else goto _L5
_L5:
        if(c != '\u2029') goto _L3; else goto _L6
_L6:
        s1 = "\\u2029";
          goto _L4
        if(i < j)
            out.write(s, i, j - i);
        out.write("\"");
        return;
    }

    private void writeDeferredName()
        throws IOException
    {
        if(deferredName != null)
        {
            beforeName();
            string(deferredName);
            deferredName = null;
        }
    }

    public JsonWriter beginArray()
        throws IOException
    {
        writeDeferredName();
        return open(1, "[");
    }

    public JsonWriter beginObject()
        throws IOException
    {
        writeDeferredName();
        return open(3, "{");
    }

    public void close()
        throws IOException
    {
        out.close();
        int i = stackSize;
        if(i > 1 || i == 1 && stack[i - 1] != 7)
        {
            throw new IOException("Incomplete document");
        } else
        {
            stackSize = 0;
            return;
        }
    }

    public JsonWriter endArray()
        throws IOException
    {
        return close(1, 2, "]");
    }

    public JsonWriter endObject()
        throws IOException
    {
        return close(3, 5, "}");
    }

    public void flush()
        throws IOException
    {
        if(stackSize == 0)
        {
            throw new IllegalStateException("JsonWriter is closed.");
        } else
        {
            out.flush();
            return;
        }
    }

    public final boolean getSerializeNulls()
    {
        return serializeNulls;
    }

    public final boolean isHtmlSafe()
    {
        return htmlSafe;
    }

    public boolean isLenient()
    {
        return lenient;
    }

    public JsonWriter name(String s)
        throws IOException
    {
        if(s == null)
            throw new NullPointerException("name == null");
        if(deferredName != null)
            throw new IllegalStateException();
        if(stackSize == 0)
        {
            throw new IllegalStateException("JsonWriter is closed.");
        } else
        {
            deferredName = s;
            return this;
        }
    }

    public JsonWriter nullValue()
        throws IOException
    {
label0:
        {
            if(deferredName != null)
            {
                if(!serializeNulls)
                    break label0;
                writeDeferredName();
            }
            beforeValue(false);
            out.write("null");
            return this;
        }
        deferredName = null;
        return this;
    }

    public final void setHtmlSafe(boolean flag)
    {
        htmlSafe = flag;
    }

    public final void setIndent(String s)
    {
        if(s.length() == 0)
        {
            indent = null;
            separator = ":";
            return;
        } else
        {
            indent = s;
            separator = ": ";
            return;
        }
    }

    public final void setLenient(boolean flag)
    {
        lenient = flag;
    }

    public final void setSerializeNulls(boolean flag)
    {
        serializeNulls = flag;
    }

    public JsonWriter value(double d)
        throws IOException
    {
        if(Double.isNaN(d) || Double.isInfinite(d))
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Numeric values must be finite, but was ").append(d).toString());
        } else
        {
            writeDeferredName();
            beforeValue(false);
            out.append(Double.toString(d));
            return this;
        }
    }

    public JsonWriter value(long l)
        throws IOException
    {
        writeDeferredName();
        beforeValue(false);
        out.write(Long.toString(l));
        return this;
    }

    public JsonWriter value(Number number)
        throws IOException
    {
        if(number == null)
            return nullValue();
        writeDeferredName();
        String s = number.toString();
        if(!lenient && (s.equals("-Infinity") || s.equals("Infinity") || s.equals("NaN")))
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Numeric values must be finite, but was ").append(number).toString());
        } else
        {
            beforeValue(false);
            out.append(s);
            return this;
        }
    }

    public JsonWriter value(String s)
        throws IOException
    {
        if(s == null)
        {
            return nullValue();
        } else
        {
            writeDeferredName();
            beforeValue(false);
            string(s);
            return this;
        }
    }

    public JsonWriter value(boolean flag)
        throws IOException
    {
        writeDeferredName();
        beforeValue(false);
        Writer writer = out;
        String s;
        if(flag)
            s = "true";
        else
            s = "false";
        writer.write(s);
        return this;
    }

    private static final String HTML_SAFE_REPLACEMENT_CHARS[];
    private static final String REPLACEMENT_CHARS[];
    private String deferredName;
    private boolean htmlSafe;
    private String indent;
    private boolean lenient;
    private final Writer out;
    private String separator;
    private boolean serializeNulls;
    private int stack[];
    private int stackSize;

    static 
    {
        REPLACEMENT_CHARS = new String[128];
        for(int i = 0; i <= 31; i++)
        {
            String as[] = REPLACEMENT_CHARS;
            Object aobj[] = new Object[1];
            aobj[0] = Integer.valueOf(i);
            as[i] = String.format("\\u%04x", aobj);
        }

        REPLACEMENT_CHARS[34] = "\\\"";
        REPLACEMENT_CHARS[92] = "\\\\";
        REPLACEMENT_CHARS[9] = "\\t";
        REPLACEMENT_CHARS[8] = "\\b";
        REPLACEMENT_CHARS[10] = "\\n";
        REPLACEMENT_CHARS[13] = "\\r";
        REPLACEMENT_CHARS[12] = "\\f";
        HTML_SAFE_REPLACEMENT_CHARS = (String[])REPLACEMENT_CHARS.clone();
        HTML_SAFE_REPLACEMENT_CHARS[60] = "\\u003c";
        HTML_SAFE_REPLACEMENT_CHARS[62] = "\\u003e";
        HTML_SAFE_REPLACEMENT_CHARS[38] = "\\u0026";
        HTML_SAFE_REPLACEMENT_CHARS[61] = "\\u003d";
        HTML_SAFE_REPLACEMENT_CHARS[39] = "\\u0027";
    }
}
