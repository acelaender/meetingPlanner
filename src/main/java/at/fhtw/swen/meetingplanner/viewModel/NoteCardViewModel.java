package at.fhtw.swen.meetingplanner.viewModel;

import at.fhtw.swen.meetingplanner.bl.model.Meeting;
import at.fhtw.swen.meetingplanner.bl.model.Note;
import at.fhtw.swen.meetingplanner.bl.service.MeetingService;
import at.fhtw.swen.meetingplanner.bl.service.NoteService;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.springframework.stereotype.Component;

@Component
public class NoteCardViewModel {

    private Meeting meeting = null;
    private Note note = null;
    private final NoteService noteService;
    private Runnable onDelete;
    private final BooleanProperty showEditNote = new SimpleBooleanProperty(false);

    private final StringProperty title = new SimpleStringProperty();
    private final StringProperty content = new SimpleStringProperty();


    public NoteCardViewModel(NoteService noteService) {
        this.noteService = noteService;
    }

    public void setNote(Note note) {
        this.note = note;
        this.title.set(note.getTitle());
        this.content.set(note.getContent());
    }

    public void setMeeting(Meeting meeting) {
        this.meeting = meeting;
    }

    public void setOnDelete(Runnable callback){
        this.onDelete = callback;
    }

    public void deleteNote(){
        noteService.deleteNote(this.note);
        if(this.onDelete != null){
            this.onDelete.run();
        }
    }

    public void editNote(){
        noteService.createNote(title.get(), content.get(), meeting);
        if(this.onDelete != null) {
            this.onDelete.run();
        }
    }


    public StringProperty titleProperty(){
        return this.title;
    }

    public StringProperty contentProperty() {
        return content;
    }

    public BooleanProperty showEditProperty(){ return showEditNote; }

    public void toggleEdit(){ showEditNote.set(!showEditNote.get()); }

}
