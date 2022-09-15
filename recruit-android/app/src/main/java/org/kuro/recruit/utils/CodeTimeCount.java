package org.kuro.recruit.utils;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.CountDownTimer;

import androidx.appcompat.widget.AppCompatButton;

public class CodeTimeCount extends CountDownTimer {

    private final AppCompatButton smsCodeBtn;

    public CodeTimeCount(long millisInFuture, long countDownInterval, AppCompatButton smsCodeBtn) {
        super(millisInFuture, countDownInterval);
        this.smsCodeBtn = smsCodeBtn;
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onTick(long l) {
        smsCodeBtn.setTextColor(Color.parseColor("#909399"));
        smsCodeBtn.setEnabled(false);
        smsCodeBtn.setText(l / 1000 + "秒");
    }


    @Override
    public void onFinish() {
        smsCodeBtn.setText("获取验证码");
        smsCodeBtn.setEnabled(true);
        smsCodeBtn.setTextColor(Color.parseColor("#3B73F6"));
    }
}
