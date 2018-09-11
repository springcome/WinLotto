package net.springcome.winlotto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import net.springcome.winlotto.utils.LottoUtils;

import java.util.ArrayList;
import java.util.List;

public class ManualInputActivity extends AppCompatActivity implements View.OnClickListener {

    private List<String> chooseBallList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_input);

        TextView ballNo01 = findViewById(R.id.ball_no01);
        ballNo01.setOnClickListener(this);

        chooseBallList = new ArrayList<>();

//        @Override
//        public void onClick(View view) {
//            List<String> chooseBallList = new ArrayList<>();
//
//            switch (view.getId()) {
//                case R.id.ball_no01 :
//                    chooseBallList.add(((TextView)view).getText().toString());
//                default:
//                    Toast.makeText(getApplicationContext(), "notting", Toast.LENGTH_SHORT).show();
//            }
//
//            if (chooseBallList.size() == 6) {
//                Toast.makeText(getApplicationContext(), chooseBallList.toString(), Toast.LENGTH_LONG).show();
//            }
//        }

//        final TextView ball01 = findViewById(R.id.ball_no01);
//        ball01.setBackground(LottoUtils.makeLottoBall(LottoUtils.getLottoColor(1)));
//        ball01.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getApplicationContext(), ((TextView) view).getText(), Toast.LENGTH_SHORT).show();
//            }
//        });
//        TextView ball02 = findViewById(R.id.ball_no02);
//        ball02.setBackground(LottoUtils.makeLottoBall(LottoUtils.getLottoColor(2)));
//        TextView ball03 = findViewById(R.id.ball_no03);
//        ball03.setBackground(LottoUtils.makeLottoBall(LottoUtils.getLottoColor(3)));
//        TextView ball04 = findViewById(R.id.ball_no04);
//        ball04.setBackground(LottoUtils.makeLottoBall(LottoUtils.getLottoColor(4)));
//        TextView ball05 = findViewById(R.id.ball_no05);
//        ball05.setBackground(LottoUtils.makeLottoBall(LottoUtils.getLottoColor(5)));
//        TextView ball06 = findViewById(R.id.ball_no06);
//        ball06.setBackground(LottoUtils.makeLottoBall(LottoUtils.getLottoColor(6)));
//        TextView ball07 = findViewById(R.id.ball_no07);
//        ball07.setBackground(LottoUtils.makeLottoBall(LottoUtils.getLottoColor(7)));
//        TextView ball08 = findViewById(R.id.ball_no08);
//        ball08.setBackground(LottoUtils.makeLottoBall(LottoUtils.getLottoColor(8)));
//        TextView ball09 = findViewById(R.id.ball_no09);
//        ball09.setBackground(LottoUtils.makeLottoBall(LottoUtils.getLottoColor(9)));
//        TextView ball10 = findViewById(R.id.ball_no10);
//        ball10.setBackground(LottoUtils.makeLottoBall(LottoUtils.getLottoColor(10)));
//        TextView ball11 = findViewById(R.id.ball_no11);
//        ball11.setBackground(LottoUtils.makeLottoBall(LottoUtils.getLottoColor(11)));
//        TextView ball12 = findViewById(R.id.ball_no12);
//        ball12.setBackground(LottoUtils.makeLottoBall(LottoUtils.getLottoColor(12)));
//        TextView ball13 = findViewById(R.id.ball_no13);
//        ball13.setBackground(LottoUtils.makeLottoBall(LottoUtils.getLottoColor(13)));
//        TextView ball14 = findViewById(R.id.ball_no14);
//        ball14.setBackground(LottoUtils.makeLottoBall(LottoUtils.getLottoColor(14)));
//        TextView ball15 = findViewById(R.id.ball_no15);
//        ball15.setBackground(LottoUtils.makeLottoBall(LottoUtils.getLottoColor(15)));
//        TextView ball16 = findViewById(R.id.ball_no16);
//        ball16.setBackground(LottoUtils.makeLottoBall(LottoUtils.getLottoColor(16)));
//        TextView ball17 = findViewById(R.id.ball_no17);
//        ball17.setBackground(LottoUtils.makeLottoBall(LottoUtils.getLottoColor(17)));
//        TextView ball18 = findViewById(R.id.ball_no18);
//        ball18.setBackground(LottoUtils.makeLottoBall(LottoUtils.getLottoColor(18)));
//        TextView ball19 = findViewById(R.id.ball_no19);
//        ball19.setBackground(LottoUtils.makeLottoBall(LottoUtils.getLottoColor(19)));
//        TextView ball20 = findViewById(R.id.ball_no20);
//        ball20.setBackground(LottoUtils.makeLottoBall(LottoUtils.getLottoColor(20)));
//        TextView ball21 = findViewById(R.id.ball_no21);
//        ball21.setBackground(LottoUtils.makeLottoBall(LottoUtils.getLottoColor(21)));
//        TextView ball22 = findViewById(R.id.ball_no22);
//        ball22.setBackground(LottoUtils.makeLottoBall(LottoUtils.getLottoColor(22)));
//        TextView ball23 = findViewById(R.id.ball_no23);
//        ball23.setBackground(LottoUtils.makeLottoBall(LottoUtils.getLottoColor(23)));
//        TextView ball24 = findViewById(R.id.ball_no24);
//        ball24.setBackground(LottoUtils.makeLottoBall(LottoUtils.getLottoColor(24)));
//        TextView ball25 = findViewById(R.id.ball_no25);
//        ball25.setBackground(LottoUtils.makeLottoBall(LottoUtils.getLottoColor(25)));
//        TextView ball26 = findViewById(R.id.ball_no26);
//        ball26.setBackground(LottoUtils.makeLottoBall(LottoUtils.getLottoColor(26)));
//        TextView ball27 = findViewById(R.id.ball_no27);
//        ball27.setBackground(LottoUtils.makeLottoBall(LottoUtils.getLottoColor(27)));
//        TextView ball28 = findViewById(R.id.ball_no28);
//        ball28.setBackground(LottoUtils.makeLottoBall(LottoUtils.getLottoColor(28)));
//        TextView ball29 = findViewById(R.id.ball_no29);
//        ball29.setBackground(LottoUtils.makeLottoBall(LottoUtils.getLottoColor(29)));
//        TextView ball30 = findViewById(R.id.ball_no30);
//        ball30.setBackground(LottoUtils.makeLottoBall(LottoUtils.getLottoColor(30)));
//        TextView ball31 = findViewById(R.id.ball_no31);
//        ball31.setBackground(LottoUtils.makeLottoBall(LottoUtils.getLottoColor(31)));
//        TextView ball32 = findViewById(R.id.ball_no32);
//        ball32.setBackground(LottoUtils.makeLottoBall(LottoUtils.getLottoColor(32)));
//        TextView ball33 = findViewById(R.id.ball_no33);
//        ball33.setBackground(LottoUtils.makeLottoBall(LottoUtils.getLottoColor(33)));
//        TextView ball34 = findViewById(R.id.ball_no34);
//        ball34.setBackground(LottoUtils.makeLottoBall(LottoUtils.getLottoColor(34)));
//        TextView ball35 = findViewById(R.id.ball_no35);
//        ball35.setBackground(LottoUtils.makeLottoBall(LottoUtils.getLottoColor(35)));
//        TextView ball36 = findViewById(R.id.ball_no36);
//        ball36.setBackground(LottoUtils.makeLottoBall(LottoUtils.getLottoColor(36)));
//        TextView ball37 = findViewById(R.id.ball_no37);
//        ball37.setBackground(LottoUtils.makeLottoBall(LottoUtils.getLottoColor(37)));
//        TextView ball38 = findViewById(R.id.ball_no38);
//        ball38.setBackground(LottoUtils.makeLottoBall(LottoUtils.getLottoColor(38)));
//        TextView ball39 = findViewById(R.id.ball_no39);
//        ball39.setBackground(LottoUtils.makeLottoBall(LottoUtils.getLottoColor(39)));
//        TextView ball40 = findViewById(R.id.ball_no40);
//        ball40.setBackground(LottoUtils.makeLottoBall(LottoUtils.getLottoColor(40)));
//        TextView ball41 = findViewById(R.id.ball_no41);
//        ball41.setBackground(LottoUtils.makeLottoBall(LottoUtils.getLottoColor(41)));
//        TextView ball42 = findViewById(R.id.ball_no42);
//        ball42.setBackground(LottoUtils.makeLottoBall(LottoUtils.getLottoColor(42)));
//        TextView ball43 = findViewById(R.id.ball_no43);
//        ball43.setBackground(LottoUtils.makeLottoBall(LottoUtils.getLottoColor(43)));
//        TextView ball44 = findViewById(R.id.ball_no44);
//        ball44.setBackground(LottoUtils.makeLottoBall(LottoUtils.getLottoColor(44)));
//        TextView ball45 = findViewById(R.id.ball_no45);
//        ball45.setBackground(LottoUtils.makeLottoBall(LottoUtils.getLottoColor(45)));
//
//        ImageButton btnDelete = findViewById(R.id.btn_delete);
//        ImageButton btnDone = findViewById(R.id.btn_done);
    }

    @Override
    public void onClick(View view) {


        switch (view.getId()) {
            case R.id.ball_no01 :
                chooseBallList.add(((TextView)view).getText().toString());
                break;
            default:
                Toast.makeText(getApplicationContext(), "notting", Toast.LENGTH_SHORT).show();
        }

        if (chooseBallList.size() == 6) {

        }

        ListView listManual = findViewById(R.id.list_manual);
    }


}
