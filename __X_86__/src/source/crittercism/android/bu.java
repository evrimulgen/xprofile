// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package crittercism.android;

import android.os.ConditionVariable;
import java.io.IOException;
import org.json.JSONArray;

// Referenced classes of package crittercism.android:
//            bt, cw, dm

public final class bu
    implements bt
{

    public bu()
    {
        String s;
        if(android.os.Build.VERSION.SDK_INT >= 8)
            s = "logcat -t 100 -v time";
        else
            s = "logcat -d -v time";
        a = s.split("\\s+");
    }

    private static JSONArray a(String as[])
    {
        JSONArray jsonarray = null;
        if(as != null)
        {
            int i = as.length;
            jsonarray = null;
            if(i > 0)
            {
                int j;
                JSONArray jsonarray1;
                if(as.length > 200)
                    j = -200 + as.length;
                else
                    j = 0;
                jsonarray1 = new JSONArray();
                for(; j < as.length; j++)
                    jsonarray1.put(as[j]);

                jsonarray = jsonarray1;
            }
        }
        return jsonarray;
    }

    private Process b()
    {
        String[] _tmp = a;
        Process process;
        try
        {
            process = (new ProcessBuilder(new String[0])).command(a).start();
        }
        catch(IOException ioexception)
        {
            (new StringBuilder("IOException in createProcess(): ")).append(ioexception.getMessage());
            return null;
        }
        return process;
    }

    public final JSONArray a()
    {
        String as[];
        Process process;
        JSONArray jsonarray;
        ConditionVariable conditionvariable;
        cw cw1;
        cw cw2;
        StringBuilder stringbuilder;
        String s;
        JSONArray jsonarray1;
        try
        {
            process = b();
        }
        catch(Throwable throwable)
        {
            (new StringBuilder("Unanticipated throwable in getLogcat: ")).append(throwable.getMessage());
            return null;
        }
        jsonarray = null;
        if(process == null) goto _L2; else goto _L1
_L1:
        conditionvariable = new ConditionVariable();
        cw1 = new cw(process, conditionvariable, cw.a.a);
        cw2 = new cw(process, null, cw.a.b);
        (new dm(cw1)).start();
        (new dm(cw2)).start();
        conditionvariable.block(250L);
        process.destroy();
        stringbuilder = cw1.b();
        if(stringbuilder == null)
            break MISSING_BLOCK_LABEL_157;
        s = stringbuilder.toString();
        if(s.length() <= 0)
            break MISSING_BLOCK_LABEL_157;
        as = s.split("\n");
_L3:
        jsonarray1 = a(as);
        jsonarray = jsonarray1;
_L2:
        return jsonarray;
        as = null;
          goto _L3
    }

    private String a[];
}
