package uz.pdp.studycenter.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@SuperBuilder
@Data
@Entity
@Table(name = "groups")
public class Group extends BaseEntity {
    private String name;
    @ManyToOne
    @NotNull
    private Course course;

}
