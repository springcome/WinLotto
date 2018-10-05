package net.springcome.winlotto.utils;

import android.content.ContentValues;

import net.springcome.winlotto.entity.User;

/**
 * Database Utility
 * @author springcome
 * @since 2018.10.05
 */
public class DatabaseUtils {
    private static final String LOG_TAG = DatabaseUtils.class.getSimpleName();

    /**
     * 사용자 정보를 ContentValues에 담는다.
     * @param user
     * @return
     */
    public static ContentValues userToContentValues(User user) {
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.User._ID, user.get_id());
        values.put(DatabaseContract.User.USER_ID, user.getUserId());
        values.put(DatabaseContract.User.USER_NM, user.getUserNm());
        values.put(DatabaseContract.User.USER_EMAIL, user.getUserNm());
        values.put(DatabaseContract.User.USER_PWD, user.getUserNm());
        values.put(DatabaseContract.User.USER_GRAD, user.getUserNm());
        values.put(DatabaseContract.User.USER_USE_DATE, user.getUserNm());
        values.put(DatabaseContract.User.USER_JOIN_DATE, user.getUserNm());
        return values;
    }
}
