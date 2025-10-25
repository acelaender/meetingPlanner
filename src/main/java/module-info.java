module at.fhtw.swen.meetingplanner {
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


    opens at.fhtw.swen.meetingplanner;///* to javafx.fxml, spring.core, spring.beans, spring.context*/;
    opens at.fhtw.swen.meetingplanner.bl.model;///* to org.hibernate.orm.core*/; //JPA entities
    opens at.fhtw.swen.meetingplanner.controller;///* to javafx.fxml*/;
    exports at.fhtw.swen.meetingplanner;
    exports at.fhtw.swen.meetingplanner.bl.model;
    exports at.fhtw.swen.meetingplanner.controller;
}