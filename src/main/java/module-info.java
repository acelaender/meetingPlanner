module at.fhtw.swen2.meetingplanner {
    requires javafx.controls;
    requires javafx.fxml;


    opens at.fhtw.swen2.meetingplanner to javafx.fxml;
    exports at.fhtw.swen2.meetingplanner;
}