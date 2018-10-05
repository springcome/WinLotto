package net.springcome.winlotto;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import net.springcome.winlotto.entity.User;
import net.springcome.winlotto.utils.DatabaseContract;
import net.springcome.winlotto.utils.DatabaseHelper;
import net.springcome.winlotto.utils.DateUtils;
import net.springcome.winlotto.utils.PreferencesUtility;

public class JoinActivity extends AppCompatActivity {
    private static final String LOG_TAG = JoinActivity.class.getSimpleName();
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        db = new DatabaseHelper(getApplicationContext(), DatabaseContract.DATABASE_NAME, DatabaseContract.DATABASE_VERSION);

        AppCompatButton btnSignup = findViewById(R.id.btn_signup);
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signup();
            }
        });

        TextView linkLogin = findViewById(R.id.link_login);
        linkLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void signup() {
        if (!validate()) {
            Toast.makeText(getBaseContext(), "가입중 오류발생", Toast.LENGTH_SHORT).show();
            return;
        }
        EditText editName = findViewById(R.id.input_name);
        EditText editMail = findViewById(R.id.input_email);
        EditText editPwd = findViewById(R.id.input_password);

        // 회원가입 - 사용자는 임시아이디를 발급받으며 그에 대한 사용자 데이터를 이미 저장하고 있다.
        //          - 추가로 입력한 데이터에 대한 UPDATE 를 진행한다.
        User user = new User();
        user.setUserId(PreferencesUtility.getLoggedInUserId(getApplicationContext()));
        user.setUserNm(editName.getText().toString());
        user.setUserEmail(editMail.getText().toString());
        user.setUserPwd(editPwd.getText().toString());
        user.setUserGrad("A");
        user.setUserJoinDate(DateUtils.getNowDate());
        int result = db.updateUser(user);
        if (result == 1) {
            Toast.makeText(getApplicationContext(), "가입이 완료되었습니다. 로그인해주세요.", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Log.e(LOG_TAG, "Return value is " + result);
        }
    }

    private boolean validate() {
        EditText editName = findViewById(R.id.input_name);
        EditText editMail = findViewById(R.id.input_email);
        EditText editPwd = findViewById(R.id.input_password);
        if (editName == null || editName.getText().toString().isEmpty()) {
            editName.setError("가입 별칭을 입력해주세요.");
            return false;
        } else {
            if (editName.getText().toString().length() < 2) {
                editName.setError("너무 짧습니다. 1글자 이상 입력해주세요.");
                return false;
            } else {
                editName.setError(null);
            }
        }
        if (editMail == null || editMail.getText().toString().isEmpty()) {
            editMail.setError("이메일을 입력해주세요.");
            return false;
        } else {
            if (!Patterns.EMAIL_ADDRESS.matcher(editMail.getText().toString()).matches()) {
                editMail.setError("이메일 형식에 맞지 않습니다.");
                return false;
            } else {
                editMail.setError(null);
            }
        }
        if (editPwd == null || editPwd.getText().toString().isEmpty()) {
            editPwd.setError("비밀번호를 입력해주세요.");
            return false;
        } else {
            if (editPwd.getText().toString().length() < 4 || editPwd.getText().toString().length() > 20) {
                editPwd.setError("비밀번호는 4자리 이상 20자리 이하로 입력해주세요.");
                return false;
            } else {
                editPwd.setError(null);
            }
        }

        return true;
    }
}
