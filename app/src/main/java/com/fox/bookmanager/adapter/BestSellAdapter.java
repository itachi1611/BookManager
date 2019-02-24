package com.fox.bookmanager.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fox.bookmanager.R;
import com.fox.bookmanager.model.Book;

import java.util.List;

public class BestSellAdapter extends RecyclerView.Adapter<BestSellAdapter.ViewHolder> {

    private List<Book> books;

    public BestSellAdapter(List<Book> books) {
        this.books = books;
    }

    @NonNull
    @Override
    public BestSellAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.item_best_sell, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BestSellAdapter.ViewHolder viewHolder, int i) {
        Book book = books.get(i);
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView imgBook;
        public TextView tvBookName;
        public TextView tvQuantity,tvId;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgBook = itemView.findViewById(R.id.imgBook);
            tvBookName = itemView.findViewById(R.id.tvBookName);
            tvId = itemView.findViewById(R.id.tvId);
            tvQuantity = itemView.findViewById(R.id.tvQuantity);
        }
    }
}
