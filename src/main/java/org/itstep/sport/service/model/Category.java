package org.itstep.sport.service.model;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@Builder
@EqualsAndHashCode(of = "name")
@ToString
@DynamicInsert
@DynamicUpdate
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Size(min = 3, max = 74)
    @Column(name = "name", unique = true, length = 74)
    private String name;

    @ToString.Exclude
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private Set<Post> posts = new HashSet<>();

    public void addPost(Post post) {
        this.posts.add(post);
        post.setCategory(this);
    }
}
