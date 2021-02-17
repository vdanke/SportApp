package org.itstep.sport.service.service;

import org.itstep.sport.service.dto.PostDTO;
import org.itstep.sport.service.model.Post;

import java.util.List;

public interface PostService {

    List<PostDTO> findAllDto();

    List<PostDTO> findAllDtoByCategory(String category);

    void save(Post post, String username);
}
