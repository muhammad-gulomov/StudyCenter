package uz.pdp.studycenter.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.studycenter.entity.Timetable;


public interface TimetableRepo extends JpaRepository<Timetable, Long> {

}
