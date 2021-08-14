package com.example.setlogger.repository.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "session_table")
public class Session {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "time")
    public int time;
}
