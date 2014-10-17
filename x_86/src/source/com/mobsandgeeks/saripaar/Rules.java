// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.mobsandgeeks.saripaar;

import android.text.TextUtils;
import android.view.View;
import android.widget.*;
import java.util.*;

// Referenced classes of package com.mobsandgeeks.saripaar:
//            Rule

public final class Rules
{

    public Rules()
    {
    }

    public static transient Rule and(String s, Rule arule[])
    {
        return new Rule(s, arule) {

            public boolean isValid(View view)
            {
                boolean flag = true;
                Rule arule1[] = rules;
                int i = arule1.length;
                int j = 0;
                do
                {
label0:
                    {
                        if(j < i)
                        {
                            Rule rule = arule1[j];
                            if(rule != null)
                                flag &= rule.isValid(view);
                            if(flag)
                                break label0;
                        }
                        return flag;
                    }
                    j++;
                } while(true);
            }

            public volatile boolean isValid(Object obj)
            {
                return isValid((View)obj);
            }

            final Rule val$rules[];

            
            {
                rules = arule;
                super(s);
            }
        }
;
    }

    public static Rule checked(String s, boolean flag)
    {
        return new Rule(s, flag) {

            public boolean isValid(Checkable checkable)
            {
                return checkable.isChecked() == checked;
            }

            public volatile boolean isValid(Object obj)
            {
                return isValid((Checkable)obj);
            }

            final boolean val$checked;

            
            {
                checked = flag;
                super(s);
            }
        }
;
    }

    public static Rule compositeAnd(String s, LinkedHashMap linkedhashmap)
    {
        return new Rule(s, linkedhashmap) {

            public boolean isValid(View view)
            {
                boolean flag = true;
                Iterator iterator = viewsAndRules.keySet().iterator();
                do
                {
                    if(!iterator.hasNext())
                        break;
                    View view1 = (View)iterator.next();
                    flag &= ((Rule)viewsAndRules.get(view1)).isValid(view);
                } while(flag);
                return flag;
            }

            public volatile boolean isValid(Object obj)
            {
                return isValid((View)obj);
            }

            final LinkedHashMap val$viewsAndRules;

            
            {
                viewsAndRules = linkedhashmap;
                super(s);
            }
        }
;
    }

    public static Rule compositeOr(String s, LinkedHashMap linkedhashmap)
    {
        return new Rule(s, linkedhashmap) {

            public boolean isValid(View view)
            {
                boolean flag = false;
                Iterator iterator = viewsAndRules.keySet().iterator();
                do
                {
                    if(!iterator.hasNext())
                        break;
                    View view1 = (View)iterator.next();
                    flag |= ((Rule)viewsAndRules.get(view1)).isValid(view1);
                } while(!flag);
                return flag;
            }

            public volatile boolean isValid(Object obj)
            {
                return isValid((View)obj);
            }

            final LinkedHashMap val$viewsAndRules;

            
            {
                viewsAndRules = linkedhashmap;
                super(s);
            }
        }
;
    }

    public static Rule eq(String s, double d)
    {
        return new Rule(s, d) {

            public boolean isValid(TextView textview)
            {
label0:
                {
label1:
                    {
                        String s1 = Rules.getText(textview, true);
                        boolean flag = false;
                        if(s1 != null)
                        {
                            if(!s1.matches("[-+]?[0-9]*\\.?[0-9]+([eE][-+]?[0-9]+)?"))
                                break label0;
                            if(Double.parseDouble(s1) != expectedDouble)
                                break label1;
                            flag = true;
                        }
                        return flag;
                    }
                    return false;
                }
                return false;
            }

            public volatile boolean isValid(Object obj)
            {
                return isValid((TextView)obj);
            }

            final double val$expectedDouble;

            
            {
                expectedDouble = d;
                super(s);
            }
        }
;
    }

    public static Rule eq(String s, float f)
    {
        return new Rule(s, f) {

            public boolean isValid(TextView textview)
            {
label0:
                {
label1:
                    {
                        String s1 = Rules.getText(textview, true);
                        boolean flag = false;
                        if(s1 != null)
                        {
                            if(!s1.matches("[-+]?[0-9]*\\.?[0-9]+([eE][-+]?[0-9]+)?"))
                                break label0;
                            if(Float.parseFloat(s1) != expectedFloat)
                                break label1;
                            flag = true;
                        }
                        return flag;
                    }
                    return false;
                }
                return false;
            }

            public volatile boolean isValid(Object obj)
            {
                return isValid((TextView)obj);
            }

            final float val$expectedFloat;

            
            {
                expectedFloat = f;
                super(s);
            }
        }
;
    }

