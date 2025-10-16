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
    private Runnable onShowDetails;
    private Runnable onDelete;

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

    public void deleteMeeting(){
        meetingService.deleteMeeting(this.meeting);
        if(this.onDelete != null){
            this.onDelete.run();
        }
    }

    public void setOnDelete(Runnable callback){
        this.onDelete = callback;
    }
    public void setOnShowDetails(Runnable callback) {
        this.onShowDetails = callback;
    }

    public void showDetails() {
        if (onShowDetails != null) {
            onShowDetails.run();
        }
    }

    public StringProperty titleProperty() {
        return title;
    }


    public StringProperty agendaProperty() {
        return agenda;
    }
}
