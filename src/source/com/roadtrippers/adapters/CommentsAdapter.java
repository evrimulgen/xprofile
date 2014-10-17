// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.adapters;

import android.content.Context;
import android.text.Html;
import android.view.*;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife;
import com.roadtrippers.RoadTrippersApp;
import com.roadtrippers.adapters.base.InflaterAdapter;
import com.roadtrippers.api.models.Comment;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import dagger.Lazy;

public class CommentsAdapter extends InflaterAdapter
{
    class ViewHolder
    {

        void setComment(Comment comment)
        {
            ((Picasso)picassoLazy.get()).load(comment.author_avatar).placeholder(0x7f0200e1).error(0x7f0200e1).into(avatar);
            user.setText(comment.author_name);
            text.setText(Html.fromHtml(comment.text));
            date.setText(comment.created_at);
        }

        ImageView avatar;
        TextView date;
        TextView text;
        final CommentsAdapter this$0;
        TextView user;

        ViewHolder(View view)
        {
            this$0 = CommentsAdapter.this;
            super();
            ButterKnife.inject(this, view);
        }
    }


    public CommentsAdapter(Context context, Comment acomment[])
    {
        super(context);
        RoadTrippersApp.from(context).inject(this);
        comments = acomment;
    }

    public void add(Comment comment)
    {
        comments = Comment.add(comments, comment);
        notifyDataSetChanged();
    }

    public int getCount()
    {
        if(comments != null)
            return comments.length;
        else
            return 0;
    }

    public Comment getItem(int i)
    {
        return comments[i];
    }

    public volatile Object getItem(int i)
    {
        return getItem(i);
    }

    public View getView(int i, View view, ViewGroup viewgroup)
    {
        if(view == null)
        {
            view = inflater.inflate(0x7f030030, viewgroup, false);
            view.setTag(new ViewHolder(view));
        }
        ((ViewHolder)view.getTag()).setComment(getItem(i));
        return view;
    }

    public boolean isEnabled(int i)
    {
        return false;
    }

    Comment comments[];
    Lazy picassoLazy;
}
