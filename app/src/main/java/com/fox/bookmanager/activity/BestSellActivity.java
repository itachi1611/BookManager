package com.fox.bookmanager.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.fox.bookmanager.R;
import com.fox.bookmanager.adapter.BestSellAdapter;
import com.fox.bookmanager.base.BaseActivity;
import com.fox.bookmanager.dao.BookDAO;
import com.fox.bookmanager.model.Book;

import java.util.ArrayList;
import java.util.List;

public class BestSellActivity extends BaseActivity {

    private Toolbar toolbar;
    private RecyclerView lvList;
    private List<Book> books;
    private BestSellAdapter adapter;
    private ArrayAdapter<String> spinner_adapter;
    private BookDAO bookDAO;
    private Spinner spMonth;
    private Button btnShow;
    private LinearLayoutManager linearLayoutManager;
    private String month[] = new String[]{"January","February","March","April","May","June","July","August","September","October","November","December"};
    private int m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_best_sell);
        initViews();
        spinner_adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,month);
        spMonth.setAdapter(spinner_adapter);
        spMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String month = spinner_adapter.getItem(position);
                switch (month){
                    case "January":
                        m = 1;
                        break;
                    case "February":
                        m = 2;
                        break;
                    case "March":
                        m = 3;
                        break;
                    case "April":
                        m = 4;
                        break;
                    case "May":
                        m = 5;
                        break;
                    case "June":
                        m = 6;
                        break;
                    case "July":
                        m = 7;
                        break;
                    case "August":
                        m = 8;
                        break;
                    case "September":
                        m = 9;
                        break;
                    case "October":
                        m = 10;
                        break;
                    case "November":
                        m = 11;
                        break;
                    case "December":
                        m = 12;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTopTenBook();
            }
        });

    }

    private void initViews(){
        books = new ArrayList<>();
        bookDAO = new BookDAO(BestSellActivity.this);
        spMonth = findViewById(R.id.spMonth);
        lvList = findViewById(R.id.lvList);
        btnShow = findViewById(R.id.btnShow);
        toolbar = findViewById(R.id.toolbar);
        linearLayoutManager = new LinearLayoutManager(BestSellActivity.this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void showTopTenBook(){
        books = bookDAO.getTopTenBook(Integer.toString(m));
        adapter = new BestSellAdapter(BestSellActivity.this,books);
        Log.e("m",books.size()+"");

        lvList.setLayoutManager(linearLayoutManager);
        lvList.setHasFixedSize(true);
        lvList.setAdapter(adapter);
    }

}
