package uz.pdp.studycenter.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uz.pdp.studycenter.model.resp.StudentData;
import uz.pdp.studycenter.service.GroupService;
import uz.pdp.studycenter.service.TimetableService;
import uz.pdp.studycenter.service.TimetableStudentService;

import java.util.List;

@Controller
@RequestMapping("timetables")
@RequiredArgsConstructor
public class TimeTableController {
    private final GroupService groupService;
    private final HttpSession httpSession;
    private final TimetableService timetableService;
    private final TimetableStudentService timetableStudentService;

    @GetMapping
    public String timetable(Model model) {
        model.addAttribute("path", "timetables");
        model.addAttribute("groups", groupService.findAll());
        return "timetables";
    }

    @GetMapping("fetch/by/timetable/id/")
    private String fetchByTimeTableId(Model model, @RequestParam Long timetableId) {
        Long groupId = (Long) httpSession.getAttribute("groupId");

        model.addAttribute("path", "timetables");
        model.addAttribute("groups", groupService.findAll());
        model.addAttribute("groupId",groupId);
        model.addAttribute("timetables", timetableService.findAllByGroupId(groupId));
        List<StudentData> byTimeTableData = timetableStudentService.findByTimeTableData(timetableId);
        for (StudentData byTimeTableDatum : byTimeTableData) {
            System.out.println(byTimeTableDatum);
        }
        model.addAttribute("studentData", byTimeTableData);
        return "timetables";
    }
}
