// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.events;

import com.roadtrippers.api.models.RTAutocompleteSuggestion;
import com.roadtrippers.api.models.Waypoint;

public class AutocompleteSelectedEvent
{

    public AutocompleteSelectedEvent(RTAutocompleteSuggestion rtautocompletesuggestion, Waypoint waypoint1)
    {
        suggestion = rtautocompletesuggestion;
        waypoint = waypoint1;
    }

    public RTAutocompleteSuggestion suggestion;
    public Waypoint waypoint;
}
