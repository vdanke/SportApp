package org.itstep.sport.service.model;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

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
@DynamicInsert
@DynamicUpdate
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

    @ToString.Exclude
    @OneToMany(mappedBy = "trainee", fetch = FetchType.LAZY)
    private Set<Comment> comments = new HashSet<>();

    public void addCoach(Coach coach) {
        this.coaches.add(coach);
        coach.getTrainees().add(this);
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
        comment.setTrainee(this);
    }
}
