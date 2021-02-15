package org.itstep.sport.service.service;

import org.itstep.sport.service.dto.CoachDTO;
import org.itstep.sport.service.model.Coach;

import java.util.List;

public interface CoachService {

    List<CoachDTO> findAllDto();

    Coach save(Coach coach);
}
