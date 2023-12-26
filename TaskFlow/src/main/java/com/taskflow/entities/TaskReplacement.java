package com.taskflow.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskReplacement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "task_id") // This is the foreign key in the TaskReplacement table
    private Task task;

    private TaskReplacementStatus taskReplacementStatus;
}
