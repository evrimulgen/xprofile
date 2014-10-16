// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package crittercism.android;

import com.newrelic.agent.android.instrumentation.JSONArrayInstrumentation;
import java.io.*;
import org.json.JSONArray;
import org.json.JSONException;

// Referenced classes of package crittercism.android:
//            bh, bz

public final class br extends bh
{
    public static final class a extends bz
    {

        public final bh a(File file)
        {
            return new br(file, (byte)0);
        }

        public a()
        {
        }
    }


    private br(File file)
    {
        a = file;
    }

    br(File file, byte byte0)
    {
        this(file);
    }

    public final Object a()
    {
        StringBuilder stringbuilder = new StringBuilder();
        FileInputStream fileinputstream;
        InputStreamReader inputstreamreader;
        fileinputstream = new FileInputStream(a);
        inputstreamreader = new InputStreamReader(fileinputstream);
_L3:
        int i = inputstreamreader.read();
        if(i == -1) goto _L2; else goto _L1
_L1:
        FileNotFoundException filenotfoundexception;
        stringbuilder.append((char)i);
          goto _L3
_L5:
        IOException ioexception;
        JSONArray jsonarray;
        try
        {
            JVM INSTR new #56  <Class JSONArray>;
            jsonarray = JSONArrayInstrumentation.init(stringbuilder.toString());
        }
        catch(JSONException jsonexception)
        {
            (new StringBuilder("JSONException in getData(): ")).append(jsonexception.getMessage());
            return null;
        }
        return jsonarray;
_L2:
        try
        {
            fileinputstream.close();
        }
        // Misplaced declaration of an exception variable
        catch(FileNotFoundException filenotfoundexception)
        {
            (new StringBuilder("FileNotFoundException in getData(): ")).append(filenotfoundexception.getMessage());
        }
        // Misplaced declaration of an exception variable
        catch(IOException ioexception)
        {
            (new StringBuilder("IOException in getData(): ")).append(ioexception.getMessage());
        }
        if(true) goto _L5; else goto _L4
_L4:
    }

    private File a;
}
