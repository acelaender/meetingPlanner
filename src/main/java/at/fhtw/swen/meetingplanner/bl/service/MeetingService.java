package at.fhtw.swen.meetingplanner.bl.service;

import at.fhtw.swen.meetingplanner.bl.model.Meeting;
import at.fhtw.swen.meetingplanner.dal.repository.meetingRepository;
import at.fhtw.swen.meetingplanner.bl.model.*;

import jakarta.persistence.EntityNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class MeetingService {
    //TODO create Meeting can probably also be done with meeting
    private final meetingRepository meetingRepository;
    private static final Logger logger = LogManager.getLogger(MeetingService.class);

    public MeetingService(meetingRepository meetingRepository) {
        this.meetingRepository = meetingRepository;
    }

    public Meeting createMeeting(String title, LocalTime startTime, LocalTime endTime, String agenda) {
        Meeting meeting = new Meeting(title, startTime, endTime, agenda);
        logger.info("Creating Meeting: " + meeting.toString());
        return meetingRepository.save(meeting);
    }

    public List<Meeting> getAllMeetings(){
        return meetingRepository.findAll();
    }

    public Meeting getMeetingById(int id){
        return meetingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Meeting with ID: " + id + " not found"));
    }

    public void deleteMeeting(Meeting meeting) {
        logger.info("Deleting Meeting: " + meeting.toString());
        meetingRepository.delete(meeting);
    }

    public Meeting updateMeeting(Meeting meeting){
        logger.info("Updating Meeting: " + meeting);
        return meetingRepository.save(meeting);
    }
}
