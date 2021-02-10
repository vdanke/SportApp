package org.itstep.sport.service.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTraineeResponse {

    @JsonProperty("firstname")
    private String firstname;

    @JsonProperty("lastname")
    private String lastname;

    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("purpose")
    private String purpose;

    @JsonProperty("height")
    private Integer height;

    @JsonProperty("weight")
    private Integer weight;
}
