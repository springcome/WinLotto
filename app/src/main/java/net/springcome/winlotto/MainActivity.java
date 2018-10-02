package net.springcome.winlotto;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import net.springcome.winlotto.api.LottoQuery;
import net.springcome.winlotto.api.UserAsynQuery;
import net.springcome.winlotto.api.UserQuery;
import net.springcome.winlotto.entity.LottoWin;
import net.springcome.winlotto.entity.User;
import net.springcome.winlotto.utils.LottoUtils;
import net.springcome.winlotto.utils.SaveSharedPreference;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<LottoWin> {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    private LottoQuery lottoQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Check if User is Already Logged In
        if (!SaveSharedPreference.getLoggedStatus(getApplicationContext())) {
            checkUserInformation();
        }

        // Query Lotto API
        ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if (ni != null && ni.isConnectedOrConnecting()) {
            getSupportLoaderManager().initLoader(0, null, MainActivity.this).forceLoad();
        }

        // QRScan button
        Button btnQrCodeScan = findViewById(R.id.btn_qr_code_scan);
        btnQrCodeScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent qrScan = new Intent(getApplicationContext(), QRCodeScanActivity.class);
                startActivity(qrScan);
            }
        });

        // Manual input button
        Button btnManual = findViewById(R.id.btn_manual_input);
        btnManual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent manual = new Intent(getApplicationContext(), ManualInputActivity.class);
                startActivity(manual);
            }
        });
//
//        // Make random win number button
//        Button btnMakeNumber = findViewById(R.id.btn_make_win_number);
//        btnMakeNumber.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getApplicationContext(), "개발중", Toast.LENGTH_LONG).show();
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_action_login:
                Intent joinIntent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(joinIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @NonNull
    @Override
    public Loader<LottoWin> onCreateLoader(int id, @Nullable Bundle args) {
        return initLoader();
    }

    private Loader<LottoWin> initLoader() {
        lottoQuery = new LottoQuery(this, null);
        return lottoQuery;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<LottoWin> loader, LottoWin data) {
        if (data != null) {
            updateUI(data);
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<LottoWin> loader) {
        updateUI(new LottoWin());
    }


    private void updateUI(LottoWin data) {
        LottoUtils.fillWinInformation(this, data);
    }

    /**
     * 임시 User ID가 생성되어 있지 않으면 생성후 Preference 저장
     */
    private void checkUserInformation() {
        try {
            User user = new UserAsynQuery().execute(0).get();
            if (user != null && !user.getUserId().isEmpty()) {
                // Preference save
                SaveSharedPreference.setLoggedIn(getApplicationContext(), true);
            }
        } catch (InterruptedException e) {
            Log.e(LOG_TAG, e.getMessage());
        } catch (Exception e) {
            Log.e(LOG_TAG, e.getMessage());
        }
    }
}
