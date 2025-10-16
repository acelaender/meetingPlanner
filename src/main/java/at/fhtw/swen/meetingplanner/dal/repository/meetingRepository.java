package at.fhtw.swen.meetingplanner.dal.repository;

import at.fhtw.swen.meetingplanner.bl.model.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface meetingRepository extends JpaRepository<Meeting, Integer> {

}
