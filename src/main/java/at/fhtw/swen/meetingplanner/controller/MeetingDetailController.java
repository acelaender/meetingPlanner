package at.fhtw.swen.meetingplanner.controller;

import at.fhtw.swen.meetingplanner.bl.model.Meeting;
import at.fhtw.swen.meetingplanner.viewModel.MeetingDetailViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.springframework.stereotype.Component;

@Component
public class MeetingDetailController {

    @FXML Label titleLabel;
    @FXML Label agendaLabel;
    @FXML Button addNoteButton;
    @FXML VBox noteContainer;


    private final MeetingDetailViewModel meetingDetailViewModel;

    public MeetingDetailController(MeetingDetailViewModel meetingDetailViewModel) {
        this.meetingDetailViewModel = meetingDetailViewModel;
    }

    @FXML
    private void initialize() {
        titleLabel.textProperty().bindBidirectional(meetingDetailViewModel.titleProperty());
        agendaLabel.textProperty().bindBidirectional(meetingDetailViewModel.agendaProperty());
    }

    public void setMeeting(Meeting meeting){
        this.meetingDetailViewModel.setMeeting(meeting);
    }
}
