package uz.pdp.studycenter.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.studycenter.entity.Group;
import uz.pdp.studycenter.entity.Timetable;

import java.util.List;


public interface TimetableRepo extends JpaRepository<Timetable, Long> {

    List<Timetable> findByGroup(Group group);
}
