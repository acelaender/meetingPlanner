package at.fhtw.swen2.meetingplanner.viewModel;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import org.springframework.stereotype.Component;

@Component
public class MainViewModel {
    private final BooleanProperty showAddMeetingForm = new SimpleBooleanProperty(false);

    public void toggleDetails() {
        showAddMeetingForm.set(!showAddMeetingForm.get());
    }

    public BooleanProperty showDetailsProperty() {
        return showAddMeetingForm;
    }
}
