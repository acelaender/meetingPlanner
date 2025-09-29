package at.fhtw.swen2.meetingplanner.controller;

import at.fhtw.swen2.meetingplanner.bl.model.Meeting;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.springframework.stereotype.Component;

@Component
public class MeetingCardViewController {
    @FXML private Label titleLabel;
    @FXML private Label timeLabel;
    @FXML private Label agendaLabel;

    //TODO setMeeting should probably be done by viewmodel ,
    //TODO IMPORTANT!!!!!!! delete Meeting import
    public void setMeeting(Meeting meeting){
        titleLabel.setText(meeting.getTitle());
        timeLabel.setText(meeting.getStartTime() + "-" + meeting.getEndTime());
        agendaLabel.setText(meeting.getAgenda());
    }
}
