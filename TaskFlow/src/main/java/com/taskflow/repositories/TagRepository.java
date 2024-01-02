package com.taskflow.repositories;

import com.taskflow.entities.Tags;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tags, Long> {
}
