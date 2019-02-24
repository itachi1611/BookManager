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

    public Context context;
    public List<Book> books;

    public BestSellAdapter(Context context, List<Book> books) {
        this.context = context;
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
        viewHolder.book = books.get(i);
        viewHolder.tvBookName.setText(viewHolder.book.NAME);
        viewHolder.tvId.setText(viewHolder.book.ID);
        viewHolder.tvQuantity.setText(viewHolder.book.QUANTITY);
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView imgBook;
        public TextView tvBookName;
        public TextView tvQuantity,tvId;
        Book book;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgBook = itemView.findViewById(R.id.imgBook);
            tvBookName = itemView.findViewById(R.id.tvBookName);
            tvId = itemView.findViewById(R.id.tvId);
            tvQuantity = itemView.findViewById(R.id.tvQuantity);
        }
    }
}
