package org.itstep.sport.service.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class UserCabinetResponse {

    @JsonProperty("username")
    protected String username;

    @JsonProperty("firstname")
    protected String firstname;

    @JsonProperty("lastname")
    protected String lastname;

    @JsonProperty("phoneNumber")
    protected String phoneNumber;
}
