package org.kuro.recruit.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.Settings;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;

import com.google.gson.Gson;

import org.json.JSONObject;
import org.kuro.recruit.MainActivity;
import org.kuro.recruit.R;
import org.kuro.recruit.api.Callback;
import org.kuro.recruit.api.Http;
import org.kuro.recruit.base.BaseUIActivity;
import org.kuro.recruit.config.ApiConfig;
import org.kuro.recruit.config.MessageEnum;
import org.kuro.recruit.model.entity.Result;
import org.kuro.recruit.model.entity.User;
import org.kuro.recruit.model.res.LoginRes;
import org.kuro.recruit.model.res.LoginSuccessRes;
import org.kuro.recruit.utils.CodeTimeCount;
import org.kuro.recruit.utils.SpUtil;
import org.kuro.recruit.utils.SystemUI;
import org.kuro.recruit.view.message.ToastMsg;

import java.util.HashMap;
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
        SpannableString agreement = new SpannableString("登录即同意《用户协议》和《隐私政策》");
        agreement.setSpan(new ForegroundColorSpan(Color.parseColor("#333333")), 0, 5, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        agreement.setSpan(new ForegroundColorSpan(Color.parseColor("#3B73F6")), 5, 11, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        agreement.setSpan(new ForegroundColorSpan(Color.parseColor("#333333")), 11, 12, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        agreement.setSpan(new ForegroundColorSpan(Color.parseColor("#3B73F6")), 12, agreement.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        loginRead.setText(agreement);

        // 微信、QQ登录
        qqLogin.setOnClickListener(v -> ToastMsg.error(this, MessageEnum.FUNCTION_NOT_EXIST));
        wechatLogin.setOnClickListener(v -> ToastMsg.error(this, MessageEnum.FUNCTION_NOT_EXIST));
    }


    private void initData() {
        // 获取短信验证码
        fetchCode.setOnClickListener(v -> {
            String mobileForm = inputMobile.getText().toString().trim();
            handleFetchCode(mobileForm);
        });

        loginBtn.setOnClickListener(v -> {
            // 为勾选同意用户协议
            if (!loginRead.isChecked()) {
                ToastMsg.error(this, MessageEnum.AGREEMENT_NOT);
                return;
            }

            String mobileForm = inputMobile.getText().toString().trim();
            String smsForm = smsCode.getText().toString().trim();
            handleLogin(mobileForm, smsForm);
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


    /**
     * 发送HTTP请求，获取短信验证码
     *
     * @param mobile 手机号
     */
    private void handleFetchCode(String mobile) {
        if (valid(mobile, 1)) {
            return;
        }

        Context context = this;
        HashMap<String, Object> param = new HashMap<>();
        param.put("mobile", mobile);
        Http.config(ApiConfig.SMS, param).post(this, new Callback() {
            @Override
            public void onSuccess(String res) {
                runOnUiThread(() -> {
                    Result result = new Gson().fromJson(res, Result.class);
                    if (result.getStatus()) {
                        CodeTimeCount timeCount = new CodeTimeCount(60000, 1000, fetchCode);
                        timeCount.start();
                        ToastMsg.success(context, result.getMessage());
                    } else {
                        ToastMsg.error(context, result.getMessage());
                    }
                });
            }

            @Override
            public void onFailure(Exception e) {
            }
        });
    }


    /**
     * 用户登录/注册
     *
     * @param mobile 手机号
     * @param code   验证码
     */
    @SuppressLint("HardwareIds")
    private void handleLogin(String mobile, String code) {
        // 校验手机号
        if (valid(mobile, 1)) {
            return;
        }

        // 校验短信验证码
        if (valid(code, 2)) {
            return;
        }

        // 获取 androidId
        String androidId = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        HashMap<String, Object> params = new HashMap<>();
        params.put("mobile", mobile);
        params.put("code", code);
        params.put("clientId", androidId);

        Http.config(ApiConfig.LOGIN, params).post(this, new Callback() {
            @Override
            public void onSuccess(String res) {
                LoginRes result = new Gson().fromJson(res, LoginRes.class);
                if (result.getStatus()) {
                    User user = result.getData().getUser();
                    String token = result.getData().getToken();
                    loginSuccess(user, token);
                }
                showToastSync(result.getMessage());
            }

            @Override
            public void onFailure(Exception e) {
            }
        });
    }


    /**
     * 登录成功回调
     *
     * @param user  用户数据
     * @param token token
     */
    private void loginSuccess(User user, String token) {
        SpUtil.getInstance().putString("token", token);
        SpUtil.getInstance().putString("userId", user.getId());
        SpUtil.getInstance().putString("nickname", user.getNickname());
        SpUtil.getInstance().putString("avatar", user.getAvatar());
        SpUtil.getInstance().putString("mobile", user.getMobile());
        SpUtil.getInstance().putString("describe", user.getSelfDescribe());
        navigateToWithFlag(MainActivity.class, this);
    }
}