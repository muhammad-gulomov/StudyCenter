package uz.pdp.studycenter.service;

import org.springframework.stereotype.Service;
import uz.pdp.studycenter.entity.StudentAttendance;
import uz.pdp.studycenter.repo.StudentAttendanceRepo;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    private final StudentAttendanceRepo studentAttendanceRepo;

    public AttendanceServiceImpl(StudentAttendanceRepo studentAttendanceRepo) {
        this.studentAttendanceRepo = studentAttendanceRepo;
    }

    @Override
    public void alterStateById(Long attendanceId) {
        StudentAttendance studentAttendance = studentAttendanceRepo.findById(attendanceId).get();
        if (studentAttendance.getAttendance()) {
            studentAttendance.setAttendance(false);
        } else {
            studentAttendance.setAttendance(true);
        }
        studentAttendanceRepo.save(studentAttendance);
    }
}
