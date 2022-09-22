package org.kuro.recruit;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.kuro.recruit.base.BaseUIActivity;
import org.kuro.recruit.fragment.CompanyFragment;
import org.kuro.recruit.fragment.HomeFragment;
import org.kuro.recruit.fragment.MessageFragment;
import org.kuro.recruit.fragment.MineFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseUIActivity {

    private List<Fragment> mFragments;

    // 上次fragment的位置
    private int lastPosition;
    // 要显示的Fragment
    private Fragment currentFragment;
    // 要隐藏的Fragment
    private Fragment hideFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        // activity重建时保存页面的位置
        outState.putInt("last_position", lastPosition);
    }


    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // 获取重建时的fragment的位置
        lastPosition = savedInstanceState.getInt("last_position");
        // 恢复销毁前显示的fragment
        setSelectedFragment(lastPosition);
    }


    private void initData() {
        mFragments = new ArrayList<>();
        mFragments.add(HomeFragment.newInstance());
        mFragments.add(CompanyFragment.newInstance());
        mFragments.add(MessageFragment.newInstance());
        mFragments.add(MineFragment.newInstance());
    }


    @SuppressLint("NonConstantResourceId")
    private void initView(Bundle savedInstanceState) {
        BottomNavigationView tabBar = findViewById(R.id.tabBar);
        if (savedInstanceState == null) {
            // 根据传入的Bundle对象判断是正常启动还是重建 true表示正常启动，false表示重建
            setSelectedFragment(0);
        }
        tabBar.setOnItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.tabBar_home:
                    setSelectedFragment(0);
                    break;
                case R.id.tabBar_company:
                    setSelectedFragment(1);
                    break;
                case R.id.tabBar_message:
                    setSelectedFragment(2);
                    break;
                case R.id.tabBar_mine:
                    setSelectedFragment(3);
                    break;
            }
            return true;
        });
    }


    /**
     * 根据位置选择Fragment
     *
     * @param position 要选中的fragment的位置
     */
    private void setSelectedFragment(int position) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // 要显示的fragment(解决了activity重建时新建实例的问题)
        currentFragment = fragmentManager.findFragmentByTag("fragment" + position);
        // 要隐藏的fragment(解决了activity重建时新建实例的问题)
        hideFragment = fragmentManager.findFragmentByTag("fragment" + lastPosition);
        if (position != lastPosition) {
            // 如果位置不同
            if (hideFragment != null) {
                // 如果要隐藏的fragment存在，则隐藏
                transaction.hide(hideFragment);
            }
            if (currentFragment == null) {
                // 如果要显示的fragment不存在，则新加并提交事务
                currentFragment = mFragments.get(position);
                transaction.add(R.id.main_container, currentFragment, "fragment" + position);
            } else {
                // 如果要显示的存在则直接显示
                transaction.show(currentFragment);
            }
        }

        if (position == lastPosition) {
            // 如果位置相同
            if (currentFragment == null) {
                // 如果fragment不存在(第一次启动应用的时候)
                currentFragment = mFragments.get(position);
                transaction.add(R.id.main_container, currentFragment, "fragment" + position);
            }
            // 如果位置相同，且fragment存在，则不作任何操作
        }
        // 提交事务
        transaction.commit();
        // 更新要隐藏的fragment的位置
        lastPosition = position;
    }

    // 连续点击两次返回键退出当前应用
    private boolean flag = true;
    private static final int RESET_BACK = 1;
    private final Handler handler = new Handler(message -> {
        if (message.what == RESET_BACK) {
            flag = true;
        }
        return true;
    });

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && flag) {
            showToast("再点击一次，退出当前应用");
            flag = false;
            return handler.sendEmptyMessageDelayed(RESET_BACK, 2000);
        }
        return super.onKeyUp(keyCode, event);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 为了防止内存泄漏，需要在onDestroy中移除所有未被执行的进程
        handler.removeCallbacksAndMessages(null);
    }
}