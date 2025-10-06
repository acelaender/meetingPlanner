package at.fhtw.swen.meetingplanner.viewModel;

import at.fhtw.swen.meetingplanner.bl.service.MeetingService;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class AddMeetingViewModel {

    private final MeetingService meetingService;

    private final StringProperty title = new SimpleStringProperty();
    private final ObjectProperty<LocalTime> startTime = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalTime> endTime = new SimpleObjectProperty<>();
    private final StringProperty agenda = new SimpleStringProperty();


    public StringProperty titleProperty() { return title; }

    public StringProperty agendaProperty() { return agenda; }

    public ObjectProperty<LocalTime> startTimeProperty() { return startTime; }
    public ObjectProperty<LocalTime> endTimeProperty() { return endTime; }

    public AddMeetingViewModel(MeetingService meetingService) {
        this.meetingService = meetingService;
    }


    public void saveMeeting() {
        meetingService.createMeeting(title.get(), startTime.get(), endTime.get(), agenda.get());
    }

}
