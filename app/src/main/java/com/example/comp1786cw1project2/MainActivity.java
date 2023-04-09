package com.example.comp1786cw1project2;

import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;

import com.example.comp1786cw1project2.databinding.ActivityMainBinding;
import com.example.comp1786cw1project2.feature.homepage.HomeFragment;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends BaseActivity {
    @Override
    public Fragment startDestination() {
        return HomeFragment.newInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = LayoutInflater.from(this);
        ActivityMainBinding viewBinding = ActivityMainBinding.inflate(inflater);
        setContentView(viewBinding.getRoot());
    }
}