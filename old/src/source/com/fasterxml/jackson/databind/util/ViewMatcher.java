// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.util;

import java.io.Serializable;

public abstract class ViewMatcher
{
    private static final class Empty extends ViewMatcher
        implements Serializable
    {

        public boolean isVisibleForView(Class class1)
        {
            return false;
        }

        static final Empty instance = new Empty();
        private static final long serialVersionUID = 1L;


        private Empty()
        {
        }
    }

    private static final class Multi extends ViewMatcher
        implements Serializable
    {

        public boolean isVisibleForView(Class class1)
        {
            int i = _views.length;
            int j = 0;
            do
            {
label0:
                {
                    boolean flag = false;
                    if(j < i)
                    {
                        Class class2 = _views[j];
                        if(class1 != class2 && !class2.isAssignableFrom(class1))
                            break label0;
                        flag = true;
                    }
                    return flag;
                }
                j++;
            } while(true);
        }

        private static final long serialVersionUID = 1L;
        private final Class _views[];

        public Multi(Class aclass[])
        {
            _views = aclass;
        }
    }

    private static final class Single extends ViewMatcher
        implements Serializable
    {

        public boolean isVisibleForView(Class class1)
        {
            return class1 == _view || _view.isAssignableFrom(class1);
        }

        private static final long serialVersionUID = 1L;
        private final Class _view;

        public Single(Class class1)
        {
            _view = class1;
        }
    }


    public ViewMatcher()
    {
    }

    public static ViewMatcher construct(Class aclass[])
    {
        if(aclass == null)
            return Empty.instance;
        switch(aclass.length)
        {
        default:
            return new Multi(aclass);

        case 0: // '\0'
            return Empty.instance;

        case 1: // '\001'
            return new Single(aclass[0]);
        }
    }

    public abstract boolean isVisibleForView(Class class1);
}
