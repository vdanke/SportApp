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
    public Long id;

    @JsonProperty("firstname")
    public String firstname;

    @JsonProperty("lastname")
    public String lastname;

    @JsonProperty("phoneNumber")
    public String phoneNumber;

    @JsonProperty("purpose")
    public String purpose;

    @JsonProperty("weight")
    public Integer weight;

    @JsonProperty("height")
    public Integer height;
}
