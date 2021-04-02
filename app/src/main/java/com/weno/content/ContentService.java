package com.weno.content;

import com.weno.content.dto.ContentResponseDto;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class ContentService {

    private final ContentRepository contentRepository;

    public ContentService(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    public ContentResponseDto getContent(Long existed_id) {
        return null;
    }
}
