package at.fhtw.swen.meetingplanner.controller;

import at.fhtw.swen.meetingplanner.bl.model.Meeting;
import at.fhtw.swen.meetingplanner.bl.model.Note;
import at.fhtw.swen.meetingplanner.viewModel.MeetingDetailViewModel;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.converter.LocalTimeStringConverter;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Component
public class MeetingDetailController {

    //Viewing
    @FXML Label titleLabel;
    @FXML Label agendaLabel;
    @FXML Label timeLabel;

    //Editing
    @FXML TextField titleField;
    @FXML TextArea agendaArea;
    @FXML TextField startTimeField;
    @FXML TextField endTimeField;
    @FXML Button saveButton;

    //NOTES//
    @FXML Button addNoteButton;
    //Notes List
    @FXML VBox noteContainer;
    //Add Note Form
    @FXML VBox formContainer;

    //Add-Note-Node
    private Node formNode;
    private final ConfigurableApplicationContext springContext;

    private final MeetingDetailViewModel meetingDetailViewModel;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

    public MeetingDetailController(ConfigurableApplicationContext springContext, MeetingDetailViewModel meetingDetailViewModel) {
        this.springContext = springContext;
        this.meetingDetailViewModel = meetingDetailViewModel;
    }

    @FXML
    private void initialize(){
        //Setting Content
        //Labels
        titleLabel.textProperty().bindBidirectional(meetingDetailViewModel.titleProperty());
        agendaLabel.textProperty().bindBidirectional(meetingDetailViewModel.agendaProperty());
        timeLabel.textProperty().bind(Bindings.createStringBinding(() -> {
            LocalTime start = meetingDetailViewModel.startTime();
            LocalTime end = meetingDetailViewModel.endTime();
            if (start != null && end != null) {
                return formatter.format(start) + " - " + formatter.format(end);
            }
            return "";
        }, meetingDetailViewModel.startTimeProperty(), meetingDetailViewModel.endTimeProperty()));

        //Editing Fields
        titleField.textProperty().bind(meetingDetailViewModel.titleProperty());
        agendaArea.textProperty().bind(meetingDetailViewModel.agendaProperty());
        Bindings.bindBidirectional(startTimeField.textProperty(), meetingDetailViewModel.startTimeProperty(), new LocalTimeStringConverter(formatter, formatter));
        Bindings.bindBidirectional(endTimeField.textProperty(), meetingDetailViewModel.endTimeProperty(), new LocalTimeStringConverter(formatter, formatter));



        //Setting Edit Meeting Bool
        //Editing Fields
        titleField.visibleProperty().bind(meetingDetailViewModel.editNoteProperty());
        titleField.managedProperty().bind(meetingDetailViewModel.editNoteProperty());
        agendaArea.visibleProperty().bind(meetingDetailViewModel.editNoteProperty());
        agendaArea.managedProperty().bind(meetingDetailViewModel.editNoteProperty());
        startTimeField.visibleProperty().bind(meetingDetailViewModel.editNoteProperty());
        startTimeField.managedProperty().bind(meetingDetailViewModel.editNoteProperty());
        endTimeField.visibleProperty().bind(meetingDetailViewModel.editNoteProperty());
        endTimeField.managedProperty().bind(meetingDetailViewModel.editNoteProperty());
        saveButton.visibleProperty().bind(meetingDetailViewModel.editNoteProperty());
        saveButton.managedProperty().bind(meetingDetailViewModel.editNoteProperty());

        //Labels
        titleLabel.visibleProperty().bind(meetingDetailViewModel.editNoteProperty().not());
        titleLabel.managedProperty().bind(meetingDetailViewModel.editNoteProperty().not());
        agendaLabel.visibleProperty().bind(meetingDetailViewModel.editNoteProperty().not());
        agendaLabel.managedProperty().bind(meetingDetailViewModel.editNoteProperty().not());
        timeLabel.visibleProperty().bind(meetingDetailViewModel.editNoteProperty().not());
        timeLabel.managedProperty().bind(meetingDetailViewModel.editNoteProperty().not());
    }

    @FXML
    protected void onAddNoteButtonClick(){
        meetingDetailViewModel.toggleAddNoteForm();
    }

    @FXML
    protected void onSaveButtonClick(){
        meetingDetailViewModel.saveMeeting();
    }

    @FXML
    protected void onEditButtonClick() {
        meetingDetailViewModel.toggleEditMeeting();
    }

    @FXML
    protected void onExportButtonClick() {
        Stage stage = (Stage) titleLabel.getScene().getWindow();

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Export Meeting Report");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf"));
        fileChooser.setInitialFileName("meeting-" + meetingDetailViewModel.getMeeting().getTitle() + "-report.pdf");

        File file = fileChooser.showSaveDialog(stage);

        if (file != null) {
            try {
                meetingDetailViewModel.exportMeeting(file.getAbsolutePath());
                System.out.println("export successful");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    @FXML
    protected void onCloseButtonClick() {
        meetingDetailViewModel.closeMeeting();
    }
    public void setMeeting(Meeting meeting) throws IOException{
        this.meetingDetailViewModel.setMeeting(meeting);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/at/fhtw/swen/meetingplanner/view/add-note-view.fxml"));
        loader.setControllerFactory(springContext::getBean);

        formNode = loader.load();

        AddNoteViewController controller = loader.getController();
        controller.setMeeting(this.meetingDetailViewModel.getMeeting());
        controller.setOnSave(this::addNoteCallback);

        formContainer.getChildren().add(formNode);
        formNode.visibleProperty().bind(meetingDetailViewModel.showAddMeetingForm());
        formNode.managedProperty().bind(meetingDetailViewModel.showAddMeetingForm());

        refreshNoteList();
    }

    private void refreshNoteList() {
        noteContainer.getChildren().clear();

        for(Note note : meetingDetailViewModel.getNotes()) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/at/fhtw/swen/meetingplanner/view/note-card-view.fxml"));
                loader.setControllerFactory(springContext::getBean);

                VBox card = loader.load();
                NoteCardViewController controller = loader.getController();

                //Setting Note and delete-Button-Callback
                controller.setNote(note);
                controller.setMeeting(meetingDetailViewModel.getMeeting());
                controller.setOnDelete(this::refreshNoteList);

                noteContainer.getChildren().add(card);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private void addNoteCallback() {
        refreshNoteList();
        meetingDetailViewModel.toggleAddNoteForm();
    }

    public void setOnClose(Runnable onClose) {
        meetingDetailViewModel.setOnClose(onClose);
    }
}
