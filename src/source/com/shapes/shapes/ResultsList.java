// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.shapes.shapes;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.*;
import android.view.*;
import android.widget.*;
import java.util.ArrayList;

// Referenced classes of package com.shapes.shapes:
//            Shape, FeetInchesType, About, ShapeTable, 
//            Analyze, AnalyzeColumn, MainActivity, Options

public class ResultsList extends ListActivity
{

    public ResultsList()
    {
    }

    private void shape_click(int i)
    {
        Shape shape = shapes[i];
        selected_position = i;
        shape_id = shape.get_id();
        String s = Double.toString(shape.get_d());
        String s1 = Double.toString(shape.get_bf());
        String s2 = Double.toString(shape.get_tf());
        String s3 = Double.toString(shape.get_tw());
        String s4 = Integer.toString(shape.get_start_year());
        String s5 = Integer.toString(shape.get_end_year());
        type.setDisplayValue(s, 4, FeetInchesType.FeetInches.Inches1, FeetInchesType.LengthUnitsType.InchSymbol);
        String s6 = type.getDisplayValue();
        type.setDisplayValue(s1, 4, FeetInchesType.FeetInches.Inches1, FeetInchesType.LengthUnitsType.InchSymbol);
        String s7 = type.getDisplayValue();
        type.setDisplayValue(s2, 4, FeetInchesType.FeetInches.Inches1, FeetInchesType.LengthUnitsType.InchSymbol);
        String s8 = type.getDisplayValue();
        type.setDisplayValue(s3, 4, FeetInchesType.FeetInches.Inches1, FeetInchesType.LengthUnitsType.InchSymbol);
        show_values(s6, s7, s8, type.getDisplayValue(), s4, s5);
    }

    public boolean about_click()
    {
        Intent intent = new Intent(this, com/shapes/shapes/About);
        intent.setFlags(0x4000000);
        startActivity(intent);
        overridePendingTransition(0x7f040001, 0x7f040000);
        return true;
    }

    public void beam_click(View view)
    {
        if(selected_position > -1)
        {
            selected = shape_table.getShape(this, shape_id);
            shape_table.updateSelected(selected, this);
            Intent intent = new Intent(this, com/shapes/shapes/Analyze);
            intent.setFlags(0x4000000);
            startActivity(intent);
            overridePendingTransition(0x7f040001, 0x7f040000);
            return;
        } else
        {
            Toast.makeText(this, "Please make a selection.", 1).show();
            return;
        }
    }

    public void column_click(View view)
    {
        if(selected_position > -1)
        {
            selected = shape_table.getShape(this, shape_id);
            shape_table.updateSelected(selected, this);
            Intent intent = new Intent(this, com/shapes/shapes/AnalyzeColumn);
            intent.setFlags(0x4000000);
            startActivity(intent);
            overridePendingTransition(0x7f040001, 0x7f040000);
            return;
        } else
        {
            Toast.makeText(this, "Please make a selection.", 1).show();
            return;
        }
    }

