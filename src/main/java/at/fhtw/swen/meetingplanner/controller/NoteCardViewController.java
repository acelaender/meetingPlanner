package at.fhtw.swen.meetingplanner.controller;

import at.fhtw.swen.meetingplanner.bl.model.Meeting;
import at.fhtw.swen.meetingplanner.bl.model.Note;
import at.fhtw.swen.meetingplanner.viewModel.NoteCardViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class NoteCardViewController {
    //Viewing
    @FXML Label titleLabel;
    @FXML Label contentLabel;
    //Editing
    @FXML TextField titleField;
    @FXML TextArea contentArea;
    @FXML Button saveButton;

    private final NoteCardViewModel noteCardViewModel;

    public NoteCardViewController(NoteCardViewModel noteCardViewModel) {
        this.noteCardViewModel = noteCardViewModel;
    }

    @FXML
    private void initialize() {
        titleLabel.textProperty().bindBidirectional(noteCardViewModel.titleProperty());
        contentLabel.textProperty().bindBidirectional(noteCardViewModel.contentProperty());
        titleField.textProperty().bindBidirectional(noteCardViewModel.titleProperty());
        contentArea.textProperty().bindBidirectional(noteCardViewModel.contentProperty());

        //Setting Edit Notes Boolean//
        //Editing Fields
        titleField.visibleProperty().bind(noteCardViewModel.showEditProperty());
        contentArea.visibleProperty().bind(noteCardViewModel.showEditProperty());
        titleField.managedProperty().bind(noteCardViewModel.showEditProperty());
        contentArea.managedProperty().bind(noteCardViewModel.showEditProperty());
        saveButton.visibleProperty().bind(noteCardViewModel.showEditProperty());
        saveButton.managedProperty().bind(noteCardViewModel.showEditProperty());

        //Labels
        titleLabel.visibleProperty().bind(noteCardViewModel.showEditProperty().not());
        contentLabel.visibleProperty().bind(noteCardViewModel.showEditProperty().not());
        titleLabel.managedProperty().bind(noteCardViewModel.showEditProperty().not());
        contentLabel.managedProperty().bind(noteCardViewModel.showEditProperty().not());
    }

    @FXML
    protected void onDeleteButtonClick(){
        noteCardViewModel.deleteNote();
    }

    @FXML
    protected void onEditButtonClick() {
        noteCardViewModel.toggleEdit();
    }

    @FXML
    protected void onSaveButtonClick() {
        noteCardViewModel.editNote();
    }

    public void setOnDelete(Runnable onDelete) {
        noteCardViewModel.setOnDelete(onDelete);
    }


    public void setNote(Note note){
        this.noteCardViewModel.setNote(note);
    }

    public void setMeeting(Meeting meeting) {
        noteCardViewModel.setMeeting(meeting);
    }
}
