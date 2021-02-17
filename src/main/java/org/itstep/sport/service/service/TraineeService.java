package org.itstep.sport.service.service;

import org.itstep.sport.service.dto.TraineeDTO;
import org.itstep.sport.service.model.Trainee;

import java.util.List;

public interface TraineeService {

    Trainee save(Trainee trainee);

    List<TraineeDTO> findAllDto();
}
