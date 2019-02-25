package com.giang.rest_api.impl;

import com.giang.rest_api.PostApi;
import com.giang.service.dto.PostDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PostController implements PostApi {
    @Override
    public ResponseEntity<List<PostDTO>> getAllPost() {
        return ResponseEntity.ok(new ArrayList<>());
    }

    @Override
    public ResponseEntity<PostDTO> findAPost(Integer id) {
        return ResponseEntity.ok(new PostDTO());
    }

    @Override
    public ResponseEntity<PostDTO> updateAPost(Integer id, String key, Object value) {
        return ResponseEntity.ok(new PostDTO());
    }

    @Override
    public ResponseEntity<Boolean> deleteAPost(Integer id) {
        return ResponseEntity.ok(true);
    }

    @Override
    public ResponseEntity<PostDTO> createNewPost(PostDTO newPost) {
        return ResponseEntity.ok(new PostDTO());
    }

    @Override
    public ResponseEntity<List<PostDTO>> searchPost(String conditions) {
        return ResponseEntity.ok(new ArrayList<>());
    }

    @Override
    public ResponseEntity<List<PostDTO>> getPostByUser(Integer userId) {
        return ResponseEntity.ok(new ArrayList<>());
    }
}