    public static Rule eq(String s, int i)
    {
        return eq(s, i);
    }

    public static Rule eq(String s, long l)
    {
        return new Rule(s, l) {

            public boolean isValid(TextView textview)
            {
label0:
                {
label1:
                    {
                        String s1 = Rules.getText(textview, true);
                        boolean flag = false;
                        if(s1 != null)
                        {
                            if(!s1.matches("\\d+"))
                                break label0;
                            if(Long.parseLong(s1) != expectedLong)
                                break label1;
                            flag = true;
                        }
                        return flag;
                    }
                    return false;
                }
                return false;
            }

            public volatile boolean isValid(Object obj)
            {
                return isValid((TextView)obj);
            }

            final long val$expectedLong;

            
            {
                expectedLong = l;
                super(s);
            }
        }
;
    }

    public static Rule eq(String s, TextView textview)
    {
        if(textview == null)
            throw new IllegalArgumentException("'anotherTextView' cannot be null");
        else
            return new Rule(s, textview) {

                public boolean isValid(TextView textview1)
                {
                    return textview1.getText().toString().equals(anotherTextView.getText().toString());
                }

                public volatile boolean isValid(Object obj)
                {
                    return isValid((TextView)obj);
                }

                final TextView val$anotherTextView;

            
            {
                anotherTextView = textview;
                super(s);
            }
            }
;
    }

    public static Rule eq(String s, String s1)
    {
        return eq(s, s1, false, false);
    }

    public static Rule eq(String s, String s1, boolean flag, boolean flag1)
    {
        String s2;
        if(s1 == null)
            s2 = "";
        else
            s2 = s1;
        return new Rule(s, flag1, flag, s2) {

            public boolean isValid(TextView textview)
            {
                String s3;
label0:
                {
                    s3 = Rules.getText(textview, trimInput);
                    boolean flag2 = false;
                    if(s3 != null)
                    {
                        if(!ignoreCase)
                            break label0;
                        flag2 = s3.equalsIgnoreCase(cleanString);
                    }
                    return flag2;
                }
                return s3.equals(cleanString);
            }

            public volatile boolean isValid(Object obj)
            {
                return isValid((TextView)obj);
            }

            final String val$cleanString;
            final boolean val$ignoreCase;
            final boolean val$trimInput;

            
            {
                trimInput = flag;
                ignoreCase = flag1;
                cleanString = s1;
                super(s);
            }
        }
;
    }

    private static String getText(TextView textview, boolean flag)
    {
        Object obj = null;
        if(textview != null)
        {
            obj = textview.getText();
            if(flag)
                obj = obj.toString().trim();
        }
        if(obj != null)
            return obj.toString();
        else
            return null;
    }

    public static Rule gt(String s, double d)
    {
        return new Rule(s, d) {

            public boolean isValid(TextView textview)
            {
label0:
                {
label1:
                    {
                        String s1 = Rules.getText(textview, true);
                        boolean flag = false;
                        if(s1 != null)
                        {
                            if(!s1.matches("[-+]?[0-9]*\\.?[0-9]+([eE][-+]?[0-9]+)?"))
                                break label0;
                            if(Double.parseDouble(s1) <= lesserDouble)
                                break label1;
                            flag = true;
                        }
                        return flag;
                    }
                    return false;
                }
                return false;
            }

            public volatile boolean isValid(Object obj)
            {
                return isValid((TextView)obj);
            }

            final double val$lesserDouble;

            
            {
                lesserDouble = d;
                super(s);
            }
        }
;
    }

    public static Rule gt(String s, float f)
    {
        return new Rule(s, f) {

            public boolean isValid(TextView textview)
            {
label0:
                {
label1:
                    {
                        String s1 = Rules.getText(textview, true);
                        boolean flag = false;
                        if(s1 != null)
                        {
                            if(!s1.matches("[-+]?[0-9]*\\.?[0-9]+([eE][-+]?[0-9]+)?"))
                                break label0;
                            if(Float.parseFloat(s1) <= lesserFloat)
                                break label1;
                            flag = true;
                        }
                        return flag;
                    }
                    return false;
                }
                return false;
            }

            public volatile boolean isValid(Object obj)
            {
                return isValid((TextView)obj);
            }

            final float val$lesserFloat;

            
            {
                lesserFloat = f;
                super(s);
            }
        }
;
    }

