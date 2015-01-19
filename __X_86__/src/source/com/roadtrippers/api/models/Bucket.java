// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.api.models;

import java.util.*;

// Referenced classes of package com.roadtrippers.api.models:
//            BucketListEntry, Poi

public class Bucket
{

    public Bucket()
    {
        entries = new ArrayList();
        additionalProperties = new HashMap();
    }

    public void add(Poi poi)
    {
        BucketListEntry bucketlistentry = BucketListEntry.fromPoi(poi);
        entries.add(bucketlistentry);
    }

    public boolean contains(Poi poi)
    {
        for(Iterator iterator = entries.iterator(); iterator.hasNext();)
            if(((BucketListEntry)iterator.next()).getId() == Integer.parseInt(poi.id))
                return true;

        return false;
    }

    public Map getAdditionalProperties()
    {
        return additionalProperties;
    }

    public String getCreated_at()
    {
        return created_at;
    }

    public Object getDescription()
    {
        return description;
    }

    public List getEntries()
    {
        return entries;
    }

    public List getEntriesAsPois()
    {
        ArrayList arraylist = new ArrayList();
        for(Iterator iterator = entries.iterator(); iterator.hasNext(); arraylist.add(((BucketListEntry)iterator.next()).asPoi()));
        return arraylist;
    }

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public int getPrivacy_level()
    {
        return privacy_level;
    }

    public String getUpdated_at()
    {
        return updated_at;
    }

    public String getUrl()
    {
        return url;
    }

    public void remove(Poi poi)
    {
        BucketListEntry bucketlistentry = null;
        Iterator iterator = entries.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            BucketListEntry bucketlistentry1 = (BucketListEntry)iterator.next();
            if(bucketlistentry1.getId() == Integer.parseInt(poi.id))
                bucketlistentry = bucketlistentry1;
        } while(true);
        entries.remove(bucketlistentry);
    }

    public void setAdditionalProperty(String s, Object obj)
    {
        additionalProperties.put(s, obj);
    }

    public void setCreated_at(String s)
    {
        created_at = s;
    }

    public void setDescription(Object obj)
    {
        description = obj;
    }

    public void setEntries(List list)
    {
        entries = list;
    }

    public void setId(int i)
    {
        id = i;
    }

    public void setName(String s)
    {
        name = s;
    }

    public void setPrivacy_level(int i)
    {
        privacy_level = i;
    }

    public void setUpdated_at(String s)
    {
        updated_at = s;
    }

    public void setUrl(String s)
    {
        url = s;
    }

    private Map additionalProperties;
    public transient boolean checked;
    private String created_at;
    private Object description;
    private List entries;
    private int id;
    private String name;
    private int privacy_level;
    private String updated_at;
    private String url;
}
