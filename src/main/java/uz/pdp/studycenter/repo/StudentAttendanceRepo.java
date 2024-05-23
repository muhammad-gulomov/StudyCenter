package uz.pdp.studycenter.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.studycenter.entity.StudentAttendance;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface StudentAttendanceRepo extends JpaRepository<StudentAttendance, Long> {
    List<StudentAttendance> findAllByCreatedAt(LocalDateTime createdAt);
}
