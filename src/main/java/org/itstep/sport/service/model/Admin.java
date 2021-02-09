package org.itstep.sport.service.model;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "admins")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "iin")
@ToString
@Builder
@DynamicInsert
@DynamicUpdate
public class Admin extends User {

    @Column(name = "iin", length = 12, unique = true)
    private String iin;
}
