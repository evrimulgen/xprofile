// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.android.observables;

import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import rx.Observable;
import rx.operators.*;

public class ViewObservable
{

    public ViewObservable()
    {
    }

    public static Observable clicks(View view, boolean flag)
    {
        return Observable.create(new OperatorViewClick(view, flag));
    }

    public static Observable input(CompoundButton compoundbutton, boolean flag)
    {
        return Observable.create(new OperatorCompoundButtonInput(compoundbutton, flag));
    }

    public static Observable input(EditText edittext, boolean flag)
    {
        return Observable.create(new OperatorEditTextInput(edittext, flag));
    }
}
