package com.craftvilla.craftsvillatest.utils;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;

public final class NetworkMonitor extends BroadcastReceiver {

    public static final int NETWORK_TYPE_FAST_3G = 2;
    public static final int NETWORK_TYPE_FAST_WIFI = 1;
    public static final int NETWORK_TYPE_NO_NETWORK = 4;
    public static final int NETWORK_TYPE_SLOW = 3;
    private static boolean mConnectionAvailable = false;
    private static Context mContext;

    public static void checkNetworkConnectivity(Context context) {
        try {
            mConnectionAvailable = false;
            ConnectivityManager connectivity = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivity == null) {
                mConnectionAvailable = false;
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Network[] networks = connectivity.getAllNetworks();
                    for (Network mNetwork : networks) {
                        NetworkInfo networkInfo = connectivity.getNetworkInfo(mNetwork);
                        if (networkInfo.getState().equals(NetworkInfo.State.CONNECTED)) {
                            mConnectionAvailable = true;
                            break;
                        }
                    }
                } else {
                    NetworkInfo[] info = connectivity.getAllNetworkInfo();
                    if (info != null) {
                        for (int i = 0; i < info.length; i++) {
                            if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                                mConnectionAvailable = true;
                                break;
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            mConnectionAvailable = false;
        }

    }

    public static String getNetworkOperatorName() {
        return ((TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE)).getNetworkOperatorName();
    }

    public static NetworkInfo getNetworkType(Context context) {
        if (context != null) {
            NetworkInfo networkinfo;
            try {
                networkinfo = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
            } catch (Exception exception) {
                return null;
            }
            return networkinfo;
        } else {
            return null;
        }
    }

    public static NetState getNetwork() {
        int i = isNetworkFast();
        if (i == NETWORK_TYPE_SLOW) {
            return NetState.NET_2G;
        }
        if (i == NETWORK_TYPE_FAST_3G) {
            return NetState.NET_3G;
        }
        if (i == NETWORK_TYPE_FAST_WIFI) {
            return NetState.NET_WIFI;
        } else {
            return NetState.NET_UNKNOWN;
        }
    }

    public static void initialize(Context context) {
        NetworkMonitor.mContext = context;
        checkNetworkConnectivity(mContext);
    }

    public static boolean isNetworkAvailable() {
        return mConnectionAvailable;
    }

    public static void setNetworkAvailable(boolean flag) {
        mConnectionAvailable = flag;
    }

    public static int isNetworkFast() {
        if (mContext != null) {
            ConnectivityManager connectivitymanager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivitymanager != null) {
                NetworkInfo networkinfo = connectivitymanager.getNetworkInfo(1);
                if (networkinfo != null && networkinfo.isConnected()) {
                    return 1;
                }
            }
            int i = ((TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE)).getNetworkType();
            if (i > 0 && i < 3) {
                return 3;
            }
            return i <= 2 ? 4 : 2;
        } else {
            return -1;
        }
    }

    public static boolean isNetworkPresent() {
        boolean flag = false;
        ConnectivityManager connectivitymanager = (ConnectivityManager) mContext
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = connectivitymanager.getActiveNetworkInfo();
        if (null != activeNetwork) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
                flag = true;

            if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
                flag = true;
        }
        return flag;
    }

    public void onReceive(Context context, Intent intent) {
        checkNetworkConnectivity(context);
    }

    public enum NetState {
        NET_2G, NET_3G, NET_4G, NET_WIFI, NET_UNKNOWN
    }

    public static boolean isAirplaneModeOn(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return Settings.System.getInt(context.getContentResolver(),
                    Settings.System.AIRPLANE_MODE_ON, 0) != 0;
        } else {
            return Settings.Global.getInt(context.getContentResolver(),
                    Settings.Global.AIRPLANE_MODE_ON, 0) != 0;
        }
    }

    public static void openNetworkSettings(Activity context) {
        Intent intent = new Intent();
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Settings.ACTION_SETTINGS);
        context.startActivity(intent);
    }
}
