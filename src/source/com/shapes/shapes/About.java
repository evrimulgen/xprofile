// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.shapes.shapes;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.*;
import android.widget.TextView;

// Referenced classes of package com.shapes.shapes:
//            MainActivity, Options

public class About extends Activity
{

    public About()
    {
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

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f030000);
        ((TextView)findViewById(0x7f0a0001)).setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setData(Uri.parse("http://www.structurx.com"));
                startActivity(intent);
            }

            final About this$0;

            
            {
                this$0 = About.this;
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

        case 2131361883: 
            return flag;

        case 2131361880: 
            return home_click();

        case 2131361882: 
            help_click();
            return flag;

        case 2131361881: 
            options_click();
            return flag;
        }
    }

    public boolean options_click()
    {
        Intent intent = new Intent(this, com/shapes/shapes/Options);
        intent.setFlags(0x4000000);
        startActivity(intent);
        overridePendingTransition(0x7f040001, 0x7f040000);
        return true;
    }
}
