package net.springcome.winlotto.utils;


import android.util.Log;

import net.springcome.winlotto.entity.LottoWin;

import java.util.ArrayList;
import java.util.List;

public class QRScanParse {

    public static List<LottoWin> parseLottoNumber(String qrContent) {
        Object [] parseContent = parseQRContent(qrContent);

        List<LottoWin> list = new ArrayList<>();

        String drwNo = parseContent[0].toString();


        for (int i = 1; i <= parseContent.length-1; i++) {
            if (i == parseContent.length-1) continue;

            LottoWin lotto = new LottoWin();
            lotto.setDrwNo(drwNo);

            String num = parseContent[i].toString();
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

        String contents = splitAll[1].replace("q", "");
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
