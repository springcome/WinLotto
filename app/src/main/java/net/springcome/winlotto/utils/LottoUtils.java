package net.springcome.winlotto.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.widget.TextView;

import net.springcome.winlotto.R;
import net.springcome.winlotto.entity.LottoWin;

import java.text.DecimalFormat;
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

    /**
     * Lotto Ball에 Gradation 및 원형태 만들기
     * @param startColor
     * @return
     */
    public static GradientDrawable makeLottoBall(int startColor) {
        int [] colors = {startColor, Color.WHITE};
        GradientDrawable g = new GradientDrawable(GradientDrawable.Orientation.BL_TR, colors);
        g.setCornerRadius(64);
        return g;
    }

    /**
     * 금액 Format
     * @param price
     * @return
     */
    public static String formatPrice(String price) {
        DecimalFormat df = new DecimalFormat("###,###");
        return df.format(Long.parseLong(price));
    }

    /**
     * 금액 Format - 억단위금액
     * @param price
     * @return
     */
    public static String aboutFormatPrice(String price) {
        if (price.length() > 7) {
            String strAboutPrice = price.substring(0, price.length()-7);
            long aboutPrice = Math.round((Math.round((Double.valueOf(strAboutPrice)/1000)*100)/100.0)*100);
            return formatPrice(Long.toString(aboutPrice));
        } else {
            return formatPrice(price);
        }
    }

    public static void fillWinInformation(Activity ac, LottoWin data) {
        TextView viewDrwNo = ac.findViewById(R.id.view_drwNo);
        viewDrwNo.setText(data.getDrwNo());

        TextView viewDrwNoDate = ac.findViewById(R.id.view_drw_no_date);
        viewDrwNoDate.setText(data.getDrwNoDate());

        TextView viewFirstPrzwnerCo = ac.findViewById(R.id.view_first_przwner_co);
        viewFirstPrzwnerCo.setText(data.getFirstPrzwnerCo());
        TextView viewFirstAccumamnt = ac.findViewById(R.id.view_first_accumamnt);
        viewFirstAccumamnt.setText(LottoUtils.aboutFormatPrice(data.getFirstAccumamnt()));
        TextView viewFirstWinamnt = ac.findViewById(R.id.view_first_winamnt);
        viewFirstWinamnt.setText(LottoUtils.aboutFormatPrice(data.getFirstWinamnt()));

        TextView viewDrwtNo1 = ac.findViewById(R.id.view_drwtNo1);
        viewDrwtNo1.setText(data.getDrwtNo1());
        viewDrwtNo1.setBackground(LottoUtils.makeLottoBall(LottoUtils.getLottoColor(Integer.parseInt(data.getDrwtNo1()))));

        TextView viewDrwtNo2 = ac.findViewById(R.id.view_drwtNo2);
        viewDrwtNo2.setText(data.getDrwtNo2());
        viewDrwtNo2.setBackground(LottoUtils.makeLottoBall(LottoUtils.getLottoColor(Integer.parseInt(data.getDrwtNo2()))));

        TextView viewDrwtNo3 = ac.findViewById(R.id.view_drwtNo3);
        viewDrwtNo3.setText(data.getDrwtNo3());
        viewDrwtNo3.setBackground(LottoUtils.makeLottoBall(LottoUtils.getLottoColor(Integer.parseInt(data.getDrwtNo3()))));

        TextView viewDrwtNo4 = ac.findViewById(R.id.view_drwtNo4);
        viewDrwtNo4.setText(data.getDrwtNo4());
        viewDrwtNo4.setBackground(LottoUtils.makeLottoBall(LottoUtils.getLottoColor(Integer.parseInt(data.getDrwtNo4()))));

        TextView viewDrwtNo5 = ac.findViewById(R.id.view_drwtNo5);
        viewDrwtNo5.setText(data.getDrwtNo5());
        viewDrwtNo5.setBackground(LottoUtils.makeLottoBall(LottoUtils.getLottoColor(Integer.parseInt(data.getDrwtNo5()))));

        TextView viewDrwtNo6 = ac.findViewById(R.id.view_drwtNo6);
        viewDrwtNo6.setText(data.getDrwtNo6());
        viewDrwtNo6.setBackground(LottoUtils.makeLottoBall(LottoUtils.getLottoColor(Integer.parseInt(data.getDrwtNo6()))));

        TextView viewBnusNo = ac.findViewById(R.id.view_bnusNo);
        viewBnusNo.setText(data.getBnusNo());
        viewBnusNo.setBackground(LottoUtils.makeLottoBall(LottoUtils.getLottoColor(Integer.parseInt(data.getBnusNo()))));
    }
}
