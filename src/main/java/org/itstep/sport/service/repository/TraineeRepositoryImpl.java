package org.itstep.sport.service.repository;

import org.itstep.sport.service.dto.TraineeDTO;
import org.itstep.sport.service.model.Trainee;
import org.itstep.sport.service.model.Trainee_;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TraineeRepositoryImpl extends UserRepository<Trainee, Long> {

    @Query("select t from Trainee t where t.id=:id")
    @EntityGraph(attributePaths = {Trainee_.COACHES})
    Optional<Trainee> findByIdWithCoaches(@Param("id") Long id);

    @Query("select new org.itstep.sport.service.dto.TraineeDTO(t.id, t.username, t.firstname, t.lastname, t.phoneNumber, t.purpose, t.weight, t.height) from Trainee t")
    List<TraineeDTO> findAllDto();
}
