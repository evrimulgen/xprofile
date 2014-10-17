// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.fragments;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.*;
import android.widget.ImageView;
import android.widget.TextView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.roadtrippers.api.models.GalleryItem;
import com.roadtrippers.fragments.base.BaseFragment;
import com.roadtrippers.util.Serializer;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import dagger.Lazy;

public class GalleryItemFragment extends BaseFragment
{

    public GalleryItemFragment()
    {
    }

    public static GalleryItemFragment newInstance(GalleryItem galleryitem)
    {
        GalleryItemFragment galleryitemfragment = new GalleryItemFragment();
        Bundle bundle = new Bundle();
        try
        {
            bundle.putString("ITEM", (new ObjectMapper()).writeValueAsString(galleryitem));
        }
        catch(JsonProcessingException jsonprocessingexception)
        {
            jsonprocessingexception.printStackTrace();
        }
        galleryitemfragment.setArguments(bundle);
        return galleryitemfragment;
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        item = (GalleryItem)((Serializer)jackson.get()).deserialize(getArguments().getString("ITEM"), com/roadtrippers/api/models/GalleryItem);
        return layoutinflater.inflate(0x7f030039, viewgroup, false);
    }

    public void onViewCreated(View view, Bundle bundle)
    {
        super.onViewCreated(view, bundle);
        ((Picasso)picasso.get()).load(item.getImage().getContentUrl()).into(image);
        if(item.getCaption() != null && item.getCaption().length() > 0)
        {
            caption.setText(item.getCaption());
            caption.setMovementMethod(new ScrollingMovementMethod());
            ((Picasso)picasso.get()).load(item.getUser_image_mini_url()).placeholder(0x7f0200e2).into(userImage);
            return;
        } else
        {
            captionContainer.setVisibility(4);
            return;
        }
    }

    public static final String ITEM = "ITEM";
    TextView caption;
    View captionContainer;
    ImageView image;
    GalleryItem item;
    Lazy jackson;
    Lazy picasso;
    ImageView userImage;
}
