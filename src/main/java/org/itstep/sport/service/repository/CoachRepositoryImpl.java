package org.itstep.sport.service.repository;

import org.itstep.sport.service.model.Coach;
import org.springframework.stereotype.Repository;

@Repository
public interface CoachRepositoryImpl extends UserRepository<Coach, Long> {
}
