package at.fhtw.swen.meetingplanner.bl.mapper;

import at.fhtw.swen.meetingplanner.bl.model.Note;
import at.fhtw.swen.meetingplanner.dal.entity.NoteEntity;

public class NoteMapper {

    //TODO MeetingMapper should be instantiated
    //NoteEntity -> Note
    public static Note toNote(NoteEntity noteEntity){
        if(noteEntity == null) return null;
        return new Note(noteEntity.getTitle(), noteEntity.getContent(), MeetingMapper.toMeeting(noteEntity.getMeetingEntity()));
    }

    public static NoteEntity toNoteEntity(Note note){
        if(note == null) return null;
        return new NoteEntity(note.getTitle(), note.getContent(), MeetingMapper.toMeetingEntity(note.getMeeting()));
    }
}
