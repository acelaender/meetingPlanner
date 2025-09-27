module at.fhtw.swen2.meetingplanner {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.persistence;
    requires static lombok;


    opens at.fhtw.swen2.meetingplanner to javafx.fxml;
    exports at.fhtw.swen2.meetingplanner;
    exports at.fhtw.swen2.meetingplanner.controller;
    opens at.fhtw.swen2.meetingplanner.controller to javafx.fxml;
}