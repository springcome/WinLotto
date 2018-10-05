package net.springcome.winlotto.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import net.springcome.winlotto.entity.LottoWin;
import net.springcome.winlotto.entity.User;
import net.springcome.winlotto.utils.DatabaseContract.*;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String LOG_TAG = DatabaseHelper.class.getSimpleName();

    public DatabaseHelper(Context context, String dbName, int dbVersion) {
        super(context, dbName, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DatabaseContract.LottoHistory.CREATE_TABLE_SQL);
        db.execSQL(DatabaseContract.User.CREATE_TABLE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(LottoHistory.DROP_TABLE_SQL);
        db.execSQL(DatabaseContract.User.DROP_TABLE_SQL);
        onCreate(db);
    }

    /**
     * 각 회차에 해당하는 당첨정보를 저장한다.
     * @param lotto
     */
    public void insertLottoHistory(LottoWin lotto) {
        // 이미 저장된 회차의 당첨정보는 다시 저장하지 않는다.
        if (fetchOneDataForLottoHistory(lotto.getDrwNo()) != null) {
            return;
        }

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(LottoHistory.DRW_NO, lotto.getDrwNo());
        values.put(LottoHistory.DRW_NO_DATE, lotto.getDrwNoDate());
        values.put(LottoHistory.BNUS_NO, lotto.getBnusNo());
        values.put(LottoHistory.DRWT_NO1, lotto.getDrwtNo1());
        values.put(LottoHistory.DRWT_NO2, lotto.getDrwtNo2());
        values.put(LottoHistory.DRWT_NO3, lotto.getDrwtNo3());
        values.put(LottoHistory.DRWT_NO4, lotto.getDrwtNo4());
        values.put(LottoHistory.DRWT_NO5, lotto.getDrwtNo5());
        values.put(LottoHistory.DRWT_NO6, lotto.getDrwtNo6());
        values.put(LottoHistory.TOT_SELL_AMNT, lotto.getTotSellamnt());
        values.put(LottoHistory.FIRST_ACCUM_AMNT, lotto.getFirstAccumamnt());
        values.put(LottoHistory.FIRST_WIN_AMNT, lotto.getFirstWinamnt());
        values.put(LottoHistory.FIRST_PRZWNER_CO, lotto.getFirstPrzwnerCo());

        db.insert(LottoHistory.TABLE_NAME, null, values);
        db.close();
    }

    /**
     * 사용자 아이디를 사용한 사용자 검색
     * @param userId
     * @return
     */
    public User fetchByUserID(String userId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                DatabaseContract.User.TABLE_NAME
                , DatabaseContract.User.COLUMNS
                , DatabaseContract.User.USER_ID + " = ?"
                , new String[]{userId}
                , null
                , null
                , null
        );
        User user = null;
        if (cursor.moveToFirst()) {
            do {
                user = converCursorToUserEntity(cursor);
            } while (cursor.moveToNext());
        }

        return user;
    }

    /**
     * 사용자 정보 UPDATE
     * @param user
     */
    public int updateUser(User user) {
        // 대상 사용자의 USER_ID를 체크
        User dbUser = fetchByUserID(user.getUserId());
        if (dbUser == null) {
            // 업데이트 대상 사용자 USER_ID를 찾을수 없다.
            Log.e(LOG_TAG, "업데이트 대상 사용자 USER_ID를 찾을수 없다.");
            return 0;
        }

        SQLiteDatabase db = this.getWritableDatabase();

//        ContentValues values = DatabaseUtils.userToContentValues(dbUser);
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.User.USER_NM, user.getUserNm());
        values.put(DatabaseContract.User.USER_EMAIL, user.getUserEmail());
        values.put(DatabaseContract.User.USER_PWD, user.getUserPwd());
        values.put(DatabaseContract.User.USER_GRAD, "A");
        values.put(DatabaseContract.User.USER_JOIN_DATE, user.getUserJoinDate());

        return db.update(
            DatabaseContract.User.TABLE_NAME
            , values
            , "_id = ?"
            , new String[]{dbUser.get_id()});
    }

    /**
     * 사용자 정보 저장
     * @param user
     */
    public void insertUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseContract.User.USER_ID, user.getUserId());
        values.put(DatabaseContract.User.USER_NM, user.getUserNm());
        values.put(DatabaseContract.User.USER_EMAIL, user.getUserEmail());
        values.put(DatabaseContract.User.USER_PWD, user.getUserPwd());
        values.put(DatabaseContract.User.USER_GRAD, "T");
        values.put(DatabaseContract.User.USER_USE_DATE, user.getUserUseDate());
        values.put(DatabaseContract.User.USER_JOIN_DATE, user.getUserJoinDate());

        db.insert(DatabaseContract.User.TABLE_NAME, null, values);
        db.close();
    }

    /**
     * 임시사용자 -> 정식사용자
     * @param user
     */
    public void joinUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.close();
    }

    public List<LottoWin> fetchAllDataForLottoHistory() {
        SQLiteDatabase db = this.getReadableDatabase();

        List<LottoWin> list = new ArrayList<>();
        Cursor cursor = db.query(
                LottoHistory.TABLE_NAME
                , LottoHistory.COLUMNS
                , null
                , null
                , null
                , null
                , LottoHistory.DRW_NO + " DESC");
        if (cursor.moveToFirst()) {
            do {
                list.add(convertCursorToEntity(cursor));
            } while (cursor.moveToNext());
        }

        return list;
    }

    public LottoWin fetchOneDataForLottoHistory(String drwNo) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                LottoHistory.TABLE_NAME
                , LottoHistory.COLUMNS
                , LottoHistory.DRW_NO + " = ?"
                , new String[]{drwNo}
                , null
                , null
                , null
        );
        LottoWin lottoWin = null;
        if (cursor.moveToFirst()) {
            do {
                lottoWin = convertCursorToEntity(cursor);
            } while (cursor.moveToNext());
        }

        return lottoWin;
    }

    /**
     * 복권정보 매핑
     * @param cursor
     * @return
     */
    private LottoWin convertCursorToEntity(Cursor cursor) {
        LottoWin lottoWin = new LottoWin();
        lottoWin.setDrwNo(cursor.getString(cursor.getColumnIndexOrThrow(LottoHistory.DRW_NO)));
        lottoWin.setDrwNoDate(cursor.getString(cursor.getColumnIndexOrThrow(LottoHistory.DRW_NO_DATE)));
        lottoWin.setBnusNo(cursor.getString(cursor.getColumnIndexOrThrow(LottoHistory.BNUS_NO)));
        lottoWin.setDrwtNo1(cursor.getString(cursor.getColumnIndexOrThrow(LottoHistory.DRWT_NO1)));
        lottoWin.setDrwtNo2(cursor.getString(cursor.getColumnIndexOrThrow(LottoHistory.DRWT_NO2)));
        lottoWin.setDrwtNo3(cursor.getString(cursor.getColumnIndexOrThrow(LottoHistory.DRWT_NO3)));
        lottoWin.setDrwtNo4(cursor.getString(cursor.getColumnIndexOrThrow(LottoHistory.DRWT_NO4)));
        lottoWin.setDrwtNo5(cursor.getString(cursor.getColumnIndexOrThrow(LottoHistory.DRWT_NO5)));
        lottoWin.setDrwtNo6(cursor.getString(cursor.getColumnIndexOrThrow(LottoHistory.DRWT_NO6)));
        lottoWin.setTotSellamnt(cursor.getString(cursor.getColumnIndexOrThrow(LottoHistory.TOT_SELL_AMNT)));
        lottoWin.setFirstAccumamnt(cursor.getString(cursor.getColumnIndexOrThrow(LottoHistory.FIRST_ACCUM_AMNT)));
        lottoWin.setFirstWinamnt(cursor.getString(cursor.getColumnIndexOrThrow(LottoHistory.FIRST_WIN_AMNT)));
        lottoWin.setFirstPrzwnerCo(cursor.getString(cursor.getColumnIndexOrThrow(LottoHistory.FIRST_PRZWNER_CO)));
        return lottoWin;
    }

    /**
     * 사용자 정보 매핑
     * @param cursor
     * @return
     */
    private User converCursorToUserEntity(Cursor cursor) {
        User user = new User();
        user.set_id(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.User._ID)));
        user.setUserId(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.User.USER_ID)));
        user.setUserNm(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.User.USER_NM)));
        user.setUserEmail(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.User.USER_EMAIL)));
        user.setUserGrad(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.User.USER_GRAD)));
        user.setUserUseDate(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.User.USER_USE_DATE)));
        user.setUserJoinDate(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.User.USER_JOIN_DATE)));
        return user;
    }
}
