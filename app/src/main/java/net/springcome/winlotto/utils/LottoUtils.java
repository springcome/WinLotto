package net.springcome.winlotto.utils;

import android.graphics.Color;

import net.springcome.winlotto.entity.LottoWin;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class LottoUtils {

    /**
     * 최신 당첨 회차 계산
     * @return
     */
    public static final String currentDrwNo(String drwNo) {
        if (drwNo == null) {
            // 조정수
            int adjustNum = 1;

            // 로또 시작일 2002.12.07 - 1회
            Calendar cal = Calendar.getInstance();
            cal.set(2002, 11, 07);

            // 현재일자
            Calendar nowCal = Calendar.getInstance();

            long currentDrwNo = ((nowCal.getTimeInMillis() - cal.getTimeInMillis()) / (1000 * 60 * 60 * 24 * 7)) + adjustNum;

            return Long.toString(currentDrwNo);
        } else {
            return drwNo;
        }
    }

    /**
     * 로또 번호에 해당하는 Color
     * @param no
     * @return
     */
    public static final int getLottoColor(int no) {
        if (0 < no && 10 >= no) {
            // 노랑
            return Color.YELLOW;
        } else if (10 < no && 20 >= no) {
            // 파랑
            return Color.BLUE;
        } else if (20 < no && 30 >= no) {
            // 빨강
            return Color.RED;
        } else if (30 < no && 40 >= no) {
            // 검정
            return Color.BLACK;
        } else if (40  < no && 45 >= no) {
            // 초록
            return Color.GREEN;
        } else {
            // 흰색
            return Color.WHITE;
        }
    }

    public static final int compareWinNumber(LottoWin lottoWin, String compareNum) {
        if (lottoWin != null) {
            if (Integer.parseInt(lottoWin.getDrwtNo1()) == Integer.parseInt(compareNum)) {
                return getLottoColor(Integer.parseInt(compareNum));
            } else if (Integer.parseInt(lottoWin.getDrwtNo2()) == Integer.parseInt(compareNum)) {
                return getLottoColor(Integer.parseInt(compareNum));
            } else if (Integer.parseInt(lottoWin.getDrwtNo3()) == Integer.parseInt(compareNum)) {
                return getLottoColor(Integer.parseInt(compareNum));
            } else if (Integer.parseInt(lottoWin.getDrwtNo4()) == Integer.parseInt(compareNum)) {
                return getLottoColor(Integer.parseInt(compareNum));
            } else if (Integer.parseInt(lottoWin.getDrwtNo5()) == Integer.parseInt(compareNum)) {
                return getLottoColor(Integer.parseInt(compareNum));
            } else if (Integer.parseInt(lottoWin.getDrwtNo6()) == Integer.parseInt(compareNum)) {
                return getLottoColor(Integer.parseInt(compareNum));
            } else {
                return getLottoColor(0);
            }
        } else {
            return getLottoColor(0);
        }
    }
}
