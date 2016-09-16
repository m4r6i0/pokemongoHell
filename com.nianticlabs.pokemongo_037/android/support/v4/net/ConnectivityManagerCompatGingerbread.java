package android.support.v4.net;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.upsight.mediation.mraid.properties.MRAIDResizeProperties;
import spacemadness.com.lunarconsole.C1562R;

class ConnectivityManagerCompatGingerbread {
    ConnectivityManagerCompatGingerbread() {
    }

    public static boolean isActiveNetworkMetered(ConnectivityManager cm) {
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info == null) {
            return true;
        }
        switch (info.getType()) {
            case C1562R.styleable.AdsAttrs_adSize /*0*/:
            case C1562R.styleable.LoadingImageView_circleCrop /*2*/:
            case MRAIDResizeProperties.CUSTOM_CLOSE_POSITION_CENTER /*3*/:
            case MRAIDResizeProperties.CUSTOM_CLOSE_POSITION_BOTTOM_LEFT /*4*/:
            case MRAIDResizeProperties.CUSTOM_CLOSE_POSITION_BOTTOM_CENTER /*5*/:
            case MRAIDResizeProperties.CUSTOM_CLOSE_POSITION_BOTTOM_RIGHT /*6*/:
                return true;
            case C1562R.styleable.LoadingImageView_imageAspectRatio /*1*/:
                return false;
            default:
                return true;
        }
    }
}
