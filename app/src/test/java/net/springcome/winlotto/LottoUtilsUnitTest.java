package net.springcome.winlotto;

import net.springcome.winlotto.utils.LottoUtils;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

public class LottoUtilsUnitTest {

    @Test
    @Deprecated
    public void nextDrwNoTest() {
        int adjustNum = 0;

        Calendar stCalc = Calendar.getInstance();
        stCalc.set(2002, 10, 30);
        Calendar nowCalc = Calendar.getInstance();
        nowCalc.set(2018, 7, 11);

        long stLong = stCalc.getTimeInMillis();
        long nowLong = nowCalc.getTimeInMillis();
        long lo = nowLong - stLong;

        System.out.println(lo);
        System.out.println(lo / 1000);
        System.out.println((lo / (1000*60*60*24*7)) + adjustNum);

    }

    @Test
    public void aboutFormatPriceTest() {
        String price = "156730000";

        String aboutPrice = price.substring(0, price.length()-7);

        System.out.println(aboutPrice + ":" + Math.round((Math.round((Double.valueOf(aboutPrice)/1000)*100)/100.0)*100));
    }
}
