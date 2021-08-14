package com.example.setlogger.Activities;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.setlogger.Adapters.ExerciseSearchResultAdapter;
import com.example.setlogger.BottomMenuFragment;
import com.example.setlogger.R;
import com.example.setlogger.repository.DatabaseContainer;
import com.example.setlogger.repository.Services.BufferedSetService;
import com.example.setlogger.repository.Services.ExerciseService;
import com.example.setlogger.repository.dto.BufferedSetDTO;
import com.example.setlogger.repository.dto.ExerciseDTO;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button submitBTN;

    EditText exerciseInput;
    EditText weightInput;
    EditText repsInput;

    RecyclerView searchResultList;
    ExerciseSearchResultAdapter exerciseSearchResultAdapter;
    List<ExerciseDTO> searchResults;

    DatabaseContainer databaseContainer;
    ExerciseService exerciseService;
    BufferedSetService bufferedSetService;

    TextView exerciseTextView;

    private float exerciseInputStartY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputInit();
        exerciseTextView = findViewById(R.id.exercise_text_view);

        databaseContainer = new DatabaseContainer(this);
        exerciseService = new ExerciseService(databaseContainer);
        bufferedSetService = new BufferedSetService(databaseContainer);

        resultsListInit();

        getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).add(R.id.fragmentContainerView, BottomMenuFragment.class, null).commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        databaseContainer.closeDB();
    }

    private void inputInit() {
        exerciseInput = findViewById(R.id.exercise_name_input);
        exerciseInput.addTextChangedListener(exerciseInputTextChange());
        exerciseInput.setOnTouchListener(exerciseInputOnTouch());
        exerciseInput.setOnEditorActionListener(exerciseInputOnEditorAction());
        exerciseInput.setOnFocusChangeListener(exerciseInputOnFocusChange());
        exerciseInputStartY = exerciseInput.getY();

        weightInput = findViewById(R.id.weight_input);
        weightInput.setOnEditorActionListener(weightInputOnEditorAction());

        repsInput = findViewById(R.id.reps_input);
        repsInput.setOnEditorActionListener(repsInputOnEditorAction());

        submitBTN = findViewById(R.id.submitBTN);
        submitBTN.setOnClickListener(submitBTNOnClick());
        updateSaveButtonColour();
    }

    private View.OnClickListener submitBTNOnClick() {
        return a -> {
            if (inputIsValid()) {
                BufferedSetDTO dto = new BufferedSetDTO();
                dto.setExercise_id(exerciseService.findByName(exerciseInput.getText().toString()).getId());
                dto.setWeight(Float.valueOf(weightInput.getText().toString()));
                dto.setReps(Integer.valueOf(repsInput.getText().toString()));
                dto.setTime(System.currentTimeMillis());
                bufferedSetService.create(dto);
                clearInputFields();
            }
        };
    }

    private void clearInputFields() {
        exerciseInput.setText("");
        weightInput.setText("");
        repsInput.setText("");
        updateSaveButtonColour();
        moveExerciseInputToStartPos();
    }

    private boolean inputIsValid() {
        try {
            if (exerciseService.findByName(exerciseInput.getText().toString()) != null &&
                    Double.valueOf(weightInput.getText().toString()) >= 0 &&
                    Integer.valueOf(repsInput.getText().toString()) >= 0
            ) {
                return true;
            }
        } catch (Exception e) {
            //do nothing
        }
        return false;
    }

    ;

    private View.OnFocusChangeListener exerciseInputOnFocusChange() {
        return new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    moveExerciseInputToStartPos();
                }
                updateSaveButtonColour();
            }
        };
    }


    private TextView.OnEditorActionListener repsInputOnEditorAction() {
        return new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_DONE) {
                    repsInput.clearFocus();
                }
                updateSaveButtonColour();
                return false;
            }
        };
    }

    private TextView.OnEditorActionListener weightInputOnEditorAction() {
        return new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_DONE) {
                    weightInput.clearFocus();
                }
                updateSaveButtonColour();
                return false;
            }
        };
    }

    private TextView.OnEditorActionListener exerciseInputOnEditorAction() {
        return new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_DONE) {
                    moveExerciseInputToStartPos();
                }
                updateSaveButtonColour();
                return false;
            }
        };
    }

    private void setSaveButtonColour(int colour) {
        submitBTN.setBackgroundColor(colour);
    }

    private View.OnTouchListener exerciseInputOnTouch() {
        return new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return false;
            }
        };
    }

    private TextWatcher exerciseInputTextChange() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                moveExerciseInputToTop();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String name = exerciseInput.getText().toString();

                if (name.length() > 0) {
                    searchResults = exerciseService.findNameLike(exerciseInput.getText().toString());
                    updateResultList();
                    showResultsList();
                } else {
                    hideResultsList();
                }
                updateSaveButtonColour();
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        };
    }

    private void updateSaveButtonColour() {
        if (inputIsValid()) {
            setSaveButtonColour(getResources().getColor(R.color.green));
        } else {
            setSaveButtonColour(getResources().getColor(R.color.gray));
        }
    }

    private void moveExerciseInputToTop() {
        exerciseInput.setY(0);
        exerciseTextView.setY(0);
        showResultsList();
    }

    public void moveExerciseInputToStartPos() {
        exerciseInput.clearFocus();
        exerciseInput.clearAnimation();
        exerciseInput.setTranslationY(exerciseInputStartY);
        exerciseTextView.setTranslationY(exerciseInputStartY);
        hideResultsList();
        exerciseInput.bringToFront();
    }


    private void resultsListInit() {
        searchResultList = findViewById(R.id.exercise_search_result_list);
        searchResults = new ArrayList<ExerciseDTO>();
        searchResultList.setHasFixedSize(false);
        updateResultList();
        hideResultsList();
    }

    private void hideResultsList() {
        searchResultList.setVisibility(View.GONE);
    }

    private void showResultsList() {
        searchResultList.setVisibility(View.VISIBLE);
        searchResultList.bringToFront();
    }

    private void updateResultList() {
        searchResultList.setLayoutManager(new LinearLayoutManager(this));
        exerciseSearchResultAdapter = new ExerciseSearchResultAdapter(searchResults, exerciseInput);
        searchResultList.setAdapter(exerciseSearchResultAdapter);
    }

    private void fillNameInput(String name) {
        exerciseInput.setText(name);
    }
}