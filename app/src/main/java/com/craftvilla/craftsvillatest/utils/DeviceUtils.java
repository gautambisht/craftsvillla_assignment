package com.craftvilla.craftsvillatest.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class DeviceUtils {

    private DeviceUtils() {
    }

    public static String getVersionName(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(
                    context.getPackageName(), 0);
            return info.versionName;
        } catch (Exception e) {
            LogUtils.printStackTrace(e);
        }
        return null;
    }

    public static String getDeviceTestId(Context cont) {
        String android_id = Settings.Secure.getString(
                cont.getContentResolver(), Settings.Secure.ANDROID_ID);
        return md5(android_id).toUpperCase();
    }

    public static String md5(String s) {
        try {
            // Create MD5 Hash
            MessageDigest digest = MessageDigest
                    .getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (int i = 0; i < messageDigest.length; i++) {
                String h = Integer.toHexString(0xFF & messageDigest[i]);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            LogUtils.e("device test id ", e.getMessage());
        }
        return "";
    }

    public static boolean isAboveJellyBean() {
        return Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN;
    }
}
