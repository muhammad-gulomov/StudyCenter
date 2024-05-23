package uz.pdp.studycenter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("timetables")
public class TimeTableController {
    @GetMapping
    public String timetable(Model model) {
        model.addAttribute("path", "timetables");
        return "timetables";
    }
}
