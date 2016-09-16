package com.google.android.gms.location.internal;

import android.app.PendingIntent;
import android.location.Location;
import android.os.Looper;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderApi;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

public class zzd implements FusedLocationProviderApi {

    private static abstract class zza extends com.google.android.gms.location.LocationServices.zza<Status> {
        public zza(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        public /* synthetic */ Result zzb(Status status) {
            return zzd(status);
        }

        public Status zzd(Status status) {
            return status;
        }
    }

    /* renamed from: com.google.android.gms.location.internal.zzd.1 */
    class C06171 extends zza {
        final /* synthetic */ LocationRequest zzaFd;
        final /* synthetic */ LocationListener zzaFe;
        final /* synthetic */ zzd zzaFf;

        /* renamed from: com.google.android.gms.location.internal.zzd.1.1 */
        class C06161 extends com.google.android.gms.location.internal.zzg.zza {
            final /* synthetic */ C06171 zzaFg;

            C06161(C06171 c06171) {
                this.zzaFg = c06171;
            }

            public void zza(FusedLocationProviderResult fusedLocationProviderResult) {
                this.zzaFg.zzb(fusedLocationProviderResult.getStatus());
            }
        }

        C06171(zzd com_google_android_gms_location_internal_zzd, GoogleApiClient googleApiClient, LocationRequest locationRequest, LocationListener locationListener) {
            this.zzaFf = com_google_android_gms_location_internal_zzd;
            this.zzaFd = locationRequest;
            this.zzaFe = locationListener;
            super(googleApiClient);
        }

        protected void zza(zzl com_google_android_gms_location_internal_zzl) throws RemoteException {
            com_google_android_gms_location_internal_zzl.zza(this.zzaFd, this.zzaFe, null, new C06161(this));
        }
    }

    /* renamed from: com.google.android.gms.location.internal.zzd.2 */
    class C06192 extends zza {
        final /* synthetic */ zzd zzaFf;
        final /* synthetic */ LocationCallback zzaFh;

        /* renamed from: com.google.android.gms.location.internal.zzd.2.1 */
        class C06181 extends com.google.android.gms.location.internal.zzg.zza {
            final /* synthetic */ C06192 zzaFi;

            C06181(C06192 c06192) {
                this.zzaFi = c06192;
            }

            public void zza(FusedLocationProviderResult fusedLocationProviderResult) {
                this.zzaFi.zzb(fusedLocationProviderResult.getStatus());
            }
        }

        C06192(zzd com_google_android_gms_location_internal_zzd, GoogleApiClient googleApiClient, LocationCallback locationCallback) {
            this.zzaFf = com_google_android_gms_location_internal_zzd;
            this.zzaFh = locationCallback;
            super(googleApiClient);
        }

        protected void zza(zzl com_google_android_gms_location_internal_zzl) throws RemoteException {
            com_google_android_gms_location_internal_zzl.zza(this.zzaFh, new C06181(this));
        }
    }

    /* renamed from: com.google.android.gms.location.internal.zzd.3 */
    class C06203 extends zza {
        final /* synthetic */ zzd zzaFf;
        final /* synthetic */ boolean zzaFj;

        C06203(zzd com_google_android_gms_location_internal_zzd, GoogleApiClient googleApiClient, boolean z) {
            this.zzaFf = com_google_android_gms_location_internal_zzd;
            this.zzaFj = z;
            super(googleApiClient);
        }

        protected void zza(zzl com_google_android_gms_location_internal_zzl) throws RemoteException {
            com_google_android_gms_location_internal_zzl.zzah(this.zzaFj);
            zzb(Status.zzabb);
        }
    }

    /* renamed from: com.google.android.gms.location.internal.zzd.4 */
    class C06214 extends zza {
        final /* synthetic */ zzd zzaFf;
        final /* synthetic */ Location zzaFk;

        C06214(zzd com_google_android_gms_location_internal_zzd, GoogleApiClient googleApiClient, Location location) {
            this.zzaFf = com_google_android_gms_location_internal_zzd;
            this.zzaFk = location;
            super(googleApiClient);
        }

        protected void zza(zzl com_google_android_gms_location_internal_zzl) throws RemoteException {
            com_google_android_gms_location_internal_zzl.zzc(this.zzaFk);
            zzb(Status.zzabb);
        }
    }

