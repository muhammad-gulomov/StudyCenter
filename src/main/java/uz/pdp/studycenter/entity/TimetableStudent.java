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

    @ManyToOne
    private User student;
    @ManyToOne
    private Timetable timetable;
    private int paid;
    private boolean paymentStatus;
    @OneToMany(mappedBy = "timetableStudent", fetch = FetchType.EAGER)
    private List<StudentAttendance> attendances;

    @Override
    public String toString() {
        return "TimetableStudent{" +
               "paymentStatus=" + paymentStatus +
               ", paid=" + paid +
               ", timetable=" + timetable +
               ", student=" + student +
               '}';
    }
}
