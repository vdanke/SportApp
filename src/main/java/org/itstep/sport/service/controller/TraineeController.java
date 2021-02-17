package org.itstep.sport.service.controller;

import lombok.RequiredArgsConstructor;
import org.itstep.sport.service.dto.TraineeDTO;
import org.itstep.sport.service.dto.request.TraineeSaveRequest;
import org.itstep.sport.service.dto.request.UpdateTraineeRequest;
import org.itstep.sport.service.dto.response.TraineeSaveResponse;
import org.itstep.sport.service.dto.response.UpdateTraineeResponse;
import org.itstep.sport.service.dto.response.TraineeCabinetResponse;
import org.itstep.sport.service.mapper.TraineeMapper;
import org.itstep.sport.service.model.Trainee;
import org.itstep.sport.service.service.CabinetService;
import org.itstep.sport.service.service.TraineeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/trainees")
@RequiredArgsConstructor
public class TraineeController {

    private final TraineeService traineeService;
    private final CabinetService<Trainee> cabinetService;
    private final TraineeMapper traineeMapper;

    @PreAuthorize("hasAnyRole('ROLE_COACH', 'ROLE_ADMIN')")
    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<TraineeDTO>> findAllTrainees() {
        List<TraineeDTO> trainees = traineeService.findAllDto();

        return ResponseEntity.ok(trainees);
    }

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

        Trainee updatedTrainee = cabinetService.update(trainee, username);

        return ResponseEntity.ok(traineeMapper.mapToUpdateTraineeResponseFromTrainee(updatedTrainee));
    }

    @PreAuthorize("hasAnyRole('ROLE_TRAINEE')")
    @GetMapping(
            value = "/cabinet",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<TraineeCabinetResponse> getUserCabinet(
            @AuthenticationPrincipal String username) {
        Trainee trainee = cabinetService.getUserCabinet(username);

        TraineeCabinetResponse response = traineeMapper.mapToTraineeCabinetResponse(trainee);

        return ResponseEntity.ok(response);
    }
}