    /* renamed from: com.google.android.gms.location.internal.zzd.5 */
    class C06235 extends zza {
        final /* synthetic */ LocationRequest zzaFd;
        final /* synthetic */ LocationListener zzaFe;
        final /* synthetic */ zzd zzaFf;
        final /* synthetic */ Looper zzaFl;

        /* renamed from: com.google.android.gms.location.internal.zzd.5.1 */
        class C06221 extends com.google.android.gms.location.internal.zzg.zza {
            final /* synthetic */ C06235 zzaFm;

            C06221(C06235 c06235) {
                this.zzaFm = c06235;
            }

            public void zza(FusedLocationProviderResult fusedLocationProviderResult) {
                this.zzaFm.zzb(fusedLocationProviderResult.getStatus());
            }
        }

        C06235(zzd com_google_android_gms_location_internal_zzd, GoogleApiClient googleApiClient, LocationRequest locationRequest, LocationListener locationListener, Looper looper) {
            this.zzaFf = com_google_android_gms_location_internal_zzd;
            this.zzaFd = locationRequest;
            this.zzaFe = locationListener;
            this.zzaFl = looper;
            super(googleApiClient);
        }

        protected void zza(zzl com_google_android_gms_location_internal_zzl) throws RemoteException {
            com_google_android_gms_location_internal_zzl.zza(this.zzaFd, this.zzaFe, this.zzaFl, new C06221(this));
        }
    }

    /* renamed from: com.google.android.gms.location.internal.zzd.6 */
    class C06256 extends zza {
        final /* synthetic */ LocationRequest zzaFd;
        final /* synthetic */ zzd zzaFf;
        final /* synthetic */ LocationCallback zzaFh;
        final /* synthetic */ Looper zzaFl;

        /* renamed from: com.google.android.gms.location.internal.zzd.6.1 */
        class C06241 extends com.google.android.gms.location.internal.zzg.zza {
            final /* synthetic */ C06256 zzaFn;

            C06241(C06256 c06256) {
                this.zzaFn = c06256;
            }

            public void zza(FusedLocationProviderResult fusedLocationProviderResult) {
                this.zzaFn.zzb(fusedLocationProviderResult.getStatus());
            }
        }

        C06256(zzd com_google_android_gms_location_internal_zzd, GoogleApiClient googleApiClient, LocationRequest locationRequest, LocationCallback locationCallback, Looper looper) {
            this.zzaFf = com_google_android_gms_location_internal_zzd;
            this.zzaFd = locationRequest;
            this.zzaFh = locationCallback;
            this.zzaFl = looper;
            super(googleApiClient);
        }

        protected void zza(zzl com_google_android_gms_location_internal_zzl) throws RemoteException {
            com_google_android_gms_location_internal_zzl.zza(LocationRequestInternal.zzb(this.zzaFd), this.zzaFh, this.zzaFl, new C06241(this));
        }
    }

    /* renamed from: com.google.android.gms.location.internal.zzd.7 */
    class C06277 extends zza {
        final /* synthetic */ PendingIntent zzaEY;
        final /* synthetic */ LocationRequest zzaFd;
        final /* synthetic */ zzd zzaFf;

        /* renamed from: com.google.android.gms.location.internal.zzd.7.1 */
        class C06261 extends com.google.android.gms.location.internal.zzg.zza {
            final /* synthetic */ C06277 zzaFo;

            C06261(C06277 c06277) {
                this.zzaFo = c06277;
            }

            public void zza(FusedLocationProviderResult fusedLocationProviderResult) {
                this.zzaFo.zzb(fusedLocationProviderResult.getStatus());
            }
        }

        C06277(zzd com_google_android_gms_location_internal_zzd, GoogleApiClient googleApiClient, LocationRequest locationRequest, PendingIntent pendingIntent) {
            this.zzaFf = com_google_android_gms_location_internal_zzd;
            this.zzaFd = locationRequest;
            this.zzaEY = pendingIntent;
            super(googleApiClient);
        }

        protected void zza(zzl com_google_android_gms_location_internal_zzl) throws RemoteException {
            com_google_android_gms_location_internal_zzl.zza(this.zzaFd, this.zzaEY, new C06261(this));
        }
    }

