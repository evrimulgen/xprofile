// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.shapes.shapes;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.*;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.view.*;
import android.widget.*;

// Referenced classes of package com.shapes.shapes:
//            FeetInchesType, About, MainActivity, BeamAnalysis, 
//            ShapeTable, AnalyzeBeam2, Options, Shape

public class Analyze extends Activity
{

    public Analyze()
    {
        d = 0.0D;
        bf = 0.0D;
        tf = 0.0D;
        tw = 0.0D;
    }

    private boolean is_valid(String s, String s1, String s2)
    {
        boolean flag = true;
        double d1 = 0.0D;
        if(s.length() > 0)
        {
            type.setDisplayValue(s, 4, FeetInchesType.FeetInches.Inches1, FeetInchesType.LengthUnitsType.InchSymbol);
            d1 = Double.parseDouble(type.getDisplayValue().replace("\"", ""));
            if(d1 <= 0.0D)
            {
                flag = false;
                showDialog(this, "Please enter a valid main span.");
            }
        }
        if(s2.length() > 0)
        {
            type.setDisplayValue(s2, 4, FeetInchesType.FeetInches.Inches1, FeetInchesType.LengthUnitsType.InchSymbol);
            double d2 = Double.parseDouble(type.getDisplayValue().replace("\"", ""));
            if(d2 < 18D || d2 > 65D)
            {
                flag = false;
                showDialog(this, "Please enter a valid yield strength.");
            }
        }
        if(s1.length() > 0)
        {
            type.setDisplayValue(s1, 4, FeetInchesType.FeetInches.Inches1, FeetInchesType.LengthUnitsType.InchSymbol);
            if(Double.parseDouble(type.getDisplayValue().replace("\"", "")) > d1)
            {
                flag = false;
                showDialog(this, "Unbraced Length cannot exceed Main Span.");
            }
        }
        return flag;
    }

    public boolean about_click()
    {
        Intent intent = new Intent(this, com/shapes/shapes/About);
        intent.setFlags(0x4000000);
        startActivity(intent);
        overridePendingTransition(0x7f040001, 0x7f040000);
        return true;
    }

    public void clear_click(View view)
    {
        EditText edittext = (EditText)findViewById(0x7f0a0013);
        EditText edittext1 = (EditText)findViewById(0x7f0a000a);
        EditText edittext2 = (EditText)findViewById(0x7f0a0019);
        edittext.setText("0'-0.0000\"");
        edittext1.setText("0'-0.0000\"");
        edittext2.setText("0");
    }

