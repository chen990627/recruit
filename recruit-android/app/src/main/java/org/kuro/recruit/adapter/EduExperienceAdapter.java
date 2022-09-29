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
import org.kuro.recruit.model.entity.EduExperience;

import java.util.List;

public class EduExperienceAdapter extends RecyclerView.Adapter<EduExperienceAdapter.ViewHolder> {

    private final Context mContext;
    private final List<EduExperience> mList;

    public EduExperienceAdapter(Context context, List<EduExperience> list) {
        this.mContext = context;
        this.mList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayout view = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.com_edu_job_item, parent, false);
        return new EduExperienceAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        EduExperience experience = mList.get(position);

        holder.title.setText(experience.getCollege());
        String content = experience.getEdu() + " " + experience.getMajor();
        holder.content.setText(content);
        holder.time.setText(experience.getTimeQuantum());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView content;
        TextView time;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.com_edu_job_title);
            content = itemView.findViewById(R.id.com_edu_job_content);
            time = itemView.findViewById(R.id.com_edu_job_time);
        }
    }
}
