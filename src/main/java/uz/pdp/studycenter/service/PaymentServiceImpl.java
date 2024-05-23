package uz.pdp.studycenter.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.studycenter.entity.Payment;
import uz.pdp.studycenter.entity.User;
import uz.pdp.studycenter.entity.enums.PayType;
import uz.pdp.studycenter.model.req.PaymentRequestDto;
import uz.pdp.studycenter.repo.PaymentRepo;
import uz.pdp.studycenter.repo.UserRepo;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepo paymentRepo;
    private final UserServiceImpl userService;

    @Override
    public List<Payment> findAll() {
        return paymentRepo.findAll();
    }

    @Override
    public void save(Payment payment) {
        paymentRepo.save(payment);
    }

    @Override
    public List<PaymentRequestDto> findAllPaymentRequestDto() {
        List<Object[]> results = paymentRepo.findAllPaymentRequestDto();
        return results.stream().map(result -> mapTpPaymentRequestDto(result)).toList();
    }

    @Override
    public List<PaymentRequestDto> findAllByFilterByDates(String startDateStr, String endDateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime startDateTime = LocalDateTime.parse(startDateStr, formatter);
        LocalDateTime endDateTime = LocalDateTime.parse(endDateStr, formatter);
        Timestamp startDate = Timestamp.valueOf(startDateTime);
        Timestamp endDate = Timestamp.valueOf(endDateTime);
        return paymentRepo.findAllByFilterByDates(startDate, endDate).stream().map(result -> mapTpPaymentRequestDto(result)).toList();
    }

    private PaymentRequestDto mapTpPaymentRequestDto(Object[] result) {
        String firstName = (String) result[0];
        String lastName = (String) result[1];
        String phoneNumber = (String) result[2];
        Integer amount = (Integer) result[3];
        String date = ((Timestamp) result[4]).toLocalDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return new PaymentRequestDto(firstName, lastName, phoneNumber, amount, date);
    }

    @Override
    public void save(Long id, Integer price, String payType) {
        User user = userService.findById(id);
        Payment payment = Payment.builder()
                .payType(PayType.valueOf(payType))
                .createdAt(LocalDateTime.now())
                .student(user)
                .amount(price)
                .build();
        paymentRepo.save(payment);
    }
}
