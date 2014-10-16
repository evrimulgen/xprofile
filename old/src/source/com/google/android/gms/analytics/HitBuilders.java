// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.analytics;

import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;

// Referenced classes of package com.google.android.gms.analytics:
//            u, aa, ak, o

public class HitBuilders
{
    public static class AppViewBuilder extends HitBuilder
    {

        public volatile Map build()
        {
            return super.build();
        }

        public AppViewBuilder()
        {
            u.bR().a(u.a.tv);
            set("&t", "appview");
        }
    }

    public static class EventBuilder extends HitBuilder
    {

        public volatile Map build()
        {
            return super.build();
        }

        public EventBuilder setAction(String s)
        {
            set("&ea", s);
            return this;
        }

        public EventBuilder setCategory(String s)
        {
            set("&ec", s);
            return this;
        }

        public EventBuilder setLabel(String s)
        {
            set("&el", s);
            return this;
        }

        public EventBuilder setValue(long l)
        {
            set("&ev", Long.toString(l));
            return this;
        }

        public EventBuilder()
        {
            u.bR().a(u.a.tj);
            set("&t", "event");
        }

        public EventBuilder(String s, String s1)
        {
            this();
            setCategory(s);
            setAction(s1);
        }
    }

    public static class ExceptionBuilder extends HitBuilder
    {

        public volatile Map build()
        {
            return super.build();
        }

        public ExceptionBuilder setDescription(String s)
        {
            set("&exd", s);
            return this;
        }

        public ExceptionBuilder setFatal(boolean flag)
        {
            set("&exf", ak.s(flag));
            return this;
        }

        public ExceptionBuilder()
        {
            u.bR().a(u.a.sS);
            set("&t", "exception");
        }
    }

    protected static class HitBuilder
    {

        public Map build()
        {
            return tO;
        }

        protected String get(String s)
        {
            return (String)tO.get(s);
        }

        public final HitBuilder set(String s, String s1)
        {
            u.bR().a(u.a.sl);
            if(s != null)
            {
                tO.put(s, s1);
                return this;
            } else
            {
                aa.w(" HitBuilder.set() called with a null paramName.");
                return this;
            }
        }

        public final HitBuilder setAll(Map map)
        {
            u.bR().a(u.a.sm);
            if(map == null)
            {
                return this;
            } else
            {
                tO.putAll(new HashMap(map));
                return this;
            }
        }

        public HitBuilder setCampaignParamsFromUrl(String s)
        {
            u.bR().a(u.a.so);
            String s1 = ak.J(s);
            if(TextUtils.isEmpty(s1))
            {
                return this;
            } else
            {
                Map map = ak.I(s1);
                set("&cc", (String)map.get("utm_content"));
                set("&cm", (String)map.get("utm_medium"));
                set("&cn", (String)map.get("utm_campaign"));
                set("&cs", (String)map.get("utm_source"));
                set("&ck", (String)map.get("utm_term"));
                set("&ci", (String)map.get("utm_id"));
                set("&gclid", (String)map.get("gclid"));
                set("&dclid", (String)map.get("dclid"));
                set("&gmob_t", (String)map.get("gmob_t"));
                return this;
            }
        }

        public HitBuilder setCustomDimension(int i, String s)
        {
            set(o.q(i), s);
            return this;
        }

        public HitBuilder setCustomMetric(int i, float f)
        {
            set(o.r(i), Float.toString(f));
            return this;
        }

        protected HitBuilder setHitType(String s)
        {
            set("&t", s);
            return this;
        }

        public HitBuilder setNewSession()
        {
            set("&sc", "start");
            return this;
        }

        public HitBuilder setNonInteraction(boolean flag)
        {
            set("&ni", ak.s(flag));
            return this;
        }

        private Map tO;

        protected HitBuilder()
        {
            tO = new HashMap();
        }
    }

    public static class ItemBuilder extends HitBuilder
    {

        public volatile Map build()
        {
            return super.build();
        }

        public ItemBuilder setCategory(String s)
        {
            set("&iv", s);
            return this;
        }

        public ItemBuilder setCurrencyCode(String s)
        {
            set("&cu", s);
            return this;
        }

        public ItemBuilder setName(String s)
        {
            set("&in", s);
            return this;
        }

        public ItemBuilder setPrice(double d)
        {
            set("&ip", Double.toString(d));
            return this;
        }

        public ItemBuilder setQuantity(long l)
        {
            set("&iq", Long.toString(l));
            return this;
        }

        public ItemBuilder setSku(String s)
        {
            set("&ic", s);
            return this;
        }

        public ItemBuilder setTransactionId(String s)
        {
            set("&ti", s);
            return this;
        }

        public ItemBuilder()
        {
            u.bR().a(u.a.tk);
            set("&t", "item");
        }
    }

    public static class SocialBuilder extends HitBuilder
    {

        public volatile Map build()
        {
            return super.build();
        }

        public SocialBuilder setAction(String s)
        {
            set("&sa", s);
            return this;
        }

        public SocialBuilder setNetwork(String s)
        {
            set("&sn", s);
            return this;
        }

        public SocialBuilder setTarget(String s)
        {
            set("&st", s);
            return this;
        }

        public SocialBuilder()
        {
            u.bR().a(u.a.sV);
            set("&t", "social");
        }
    }

    public static class TimingBuilder extends HitBuilder
    {

        public volatile Map build()
        {
            return super.build();
        }

        public TimingBuilder setCategory(String s)
        {
            set("&utc", s);
            return this;
        }

        public TimingBuilder setLabel(String s)
        {
            set("&utl", s);
            return this;
        }

        public TimingBuilder setValue(long l)
        {
            set("&utt", Long.toString(l));
            return this;
        }

        public TimingBuilder setVariable(String s)
        {
            set("&utv", s);
            return this;
        }

        public TimingBuilder()
        {
            u.bR().a(u.a.sU);
            set("&t", "timing");
        }

        public TimingBuilder(String s, String s1, long l)
        {
            this();
            setVariable(s1);
            setValue(l);
            setCategory(s);
        }
    }

    public static class TransactionBuilder extends HitBuilder
    {

        public volatile Map build()
        {
            return super.build();
        }

        public TransactionBuilder setAffiliation(String s)
        {
            set("&ta", s);
            return this;
        }

        public TransactionBuilder setCurrencyCode(String s)
        {
            set("&cu", s);
            return this;
        }

        public TransactionBuilder setRevenue(double d)
        {
            set("&tr", Double.toString(d));
            return this;
        }

        public TransactionBuilder setShipping(double d)
        {
            set("&ts", Double.toString(d));
            return this;
        }

        public TransactionBuilder setTax(double d)
        {
            set("&tt", Double.toString(d));
            return this;
        }

        public TransactionBuilder setTransactionId(String s)
        {
            set("&ti", s);
            return this;
        }

        public TransactionBuilder()
        {
            u.bR().a(u.a.sR);
            set("&t", "transaction");
        }
    }


    public HitBuilders()
    {
    }
}
