package uz.pdp.studycenter.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.pdp.studycenter.entity.User;
import uz.pdp.studycenter.model.resp.StudentRespDto;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    User findByPhoneNumber(String phoneNumber);

    List<StudentRespDto> findAllByIsArchivedFalse();

    @Query(value = "select id, first_name as firstName, last_name as lastName, phone_number as phoneNumber from users where first_name ilike :searched or last_name ilike :searched or phone_number ilike :searched", nativeQuery = true)
    List<Object[]> findAllBySearch(String searched);

    @Query(value = """
        select u.id,
               u.first_name,
               u.last_name,
               u.phone_number,
               tt.name,
               tt.current_lesson,
               g.name,
               c.name,
               sum(p.amount)
        from users u
                 join timetable_student tts on u.id = tts.student_id
                 join timetable tt on tts.timetable_id = tt.id
                 join groups g on tt.group_id = g.id
                 join course c on g.course_id = c.id
                 join payment p on u.id = p.student_id
        where u.id = :id
                     and tt.is_archived = false
        group by u.id, tt.name, tt.current_lesson, g.name, c.name
    """, nativeQuery = true)

    List<Object[]> findStudentInfosById(Long id);
}

