package at.fhtw.swen2.meetingplanner.dal.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class NoteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull(message = "title cannot be null")
    @NotBlank(message = "title cannot be blank")
    private String title;
    @NotNull(message = "content cannot be null")
    private String content;
    @ManyToOne(optional = false)
    @JoinColumn(name="meeting_id", nullable = false, updatable = false)
    @Setter
    private MeetingEntity meetingEntity;

    public NoteEntity(String title, String content, MeetingEntity meeting) {
        this.title = title;
        this.content = content;
        this.meetingEntity = meeting;
    }
}
