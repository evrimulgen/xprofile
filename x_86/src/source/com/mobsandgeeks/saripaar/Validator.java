// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.mobsandgeeks.saripaar;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.mobsandgeeks.saripaar.annotation.Checked;
import com.mobsandgeeks.saripaar.annotation.ConfirmPassword;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.IpAddress;
import com.mobsandgeeks.saripaar.annotation.NumberRule;
import com.mobsandgeeks.saripaar.annotation.Password;
import com.mobsandgeeks.saripaar.annotation.Regex;
import com.mobsandgeeks.saripaar.annotation.Required;
import com.mobsandgeeks.saripaar.annotation.Select;
import com.mobsandgeeks.saripaar.annotation.TextRule;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.instrumentation.AsyncTaskInstrumentation;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;

// Referenced classes of package com.mobsandgeeks.saripaar:
//            AnnotationRuleFactory, Rule

public class Validator
{
    private class AnnotationFieldPair
    {

        public Annotation annotation;
        public Field field;
        final Validator this$0;

        public AnnotationFieldPair(Annotation annotation1, Field field1)
        {
            this$0 = Validator.this;
            super();
            annotation = annotation1;
            field = field1;
        }
    }

    private class AnnotationFieldPairCompartor
        implements Comparator
    {

        private int getAnnotationOrder(Annotation annotation)
        {
            Class class1 = annotation.annotationType();
            if(class1.equals(com/mobsandgeeks/saripaar/annotation/Checked))
                return ((Checked)annotation).order();
            if(class1.equals(com/mobsandgeeks/saripaar/annotation/ConfirmPassword))
                return ((ConfirmPassword)annotation).order();
            if(class1.equals(com/mobsandgeeks/saripaar/annotation/Email))
                return ((Email)annotation).order();
            if(class1.equals(com/mobsandgeeks/saripaar/annotation/IpAddress))
                return ((IpAddress)annotation).order();
            if(class1.equals(com/mobsandgeeks/saripaar/annotation/NumberRule))
                return ((NumberRule)annotation).order();
            if(class1.equals(com/mobsandgeeks/saripaar/annotation/Password))
                return ((Password)annotation).order();
            if(class1.equals(com/mobsandgeeks/saripaar/annotation/Regex))
                return ((Regex)annotation).order();
            if(class1.equals(com/mobsandgeeks/saripaar/annotation/Required))
                return ((Required)annotation).order();
            if(class1.equals(com/mobsandgeeks/saripaar/annotation/Select))
                return ((Select)annotation).order();
            if(class1.equals(com/mobsandgeeks/saripaar/annotation/TextRule))
            {
                return ((TextRule)annotation).order();
            } else
            {
                Object aobj[] = new Object[1];
                aobj[0] = class1.getName();
                throw new IllegalArgumentException(String.format("%s is not a Saripaar annotation", aobj));
            }
        }

        public int compare(AnnotationFieldPair annotationfieldpair, AnnotationFieldPair annotationfieldpair1)
        {
            int i = getAnnotationOrder(annotationfieldpair.annotation);
            int j = getAnnotationOrder(annotationfieldpair1.annotation);
            if(i < j)
                return -1;
            return i != j ? 1 : 0;
        }

        public volatile int compare(Object obj, Object obj1)
        {
            return compare((AnnotationFieldPair)obj, (AnnotationFieldPair)obj1);
        }

        final Validator this$0;

        private AnnotationFieldPairCompartor()
        {
            this$0 = Validator.this;
            super();
        }

    }

    public static interface ValidationListener
    {

        public abstract void onValidationFailed(View view, Rule rule);

        public abstract void onValidationSucceeded();
    }

    private class ViewRulePair
    {

        public Rule rule;
        final Validator this$0;
        public View view;

        public ViewRulePair(View view1, Rule rule1)
        {
            this$0 = Validator.this;
            super();
            view = view1;
            rule = rule1;
        }
    }


    private Validator()
    {
        mAnnotationsProcessed = false;
        mViewsAndRules = new ArrayList();
        mProperties = new HashMap();
    }

    public Validator(Object obj)
    {
        this();
        if(obj == null)
        {
            throw new IllegalArgumentException("'controller' cannot be null");
        } else
        {
            mController = obj;
            return;
        }
    }

