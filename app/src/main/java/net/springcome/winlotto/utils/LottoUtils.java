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

    /**
     * 당첨번호와 Scan번호 비교하여 일치할경우 Color를 리턴
     * @param lottoWin
     * @param compareNum
     * @return
     */
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

    /**
     * 당첨여부 확인
     * @param lottoWin
     * @param scan
     * @return
     */
    public static final String winResult(LottoWin lottoWin, LottoWin scan) {
        Integer [] scanNoArr = {
                Integer.parseInt(scan.getDrwtNo1())
                , Integer.parseInt(scan.getDrwtNo2())
                , Integer.parseInt(scan.getDrwtNo3())
                , Integer.parseInt(scan.getDrwtNo4())
                , Integer.parseInt(scan.getDrwtNo5())
                , Integer.parseInt(scan.getDrwtNo6())
        };
        int winCount = 0;
        int bunsCount = 0;
        for (int no : scanNoArr) {
            if (Integer.parseInt(lottoWin.getDrwtNo1()) == no) {
                winCount += 1;
            } else if (Integer.parseInt(lottoWin.getDrwtNo2()) == no) {
                winCount += 1;
            } else if (Integer.parseInt(lottoWin.getDrwtNo3()) == no) {
                winCount += 1;
            } else if (Integer.parseInt(lottoWin.getDrwtNo4()) == no) {
                winCount += 1;
            } else if (Integer.parseInt(lottoWin.getDrwtNo5()) == no) {
                winCount += 1;
            } else if (Integer.parseInt(lottoWin.getDrwtNo6()) == no) {
                winCount += 1;
            }

            if  (Integer.parseInt(lottoWin.getBnusNo()) == no) {
                bunsCount += 1;
            }
        }


        if ((winCount + bunsCount) == 6) {
            if (bunsCount == 0) {
                return "1등";
            } else {
                return "2등";
            }
        } else if (winCount == 5) {
            return "3등";
        } else if (winCount == 4) {
            return "4등";
        } else if (winCount == 3) {
            return "5등";
        } else {
            return "-";
        }
    }
}
