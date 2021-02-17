package org.itstep.sport.service.repository;

import org.itstep.sport.service.model.Post;
import org.itstep.sport.service.model.Post_;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepositoryImpl extends JpaRepository<Post, Long> {

    @Query("select p from Post p")
    @EntityGraph(attributePaths = {Post_.COACH})
    List<Post> findAllWithAuthor();

    @Query("select p from Post p where p.category.name=?1")
    @EntityGraph(attributePaths = {Post_.COACH})
    List<Post> findAllWithAuthorByCategory(String category);

    @Modifying
    @Query(
            nativeQuery = true,
            value = "INSERT INTO POSTS(TOPIC, TEXT, CATEGORY_ID, COACH_ID, POST_BUSINESS_KEY) VALUES(?1, ?2, ?3, ?4, ?5)"
    )
    void insertPost(String topic, String text, Long categoryId, Long coachId, String businessKey);
}
