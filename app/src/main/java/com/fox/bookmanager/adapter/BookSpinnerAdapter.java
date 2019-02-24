package com.fox.bookmanager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fox.bookmanager.R;
import com.fox.bookmanager.model.Book;

import java.util.List;

public class BookSpinnerAdapter extends BaseAdapter {

    public Context context;
    public List<Book> books;

    public BookSpinnerAdapter(Context context, List<Book> books) {
        this.context = context;
        this.books = books;
    }

    @Override
    public int getCount() {
        return (books != null) ? books.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return books.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.book_spinner,parent,false);
        TextView tvName = convertView.findViewById(R.id.tvName);
        tvName.setText(books.get(position).NAME);
        return convertView;
    }
}
