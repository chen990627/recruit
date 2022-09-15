package org.kuro.recruit.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;

import org.kuro.recruit.MainActivity;
import org.kuro.recruit.R;
import org.kuro.recruit.base.BaseUIActivity;
import org.kuro.recruit.config.MessageEnum;
import org.kuro.recruit.utils.CodeTimeCount;
import org.kuro.recruit.utils.SystemUI;
import org.kuro.recruit.view.message.ToastMsg;

import java.util.regex.Pattern;

public class LoginActivity extends BaseUIActivity {

    private EditText inputMobile;
    private EditText smsCode;
    private AppCompatButton fetchCode;
    private RadioButton loginRead;
    private AppCompatButton loginBtn;
    private TextView qqLogin;
    private TextView wechatLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        SystemUI.fixSystemUI(this, true);

        initView();
        initData();
    }


    private void initView() {

        inputMobile = findViewById(R.id.input_mobile);
        smsCode = findViewById(R.id.sms_code);
        fetchCode = findViewById(R.id.fetch_code);
        loginRead = findViewById(R.id.login_read);
        loginBtn = findViewById(R.id.login_btn);

        qqLogin = findViewById(R.id.other_login_qq);
        wechatLogin = findViewById(R.id.other_login_wechat);

        // 给用户协议设置不同的颜色
        SpannableString agreement = new SpannableString("已阅读并同意《用户协议》");
        agreement.setSpan(new ForegroundColorSpan(Color.parseColor("#333333")), 0, 6, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        agreement.setSpan(new ForegroundColorSpan(Color.parseColor("#3B73F6")), 6, agreement.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        loginRead.setText(agreement);

        // 微信、QQ登录
        qqLogin.setOnClickListener(v -> ToastMsg.error(this, MessageEnum.FUNCTION_NOT_EXIST));
        wechatLogin.setOnClickListener(v -> ToastMsg.error(this, MessageEnum.FUNCTION_NOT_EXIST));
    }


    private void initData() {
        fetchCode.setOnClickListener(v -> {
            // 校验手机号
            String mobileForm = inputMobile.getText().toString().trim();
            if (valid(mobileForm, 1)) {
                return;
            }

            // todo 获取短信验证码
            CodeTimeCount timeCount = new CodeTimeCount(60000, 1000, fetchCode);
            timeCount.start();
        });

        loginBtn.setOnClickListener(v -> {
            // 为勾选同意用户协议
            if (!loginRead.isChecked()) {
                ToastMsg.error(this, MessageEnum.AGREEMENT_NOT);
                return;
            }

            // 校验手机号
//            String mobileForm = inputMobile.getText().toString().trim();
//            if (valid(mobileForm, 1)) {
//                return;
//            }

            // 校验短信验证码
//            String smsForm = smsCode.getText().toString().trim();
//            if (valid(smsForm, 2)) {
//                return;
//            }

            // todo 发送api请求

            startActivity(new Intent(this, MainActivity.class));
            finish();
        });
    }


    /**
     * 校验短信验证码、手机号
     *
     * @param param 参数
     * @param t     1：手机号，2验证码
     * @return true: 不合法, false: 合法
     */
    private Boolean valid(String param, Integer t) {
        String messageEmpty;
        String messageRex;
        String regex;
        if (t == 1) {
            messageEmpty = MessageEnum.MOBILE_NOT_EMPTY.getMessage();
            messageRex = MessageEnum.MOBILE_PATTERN_ERROR.getMessage();
            regex = "^(13[0-9]|14[01456879]|15[0-9]|16[2567]|17[0-9]|18[0-9]|19[0-9])\\d{8}$";
        } else {
            messageEmpty = MessageEnum.SMS_NOT_EMPTY.getMessage();
            messageRex = MessageEnum.SMS_PATTERN_ERROR.getMessage();
            regex = "^\\d{6}$";
        }

        if (TextUtils.isEmpty(param)) {
            ToastMsg.error(this, messageEmpty);
            return true;
        }

        if (!Pattern.matches(regex, param)) {
            ToastMsg.error(this, messageRex);
            return true;
        }

        return false;
    }

}