package org.itstep.sport.service.service.impl;

import lombok.RequiredArgsConstructor;
import org.itstep.sport.service.model.Authorities;
import org.itstep.sport.service.model.Trainee;
import org.itstep.sport.service.repository.TraineeRepositoryImpl;
import org.itstep.sport.service.service.TraineeService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TraineeServiceImpl implements TraineeService {

    private final TraineeRepositoryImpl traineeRepository;

    @Override
    public Trainee save(Trainee trainee) {
        trainee.setAuthority(Authorities.ROLE_TRAINEE);
        return traineeRepository.save(trainee);
    }
}
