package org.itstep.sport.service.service.impl;

import lombok.RequiredArgsConstructor;
import org.itstep.sport.service.dto.CoachDTO;
import org.itstep.sport.service.model.Authorities;
import org.itstep.sport.service.model.Coach;
import org.itstep.sport.service.repository.CoachRepositoryImpl;
import org.itstep.sport.service.service.CoachService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CoachServiceImpl implements CoachService {

    private final CoachRepositoryImpl coachRepository;
    private final PasswordEncoder passwordEncoder;

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
}
