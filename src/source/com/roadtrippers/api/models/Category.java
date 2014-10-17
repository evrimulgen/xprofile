// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.api.models;


// Referenced classes of package com.roadtrippers.api.models:
//            Poi

public class Category
    implements Comparable
{

    public Category()
    {
    }

    static Poi[] addLast(Poi apoi[], Poi poi)
    {
        Poi apoi1[] = new Poi[1 + apoi.length];
        System.arraycopy(apoi, 0, apoi1, 0, apoi.length);
        apoi1[apoi.length] = poi;
        return apoi1;
    }

    static Poi[] remove(Poi apoi[], int i)
    {
        Poi apoi1[];
        if(i != -1)
        {
            apoi1 = new Poi[-1 + apoi.length];
            int j = 0;
            while(j < apoi1.length) 
            {
                int k;
                if(j < i)
                    k = j;
                else
                    k = j + 1;
                apoi1[j] = apoi[k];
                j++;
            }
        } else
        {
            apoi1 = apoi;
        }
        return apoi1;
    }

    public void add(Poi poi)
    {
        pois = addLast(pois, poi);
    }

    public int compareTo(Category category)
    {
        return value.compareTo(category.value);
    }

    public volatile int compareTo(Object obj)
    {
        return compareTo((Category)obj);
    }

    public boolean contains(Poi poi)
    {
        return indexOf(poi) != -1;
    }

    public boolean equals(Object obj)
    {
        if(this != obj)
        {
            if(obj == null || getClass() != obj.getClass())
                return false;
            Category category = (Category)obj;
            if(id != category.id)
                return false;
            if(value == null ? category.value != null : !value.equals(category.value))
                return false;
        }
        return true;
    }

    public int hashCode()
    {
        int i;
        if(value != null)
            i = value.hashCode();
        else
            i = 0;
        return i * 31 + id;
    }

    int indexOf(Poi poi)
    {
        for(int i = 0; i < pois.length; i++)
            if(poi.equals(pois[i]))
                return i;

        return -1;
    }

    public void remove(Poi poi)
    {
        pois = remove(pois, indexOf(poi));
    }

    public boolean checked;
    public String group;
    public String group_name;
    public int id;
    public String name;
    public Poi pois[];
    public String value;
}
