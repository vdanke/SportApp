package org.itstep.sport.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TraineeDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("username")
    private String username;

    @JsonProperty("firstname")
    private String firstname;

    @JsonProperty("lastname")
    private String lastname;

    @JsonProperty("phoneNumber")
    private String phoneNumber;

    @JsonProperty("purpose")
    private String purpose;

    @JsonProperty("weight")
    private Integer weight;

    @JsonProperty("height")
    private Integer height;
}
