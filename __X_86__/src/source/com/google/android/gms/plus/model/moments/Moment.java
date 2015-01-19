// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.plus.model.moments;

import com.google.android.gms.common.data.Freezable;
import com.google.android.gms.internal.im;
import com.google.android.gms.internal.io;
import java.util.HashSet;
import java.util.Set;

// Referenced classes of package com.google.android.gms.plus.model.moments:
//            ItemScope

public interface Moment
    extends Freezable
{
    public static class Builder
    {

        public Moment build()
        {
            return new io(RM, uS, SH, Sz, SI, Oc);
        }

        public Builder setId(String s)
        {
            uS = s;
            RM.add(Integer.valueOf(2));
            return this;
        }

        public Builder setResult(ItemScope itemscope)
        {
            SH = (im)itemscope;
            RM.add(Integer.valueOf(4));
            return this;
        }

        public Builder setStartDate(String s)
        {
            Sz = s;
            RM.add(Integer.valueOf(5));
            return this;
        }

        public Builder setTarget(ItemScope itemscope)
        {
            SI = (im)itemscope;
            RM.add(Integer.valueOf(6));
            return this;
        }

        public Builder setType(String s)
        {
            Oc = s;
            RM.add(Integer.valueOf(7));
            return this;
        }

        private String Oc;
        private final Set RM = new HashSet();
        private im SH;
        private im SI;
        private String Sz;
        private String uS;

        public Builder()
        {
        }
    }


    public abstract String getId();

    public abstract ItemScope getResult();

    public abstract String getStartDate();

    public abstract ItemScope getTarget();

    public abstract String getType();

    public abstract boolean hasId();

    public abstract boolean hasResult();

    public abstract boolean hasStartDate();

    public abstract boolean hasTarget();

    public abstract boolean hasType();
}
