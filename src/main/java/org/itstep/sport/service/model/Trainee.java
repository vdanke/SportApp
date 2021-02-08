package org.itstep.sport.service.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "trainees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "username", callSuper = true)
@ToString(callSuper = true)
@Builder
public class Trainee extends User {

    @Column(name = "purpose")
    private String purpose;

    @Column(name = "height")
    private Integer height;

    @Column(name = "weight")
    private Integer weight;

    @ToString.Exclude
    @ManyToMany(
            mappedBy = "trainees",
            fetch = FetchType.LAZY
    )
    private Set<Coach> coaches = new HashSet<>();
}
