// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.util;

import android.text.TextUtils;
import com.roadtrippers.api.models.*;
import com.roadtrippers.api.requests.TripRequest;
import com.roadtrippers.api.requests.WaypointRequest;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.roadtrippers.util:
//            Log

public class TripManager
{

    public TripManager(Lazy lazy)
    {
        clientCount = 0;
        persistenceLazy = lazy;
    }

    public static Waypoint[] add(Waypoint awaypoint[], Waypoint waypoint)
    {
        Waypoint awaypoint1[];
        if(awaypoint == null)
            awaypoint1 = new Waypoint[1];
        else
            awaypoint1 = new Waypoint[1 + awaypoint.length];
        if(awaypoint != null)
            System.arraycopy(awaypoint, 0, awaypoint1, 0, awaypoint.length);
        awaypoint1[-1 + awaypoint1.length] = waypoint;
        return awaypoint1;
    }

    public void acquire()
    {
        clientCount = 1 + clientCount;
    }

    public void add(Poi poi)
    {
        pruneEmptyWaypoints();
        if(getCurrentTrip().waypoints.length < 40)
            getCurrentTrip().waypoints = add(getWaypoints(), Waypoint.from(poi));
    }

    public void createNewEmptyTrip()
    {
        Trip trip = new Trip();
        Waypoint awaypoint[] = new Waypoint[2];
        awaypoint[0] = new Waypoint();
        awaypoint[1] = new Waypoint();
        trip.waypoints = awaypoint;
        setCurrentTrip(trip);
    }

    public void createNewTripWith(Waypoint waypoint)
    {
        Trip trip = new Trip();
        trip.waypoints = (new Waypoint[] {
            waypoint
        });
        setCurrentTrip(trip);
    }

    public TripRequest createTripRequest()
    {
        TripRequest triprequest = new TripRequest();
        triprequest.waypoints = WaypointRequest.listFrom(currentTrip);
        return triprequest;
    }

    public void fill(TripRequest triprequest)
    {
        if(triprequest == null || getCurrentTrip() == null)
            return;
        if(TextUtils.isEmpty(triprequest.name))
            triprequest.setName("Unnamed Trip");
        triprequest.description = getCurrentTrip().description;
        triprequest.privacy_level = getCurrentTrip().privacy_level;
    }

    public Trip getCurrentTrip()
    {
        return currentTrip;
    }

    public Waypoint[] getWaypoints()
    {
        if(getCurrentTrip() == null)
            createNewEmptyTrip();
        return getCurrentTrip().waypoints;
    }

    public void pruneEmptyWaypoints()
    {
        ArrayList arraylist = new ArrayList();
        Waypoint awaypoint[] = getCurrentTrip().waypoints;
        int i = awaypoint.length;
        int j = 0;
        while(j < i) 
        {
            Waypoint waypoint = awaypoint[j];
            if(waypoint.id != null && waypoint.id.length() != 0 || waypoint.name != null && waypoint.name.length() != 0)
                arraylist.add(waypoint);
            j++;
        }
        getCurrentTrip().waypoints = new Waypoint[arraylist.size()];
        arraylist.toArray(getCurrentTrip().waypoints);
    }

    public void release()
    {
        clientCount = -1 + clientCount;
        if(clientCount == 0)
            setCurrentTrip(null);
    }

    public void replace(Waypoint waypoint, Waypoint waypoint1)
    {
        int i = 0;
        do
        {
label0:
            {
                if(i < getWaypoints().length)
                {
                    if(getWaypoints()[i] != waypoint)
                        break label0;
                    getWaypoints()[i] = waypoint1;
                }
                return;
            }
            i++;
        } while(true);
    }

    public void setCurrentTrip(Trip trip)
    {
        currentTrip = trip;
    }

    public void swap(int i, int j)
    {
        Waypoint awaypoint[] = getWaypoints();
        Waypoint waypoint = awaypoint[i];
        Waypoint awaypoint1[] = new Waypoint[awaypoint.length];
        if(i < j)
        {
            int i1 = 0;
            while(i1 < awaypoint.length) 
            {
                if(i1 < i)
                    awaypoint1[i1] = awaypoint[i1];
                else
                if(i1 < j)
                    awaypoint1[i1] = awaypoint[i1 + 1];
                else
                if(i1 == j)
                    awaypoint1[i1] = waypoint;
                else
                    awaypoint1[i1] = awaypoint[i1];
                i1++;
            }
        } else
        if(j < i)
        {
            int l = 0;
            while(l < awaypoint.length) 
            {
                if(l < j)
                    awaypoint1[l] = awaypoint[l];
                else
                if(l == j)
                    awaypoint1[l] = waypoint;
                else
                if(l <= i)
                    awaypoint1[l] = awaypoint[l - 1];
                else
                    awaypoint1[l] = awaypoint[l];
                l++;
            }
        } else
        {
            awaypoint1 = awaypoint;
        }
        for(int k = 0; k < awaypoint.length; k++)
        {
            Object aobj[] = new Object[2];
            aobj[0] = Integer.valueOf(k + 1);
            aobj[1] = awaypoint1[k].name;
            Log.d("NewWaypoints %d %s", aobj);
            getWaypoints()[k] = awaypoint1[k];
        }

    }

    int clientCount;
    Trip currentTrip;
    Lazy persistenceLazy;
}
