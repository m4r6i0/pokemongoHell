package com.google.android.gms.ads.internal.util.client;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.upsight.mediation.mraid.properties.MRAIDResizeProperties;
import spacemadness.com.lunarconsole.C1562R;

public class zzc implements Creator<VersionInfoParcel> {
    static void zza(VersionInfoParcel versionInfoParcel, Parcel parcel, int i) {
        int zzaq = zzb.zzaq(parcel);
        zzb.zzc(parcel, 1, versionInfoParcel.versionCode);
        zzb.zza(parcel, 2, versionInfoParcel.zzJu, false);
        zzb.zzc(parcel, 3, versionInfoParcel.zzJv);
        zzb.zzc(parcel, 4, versionInfoParcel.zzJw);
        zzb.zza(parcel, 5, versionInfoParcel.zzJx);
        zzb.zzI(parcel, zzaq);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return zzp(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return zzO(x0);
    }

    public VersionInfoParcel[] zzO(int i) {
        return new VersionInfoParcel[i];
    }

    public VersionInfoParcel zzp(Parcel parcel) {
        boolean z = false;
        int zzap = zza.zzap(parcel);
        String str = null;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (parcel.dataPosition() < zzap) {
            int zzao = zza.zzao(parcel);
            switch (zza.zzbM(zzao)) {
                case C1562R.styleable.LoadingImageView_imageAspectRatio /*1*/:
                    i3 = zza.zzg(parcel, zzao);
                    break;
                case C1562R.styleable.LoadingImageView_circleCrop /*2*/:
                    str = zza.zzp(parcel, zzao);
                    break;
                case MRAIDResizeProperties.CUSTOM_CLOSE_POSITION_CENTER /*3*/:
                    i2 = zza.zzg(parcel, zzao);
                    break;
                case MRAIDResizeProperties.CUSTOM_CLOSE_POSITION_BOTTOM_LEFT /*4*/:
                    i = zza.zzg(parcel, zzao);
                    break;
                case MRAIDResizeProperties.CUSTOM_CLOSE_POSITION_BOTTOM_CENTER /*5*/:
                    z = zza.zzc(parcel, zzao);
                    break;
                default:
                    zza.zzb(parcel, zzao);
                    break;
            }
        }
        if (parcel.dataPosition() == zzap) {
            return new VersionInfoParcel(i3, str, i2, i, z);
        }
        throw new zza.zza("Overread allowed size end=" + zzap, parcel);
    }
}
