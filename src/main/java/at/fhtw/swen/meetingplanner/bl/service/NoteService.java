package at.fhtw.swen.meetingplanner.bl.service;

import at.fhtw.swen.meetingplanner.bl.mapper.MeetingMapper;
import at.fhtw.swen.meetingplanner.bl.mapper.NoteMapper;
import at.fhtw.swen.meetingplanner.bl.model.Meeting;
import at.fhtw.swen.meetingplanner.bl.model.Note;
import at.fhtw.swen.meetingplanner.dal.entity.MeetingEntity;
import at.fhtw.swen.meetingplanner.dal.entity.NoteEntity;
import at.fhtw.swen.meetingplanner.dal.repository.noteRepository;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NoteService {

    private final noteRepository noteRepository;
    private final MeetingService meetingService;
    private final NoteMapper noteMapper = new NoteMapper();
    private final MeetingMapper meetingMapper = new MeetingMapper();

    public NoteService(noteRepository noteRepository, MeetingService meetingService) {
        this.noteRepository = noteRepository;
        this.meetingService = meetingService;
    }

    public Note createNote(String title, String content, Meeting meeting){

        //Remapping the concerning meeting
        MeetingEntity meetingEntity = meetingMapper.toMeetingEntity(meeting);
        meetingEntity.setId(meeting.getId());

        //Binding it to Note
        NoteEntity noteEntity = new NoteEntity(title, content, meetingEntity);

        //Saving Note
        return noteMapper.toNote(noteRepository.save(noteEntity));
    }

    public List<Note> getMeetingNotes(Meeting meeting){
        List<Note> notes = new ArrayList<>();
        List<NoteEntity> noteEntities = noteRepository.findByMeetingEntityId(meeting.getId());

        for (int i = 0; i < noteEntities.size(); i++) {
            notes.add(noteMapper.toNote(noteEntities.get(i)));
        }
        return notes;
    }

    public Note updateNote(Note note){
        NoteEntity entity = noteMapper.toNoteEntity(note);
        entity.setId(note.getId());
        return noteMapper.toNote(noteRepository.save(entity));
    }


}
