package com.fox.bookmanager.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fox.bookmanager.R;
import com.fox.bookmanager.dao.StatisticalDAO;

public class FragmentDay extends Fragment{

    private StatisticalDAO statisticalDAO;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        statisticalDAO = new StatisticalDAO(getContext());
        View v = inflater.inflate(R.layout.fragment_stat_day,container,false);
        TextView tvStat = v.findViewById(R.id.tvStat);
        tvStat.setText("Doanh thu : " + statisticalDAO.getStatisticalByDay());
        return v;
    }

}
