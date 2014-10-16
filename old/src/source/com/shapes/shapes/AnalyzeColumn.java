// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.shapes.shapes;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.*;
import android.net.Uri;
import android.os.Bundle;
import android.text.*;
import android.text.method.LinkMovementMethod;
import android.view.*;
import android.widget.*;

// Referenced classes of package com.shapes.shapes:
//            Shape, About, FeetInchesType, ColumnAnalysis, 
//            ShapeTable, MainActivity, Options

public class AnalyzeColumn extends Activity
{

    public AnalyzeColumn()
    {
        settings = new float[7];
    }

    private void display_calculation(double d, double d1, double d2, double d3)
    {
        double d4 = shape.get_h();
        double d5 = shape.get_tw();
        double d6 = shape.get_ag();
        double d7 = shape.get_rx();
        double d8 = shape.get_ry();
        double d9 = shape.get_h_tw();
        double d10 = shape.get_bf_2tf();
        float f = settings[6];
        String as[] = new String[4];
        if(d1 == 0.0D)
            d1 = d;
        if(d2 == 0.0D)
            d2 = d;
        double d11 = d1 / d7;
        double d12 = d2 / d8;
        double d13;
        double d14;
        double d15;
        double d16;
        double d18;
        double d19;
        double d20;
        Double double1;
        Double double2;
        Double double3;
        double d21;
        double d22;
        double d23;
        double d24;
        double d25;
        double d26;
        String s;
        String s1;
        String s2;
        if(d11 > d12)
        {
            d13 = d11;
            as[0] = "Strong Axis";
        } else
        {
            d13 = d12;
            as[0] = "Weak Axis";
        }
        if(d13 > 2D)
            d14 = (29000D * Math.pow(3.1415926535897931D, 2D)) / Math.pow(d13, 2D);
        else
            d14 = (29000D * Math.pow(3.1415926535897931D, 2D)) / Math.pow(2D, 2D);
        if(d13 <= 4.71D * Math.sqrt(29000D / d3))
        {
            d15 = d3 * Math.pow(0.65800000000000003D, d3 / d14);
            as[1] = "(EQN E 3-2)";
        } else
        {
            d15 = 0.877D * d14;
            as[1] = "(EQN E 3-3)";
        }
        if(d10 <= 0.56000000000000005D * Math.sqrt(29000D / d3))
        {
            d16 = 1.0D;
            as[2] = "Flange is compact, Q<sub>s</sub> = 1.0 (EQN E7-4)";
        } else
        if(d10 < 1.03D * Math.sqrt(29000D / d3))
        {
            d16 = (double)Math.round(100D * (1.415D - 0.73999999999999999D * d10 * Math.sqrt(d3 / 29000D))) / 100D;
            as[2] = (new StringBuilder("Flange is non-compact, Q<sub>s</sub> = ")).append(d16).append(" 1.0 (EQN E7-5)").toString();
        } else
        {
            d16 = (double)Math.round(100D * (20010D / (d3 * Math.pow(d10, 2D)))) / 100D;
            as[2] = (new StringBuilder("Flange is slender, Q<sub>s</sub> = ")).append(d16).append(" 1.0 (EQN E7-6)").toString();
        }
        if(d9 <= 1.49D * Math.sqrt(29000D / d3))
        {
            d18 = 1.0D;
            as[3] = "Web is compact, Qa = 1.0";
        } else
        if(d9 < 1.49D * Math.sqrt(29000D / d15))
        {
            d18 = 1.0D;
            as[3] = "Web is non-compact, Qa = 1.0";
        } else
        {
            double d17 = 1.9199999999999999D * d5 * Math.sqrt(29000D / d15) * (1.0D - (0.34000000000000002D / d10) * Math.sqrt(29000D / d15));
            if(d17 > d4)
                d17 = d4;
            d18 = (double)Math.round(100D * ((d6 - d5 * (d4 - d17)) / d6)) / 100D;
            as[3] = (new StringBuilder("Web is slender, Qa = ")).append(d18).append(" (EQN E7-16)").toString();
        }
        d19 = (double)Math.round(100D * (d16 * d18)) / 100D;
        if(d19 < 1.0D)
            if(d13 <= 4.71D * Math.sqrt(29000D / (d3 * d19)))
            {
                d15 = d3 * (d19 * Math.pow(0.65800000000000003D, (d19 * d3) / d14));
                as[1] = "(E7-2)";
            } else
            {
                d15 = 0.877D * d14;
                as[1] = "(E7-3)";
            }
        d20 = d15 * d6;
        double1 = Double.valueOf((double)Math.round(10D * (0.90000000000000002D * d20)) / 10D);
        double2 = Double.valueOf((double)Math.round(10D * (d20 / 1.6699999999999999D)) / 10D);
        double3 = Double.valueOf((double)Math.round(100D * d20) / 100D);
        d21 = (double)Math.round(100D * (d / 12D)) / 100D;
        d22 = (double)Math.round(100D * (d1 / 12D)) / 100D;
        d23 = (double)Math.round(100D * (d2 / 12D)) / 100D;
        d24 = (double)Math.round(10D * d13) / 10D;
        d25 = (double)Math.round(10D * d14) / 10D;
        d26 = (double)Math.round(100D * d15) / 100D;
        if(f > 0.0F)
            s = (new StringBuilder("Factored Load, &#934;P<sub>n</sub> = ")).append(double1).append(" k").toString();
        else
            s = (new StringBuilder("Allowable Load, P<sub>n</sub>/&#937; = ")).append(double2).append(" k").toString();
        s1 = (new StringBuilder(String.valueOf((new StringBuilder(String.valueOf((new StringBuilder(String.valueOf((new StringBuilder(String.valueOf((new StringBuilder(String.valueOf((new StringBuilder(String.valueOf((new StringBuilder(String.valueOf((new StringBuilder(String.valueOf((new StringBuilder(String.valueOf((new StringBuilder("<b>")).append(shape.get_designation()).append("</b><br/>").toString()))).append("Column height = ").append(d21).append(" ft.<br/>").toString()))).append("Unbraced Length, Strong Axis = ").append(d22).append(" ft.<br/>").toString()))).append("Ubraced Length, Weak Axis = ").append(d23).append(" ft.<br/>").toString()))).append("Yield Strength, F<sub>y</sub> = ").append(d3).append(" ksi<br/>").toString()))).append("<br/>").toString()))).append("K = 1.0 for both axes<br/>").toString()))).append("Controlling axis is the ").append(as[0]).append("<br/>").toString()))).append("Slenderness ratio, KL/r = ").append(d24).append("<br/>").toString()))).append("Elastic buckling stress, F<sub>e</sub> = ").append(d25).append(" ksi (EQN E3-4) <br/>").toString();
        if(d19 < 1.0D)
            s2 = (new StringBuilder(String.valueOf((new StringBuilder(String.valueOf(s1))).append(as[2]).append("<br/>").toString()))).append(as[3]).append("<br/>").toString();
        else
            s2 = (new StringBuilder(String.valueOf((new StringBuilder(String.valueOf(s1))).append("Flange is compact<br/>").toString()))).append("Web is compact<br/>").toString();
        showResults(this, (new StringBuilder(String.valueOf((new StringBuilder(String.valueOf((new StringBuilder(String.valueOf((new StringBuilder(String.valueOf(s2))).append("Q = ").append(d19).append("<br/>").toString()))).append("Critical stress, F<sub>cr</sub> ").append(d26).append(" ksi ").append(as[1]).append("<br/>").toString()))).append("Nominal capacity, P<sub>n</sub> = ").append(double3).append(" k<br/><br/>").toString()))).append("<b>").append(s).append("</b><br/>").toString());
    }

