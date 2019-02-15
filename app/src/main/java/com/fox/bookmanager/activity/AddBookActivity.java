package com.fox.bookmanager.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.fox.bookmanager.R;

public class AddBookActivity extends AppCompatActivity {

    private EditText edtBookId;
    private Spinner spCatId;
    private ImageButton iBtnAdd;
    private EditText edtBookName;
    private EditText edtAuthor;
    private EditText edtProducer;
    private EditText edtPrice;
    private EditText edtQuantity;
    private Button btnAdd;
    private Button btnCancel;
    private Button benShow;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        initViews();
    }

    private void initViews(){
        edtBookId = findViewById(R.id.edtBookId);
        spCatId = findViewById(R.id.spCatId);
        iBtnAdd = findViewById(R.id.i_btnAdd);
        edtBookName = findViewById(R.id.edtBookName);
        edtAuthor = findViewById(R.id.edtAuthor);
        edtProducer = findViewById(R.id.edtProducer);
        edtPrice = findViewById(R.id.edtPrice);
        edtQuantity = findViewById(R.id.edtQuantity);
        btnAdd = findViewById(R.id.btnAdd);
        btnCancel = findViewById(R.id.btnCancel);
        benShow = findViewById(R.id.benShow);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
