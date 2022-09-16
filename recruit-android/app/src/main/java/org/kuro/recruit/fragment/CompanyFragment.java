package org.kuro.recruit.fragment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.kuro.recruit.R;
import org.kuro.recruit.adapter.CompanyAdapter;
import org.kuro.recruit.base.BaseFragment;
import org.kuro.recruit.model.entity.Company;

public class CompanyFragment extends BaseFragment {

    private RecyclerView recycler;

    public CompanyFragment() {
    }

    public static CompanyFragment newInstance() {
        return new CompanyFragment();
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_company;
    }

    @Override
    protected void initView() {
        recycler = mRootView.findViewById(R.id.company_recycler);
    }

    @Override
    protected void initData() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(layoutManager);
        recycler.setAdapter(new CompanyAdapter(requireContext(), Company.list()));
    }
}