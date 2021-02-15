package org.itstep.sport.service.repository;

import org.itstep.sport.service.dto.CoachDTO;
import org.itstep.sport.service.model.Coach;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoachRepositoryImpl extends UserRepository<Coach, Long> {

    @Query("select new org.itstep.sport.service.dto.CoachDTO(c.id, c.firstname, c.lastname, c.phoneNumber, c.category, c.sportClass, c.achievements) from Coach c")
    List<CoachDTO> findAllDto();
}
