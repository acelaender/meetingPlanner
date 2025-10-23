package at.fhtw.swen.meetingplanner.controller;

import at.fhtw.swen.meetingplanner.viewModel.AddMeetingViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.converter.LocalTimeStringConverter;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
public class AddMeetingViewController {
    private final AddMeetingViewModel addMeetingViewModel;

    @FXML private TextField titleField;
    @FXML private TextField startTimeField;
    @FXML private TextField endTimeField;
    @FXML private TextArea agendaArea;
    @FXML private Button saveButton;

    //Validation-Fields
    @FXML private Label titleErrorLabel;
    @FXML private Label timeErrorLabel;

    public AddMeetingViewController(AddMeetingViewModel addMeetingViewModel){
        this.addMeetingViewModel = addMeetingViewModel;
    }

    public void initialize(){
        titleField.textProperty().bindBidirectional(addMeetingViewModel.titleProperty());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTimeStringConverter converter = new LocalTimeStringConverter(formatter, null);

        startTimeField.textProperty().bindBidirectional(addMeetingViewModel.startTimeProperty(), converter);
        endTimeField.textProperty().bindBidirectional(addMeetingViewModel.endTimeProperty(), converter);

        agendaArea.textProperty().bindBidirectional(addMeetingViewModel.agendaProperty());

        saveButton.setOnAction(e -> addMeetingViewModel.saveMeeting());

        titleErrorLabel.textProperty().bindBidirectional(addMeetingViewModel.titleErrorProperty());
        timeErrorLabel.textProperty().bindBidirectional(addMeetingViewModel.timeErrorProperty());
    }


}
