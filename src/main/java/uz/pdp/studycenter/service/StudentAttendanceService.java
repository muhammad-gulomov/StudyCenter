package uz.pdp.studycenter.service;

import org.springframework.stereotype.Service;
import uz.pdp.studycenter.entity.StudentAttendance;

@Service
public interface StudentAttendanceService {

    StudentAttendance save(StudentAttendance studentAttendance);

}
