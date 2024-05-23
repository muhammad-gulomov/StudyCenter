package uz.pdp.studycenter.service;

import org.springframework.stereotype.Service;
import uz.pdp.studycenter.entity.Timetable;

import java.util.List;


@Service
public interface TimetableService {

    Timetable save(Timetable timetable);

    List<Timetable> findAll();

    List<Timetable> findAllByGroupId(Long groupId);
}
