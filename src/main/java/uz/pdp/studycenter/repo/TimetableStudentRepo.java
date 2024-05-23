package uz.pdp.studycenter.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.studycenter.entity.TimetableStudent;


public interface TimetableStudentRepo extends JpaRepository<TimetableStudent, Long> {


}
