package uz.pdp.studycenter.service;

import org.springframework.stereotype.Service;

@Service
public interface AttendanceService {

    void alterStateById(Long attendanceId);
}
