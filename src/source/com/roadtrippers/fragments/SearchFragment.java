// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.fragments;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.*;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;
import com.roadtrippers.adapters.AutocompleteAdapter;
import com.roadtrippers.api.models.RTAutocompleteSuggestion;
import com.roadtrippers.fragments.base.BaseProgressFragment;
import com.roadtrippers.fragments.base.RetainedFragment;
import com.roadtrippers.util.RTAnalytics;
import com.roadtrippers.widget.TypefaceAutoComplete;
import com.squareup.otto.Bus;
import dagger.Lazy;

// Referenced classes of package com.roadtrippers.fragments:
//            SettingsFragment

public class SearchFragment extends RetainedFragment
{

    public SearchFragment()
    {
    }

    void onClearSearchClick()
    {
        RTAnalytics.logEvent(getActivity(), 0x7f0c006e, 0x7f0c003b);
        searchField.setText(null);
        inputMethod.toggleSoftInput(2, 2);
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        inputMethod = (InputMethodManager)getActivity().getSystemService("input_method");
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        return layoutinflater.inflate(0x7f03005c, viewgroup, false);
    }

    public void onDestroyView()
    {
        inputMethod.hideSoftInputFromWindow(searchField.getWindowToken(), 0);
        super.onDestroyView();
    }

    void onSearchClick()
    {
        RTAnalytics.logEvent(getActivity(), 0x7f0c006e, 0x7f0c003a);
        getFragmentManager().popBackStack();
    }

    public void onViewCreated(View view, Bundle bundle)
    {
        super.onViewCreated(view, bundle);
        inputMethod.toggleSoftInput(2, 0);
        searchField.setAdapter(new AutocompleteAdapter(getActivity(), 0x7f030043));
        searchField.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView adapterview, View view1, int i, long l)
            {
                RTAutocompleteSuggestion rtautocompletesuggestion = (RTAutocompleteSuggestion)adapterview.getItemAtPosition(i);
                RTAnalytics.logEvent(getActivity(), 0x7f0c006e, 0x7f0c003c, rtautocompletesuggestion.getId().toString());
                ((Bus)bus.get()).post(rtautocompletesuggestion);
                searchField.setText(rtautocompletesuggestion.getName());
                inputMethod.hideSoftInputFromWindow(searchField.getWindowToken(), 0);
            }

            final SearchFragment this$0;

            
            {
                this$0 = SearchFragment.this;
                super();
            }
        }
);
        searchField.setOnEditorActionListener(new android.widget.TextView.OnEditorActionListener() {

            public boolean onEditorAction(TextView textview, int i, KeyEvent keyevent)
            {
                searchField.dismissDropDown();
                ((Bus)bus.get()).post(SettingsFragment.trim(textview));
                RTAnalytics.logEvent(getActivity(), 0x7f0c006e, 0x7f0c003e, SettingsFragment.trim(textview));
                inputMethod.hideSoftInputFromWindow(searchField.getWindowToken(), 0);
                return true;
            }

            final SearchFragment this$0;

            
            {
                this$0 = SearchFragment.this;
                super();
            }
        }
);
        searchField.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable editable)
            {
            }

            public void beforeTextChanged(CharSequence charsequence, int i, int j, int k)
            {
            }

            public void onTextChanged(CharSequence charsequence, int i, int j, int k)
            {
                if(charsequence.length() > 0)
                {
                    clearSearch.setVisibility(0);
                    return;
                } else
                {
                    clearSearch.setVisibility(4);
                    return;
                }
            }

            final SearchFragment this$0;

            
            {
                this$0 = SearchFragment.this;
                super();
            }
        }
);
        BaseProgressFragment.setColorFilter(clearSearch, 0xffcccccc);
    }

    Lazy bus;
    ImageView clearSearch;
    InputMethodManager inputMethod;
    TypefaceAutoComplete searchField;
}
