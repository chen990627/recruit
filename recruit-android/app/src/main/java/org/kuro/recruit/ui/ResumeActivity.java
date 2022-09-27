package org.kuro.recruit.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;

import org.kuro.recruit.R;
import org.kuro.recruit.base.BaseUIActivity;
import org.kuro.recruit.databinding.ActivityResumeBinding;

public class ResumeActivity extends BaseUIActivity {

    private ActivityResumeBinding resumeBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        resumeBinding = DataBindingUtil.setContentView(this, R.layout.activity_resume);

        initView();
    }


    private void initView() {
        resumeBinding.resumeBack.setOnClickListener(v -> finish());
        resumeBinding.eduRow.setOnClickListener(v -> startActivity(new Intent(this, EduActivity.class)));
    }
}