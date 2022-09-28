package org.kuro.recruit.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.kuro.recruit.R;

public class RecruitAdapter extends RecyclerView.Adapter<RecruitAdapter.ViewHolder> {

    private final Context mContext;

    public RecruitAdapter(Context context) {
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayout view = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.recruit_item, parent, false);
        return new RecruitAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (position == 7) {
            holder.bottom_line.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return 8;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        View bottom_line;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            bottom_line = itemView.findViewById(R.id.bottom_line);
        }
    }
}
