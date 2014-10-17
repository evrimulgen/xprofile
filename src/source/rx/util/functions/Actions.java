// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.util.functions;

import rx.Observer;

// Referenced classes of package rx.util.functions:
//            Action0, Action1, Func0, Func1, 
//            Action2, Func2, Action3, Func3, 
//            Action4, Func4, Action5, Func5, 
//            Action6, Func6, Action7, Func7, 
//            Action8, Func8, Action9, Func9, 
//            ActionN, FuncN

public final class Actions
{
    private static final class EmptyAction
        implements Action0, Action1, Action2, Action3, Action4, Action5, Action6, Action7, Action8, Action9, ActionN
    {

        public void call()
        {
        }

        public void call(Object obj)
        {
        }

        public void call(Object obj, Object obj1)
        {
        }

        public void call(Object obj, Object obj1, Object obj2)
        {
        }

        public void call(Object obj, Object obj1, Object obj2, Object obj3)
        {
        }

        public void call(Object obj, Object obj1, Object obj2, Object obj3, Object obj4)
        {
        }

        public void call(Object obj, Object obj1, Object obj2, Object obj3, Object obj4, Object obj5)
        {
        }

        public void call(Object obj, Object obj1, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6)
        {
        }

        public void call(Object obj, Object obj1, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, 
                Object obj7)
        {
        }

        public void call(Object obj, Object obj1, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, 
                Object obj7, Object obj8)
        {
        }

        public transient void call(Object aobj[])
        {
        }

        private EmptyAction()
        {
        }

    }


    private Actions()
    {
        throw new IllegalStateException("No instances!");
    }

    public static final EmptyAction empty()
    {
        return EMPTY_ACTION;
    }

    public static Action0 onCompletedFrom(Observer observer)
    {
        return new Action0(observer) {

            public void call()
            {
                observer.onCompleted();
            }

            final Observer val$observer;

            
            {
                observer = observer1;
                super();
            }
        }
;
    }

    public static Action1 onErrorFrom(Observer observer)
    {
        return new Action1(observer) {

            public volatile void call(Object obj)
            {
                call((Throwable)obj);
            }

            public void call(Throwable throwable)
            {
                observer.onError(throwable);
            }

            final Observer val$observer;

            
            {
                observer = observer1;
                super();
            }
        }
;
    }

    public static Action1 onNextFrom(Observer observer)
    {
        return new Action1(observer) {

            public void call(Object obj)
            {
                observer.onNext(obj);
            }

            final Observer val$observer;

            
            {
                observer = observer1;
                super();
            }
        }
;
    }

    public static Func0 toFunc(Action0 action0)
    {
        return toFunc(action0, (Void)null);
    }

    public static Func0 toFunc(Action0 action0, Object obj)
    {
        return new Func0(action0, obj) {

            public Object call()
            {
                action.call();
                return result;
            }

            final Action0 val$action;
            final Object val$result;

            
            {
                action = action0;
                result = obj;
                super();
            }
        }
;
    }

    public static Func1 toFunc(Action1 action1)
    {
        return toFunc(action1, (Void)null);
    }

    public static Func1 toFunc(Action1 action1, Object obj)
    {
        return new Func1(action1, obj) {

            public Object call(Object obj1)
            {
                action.call(obj1);
                return result;
            }

            final Action1 val$action;
            final Object val$result;

            
            {
                action = action1;
                result = obj;
                super();
            }
        }
;
    }

    public static Func2 toFunc(Action2 action2)
    {
        return toFunc(action2, (Void)null);
    }

    public static Func2 toFunc(Action2 action2, Object obj)
    {
        return new Func2(action2, obj) {

            public Object call(Object obj1, Object obj2)
            {
                action.call(obj1, obj2);
                return result;
            }

            final Action2 val$action;
            final Object val$result;

            
            {
                action = action2;
                result = obj;
                super();
            }
        }
;
    }

    public static Func3 toFunc(Action3 action3)
    {
        return toFunc(action3, (Void)null);
    }

