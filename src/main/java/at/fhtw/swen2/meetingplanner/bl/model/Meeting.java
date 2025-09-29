package at.fhtw.swen2.meetingplanner.bl.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


public class Meeting {

    private int id;
    private String title;
    private LocalTime startTime;
    private LocalTime endTime;
    private String agenda;

    private final List<Note> notes = new ArrayList<>();

    public Meeting(String title, LocalTime startTime, LocalTime endTime, String agenda) {
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        this.agenda = agenda;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public String getAgenda() {
        return agenda;
    }

    public void setAgenda(String agenda) {
        this.agenda = agenda;
    }
}
