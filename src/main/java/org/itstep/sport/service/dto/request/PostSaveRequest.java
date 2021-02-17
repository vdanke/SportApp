package org.itstep.sport.service.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostSaveRequest {

    @JsonProperty("topic")
    private String topic;

    @JsonProperty("text")
    private String text;

    @JsonProperty("categoryId")
    private String categoryId;
}
