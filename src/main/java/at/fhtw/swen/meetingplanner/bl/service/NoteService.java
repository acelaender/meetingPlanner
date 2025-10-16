package at.fhtw.swen.meetingplanner.bl.service;

import at.fhtw.swen.meetingplanner.bl.model.Meeting;
import at.fhtw.swen.meetingplanner.bl.model.Note;
import at.fhtw.swen.meetingplanner.dal.repository.noteRepository;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NoteService {

    private final noteRepository noteRepository;
    private final MeetingService meetingService;

    public NoteService(noteRepository noteRepository, MeetingService meetingService) {
        this.noteRepository = noteRepository;
        this.meetingService = meetingService;
    }

    public Note createNote(String title, String content, Meeting meeting){
        Note note = new Note(title, content, meeting);

        return noteRepository.save(note);
    }

    public List<Note> getMeetingNotes(Meeting meeting){
        return noteRepository.findByMeetingEntityId(meeting.getId());
    }

    public Note updateNote(Note note){
        return noteRepository.save(note);
    }


}
