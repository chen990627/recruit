package org.kuro.recruit.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.kuro.recruit.R;
import org.kuro.recruit.model.entity.WorkHistory;

import java.util.List;

public class WorkHistoryAdapter extends RecyclerView.Adapter<WorkHistoryAdapter.ViewHolder> {

    private final Context mContext;
    private final List<WorkHistory> mList;

    public WorkHistoryAdapter(Context context, List<WorkHistory> list) {
        this.mContext = context;
        this.mList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayout view = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.work_history_item, parent, false);
        return new WorkHistoryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        WorkHistory workHistory = mList.get(position);
        holder.company_name.setText(workHistory.getCompany());
        String workTime = workHistory.getStart() + "~" + workHistory.getEnd();
        holder.work_time.setText(workTime);

        String content = workHistory.getContent().replace("\\n", "\n");
        holder.work_content.setText(content);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView company_name;
        TextView work_time;
        TextView work_content;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            company_name = itemView.findViewById(R.id.work_company_name);
            work_time = itemView.findViewById(R.id.work_time);
            work_content = itemView.findViewById(R.id.work_content);
        }
    }
}
