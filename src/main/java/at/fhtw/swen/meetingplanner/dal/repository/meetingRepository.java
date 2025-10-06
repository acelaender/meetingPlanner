package at.fhtw.swen.meetingplanner.dal.repository;

import at.fhtw.swen.meetingplanner.dal.entity.MeetingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface meetingRepository extends JpaRepository<MeetingEntity, Integer> {

}
