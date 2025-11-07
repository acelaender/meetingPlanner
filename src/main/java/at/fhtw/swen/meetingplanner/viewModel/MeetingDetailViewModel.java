package at.fhtw.swen.meetingplanner.viewModel;

import at.fhtw.swen.meetingplanner.bl.model.Meeting;
import at.fhtw.swen.meetingplanner.bl.model.Note;
import at.fhtw.swen.meetingplanner.bl.service.MeetingService;
import at.fhtw.swen.meetingplanner.bl.service.NoteService;
import at.fhtw.swen.meetingplanner.bl.service.ReportService;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Component
public class MeetingDetailViewModel {
    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty title = new SimpleStringProperty();
    private final ObjectProperty<LocalTime> startTime = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalTime> endTime = new SimpleObjectProperty<>();
    private final StringProperty agenda = new SimpleStringProperty();
    private final BooleanProperty showAddNoteForm = new SimpleBooleanProperty(false);
    //EDIT MEETING//
    private final BooleanProperty showEditMeeting = new SimpleBooleanProperty(false);
    private Runnable onSave;
    private Runnable onClose;

    private Meeting meeting;


    private final ObservableList<Note> notes = FXCollections.observableArrayList();

    private final NoteService noteService;
    private final MeetingService meetingService;
    private final ReportService reportService;

    public MeetingDetailViewModel(NoteService noteService, MeetingService meetingService, ReportService reportService) {
        this.noteService = noteService;
        this.meetingService = meetingService;
        this.reportService = reportService;
    }

    public void setMeeting(Meeting meeting){
        this.id.set(meeting.getId());
        this.title.set(meeting.getTitle());
        this.startTime.set(meeting.getStartTime());
        this.endTime.set(meeting.getEndTime());
        this.agenda.set(meeting.getAgenda());
        this.meeting = meeting;
        this.showEditMeeting.set(false);
    }

    public StringProperty titleProperty() {
        return title;
    }
    public StringProperty agendaProperty() {
        return agenda;
    }
    public ObjectProperty startTimeProperty() {
        return this.startTime;
    }
    public ObjectProperty endTimeProperty() {
        return this.endTime;
    }
    public LocalTime startTime(){
        return this.startTime.get();
    }
    public  LocalTime endTime(){
        return this.endTime.get();
    }

    public void toggleAddNoteForm() {
        showAddNoteForm.set(!showAddNoteForm.get());
    }

    public void toggleEditMeeting() {
        showEditMeeting.set(!showEditMeeting.get());
    }
    public BooleanProperty showAddMeetingForm() {
        return showAddNoteForm;
    }
    public BooleanProperty editNoteProperty() { return showEditMeeting; }

    public Meeting getMeeting() {
        return meeting;
    }

    public ObservableList<Note> getNotes(){
        loadMeetings();
        return this.notes;
    }

    public void saveMeeting(){
        this.meeting.setTitle(title.get());
        this.meeting.setAgenda(agenda.get());
        this.meeting.setStartTime(startTime.get());
        this.meeting.setEndTime(endTime.get());
        meetingService.updateMeeting(meeting);
        this.onClose.run();
    }

    public File exportMeeting(String filePath) throws IOException {
        return reportService.generateMeetingReport(this.meeting, filePath);
    }

    public void closeMeeting() {
        this.onClose.run();
    }

    public void deleteMeeting() {
        this.meetingService.deleteMeeting(this.meeting);
        closeMeeting();
    }

    public void loadMeetings() {
        notes.setAll(noteService.getMeetingNotes(this.meeting));
    }

    public void setOnClose(Runnable callback) {
        this.onClose = callback;
    }
}
