package uz.pdp.studycenter.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.studycenter.entity.Course;


public interface CourseRepo extends JpaRepository<Course, Long> {
}
