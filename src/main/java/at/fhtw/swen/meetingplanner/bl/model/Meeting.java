package at.fhtw.swen.meetingplanner.bl.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = "notes")
@Entity
public class Meeting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull(message = "Title cannot be null")
    @NotBlank(message = "Title cannot be blank")
    private String title;
    @NotNull(message = "startTime cannot be null")
    private LocalTime startTime;
    @NotNull(message = "endTime cannot be null")
    private LocalTime endTime;
    private String agenda;

    @OneToMany(mappedBy = "meetingEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private final List<Note> notes = new ArrayList<>();

    public Meeting(String title, LocalTime startTime, LocalTime endTime, String agenda) {
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        this.agenda = agenda;
    }
}