    /* renamed from: com.google.android.gms.location.internal.zzd.8 */
    class C06298 extends zza {
        final /* synthetic */ LocationListener zzaFe;
        final /* synthetic */ zzd zzaFf;

        /* renamed from: com.google.android.gms.location.internal.zzd.8.1 */
        class C06281 extends com.google.android.gms.location.internal.zzg.zza {
            final /* synthetic */ C06298 zzaFp;

            C06281(C06298 c06298) {
                this.zzaFp = c06298;
            }

            public void zza(FusedLocationProviderResult fusedLocationProviderResult) {
                this.zzaFp.zzb(fusedLocationProviderResult.getStatus());
            }
        }

        C06298(zzd com_google_android_gms_location_internal_zzd, GoogleApiClient googleApiClient, LocationListener locationListener) {
            this.zzaFf = com_google_android_gms_location_internal_zzd;
            this.zzaFe = locationListener;
            super(googleApiClient);
        }

        protected void zza(zzl com_google_android_gms_location_internal_zzl) throws RemoteException {
            com_google_android_gms_location_internal_zzl.zza(this.zzaFe, new C06281(this));
        }
    }

    /* renamed from: com.google.android.gms.location.internal.zzd.9 */
    class C06319 extends zza {
        final /* synthetic */ PendingIntent zzaEY;
        final /* synthetic */ zzd zzaFf;

        /* renamed from: com.google.android.gms.location.internal.zzd.9.1 */
        class C06301 extends com.google.android.gms.location.internal.zzg.zza {
            final /* synthetic */ C06319 zzaFq;

            C06301(C06319 c06319) {
                this.zzaFq = c06319;
            }

            public void zza(FusedLocationProviderResult fusedLocationProviderResult) {
                this.zzaFq.zzb(fusedLocationProviderResult.getStatus());
            }
        }

        C06319(zzd com_google_android_gms_location_internal_zzd, GoogleApiClient googleApiClient, PendingIntent pendingIntent) {
            this.zzaFf = com_google_android_gms_location_internal_zzd;
            this.zzaEY = pendingIntent;
            super(googleApiClient);
        }

        protected void zza(zzl com_google_android_gms_location_internal_zzl) throws RemoteException {
            com_google_android_gms_location_internal_zzl.zza(this.zzaEY, new C06301(this));
        }
    }

    public Location getLastLocation(GoogleApiClient client) {
        try {
            return LocationServices.zzd(client).getLastLocation();
        } catch (Exception e) {
            return null;
        }
    }

    public LocationAvailability getLocationAvailability(GoogleApiClient client) {
        try {
            return LocationServices.zzd(client).zzwD();
        } catch (Exception e) {
            return null;
        }
    }

    public PendingResult<Status> removeLocationUpdates(GoogleApiClient client, PendingIntent callbackIntent) {
        return client.zzb(new C06319(this, client, callbackIntent));
    }

    public PendingResult<Status> removeLocationUpdates(GoogleApiClient client, LocationCallback callback) {
        return client.zzb(new C06192(this, client, callback));
    }

    public PendingResult<Status> removeLocationUpdates(GoogleApiClient client, LocationListener listener) {
        return client.zzb(new C06298(this, client, listener));
    }

    public PendingResult<Status> requestLocationUpdates(GoogleApiClient client, LocationRequest request, PendingIntent callbackIntent) {
        return client.zzb(new C06277(this, client, request, callbackIntent));
    }

    public PendingResult<Status> requestLocationUpdates(GoogleApiClient client, LocationRequest request, LocationCallback callback, Looper looper) {
        return client.zzb(new C06256(this, client, request, callback, looper));
    }

    public PendingResult<Status> requestLocationUpdates(GoogleApiClient client, LocationRequest request, LocationListener listener) {
        return client.zzb(new C06171(this, client, request, listener));
    }

    public PendingResult<Status> requestLocationUpdates(GoogleApiClient client, LocationRequest request, LocationListener listener, Looper looper) {
        return client.zzb(new C06235(this, client, request, listener, looper));
    }

    public PendingResult<Status> setMockLocation(GoogleApiClient client, Location mockLocation) {
        return client.zzb(new C06214(this, client, mockLocation));
    }

    public PendingResult<Status> setMockMode(GoogleApiClient client, boolean isMockMode) {
        return client.zzb(new C06203(this, client, isMockMode));
    }
}
