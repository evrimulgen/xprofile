// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.api;

import com.roadtrippers.api.models.AutocompleteResponse;
import rx.Observable;

public interface GooglePlaces
{

    public abstract AutocompleteResponse autocomplete(String s, String s1);

    public abstract Observable details(String s, String s1);

    public abstract Observable fetchAddress(String s);

    public abstract Observable geocode(String s);

    public abstract com.roadtrippers.api.models.Route.Response getDirections(String s, String s1, String s2, String s3);

    public static final String KEY = "AIzaSyBm8Rs4h4xvSJ1AD5O8Aej-h3KUkL2DV60";
    public static final String SECRET_KEY = "hWSLa7CP4NEjmxJ3l-N8lYCODl4=";
    public static final String SERVER = "https://maps.googleapis.com";
}
