package org.itstep.sport.service.service.impl;

import lombok.RequiredArgsConstructor;
import org.itstep.sport.service.dto.TraineeDTO;
import org.itstep.sport.service.exception.NotFoundException;
import org.itstep.sport.service.mapper.TraineeMapper;
import org.itstep.sport.service.model.Authorities;
import org.itstep.sport.service.model.Trainee;
import org.itstep.sport.service.repository.TraineeRepositoryImpl;
import org.itstep.sport.service.service.CabinetService;
import org.itstep.sport.service.service.TraineeService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TraineeServiceImpl implements TraineeService, CabinetService<Trainee> {

    private final TraineeRepositoryImpl traineeRepository;
    private final TraineeMapper traineeMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public List<TraineeDTO> findAllDto() {
        return traineeRepository.findAllDto();
    }

    @Override
    @Transactional(readOnly = true)
    public Trainee getUserCabinet(String username) {
        return traineeRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException(
                        String.format("Trainee with username %s not found", username)
                ));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Trainee save(Trainee trainee) {
        trainee.setAuthority(Authorities.ROLE_TRAINEE);
        trainee.setPassword(passwordEncoder.encode(trainee.getPassword()));
        return traineeRepository.save(trainee);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Trainee update(Trainee trainee, String username) {
        Trainee traineeFromDB = traineeRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException(
                        String.format("User with username %s not found", username)
                ));
        traineeMapper.updateExistingTrainee(traineeFromDB, trainee);
        return traineeFromDB;
    }
}
