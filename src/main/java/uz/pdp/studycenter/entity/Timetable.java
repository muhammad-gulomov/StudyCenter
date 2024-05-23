package uz.pdp.studycenter.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@SuperBuilder
@Data
@Entity
public class Timetable extends BaseEntity {
    @ManyToOne
    private Group group;
    private Integer currentLesson;
    private String name;
}
