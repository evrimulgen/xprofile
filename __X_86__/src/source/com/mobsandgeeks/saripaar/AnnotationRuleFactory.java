// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.mobsandgeeks.saripaar;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.*;
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
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.mobsandgeeks.saripaar:
//            Rules, Rule

class AnnotationRuleFactory
{

    AnnotationRuleFactory()
    {
    }

    private static Rule getCheckedRule(Field field, View view, Checked checked)
    {
        if(!android/widget/Checkable.isAssignableFrom(view.getClass()))
        {
            Object aobj[] = new Object[2];
            aobj[0] = field.getName();
            aobj[1] = com/mobsandgeeks/saripaar/annotation/Checked.getSimpleName();
            Log.w("AnnotationToRuleConverter", String.format("%s - @%s can only be applied to Checkable, its implementations and subclasses.", aobj));
            return null;
        }
        int i = checked.messageResId();
        String s;
        if(i != 0)
            s = view.getContext().getString(i);
        else
            s = checked.message();
        return Rules.checked(s, checked.checked());
    }

    private static Rule getConfirmPasswordRule(Field field, View view, ConfirmPassword confirmpassword, TextView textview)
    {
        if(!android/widget/TextView.isAssignableFrom(view.getClass()))
        {
            Object aobj[] = new Object[2];
            aobj[0] = field.getName();
            aobj[1] = com/mobsandgeeks/saripaar/annotation/ConfirmPassword.getSimpleName();
            Log.w("AnnotationToRuleConverter", String.format("%s - @%s can only be applied to TextView and its subclasses.", aobj));
            return null;
        }
        int i = confirmpassword.messageResId();
        String s;
        if(i != 0)
            s = view.getContext().getString(i);
        else
            s = confirmpassword.message();
        return Rules.eq(s, textview);
    }

    private static Rule getEmailRule(Field field, View view, Email email)
    {
        if(!android/widget/TextView.isAssignableFrom(view.getClass()))
        {
            Object aobj[] = new Object[2];
            aobj[0] = field.getName();
            aobj[1] = com/mobsandgeeks/saripaar/annotation/Regex.getSimpleName();
            Log.w("AnnotationToRuleConverter", String.format("%s - @%s can only be applied to TextView and its subclasses.", aobj));
            return null;
        }
        int i = email.messageResId();
        String s;
        Rule arule[];
        if(i != 0)
            s = view.getContext().getString(i);
        else
            s = email.message();
        arule = new Rule[2];
        arule[0] = Rules.eq(null, "");
        arule[1] = Rules.regex(s, "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}\\@[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}(\\.[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25})+", true);
        return Rules.or(s, arule);
    }

    private static Rule getIpAddressRule(Field field, View view, IpAddress ipaddress)
    {
        if(!android/widget/TextView.isAssignableFrom(view.getClass()))
        {
            Object aobj[] = new Object[2];
            aobj[0] = field.getName();
            aobj[1] = com/mobsandgeeks/saripaar/annotation/IpAddress.getSimpleName();
            Log.w("AnnotationToRuleConverter", String.format("%s - @%s can only be applied to TextView and its subclasses.", aobj));
            return null;
        }
        int i = ipaddress.messageResId();
        String s;
        Rule arule[];
        if(i != 0)
            s = view.getContext().getString(i);
        else
            s = ipaddress.message();
        arule = new Rule[2];
        arule[0] = Rules.eq(null, "");
        arule[1] = Rules.regex(s, "((25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9])\\.(25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[0-9]))", true);
        return Rules.or(s, arule);
    }

