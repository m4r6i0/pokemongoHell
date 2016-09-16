package crittercism.android;

import android.location.Location;

public final class bc {
    private static Location f401a;

    public static synchronized void m474a(Location location) {
        synchronized (bc.class) {
            if (location != null) {
                location = new Location(location);
            }
            f401a = location;
        }
    }

    public static synchronized Location m473a() {
        Location location;
        synchronized (bc.class) {
            location = f401a;
        }
        return location;
    }

    public static synchronized boolean m475b() {
        boolean z;
        synchronized (bc.class) {
            z = f401a != null;
        }
        return z;
    }
}