    public static Func3 toFunc(Action3 action3, Object obj)
    {
        return new Func3(action3, obj) {

            public Object call(Object obj1, Object obj2, Object obj3)
            {
                action.call(obj1, obj2, obj3);
                return result;
            }

            final Action3 val$action;
            final Object val$result;

            
            {
                action = action3;
                result = obj;
                super();
            }
        }
;
    }

    public static Func4 toFunc(Action4 action4)
    {
        return toFunc(action4, (Void)null);
    }

    public static Func4 toFunc(Action4 action4, Object obj)
    {
        return new Func4(action4, obj) {

            public Object call(Object obj1, Object obj2, Object obj3, Object obj4)
            {
                action.call(obj1, obj2, obj3, obj4);
                return result;
            }

            final Action4 val$action;
            final Object val$result;

            
            {
                action = action4;
                result = obj;
                super();
            }
        }
;
    }

    public static Func5 toFunc(Action5 action5)
    {
        return toFunc(action5, (Void)null);
    }

    public static Func5 toFunc(Action5 action5, Object obj)
    {
        return new Func5(action5, obj) {

            public Object call(Object obj1, Object obj2, Object obj3, Object obj4, Object obj5)
            {
                action.call(obj1, obj2, obj3, obj4, obj5);
                return result;
            }

            final Action5 val$action;
            final Object val$result;

            
            {
                action = action5;
                result = obj;
                super();
            }
        }
;
    }

    public static Func6 toFunc(Action6 action6)
    {
        return toFunc(action6, (Void)null);
    }

    public static Func6 toFunc(Action6 action6, Object obj)
    {
        return new Func6(action6, obj) {

            public Object call(Object obj1, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6)
            {
                action.call(obj1, obj2, obj3, obj4, obj5, obj6);
                return result;
            }

            final Action6 val$action;
            final Object val$result;

            
            {
                action = action6;
                result = obj;
                super();
            }
        }
;
    }

    public static Func7 toFunc(Action7 action7)
    {
        return toFunc(action7, (Void)null);
    }

    public static Func7 toFunc(Action7 action7, Object obj)
    {
        return new Func7(action7, obj) {

            public Object call(Object obj1, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7)
            {
                action.call(obj1, obj2, obj3, obj4, obj5, obj6, obj7);
                return result;
            }

            final Action7 val$action;
            final Object val$result;

            
            {
                action = action7;
                result = obj;
                super();
            }
        }
;
    }

    public static Func8 toFunc(Action8 action8)
    {
        return toFunc(action8, (Void)null);
    }

    public static Func8 toFunc(Action8 action8, Object obj)
    {
        return new Func8(action8, obj) {

            public Object call(Object obj1, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, 
                    Object obj8)
            {
                action.call(obj1, obj2, obj3, obj4, obj5, obj6, obj7, obj8);
                return result;
            }

            final Action8 val$action;
            final Object val$result;

            
            {
                action = action8;
                result = obj;
                super();
            }
        }
;
    }

    public static Func9 toFunc(Action9 action9)
    {
        return toFunc(action9, (Void)null);
    }

    public static Func9 toFunc(Action9 action9, Object obj)
    {
        return new Func9(action9, obj) {

            public Object call(Object obj1, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, 
                    Object obj8, Object obj9)
            {
                action.call(obj1, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9);
                return result;
            }

            final Action9 val$action;
            final Object val$result;

            
            {
                action = action9;
                result = obj;
                super();
            }
        }
;
    }

    public static FuncN toFunc(ActionN actionn)
    {
        return toFunc(actionn, (Void)null);
    }

    public static FuncN toFunc(ActionN actionn, Object obj)
    {
        return new FuncN(actionn, obj) {

            public transient Object call(Object aobj[])
            {
                action.call(aobj);
                return result;
            }

            final ActionN val$action;
            final Object val$result;

            
            {
                action = actionn;
                result = obj;
                super();
            }
        }
;
    }

    private static final EmptyAction EMPTY_ACTION = new EmptyAction();

}
