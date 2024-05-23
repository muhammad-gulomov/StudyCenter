package uz.pdp.studycenter.component;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.pdp.studycenter.entity.*;
import uz.pdp.studycenter.entity.enums.PayType;
import uz.pdp.studycenter.entity.enums.RoleName;
import uz.pdp.studycenter.repo.StudentAttendanceRepo;
import uz.pdp.studycenter.service.*;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class Runnable implements CommandLineRunner {

    private final RoleServiceImpl roleService;
    private final GroupServiceImpl groupService;
    private final CourseServiceImpl courseService;
    private final UserServiceImpl userService;
    private final TimetableServiceImpl timetableService;
    private final TimetableStudentServiceImpl timetableStudentService;
    private final StudentAttendanceServiceImpl studentAttendanceService;
    private final PaymentServiceImpl paymentService;
    private final PasswordEncoder passwordEncoder;
    private final StudentAttendanceRepo studentAttendanceRepo;

    /* Initial data will be created if init.data is true in application properties
    By default, it's set as false */
    @Value("${init.data}")
    private Boolean initialData;

    @Override
    public void run(String... args) {
        if (initialData) {
            System.out.println("‼️...adding mock data...‼️");
            initData();
        }
    }

    //Creating initial data method "mock data"
    private void initData() {
        Random random = new Random();
        int count = 0;

        List<String> namePrefixes = List.of("Travis", "Kendrick", "J Cole", "Drake", "Eminem", "Jay-Z");

        //Adding every possible role types to db and one user to every role
        for (RoleName roleName : RoleName.values()) {
            if (!roleService.exists(roleName)) {
                Role role = roleService.save(Role.builder().name(roleName).build());
                userService.save(
                        User.builder()
                                .firstName("user")
                                .lastName(roleName.name().toLowerCase())
                                .createdAt(LocalDateTime.now())
                                .phoneNumber(String.valueOf(count).repeat(3))
                                .roles(List.of(role))
                                .password(passwordEncoder.encode("qwe"))
                                .build());
                count++;
            }
        }

        //Adding courses, its groups, groups' timetables, students to timetable
        if (courseService.findAll().isEmpty() && groupService.findAll().isEmpty()) {
            for (int i = 0; i <= 5; i++) {
                // generating six courses
                Course course = courseService.save(Course.builder()
                        .price(random.nextInt(90, 150))
                        .name("CS" + ((i + 1) * 50))
                        .createdAt(LocalDateTime.now())
                        .build());
                // generating 10 groups for each course. That would be 60 groups and six courses overall.
                for (int j = 1; j <= 10; j++) {
                    Group group = groupService.save(Group.builder()
                            .name("Group " + (i * 10 + j))
                            .course(course)
                            .createdAt(LocalDateTime.now())
                            .build());
                    // one timetable for each group. Again, it would make it 60 timetables
                    Timetable timetable = timetableService.save(
                            Timetable.builder()
                                    .currentLesson(1)
                                    .group(group)
                                    .name("timetable-" + (j))
                                    .createdAt(LocalDateTime.now())
                                    .build());
                    // 5 students per timetable
                    for (int k = 0; k < 5; k++) {
                        String fullName = namePrefixes.get(random.nextInt(namePrefixes.size())) + " - " + (1 + k);
                        TimetableStudent timetableStudent = timetableStudentService.save(
                                TimetableStudent.builder()
                                        .timetable(timetable)
                                        .student(
                                                userService.save(User.builder()
                                                        .phoneNumber(String.valueOf(random.nextInt(194385320, 986676787)))
                                                        .createdAt(LocalDateTime.now())
                                                        .firstName(fullName)
                                                        .lastName("Scott - " + (1 + k))
                                                        .password(passwordEncoder.encode("123"))
                                                        .roles(
                                                                List.of(
                                                                        roleService.findByName(
                                                                                RoleName.ROLE_STUDENT)
                                                                ))
                                                        .build()))
                                        .createdAt(LocalDateTime.now())
                                        .build());
                        timetableStudentService.save(timetableStudent);
                        generateAttendances(timetableStudent);
                    }
                }
            }
        }
        if (paymentService.findAll().isEmpty()) {
            for (int i = 1; i <= 1000; i++) {
                User user = userService.findById(random.nextLong(1, 60));
                paymentService.save(Payment.builder()
                        .amount(random.nextInt(10, 30))
                        .student(user)
                        .payType(PayType.CARD)
                        .createdAt(getRandomLocalDateTime())
                        .build());
            }
        }
    }


    //Creating a default 12 lessons for a timetable as timetable is one month and 12 lessons would be there
    private void generateAttendances(TimetableStudent timetableStudent) {
        for (int i = 1; i <= 12; i++) {
            StudentAttendance studentAttendance = StudentAttendance.builder()
                    .attendance(false)
                    .lessonOrder(i)
                    .timetableStudent(timetableStudent)
                    .createdAt(LocalDateTime.now())
                    .build();
            studentAttendanceService.save(studentAttendance);
        }
    }

    //Generating random local date time for payments
    private LocalDateTime getRandomLocalDateTime() {
        Random random = new Random();
        LocalDateTime origin = LocalDateTime.of(2024, 1, 1, 0, 0);
        LocalDateTime now = LocalDateTime.now();
        long days = origin.toLocalDate().toEpochDay() + random.nextInt((int) (now.toLocalDate().toEpochDay() - origin.toLocalDate().toEpochDay() + 1));
        long seconds = random.nextInt(24 * 60 * 60);
        return LocalDateTime.ofEpochSecond(days * 24 * 60 * 60 + seconds, 0, OffsetDateTime.now().getOffset());
    }
}
