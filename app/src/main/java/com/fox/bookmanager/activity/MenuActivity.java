package com.fox.bookmanager.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.fox.bookmanager.R;
import com.fox.bookmanager.adapter.UserAdapter;
import com.fox.bookmanager.base.BaseActivity;
import com.fox.bookmanager.database.DBHelper;
import com.fox.bookmanager.model.Invoice;
import com.fox.bookmanager.model.User;

import java.util.List;

public class MenuActivity extends BaseActivity implements View.OnClickListener {

    private TextView tvProfile;
    private TextView tvBook;
    private TextView tvCategory;
    private TextView tvInvoice;
    private TextView tvBestSell;
    private TextView tvStatistical;

    private DBHelper dbHelper;

    private RecyclerView lvList;
    private UserAdapter userAdapter;
    private LinearLayoutManager linearLayoutManager;
    private List<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initViews();

        tvProfile.setOnClickListener(this);
        tvBook.setOnClickListener(this);
        tvCategory.setOnClickListener(this);
        tvInvoice.setOnClickListener(this);
        tvBestSell.setOnClickListener(this);
        tvStatistical.setOnClickListener(this);

    }

    private void initViews(){
        tvProfile = findViewById(R.id.tvProfile);
        tvBook = findViewById(R.id.tvBook);
        tvCategory = findViewById(R.id.tvCategory);
        tvInvoice = findViewById(R.id.tvInvoice);
        tvBestSell = findViewById(R.id.tvBestSell);
        tvStatistical = findViewById(R.id.tvStatistical);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.tvProfile:
                startNewActivity(UserActivity.class);
                break;
            case R.id.tvBook:
                startNewActivity(BookActivity.class);
                break;
            case R.id.tvCategory:
                startNewActivity(CategoryActivity.class);
                break;
            case R.id.tvInvoice:
                startNewActivity(InvoiceActivity.class);
                break;
            case R.id.tvBestSell:
                startNewActivity(BestSellActivity.class);
                break;
            case R.id.tvStatistical:
                startNewActivity(StatisticalActivity.class);
                break;
            default:
                break;
        }
    }
}
