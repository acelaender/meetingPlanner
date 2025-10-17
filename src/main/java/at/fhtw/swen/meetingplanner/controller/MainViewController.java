package at.fhtw.swen.meetingplanner.controller;

import at.fhtw.swen.meetingplanner.bl.model.Meeting;
import at.fhtw.swen.meetingplanner.viewModel.MainViewModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
    @FXML private VBox meetingDetailsContainer;

    public MainViewController(ConfigurableApplicationContext springContext, MainViewModel mainViewModel){
        this.springContext = springContext;
        this.mainViewModel = mainViewModel;
    }


    public void initialize() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/at/fhtw/swen/meetingplanner/view/add-meeting-view.fxml"));
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
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/at/fhtw/swen/meetingplanner/view/meeting-card-view.fxml"));
                loader.setControllerFactory(springContext::getBean);

                VBox card = loader.load();
                MeetingCardViewController controller = loader.getController();

                //Setting Meeting and button-Callback-Actions in meetingCards
                controller.setMeeting(meeting);
                controller.setOnShowDetails(() -> showMeetingDetails(meeting));
                controller.setOnDelete(this::refreshMeetingList);

                meetingListContainer.getChildren().add(card);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private void showMeetingDetails(Meeting meeting) {
        try {
            meetingDetailsContainer.getChildren().clear();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/at/fhtw/swen/meetingplanner/view/meeting-detail-view.fxml"));
            loader.setControllerFactory(springContext::getBean);
            VBox detailsView = loader.load();

            MeetingDetailController detailsController = loader.getController();
            detailsController.setMeeting(meeting);

            meetingDetailsContainer.getChildren().add(detailsView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
