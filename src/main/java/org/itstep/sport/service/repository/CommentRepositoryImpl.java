package org.itstep.sport.service.repository;

import org.itstep.sport.service.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepositoryImpl extends JpaRepository<Comment, Long> {
}
