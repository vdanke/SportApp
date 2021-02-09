package org.itstep.sport.service.service;

import org.itstep.sport.service.model.Authorities;
import org.itstep.sport.service.model.Trainee;
import org.itstep.sport.service.repository.TraineeRepositoryImpl;
import org.itstep.sport.service.service.impl.TraineeServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.SpringBootDependencyInjectionTestExecutionListener;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@TestExecutionListeners(
        listeners = {
                MockitoTestExecutionListener.class,
                SpringBootDependencyInjectionTestExecutionListener.class
        }
)
@Import(TraineeServiceImpl.class)
public class TraineeServiceTest {

    @Autowired
    private TraineeServiceImpl traineeService;
    @MockBean
    private TraineeRepositoryImpl traineeRepository;

    @Test
    public void shouldPrepareUserForSaving() {
        final long id = 5L;
        final String testData = "testData";

        Trainee trainee = new Trainee();
        trainee.setUsername(testData);
        trainee.setPassword(testData);
        trainee.setFirstname(testData);
        trainee.setLastname(testData);
        trainee.setId(id);

        Mockito.when(traineeRepository.save(ArgumentMatchers.any(Trainee.class)))
                .thenReturn(trainee);

        Trainee savedTrainee = traineeService.save(trainee);

        Assertions.assertNotNull(savedTrainee.getId());
        Assertions.assertEquals(id, savedTrainee.getId());
        Assertions.assertEquals(testData, savedTrainee.getUsername());
        Assertions.assertEquals(testData, savedTrainee.getPassword());
        Assertions.assertEquals(testData, savedTrainee.getFirstname());
        Assertions.assertEquals(testData, savedTrainee.getLastname());
        Assertions.assertNotNull(savedTrainee.getAuthority());
        Assertions.assertEquals(Authorities.ROLE_TRAINEE, savedTrainee.getAuthority());

        Mockito.verify(traineeRepository, Mockito.times(1))
                .save(ArgumentMatchers.any(Trainee.class));
    }
}