    public static Rule gt(String s, int i)
    {
        return gt(s, i);
    }

    public static Rule gt(String s, long l)
    {
        return new Rule(s, l) {

            public boolean isValid(TextView textview)
            {
label0:
                {
label1:
                    {
                        String s1 = Rules.getText(textview, true);
                        boolean flag = false;
                        if(s1 != null)
                        {
                            if(!s1.matches("\\d+"))
                                break label0;
                            if(Long.parseLong(s1) <= lesserLong)
                                break label1;
                            flag = true;
                        }
                        return flag;
                    }
                    return false;
                }
                return false;
            }

            public volatile boolean isValid(Object obj)
            {
                return isValid((TextView)obj);
            }

            final long val$lesserLong;

            
            {
                lesserLong = l;
                super(s);
            }
        }
;
    }

    public static Rule lt(String s, double d)
    {
        return new Rule(s, d) {

            public boolean isValid(TextView textview)
            {
label0:
                {
label1:
                    {
                        String s1 = Rules.getText(textview, true);
                        boolean flag = false;
                        if(s1 != null)
                        {
                            if(!s1.matches("[-+]?[0-9]*\\.?[0-9]+([eE][-+]?[0-9]+)?"))
                                break label0;
                            if(Double.parseDouble(s1) >= greaterDouble)
                                break label1;
                            flag = true;
                        }
                        return flag;
                    }
                    return false;
                }
                return false;
            }

            public volatile boolean isValid(Object obj)
            {
                return isValid((TextView)obj);
            }

            final double val$greaterDouble;

            
            {
                greaterDouble = d;
                super(s);
            }
        }
;
    }

    public static Rule lt(String s, float f)
    {
        return new Rule(s, f) {

            public boolean isValid(TextView textview)
            {
label0:
                {
label1:
                    {
                        String s1 = Rules.getText(textview, true);
                        boolean flag = false;
                        if(s1 != null)
                        {
                            if(!s1.matches("[-+]?[0-9]*\\.?[0-9]+([eE][-+]?[0-9]+)?"))
                                break label0;
                            if(Float.parseFloat(s1) >= greaterFloat)
                                break label1;
                            flag = true;
                        }
                        return flag;
                    }
                    return false;
                }
                return false;
            }

            public volatile boolean isValid(Object obj)
            {
                return isValid((TextView)obj);
            }

            final float val$greaterFloat;

            
            {
                greaterFloat = f;
                super(s);
            }
        }
;
    }

    public static Rule lt(String s, int i)
    {
        return lt(s, i);
    }

    public static Rule lt(String s, long l)
    {
        return new Rule(s, l) {

            public boolean isValid(TextView textview)
            {
label0:
                {
label1:
                    {
                        String s1 = Rules.getText(textview, true);
                        boolean flag = false;
                        if(s1 != null)
                        {
                            if(!s1.matches("\\d+"))
                                break label0;
                            if(Long.parseLong(s1) >= greaterLong)
                                break label1;
                            flag = true;
                        }
                        return flag;
                    }
                    return false;
                }
                return false;
            }

            public volatile boolean isValid(Object obj)
            {
                return isValid((TextView)obj);
            }

            final long val$greaterLong;

            
            {
                greaterLong = l;
                super(s);
            }
        }
;
    }

    public static Rule maxLength(String s, int i, boolean flag)
    {
        return new Rule(s, flag, i) {

            public boolean isValid(TextView textview)
            {
                String s1 = Rules.getText(textview, trimInput);
                boolean flag1 = false;
                if(s1 != null)
                {
                    int j = s1.length();
                    int k = maxLength;
                    flag1 = false;
                    if(j <= k)
                        flag1 = true;
                }
                return flag1;
            }

            public volatile boolean isValid(Object obj)
            {
                return isValid((TextView)obj);
            }

            final int val$maxLength;
            final boolean val$trimInput;

            
            {
                trimInput = flag;
                maxLength = i;
                super(s);
            }
        }
;
    }

