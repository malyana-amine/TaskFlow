package com.taskflow.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String title;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean taskPassed;
    private TaskStatus taskStatus;

    @ManyToOne
    @JoinColumn(name = "user_id") // This is the foreign key in the Task table
    private Users user;

    @ManyToMany
    @JoinTable(name = "Task_tags",
            joinColumns = @JoinColumn(name = "task_id"))
    private Set<Tags> tags = new LinkedHashSet<>();

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TaskReplacement> taskReplacements = new LinkedHashSet<>();
}