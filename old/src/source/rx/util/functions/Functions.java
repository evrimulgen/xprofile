// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.util.functions;


// Referenced classes of package rx.util.functions:
//            Not, Func1, Action0, FuncN, 
//            Action1, Action2, Action3, Func0, 
//            Func2, Func3, Func4, Func5, 
//            Func6, Func7, Func8, Func9

public class Functions
{
    private static final class AlwaysFalse extends Enum
        implements Func1
    {

        public static AlwaysFalse valueOf(String s)
        {
            return (AlwaysFalse)Enum.valueOf(rx/util/functions/Functions$AlwaysFalse, s);
        }

        public static AlwaysFalse[] values()
        {
            return (AlwaysFalse[])$VALUES.clone();
        }

        public Boolean call(Object obj)
        {
            return Boolean.valueOf(false);
        }

        public volatile Object call(Object obj)
        {
            return call(obj);
        }

        private static final AlwaysFalse $VALUES[];
        public static final AlwaysFalse INSTANCE;

        static 
        {
            INSTANCE = new AlwaysFalse("INSTANCE", 0);
            AlwaysFalse aalwaysfalse[] = new AlwaysFalse[1];
            aalwaysfalse[0] = INSTANCE;
            $VALUES = aalwaysfalse;
        }

        private AlwaysFalse(String s, int i)
        {
            super(s, i);
        }
    }

    private static final class AlwaysTrue extends Enum
        implements Func1
    {

        public static AlwaysTrue valueOf(String s)
        {
            return (AlwaysTrue)Enum.valueOf(rx/util/functions/Functions$AlwaysTrue, s);
        }

        public static AlwaysTrue[] values()
        {
            return (AlwaysTrue[])$VALUES.clone();
        }

        public Boolean call(Object obj)
        {
            return Boolean.valueOf(true);
        }

        public volatile Object call(Object obj)
        {
            return call(obj);
        }

        private static final AlwaysTrue $VALUES[];
        public static final AlwaysTrue INSTANCE;

        static 
        {
            INSTANCE = new AlwaysTrue("INSTANCE", 0);
            AlwaysTrue aalwaystrue[] = new AlwaysTrue[1];
            aalwaystrue[0] = INSTANCE;
            $VALUES = aalwaystrue;
        }

        private AlwaysTrue(String s, int i)
        {
            super(s, i);
        }
    }


    public Functions()
    {
    }

    public static Func1 alwaysFalse()
    {
        return AlwaysFalse.INSTANCE;
    }

    public static Func1 alwaysTrue()
    {
        return AlwaysTrue.INSTANCE;
    }

    public static FuncN fromAction(Action0 action0)
    {
        return new FuncN(action0) {

            public volatile Object call(Object aobj[])
            {
                return call(aobj);
            }

            public transient Void call(Object aobj[])
            {
                if(aobj.length != 0)
                {
                    throw new RuntimeException("Action0 expecting 0 arguments.");
                } else
                {
                    f.call();
                    return null;
                }
            }

            final Action0 val$f;

            
            {
                f = action0;
                super();
            }
        }
;
    }

    public static FuncN fromAction(Action1 action1)
    {
        return new FuncN(action1) {

            public volatile Object call(Object aobj[])
            {
                return call(aobj);
            }

            public transient Void call(Object aobj[])
            {
                if(aobj.length != 1)
                {
                    throw new RuntimeException("Action1 expecting 1 argument.");
                } else
                {
                    f.call(aobj[0]);
                    return null;
                }
            }

            final Action1 val$f;

            
            {
                f = action1;
                super();
            }
        }
;
    }

    public static FuncN fromAction(Action2 action2)
    {
        return new FuncN(action2) {

            public volatile Object call(Object aobj[])
            {
                return call(aobj);
            }

            public transient Void call(Object aobj[])
            {
                if(aobj.length != 2)
                {
                    throw new RuntimeException("Action3 expecting 2 arguments.");
                } else
                {
                    f.call(aobj[0], aobj[1]);
                    return null;
                }
            }

            final Action2 val$f;

            
            {
                f = action2;
                super();
            }
        }
;
    }

    public static FuncN fromAction(Action3 action3)
    {
        return new FuncN(action3) {

            public volatile Object call(Object aobj[])
            {
                return call(aobj);
            }

            public transient Void call(Object aobj[])
            {
                if(aobj.length != 3)
                {
                    throw new RuntimeException("Action3 expecting 3 arguments.");
                } else
                {
                    f.call(aobj[0], aobj[1], aobj[2]);
                    return null;
                }
            }

            final Action3 val$f;

            
            {
                f = action3;
                super();
            }
        }
;
    }