    public static Rule minLength(String s, int i, boolean flag)
    {
        return new Rule(s, flag, i) {

            public boolean isValid(TextView textview)
            {
                String s1 = Rules.getText(textview, trimInput);
                boolean flag1 = false;
                if(s1 != null)
                {
                    int j = s1.length();
                    int k = minLength;
                    flag1 = false;
                    if(j >= k)
                        flag1 = true;
                }
                return flag1;
            }

            public volatile boolean isValid(Object obj)
            {
                return isValid((TextView)obj);
            }

            final int val$minLength;
            final boolean val$trimInput;

            
            {
                trimInput = flag;
                minLength = i;
                super(s);
            }
        }
;
    }

    public static transient Rule or(String s, Rule arule[])
    {
        return new Rule(s, arule) {

            public boolean isValid(View view)
            {
                boolean flag = false;
                Rule arule1[] = rules;
                int i = arule1.length;
                int j = 0;
                do
                {
label0:
                    {
                        if(j < i)
                        {
                            Rule rule = arule1[j];
                            if(rule != null)
                                flag |= rule.isValid(view);
                            if(!flag)
                                break label0;
                        }
                        return flag;
                    }
                    j++;
                } while(true);
            }

            public volatile boolean isValid(Object obj)
            {
                return isValid((View)obj);
            }

            final Rule val$rules[];

            
            {
                rules = arule;
                super(s);
            }
        }
;
    }

    public static Rule regex(String s, String s1, boolean flag)
    {
        if(s1 == null)
            throw new IllegalArgumentException("'regex' cannot be null");
        else
            return new Rule(s, flag, s1) {

                public boolean isValid(TextView textview)
                {
                    String s2 = Rules.getText(textview, trimInput);
                    if(s2 != null)
                        return s2.matches(regex);
                    else
                        return false;
                }

                public volatile boolean isValid(Object obj)
                {
                    return isValid((TextView)obj);
                }

                final String val$regex;
                final boolean val$trimInput;

            
            {
                trimInput = flag;
                regex = s1;
                super(s);
            }
            }
;
    }

    public static Rule required(String s, boolean flag)
    {
        return new Rule(s, flag) {

            public boolean isValid(TextView textview)
            {
                return !TextUtils.isEmpty(Rules.getText(textview, trimInput));
            }

            public volatile boolean isValid(Object obj)
            {
                return isValid((TextView)obj);
            }

            final boolean val$trimInput;

            
            {
                trimInput = flag;
                super(s);
            }
        }
;
    }

    public static Rule spinnerEq(String s, int i)
    {
        return new Rule(s, i) {

            public boolean isValid(Spinner spinner)
            {
                return spinner.getSelectedItemPosition() == expectedPosition;
            }

            public volatile boolean isValid(Object obj)
            {
                return isValid((Spinner)obj);
            }

            final int val$expectedPosition;

            
            {
                expectedPosition = i;
                super(s);
            }
        }
;
    }

    public static Rule spinnerEq(String s, String s1, boolean flag, boolean flag1)
    {
        return new Rule(s, s1, flag1, flag) {

            public boolean isValid(Spinner spinner)
            {
                Object obj = spinner.getSelectedItem();
                boolean flag2;
                if(expectedString == null && obj == null)
                {
                    flag2 = true;
                } else
                {
                    String s2 = expectedString;
                    flag2 = false;
                    if(s2 != null)
                    {
                        flag2 = false;
                        if(obj != null)
                        {
                            String s3 = obj.toString();
                            if(trimInput)
                                s3 = s3.trim();
                            if(ignoreCase)
                                return s3.equalsIgnoreCase(expectedString);
                            else
                                return s3.equals(expectedString);
                        }
                    }
                }
                return flag2;
            }

            public volatile boolean isValid(Object obj)
            {
                return isValid((Spinner)obj);
            }

            final String val$expectedString;
            final boolean val$ignoreCase;
            final boolean val$trimInput;

            
            {
                expectedString = s1;
                trimInput = flag;
                ignoreCase = flag1;
                super(s);
            }
        }
;
    }

    public static Rule spinnerNotEq(String s, int i)
    {
        return new Rule(s, i) {

            public boolean isValid(Spinner spinner)
            {
                return spinner.getSelectedItemPosition() != selection;
            }

            public volatile boolean isValid(Object obj)
            {
                return isValid((Spinner)obj);
            }

            final int val$selection;

            
            {
                selection = i;
                super(s);
            }
        }
;
    }

