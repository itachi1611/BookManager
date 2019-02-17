package com.fox.bookmanager.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.fox.bookmanager.R;
import com.fox.bookmanager.adapter.CategoryAdapter;
import com.fox.bookmanager.base.BaseActivity;
import com.fox.bookmanager.dao.CategoryDAO;
import com.fox.bookmanager.model.Book;
import com.fox.bookmanager.model.Category;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CategoryActivity extends BaseActivity {

    private CategoryDAO categoryDAO;

    private RecyclerView lvList;
    private CategoryAdapter categoryAdapter;
    private LinearLayoutManager linearLayoutManager;
    private List<Category> categories;

    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        initViews();
        for (int i = 0; i < 10; i++) {
            Category category = new Category();
            category.ID ="s " + i;
            category.NAME = "Công nghệ thông tin";
            category.DESCRIPTION = "Sách công nghệ thông tin";
            category.POSITION = "A1";
            categoryDAO.insertCategory(category);
        }
        addRecycleView();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startNewActivity(AddCategoryActivity.class);
                finish();
            }
        });
    }

    private void initViews(){
        categories = new ArrayList<>();
        categories.clear();
        categoryDAO = new CategoryDAO(CategoryActivity.this);
        lvList = findViewById(R.id.lvList);
        linearLayoutManager = new LinearLayoutManager(CategoryActivity.this);
        fab = findViewById(R.id.fab);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void addRecycleView(){
        categories = categoryDAO.getAllCategory();
        categoryAdapter = new CategoryAdapter(this,categories);

        lvList.setLayoutManager(linearLayoutManager);
        lvList.setHasFixedSize(true);
        lvList.setAdapter(categoryAdapter);
    }

}
