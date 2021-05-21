package com.weno.content;

import com.weno.content.dto.ContentRequestDto;
import com.weno.content.dto.ContentResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/v1/content")
@RestController
public class ContentController {
    private final ContentService contentService;

    public ContentController(ContentService contentService) {
        this.contentService = contentService;
    }

    @GetMapping("/{id}")
    public ContentResponseDto detail(@PathVariable Long id){
        return contentService.detail(id);
    }

    @GetMapping
    public List<ContentResponseDto> list(){
        return contentService.list();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ContentResponseDto create(@RequestBody ContentRequestDto request){
        return contentService.create(request);
    }

    @PutMapping("/{id}")
    public ContentResponseDto update(@PathVariable Long id, @RequestBody ContentRequestDto request){
        return contentService.update(id, request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public ContentResponseDto delete(@PathVariable Long id){
        return contentService.delete(id);
    }

}
