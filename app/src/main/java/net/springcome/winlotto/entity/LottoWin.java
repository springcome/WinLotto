package net.springcome.winlotto.entity;

/**
 *
 * @author springcome
 * @since 2018.08.12
 */
public class LottoWin {
    /** 조회결과 */
    private String returnValue;
    /** 횟차 */
    private String drwNo;
    /** 당첨번호 - 보너스 번호 */
    private String bnusNo;
    /** 당첨번호 1 */
    private String drwtNo1;
    /** 당첨번호 2 */
    private String drwtNo2;
    /** 당첨번호 3 */
    private String drwtNo3;
    /** 당첨번호 4 */
    private String drwtNo4;
    /** 당첨번호 5 */
    private String drwtNo5;
    /** 당첨번호 6 */
    private String drwtNo6;
    /** 총당첨 금액 (1등에게 배분될 금액) */
    private String firstAccumamnt;
    /** 1등 당첨금액 */
    private String firstWinamnt;
    /** 회차에 판매금액 */
    private String totSellamnt;
    /** 추첨일자 */
    private String drwNoDate;
    /** 1등 당첨자 수 */
    private String firstPrzwnerCo;

    public String getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(String returnValue) {
        this.returnValue = returnValue;
    }

    public String getDrwNo() {
        return drwNo;
    }

    public void setDrwNo(String drwNo) {
        this.drwNo = drwNo;
    }

    public String getBnusNo() {
        return bnusNo;
    }

    public void setBnusNo(String bnusNo) {
        this.bnusNo = bnusNo;
    }

    public String getDrwtNo1() {
        return drwtNo1;
    }

    public void setDrwtNo1(String drwtNo1) {
        this.drwtNo1 = drwtNo1;
    }

    public String getDrwtNo2() {
        return drwtNo2;
    }

    public void setDrwtNo2(String drwtNo2) {
        this.drwtNo2 = drwtNo2;
    }

    public String getDrwtNo3() {
        return drwtNo3;
    }

    public void setDrwtNo3(String drwtNo3) {
        this.drwtNo3 = drwtNo3;
    }

    public String getDrwtNo4() {
        return drwtNo4;
    }

    public void setDrwtNo4(String drwtNo4) {
        this.drwtNo4 = drwtNo4;
    }

    public String getDrwtNo5() {
        return drwtNo5;
    }

    public void setDrwtNo5(String drwtNo5) {
        this.drwtNo5 = drwtNo5;
    }

    public String getDrwtNo6() {
        return drwtNo6;
    }

    public void setDrwtNo6(String drwtNo6) {
        this.drwtNo6 = drwtNo6;
    }

    public String getFirstAccumamnt() {
        return firstAccumamnt;
    }

    public void setFirstAccumamnt(String firstAccumamnt) {
        this.firstAccumamnt = firstAccumamnt;
    }

    public String getFirstWinamnt() {
        return firstWinamnt;
    }

    public void setFirstWinamnt(String firstWinamnt) {
        this.firstWinamnt = firstWinamnt;
    }

    public String getTotSellamnt() {
        return totSellamnt;
    }

    public void setTotSellamnt(String totSellamnt) {
        this.totSellamnt = totSellamnt;
    }

    public String getDrwNoDate() {
        return drwNoDate;
    }

    public void setDrwNoDate(String drwNoDate) {
        this.drwNoDate = drwNoDate;
    }

    public String getFirstPrzwnerCo() {
        return firstPrzwnerCo;
    }

    public void setFirstPrzwnerCo(String firstPrzwnerCo) {
        this.firstPrzwnerCo = firstPrzwnerCo;
    }
}
