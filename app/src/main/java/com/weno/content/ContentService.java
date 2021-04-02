package com.weno.content;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class ContentService {

    private final ContentRepository contentRepository;

    public ContentService(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    public void getContent(Long existed_id) {
        return;
    }
}
