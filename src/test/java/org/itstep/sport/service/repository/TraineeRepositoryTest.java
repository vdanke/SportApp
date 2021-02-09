package org.itstep.sport.service.repository;

import org.itstep.sport.service.model.Authorities;
import org.itstep.sport.service.model.Trainee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@TestPropertySource(value = "classpath:application-test.properties")
@Sql(scripts = {"classpath:test_sql/trainee_up.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = {"classpath:test_sql/trainee_down.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class TraineeRepositoryTest {

    private final int expectedSize = 3;

    @Autowired
    public TraineeRepositoryImpl traineeRepository;

    @Test
    public void shouldSaveNewTrainee() {
        Trainee trainee = new Trainee();
        trainee.setUsername("test@mail.ru");
        trainee.setPassword("testPassword");
        trainee.setFirstname("testFirstName");
        trainee.setLastname("testLastName");
        trainee.setAuthority(Authorities.ROLE_TRAINEE);

        Trainee savedTrainee = traineeRepository.save(trainee);

        Assertions.assertNotNull(savedTrainee.getId());
    }

    @Test
    public void shouldThrowExceptionWhileSavingNewTrainee() {
        Trainee trainee = new Trainee();

        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            traineeRepository.save(trainee);
        });
    }

    @Test
    public void shouldFindAllTrainees() {
        List<Trainee> trainees = traineeRepository.findAll();

        Assertions.assertNotNull(trainees);
        Assertions.assertFalse(trainees.isEmpty());
        Assertions.assertEquals(expectedSize, trainees.size());
    }

    @Test
    public void shouldFindTraineeById() {
        final long id = 1;

        Optional<Trainee> traineeById = traineeRepository.findById(id);

        Assertions.assertTrue(traineeById.isPresent());
        Assertions.assertEquals(id, traineeById.get().getId());
    }

    @Test
    public void shouldNotFoundUserById() {
        final long id = 4;

        Optional<Trainee> traineeById = traineeRepository.findById(id);

        Assertions.assertFalse(traineeById.isPresent());
    }
}
