// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package de.keyboardsurfer.android.widget.crouton;

import android.graphics.drawable.Drawable;

// Referenced classes of package de.keyboardsurfer.android.widget.crouton:
//            Configuration

public class Style
{
    public static class Builder
    {

        public Style build()
        {
            return new Style(this);
        }

        public Builder setBackgroundColor(int i)
        {
            backgroundColorResourceId = i;
            return this;
        }

        public Builder setBackgroundColorValue(int i)
        {
            backgroundColorValue = i;
            return this;
        }

        public Builder setBackgroundDrawable(int i)
        {
            backgroundDrawableResourceId = i;
            return this;
        }

        public Builder setConfiguration(Configuration configuration1)
        {
            configuration = configuration1;
            return this;
        }

        public Builder setGravity(int i)
        {
            gravity = i;
            return this;
        }

        public Builder setHeight(int i)
        {
            heightInPixels = i;
            return this;
        }

        public Builder setHeightDimensionResId(int i)
        {
            heightDimensionResId = i;
            return this;
        }

        public Builder setImageDrawable(Drawable drawable)
        {
            imageDrawable = drawable;
            return this;
        }

        public Builder setImageResource(int i)
        {
            imageResId = i;
            return this;
        }

        public Builder setImageScaleType(android.widget.ImageView.ScaleType scaletype)
        {
            imageScaleType = scaletype;
            return this;
        }

        public Builder setPaddingDimensionResId(int i)
        {
            paddingDimensionResId = i;
            return this;
        }

        public Builder setPaddingInPixels(int i)
        {
            paddingInPixels = i;
            return this;
        }

        public Builder setTextAppearance(int i)
        {
            textAppearanceResId = i;
            return this;
        }

        public Builder setTextColor(int i)
        {
            textColorResourceId = i;
            return this;
        }

        public Builder setTextColorValue(int i)
        {
            textColorValue = i;
            return this;
        }

        public Builder setTextShadowColor(int i)
        {
            textShadowColorResId = i;
            return this;
        }

        public Builder setTextShadowDx(float f)
        {
            textShadowDx = f;
            return this;
        }

        public Builder setTextShadowDy(float f)
        {
            textShadowDy = f;
            return this;
        }

        public Builder setTextShadowRadius(float f)
        {
            textShadowRadius = f;
            return this;
        }

        public Builder setTextSize(int i)
        {
            textSize = i;
            return this;
        }

        public Builder setTileEnabled(boolean flag)
        {
            isTileEnabled = flag;
            return this;
        }

        public Builder setWidth(int i)
        {
            widthInPixels = i;
            return this;
        }

        public Builder setWidthDimensionResId(int i)
        {
            widthDimensionResId = i;
            return this;
        }

        private int backgroundColorResourceId;
        private int backgroundColorValue;
        private int backgroundDrawableResourceId;
        private Configuration configuration;
        private int gravity;
        private int heightDimensionResId;
        private int heightInPixels;
        private Drawable imageDrawable;
        private int imageResId;
        private android.widget.ImageView.ScaleType imageScaleType;
        private boolean isTileEnabled;
        private int paddingDimensionResId;
        private int paddingInPixels;
        private int textAppearanceResId;
        private int textColorResourceId;
        private int textColorValue;
        private int textShadowColorResId;
        private float textShadowDx;
        private float textShadowDy;
        private float textShadowRadius;
        private int textSize;
        private int widthDimensionResId;
        private int widthInPixels;
























        public Builder()
        {
            configuration = Configuration.DEFAULT;
            paddingInPixels = 10;
            backgroundColorResourceId = 0x1060012;
            backgroundDrawableResourceId = 0;
            backgroundColorValue = -1;
            isTileEnabled = false;
            textColorResourceId = 0x106000b;
            textColorValue = -1;
            heightInPixels = -2;
            widthInPixels = -1;
            gravity = 17;
            imageDrawable = null;
            imageResId = 0;
            imageScaleType = android.widget.ImageView.ScaleType.FIT_XY;
        }

