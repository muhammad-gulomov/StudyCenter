package uz.pdp.studycenter.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.studycenter.entity.StudentAttendance;
import uz.pdp.studycenter.repo.StudentAttendanceRepo;


@Service
@RequiredArgsConstructor
public class StudentAttendanceServiceImpl implements StudentAttendanceService {

    private final StudentAttendanceRepo studentAttendanceRepo;

    @Override
    public StudentAttendance save(StudentAttendance studentAttendance) {
        return studentAttendanceRepo.save(studentAttendance);
    }

}
