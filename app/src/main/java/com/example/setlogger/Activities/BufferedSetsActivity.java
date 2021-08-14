package com.example.setlogger.Activities;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.setlogger.Adapters.BufferedSetAdapter;
import com.example.setlogger.BottomMenuFragment;
import com.example.setlogger.BufferedSetOptionsFragment;
import com.example.setlogger.R;
import com.example.setlogger.repository.DatabaseContainer;
import com.example.setlogger.repository.Services.BufferedSetService;
import com.example.setlogger.repository.Services.ExerciseService;
import com.example.setlogger.repository.dto.BufferedSetDTO;

import java.util.List;

public class BufferedSetsActivity extends AppCompatActivity {

    DatabaseContainer databaseContainer;
    BufferedSetService bufferedSetService;
    ExerciseService exerciseService;

    RecyclerView bufferedSetList;
    List<BufferedSetDTO> bufferedSetDTOS;
    BufferedSetAdapter bufferedSetAdapter;

    private int selectedId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buffered_sets);

        databaseContainer = new DatabaseContainer(this);
        bufferedSetService = new BufferedSetService(databaseContainer);
        exerciseService = new ExerciseService(databaseContainer);

        bufferedSetListInit();

        getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).add(R.id.fragmentContainerView_options_for_bufferedsets, BufferedSetOptionsFragment.class, null).commit();
        getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).add(R.id.fragmentContainerView2, BottomMenuFragment.class,null).commit();

    }

    private View.OnClickListener commitAllOnClick() {
        return a->{

        };
    }

    private void bufferedSetListInit() {
        bufferedSetList = findViewById(R.id.buffered_set_recycler_view);
        bufferedSetDTOS = bufferedSetService.getAll();
        updateResultList();
    }

    public void updateResultList() {
        bufferedSetList.setLayoutManager(new LinearLayoutManager(this));
        bufferedSetAdapter = new BufferedSetAdapter(bufferedSetDTOS,this);
        bufferedSetList.setAdapter(bufferedSetAdapter);
        bufferedSetList.bringToFront();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        databaseContainer.closeDB();
    }

    public ExerciseService getExerciseService(){
        return exerciseService;
    }

    public BufferedSetService getBufferedSetService() {
        return bufferedSetService;
    }

    public void setSelectedId(int id) {
        this.selectedId = id;
    }

    public int getSelectedId(){
        return this.selectedId;
    }
}