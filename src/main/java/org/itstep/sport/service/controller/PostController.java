package org.itstep.sport.service.controller;

import lombok.RequiredArgsConstructor;
import org.itstep.sport.service.dto.PostDTO;
import org.itstep.sport.service.dto.request.PostSaveRequest;
import org.itstep.sport.service.mapper.PostMapper;
import org.itstep.sport.service.model.Post;
import org.itstep.sport.service.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final PostMapper postMapper;

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<PostDTO>> getAllPosts() {
        List<PostDTO> postDTOList = postService.findAllDto();

        return ResponseEntity.ok(postDTOList);
    }

    @GetMapping(
            path = "/{category}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<PostDTO>> getAllPostsByCategory(
            @PathVariable(name = "category") String category
    ) {
        List<PostDTO> postDTOList = postService.findAllDtoByCategory(category);

        return ResponseEntity.ok(postDTOList);
    }

    @PreAuthorize("hasAnyRole('ROLE_COACH', 'ROLE_ADMIN')")
    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Void> addPost(
            @Valid @RequestBody PostSaveRequest request,
            @AuthenticationPrincipal String username
    ) {
        Post post = postMapper.mapToPostFromPostSaveRequest(request);

        postService.save(post, username);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
