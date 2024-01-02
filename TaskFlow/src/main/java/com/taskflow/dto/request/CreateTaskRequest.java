package com.taskflow.dto.request;

import java.time.LocalDate;
import java.util.Set;

public record CreateTaskRequest(
        String title,
        String description,
        LocalDate startDate,
        LocalDate endDate,
        Set<TagRequestDto> tags

) {
}
