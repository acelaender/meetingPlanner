open module at.fhtw.swen.meetingplanner {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.persistence;
    requires jakarta.validation;
    requires static lombok;
    requires org.antlr.antlr4.runtime;
    requires spring.boot.autoconfigure;
    requires spring.boot;
    requires spring.data.jpa;
    requires spring.context;
    requires org.apache.logging.log4j;
    requires kernel;
    requires layout;



    exports at.fhtw.swen.meetingplanner;
    exports at.fhtw.swen.meetingplanner.bl.model;
    exports at.fhtw.swen.meetingplanner.controller;
}
