package at.fhtw.swen2.meetingplanner.bl.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class Note {

    private int id;
    private String title;
    private String content;
    private Meeting meeting;

    public Note(String title, String content, Meeting meeting) {
        this.title = title;
        this.content = content;
        this.meeting = meeting;
    }
}
