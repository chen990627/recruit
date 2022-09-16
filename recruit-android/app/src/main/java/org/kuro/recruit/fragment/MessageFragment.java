package org.kuro.recruit.fragment;

import android.widget.ListView;

import org.kuro.recruit.R;
import org.kuro.recruit.adapter.MessageAdapter;
import org.kuro.recruit.base.BaseFragment;
import org.kuro.recruit.model.entity.Message;

public class MessageFragment extends BaseFragment {


    public MessageFragment() {
    }

    public static MessageFragment newInstance() {
        return new MessageFragment();
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_message;
    }

    @Override
    protected void initView() {
        ListView messageList = mRootView.findViewById(R.id.message_list);
        messageList.setAdapter(new MessageAdapter(requireActivity(), Message.list()));
    }

    @Override
    protected void initData() {

    }

}