    private boolean isValid(double d, double d1, double d2, int i)
    {
        boolean flag = true;
        if(i < 18 || i > 65)
        {
            flag = false;
            showDialog(this, "Please enter a valid yield strength.");
        } else
        if(warning <= 0 && d1 > d || d2 > d)
        {
            warningDialog(this, "Warning: Unbraced length is greater than column length.");
            return flag;
        }
        return flag;
    }

    public void about_click(MenuItem menuitem)
    {
        Intent intent = new Intent(this, com/shapes/shapes/About);
        intent.setFlags(0x4000000);
        startActivity(intent);
        overridePendingTransition(0x7f040001, 0x7f040000);
    }

    public void analyze_click(View view)
    {
        ((LinearLayout)findViewById(0x7f0a0005)).requestFocus();
        EditText edittext;
        EditText edittext1;
        EditText edittext2;
        EditText edittext3;
        String s;
        String s1;
        String s2;
        String s3;
        edittext = (EditText)findViewById(0x7f0a002b);
        edittext1 = (EditText)findViewById(0x7f0a0028);
        edittext2 = (EditText)findViewById(0x7f0a0029);
        edittext3 = (EditText)findViewById(0x7f0a0019);
        new String();
        s = edittext.getText().toString();
        s1 = edittext1.getText().toString();
        s2 = edittext2.getText().toString();
        s3 = edittext3.getText().toString();
        double d;
        double d1;
        double d2;
        d = 0.0D;
        d1 = 0.0D;
        d2 = 0.0D;
        if(s.length() <= 0) goto _L2; else goto _L1
_L1:
        type.setDisplayValue(s, 4, FeetInchesType.FeetInches.Inches1, FeetInchesType.LengthUnitsType.InchSymbol);
        d = Double.parseDouble(type.getDisplayValue().replace("\"", ""));
_L16:
        if(s1.length() <= 0) goto _L4; else goto _L3
_L3:
        type.setDisplayValue(s1, 4, FeetInchesType.FeetInches.Inches1, FeetInchesType.LengthUnitsType.InchSymbol);
        d1 = Double.parseDouble(type.getDisplayValue().replace("\"", ""));
_L12:
        if(s2.length() <= 0) goto _L6; else goto _L5
_L5:
        type.setDisplayValue(s2, 4, FeetInchesType.FeetInches.Inches1, FeetInchesType.LengthUnitsType.InchSymbol);
        d2 = Double.parseDouble(type.getDisplayValue().replace("\"", ""));
_L13:
        if(s3.length() <= 0) goto _L8; else goto _L7
_L7:
        int i = Integer.parseInt(s3);
_L14:
        if(!isValid(d, d1, d2, i)) goto _L10; else goto _L9
_L9:
        column = new ColumnAnalysis(d, d1, d2, i, 0.0D, 0.0D, 0.0D, 0.0D);
        table.updateColumnAnalysis(column, this);
          goto _L11
_L2:
        edittext.setText("0'-0.0000\"");
        continue; /* Loop/switch isn't completed */
_L11:
        double d3 = i;
        try
        {
            display_calculation(d, d2, d1, d3);
            return;
        }
        catch(Exception exception)
        {
            Toast.makeText(this, exception.toString(), 1).show();
        }
        return;
_L4:
        edittext1.setText("0'-0.0000\"");
          goto _L12
_L6:
        edittext2.setText("0'-0.0000\"");
          goto _L13
_L8:
        edittext3.setText("0");
        i = 0;
          goto _L14
_L10:
        return;
        if(true) goto _L16; else goto _L15
_L15:
    }

