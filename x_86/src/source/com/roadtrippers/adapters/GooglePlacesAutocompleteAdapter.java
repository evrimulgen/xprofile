// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.adapters;

import android.content.Context;
import android.text.TextUtils;
import android.view.*;
import android.widget.*;
import butterknife.ButterKnife;
import com.roadtrippers.RoadTrippersApp;
import com.roadtrippers.api.GooglePlaces;
import com.roadtrippers.api.models.AutocompleteResponse;
import com.roadtrippers.api.models.GooglePlace;
import com.roadtrippers.db.DatabaseHelper;
import dagger.Lazy;

public class GooglePlacesAutocompleteAdapter extends ArrayAdapter
{
    static class ViewHolder
    {

        void setGooglePlace(GooglePlace googleplace)
        {
            String as[] = googleplace.split();
            bold.setText(as[0]);
            normal.setText(as[1]);
        }

        TextView bold;
        TextView normal;

        ViewHolder(View view)
        {
            ButterKnife.inject(this, view);
        }
    }


    public GooglePlacesAutocompleteAdapter(Context context, int i)
    {
        super(context, i);
        RoadTrippersApp.from(context).inject(this);
        inflater = LayoutInflater.from(context);
    }

    public int getCount()
    {
        if(predictions != null)
            return predictions.length;
        else
            return 0;
    }

    public Filter getFilter()
    {
        return new Filter() {

            protected android.widget.Filter.FilterResults performFiltering(CharSequence charsequence)
            {
                String s;
                android.widget.Filter.FilterResults filterresults;
                if(charsequence == null)
                    s = "";
                else
                    s = charsequence.toString().trim();
                filterresults = new android.widget.Filter.FilterResults();
                if(!TextUtils.isEmpty(s) && focused)
                {
                    if(true)
                    {
                        GooglePlace agoogleplace[] = ((GooglePlaces)placesApi.get()).autocomplete(s, "AIzaSyBm8Rs4h4xvSJ1AD5O8Aej-h3KUkL2DV60").predictions;
                        ((DatabaseHelper)databaseHelperLazy.get()).savePlaces(s, agoogleplace);
                        filterresults.values = agoogleplace;
                        filterresults.count = agoogleplace.length;
                        return filterresults;
                    } else
                    {
                        filterresults.values = null;
                        filterresults.count = null.length;
                        return filterresults;
                    }
                } else
                {
                    filterresults.count = 0;
                    return filterresults;
                }
            }

            protected void publishResults(CharSequence charsequence, android.widget.Filter.FilterResults filterresults)
            {
                predictions = (GooglePlace[])(GooglePlace[])filterresults.values;
                notifyDataSetChanged();
            }

            final GooglePlacesAutocompleteAdapter this$0;

            
            {
                this$0 = GooglePlacesAutocompleteAdapter.this;
                super();
            }
        }
;
    }

    public GooglePlace getItem(int i)
    {
        return predictions[i];
    }

    public volatile Object getItem(int i)
    {
        return getItem(i);
    }

    public View getView(int i, View view, ViewGroup viewgroup)
    {
        if(view == null)
        {
            view = inflater.inflate(0x7f03001d, viewgroup, false);
            view.setTag(new ViewHolder(view));
        }
        ((ViewHolder)view.getTag()).setGooglePlace(getItem(i));
        return view;
    }

    public void setFocused(boolean flag)
    {
        focused = flag;
    }

    Lazy databaseHelperLazy;
    boolean focused;
    LayoutInflater inflater;
    Lazy placesApi;
    GooglePlace predictions[];
}
