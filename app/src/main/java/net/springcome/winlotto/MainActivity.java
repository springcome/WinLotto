package net.springcome.winlotto;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import net.springcome.winlotto.adapter.LottoScanArrayAdapter;
import net.springcome.winlotto.adapter.LottoScanBaseAdapter;
import net.springcome.winlotto.api.LottoQuery;
import net.springcome.winlotto.entity.LottoWin;
import net.springcome.winlotto.utils.LottoUtils;
import net.springcome.winlotto.utils.QRScanParse;

import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<LottoWin> {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    private IntentIntegrator qrCodeScan;

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

        // QR Code scan
        qrCodeScan = new IntentIntegrator(this);
        Button btnQrCodeScan = findViewById(R.id.btn_qrCodeScan);
        btnQrCodeScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qrCodeScan.initiateScan();
            }
        });
    }

    @NonNull
    @Override
    public Loader<LottoWin> onCreateLoader(int id, @Nullable Bundle args) {
        return new LottoQuery(this);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<LottoWin> loader, LottoWin data) {
        if (data != null) updateUI(data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<LottoWin> loader) {
        updateUI(new LottoWin());
    }


    private void updateUI(LottoWin data) {
        TextView viewDrwNo = findViewById(R.id.view_drwNo);
        viewDrwNo.setText(data.getDrwNo());

        TextView viewDrwDate = findViewById(R.id.view_drwDate);
        viewDrwDate.setText("["+data.getDrwNoDate()+"]");


        TextView viewDrwtNo1 = findViewById(R.id.view_drwtNo1);
        viewDrwtNo1.setText(data.getDrwtNo1());
        GradientDrawable lottoNo1 = (GradientDrawable) viewDrwtNo1.getBackground();
        lottoNo1.setColor(LottoUtils.getLottoColor(Integer.parseInt(data.getDrwtNo1())));

        TextView viewDrwtNo2 = findViewById(R.id.view_drwtNo2);
        viewDrwtNo2.setText(data.getDrwtNo2());
        GradientDrawable lottoNo2 = (GradientDrawable) viewDrwtNo2.getBackground();
        lottoNo2.setColor(LottoUtils.getLottoColor(Integer.parseInt(data.getDrwtNo2())));

        TextView viewDrwtNo3 = findViewById(R.id.view_drwtNo3);
        viewDrwtNo3.setText(data.getDrwtNo3());
        GradientDrawable lottoNo3 = (GradientDrawable) viewDrwtNo3.getBackground();
        lottoNo3.setColor(LottoUtils.getLottoColor(Integer.parseInt(data.getDrwtNo3())));

        TextView viewDrwtNo4 = findViewById(R.id.view_drwtNo4);
        viewDrwtNo4.setText(data.getDrwtNo4());
        GradientDrawable lottoNo4 = (GradientDrawable) viewDrwtNo4.getBackground();
        lottoNo4.setColor(LottoUtils.getLottoColor(Integer.parseInt(data.getDrwtNo4())));

        TextView viewDrwtNo5 = findViewById(R.id.view_drwtNo5);
        viewDrwtNo5.setText(data.getDrwtNo5());
        GradientDrawable lottoNo5 = (GradientDrawable) viewDrwtNo5.getBackground();
        lottoNo5.setColor(LottoUtils.getLottoColor(Integer.parseInt(data.getDrwtNo5())));

        TextView viewDrwtNo6 = findViewById(R.id.view_drwtNo6);
        viewDrwtNo6.setText(data.getDrwtNo6());
        GradientDrawable lottoNo6 = (GradientDrawable) viewDrwtNo6.getBackground();
        lottoNo6.setColor(LottoUtils.getLottoColor(Integer.parseInt(data.getDrwtNo6())));

        TextView viewBnusNo = findViewById(R.id.view_bnusNo);
        viewBnusNo.setText(data.getBnusNo());
        GradientDrawable bnusNo = (GradientDrawable) viewBnusNo.getBackground();
        bnusNo.setColor(LottoUtils.getLottoColor(Integer.parseInt(data.getBnusNo())));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        Log.i(LOG_TAG, result.getContents());
        if (result != null) {
            List<LottoWin> winNumberList = QRScanParse.parseLottoNumber(result.getContents());
            ListView listView = findViewById(R.id.list_qr_result);
            listView.setAdapter(new LottoScanBaseAdapter(getApplicationContext(), winNumberList));
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
