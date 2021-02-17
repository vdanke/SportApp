package org.itstep.sport.service.service.impl;

import lombok.RequiredArgsConstructor;
import org.itstep.sport.service.dto.PostDTO;
import org.itstep.sport.service.exception.NotFoundException;
import org.itstep.sport.service.model.Category;
import org.itstep.sport.service.model.Coach;
import org.itstep.sport.service.model.Post;
import org.itstep.sport.service.repository.CoachRepositoryImpl;
import org.itstep.sport.service.repository.PostRepositoryImpl;
import org.itstep.sport.service.service.CabinetService;
import org.itstep.sport.service.service.CoachService;
import org.itstep.sport.service.service.PostService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepositoryImpl postRepository;
    private final CoachRepositoryImpl coachRepository;

    @Override
    @Transactional(readOnly = true)
    public List<PostDTO> findAllDto() {
        return postRepository.findAllWithAuthor()
                .parallelStream()
                .map(PostDTO::toPostDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<PostDTO> findAllDtoByCategory(String category) {
        return postRepository.findAllWithAuthorByCategory(category)
                .parallelStream()
                .map(PostDTO::toPostDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void save(Post post, String username) {
        Coach coach = coachRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException(
                        String.format("Coach with username %s not found", username)
                ));
        postRepository.insertPost(
                post.getTopic(),
                post.getText(),
                post.getCategory().getId(),
                coach.getId(),
                UUID.randomUUID().toString()
        );
    }
}
