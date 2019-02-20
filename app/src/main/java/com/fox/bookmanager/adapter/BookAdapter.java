package com.fox.bookmanager.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fox.bookmanager.R;
import com.fox.bookmanager.model.Book;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookHolder> {

    public Context context;
    public List<Book> books;

    public BookAdapter(Context context, List<Book> books) {
        this.context = context;
        this.books = books;
    }

    @NonNull
    @Override
    public BookHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.book_item,viewGroup,false);
        return new BookHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookHolder bookHolder, int i) {
        bookHolder.book = books.get(i);
        bookHolder.tvBookName.setText(bookHolder.book.NAME);
        bookHolder.tvBookQuantity.setText(Integer.toString(bookHolder.book.QUANTITY));
    }


    @Override
    public int getItemCount() {
        return (books == null) ? 0 : books.size();
    }

    public class BookHolder extends RecyclerView.ViewHolder{

        private TextView tvBookName;
        private TextView tvBookQuantity;
        Book book;

        public BookHolder(@NonNull View itemView) {
            super(itemView);
            tvBookName = itemView.findViewById(R.id.tvBookName);
            tvBookQuantity = itemView.findViewById(R.id.tvBookQuantity);
        }
    }

}
