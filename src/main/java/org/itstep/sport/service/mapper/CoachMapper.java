package org.itstep.sport.service.mapper;

import org.itstep.sport.service.dto.request.CoachSaveRequest;
import org.itstep.sport.service.dto.response.CoachSaveResponse;
import org.itstep.sport.service.model.Coach;
import org.mapstruct.Mapper;

@Mapper
public interface CoachMapper {

    Coach mapToCoachFromCoachSaveRequest(CoachSaveRequest request);
    CoachSaveResponse mapToCoachSaveResponse(Coach savedCoach);
}
