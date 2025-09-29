package at.fhtw.swen2.meetingplanner.bl.mapper;

import at.fhtw.swen2.meetingplanner.bl.model.Meeting;
import at.fhtw.swen2.meetingplanner.dal.entity.MeetingEntity;

public class MeetingMapper {

    //MeetingEntity -> Meeting
    public static Meeting toMeeting(MeetingEntity entity){
        if(entity==null) return null;
        return new Meeting(entity.getTitle(), entity.getStartTime(), entity.getEndTime(), entity.getAgenda());
    }

    public static MeetingEntity toMeetingEntity(Meeting meeting){
        if(meeting == null) return null;
        return new MeetingEntity(meeting.getTitle(), meeting.getStartTime(), meeting.getEndTime(), meeting.getAgenda());
    }
}
