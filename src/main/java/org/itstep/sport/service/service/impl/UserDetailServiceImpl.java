package org.itstep.sport.service.service.impl;

import lombok.RequiredArgsConstructor;
import org.itstep.sport.service.model.Coach;
import org.itstep.sport.service.model.Trainee;
import org.itstep.sport.service.model.UserDetailsImpl;
import org.itstep.sport.service.repository.CoachRepositoryImpl;
import org.itstep.sport.service.repository.TraineeRepositoryImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final TraineeRepositoryImpl traineeRepository;
    private final CoachRepositoryImpl coachRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Trainee> trainee = traineeRepository.findByUsername(username);

        if (trainee.isPresent()) {
            return UserDetailsImpl.toUserDetails(trainee.get());
        }
        Optional<Coach> coach = coachRepository.findByUsername(username);

        if (coach.isPresent()) {
            return UserDetailsImpl.toUserDetails(coach.get());
        }
        throw new UsernameNotFoundException(String.format("User with %s username not found", username));
    }
}
