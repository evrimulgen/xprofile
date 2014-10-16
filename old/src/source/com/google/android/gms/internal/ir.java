// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.plus.model.people.Person;
import java.util.*;

// Referenced classes of package com.google.android.gms.internal:
//            fb, is, ey, it, 
//            iu, iv, iw, ix, 
//            iy, iz, ja, jb

public final class ir extends fb
    implements SafeParcelable, Person
{
    public static final class a extends fb
        implements SafeParcelable, com.google.android.gms.plus.model.people.Person.AgeRange
    {

        protected boolean a(fb.a a1)
        {
            return RM.contains(Integer.valueOf(a1.eu()));
        }

        protected Object ak(String s)
        {
            return null;
        }

        protected boolean al(String s)
        {
            return false;
        }

        protected Object b(fb.a a1)
        {
            switch(a1.eu())
            {
            default:
                throw new IllegalStateException((new StringBuilder()).append("Unknown safe parcelable id=").append(a1.eu()).toString());

            case 2: // '\002'
                return Integer.valueOf(Te);

            case 3: // '\003'
                return Integer.valueOf(Tf);
            }
        }

        public int describeContents()
        {
            it _tmp = CREATOR;
            return 0;
        }

        public HashMap en()
        {
            return RL;
        }

        public boolean equals(Object obj)
        {
label0:
            {
                if(!(obj instanceof a))
                    return false;
                if(this == obj)
                    return true;
                a a1 = (a)obj;
                fb.a a2;
label1:
                do
                {
                    for(Iterator iterator = RL.values().iterator(); iterator.hasNext();)
                    {
                        a2 = (fb.a)iterator.next();
                        if(!a(a2))
                            continue label1;
                        if(a1.a(a2))
                        {
                            if(!b(a2).equals(a1.b(a2)))
                                return false;
                        } else
                        {
                            return false;
                        }
                    }

                    break label0;
                } while(!a1.a(a2));
                return false;
            }
            return true;
        }

        public Object freeze()
        {
            return ie();
        }

        public int getMax()
        {
            return Te;
        }

        public int getMin()
        {
            return Tf;
        }

        int getVersionCode()
        {
            return wj;
        }

        Set hB()
        {
            return RM;
        }

        public boolean hasMax()
        {
            return RM.contains(Integer.valueOf(2));
        }

        public boolean hasMin()
        {
            return RM.contains(Integer.valueOf(3));
        }

        public int hashCode()
        {
            Iterator iterator = RL.values().iterator();
            int j = 0;
            while(iterator.hasNext()) 
            {
                fb.a a1 = (fb.a)iterator.next();
                int k;
                if(a(a1))
                    k = j + a1.eu() + b(a1).hashCode();
                else
                    k = j;
                j = k;
            }
            return j;
        }

        public a ie()
        {
            return this;
        }

        public boolean isDataValid()
        {
            return true;
        }

        public void writeToParcel(Parcel parcel, int j)
        {
            it _tmp = CREATOR;
            it.a(this, parcel, j);
        }

        public static final it CREATOR = new it();
        private static final HashMap RL;
        private final Set RM;
        private int Te;
        private int Tf;
        private final int wj;

        static 
        {
            RL = new HashMap();
            RL.put("max", fb.a.g("max", 2));
            RL.put("min", fb.a.g("min", 3));
        }

        public a()
        {
            wj = 1;
            RM = new HashSet();
        }

        a(Set set, int j, int k, int l)
        {
            RM = set;
            wj = j;
            Te = k;
            Tf = l;
        }
    }

    public static final class b extends fb
        implements SafeParcelable, com.google.android.gms.plus.model.people.Person.Cover
    {

        protected boolean a(fb.a a1)
        {
            return RM.contains(Integer.valueOf(a1.eu()));
        }

        protected Object ak(String s)
        {
            return null;
        }

        protected boolean al(String s)
        {
            return false;
        }

        protected Object b(fb.a a1)
        {
            switch(a1.eu())
            {
            default:
                throw new IllegalStateException((new StringBuilder()).append("Unknown safe parcelable id=").append(a1.eu()).toString());

            case 2: // '\002'
                return Tg;

            case 3: // '\003'
                return Th;

            case 4: // '\004'
                return Integer.valueOf(Ti);
            }
        }

        public int describeContents()
        {
            iu _tmp = CREATOR;
            return 0;
        }

        public HashMap en()
        {
            return RL;
        }

        public boolean equals(Object obj)
        {
label0:
            {
                if(!(obj instanceof b))
                    return false;
                if(this == obj)
                    return true;
                b b1 = (b)obj;
                fb.a a1;
label1:
                do
                {
                    for(Iterator iterator = RL.values().iterator(); iterator.hasNext();)
                    {
                        a1 = (fb.a)iterator.next();
                        if(!a(a1))
                            continue label1;
                        if(b1.a(a1))
                        {
                            if(!b(a1).equals(b1.b(a1)))
                                return false;
                        } else
                        {
                            return false;
                        }
                    }

                    break label0;
                } while(!b1.a(a1));
                return false;
            }
            return true;
        }

        public Object freeze()
        {
            return ih();
        }

        public com.google.android.gms.plus.model.people.Person.Cover.CoverInfo getCoverInfo()
        {
            return Tg;
        }

        public com.google.android.gms.plus.model.people.Person.Cover.CoverPhoto getCoverPhoto()
        {
            return Th;
        }

        public int getLayout()
        {
            return Ti;
        }

        int getVersionCode()
        {
            return wj;
        }

        Set hB()
        {
            return RM;
        }

        public boolean hasCoverInfo()
        {
            return RM.contains(Integer.valueOf(2));
        }

        public boolean hasCoverPhoto()
        {
            return RM.contains(Integer.valueOf(3));
        }

        public boolean hasLayout()
        {
            return RM.contains(Integer.valueOf(4));
        }

        public int hashCode()
        {
            Iterator iterator = RL.values().iterator();
            int j = 0;
            while(iterator.hasNext()) 
            {
                fb.a a1 = (fb.a)iterator.next();
                int k;
                if(a(a1))
                    k = j + a1.eu() + b(a1).hashCode();
                else
                    k = j;
                j = k;
            }
            return j;
        }

        a _mthif()
        {
            return Tg;
        }

        b ig()
        {
            return Th;
        }

        public b ih()
        {
            return this;
        }

        public boolean isDataValid()
        {
            return true;
        }

        public void writeToParcel(Parcel parcel, int j)
        {
            iu _tmp = CREATOR;
            iu.a(this, parcel, j);
        }

        public static final iu CREATOR = new iu();
        private static final HashMap RL;
        private final Set RM;
        private a Tg;
        private b Th;
        private int Ti;
        private final int wj;

        static 
        {
            RL = new HashMap();
            RL.put("coverInfo", fb.a.a("coverInfo", 2, com/google/android/gms/internal/ir$b$a));
            RL.put("coverPhoto", fb.a.a("coverPhoto", 3, com/google/android/gms/internal/ir$b$b));
            RL.put("layout", fb.a.a("layout", 4, (new ey()).f("banner", 0), false));
        }

        public b()
        {
            wj = 1;
            RM = new HashSet();
        }

        b(Set set, int j, a a1, b b1, int k)
        {
            RM = set;
            wj = j;
            Tg = a1;
            Th = b1;
            Ti = k;
        }
    }

    public static final class b.a extends fb
        implements SafeParcelable, com.google.android.gms.plus.model.people.Person.Cover.CoverInfo
    {

        protected boolean a(fb.a a1)
        {
            return RM.contains(Integer.valueOf(a1.eu()));
        }

        protected Object ak(String s)
        {
            return null;
        }

        protected boolean al(String s)
        {
            return false;
        }

        protected Object b(fb.a a1)
        {
            switch(a1.eu())
            {
            default:
                throw new IllegalStateException((new StringBuilder()).append("Unknown safe parcelable id=").append(a1.eu()).toString());

            case 2: // '\002'
                return Integer.valueOf(Tj);

            case 3: // '\003'
                return Integer.valueOf(Tk);
            }
        }

        public int describeContents()
        {
            iv _tmp = CREATOR;
            return 0;
        }

        public HashMap en()
        {
            return RL;
        }

        public boolean equals(Object obj)
        {
label0:
            {
                if(!(obj instanceof b.a))
                    return false;
                if(this == obj)
                    return true;
                b.a a1 = (b.a)obj;
                fb.a a2;
label1:
                do
                {
                    for(Iterator iterator = RL.values().iterator(); iterator.hasNext();)
                    {
                        a2 = (fb.a)iterator.next();
                        if(!a(a2))
                            continue label1;
                        if(a1.a(a2))
                        {
                            if(!b(a2).equals(a1.b(a2)))
                                return false;
                        } else
                        {
                            return false;
                        }
                    }

                    break label0;
                } while(!a1.a(a2));
                return false;
            }
            return true;
        }

        public Object freeze()
        {
            return ii();
        }

        public int getLeftImageOffset()
        {
            return Tj;
        }

        public int getTopImageOffset()
        {
            return Tk;
        }

        int getVersionCode()
        {
            return wj;
        }

        Set hB()
        {
            return RM;
        }

        public boolean hasLeftImageOffset()
        {
            return RM.contains(Integer.valueOf(2));
        }

        public boolean hasTopImageOffset()
        {
            return RM.contains(Integer.valueOf(3));
        }

        public int hashCode()
        {
            Iterator iterator = RL.values().iterator();
            int j = 0;
            while(iterator.hasNext()) 
            {
                fb.a a1 = (fb.a)iterator.next();
                int k;
                if(a(a1))
                    k = j + a1.eu() + b(a1).hashCode();
                else
                    k = j;
                j = k;
            }
            return j;
        }

        public b.a ii()
        {
            return this;
        }

        public boolean isDataValid()
        {
            return true;
        }

        public void writeToParcel(Parcel parcel, int j)
        {
            iv _tmp = CREATOR;
            iv.a(this, parcel, j);
        }

        public static final iv CREATOR = new iv();
        private static final HashMap RL;
        private final Set RM;
        private int Tj;
        private int Tk;
        private final int wj;

        static 
        {
            RL = new HashMap();
            RL.put("leftImageOffset", fb.a.g("leftImageOffset", 2));
            RL.put("topImageOffset", fb.a.g("topImageOffset", 3));
        }

        public b.a()
        {
            wj = 1;
            RM = new HashSet();
        }

        b.a(Set set, int j, int k, int l)
        {
            RM = set;
            wj = j;
            Tj = k;
            Tk = l;
        }
    }

    public static final class b.b extends fb
        implements SafeParcelable, com.google.android.gms.plus.model.people.Person.Cover.CoverPhoto
    {

        protected boolean a(fb.a a1)
        {
            return RM.contains(Integer.valueOf(a1.eu()));
        }

        protected Object ak(String s)
        {
            return null;
        }

        protected boolean al(String s)
        {
            return false;
        }

        protected Object b(fb.a a1)
        {
            switch(a1.eu())
            {
            default:
                throw new IllegalStateException((new StringBuilder()).append("Unknown safe parcelable id=").append(a1.eu()).toString());

            case 2: // '\002'
                return Integer.valueOf(v);

            case 3: // '\003'
                return pS;

            case 4: // '\004'
                return Integer.valueOf(w);
            }
        }

        public int describeContents()
        {
            iw _tmp = CREATOR;
            return 0;
        }

        public HashMap en()
        {
            return RL;
        }

        public boolean equals(Object obj)
        {
label0:
            {
                if(!(obj instanceof b.b))
                    return false;
                if(this == obj)
                    return true;
                b.b b1 = (b.b)obj;
                fb.a a1;
label1:
                do
                {
                    for(Iterator iterator = RL.values().iterator(); iterator.hasNext();)
                    {
                        a1 = (fb.a)iterator.next();
                        if(!a(a1))
                            continue label1;
                        if(b1.a(a1))
                        {
                            if(!b(a1).equals(b1.b(a1)))
                                return false;
                        } else
                        {
                            return false;
                        }
                    }

                    break label0;
                } while(!b1.a(a1));
                return false;
            }
            return true;
        }

        public Object freeze()
        {
            return ij();
        }

        public int getHeight()
        {
            return v;
        }

        public String getUrl()
        {
            return pS;
        }

        int getVersionCode()
        {
            return wj;
        }

        public int getWidth()
        {
            return w;
        }

        Set hB()
        {
            return RM;
        }

        public boolean hasHeight()
        {
            return RM.contains(Integer.valueOf(2));
        }

        public boolean hasUrl()
        {
            return RM.contains(Integer.valueOf(3));
        }

        public boolean hasWidth()
        {
            return RM.contains(Integer.valueOf(4));
        }

        public int hashCode()
        {
            Iterator iterator = RL.values().iterator();
            int j = 0;
            while(iterator.hasNext()) 
            {
                fb.a a1 = (fb.a)iterator.next();
                int k;
                if(a(a1))
                    k = j + a1.eu() + b(a1).hashCode();
                else
                    k = j;
                j = k;
            }
            return j;
        }

        public b.b ij()
        {
            return this;
        }

        public boolean isDataValid()
        {
            return true;
        }

        public void writeToParcel(Parcel parcel, int j)
        {
            iw _tmp = CREATOR;
            iw.a(this, parcel, j);
        }

        public static final iw CREATOR = new iw();
        private static final HashMap RL;
        private final Set RM;
        private String pS;
        private int v;
        private int w;
        private final int wj;

        static 
        {
            RL = new HashMap();
            RL.put("height", fb.a.g("height", 2));
            RL.put("url", fb.a.j("url", 3));
            RL.put("width", fb.a.g("width", 4));
        }

        public b.b()
        {
            wj = 1;
            RM = new HashSet();
        }

        b.b(Set set, int j, int k, String s, int l)
        {
            RM = set;
            wj = j;
            v = k;
            pS = s;
            w = l;
        }
    }

    public static final class c extends fb
        implements SafeParcelable, com.google.android.gms.plus.model.people.Person.Image
    {

        protected boolean a(fb.a a1)
        {
            return RM.contains(Integer.valueOf(a1.eu()));
        }

        protected Object ak(String s)
        {
            return null;
        }

        protected boolean al(String s)
        {
            return false;
        }

        protected Object b(fb.a a1)
        {
            switch(a1.eu())
            {
            default:
                throw new IllegalStateException((new StringBuilder()).append("Unknown safe parcelable id=").append(a1.eu()).toString());

            case 2: // '\002'
                return pS;
            }
        }

        public int describeContents()
        {
            ix _tmp = CREATOR;
            return 0;
        }

        public HashMap en()
        {
            return RL;
        }

        public boolean equals(Object obj)
        {
label0:
            {
                if(!(obj instanceof c))
                    return false;
                if(this == obj)
                    return true;
                c c1 = (c)obj;
                fb.a a1;
label1:
                do
                {
                    for(Iterator iterator = RL.values().iterator(); iterator.hasNext();)
                    {
                        a1 = (fb.a)iterator.next();
                        if(!a(a1))
                            continue label1;
                        if(c1.a(a1))
                        {
                            if(!b(a1).equals(c1.b(a1)))
                                return false;
                        } else
                        {
                            return false;
                        }
                    }

                    break label0;
                } while(!c1.a(a1));
                return false;
            }
            return true;
        }

        public Object freeze()
        {
            return ik();
        }

        public String getUrl()
        {
            return pS;
        }

        int getVersionCode()
        {
            return wj;
        }

        Set hB()
        {
            return RM;
        }

        public boolean hasUrl()
        {
            return RM.contains(Integer.valueOf(2));
        }

        public int hashCode()
        {
            Iterator iterator = RL.values().iterator();
            int j = 0;
            while(iterator.hasNext()) 
            {
                fb.a a1 = (fb.a)iterator.next();
                int k;
                if(a(a1))
                    k = j + a1.eu() + b(a1).hashCode();
                else
                    k = j;
                j = k;
            }
            return j;
        }

        public c ik()
        {
            return this;
        }

        public boolean isDataValid()
        {
            return true;
        }

        public void writeToParcel(Parcel parcel, int j)
        {
            ix _tmp = CREATOR;
            ix.a(this, parcel, j);
        }

        public static final ix CREATOR = new ix();
        private static final HashMap RL;
        private final Set RM;
        private String pS;
        private final int wj;

        static 
        {
            RL = new HashMap();
            RL.put("url", fb.a.j("url", 2));
        }

        public c()
        {
            wj = 1;
            RM = new HashSet();
        }

        public c(String s)
        {
            RM = new HashSet();
            wj = 1;
            pS = s;
            RM.add(Integer.valueOf(2));
        }

        c(Set set, int j, String s)
        {
            RM = set;
            wj = j;
            pS = s;
        }
    }

    public static final class d extends fb
        implements SafeParcelable, com.google.android.gms.plus.model.people.Person.Name
    {

        protected boolean a(fb.a a1)
        {
            return RM.contains(Integer.valueOf(a1.eu()));
        }

        protected Object ak(String s)
        {
            return null;
        }

        protected boolean al(String s)
        {
            return false;
        }

        protected Object b(fb.a a1)
        {
            switch(a1.eu())
            {
            default:
                throw new IllegalStateException((new StringBuilder()).append("Unknown safe parcelable id=").append(a1.eu()).toString());

            case 2: // '\002'
                return Sk;

            case 3: // '\003'
                return Tl;

            case 4: // '\004'
                return Sn;

            case 5: // '\005'
                return Tm;

            case 6: // '\006'
                return Tn;

            case 7: // '\007'
                return To;
            }
        }

        public int describeContents()
        {
            iy _tmp = CREATOR;
            return 0;
        }

        public HashMap en()
        {
            return RL;
        }

        public boolean equals(Object obj)
        {
label0:
            {
                if(!(obj instanceof d))
                    return false;
                if(this == obj)
                    return true;
                d d1 = (d)obj;
                fb.a a1;
label1:
                do
                {
                    for(Iterator iterator = RL.values().iterator(); iterator.hasNext();)
                    {
                        a1 = (fb.a)iterator.next();
                        if(!a(a1))
                            continue label1;
                        if(d1.a(a1))
                        {
                            if(!b(a1).equals(d1.b(a1)))
                                return false;
                        } else
                        {
                            return false;
                        }
                    }

                    break label0;
                } while(!d1.a(a1));
                return false;
            }
            return true;
        }

        public Object freeze()
        {
            return il();
        }

        public String getFamilyName()
        {
            return Sk;
        }

        public String getFormatted()
        {
            return Tl;
        }

        public String getGivenName()
        {
            return Sn;
        }

        public String getHonorificPrefix()
        {
            return Tm;
        }

        public String getHonorificSuffix()
        {
            return Tn;
        }

        public String getMiddleName()
        {
            return To;
        }

        int getVersionCode()
        {
            return wj;
        }

        Set hB()
        {
            return RM;
        }

        public boolean hasFamilyName()
        {
            return RM.contains(Integer.valueOf(2));
        }

        public boolean hasFormatted()
        {
            return RM.contains(Integer.valueOf(3));
        }

        public boolean hasGivenName()
        {
            return RM.contains(Integer.valueOf(4));
        }

        public boolean hasHonorificPrefix()
        {
            return RM.contains(Integer.valueOf(5));
        }

        public boolean hasHonorificSuffix()
        {
            return RM.contains(Integer.valueOf(6));
        }

        public boolean hasMiddleName()
        {
            return RM.contains(Integer.valueOf(7));
        }

        public int hashCode()
        {
            Iterator iterator = RL.values().iterator();
            int j = 0;
            while(iterator.hasNext()) 
            {
                fb.a a1 = (fb.a)iterator.next();
                int k;
                if(a(a1))
                    k = j + a1.eu() + b(a1).hashCode();
                else
                    k = j;
                j = k;
            }
            return j;
        }

        public d il()
        {
            return this;
        }

        public boolean isDataValid()
        {
            return true;
        }

        public void writeToParcel(Parcel parcel, int j)
        {
            iy _tmp = CREATOR;
            iy.a(this, parcel, j);
        }

        public static final iy CREATOR = new iy();
        private static final HashMap RL;
        private final Set RM;
        private String Sk;
        private String Sn;
        private String Tl;
        private String Tm;
        private String Tn;
        private String To;
        private final int wj;

        static 
        {
            RL = new HashMap();
            RL.put("familyName", fb.a.j("familyName", 2));
            RL.put("formatted", fb.a.j("formatted", 3));
            RL.put("givenName", fb.a.j("givenName", 4));
            RL.put("honorificPrefix", fb.a.j("honorificPrefix", 5));
            RL.put("honorificSuffix", fb.a.j("honorificSuffix", 6));
            RL.put("middleName", fb.a.j("middleName", 7));
        }

        public d()
        {
            wj = 1;
            RM = new HashSet();
        }

        d(Set set, int j, String s, String s1, String s2, String s3, String s4, 
                String s5)
        {
            RM = set;
            wj = j;
            Sk = s;
            Tl = s1;
            Sn = s2;
            Tm = s3;
            Tn = s4;
            To = s5;
        }
    }

    public static class e
    {

        public static int aT(String s)
        {
            if(s.equals("person"))
                return 0;
            if(s.equals("page"))
                return 1;
            else
                throw new IllegalArgumentException((new StringBuilder()).append("Unknown objectType string: ").append(s).toString());
        }
    }

    public static final class f extends fb
        implements SafeParcelable, com.google.android.gms.plus.model.people.Person.Organizations
    {

        protected boolean a(fb.a a1)
        {
            return RM.contains(Integer.valueOf(a1.eu()));
        }

        protected Object ak(String s)
        {
            return null;
        }

        protected boolean al(String s)
        {
            return false;
        }

        protected Object b(fb.a a1)
        {
            switch(a1.eu())
            {
            default:
                throw new IllegalStateException((new StringBuilder()).append("Unknown safe parcelable id=").append(a1.eu()).toString());

            case 2: // '\002'
                return Tp;

            case 3: // '\003'
                return FH;

            case 4: // '\004'
                return Sj;

            case 5: // '\005'
                return Tq;

            case 6: // '\006'
                return mName;

            case 7: // '\007'
                return Boolean.valueOf(Tr);

            case 8: // '\b'
                return Sz;

            case 9: // '\t'
                return CX;

            case 10: // '\n'
                return Integer.valueOf(AI);
            }
        }

        public int describeContents()
        {
            iz _tmp = CREATOR;
            return 0;
        }

        public HashMap en()
        {
            return RL;
        }

        public boolean equals(Object obj)
        {
label0:
            {
                if(!(obj instanceof f))
                    return false;
                if(this == obj)
                    return true;
                f f1 = (f)obj;
                fb.a a1;
label1:
                do
                {
                    for(Iterator iterator = RL.values().iterator(); iterator.hasNext();)
                    {
                        a1 = (fb.a)iterator.next();
                        if(!a(a1))
                            continue label1;
                        if(f1.a(a1))
                        {
                            if(!b(a1).equals(f1.b(a1)))
                                return false;
                        } else
                        {
                            return false;
                        }
                    }

                    break label0;
                } while(!f1.a(a1));
                return false;
            }
            return true;
        }

        public Object freeze()
        {
            return im();
        }

        public String getDepartment()
        {
            return Tp;
        }

        public String getDescription()
        {
            return FH;
        }

        public String getEndDate()
        {
            return Sj;
        }

        public String getLocation()
        {
            return Tq;
        }

        public String getName()
        {
            return mName;
        }

        public String getStartDate()
        {
            return Sz;
        }

        public String getTitle()
        {
            return CX;
        }

        public int getType()
        {
            return AI;
        }

        int getVersionCode()
        {
            return wj;
        }

        Set hB()
        {
            return RM;
        }

        public boolean hasDepartment()
        {
            return RM.contains(Integer.valueOf(2));
        }

        public boolean hasDescription()
        {
            return RM.contains(Integer.valueOf(3));
        }

        public boolean hasEndDate()
        {
            return RM.contains(Integer.valueOf(4));
        }

        public boolean hasLocation()
        {
            return RM.contains(Integer.valueOf(5));
        }

        public boolean hasName()
        {
            return RM.contains(Integer.valueOf(6));
        }

        public boolean hasPrimary()
        {
            return RM.contains(Integer.valueOf(7));
        }

        public boolean hasStartDate()
        {
            return RM.contains(Integer.valueOf(8));
        }

        public boolean hasTitle()
        {
            return RM.contains(Integer.valueOf(9));
        }

        public boolean hasType()
        {
            return RM.contains(Integer.valueOf(10));
        }

        public int hashCode()
        {
            Iterator iterator = RL.values().iterator();
            int j = 0;
            while(iterator.hasNext()) 
            {
                fb.a a1 = (fb.a)iterator.next();
                int k;
                if(a(a1))
                    k = j + a1.eu() + b(a1).hashCode();
                else
                    k = j;
                j = k;
            }
            return j;
        }

        public f im()
        {
            return this;
        }

        public boolean isDataValid()
        {
            return true;
        }

        public boolean isPrimary()
        {
            return Tr;
        }

        public void writeToParcel(Parcel parcel, int j)
        {
            iz _tmp = CREATOR;
            iz.a(this, parcel, j);
        }

        public static final iz CREATOR = new iz();
        private static final HashMap RL;
        private int AI;
        private String CX;
        private String FH;
        private final Set RM;
        private String Sj;
        private String Sz;
        private String Tp;
        private String Tq;
        private boolean Tr;
        private String mName;
        private final int wj;

        static 
        {
            RL = new HashMap();
            RL.put("department", fb.a.j("department", 2));
            RL.put("description", fb.a.j("description", 3));
            RL.put("endDate", fb.a.j("endDate", 4));
            RL.put("location", fb.a.j("location", 5));
            RL.put("name", fb.a.j("name", 6));
            RL.put("primary", fb.a.i("primary", 7));
            RL.put("startDate", fb.a.j("startDate", 8));
            RL.put("title", fb.a.j("title", 9));
            RL.put("type", fb.a.a("type", 10, (new ey()).f("work", 0).f("school", 1), false));
        }

        public f()
        {
            wj = 1;
            RM = new HashSet();
        }

        f(Set set, int j, String s, String s1, String s2, String s3, String s4, 
                boolean flag, String s5, String s6, int k)
        {
            RM = set;
            wj = j;
            Tp = s;
            FH = s1;
            Sj = s2;
            Tq = s3;
            mName = s4;
            Tr = flag;
            Sz = s5;
            CX = s6;
            AI = k;
        }
    }

    public static final class g extends fb
        implements SafeParcelable, com.google.android.gms.plus.model.people.Person.PlacesLived
    {

        protected boolean a(fb.a a1)
        {
            return RM.contains(Integer.valueOf(a1.eu()));
        }

        protected Object ak(String s)
        {
            return null;
        }

        protected boolean al(String s)
        {
            return false;
        }

        protected Object b(fb.a a1)
        {
            switch(a1.eu())
            {
            default:
                throw new IllegalStateException((new StringBuilder()).append("Unknown safe parcelable id=").append(a1.eu()).toString());

            case 2: // '\002'
                return Boolean.valueOf(Tr);

            case 3: // '\003'
                return mValue;
            }
        }

        public int describeContents()
        {
            ja _tmp = CREATOR;
            return 0;
        }

        public HashMap en()
        {
            return RL;
        }

        public boolean equals(Object obj)
        {
label0:
            {
                if(!(obj instanceof g))
                    return false;
                if(this == obj)
                    return true;
                g g1 = (g)obj;
                fb.a a1;
label1:
                do
                {
                    for(Iterator iterator = RL.values().iterator(); iterator.hasNext();)
                    {
                        a1 = (fb.a)iterator.next();
                        if(!a(a1))
                            continue label1;
                        if(g1.a(a1))
                        {
                            if(!b(a1).equals(g1.b(a1)))
                                return false;
                        } else
                        {
                            return false;
                        }
                    }

                    break label0;
                } while(!g1.a(a1));
                return false;
            }
            return true;
        }

        public Object freeze()
        {
            return in();
        }

        public String getValue()
        {
            return mValue;
        }

        int getVersionCode()
        {
            return wj;
        }

        Set hB()
        {
            return RM;
        }

        public boolean hasPrimary()
        {
            return RM.contains(Integer.valueOf(2));
        }

        public boolean hasValue()
        {
            return RM.contains(Integer.valueOf(3));
        }

        public int hashCode()
        {
            Iterator iterator = RL.values().iterator();
            int j = 0;
            while(iterator.hasNext()) 
            {
                fb.a a1 = (fb.a)iterator.next();
                int k;
                if(a(a1))
                    k = j + a1.eu() + b(a1).hashCode();
                else
                    k = j;
                j = k;
            }
            return j;
        }

        public g in()
        {
            return this;
        }

        public boolean isDataValid()
        {
            return true;
        }

        public boolean isPrimary()
        {
            return Tr;
        }

        public void writeToParcel(Parcel parcel, int j)
        {
            ja _tmp = CREATOR;
            ja.a(this, parcel, j);
        }

        public static final ja CREATOR = new ja();
        private static final HashMap RL;
        private final Set RM;
        private boolean Tr;
        private String mValue;
        private final int wj;

        static 
        {
            RL = new HashMap();
            RL.put("primary", fb.a.i("primary", 2));
            RL.put("value", fb.a.j("value", 3));
        }

        public g()
        {
            wj = 1;
            RM = new HashSet();
        }

        g(Set set, int j, boolean flag, String s)
        {
            RM = set;
            wj = j;
            Tr = flag;
            mValue = s;
        }
    }

    public static final class h extends fb
        implements SafeParcelable, com.google.android.gms.plus.model.people.Person.Urls
    {

        protected boolean a(fb.a a1)
        {
            return RM.contains(Integer.valueOf(a1.eu()));
        }

        protected Object ak(String s)
        {
            return null;
        }

        protected boolean al(String s)
        {
            return false;
        }

        protected Object b(fb.a a1)
        {
            switch(a1.eu())
            {
            default:
                throw new IllegalStateException((new StringBuilder()).append("Unknown safe parcelable id=").append(a1.eu()).toString());

            case 5: // '\005'
                return Ts;

            case 6: // '\006'
                return Integer.valueOf(AI);

            case 4: // '\004'
                return mValue;
            }
        }

        public int describeContents()
        {
            jb _tmp = CREATOR;
            return 0;
        }

        public HashMap en()
        {
            return RL;
        }

        public boolean equals(Object obj)
        {
label0:
            {
                if(!(obj instanceof h))
                    return false;
                if(this == obj)
                    return true;
                h h1 = (h)obj;
                fb.a a1;
label1:
                do
                {
                    for(Iterator iterator = RL.values().iterator(); iterator.hasNext();)
                    {
                        a1 = (fb.a)iterator.next();
                        if(!a(a1))
                            continue label1;
                        if(h1.a(a1))
                        {
                            if(!b(a1).equals(h1.b(a1)))
                                return false;
                        } else
                        {
                            return false;
                        }
                    }

                    break label0;
                } while(!h1.a(a1));
                return false;
            }
            return true;
        }

        public Object freeze()
        {
            return ip();
        }

        public String getLabel()
        {
            return Ts;
        }

        public int getType()
        {
            return AI;
        }

        public String getValue()
        {
            return mValue;
        }

        int getVersionCode()
        {
            return wj;
        }

        Set hB()
        {
            return RM;
        }

        public boolean hasLabel()
        {
            return RM.contains(Integer.valueOf(5));
        }

        public boolean hasType()
        {
            return RM.contains(Integer.valueOf(6));
        }

        public boolean hasValue()
        {
            return RM.contains(Integer.valueOf(4));
        }

        public int hashCode()
        {
            Iterator iterator = RL.values().iterator();
            int j = 0;
            while(iterator.hasNext()) 
            {
                fb.a a1 = (fb.a)iterator.next();
                int k;
                if(a(a1))
                    k = j + a1.eu() + b(a1).hashCode();
                else
                    k = j;
                j = k;
            }
            return j;
        }

        public int io()
        {
            return 4;
        }

        public h ip()
        {
            return this;
        }

        public boolean isDataValid()
        {
            return true;
        }

        public void writeToParcel(Parcel parcel, int j)
        {
            jb _tmp = CREATOR;
            jb.a(this, parcel, j);
        }

        public static final jb CREATOR = new jb();
        private static final HashMap RL;
        private int AI;
        private final Set RM;
        private String Ts;
        private final int Tt;
        private String mValue;
        private final int wj;

        static 
        {
            RL = new HashMap();
            RL.put("label", fb.a.j("label", 5));
            RL.put("type", fb.a.a("type", 6, (new ey()).f("home", 0).f("work", 1).f("blog", 2).f("profile", 3).f("other", 4).f("otherProfile", 5).f("contributor", 6).f("website", 7), false));
            RL.put("value", fb.a.j("value", 4));
        }

        public h()
        {
            Tt = 4;
            wj = 2;
            RM = new HashSet();
        }

        h(Set set, int j, String s, int k, String s1, int l)
        {
            Tt = 4;
            RM = set;
            wj = j;
            Ts = s;
            AI = k;
            mValue = s1;
        }
    }


    public ir()
    {
        wj = 2;
        RM = new HashSet();
    }

    public ir(String s, String s1, c c1, int j, String s2)
    {
        wj = 2;
        RM = new HashSet();
        FE = s;
        RM.add(Integer.valueOf(9));
        uS = s1;
        RM.add(Integer.valueOf(14));
        SR = c1;
        RM.add(Integer.valueOf(15));
        SW = j;
        RM.add(Integer.valueOf(21));
        pS = s2;
        RM.add(Integer.valueOf(27));
    }

    ir(Set set, int j, String s, a a1, String s1, String s2, int k, 
            b b1, String s3, String s4, int l, String s5, c c1, boolean flag, 
            String s6, d d1, String s7, int i1, List list, List list1, int j1, 
            int k1, String s8, String s9, List list2, boolean flag1)
    {
        RM = set;
        wj = j;
        SK = s;
        SL = a1;
        SM = s1;
        SN = s2;
        SO = k;
        SP = b1;
        SQ = s3;
        FE = s4;
        lu = l;
        uS = s5;
        SR = c1;
        SS = flag;
        ST = s6;
        SU = d1;
        SV = s7;
        SW = i1;
        SX = list;
        SY = list1;
        SZ = j1;
        Ta = k1;
        Tb = s8;
        pS = s9;
        Tc = list2;
        Td = flag1;
    }

    public static ir i(byte abyte0[])
    {
        Parcel parcel = Parcel.obtain();
        parcel.unmarshall(abyte0, 0, abyte0.length);
        parcel.setDataPosition(0);
        ir ir1 = CREATOR.aI(parcel);
        parcel.recycle();
        return ir1;
    }

    protected boolean a(fb.a a1)
    {
        return RM.contains(Integer.valueOf(a1.eu()));
    }

    protected Object ak(String s)
    {
        return null;
    }

    protected boolean al(String s)
    {
        return false;
    }

    protected Object b(fb.a a1)
    {
        switch(a1.eu())
        {
        case 10: // '\n'
        case 11: // '\013'
        case 13: // '\r'
        case 17: // '\021'
        default:
            throw new IllegalStateException((new StringBuilder()).append("Unknown safe parcelable id=").append(a1.eu()).toString());

        case 2: // '\002'
            return SK;

        case 3: // '\003'
            return SL;

        case 4: // '\004'
            return SM;

        case 5: // '\005'
            return SN;

        case 6: // '\006'
            return Integer.valueOf(SO);

        case 7: // '\007'
            return SP;

        case 8: // '\b'
            return SQ;

        case 9: // '\t'
            return FE;

        case 12: // '\f'
            return Integer.valueOf(lu);

        case 14: // '\016'
            return uS;

        case 15: // '\017'
            return SR;

        case 16: // '\020'
            return Boolean.valueOf(SS);

        case 18: // '\022'
            return ST;

        case 19: // '\023'
            return SU;

        case 20: // '\024'
            return SV;

        case 21: // '\025'
            return Integer.valueOf(SW);

        case 22: // '\026'
            return SX;

        case 23: // '\027'
            return SY;

        case 24: // '\030'
            return Integer.valueOf(SZ);

        case 25: // '\031'
            return Integer.valueOf(Ta);

        case 26: // '\032'
            return Tb;

        case 27: // '\033'
            return pS;

        case 28: // '\034'
            return Tc;

        case 29: // '\035'
            return Boolean.valueOf(Td);
        }
    }

    public int describeContents()
    {
        is _tmp = CREATOR;
        return 0;
    }

    public HashMap en()
    {
        return RL;
    }

    public boolean equals(Object obj)
    {
label0:
        {
            if(!(obj instanceof ir))
                return false;
            if(this == obj)
                return true;
            ir ir1 = (ir)obj;
            fb.a a1;
label1:
            do
            {
                for(Iterator iterator = RL.values().iterator(); iterator.hasNext();)
                {
                    a1 = (fb.a)iterator.next();
                    if(!a(a1))
                        continue label1;
                    if(ir1.a(a1))
                    {
                        if(!b(a1).equals(ir1.b(a1)))
                            return false;
                    } else
                    {
                        return false;
                    }
                }

                break label0;
            } while(!ir1.a(a1));
            return false;
        }
        return true;
    }

    public Object freeze()
    {
        return id();
    }

    public String getAboutMe()
    {
        return SK;
    }

    public com.google.android.gms.plus.model.people.Person.AgeRange getAgeRange()
    {
        return SL;
    }

    public String getBirthday()
    {
        return SM;
    }

    public String getBraggingRights()
    {
        return SN;
    }

    public int getCircledByCount()
    {
        return SO;
    }

    public com.google.android.gms.plus.model.people.Person.Cover getCover()
    {
        return SP;
    }

    public String getCurrentLocation()
    {
        return SQ;
    }

    public String getDisplayName()
    {
        return FE;
    }

    public int getGender()
    {
        return lu;
    }

    public String getId()
    {
        return uS;
    }

    public com.google.android.gms.plus.model.people.Person.Image getImage()
    {
        return SR;
    }

    public String getLanguage()
    {
        return ST;
    }

    public com.google.android.gms.plus.model.people.Person.Name getName()
    {
        return SU;
    }

    public String getNickname()
    {
        return SV;
    }

    public int getObjectType()
    {
        return SW;
    }

    public List getOrganizations()
    {
        return (ArrayList)SX;
    }

    public List getPlacesLived()
    {
        return (ArrayList)SY;
    }

    public int getPlusOneCount()
    {
        return SZ;
    }

    public int getRelationshipStatus()
    {
        return Ta;
    }

    public String getTagline()
    {
        return Tb;
    }

    public String getUrl()
    {
        return pS;
    }

    public List getUrls()
    {
        return (ArrayList)Tc;
    }

    int getVersionCode()
    {
        return wj;
    }

    Set hB()
    {
        return RM;
    }

    a hW()
    {
        return SL;
    }

    b hX()
    {
        return SP;
    }

    c hY()
    {
        return SR;
    }

    d hZ()
    {
        return SU;
    }

    public boolean hasAboutMe()
    {
        return RM.contains(Integer.valueOf(2));
    }

    public boolean hasAgeRange()
    {
        return RM.contains(Integer.valueOf(3));
    }

    public boolean hasBirthday()
    {
        return RM.contains(Integer.valueOf(4));
    }

    public boolean hasBraggingRights()
    {
        return RM.contains(Integer.valueOf(5));
    }

    public boolean hasCircledByCount()
    {
        return RM.contains(Integer.valueOf(6));
    }

    public boolean hasCover()
    {
        return RM.contains(Integer.valueOf(7));
    }

    public boolean hasCurrentLocation()
    {
        return RM.contains(Integer.valueOf(8));
    }

    public boolean hasDisplayName()
    {
        return RM.contains(Integer.valueOf(9));
    }

    public boolean hasGender()
    {
        return RM.contains(Integer.valueOf(12));
    }

    public boolean hasId()
    {
        return RM.contains(Integer.valueOf(14));
    }

    public boolean hasImage()
    {
        return RM.contains(Integer.valueOf(15));
    }

    public boolean hasIsPlusUser()
    {
        return RM.contains(Integer.valueOf(16));
    }

    public boolean hasLanguage()
    {
        return RM.contains(Integer.valueOf(18));
    }

    public boolean hasName()
    {
        return RM.contains(Integer.valueOf(19));
    }

    public boolean hasNickname()
    {
        return RM.contains(Integer.valueOf(20));
    }

    public boolean hasObjectType()
    {
        return RM.contains(Integer.valueOf(21));
    }

    public boolean hasOrganizations()
    {
        return RM.contains(Integer.valueOf(22));
    }

    public boolean hasPlacesLived()
    {
        return RM.contains(Integer.valueOf(23));
    }

    public boolean hasPlusOneCount()
    {
        return RM.contains(Integer.valueOf(24));
    }

    public boolean hasRelationshipStatus()
    {
        return RM.contains(Integer.valueOf(25));
    }

    public boolean hasTagline()
    {
        return RM.contains(Integer.valueOf(26));
    }

    public boolean hasUrl()
    {
        return RM.contains(Integer.valueOf(27));
    }

    public boolean hasUrls()
    {
        return RM.contains(Integer.valueOf(28));
    }

    public boolean hasVerified()
    {
        return RM.contains(Integer.valueOf(29));
    }

    public int hashCode()
    {
        Iterator iterator = RL.values().iterator();
        int j = 0;
        while(iterator.hasNext()) 
        {
            fb.a a1 = (fb.a)iterator.next();
            int k;
            if(a(a1))
                k = j + a1.eu() + b(a1).hashCode();
            else
                k = j;
            j = k;
        }
        return j;
    }

    List ia()
    {
        return SX;
    }

    List ib()
    {
        return SY;
    }

    List ic()
    {
        return Tc;
    }

    public ir id()
    {
        return this;
    }

    public boolean isDataValid()
    {
        return true;
    }

    public boolean isPlusUser()
    {
        return SS;
    }

    public boolean isVerified()
    {
        return Td;
    }

    public void writeToParcel(Parcel parcel, int j)
    {
        is _tmp = CREATOR;
        is.a(this, parcel, j);
    }

    public static final is CREATOR = new is();
    private static final HashMap RL;
    private String FE;
    private final Set RM;
    private String SK;
    private a SL;
    private String SM;
    private String SN;
    private int SO;
    private b SP;
    private String SQ;
    private c SR;
    private boolean SS;
    private String ST;
    private d SU;
    private String SV;
    private int SW;
    private List SX;
    private List SY;
    private int SZ;
    private int Ta;
    private String Tb;
    private List Tc;
    private boolean Td;
    private int lu;
    private String pS;
    private String uS;
    private final int wj;

    static 
    {
        RL = new HashMap();
        RL.put("aboutMe", fb.a.j("aboutMe", 2));
        RL.put("ageRange", fb.a.a("ageRange", 3, com/google/android/gms/internal/ir$a));
        RL.put("birthday", fb.a.j("birthday", 4));
        RL.put("braggingRights", fb.a.j("braggingRights", 5));
        RL.put("circledByCount", fb.a.g("circledByCount", 6));
        RL.put("cover", fb.a.a("cover", 7, com/google/android/gms/internal/ir$b));
        RL.put("currentLocation", fb.a.j("currentLocation", 8));
        RL.put("displayName", fb.a.j("displayName", 9));
        RL.put("gender", fb.a.a("gender", 12, (new ey()).f("male", 0).f("female", 1).f("other", 2), false));
        RL.put("id", fb.a.j("id", 14));
        RL.put("image", fb.a.a("image", 15, com/google/android/gms/internal/ir$c));
        RL.put("isPlusUser", fb.a.i("isPlusUser", 16));
        RL.put("language", fb.a.j("language", 18));
        RL.put("name", fb.a.a("name", 19, com/google/android/gms/internal/ir$d));
        RL.put("nickname", fb.a.j("nickname", 20));
        RL.put("objectType", fb.a.a("objectType", 21, (new ey()).f("person", 0).f("page", 1), false));
        RL.put("organizations", fb.a.b("organizations", 22, com/google/android/gms/internal/ir$f));
        RL.put("placesLived", fb.a.b("placesLived", 23, com/google/android/gms/internal/ir$g));
        RL.put("plusOneCount", fb.a.g("plusOneCount", 24));
        RL.put("relationshipStatus", fb.a.a("relationshipStatus", 25, (new ey()).f("single", 0).f("in_a_relationship", 1).f("engaged", 2).f("married", 3).f("its_complicated", 4).f("open_relationship", 5).f("widowed", 6).f("in_domestic_partnership", 7).f("in_civil_union", 8), false));
        RL.put("tagline", fb.a.j("tagline", 26));
        RL.put("url", fb.a.j("url", 27));
        RL.put("urls", fb.a.b("urls", 28, com/google/android/gms/internal/ir$h));
        RL.put("verified", fb.a.i("verified", 29));
    }
}
