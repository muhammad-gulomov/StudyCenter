package uz.pdp.studycenter.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uz.pdp.studycenter.model.req.PaymentRequestDto;
import uz.pdp.studycenter.service.PaymentServiceImpl;

import java.util.List;

@Controller
@RequestMapping("payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentServiceImpl paymentService;
    @GetMapping
    public String payments(Model model) {
        List<PaymentRequestDto> payments = paymentService.findAllPaymentRequestDto();
        model.addAttribute("payments", payments);
        model.addAttribute("path", "payments");
        return "payments";
    }

    @GetMapping("filter")
    public String paymentsFilter(@RequestParam String fromDate, @RequestParam String toDate, Model model) {
        List<PaymentRequestDto> payments = paymentService.findAllByFilterByDates(fromDate, toDate);
        model.addAttribute("payments", payments);
        model.addAttribute("path", "payments");
        return "payments";

    }
}
