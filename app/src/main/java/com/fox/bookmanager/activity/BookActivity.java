package com.fox.bookmanager.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.fox.bookmanager.R;
import com.fox.bookmanager.adapter.BookAdapter;
import com.fox.bookmanager.base.BaseActivity;
import com.fox.bookmanager.dao.BookDAO;
import com.fox.bookmanager.model.Book;

import java.util.List;
import java.util.Random;

public class BookActivity extends BaseActivity {

    private BookDAO bookDAO;

    private RecyclerView lvList;
    private BookAdapter bookAdapter;
    private LinearLayoutManager linearLayoutManager;
    private List<Book> books;

    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        initViews();
        for (int i = 0; i < 10; i++) {
            Book book = new Book();
            book.ID ="s "+i;
            book.CAT_ID = "tyle"+i;
            book.NAME = "abc";
            book.AUTHOR = "123456";
            book.PRODUCER = "12d3456";
            book.PRICE = String.valueOf(new Random().nextInt(100));
            book.QUANTITY = String.valueOf(new Random().nextInt(100));;
            bookDAO.insertBook(book);
        }
        addRecycleView();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startNewActivity(AddBookActivity.class);
                finish();
            }
        });

    }

    private void initViews(){
        lvList = findViewById(R.id.lvList);
        FloatingActionButton fab = findViewById(R.id.fab);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void addRecycleView(){
        books = bookDAO.getAllBook();
        bookAdapter = new BookAdapter(this,books);

        lvList.setLayoutManager(linearLayoutManager);
        lvList.setHasFixedSize(true);
        lvList.setAdapter(bookAdapter);
    }

}
