package at.fhtw.swen.meetingplanner.dal.repository;

import at.fhtw.swen.meetingplanner.bl.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface noteRepository extends JpaRepository<Note, Integer> {
    List<Note> findByMeetingEntityId(int meetingId);
}
