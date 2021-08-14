package com.example.setlogger.repository;

import android.content.Context;

import androidx.room.Room;

public class DatabaseContainer {

    private Database database;

    public DatabaseContainer(Context context) {
        database = Room.databaseBuilder(context, Database.class, "database_v1.0").allowMainThreadQueries().fallbackToDestructiveMigration().build();
    }

    public void closeDB() {
        database.close();
    }

    public Database getDB() {
        return database;
    }


}
