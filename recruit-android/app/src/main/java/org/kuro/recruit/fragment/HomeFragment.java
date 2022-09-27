package org.kuro.recruit.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import org.kuro.recruit.R;
import org.kuro.recruit.base.BaseFragment;
import org.kuro.recruit.databinding.FragmentHomeBinding;


public class HomeFragment extends BaseFragment {

    private FragmentHomeBinding homeBinding;

    public HomeFragment() {
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        return homeBinding.getRoot();
    }


    @Override
    protected void initView() {
        homeBinding.appBar.addOnOffsetChangedListener((appBarLayout, i) -> {
            if (Math.abs(i) < appBarLayout.getTotalScrollRange()) {
                homeBinding.homeSearch.setVisibility(View.GONE);
            } else if (Math.abs(i) >= appBarLayout.getTotalScrollRange()) {
                homeBinding.homeSearch.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    protected void initData() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        homeBinding.homeRecycler.setLayoutManager(layoutManager);
    }

}