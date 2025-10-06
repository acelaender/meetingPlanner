package at.fhtw.swen.meetingplanner.dal.repository;

import at.fhtw.swen.meetingplanner.dal.entity.NoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface noteRepository extends JpaRepository<NoteEntity, Integer> {
    List<NoteEntity> findByMeetingEntityId(int meetingId);
}
