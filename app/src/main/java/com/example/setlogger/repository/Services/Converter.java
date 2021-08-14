package com.example.setlogger.repository.Services;

import com.example.setlogger.repository.dto.BufferedSetDTO;
import com.example.setlogger.repository.dto.ExerciseDTO;
import com.example.setlogger.repository.dto.SessionAndSetDTO;
import com.example.setlogger.repository.dto.SessionDTO;
import com.example.setlogger.repository.dto.SetDTO;
import com.example.setlogger.repository.entities.BufferedSet;
import com.example.setlogger.repository.entities.Exercise;
import com.example.setlogger.repository.entities.Session;
import com.example.setlogger.repository.entities.SessionAndSet;
import com.example.setlogger.repository.entities.Set;

public class Converter {

    private Converter() {
    }

    public static Exercise DTOToExercise(ExerciseDTO dto) {
        if(dto == null){
            return null;
        }
        Exercise e = new Exercise();
        e.id = dto.getId();
        e.rom = dto.getRom();
        e.name = dto.getName();
        return e;
    }

    public static ExerciseDTO ExerciseToDTO(Exercise e) {
        if(e == null){
            return null;
        }
        ExerciseDTO dto = new ExerciseDTO();
        dto.setId(e.id);
        dto.setName(e.name);
        dto.setRom(e.rom);
        return dto;
    }

    public static Session DTOToSession(SessionDTO dto) {
        if(dto == null){
            return null;
        }
        Session s = new Session();
        s.id = dto.getId();
        s.time = dto.getTime();
        return s;
    }

    public static SessionDTO SessionToDTO(Session s) {
        if(s == null){
            return null;
        }
        SessionDTO dto = new SessionDTO();
        dto.setId(s.id);
        dto.setTime(s.time);
        return dto;
    }

    public static Set DTOToSet(SetDTO dto) {
        if(dto == null){
            return null;
        }
        Set s = new Set();
        s.exercise_id = dto.getExercise_id();
        s.id = dto.getId();
        s.reps = dto.getReps();
        s.weight = dto.getWeight();
        return s;
    }

    public static SetDTO SetToDTO(Set s) {
        if(s == null){
            return null;
        }
        SetDTO dto = new SetDTO();
        dto.setExercise_id(s.exercise_id);
        dto.setId(s.id);
        dto.setReps(s.reps);
        dto.setWeight(s.weight);
        return dto;
    }

    public static SessionAndSet DTOToSessionAndSet(SessionAndSetDTO dto) {
        if(dto == null){
            return null;
        }
        SessionAndSet s = new SessionAndSet();
        s.occurrences = dto.getOccurrences();
        s.session_id = dto.getSession_id();
        s.set_id = dto.getSet_id();
        return s;
    }

    public static SessionAndSetDTO SessionAndSetToDTO(SessionAndSet s) {
        if(s == null){
            return null;
        }
        SessionAndSetDTO dto = new SessionAndSetDTO();
        dto.setOccurrences(s.occurrences);
        dto.setSession_id(s.session_id);
        dto.setSet_id(s.set_id);
        return dto;
    }

    public static BufferedSet DTOToBufferedSet(BufferedSetDTO dto) {
        if(dto == null){
            return null;
        }
        BufferedSet b = new BufferedSet();
        b.exercise_id = dto.getExercise_id();
        b.id = dto.getId();
        b.reps = dto.getReps();
        b.session_id = dto.getSession_id();
        b.weight = dto.getWeight();
        b.time = dto.getTime();
        return b;
    }

    public static BufferedSetDTO BufferedSetToDTO(BufferedSet b) {
        if(b == null){
            return null;
        }
        BufferedSetDTO dto = new BufferedSetDTO();
        dto.setExercise_id(b.exercise_id);
        dto.setId(b.id);
        dto.setReps(b.reps);
        dto.setSession_id(b.session_id);
        dto.setWeight(b.weight);
        dto.setTime(b.time);
        return dto;
    }
}
