package com.example.setlogger.Activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.setlogger.BottomMenuFragment;
import com.example.setlogger.R;

public class StatisticsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).add(R.id.fragmentContainerView4, BottomMenuFragment.class,null).commit();
        }
    }
}