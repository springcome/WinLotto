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
import android.widget.ListView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import net.springcome.winlotto.adapter.LottoScanBaseAdapter;
import net.springcome.winlotto.api.LottoQuery;
import net.springcome.winlotto.entity.LottoWin;
import net.springcome.winlotto.utils.DatabaseContract;
import net.springcome.winlotto.utils.DatabaseHelper;
import net.springcome.winlotto.utils.LottoUtils;
import net.springcome.winlotto.utils.QRScanParse;

import java.util.ArrayList;
import java.util.List;

public class QRCodeScanActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<LottoWin> {
    private static final String LOG_TAG = QRCodeScanActivity.class.getSimpleName();

    private IntentIntegrator qrCodeScan;
    private List<LottoWin> scanList;
    private LottoQuery lottoQuery;
    private DatabaseHelper db;

    private String drwNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode_scan);

        // QR Code scan
        qrCodeScan = new IntentIntegrator(this);
        qrCodeScan.initiateScan();

        db = new DatabaseHelper(getApplicationContext(), DatabaseContract.DATABASE_NAME, DatabaseContract.DATABASE_VERSION);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null && result.getContents() != null) {
            scanList = QRScanParse.parseLottoNumber(result.getContents());

            // Scan한 회차번호
            drwNo = scanList.get(0).getDrwNo();

            if (Integer.parseInt(drwNo) > Integer.parseInt(LottoUtils.currentDrwNo(null))) {
                Toast.makeText(getApplicationContext(), R.string.msg_can_not_win_number, Toast.LENGTH_LONG).show();
                finish();
            }

            // Query API of Lotto
            ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo ni = cm.getActiveNetworkInfo();
            if (ni != null && ni.isConnectedOrConnecting()) {
                getSupportLoaderManager().initLoader(1, null, QRCodeScanActivity.this).forceLoad();
            }
        } else {
            finish();
        }
    }

    @NonNull
    @Override
    public Loader<LottoWin> onCreateLoader(int id, @Nullable Bundle args) {
        lottoQuery = new LottoQuery(this, drwNo);
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
        if (scanList != null)
            listView.setAdapter(new LottoScanBaseAdapter(getApplicationContext(), data, scanList));

        LottoUtils.fillWinInformation(this, data);
    }
}
