package uz.pdp.studycenter.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.pdp.studycenter.service.AttendanceService;

@Controller
@RequestMapping("attendance")
@RequiredArgsConstructor
public class AttendanceController {
    private final AttendanceService attendanceService;

    @GetMapping("/attendance/{attendanceId}")
    private String alterStatusById(@PathVariable Long attendanceId) {
        attendanceService.alterStateById(attendanceId);
        return "redirect:/timetable";
    }
}
