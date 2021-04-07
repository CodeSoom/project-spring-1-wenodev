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
    public ContentResponseDto getContent(@PathVariable Long id){
        return contentService.getContent(id);
    }

    @GetMapping
    public List<ContentResponseDto> getAllContent(){
        return contentService.getAllContents();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ContentResponseDto saveContent(@RequestBody ContentRequestDto request){
        return contentService.saveContent(request);
    }

    @PutMapping("/{id}")
    public ContentResponseDto updateContent(@PathVariable Long id, @RequestBody ContentRequestDto request){
        return contentService.updateContent(id, request);
    }

    @DeleteMapping("/{id}")
    public ContentResponseDto deleteContent(@PathVariable Long id){
        return contentService.deleteContent(id);
    }

}
