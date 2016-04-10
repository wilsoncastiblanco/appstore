package com.grability.appstore.modules.apps.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.grability.appstore.R;
import com.grability.appstore.models.ApplicationEntry;
import com.grability.appstore.models.Category;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wilson on 15/03/16.
 */
public class AppsAdapter extends RecyclerView.Adapter<AppsAdapter.AppsViewHolder>{

    private List<ApplicationEntry> applicationEntryList;
    Context context;
    ItemClickListener eventListener;

    public AppsAdapter(Context context, List<ApplicationEntry> applicationEntryList) {
        this.applicationEntryList = applicationEntryList;
        this.context = context;
    }

    @Override
    public AppsAdapter.AppsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_apps, parent, false);
        return new AppsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AppsAdapter.AppsViewHolder holder, int position) {
        final ApplicationEntry applicationEntry = applicationEntryList.get(position);
        holder.textViewAppName.setText(applicationEntry.getName().getLabel());
        holder.setOnViewClickListener(new AppsViewHolder.ViewClickListener() {
            @Override
            public void onViewClickListener(View view) {
                if(eventListener != null){
                    eventListener.onItemClickListener(view, applicationEntry);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return applicationEntryList.size();
    }

    //region events
    public interface ItemClickListener{
        void onItemClickListener(View view, ApplicationEntry applicationEntry);
    }

    public void setOnItemClickListener(ItemClickListener eventListener){
        this.eventListener = eventListener;
    }
    //endregion events

    public static class AppsViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.textViewAppName)
        TextView textViewAppName;
        ViewClickListener eventListener;

        public AppsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(eventListener != null){
                        eventListener.onViewClickListener(v);
                    }
                }
            });
        }

        public interface ViewClickListener{
            public void onViewClickListener(View view);
        }

        public void setOnViewClickListener(ViewClickListener eventListener){
            this.eventListener = eventListener;
        }
    }
}
