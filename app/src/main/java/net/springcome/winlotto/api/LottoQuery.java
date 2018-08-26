package net.springcome.winlotto.api;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import net.springcome.winlotto.entity.LottoWin;
import net.springcome.winlotto.utils.LottoUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

public class LottoQuery extends AsyncTaskLoader<LottoWin> {
    private final static String LOG_TAG = LottoQuery.class.getSimpleName();

    public LottoQuery(@NonNull Context context) {
        super(context);
    }

    @Nullable
    @Override
    public LottoWin loadInBackground() {
        String requestUrl = "http://www.nlotto.co.kr/common.do?method=getLottoNumber&drwNo=" + LottoUtils.currentDrwNo();

        return fetchLottoData(requestUrl);
    }

    public static LottoWin fetchLottoData(String requestUrl) {
        LottoWin lotto = null;
        try {
            lotto = extractFeatureFormJson(makeHttpRequest(createUrl(requestUrl)));
        } catch (IOException e) {

        }
        return lotto;
    }

    private static URL createUrl(String requestUrl) {
        URL url = null;
        try {
            url = new URL(requestUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, e.getMessage());
        }
        return url;
    }

    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.connect();

            inputStream = urlConnection.getInputStream();
            jsonResponse = readFormStream(inputStream);
        } finally {
            if (urlConnection != null) urlConnection.disconnect();
            if (inputStream != null) inputStream.close();
        }
        return jsonResponse;
    }

    private static LottoWin extractFeatureFormJson(String lottoJSON) {
        LottoWin lotto = null;
        try {
            lotto = new LottoWin();

            JSONObject info = new JSONObject(lottoJSON);
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
        } catch (JSONException e) {
            Log.e(LOG_TAG, e.getMessage());
        } catch (Exception e) {
            Log.e(LOG_TAG, e.getMessage());
        }
        return lotto;
    }

    private static String readFormStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }
}
