package net.springcome.winlotto.utils;


import android.util.Log;

import net.springcome.winlotto.entity.LottoWin;

import java.util.ArrayList;
import java.util.List;

public class QRScanParse {
    private static final String LOG_TAG = QRScanParse.class.getSimpleName();

    // http://qr.nlotto.co.kr/?v=0793m040515222326m121619203844m112223363744m202730363741m0709111235451581497433    // Real QRCode scan value
    // http://qr.nlotto.co.kr/?v=0793m101521353843m101521313843m102021353843m101520353743m1016213639431581497433    // Test QRCode scan value
    public static List<LottoWin> parseLottoNumber(String qrContent) {
        Object [] parseContent = parseQRContent(qrContent);

        List<LottoWin> list = new ArrayList<>();

        String drwNo = parseContent[0].toString();


        for (int i = 1; i <= parseContent.length-1; i++) {
            if (i == parseContent.length-1) continue;

            LottoWin lotto = new LottoWin();
            lotto.setDrwNo(drwNo);

            String num = parseContent[i].toString();

            if (num.substring(0, 2).equals("00")) continue;

            lotto.setDrwtNo1(num.substring(0, 2));
            lotto.setDrwtNo2(num.substring(2, 4));
            lotto.setDrwtNo3(num.substring(4, 6));
            lotto.setDrwtNo4(num.substring(6, 8));
            lotto.setDrwtNo5(num.substring(8, 10));
            lotto.setDrwtNo6(num.substring(10));

            list.add(lotto);
        }

        return list;
    }

    private static Object[] parseQRContent(String qrContent) {
        String [] splitAll = qrContent.split("v=");

        List<String> list = new ArrayList<String>();

        // q : 자동게임, m : 수동게임, n : 없는게임
        String contents = splitAll[1].replace("q", "");
        contents = contents.replace("m", "");
        contents = contents.replace("n", "");
        list.add(contents.substring(0, 4));
        contents = contents.substring(4);
        while(contents.length() > 12) {
            list.add(contents.substring(0, 12));
            contents = contents.substring(12);
        }
        list.add(contents);

        return list.toArray();
    }
}
