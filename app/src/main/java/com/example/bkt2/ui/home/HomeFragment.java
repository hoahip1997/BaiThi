package com.example.bkt2.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.example.bkt2.R;
import com.example.bkt2.ViewPagerAdapter;
import com.example.bkt2.tablayout_fragment_01;
import com.example.bkt2.tablayout_fragment_02;
import com.google.android.material.tabs.TabLayout;

public class HomeFragment extends Fragment {
    ViewPager viewpager;
    TabLayout tabLayout;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_home, null);
        viewpager = view.findViewById(R.id.viewpager);
        tabLayout = view.findViewById(R.id.tablayout);
        addTab(viewpager);
        tabLayout.setupWithViewPager(viewpager);
        return view;
    }

    public void addTab(ViewPager viewPager){
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.add(new tablayout_fragment_02(), "Danh sách Đồ Uống");
        adapter.add(new tablayout_fragment_01(), "Thêm Đồ Uống");
        viewPager.setAdapter(adapter);
    }

}
