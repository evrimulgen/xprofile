// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.api.models;

import android.content.Context;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.text.SimpleDateFormat;

// Referenced classes of package com.roadtrippers.api.models:
//            Poi

public class Comment
{
    static class DateParser extends JsonDeserializer
    {

        public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            return deserialize(jsonparser, deserializationcontext);
        }

        public String deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            String s = jsonparser.getValueAsString();
            String s1;
            try
            {
                java.util.Date date = IN.parse(s);
                s1 = OUT.format(date);
            }
            catch(Throwable throwable)
            {
                return s;
            }
            return s1;
        }

        static final SimpleDateFormat IN = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        static final SimpleDateFormat OUT = new SimpleDateFormat("dd MMMM yyyy HH:mm");


        DateParser()
        {
        }
    }


    public Comment()
    {
    }

    public static Comment[] add(Comment acomment[], Comment comment)
    {
        Comment acomment1[];
        if(acomment == null)
            acomment1 = new Comment[1];
        else
            acomment1 = new Comment[1 + acomment.length];
        if(acomment != null)
            System.arraycopy(acomment, 0, acomment1, 0, acomment.length);
        acomment1[-1 + acomment1.length] = comment;
        return acomment1;
    }

    public static Comment defaultCommentWithPoi(Context context, Poi poi)
    {
        Comment comment = new Comment();
        comment.author_name = context.getString(0x7f0c00da);
        Object aobj[] = new Object[1];
        aobj[0] = poi.name;
        comment.text = context.getString(0x7f0c00a7, aobj);
        return comment;
    }

    public String author_avatar;
    public String author_name;
    public String created_at;
    public int id;
    public String text;
}
