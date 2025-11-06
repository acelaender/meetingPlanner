package at.fhtw.swen.meetingplanner.viewModel;

import at.fhtw.swen.meetingplanner.bl.service.MeetingService;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.util.converter.LocalTimeStringConverter;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Component
public class AddMeetingViewModel {

    private final MeetingService meetingService;
    private Runnable onSave;


    //Inputs//
    private final StringProperty title = new SimpleStringProperty("");
    private final ObjectProperty<LocalTime> startTime = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalTime> endTime = new SimpleObjectProperty<>();
    private final StringProperty agenda = new SimpleStringProperty();

    //Warning Labels//
    private final StringProperty titleErrorProperty = new SimpleStringProperty("");
    private final StringProperty timeErrorProperty = new SimpleStringProperty("");


    public StringProperty titleProperty() { return title; }
    public StringProperty agendaProperty() { return agenda; }

    public ObjectProperty<LocalTime> startTimeProperty() { return startTime; }
    public ObjectProperty<LocalTime> endTimeProperty() { return endTime; }

    public AddMeetingViewModel(MeetingService meetingService) {
        this.meetingService = meetingService;
    }


    public void saveMeeting(String startTime, String endTime) {
        setErrorsBlank();

        //converting time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTimeStringConverter converter = new LocalTimeStringConverter(formatter, null);
        try {
            LocalTime localTimeStart = LocalTime.parse(startTime, formatter);
            LocalTime localTimeEnd = LocalTime.parse(endTime, formatter);
            this.startTime.set(localTimeStart);
            this.endTime.set(localTimeEnd);
        } catch (DateTimeParseException e) {
            timeError();
            return;
        }

        //
        if(title.getValue().isBlank()){
            titleErrorProperty.set("This Field cannot be empty!");
        }else if(this.startTime.get() == null || this.endTime.get() == null){
            timeErrorProperty.set("This Field cannot be empty!");
        }else{
            meetingService.createMeeting(title.get(), this.startTime.get(), this.endTime.get(), agenda.get());
            cleanInputs();
            if(this.onSave != null){
                this.onSave.run();
            }
        }
    }

    public void setOnSave(Runnable callback){
        this.onSave = callback;
    }

    public StringProperty titleErrorProperty() {
        return this.titleErrorProperty;
    }

    public StringProperty timeErrorProperty() {
        return this.timeErrorProperty;
    }

    public void setErrorsBlank() {
        this.titleErrorProperty.set("");
        this.timeErrorProperty.set("");
    }

    private void cleanInputs() {
        this.title.set("");
        this.agenda.set("");
        this.startTime.set(null);
        this.endTime.set(null);
    }

    public void timeError(){
        this.timeErrorProperty.set("This Field has to be of Format HH:mm");
    }
    public void setStartTime(LocalTime startTime) {
        this.startTime.set(startTime);
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime.set(endTime);
    }
}
