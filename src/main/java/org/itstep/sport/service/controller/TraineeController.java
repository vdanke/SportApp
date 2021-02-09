package org.itstep.sport.service.controller;

import lombok.RequiredArgsConstructor;
import org.itstep.sport.service.dto.request.TraineeSaveRequest;
import org.itstep.sport.service.dto.response.TraineeSaveResponse;
import org.itstep.sport.service.model.Trainee;
import org.itstep.sport.service.service.TraineeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class TraineeController {

    private final TraineeService traineeService;

    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<TraineeSaveResponse> saveTrainee(
            @Valid @RequestBody TraineeSaveRequest request
    ) {
        Trainee trainee = new Trainee();

        trainee.setUsername(request.getUsername());
        trainee.setPassword(request.getPassword());
        trainee.setFirstname(request.getFirstname());
        trainee.setLastname(request.getLastname());

        Trainee savedTrainee = traineeService.save(trainee);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new TraineeSaveResponse(savedTrainee.getId(), savedTrainee.getUsername()));
    }
}