    public static FuncN fromFunc(Func0 func0)
    {
        return new FuncN(func0) {

            public transient Object call(Object aobj[])
            {
                if(aobj.length != 0)
                    throw new RuntimeException("Func0 expecting 0 arguments.");
                else
                    return f.call();
            }

            final Func0 val$f;

            
            {
                f = func0;
                super();
            }
        }
;
    }

    public static FuncN fromFunc(Func1 func1)
    {
        return new FuncN(func1) {

            public transient Object call(Object aobj[])
            {
                if(aobj.length != 1)
                    throw new RuntimeException("Func1 expecting 1 argument.");
                else
                    return f.call(aobj[0]);
            }

            final Func1 val$f;

            
            {
                f = func1;
                super();
            }
        }
;
    }

    public static FuncN fromFunc(Func2 func2)
    {
        return new FuncN(func2) {

            public transient Object call(Object aobj[])
            {
                if(aobj.length != 2)
                    throw new RuntimeException("Func2 expecting 2 arguments.");
                else
                    return f.call(aobj[0], aobj[1]);
            }

            final Func2 val$f;

            
            {
                f = func2;
                super();
            }
        }
;
    }

    public static FuncN fromFunc(Func3 func3)
    {
        return new FuncN(func3) {

            public transient Object call(Object aobj[])
            {
                if(aobj.length != 3)
                    throw new RuntimeException("Func3 expecting 3 arguments.");
                else
                    return f.call(aobj[0], aobj[1], aobj[2]);
            }

            final Func3 val$f;

            
            {
                f = func3;
                super();
            }
        }
;
    }

    public static FuncN fromFunc(Func4 func4)
    {
        return new FuncN(func4) {

            public transient Object call(Object aobj[])
            {
                if(aobj.length != 4)
                    throw new RuntimeException("Func4 expecting 4 arguments.");
                else
                    return f.call(aobj[0], aobj[1], aobj[2], aobj[3]);
            }

            final Func4 val$f;

            
            {
                f = func4;
                super();
            }
        }
;
    }

    public static FuncN fromFunc(Func5 func5)
    {
        return new FuncN(func5) {

            public transient Object call(Object aobj[])
            {
                if(aobj.length != 5)
                    throw new RuntimeException("Func5 expecting 5 arguments.");
                else
                    return f.call(aobj[0], aobj[1], aobj[2], aobj[3], aobj[4]);
            }

            final Func5 val$f;

            
            {
                f = func5;
                super();
            }
        }
;
    }

    public static FuncN fromFunc(Func6 func6)
    {
        return new FuncN(func6) {

            public transient Object call(Object aobj[])
            {
                if(aobj.length != 6)
                    throw new RuntimeException("Func6 expecting 6 arguments.");
                else
                    return f.call(aobj[0], aobj[1], aobj[2], aobj[3], aobj[4], aobj[5]);
            }

            final Func6 val$f;

            
            {
                f = func6;
                super();
            }
        }
;
    }

    public static FuncN fromFunc(Func7 func7)
    {
        return new FuncN(func7) {

            public transient Object call(Object aobj[])
            {
                if(aobj.length != 7)
                    throw new RuntimeException("Func7 expecting 7 arguments.");
                else
                    return f.call(aobj[0], aobj[1], aobj[2], aobj[3], aobj[4], aobj[5], aobj[6]);
            }

            final Func7 val$f;

            
            {
                f = func7;
                super();
            }
        }
;
    }

    public static FuncN fromFunc(Func8 func8)
    {
        return new FuncN(func8) {

            public transient Object call(Object aobj[])
            {
                if(aobj.length != 8)
                    throw new RuntimeException("Func8 expecting 8 arguments.");
                else
                    return f.call(aobj[0], aobj[1], aobj[2], aobj[3], aobj[4], aobj[5], aobj[6], aobj[7]);
            }

            final Func8 val$f;

            
            {
                f = func8;
                super();
            }
        }
;
    }

    public static FuncN fromFunc(Func9 func9)
    {
        return new FuncN(func9) {

            public transient Object call(Object aobj[])
            {
                if(aobj.length != 9)
                    throw new RuntimeException("Func9 expecting 9 arguments.");
                else
                    return f.call(aobj[0], aobj[1], aobj[2], aobj[3], aobj[4], aobj[5], aobj[6], aobj[7], aobj[8]);
            }

            final Func9 val$f;

            
            {
                f = func9;
                super();
            }
        }
;
    }

    public static Func1 identity()
    {
        return new Func1() {

            public Object call(Object obj)
            {
                return obj;
            }

        }
;
    }

    public static Func1 not(Func1 func1)
    {
        return new Not(func1);
    }
}
