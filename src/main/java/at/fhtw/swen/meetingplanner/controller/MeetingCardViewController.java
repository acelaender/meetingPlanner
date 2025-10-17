package at.fhtw.swen.meetingplanner.controller;

import at.fhtw.swen.meetingplanner.bl.model.Meeting;
import at.fhtw.swen.meetingplanner.viewModel.MeetingCardViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.springframework.context.annotation.Scope;
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


    public MeetingCardViewController(MeetingCardViewModel meetingCardViewModel) {
        this.meetingCardViewModel = meetingCardViewModel;
        System.out.println("New MeetingCardViewController: " + this);
    }

    @FXML
    private void initialize() {
        titleLabel.textProperty().bindBidirectional(meetingCardViewModel.titleProperty());
        agendaLabel.textProperty().bindBidirectional(meetingCardViewModel.agendaProperty());
    }

    @FXML
    protected void onExpandMeetingButtonClick(){
        meetingCardViewModel.showDetails();
    }

    @FXML
    protected void onDeleteButtonClick(){
        meetingCardViewModel.deleteMeeting();
    }

    public void setMeeting(Meeting meeting){
        meetingCardViewModel.setMeeting(meeting);
    }

    public void setOnShowDetails(Runnable onShowDetails){
        meetingCardViewModel.setOnShowDetails(onShowDetails);
    }

    public void setOnDelete(Runnable onDelete) {
        meetingCardViewModel.setOnDelete(onDelete);
    }

}
