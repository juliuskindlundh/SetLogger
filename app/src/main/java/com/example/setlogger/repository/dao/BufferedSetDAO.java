package com.example.setlogger.repository.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.setlogger.repository.entities.BufferedSet;

import java.util.List;

@Dao
public interface BufferedSetDAO {

    @Query("SELECT * FROM buffered_set_table")
    List<BufferedSet> getAll();

    @Query("SELECT * FROM buffered_set_table WHERE id = :id")
    BufferedSet findById(int id);

    @Insert()
    void insert(BufferedSet set);

    @Update
    void update(BufferedSet set);

    @Delete
    void delete(BufferedSet set);
}
