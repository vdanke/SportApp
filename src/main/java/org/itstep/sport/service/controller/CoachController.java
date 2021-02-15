package org.itstep.sport.service.controller;

import lombok.RequiredArgsConstructor;
import org.itstep.sport.service.dto.CoachDTO;
import org.itstep.sport.service.dto.request.CoachSaveRequest;
import org.itstep.sport.service.dto.response.CoachSaveResponse;
import org.itstep.sport.service.mapper.CoachMapper;
import org.itstep.sport.service.model.Coach;
import org.itstep.sport.service.service.CoachService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/coaches")
@RequiredArgsConstructor
public class CoachController {

    private final CoachService coachService;
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
}