    public static final String EMPTY_STRING = "";
    private static final String GOOD_IRI_CHAR = "a-zA-Z0-9\240-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF";
    public static final String REGEX_DECIMAL = "[-+]?[0-9]*\\.?[0-9]+([eE][-+]?[0-9]+)?";
    public static final String REGEX_EMAIL = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}\\@[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}(\\.[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25})+";
    public static final String REGEX_INTEGER = "\\d+";
    public static final String REGEX_IP_ADDRESS = "((25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9])\\.(25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[0-9]))";
    public static final String REGEX_WEB_URL = "((?:(http|https|Http|Https|rtsp|Rtsp):\\/\\/(?:(?:[a-zA-Z0-9\\$\\-\\_\\.\\+\\!\\*\\'\\(\\)\\,\\;\\?\\&\\=]|(?:\\%[a-fA-F0-9]{2})){1,64}(?:\\:(?:[a-zA-Z0-9\\$\\-\\_\\.\\+\\!\\*\\'\\(\\)\\,\\;\\?\\&\\=]|(?:\\%[a-fA-F0-9]{2})){1,25})?\\@)?)?((?:(?:[a-zA-Z0-9\240-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF][a-zA-Z0-9\240-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF\\-]{0,64}\\.)+(?:(?:aero|arpa|asia|a[cdefgilmnoqrstuwxz])|(?:biz|b[abdefghijmnorstvwyz])|(?:cat|com|coop|c[acdfghiklmnoruvxyz])|d[ejkmoz]|(?:edu|e[cegrstu])|f[ijkmor]|(?:gov|g[abdefghilmnpqrstuwy])|h[kmnrtu]|(?:info|int|i[delmnoqrst])|(?:jobs|j[emop])|k[eghimnprwyz]|l[abcikrstuvy]|(?:mil|mobi|museum|m[acdeghklmnopqrstuvwxyz])|(?:name|net|n[acefgilopruz])|(?:org|om)|(?:pro|p[aefghklmnrstwy])|qa|r[eosuw]|s[abcdeghijklmnortuvyz]|(?:tel|travel|t[cdfghjklmnoprtvwz])|u[agksyz]|v[aceginu]|w[fs]|(?:xn\\-\\-0zwm56d|xn\\-\\-11b5bs3a9aj6g|xn\\-\\-80akhbyknj4f|xn\\-\\-9t4b11yi5a|xn\\-\\-deba0ad|xn\\-\\-g6w251d|xn\\-\\-hgbk6aj7f53bba|xn\\-\\-hlcj6aya9esc7a|xn\\-\\-jxalpdlp|xn\\-\\-kgbechtv|xn\\-\\-zckzah)|y[etu]|z[amw]))|(?:(?:25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9])\\.(?:25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(?:25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(?:25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[0-9])))(?:\\:\\d{1,5})?)(\\/(?:(?:[a-zA-Z0-9\240-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF\\;\\/\\?\\:\\@\\&\\=\\#\\~\\-\\.\\+\\!\\*\\'\\(\\)\\,\\_])|(?:\\%[a-fA-F0-9]{2}))*)?(?:\\b|$)";
    private static final String TOP_LEVEL_DOMAIN_STR_FOR_WEB_URL = "(?:(?:aero|arpa|asia|a[cdefgilmnoqrstuwxz])|(?:biz|b[abdefghijmnorstvwyz])|(?:cat|com|coop|c[acdfghiklmnoruvxyz])|d[ejkmoz]|(?:edu|e[cegrstu])|f[ijkmor]|(?:gov|g[abdefghilmnpqrstuwy])|h[kmnrtu]|(?:info|int|i[delmnoqrst])|(?:jobs|j[emop])|k[eghimnprwyz]|l[abcikrstuvy]|(?:mil|mobi|museum|m[acdeghklmnopqrstuvwxyz])|(?:name|net|n[acefgilopruz])|(?:org|om)|(?:pro|p[aefghklmnrstwy])|qa|r[eosuw]|s[abcdeghijklmnortuvyz]|(?:tel|travel|t[cdfghjklmnoprtvwz])|u[agksyz]|v[aceginu]|w[fs]|(?:xn\\-\\-0zwm56d|xn\\-\\-11b5bs3a9aj6g|xn\\-\\-80akhbyknj4f|xn\\-\\-9t4b11yi5a|xn\\-\\-deba0ad|xn\\-\\-g6w251d|xn\\-\\-hgbk6aj7f53bba|xn\\-\\-hlcj6aya9esc7a|xn\\-\\-jxalpdlp|xn\\-\\-kgbechtv|xn\\-\\-zckzah)|y[etu]|z[amw]))";

}
