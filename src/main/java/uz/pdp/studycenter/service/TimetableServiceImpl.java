package uz.pdp.studycenter.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.studycenter.entity.Timetable;
import uz.pdp.studycenter.repo.TimetableRepo;

import java.util.List;


@Service
@RequiredArgsConstructor
public class TimetableServiceImpl implements TimetableService {

    private final TimetableRepo timetableRepo;

    @Override
    public Timetable save(Timetable timetable) {
        return timetableRepo.save(timetable);
    }

    @Override
    public List<Timetable> findAll() {
        return timetableRepo.findAll();
    }
}
