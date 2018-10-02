package net.springcome.winlotto.utils;

import android.provider.BaseColumns;

import java.sql.Blob;

public final class DatabaseContract {

    public static final String DATABASE_NAME = "lotto.db";
    public static final int DATABASE_VERSION = 1;

    /**
     *  회차별 당첨정보를 저장하는 테이블
     */
    public static final class LottoHistory implements BaseColumns {
        public static final String TABLE_NAME = "lotto_history";
        public static final String _ID = BaseColumns._ID;
        public static final String DRW_NO = "drw_no";
        public static final String BNUS_NO = "bnus_no";
        public static final String DRWT_NO1 = "drwt_no1";
        public static final String DRWT_NO2 = "drwt_no2";
        public static final String DRWT_NO3 = "drwt_no3";
        public static final String DRWT_NO4 = "drwt_no4";
        public static final String DRWT_NO5 = "drwt_no5";
        public static final String DRWT_NO6 = "drwt_no6";
        public static final String FIRST_ACCUM_AMNT = "first_accum_amnt";
        public static final String FIRST_WIN_AMNT = "first_win_amnt";
        public static final String TOT_SELL_AMNT = "tot_sell_amnt";
        public static final String DRW_NO_DATE = "drw_no_date";
        public static final String FIRST_PRZWNER_CO = "first_przwner_co";

        public static final String [] COLUMNS = {
                _ID, DRW_NO, BNUS_NO, DRWT_NO1, DRWT_NO2, DRWT_NO3
                , DRWT_NO4, DRWT_NO5, DRWT_NO6, FIRST_ACCUM_AMNT
                , FIRST_WIN_AMNT, TOT_SELL_AMNT, DRW_NO_DATE
                , FIRST_PRZWNER_CO
        };

        public static final String CREATE_TABLE_SQL = "CREATE TABLE " + TABLE_NAME + " ("
                + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + DRW_NO + " TEXT NOT NULL, "
                + DRW_NO_DATE + " TEXT NOT NULL, "
                + BNUS_NO + " TEXT NOT NULL, "
                + DRWT_NO1 + " TEXT NOT NULL, "
                + DRWT_NO2 + " TEXT NOT NULL, "
                + DRWT_NO3 + " TEXT NOT NULL, "
                + DRWT_NO4 + " TEXT NOT NULL, "
                + DRWT_NO5 + " TEXT NOT NULL, "
                + DRWT_NO6 + " TEXT NOT NULL, "
                + TOT_SELL_AMNT + " TEXT NOT NULL, "
                + FIRST_ACCUM_AMNT + " TEXT NOT NULL, "
                + FIRST_WIN_AMNT + " TEXT NOT NULL, "
                + FIRST_PRZWNER_CO + " TEXT NOT NULL)";
        public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    /**
     * 사용자가 구매한 로또 정보 저장
     */
    public static final class MyLotto implements BaseColumns {
        // http://qr.nlotto.co.kr/?v=0793m040515222326m121619203844m112223363744m202730363741m0709111235451581497433
        public static final String TABLE_NAME = "my_lotto";
        public static final String _ID = BaseColumns._ID;
        public static final String USER_ID = "user_id";
        public static final String TR = "tr";
        public static final String DRW_NO = "drw_no";
        public static final String DRWT_NO1 = "drwt_no1";
        public static final String DRWT_NO2 = "drwt_no2";
        public static final String DRWT_NO3 = "drwt_no3";
        public static final String DRWT_NO4 = "drwt_no4";
        public static final String DRWT_NO5 = "drwt_no5";
        public static final String DRWT_NO6 = "drwt_no6";

        public static final String [] COLUMNS = {
                USER_ID, TR, DRW_NO, DRWT_NO1, DRWT_NO2, DRWT_NO3, DRWT_NO4, DRWT_NO5, DRWT_NO6
        };

        public static final String CREATE_TABLE_SQL = "CREATE TABLE " + TABLE_NAME + " ("
                + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + USER_ID + " TEXT NOT NULL, "
                + TR + " TEXT NOT NULL, "
                + DRW_NO + " TEXT NOT NULL, "
                + DRWT_NO1 + " TEXT NOT NULL, "
                + DRWT_NO2 + " TEXT NOT NULL, "
                + DRWT_NO3 + " TEXT NOT NULL, "
                + DRWT_NO4 + " TEXT NOT NULL, "
                + DRWT_NO5 + " TEXT NOT NULL, "
                + DRWT_NO6 + " TEXT NOT NULL)";
        public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    /**
     * 가입 사용자 정보
     */
    public static final class User implements  BaseColumns {
        public static final String TABLE_NAME = "user";
        public static final String _ID = BaseColumns._ID;
        public static final String USER_ID = "user_id";
        public static final String USER_NM = "user_nm";
        public static final String USER_EMAIL = "user_email";
        public static final String USER_PWD = "user_pwd";
        public static final String USER_GRAD = "user_grad";
        public static final String USER_IMAGE = "user_image";
        public static final String USER_JOIN_DATE = "user_join_date";

        public static final String [] COLUMNS = {
                USER_ID, USER_EMAIL, USER_PWD, USER_GRAD, USER_JOIN_DATE, USER_JOIN_DATE
        };

        public static final String CREATE_TABLE_SQL = "CREATE TABLE " + TABLE_NAME + " ("
                + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + USER_ID + " TEXT NOT NULL, "
                + USER_EMAIL + " TEXT NOT NULL, "
                + USER_PWD + " TEXT NOT NULL, "
                + USER_GRAD + " TEXT NOT NULL, "
                + USER_IMAGE + " BLOB NULL, "
                + USER_JOIN_DATE + " TEXT NOT NULL)";
        public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }
}
