package org.itstep.sport.service.controller;

import lombok.RequiredArgsConstructor;
import org.itstep.sport.service.dto.request.TraineeSaveRequest;
import org.itstep.sport.service.dto.request.UpdateTraineeRequest;
import org.itstep.sport.service.dto.response.TraineeSaveResponse;
import org.itstep.sport.service.dto.response.UpdateTraineeResponse;
import org.itstep.sport.service.mapper.TraineeMapper;
import org.itstep.sport.service.model.Trainee;
import org.itstep.sport.service.model.UserDetailsImpl;
import org.itstep.sport.service.service.TraineeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/trainees")
@RequiredArgsConstructor
public class TraineeController {

    private final TraineeService traineeService;
    private final TraineeMapper traineeMapper;

    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<TraineeSaveResponse> saveTrainee(
            @Valid @RequestBody TraineeSaveRequest request
    ) {
        Trainee trainee = traineeMapper.mapToTraineeFromTraineeSaveRequest(request);

        Trainee savedTrainee = traineeService.save(trainee);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(traineeMapper.mapToTraineeSaveResponseFromTrainee(savedTrainee));
    }

    @PreAuthorize("hasAnyRole('ROLE_TRAINEE', 'ROLE_COACH', 'ROLE_ADMIN')")
    @PutMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<UpdateTraineeResponse> updateTrainee(
            @Valid @RequestBody UpdateTraineeRequest request,
            @AuthenticationPrincipal String username
    ) {
        Trainee trainee = traineeMapper.mapToTraineeFromTraineeUpdateRequest(request);

        Trainee updatedTrainee = traineeService.update(trainee, username);

        return ResponseEntity.ok(traineeMapper.mapToUpdateTraineeResponseFromTrainee(updatedTrainee));
    }
}
