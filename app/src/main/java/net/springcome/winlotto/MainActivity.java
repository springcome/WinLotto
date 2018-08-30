package net.springcome.winlotto;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import net.springcome.winlotto.adapter.LottoScanArrayAdapter;
import net.springcome.winlotto.adapter.LottoScanBaseAdapter;
import net.springcome.winlotto.api.LottoQuery;
import net.springcome.winlotto.entity.LottoWin;
import net.springcome.winlotto.utils.LottoUtils;
import net.springcome.winlotto.utils.QRScanParse;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<LottoWin> {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    private IntentIntegrator qrCodeScan;

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
//        return new LottoQuery(this, null);
        return initLoader();
    }

    private Loader<LottoWin> initLoader() {
        lottoQuery = new LottoQuery(this);
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
        ListView listView = findViewById(R.id.list_qr_result);
        if (data.getDrwNo() == null) {
            Snackbar.make(listView, "아직 추첨되지 않은 게임입니다.", Snackbar.LENGTH_LONG).show();
            listView.setAdapter(new LottoScanBaseAdapter(getApplicationContext(), new LottoWin(), new ArrayList<LottoWin>()));
            return;
        }
        if (winNumberList != null)
            listView.setAdapter(new LottoScanBaseAdapter(getApplicationContext(), data, winNumberList));

        TextView viewDrwNo = findViewById(R.id.view_drwNo);
        viewDrwNo.setText(data.getDrwNo());

//        TextView viewDrwDate = findViewById(R.id.view_drwDate);
//        viewDrwDate.setText("["+data.getDrwNoDate()+"]");

        TextView viewFirstPrzwnerCo = findViewById(R.id.view_first_przwner_co);
        viewFirstPrzwnerCo.setText(data.getFirstPrzwnerCo());
        TextView viewFirstAccumamnt = findViewById(R.id.view_first_accumamnt);
        viewFirstAccumamnt.setText(LottoUtils.formatPrice(data.getFirstAccumamnt()));
        TextView viewFirstWinamnt = findViewById(R.id.view_first_winamnt);
        viewFirstWinamnt.setText(LottoUtils.formatPrice(data.getFirstWinamnt()));

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null && result.getContents() != null) {
            winNumberList = QRScanParse.parseLottoNumber(result.getContents());

            // Scan한 회차번호
            String drwNo = winNumberList.get(0).getDrwNo();
            // Scan한 회차번호와 가장최근 회차번호가 일치 하지 않을때만 당첨정보를 다시 조회한다.
            if (!LottoUtils.currentDrwNo(null).equals(drwNo)) {
                lottoQuery.setDrwNo(drwNo);
                getSupportLoaderManager().initLoader(0, null, this).forceLoad();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
