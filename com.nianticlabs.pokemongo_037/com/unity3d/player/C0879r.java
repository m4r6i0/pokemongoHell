package com.unity3d.player;

import android.content.Context;
import android.hardware.GeomagneticField;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import java.util.List;

/* renamed from: com.unity3d.player.r */
final class C0879r implements LocationListener {
    private final Context f208a;
    private final UnityPlayer f209b;
    private Location f210c;
    private float f211d;
    private boolean f212e;
    private int f213f;
    private boolean f214g;
    private int f215h;

    protected C0879r(Context context, UnityPlayer unityPlayer) {
        this.f211d = 0.0f;
        this.f212e = false;
        this.f213f = 0;
        this.f214g = false;
        this.f215h = 0;
        this.f208a = context;
        this.f209b = unityPlayer;
    }

    private void m164a(int i) {
        this.f215h = i;
        this.f209b.nativeSetLocationStatus(i);
    }

    private void m165a(Location location) {
        if (location != null && C0879r.m166a(location, this.f210c)) {
            this.f210c = location;
            this.f209b.nativeSetLocation((float) location.getLatitude(), (float) location.getLongitude(), (float) location.getAltitude(), location.getAccuracy(), ((double) location.getTime()) / 1000.0d, new GeomagneticField((float) this.f210c.getLatitude(), (float) this.f210c.getLongitude(), (float) this.f210c.getAltitude(), this.f210c.getTime()).getDeclination());
        }
    }

    private static boolean m166a(Location location, Location location2) {
        if (location2 == null) {
            return true;
        }
        long time = location.getTime() - location2.getTime();
        boolean z = time > 120000;
        boolean z2 = time < -120000;
        boolean z3 = time > 0;
        if (z) {
            return true;
        }
        if (z2) {
            return false;
        }
        int accuracy = (int) (location.getAccuracy() - location2.getAccuracy());
        return !(accuracy < 0) ? (!z3 || (accuracy > 0)) ? z3 && ((accuracy > 200 ? 1 : 0) | (location.getAccuracy() == 0.0f ? 1 : 0)) == 0 && C0879r.m167a(location.getProvider(), location2.getProvider()) : true : true;
    }

    private static boolean m167a(String str, String str2) {
        return str == null ? str2 == null : str.equals(str2);
    }

    public final void m168a(float f) {
        this.f211d = f;
    }

    public final boolean m169a() {
        return !((LocationManager) this.f208a.getSystemService("location")).getProviders(new Criteria(), true).isEmpty();
    }

    public final void m170b() {
        this.f214g = false;
        if (this.f212e) {
            C0872m.Log(5, "Location_StartUpdatingLocation already started!");
        } else if (m169a()) {
            LocationManager locationManager = (LocationManager) this.f208a.getSystemService("location");
            m164a(1);
            List<String> providers = locationManager.getProviders(true);
            if (providers.isEmpty()) {
                m164a(3);
                return;
            }
            LocationProvider locationProvider;
            if (this.f213f == 2) {
                for (String provider : providers) {
                    LocationProvider provider2 = locationManager.getProvider(provider);
                    if (provider2.getAccuracy() == 2) {
                        locationProvider = provider2;
                        break;
                    }
                }
            }
            locationProvider = null;
            for (String provider3 : providers) {
                if (locationProvider == null || locationManager.getProvider(provider3).getAccuracy() != 1) {
                    m165a(locationManager.getLastKnownLocation(provider3));
                    locationManager.requestLocationUpdates(provider3, 0, this.f211d, this, this.f208a.getMainLooper());
                    this.f212e = true;
                }
            }
        } else {
            m164a(3);
        }
    }

    public final void m171b(float f) {
        if (f < 100.0f) {
            this.f213f = 1;
        } else if (f < 500.0f) {
            this.f213f = 1;
        } else {
            this.f213f = 2;
        }
    }

    public final void m172c() {
        ((LocationManager) this.f208a.getSystemService("location")).removeUpdates(this);
        this.f212e = false;
        this.f210c = null;
        m164a(0);
    }

    public final void m173d() {
        if (this.f215h == 1 || this.f215h == 2) {
            this.f214g = true;
            m172c();
        }
    }

    public final void m174e() {
        if (this.f214g) {
            m170b();
        }
    }

    public final void onLocationChanged(Location location) {
        m164a(2);
        m165a(location);
    }

    public final void onProviderDisabled(String str) {
        this.f210c = null;
    }

    public final void onProviderEnabled(String str) {
    }

    public final void onStatusChanged(String str, int i, Bundle bundle) {
    }
}
