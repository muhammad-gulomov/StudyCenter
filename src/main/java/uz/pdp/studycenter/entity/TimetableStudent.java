package uz.pdp.studycenter.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@SuperBuilder
@Data
@Entity
public class TimetableStudent extends BaseEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    private User student;
    @ManyToOne
    private Timetable timetable;
    private int paid;
    private boolean paymentStatus;
    @OneToMany(mappedBy = "timetableStudent")
    private List<StudentAttendance> attendances;

}
