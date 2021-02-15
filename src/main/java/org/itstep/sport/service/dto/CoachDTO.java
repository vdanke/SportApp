package org.itstep.sport.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoachDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("firstname")
    private String firstname;

    @JsonProperty("lastname")
    private String lastname;

    @JsonProperty("phoneNumber")
    private String phoneNumber;

    @JsonProperty("category")
    private String category;

    @JsonProperty("sportClass")
    private String sportClass;

    @JsonProperty("achievements")
    private String achievements;
}
