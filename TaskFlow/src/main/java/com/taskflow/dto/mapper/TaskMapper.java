package com.taskflow.dto.mapper;

import com.taskflow.dto.request.CreateTaskRequest;
import com.taskflow.entities.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import org.mapstruct.factory.Mappers;


@Mapper
public interface TaskMapper {
    Class<? extends TaskMapper> INSTANCE = Mappers.getMapperClass(TaskMapper.class);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "taskStatus", ignore = true)
    @Mapping(target = "tags", ignore = true)
    Task createTaskRequestToTask(CreateTaskRequest createTaskRequest);

}