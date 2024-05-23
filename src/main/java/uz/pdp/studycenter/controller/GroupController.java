package uz.pdp.studycenter.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import uz.pdp.studycenter.entity.Course;
import uz.pdp.studycenter.model.req.GroupRequestDto;
import uz.pdp.studycenter.model.resp.GroupResponseDto;
import uz.pdp.studycenter.service.CourseServiceImpl;
import uz.pdp.studycenter.service.GroupServiceImpl;
import uz.pdp.studycenter.service.TimetableService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class GroupController {
    private final GroupServiceImpl groupService;
    private final CourseServiceImpl courseService;
    private final TimetableService timetableService;
    private final HttpSession httpSession;

    @GetMapping
    public String group(Model model) {
        List<GroupResponseDto> groups = groupService.findAllByIsArchivedFalse();
        model.addAttribute("groups", groups);
        model.addAttribute("path", "/");
        return "index";
    }

    @GetMapping("/groupForm")
    public String groupForm(Model model) {
        List<Course> courses = courseService.findAll();
        model.addAttribute("courses", courses);
        return "groupForm";
    }

    @PostMapping("addGroup")
    public String addGroup(GroupRequestDto groupRequestDto, Model model) {
        groupService.save(groupRequestDto);
        return "redirect:/";
    }

    @GetMapping("/group/delete/{id}")
    public String deleteGroup(@PathVariable Long id) {
        groupService.remove(id);
        return "redirect:/";
    }

    @GetMapping("fetch/timetables/via/groups/{groupId}")
    private String fetchTimeTablesByGroupId(@PathVariable Long groupId, Model model) {
        model.addAttribute("path", "timetables");
        model.addAttribute("groups", groupService.findAll());
        model.addAttribute("timetables", timetableService.findAllByGroupId(groupId));
        httpSession.setAttribute("groupId", groupId);
        return "timetables";
    }
}
