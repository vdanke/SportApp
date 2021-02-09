package org.itstep.sport.service.repository;

import org.itstep.sport.service.model.Trainee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TraineeRepositoryImpl extends JpaRepository<Trainee, Long> {
}
