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
import android.view.*;
import android.widget.*;

// Referenced classes of package com.shapes.shapes:
//            About, MainActivity, ShapeTable, FeetInchesType

public class Options extends Activity
{

    public Options()
    {
        settings = new float[7];
    }

    private void confirm(Context context, Class class1)
    {
        final Intent intent = new Intent(context, class1);
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setTitle(Html.fromHtml("<font color='#000000'>Confirm</font>"));
        builder.setMessage("Are you sure?  Changes will be lost.");
        builder.setPositiveButton("Yes", new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i)
            {
                dialoginterface.dismiss();
                intent.setFlags(0x4000000);
                startActivity(intent);
                overridePendingTransition(0x7f040001, 0x7f040000);
            }

            final Options this$0;
            private final Intent val$intent;

            
            {
                this$0 = Options.this;
                intent = intent1;
                super();
            }
        }
);
        builder.setNegativeButton("No", new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i)
            {
                dialoginterface.dismiss();
            }

            final Options this$0;

            
            {
                this$0 = Options.this;
                super();
            }
        }
);
        builder.show();
    }

    private boolean is_valid()
    {
        boolean flag = true;
        EditText edittext = (EditText)findViewById(0x7f0a003c);
        EditText edittext1 = (EditText)findViewById(0x7f0a0033);
        EditText edittext2 = (EditText)findViewById(0x7f0a003e);
        EditText edittext3 = (EditText)findViewById(0x7f0a0038);
        EditText edittext4 = (EditText)findViewById(0x7f0a003f);
        String s = edittext.getText().toString();
        String s1 = edittext1.getText().toString();
        String s2 = edittext2.getText().toString();
        String s3 = edittext3.getText().toString();
        String s4 = edittext4.getText().toString();
        if(!s.matches("0\"") && !s.matches("[0-9\" '-.]*"))
            flag = false;
        if(!s1.matches("0\"") && !s1.matches("[0-9\" '-.]*"))
            flag = false;
        if(!s2.matches("0\"") && !s2.matches("[0-9\" '-.]*"))
            flag = false;
        if(!s3.matches("0\"") && !s3.matches("[0-9\" '-.]*"))
            flag = false;
        if(s4.length() > 0 && !s4.matches("[0-9]*"))
            flag = false;
        if(flag)
        {
            return true;
        } else
        {
            showDialog(this, "Please enter a valid number.");
            return false;
        }
    }

    public boolean about_click()
    {
        Intent intent = new Intent(this, com/shapes/shapes/About);
        intent.setFlags(0x4000000);
        startActivity(intent);
        overridePendingTransition(0x7f040001, 0x7f040000);
        return true;
    }

    public void button_click(View view)
    {
        changed = true;
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
        if(changed)
        {
            confirm(this, com/shapes/shapes/MainActivity);
        } else
        {
            Intent intent = new Intent(this, com/shapes/shapes/MainActivity);
            intent.setFlags(0x4000000);
            startActivity(intent);
            overridePendingTransition(0x7f040001, 0x7f040000);
        }
        return true;
    }

    public void onBackPressed()
    {
        if(changed)
        {
            confirm(this, previous);
            return;
        } else
        {
            Intent intent = new Intent(this, previous);
            intent.setFlags(0x4000000);
            startActivity(intent);
            overridePendingTransition(0x7f040001, 0x7f040000);
            return;
        }
    }

    protected void onCreate(Bundle bundle)
    {
        ShapeTable shapetable = new ShapeTable();
        new String();
        super.onCreate(bundle);
        setRequestedOrientation(1);
        setContentView(0x7f030005);
        getWindow().setWindowAnimations(1);
        changed = false;
        type = new FeetInchesType();
        settings = shapetable.getSettings(this);
        final EditText et_d_tolerance = (EditText)findViewById(0x7f0a003c);
        final EditText et_bf_tolerance = (EditText)findViewById(0x7f0a0033);
        final EditText et_tf_tolerance = (EditText)findViewById(0x7f0a003e);
        final EditText et_tw_tolerance = (EditText)findViewById(0x7f0a0038);
        final EditText et_year_tolerance = (EditText)findViewById(0x7f0a003f);
        RadioButton radiobutton = (RadioButton)findViewById(0x7f0a0046);
        RadioButton radiobutton1 = (RadioButton)findViewById(0x7f0a0047);
        String s = Float.toString(settings[1]);
        String s1;
        String s2;
        String s3;
        String s4;
        if(s.length() > 0 && settings[1] > 0.0F)
        {
            type.setDisplayValue(s, 4, FeetInchesType.FeetInches.Inches1, FeetInchesType.LengthUnitsType.InchSymbol);
            et_d_tolerance.setText(type.getDisplayValue());
        } else
        {
            et_d_tolerance.setText("0\"");
        }
        s1 = Float.toString(settings[2]);
        if(s1.length() > 0 && settings[2] > 0.0F)
        {
            type.setDisplayValue(s1, 4, FeetInchesType.FeetInches.Inches1, FeetInchesType.LengthUnitsType.InchSymbol);
            et_bf_tolerance.setText(type.getDisplayValue());
        } else
        {
            et_bf_tolerance.setText("0\"");
        }
        s2 = Float.toString(settings[3]);
        if(s2.length() > 0 && settings[3] > 0.0F)
        {
            type.setDisplayValue(s2, 4, FeetInchesType.FeetInches.Inches1, FeetInchesType.LengthUnitsType.InchSymbol);
            et_tf_tolerance.setText(type.getDisplayValue());
        } else
        {
            et_tf_tolerance.setText("0\"");
        }
        s3 = Float.toString(settings[4]);
        if(s3.length() > 0 && settings[4] > 0.0F)
        {
            type.setDisplayValue(s3, 4, FeetInchesType.FeetInches.Inches1, FeetInchesType.LengthUnitsType.InchSymbol);
            et_tw_tolerance.setText(type.getDisplayValue());
        } else
        {
            et_tw_tolerance.setText("0\"");
        }
        s4 = Integer.toString((int)settings[5]);
        if(s4.length() > 0 && settings[5] > 0.0F)
            et_year_tolerance.setText(s4);
        else
            et_year_tolerance.setText("0");
        if(settings[6] > 0.0F)
            radiobutton1.setChecked(true);
        else
            radiobutton.setChecked(true);
        et_d_tolerance.setOnFocusChangeListener(new android.view.View.OnFocusChangeListener() {

            public void onFocusChange(View view, boolean flag)
            {
                if(flag)
                {
                    String s5;
                    try
                    {
                        et_d_tolerance.setBackgroundResource(0x7f020007);
                        return;
                    }
                    catch(Exception exception)
                    {
                        et_d_tolerance.setText("");
                    }
                    break MISSING_BLOCK_LABEL_84;
                }
                type.setDisplayValue(et_d_tolerance.getText().toString(), 4, FeetInchesType.FeetInches.Inches1, FeetInchesType.LengthUnitsType.InchSymbol);
                s5 = type.getDisplayValue();
                et_d_tolerance.setText(s5);
                et_d_tolerance.setBackgroundResource(0x7f020005);
                return;
                et_d_tolerance.setBackgroundResource(0x7f020005);
                return;
            }

            final Options this$0;
            private final EditText val$et_d_tolerance;

            
            {
                this$0 = Options.this;
                et_d_tolerance = edittext;
                super();
            }
        }
);
        et_bf_tolerance.setOnFocusChangeListener(new android.view.View.OnFocusChangeListener() {

            public void onFocusChange(View view, boolean flag)
            {
                if(flag)
                {
                    String s5;
                    try
                    {
                        et_bf_tolerance.setBackgroundResource(0x7f020007);
                        return;
                    }
                    catch(Exception exception)
                    {
                        et_bf_tolerance.setText("");
                    }
                    break MISSING_BLOCK_LABEL_84;
                }
                type.setDisplayValue(et_bf_tolerance.getText().toString(), 4, FeetInchesType.FeetInches.Inches1, FeetInchesType.LengthUnitsType.InchSymbol);
                s5 = type.getDisplayValue();
                et_bf_tolerance.setText(s5);
                et_bf_tolerance.setBackgroundResource(0x7f020005);
                return;
                et_bf_tolerance.setBackgroundResource(0x7f020005);
                return;
            }

            final Options this$0;
            private final EditText val$et_bf_tolerance;

            
            {
                this$0 = Options.this;
                et_bf_tolerance = edittext;
                super();
            }
        }
);
        et_tf_tolerance.setOnFocusChangeListener(new android.view.View.OnFocusChangeListener() {

            public void onFocusChange(View view, boolean flag)
            {
                if(flag)
                {
                    String s5;
                    try
                    {
                        et_tf_tolerance.setBackgroundResource(0x7f020007);
                        return;
                    }
                    catch(Exception exception)
                    {
                        et_tf_tolerance.setText("");
                    }
                    break MISSING_BLOCK_LABEL_84;
                }
                type.setDisplayValue(et_tf_tolerance.getText().toString(), 4, FeetInchesType.FeetInches.Inches1, FeetInchesType.LengthUnitsType.InchSymbol);
                s5 = type.getDisplayValue();
                et_tf_tolerance.setText(s5);
                et_tf_tolerance.setBackgroundResource(0x7f020005);
                return;
                et_tf_tolerance.setBackgroundResource(0x7f020005);
                return;
            }

            final Options this$0;
            private final EditText val$et_tf_tolerance;

            
            {
                this$0 = Options.this;
                et_tf_tolerance = edittext;
                super();
            }
        }
);
        et_tw_tolerance.setOnFocusChangeListener(new android.view.View.OnFocusChangeListener() {

            public void onFocusChange(View view, boolean flag)
            {
                if(flag)
                {
                    String s5;
                    try
                    {
                        et_tw_tolerance.setBackgroundResource(0x7f020007);
                        return;
                    }
                    catch(Exception exception)
                    {
                        et_tw_tolerance.setText("");
                    }
                    break MISSING_BLOCK_LABEL_84;
                }
                type.setDisplayValue(et_tw_tolerance.getText().toString(), 4, FeetInchesType.FeetInches.Inches1, FeetInchesType.LengthUnitsType.InchSymbol);
                s5 = type.getDisplayValue();
                et_tw_tolerance.setText(s5);
                et_tw_tolerance.setBackgroundResource(0x7f020005);
                return;
                et_tw_tolerance.setBackgroundResource(0x7f020005);
                return;
            }

            final Options this$0;
            private final EditText val$et_tw_tolerance;

            
            {
                this$0 = Options.this;
                et_tw_tolerance = edittext;
                super();
            }
        }
);
        et_year_tolerance.setOnFocusChangeListener(new android.view.View.OnFocusChangeListener() {

            public void onFocusChange(View view, boolean flag)
            {
                if(flag)
                {
                    try
                    {
                        et_year_tolerance.setBackgroundResource(0x7f020007);
                        return;
                    }
                    catch(Exception exception)
                    {
                        et_year_tolerance.setText("");
                    }
                    break MISSING_BLOCK_LABEL_34;
                }
                et_year_tolerance.setBackgroundResource(0x7f020005);
                return;
                et_year_tolerance.setBackgroundResource(0x7f020005);
                return;
            }

            final Options this$0;
            private final EditText val$et_year_tolerance;

            
            {
                this$0 = Options.this;
                et_year_tolerance = edittext;
                super();
            }
        }
);
        et_d_tolerance.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable editable)
            {
                changed = true;
            }

            public void beforeTextChanged(CharSequence charsequence, int i, int j, int k)
            {
            }

            public void onTextChanged(CharSequence charsequence, int i, int j, int k)
            {
            }

            final Options this$0;

            
            {
                this$0 = Options.this;
                super();
            }
        }
);
        et_bf_tolerance.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable editable)
            {
                changed = true;
            }

            public void beforeTextChanged(CharSequence charsequence, int i, int j, int k)
            {
            }

            public void onTextChanged(CharSequence charsequence, int i, int j, int k)
            {
            }

            final Options this$0;

            
            {
                this$0 = Options.this;
                super();
            }
        }
);
        et_tf_tolerance.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable editable)
            {
                changed = true;
            }

            public void beforeTextChanged(CharSequence charsequence, int i, int j, int k)
            {
            }

            public void onTextChanged(CharSequence charsequence, int i, int j, int k)
            {
            }

            final Options this$0;

            
            {
                this$0 = Options.this;
                super();
            }
        }
);
        et_tw_tolerance.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable editable)
            {
                changed = true;
            }

            public void beforeTextChanged(CharSequence charsequence, int i, int j, int k)
            {
            }

            public void onTextChanged(CharSequence charsequence, int i, int j, int k)
            {
            }

            final Options this$0;

            
            {
                this$0 = Options.this;
                super();
            }
        }
);
        et_year_tolerance.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable editable)
            {
                changed = true;
            }

            public void beforeTextChanged(CharSequence charsequence, int i, int j, int k)
            {
            }

            public void onTextChanged(CharSequence charsequence, int i, int j, int k)
            {
            }

            final Options this$0;

            
            {
                this$0 = Options.this;
                super();
            }
        }
);
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(0x7f090004, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuitem)
    {
        boolean flag = true;
        switch(menuitem.getItemId())
        {
        default:
            flag = super.onOptionsItemSelected(menuitem);
            // fall through

        case 2131361881: 
            return flag;

        case 2131361880: 
            return home_click();

        case 2131361882: 
            help_click();
            return flag;

        case 2131361883: 
            about_click();
            break;
        }
        return flag;
    }

    public void restore_defaults(View view)
    {
        EditText edittext = (EditText)findViewById(0x7f0a003c);
        EditText edittext1 = (EditText)findViewById(0x7f0a0033);
        EditText edittext2 = (EditText)findViewById(0x7f0a003e);
        EditText edittext3 = (EditText)findViewById(0x7f0a0038);
        EditText edittext4 = (EditText)findViewById(0x7f0a003f);
        edittext.setText("0.5000\"");
        edittext1.setText("0.2500\"");
        edittext2.setText("0.0625\"");
        edittext3.setText("0.0625\"");
        edittext4.setText("10");
        ((RadioGroup)findViewById(0x7f0a0045)).check(0x7f0a0046);
    }

    public void save_click(View view)
    {
        ShapeTable shapetable = new ShapeTable();
        EditText edittext = (EditText)findViewById(0x7f0a003c);
        EditText edittext1 = (EditText)findViewById(0x7f0a0033);
        EditText edittext2 = (EditText)findViewById(0x7f0a003e);
        EditText edittext3 = (EditText)findViewById(0x7f0a0038);
        EditText edittext4 = (EditText)findViewById(0x7f0a003f);
        if(is_valid())
        {
            String s = edittext.getText().toString().replace("\"", "");
            String s1 = edittext1.getText().toString().replace("\"", "");
            String s2 = edittext2.getText().toString().replace("\"", "");
            String s3 = edittext3.getText().toString().replace("\"", "");
            String s4 = edittext4.getText().toString().replace("\"", "");
            ((LinearLayout)findViewById(0x7f0a0005)).requestFocus();
            RadioButton radiobutton = (RadioButton)findViewById(0x7f0a0046);
            if(s.length() > 0)
                settings[1] = Float.parseFloat(s);
            if(s1.length() > 0)
                settings[2] = Float.parseFloat(s1);
            if(s2.length() > 0)
                settings[3] = Float.parseFloat(s2);
            if(s3.length() > 0)
                settings[4] = Float.parseFloat(s3);
            if(s4.length() > 0)
                settings[5] = Float.parseFloat(s4);
            if(radiobutton.isChecked())
                settings[6] = 0.0F;
            else
                settings[6] = 1.0F;
            if(shapetable.updateSettings(settings, this))
            {
                Intent intent = new Intent(this, previous);
                intent.setFlags(0x4000000);
                startActivity(intent);
                overridePendingTransition(0x7f040001, 0x7f040000);
                Toast.makeText(this, "Changes saved.", 1).show();
            }
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
            builder.setTitle("Error");
            builder.setMessage(s);
            builder.setPositiveButton("OK", new android.content.DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialoginterface, int i)
                {
                    dialoginterface.dismiss();
                }

                final Options this$0;

            
            {
                this$0 = Options.this;
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

    public static Class previous;
    private boolean changed;
    private AlertDialog myAlertDialog;
    private float settings[];
    private FeetInchesType type;


}
