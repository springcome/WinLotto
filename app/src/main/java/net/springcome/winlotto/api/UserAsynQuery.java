package net.springcome.winlotto.api;

import android.os.AsyncTask;
import android.util.Log;

import net.springcome.winlotto.entity.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class UserAsynQuery extends AsyncTask<Integer, Integer, User> {
    private final static String LOG_TAG = UserAsynQuery.class.getSimpleName();
    private final static String fetchNextUserIdRequestUrl = "http://www.springcome.net/database/fetch_next_user_id.php";

    @Override
    protected User doInBackground(Integer... fetchValue) {
        User user = null;
        switch (fetchValue[0]) {
            case 0:
                user = fetchNextUserIdData(fetchNextUserIdRequestUrl);
                break;
            default:
                break;
        }
        return user;
    }

    private User fetchNextUserIdData(String requestUrl) {
        User user = null;
        try {
            user = extractFeatureFormJson(CommonQuery.makeHttpRequest(CommonQuery.createUrl(requestUrl)));
        } catch (IOException e) {

        }
        return user;
    }

    private User extractFeatureFormJson(String userJSON) {
        User user = null;
        try {
            user = new User();
            JSONObject json = new JSONObject(userJSON);
            JSONObject response = json.getJSONObject("response");
            JSONObject meta = response.getJSONObject("meta");
            JSONObject data = response.getJSONObject("data");
            if (meta.getString("code").equals("200")) {
                user.setUserId(data.getString("user_id"));
            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, e.getMessage());
        } catch (Exception e) {
            Log.e(LOG_TAG, e.getMessage());
        }
        return user;
    }
}
