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
import com.roadtrippers.api.Roadtrippers;
import com.roadtrippers.api.models.RTAutocompleteResponse;
import com.roadtrippers.api.models.RTAutocompleteSuggestion;
import com.roadtrippers.db.DatabaseHelper;
import dagger.Lazy;
import rx.*;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class AutocompleteAdapter extends ArrayAdapter
{
    static class ViewHolder
    {

        void setRTAutocompleteSuggestion(RTAutocompleteSuggestion rtautocompletesuggestion)
        {
            bold.setText(rtautocompletesuggestion.getName());
            StringBuilder stringbuilder = (new StringBuilder()).append(rtautocompletesuggestion.getAddress1());
            String s;
            StringBuilder stringbuilder1;
            String s1;
            StringBuilder stringbuilder2;
            String s2;
            String s3;
            if(rtautocompletesuggestion.getAddress1().length() == 0)
                s = "";
            else
                s = " ";
            stringbuilder1 = stringbuilder.append(s).append(rtautocompletesuggestion.getAddress2());
            if(rtautocompletesuggestion.getAddress2().length() == 0)
                s1 = "";
            else
                s1 = " ";
            stringbuilder2 = stringbuilder1.append(s1).append(rtautocompletesuggestion.getCity());
            if(rtautocompletesuggestion.getCity().length() == 0)
                s2 = "";
            else
                s2 = ", ";
            s3 = stringbuilder2.append(s2).append(rtautocompletesuggestion.getState()).toString().trim();
            normal.setText(s3);
        }

        TextView bold;
        TextView normal;

        ViewHolder(View view)
        {
            ButterKnife.inject(this, view);
        }
    }


    public AutocompleteAdapter(Context context, int i)
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
                final android.widget.Filter.FilterResults filterResults;
                if(charsequence == null)
                    s = "";
                else
                    s = charsequence.toString().trim();
                filterResults = new android.widget.Filter.FilterResults();
                if(!TextUtils.isEmpty(s) && s.length() >= 3)
                {
                    if(true)
                    {
                        RTAutocompleteSuggestion artautocompletesuggestion[] = ((Roadtrippers)roadtrippersLazy.get()).autocomplete(s).places;
                        ((DatabaseHelper)databaseHelperLazy.get()).saveRTAutocompleteSuggestions(s, artautocompletesuggestion);
                        filterResults.values = artautocompletesuggestion;
                        filterResults.count = artautocompletesuggestion.length;
                        return filterResults;
                    } else
                    {
                        filterResults.values = null;
                        filterResults.count = null.length;
                        sub = Observable.create(s. new rx.Observable.OnSubscribe() {

                            public volatile void call(Object obj)
                            {
                                call((Subscriber)obj);
                            }

                            public void call(Subscriber subscriber)
                            {
                                try
                                {
                                    subscriber.onNext(((Roadtrippers)roadtrippersLazy.get()).autocomplete(query).places);
                                    return;
                                }
                                catch(Exception exception)
                                {
                                    exception.printStackTrace();
                                }
                            }

                            final _cls1 this$1;
                            final String val$query;

            
            {
                this$1 = final__pcls1;
                query = String.this;
                super();
            }
                        }
).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(s. new Action1() {

                            public volatile void call(Object obj)
                            {
                                call((RTAutocompleteSuggestion[])obj);
                            }

                            public void call(RTAutocompleteSuggestion artautocompletesuggestion[])
                            {
                                filterResults.values = artautocompletesuggestion;
                                filterResults.count = artautocompletesuggestion.length;
                                ((DatabaseHelper)databaseHelperLazy.get()).saveRTAutocompleteSuggestions(query, artautocompletesuggestion);
                            }

                            final _cls1 this$1;
                            final android.widget.Filter.FilterResults val$filterResults;
                            final String val$query;

            
            {
                this$1 = final__pcls1;
                filterResults = filterresults;
                query = String.this;
                super();
            }
                        }
);
                        return filterResults;
                    }
                } else
                {
                    filterResults.count = 0;
                    return filterResults;
                }
            }

            protected void publishResults(CharSequence charsequence, android.widget.Filter.FilterResults filterresults)
            {
                predictions = (RTAutocompleteSuggestion[])(RTAutocompleteSuggestion[])filterresults.values;
                notifyDataSetChanged();
            }

            final AutocompleteAdapter this$0;

            
            {
                this$0 = AutocompleteAdapter.this;
                super();
            }
        }
;
    }

    public RTAutocompleteSuggestion getItem(int i)
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
        ((ViewHolder)view.getTag()).setRTAutocompleteSuggestion(getItem(i));
        return view;
    }

    Lazy databaseHelperLazy;
    LayoutInflater inflater;
    Lazy placesApi;
    RTAutocompleteSuggestion predictions[];
    Lazy roadtrippersLazy;
    Subscription sub;
}
