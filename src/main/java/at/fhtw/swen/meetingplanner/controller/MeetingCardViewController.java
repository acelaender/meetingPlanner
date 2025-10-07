package at.fhtw.swen.meetingplanner.controller;

import at.fhtw.swen.meetingplanner.bl.model.Meeting;
import at.fhtw.swen.meetingplanner.viewModel.MeetingCardViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class MeetingCardViewController {
    @FXML private Label titleLabel;
    @FXML private Label timeLabel;
    @FXML private Label agendaLabel;
    @FXML private Button editButton;
    @FXML private Button deleteButton;
    @FXML private Button expandMeetingButton;

    private final MeetingCardViewModel meetingCardViewModel;

    private Consumer<Meeting> onShowDetails;

    public MeetingCardViewController(MeetingCardViewModel meetingCardViewModel) {
        this.meetingCardViewModel = meetingCardViewModel;
    }

    @FXML
    private void initialize() {
        titleLabel.textProperty().bindBidirectional(meetingCardViewModel.titleProperty());
        agendaLabel.textProperty().bindBidirectional(meetingCardViewModel.agendaProperty());
    }

    //TODO setMeeting should probably be done by viewmodel ,
    //TODO IMPORTANT!!!!!!! delete Meeting import
    public void setMeeting(Meeting meeting){
        meetingCardViewModel.setMeeting(meeting);
    }

    public void setOnShowDetails(Consumer<Meeting> listener){
        this.onShowDetails = listener;
    }

    //callback method ondelete
}
