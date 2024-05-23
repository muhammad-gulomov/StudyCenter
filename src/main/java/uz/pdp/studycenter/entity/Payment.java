package uz.pdp.studycenter.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;
import lombok.experimental.SuperBuilder;
import uz.pdp.studycenter.entity.enums.PayType;


@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@SuperBuilder
@Data
@Entity
public class Payment extends BaseEntity{
    @ManyToOne
    private User student;
    @Min(value = 1, message = "Amount must be greater than zero!")
    private int amount;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PayType payType;
}
