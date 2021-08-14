package com.example.setlogger;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.setlogger.Activities.BufferedSetsActivity;
import com.example.setlogger.Activities.ExerciseManagerActivity;
import com.example.setlogger.Activities.MainActivity;
import com.example.setlogger.Activities.StatisticsActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BottomMenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BottomMenuFragment extends Fragment {

    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;

    public BottomMenuFragment() {
    }

    private static Class currentActivity = null;

    public static BottomMenuFragment newInstance() {
        BottomMenuFragment fragment = new BottomMenuFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_bottom_menu, container, false);

        buttonInit(view);

        return view;
    }

    private void buttonInit(View view) {
        btn1 = view.findViewById(R.id.menuButton1);
        btn2 = view.findViewById(R.id.menuButton2);
        btn3 = view.findViewById(R.id.menuButton3);
        btn4 = view.findViewById(R.id.menuButton4);


        btn1.setOnClickListener(a -> {
            if (currentActivity == MainActivity.class || currentActivity == null) {
                return;
            }
            currentActivity = MainActivity.class;
            switchTo(MainActivity.class);
        });
        btn2.setOnClickListener(a -> {
            if (currentActivity == ExerciseManagerActivity.class) {
                return;
            }
            currentActivity = ExerciseManagerActivity.class;
            switchTo(ExerciseManagerActivity.class);
        });
        btn3.setOnClickListener(a -> {
            if (currentActivity == BufferedSetsActivity.class) {
                return;
            }
            currentActivity = BufferedSetsActivity.class;
            switchTo(BufferedSetsActivity.class);
        });
        btn4.setOnClickListener(a -> {
            if (currentActivity == StatisticsActivity.class) {
                return;
            }
            currentActivity = StatisticsActivity.class;
            switchTo(StatisticsActivity.class);
        });
    }

    private void switchTo(Class activity) {
        Intent intent = new Intent(this.getContext(), activity);
        startActivity(intent);
    }
}