package at.fhtw.swen2.meetingplanner.bl.service;

import at.fhtw.swen2.meetingplanner.bl.mapper.MeetingMapper;
import at.fhtw.swen2.meetingplanner.dal.entity.MeetingEntity;
import at.fhtw.swen2.meetingplanner.bl.model.*;
import at.fhtw.swen2.meetingplanner.dal.repository.meetingRepository;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class MeetingService {

    private final meetingRepository meetingRepository;
    private final MeetingMapper meetingMapper = new MeetingMapper();

    public MeetingService(meetingRepository meetingRepository) {
        this.meetingRepository = meetingRepository;
    }

    public Meeting createMeeting(String title, LocalTime startTime, LocalTime endTime, String agenda) {
        MeetingEntity meeting = new MeetingEntity(title, startTime, endTime, agenda);
        return meetingMapper.toMeeting(meetingRepository.save(meeting));
    }

    public List<Meeting> getAllMeetings(){
        List<Meeting> meetings = new ArrayList<>();
        List<MeetingEntity> meetingEntities = meetingRepository.findAll();
        for (int i = 0; i < meetingEntities.size(); i++) {
            meetings.add(meetingMapper.toMeeting(meetingEntities.get(i)));
        }
        return meetings;
    }
}
