package org.itstep.sport.service.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Size(min = 5, max = 128)
    @Email
    @Column(name = "username", unique = true, length = 128)
    private String username;

    @NotBlank
    @Size(min = 7, max = 128)
    @Column(name = "password", length = 128)
    private String password;

    @NotBlank
    @Size(min = 2, max = 128)
    @Column(name = "firstname", length = 128)
    private String firstname;

    @NotBlank
    @Size(min = 2, max = 128)
    @Column(name = "lastname", length = 128)
    private String lastname;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "authority", length = 15)
    private Authorities authority;

    @Column(name = "phone_number", length = 15)
    private String phoneNumber;
}
