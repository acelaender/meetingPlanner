package at.fhtw.swen2.meetingplanner.bl.service;

import at.fhtw.swen2.meetingplanner.bl.model.Meeting;
import at.fhtw.swen2.meetingplanner.dal.repository.meetingRepository;

import java.time.LocalTime;

public class MeetingService {

    private meetingRepository meetingRepository;

    public Meeting createMeeting(String title, LocalTime startTime, LocalTime endTime, String agenda) {
        Meeting meeting = new Meeting(title, startTime, endTime, agenda);
        return meetingRepository.save(meeting);
    }
}
