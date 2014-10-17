// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.com.google.gson.stream;

import com.newrelic.com.google.gson.internal.JsonReaderInternalAccess;
import com.newrelic.com.google.gson.internal.bind.JsonTreeReader;
import java.io.*;

// Referenced classes of package com.newrelic.com.google.gson.stream:
//            MalformedJsonException, JsonToken

public class JsonReader
    implements Closeable
{

    public JsonReader(Reader reader)
    {
        lenient = false;
        pos = 0;
        limit = 0;
        lineNumber = 0;
        lineStart = 0;
        peeked = 0;
        stack = new int[32];
        stackSize = 0;
        int ai[] = stack;
        int i = stackSize;
        stackSize = i + 1;
        ai[i] = 6;
        if(reader == null)
        {
            throw new NullPointerException("in == null");
        } else
        {
            in = reader;
            return;
        }
    }

    private void checkLenient()
        throws IOException
    {
        if(!lenient)
            throw syntaxError("Use JsonReader.setLenient(true) to accept malformed JSON");
        else
            return;
    }

    private void consumeNonExecutePrefix()
        throws IOException
    {
        nextNonWhitespace(true);
        pos = -1 + pos;
        if(pos + NON_EXECUTE_PREFIX.length <= limit || fillBuffer(NON_EXECUTE_PREFIX.length)) goto _L2; else goto _L1
_L1:
        return;
_L2:
        int i = 0;
label0:
        do
        {
label1:
            {
                if(i >= NON_EXECUTE_PREFIX.length)
                    break label1;
                if(buffer[i + pos] != NON_EXECUTE_PREFIX[i])
                    break label0;
                i++;
            }
        } while(true);
        if(true) goto _L1; else goto _L3
_L3:
        pos = pos + NON_EXECUTE_PREFIX.length;
        return;
    }

    private int doPeek()
        throws IOException
    {
        int i = stack[-1 + stackSize];
        if(i != 1) goto _L2; else goto _L1
_L1:
        stack[-1 + stackSize] = 2;
_L14:
        nextNonWhitespace(true);
        JVM INSTR lookupswitch 7: default 100
    //                   34: 668
    //                   39: 655
    //                   44: 615
    //                   59: 615
    //                   91: 689
    //                   93: 603
    //                   123: 696;
           goto _L3 _L4 _L5 _L6 _L6 _L7 _L8 _L9
_L3:
        int k;
        pos = -1 + pos;
        if(stackSize == 1)
            checkLenient();
        k = peekKeyword();
        if(k == 0) goto _L11; else goto _L10
_L10:
        return k;
_L2:
        if(i == 2)
        {
            switch(nextNonWhitespace(true))
            {
            default:
                throw syntaxError("Unterminated array");

            case 93: // ']'
                peeked = 4;
                return 4;

            case 59: // ';'
                checkLenient();
                break;

            case 44: // ','
                break;
            }
        } else
        {
            if(i == 3 || i == 5)
            {
                stack[-1 + stackSize] = 4;
                if(i == 5)
                    switch(nextNonWhitespace(true))
                    {
                    default:
                        throw syntaxError("Unterminated object");

                    case 125: // '}'
                        peeked = 2;
                        return 2;

                    case 59: // ';'
                        checkLenient();
                        break;

                    case 44: // ','
                        break;
                    }
                int j = nextNonWhitespace(true);
                switch(j)
                {
                default:
                    checkLenient();
                    pos = -1 + pos;
                    if(isLiteral((char)j))
                    {
                        peeked = 14;
                        return 14;
                    } else
                    {
                        throw syntaxError("Expected name");
                    }

                case 34: // '"'
                    peeked = 13;
                    return 13;

                case 39: // '\''
                    checkLenient();
                    peeked = 12;
                    return 12;

                case 125: // '}'
                    break;
                }
                if(i != 5)
                {
                    peeked = 2;
                    return 2;
                } else
                {
                    throw syntaxError("Expected name");
                }
            }
            if(i == 4)
            {
                stack[-1 + stackSize] = 5;
                switch(nextNonWhitespace(true))
                {
                case 59: // ';'
                case 60: // '<'
                default:
                    throw syntaxError("Expected ':'");

                case 61: // '='
                    checkLenient();
                    if((pos < limit || fillBuffer(1)) && buffer[pos] == '>')
                        pos = 1 + pos;
                    break;

                case 58: // ':'
                    break;
                }
            } else
            if(i == 6)
            {
                if(lenient)
                    consumeNonExecutePrefix();
                stack[-1 + stackSize] = 7;
            } else
            if(i == 7)
            {
                if(nextNonWhitespace(false) == -1)
                {
                    peeked = 17;
                    return 17;
                }
                checkLenient();
                pos = -1 + pos;
            } else
            if(i == 8)
                throw new IllegalStateException("JsonReader is closed");
        }
        continue; /* Loop/switch isn't completed */
_L8:
        if(i == 1)
        {
            peeked = 4;
            return 4;
        }
_L6:
        if(i == 1 || i == 2)
        {
            checkLenient();
            pos = -1 + pos;
            peeked = 7;
            return 7;
        } else
        {
            throw syntaxError("Unexpected value");
        }
_L5:
        checkLenient();
        peeked = 8;
        return 8;
_L4:
        if(stackSize == 1)
            checkLenient();
        peeked = 9;
        return 9;
_L7:
        peeked = 3;
        return 3;
_L9:
        peeked = 1;
        return 1;
_L11:
        if((k = peekNumber()) != 0) goto _L10; else goto _L12
_L12:
        if(!isLiteral(buffer[pos]))
        {
            throw syntaxError("Expected value");
        } else
        {
            checkLenient();
            peeked = 10;
            return 10;
        }
        if(true) goto _L14; else goto _L13
_L13:
    }

    private boolean fillBuffer(int i)
        throws IOException
    {
        char ac[] = buffer;
        lineStart = lineStart - pos;
        boolean flag;
        if(limit != pos)
        {
            limit = limit - pos;
            System.arraycopy(ac, pos, ac, 0, limit);
        } else
        {
            limit = 0;
        }
        pos = 0;
        do
        {
            int j = in.read(ac, limit, ac.length - limit);
            flag = false;
            if(j == -1)
                break;
            limit = j + limit;
            if(lineNumber == 0 && lineStart == 0 && limit > 0 && ac[0] == '\uFEFF')
            {
                pos = 1 + pos;
                lineStart = 1 + lineStart;
                i++;
            }
            if(limit < i)
                continue;
            flag = true;
            break;
        } while(true);
        return flag;
    }

    private int getColumnNumber()
    {
        return 1 + (pos - lineStart);
    }

    private int getLineNumber()
    {
        return 1 + lineNumber;
    }

    private boolean isLiteral(char c)
        throws IOException
    {
        switch(c)
        {
        default:
            return true;

        case 35: // '#'
        case 47: // '/'
        case 59: // ';'
        case 61: // '='
        case 92: // '\\'
            checkLenient();
            // fall through

        case 9: // '\t'
        case 10: // '\n'
        case 12: // '\f'
        case 13: // '\r'
        case 32: // ' '
        case 44: // ','
        case 58: // ':'
        case 91: // '['
        case 93: // ']'
        case 123: // '{'
        case 125: // '}'
            return false;
        }
    }

    private int nextNonWhitespace(boolean flag)
        throws IOException
    {
        char ac[];
        int i;
        int j;
        ac = buffer;
        i = pos;
        j = limit;
_L2:
        int k;
        do
        {
            if(i == j)
            {
                pos = i;
                char c;
                boolean flag1;
                if(!fillBuffer(1))
                    if(flag)
                        throw new EOFException((new StringBuilder()).append("End of input at line ").append(getLineNumber()).append(" column ").append(getColumnNumber()).toString());
                    else
                        return -1;
                i = pos;
                j = limit;
            }
            k = i + 1;
            c = ac[i];
            if(c == '\n')
            {
                lineNumber = 1 + lineNumber;
                lineStart = k;
                i = k;
            } else
            {
label0:
                {
                    if(c == ' ' || c == '\r')
                        break label0;
                    if(c == '\t')
                    {
                        i = k;
                    } else
                    {
                        if(c == '/')
                        {
                            pos = k;
                            if(k == j)
                            {
                                pos = -1 + pos;
                                flag1 = fillBuffer(2);
                                pos = 1 + pos;
                                if(!flag1)
                                    return c;
                            }
                            checkLenient();
                            switch(ac[pos])
                            {
                            default:
                                return c;

                            case 42: // '*'
                                pos = 1 + pos;
                                if(!skipTo("*/"))
                                    throw syntaxError("Unterminated comment");
                                i = 2 + pos;
                                j = limit;
                                break;

                            case 47: // '/'
                                pos = 1 + pos;
                                skipToEndOfLine();
                                i = pos;
                                j = limit;
                                break;
                            }
                        } else
                        if(c == '#')
                        {
                            pos = k;
                            checkLenient();
                            skipToEndOfLine();
                            i = pos;
                            j = limit;
                        } else
                        {
                            pos = k;
                            return c;
                        }
                        continue; /* Loop/switch isn't completed */
                    }
                }
            }
        } while(true);
        i = k;
        if(true) goto _L2; else goto _L1
_L1:
    }

    private String nextQuotedValue(char c)
        throws IOException
    {
        char ac[] = buffer;
        StringBuilder stringbuilder = new StringBuilder();
        do
        {
            int i = pos;
            int j = limit;
            int k = i;
            int l = i;
            while(l < j) 
            {
                int i1 = l + 1;
                char c1 = ac[l];
                if(c1 == c)
                {
                    pos = i1;
                    stringbuilder.append(ac, k, -1 + (i1 - k));
                    return stringbuilder.toString();
                }
                if(c1 == '\\')
                {
                    pos = i1;
                    stringbuilder.append(ac, k, -1 + (i1 - k));
                    stringbuilder.append(readEscapeCharacter());
                    i1 = pos;
                    j = limit;
                    k = i1;
                } else
                if(c1 == '\n')
                {
                    lineNumber = 1 + lineNumber;
                    lineStart = i1;
                }
                l = i1;
            }
            stringbuilder.append(ac, k, l - k);
            pos = l;
        } while(fillBuffer(1));
        throw syntaxError("Unterminated string");
    }

    private String nextUnquotedValue()
        throws IOException
    {
        StringBuilder stringbuilder;
        int i;
        stringbuilder = null;
        i = 0;
_L6:
        if(i + pos >= limit) goto _L2; else goto _L1
_L1:
        buffer[i + pos];
        JVM INSTR lookupswitch 16: default 168
    //                   9: 178
    //                   10: 178
    //                   12: 178
    //                   13: 178
    //                   32: 178
    //                   35: 174
    //                   44: 178
    //                   47: 174
    //                   58: 178
    //                   59: 174
    //                   61: 174
    //                   91: 178
    //                   92: 174
    //                   93: 178
    //                   123: 178
    //                   125: 178;
           goto _L3 _L4 _L4 _L4 _L4 _L4 _L5 _L4 _L5 _L4 _L5 _L5 _L4 _L5 _L4 _L4 _L4
_L3:
        i++;
        break; /* Loop/switch isn't completed */
_L5:
        checkLenient();
          goto _L4
        if(true) goto _L6; else goto _L2
_L4:
        boolean flag;
        String s;
        if(stringbuilder == null)
        {
            s = new String(buffer, pos, i);
        } else
        {
            stringbuilder.append(buffer, pos, i);
            s = stringbuilder.toString();
        }
        pos = i + pos;
        return s;
_L2:
        if(i >= buffer.length) goto _L8; else goto _L7
_L7:
        if(!fillBuffer(i + 1)) goto _L4; else goto _L6
_L8:
        if(stringbuilder == null)
            stringbuilder = new StringBuilder();
        stringbuilder.append(buffer, pos, i);
        pos = i + pos;
        flag = fillBuffer(1);
        i = 0;
        if(flag) goto _L6; else goto _L9
_L9:
        i = 0;
          goto _L4
    }

    private int peekKeyword()
        throws IOException
    {
        char c = buffer[pos];
        String s;
        String s1;
        int i;
        if(c == 't' || c == 'T')
        {
            s = "true";
            s1 = "TRUE";
            i = 5;
        } else
        if(c == 'f' || c == 'F')
        {
            s = "false";
            s1 = "FALSE";
            i = 6;
        } else
        if(c == 'n' || c == 'N')
        {
            s = "null";
            s1 = "NULL";
            i = 7;
        } else
        {
            return 0;
        }
        do
        {
            int j = s.length();
            for(int k = 1; k < j; k++)
            {
                if(k + pos >= limit && !fillBuffer(k + 1))
                    return 0;
                char c1 = buffer[k + pos];
                if(c1 != s.charAt(k) && c1 != s1.charAt(k))
                    return 0;
            }

            if((j + pos < limit || fillBuffer(j + 1)) && isLiteral(buffer[j + pos]))
                return 0;
            pos = j + pos;
            peeked = i;
            return i;
        } while(true);
    }

    private int peekNumber()
        throws IOException
    {
        char ac[];
        int i;
        int j;
        long l;
        boolean flag;
        boolean flag1;
        int k;
        int i1;
        ac = buffer;
        i = pos;
        j = limit;
        l = 0L;
        flag = false;
        flag1 = true;
        k = 0;
        i1 = 0;
_L12:
        if(i + i1 != j) goto _L2; else goto _L1
_L1:
        if(i1 == ac.length)
            return 0;
        if(fillBuffer(i1 + 1)) goto _L4; else goto _L3
_L4:
        i = pos;
        j = limit;
_L2:
        c = ac[i + i1];
        c;
        JVM INSTR lookupswitch 5: default 184
    //                   43: 241
    //                   45: 209
    //                   46: 276
    //                   69: 256
    //                   101: 256;
           goto _L5 _L6 _L7 _L8 _L9 _L9
_L6:
        break; /* Loop/switch isn't completed */
_L5:
        if(c >= '0' && c <= '9')
            break MISSING_BLOCK_LABEL_290;
        if(isLiteral(c))
            return 0;
        break; /* Loop/switch isn't completed */
_L7:
        if(k == 0)
        {
            flag = true;
            k = 1;
        } else
        if(k == 5)
            k = 6;
        else
            return 0;
        break MISSING_BLOCK_LABEL_220;
        if(true) goto _L3; else goto _L10
_L10:
        i1++;
        if(true) goto _L12; else goto _L11
_L11:
        if(k == 5)
            k = 6;
        else
            return 0;
          goto _L10
_L9:
        if(k == 2 || k == 4)
            k = 5;
        else
            return 0;
          goto _L10
_L8:
        if(k == 2)
            k = 3;
        else
            return 0;
          goto _L10
        if(k == 1 || k == 0)
        {
            l = -(c - 48);
            k = 2;
        } else
        if(k == 2)
        {
            if(l == 0L)
                return 0;
            long l1 = 10L * l - (long)(c - 48);
            boolean flag2;
            if(l > 0xf333333333333334L || l == 0xf333333333333334L && l1 < l)
                flag2 = true;
            else
                flag2 = false;
            flag1 &= flag2;
            l = l1;
        } else
        if(k == 3)
            k = 4;
        else
        if(k == 5 || k == 6)
            k = 7;
          goto _L10
_L3:
        if(k == 2 && flag1 && (l != 0x8000000000000000L || flag))
        {
            char c;
            if(!flag)
                l = -l;
            peekedLong = l;
            pos = i1 + pos;
            peeked = 15;
            return 15;
        }
        if(k == 2 || k == 4 || k == 7)
        {
            peekedNumberLength = i1;
            peeked = 16;
            return 16;
        } else
        {
            return 0;
        }
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

    private char readEscapeCharacter()
        throws IOException
    {
        char c;
        if(pos == limit && !fillBuffer(1))
            throw syntaxError("Unterminated escape sequence");
        char ac[] = buffer;
        int i = pos;
        pos = i + 1;
        c = ac[i];
        c;
        JVM INSTR lookupswitch 7: default 116
    //                   10: 349
    //                   98: 337
    //                   102: 346
    //                   110: 340
    //                   114: 343
    //                   116: 334
    //                   117: 118;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L1:
        return c;
_L8:
        if(4 + pos > limit && !fillBuffer(4))
            throw syntaxError("Unterminated escape sequence");
        char c1 = '\0';
        int j = pos;
        int k = j + 4;
        while(j < k) 
        {
            char c2 = buffer[j];
            char c3 = (char)(c1 << 4);
            if(c2 >= '0' && c2 <= '9')
                c1 = (char)(c3 + (c2 - 48));
            else
            if(c2 >= 'a' && c2 <= 'f')
                c1 = (char)(c3 + (10 + (c2 - 97)));
            else
            if(c2 >= 'A' && c2 <= 'F')
                c1 = (char)(c3 + (10 + (c2 - 65)));
            else
                throw new NumberFormatException((new StringBuilder()).append("\\u").append(new String(buffer, pos, 4)).toString());
            j++;
        }
        pos = 4 + pos;
        return c1;
_L7:
        return '\t';
_L3:
        return '\b';
_L5:
        return '\n';
_L6:
        return '\r';
_L4:
        return '\f';
_L2:
        lineNumber = 1 + lineNumber;
        lineStart = pos;
        if(true) goto _L1; else goto _L9
_L9:
    }

    private void skipQuotedValue(char c)
        throws IOException
    {
        char ac[] = buffer;
        do
        {
            int i = pos;
            int j = limit;
            int k = i;
            while(k < j) 
            {
                int l = k + 1;
                char c1 = ac[k];
                if(c1 == c)
                {
                    pos = l;
                    return;
                }
                if(c1 == '\\')
                {
                    pos = l;
                    readEscapeCharacter();
                    l = pos;
                    j = limit;
                } else
                if(c1 == '\n')
                {
                    lineNumber = 1 + lineNumber;
                    lineStart = l;
                }
                k = l;
            }
            pos = k;
        } while(fillBuffer(1));
        throw syntaxError("Unterminated string");
    }

    private boolean skipTo(String s)
        throws IOException
    {
_L2:
        if(pos + s.length() > limit && !fillBuffer(s.length()))
            break MISSING_BLOCK_LABEL_111;
        if(buffer[pos] != '\n')
            break; /* Loop/switch isn't completed */
        lineNumber = 1 + lineNumber;
        lineStart = 1 + pos;
_L4:
        pos = 1 + pos;
        if(true) goto _L2; else goto _L1
_L1:
        int i = 0;
_L5:
        if(i >= s.length())
            break MISSING_BLOCK_LABEL_109;
        if(buffer[i + pos] != s.charAt(i)) goto _L4; else goto _L3
_L3:
        i++;
          goto _L5
          goto _L4
        return true;
        return false;
    }

    private void skipToEndOfLine()
        throws IOException
    {
        char c;
        do
        {
            if(pos < limit || fillBuffer(1))
            {
                char ac[] = buffer;
                int i = pos;
                pos = i + 1;
                c = ac[i];
                if(c != '\n')
                    continue;
                lineNumber = 1 + lineNumber;
                lineStart = pos;
            }
            return;
        } while(c != '\r');
    }

    private void skipUnquotedValue()
        throws IOException
    {
_L2:
        int i;
label0:
        {
            i = 0;
            do
            {
                if(i + pos >= limit)
                    break label0;
                switch(buffer[i + pos])
                {
                default:
                    i++;
                    break;

                case 35: // '#'
                case 47: // '/'
                case 59: // ';'
                case 61: // '='
                case 92: // '\\'
                    checkLenient();
                    // fall through

                case 9: // '\t'
                case 10: // '\n'
                case 12: // '\f'
                case 13: // '\r'
                case 32: // ' '
                case 44: // ','
                case 58: // ':'
                case 91: // '['
                case 93: // ']'
                case 123: // '{'
                case 125: // '}'
label1:
                    {
                        pos = i + pos;
                        break label1;
                    }
                    break;
                }
            } while(true);
            break label0;
        }
        return;
        pos = i + pos;
        if(!fillBuffer(1))
            return;
        if(true) goto _L2; else goto _L1
_L1:
    }

    private IOException syntaxError(String s)
        throws IOException
    {
        throw new MalformedJsonException((new StringBuilder()).append(s).append(" at line ").append(getLineNumber()).append(" column ").append(getColumnNumber()).toString());
    }

    public void beginArray()
        throws IOException
    {
        int i = peeked;
        if(i == 0)
            i = doPeek();
        if(i == 3)
        {
            push(1);
            peeked = 0;
            return;
        } else
        {
            throw new IllegalStateException((new StringBuilder()).append("Expected BEGIN_ARRAY but was ").append(peek()).append(" at line ").append(getLineNumber()).append(" column ").append(getColumnNumber()).toString());
        }
    }

    public void beginObject()
        throws IOException
    {
        int i = peeked;
        if(i == 0)
            i = doPeek();
        if(i == 1)
        {
            push(3);
            peeked = 0;
            return;
        } else
        {
            throw new IllegalStateException((new StringBuilder()).append("Expected BEGIN_OBJECT but was ").append(peek()).append(" at line ").append(getLineNumber()).append(" column ").append(getColumnNumber()).toString());
        }
    }

    public void close()
        throws IOException
    {
        peeked = 0;
        stack[0] = 8;
        stackSize = 1;
        in.close();
    }

    public void endArray()
        throws IOException
    {
        int i = peeked;
        if(i == 0)
            i = doPeek();
        if(i == 4)
        {
            stackSize = -1 + stackSize;
            peeked = 0;
            return;
        } else
        {
            throw new IllegalStateException((new StringBuilder()).append("Expected END_ARRAY but was ").append(peek()).append(" at line ").append(getLineNumber()).append(" column ").append(getColumnNumber()).toString());
        }
    }

    public void endObject()
        throws IOException
    {
        int i = peeked;
        if(i == 0)
            i = doPeek();
        if(i == 2)
        {
            stackSize = -1 + stackSize;
            peeked = 0;
            return;
        } else
        {
            throw new IllegalStateException((new StringBuilder()).append("Expected END_OBJECT but was ").append(peek()).append(" at line ").append(getLineNumber()).append(" column ").append(getColumnNumber()).toString());
        }
    }

    public boolean hasNext()
        throws IOException
    {
        int i = peeked;
        if(i == 0)
            i = doPeek();
        return i != 2 && i != 4;
    }

    public final boolean isLenient()
    {
        return lenient;
    }

    public boolean nextBoolean()
        throws IOException
    {
        int i = peeked;
        if(i == 0)
            i = doPeek();
        if(i == 5)
        {
            peeked = 0;
            return true;
        }
        if(i == 6)
        {
            peeked = 0;
            return false;
        } else
        {
            throw new IllegalStateException((new StringBuilder()).append("Expected a boolean but was ").append(peek()).append(" at line ").append(getLineNumber()).append(" column ").append(getColumnNumber()).toString());
        }
    }

    public double nextDouble()
        throws IOException
    {
        int i = peeked;
        if(i == 0)
            i = doPeek();
        if(i == 15)
        {
            peeked = 0;
            return (double)peekedLong;
        }
        if(i == 16)
        {
            peekedString = new String(buffer, pos, peekedNumberLength);
            pos = pos + peekedNumberLength;
        } else
        if(i == 8 || i == 9)
        {
            char c;
            if(i == 8)
                c = '\'';
            else
                c = '"';
            peekedString = nextQuotedValue(c);
        } else
        {
            if(i != 10)
                continue;
            peekedString = nextUnquotedValue();
        }
        do
        {
            peeked = 11;
            double d = Double.parseDouble(peekedString);
            if(!lenient && (Double.isNaN(d) || Double.isInfinite(d)))
            {
                throw new MalformedJsonException((new StringBuilder()).append("JSON forbids NaN and infinities: ").append(d).append(" at line ").append(getLineNumber()).append(" column ").append(getColumnNumber()).toString());
            } else
            {
                peekedString = null;
                peeked = 0;
                return d;
            }
        } while(i == 11);
        throw new IllegalStateException((new StringBuilder()).append("Expected a double but was ").append(peek()).append(" at line ").append(getLineNumber()).append(" column ").append(getColumnNumber()).toString());
    }

    public int nextInt()
        throws IOException
    {
        int i;
        i = peeked;
        if(i == 0)
            i = doPeek();
        if(i == 15)
        {
            int l = (int)peekedLong;
            if(peekedLong != (long)l)
            {
                throw new NumberFormatException((new StringBuilder()).append("Expected an int but was ").append(peekedLong).append(" at line ").append(getLineNumber()).append(" column ").append(getColumnNumber()).toString());
            } else
            {
                peeked = 0;
                return l;
            }
        }
        if(i != 16) goto _L2; else goto _L1
_L1:
        peekedString = new String(buffer, pos, peekedNumberLength);
        pos = pos + peekedNumberLength;
_L4:
        peeked = 11;
        double d = Double.parseDouble(peekedString);
        int j = (int)d;
        char c;
        int k;
        if((double)j != d)
        {
            throw new NumberFormatException((new StringBuilder()).append("Expected an int but was ").append(peekedString).append(" at line ").append(getLineNumber()).append(" column ").append(getColumnNumber()).toString());
        } else
        {
            peekedString = null;
            peeked = 0;
            return j;
        }
_L2:
        if(i != 8 && i != 9)
            break MISSING_BLOCK_LABEL_282;
        if(i == 8)
            c = '\'';
        else
            c = '"';
        peekedString = nextQuotedValue(c);
        k = Integer.parseInt(peekedString);
        peeked = 0;
        return k;
        throw new IllegalStateException((new StringBuilder()).append("Expected an int but was ").append(peek()).append(" at line ").append(getLineNumber()).append(" column ").append(getColumnNumber()).toString());
        NumberFormatException numberformatexception;
        numberformatexception;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public long nextLong()
        throws IOException
    {
        int i;
        i = peeked;
        if(i == 0)
            i = doPeek();
        if(i == 15)
        {
            peeked = 0;
            return peekedLong;
        }
        if(i != 16) goto _L2; else goto _L1
_L1:
        peekedString = new String(buffer, pos, peekedNumberLength);
        pos = pos + peekedNumberLength;
_L3:
        peeked = 11;
        double d = Double.parseDouble(peekedString);
        long l = (long)d;
        char c;
        NumberFormatException numberformatexception;
        long l1;
        if((double)l != d)
        {
            throw new NumberFormatException((new StringBuilder()).append("Expected a long but was ").append(peekedString).append(" at line ").append(getLineNumber()).append(" column ").append(getColumnNumber()).toString());
        } else
        {
            peekedString = null;
            peeked = 0;
            return l;
        }
_L2:
        if(i != 8 && i != 9)
            break MISSING_BLOCK_LABEL_214;
        if(i == 8)
            c = '\'';
        else
            c = '"';
        peekedString = nextQuotedValue(c);
        l1 = Long.parseLong(peekedString);
        peeked = 0;
        return l1;
        numberformatexception;
          goto _L3
        throw new IllegalStateException((new StringBuilder()).append("Expected a long but was ").append(peek()).append(" at line ").append(getLineNumber()).append(" column ").append(getColumnNumber()).toString());
    }

    public String nextName()
        throws IOException
    {
        int i = peeked;
        if(i == 0)
            i = doPeek();
        String s;
        if(i == 14)
            s = nextUnquotedValue();
        else
        if(i == 12)
            s = nextQuotedValue('\'');
        else
        if(i == 13)
            s = nextQuotedValue('"');
        else
            throw new IllegalStateException((new StringBuilder()).append("Expected a name but was ").append(peek()).append(" at line ").append(getLineNumber()).append(" column ").append(getColumnNumber()).toString());
        peeked = 0;
        return s;
    }

    public void nextNull()
        throws IOException
    {
        int i = peeked;
        if(i == 0)
            i = doPeek();
        if(i == 7)
        {
            peeked = 0;
            return;
        } else
        {
            throw new IllegalStateException((new StringBuilder()).append("Expected null but was ").append(peek()).append(" at line ").append(getLineNumber()).append(" column ").append(getColumnNumber()).toString());
        }
    }

    public String nextString()
        throws IOException
    {
        int i = peeked;
        if(i == 0)
            i = doPeek();
        String s;
        if(i == 10)
            s = nextUnquotedValue();
        else
        if(i == 8)
            s = nextQuotedValue('\'');
        else
        if(i == 9)
            s = nextQuotedValue('"');
        else
        if(i == 11)
        {
            s = peekedString;
            peekedString = null;
        } else
        if(i == 15)
            s = Long.toString(peekedLong);
        else
        if(i == 16)
        {
            s = new String(buffer, pos, peekedNumberLength);
            pos = pos + peekedNumberLength;
        } else
        {
            throw new IllegalStateException((new StringBuilder()).append("Expected a string but was ").append(peek()).append(" at line ").append(getLineNumber()).append(" column ").append(getColumnNumber()).toString());
        }
        peeked = 0;
        return s;
    }

    public JsonToken peek()
        throws IOException
    {
        int i = peeked;
        if(i == 0)
            i = doPeek();
        switch(i)
        {
        default:
            throw new AssertionError();

        case 1: // '\001'
            return JsonToken.BEGIN_OBJECT;

        case 2: // '\002'
            return JsonToken.END_OBJECT;

        case 3: // '\003'
            return JsonToken.BEGIN_ARRAY;

        case 4: // '\004'
            return JsonToken.END_ARRAY;

        case 12: // '\f'
        case 13: // '\r'
        case 14: // '\016'
            return JsonToken.NAME;

        case 5: // '\005'
        case 6: // '\006'
            return JsonToken.BOOLEAN;

        case 7: // '\007'
            return JsonToken.NULL;

        case 8: // '\b'
        case 9: // '\t'
        case 10: // '\n'
        case 11: // '\013'
            return JsonToken.STRING;

        case 15: // '\017'
        case 16: // '\020'
            return JsonToken.NUMBER;

        case 17: // '\021'
            return JsonToken.END_DOCUMENT;
        }
    }

    public final void setLenient(boolean flag)
    {
        lenient = flag;
    }

    public void skipValue()
        throws IOException
    {
        int i = 0;
_L2:
        int j;
        j = peeked;
        if(j == 0)
            j = doPeek();
        if(j != 3)
            break; /* Loop/switch isn't completed */
        push(1);
        i++;
_L3:
        peeked = 0;
        if(i == 0)
            return;
        if(true) goto _L2; else goto _L1
_L1:
        if(j == 1)
        {
            push(3);
            i++;
        } else
        if(j == 4)
        {
            stackSize = -1 + stackSize;
            i--;
        } else
        if(j == 2)
        {
            stackSize = -1 + stackSize;
            i--;
        } else
        if(j == 14 || j == 10)
            skipUnquotedValue();
        else
        if(j == 8 || j == 12)
            skipQuotedValue('\'');
        else
        if(j == 9 || j == 13)
            skipQuotedValue('"');
        else
        if(j == 16)
            pos = pos + peekedNumberLength;
          goto _L3
        if(true) goto _L2; else goto _L4
_L4:
    }

    public String toString()
    {
        return (new StringBuilder()).append(getClass().getSimpleName()).append(" at line ").append(getLineNumber()).append(" column ").append(getColumnNumber()).toString();
    }

    private static final long MIN_INCOMPLETE_INTEGER = 0xf333333333333334L;
    private static final char NON_EXECUTE_PREFIX[] = ")]}'\n".toCharArray();
    private static final int NUMBER_CHAR_DECIMAL = 3;
    private static final int NUMBER_CHAR_DIGIT = 2;
    private static final int NUMBER_CHAR_EXP_DIGIT = 7;
    private static final int NUMBER_CHAR_EXP_E = 5;
    private static final int NUMBER_CHAR_EXP_SIGN = 6;
    private static final int NUMBER_CHAR_FRACTION_DIGIT = 4;
    private static final int NUMBER_CHAR_NONE = 0;
    private static final int NUMBER_CHAR_SIGN = 1;
    private static final int PEEKED_BEGIN_ARRAY = 3;
    private static final int PEEKED_BEGIN_OBJECT = 1;
    private static final int PEEKED_BUFFERED = 11;
    private static final int PEEKED_DOUBLE_QUOTED = 9;
    private static final int PEEKED_DOUBLE_QUOTED_NAME = 13;
    private static final int PEEKED_END_ARRAY = 4;
    private static final int PEEKED_END_OBJECT = 2;
    private static final int PEEKED_EOF = 17;
    private static final int PEEKED_FALSE = 6;
    private static final int PEEKED_LONG = 15;
    private static final int PEEKED_NONE = 0;
    private static final int PEEKED_NULL = 7;
    private static final int PEEKED_NUMBER = 16;
    private static final int PEEKED_SINGLE_QUOTED = 8;
    private static final int PEEKED_SINGLE_QUOTED_NAME = 12;
    private static final int PEEKED_TRUE = 5;
    private static final int PEEKED_UNQUOTED = 10;
    private static final int PEEKED_UNQUOTED_NAME = 14;
    private final char buffer[] = new char[1024];
    private final Reader in;
    private boolean lenient;
    private int limit;
    private int lineNumber;
    private int lineStart;
    private int peeked;
    private long peekedLong;
    private int peekedNumberLength;
    private String peekedString;
    private int pos;
    private int stack[];
    private int stackSize;

    static 
    {
        JsonReaderInternalAccess.INSTANCE = new JsonReaderInternalAccess() {

            public void promoteNameToValue(JsonReader jsonreader)
                throws IOException
            {
                if(jsonreader instanceof JsonTreeReader)
                {
                    ((JsonTreeReader)jsonreader).promoteNameToValue();
                    return;
                }
                int i = jsonreader.peeked;
                if(i == 0)
                    i = jsonreader.doPeek();
                if(i == 13)
                {
                    jsonreader.peeked = 9;
                    return;
                }
                if(i == 12)
                {
                    jsonreader.peeked = 8;
                    return;
                }
                if(i == 14)
                {
                    jsonreader.peeked = 10;
                    return;
                } else
                {
                    throw new IllegalStateException((new StringBuilder()).append("Expected a name but was ").append(jsonreader.peek()).append(" ").append(" at line ").append(jsonreader.getLineNumber()).append(" column ").append(jsonreader.getColumnNumber()).toString());
                }
            }

        }
;
    }



/*
    static int access$002(JsonReader jsonreader, int i)
    {
        jsonreader.peeked = i;
        return i;
    }

*/



}
