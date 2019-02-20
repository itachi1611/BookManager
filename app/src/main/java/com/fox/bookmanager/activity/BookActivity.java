package com.fox.bookmanager.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.fox.bookmanager.R;
import com.fox.bookmanager.adapter.BookAdapter;
import com.fox.bookmanager.base.BaseActivity;
import com.fox.bookmanager.base.RecyclerViewClickListener;
import com.fox.bookmanager.base.RecyclerViewTouchListener;
import com.fox.bookmanager.dao.BookDAO;
import com.fox.bookmanager.model.Book;

import java.util.ArrayList;
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

        addRecycleView();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startNewActivity(AddBookActivity.class);
            }
        });
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void initViews(){
        books = new ArrayList<>();
        books.clear();
        bookDAO = new BookDAO(BookActivity.this);
        lvList = findViewById(R.id.lvList);
        linearLayoutManager = new LinearLayoutManager(BookActivity.this);
        fab = findViewById(R.id.fab);
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

        lvList.addOnItemTouchListener(new RecyclerViewTouchListener(getApplicationContext(), lvList, new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int pos) {
            }

            @Override
            public void onLongClick(View view, int pos) {
                PopupMenu popup = new PopupMenu(getApplicationContext(),view);
                popup.getMenuInflater().inflate(R.menu.popup_menu,popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int id = item.getItemId();
                        switch (id){
                            case R.id.menu_edit:

                                break;
                            case R.id.menu_delete:

                                break;
                        }
                        return true;
                    }
                });
                popup.show();
            }
        }));
    }

    private void onEdit(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.dialog_edit_category, null);
        dialog.setView(dialogView);
        final Dialog dialog1 = dialog.show();
//        Button sua = dialogView.findViewById(R.id.btnSua_EditTheLoai);
//        Button huy = dialogView.findViewById(R.id.btnHuy_EditTheLoai);
//        huy.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog1.dismiss();
//            }
//        });
    }

}
