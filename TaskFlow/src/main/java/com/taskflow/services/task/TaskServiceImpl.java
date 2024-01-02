package com.taskflow.services.task;

import com.taskflow.entities.Tags;
import com.taskflow.entities.Task;
import com.taskflow.entities.TaskStatus;
import com.taskflow.exeption.CustomValidationException;
import com.taskflow.repositories.TaskRepository;
import com.taskflow.services.tags.TagsService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Set;
@Service
@RequiredArgsConstructor
@Transactional
public class TaskServiceImpl implements TaskService {

        private final TaskRepository taskRepository;
        private final TagsService tagService;


    @Override
    public Task createTask(Task task, Set<Tags> tag){
        validateStartDateTask(task.getStartDate());
        validateEndDateTask(task.getStartDate(), task.getEndDate());
        task.setTaskStatus(TaskStatus.TODO);
        if (tag.size() < 2)
            throw new CustomValidationException("you should at least give 2 tag for this task");
        Task tasksaved = taskRepository.save(task);
        addTagToTask(tasksaved, tag);
        return tasksaved;
    }

    @Override
    public void addTagToTask(Task task, Set<Tags> tag){
        List<Tags> tagsSaved = tagService.createTags(tag);
        task.setTags((Set<Tags>) tagsSaved);
    }



    private void validateStartDateTask(LocalDate startDate) {
        if (startDate.isBefore(LocalDate.now()))
            throw new CustomValidationException("start Date of task shouldn't be before or equals time now");
    }

    private void validateEndDateTask(LocalDate startDate, LocalDate endDate) {
        if (endDate.isBefore(startDate.plus(3, ChronoUnit.DAYS)))
            throw new CustomValidationException("The end date and time of the task must be at least 3 days after the start date and time.");
    }

}
