package com.fox.bookmanager.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.fox.bookmanager.R;
import com.fox.bookmanager.base.BaseActivity;
import com.fox.bookmanager.dao.BookDAO;

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
    }

    private void initViews(){
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

                break;
            case R.id.btnReset:
                resetData();
                break;
            case R.id.btnShow:
                finish();
                break;
        }
    }

    private void checkBookData(){

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
