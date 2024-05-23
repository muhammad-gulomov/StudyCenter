package uz.pdp.studycenter.service;

import org.springframework.stereotype.Service;
import uz.pdp.studycenter.entity.Course;

import java.util.List;


@Service
public interface CourseService {

    List<Course> findAll();

    Course save(Course course);

}
