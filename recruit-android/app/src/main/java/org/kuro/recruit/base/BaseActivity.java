package org.kuro.recruit.base;

import android.view.Gravity;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

// 基类，获取权限
public class BaseActivity extends AppCompatActivity {

    public void showToast(String msg) {
        Toast toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP, 0, 0);
        toast.show();
    }
}