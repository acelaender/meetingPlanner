package at.fhtw.swen2.meetingplanner.dal.repository;

import at.fhtw.swen2.meetingplanner.dal.entity.MeetingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface meetingRepository extends JpaRepository<MeetingEntity, Integer> {

}
