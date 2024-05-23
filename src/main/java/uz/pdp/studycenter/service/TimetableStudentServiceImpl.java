package uz.pdp.studycenter.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import uz.pdp.studycenter.entity.TimetableStudent;
import uz.pdp.studycenter.repo.TimetableStudentRepo;



@Service
@RequiredArgsConstructor
public class TimetableStudentServiceImpl implements TimetableStudentService {

    private final TimetableStudentRepo timetableStudentRepo;

    @Override
    public TimetableStudent save(TimetableStudent timetableStudent) {
        return timetableStudentRepo.save(timetableStudent);
    }
}