        public Builder(Style style)
        {
            configuration = style.configuration;
            backgroundColorValue = style.backgroundColorValue;
            backgroundColorResourceId = style.backgroundColorResourceId;
            backgroundDrawableResourceId = style.backgroundDrawableResourceId;
            isTileEnabled = style.isTileEnabled;
            textColorResourceId = style.textColorResourceId;
            textColorValue = style.textColorValue;
            heightInPixels = style.heightInPixels;
            heightDimensionResId = style.heightDimensionResId;
            widthInPixels = style.widthInPixels;
            widthDimensionResId = style.widthDimensionResId;
            gravity = style.gravity;
            imageDrawable = style.imageDrawable;
            textSize = style.textSize;
            textShadowColorResId = style.textShadowColorResId;
            textShadowRadius = style.textShadowRadius;
            textShadowDx = style.textShadowDx;
            textShadowDy = style.textShadowDy;
            textAppearanceResId = style.textAppearanceResId;
            imageResId = style.imageResId;
            imageScaleType = style.imageScaleType;
            paddingInPixels = style.paddingInPixels;
            paddingDimensionResId = style.paddingDimensionResId;
        }
    }


    private Style(Builder builder)
    {
        configuration = builder.configuration;
        backgroundColorResourceId = builder.backgroundColorResourceId;
        backgroundDrawableResourceId = builder.backgroundDrawableResourceId;
        isTileEnabled = builder.isTileEnabled;
        textColorResourceId = builder.textColorResourceId;
        textColorValue = builder.textColorValue;
        heightInPixels = builder.heightInPixels;
        heightDimensionResId = builder.heightDimensionResId;
        widthInPixels = builder.widthInPixels;
        widthDimensionResId = builder.widthDimensionResId;
        gravity = builder.gravity;
        imageDrawable = builder.imageDrawable;
        textSize = builder.textSize;
        textShadowColorResId = builder.textShadowColorResId;
        textShadowRadius = builder.textShadowRadius;
        textShadowDx = builder.textShadowDx;
        textShadowDy = builder.textShadowDy;
        textAppearanceResId = builder.textAppearanceResId;
        imageResId = builder.imageResId;
        imageScaleType = builder.imageScaleType;
        paddingInPixels = builder.paddingInPixels;
        paddingDimensionResId = builder.paddingDimensionResId;
        backgroundColorValue = builder.backgroundColorValue;
    }


    public String toString()
    {
        return (new StringBuilder()).append("Style{configuration=").append(configuration).append(", backgroundColorResourceId=").append(backgroundColorResourceId).append(", backgroundDrawableResourceId=").append(backgroundDrawableResourceId).append(", backgroundColorValue=").append(backgroundColorValue).append(", isTileEnabled=").append(isTileEnabled).append(", textColorResourceId=").append(textColorResourceId).append(", textColorValue=").append(textColorValue).append(", heightInPixels=").append(heightInPixels).append(", heightDimensionResId=").append(heightDimensionResId).append(", widthInPixels=").append(widthInPixels).append(", widthDimensionResId=").append(widthDimensionResId).append(", gravity=").append(gravity).append(", imageDrawable=").append(imageDrawable).append(", imageResId=").append(imageResId).append(", imageScaleType=").append(imageScaleType).append(", textSize=").append(textSize).append(", textShadowColorResId=").append(textShadowColorResId).append(", textShadowRadius=").append(textShadowRadius).append(", textShadowDy=").append(textShadowDy).append(", textShadowDx=").append(textShadowDx).append(", textAppearanceResId=").append(textAppearanceResId).append(", paddingInPixels=").append(paddingInPixels).append(", paddingDimensionResId=").append(paddingDimensionResId).append('}').toString();
    }

    public static final Style ALERT = (new Builder()).setBackgroundColorValue(-48060).build();
    public static final Style CONFIRM = (new Builder()).setBackgroundColorValue(0xff99cc00).build();
    public static final Style INFO = (new Builder()).setBackgroundColorValue(0xff33b5e5).build();
    public static final int NOT_SET = -1;
    public static final int holoBlueLight = 0xff33b5e5;
    public static final int holoGreenLight = 0xff99cc00;
    public static final int holoRedLight = -48060;
    final int backgroundColorResourceId;
    final int backgroundColorValue;
    final int backgroundDrawableResourceId;
    final Configuration configuration;
    final int gravity;
    final int heightDimensionResId;
    final int heightInPixels;
    final Drawable imageDrawable;
    final int imageResId;
    final android.widget.ImageView.ScaleType imageScaleType;
    final boolean isTileEnabled;
    final int paddingDimensionResId;
    final int paddingInPixels;
    final int textAppearanceResId;
    final int textColorResourceId;
    final int textColorValue;
    final int textShadowColorResId;
    final float textShadowDx;
    final float textShadowDy;
    final float textShadowRadius;
    final int textSize;
    final int widthDimensionResId;
    final int widthInPixels;

}
