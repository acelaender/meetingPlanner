module at.fhtw.swen2.meetingplanner {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.persistence;
    requires jakarta.validation;
    requires static lombok;
    requires org.antlr.antlr4.runtime;
    requires spring.boot.autoconfigure;
    requires spring.boot;
    requires spring.data.jpa;


    opens at.fhtw.swen2.meetingplanner to javafx.fxml, spring.core, spring.beans;
    exports at.fhtw.swen2.meetingplanner;
    exports at.fhtw.swen2.meetingplanner.controller;
    opens at.fhtw.swen2.meetingplanner.controller to javafx.fxml;
}