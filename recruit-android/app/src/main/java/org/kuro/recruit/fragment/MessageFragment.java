package org.kuro.recruit.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import org.kuro.recruit.R;
import org.kuro.recruit.adapter.MessageAdapter;
import org.kuro.recruit.base.BaseFragment;
import org.kuro.recruit.databinding.FragmentMessageBinding;
import org.kuro.recruit.model.entity.Message;

public class MessageFragment extends BaseFragment {

    private FragmentMessageBinding messageBinding;

    public MessageFragment() {
    }

    public static MessageFragment newInstance() {
        return new MessageFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        messageBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_message, container, false);
        return messageBinding.getRoot();
    }


    @Override
    protected void initView() {
    }

    @Override
    protected void initData() {
        messageBinding.messageList.setAdapter(new MessageAdapter(requireActivity(), Message.list()));
    }

}