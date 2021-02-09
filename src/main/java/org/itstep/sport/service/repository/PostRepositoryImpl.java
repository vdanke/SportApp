package org.itstep.sport.service.repository;

import org.itstep.sport.service.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepositoryImpl extends JpaRepository<Post, Long> {
}
