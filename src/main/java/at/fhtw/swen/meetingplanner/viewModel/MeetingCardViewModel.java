package at.fhtw.swen.meetingplanner.viewModel;

import at.fhtw.swen.meetingplanner.bl.model.Meeting;
import at.fhtw.swen.meetingplanner.bl.service.MeetingService;
import javafx.beans.property.*;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class MeetingCardViewModel {
    private Meeting meeting = null;
    private final MeetingService meetingService;

    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty title = new SimpleStringProperty();
    private final ObjectProperty<LocalTime> startTime = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalTime> endTime = new SimpleObjectProperty<>();
    private final StringProperty agenda = new SimpleStringProperty();

    public MeetingCardViewModel(MeetingService meetingService) {
        this.meetingService = meetingService;
    }

    public void setMeeting(Meeting meeting){
        this.meeting = meeting;
        this.id.set(meeting.getId());
        this.title.set(meeting.getTitle());
        this.startTime.set(meeting.getStartTime());
        this.endTime.set(meeting.getEndTime());
        this.agenda.set(meeting.getAgenda());
    }

    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

    public LocalTime getStartTime() {
        return startTime.get();
    }

    public ObjectProperty<LocalTime> startTimeProperty() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime.get();
    }

    public ObjectProperty<LocalTime> endTimeProperty() {
        return endTime;
    }

    public String getAgenda() {
        return agenda.get();
    }

    public StringProperty agendaProperty() {
        return agenda;
    }
}
