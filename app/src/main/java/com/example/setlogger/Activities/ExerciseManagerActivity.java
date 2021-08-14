package com.example.setlogger.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.setlogger.Adapters.ExerciseAdapter;
import com.example.setlogger.BottomMenuFragment;
import com.example.setlogger.R;
import com.example.setlogger.repository.DatabaseContainer;
import com.example.setlogger.repository.Services.ExerciseService;
import com.example.setlogger.repository.dto.ExerciseDTO;

import java.util.List;

public class ExerciseManagerActivity extends AppCompatActivity {

    private Button saveButton;
    private Button resetButton;
    private Button deleteButton;
    private Button stopEditButton;

    private EditText nameInput;
    private EditText romInput;

    private int currentSelectedId;

    private RecyclerView exerciseView;
    private ExerciseAdapter exerciseAdapter;

    private boolean isEditing = false;
    private String originalName;
    private String originalRom;

    private DatabaseContainer databaseContainer;
    private ExerciseService exerciseService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_manager);
        getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).add(R.id.fragmentContainerView3, BottomMenuFragment.class, null).commit();

        inputInit();

        databaseContainer = new DatabaseContainer(this);
        exerciseService = new ExerciseService(databaseContainer);
        List<ExerciseDTO> exercises = exerciseService.getAll();


        exerciseView = findViewById(R.id.exercise_view);
        updateRecyclerView(exercises);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        databaseContainer.closeDB();
    }

    private void inputInit() {
        saveButton = findViewById(R.id.save_exercise_button);
        saveButton.setOnClickListener(saveButtonOnClick());

        resetButton = findViewById(R.id.reset_exercise_button);
        resetButton.setOnClickListener(resetButtonOnClick());

        deleteButton = findViewById(R.id.delete_exercise_button);
        deleteButton.setOnClickListener(deleteButtonOnClick());

        nameInput = findViewById(R.id.exercise_name_input);
        romInput = findViewById(R.id.exercise_rom_input);

        stopEditButton = findViewById(R.id.stop_edit_button);
        setStopEditButtonColour(R.color.gray);
        stopEditButton.setOnClickListener(stopEditButtonOnClick());
        stopEditButton.bringToFront();
    }

    private View.OnClickListener stopEditButtonOnClick() {
        return a -> {
            if (!isEditing) {
                return;
            } else {
                isEditing = false;
                saveButton.setText("Save");
                setStopEditButtonColour(R.color.gray);
                setColourOfDeleteButton(R.color.gray);
                nameInput.setText("");
                romInput.setText("");
            }
        };
    }

    private View.OnClickListener deleteButtonOnClick() {
        return a -> {
            if (isEditing) {

                ExerciseDTO exerciseDTO = new ExerciseDTO();
                exerciseDTO.setId(currentSelectedId);
                exerciseService.delete(exerciseDTO);
                List<ExerciseDTO> list = exerciseService.getAll();
                isEditing = false;
                saveButton.setText("Save");
                setColourOfDeleteButton(R.color.gray);
                setStopEditButtonColour(R.color.gray);
                nameInput.setText("");
                romInput.setText("");
                updateRecyclerView(list);
            }
        };
    }

    private View.OnClickListener resetButtonOnClick() {
        return a -> {
            if (isEditing) {
                nameInput.setText(originalName);
                romInput.setText(originalRom);
            } else {
                nameInput.setText("");
                romInput.setText("");
            }
        };
    }

    private View.OnClickListener saveButtonOnClick() {
        return a -> {
            String exerciseName = nameInput.getText().toString();

            if (exerciseName.length() == 0) {
                return;
            }

            exerciseName.toLowerCase();
            double exerciseRom;
            try {
                exerciseRom = Double.parseDouble(romInput.getText().toString());
            } catch (Exception e) {
                exerciseRom = -1;
            }

            ExerciseDTO e = exerciseService.findByName(exerciseName);

            ExerciseDTO exerciseDTO = new ExerciseDTO();
            exerciseDTO.setName(exerciseName);
            exerciseDTO.setRom(exerciseRom);
            if (!isEditing) {
                if (e == null) {
                    exerciseService.create(exerciseDTO);
                }
            } else {
                exerciseDTO.setId(currentSelectedId);
                exerciseService.update(exerciseDTO);
                saveButton.setText("Save");
                isEditing = false;
                setStopEditButtonColour(R.color.gray);
                setColourOfDeleteButton(R.color.gray);
            }
            nameInput.setText("");
            romInput.setText("");
            updateRecyclerView(exerciseService.getAll());
            //TODO make dialogue thing go bling blong
        };
    }

    private void updateRecyclerView(List<ExerciseDTO> exercises) {
        exerciseView.setLayoutManager(new LinearLayoutManager(this));
        exerciseAdapter = new ExerciseAdapter(exercises, this);

        exerciseView.setAdapter(exerciseAdapter);
    }

    public void setCurrentSelectedId(int currentSelectedId) {
        this.currentSelectedId = currentSelectedId;
    }

    public void setSaveButtonText(String str) {
        saveButton.setText(str);
    }

    public void setIsEditing(boolean b) {
        isEditing = b;
    }

    public void setDisplayName(String name) {
        nameInput.setText(name);
        originalName = name;
    }

    public void setDisplayRom(String rom) {
        romInput.setText(rom);
        originalRom = rom;
    }

    public void setStopEditButtonColour(int id) {
        stopEditButton.setBackgroundColor(getResources().getColor(id));
    }

    public void setColourOfDeleteButton(int id) {
        deleteButton.setBackgroundColor(getResources().getColor(id));
    }

}
