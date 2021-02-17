package org.itstep.sport.service.service.impl;

import lombok.RequiredArgsConstructor;
import org.itstep.sport.service.dto.CoachDTO;
import org.itstep.sport.service.exception.NotFoundException;
import org.itstep.sport.service.mapper.CoachMapper;
import org.itstep.sport.service.model.Authorities;
import org.itstep.sport.service.model.Coach;
import org.itstep.sport.service.repository.CoachRepositoryImpl;
import org.itstep.sport.service.service.CabinetService;
import org.itstep.sport.service.service.CoachService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CoachServiceImpl implements CoachService, CabinetService<Coach> {

    private final CoachRepositoryImpl coachRepository;
    private final PasswordEncoder passwordEncoder;
    private final CoachMapper coachMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Coach save(Coach coach) {
        coach.setAuthority(Authorities.ROLE_COACH);
        coach.setPassword(passwordEncoder.encode(coach.getPassword()));
        return coachRepository.save(coach);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CoachDTO> findAllDto() {
        return coachRepository.findAllDto();
    }

    @Override
    public Coach getUserCabinet(String username) {
        return coachRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException(
                        String.format("Coach with username %s not found", username)
                ));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Coach update(Coach coach, String username) {
        Coach coachFromDb = coachRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException(
                        String.format("Coach with username %s not found", username)
                ));
        coachMapper.updateExistingCoach(coachFromDb, coach);
        return coachFromDb;
    }
}
