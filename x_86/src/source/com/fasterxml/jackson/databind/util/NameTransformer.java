// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.util;


public abstract class NameTransformer
{
    public static class Chained extends NameTransformer
    {

        public String reverse(String s)
        {
            String s1 = _t1.reverse(s);
            if(s1 != null)
                s1 = _t2.reverse(s1);
            return s1;
        }

        public String toString()
        {
            return (new StringBuilder()).append("[ChainedTransformer(").append(_t1).append(", ").append(_t2).append(")]").toString();
        }

        public String transform(String s)
        {
            return _t1.transform(_t2.transform(s));
        }

        protected final NameTransformer _t1;
        protected final NameTransformer _t2;

        public Chained(NameTransformer nametransformer, NameTransformer nametransformer1)
        {
            _t1 = nametransformer;
            _t2 = nametransformer1;
        }
    }


    protected NameTransformer()
    {
    }

    public static NameTransformer chainedTransformer(NameTransformer nametransformer, NameTransformer nametransformer1)
    {
        return new Chained(nametransformer, nametransformer1);
    }

    public static NameTransformer simpleTransformer(String s, String s1)
    {
        boolean flag = true;
        boolean flag1;
        if(s != null && s.length() > 0)
            flag1 = flag;
        else
            flag1 = false;
        if(s1 == null || s1.length() <= 0)
            flag = false;
        if(flag1)
            if(flag)
                return new NameTransformer(s, s1) {

                    public String reverse(String s2)
                    {
                        if(s2.startsWith(prefix))
                        {
                            String s3 = s2.substring(prefix.length());
                            if(s3.endsWith(suffix))
                                return s3.substring(0, s3.length() - suffix.length());
                        }
                        return null;
                    }

                    public String toString()
                    {
                        return (new StringBuilder()).append("[PreAndSuffixTransformer('").append(prefix).append("','").append(suffix).append("')]").toString();
                    }

                    public String transform(String s2)
                    {
                        return (new StringBuilder()).append(prefix).append(s2).append(suffix).toString();
                    }

                    final String val$prefix;
                    final String val$suffix;

            
            {
                prefix = s;
                suffix = s1;
                super();
            }
                }
;
            else
                return new NameTransformer(s) {

                    public String reverse(String s2)
                    {
                        if(s2.startsWith(prefix))
                            return s2.substring(prefix.length());
                        else
                            return null;
                    }

                    public String toString()
                    {
                        return (new StringBuilder()).append("[PrefixTransformer('").append(prefix).append("')]").toString();
                    }

                    public String transform(String s2)
                    {
                        return (new StringBuilder()).append(prefix).append(s2).toString();
                    }

                    final String val$prefix;

            
            {
                prefix = s;
                super();
            }
                }
;
        if(flag)
            return new NameTransformer(s1) {

                public String reverse(String s2)
                {
                    if(s2.endsWith(suffix))
                        return s2.substring(0, s2.length() - suffix.length());
                    else
                        return null;
                }

                public String toString()
                {
                    return (new StringBuilder()).append("[SuffixTransformer('").append(suffix).append("')]").toString();
                }

                public String transform(String s2)
                {
                    return (new StringBuilder()).append(s2).append(suffix).toString();
                }

                final String val$suffix;

            
            {
                suffix = s;
                super();
            }
            }
;
        else
            return NOP;
    }

    public abstract String reverse(String s);

    public abstract String transform(String s);

    public static final NameTransformer NOP = new NameTransformer() {

        public String reverse(String s)
        {
            return s;
        }

        public String transform(String s)
        {
            return s;
        }

    }
;

}
