package org.itstep.sport.service.mapper;

import org.itstep.sport.service.dto.request.PostSaveRequest;
import org.itstep.sport.service.model.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface PostMapper {
    @Mappings({
            @Mapping(target = "category.id", expression = "java(Long.parseLong(postSaveRequest.getCategoryId()))"),
            @Mapping(target = "text", source = "request.text"),
            @Mapping(target = "topic", source = "request.topic"),
            @Mapping(target = "postBusinessKey", ignore = true),
            @Mapping(target = "coach", ignore = true),
            @Mapping(target = "comments", ignore = true)
    })
    Post mapToPostFromPostSaveRequest(PostSaveRequest request);
}
