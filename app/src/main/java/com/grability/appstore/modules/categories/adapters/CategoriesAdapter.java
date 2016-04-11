package com.grability.appstore.modules.categories.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.grability.appstore.R;
import com.grability.appstore.models.Category;
import com.grability.appstore.utils.ViewUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wilson on 15/03/16.
 */
public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>{

    private List<Category> categoryList;
    Context context;
    ItemClickListener eventListener;

    public CategoriesAdapter(Context context, List<Category> categoryList) {
        this.categoryList = categoryList;
        this.context = context;
    }

    @Override
    public CategoriesAdapter.CategoriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_categories, parent, false);
        return new CategoriesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CategoriesAdapter.CategoriesViewHolder holder, int position) {
        final Category category = categoryList.get(position);
        holder.textViewCategoryName.setText(category.getLabel());
        holder.textViewCategory.setText(category.getLabel().substring(0,1).toUpperCase());
        holder.frameLayoutCategory.setBackgroundColor(ViewUtils.getRandomArgbColor());
        holder.setOnViewClickListener(new CategoriesViewHolder.ViewClickListener() {
            @Override
            public void onViewClickListener(View view) {
                if(eventListener != null){
                    eventListener.onItemClickListener(view, category);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    //region events
    public interface ItemClickListener{
        void onItemClickListener(View view, Category category);
    }

    public void setOnItemClickListener(ItemClickListener eventListener){
        this.eventListener = eventListener;
    }
    //endregion events

    public static class CategoriesViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.textViewCategoryName)
        TextView textViewCategoryName;
        @Bind(R.id.textViewCategory)
        TextView textViewCategory;
        @Bind(R.id.frameLayoutCategory)
        FrameLayout frameLayoutCategory;
        ViewClickListener eventListener;

        public CategoriesViewHolder(View itemView) {
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
            void onViewClickListener(View view);
        }

        public void setOnViewClickListener(ViewClickListener eventListener){
            this.eventListener = eventListener;
        }
    }
}
