// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package crittercism.android;

import com.newrelic.agent.android.instrumentation.JSONArrayInstrumentation;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import org.json.JSONArray;

// Referenced classes of package crittercism.android:
//            bf, dp

public final class bx extends bf
{

    public bx(String s)
    {
        this(s, dp.a.a());
    }

    private bx(String s, String s1)
    {
        if(s.length() > 140)
            s = s.substring(0, 140);
        b = s;
        c = s1;
    }

    public final void a(OutputStream outputstream)
    {
        OutputStreamWriter outputstreamwriter = new OutputStreamWriter(outputstream);
        JSONArray jsonarray = (new JSONArray()).put(b).put(c);
        String s;
        if(!(jsonarray instanceof JSONArray))
            s = jsonarray.toString();
        else
            s = JSONArrayInstrumentation.toString((JSONArray)jsonarray);
        outputstreamwriter.write(s);
        outputstreamwriter.close();
    }

    public static final bx a = new bx("session_start");
    private String b;
    private String c;

}
