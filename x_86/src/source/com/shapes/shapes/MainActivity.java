// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.shapes.shapes;

import android.app.*;
import android.content.*;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.view.*;
import android.widget.*;
import java.util.Calendar;

// Referenced classes of package com.shapes.shapes:
//            ShapeTable, About, FeetInchesType, Options, 
//            ResultsList

public class MainActivity extends Activity
{

    public MainActivity()
    {
        shapes = new ShapeTable();
        settings = new float[6];
        search = new float[6];
    }

    private boolean is_valid(String s, String s1, String s2, String s3, String s4)
    {
        int i = s.length();
        float f = 0.0F;
        int j = 0;
        if(i > 0)
        {
            f = Float.parseFloat(s.replace("\"", ""));
            j = 0 + 1;
        }
        int k = s1.length();
        float f1 = 0.0F;
        if(k > 0)
        {
            f1 = Float.parseFloat(s1.replace("\"", ""));
            j++;
        }
        int l = s2.length();
        float f2 = 0.0F;
        if(l > 0)
        {
            f2 = Float.parseFloat(s2.replace("\"", ""));
            j++;
        }
        int i1 = s3.length();
        float f3 = 0.0F;
        if(i1 > 0)
        {
            f3 = Float.parseFloat(s3.replace("\"", ""));
            j++;
        }
        if(s4.length() == 4)
            j++;
        if(j >= 2)
        {
            boolean flag = true;
            if(!s.matches("[0-9\" '-.]*"))
                flag = false;
            if(!s1.matches("[0-9\" '-.]*"))
                flag = false;
            if(!s2.matches("[0-9\" '-.]*"))
                flag = false;
            if(!s3.matches("[0-9\" '-.]*"))
                flag = false;
            int j1 = s4.length();
            int k1 = 0;
            if(j1 > 0)
                if(s4.matches("[0-9]*"))
                {
                    k1 = Integer.parseInt(s4);
                } else
                {
                    flag = false;
                    k1 = 0;
                }
            if(flag)
            {
                if(f > 0.0F && (f < 3F || f > 50F))
                {
                    showDialog(this, "Value d out of range (3-50).");
                    return false;
                }
                if(f1 > 0.0F && (f1 < 2.0F || f1 > 20F))
                {
                    showDialog(this, "Value bf out of range (2-20).");
                    return false;
                }
                if(f2 > 0.0F && (f2 < 0.0F || f2 > 3F))
                {
                    showDialog(this, "Value tf out of range (0-3).");
                    return false;
                }
                if(f3 > 0.0F && (f3 < 0.0F || f3 > 2.0F))
                {
                    showDialog(this, "Value tw out of range (0-2).");
                    return false;
                }
                if(k1 > 0 && k1 < 1870 || k1 > Calendar.getInstance().get(1))
                {
                    showDialog(this, "Year out of range (1870+).");
                    return false;
                } else
                {
                    return true;
                }
            } else
            {
                showDialog(this, "Please enter a valid number.");
                return false;
            }
        } else
        {
            showDialog(this, "Please enter at least two criteria.");
            return false;
        }
    }

    public void about_click(MenuItem menuitem)
    {
        Intent intent = new Intent(this, com/shapes/shapes/About);
        intent.setFlags(0x4000000);
        startActivity(intent);
        overridePendingTransition(0x7f040001, 0x7f040000);
    }

