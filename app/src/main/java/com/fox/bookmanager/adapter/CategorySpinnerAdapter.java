package com.fox.bookmanager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fox.bookmanager.R;
import com.fox.bookmanager.model.Category;

import java.util.List;

public class CategorySpinnerAdapter extends BaseAdapter {

    public Context context;
    public List<Category> categories;

    public CategorySpinnerAdapter(Context context, List<Category> categories) {
        this.context = context;
        this.categories = categories;
    }

    @Override
    public int getCount() {
        return (categories != null) ? categories.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return categories.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.category_spinner,parent,false);
        TextView tvName = convertView.findViewById(R.id.tvName);
        tvName.setText(position + " | " + categories.get(position).NAME);
        return convertView;
    }
}