    private void createRulesFromAnnotations(List list)
    {
        TextView textview = null;
        TextView textview1 = null;
        Iterator iterator = list.iterator();
label0:
        do
        {
            AnnotationFieldPair annotationfieldpair;
label1:
            {
label2:
                {
label3:
                    {
                        if(!iterator.hasNext())
                            break label0;
                        annotationfieldpair = (AnnotationFieldPair)iterator.next();
                        if(annotationfieldpair.annotation.annotationType().equals(com/mobsandgeeks/saripaar/annotation/Password))
                        {
                            if(textview != null)
                                break label3;
                            textview = (TextView)getView(annotationfieldpair.field);
                        }
                        if(!annotationfieldpair.annotation.annotationType().equals(com/mobsandgeeks/saripaar/annotation/ConfirmPassword))
                            break label1;
                        if(textview == null)
                            throw new IllegalStateException("A @Password annotated field is required before you can use @ConfirmPassword.");
                        break label2;
                    }
                    throw new IllegalStateException("You cannot annotate two fields in the same Activity with @Password.");
                }
                if(textview1 != null)
                    throw new IllegalStateException("You cannot annotate two fields in the same Activity with @ConfirmPassword.");
                if(textview1 == null)
                    textview1 = (TextView)getView(annotationfieldpair.field);
            }
            ViewRulePair viewrulepair;
            if(annotationfieldpair.annotation.annotationType().equals(com/mobsandgeeks/saripaar/annotation/ConfirmPassword))
                viewrulepair = getViewAndRule(annotationfieldpair.field, annotationfieldpair.annotation, new Object[] {
                    textview
                });
            else
                viewrulepair = getViewAndRule(annotationfieldpair.field, annotationfieldpair.annotation, new Object[0]);
            if(viewrulepair != null)
                mViewsAndRules.add(viewrulepair);
        } while(true);
    }

    private List getAllViewFields()
    {
        ArrayList arraylist = new ArrayList();
        Object obj = mController;
        Class class1 = null;
        if(obj != null)
        {
            arraylist.addAll(getDeclaredViewFields(mController.getClass()));
            class1 = mController.getClass().getSuperclass();
        }
        for(; class1 != null && !class1.equals(java/lang/Object); class1 = class1.getSuperclass())
        {
            List list = getDeclaredViewFields(class1);
            if(list.size() > 0)
                arraylist.addAll(list);
        }

        return arraylist;
    }

    private List getDeclaredViewFields(Class class1)
    {
        ArrayList arraylist = new ArrayList();
        Field afield[] = class1.getDeclaredFields();
        int i = afield.length;
        for(int j = 0; j < i; j++)
        {
            Field field = afield[j];
            if(android/view/View.isAssignableFrom(field.getType()))
                arraylist.add(field);
        }

        return arraylist;
    }

    private List getSaripaarAnnotatedFields()
    {
        ArrayList arraylist = new ArrayList();
        for(Iterator iterator = getViewFieldsWithAnnotations().iterator(); iterator.hasNext();)
        {
            Field field = (Field)iterator.next();
            Annotation aannotation[] = field.getAnnotations();
            int i = aannotation.length;
            int j = 0;
            while(j < i) 
            {
                Annotation annotation = aannotation[j];
                if(isSaripaarAnnotation(annotation))
                    arraylist.add(new AnnotationFieldPair(annotation, field));
                j++;
            }
        }

        Collections.sort(arraylist, new AnnotationFieldPairCompartor());
        return arraylist;
    }

    private View getView(Field field)
    {
        View view;
        field.setAccessible(true);
        view = (View)field.get(mController);
        return view;
        IllegalArgumentException illegalargumentexception;
        illegalargumentexception;
        illegalargumentexception.printStackTrace();
_L2:
        return null;
        IllegalAccessException illegalaccessexception;
        illegalaccessexception;
        illegalaccessexception.printStackTrace();
        if(true) goto _L2; else goto _L1
_L1:
    }

    private transient ViewRulePair getViewAndRule(Field field, Annotation annotation, Object aobj[])
    {
        View view = getView(field);
        if(view == null)
        {
            Object aobj1[] = new Object[2];
            aobj1[0] = field.getType().getSimpleName();
            aobj1[1] = field.getName();
            Log.w("Validator", String.format("Your %s - %s is null. Please check your field assignment(s).", aobj1));
        } else
        {
            Rule rule;
            if(aobj != null && aobj.length > 0)
                rule = AnnotationRuleFactory.getRule(field, view, annotation, aobj);
            else
                rule = AnnotationRuleFactory.getRule(field, view, annotation);
            if(rule != null)
                return new ViewRulePair(view, rule);
        }
        return null;
    }

