package com.weno.content;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContentController {
    private final ContentService contentService;

    public ContentController(ContentService contentService) {
        this.contentService = contentService;
    }
}
