// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package crittercism.android;

import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

// Referenced classes of package crittercism.android:
//            bk, bl, be, bj, 
//            cg

public final class bw
    implements bk
{

    public bw(File file, bj bj1, bj bj2, bj bj3)
    {
        file.exists();
        d = file;
        a = (new bl()).a(new bq.c()).a(new bq.b()).a(new bq.d()).a(new bq.f()).a(new bq.i()).a(new bq.y()).a(new bq.z()).a(new bq.l()).a(new bq.q()).a(new bq.r()).a(new bq.v()).a(new bq.w()).a();
        HashMap hashmap = new HashMap();
        hashmap.put("crashed_session", (new be(bj1)).a);
        if(bj2.c() > 0)
            hashmap.put("previous_session", (new be(bj2)).a);
        b = new JSONObject(hashmap);
        c = (new be(bj3)).a;
    }

    public final void a(OutputStream outputstream)
    {
        HashMap hashmap = new HashMap();
        hashmap.put("app_state", a);
        hashmap.put("breadcrumbs", b);
        hashmap.put("endpoints", c);
        byte abyte0[] = new byte[0];
        byte abyte1[] = new byte[8192];
        FileInputStream fileinputstream = new FileInputStream(d);
        do
        {
            int i = fileinputstream.read(abyte1);
            if(i == -1)
                break;
            byte abyte2[] = new byte[i + abyte0.length];
            System.arraycopy(abyte0, 0, abyte2, 0, abyte0.length);
            System.arraycopy(abyte1, 0, abyte2, abyte0.length, i);
            abyte0 = abyte2;
        } while(true);
        fileinputstream.close();
        HashMap hashmap1 = new HashMap();
        hashmap1.put("dmp_name", d.getName());
        hashmap1.put("dmp_file", cg.a(abyte0));
        hashmap.put("ndk_dmp_info", new JSONObject(hashmap1));
        OutputStreamWriter outputstreamwriter = new OutputStreamWriter(outputstream);
        JSONObject jsonobject = new JSONObject(hashmap);
        String s;
        if(!(jsonobject instanceof JSONObject))
            s = jsonobject.toString();
        else
            s = JSONObjectInstrumentation.toString((JSONObject)jsonobject);
        outputstreamwriter.write(s);
        outputstreamwriter.close();
    }

    private JSONObject a;
    private JSONObject b;
    private JSONArray c;
    private File d;
}
