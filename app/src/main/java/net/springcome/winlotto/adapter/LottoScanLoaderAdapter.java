package net.springcome.winlotto.adapter;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.os.Bundle;

import net.springcome.winlotto.entity.LottoWin;

import java.util.List;

public class LottoScanLoaderAdapter implements LoaderManager.LoaderCallbacks<List<LottoWin>> {
    private Context mContext;
    public LottoScanLoaderAdapter(Context context) {
        mContext = context;
    }

    @Override
    public Loader<List<LottoWin>> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<List<LottoWin>> loader, List<LottoWin> data) {

    }

    @Override
    public void onLoaderReset(Loader<List<LottoWin>> loader) {

    }
}
