package at.fhtw.swen2.meetingplanner.bl.service;

import at.fhtw.swen2.meetingplanner.dal.entity.MeetingEntity;
import at.fhtw.swen2.meetingplanner.dal.repository.meetingRepository;

import java.time.LocalTime;

public class MeetingService {

    private meetingRepository meetingRepository;

    public MeetingEntity createMeeting(String title, LocalTime startTime, LocalTime endTime, String agenda) {
        MeetingEntity meeting = new MeetingEntity(title, startTime, endTime, agenda);
        return meetingRepository.save(meeting);
        //TODO Should return meeting not meetingentity
    }
}
