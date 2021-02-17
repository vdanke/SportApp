package org.itstep.sport.service.controller;

import lombok.RequiredArgsConstructor;
import org.itstep.sport.service.dto.CoachDTO;
import org.itstep.sport.service.dto.request.CoachSaveRequest;
import org.itstep.sport.service.dto.request.CoachUpdateRequest;
import org.itstep.sport.service.dto.response.CoachCabinetResponse;
import org.itstep.sport.service.dto.response.CoachSaveResponse;
import org.itstep.sport.service.dto.response.CoachUpdateResponse;
import org.itstep.sport.service.mapper.CoachMapper;
import org.itstep.sport.service.model.Coach;
import org.itstep.sport.service.service.CabinetService;
import org.itstep.sport.service.service.CoachService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.util.List;

@RestController
@RequestMapping("/api/v1/coaches")
@RequiredArgsConstructor
public class CoachController {

    private final CoachService coachService;
    private final CabinetService<Coach> cabinetService;
    private final CoachMapper coachMapper;

    @PreAuthorize("hasAnyRole('ROLE_TRAINEE', 'ROLE_ADMIN')")
    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<CoachDTO>> findAllCoaches() {
        List<CoachDTO> coachDTOList = coachService.findAllDto();

        return ResponseEntity.ok(coachDTOList);
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CoachSaveResponse> saveCoach(
            @Valid @RequestBody CoachSaveRequest request) {
        Coach coach = coachMapper.mapToCoachFromCoachSaveRequest(request);

        Coach savedCoach = coachService.save(coach);

        CoachSaveResponse response = coachMapper.mapToCoachSaveResponse(savedCoach);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PreAuthorize("hasAnyRole('ROLE_COACH')")
    @GetMapping(
            path = "/cabinet",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CoachCabinetResponse> getCoachCabinet(
            @AuthenticationPrincipal String username
    ) {
        Coach coach = cabinetService.getUserCabinet(username);

        CoachCabinetResponse response = coachMapper.mapToCoachCabinetResponse(coach);

        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAnyRole('ROLE_COACH')")
    @PutMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CoachUpdateResponse> updateCoach(
            @Valid @RequestBody CoachUpdateRequest request,
            @AuthenticationPrincipal String username
    ) {
        Coach coach = coachMapper.mapToCoachFromCoachUpdateRequest(request);

        Coach updatedCoach = cabinetService.update(coach, username);

        CoachUpdateResponse response = coachMapper.mapToCoachUpdateResponse(updatedCoach);

        return ResponseEntity.ok(response);
    }
}
