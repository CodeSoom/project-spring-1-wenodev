package com.weno.content;

import com.weno.content.dto.ContentRequestDto;
import com.weno.content.dto.ContentResponseDto;
import com.weno.content.exception.ContentNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class ContentService {

    private final ContentRepository contentRepository;

    public ContentService(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    public ContentResponseDto detail(Long id) {
        Content content = contentRepository.findById(id).orElseThrow(()->new ContentNotFoundException("no content id : " + id));
        return ContentResponseDto.of(content);
    }

    public List<ContentResponseDto> list() {
        List<Content> contents = contentRepository.findAll();
        return ContentResponseDto.ofList(contents);
    }

    public ContentResponseDto create(ContentRequestDto request) {
        Content content = ContentRequestDto.toEntity(request);
        contentRepository.save(content);
        return ContentResponseDto.of(content);
    }

    public ContentResponseDto update(Long id, ContentRequestDto request) {
        Content content = contentRepository.findById(id).orElseThrow(()->new ContentNotFoundException("no content id : " + id));
        content.updateAll(request.getQuestion(), request.getAnswer(), request.getUserAnswer());
        return ContentResponseDto.of(content);
    }

    public ContentResponseDto delete(Long id) {
        Content content = contentRepository.findById(id).orElseThrow(()->new ContentNotFoundException("no content id : " + id));
        contentRepository.delete(content);
        return ContentResponseDto.of(content);
    }
}
