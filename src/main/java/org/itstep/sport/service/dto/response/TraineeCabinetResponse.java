package org.itstep.sport.service.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TraineeCabinetResponse extends UserCabinetResponse {

    @JsonProperty("height")
    private Integer height;

    @JsonProperty("weight")
    private Integer weight;

    @JsonProperty("purpose")
    private String purpose;
}
