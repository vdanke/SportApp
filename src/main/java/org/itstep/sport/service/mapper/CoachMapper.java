package org.itstep.sport.service.mapper;

import org.itstep.sport.service.dto.request.CoachSaveRequest;
import org.itstep.sport.service.dto.request.CoachUpdateRequest;
import org.itstep.sport.service.dto.response.CoachCabinetResponse;
import org.itstep.sport.service.dto.response.CoachSaveResponse;
import org.itstep.sport.service.dto.response.CoachUpdateResponse;
import org.itstep.sport.service.model.Coach;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

@Mapper
public interface CoachMapper {

    Coach mapToCoachFromCoachSaveRequest(CoachSaveRequest request);
    CoachSaveResponse mapToCoachSaveResponse(Coach savedCoach);
    CoachCabinetResponse mapToCoachCabinetResponse(Coach coach);
    Coach mapToCoachFromCoachUpdateRequest(CoachUpdateRequest request);
    CoachUpdateResponse mapToCoachUpdateResponse(Coach updatedCoach);

    @Mappings({
            @Mapping(target = "coachFromDb.username", ignore = true),
            @Mapping(target = "coachFromDb.trainees", ignore = true),
            @Mapping(target = "coachFromDb.posts", ignore = true),
            @Mapping(target = "coachFromDb.id", ignore = true),
            @Mapping(target = "coachFromDb.password", ignore = true),
            @Mapping(target = "coachFromDb.authority", ignore = true)
    })
    void updateExistingCoach(@MappingTarget Coach coachFromDb, Coach coach);
}
