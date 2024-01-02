package com.taskflow.services.tags;

import com.taskflow.entities.Tags;
import com.taskflow.repositories.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TagsServiceImpl implements TagsService {
    private final TagRepository tagRepository;

    @Override
    public List<Tags> createTags(Set<Tags> tags) {
        Set<Tags> tagFiltered = filterTags(tags);
        return tagRepository.saveAll(tagFiltered);
    }
    public List<Tags> fetchAllTags(){
        return tagRepository.findAll();
    }
    private Set<Tags> filterTags(Set<Tags> tags){
        List<String> existingTags = fetchAllTags()
                .stream()
                .map(Tags::getTagName)
                .toList();
        return tags.stream()
                .filter(tag -> !existingTags.contains(tag.getTagName()))
                .collect(Collectors.toSet());
    }
}
