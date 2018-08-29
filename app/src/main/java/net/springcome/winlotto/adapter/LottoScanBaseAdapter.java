package net.springcome.winlotto.adapter;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import net.springcome.winlotto.R;
import net.springcome.winlotto.entity.LottoWin;
import net.springcome.winlotto.utils.LottoUtils;

import java.util.List;

public class LottoScanBaseAdapter extends BaseAdapter {
    private Context context;
    private LottoWin lottoWin;
    private List<LottoWin> listData;

    public LottoScanBaseAdapter(Context context, LottoWin lottoWin, List<LottoWin> listData) {
        this.context = context;
        this.lottoWin = lottoWin;
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

        TextView scanNo1 = view.findViewById(R.id.view_scanNo1);
        scanNo1.setText(listData.get(i).getDrwtNo1());
        GradientDrawable scanNoDrawableNo1 = (GradientDrawable) scanNo1.getBackground();
        scanNoDrawableNo1.setColor(LottoUtils.compareWinNumber(lottoWin, listData.get(i).getDrwtNo1()));

        TextView scanNo2 = view.findViewById(R.id.view_scanNo2);
        scanNo2.setText(listData.get(i).getDrwtNo2());
        GradientDrawable scanNoDrawableNo2 = (GradientDrawable) scanNo2.getBackground();
        scanNoDrawableNo2.setColor(LottoUtils.compareWinNumber(lottoWin, listData.get(i).getDrwtNo2()));

        TextView scanNo3 = view.findViewById(R.id.view_scanNo3);
        scanNo3.setText(listData.get(i).getDrwtNo3());
        GradientDrawable scanNoDrawableNo3 = (GradientDrawable) scanNo3.getBackground();
        scanNoDrawableNo3.setColor(LottoUtils.compareWinNumber(lottoWin, listData.get(i).getDrwtNo3()));

        TextView scanNo4 = view.findViewById(R.id.view_scanNo4);
        scanNo4.setText(listData.get(i).getDrwtNo4());
        GradientDrawable scanNoDrawableNo4 = (GradientDrawable) scanNo4.getBackground();
        scanNoDrawableNo4.setColor(LottoUtils.compareWinNumber(lottoWin, listData.get(i).getDrwtNo4()));

        TextView scanNo5 = view.findViewById(R.id.view_scanNo5);
        scanNo5.setText(listData.get(i).getDrwtNo5());
        GradientDrawable scanNoDrawableNo5 = (GradientDrawable) scanNo5.getBackground();
        scanNoDrawableNo5.setColor(LottoUtils.compareWinNumber(lottoWin, listData.get(i).getDrwtNo5()));

        TextView scanNo6 = view.findViewById(R.id.view_scanNo6);
        scanNo6.setText(listData.get(i).getDrwtNo6());
        GradientDrawable scanNoDrawableNo6 = (GradientDrawable) scanNo6.getBackground();
        scanNoDrawableNo6.setColor(LottoUtils.compareWinNumber(lottoWin, listData.get(i).getDrwtNo6()));

        return view;
    }
}
