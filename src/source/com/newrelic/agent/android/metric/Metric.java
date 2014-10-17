// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.agent.android.metric;

import com.newrelic.agent.android.harvest.type.HarvestableObject;
import com.newrelic.com.google.gson.*;

public class Metric extends HarvestableObject
{

    public Metric(Metric metric)
    {
        name = metric.getName();
        scope = metric.getScope();
        min = Double.valueOf(metric.getMin());
        max = Double.valueOf(metric.getMax());
        total = Double.valueOf(metric.getTotal());
        sumOfSquares = Double.valueOf(metric.getSumOfSquares());
        exclusive = Double.valueOf(metric.getExclusive());
        count = metric.getCount();
    }

    public Metric(String s)
    {
        this(s, null);
    }

    public Metric(String s, String s1)
    {
        name = s;
        scope = s1;
        count = 0L;
    }

    public void addExclusive(double d)
    {
        if(exclusive == null)
        {
            exclusive = Double.valueOf(d);
            return;
        } else
        {
            exclusive = Double.valueOf(d + exclusive.doubleValue());
            return;
        }
    }

    public void aggregate(Metric metric)
    {
        if(metric != null)
        {
            increment(metric.getCount());
            if(!metric.isCountOnly())
            {
                double d;
                double d1;
                double d2;
                if(total == null)
                    d = metric.getTotal();
                else
                    d = total.doubleValue() + metric.getTotal();
                total = Double.valueOf(d);
                if(sumOfSquares == null)
                    d1 = metric.getSumOfSquares();
                else
                    d1 = sumOfSquares.doubleValue() + metric.getSumOfSquares();
                sumOfSquares = Double.valueOf(d1);
                if(exclusive == null)
                    d2 = metric.getExclusive();
                else
                    d2 = exclusive.doubleValue() + metric.getExclusive();
                exclusive = Double.valueOf(d2);
                setMin(Double.valueOf(metric.getMin()));
                setMax(Double.valueOf(metric.getMax()));
                return;
            }
        }
    }

    public JsonElement asJson()
    {
        if(isCountOnly())
            return new JsonPrimitive(Long.valueOf(count));
        else
            return asJsonObject();
    }

    public JsonObject asJsonObject()
    {
        JsonObject jsonobject = new JsonObject();
        jsonobject.add("count", new JsonPrimitive(Long.valueOf(count)));
        if(total != null)
            jsonobject.add("total", new JsonPrimitive(total));
        if(min != null)
            jsonobject.add("min", new JsonPrimitive(min));
        if(max != null)
            jsonobject.add("max", new JsonPrimitive(max));
        if(sumOfSquares != null)
            jsonobject.add("sum_of_squares", new JsonPrimitive(sumOfSquares));
        if(exclusive != null)
            jsonobject.add("exclusive", new JsonPrimitive(exclusive));
        return jsonobject;
    }

    public void clear()
    {
        min = null;
        max = null;
        total = null;
        sumOfSquares = null;
        exclusive = null;
        count = 0L;
    }

    public long getCount()
    {
        return count;
    }

    public double getExclusive()
    {
        if(exclusive == null)
            return 0.0D;
        else
            return exclusive.doubleValue();
    }

    public double getMax()
    {
        if(max == null)
            return 0.0D;
        else
            return max.doubleValue();
    }

    public double getMin()
    {
        if(min == null)
            return 0.0D;
        else
            return min.doubleValue();
    }

    public String getName()
    {
        return name;
    }

    public String getScope()
    {
        return scope;
    }

    public String getStringScope()
    {
        if(scope == null)
            return "";
        else
            return scope;
    }

    public double getSumOfSquares()
    {
        if(sumOfSquares == null)
            return 0.0D;
        else
            return sumOfSquares.doubleValue();
    }

    public double getTotal()
    {
        if(total == null)
            return 0.0D;
        else
            return total.doubleValue();
    }

    public void increment()
    {
        increment(1L);
    }

    public void increment(long l)
    {
        count = l + count;
    }

    public boolean isCountOnly()
    {
        return total == null;
    }

    public boolean isScoped()
    {
        return scope != null;
    }

    public boolean isUnscoped()
    {
        return scope == null;
    }

    public void sample(double d)
    {
        count = 1L + count;
        if(total == null)
        {
            total = Double.valueOf(d);
            sumOfSquares = Double.valueOf(d * d);
        } else
        {
            total = Double.valueOf(d + total.doubleValue());
            sumOfSquares = Double.valueOf(sumOfSquares.doubleValue() + d * d);
        }
        setMin(Double.valueOf(d));
        setMax(Double.valueOf(d));
    }

    public void setCount(long l)
    {
        count = l;
    }

    public void setExclusive(Double double1)
    {
        exclusive = double1;
    }

    public void setMax(Double double1)
    {
        if(double1 != null)
        {
            if(max == null)
            {
                max = double1;
                return;
            }
            if(double1.doubleValue() > max.doubleValue())
            {
                max = double1;
                return;
            }
        }
    }

    public void setMaxFieldValue(Double double1)
    {
        max = double1;
    }

    public void setMin(Double double1)
    {
        if(double1 != null)
        {
            if(min == null)
            {
                min = double1;
                return;
            }
            if(double1.doubleValue() < min.doubleValue())
            {
                min = double1;
                return;
            }
        }
    }

    public void setMinFieldValue(Double double1)
    {
        min = double1;
    }

    public void setName(String s)
    {
        name = s;
    }

    public void setScope(String s)
    {
        scope = s;
    }

    public void setSumOfSquares(Double double1)
    {
        sumOfSquares = double1;
    }

    public void setTotal(Double double1)
    {
        total = double1;
    }

    public String toString()
    {
        return (new StringBuilder()).append("Metric{count=").append(count).append(", total=").append(total).append(", max=").append(max).append(", min=").append(min).append(", scope='").append(scope).append('\'').append(", name='").append(name).append('\'').append(", exclusive='").append(exclusive).append('\'').append(", sumofsquares='").append(sumOfSquares).append('\'').append('}').toString();
    }

    private long count;
    private Double exclusive;
    private Double max;
    private Double min;
    private String name;
    private String scope;
    private Double sumOfSquares;
    private Double total;
}
