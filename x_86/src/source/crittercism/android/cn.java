// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package crittercism.android;

import android.content.Context;
import org.json.JSONObject;

// Referenced classes of package crittercism.android:
//            cl, bj, cm, au, 
//            dk

public class cn
    implements cl
{
    public static final class a
        implements cm
    {

        public final cl a(bj bj1, bj bj2, String s, Context context, au au, dk dk)
        {
            return new cn(bj1, bj2);
        }

        public a()
        {
        }
    }


    public cn(bj bj1, bj bj2)
    {
        a = bj1;
        b = bj2;
    }

    public void a(boolean flag, int i, JSONObject jsonobject)
    {
        boolean flag1;
        if(flag || i >= 200 && i < 300)
            flag1 = true;
        else
            flag1 = false;
        if(flag1)
        {
            a.a();
            return;
        } else
        {
            a.a(b);
            return;
        }
    }

    private bj a;
    private bj b;
}
