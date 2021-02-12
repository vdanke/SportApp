package org.itstep.sport.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

@NoRepositoryBean
public interface UserRepository<T, ID> extends JpaRepository<T, ID> {

    @Query("select u from #{#entityName} u where u.username=:username")
    Optional<T> findByUsername(@Param("username") String username);
}
