package net.springcome.winlotto.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import net.springcome.winlotto.entity.LottoWin;

import java.util.List;

public class LottoScanArrayAdapter extends ArrayAdapter<LottoWin> {
    private static final String LOG_TAG = LottoScanArrayAdapter.class.getSimpleName();

    public LottoScanArrayAdapter(@NonNull Context context, int resource, @NonNull List<LottoWin> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }
}
