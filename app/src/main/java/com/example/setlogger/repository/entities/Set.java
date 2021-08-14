package com.example.setlogger.repository.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "set_table")
public class Set {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name="exercise_id")
    public int exercise_id;

    @ColumnInfo(name = "reps")
    public int reps;

    @ColumnInfo(name="weight")
    public float weight;
}
