package com.fox.bookmanager.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.fox.bookmanager.R;
import com.fox.bookmanager.adapter.CategorySpinnerAdapter;
import com.fox.bookmanager.base.BaseActivity;
import com.fox.bookmanager.dao.BookDAO;
import com.fox.bookmanager.dao.CategoryDAO;
import com.fox.bookmanager.model.Book;
import com.fox.bookmanager.model.Category;

import java.util.List;

public class AddBookActivity extends BaseActivity implements View.OnClickListener {

    private EditText edtBookId;
    private Spinner spCatId;
    private ImageButton iBtnAdd;
    private EditText edtBookName;
    private EditText edtAuthor;
    private EditText edtProducer;
    private EditText edtPrice;
    private EditText edtQuantity;
    private Button btnAdd;
    private Button btnReset;
    private Button btnShow;
    private BookDAO bookDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        initViews();
        btnAdd.setOnClickListener(this);
        btnReset.setOnClickListener(this);
        btnShow.setOnClickListener(this);
        List<Category> categories = new CategoryDAO(AddBookActivity.this).getAllCategory();
        spCatId.setAdapter(new CategorySpinnerAdapter(AddBookActivity.this,categories));
    }

    private void initViews(){
        bookDAO = new BookDAO(AddBookActivity.this);
        edtBookId = findViewById(R.id.edtBookId);
        edtBookName = findViewById(R.id.edtBookName);
        edtAuthor = findViewById(R.id.edtAuthor);
        edtProducer = findViewById(R.id.edtProducer);
        edtPrice = findViewById(R.id.edtPrice);
        edtQuantity = findViewById(R.id.edtQuantity);
        spCatId = findViewById(R.id.spCatId);
        iBtnAdd = findViewById(R.id.i_btnAdd);
        btnAdd = findViewById(R.id.btnAdd);
        btnReset = findViewById(R.id.btnReset);
        btnShow = findViewById(R.id.btnShow);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btnAdd:
                checkBookData();
                break;
            case R.id.btnReset:
                resetData();
                break;
            case R.id.btnShow:
                startNewActivity(BookActivity.class);
                break;
        }
    }

    private void checkBookData(){
        final Book book = new Book();
        book.ID = edtBookId.getText().toString().trim();
        if(book.ID.matches("")){
            edtBookId.setError("ID can not be empty!");
            return;
        }
        book.CAT_ID = ((Category)spCatId.getSelectedItem()).ID;
        book.NAME = edtBookName.getText().toString().trim();
        if(book.NAME.matches("")){
            edtBookName.setError("Name can not be empty!");
            return;
        }
        book.AUTHOR = edtAuthor.getText().toString().trim();
        book.PRODUCER = edtProducer.getText().toString().trim();
        book.PRICE = Float.parseFloat(edtPrice.getText().toString().trim());
        if(edtPrice.getText().toString().trim().matches("")){
            edtPrice.setError("Price can not be empty!");
            return;
        }
        book.QUANTITY = Integer.parseInt(edtQuantity.getText().toString().trim());
        if(edtQuantity.getText().toString().trim().matches("")){
            edtQuantity.setError("Quantity can not be empty!");
            return;
        }
        bookDAO.insertBook(book);
        resetData();
    }

    private void resetData(){
        edtBookId.setText("");
        edtBookName.setText("");
        edtAuthor.setText("");
        edtProducer.setText("");
        edtPrice.setText("");
        edtQuantity.setText("");
    }

}
