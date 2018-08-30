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

import org.w3c.dom.Text;

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
        String [] gameNoArr = {"A", "B", "C", "D", "E"};
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_item_main, null);
        }

        TextView gameNo = view.findViewById(R.id.view_game_no);
        gameNo.setText(gameNoArr[i]);

        TextView compareResult = view.findViewById(R.id.view_compare_result);
        compareResult.setText(LottoUtils.winResult(lottoWin, listData.get(i)));

        TextView scanNo1 = view.findViewById(R.id.view_scanNo1);
        scanNo1.setText(listData.get(i).getDrwtNo1());
        scanNo1.setBackground(LottoUtils.makeLottoBall(LottoUtils.compareWinNumber(lottoWin, listData.get(i).getDrwtNo1())));

        TextView scanNo2 = view.findViewById(R.id.view_scanNo2);
        scanNo2.setText(listData.get(i).getDrwtNo2());
        scanNo2.setBackground(LottoUtils.makeLottoBall(LottoUtils.compareWinNumber(lottoWin, listData.get(i).getDrwtNo2())));

        TextView scanNo3 = view.findViewById(R.id.view_scanNo3);
        scanNo3.setText(listData.get(i).getDrwtNo3());
        scanNo3.setBackground(LottoUtils.makeLottoBall(LottoUtils.compareWinNumber(lottoWin, listData.get(i).getDrwtNo3())));

        TextView scanNo4 = view.findViewById(R.id.view_scanNo4);
        scanNo4.setText(listData.get(i).getDrwtNo4());
        scanNo4.setBackground(LottoUtils.makeLottoBall(LottoUtils.compareWinNumber(lottoWin, listData.get(i).getDrwtNo4())));

        TextView scanNo5 = view.findViewById(R.id.view_scanNo5);
        scanNo5.setText(listData.get(i).getDrwtNo5());
        scanNo5.setBackground(LottoUtils.makeLottoBall(LottoUtils.compareWinNumber(lottoWin, listData.get(i).getDrwtNo5())));

        TextView scanNo6 = view.findViewById(R.id.view_scanNo6);
        scanNo6.setText(listData.get(i).getDrwtNo6());
        scanNo6.setBackground(LottoUtils.makeLottoBall(LottoUtils.compareWinNumber(lottoWin, listData.get(i).getDrwtNo6())));

        return view;
    }
}
