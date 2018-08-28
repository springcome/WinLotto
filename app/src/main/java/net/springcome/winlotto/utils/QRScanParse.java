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

        // TODO 조회되는 기기에 따라(또는 android version) 중간구분자가 다르게 Scan되는것을 확인 - 원인은 찾지 못했으며 임시 해결책으로 확인된 구분자만 처리하도록함
        String contents = splitAll[1].replace("q", "");
        contents = contents.replace("m", "");
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