    public void clear_click(View view)
    {
        EditText edittext = (EditText)findViewById(0x7f0a002b);
        EditText edittext1 = (EditText)findViewById(0x7f0a0028);
        EditText edittext2 = (EditText)findViewById(0x7f0a0029);
        EditText edittext3 = (EditText)findViewById(0x7f0a0019);
        edittext.setText("0'-0.0000\"");
        edittext1.setText("0'-0.0000\"");
        edittext2.setText("0'-0.0000\"");
        edittext3.setText("0");
    }

    public void help_click(MenuItem menuitem)
    {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(MainActivity.URL));
        startActivity(intent);
    }

    public void home_click(MenuItem menuitem)
    {
        Intent intent = new Intent(this, com/shapes/shapes/MainActivity);
        intent.setFlags(0x4000000);
        startActivity(intent);
        overridePendingTransition(0x7f040001, 0x7f040000);
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f030003);
        table = new ShapeTable();
        type = new FeetInchesType();
        column = table.getColumnAnalysis(this);
        Options.previous = com/shapes/shapes/AnalyzeColumn;
        settings = table.getSettings(this);
        shape = table.getSelected(this);
        warning = 0;
        final EditText et_column_height = (EditText)findViewById(0x7f0a002b);
        final EditText et_weak_axis = (EditText)findViewById(0x7f0a0028);
        final EditText et_strong_axis = (EditText)findViewById(0x7f0a0029);
        final EditText et_yield_strength = (EditText)findViewById(0x7f0a0019);
        et_column_height.setOnFocusChangeListener(new android.view.View.OnFocusChangeListener() {

            public void onFocusChange(View view, boolean flag)
            {
                if(flag)
                {
                    String s3;
                    try
                    {
                        et_column_height.setBackgroundResource(0x7f020007);
                        return;
                    }
                    catch(Exception exception)
                    {
                        et_column_height.setText("");
                    }
                    break MISSING_BLOCK_LABEL_121;
                }
                type.setDisplayValue("0", 4, FeetInchesType.FeetInches.Feet1, FeetInchesType.LengthUnitsType.FtIn);
                if(et_column_height.getText().toString().length() > 0)
                    type.setDisplayValue(et_column_height.getText().toString(), 4, FeetInchesType.FeetInches.Feet1, FeetInchesType.LengthUnitsType.FtIn);
                s3 = type.getDisplayValue();
                et_column_height.setText(s3);
                et_column_height.setBackgroundResource(0x7f020005);
                return;
                et_column_height.setBackgroundResource(0x7f020005);
                return;
            }

            final AnalyzeColumn this$0;
            private final EditText val$et_column_height;

            
            {
                this$0 = AnalyzeColumn.this;
                et_column_height = edittext;
                super();
            }
        }
);
        et_weak_axis.setOnFocusChangeListener(new android.view.View.OnFocusChangeListener() {

            public void onFocusChange(View view, boolean flag)
            {
                if(flag)
                {
                    String s3;
                    try
                    {
                        et_weak_axis.setBackgroundResource(0x7f020007);
                        return;
                    }
                    catch(Exception exception)
                    {
                        et_weak_axis.setText("");
                    }
                    break MISSING_BLOCK_LABEL_121;
                }
                type.setDisplayValue("0", 4, FeetInchesType.FeetInches.Feet1, FeetInchesType.LengthUnitsType.FtIn);
                if(et_weak_axis.getText().toString().length() > 0)
                    type.setDisplayValue(et_weak_axis.getText().toString(), 4, FeetInchesType.FeetInches.Feet1, FeetInchesType.LengthUnitsType.FtIn);
                s3 = type.getDisplayValue();
                et_weak_axis.setText(s3);
                et_weak_axis.setBackgroundResource(0x7f020005);
                return;
                et_weak_axis.setBackgroundResource(0x7f020005);
                return;
            }

            final AnalyzeColumn this$0;
            private final EditText val$et_weak_axis;

            
            {
                this$0 = AnalyzeColumn.this;
                et_weak_axis = edittext;
                super();
            }
        }
);
        et_strong_axis.setOnFocusChangeListener(new android.view.View.OnFocusChangeListener() {

            public void onFocusChange(View view, boolean flag)
            {
                if(flag)
                {
                    String s3;
                    try
                    {
                        et_strong_axis.setBackgroundResource(0x7f020007);
                        return;
                    }
                    catch(Exception exception)
                    {
                        et_strong_axis.setText("");
                    }
                    break MISSING_BLOCK_LABEL_121;
                }
                type.setDisplayValue("0", 4, FeetInchesType.FeetInches.Feet1, FeetInchesType.LengthUnitsType.FtIn);
                if(et_strong_axis.getText().toString().length() > 0)
                    type.setDisplayValue(et_strong_axis.getText().toString(), 4, FeetInchesType.FeetInches.Feet1, FeetInchesType.LengthUnitsType.FtIn);
                s3 = type.getDisplayValue();
                et_strong_axis.setText(s3);
                et_strong_axis.setBackgroundResource(0x7f020005);
                return;
                et_strong_axis.setBackgroundResource(0x7f020005);
                return;
            }

            final AnalyzeColumn this$0;
            private final EditText val$et_strong_axis;

            
            {
                this$0 = AnalyzeColumn.this;
                et_strong_axis = edittext;
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

            final AnalyzeColumn this$0;
            private final EditText val$et_yield_strength;

            
            {
                this$0 = AnalyzeColumn.this;
                et_yield_strength = edittext;
                super();
            }
        }
);
        new String();
        String s = Double.toString(column.getHeight());
        type.setDisplayValue(s, 4, FeetInchesType.FeetInches.Inches1, FeetInchesType.LengthUnitsType.FtIn);
        et_column_height.setText(type.getDisplayValue());
        String s1 = Double.toString(column.getWeakAxis());
        type.setDisplayValue(s1, 4, FeetInchesType.FeetInches.Inches1, FeetInchesType.LengthUnitsType.FtIn);
        et_weak_axis.setText(type.getDisplayValue());
        String s2 = Double.toString(column.getStrongAxis());
        type.setDisplayValue(s2, 4, FeetInchesType.FeetInches.Inches1, FeetInchesType.LengthUnitsType.FtIn);
        et_strong_axis.setText(type.getDisplayValue());
        et_yield_strength.setText(Integer.toString(column.getYieldStrength()));
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(0x7f090003, menu);
        return true;
    }

    public void options_click(MenuItem menuitem)
    {
        Options.previous = com/shapes/shapes/AnalyzeColumn;
        Intent intent = new Intent(this, com/shapes/shapes/Options);
        intent.setFlags(0x4000000);
        startActivity(intent);
        overridePendingTransition(0x7f040001, 0x7f040000);
    }

    public void showDialog(Context context, String s)
    {
        if(myAlertDialog != null && myAlertDialog.isShowing())
        {
            return;
        } else
        {
            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
            View view = ((LayoutInflater)getSystemService("layout_inflater")).inflate(0x7f030008, null);
            TextView textview = (TextView)view.findViewById(0x7f0a0057);
            textview.setMovementMethod(LinkMovementMethod.getInstance());
            textview.setTextSize(12F);
            textview.setText(Html.fromHtml(s));
            builder.setView(view);
            builder.setPositiveButton("OK", new android.content.DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialoginterface, int i)
                {
                    warning = 1;
                    dialoginterface.dismiss();
                }

                final AnalyzeColumn this$0;

            
            {
                this$0 = AnalyzeColumn.this;
                super();
            }
            }
);
            builder.setNegativeButton("Cancel", new android.content.DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialoginterface, int i)
                {
                    dialoginterface.dismiss();
                }

                final AnalyzeColumn this$0;

            
            {
                this$0 = AnalyzeColumn.this;
                super();
            }
            }
);
            myAlertDialog = builder.create();
            myAlertDialog.show();
            return;
        }
    }

    public void showResults(Context context, final String message)
    {
        if(myAlertDialog != null && myAlertDialog.isShowing())
        {
            return;
        } else
        {
            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
            View view = ((LayoutInflater)getSystemService("layout_inflater")).inflate(0x7f030008, null);
            TextView textview = (TextView)view.findViewById(0x7f0a0057);
            textview.setMovementMethod(LinkMovementMethod.getInstance());
            textview.setTextSize(12F);
            textview.setText(Html.fromHtml(message));
            builder.setView(view);
            builder.setPositiveButton("OK", new android.content.DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialoginterface, int i)
                {
                    dialoginterface.dismiss();
                }

                final AnalyzeColumn this$0;

            
            {
                this$0 = AnalyzeColumn.this;
                super();
            }
            }
);
            builder.setNegativeButton("Copy to Clipboard", new android.content.DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialoginterface, int i)
                {
                    ((ClipboardManager)getSystemService("clipboard")).setText(Html.fromHtml(message).toString());
                    dialoginterface.dismiss();
                }

                final AnalyzeColumn this$0;
                private final String val$message;

            
            {
                this$0 = AnalyzeColumn.this;
                message = s;
                super();
            }
            }
);
            myAlertDialog = builder.create();
            myAlertDialog.show();
            return;
        }
    }

    public void warningDialog(Context context, String s)
    {
        if(myAlertDialog != null && myAlertDialog.isShowing())
        {
            return;
        } else
        {
            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
            View view = ((LayoutInflater)getSystemService("layout_inflater")).inflate(0x7f030008, null);
            TextView textview = (TextView)view.findViewById(0x7f0a0057);
            textview.setMovementMethod(LinkMovementMethod.getInstance());
            textview.setTextSize(12F);
            textview.setText(Html.fromHtml(s));
            builder.setView(view);
            builder.setPositiveButton("OK", new android.content.DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialoginterface, int i)
                {
                    warning = 1;
                    dialoginterface.dismiss();
                    analyze_click(null);
                }

                final AnalyzeColumn this$0;

            
            {
                this$0 = AnalyzeColumn.this;
                super();
            }
            }
);
            builder.setNegativeButton("Cancel", new android.content.DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialoginterface, int i)
                {
                    dialoginterface.dismiss();
                }

                final AnalyzeColumn this$0;

            
            {
                this$0 = AnalyzeColumn.this;
                super();
            }
            }
);
            myAlertDialog = builder.create();
            myAlertDialog.show();
            return;
        }
    }

    private ColumnAnalysis column;
    private AlertDialog myAlertDialog;
    private float settings[];
    private Shape shape;
    private ShapeTable table;
    private FeetInchesType type;
    private int warning;


}
