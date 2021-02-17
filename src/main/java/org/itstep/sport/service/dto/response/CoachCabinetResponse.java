package org.itstep.sport.service.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CoachCabinetResponse extends UserCabinetResponse {

    @JsonProperty("sportClass")
    private String sportClass;

    @JsonProperty("achievements")
    private String achievements;

    @JsonProperty("category")
    private String category;
}
