package uz.pdp.studycenter.service;

import org.springframework.stereotype.Service;
import uz.pdp.studycenter.entity.Payment;
import uz.pdp.studycenter.model.req.PaymentRequestDto;

import java.util.List;


@Service
public interface PaymentService {
    void save(Payment payment);

    List<Payment> findAll();

    List<PaymentRequestDto> findAllPaymentRequestDto();

    List<PaymentRequestDto> findAllByFilterByDates(String startDate, String endDate);

    void save(Long id, Integer price, String payType);
}
