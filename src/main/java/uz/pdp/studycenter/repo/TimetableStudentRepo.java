package uz.pdp.studycenter.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.studycenter.entity.TimetableStudent;
import uz.pdp.studycenter.model.resp.StudentData;

import java.util.List;


public interface TimetableStudentRepo extends JpaRepository<TimetableStudent, Long> {
    @Query(nativeQuery = true, value = """
                    select u.first_name||' '||u.last_name as fullname,
                           array_agg(sa.attendance order by sa.lesson_order) as attendances,
                           array_agg(sa.attendance order by sa.lesson_order) as studentIds,
                           array_agg(sa.id order by sa.lesson_order) as attendanceIds
                    from timetable_student tts
                    join timetable tt on tts.timetable_id = tt.id
                    join public.users u on u.id = tts.student_id
                    join public.student_attendance sa on tts.id = sa.timetable_student_id
                    where tt.id =:timetableId
                    group by u.id
            """)
    List<StudentData> findAllByTimeTableId(Long timetableId);

}
