package org.itstep.sport.service.service;

import org.itstep.sport.service.dto.TraineeDTO;
import org.itstep.sport.service.model.Trainee;

import java.util.List;

public interface TraineeService {

    Trainee save(Trainee trainee);

    Trainee update(Trainee trainee, String username);

    Trainee getUserCabinet(String username);

    List<TraineeDTO> findAllDto();
}
