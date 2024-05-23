package uz.pdp.studycenter.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.studycenter.entity.Timetable;
import uz.pdp.studycenter.entity.TimetableStudent;
import uz.pdp.studycenter.model.resp.StudentData;
import uz.pdp.studycenter.repo.TimetableRepo;
import uz.pdp.studycenter.repo.TimetableStudentRepo;

import java.util.List;


@Service
@RequiredArgsConstructor
public class TimetableStudentServiceImpl implements TimetableStudentService {

    private final TimetableStudentRepo timetableStudentRepo;
    private final TimetableService timetableService;
    private final TimetableRepo timetableRepo;

    @Override
    public TimetableStudent save(TimetableStudent timetableStudent) {
        return timetableStudentRepo.save(timetableStudent);
    }

    @Override
    public List<StudentData> findByTimeTableData(Long timeTableId) {
        return timetableStudentRepo.findAllByTimeTableId(timeTableId);
    }
}
