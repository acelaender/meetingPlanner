package at.fhtw.swen.meetingplanner.service;

import at.fhtw.swen.meetingplanner.bl.model.Meeting;
import at.fhtw.swen.meetingplanner.bl.service.MeetingService;
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
public class MeetingServiceTest {
    @Autowired
    private MeetingService meetingService;

    @Autowired
    private meetingRepository meetingRepository;

    private static Meeting testingMeeting;

    @BeforeAll
    static void setUp() {
        testingMeeting = new Meeting();
        testingMeeting.setTitle("InitialMeeting");
        testingMeeting.setAgenda("Test Meeting Agenda");
        testingMeeting.setStartTime(LocalTime.of(12, 0));
        testingMeeting.setEndTime(LocalTime.of(1, 0));
    }

    @AfterAll
    void cleanUp() {
        meetingRepository.deleteAll();
    }

    @Test
    @Order(1)
    void createMeetingTest() {
        testingMeeting = meetingService.createMeeting(testingMeeting.getTitle(), testingMeeting.getStartTime(), testingMeeting.getEndTime(), testingMeeting.getAgenda());

        assertNotNull(testingMeeting.getId());
        assertEquals("InitialMeeting", testingMeeting.getTitle());
        assertEquals(1, meetingService.getAllMeetings().size());
        assertEquals(1, meetingRepository.count());

        Meeting testingMeeting2 = meetingService.createMeeting("secondMeeting", testingMeeting.getStartTime(), testingMeeting.getEndTime(), testingMeeting.getAgenda());
        assertEquals(2, meetingService.getAllMeetings().size());
        assertEquals(2, meetingRepository.count());
    }

    @Test
    @Order(2)
    void updateMeetingTest() {
        testingMeeting.setTitle("EditedMeeting");
        testingMeeting = meetingService.updateMeeting(testingMeeting);

        assertEquals("EditedMeeting", testingMeeting.getTitle());
        assertEquals(2, meetingService.getAllMeetings().size());
        assertEquals(2, meetingRepository.count());
    }

    @Test
    @Order(3)
    void deleteMeetingTest() {
        meetingService.deleteMeeting(testingMeeting);
        Optional<Meeting> deleted = meetingRepository.findById(testingMeeting.getId());

        assertEquals(true, deleted.isEmpty());
        assertEquals(1, meetingService.getAllMeetings().size());
        assertEquals(1, meetingRepository.count());
    }
}