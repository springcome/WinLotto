package net.springcome.winlotto.api;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import net.springcome.winlotto.entity.LottoWin;
import net.springcome.winlotto.utils.DatabaseContract;
import net.springcome.winlotto.utils.DatabaseHelper;
import net.springcome.winlotto.utils.LottoUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class LottoQuery extends AsyncTaskLoader<LottoWin> {
    private final static String LOG_TAG = LottoQuery.class.getSimpleName();
    private String drwNo;
    private Context context;
    private DatabaseHelper db;

    public LottoQuery(@NonNull Context context, String drwNo) {
        super(context);
        this.drwNo = drwNo;
        this.context = context;
        db = new DatabaseHelper(context, DatabaseContract.DATABASE_NAME, DatabaseContract.DATABASE_VERSION);
    }

    public void setDrwNo(String drwNo) {
        this.drwNo = drwNo;
    }

    @Nullable
    @Override
    public LottoWin loadInBackground() {
        LottoWin lottoWin = db.fetchOneDataForLottoHistory(LottoUtils.currentDrwNo(drwNo));
        String requestUrl = "http://www.nlotto.co.kr/common.do?method=getLottoNumber&drwNo=";
        if (lottoWin == null) {
            requestUrl += LottoUtils.currentDrwNo(drwNo);
            LottoWin queryResult = fetchLottoData(requestUrl);
            db.insertLottoHistory(queryResult);
            return queryResult;
        } else {
            return lottoWin;
        }

    }

    public LottoWin fetchLottoData(String requestUrl) {
        LottoWin lotto = null;
        try {
            lotto = extractFeatureFormJson(CommonQuery.makeHttpRequest(CommonQuery.createUrl(requestUrl)));
        } catch (IOException e) {

        }
        return lotto;
    }
    private LottoWin extractFeatureFormJson(String lottoJSON) {
        LottoWin lotto = null;
        try {
            lotto = new LottoWin();

            JSONObject info = new JSONObject(lottoJSON);

            if (info.getString("returnValue").equals("fail")) {
                setDrwNo(String.valueOf(Integer.parseInt(LottoUtils.currentDrwNo(drwNo))-1));
                return loadInBackground();
            }

            lotto.setDrwNo(info.getString("drwNo"));
            lotto.setBnusNo(info.getString("bnusNo"));
            lotto.setFirstAccumamnt(info.getString("firstAccumamnt"));
            lotto.setFirstWinamnt(info.getString("firstWinamnt"));
            lotto.setFirstPrzwnerCo(info.getString("firstPrzwnerCo"));
            lotto.setTotSellamnt(info.getString("totSellamnt"));
            lotto.setDrwtNo1(info.getString("drwtNo1"));
            lotto.setDrwtNo2(info.getString("drwtNo2"));
            lotto.setDrwtNo3(info.getString("drwtNo3"));
            lotto.setDrwtNo4(info.getString("drwtNo4"));
            lotto.setDrwtNo5(info.getString("drwtNo5"));
            lotto.setDrwtNo6(info.getString("drwtNo6"));
            lotto.setDrwNoDate(info.getString("drwNoDate"));
            lotto.setReturnValue(info.getString("returnValue"));
        } catch (JSONException e) {
            Log.e(LOG_TAG, e.getMessage());
        } catch (Exception e) {
            Log.e(LOG_TAG, e.getMessage());
        }
        return lotto;
    }
}
