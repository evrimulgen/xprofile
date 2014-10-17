// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.type;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.Serializable;
import java.util.*;

// Referenced classes of package com.fasterxml.jackson.databind.type:
//            TypeFactory

public class TypeParser
    implements Serializable
{
    static final class MyTokenizer extends StringTokenizer
    {

        public String getAllInput()
        {
            return _input;
        }

        public String getRemainingInput()
        {
            return _input.substring(_index);
        }

        public String getUsedInput()
        {
            return _input.substring(0, _index);
        }

        public boolean hasMoreTokens()
        {
            return _pushbackToken != null || super.hasMoreTokens();
        }

        public String nextToken()
        {
            String s;
            if(_pushbackToken != null)
            {
                s = _pushbackToken;
                _pushbackToken = null;
            } else
            {
                s = super.nextToken();
            }
            _index = _index + s.length();
            return s;
        }

        public void pushBack(String s)
        {
            _pushbackToken = s;
            _index = _index - s.length();
        }

        protected int _index;
        protected final String _input;
        protected String _pushbackToken;

        public MyTokenizer(String s)
        {
            super(s, "<,>", true);
            _input = s;
        }
    }


    public TypeParser(TypeFactory typefactory)
    {
        _factory = typefactory;
    }

    protected IllegalArgumentException _problem(MyTokenizer mytokenizer, String s)
    {
        return new IllegalArgumentException((new StringBuilder()).append("Failed to parse type '").append(mytokenizer.getAllInput()).append("' (remaining: '").append(mytokenizer.getRemainingInput()).append("'): ").append(s).toString());
    }

    protected Class findClass(String s, MyTokenizer mytokenizer)
    {
        Class class1;
        try
        {
            class1 = ClassUtil.findClass(s);
        }
        catch(Exception exception)
        {
            if(exception instanceof RuntimeException)
                throw (RuntimeException)exception;
            else
                throw _problem(mytokenizer, (new StringBuilder()).append("Can not locate class '").append(s).append("', problem: ").append(exception.getMessage()).toString());
        }
        return class1;
    }

    public JavaType parse(String s)
        throws IllegalArgumentException
    {
        MyTokenizer mytokenizer = new MyTokenizer(s.trim());
        JavaType javatype = parseType(mytokenizer);
        if(mytokenizer.hasMoreTokens())
            throw _problem(mytokenizer, "Unexpected tokens after complete type");
        else
            return javatype;
    }

    protected JavaType parseType(MyTokenizer mytokenizer)
        throws IllegalArgumentException
    {
        if(!mytokenizer.hasMoreTokens())
            throw _problem(mytokenizer, "Unexpected end-of-string");
        Class class1 = findClass(mytokenizer.nextToken(), mytokenizer);
        if(mytokenizer.hasMoreTokens())
        {
            String s = mytokenizer.nextToken();
            if("<".equals(s))
                return _factory._fromParameterizedClass(class1, parseTypes(mytokenizer));
            mytokenizer.pushBack(s);
        }
        return _factory._fromClass(class1, null);
    }

    protected List parseTypes(MyTokenizer mytokenizer)
        throws IllegalArgumentException
    {
        ArrayList arraylist = new ArrayList();
        do
        {
label0:
            {
                if(mytokenizer.hasMoreTokens())
                {
                    arraylist.add(parseType(mytokenizer));
                    if(mytokenizer.hasMoreTokens())
                        break label0;
                }
                throw _problem(mytokenizer, "Unexpected end-of-string");
            }
            String s = mytokenizer.nextToken();
            if(">".equals(s))
                return arraylist;
            if(!",".equals(s))
                throw _problem(mytokenizer, (new StringBuilder()).append("Unexpected token '").append(s).append("', expected ',' or '>')").toString());
        } while(true);
    }

    private static final long serialVersionUID = 1L;
    protected final TypeFactory _factory;
}