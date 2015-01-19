// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.fragments;

import android.app.ActivityManager;
import android.content.*;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.*;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;
import butterknife.ButterKnife;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.location.*;
import com.google.gson.JsonObject;
import com.mobsandgeeks.saripaar.Rule;
import com.mobsandgeeks.saripaar.Validator;
import com.roadtrippers.adapters.base.InflaterAdapter;
import com.roadtrippers.api.GooglePlaces;
import com.roadtrippers.api.Roadtrippers;
import com.roadtrippers.api.models.*;
import com.roadtrippers.api.requests.CreatePoiRequest;
import com.roadtrippers.fragments.base.ChooseCropPhotoFragment;
import com.roadtrippers.util.*;
import dagger.Lazy;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import io.segment.android.models.EasyJSONObject;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import rx.Observable;
import rx.Observer;
import rx.android.observables.AndroidObservable;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

// Referenced classes of package com.roadtrippers.fragments:
//            DetailFragment, SettingsFragment

public class AddPlaceFragment extends ChooseCropPhotoFragment
    implements LocationListener
{
    static class InvalidAddressException extends Exception
    {

        InvalidAddressException()
        {
        }
    }

    static class SuggestAddressException extends Exception
    {

        SuggestAddressException()
        {
        }
    }

    class SuggestionsAdapter extends InflaterAdapter
    {

        public int getCount()
        {
            return results.length;
        }

        public Object getItem(int i)
        {
            return results[i];
        }

        public View getView(int i, View view, ViewGroup viewgroup)
        {
            if(view == null)
            {
                view = inflater.inflate(0x7f03001c, viewgroup, false);
                view.setTag(new ViewHolder(view));
            }
            ((ViewHolder)view.getTag()).setAddress((Address)getItem(i));
            return view;
        }

        Address results[];
        final AddPlaceFragment this$0;

        public SuggestionsAdapter(Context context, Address aaddress[])
        {
            this$0 = AddPlaceFragment.this;
            super(context);
            results = aaddress;
        }
    }

    class SuggestionsAdapter.ViewHolder
    {

        void setAddress(Address address)
        {
            street.setText(address.getStreet());
            cityStateZip.setText((new StringBuilder()).append(address.getCity()).append(", ").append(address.getState()).append(" ").append(address.getZip()).toString());
        }

        TextView cityStateZip;
        TextView street;
        final SuggestionsAdapter this$1;

        SuggestionsAdapter.ViewHolder(View view)
        {
            this$1 = SuggestionsAdapter.this;
            super();
            ButterKnife.inject(this, view);
        }
    }


    public AddPlaceFragment()
    {
    }

    private void releaseLocationClient()
    {
        locationClient.removeLocationUpdates(this);
        locationClient.disconnect();
    }

    void addTouchColorFilters()
    {
        setColorFilter(detectMyLocation, 0xffcccccc);
        setColorFilter(addPlacePhoto, 0xffcccccc);
        setColorFilter(saveButton, 0xffcccccc);
        setColorFilter(back, 0xffcccccc);
    }

    void back()
    {
        getActivity().finish();
    }

    void calculatePhotoHeight()
    {
        DetailFragment.runAfterMeasure(addPlacePhoto, new Runnable() {

            public void run()
            {
                float f = ((float)(-10 + addPlacePhoto.getWidth()) * (float)getCropAspectY()) / (float)getCropAspectX();
                DetailFragment.setHeight(addPlacePhoto, 10 + (int)f);
            }

            final AddPlaceFragment this$0;

            
            {
                this$0 = AddPlaceFragment.this;
                super();
            }
        }
, new Callable() {

            public Boolean call()
                throws Exception
            {
                boolean flag;
                if(addPlacePhoto.getWidth() > 0)
                    flag = true;
                else
                    flag = false;
                return Boolean.valueOf(flag);
            }

            public volatile Object call()
                throws Exception
            {
                return call();
            }

            final AddPlaceFragment this$0;

            
            {
                this$0 = AddPlaceFragment.this;
                super();
            }
        }
);
    }

    Observable createPoiObservable(final CreatePoiRequest createPoiRequest)
    {
        File file = getCroppedFile();
        if(file.exists() && file.length() > 0L)
            return base64Observable(file).flatMap(new Func1() {

                public volatile Object call(Object obj)
                {
                    return call((String)obj);
                }

                public Observable call(String s)
                {
                    createPoiRequest.primary_image_data = s;
                    return ((Roadtrippers)roadtrippersLazy.get()).createNewPoi(createPoiRequest);
                }

                final AddPlaceFragment this$0;
                final CreatePoiRequest val$createPoiRequest;

            
            {
                this$0 = AddPlaceFragment.this;
                createPoiRequest = createpoirequest;
                super();
            }
            }
);
        else
            return ((Roadtrippers)roadtrippersLazy.get()).createNewPoi(createPoiRequest);
    }

    Func1 createPoiWithAddress()
    {
        return new Func1() {

            public volatile Object call(Object obj)
            {
                return call((com.roadtrippers.api.models.Address.Response)obj);
            }

            public Observable call(com.roadtrippers.api.models.Address.Response response)
            {
                results = response.results;
                ArrayList arraylist = new ArrayList();
                Address aaddress[] = results;
                int i = aaddress.length;
                for(int j = 0; j < i; j++)
                {
                    Address address = aaddress[j];
                    if(address.isAddress())
                        arraylist.add(address);
                }

                results = new Address[arraylist.size()];
                arraylist.toArray(results);
                if(response.isOk() && results.length > 0 && !results[0].partial_match && results[0].getStreet().equalsIgnoreCase(SettingsFragment.trim(editAddress)) && results[0].getCity().equalsIgnoreCase(SettingsFragment.trim(editCity)) && results[0].getZip().equalsIgnoreCase(SettingsFragment.trim(editZip)) && results[0].getState().equalsIgnoreCase(SettingsFragment.trim(editState)))
                {
                    com.roadtrippers.api.models.Geometry.Location location = results[0].geometry.location;
                    CreatePoiRequest createpoirequest = new CreatePoiRequest(SettingsFragment.trim(addPlacePlaceName), SettingsFragment.trim(editAddress), SettingsFragment.trim(editCity), SettingsFragment.trim(editState), SettingsFragment.trim(editZip), location.lat, location.lng, "US", "approved");
                    return createPoiObservable(createpoirequest);
                }
                if(response.isOk() && results.length > 0 && results[0].getStreet() != null && results[0].getStreet().length() > 0)
                    return Observable.error(new SuggestAddressException());
                else
                    return Observable.error(new InvalidAddressException());
            }

            final AddPlaceFragment this$0;

            
            {
                this$0 = AddPlaceFragment.this;
                super();
            }
        }
;
    }

    void detectMyLocation()
    {
        if(!locationClient.isConnected() && !locationClient.isConnecting())
            locationClient.connect();
    }

    protected int getCropAspectX()
    {
        return 441;
    }

    protected int getCropAspectY()
    {
        return 185;
    }

    protected ViewGroup getCroutonContainer()
    {
        return container;
    }

    protected ImageView getTargetImageView()
    {
        return addPlacePhoto;
    }

    protected void onContentCreated(Bundle bundle)
    {
        setUpValidation();
        setUpLocationClient();
        calculatePhotoHeight();
        addTouchColorFilters();
        editZip.setOnEditorActionListener(new android.widget.TextView.OnEditorActionListener() {

            public boolean onEditorAction(TextView textview, int i, KeyEvent keyevent)
            {
                if(i == 6)
                {
                    ((InputMethodManager)inputMethodManagerLazy.get()).hideSoftInputFromWindow(editZip.getWindowToken(), 0);
                    return true;
                } else
                {
                    return false;
                }
            }

            final AddPlaceFragment this$0;

            
            {
                this$0 = AddPlaceFragment.this;
                super();
            }
        }
);
        setContentShownNoAnimation(true);
    }

    protected void onCreateContent(Bundle bundle)
    {
        setContentView(0x7f03001b);
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        View view = layoutinflater.inflate(0x7f03001a, viewgroup, false);
        if(view != null)
            view.setBackgroundResource(0x7f0201ba);
        return view;
    }

    public void onDestroyView()
    {
        super.onDestroyView();
        if(locationClient.isConnected() || locationClient.isConnecting())
            releaseLocationClient();
    }

    public void onLocationChanged(final Location location)
    {
        releaseLocationClient();
        Crouton.cancelAllCroutons();
        Crouton.showText(getActivity(), getString(0x7f0c00d9), Style.INFO, container);
        GooglePlaces googleplaces = (GooglePlaces)googlePlacesLazy.get();
        Object aobj[] = new Object[2];
        aobj[0] = Double.valueOf(location.getLatitude());
        aobj[1] = Double.valueOf(location.getLongitude());
        AndroidObservable.fromFragment(this, googleplaces.geocode(String.format("%f,%f", aobj)).subscribeOn(Schedulers.io())).subscribe(new Observer() {

            public void onCompleted()
            {
            }

            public void onError(Throwable throwable)
            {
                Log.e(throwable);
                Crouton.cancelAllCroutons();
                Crouton.showText(getActivity(), getString(0x7f0c00ad), Style.ALERT, container);
            }

            public void onNext(com.roadtrippers.api.models.Address.Response response)
            {
                Crouton.cancelAllCroutons();
                Address address = response.results[0];
                editAddress.setText(address.getStreet());
                editCity.setText(address.getCity());
                editState.setText(address.getState());
                editZip.setText(address.getZip());
                fetchedLocation = location;
                watchForAddressTextChange();
            }

            public volatile void onNext(Object obj)
            {
                onNext((com.roadtrippers.api.models.Address.Response)obj);
            }

            final AddPlaceFragment this$0;
            final Location val$location;

            
            {
                this$0 = AddPlaceFragment.this;
                location = location1;
                super();
            }
        }
);
    }

    protected void onTargetImageChanged()
    {
        setColorFilter(addPlacePhoto, 0xffcccccc);
    }

    void onValidationSucceed()
    {
        setContentShown(false);
        Observable observable;
        if(fetchedLocation == null)
        {
            Log.d("Getting google Place");
            Object aobj[] = new Object[4];
            aobj[0] = SettingsFragment.trim(editAddress);
            aobj[1] = SettingsFragment.trim(editCity);
            aobj[2] = SettingsFragment.trim(editState);
            aobj[3] = SettingsFragment.trim(editZip);
            String s = String.format("%s, %s, %s, %s", aobj);
            observable = ((GooglePlaces)googlePlacesLazy.get()).fetchAddress(s).flatMap(createPoiWithAddress());
        } else
        {
            Log.d("Using fetched location");
            observable = createPoiObservable(new CreatePoiRequest(SettingsFragment.trim(addPlacePlaceName), SettingsFragment.trim(editAddress), SettingsFragment.trim(editCity), SettingsFragment.trim(editState), SettingsFragment.trim(editZip), fetchedLocation.getLatitude(), fetchedLocation.getLongitude(), "US", "approved"));
        }
        AndroidObservable.fromFragment(this, observable.subscribeOn(Schedulers.io())).subscribe(new Observer() {

            public void onCompleted()
            {
            }

            public void onError(Throwable throwable)
            {
                Log.e(throwable);
                setContentShown(true);
                if(throwable instanceof InvalidAddressException)
                {
                    (new android.app.AlertDialog.Builder(getActivity())).setTitle("Invalid Address").setMessage(getString(0x7f0c00b5)).setPositiveButton(0x7f0c00c8, null).show();
                    return;
                }
                if(throwable instanceof SuggestAddressException)
                {
                    showSuggestionsDialog();
                    return;
                }
                if(throwable instanceof IOException)
                {
                    Crouton.cancelAllCroutons();
                    Crouton.showText(getActivity(), getString(0x7f0c00c1), Style.ALERT, container);
                    return;
                } else
                {
                    throwable.printStackTrace();
                    Crouton.cancelAllCroutons();
                    Crouton.showText(getActivity(), getString(0x7f0c00e7), Style.ALERT, container);
                    return;
                }
            }

            public void onNext(Poi poi)
            {
                new JsonObject();
                File file = getCroppedFile();
                boolean flag;
                EasyJSONObject easyjsonobject;
                EasyJSONObject easyjsonobject1;
                Intent intent;
                if(file != null && file.exists() && file.length() > 0L)
                    flag = true;
                else
                    flag = false;
                easyjsonobject = new EasyJSONObject();
                easyjsonobject1 = new EasyJSONObject();
                easyjsonobject1.put("lat", poi.latitude);
                easyjsonobject1.put("lng", poi.longitude);
                easyjsonobject.put("added_photo", flag);
                easyjsonobject.put("location", easyjsonobject1);
                RTAnalytics.logEvent(getActivity(), 0x7f0c006d, 0x7f0c0025, easyjsonobject.toString());
                intent = new Intent();
                intent.putExtra("CREATED_POI", ((Serializer)jackson.get()).serialize(poi));
                getActivity().setResult(-1, intent);
                getActivity().finish();
            }

            public volatile void onNext(Object obj)
            {
                onNext((Poi)obj);
            }

            final AddPlaceFragment this$0;

            
            {
                this$0 = AddPlaceFragment.this;
                super();
            }
        }
);
    }

    void populateFromAddress(Address address)
    {
        editAddress.setText(address.getStreet());
        editCity.setText(address.getCity());
        editState.setText(address.getState());
        editZip.setText(address.getZip());
    }

    void save()
    {
        if(ActivityManager.isUserAMonkey())
        {
            return;
        } else
        {
            validateFields();
            return;
        }
    }

    void setUpLocationClient()
    {
        locationClient = new LocationClient(getActivity(), new com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks() {

            public void onConnected(Bundle bundle)
            {
                Location location = locationClient.getLastLocation();
                if(location == null)
                {
                    LocationRequest locationrequest = new LocationRequest();
                    locationrequest.setPriority(102);
                    locationrequest.setInterval(1L);
                    locationrequest.setNumUpdates(1);
                    locationClient.requestLocationUpdates(locationrequest, AddPlaceFragment.this);
                    Crouton.cancelAllCroutons();
                    Crouton.showText(getActivity(), getString(0x7f0c00b0), Style.INFO, container);
                    return;
                } else
                {
                    onLocationChanged(location);
                    return;
                }
            }

            public void onDisconnected()
            {
            }

            final AddPlaceFragment this$0;

            
            {
                this$0 = AddPlaceFragment.this;
                super();
            }
        }
, new com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener() {

            public void onConnectionFailed(ConnectionResult connectionresult)
            {
                Crouton.cancelAllCroutons();
                Croutons.showLocationUnavailableError(container, getActivity());
            }

            final AddPlaceFragment this$0;

            
            {
                this$0 = AddPlaceFragment.this;
                super();
            }
        }
);
    }

    void setUpValidation()
    {
        validator = new Validator(this);
        validator.setValidationListener(new com.mobsandgeeks.saripaar.Validator.ValidationListener() {

            public void onValidationFailed(View view, Rule rule)
            {
                if(view instanceof EditText)
                {
                    EditText edittext = (EditText)view;
                    edittext.setError(rule.getFailureMessage());
                    edittext.requestFocus();
                }
            }

            public void onValidationSucceeded()
            {
                onValidationSucceed();
            }

            final AddPlaceFragment this$0;

            
            {
                this$0 = AddPlaceFragment.this;
                super();
            }
        }
);
    }

    void showSuggestionsDialog()
    {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getActivity());
        builder.setTitle("Did you mean...");
        builder.setNegativeButton("Cancel", null);
        builder.setAdapter(new SuggestionsAdapter(getActivity(), results), new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i)
            {
                populateFromAddress(results[i]);
            }

            final AddPlaceFragment this$0;

            
            {
                this$0 = AddPlaceFragment.this;
                super();
            }
        }
);
        builder.show();
    }

    void validateFields()
    {
        EditText aedittext[] = new EditText[5];
        aedittext[0] = addPlacePlaceName;
        aedittext[1] = editAddress;
        aedittext[2] = editCity;
        aedittext[3] = editState;
        aedittext[4] = editZip;
        boolean flag = true;
        boolean flag1 = false;
        int i = aedittext.length;
        for(int j = 0; j < i; j++)
        {
            EditText edittext = aedittext[j];
            if(edittext.getText().toString().trim().length() != 0)
                continue;
            if(!flag1)
            {
                edittext.requestFocus();
                flag1 = true;
            }
            edittext.setError("This field is required");
            flag = false;
        }

        if(flag)
            onValidationSucceed();
    }

    void watchForAddressTextChange()
    {
        final EditText addressForms[] = new EditText[4];
        addressForms[0] = editAddress;
        addressForms[1] = editCity;
        addressForms[2] = editState;
        addressForms[3] = editZip;
        TextWatcher textwatcher = new TextWatcher() {

            public void afterTextChanged(Editable editable)
            {
                Log.d("Location invalidated");
                fetchedLocation = null;
                EditText aedittext[] = addressForms;
                int k = aedittext.length;
                for(int l = 0; l < k; l++)
                    aedittext[l].removeTextChangedListener(this);

            }

            public void beforeTextChanged(CharSequence charsequence, int k, int l, int i1)
            {
            }

            public void onTextChanged(CharSequence charsequence, int k, int l, int i1)
            {
            }

            final AddPlaceFragment this$0;
            final EditText val$addressForms[];

            
            {
                this$0 = AddPlaceFragment.this;
                addressForms = aedittext;
                super();
            }
        }
;
        int i = addressForms.length;
        for(int j = 0; j < i; j++)
            addressForms[j].addTextChangedListener(textwatcher);

    }

    public static final String CREATED_POI = "CREATED_POI";
    ImageView addPlacePhoto;
    EditText addPlacePlaceName;
    ImageView back;
    ViewGroup container;
    ImageView detectMyLocation;
    EditText editAddress;
    EditText editCity;
    EditText editState;
    EditText editZip;
    Location fetchedLocation;
    Lazy googlePlacesLazy;
    Lazy inputMethodManagerLazy;
    Lazy jackson;
    LocationClient locationClient;
    private Address results[];
    Lazy roadtrippersLazy;
    ImageView saveButton;
    Validator validator;




/*
    static Address[] access$102(AddPlaceFragment addplacefragment, Address aaddress[])
    {
        addplacefragment.results = aaddress;
        return aaddress;
    }

*/
}
