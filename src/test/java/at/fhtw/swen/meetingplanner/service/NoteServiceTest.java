package at.fhtw.swen.meetingplanner.service;


import at.fhtw.swen.meetingplanner.bl.model.Meeting;
import at.fhtw.swen.meetingplanner.bl.model.Note;
import at.fhtw.swen.meetingplanner.bl.service.MeetingService;
import at.fhtw.swen.meetingplanner.bl.service.NoteService;
import at.fhtw.swen.meetingplanner.dal.repository.noteRepository;
import at.fhtw.swen.meetingplanner.dal.repository.meetingRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class NoteServiceTest {
    @Autowired
    private MeetingService meetingService;

    @Autowired
    private NoteService noteService;

    @Autowired
    private noteRepository noteRepository;

    @Autowired meetingRepository meetingRepository;

    private static Meeting testingMeeting;
    private static Meeting testingMeeting2;
    private static Note testNote;

    @BeforeAll
    static void setUp() {
        testingMeeting = new Meeting();
        testingMeeting.setTitle("InitialMeeting");
        testingMeeting.setAgenda("Test Meeting Agenda");
        testingMeeting.setStartTime(LocalTime.of(12, 0));
        testingMeeting.setEndTime(LocalTime.of(1, 0));


        testingMeeting2 = new Meeting();
        testingMeeting2.setTitle("InitialMeeting2");
        testingMeeting2.setAgenda("Test Meeting Agenda");
        testingMeeting2.setStartTime(LocalTime.of(12, 0));
        testingMeeting2.setEndTime(LocalTime.of(1, 0));

        testNote = new Note();
        testNote.setTitle("TestNote");
        testNote.setContent("Test Content");
    }

    @AfterAll
    void cleanUp() {
        meetingRepository.deleteAll();
        noteRepository.deleteAll();
    }

    @Test
    @Order(1)
    void createNoteTest() {
        testingMeeting = meetingService.createMeeting(testingMeeting.getTitle(), testingMeeting.getStartTime(), testingMeeting.getEndTime(), testingMeeting.getAgenda());
        testingMeeting2 = meetingService.createMeeting(testingMeeting2.getTitle(), testingMeeting2.getStartTime(), testingMeeting2.getEndTime(), testingMeeting2.getAgenda());

        testNote = noteService.createNote(testNote.getTitle(), testNote.getContent(), testingMeeting);

        assertNotNull(testNote.getId());
        assertEquals("TestNote", testNote.getTitle());
        assertEquals(1, noteService.getMeetingNotes(testingMeeting).size());
        assertEquals(1, noteRepository.count());

        Note testNote2 = noteService.createNote(testNote.getTitle(), testNote.getContent(), testingMeeting);
        assertEquals(2, noteService.getMeetingNotes(testingMeeting).size());
        assertEquals(2, noteRepository.count());

        Note testNote3 = noteService.createNote(testNote.getTitle(), testNote.getContent(), testingMeeting2);
        assertEquals("TestNote", testNote3.getTitle());
        assertEquals(1, noteService.getMeetingNotes(testingMeeting2).size());
        assertEquals(2, noteService.getMeetingNotes(testingMeeting).size());
        assertEquals(3, noteRepository.count());
    }

    @Test
    @Order(2)
    void updateNoteTest() {
        testNote.setTitle("EditedNote");
        testNote = noteService.updateNote(testNote);

        assertEquals("EditedNote", testNote.getTitle());
        assertEquals(2, meetingService.getAllMeetings().size());
        assertEquals(1, noteService.getMeetingNotes(testingMeeting2).size());
        assertEquals(2, noteService.getMeetingNotes(testingMeeting).size());
        assertEquals(3, noteRepository.count());
    }

    @Test
    @Order(3)
    void deleteNoteTest() {
        noteService.deleteNote(testNote);
        Optional<Note> deleted = noteRepository.findById(testNote.getId());

        assertEquals(true, deleted.isEmpty());
        assertEquals(1, noteService.getMeetingNotes(testingMeeting2).size());
        assertEquals(1, noteService.getMeetingNotes(testingMeeting).size());
        assertEquals(2, noteRepository.count());
    }
}
