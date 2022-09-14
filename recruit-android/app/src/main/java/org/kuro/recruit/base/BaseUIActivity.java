package org.kuro.recruit.base;

import android.os.Bundle;

import org.kuro.recruit.utils.SystemUI;


/**
 * UI 基类
 */
public class BaseUIActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 沉浸式布局
        SystemUI.fixSystemUI(this, false);
    }
}
