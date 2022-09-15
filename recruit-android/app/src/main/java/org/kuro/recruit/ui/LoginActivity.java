package org.kuro.recruit.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.appcompat.widget.AppCompatButton;

import org.kuro.recruit.MainActivity;
import org.kuro.recruit.R;
import org.kuro.recruit.base.BaseUIActivity;
import org.kuro.recruit.utils.SystemUI;

public class LoginActivity extends BaseUIActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        SystemUI.fixSystemUI(this, true);

        initView();
    }


    private void initView() {

        EditText inputMobile = findViewById(R.id.input_mobile);
        EditText smsCode = findViewById(R.id.sms_code);
        RadioButton loginRead = findViewById(R.id.login_read);
        AppCompatButton loginBtn = findViewById(R.id.login_btn);

        AppCompatButton fetchCode = findViewById(R.id.fetch_code);
        fetchCode.setOnClickListener(v -> {
            fetchCode.setText("60s");
            fetchCode.setEnabled(false);
            fetchCode.setTextColor(getResources().getColor(R.color.gray_tag));
        });

        loginBtn.setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });
    }
}