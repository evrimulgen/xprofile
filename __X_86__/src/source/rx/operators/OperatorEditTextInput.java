// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package rx.operators;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import rx.Subscriber;
import rx.android.observables.Assertions;
import rx.android.subscriptions.AndroidSubscriptions;
import rx.functions.Action0;

public class OperatorEditTextInput
    implements rx.Observable.OnSubscribe
{
    private static class SimpleTextWatcher
        implements TextWatcher
    {

        public void afterTextChanged(Editable editable)
        {
        }

        public void beforeTextChanged(CharSequence charsequence, int i, int j, int k)
        {
        }

        public void onTextChanged(CharSequence charsequence, int i, int j, int k)
        {
        }

        private SimpleTextWatcher()
        {
        }

    }


    public OperatorEditTextInput(EditText edittext, boolean flag)
    {
        input = edittext;
        emitInitialValue = flag;
    }

    public volatile void call(Object obj)
    {
        call((Subscriber)obj);
    }

    public void call(final Subscriber observer)
    {
        Assertions.assertUiThread();
        final SimpleTextWatcher watcher = new SimpleTextWatcher() {

            public void afterTextChanged(Editable editable)
            {
                observer.onNext(editable.toString());
            }

            final OperatorEditTextInput this$0;
            final Subscriber val$observer;

            
            {
                this$0 = OperatorEditTextInput.this;
                observer = subscriber;
                SimpleTextWatcher();
            }
        }
;
        rx.Subscription subscription = AndroidSubscriptions.unsubscribeInUiThread(new Action0() {

            public void call()
            {
                input.removeTextChangedListener(watcher);
            }

            final OperatorEditTextInput this$0;
            final TextWatcher val$watcher;

            
            {
                this$0 = OperatorEditTextInput.this;
                watcher = textwatcher;
                Object();
            }
        }
);
        if(emitInitialValue)
            observer.onNext(input.getEditableText().toString());
        input.addTextChangedListener(watcher);
        observer.add(subscription);
    }

    private final boolean emitInitialValue;
    private final EditText input;

}
