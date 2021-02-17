package org.itstep.sport.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.itstep.sport.service.model.Post;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {

    @JsonProperty("topic")
    private String topic;

    @JsonProperty("text")
    private String text;

    @JsonProperty("author")
    private String author;

    public static PostDTO toPostDTO(Post post) {
        return new PostDTO(
                post.getTopic(),
                post.getText(),
                String.format("%s %s", post.getCoach().getFirstname(), post.getCoach().getLastname())
        );
    }
}
