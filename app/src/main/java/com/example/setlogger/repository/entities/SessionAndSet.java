package com.example.setlogger.repository.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName = "session_and_set",primaryKeys = {"session_id","set_id"})
public class SessionAndSet {
    public int session_id;
    public int set_id;
    @ColumnInfo(name = "occurrences")
    public int occurrences;



}
