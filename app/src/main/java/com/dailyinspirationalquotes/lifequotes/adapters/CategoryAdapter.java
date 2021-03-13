package com.dailyinspirationalquotes.lifequotes.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dailyinspirationalquotes.lifequotes.R;
import com.dailyinspirationalquotes.lifequotes.models.QuotesCategory;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import java.util.ArrayList;
import java.util.List;


public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {

    private static final String TAG = "ReportAdapter";

    private List<QuotesCategory> mModels;
    private ReportListener listener;

    public CategoryAdapter() {
        mModels = new ArrayList<>();
    }

    public void addModel(List<QuotesCategory> mModels) {
        this.mModels = mModels;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.cust_category, viewGroup, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, int i) {

        QuotesCategory mSingleModel = mModels.get(i);

        //myViewHolder.type_tv.setText(mSingleModel.getReportType());
        myViewHolder.category_iv.setImageResource(mSingleModel.getCategoryIcon());
        myViewHolder.category_name_tv.setText(mSingleModel.getCategoryTitle());
        myViewHolder.category_count_tv.setText(String.valueOf(mSingleModel.getQuantity()));
        myViewHolder.circularProgressBar.setProgress(mSingleModel.getQuantity());

    }

    /*Count total items*/
    @Override
    public int getItemCount() {
        return mModels.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView category_iv;
        TextView category_name_tv;
        TextView category_count_tv;
        CircularProgressBar circularProgressBar;


        MyViewHolder(@NonNull final View itemView) {
            super(itemView);

            category_iv = itemView.findViewById(R.id.categoryIconItemGv_iv);
            category_name_tv = itemView.findViewById(R.id.categoryTitleItemGv_tv);
            category_count_tv = itemView.findViewById(R.id.categoryTotalQuote_tv);
            circularProgressBar = itemView.findViewById(R.id.categoryTotalQuote_cpb);

            itemView.setOnClickListener(view -> {
                int pos = getAdapterPosition();
                if (listener != null && pos != RecyclerView.NO_POSITION) {
                    listener.getReport(mModels.get(pos));
                }
            });
        }
    }

    //listener for sending value from adapter to activity
    public interface ReportListener {
        void getReport(QuotesCategory model);
    }

    public void setOnItemClickListener(ReportListener listener) {
        this.listener = listener;
    }
}