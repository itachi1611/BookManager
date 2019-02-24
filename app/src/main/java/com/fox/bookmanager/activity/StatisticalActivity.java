package com.fox.bookmanager.activity;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.fox.bookmanager.R;
import com.fox.bookmanager.base.BaseActivity;

public class StatisticalActivity extends BaseActivity {

    private TabLayout tbLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistical);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        addTabLayoutControl();

    }

    private void initViews(){
        tbLayout = findViewById(R.id.tbLayout);
        viewPager = findViewById(R.id.viewPager);
    }

    private void addTabLayoutControl(){
        initViews();

        FragmentManager fragmentManager = getSupportFragmentManager();

        PagerAdapter pagerAdapter = new com.fox.bookmanager.adapter.PagerAdapter(fragmentManager);
        viewPager.setAdapter(pagerAdapter);
        tbLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tbLayout));
        tbLayout.setTabsFromPagerAdapter(pagerAdapter);
        tbLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));

        View v = tbLayout.getChildAt(0);
        if(v instanceof LinearLayout){
            ((LinearLayout) v).setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
            GradientDrawable drawable = new GradientDrawable();
            drawable.setColor(getResources().getColor(R.color.colorPrimary));
            drawable.setSize(2, 1);
            ((LinearLayout) v).setDividerPadding(20);
            ((LinearLayout) v).setDividerDrawable(drawable);
        }
    }

}
