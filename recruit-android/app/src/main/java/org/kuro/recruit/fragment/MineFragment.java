package org.kuro.recruit.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.kuro.recruit.databinding.FragmentMineBinding;
import org.kuro.recruit.manager.UserManage;
import org.kuro.recruit.ui.ResumeActivity;

public class MineFragment extends Fragment {

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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData();
    }

    protected void initView() {
        // 前往个人在线简历页面
        mineBinding.rowResume.setOnClickListener(v -> {
            startActivity(new Intent(requireActivity(), ResumeActivity.class));
            // navigateTo(ResumeActivity.class);
        });
    }


    protected void initData() {
        mineBinding.setUser(UserManage.account());
    }
}