package at.fhtw.swen2.meetingplanner.viewModel;

import at.fhtw.swen2.meetingplanner.bl.model.Meeting;
import at.fhtw.swen2.meetingplanner.bl.service.MeetingService;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import org.springframework.stereotype.Component;

@Component
public class MainViewModel {
    private final BooleanProperty showAddMeetingForm = new SimpleBooleanProperty(false);
    private final ObservableList<Meeting> meetings = FXCollections.observableArrayList();
    private final MeetingService meetingService;

    public MainViewModel(MeetingService meetingService){
        this.meetingService = meetingService;
    }

    public void toggleDetails() {
        showAddMeetingForm.set(!showAddMeetingForm.get());
    }

    public BooleanProperty showDetailsProperty() {
        return showAddMeetingForm;
    }

    public ObservableList<Meeting> getMeetings(){
        return this.meetings;
    }

    public void loadMeetings() {
        meetings.setAll(meetingService.getAllMeetings());
    }
}
