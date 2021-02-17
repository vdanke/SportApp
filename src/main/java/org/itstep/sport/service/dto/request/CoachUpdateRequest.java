package org.itstep.sport.service.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoachUpdateRequest {

    @JsonProperty("firstname")
    private String firstname;

    @JsonProperty("lastname")
    private String lastname;

    @JsonProperty("sportClass")
    private String sportClass;

    @JsonProperty("achievements")
    private String achievements;

    @JsonProperty("category")
    private String category;

    @JsonProperty("phoneNumber")
    private String phoneNumber;
}
