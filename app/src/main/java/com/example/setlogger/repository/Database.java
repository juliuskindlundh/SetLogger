package com.example.setlogger.repository;

import androidx.room.RoomDatabase;

import com.example.setlogger.repository.dao.BufferedSetDAO;
import com.example.setlogger.repository.dao.ExerciseDAO;
import com.example.setlogger.repository.dao.SessionAndSetDAO;
import com.example.setlogger.repository.dao.SessionDAO;
import com.example.setlogger.repository.dao.SetDAO;
import com.example.setlogger.repository.entities.BufferedSet;
import com.example.setlogger.repository.entities.Exercise;
import com.example.setlogger.repository.entities.Session;
import com.example.setlogger.repository.entities.Set;

@androidx.room.Database(entities = {Exercise.class, Set.class, Session.class, BufferedSet.class}, version = 4, exportSchema = false)
public abstract class Database extends RoomDatabase {
    public abstract ExerciseDAO exerciseDAO();

    public abstract SetDAO setDAO();

    public abstract SessionDAO sessionDAO();

    public abstract SessionAndSetDAO sessionAndSetDAO();

    public abstract BufferedSetDAO bufferedSetDAO();
}
