// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.quantcast.measurement.service;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.*;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.tracing.TraceMachine;

// Referenced classes of package com.quantcast.measurement.service:
//            QCOptOutUtility, QCUtility, QuantcastClient, QCLog

public class AboutQuantcastScreen extends Activity
    implements TraceFieldInterface
{

    public AboutQuantcastScreen()
    {
    }

    protected void onCreate(Bundle bundle)
    {
        TraceMachine.startTracing("AboutQuantcastScreen");
        TraceMachine.enterMethod(_nr_trace, "AboutQuantcastScreen#onCreate", null);
_L1:
        super.onCreate(bundle);
        NoSuchFieldError nosuchfielderror;
        boolean flag;
        String s;
        LinearLayout linearlayout;
        TextView textview;
        Button button;
        android.widget.LinearLayout.LayoutParams layoutparams;
        StateListDrawable statelistdrawable;
        int ai[];
        android.content.res.Resources resources;
        int ai1[];
        int ai2[];
        android.content.res.Resources resources1;
        int ai3[];
        if(!QCOptOutUtility.isOptedOut(getApplicationContext()))
            flag = true;
        else
            flag = false;
        m_ogAllowsCollection = flag;
        setTitle("About Quantcast");
        s = QCUtility.getAppName(this);
        linearlayout = new LinearLayout(this);
        linearlayout.setLayoutParams(new android.view.ViewGroup.LayoutParams(-1, -2));
        linearlayout.setOrientation(1);
        linearlayout.setPadding(35, 10, 35, 10);
        textview = new TextView(this);
        textview.setMovementMethod(LinkMovementMethod.getInstance());
        textview.setText(Html.fromHtml(String.format("Quantcast helps us measure the usage of our app so we can better understand our audience.  Quantcast collects anonymous (non-personally identifiable) data from users across apps, such as details of app usage, the number of visits and duration, their device information, city, and settings, to provide this measurement and behavioral advertising.  A full description of Quantcast\u2019s data collection and use practices can be found in its <a href=\"https://www.quantcast.com/privacy\">Privacy Policy</a>, and you can opt out below.  Please also review our %s privacy policy.", new Object[] {
            s
        })));
        textview.setLayoutParams(new android.view.ViewGroup.LayoutParams(-2, -2));
        textview.setPadding(5, 5, 5, 5);
        textview.setLinksClickable(true);
        textview.setTextSize(15F);
        textview.setTextColor(Color.rgb(190, 190, 190));
        linearlayout.addView(textview);
        button = new Button(this);
        button.setId(600);
        button.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                activity.finish();
            }

            final AboutQuantcastScreen this$0;
            final Activity val$activity;

            
            {
                this$0 = AboutQuantcastScreen.this;
                activity = activity1;
                super();
            }
        }
);
        layoutparams = new android.widget.LinearLayout.LayoutParams(-1, -2);
        layoutparams.setMargins(0, 15, 0, 15);
        button.setLayoutParams(layoutparams);
        button.setText("Proceed");
        button.setTextSize(25F);
        statelistdrawable = new StateListDrawable();
        ai = new int[1];
        ai[0] = -0x10100a7;
        resources = getResources();
        ai1 = new int[1];
        ai1[0] = Color.rgb(0, 128, 52);
        statelistdrawable.addState(ai, new BitmapDrawable(resources, Bitmap.createBitmap(ai1, 1, 1, android.graphics.Bitmap.Config.ARGB_8888)));
        ai2 = (new int[] {
            0x10100a7
        });
        resources1 = getResources();
        ai3 = new int[1];
        ai3[0] = Color.rgb(0, 64, 26);
        statelistdrawable.addState(ai2, new BitmapDrawable(resources1, Bitmap.createBitmap(ai3, 1, 1, android.graphics.Bitmap.Config.ARGB_8888)));
        button.setBackgroundDrawable(statelistdrawable);
        button.setTextColor(-1);
        linearlayout.addView(button);
        m_optOutCheckbox = new CheckBox(this);
        m_optOutCheckbox.setId(500);
        m_optOutCheckbox.setChecked(m_ogAllowsCollection);
        m_optOutCheckbox.setLayoutParams(new android.view.ViewGroup.LayoutParams(-2, -2));
        m_optOutCheckbox.setText("Allow data collection for this app");
        m_optOutCheckbox.setTextSize(15F);
        m_optOutCheckbox.setTextColor(Color.rgb(190, 190, 190));
        linearlayout.addView(m_optOutCheckbox);
        setContentView(linearlayout);
        TraceMachine.exitMethod();
        return;
        nosuchfielderror;
        TraceMachine.enterMethod(null, "AboutQuantcastScreen#onCreate", null);
          goto _L1
    }

    protected void onStart()
    {
        ApplicationStateMonitor.getInstance().activityStarted();
        super.onStart();
        QuantcastClient.activityStart(this);
    }

    protected void onStop()
    {
        boolean flag = true;
        ApplicationStateMonitor.getInstance().activityStopped();
        super.onStop();
        boolean flag1 = m_optOutCheckbox.isChecked();
        if(m_ogAllowsCollection != flag1)
        {
            QCLog.Tag tag = TAG;
            StringBuilder stringbuilder = (new StringBuilder()).append("User opt out status changed to ");
            boolean flag2;
            android.content.Context context;
            if(!flag1)
                flag2 = flag;
            else
                flag2 = false;
            QCLog.i(tag, stringbuilder.append(flag2).toString());
            context = getApplicationContext();
            if(flag1)
                flag = false;
            QCOptOutUtility.saveOptOutStatus(context, flag);
        }
        QuantcastClient.activityStop();
    }

    private static final String CLOSE_DIALOG_BUTTON_TEXT = "Proceed";
    private static final int DIALOG_MESSAGE_PADDING = 5;
    private static final String DIALOG_TITLE = "About Quantcast";
    private static final int DIALOG_VIEW_BOTTOM_PADDING = 10;
    private static final int DIALOG_VIEW_LEFT_PADDING = 35;
    private static final int DIALOG_VIEW_RIGHT_PADDING = 35;
    private static final int DIALOG_VIEW_TOP_PADDING = 10;
    private static final String FORMATTED_DIALOG_MESSAGE = "Quantcast helps us measure the usage of our app so we can better understand our audience.  Quantcast collects anonymous (non-personally identifiable) data from users across apps, such as details of app usage, the number of visits and duration, their device information, city, and settings, to provide this measurement and behavioral advertising.  A full description of Quantcast\u2019s data collection and use practices can be found in its <a href=\"https://www.quantcast.com/privacy\">Privacy Policy</a>, and you can opt out below.  Please also review our %s privacy policy.";
    private static final String OPT_OUT_CHECKBOX_TEXT = "Allow data collection for this app";
    private static final QCLog.Tag TAG = new QCLog.Tag(com/quantcast/measurement/service/AboutQuantcastScreen);
    private boolean m_ogAllowsCollection;
    private CheckBox m_optOutCheckbox;

}
