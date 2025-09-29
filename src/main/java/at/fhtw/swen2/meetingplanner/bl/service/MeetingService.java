package at.fhtw.swen2.meetingplanner.bl.service;

import at.fhtw.swen2.meetingplanner.bl.mapper.MeetingMapper;
import at.fhtw.swen2.meetingplanner.dal.entity.MeetingEntity;
import at.fhtw.swen2.meetingplanner.bl.model.*;
import at.fhtw.swen2.meetingplanner.dal.repository.meetingRepository;

import java.time.LocalTime;

import org.springframework.stereotype.Service;

@Service
public class MeetingService {

    private meetingRepository meetingRepository;

    public MeetingService(meetingRepository meetingRepository) {
        this.meetingRepository = meetingRepository;
    }

    public Meeting createMeeting(String title, LocalTime startTime, LocalTime endTime, String agenda) {
        MeetingEntity meeting = new MeetingEntity(title, startTime, endTime, agenda);
        return new MeetingMapper().toMeeting(meetingRepository.save(meeting));
    }
}
