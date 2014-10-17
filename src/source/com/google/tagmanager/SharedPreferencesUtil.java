// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.tagmanager;

import android.content.Context;
import android.content.SharedPreferences;

class SharedPreferencesUtil
{

    SharedPreferencesUtil()
    {
    }

    static void saveAsync(Context context, String s, String s1, String s2)
    {
        android.content.SharedPreferences.Editor editor = context.getSharedPreferences(s, 0).edit();
        editor.putString(s1, s2);
        saveEditorAsync(editor);
    }

    static void saveEditorAsync(android.content.SharedPreferences.Editor editor)
    {
        if(android.os.Build.VERSION.SDK_INT >= 9)
        {
            editor.apply();
            return;
        } else
        {
            (new Thread(new Runnable(editor) {

                public void run()
                {
                    editor.commit();
                }

                final android.content.SharedPreferences.Editor val$editor;

            
            {
                editor = editor1;
                super();
            }
            }
)).start();
            return;
        }
    }
}
