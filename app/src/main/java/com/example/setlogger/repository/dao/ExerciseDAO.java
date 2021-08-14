package com.example.setlogger.repository.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.setlogger.repository.entities.Exercise;

import java.util.List;

@Dao
public interface ExerciseDAO {

    @Query("SELECT * FROM exercise_table")
    List<Exercise> getAll();

    @Query("SELECT * FROM exercise_table WHERE name = :name")
    Exercise findByName(String name);

    @Query("SELECT * FROM exercise_table WHERE id = :id")
    Exercise getById(int id);

    @Insert()
    void insert(Exercise exercise);

    @Update()
    void update(Exercise exercise);

    @Delete()
    void delete(Exercise exercise);

    @Query("SELECT * FROM exercise_table WHERE name LIKE (:name||'%')")
    List<Exercise> findNameLike(String name);
}
