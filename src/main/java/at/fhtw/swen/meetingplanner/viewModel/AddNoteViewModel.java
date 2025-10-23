package at.fhtw.swen.meetingplanner.viewModel;

import at.fhtw.swen.meetingplanner.bl.model.Meeting;
import at.fhtw.swen.meetingplanner.bl.service.NoteService;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.springframework.stereotype.Component;

@Component
public class AddNoteViewModel {
    Meeting contextMeeting;

    private NoteService noteService;

    private final StringProperty titleProperty = new SimpleStringProperty("");
    private final StringProperty contentProperty = new SimpleStringProperty("");
    //ErrorStrings
    private final StringProperty titleErrorProperty = new SimpleStringProperty("");
    private final StringProperty contentErrorProperty = new SimpleStringProperty("");

    public AddNoteViewModel(NoteService noteService) {
        this.noteService = noteService;
    }

    public void setContextMeeting(Meeting contextMeeting) {
        this.contextMeeting = contextMeeting;
    }

    public StringProperty titleProperty(){
        return this.titleProperty;
    }

    public StringProperty contentProperty(){
        return this.contentProperty;
    }

    public StringProperty titleErrorProperty() {
        return this.titleErrorProperty;
    }

    public StringProperty contentErrorProperty() {
        return this.contentErrorProperty;
    }

    public void saveNote(){
        setErrorsBlank();
        if(titleProperty.getValue().isBlank()){
            titleErrorProperty.set("This Field cannot be empty!");
        }else if(contentProperty.getValue().isBlank()){
            contentErrorProperty.set("This Field cannot be empty!");
        }else{
            noteService.createNote(titleProperty.getValue(), contentProperty.getValue(), contextMeeting);
        }
    }

    public void setErrorsBlank() {
        this.titleErrorProperty.set("");
        this.contentErrorProperty.set("");
    }

}
