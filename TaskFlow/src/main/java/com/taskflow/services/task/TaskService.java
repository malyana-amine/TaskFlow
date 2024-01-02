package com.taskflow.services.task;

import com.taskflow.entities.Tags;
import com.taskflow.entities.Task;

import java.util.Set;

public interface TaskService {
    Task createTask(Task task, Set<Tags> tag);

    void addTagToTask(Task task, Set<Tags> tag);
}
