package org.itstep.sport.service.model;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "coaches")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "username", callSuper = true)
@ToString
@Builder
@DynamicInsert
@DynamicUpdate
public class Coach extends User {

    @Column(name = "sport_class", length = 128)
    private String sportClass;

    @Column(name = "achievements", length = 512)
    private String achievements;

    @Column(name = "category")
    private String category;

    @ToString.Exclude
    @ManyToMany(
            fetch = FetchType.LAZY
    )
    @JoinTable(
            name = "coach_trainee",
            joinColumns = @JoinColumn(name = "coach_id"),
            inverseJoinColumns = @JoinColumn(name = "trainee_id"),
            foreignKey = @ForeignKey(name = "coach_trainee_fk"),
            inverseForeignKey = @ForeignKey(name = "trainee_coach_fk")
    )
    private Set<Trainee> trainees = new HashSet<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "coach", fetch = FetchType.LAZY)
    private Set<Post> posts = new HashSet<>();

    public void addTrainee(Trainee trainee) {
        this.trainees.add(trainee);
        trainee.getCoaches().add(this);
    }

    public void addPost(Post post) {
        this.posts.add(post);
        post.setCoach(this);
    }
}
