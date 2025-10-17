package at.fhtw.swen.meetingplanner.controller;

import at.fhtw.swen.meetingplanner.bl.model.Meeting;
import at.fhtw.swen.meetingplanner.viewModel.AddNoteViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;

@Component
public class AddNoteViewController {
    private final AddNoteViewModel addNoteViewModel;
    @FXML private TextField titleField;
    @FXML private TextArea contentArea;

    public void initialize(){
        titleField.textProperty().bindBidirectional(addNoteViewModel.titleProperty());
        contentArea.textProperty().bindBidirectional(addNoteViewModel.contentProperty());
    }

    public AddNoteViewController(AddNoteViewModel addNoteViewModel) {
        this.addNoteViewModel = addNoteViewModel;
    }

    public void setMeeting(Meeting meeting){
        this.addNoteViewModel.setContextMeeting(meeting);
    }

    @FXML
    protected void onSaveButtonClick(){
        this.addNoteViewModel.saveNote();
    }
}
