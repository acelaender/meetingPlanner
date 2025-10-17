package at.fhtw.swen.meetingplanner.viewModel;

import at.fhtw.swen.meetingplanner.bl.model.Meeting;
import at.fhtw.swen.meetingplanner.bl.service.MeetingService;
import at.fhtw.swen.meetingplanner.bl.service.NoteService;
import javafx.beans.property.*;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class MeetingDetailViewModel {
    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty title = new SimpleStringProperty();
    private final ObjectProperty<LocalTime> startTime = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalTime> endTime = new SimpleObjectProperty<>();
    private final StringProperty agenda = new SimpleStringProperty();
    private final BooleanProperty showAddNoteForm = new SimpleBooleanProperty(false);

    //TODO Rework Variables
    private Meeting meeting;


    //TODO Note-List

    private final NoteService noteService;

    public MeetingDetailViewModel(NoteService noteService) {
        this.noteService = noteService;
    }

    public void setMeeting(Meeting meeting){
        this.id.set(meeting.getId());
        this.title.set(meeting.getTitle());
        this.startTime.set(meeting.getStartTime());
        this.endTime.set(meeting.getEndTime());
        this.agenda.set(meeting.getAgenda());
        this.meeting = meeting;
    }

    public StringProperty titleProperty() {
        return title;
    }
    public StringProperty agendaProperty() {
        return agenda;
    }

    public void toggleAddNoteForm() {
        showAddNoteForm.set(!showAddNoteForm.get());
    }

    public BooleanProperty showAddMeetingForm() {
        return showAddNoteForm;
    }

    public Meeting getMeeting() {
        return meeting;
    }
}