    public boolean help_click()
    {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(MainActivity.URL));
        startActivity(intent);
        return true;
    }

    public boolean home_click()
    {
        Intent intent = new Intent(this, com/shapes/shapes/MainActivity);
        intent.setFlags(0x4000000);
        startActivity(intent);
        overridePendingTransition(0x7f040001, 0x7f040000);
        return true;
    }

    public void next_click(View view)
    {
        ((LinearLayout)findViewById(0x7f0a0005)).requestFocus();
        EditText edittext;
        EditText edittext1;
        EditText edittext2;
        String s;
        String s1;
        String s2;
        edittext = (EditText)findViewById(0x7f0a0013);
        edittext1 = (EditText)findViewById(0x7f0a000a);
        edittext2 = (EditText)findViewById(0x7f0a0019);
        s = edittext.getText().toString();
        s1 = edittext1.getText().toString();
        s2 = edittext2.getText().toString();
        if(!is_valid(s, s1, s2)) goto _L2; else goto _L1
_L1:
        double d1;
        double d2;
        d1 = 0.0D;
        d2 = 0.0D;
        if(s.length() <= 0) goto _L4; else goto _L3
_L3:
        type.setDisplayValue(s, 4, FeetInchesType.FeetInches.Inches1, FeetInchesType.LengthUnitsType.InchSymbol);
        d1 = Double.parseDouble(type.getDisplayValue().replace("\"", ""));
_L12:
        if(s1.length() <= 0) goto _L6; else goto _L5
_L5:
        type.setDisplayValue(s1, 4, FeetInchesType.FeetInches.Inches1, FeetInchesType.LengthUnitsType.InchSymbol);
        d2 = Double.parseDouble(type.getDisplayValue().replace("\"", ""));
_L9:
        if(s2.length() <= 0) goto _L8; else goto _L7
_L7:
        int i = Integer.parseInt(s2);
_L10:
        beam.setMainSpan(d1);
        beam.setTopFlange(d2);
        beam.setYieldStrength(i);
        beam.set_d(d);
        beam.set_bf(bf);
        beam.set_tf(tf);
        beam.set_tw(tw);
        shape_table.updateBeamAnalysis(beam, this);
        Intent intent = new Intent(this, com/shapes/shapes/AnalyzeBeam2);
        intent.setFlags(0x4000000);
        startActivity(intent);
        overridePendingTransition(0x7f040002, 0x7f040000);
        return;
_L4:
        try
        {
            edittext.setText("0'-0.0000\"");
        }
        catch(Exception exception)
        {
            Toast.makeText(this, exception.toString(), 1).show();
            return;
        }
        continue; /* Loop/switch isn't completed */
_L6:
        edittext1.setText("0'-0.0000\"");
          goto _L9
_L8:
        edittext2.setText("0");
        i = 0;
          goto _L10
_L2:
        return;
        if(true) goto _L12; else goto _L11
_L11:
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f030001);
        setRequestedOrientation(1);
        getWindow().setWindowAnimations(1);
        type = new FeetInchesType();
        shape_table = new ShapeTable();
        beam = shape_table.getBeamAnalysis(this);
        Options.previous = com/shapes/shapes/Analyze;
        selected = shape_table.getSelected(this);
        d = selected.get_d();
        bf = selected.get_bf();
        tf = selected.get_tf();
        tw = selected.get_tw();
        final EditText et_main_span = (EditText)findViewById(0x7f0a0013);
        final EditText et_top_flange = (EditText)findViewById(0x7f0a000a);
        final EditText et_yield_strength = (EditText)findViewById(0x7f0a0019);
        et_main_span.setOnFocusChangeListener(new android.view.View.OnFocusChangeListener() {

            public void onFocusChange(View view, boolean flag)
            {
                if(flag)
                {
                    String s2;
                    try
                    {
                        et_main_span.setBackgroundResource(0x7f020007);
                        return;
                    }
                    catch(Exception exception)
                    {
                        et_main_span.setText("");
                    }
                    break MISSING_BLOCK_LABEL_121;
                }
                type.setDisplayValue("0", 4, FeetInchesType.FeetInches.Feet1, FeetInchesType.LengthUnitsType.FtIn);
                if(et_main_span.getText().toString().length() > 0)
                    type.setDisplayValue(et_main_span.getText().toString(), 4, FeetInchesType.FeetInches.Feet1, FeetInchesType.LengthUnitsType.FtIn);
                s2 = type.getDisplayValue();
                et_main_span.setText(s2);
                et_main_span.setBackgroundResource(0x7f020005);
                return;
                et_main_span.setBackgroundResource(0x7f020005);
                return;
            }

            final Analyze this$0;
            private final EditText val$et_main_span;

            
            {
                this$0 = Analyze.this;
                et_main_span = edittext;
                super();
            }
        }
);
        et_top_flange.setOnFocusChangeListener(new android.view.View.OnFocusChangeListener() {

            public void onFocusChange(View view, boolean flag)
            {
                if(flag)
                {
                    String s2;
                    try
                    {
                        et_top_flange.setBackgroundResource(0x7f020007);
                        return;
                    }
                    catch(Exception exception)
                    {
                        et_top_flange.setText("");
                    }
                    break MISSING_BLOCK_LABEL_121;
                }
                type.setDisplayValue("0", 4, FeetInchesType.FeetInches.Feet1, FeetInchesType.LengthUnitsType.FtIn);
                if(et_top_flange.getText().toString().length() > 0)
                    type.setDisplayValue(et_top_flange.getText().toString(), 4, FeetInchesType.FeetInches.Feet1, FeetInchesType.LengthUnitsType.FtIn);
                s2 = type.getDisplayValue();
                et_top_flange.setText(s2);
                et_top_flange.setBackgroundResource(0x7f020005);
                return;
                et_top_flange.setBackgroundResource(0x7f020005);
                return;
            }

            final Analyze this$0;
            private final EditText val$et_top_flange;

            
            {
                this$0 = Analyze.this;
                et_top_flange = edittext;
                super();
            }
        }
);
        et_yield_strength.setOnFocusChangeListener(new android.view.View.OnFocusChangeListener() {

            public void onFocusChange(View view, boolean flag)
            {
                if(flag)
                {
                    try
                    {
                        et_yield_strength.setBackgroundResource(0x7f020007);
                        return;
                    }
                    catch(Exception exception)
                    {
                        et_yield_strength.setText("");
                    }
                    break MISSING_BLOCK_LABEL_61;
                }
                if(et_yield_strength.getText().toString().length() <= 0)
                    et_yield_strength.setText("0");
                et_yield_strength.setBackgroundResource(0x7f020005);
                return;
                et_yield_strength.setBackgroundResource(0x7f020005);
                return;
            }

            final Analyze this$0;
            private final EditText val$et_yield_strength;

            
            {
                this$0 = Analyze.this;
                et_yield_strength = edittext;
                super();
            }
        }
);
        new String();
        String s = Double.toString(beam.getMainSpan());
        type.setDisplayValue(s, 4, FeetInchesType.FeetInches.Inches1, FeetInchesType.LengthUnitsType.FtIn);
        et_main_span.setText(type.getDisplayValue());
        String s1 = Double.toString(beam.getTopFlange());
        type.setDisplayValue(s1, 4, FeetInchesType.FeetInches.Inches1, FeetInchesType.LengthUnitsType.FtIn);
        et_top_flange.setText(type.getDisplayValue());
        et_yield_strength.setText(Integer.toString(beam.getYieldStrength()));
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(0x7f090004, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuitem)
    {
        switch(menuitem.getItemId())
        {
        default:
            return super.onOptionsItemSelected(menuitem);

        case 2131361880: 
            return home_click();

        case 2131361882: 
            help_click();
            return true;

        case 2131361883: 
            about_click();
            return true;

        case 2131361881: 
            options_click();
            return true;
        }
    }

    public boolean options_click()
    {
        Options.previous = com/shapes/shapes/Analyze;
        Intent intent = new Intent(this, com/shapes/shapes/Options);
        intent.setFlags(0x4000000);
        startActivity(intent);
        overridePendingTransition(0x7f040001, 0x7f040000);
        return true;
    }

    public void showDialog(Context context, String s)
    {
        if(myAlertDialog != null && myAlertDialog.isShowing())
        {
            return;
        } else
        {
            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
            builder.setMessage(s);
            builder.setPositiveButton("OK", new android.content.DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialoginterface, int i)
                {
                    dialoginterface.dismiss();
                }

                final Analyze this$0;

            
            {
                this$0 = Analyze.this;
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

    public static BeamAnalysis beam;
    private double bf;
    private double d;
    public AlertDialog myAlertDialog;
    private Shape selected;
    private ShapeTable shape_table;
    private double tf;
    private double tw;
    private FeetInchesType type;

}
