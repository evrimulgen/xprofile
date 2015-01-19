// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.api.models;

import java.util.HashMap;
import java.util.Map;

public class GalleryItem
{
    public class GalleryImage
    {

        public Map getAdditionalProperties()
        {
            return additionalProperties;
        }

        public String getContentUrl()
        {
            return content_url;
        }

        public String getThumb_url()
        {
            return thumb_url;
        }

        public void setAdditionalProperty(String s, Object obj)
        {
            additionalProperties.put(s, obj);
        }

        public void setContent_url(String s)
        {
            content_url = s;
        }

        public void setThumbUrl(String s)
        {
            thumb_url = s;
        }

        private Map additionalProperties;
        private String content_url;
        final GalleryItem this$0;
        private String thumb_url;

        public GalleryImage()
        {
            this$0 = GalleryItem.this;
            super();
            additionalProperties = new HashMap();
        }
    }


    public GalleryItem()
    {
        additionalProperties = new HashMap();
    }

    public GalleryItem(String s)
    {
        additionalProperties = new HashMap();
        image = new GalleryImage();
        image.setContent_url(s);
    }

    public Map getAdditionalProperties()
    {
        return additionalProperties;
    }

    public String getCaption()
    {
        return caption;
    }

    public String getCreated_at()
    {
        return created_at;
    }

    public Integer getId()
    {
        return id;
    }

    public GalleryImage getImage()
    {
        return image;
    }

    public String getUser_image_mini_url()
    {
        return user_image_mini_url;
    }

    public String getUsername()
    {
        return username;
    }

    public void setAdditionalProperty(String s, Object obj)
    {
        additionalProperties.put(s, obj);
    }

    public void setCaption(String s)
    {
        caption = s;
    }

    public void setCreated_at(String s)
    {
        created_at = s;
    }

    public void setId(Integer integer)
    {
        id = integer;
    }

    public void setImage(GalleryImage galleryimage)
    {
        image = galleryimage;
    }

    public void setUser_image_mini_url(String s)
    {
        user_image_mini_url = s;
    }

    public void setUsername(String s)
    {
        username = s;
    }

    private Map additionalProperties;
    private String caption;
    private String created_at;
    private Integer id;
    private GalleryImage image;
    private String user_image_mini_url;
    private String username;
}
