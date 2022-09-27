package org.kuro.recruit.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import org.kuro.recruit.R;
import org.kuro.recruit.adapter.CompanyAdapter;
import org.kuro.recruit.base.BaseFragment;
import org.kuro.recruit.databinding.FragmentCompanyBinding;
import org.kuro.recruit.model.entity.Company;

public class CompanyFragment extends BaseFragment {

    private FragmentCompanyBinding companyBinding;

    public CompanyFragment() {
    }

    public static CompanyFragment newInstance() {
        return new CompanyFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        companyBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_company, container, false);
        return companyBinding.getRoot();
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void initData() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        companyBinding.companyRecycler.setLayoutManager(layoutManager);
        companyBinding.companyRecycler.setAdapter(new CompanyAdapter(requireContext(), Company.list()));
    }
}