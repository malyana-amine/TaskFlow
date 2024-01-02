package com.taskflow.services.tags;

import com.taskflow.entities.Tags;

import java.util.List;
import java.util.Set;

public interface TagsService {
    List<Tags> createTags(Set<Tags> tags);
}
