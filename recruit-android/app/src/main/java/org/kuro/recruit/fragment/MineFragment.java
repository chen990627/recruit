package org.kuro.recruit.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import org.kuro.recruit.R;
import org.kuro.recruit.base.BaseFragment;
import org.kuro.recruit.databinding.FragmentMineBinding;
import org.kuro.recruit.manager.UserManage;

public class MineFragment extends BaseFragment {

    private FragmentMineBinding mineBinding;

    public MineFragment() {
    }

    public static MineFragment newInstance() {
        return new MineFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mineBinding = FragmentMineBinding.inflate(inflater, container, false);
        return mineBinding.getRoot();
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void initData() {
        mineBinding.setUser(UserManage.account());
    }
}