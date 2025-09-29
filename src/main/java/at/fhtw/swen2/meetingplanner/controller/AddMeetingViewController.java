package at.fhtw.swen2.meetingplanner.controller;

import at.fhtw.swen2.meetingplanner.bl.service.MeetingService;
import at.fhtw.swen2.meetingplanner.viewModel.AddMeetingViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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

    public AddMeetingViewController(AddMeetingViewModel addMeetingViewModel){
        this.addMeetingViewModel = addMeetingViewModel;
    }

    public void initialize(){
        titleField.textProperty().bindBidirectional(addMeetingViewModel.titleProperty());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTimeStringConverter converter = new LocalTimeStringConverter(formatter, null);

        startTimeField.textProperty().bindBidirectional(addMeetingViewModel.startTimeProperty(), converter);
        endTimeField.textProperty().bindBidirectional(addMeetingViewModel.endTimeProperty(), converter);

        saveButton.setOnAction(e -> addMeetingViewModel.saveMeeting());
    }
}
