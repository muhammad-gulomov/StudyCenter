package uz.pdp.studycenter.component;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.pdp.studycenter.entity.*;
import uz.pdp.studycenter.entity.enums.PayType;
import uz.pdp.studycenter.entity.enums.RoleName;
import uz.pdp.studycenter.repo.PaymentRepo;
import uz.pdp.studycenter.service.*;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
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

    //Initial data will be created if init.data is true in application properties
    //By default, it's false
    @Value("${init.data}")
    private Boolean initialData;

    @Override
    public void run(String... args) {
        if (initialData) {
            System.out.println("...adding mock data...");
            initData();
        }
    }

    //Creating initial data method "mock data"
    private void initData() {
        Random random = new Random();
        int count = 0;

        //Adding every possible role types to db and one user to every role
        for (RoleName roleName : RoleName.values()) {
            if (!roleService.exists(roleName)) {
                Role role = roleService.save(Role.builder().name(roleName).build());
                userService.save(
                        User.builder()
                                .firstName("user")
                                .lastName(roleName.name().toLowerCase())
                                .createdAt(LocalDateTime.now())
                                .phoneNumber(String.valueOf(count).repeat(9))
                                .roles(List.of(role))
                                .password(passwordEncoder.encode("qwe"))
                                .build());
                count++;
            }
        }

        //Adding courses, its groups, groups' timetables, students to timetable
        if (courseService.findAll().isEmpty() && groupService.findAll().isEmpty()) {
            for (int i = 0; i <= 5; i++) {
                Course course = courseService.save(Course.builder()
                        .price(random.nextInt(90, 150))
                        .name("Course" + (i + 1))
                        .createdAt(LocalDateTime.now())
                        .build());
                for (int j = 1; j <= 10; j++) {
                    Group group = groupService.save(Group.builder()
                            .name("Group " + (i * 10 + j))
                            .course(course)
                            .createdAt(LocalDateTime.now())
                            .build());
                    Timetable timetable = timetableService.save(Timetable.builder()
                            .currentLesson(1)
                            .group(group)
                            .name("timetable-" + (j))
                            .createdAt(LocalDateTime.now())
                            .build());
                    timetableStudentService.save(TimetableStudent.builder()
                            .timetable(timetable)
                            .student(userService.save(User.builder()
                                    .phoneNumber(String.valueOf(random.nextInt(194385320, 986676787)))
                                    .createdAt(LocalDateTime.now())
                                    .firstName("John-" + (i * 10 + j))
                                    .lastName("Doe-" + (i * 10 + j))
                                    .password(passwordEncoder.encode("123"))
                                    .roles(List.of(roleService.findByName(RoleName.ROLE_STUDENT)))
                                    .build()))
                            .attendances(generateAttendances())
                            .createdAt(LocalDateTime.now())
                            .build());
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

    //Creating a default 12 lesson for a timetable as timetable is one month and 12 lesson would be there
    private List<StudentAttendance> generateAttendances() {
        List<StudentAttendance> attendances = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            StudentAttendance studentAttendance = StudentAttendance.builder()
                    .attendance(false)
                    .lessonOrder(i)
                    .createdAt(LocalDateTime.now())
                    .build();
            StudentAttendance saved = studentAttendanceService.save(studentAttendance);
            attendances.add(saved);
        }
        return attendances;
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
