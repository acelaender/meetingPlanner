package at.fhtw.swen.meetingplanner.viewModel;

import at.fhtw.swen.meetingplanner.MeetingPlannerApplication;
import at.fhtw.swen.meetingplanner.bl.model.Meeting;
import at.fhtw.swen.meetingplanner.controller.AddMeetingViewController;
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
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.framework.junit5.Start;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(ApplicationExtension.class)
@Transactional
public class AddMeetingViewModelTest {
    @Autowired
    private AddMeetingViewModel addMeetingViewModel;
    private AddMeetingViewController addMeetingViewController;

    @Autowired
    private meetingRepository meetingRepository;

    @AfterAll
    void cleanUp() {
        meetingRepository.deleteAll();
    }

    @Start
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(MeetingPlannerApplication.class.getResource("view/add-meeting-view.fxml"));
        loader.setControllerFactory(param -> new AddMeetingViewController(addMeetingViewModel));
        Parent root = loader.load();
        addMeetingViewController = loader.getController();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /*@BeforeEach
    void setUp() {
        // Reset errors before each test
        addMeetingViewModel.titleProperty().set("");
        addMeetingViewModel.agendaProperty().set("");
        addMeetingViewModel.titleErrorProperty().set("");
        addMeetingViewModel.timeErrorProperty().set("");
    }

     */

    @Test
    void testInvalidTime(FxRobot robot) {
        robot.clickOn("#titleField").write("test");
        robot.clickOn("#agendaArea").write("test");
        robot.clickOn("#startTimeField").write("10:ab");
        robot.clickOn("#endTimeField").write("11:00");
        robot.clickOn("#saveButton");

        assertFalse(addMeetingViewModel.timeErrorProperty().isEmpty().get());
    }

    @Test
    void testSave(FxRobot robot) {
        assertEquals(0, meetingRepository.count());
        robot.clickOn("#titleField").write("test");
        robot.clickOn("#agendaArea").write("test");
        robot.clickOn("#startTimeField").write("10:00");
        robot.clickOn("#endTimeField").write("11:00");
        robot.clickOn("#saveButton");

        assertEquals(1, meetingRepository.count());
    }




}
