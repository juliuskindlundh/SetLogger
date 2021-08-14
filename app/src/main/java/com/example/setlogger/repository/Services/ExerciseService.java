package com.example.setlogger.repository.Services;

import com.example.setlogger.repository.DatabaseContainer;
import com.example.setlogger.repository.dao.ExerciseDAO;
import com.example.setlogger.repository.dto.ExerciseDTO;
import com.example.setlogger.repository.entities.Exercise;

import java.util.ArrayList;
import java.util.List;

public class ExerciseService implements BasicCRUDInterface<ExerciseDTO> {

    private ExerciseDAO exerciseDAO;

    public ExerciseService(DatabaseContainer databaseContainer) {
        this.exerciseDAO = databaseContainer.getDB().exerciseDAO();
    }

    @Override
    public List<ExerciseDTO> getAll() {
        List<Exercise> exercises = exerciseDAO.getAll();
        return convertToDTOList(exercises);
    }

    @Override
    public ExerciseDTO findById(int id) {
        return Converter.ExerciseToDTO(exerciseDAO.getById(id));
    }

    @Override
    public void create(ExerciseDTO toCreate) {
        toCreate.setName(cleanUpName(toCreate.getName()));
        exerciseDAO.insert(Converter.DTOToExercise(toCreate));
    }

    @Override
    public void update(ExerciseDTO toUpdate) {
        toUpdate.setName(cleanUpName(toUpdate.getName()));
        exerciseDAO.update(Converter.DTOToExercise(toUpdate));
    }

    @Override
    public void delete(ExerciseDTO toDelete) {
        toDelete.setName(cleanUpName(toDelete.getName()));
        exerciseDAO.delete(Converter.DTOToExercise(toDelete));
    }

    public ExerciseDTO findByName(String exerciseName) {
        return Converter.ExerciseToDTO(exerciseDAO.findByName(cleanUpName(exerciseName)));
    }

    public List<ExerciseDTO> findNameLike(String name){
        List<Exercise> exercises = exerciseDAO.findNameLike(cleanUpName(name));
        return convertToDTOList(exercises);
    }

    private String cleanUpName(String s){
        if(s != null){
            s.toLowerCase();
            s.trim();
        }
        return s;
    }

    private List<ExerciseDTO> convertToDTOList(List<Exercise> exercises){
        List<ExerciseDTO> exerciseDTOS = new ArrayList<ExerciseDTO>();
        int i = 0;
        while (i < exercises.size()) {
            exerciseDTOS.add(Converter.ExerciseToDTO(exercises.get(i)));
            i++;
        }
        return exerciseDTOS;
    }

}