    private List getViewFieldsWithAnnotations()
    {
        ArrayList arraylist = new ArrayList();
        Iterator iterator = getAllViewFields().iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            Field field = (Field)iterator.next();
            Annotation aannotation[] = field.getAnnotations();
            if(aannotation != null && aannotation.length != 0)
                arraylist.add(field);
        } while(true);
        return arraylist;
    }

    private boolean isSaripaarAnnotation(Annotation annotation)
    {
        Class class1 = annotation.annotationType();
        return class1.equals(com/mobsandgeeks/saripaar/annotation/Checked) || class1.equals(com/mobsandgeeks/saripaar/annotation/ConfirmPassword) || class1.equals(com/mobsandgeeks/saripaar/annotation/Email) || class1.equals(com/mobsandgeeks/saripaar/annotation/IpAddress) || class1.equals(com/mobsandgeeks/saripaar/annotation/NumberRule) || class1.equals(com/mobsandgeeks/saripaar/annotation/Password) || class1.equals(com/mobsandgeeks/saripaar/annotation/Regex) || class1.equals(com/mobsandgeeks/saripaar/annotation/Required) || class1.equals(com/mobsandgeeks/saripaar/annotation/Select) || class1.equals(com/mobsandgeeks/saripaar/annotation/TextRule);
    }

    private ViewRulePair validateAllRules()
    {
        if(!mAnnotationsProcessed)
        {
            createRulesFromAnnotations(getSaripaarAnnotatedFields());
            mAnnotationsProcessed = true;
        }
        if(mViewsAndRules.size() != 0) goto _L2; else goto _L1
_L1:
        Log.i("Validator", "No rules found. Passing validation by default.");
_L4:
        return null;
_L2:
        ViewRulePair viewrulepair;
        Iterator iterator = mViewsAndRules.iterator();
        do
        {
            if(!iterator.hasNext())
                continue; /* Loop/switch isn't completed */
            viewrulepair = (ViewRulePair)iterator.next();
        } while(viewrulepair == null || viewrulepair.view != null && (!viewrulepair.view.isShown() || !viewrulepair.view.isEnabled()) || viewrulepair.rule.isValid(viewrulepair.view));
        break; /* Loop/switch isn't completed */
        if(true) goto _L4; else goto _L3
_L3:
        return viewrulepair;
    }

    public boolean cancelAsync()
    {
        AsyncTask asynctask = mAsyncValidationTask;
        boolean flag = false;
        if(asynctask != null)
        {
            flag = mAsyncValidationTask.cancel(true);
            mAsyncValidationTask = null;
        }
        return flag;
    }

    public boolean containsProperty(String s)
    {
        if(s != null)
            return mProperties.containsKey(s);
        else
            return false;
    }

    public Object getProperty(String s)
    {
        if(s == null)
            throw new IllegalArgumentException("'name' cannot be null");
        else
            return mProperties.get(s);
    }

    public ValidationListener getValidationListener()
    {
        return mValidationListener;
    }

    public boolean isValidating()
    {
        return mAsyncValidationTask != null && mAsyncValidationTask.getStatus() != android.os.AsyncTask.Status.FINISHED;
    }

    public void put(View view, Rule rule)
    {
        if(rule == null)
        {
            throw new IllegalArgumentException("'rule' cannot be null");
        } else
        {
            mViewsAndRules.add(new ViewRulePair(view, rule));
            return;
        }
    }

    public void put(View view, List list)
    {
        if(list == null)
            throw new IllegalArgumentException("'rules' cannot be null");
        for(Iterator iterator = list.iterator(); iterator.hasNext(); put(view, (Rule)iterator.next()));
    }

    public void put(Rule rule)
    {
        put(null, rule);
    }

    public void removeAllProperties()
    {
        mProperties.clear();
    }

    public Object removeProperty(String s)
    {
        if(s != null)
            return mProperties.remove(s);
        else
            return null;
    }

    public void removeRulesFor(View view)
    {
        if(view == null)
            throw new IllegalArgumentException("'view' cannot be null");
        for(int i = 0; i < mViewsAndRules.size();)
            if(((ViewRulePair)mViewsAndRules.get(i)).view == view)
                mViewsAndRules.remove(i);
            else
                i++;

    }

    public void setProperty(String s, Object obj)
    {
        if(s == null)
        {
            throw new IllegalArgumentException("'name' cannot be null");
        } else
        {
            mProperties.put(s, obj);
            return;
        }
    }

    public void setValidationListener(ValidationListener validationlistener)
    {
        mValidationListener = validationlistener;
    }

    public void validate()
    {
        this;
        JVM INSTR monitorenter ;
        if(mValidationListener == null)
            throw new IllegalStateException((new StringBuilder()).append("Set a ").append(com/mobsandgeeks/saripaar/Validator$ValidationListener.getSimpleName()).append(" before attempting to validate.").toString());
        break MISSING_BLOCK_LABEL_53;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        ViewRulePair viewrulepair = validateAllRules();
        if(viewrulepair != null)
            break MISSING_BLOCK_LABEL_74;
        mValidationListener.onValidationSucceeded();
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
        mValidationListener.onValidationFailed(viewrulepair.view, viewrulepair.rule);
          goto _L1
    }

    public void validateAsync()
    {
        if(mValidationListener == null)
            throw new IllegalStateException((new StringBuilder()).append("Set a ").append(com/mobsandgeeks/saripaar/Validator$ValidationListener.getSimpleName()).append(" before attempting to validate.").toString());
        if(mAsyncValidationTask != null)
        {
            mAsyncValidationTask.cancel(true);
            mAsyncValidationTask = null;
        }
        mAsyncValidationTask = new TraceFieldInterface() {

            public void _nr_setTrace(Trace trace)
            {
                try
                {
                    _nr_trace = trace;
                    return;
                }
                catch(Exception exception)
                {
                    return;
                }
            }

            protected transient ViewRulePair doInBackground(Void avoid1[])
            {
                return validateAllRules();
            }

            protected volatile Object doInBackground(Object aobj[])
            {
                TraceMachine.enterMethod(_nr_trace, "Validator$1#doInBackground", null);
_L1:
                ViewRulePair viewrulepair = doInBackground((Void[])aobj);
                TraceMachine.exitMethod();
                TraceMachine.unloadTraceContext(this);
                return viewrulepair;
                NoSuchFieldError nosuchfielderror;
                nosuchfielderror;
                TraceMachine.enterMethod(null, "Validator$1#doInBackground", null);
                  goto _L1
            }

            protected void onCancelled()
            {
                mAsyncValidationTask = null;
            }

            protected void onPostExecute(ViewRulePair viewrulepair)
            {
                if(viewrulepair == null)
                    mValidationListener.onValidationSucceeded();
                else
                    mValidationListener.onValidationFailed(viewrulepair.view, viewrulepair.rule);
                mAsyncValidationTask = null;
            }

            protected volatile void onPostExecute(Object obj)
            {
                TraceMachine.enterMethod(_nr_trace, "Validator$1#onPostExecute", null);
_L1:
                onPostExecute((ViewRulePair)obj);
                TraceMachine.exitMethod();
                return;
                NoSuchFieldError nosuchfielderror;
                nosuchfielderror;
                TraceMachine.enterMethod(null, "Validator$1#onPostExecute", null);
                  goto _L1
            }

            public Trace _nr_trace;
            final Validator this$0;

            
            {
                this$0 = Validator.this;
                super();
            }
        }
;
        AsyncTask asynctask = mAsyncValidationTask;
        Void avoid[] = (Void[])null;
        if(!(asynctask instanceof AsyncTask))
        {
            asynctask.execute(avoid);
            return;
        } else
        {
            AsyncTaskInstrumentation.execute((AsyncTask)asynctask, avoid);
            return;
        }
    }

    static final boolean DEBUG = false;
    static final String TAG = "Validator";
    private boolean mAnnotationsProcessed;
    private AsyncTask mAsyncValidationTask;
    private Object mController;
    private Map mProperties;
    private ValidationListener mValidationListener;
    private List mViewsAndRules;




/*
    static AsyncTask access$202(Validator validator, AsyncTask asynctask)
    {
        validator.mAsyncValidationTask = asynctask;
        return asynctask;
    }

*/
}
