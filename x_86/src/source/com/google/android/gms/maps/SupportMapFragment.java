// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.maps;

import android.app.Activity;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.dynamic.LifecycleDelegate;
import com.google.android.gms.dynamic.c;
import com.google.android.gms.dynamic.d;
import com.google.android.gms.internal.er;
import com.google.android.gms.maps.internal.IGoogleMapDelegate;
import com.google.android.gms.maps.internal.IMapFragmentDelegate;
import com.google.android.gms.maps.internal.p;
import com.google.android.gms.maps.internal.q;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.tracing.TraceMachine;

// Referenced classes of package com.google.android.gms.maps:
//            GoogleMap, GoogleMapOptions, MapsInitializer

public class SupportMapFragment extends Fragment
    implements TraceFieldInterface
{
    static class a
        implements LifecycleDelegate
    {

        public IMapFragmentDelegate gV()
        {
            return Pp;
        }

        public void onCreate(Bundle bundle)
        {
            if(bundle != null)
                break MISSING_BLOCK_LABEL_12;
            bundle = new Bundle();
            Bundle bundle1 = Pz.getArguments();
            if(bundle1 == null)
                break MISSING_BLOCK_LABEL_45;
            if(bundle1.containsKey("MapOptions"))
                p.a(bundle, "MapOptions", bundle1.getParcelable("MapOptions"));
            Pp.onCreate(bundle);
            return;
            RemoteException remoteexception;
            remoteexception;
            throw new RuntimeRemoteException(remoteexception);
        }

        public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
        {
            com.google.android.gms.dynamic.b b1;
            try
            {
                b1 = Pp.onCreateView(c.h(layoutinflater), c.h(viewgroup), bundle);
            }
            catch(RemoteException remoteexception)
            {
                throw new RuntimeRemoteException(remoteexception);
            }
            return (View)c.b(b1);
        }

        public void onDestroy()
        {
            try
            {
                Pp.onDestroy();
                return;
            }
            catch(RemoteException remoteexception)
            {
                throw new RuntimeRemoteException(remoteexception);
            }
        }

        public void onDestroyView()
        {
            try
            {
                Pp.onDestroyView();
                return;
            }
            catch(RemoteException remoteexception)
            {
                throw new RuntimeRemoteException(remoteexception);
            }
        }

        public void onInflate(Activity activity, Bundle bundle, Bundle bundle1)
        {
            GoogleMapOptions googlemapoptions = (GoogleMapOptions)bundle.getParcelable("MapOptions");
            try
            {
                Pp.onInflate(c.h(activity), googlemapoptions, bundle1);
                return;
            }
            catch(RemoteException remoteexception)
            {
                throw new RuntimeRemoteException(remoteexception);
            }
        }

        public void onLowMemory()
        {
            try
            {
                Pp.onLowMemory();
                return;
            }
            catch(RemoteException remoteexception)
            {
                throw new RuntimeRemoteException(remoteexception);
            }
        }

        public void onPause()
        {
            try
            {
                Pp.onPause();
                return;
            }
            catch(RemoteException remoteexception)
            {
                throw new RuntimeRemoteException(remoteexception);
            }
        }

        public void onResume()
        {
            try
            {
                Pp.onResume();
                return;
            }
            catch(RemoteException remoteexception)
            {
                throw new RuntimeRemoteException(remoteexception);
            }
        }

        public void onSaveInstanceState(Bundle bundle)
        {
            try
            {
                Pp.onSaveInstanceState(bundle);
                return;
            }
            catch(RemoteException remoteexception)
            {
                throw new RuntimeRemoteException(remoteexception);
            }
        }

        public void onStart()
        {
        }

        public void onStop()
        {
        }

        private final IMapFragmentDelegate Pp;
        private final Fragment Pz;

        public a(Fragment fragment, IMapFragmentDelegate imapfragmentdelegate)
        {
            Pp = (IMapFragmentDelegate)er.f(imapfragmentdelegate);
            Pz = (Fragment)er.f(fragment);
        }
    }

    static class b extends com.google.android.gms.dynamic.a
    {

        static void a(b b1, Activity activity)
        {
            b1.setActivity(activity);
        }

        private void setActivity(Activity activity)
        {
            nd = activity;
            gW();
        }

        protected void a(d d1)
        {
            Pq = d1;
            gW();
        }

        public void gW()
        {
            if(nd == null || Pq == null || fj() != null)
                break MISSING_BLOCK_LABEL_72;
            MapsInitializer.initialize(nd);
            IMapFragmentDelegate imapfragmentdelegate = q.A(nd).f(c.h(nd));
            Pq.a(new a(Pz, imapfragmentdelegate));
            return;
            RemoteException remoteexception;
            remoteexception;
            throw new RuntimeRemoteException(remoteexception);
            GooglePlayServicesNotAvailableException googleplayservicesnotavailableexception;
            googleplayservicesnotavailableexception;
        }

        protected d Pq;
        private final Fragment Pz;
        private Activity nd;

        b(Fragment fragment)
        {
            Pz = fragment;
        }
    }


    public SupportMapFragment()
    {
    }

    public static SupportMapFragment newInstance()
    {
        return new SupportMapFragment();
    }

    public static SupportMapFragment newInstance(GoogleMapOptions googlemapoptions)
    {
        SupportMapFragment supportmapfragment = new SupportMapFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("MapOptions", googlemapoptions);
        supportmapfragment.setArguments(bundle);
        return supportmapfragment;
    }

    protected IMapFragmentDelegate gV()
    {
        Py.gW();
        if(Py.fj() == null)
            return null;
        else
            return ((a)Py.fj()).gV();
    }

    public final GoogleMap getMap()
    {
        IMapFragmentDelegate imapfragmentdelegate = gV();
        if(imapfragmentdelegate != null)
        {
            IGoogleMapDelegate igooglemapdelegate;
            try
            {
                igooglemapdelegate = imapfragmentdelegate.getMap();
            }
            catch(RemoteException remoteexception)
            {
                throw new RuntimeRemoteException(remoteexception);
            }
            if(igooglemapdelegate != null)
            {
                if(Pn == null || Pn.gM().asBinder() != igooglemapdelegate.asBinder())
                    Pn = new GoogleMap(igooglemapdelegate);
                return Pn;
            }
        }
        return null;
    }

    public void onActivityCreated(Bundle bundle)
    {
        if(bundle != null)
            bundle.setClassLoader(com/google/android/gms/maps/SupportMapFragment.getClassLoader());
        super.onActivityCreated(bundle);
    }

    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        b.a(Py, activity);
    }

    public void onCreate(Bundle bundle)
    {
        TraceMachine.startTracing("SupportMapFragment");
        TraceMachine.enterMethod(_nr_trace, "SupportMapFragment#onCreate", null);
_L1:
        super.onCreate(bundle);
        Py.onCreate(bundle);
        TraceMachine.exitMethod();
        return;
        NoSuchFieldError nosuchfielderror;
        nosuchfielderror;
        TraceMachine.enterMethod(null, "SupportMapFragment#onCreate", null);
          goto _L1
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        TraceMachine.enterMethod(_nr_trace, "SupportMapFragment#onCreateView", null);
_L1:
        View view = Py.onCreateView(layoutinflater, viewgroup, bundle);
        TraceMachine.exitMethod();
        return view;
        NoSuchFieldError nosuchfielderror;
        nosuchfielderror;
        TraceMachine.enterMethod(null, "SupportMapFragment#onCreateView", null);
          goto _L1
    }

    public void onDestroy()
    {
        Py.onDestroy();
        super.onDestroy();
    }

    public void onDestroyView()
    {
        Py.onDestroyView();
        super.onDestroyView();
    }

    public void onInflate(Activity activity, AttributeSet attributeset, Bundle bundle)
    {
        super.onInflate(activity, attributeset, bundle);
        b.a(Py, activity);
        GoogleMapOptions googlemapoptions = GoogleMapOptions.createFromAttributes(activity, attributeset);
        Bundle bundle1 = new Bundle();
        bundle1.putParcelable("MapOptions", googlemapoptions);
        Py.onInflate(activity, bundle1, bundle);
    }

    public void onLowMemory()
    {
        Py.onLowMemory();
        super.onLowMemory();
    }

    public void onPause()
    {
        Py.onPause();
        super.onPause();
    }

    public void onResume()
    {
        super.onResume();
        Py.onResume();
    }

    public void onSaveInstanceState(Bundle bundle)
    {
        if(bundle != null)
            bundle.setClassLoader(com/google/android/gms/maps/SupportMapFragment.getClassLoader());
        super.onSaveInstanceState(bundle);
        Py.onSaveInstanceState(bundle);
    }

    protected void onStart()
    {
        super.onStart();
        ApplicationStateMonitor.getInstance().activityStarted();
    }

    protected void onStop()
    {
        super.onStop();
        ApplicationStateMonitor.getInstance().activityStopped();
    }

    public void setArguments(Bundle bundle)
    {
        super.setArguments(bundle);
    }

    private GoogleMap Pn;
    private final b Py = new b(this);
}
