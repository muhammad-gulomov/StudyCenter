package uz.pdp.studycenter.service;

import org.springframework.stereotype.Service;
import uz.pdp.studycenter.entity.TimetableStudent;


@Service
public interface TimetableStudentService {

    TimetableStudent save(TimetableStudent timetableStudent);

}
