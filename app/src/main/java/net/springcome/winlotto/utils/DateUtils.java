package net.springcome.winlotto.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 일자관련 Utility
 *
 * @author springcome
 * @since 2018.10.05
 */
public class DateUtils {
    private static final String LOG_TAG = DateUtils.class.getSimpleName();

    /**
     * "yyyyMMdd" Format의 현재일자 리턴
     * @return
     */
    public static String getNowDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(new Date());
    }
}
