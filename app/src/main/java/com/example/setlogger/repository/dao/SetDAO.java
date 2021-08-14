package com.example.setlogger.repository.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.setlogger.repository.entities.Set;

import java.util.List;

@Dao
public interface SetDAO {

    @Query("SELECT * FROM set_table")
    List<Set> getAll();

    @Query("SELECT * FROM set_table WHERE id = :id")
    Set findById(int id);

    @Insert()
    void insert(Set set);

    @Update
    void update(Set set);

    @Delete
    void delete(Set set);
}
