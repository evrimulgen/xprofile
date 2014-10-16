// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.maps.internal;

import android.location.Location;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CameraPositionCreator;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.CircleOptionsCreator;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.GroundOverlayOptionsCreator;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.MarkerOptionsCreator;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolygonOptionsCreator;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.PolylineOptionsCreator;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.android.gms.maps.model.TileOverlayOptionsCreator;
import com.google.android.gms.maps.model.internal.IPolylineDelegate;
import com.google.android.gms.maps.model.internal.b;
import com.google.android.gms.maps.model.internal.c;
import com.google.android.gms.maps.model.internal.d;
import com.google.android.gms.maps.model.internal.e;
import com.google.android.gms.maps.model.internal.f;

// Referenced classes of package com.google.android.gms.maps.internal:
//            b, IProjectionDelegate, IUiSettingsDelegate, d, 
//            ILocationSourceDelegate, e, f, h, 
//            i, j, k, l, 
//            m, n, o

public interface IGoogleMapDelegate
    extends IInterface
{
    public static abstract class a extends Binder
        implements IGoogleMapDelegate
    {

        public static IGoogleMapDelegate V(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            if(iinterface != null && (iinterface instanceof IGoogleMapDelegate))
                return (IGoogleMapDelegate)iinterface;
            else
                return new a(ibinder);
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel1, int j)
            throws RemoteException
        {
            switch(i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                CameraPosition cameraposition = getCameraPosition();
                parcel1.writeNoException();
                if(cameraposition != null)
                {
                    parcel1.writeInt(1);
                    cameraposition.writeToParcel(parcel1, 1);
                    return true;
                } else
                {
                    parcel1.writeInt(0);
                    return true;
                }

            case 2: // '\002'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                float f3 = getMaxZoomLevel();
                parcel1.writeNoException();
                parcel1.writeFloat(f3);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                float f2 = getMinZoomLevel();
                parcel1.writeNoException();
                parcel1.writeFloat(f2);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                moveCamera(com.google.android.gms.dynamic.b.a.G(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 5: // '\005'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                animateCamera(com.google.android.gms.dynamic.b.a.G(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 6: // '\006'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                animateCameraWithCallback(com.google.android.gms.dynamic.b.a.G(parcel.readStrongBinder()), b.a.T(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 7: // '\007'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                animateCameraWithDurationAndCallback(com.google.android.gms.dynamic.b.a.G(parcel.readStrongBinder()), parcel.readInt(), b.a.T(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 8: // '\b'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                stopAnimation();
                parcel1.writeNoException();
                return true;

            case 9: // '\t'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                PolylineOptions polylineoptions;
                IPolylineDelegate ipolylinedelegate;
                IBinder ibinder8;
                if(parcel.readInt() != 0)
                    polylineoptions = PolylineOptions.CREATOR.createFromParcel(parcel);
                else
                    polylineoptions = null;
                ipolylinedelegate = addPolyline(polylineoptions);
                parcel1.writeNoException();
                ibinder8 = null;
                if(ipolylinedelegate != null)
                    ibinder8 = ipolylinedelegate.asBinder();
                parcel1.writeStrongBinder(ibinder8);
                return true;

            case 10: // '\n'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                PolygonOptions polygonoptions;
                e e1;
                IBinder ibinder7;
                if(parcel.readInt() != 0)
                    polygonoptions = PolygonOptions.CREATOR.createFromParcel(parcel);
                else
                    polygonoptions = null;
                e1 = addPolygon(polygonoptions);
                parcel1.writeNoException();
                ibinder7 = null;
                if(e1 != null)
                    ibinder7 = e1.asBinder();
                parcel1.writeStrongBinder(ibinder7);
                return true;

            case 11: // '\013'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                MarkerOptions markeroptions;
                d d1;
                IBinder ibinder6;
                if(parcel.readInt() != 0)
                    markeroptions = MarkerOptions.CREATOR.createFromParcel(parcel);
                else
                    markeroptions = null;
                d1 = addMarker(markeroptions);
                parcel1.writeNoException();
                ibinder6 = null;
                if(d1 != null)
                    ibinder6 = d1.asBinder();
                parcel1.writeStrongBinder(ibinder6);
                return true;

            case 12: // '\f'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                GroundOverlayOptions groundoverlayoptions;
                c c1;
                IBinder ibinder5;
                if(parcel.readInt() != 0)
                    groundoverlayoptions = GroundOverlayOptions.CREATOR.createFromParcel(parcel);
                else
                    groundoverlayoptions = null;
                c1 = addGroundOverlay(groundoverlayoptions);
                parcel1.writeNoException();
                ibinder5 = null;
                if(c1 != null)
                    ibinder5 = c1.asBinder();
                parcel1.writeStrongBinder(ibinder5);
                return true;

            case 13: // '\r'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                TileOverlayOptions tileoverlayoptions;
                f f1;
                IBinder ibinder4;
                if(parcel.readInt() != 0)
                    tileoverlayoptions = TileOverlayOptions.CREATOR.createFromParcel(parcel);
                else
                    tileoverlayoptions = null;
                f1 = addTileOverlay(tileoverlayoptions);
                parcel1.writeNoException();
                ibinder4 = null;
                if(f1 != null)
                    ibinder4 = f1.asBinder();
                parcel1.writeStrongBinder(ibinder4);
                return true;

            case 14: // '\016'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                clear();
                parcel1.writeNoException();
                return true;

            case 15: // '\017'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                int k2 = getMapType();
                parcel1.writeNoException();
                parcel1.writeInt(k2);
                return true;

            case 16: // '\020'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                setMapType(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 17: // '\021'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                boolean flag8 = isTrafficEnabled();
                parcel1.writeNoException();
                int j2 = 0;
                if(flag8)
                    j2 = 1;
                parcel1.writeInt(j2);
                return true;

            case 18: // '\022'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                int i2 = parcel.readInt();
                boolean flag7 = false;
                if(i2 != 0)
                    flag7 = true;
                setTrafficEnabled(flag7);
                parcel1.writeNoException();
                return true;

            case 19: // '\023'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                boolean flag6 = isIndoorEnabled();
                parcel1.writeNoException();
                int l1 = 0;
                if(flag6)
                    l1 = 1;
                parcel1.writeInt(l1);
                return true;

            case 20: // '\024'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                boolean flag4;
                boolean flag5;
                int k1;
                if(parcel.readInt() != 0)
                    flag4 = true;
                else
                    flag4 = false;
                flag5 = setIndoorEnabled(flag4);
                parcel1.writeNoException();
                k1 = 0;
                if(flag5)
                    k1 = 1;
                parcel1.writeInt(k1);
                return true;

            case 21: // '\025'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                boolean flag3 = isMyLocationEnabled();
                parcel1.writeNoException();
                int j1 = 0;
                if(flag3)
                    j1 = 1;
                parcel1.writeInt(j1);
                return true;

            case 22: // '\026'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                int i1 = parcel.readInt();
                boolean flag2 = false;
                if(i1 != 0)
                    flag2 = true;
                setMyLocationEnabled(flag2);
                parcel1.writeNoException();
                return true;

            case 23: // '\027'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                Location location = getMyLocation();
                parcel1.writeNoException();
                if(location != null)
                {
                    parcel1.writeInt(1);
                    location.writeToParcel(parcel1, 1);
                    return true;
                } else
                {
                    parcel1.writeInt(0);
                    return true;
                }

            case 24: // '\030'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                setLocationSource(ILocationSourceDelegate.a.X(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 25: // '\031'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                IUiSettingsDelegate iuisettingsdelegate = getUiSettings();
                parcel1.writeNoException();
                IBinder ibinder3 = null;
                if(iuisettingsdelegate != null)
                    ibinder3 = iuisettingsdelegate.asBinder();
                parcel1.writeStrongBinder(ibinder3);
                return true;

            case 26: // '\032'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                IProjectionDelegate iprojectiondelegate = getProjection();
                parcel1.writeNoException();
                IBinder ibinder2 = null;
                if(iprojectiondelegate != null)
                    ibinder2 = iprojectiondelegate.asBinder();
                parcel1.writeStrongBinder(ibinder2);
                return true;

            case 27: // '\033'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                setOnCameraChangeListener(e.a.aa(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 28: // '\034'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                setOnMapClickListener(h.a.ad(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 29: // '\035'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                setOnMapLongClickListener(com.google.android.gms.maps.internal.j.a.af(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 30: // '\036'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                setOnMarkerClickListener(com.google.android.gms.maps.internal.k.a.ag(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 31: // '\037'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                setOnMarkerDragListener(com.google.android.gms.maps.internal.l.a.ah(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 32: // ' '
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                setOnInfoWindowClickListener(f.a.ab(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 33: // '!'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                setInfoWindowAdapter(d.a.W(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 34: // '"'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                com.google.android.gms.dynamic.b b2 = getTestingHelper();
                parcel1.writeNoException();
                IBinder ibinder1 = null;
                if(b2 != null)
                    ibinder1 = b2.asBinder();
                parcel1.writeStrongBinder(ibinder1);
                return true;

            case 35: // '#'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                CircleOptions circleoptions;
                b b1;
                IBinder ibinder;
                if(parcel.readInt() != 0)
                    circleoptions = CircleOptions.CREATOR.createFromParcel(parcel);
                else
                    circleoptions = null;
                b1 = addCircle(circleoptions);
                parcel1.writeNoException();
                ibinder = null;
                if(b1 != null)
                    ibinder = b1.asBinder();
                parcel1.writeStrongBinder(ibinder);
                return true;

            case 36: // '$'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                setOnMyLocationChangeListener(n.a.aj(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 37: // '%'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                setOnMyLocationButtonClickListener(m.a.ai(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 38: // '&'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                snapshot(o.a.al(parcel.readStrongBinder()), com.google.android.gms.dynamic.b.a.G(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 39: // '\''
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                setPadding(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 40: // '('
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                boolean flag1 = isBuildingsEnabled();
                parcel1.writeNoException();
                int l = 0;
                if(flag1)
                    l = 1;
                parcel1.writeInt(l);
                return true;

            case 41: // ')'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                int k = parcel.readInt();
                boolean flag = false;
                if(k != 0)
                    flag = true;
                setBuildingsEnabled(flag);
                parcel1.writeNoException();
                return true;

            case 42: // '*'
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                setOnMapLoadedCallback(com.google.android.gms.maps.internal.i.a.ae(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;
            }
        }
    }

    private static class a.a
        implements IGoogleMapDelegate
    {

        public b addCircle(CircleOptions circleoptions)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            if(circleoptions == null)
                break MISSING_BLOCK_LABEL_68;
            parcel.writeInt(1);
            circleoptions.writeToParcel(parcel, 0);
_L1:
            b b1;
            ky.transact(35, parcel, parcel1, 0);
            parcel1.readException();
            b1 = com.google.android.gms.maps.model.internal.b.a.ao(parcel1.readStrongBinder());
            parcel1.recycle();
            parcel.recycle();
            return b1;
            parcel.writeInt(0);
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public c addGroundOverlay(GroundOverlayOptions groundoverlayoptions)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            if(groundoverlayoptions == null)
                break MISSING_BLOCK_LABEL_68;
            parcel.writeInt(1);
            groundoverlayoptions.writeToParcel(parcel, 0);
_L1:
            c c;
            ky.transact(12, parcel, parcel1, 0);
            parcel1.readException();
            c = com.google.android.gms.maps.model.internal.c.a.ap(parcel1.readStrongBinder());
            parcel1.recycle();
            parcel.recycle();
            return c;
            parcel.writeInt(0);
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public d addMarker(MarkerOptions markeroptions)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            if(markeroptions == null)
                break MISSING_BLOCK_LABEL_68;
            parcel.writeInt(1);
            markeroptions.writeToParcel(parcel, 0);
_L1:
            d d1;
            ky.transact(11, parcel, parcel1, 0);
            parcel1.readException();
            d1 = com.google.android.gms.maps.model.internal.d.a.aq(parcel1.readStrongBinder());
            parcel1.recycle();
            parcel.recycle();
            return d1;
            parcel.writeInt(0);
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public e addPolygon(PolygonOptions polygonoptions)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            if(polygonoptions == null)
                break MISSING_BLOCK_LABEL_68;
            parcel.writeInt(1);
            polygonoptions.writeToParcel(parcel, 0);
_L1:
            e e1;
            ky.transact(10, parcel, parcel1, 0);
            parcel1.readException();
            e1 = com.google.android.gms.maps.model.internal.e.a.ar(parcel1.readStrongBinder());
            parcel1.recycle();
            parcel.recycle();
            return e1;
            parcel.writeInt(0);
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public IPolylineDelegate addPolyline(PolylineOptions polylineoptions)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            if(polylineoptions == null)
                break MISSING_BLOCK_LABEL_68;
            parcel.writeInt(1);
            polylineoptions.writeToParcel(parcel, 0);
_L1:
            IPolylineDelegate ipolylinedelegate;
            ky.transact(9, parcel, parcel1, 0);
            parcel1.readException();
            ipolylinedelegate = com.google.android.gms.maps.model.internal.IPolylineDelegate.a.as(parcel1.readStrongBinder());
            parcel1.recycle();
            parcel.recycle();
            return ipolylinedelegate;
            parcel.writeInt(0);
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public f addTileOverlay(TileOverlayOptions tileoverlayoptions)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            if(tileoverlayoptions == null)
                break MISSING_BLOCK_LABEL_68;
            parcel.writeInt(1);
            tileoverlayoptions.writeToParcel(parcel, 0);
_L1:
            f f1;
            ky.transact(13, parcel, parcel1, 0);
            parcel1.readException();
            f1 = com.google.android.gms.maps.model.internal.f.a.at(parcel1.readStrongBinder());
            parcel1.recycle();
            parcel.recycle();
            return f1;
            parcel.writeInt(0);
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void animateCamera(com.google.android.gms.dynamic.b b1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            if(b1 == null)
                break MISSING_BLOCK_LABEL_59;
            IBinder ibinder = b1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            ky.transact(5, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void animateCameraWithCallback(com.google.android.gms.dynamic.b b1, com.google.android.gms.maps.internal.b b2)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            if(b1 == null)
                break MISSING_BLOCK_LABEL_85;
            IBinder ibinder = b1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            IBinder ibinder1;
            ibinder1 = null;
            if(b2 == null)
                break MISSING_BLOCK_LABEL_48;
            ibinder1 = b2.asBinder();
            parcel.writeStrongBinder(ibinder1);
            ky.transact(6, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void animateCameraWithDurationAndCallback(com.google.android.gms.dynamic.b b1, int i1, com.google.android.gms.maps.internal.b b2)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            if(b1 == null)
                break MISSING_BLOCK_LABEL_97;
            IBinder ibinder = b1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i1);
            IBinder ibinder1;
            ibinder1 = null;
            if(b2 == null)
                break MISSING_BLOCK_LABEL_57;
            ibinder1 = b2.asBinder();
            parcel.writeStrongBinder(ibinder1);
            ky.transact(7, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public IBinder asBinder()
        {
            return ky;
        }

        public void clear()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            ky.transact(14, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public CameraPosition getCameraPosition()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            ky.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            CameraPosition cameraposition1 = CameraPosition.CREATOR.createFromParcel(parcel1);
            CameraPosition cameraposition = cameraposition1;
_L4:
            parcel1.recycle();
            parcel.recycle();
            return cameraposition;
_L2:
            cameraposition = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public int getMapType()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i1;
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            ky.transact(15, parcel, parcel1, 0);
            parcel1.readException();
            i1 = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i1;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public float getMaxZoomLevel()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            float f1;
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            ky.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            f1 = parcel1.readFloat();
            parcel1.recycle();
            parcel.recycle();
            return f1;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public float getMinZoomLevel()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            float f1;
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            ky.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            f1 = parcel1.readFloat();
            parcel1.recycle();
            parcel.recycle();
            return f1;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public Location getMyLocation()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            ky.transact(23, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            Location location = (Location)Location.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return location;
_L2:
            location = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public IProjectionDelegate getProjection()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            IProjectionDelegate iprojectiondelegate;
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            ky.transact(26, parcel, parcel1, 0);
            parcel1.readException();
            iprojectiondelegate = IProjectionDelegate.a.ak(parcel1.readStrongBinder());
            parcel1.recycle();
            parcel.recycle();
            return iprojectiondelegate;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public com.google.android.gms.dynamic.b getTestingHelper()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            com.google.android.gms.dynamic.b b1;
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            ky.transact(34, parcel, parcel1, 0);
            parcel1.readException();
            b1 = com.google.android.gms.dynamic.b.a.G(parcel1.readStrongBinder());
            parcel1.recycle();
            parcel.recycle();
            return b1;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public IUiSettingsDelegate getUiSettings()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            IUiSettingsDelegate iuisettingsdelegate;
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            ky.transact(25, parcel, parcel1, 0);
            parcel1.readException();
            iuisettingsdelegate = IUiSettingsDelegate.a.am(parcel1.readStrongBinder());
            parcel1.recycle();
            parcel.recycle();
            return iuisettingsdelegate;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean isBuildingsEnabled()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i1;
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            ky.transact(40, parcel, parcel1, 0);
            parcel1.readException();
            i1 = parcel1.readInt();
            boolean flag = false;
            if(i1 != 0)
                flag = true;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean isIndoorEnabled()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i1;
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            ky.transact(19, parcel, parcel1, 0);
            parcel1.readException();
            i1 = parcel1.readInt();
            boolean flag = false;
            if(i1 != 0)
                flag = true;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean isMyLocationEnabled()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i1;
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            ky.transact(21, parcel, parcel1, 0);
            parcel1.readException();
            i1 = parcel1.readInt();
            boolean flag = false;
            if(i1 != 0)
                flag = true;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean isTrafficEnabled()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i1;
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            ky.transact(17, parcel, parcel1, 0);
            parcel1.readException();
            i1 = parcel1.readInt();
            boolean flag = false;
            if(i1 != 0)
                flag = true;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void moveCamera(com.google.android.gms.dynamic.b b1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            if(b1 == null)
                break MISSING_BLOCK_LABEL_59;
            IBinder ibinder = b1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            ky.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void setBuildingsEnabled(boolean flag)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            int i1;
            i1 = 0;
            if(flag)
                i1 = 1;
            parcel.writeInt(i1);
            ky.transact(41, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean setIndoorEnabled(boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = true;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            int i1;
            int j1;
            if(flag)
                i1 = ((flag1) ? 1 : 0);
            else
                i1 = 0;
            parcel.writeInt(i1);
            ky.transact(20, parcel, parcel1, 0);
            parcel1.readException();
            j1 = parcel1.readInt();
            if(j1 == 0)
                flag1 = false;
            parcel1.recycle();
            parcel.recycle();
            return flag1;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void setInfoWindowAdapter(com.google.android.gms.maps.internal.d d1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            if(d1 == null)
                break MISSING_BLOCK_LABEL_60;
            IBinder ibinder = d1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            ky.transact(33, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void setLocationSource(ILocationSourceDelegate ilocationsourcedelegate)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            if(ilocationsourcedelegate == null)
                break MISSING_BLOCK_LABEL_60;
            IBinder ibinder = ilocationsourcedelegate.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            ky.transact(24, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void setMapType(int i1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            parcel.writeInt(i1);
            ky.transact(16, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void setMyLocationEnabled(boolean flag)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            int i1;
            i1 = 0;
            if(flag)
                i1 = 1;
            parcel.writeInt(i1);
            ky.transact(22, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void setOnCameraChangeListener(com.google.android.gms.maps.internal.e e1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            if(e1 == null)
                break MISSING_BLOCK_LABEL_60;
            IBinder ibinder = e1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            ky.transact(27, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void setOnInfoWindowClickListener(com.google.android.gms.maps.internal.f f1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            if(f1 == null)
                break MISSING_BLOCK_LABEL_60;
            IBinder ibinder = f1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            ky.transact(32, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void setOnMapClickListener(h h1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            if(h1 == null)
                break MISSING_BLOCK_LABEL_60;
            IBinder ibinder = h1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            ky.transact(28, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void setOnMapLoadedCallback(i i1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            if(i1 == null)
                break MISSING_BLOCK_LABEL_60;
            IBinder ibinder = i1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            ky.transact(42, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void setOnMapLongClickListener(j j1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            if(j1 == null)
                break MISSING_BLOCK_LABEL_60;
            IBinder ibinder = j1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            ky.transact(29, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void setOnMarkerClickListener(k k1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            if(k1 == null)
                break MISSING_BLOCK_LABEL_60;
            IBinder ibinder = k1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            ky.transact(30, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void setOnMarkerDragListener(l l1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            if(l1 == null)
                break MISSING_BLOCK_LABEL_60;
            IBinder ibinder = l1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            ky.transact(31, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void setOnMyLocationButtonClickListener(m m1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            if(m1 == null)
                break MISSING_BLOCK_LABEL_60;
            IBinder ibinder = m1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            ky.transact(37, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void setOnMyLocationChangeListener(n n1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            if(n1 == null)
                break MISSING_BLOCK_LABEL_60;
            IBinder ibinder = n1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            ky.transact(36, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void setPadding(int i1, int j1, int k1, int l1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            parcel.writeInt(i1);
            parcel.writeInt(j1);
            parcel.writeInt(k1);
            parcel.writeInt(l1);
            ky.transact(39, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void setTrafficEnabled(boolean flag)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            int i1;
            i1 = 0;
            if(flag)
                i1 = 1;
            parcel.writeInt(i1);
            ky.transact(18, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void snapshot(o o1, com.google.android.gms.dynamic.b b1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            if(o1 == null)
                break MISSING_BLOCK_LABEL_85;
            IBinder ibinder = o1.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            IBinder ibinder1;
            ibinder1 = null;
            if(b1 == null)
                break MISSING_BLOCK_LABEL_48;
            ibinder1 = b1.asBinder();
            parcel.writeStrongBinder(ibinder1);
            ky.transact(38, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder = null;
              goto _L1
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void stopAnimation()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            ky.transact(8, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        private IBinder ky;

        a.a(IBinder ibinder)
        {
            ky = ibinder;
        }
    }


    public abstract b addCircle(CircleOptions circleoptions)
        throws RemoteException;

    public abstract c addGroundOverlay(GroundOverlayOptions groundoverlayoptions)
        throws RemoteException;

    public abstract d addMarker(MarkerOptions markeroptions)
        throws RemoteException;

    public abstract e addPolygon(PolygonOptions polygonoptions)
        throws RemoteException;

    public abstract IPolylineDelegate addPolyline(PolylineOptions polylineoptions)
        throws RemoteException;

    public abstract f addTileOverlay(TileOverlayOptions tileoverlayoptions)
        throws RemoteException;

    public abstract void animateCamera(com.google.android.gms.dynamic.b b)
        throws RemoteException;

    public abstract void animateCameraWithCallback(com.google.android.gms.dynamic.b b, com.google.android.gms.maps.internal.b b1)
        throws RemoteException;

    public abstract void animateCameraWithDurationAndCallback(com.google.android.gms.dynamic.b b, int i, com.google.android.gms.maps.internal.b b1)
        throws RemoteException;

    public abstract void clear()
        throws RemoteException;

    public abstract CameraPosition getCameraPosition()
        throws RemoteException;

    public abstract int getMapType()
        throws RemoteException;

    public abstract float getMaxZoomLevel()
        throws RemoteException;

    public abstract float getMinZoomLevel()
        throws RemoteException;

    public abstract Location getMyLocation()
        throws RemoteException;

    public abstract IProjectionDelegate getProjection()
        throws RemoteException;

    public abstract com.google.android.gms.dynamic.b getTestingHelper()
        throws RemoteException;

    public abstract IUiSettingsDelegate getUiSettings()
        throws RemoteException;

    public abstract boolean isBuildingsEnabled()
        throws RemoteException;

    public abstract boolean isIndoorEnabled()
        throws RemoteException;

    public abstract boolean isMyLocationEnabled()
        throws RemoteException;

    public abstract boolean isTrafficEnabled()
        throws RemoteException;

    public abstract void moveCamera(com.google.android.gms.dynamic.b b)
        throws RemoteException;

    public abstract void setBuildingsEnabled(boolean flag)
        throws RemoteException;

    public abstract boolean setIndoorEnabled(boolean flag)
        throws RemoteException;

    public abstract void setInfoWindowAdapter(com.google.android.gms.maps.internal.d d)
        throws RemoteException;

    public abstract void setLocationSource(ILocationSourceDelegate ilocationsourcedelegate)
        throws RemoteException;

    public abstract void setMapType(int i)
        throws RemoteException;

    public abstract void setMyLocationEnabled(boolean flag)
        throws RemoteException;

    public abstract void setOnCameraChangeListener(com.google.android.gms.maps.internal.e e)
        throws RemoteException;

    public abstract void setOnInfoWindowClickListener(com.google.android.gms.maps.internal.f f)
        throws RemoteException;

    public abstract void setOnMapClickListener(h h)
        throws RemoteException;

    public abstract void setOnMapLoadedCallback(i i)
        throws RemoteException;

    public abstract void setOnMapLongClickListener(j j)
        throws RemoteException;

    public abstract void setOnMarkerClickListener(k k)
        throws RemoteException;

    public abstract void setOnMarkerDragListener(l l)
        throws RemoteException;

    public abstract void setOnMyLocationButtonClickListener(m m)
        throws RemoteException;

    public abstract void setOnMyLocationChangeListener(n n)
        throws RemoteException;

    public abstract void setPadding(int i, int j, int k, int l)
        throws RemoteException;

    public abstract void setTrafficEnabled(boolean flag)
        throws RemoteException;

    public abstract void snapshot(o o, com.google.android.gms.dynamic.b b)
        throws RemoteException;

    public abstract void stopAnimation()
        throws RemoteException;
}
