package net.springcome.winlotto;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import net.springcome.winlotto.api.LottoQuery;
import net.springcome.winlotto.entity.LottoWin;
import net.springcome.winlotto.utils.LottoUtils;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<LottoWin> {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    private LottoQuery lottoQuery;
    List<LottoWin> winNumberList;

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

//        // Manual input button
//        Button btnManual = findViewById(R.id.btn_manual_input);
//        btnManual.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getApplicationContext(), "개발중", Toast.LENGTH_LONG).show();
//            }
//        });
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
//        return new LottoQuery(this, null);
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
        TextView viewDrwNo = findViewById(R.id.view_drwNo);
        viewDrwNo.setText(data.getDrwNo());

        TextView viewFirstPrzwnerCo = findViewById(R.id.view_first_przwner_co);
        viewFirstPrzwnerCo.setText(data.getFirstPrzwnerCo());
        TextView viewFirstAccumamnt = findViewById(R.id.view_first_accumamnt);
        viewFirstAccumamnt.setText(LottoUtils.aboutFormatPrice(data.getFirstAccumamnt()));
        TextView viewFirstWinamnt = findViewById(R.id.view_first_winamnt);
        viewFirstWinamnt.setText(LottoUtils.aboutFormatPrice(data.getFirstWinamnt()));

        TextView viewDrwtNo1 = findViewById(R.id.view_drwtNo1);
        viewDrwtNo1.setText(data.getDrwtNo1());
        viewDrwtNo1.setBackground(LottoUtils.makeLottoBall(LottoUtils.getLottoColor(Integer.parseInt(data.getDrwtNo1()))));

        TextView viewDrwtNo2 = findViewById(R.id.view_drwtNo2);
        viewDrwtNo2.setText(data.getDrwtNo2());
        viewDrwtNo2.setBackground(LottoUtils.makeLottoBall(LottoUtils.getLottoColor(Integer.parseInt(data.getDrwtNo2()))));

        TextView viewDrwtNo3 = findViewById(R.id.view_drwtNo3);
        viewDrwtNo3.setText(data.getDrwtNo3());
        viewDrwtNo3.setBackground(LottoUtils.makeLottoBall(LottoUtils.getLottoColor(Integer.parseInt(data.getDrwtNo3()))));

        TextView viewDrwtNo4 = findViewById(R.id.view_drwtNo4);
        viewDrwtNo4.setText(data.getDrwtNo4());
        viewDrwtNo4.setBackground(LottoUtils.makeLottoBall(LottoUtils.getLottoColor(Integer.parseInt(data.getDrwtNo4()))));

        TextView viewDrwtNo5 = findViewById(R.id.view_drwtNo5);
        viewDrwtNo5.setText(data.getDrwtNo5());
        viewDrwtNo5.setBackground(LottoUtils.makeLottoBall(LottoUtils.getLottoColor(Integer.parseInt(data.getDrwtNo5()))));

        TextView viewDrwtNo6 = findViewById(R.id.view_drwtNo6);
        viewDrwtNo6.setText(data.getDrwtNo6());
        viewDrwtNo6.setBackground(LottoUtils.makeLottoBall(LottoUtils.getLottoColor(Integer.parseInt(data.getDrwtNo6()))));

        TextView viewBnusNo = findViewById(R.id.view_bnusNo);
        viewBnusNo.setText(data.getBnusNo());
        viewBnusNo.setBackground(LottoUtils.makeLottoBall(LottoUtils.getLottoColor(Integer.parseInt(data.getBnusNo()))));
    }
}
