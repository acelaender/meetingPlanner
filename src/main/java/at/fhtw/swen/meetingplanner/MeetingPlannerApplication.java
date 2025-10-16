package at.fhtw.swen.meetingplanner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

@SpringBootApplication()
public class MeetingPlannerApplication extends Application {

    private static ConfigurableApplicationContext context;

    public static void main(String[] args) {
        context = SpringApplication.run(MeetingPlannerApplication.class, args);
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MeetingPlannerApplication.class.getResource("view/main-view.fxml"));

        fxmlLoader.setControllerFactory(context::getBean);
        Scene scene = new Scene(fxmlLoader.load(), 1000, 700);
        scene.getStylesheets().add(getClass().getResource("/at/fhtw/swen/meetingplanner/styles/main.css").toExternalForm());
        stage.setTitle("MeetingPlanner");
        stage.setScene(scene);
        stage.show();
    }
}
