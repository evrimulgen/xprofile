// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package de.keyboardsurfer.android.widget.crouton;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.view.*;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.*;
import com.newrelic.agent.android.instrumentation.BitmapFactoryInstrumentation;

// Referenced classes of package de.keyboardsurfer.android.widget.crouton:
//            Configuration, Manager, Style, DefaultAnimationsBuilder, 
//            LifecycleCallback

public final class Crouton
{

    private Crouton(Activity activity1, View view)
    {
        configuration = null;
        lifecycleCallback = null;
        if(activity1 == null || view == null)
        {
            throw new IllegalArgumentException("Null parameters are not accepted");
        } else
        {
            activity = activity1;
            viewGroup = null;
            customView = view;
            style = (new Style.Builder()).build();
            text = null;
            return;
        }
    }

    private Crouton(Activity activity1, View view, ViewGroup viewgroup)
    {
        this(activity1, view, viewgroup, Configuration.DEFAULT);
    }

    private Crouton(Activity activity1, View view, ViewGroup viewgroup, Configuration configuration1)
    {
        configuration = null;
        lifecycleCallback = null;
        if(activity1 == null || view == null)
        {
            throw new IllegalArgumentException("Null parameters are not accepted");
        } else
        {
            activity = activity1;
            customView = view;
            viewGroup = viewgroup;
            style = (new Style.Builder()).build();
            text = null;
            configuration = configuration1;
            return;
        }
    }

    private Crouton(Activity activity1, CharSequence charsequence, Style style1)
    {
        configuration = null;
        lifecycleCallback = null;
        if(activity1 == null || charsequence == null || style1 == null)
        {
            throw new IllegalArgumentException("Null parameters are not accepted");
        } else
        {
            activity = activity1;
            viewGroup = null;
            text = charsequence;
            style = style1;
            customView = null;
            return;
        }
    }

    private Crouton(Activity activity1, CharSequence charsequence, Style style1, ViewGroup viewgroup)
    {
        configuration = null;
        lifecycleCallback = null;
        if(activity1 == null || charsequence == null || style1 == null)
        {
            throw new IllegalArgumentException("Null parameters are not accepted");
        } else
        {
            activity = activity1;
            text = charsequence;
            style = style1;
            viewGroup = viewgroup;
            customView = null;
            return;
        }
    }

    public static void cancelAllCroutons()
    {
        Manager.getInstance().clearCroutonQueue();
    }

    public static void clearCroutonsForActivity(Activity activity1)
    {
        Manager.getInstance().clearCroutonsForActivity(activity1);
    }

    public static String getLicenseText()
    {
        return "This application uses the Crouton library.\n\nCopyright 2012 - 2013 Benjamin Weiss \n\nLicensed under the Apache License, Version 2.0 (the \"License\");\nyou may not use this file except in compliance with the License.\nYou may obtain a copy of the License at\n\n   http://www.apache.org/licenses/LICENSE-2.0\n\nUnless required by applicable law or agreed to in writing, software\ndistributed under the License is distributed on an \"AS IS\" BASIS,\nWITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\nSee the License for the specific language governing permissions and\nlimitations under the License.";
    }

    public static void hide(Crouton crouton)
    {
        crouton.hide();
    }