    public void copy_click(View view)
    {
        Shape shape = shape_table.getShape(this, shape_id);
        String s = (new StringBuilder("<b>")).append(shape.get_designation()).append("</b>").append("<br/>").append("d = ").append(shape.get_d()).append("<br/>").append("bf = ").append(shape.get_bf()).append("<br/>").append("tf = ").append(shape.get_tf()).append("<br/>").append("tw = ").append(shape.get_tw()).append("<br/>").append("Year = ").append(shape.get_start_year()).append(" - ").append(shape.get_end_year()).append("<br/><br/>").append("Search:").append("<br/>").append("d = ").append(Float.toString(search[1])).append(" &plusmn; ").append(Float.toString(settings[1])).append("\"").append("<br/>").append("bf = ").append(Float.toString(search[2])).append(" &plusmn; ").append(Float.toString(settings[2])).append("\"").append("<br/>").append("tf = ").append(Float.toString(search[3])).append(" &plusmn; ").append(Float.toString(settings[3])).append("\"").append("<br/>").append("tw = ").append(Float.toString(search[4])).append(" &plusmn; ").append(Float.toString(settings[4])).append("\"").append("<br/>").append("Year = ").append(Float.toString(search[5])).append(" &plusmn; ").append(Float.toString(settings[5])).toString();
        ((ClipboardManager)getSystemService("clipboard")).setText(Html.fromHtml(s).toString());
        Toast.makeText(this, (new StringBuilder("Copied ")).append(shape.get_designation()).append(" to Clipboard").toString(), 1).show();
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

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f030006);
        setRequestedOrientation(1);
        getWindow().setWindowAnimations(1);
        shapes = null;
        shape_table = new ShapeTable();
        selected_position = -1;
        list_data = new ArrayList();
        type = new FeetInchesType();
        Options.previous = com/shapes/shapes/ResultsList;
        search = shape_table.getSearch(this);
        settings = shape_table.getSettings(this);
        selected = new Shape();
        shape_id = -1;
        String s = Float.toString(search[1]);
        String s1 = Float.toString(search[2]);
        String s2 = Float.toString(search[3]);
        String s3 = Float.toString(search[4]);
        String s4 = Float.toString(search[5]);
        String s5 = Float.toString(settings[1]);
        String s6 = Float.toString(settings[2]);
        String s7 = Float.toString(settings[3]);
        String s8 = Float.toString(settings[4]);
        String s9 = Float.toString(settings[5]);
        TextView textview = (TextView)findViewById(0x7f0a004b);
        TextView textview1 = (TextView)findViewById(0x7f0a004c);
        TextView textview2 = (TextView)findViewById(0x7f0a004d);
        TextView textview3 = (TextView)findViewById(0x7f0a004e);
        TextView textview4 = (TextView)findViewById(0x7f0a004f);
        if(s.length() == 0)
            s = "0";
        if(s1.length() == 0)
            s1 = "0";
        if(s2.length() == 0)
            s2 = "0";
        if(s3.length() == 0)
            s3 = "0";
        String s10;
        FeetInchesType feetinchestype;
        FeetInchesType.FeetInches feetinches;
        FeetInchesType.LengthUnitsType lengthunitstype;
        FeetInchesType feetinchestype1;
        FeetInchesType.FeetInches feetinches1;
        FeetInchesType.LengthUnitsType lengthunitstype1;
        FeetInchesType feetinchestype2;
        FeetInchesType.FeetInches feetinches2;
        FeetInchesType.LengthUnitsType lengthunitstype2;
        FeetInchesType feetinchestype3;
        FeetInchesType.FeetInches feetinches3;
        FeetInchesType.LengthUnitsType lengthunitstype3;
        int i;
        float f;
        int j;
        float f1;
        int k;
        float f2;
        int l;
        float f3;
        int i1;
        float f4;
        int j1;
        float f5;
        int k1;
        float f6;
        int l1;
        float f7;
        int i2;
        float f8;
        int j2;
        float f9;
        TextView textview5;
        int k2;
        int l2;
        if(s4.length() == 0)
            s10 = "0";
        else
            s10 = s4.substring(0, -2 + s4.length());
        feetinchestype = type;
        feetinches = FeetInchesType.FeetInches.Inches1;
        lengthunitstype = FeetInchesType.LengthUnitsType.InchSymbol;
        feetinchestype.setDisplayValue(s, 4, feetinches, lengthunitstype);
        textview.setText((new StringBuilder(" = ")).append(type.getDisplayValue()).append("  \261").append(s5).append("\"").toString());
        feetinchestype1 = type;
        feetinches1 = FeetInchesType.FeetInches.Inches1;
        lengthunitstype1 = FeetInchesType.LengthUnitsType.InchSymbol;
        feetinchestype1.setDisplayValue(s1, 4, feetinches1, lengthunitstype1);
        textview1.setText((new StringBuilder(" = ")).append(type.getDisplayValue()).append("  \261").append(s6).append("\"").toString());
        feetinchestype2 = type;
        feetinches2 = FeetInchesType.FeetInches.Inches1;
        lengthunitstype2 = FeetInchesType.LengthUnitsType.InchSymbol;
        feetinchestype2.setDisplayValue(s2, 4, feetinches2, lengthunitstype2);
        textview2.setText((new StringBuilder(" = ")).append(type.getDisplayValue()).append("  \261").append(s7).append("\"").toString());
        feetinchestype3 = type;
        feetinches3 = FeetInchesType.FeetInches.Inches1;
        lengthunitstype3 = FeetInchesType.LengthUnitsType.InchSymbol;
        feetinchestype3.setDisplayValue(s3, 4, feetinches3, lengthunitstype3);
        textview3.setText((new StringBuilder(" = ")).append(type.getDisplayValue()).append("  \261").append(s8).append("\"").toString());
        textview4.setText((new StringBuilder(" = ")).append(s10).append("  \261").append(s9).toString());
        i = s.length();
        f = 0.0F;
        if(i > 0)
            f = Float.parseFloat(s.replace("\"", ""));
        j = s1.length();
        f1 = 0.0F;
        if(j > 0)
            f1 = Float.parseFloat(s1.replace("\"", ""));
        k = s2.length();
        f2 = 0.0F;
        if(k > 0)
            f2 = Float.parseFloat(s2.replace("\"", ""));
        l = s3.length();
        f3 = 0.0F;
        if(l > 0)
            f3 = Float.parseFloat(s3.replace("\"", ""));
        i1 = s10.length();
        f4 = 0.0F;
        if(i1 > 0)
            f4 = Float.parseFloat(s10);
        j1 = s5.length();
        f5 = 0.0F;
        if(j1 > 0)
            f5 = Float.parseFloat(s5.replace("\"", ""));
        k1 = s6.length();
        f6 = 0.0F;
        if(k1 > 0)
            f6 = Float.parseFloat(s6.replace("\"", ""));
        l1 = s7.length();
        f7 = 0.0F;
        if(l1 > 0)
            f7 = Float.parseFloat(s7.replace("\"", ""));
        i2 = s8.length();
        f8 = 0.0F;
        if(i2 > 0)
            f8 = Float.parseFloat(s8.replace("\"", ""));
        j2 = s9.length();
        f9 = 0.0F;
        if(j2 > 0)
            f9 = Float.parseFloat(s9);
        shapes = shape_table.getShapes(this, f, f1, f2, f3, f4, f5, f6, f7, f8, f9);
        textview5 = (TextView)findViewById(0x7f0a0049);
        if(shapes.length > 100)
        {
            k2 = 100;
            textview5.setText("Found 100+ shapes");
        } else
        {
            k2 = shapes.length;
            textview5.setText((new StringBuilder("Found ")).append(k2).append(" shapes").toString());
        }
        l2 = 0;
        do
        {
            if(l2 >= k2)
            {
                setListAdapter(new ArrayAdapter(this, 0x7f030009, 0x7f0a0057, list_data));
                ListView listview = (ListView)findViewById(0x102000a);
                listview.setClickable(true);
                listview.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

                    public void onItemClick(AdapterView adapterview, View view, int i3, long l3)
                    {
                        shape_click(i3);
                    }

                    final ResultsList this$0;

            
            {
                this$0 = ResultsList.this;
                super();
            }
                }
);
                if(MainActivity.pd != null)
                    MainActivity.pd.dismiss();
                return;
            }
            list_data.add(shapes[l2].get_designation());
            l2++;
        } while(true);
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
        Options.previous = com/shapes/shapes/ResultsList;
        Intent intent = new Intent(this, com/shapes/shapes/Options);
        intent.setFlags(0x4000000);
        startActivity(intent);
        overridePendingTransition(0x7f040001, 0x7f040000);
        return true;
    }

    public void show_values(String s, String s1, String s2, String s3, String s4, String s5)
    {
        TextView textview = (TextView)findViewById(0x7f0a0032);
        TextView textview1 = (TextView)findViewById(0x7f0a0031);
        TextView textview2 = (TextView)findViewById(0x7f0a003b);
        TextView textview3 = (TextView)findViewById(0x7f0a0036);
        TextView textview4 = (TextView)findViewById(0x7f0a0051);
        if(s.length() > 0)
            textview.setText((new StringBuilder(" = ")).append(s).toString());
        if(s1.length() > 0)
            textview1.setText((new StringBuilder(" = ")).append(s1).toString());
        if(s2.length() > 0)
            textview2.setText((new StringBuilder(" = ")).append(s2).toString());
        if(s3.length() > 0)
            textview3.setText((new StringBuilder(" = ")).append(s3).toString());
        if(s4.length() > 0 && s5.length() > 0)
            textview4.setText((new StringBuilder(" = ")).append(s4).append(" - ").append(s5).toString());
    }

    private ArrayList list_data;
    private float search[];
    private Shape selected;
    private int selected_position;
    private float settings[];
    private int shape_id;
    private ShapeTable shape_table;
    private Shape shapes[];
    private FeetInchesType type;

}
