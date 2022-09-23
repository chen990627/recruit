package org.kuro.recruit.ui;

import android.os.Bundle;
import android.widget.ImageView;

import org.kuro.recruit.R;
import org.kuro.recruit.base.BaseUIActivity;

public class ResumeActivity extends BaseUIActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume);

        ImageView back = findViewById(R.id.resume_back);
        back.setOnClickListener(v -> finish());
    }
}