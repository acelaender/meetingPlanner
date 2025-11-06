package at.fhtw.swen.meetingplanner.controller;

import at.fhtw.swen.meetingplanner.viewModel.AddMeetingViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.converter.LocalTimeStringConverter;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Component
public class AddMeetingViewController {
    private final AddMeetingViewModel addMeetingViewModel;

    @FXML private TextField titleField;
    @FXML private TextField startTimeField;
    @FXML private TextField endTimeField;
    @FXML private TextArea agendaArea;

    //Validation-Fields
    @FXML private Label titleErrorLabel;
    @FXML private Label timeErrorLabel;

    public AddMeetingViewController(AddMeetingViewModel addMeetingViewModel){
        this.addMeetingViewModel = addMeetingViewModel;
    }

    public void initialize(){
        titleField.textProperty().bindBidirectional(addMeetingViewModel.titleProperty());

        agendaArea.textProperty().bindBidirectional(addMeetingViewModel.agendaProperty());


        titleErrorLabel.textProperty().bindBidirectional(addMeetingViewModel.titleErrorProperty());
        timeErrorLabel.textProperty().bindBidirectional(addMeetingViewModel.timeErrorProperty());
    }

    @FXML
    public void onSubmit(){
        this.addMeetingViewModel.saveMeeting(startTimeField.getText(), endTimeField.getText());
    }

    public void setOnSave(Runnable onSave) {
        addMeetingViewModel.setOnSave(onSave);
    }


}
