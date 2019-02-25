package com.giang.rest_api;

import com.giang.service.dto.PostDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@RequestMapping("/posts")
public interface PostApi {
    @ApiOperation(tags = {"POST"}, response = PostDTO.class, value = "Get all posts")
    @GetMapping("")
    ResponseEntity<List<PostDTO>> getAllPost();

    @ApiOperation(tags = {"POST"}, response = PostDTO.class, value = "Get a post by Id")
    @GetMapping("/{id}")
    ResponseEntity<PostDTO> findAPost(@PathVariable("id") Integer id);

    @ApiOperation(tags = {"POST"}, response = PostDTO.class, value = "Update a post by Id")
    @PutMapping("/{id}")
    ResponseEntity<PostDTO> updateAPost(@PathVariable("id") Integer id,
                                        @RequestParam("key") String key,
                                        @RequestParam("value") Object value);

    @ApiOperation(tags = {"POST"}, response = PostDTO.class, value = "Delete all post by Id")
    @DeleteMapping("/{id}")
    ResponseEntity<Boolean> deleteAPost(@PathVariable("id") Integer id);

    @ApiOperation(tags = {"POST"}, response = PostDTO.class, value = "Create new post")
    @PostMapping("")
    ResponseEntity<PostDTO> createNewPost(@RequestBody PostDTO newPost);

    @ApiOperation(tags = {"POST"}, response = PostDTO.class, value = "Filter a post by list conditions")
    @GetMapping("/conditions")
    ResponseEntity<List<PostDTO>> searchPost(@RequestParam String conditions);

    @ApiOperation(tags = {"POST"}, response = PostDTO.class, value = "Get all posts of an user")
    @GetMapping("/{user-id")
    ResponseEntity<List<PostDTO>> getPostByUser(@PathVariable("user-id") Integer userId);
}
