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
import android.view.View;
import android.widget.Button;

import net.springcome.winlotto.api.LottoQuery;
import net.springcome.winlotto.entity.LottoWin;
import net.springcome.winlotto.utils.LottoUtils;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<LottoWin> {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    private LottoQuery lottoQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
}
