package org.kuro.recruit.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import org.kuro.recruit.R;
import org.kuro.recruit.model.entity.Company;

import java.util.List;

public class CompanyAdapter extends RecyclerView.Adapter<CompanyAdapter.ViewHolder> {

    private List<Company> mList;
    private Context mContext;

    public CompanyAdapter(Context context, List<Company> list) {
        this.mList = list;
        this.mContext = context;
    }

    @NonNull
    @Override
    public CompanyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView view = (CardView) LayoutInflater.from(mContext).inflate(R.layout.company_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CompanyAdapter.ViewHolder holder, int position) {
        Company company = mList.get(position);
        holder.company_name.setText(company.getCompanyName());
        holder.company_intro.setText(company.getIntro());
        holder.company_tag.setText(company.getTag());
        holder.company_jobs.setText(company.getJobs());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView company_name;
        TextView company_intro;
        TextView company_tag;
        TextView company_jobs;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            company_name = itemView.findViewById(R.id.company_name);
            company_intro = itemView.findViewById(R.id.company_intro);
            company_tag = itemView.findViewById(R.id.company_tag);
            company_jobs = itemView.findViewById(R.id.company_jobs);
        }
    }
}
