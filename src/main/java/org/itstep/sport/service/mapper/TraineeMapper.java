package org.itstep.sport.service.mapper;

import org.itstep.sport.service.dto.request.TraineeSaveRequest;
import org.itstep.sport.service.dto.request.UpdateTraineeRequest;
import org.itstep.sport.service.dto.response.TraineeSaveResponse;
import org.itstep.sport.service.dto.response.UpdateTraineeResponse;
import org.itstep.sport.service.model.Trainee;
import org.itstep.sport.service.model.Trainee_;
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
            @Mapping(source = Trainee_.ID, target = Trainee_.ID, ignore = true),
            @Mapping(source = Trainee_.AUTHORITY, target = Trainee_.AUTHORITY, ignore = true),
            @Mapping(source = Trainee_.USERNAME, target = Trainee_.USERNAME, ignore = true),
            @Mapping(source = Trainee_.PASSWORD, target = Trainee_.PASSWORD, ignore = true),
            @Mapping(source = Trainee_.COACHES, target = Trainee_.COACHES, ignore = true),
            @Mapping(source = Trainee_.COMMENTS, target = Trainee_.COMMENTS, ignore = true)
    })
    void updateExistingTrainee(@MappingTarget Trainee existing, Trainee from);
}