    public void help_click(MenuItem menuitem)
    {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(URL));
        startActivity(intent);
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setRequestedOrientation(1);
        setContentView(0x7f030004);
        pd = new ProgressDialog(this);
        pd.setProgressStyle(0);
        pd.setMessage(Html.fromHtml("<font color='#000000'>Loading...</font>"));
        pd.setIndeterminate(true);
        pd.show();
        getWindow().setWindowAnimations(1);
        SQLiteDatabase sqlitedatabase = openOrCreateDatabase("shape_app", 0, null);
        type = new FeetInchesType();
        settings = shapes.getSettings(this);
        search = shapes.getSearch(this);
        URL = "http://docs.google.com/gview?embedded=true&url=http://structurx.com/help/Help.pdf";
        Options.previous = com/shapes/shapes/MainActivity;
        TextView textview = (TextView)findViewById(0x7f0a003c);
        TextView textview1 = (TextView)findViewById(0x7f0a0033);
        TextView textview2 = (TextView)findViewById(0x7f0a003e);
        TextView textview3 = (TextView)findViewById(0x7f0a0038);
        TextView textview4 = (TextView)findViewById(0x7f0a003f);
        new String();
        String s = (new StringBuilder("\261")).append(Float.toString(settings[1])).append("\"").toString();
        if(s.length() > 0)
            textview.setText(s);
        String s1 = (new StringBuilder("\261")).append(Float.toString(settings[2])).append("\"").toString();
        if(s1.length() > 0)
            textview1.setText(s1);
        String s2 = (new StringBuilder("\261")).append(Float.toString(settings[3])).append("\"").toString();
        if(s2.length() > 0)
            textview2.setText(s2);
        String s3 = (new StringBuilder("\261")).append(Float.toString(settings[4])).append("\"").toString();
        if(s3.length() > 0)
            textview3.setText(s3);
        String s4 = (new StringBuilder("\261")).append(Integer.toString((int)settings[5])).toString();
        final EditText et_d;
        final EditText et_bf;
        final EditText et_tf;
        final EditText et_tw;
        final EditText et_year;
        String s5;
        String s6;
        String s7;
        String s8;
        String s9;
        if(s4.length() > 0 && settings[5] > 1.0F)
            textview4.setText((new StringBuilder(String.valueOf(s4))).append(" years").toString());
        else
            textview4.setText((new StringBuilder(String.valueOf(s4))).append(" year").toString());
        et_d = (EditText)findViewById(0x7f0a0032);
        et_bf = (EditText)findViewById(0x7f0a0031);
        et_tf = (EditText)findViewById(0x7f0a003b);
        et_tw = (EditText)findViewById(0x7f0a0036);
        et_year = (EditText)findViewById(0x7f0a0037);
        et_d.setOnFocusChangeListener(new android.view.View.OnFocusChangeListener() {

            public void onFocusChange(View view, boolean flag)
            {
                if(flag)
                {
                    String s10;
                    try
                    {
                        et_d.setHint("");
                        et_d.setBackgroundResource(0x7f020008);
                        return;
                    }
                    catch(Exception exception)
                    {
                        et_d.setText("");
                    }
                    break MISSING_BLOCK_LABEL_121;
                }
                type.setDisplayValue("0", 4, FeetInchesType.FeetInches.Inches1, FeetInchesType.LengthUnitsType.InchSymbol);
                type.setDisplayValue(et_d.getText().toString(), 4, FeetInchesType.FeetInches.Inches1, FeetInchesType.LengthUnitsType.InchSymbol);
                s10 = type.getDisplayValue();
                et_d.setText(s10);
                et_d.setHint(0x7f050003);
                et_d.setBackgroundResource(0x7f020005);
                return;
                et_d.setBackgroundResource(0x7f020005);
                return;
            }

            final MainActivity this$0;
            private final EditText val$et_d;

            
            {
                this$0 = MainActivity.this;
                et_d = edittext;
                super();
            }
        }
);
        et_bf.setOnFocusChangeListener(new android.view.View.OnFocusChangeListener() {

            public void onFocusChange(View view, boolean flag)
            {
                if(flag)
                {
                    String s10;
                    try
                    {
                        et_bf.setHint("");
                        et_bf.setBackgroundResource(0x7f020008);
                        return;
                    }
                    catch(Exception exception)
                    {
                        et_bf.setText("");
                    }
                    break MISSING_BLOCK_LABEL_121;
                }
                type.setDisplayValue("0", 4, FeetInchesType.FeetInches.Inches1, FeetInchesType.LengthUnitsType.InchSymbol);
                type.setDisplayValue(et_bf.getText().toString(), 4, FeetInchesType.FeetInches.Inches1, FeetInchesType.LengthUnitsType.InchSymbol);
                s10 = type.getDisplayValue();
                et_bf.setText(s10);
                et_bf.setHint(0x7f050004);
                et_bf.setBackgroundResource(0x7f020005);
                return;
                et_bf.setBackgroundResource(0x7f020005);
                return;
            }

            final MainActivity this$0;
            private final EditText val$et_bf;

            
            {
                this$0 = MainActivity.this;
                et_bf = edittext;
                super();
            }
        }
);
        et_tf.setOnFocusChangeListener(new android.view.View.OnFocusChangeListener() {

            public void onFocusChange(View view, boolean flag)
            {
                if(flag)
                {
                    String s10;
                    try
                    {
                        et_tf.setHint("");
                        et_tf.setBackgroundResource(0x7f020008);
                        return;
                    }
                    catch(Exception exception)
                    {
                        et_tf.setText("");
                    }
                    break MISSING_BLOCK_LABEL_121;
                }
                type.setDisplayValue("0", 4, FeetInchesType.FeetInches.Inches1, FeetInchesType.LengthUnitsType.InchSymbol);
                type.setDisplayValue(et_tf.getText().toString(), 4, FeetInchesType.FeetInches.Inches1, FeetInchesType.LengthUnitsType.InchSymbol);
                s10 = type.getDisplayValue();
                et_tf.setText(s10);
                et_tf.setHint(0x7f050005);
                et_tf.setBackgroundResource(0x7f020005);
                return;
                et_tf.setBackgroundResource(0x7f020005);
                return;
            }

            final MainActivity this$0;
            private final EditText val$et_tf;

            
            {
                this$0 = MainActivity.this;
                et_tf = edittext;
                super();
            }
        }
);
        et_tw.setOnFocusChangeListener(new android.view.View.OnFocusChangeListener() {

            public void onFocusChange(View view, boolean flag)
            {
                if(flag)
                {
                    String s10;
                    try
                    {
                        et_tw.setHint("");
                        et_tw.setBackgroundResource(0x7f020008);
                        return;
                    }
                    catch(Exception exception)
                    {
                        et_tw.setText("");
                    }
                    break MISSING_BLOCK_LABEL_121;
                }
                type.setDisplayValue("0", 4, FeetInchesType.FeetInches.Inches1, FeetInchesType.LengthUnitsType.InchSymbol);
                type.setDisplayValue(et_tw.getText().toString(), 4, FeetInchesType.FeetInches.Inches1, FeetInchesType.LengthUnitsType.InchSymbol);
                s10 = type.getDisplayValue();
                et_tw.setText(s10);
                et_tw.setHint(0x7f050006);
                et_tw.setBackgroundResource(0x7f020005);
                return;
                et_tw.setBackgroundResource(0x7f020005);
                return;
            }

            final MainActivity this$0;
            private final EditText val$et_tw;

            
            {
                this$0 = MainActivity.this;
                et_tw = edittext;
                super();
            }
        }
);
        et_year.setOnFocusChangeListener(new android.view.View.OnFocusChangeListener() {

            public void onFocusChange(View view, boolean flag)
            {
                if(flag)
                {
                    try
                    {
                        et_year.setHint("");
                        et_year.setBackgroundResource(0x7f020008);
                        return;
                    }
                    catch(Exception exception)
                    {
                        et_year.setText("");
                    }
                    break MISSING_BLOCK_LABEL_52;
                }
                et_year.setHint(0x7f05000d);
                et_year.setBackgroundResource(0x7f020005);
                return;
                et_year.setBackgroundResource(0x7f020005);
                return;
            }

            final MainActivity this$0;
            private final EditText val$et_year;

            
            {
                this$0 = MainActivity.this;
                et_year = edittext;
                super();
            }
        }
);
        s5 = Float.toString(search[1]);
        if(s5.length() > 0 && search[1] > 0.0F)
        {
            type.setDisplayValue(s5, 4, FeetInchesType.FeetInches.Inches1, FeetInchesType.LengthUnitsType.InchSymbol);
            et_d.setText(type.getDisplayValue());
        }
        s6 = Float.toString(search[2]);
        if(s6.length() > 0 && search[2] > 0.0F)
        {
            type.setDisplayValue(s6, 4, FeetInchesType.FeetInches.Inches1, FeetInchesType.LengthUnitsType.InchSymbol);
            et_bf.setText(type.getDisplayValue());
        }
        s7 = Float.toString(search[3]);
        if(s7.length() > 0 && search[3] > 0.0F)
        {
            type.setDisplayValue(s7, 4, FeetInchesType.FeetInches.Inches1, FeetInchesType.LengthUnitsType.InchSymbol);
            et_tf.setText(type.getDisplayValue());
        }
        s8 = Float.toString(search[4]);
        if(s8.length() > 0 && search[4] > 0.0F)
        {
            type.setDisplayValue(s8, 4, FeetInchesType.FeetInches.Inches1, FeetInchesType.LengthUnitsType.InchSymbol);
            et_tw.setText(type.getDisplayValue());
        }
        s9 = Float.toString(search[5]);
        if(s9.length() > 0 && search[5] > 0.0F)
            et_year.setText(s9);
        if(ShapeTable.CREATED)
            sqlitedatabase.execSQL((new StringBuilder("UPDATE Shapes SET end_year = '")).append(Calendar.getInstance().get(1)).append("' WHERE end_year >= '2010';").toString());
        sqlitedatabase.close();
        pd.dismiss();
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(0x7f090004, menu);
        return true;
    }

    public void options_click(MenuItem menuitem)
    {
        EditText edittext = (EditText)findViewById(0x7f0a0032);
        EditText edittext1 = (EditText)findViewById(0x7f0a0031);
        EditText edittext2 = (EditText)findViewById(0x7f0a003b);
        EditText edittext3 = (EditText)findViewById(0x7f0a0036);
        EditText edittext4 = (EditText)findViewById(0x7f0a0037);
        String s = edittext.getText().toString();
        String s1 = edittext1.getText().toString();
        String s2 = edittext2.getText().toString();
        String s3 = edittext3.getText().toString();
        String s4 = edittext4.getText().toString();
        Intent intent;
        if(s.length() > 0)
            search[1] = Float.parseFloat(s.replace("\"", ""));
        else
            search[1] = 0.0F;
        if(s1.length() > 0)
            search[2] = Float.parseFloat(s1.replace("\"", ""));
        else
            search[2] = 0.0F;
        if(s2.length() > 0)
            search[3] = Float.parseFloat(s2.replace("\"", ""));
        else
            search[3] = 0.0F;
        if(s3.length() > 0)
            search[4] = Float.parseFloat(s3.replace("\"", ""));
        else
            search[4] = 0.0F;
        if(s4.length() > 0)
            search[5] = Float.parseFloat(s4);
        else
            search[5] = 0.0F;
        shapes.updateSearch(search, this);
        Options.previous = com/shapes/shapes/MainActivity;
        intent = new Intent(this, com/shapes/shapes/Options);
        intent.setFlags(0x4000000);
        startActivity(intent);
        overridePendingTransition(0x7f040001, 0x7f040000);
    }

    public void search_click(View view)
    {
        EditText edittext = (EditText)findViewById(0x7f0a0032);
        EditText edittext1 = (EditText)findViewById(0x7f0a0031);
        EditText edittext2 = (EditText)findViewById(0x7f0a003b);
        EditText edittext3 = (EditText)findViewById(0x7f0a0036);
        EditText edittext4 = (EditText)findViewById(0x7f0a0037);
        String s = edittext.getText().toString();
        String s1 = edittext1.getText().toString();
        String s2 = edittext2.getText().toString();
        String s3 = edittext3.getText().toString();
        String s4 = edittext4.getText().toString();
        if(is_valid(s, s1, s2, s3, s4))
        {
            ((LinearLayout)findViewById(0x7f0a0005)).requestFocus();
            pd = new ProgressDialog(this);
            pd.setProgressStyle(0);
            pd.setMessage(Html.fromHtml("<font color='#000000'>Searching for shapes.</font>"));
            pd.setIndeterminate(true);
            pd.show();
            Intent intent;
            if(s.length() > 0)
                search[1] = Float.parseFloat(s.replace("\"", ""));
            else
                search[1] = 0.0F;
            if(s1.length() > 0)
                search[2] = Float.parseFloat(s1.replace("\"", ""));
            else
                search[2] = 0.0F;
            if(s2.length() > 0)
                search[3] = Float.parseFloat(s2.replace("\"", ""));
            else
                search[3] = 0.0F;
            if(s3.length() > 0)
                search[4] = Float.parseFloat(s3.replace("\"", ""));
            else
                search[4] = 0.0F;
            if(s4.length() > 0)
                search[5] = Float.parseFloat(s4);
            else
                search[5] = 0.0F;
            shapes.updateSearch(search, this);
            intent = new Intent(this, com/shapes/shapes/ResultsList);
            intent.setFlags(0x4000000);
            startActivity(intent);
            overridePendingTransition(0x7f040001, 0x7f040000);
        }
    }

    public void showDialog(Context context, String s)
    {
        if(myAlertDialog != null && myAlertDialog.isShowing())
        {
            return;
        } else
        {
            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
            builder.setTitle(Html.fromHtml("<font color='#000000'>Invalid Search</font>"));
            builder.setMessage(s);
            builder.setPositiveButton("OK", new android.content.DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialoginterface, int i)
                {
                    dialoginterface.dismiss();
                }

                final MainActivity this$0;

            
            {
                this$0 = MainActivity.this;
                super();
            }
            }
);
            builder.setCancelable(false);
            myAlertDialog = builder.create();
            myAlertDialog.show();
            return;
        }
    }

    public static String URL;
    public static ProgressDialog pd;
    private AlertDialog myAlertDialog;
    private float search[];
    private float settings[];
    private ShapeTable shapes;
    private FeetInchesType type;

}
