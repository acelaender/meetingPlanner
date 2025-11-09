package at.fhtw.swen.meetingplanner.viewModel;


import at.fhtw.swen.meetingplanner.MeetingPlannerApplication;
import at.fhtw.swen.meetingplanner.bl.model.Meeting;
import at.fhtw.swen.meetingplanner.bl.service.MeetingService;
import at.fhtw.swen.meetingplanner.controller.AddMeetingViewController;
import at.fhtw.swen.meetingplanner.controller.AddNoteViewController;
import at.fhtw.swen.meetingplanner.dal.repository.meetingRepository;
import jakarta.transaction.Transactional;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(ApplicationExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AddNoteViewModelTest {
    @Autowired
    private AddNoteViewModel addNoteViewModel;

    @Autowired
    private at.fhtw.swen.meetingplanner.dal.repository.noteRepository noteRepository;
    @Autowired
    private at.fhtw.swen.meetingplanner.dal.repository.meetingRepository meetingRepository;

    @Autowired
    private MeetingService meetingService;

    @AfterAll
    void cleanUp() {
        meetingRepository.deleteAll();
        noteRepository.deleteAll();
    }

    @Start
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(MeetingPlannerApplication.class.getResource("view/add-note-view.fxml"));
        loader.setControllerFactory(param -> new AddNoteViewController(addNoteViewModel));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Test
    @Order(1)
    void testInvalidEntries(FxRobot robot) {
        robot.clickOn("#titleField").write("test");

        assertTrue(addNoteViewModel.contentErrorProperty().isEmpty().get());
    }

    @Test
    @Order(2)
    void testSave(FxRobot robot) {
        assertEquals(0, noteRepository.count());

        Meeting meeting = meetingRepository.save(new Meeting("test", LocalTime.of(12, 0), LocalTime.of(13, 0), "testAgenda"));

        addNoteViewModel.setContextMeeting(meeting);

        robot.clickOn("#titleField").write("test");
        robot.clickOn("#contentArea").write("test");
        robot.clickOn("#saveButton");

        assertEquals(1, noteRepository.count());
    }


}