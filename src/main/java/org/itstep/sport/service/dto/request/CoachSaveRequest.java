package org.itstep.sport.service.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoachSaveRequest {

    @NotBlank
    @Size(min = 2, max = 128)
    @JsonProperty("username")
    private String username;

    @NotBlank
    @Size(min = 7, max = 128)
    @JsonProperty("password")
    private String password;

    @NotBlank
    @Size(min = 2, max = 128)
    @JsonProperty("firstname")
    private String firstname;

    @NotBlank
    @Size(min = 2, max = 128)
    @JsonProperty("lastname")
    private String lastname;

    @JsonProperty("phoneNumber")
    private String phoneNumber;
}
