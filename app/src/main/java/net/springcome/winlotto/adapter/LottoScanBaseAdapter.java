package net.springcome.winlotto.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import net.springcome.winlotto.R;
import net.springcome.winlotto.entity.LottoWin;

import java.util.List;

public class LottoScanBaseAdapter extends BaseAdapter {
    private Context context;
    private List<LottoWin> listData;

    public LottoScanBaseAdapter(Context context, List<LottoWin> listData) {
        this.context = context;
        this.listData = listData;
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_item_main, null);
        }

        TextView scanNo1 = (TextView) view.findViewById(R.id.view_scanNo1);
        scanNo1.setText(listData.get(i).getDrwtNo1());
        TextView scanNo2 = (TextView) view.findViewById(R.id.view_scanNo2);
        scanNo2.setText(listData.get(i).getDrwtNo2());
        TextView scanNo3 = (TextView) view.findViewById(R.id.view_scanNo3);
        scanNo3.setText(listData.get(i).getDrwtNo3());
        TextView scanNo4 = (TextView) view.findViewById(R.id.view_scanNo4);
        scanNo4.setText(listData.get(i).getDrwtNo4());
        TextView scanNo5 = (TextView) view.findViewById(R.id.view_scanNo5);
        scanNo5.setText(listData.get(i).getDrwtNo5());
        TextView scanNo6 = (TextView) view.findViewById(R.id.view_scanNo6);
        scanNo6.setText(listData.get(i).getDrwtNo6());

        return view;
    }
}
