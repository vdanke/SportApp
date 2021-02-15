package org.itstep.sport.service.mapper;

import org.itstep.sport.service.dto.request.TraineeSaveRequest;
import org.itstep.sport.service.dto.request.UpdateTraineeRequest;
import org.itstep.sport.service.dto.response.TraineeSaveResponse;
import org.itstep.sport.service.dto.response.UpdateTraineeResponse;
import org.itstep.sport.service.dto.response.UserCabinetResponse;
import org.itstep.sport.service.model.Trainee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

@Mapper
public interface TraineeMapper {

    Trainee mapToTraineeFromTraineeSaveRequest(TraineeSaveRequest request);
    TraineeSaveResponse mapToTraineeSaveResponseFromTrainee(Trainee trainee);
    Trainee mapToTraineeFromTraineeUpdateRequest(UpdateTraineeRequest request);
    UpdateTraineeResponse mapToUpdateTraineeResponseFromTrainee(Trainee trainee);
    @Mappings({
            @Mapping(target = "existing.id", ignore = true),
            @Mapping(target = "existing.authority", ignore = true),
            @Mapping(target = "existing.username", ignore = true),
            @Mapping(target = "existing.password", ignore = true),
            @Mapping(target = "existing.coaches", ignore = true),
            @Mapping(target = "existing.comments", ignore = true),
    })
    void updateExistingTrainee(@MappingTarget Trainee existing, Trainee from);

    UserCabinetResponse mapToTraineeCabinetResponse(Trainee trainee);
}
