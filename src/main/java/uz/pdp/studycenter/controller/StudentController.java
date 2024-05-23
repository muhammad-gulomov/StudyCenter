package uz.pdp.studycenter.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.pdp.studycenter.entity.User;
import uz.pdp.studycenter.entity.enums.PayType;
import uz.pdp.studycenter.model.resp.StudentInfoResponseDto;
import uz.pdp.studycenter.model.resp.StudentRespDto;
import uz.pdp.studycenter.service.PaymentServiceImpl;
import uz.pdp.studycenter.service.UserServiceImpl;

import java.util.Arrays;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("students")
public class StudentController {
    private final UserServiceImpl userService;
    private final PaymentServiceImpl paymentService;
    @GetMapping
    public String students(Model model) {
        List<StudentRespDto> students = userService.findAllByIsArchivedFalse();
        model.addAttribute("path","students");
        model.addAttribute("students", students);
        return "students";
    }

    @GetMapping("search")
    public String search(@RequestParam String search, Model model) {
        List<StudentRespDto> allBySearch = userService.findAllBySearch("%"+search+"%");
        model.addAttribute("path","students");
        model.addAttribute("students", allBySearch);
        return "students";
    }
    @GetMapping("info/{id}")
    public String info(@PathVariable Long id, Model model) {
        StudentInfoResponseDto student = userService.findStudentInfosById(id);
        model.addAttribute("student",student);
        return "studentInfo";
    }

    @GetMapping("payment/{id}")
    public String payment(@PathVariable Long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        model.addAttribute("payTypes", Arrays.asList(PayType.values()));
        return "paymentForm";
    }

    @PostMapping("/addPayment")
    public String addPayment(Long id, Integer price, String payType, Model model) {
        paymentService.save(id, price, payType);
        return "redirect:/payments";
    }
}
