package org.kuro.recruit.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import org.kuro.recruit.R;
import org.kuro.recruit.base.BaseFragment;
import org.kuro.recruit.databinding.FragmentMineBinding;
import org.kuro.recruit.manager.UserManage;
import org.kuro.recruit.ui.ResumeActivity;

public class MineFragment extends BaseFragment {

    private FragmentMineBinding mineBinding;

    public MineFragment() {
    }

    public static MineFragment newInstance() {
        return new MineFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mineBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_mine, container, false);
        return mineBinding.getRoot();
    }

    protected void initView() {
        // 前往个人在线简历页面
        mineBinding.rowResume.setOnClickListener(v -> navigateTo(ResumeActivity.class));
    }


    protected void initData() {
        mineBinding.setUser(UserManage.account());
    }
}