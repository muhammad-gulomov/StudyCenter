package uz.pdp.studycenter.service;

import org.springframework.stereotype.Service;
import uz.pdp.studycenter.entity.TimetableStudent;
import uz.pdp.studycenter.model.resp.StudentData;

import java.util.List;


@Service
public interface TimetableStudentService {

    TimetableStudent save(TimetableStudent timetableStudent);

    List<StudentData> findByTimeTableData(Long timetableId);
}
