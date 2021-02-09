package org.itstep.sport.service.repository;

import org.itstep.sport.service.model.Coach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoachRepositoryImpl extends JpaRepository<Coach, Long> {
}
