package org.itstep.sport.service.service.impl;

import lombok.RequiredArgsConstructor;
import org.itstep.sport.service.exception.NotFoundException;
import org.itstep.sport.service.mapper.TraineeMapper;
import org.itstep.sport.service.model.Authorities;
import org.itstep.sport.service.model.Trainee;
import org.itstep.sport.service.repository.TraineeRepositoryImpl;
import org.itstep.sport.service.service.TraineeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TraineeServiceImpl implements TraineeService {

    private final TraineeRepositoryImpl traineeRepository;
    private final TraineeMapper traineeMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Trainee save(Trainee trainee) {
        trainee.setAuthority(Authorities.ROLE_TRAINEE);
        return traineeRepository.save(trainee);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Trainee update(Trainee trainee, Long id) {
        Trainee traineeFromDB = traineeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(
                        "Trainee with ID %d not found", trainee.getId()
                )));
        traineeMapper.updateExistingTrainee(traineeFromDB, trainee);
        return traineeFromDB;
    }
}
