package uz.pdp.studycenter.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.pdp.studycenter.entity.Payment;
import uz.pdp.studycenter.model.req.PaymentRequestDto;

import java.sql.Timestamp;
import java.util.List;


@Repository
public interface PaymentRepo extends JpaRepository<Payment, Long> {
    @Query(value = """
                select u.first_name, u.last_name, u.phone_number, p.amount, p.created_at
                from users u
                         join payment p on u.id = p.student_id
            """, nativeQuery = true)
    List<Object[]> findAllPaymentRequestDto();

    @Query(value = """
                      select u.first_name, u.last_name, u.phone_number, p.amount, p.created_at
                      from users u
                               join payment p on u.id = p.student_id
                      where p.created_at between :startDate and :endDate
            """, nativeQuery = true)
    List<Object[]> findAllByFilterByDates(Timestamp startDate, Timestamp endDate);
}
