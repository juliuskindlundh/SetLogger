package com.example.setlogger.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.setlogger.Activities.ExerciseManagerActivity;
import com.example.setlogger.R;
import com.example.setlogger.repository.dto.ExerciseDTO;

import java.util.List;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ViewHolder> {

    private List<ExerciseDTO> exercises;
    private ExerciseManagerActivity exerciseManagerActivity;

    public ExerciseAdapter(List<ExerciseDTO> exercises, ExerciseManagerActivity exerciseManagerActivity) {
        this.exercises = exercises;
        this.exerciseManagerActivity = exerciseManagerActivity;
    }

    @Override
    public ExerciseAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.exercise_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ExerciseAdapter.ViewHolder holder, int position) {
        holder.getNameTextView().setText(exercises.get(position).getName());
        holder.getRomTextView().setText(String.valueOf(exercises.get(position).getRom()));
        holder.setId(exercises.get(position).getId());
    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nameTextView;
        TextView romTextView;
        Button editButton;

        private int id;

        public ViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.exercise_name_display);
            romTextView = itemView.findViewById(R.id.exerise_rom_display);
            editButton = itemView.findViewById(R.id.edit_exercise_button);
            editButton.setOnClickListener(editButtonOnClick(itemView));
        }

        private View.OnClickListener editButtonOnClick(View itemView) {
            return a -> {
                exerciseManagerActivity.setCurrentSelectedId(id);
                exerciseManagerActivity.setSaveButtonText("Update");
                exerciseManagerActivity.setIsEditing(true);
                exerciseManagerActivity.setDisplayName(nameTextView.getText().toString());
                exerciseManagerActivity.setDisplayRom(romTextView.getText().toString());
                exerciseManagerActivity.setStopEditButtonColour(R.color.design_default_color_primary_variant);
                exerciseManagerActivity.setColourOfDeleteButton(R.color.red);
            };
        }


        public TextView getNameTextView() {
            return nameTextView;
        }

        public TextView getRomTextView() {
            return romTextView;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

}

