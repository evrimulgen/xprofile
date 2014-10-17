// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.dmitriy.tarasov.android.intents;

import android.content.*;
import android.content.pm.*;
import android.net.Uri;
import android.text.TextUtils;
import java.io.File;
import java.net.URL;
import java.util.List;

public class IntentUtils
{

    public IntentUtils()
    {
    }

    public static Intent callPhone(String s)
    {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.CALL");
        intent.setData(Uri.parse((new StringBuilder()).append("tel:").append(s).toString()));
        return intent;
    }

    public static Intent cropImage(Context context, File file, int i, int j, int k, int l, boolean flag)
    {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setType("image/*");
        ResolveInfo resolveinfo = (ResolveInfo)context.getPackageManager().queryIntentActivities(intent, 0).get(0);
        intent.putExtra("outputX", i);
        intent.putExtra("outputY", j);
        intent.putExtra("aspectX", k);
        intent.putExtra("aspectY", l);
        intent.putExtra("scale", flag);
        intent.putExtra("return-data", true);
        intent.setData(Uri.fromFile(file));
        intent.setComponent(new ComponentName(resolveinfo.activityInfo.packageName, resolveinfo.activityInfo.name));
        return intent;
    }

    public static Intent dialPhone(String s)
    {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.DIAL");
        intent.setData(Uri.parse((new StringBuilder()).append("tel:").append(s).toString()));
        return intent;
    }

    public static Intent findLocation(String s)
    {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setData(Uri.parse(String.format("geo:0,0?q=%s", new Object[] {
            s
        })));
        return intent;
    }

    public static boolean isCropAvailable(Context context)
    {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setType("image/*");
        return isIntentAvailable(context, intent);
    }

    public static boolean isIntentAvailable(Context context, Intent intent)
    {
        return context.getPackageManager().queryIntentActivities(intent, 0x10000).size() > 0;
    }

    public static Intent openAudio(Uri uri)
    {
        return openMedia(uri, "audio/*");
    }

    public static Intent openAudio(File file)
    {
        return openAudio(Uri.fromFile(file));
    }

    public static Intent openAudio(String s)
    {
        return openAudio(new File(s));
    }

    public static Intent openImage(Uri uri)
    {
        return openMedia(uri, "image/*");
    }

    public static Intent openImage(File file)
    {
        return openImage(Uri.fromFile(file));
    }

    public static Intent openImage(String s)
    {
        return openImage(new File(s));
    }

    public static Intent openLink(String s)
    {
        if(!TextUtils.isEmpty(s) && !s.contains("://"))
            s = (new StringBuilder()).append("http://").append(s).toString();
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setData(Uri.parse(s));
        return intent;
    }

    public static Intent openLink(URL url)
    {
        return openLink(url.toString());
    }

    private static Intent openMedia(Uri uri, String s)
    {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setDataAndType(uri, s);
        return intent;
    }

    public static Intent openPlayStore(Context context)
    {
        return openPlayStore(context, true);
    }

    public static Intent openPlayStore(Context context, boolean flag)
    {
        String s = context.getPackageName();
        for(Intent intent = new Intent("android.intent.action.VIEW", Uri.parse((new StringBuilder()).append("market://details?id=").append(s).toString())); isIntentAvailable(context, intent) || !flag;)
            return intent;

        return openLink((new StringBuilder()).append("https://play.google.com/store/apps/details?id=").append(s).toString());
    }

    public static Intent openText(Uri uri)
    {
        return openMedia(uri, "text/plain");
    }

    public static Intent openText(File file)
    {
        return openText(Uri.fromFile(file));
    }

    public static Intent openText(String s)
    {
        return openText(new File(s));
    }

    public static Intent openVideo(Uri uri)
    {
        return openMedia(uri, "video/*");
    }

    public static Intent openVideo(File file)
    {
        return openVideo(Uri.fromFile(file));
    }

    public static Intent openVideo(String s)
    {
        return openVideo(new File(s));
    }

    public static Intent photoCapture(String s)
    {
        Uri uri = Uri.fromFile(new File(s));
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra("output", uri);
        return intent;
    }

    public static Intent pickContact()
    {
        return pickContact(null);
    }

    public static Intent pickContact(String s)
    {
        Intent intent;
        if(android.os.Build.VERSION.SDK_INT < 5)
            intent = new Intent("android.intent.action.PICK", android.provider.Contacts.People.CONTENT_URI);
        else
            intent = new Intent("android.intent.action.PICK", Uri.parse("content://com.android.contacts/contacts"));
        if(!TextUtils.isEmpty(s))
            intent.setType(s);
        return intent;
    }

    public static Intent pickFile()
    {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("file/*");
        return intent;
    }

    public static Intent pickImage()
    {
        Intent intent = new Intent("android.intent.action.PICK");
        intent.setType("image/*");
        return intent;
    }

    public static Intent sendEmail(String s, String s1, String s2)
    {
        return sendEmail(new String[] {
            s
        }, s1, s2);
    }

    public static Intent sendEmail(String as[], String s, String s1)
    {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("message/rfc822");
        intent.putExtra("android.intent.extra.EMAIL", as);
        intent.putExtra("android.intent.extra.SUBJECT", s);
        intent.putExtra("android.intent.extra.TEXT", s1);
        return intent;
    }

    public static Intent sendSms(String s, String s1)
    {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse((new StringBuilder()).append("tel:").append(s).toString()));
        intent.putExtra("address", s);
        intent.putExtra("sms_body", s1);
        intent.setType("vnd.android-dir/mms-sms");
        return intent;
    }

    public static Intent shareText(String s, String s1)
    {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.SEND");
        if(!TextUtils.isEmpty(s))
            intent.putExtra("android.intent.extra.SUBJECT", s);
        intent.putExtra("android.intent.extra.TEXT", s1);
        intent.setType("text/plain");
        return intent;
    }

    public static Intent showLocation(float f, float f1, Integer integer)
    {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Object aobj[] = new Object[2];
        aobj[0] = Float.valueOf(f);
        aobj[1] = Float.valueOf(f1);
        String s = String.format("geo:%s,%s", aobj);
        if(integer != null)
            s = String.format("%s?z=%s", new Object[] {
                s, integer
            });
        intent.setData(Uri.parse(s));
        return intent;
    }

    public static Intent showLocationServices()
    {
        Intent intent = new Intent("android.settings.LOCATION_SOURCE_SETTINGS");
        intent.addFlags(0x40080000);
        return intent;
    }

    public static Intent showStreetView(float f, float f1, Float float1, Integer integer, Float float2, Integer integer1)
    {
        StringBuilder stringbuilder = (new StringBuilder("google.streetview:cbll=")).append(f).append(",").append(f1);
        if(float1 != null || integer != null || float2 != null)
        {
            Object aobj[] = new Object[3];
            if(float1 == null)
                float1 = "";
            aobj[0] = float1;
            if(integer == null)
                integer = "";
            aobj[1] = integer;
            if(float2 == null)
                float2 = "";
            aobj[2] = float2;
            String s = String.format("%s,,%s,%s", aobj);
            stringbuilder.append("&cbp=1,").append(s);
        }
        if(integer1 != null)
            stringbuilder.append("&mz=").append(integer1);
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setData(Uri.parse(stringbuilder.toString()));
        return intent;
    }
}
