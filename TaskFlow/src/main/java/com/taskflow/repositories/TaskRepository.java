package com.taskflow.repositories;

import com.taskflow.entities.Task;

import org.springframework.data.jpa.repository.JpaRepository;


public interface TaskRepository extends JpaRepository<Task, Long> {
}
