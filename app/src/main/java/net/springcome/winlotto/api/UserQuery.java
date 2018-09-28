package net.springcome.winlotto.api;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import net.springcome.winlotto.entity.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class UserQuery extends AsyncTaskLoader<User> {
    private final static String LOG_TAG = UserQuery.class.getSimpleName();

    private final static String fetchNextUserIdRequestUrl = "http://www.springcome.net/database/fetch_next_user_id.php";
    int fetchValue = 0;

    public UserQuery(@NonNull Context context, int fetchValue) {
        super(context);
        this.fetchValue = fetchValue;
    }

    @Nullable
    @Override
    public User loadInBackground() {
        User user = new User();

        switch (this.fetchValue) {
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
            JSONObject response = new JSONObject(userJSON);
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
