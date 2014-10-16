// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package retrofit;


// Referenced classes of package retrofit:
//            RetrofitError

public interface ErrorHandler
{

    public abstract Throwable handleError(RetrofitError retrofiterror);

    public static final ErrorHandler DEFAULT = new ErrorHandler() {

        public Throwable handleError(RetrofitError retrofiterror)
        {
            return retrofiterror;
        }

    }
;

}
