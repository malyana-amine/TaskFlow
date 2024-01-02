package com.taskflow.controller;

import com.taskflow.dto.mapper.TagMapper;
import com.taskflow.dto.mapper.TaskMapper;
import com.taskflow.dto.request.CreateTaskRequest;
import com.taskflow.entities.Tags;
import com.taskflow.entities.Task;
import com.taskflow.services.task.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("api/v1/task")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService userTaskService;
    @PostMapping
    public void createTask(@RequestBody
                                          CreateTaskRequest createTaskRequest)
            throws InstantiationException, IllegalAccessException {
        Task task = TaskMapper.INSTANCE.newInstance().createTaskRequestToTask(createTaskRequest);
        Set<Tags> tags = TagMapper.INSTANCE.newInstance().tagDtoListToTagList(createTaskRequest.tags());
        Task taskSaved = userTaskService.createTask(task, tags);
        System.out.println(taskSaved);
    }
}