    private static Rule getNumberRule(Field field, View view, NumberRule numberrule)
    {
        ArrayList arraylist;
        String s1;
        double d;
        String s2;
        double d1;
        String s3;
        double d2;
        if(!android/widget/TextView.isAssignableFrom(view.getClass()))
        {
            Object aobj1[] = new Object[2];
            aobj1[0] = field.getName();
            aobj1[1] = com/mobsandgeeks/saripaar/annotation/NumberRule.getSimpleName();
            Log.w("AnnotationToRuleConverter", String.format("%s - @%s can only be applied to TextView and its subclasses.", aobj1));
            return null;
        }
        if(numberrule.type() == null)
        {
            Object aobj[] = new Object[1];
            aobj[0] = com/mobsandgeeks/saripaar/annotation/NumberRule.getSimpleName();
            throw new IllegalArgumentException(String.format("@%s.type() cannot be null.", aobj));
        }
        arraylist = new ArrayList();
        int i = numberrule.messageResId();
        static class _cls1
        {

            static final int $SwitchMap$com$mobsandgeeks$saripaar$annotation$NumberRule$NumberType[];

            static 
            {
                $SwitchMap$com$mobsandgeeks$saripaar$annotation$NumberRule$NumberType = new int[com.mobsandgeeks.saripaar.annotation.NumberRule.NumberType.values().length];
                try
                {
                    $SwitchMap$com$mobsandgeeks$saripaar$annotation$NumberRule$NumberType[com.mobsandgeeks.saripaar.annotation.NumberRule.NumberType.INTEGER.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$com$mobsandgeeks$saripaar$annotation$NumberRule$NumberType[com.mobsandgeeks.saripaar.annotation.NumberRule.NumberType.LONG.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$com$mobsandgeeks$saripaar$annotation$NumberRule$NumberType[com.mobsandgeeks.saripaar.annotation.NumberRule.NumberType.FLOAT.ordinal()] = 3;
                }
                catch(NoSuchFieldError nosuchfielderror2) { }
                try
                {
                    $SwitchMap$com$mobsandgeeks$saripaar$annotation$NumberRule$NumberType[com.mobsandgeeks.saripaar.annotation.NumberRule.NumberType.DOUBLE.ordinal()] = 4;
                }
                catch(NoSuchFieldError nosuchfielderror3)
                {
                    return;
                }
            }
        }

        String s;
        Rule arule[];
        if(i != 0)
            s = view.getContext().getString(i);
        else
            s = numberrule.message();
        _cls1..SwitchMap.com.mobsandgeeks.saripaar.annotation.NumberRule.NumberType[numberrule.type().ordinal()];
        JVM INSTR tableswitch 1 4: default 164
    //                   1 431
    //                   2 431
    //                   3 442
    //                   4 442;
           goto _L1 _L2 _L2 _L3 _L3
_L1:
        if(numberrule.lt() == 4.9406564584124654E-324D) goto _L5; else goto _L4
_L4:
        s3 = String.valueOf(numberrule.lt());
        d2 = Double.parseDouble(s3);
        _cls1..SwitchMap.com.mobsandgeeks.saripaar.annotation.NumberRule.NumberType[numberrule.type().ordinal()];
        JVM INSTR tableswitch 1 4: default 240
    //                   1 453
    //                   2 470
    //                   3 487
    //                   4 506;
           goto _L5 _L6 _L7 _L8 _L9
_L5:
        if(numberrule.gt() == 1.7976931348623157E+308D) goto _L11; else goto _L10
_L10:
        s2 = String.valueOf(numberrule.gt());
        d1 = Double.parseDouble(s2);
        _cls1..SwitchMap.com.mobsandgeeks.saripaar.annotation.NumberRule.NumberType[numberrule.type().ordinal()];
        JVM INSTR tableswitch 1 4: default 316
    //                   1 525
    //                   2 542
    //                   3 559
    //                   4 578;
           goto _L11 _L12 _L13 _L14 _L15
_L11:
        if(numberrule.eq() == 1.7976931348623157E+308D) goto _L17; else goto _L16
_L16:
        s1 = String.valueOf(numberrule.eq());
        d = Double.parseDouble(s1);
        _cls1..SwitchMap.com.mobsandgeeks.saripaar.annotation.NumberRule.NumberType[numberrule.type().ordinal()];
        JVM INSTR tableswitch 1 4: default 392
    //                   1 597
    //                   2 614
    //                   3 631
    //                   4 650;
           goto _L17 _L18 _L19 _L20 _L21
_L17:
        arule = new Rule[arraylist.size()];
        arraylist.toArray(arule);
        return Rules.and(s, arule);
_L2:
        Rules.regex(null, "\\d+", true);
          goto _L1
_L3:
        Rules.regex(null, "[-+]?[0-9]*\\.?[0-9]+([eE][-+]?[0-9]+)?", true);
          goto _L1
_L6:
        arraylist.add(Rules.lt(null, (int)d2));
          goto _L5
_L7:
        arraylist.add(Rules.lt(null, (long)d2));
          goto _L5
_L8:
        arraylist.add(Rules.lt(null, Float.parseFloat(s3)));
          goto _L5
_L9:
        arraylist.add(Rules.lt(null, Double.parseDouble(s3)));
          goto _L5
_L12:
        arraylist.add(Rules.gt(null, (int)d1));
          goto _L11
_L13:
        arraylist.add(Rules.gt(null, (long)d1));
          goto _L11
_L14:
        arraylist.add(Rules.gt(null, Float.parseFloat(s2)));
          goto _L11
_L15:
        arraylist.add(Rules.gt(null, Double.parseDouble(s2)));
          goto _L11
_L18:
        arraylist.add(Rules.eq(null, (int)d));
          goto _L17
_L19:
        arraylist.add(Rules.eq(null, (long)d));
          goto _L17
_L20:
        arraylist.add(Rules.eq(null, Float.parseFloat(s1)));
          goto _L17
_L21:
        arraylist.add(Rules.eq(null, Double.parseDouble(s1)));
          goto _L17
    }

    private static Rule getPasswordRule(Field field, View view, Password password)
    {
        if(!android/widget/TextView.isAssignableFrom(view.getClass()))
        {
            Object aobj[] = new Object[2];
            aobj[0] = field.getName();
            aobj[1] = com/mobsandgeeks/saripaar/annotation/Password.getSimpleName();
            Log.w("AnnotationToRuleConverter", String.format("%s - @%s can only be applied to TextView and its subclasses.", aobj));
            return null;
        }
        int i = password.messageResId();
        String s;
        if(i != 0)
            s = view.getContext().getString(i);
        else
            s = password.message();
        return Rules.required(s, false);
    }

    private static Rule getRegexRule(Field field, View view, Regex regex)
    {
        if(!android/widget/TextView.isAssignableFrom(view.getClass()))
        {
            Object aobj[] = new Object[2];
            aobj[0] = field.getName();
            aobj[1] = com/mobsandgeeks/saripaar/annotation/Regex.getSimpleName();
            Log.w("AnnotationToRuleConverter", String.format("%s - @%s can only be applied to TextView and its subclasses.", aobj));
            return null;
        }
        Context context = view.getContext();
        int i = regex.messageResId();
        String s;
        int j;
        String s1;
        if(i != 0)
            s = context.getString(i);
        else
            s = regex.message();
        j = regex.patternResId();
        if(j != 0)
            s1 = view.getContext().getString(j);
        else
            s1 = regex.pattern();
        return Rules.regex(s, s1, regex.trim());
    }

    private static Rule getRequiredRule(Field field, View view, Required required)
    {
        if(!android/widget/TextView.isAssignableFrom(view.getClass()))
        {
            Object aobj[] = new Object[2];
            aobj[0] = field.getName();
            aobj[1] = com/mobsandgeeks/saripaar/annotation/Required.getSimpleName();
            Log.w("AnnotationToRuleConverter", String.format("%s - @%s can only be applied to TextView and its subclasses.", aobj));
            return null;
        }
        int i = required.messageResId();
        String s;
        if(i != 0)
            s = view.getContext().getString(i);
        else
            s = required.message();
        return Rules.required(s, required.trim());
    }

    public static Rule getRule(Field field, View view, Annotation annotation)
    {
        Class class1 = annotation.annotationType();
        if(com/mobsandgeeks/saripaar/annotation/Checked.equals(class1))
            return getCheckedRule(field, view, (Checked)annotation);
        if(com/mobsandgeeks/saripaar/annotation/Required.equals(class1))
            return getRequiredRule(field, view, (Required)annotation);
        if(com/mobsandgeeks/saripaar/annotation/TextRule.equals(class1))
            return getTextRule(field, view, (TextRule)annotation);
        if(com/mobsandgeeks/saripaar/annotation/Regex.equals(class1))
            return getRegexRule(field, view, (Regex)annotation);
        if(com/mobsandgeeks/saripaar/annotation/NumberRule.equals(class1))
            return getNumberRule(field, view, (NumberRule)annotation);
        if(com/mobsandgeeks/saripaar/annotation/Password.equals(class1))
            return getPasswordRule(field, view, (Password)annotation);
        if(com/mobsandgeeks/saripaar/annotation/Email.equals(class1))
            return getEmailRule(field, view, (Email)annotation);
        if(com/mobsandgeeks/saripaar/annotation/IpAddress.equals(class1))
            return getIpAddressRule(field, view, (IpAddress)annotation);
        if(com/mobsandgeeks/saripaar/annotation/Select.equals(class1))
            return getSelectRule(field, view, (Select)annotation);
        else
            return null;
    }

    public static transient Rule getRule(Field field, View view, Annotation annotation, Object aobj[])
    {
        if(com/mobsandgeeks/saripaar/annotation/ConfirmPassword.equals(annotation.annotationType()))
        {
            TextView textview = (TextView)aobj[0];
            return getConfirmPasswordRule(field, view, (ConfirmPassword)annotation, textview);
        }
        if(aobj == null || aobj.length == 0)
            return getRule(field, view, annotation);
        else
            return null;
    }

    private static Rule getSelectRule(Field field, View view, Select select)
    {
        if(!android/widget/Spinner.isAssignableFrom(view.getClass()))
        {
            Object aobj[] = new Object[2];
            aobj[0] = field.getName();
            aobj[1] = android/widget/Spinner.getSimpleName();
            Log.w("AnnotationToRuleConverter", String.format("%s - @%s can only be applied to Spinner, its implementations and subclasses.", aobj));
            return null;
        }
        int i = select.messageResId();
        String s;
        if(i != 0)
            s = view.getContext().getString(i);
        else
            s = select.message();
        return Rules.spinnerNotEq(s, select.defaultSelection());
    }

    private static Rule getTextRule(Field field, View view, TextRule textrule)
    {
        if(!android/widget/TextView.isAssignableFrom(view.getClass()))
        {
            Object aobj[] = new Object[2];
            aobj[0] = field.getName();
            aobj[1] = com/mobsandgeeks/saripaar/annotation/TextRule.getSimpleName();
            Log.w("AnnotationToRuleConverter", String.format("%s - @%s can only be applied to TextView and its subclasses.", aobj));
            return null;
        }
        ArrayList arraylist = new ArrayList();
        int i = textrule.messageResId();
        String s;
        Rule arule[];
        if(i != 0)
            s = view.getContext().getString(i);
        else
            s = textrule.message();
        if(textrule.minLength() > 0)
            arraylist.add(Rules.minLength(null, textrule.minLength(), textrule.trim()));
        if(textrule.maxLength() != 0x7fffffff)
            arraylist.add(Rules.maxLength(null, textrule.maxLength(), textrule.trim()));
        arule = new Rule[arraylist.size()];
        arraylist.toArray(arule);
        return Rules.and(s, arule);
    }

    static final String TAG = "AnnotationToRuleConverter";
    static final String WARN_CHECKABLE = "%s - @%s can only be applied to Checkable, its implementations and subclasses.";
    static final String WARN_SPINNER = "%s - @%s can only be applied to Spinner, its implementations and subclasses.";
    static final String WARN_TEXT = "%s - @%s can only be applied to TextView and its subclasses.";
}
