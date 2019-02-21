package com.fox.bookmanager.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.fox.bookmanager.R;
import com.fox.bookmanager.base.BaseActivity;


public class MenuActivity extends BaseActivity implements View.OnClickListener,NavigationView.OnNavigationItemSelectedListener {

    private TextView tvProfile;
    private TextView tvBook;
    private TextView tvCategory;
    private TextView tvInvoice;
    private TextView tvBestSell;
    private TextView tvStatistical;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initViews();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

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

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        // Handle navigation view item clicks here.
        int id = menuItem.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_tools) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
