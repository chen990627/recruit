package org.kuro.recruit.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.kuro.recruit.R;
import org.kuro.recruit.model.entity.Message;

import java.util.List;

public class MessageAdapter extends BaseAdapter {

    private final Context mContext;
    private final List<Message> mList;

    public MessageAdapter(Context context, List<Message> list) {
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public int getCount() {
        if (mList == null) {
            return 0;
        }
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(mContext, R.layout.message_item, null);
            holder.message_from = convertView.findViewById(R.id.message_from);
            holder.top_message = convertView.findViewById(R.id.message_top_message);
            holder.message_time = convertView.findViewById(R.id.message_time);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Message message = mList.get(position);
        holder.message_from.setText(message.getFromUser());
        holder.top_message.setText(message.getTopMessage());
        holder.message_time.setText(message.getTime());

        return convertView;
    }

    static class ViewHolder {
        private TextView message_from;
        private TextView top_message;
        private TextView message_time;
    }
}
