package net.springcome.winlotto.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PreferencesUtility {

    /** 임사사용자 또는 가입사용자의 아이디 체크 */
    public static final String LOGGED_IN_PREF           = "logged_in_status";
    /** 사용자 아이디 */
    public static final String LOGGED_IN_USER_ID        = "logged_in_user_id";
    /** 변경된 내용 체크 = {true, false}, 변경사항이 있으면 다시 체크해야 한다. */
    public static final String LOGGED_IN_CHANGE_STATUS  = "logged_in_change_status";

    public static String getLoggedInUserId(Context context) {
        SharedPreferences shared = PreferenceManager.getDefaultSharedPreferences(context);
        return shared.getString(LOGGED_IN_USER_ID, "");
    }
}