    private RelativeLayout initializeContentView(Resources resources)
    {
        RelativeLayout relativelayout;
        TextView textview;
        android.widget.RelativeLayout.LayoutParams layoutparams;
        ImageView imageview;
label0:
        {
            relativelayout = new RelativeLayout(activity);
            relativelayout.setLayoutParams(new android.widget.RelativeLayout.LayoutParams(-1, -1));
            int i = style.paddingInPixels;
            if(style.paddingDimensionResId > 0)
                i = resources.getDimensionPixelSize(style.paddingDimensionResId);
            relativelayout.setPadding(i, i, i, i);
            if(style.imageDrawable == null)
            {
                int j = style.imageResId;
                imageview = null;
                if(j == 0)
                    break label0;
            }
            imageview = initializeImageView();
            relativelayout.addView(imageview, imageview.getLayoutParams());
        }
        textview = initializeTextView(resources);
        layoutparams = new android.widget.RelativeLayout.LayoutParams(-1, -2);
        if(imageview != null)
            layoutparams.addRule(1, imageview.getId());
        if((0x11 & style.gravity) == 0) goto _L2; else goto _L1
_L1:
        layoutparams.addRule(13);
_L4:
        relativelayout.addView(textview, layoutparams);
        return relativelayout;
_L2:
        if((0x10 & style.gravity) != 0)
            layoutparams.addRule(15);
        else
        if((1 & style.gravity) != 0)
            layoutparams.addRule(14);
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void initializeCroutonView()
    {
        Resources resources = activity.getResources();
        croutonView = initializeCroutonViewGroup(resources);
        RelativeLayout relativelayout = initializeContentView(resources);
        croutonView.addView(relativelayout);
    }

    private FrameLayout initializeCroutonViewGroup(Resources resources)
    {
        FrameLayout framelayout = new FrameLayout(activity);
        if(onClickListener != null)
            framelayout.setOnClickListener(onClickListener);
        int i;
        int j;
        if(style.heightDimensionResId > 0)
            i = resources.getDimensionPixelSize(style.heightDimensionResId);
        else
            i = style.heightInPixels;
        if(style.widthDimensionResId > 0)
            j = resources.getDimensionPixelSize(style.widthDimensionResId);
        else
            j = style.widthInPixels;
        if(j == 0)
            j = -1;
        framelayout.setLayoutParams(new android.widget.FrameLayout.LayoutParams(j, i));
        if(style.backgroundColorValue != -1)
            framelayout.setBackgroundColor(style.backgroundColorValue);
        else
            framelayout.setBackgroundColor(resources.getColor(style.backgroundColorResourceId));
        if(style.backgroundDrawableResourceId != 0)
        {
            BitmapDrawable bitmapdrawable = new BitmapDrawable(resources, BitmapFactoryInstrumentation.decodeResource(resources, style.backgroundDrawableResourceId));
            if(style.isTileEnabled)
                bitmapdrawable.setTileModeXY(android.graphics.Shader.TileMode.REPEAT, android.graphics.Shader.TileMode.REPEAT);
            framelayout.setBackgroundDrawable(bitmapdrawable);
        }
        return framelayout;
    }

    private ImageView initializeImageView()
    {
        ImageView imageview = new ImageView(activity);
        imageview.setId(256);
        imageview.setAdjustViewBounds(true);
        imageview.setScaleType(style.imageScaleType);
        if(style.imageDrawable != null)
            imageview.setImageDrawable(style.imageDrawable);
        if(style.imageResId != 0)
            imageview.setImageResource(style.imageResId);
        android.widget.RelativeLayout.LayoutParams layoutparams = new android.widget.RelativeLayout.LayoutParams(-2, -2);
        layoutparams.addRule(9, -1);
        layoutparams.addRule(15, -1);
        imageview.setLayoutParams(layoutparams);
        return imageview;
    }

    private TextView initializeTextView(Resources resources)
    {
        TextView textview;
        textview = new TextView(activity);
        textview.setId(257);
        textview.setText(text);
        textview.setTypeface(Typeface.DEFAULT_BOLD);
        textview.setGravity(style.gravity);
        if(style.textColorValue == -1) goto _L2; else goto _L1
_L1:
        textview.setTextColor(style.textColorValue);
_L4:
        if(style.textSize != 0)
            textview.setTextSize(2, style.textSize);
        if(style.textShadowColorResId != 0)
            initializeTextViewShadow(resources, textview);
        if(style.textAppearanceResId != 0)
            textview.setTextAppearance(activity, style.textAppearanceResId);
        return textview;
_L2:
        if(style.textColorResourceId != 0)
            textview.setTextColor(resources.getColor(style.textColorResourceId));
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void initializeTextViewShadow(Resources resources, TextView textview)
    {
        int i = resources.getColor(style.textShadowColorResId);
        textview.setShadowLayer(style.textShadowRadius, style.textShadowDx, style.textShadowDy, i);
    }

    private boolean isCroutonViewNotNull()
    {
        return croutonView != null && croutonView.getParent() != null;
    }

    private boolean isCustomViewNotNull()
    {
        return customView != null && customView.getParent() != null;
    }

    public static Crouton make(Activity activity1, View view)
    {
        return new Crouton(activity1, view);
    }

    public static Crouton make(Activity activity1, View view, int i)
    {
        return new Crouton(activity1, view, (ViewGroup)activity1.findViewById(i));
    }

    public static Crouton make(Activity activity1, View view, int i, Configuration configuration1)
    {
        return new Crouton(activity1, view, (ViewGroup)activity1.findViewById(i), configuration1);
    }

    public static Crouton make(Activity activity1, View view, ViewGroup viewgroup)
    {
        return new Crouton(activity1, view, viewgroup);
    }

    public static Crouton makeText(Activity activity1, int i, Style style1)
    {
        return makeText(activity1, ((CharSequence) (activity1.getString(i))), style1);
    }

    public static Crouton makeText(Activity activity1, int i, Style style1, int j)
    {
        return makeText(activity1, ((CharSequence) (activity1.getString(i))), style1, (ViewGroup)activity1.findViewById(j));
    }

    public static Crouton makeText(Activity activity1, int i, Style style1, ViewGroup viewgroup)
    {
        return makeText(activity1, ((CharSequence) (activity1.getString(i))), style1, viewgroup);
    }

    public static Crouton makeText(Activity activity1, CharSequence charsequence, Style style1)
    {
        return new Crouton(activity1, charsequence, style1);
    }

    public static Crouton makeText(Activity activity1, CharSequence charsequence, Style style1, int i)
    {
        return new Crouton(activity1, charsequence, style1, (ViewGroup)activity1.findViewById(i));
    }

    public static Crouton makeText(Activity activity1, CharSequence charsequence, Style style1, ViewGroup viewgroup)
    {
        return new Crouton(activity1, charsequence, style1, viewgroup);
    }

    private void measureCroutonView()
    {
        View view = getView();
        int i;
        if(viewGroup != null)
            i = android.view.View.MeasureSpec.makeMeasureSpec(viewGroup.getMeasuredWidth(), 0x80000000);
        else
            i = android.view.View.MeasureSpec.makeMeasureSpec(activity.getWindow().getDecorView().getMeasuredWidth(), 0x80000000);
        view.measure(i, android.view.View.MeasureSpec.makeMeasureSpec(0, 0));
    }

    public static void show(Activity activity1, View view)
    {
        make(activity1, view).show();
    }

    public static void show(Activity activity1, View view, int i)
    {
        make(activity1, view, i).show();
    }

    public static void show(Activity activity1, View view, ViewGroup viewgroup)
    {
        make(activity1, view, viewgroup).show();
    }

    public static void showText(Activity activity1, int i, Style style1)
    {
        showText(activity1, ((CharSequence) (activity1.getString(i))), style1);
    }

    public static void showText(Activity activity1, int i, Style style1, int j)
    {
        showText(activity1, ((CharSequence) (activity1.getString(i))), style1, j);
    }

    public static void showText(Activity activity1, int i, Style style1, ViewGroup viewgroup)
    {
        showText(activity1, ((CharSequence) (activity1.getString(i))), style1, viewgroup);
    }

    public static void showText(Activity activity1, CharSequence charsequence, Style style1)
    {
        makeText(activity1, charsequence, style1).show();
    }

    public static void showText(Activity activity1, CharSequence charsequence, Style style1, int i)
    {
        makeText(activity1, charsequence, style1, (ViewGroup)activity1.findViewById(i)).show();
    }

    public static void showText(Activity activity1, CharSequence charsequence, Style style1, int i, Configuration configuration1)
    {
        makeText(activity1, charsequence, style1, (ViewGroup)activity1.findViewById(i)).setConfiguration(configuration1).show();
    }

    public static void showText(Activity activity1, CharSequence charsequence, Style style1, ViewGroup viewgroup)
    {
        makeText(activity1, charsequence, style1, viewgroup).show();
    }

    public void cancel()
    {
        Manager.getInstance().removeCroutonImmediately(this);
    }

    void detachActivity()
    {
        activity = null;
    }

    void detachLifecycleCallback()
    {
        lifecycleCallback = null;
    }

    void detachViewGroup()
    {
        viewGroup = null;
    }

    Activity getActivity()
    {
        return activity;
    }

    Configuration getConfiguration()
    {
        if(configuration == null)
            configuration = getStyle().configuration;
        return configuration;
    }

    public Animation getInAnimation()
    {
        if(inAnimation == null && activity != null)
            if(getConfiguration().inAnimationResId > 0)
            {
                inAnimation = AnimationUtils.loadAnimation(getActivity(), getConfiguration().inAnimationResId);
            } else
            {
                measureCroutonView();
                inAnimation = DefaultAnimationsBuilder.buildDefaultSlideInDownAnimation(getView());
            }
        return inAnimation;
    }

    LifecycleCallback getLifecycleCallback()
    {
        return lifecycleCallback;
    }

    public Animation getOutAnimation()
    {
        if(outAnimation == null && activity != null)
            if(getConfiguration().outAnimationResId > 0)
                outAnimation = AnimationUtils.loadAnimation(getActivity(), getConfiguration().outAnimationResId);
            else
                outAnimation = DefaultAnimationsBuilder.buildDefaultSlideOutUpAnimation(getView());
        return outAnimation;
    }

    Style getStyle()
    {
        return style;
    }

    CharSequence getText()
    {
        return text;
    }

    View getView()
    {
        if(customView != null)
            return customView;
        if(croutonView == null)
            initializeCroutonView();
        return croutonView;
    }

    ViewGroup getViewGroup()
    {
        return viewGroup;
    }

    public void hide()
    {
        Manager.getInstance().removeCrouton(this);
    }

    boolean isShowing()
    {
        return activity != null && (isCroutonViewNotNull() || isCustomViewNotNull());
    }

    public Crouton setConfiguration(Configuration configuration1)
    {
        configuration = configuration1;
        return this;
    }

    public void setLifecycleCallback(LifecycleCallback lifecyclecallback)
    {
        lifecycleCallback = lifecyclecallback;
    }

    public Crouton setOnClickListener(android.view.View.OnClickListener onclicklistener)
    {
        onClickListener = onclicklistener;
        return this;
    }

    public void show()
    {
        Manager.getInstance().add(this);
    }

    public String toString()
    {
        return (new StringBuilder()).append("Crouton{text=").append(text).append(", style=").append(style).append(", configuration=").append(configuration).append(", customView=").append(customView).append(", onClickListener=").append(onClickListener).append(", activity=").append(activity).append(", viewGroup=").append(viewGroup).append(", croutonView=").append(croutonView).append(", inAnimation=").append(inAnimation).append(", outAnimation=").append(outAnimation).append(", lifecycleCallback=").append(lifecycleCallback).append('}').toString();
    }

    private static final int IMAGE_ID = 256;
    private static final String NULL_PARAMETERS_ARE_NOT_ACCEPTED = "Null parameters are not accepted";
    private static final int TEXT_ID = 257;
    private Activity activity;
    private Configuration configuration;
    private FrameLayout croutonView;
    private final View customView;
    private Animation inAnimation;
    private LifecycleCallback lifecycleCallback;
    private android.view.View.OnClickListener onClickListener;
    private Animation outAnimation;
    private final Style style;
    private final CharSequence text;
    private ViewGroup viewGroup;
}
