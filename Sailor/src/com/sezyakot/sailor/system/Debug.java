package com.sezyakot.sailor.system;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;

public final class Debug {
	public static final boolean MODE = true;
	public static final boolean VERBOSE = true;
    public static final boolean WITHOUT_AUTORIZATION = true;

    public static void lockScreenOrientation(Activity a) {
        int currentOrientation = a.getResources().getConfiguration().orientation;
        if (currentOrientation == Configuration.ORIENTATION_PORTRAIT) {
            a.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } else {
            a.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
    }

    public static void unlockScreenOrientation(Activity a) {
        a.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
    }
}


