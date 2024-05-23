package uz.pdp.studycenter.entity;

import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@Entity
public class Course extends BaseEntity {
    private String name;
    private String description;
    private Integer price;
}
