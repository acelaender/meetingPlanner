package at.fhtw.swen2.meetingplanner.controller;

import at.fhtw.swen2.meetingplanner.bl.model.Meeting;
import at.fhtw.swen2.meetingplanner.viewModel.MainViewModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MainViewController {

    private final ConfigurableApplicationContext springContext;
    private final MainViewModel mainViewModel;
    private Node formNode;
    @FXML private StackPane formContainer;
    @FXML private VBox meetingListContainer;

    public MainViewController(ConfigurableApplicationContext springContext, MainViewModel mainViewModel){
        this.springContext = springContext;
        this.mainViewModel = mainViewModel;
    }

    @FXML
    private void initialize() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/at/fhtw/swen2/meetingplanner/view/add-meeting-view.fxml"));
        loader.setControllerFactory(springContext::getBean);

        formNode = loader.load();
        formContainer.getChildren().add(formNode);
        formNode.visibleProperty().bind(mainViewModel.showDetailsProperty());
        formNode.managedProperty().bind(mainViewModel.showDetailsProperty());

        refreshMeetingList();
    }

    @FXML
    protected void onAddMeetingButtonClick(){
        mainViewModel.toggleDetails();
    }

    @FXML
    protected void onRefreshButtonClick() {
        refreshMeetingList();
    }

    private void refreshMeetingList() {
        meetingListContainer.getChildren().clear();

        for(Meeting meeting : mainViewModel.getMeetings()) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/at/fhtw/swen2/meetingplanner/view/meeting-card-view.fxml"));
                VBox card = loader.load();
                MeetingCardViewController controller = loader.getController();
                controller.setMeeting(meeting);
                meetingListContainer.getChildren().add(card);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
