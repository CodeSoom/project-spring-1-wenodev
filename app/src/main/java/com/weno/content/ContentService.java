package com.weno.content;

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

    public ContentResponseDto getContent(Long id) {
        Content content = contentRepository.findById(id).orElseThrow(()->new ContentNotFoundException("no content id : " + id));
        return ContentResponseDto.of(content);
    }

    public List<ContentResponseDto> getAllContents() {
        return null;
    }
}
