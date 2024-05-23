package uz.pdp.studycenter.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@SuperBuilder
@Data
@Entity
public class StudentAttendance extends BaseEntity{

    @ManyToOne
    private TimetableStudent timetableStudent;
    private Integer lessonOrder;
    private Boolean attendance;

}
