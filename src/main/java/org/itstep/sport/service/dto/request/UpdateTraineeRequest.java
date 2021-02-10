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
public class UpdateTraineeRequest {

    @NotBlank
    @Size(min = 2, max = 128)
    @JsonProperty("firstname")
    private String firstname;

    @NotBlank
    @Size(min = 2, max = 128)
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
