package com.example.setlogger.repository.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "buffered_set_table")
public class BufferedSet {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name="session_id")
    public int session_id;

    @ColumnInfo(name="exercise_id")
    public int exercise_id;

    @ColumnInfo(name = "reps")
    public int reps;

    @ColumnInfo(name="weight")
    public float weight;

    @ColumnInfo(name="time")
     public long time;
}
