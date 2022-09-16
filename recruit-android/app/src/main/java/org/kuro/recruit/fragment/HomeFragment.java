package org.kuro.recruit.fragment;

import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.AppBarLayout;

import org.kuro.recruit.R;
import org.kuro.recruit.base.BaseFragment;


public class HomeFragment extends BaseFragment {

    private AppBarLayout appBar;
    private Toolbar homeSearch;
    private RecyclerView recycler;

    public HomeFragment() {
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        appBar = mRootView.findViewById(R.id.app_bar);
        homeSearch = mRootView.findViewById(R.id.home_search);
        recycler = mRootView.findViewById(R.id.home_recycler);
    }

    @Override
    protected void initData() {
        appBar.addOnOffsetChangedListener((appBarLayout, i) -> {
            if (Math.abs(i) < appBarLayout.getTotalScrollRange()) {
                homeSearch.setVisibility(View.GONE);
            } else if (Math.abs(i) >= appBarLayout.getTotalScrollRange()) {
                homeSearch.setVisibility(View.VISIBLE);
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(layoutManager);
    }

}