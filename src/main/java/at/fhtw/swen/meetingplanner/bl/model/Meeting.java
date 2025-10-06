package at.fhtw.swen.meetingplanner.bl.model;

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

    public Meeting(int id, String title, LocalTime startTime, LocalTime endTime, String agenda) {
        this.id = id;
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
