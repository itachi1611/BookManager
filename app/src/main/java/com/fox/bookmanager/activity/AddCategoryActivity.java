package com.fox.bookmanager.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.fox.bookmanager.R;
import com.fox.bookmanager.base.BaseActivity;
import com.fox.bookmanager.dao.CategoryDAO;
import com.fox.bookmanager.model.Category;


public class AddCategoryActivity extends BaseActivity implements View.OnClickListener{

    private EditText edtCategoryId;
    private EditText edtCategoryName;
    private EditText edtCategoryPosition;
    private EditText edtCategoryDescription;
    private Button btnAdd;
    private Button btnReset;
    private Button btnShow;
    private CategoryDAO categoryDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);
        initViews();

        btnAdd.setOnClickListener(this);
        btnReset.setOnClickListener(this);
        btnShow.setOnClickListener(this);
    }

    private void initViews(){
        edtCategoryId = findViewById(R.id.edtCategoryId);
        edtCategoryName = findViewById(R.id.edtCategoryName);
        edtCategoryPosition = findViewById(R.id.edtCategoryPosition);
        edtCategoryDescription = findViewById(R.id.edtCategoryDescription);
        btnAdd = findViewById(R.id.btnAdd);
        btnReset = findViewById(R.id.btnReset);
        btnShow = findViewById(R.id.btnShow);
        categoryDAO = new CategoryDAO(AddCategoryActivity.this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btnAdd:
                checkCategoryData();
                break;
            case R.id.btnReset:
                resetData();
                break;
            case R.id.btnShow:
                finish();
                break;
        }
    }

    private void checkCategoryData(){
        Category category = new Category();
        category.ID = edtCategoryId.getText().toString().trim();
        if(category.ID.matches("")){
            edtCategoryId.setError("ID can not be empty!");
            return;
        }
        category.NAME = edtCategoryName.getText().toString().trim();
        if(category.NAME.matches("")){
            edtCategoryName.setError("Name can not be empty!");
            return;
        }
        category.POSITION = edtCategoryPosition.getText().toString().trim();
        category.DESCRIPTION = edtCategoryDescription.getText().toString().trim();
        categoryDAO.insertCategory(category);
        resetData();
    }

    private void resetData(){
        edtCategoryId.setText("");
        edtCategoryName.setText("");
        edtCategoryPosition.setText("");
        edtCategoryDescription.setText("");
    }

}
