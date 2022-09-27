package org.kuro.recruit.base;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment extends Fragment {

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

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

    public void showToast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    public void showToastSync(String msg) {
        Looper.prepare();
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
        Looper.loop();
    }
}
