package net.springcome.winlotto.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SaveSharedPreference {

    static SharedPreferences getPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void setLoggedIn(Context context, boolean loggedIn, String userId, boolean changeStatus) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putBoolean(PreferencesUtility.LOGGED_IN_PREF, loggedIn);
        editor.putString(PreferencesUtility.LOGGED_IN_USER_ID, userId);
        editor.putBoolean(PreferencesUtility.LOGGED_IN_CHANGE_STATUS, changeStatus);
        editor.apply();
    }

    public static boolean getLoggedStatus(Context context) {
        getPreferences(context).getString(PreferencesUtility.LOGGED_IN_USER_ID, null);
        getPreferences(context).getBoolean(PreferencesUtility.LOGGED_IN_CHANGE_STATUS, false);
        return getPreferences(context).getBoolean(PreferencesUtility.LOGGED_IN_PREF, false);
    }
}
