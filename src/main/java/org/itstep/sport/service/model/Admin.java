package org.itstep.sport.service.model;

import lombok.*;

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
public class Admin extends User {

    @Column(name = "iin", length = 12, unique = true)
    private String iin;
}
