package com.fox.bookmanager.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fox.bookmanager.R;
import com.fox.bookmanager.model.Category;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryHolder> {

    public Context context;
    public List<Category> categories;

    public CategoryAdapter(Context context, List<Category> categories) {
        this.context = context;
        this.categories = categories;
    }

    @NonNull
    @Override
    public CategoryHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.category_item,viewGroup,false);
        return new CategoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryHolder categoryHolder, int i) {
        categoryHolder.category = categories.get(i);
        categoryHolder.tvCatId.setText(categoryHolder.category.ID);
        categoryHolder.tvCatName.setText(categoryHolder.category.NAME);
    }

    @Override
    public int getItemCount() {
        return (categories == null) ? 0 : categories.size();
    }

    public class CategoryHolder extends RecyclerView.ViewHolder{

        private TextView tvCatId;
        private TextView tvCatName;
        Category category;

        public CategoryHolder(@NonNull View itemView) {
            super(itemView);
            tvCatId = itemView.findViewById(R.id.tvCatId);
            tvCatName = itemView.findViewById(R.id.tvCatName);
        }
    }

}
