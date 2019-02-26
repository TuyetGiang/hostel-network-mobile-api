package com.giang.rest_api.impl;

import com.giang.rest_api.PostApi;
import com.giang.service.PostService;
import com.giang.service.dto.PostDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PostController implements PostApi {

    @Autowired
    PostService postService;

    @Override
    public ResponseEntity<List<PostDTO>> getAll() {
        return ResponseEntity.ok(postService.getAll());
    }

    @Override
    public ResponseEntity<PostDTO> getPost(@PathVariable("id")Integer id) {
        return ResponseEntity.ok(postService.getPost(id));
    }

    @Override
    public ResponseEntity<PostDTO> createNewPost(@RequestBody PostDTO newPost) {
        return ResponseEntity.ok(postService.createNewPost(newPost));
    }

    @Override
    public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO dto) {
        return ResponseEntity.ok(postService.updatePost(dto));
    }

    @Override
    public ResponseEntity<List<PostDTO>> filterPost(@RequestParam(value = "benefits", required = false) List<Integer> benefitIds,
                                                    @RequestParam(value = "type", required = false) Integer typeId,
                                                    @RequestParam(value = "location", required = false) String location) {
        return ResponseEntity.ok(postService.fillterPost(benefitIds, typeId, location) );
    }
}
