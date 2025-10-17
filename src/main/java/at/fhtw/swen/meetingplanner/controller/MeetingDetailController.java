package at.fhtw.swen.meetingplanner.controller;

import at.fhtw.swen.meetingplanner.bl.model.Meeting;
import at.fhtw.swen.meetingplanner.viewModel.MeetingDetailViewModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MeetingDetailController {

    @FXML Label titleLabel;
    @FXML Label agendaLabel;
    @FXML Button addNoteButton;
    @FXML VBox noteContainer;
    @FXML VBox formContainer;

    //Add-Note-Node
    private Node formNode;
    private final ConfigurableApplicationContext springContext;

    private final MeetingDetailViewModel meetingDetailViewModel;

    public MeetingDetailController(ConfigurableApplicationContext springContext, MeetingDetailViewModel meetingDetailViewModel) {
        this.springContext = springContext;
        this.meetingDetailViewModel = meetingDetailViewModel;
    }

    @FXML
    private void initialize(){
        titleLabel.textProperty().bindBidirectional(meetingDetailViewModel.titleProperty());
        agendaLabel.textProperty().bindBidirectional(meetingDetailViewModel.agendaProperty());


    }

    @FXML
    protected void onAddNoteButtonClick(){
        meetingDetailViewModel.toggleAddNoteForm();
    }

    public void setMeeting(Meeting meeting) throws IOException{
        this.meetingDetailViewModel.setMeeting(meeting);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/at/fhtw/swen/meetingplanner/view/add-note-view.fxml"));
        loader.setControllerFactory(springContext::getBean);

        formNode = loader.load();

        AddNoteViewController controller = loader.getController();
        controller.setMeeting(this.meetingDetailViewModel.getMeeting());

        formContainer.getChildren().add(formNode);
        formNode.visibleProperty().bind(meetingDetailViewModel.showAddMeetingForm());
        formNode.managedProperty().bind(meetingDetailViewModel.showAddMeetingForm());
    }
}
