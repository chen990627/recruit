package org.kuro.recruit.base;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment extends Fragment {

    protected View mRootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(initLayout(), container, false);
            initView();
        }
        return mRootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    protected abstract int initLayout();

    protected abstract void initView();

    protected abstract void initData();

    public void navigateTo(Class<?> cls) {
        Intent in = new Intent(requireActivity(), cls);
        startActivity(in);
    }

    public void navigateToWithBundle(Class<?> cls, Bundle bundle) {
        Intent in = new Intent(requireActivity(), cls);
        in.putExtras(bundle);
        startActivity(in);
    }

    public void navigateToWithFlag(Class<?> cls, int flags) {
        Intent in = new Intent(requireActivity(), cls);
        in.setFlags(flags);
        startActivity(in);
    }
}
