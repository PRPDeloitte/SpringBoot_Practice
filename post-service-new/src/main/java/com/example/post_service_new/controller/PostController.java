package com.example.post_service_new.controller;

import com.example.post_service_new.dto.PostDTO;
import com.example.post_service_new.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostDTO> create(@RequestBody PostDTO dto){
        return ResponseEntity.ok(postService.create(dto));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PostDTO>> getByUserId(@PathVariable Long userId){
        return ResponseEntity.ok(postService.getPostByUserId(userId));
    }

    @GetMapping
    public ResponseEntity<List<PostDTO>> getAll(){
        return ResponseEntity.ok(postService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getById(@PathVariable Long id){
        return ResponseEntity.ok(postService.getById(id));
    }

    @GetMapping("/grouped")
    public ResponseEntity<Map<Long,List<PostDTO>>> groupedByUser(){
        return ResponseEntity.ok(postService.groupedPostsByUserId());
    }

    @GetMapping("/grouped/nested")
    public ResponseEntity<Map<Long,Map<String,List<PostDTO>>>> nestedGroup(){
        return ResponseEntity.ok(postService.nestedGroupByUserAndCategory());
    }




}
